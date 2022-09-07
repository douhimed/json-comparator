package org.adex.utilities;

import lombok.extern.slf4j.Slf4j;
import org.adex.dtos.Node;
import org.adex.services.JsonComparator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;
import java.util.function.Consumer;

public final class JsonUtils {

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

    public static Map<String, String> asMap(String json) {
        try {
            return (Map) new JSONParser().parse(json, CONTAINER_FACTORY);
        } catch (ParseException pe) {
            System.err.println("Error [JsonUtils.asMap] " + pe.getErrorType() + ", Message : " + pe.getMessage());
            return Collections.emptyMap();
        }
    }

    public static Collection<String> asArray(String json) {
        try {
            return (List) new JSONParser().parse(json, CONTAINER_FACTORY);
        } catch (ParseException pe) {
            System.err.println("Error [JsonUtils.asMap] " + pe.getErrorType() + ", Message : " + pe.getMessage());
            return Collections.emptyList();
        }
    }

    public static boolean isOfType(Consumer<String> consumer, String json) {
        try {
            consumer.accept(json);
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

}
