/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2019 Jakub Wach
 */

package eu.lunisolar.magma.basics.exceptions;

/**
 * @author Jakub Wach
 */
public class NotYetImplementedException extends RE {
    public NotYetImplementedException() {
    }
    public NotYetImplementedException(String message) {
        super(message);
    }
    public NotYetImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotYetImplementedException(Throwable cause) {
        super(cause);
    }
    public NotYetImplementedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
