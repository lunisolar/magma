/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.fluent.FluentSyntax;

import java.io.*;
import java.util.*;

import static eu.lunisolar.magma.basics.exceptions.WrapingHandler.handler;

/**
 * Here are abbreviations for the most commonly used exceptions.
 *
 * @author Jakub Wach
 */
public class X implements FluentSyntax {

    //<editor-fold desc="no instance">
    private X() {
    }
    //</editor-fold>

    public static final WrapingHandler<NestedException> nested = handler(NestedException.class, NestedException::new, NestedException::new);

    public static NestedException nested(String message, Throwable cause) { return new NestedException(message, cause);}
    public static NestedException nested(Throwable cause)                 { return new NestedException(cause);}

    public static final WrapingHandler<RuntimeException> re = handler(RuntimeException.class, RuntimeException::new, RuntimeException::new);

    public static RuntimeException RE()                                { return new RuntimeException(); }
    public static RuntimeException RE(String message)                  { return new RuntimeException(message);}
    public static RuntimeException RE(String message, Throwable cause) { return new RuntimeException(message, cause);}
    public static RuntimeException RE(Throwable cause)                 { return new RuntimeException(cause);}

    public static final WrapingHandler<IllegalArgumentException> arg =
            handler(IllegalArgumentException.class, IllegalArgumentException::new, IllegalArgumentException::new);

    public static IllegalArgumentException arg()                                { return new IllegalArgumentException(); }
    public static IllegalArgumentException arg(String message)                  { return new IllegalArgumentException(message);}
    public static IllegalArgumentException arg(String message, Throwable cause) { return new IllegalArgumentException(message, cause);}
    public static IllegalArgumentException arg(Throwable cause)                 { return new IllegalArgumentException(cause);}

    public static final WrapingHandler<IllegalStateException> state =
            handler(IllegalStateException.class, IllegalStateException::new, IllegalStateException::new);

    public static IllegalStateException state()                                { return new IllegalStateException(); }
    public static IllegalStateException state(String message)                  { return new IllegalStateException(message);}
    public static IllegalStateException state(String message, Throwable cause) { return new IllegalStateException(message, cause);}
    public static IllegalStateException state(Throwable cause)                 { return new IllegalStateException(cause);}

    public static final WrapingHandler<UnsupportedOperationException> unsupported =
            handler(UnsupportedOperationException.class, UnsupportedOperationException::new, UnsupportedOperationException::new);

    public static UnsupportedOperationException unsupported()                                { return new UnsupportedOperationException(); }
    public static UnsupportedOperationException unsupported(String message)                  { return new UnsupportedOperationException(message);}
    public static UnsupportedOperationException unsupported(String message, Throwable cause) { return new UnsupportedOperationException(message, cause);}
    public static UnsupportedOperationException unsupported(Throwable cause)                 { return new UnsupportedOperationException(cause);}

    public static final WrapingHandler<IOException> io = handler(IOException.class, IOException::new, IOException::new);

    public static IOException io()                                     { return new IOException(); }
    public static IOException io(String message)                       { return new IOException(message);}
    public static IOException io(String message, Throwable cause)      { return new IOException(message, cause);}
    public static IOException io(Throwable cause)                      { return new IOException(cause);}

    public static NullPointerException npe()                           { return new NullPointerException(); }
    public static NullPointerException npe(String message)             { return new NullPointerException(message);}

    public static NumberFormatException number()                       { return new NumberFormatException(); }
    public static NumberFormatException number(String message)         { return new NumberFormatException(message);}

    public static InterruptedException interrupted()                   { return new InterruptedException(); }
    public static InterruptedException interrupted(String message)     { return new InterruptedException(message);}

    public static ArithmeticException ar()                             { return new ArithmeticException(); }
    public static ArithmeticException ar(String message)               { return new ArithmeticException(message);}

    public static NoSuchElementException noSuchElement()               { return new NoSuchElementException(); }
    public static NoSuchElementException noSuchElement(String message) { return new NoSuchElementException(message);}

}