/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics.exceptions.re;

import eu.lunisolar.magma.basics.exceptions.RE;

/**
 * Convenient RE replacement for {@link java.io.UnsupportedEncodingException}
 * @author Jakub Wach
 */
public class UnsupportedEncodingRE extends RE {
    public UnsupportedEncodingRE() {
    }
    public UnsupportedEncodingRE(String message) {
        super(message);
    }
    public UnsupportedEncodingRE(String message, Throwable cause) {
        super(message, cause);
    }
    public UnsupportedEncodingRE(Throwable cause) {
        super(cause);
    }
    public UnsupportedEncodingRE(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
