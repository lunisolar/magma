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

package eu.lunisolar.magma.basics.exceptions;

class OriginalRuntimeException extends RuntimeException {

    public OriginalRuntimeException(String message) {
        super(message);
    }
}

class RuntimeException1 extends RuntimeException {

    public RuntimeException1(String message) {
        super(message);
    }

    public RuntimeException1(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeException1(Throwable cause) {
        super(cause);
    }

}

class RuntimeException2 extends RuntimeException {
    public RuntimeException2(String message) {
        super(message);
    }

    public RuntimeException2(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeException2(Throwable cause) {
        super(cause);
    }
}

class OriginalException extends Exception {

    public OriginalException(String message) {
        super(message);
    }
}

class Exception1 extends Exception {

    public Exception1(String message) {
        super(message);
    }

    public Exception1(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception1(Throwable cause) {
        super(cause);
    }
}

class Exception2 extends Exception {
    public Exception2(String message) {
        super(message);
    }

    public Exception2(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception2(Throwable cause) {
        super(cause);
    }
}
