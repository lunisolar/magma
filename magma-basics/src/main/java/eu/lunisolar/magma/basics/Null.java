/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
