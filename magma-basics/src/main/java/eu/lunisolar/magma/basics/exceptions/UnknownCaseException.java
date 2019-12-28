/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2019 Jakub Wach
 */

package eu.lunisolar.magma.basics.exceptions;

/**
 * @author Jakub Wach
 */
public class UnknownCaseException extends RE {
    public UnknownCaseException() {
    }
    public UnknownCaseException(String message) {
        super(message);
    }
    public UnknownCaseException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnknownCaseException(Throwable cause) {
        super(cause);
    }
    public UnknownCaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
