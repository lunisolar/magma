/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.basics;

import java.util.*;
import java.util.function.*;

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

    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }

    // <editor-fold desc="no-instance constructor">

    private Null() {
    }

    // </editor-fold>

}
