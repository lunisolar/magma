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

package eu.lunisolar.magma.basics.probing;

import javax.naming.NoInitialContextException;
import java.util.*;

class Internal {

    // <editor-fold desc="internals">

    static boolean isItemInArray(Object item, Object[] arrayOfValues) {
        if (arrayOfValues == null) {
            return false;
        }

        for (Object value : arrayOfValues) {
            if (Objects.equals(value, item)) {
                return true;
            }
        }
        return false;
    }

    static Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() == null) {
            return null;
        }

        Throwable cause;
        while ((cause = throwable.getCause()) != null) {
            throwable = cause;
        }
        return throwable;
    }

    // </editor-fold>

    private Internal() {
    }
}
