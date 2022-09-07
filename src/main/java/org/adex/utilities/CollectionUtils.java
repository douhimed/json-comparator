package org.adex.utilities;

import java.util.*;

public final class CollectionUtils<T> {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static boolean equals(Collection<String> collection1, Collection<String> collection2) {
        return collection1.size() == collection2.size() && collection2.containsAll(collection1);
    }

    public static boolean equals(Map<String, String> actual, Map<String, String> expected) {
        return actual.size() == expected.size()
                && actual.entrySet()
                        .stream()
                        .allMatch(entry -> expected.containsKey(entry.getKey()) && expected.get(entry.getKey()).equals(entry.getValue()));
    }

}
