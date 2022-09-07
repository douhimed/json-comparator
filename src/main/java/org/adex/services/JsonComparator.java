package org.adex.services;

public interface JsonComparator {

    boolean isObjectType(String json);

    boolean isArrayType(String json);

    boolean assertEquals(String actual, String expected);

    enum JsonNodeType {
        ARRAY, OBJECT, BASIC;

        public boolean isObjectType() {
            return this == OBJECT;
        }

        public boolean isArrayType() {
            return this == ARRAY;
        }
    }
}
