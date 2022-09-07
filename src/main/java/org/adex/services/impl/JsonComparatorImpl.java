package org.adex.services.impl;

import org.adex.services.JsonComparator;
import org.adex.utilities.CollectionUtils;
import org.adex.utilities.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonComparatorImpl implements JsonComparator {

    @Override
    public boolean isObjectType(String json) {
        return JsonUtils.isOfType(s -> {
            var v = (JSONObject) JSONValue.parse(s);
        }, json);
    }

    @Override
    public boolean isArrayType(String json) {
        return JsonUtils.isOfType(s -> {
            var v = (JSONArray) JSONValue.parse(s);
        }, json);
    }

    @Override
    public boolean assertEquals(String actual, String expected) {
        final JsonNodeType actualType = getJsonType(actual);
        final JsonNodeType expectedType = getJsonType(expected);

        if (!actualType.equals(expectedType))
            return false;

        if (actualType.isObjectType())
            return CollectionUtils.equals(JsonUtils.asMap(actual), JsonUtils.asMap(expected));

        return actualType.isArrayType() ? CollectionUtils.equals(JsonUtils.asArray(actual), JsonUtils.asArray(expected)) : actual.equals(expected);
    }

    private JsonComparator.JsonNodeType getJsonType(String json) {
        if (isObjectType(json)) return JsonComparator.JsonNodeType.OBJECT;
        return isArrayType(json) ? JsonComparator.JsonNodeType.ARRAY : JsonComparator.JsonNodeType.BASIC;
    }

}
