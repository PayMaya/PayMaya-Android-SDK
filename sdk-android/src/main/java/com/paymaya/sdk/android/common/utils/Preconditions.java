/*
 * Copyright (c) 2016 PayMaya Philippines, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.paymaya.sdk.android.common.utils;

import java.util.Arrays;
import java.util.Locale;

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
