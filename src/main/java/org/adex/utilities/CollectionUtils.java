package org.adex.utilities;

import java.util.*;

public final class CollectionUtils<T> {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return  !isEmpty(collection);
    }

}
