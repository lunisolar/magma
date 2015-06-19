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
