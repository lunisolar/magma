/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2018 Jakub Wach
 */

package eu.lunisolar.magma.basics.exceptions;

/**
 * @author Jakub Wach
 */
public class RE extends RuntimeException {

    public RE() {
    }

    public RE(String message) {
        super(message);
    }

    public RE(String message, Throwable cause) {
        super(message, cause);
    }

    public RE(Throwable cause) {
        super(cause);
    }

    public RE(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
