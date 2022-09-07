package org.adex.utilities;

import java.util.Objects;

public final class StringUtils {

    public static boolean isBlank(final String value) {
        return Objects.isNull(value) || value.trim().length() == 0;
    }

    public static boolean isNotBlank(final String value) {
        return !isBlank(value);
    }

}
