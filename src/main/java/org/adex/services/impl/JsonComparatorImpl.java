package org.adex.services.impl;

import org.adex.services.JsonComparator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;
import java.util.function.Consumer;

public class JsonComparatorImpl implements JsonComparator {

    public static final ContainerFactory CONTAINER_FACTORY = new ContainerFactory() {
        @Override
        public Map<String, String> createObjectContainer() {
            return new LinkedHashMap<>();
        }

        @Override
        public List<String> creatArrayContainer() {
            return new LinkedList<>();
        }
    };

    @Override
    public boolean assertEquals(String actual, String expected) {
        final JsonNodeType actualType = getNodeType(actual);
        final JsonNodeType expectedType = getNodeType(expected);

        if (!actualType.equals(expectedType))
            return false;

        if (actualType.isObjectType())
            return assertMapEquals(asMap(actual), asMap(expected));

        return actualType.isArrayType() ? assertArraysEquals(asArray(actual), asArray(expected)) : actual.equals(expected);
    }

    private boolean assertArraysEquals(Collection<String> actual, Collection<String> expected) {
        return actual.size() == expected.size() && expected.containsAll(actual);
    }

    private boolean assertMapEquals(Map<String, String> actual, Map<String, String> expected) {

        if (actual.size() != expected.size())
            return false;

        return actual.entrySet()
                .stream()
                .allMatch(entry -> expected.containsKey(entry.getKey()) && expected.get(entry.getKey()).equals(entry.getValue()));
    }

    static Map<String, String> asMap(String json) {
        try {
            return (Map) new JSONParser().parse(json, CONTAINER_FACTORY);
        } catch (ParseException pe) {
            System.err.println("Error [JsonUtils.asMap] " + pe.getErrorType() + ", Message : " + pe.getMessage());
            return Collections.emptyMap();
        }
    }

    static Collection<String> asArray(String json) {
        try {
            return (List) new JSONParser().parse(json, CONTAINER_FACTORY);
        } catch (ParseException pe) {
            System.err.println("Error [JsonUtils.asMap] " + pe.getErrorType() + ", Message : " + pe.getMessage());
            return Collections.emptyList();
        }
    }

    public static boolean isObjectType(String json) {
        return isOfType(s -> {
            var v = (JSONObject) JSONValue.parse(s);
        }, json);
    }

    public static boolean isArrayType(String json) {
        return isOfType(s -> {
            var v = (JSONArray) JSONValue.parse(s);
        }, json);
    }

    private static boolean isOfType(Consumer<String> consumer, String json) {
        try {
            consumer.accept(json);
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    private static JsonNodeType getNodeType(String json) {
        if (isObjectType(json)) return JsonNodeType.OBJECT;
        return isArrayType(json) ? JsonNodeType.ARRAY : JsonNodeType.BASIC;
    }

    private enum JsonNodeType {
        ARRAY, OBJECT, BASIC;

        public boolean isObjectType() {
            return this == OBJECT;
        }

        public boolean isArrayType() {
            return this == ARRAY;
        }
    }
}
