package com.paymaya.sdk.android.common.utils;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by giaquino on 11/2/15.
 */
public final class Preconditions {

    private static final String TEMPLATE_NON_NULL  = "%s must be defined.";

    public static <T> T checkNotNull(T value, Object errorArg) {
        if (value == null) {
            throw new NullPointerException(String.format(TEMPLATE_NON_NULL, errorArg));
        }
        return value;
    }

    public static String checkNotNull(String value, Object errorArg) {
        if (value == null || value.length() <= 0) {
            throw new NullPointerException(String.format(TEMPLATE_NON_NULL, errorArg));
        }
        return value;
    }
    
    public static void checkArgument(boolean expression, String errorTemplate, Object errorArgs) {
        if (expression) return;
        throw new IllegalArgumentException(String.format(errorTemplate, errorArgs));
    }

    private static boolean checkValidNumber(String value) {
        return value.matches("[-+]?\\d*\\.?\\d+");
    }

    public static String checkValidCountryCode(final String value) {
        final String[] isoCountries = Locale.getISOCountries();
        if (!Arrays.asList(isoCountries).contains(value)) {
            throw new IllegalArgumentException(value + " is not a valid ISO 3166-1 alpha-2 country code");
        }
        return value;
    }
}
