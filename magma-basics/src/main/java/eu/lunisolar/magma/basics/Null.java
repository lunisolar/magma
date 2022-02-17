/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.basics;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;

@SuppressWarnings("unchecked")
public class Null {

    public static final String NULL_ARG_MESSAGE   = "Argument '%s' must not be null.";
    public static final String NULL_VALUE_MESSAGE = "Value of '%s' must not be null.";

    public static String argNullMessage(String argName) {
        return String.format(NULL_ARG_MESSAGE, argName);
    }

    public static String valueNullMessage(String valueName) {
        return String.format(NULL_VALUE_MESSAGE, valueName);
    }

    public static <T> T nonNullArg(T obj, String argName) {
        if (obj == null) {
            throw new NullPointerException(argNullMessage(argName));
        }
        return obj;
    }

    public static <T> T[] nonNullArg(String argName, T... obj) {
        if (obj == null) {
            throw new NullPointerException(argNullMessage(argName));
        }

        for (T t : obj) {
            nonNullArg(t, argName);
        }

        return obj;
    }

    public static <T> T nonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    public static <T> T nonNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }

    //<editor-fold desc="null safe comparable">

    public static <T extends Comparable> int compare(@Nullable T first, @Nullable T second) {
        return compare(first, second, Comparable::compareTo);
    }

    public static <T> int compare(@Nullable T first, @Nullable T second, @Nonnull Comparator<T> comparator) {
        return compare(first, second, false, comparator);
    }

    public static <T> int compare(T first, T second, boolean nullGreater, @Nonnull Comparator<T> comparator) {

        if (first == second) {
            return 0;
        } else if (first == null) {
            return nullGreater ? 1 : -1;
        } else if (second == null) {
            return nullGreater ? -1 : 1;
        }

        return comparator.compare(first, second);
    }

    //</editor-fold>

    //<editor-fold desc="null safe equals">

    public static <T> boolean equals(@Nullable T first, @Nullable Object second) {
        return equals(first, second, Object::equals);
    }

    /** Provides null safety. Compliance with rules regarding "equals" are left to the predicate. */
    public static <T> boolean equals(@Nullable T first, @Nullable Object second, @Nonnull BiPredicate<T, Object> equals) {

        if (first == second) {
            return true;
        } else if (first == null) {
            return false;
        } else if (second == null) {
            return false;
        }

        return equals.test(first, second);
    }

    //</editor-fold>

    //<editor-fold desc="null safe hash">

    public static int hashCode(Object the) {
        return the == null ? -1 : the.hashCode();
    }

    //</editor-fold>

    // <editor-fold desc="no-instance constructor">

    private Null() {
    }

    // </editor-fold>

}
