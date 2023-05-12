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

package eu.lunisolar.magma.basics.exceptions;

import eu.lunisolar.magma.basics.exceptions.re.InputOutputRE;
import eu.lunisolar.magma.basics.exceptions.re.MalformedUrlRE;
import eu.lunisolar.magma.basics.exceptions.re.ParseRE;
import eu.lunisolar.magma.basics.exceptions.re.UnsupportedEncodingRE;
import eu.lunisolar.magma.basics.fluent.FluentSyntax;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    public static @Nonnull NestedException nested(String message, Throwable cause) { return new NestedException(message, cause);}
    public static @Nonnull NestedException nested(Throwable cause)                 { return new NestedException(cause);}

    public static final WrapingHandler<RuntimeException> runtime = handler(RuntimeException.class, RuntimeException::new, RuntimeException::new);

    public static @Nonnull RuntimeException runtime()                                { return new RuntimeException(); }
    public static @Nonnull RuntimeException runtime(String message)                  { return new RuntimeException(message);}
    public static @Nonnull RuntimeException runtime(String message, Throwable cause) { return new RuntimeException(message, cause);}
    public static @Nonnull RuntimeException runtime(Throwable cause)                 { return new RuntimeException(cause);}

    public static final WrapingHandler<IllegalArgumentException> arg =
            handler(IllegalArgumentException.class, IllegalArgumentException::new, IllegalArgumentException::new);

    public static @Nonnull IllegalArgumentException arg()                                { return new IllegalArgumentException(); }
    public static @Nonnull IllegalArgumentException arg(String message)                  { return new IllegalArgumentException(message);}
    public static @Nonnull IllegalArgumentException arg(String message, Throwable cause) { return new IllegalArgumentException(message, cause);}
    public static @Nonnull IllegalArgumentException arg(Throwable cause)                 { return new IllegalArgumentException(cause);}

    public static final WrapingHandler<IllegalStateException> state =
            handler(IllegalStateException.class, IllegalStateException::new, IllegalStateException::new);

    public static @Nonnull IllegalStateException state()                                { return new IllegalStateException(); }
    public static @Nonnull IllegalStateException state(String message)                  { return new IllegalStateException(message);}
    public static @Nonnull IllegalStateException state(String message, Throwable cause) { return new IllegalStateException(message, cause);}
    public static @Nonnull IllegalStateException state(Throwable cause)                 { return new IllegalStateException(cause);}

    public static final WrapingHandler<IllegalValueException> value =
            handler(IllegalValueException.class, IllegalValueException::new, IllegalValueException::new);

    public static @Nonnull IllegalValueException value()                                { return new IllegalValueException(); }
    public static @Nonnull IllegalValueException value(String message)                  { return new IllegalValueException(message);}
    public static @Nonnull IllegalValueException value(String message, Throwable cause) { return new IllegalValueException(message, cause);}
    public static @Nonnull IllegalValueException value(Throwable cause)                 { return new IllegalValueException(cause);}

    public static final WrapingHandler<UnsupportedOperationException> unsupported =
            handler(UnsupportedOperationException.class, UnsupportedOperationException::new, UnsupportedOperationException::new);

    public static @Nonnull UnsupportedOperationException unsupported()               { return new UnsupportedOperationException(); }
    public static @Nonnull UnsupportedOperationException unsupported(String message) { return new UnsupportedOperationException(message);}
    public static @Nonnull UnsupportedOperationException unsupported(String message, Throwable cause) {
        return new UnsupportedOperationException(message, cause);
    }
    public static @Nonnull UnsupportedOperationException unsupported(Throwable cause) { return new UnsupportedOperationException(cause);}

    public static final WrapingHandler<IOException> io = handler(IOException.class, IOException::new, IOException::new);

    public static @Nonnull IOException io()                                     { return new IOException(); }
    public static @Nonnull IOException io(String message)                       { return new IOException(message);}
    public static @Nonnull IOException io(String message, Throwable cause)      { return new IOException(message, cause);}
    public static @Nonnull IOException io(Throwable cause)                      { return new IOException(cause);}

    public static @Nonnull NullPointerException npe()                           { return new NullPointerException(); }
    public static @Nonnull NullPointerException npe(String message)             { return new NullPointerException(message);}

    public static @Nonnull NumberFormatException number()                       { return new NumberFormatException(); }
    public static @Nonnull NumberFormatException number(String message)         { return new NumberFormatException(message);}

    public static @Nonnull InterruptedException interrupted()                   { return new InterruptedException(); }
    public static @Nonnull InterruptedException interrupted(String message)     { return new InterruptedException(message);}

    public static @Nonnull ArithmeticException ar()                             { return new ArithmeticException(); }
    public static @Nonnull ArithmeticException ar(String message)               { return new ArithmeticException(message);}

    public static @Nonnull NoSuchElementException noSuchElement()               { return new NoSuchElementException(); }
    public static @Nonnull NoSuchElementException noSuchElement(String message) { return new NoSuchElementException(message);}

    public static final WrapingHandler<AssertionError> assertion = handler(AssertionError.class, AssertionError::new, AssertionError::new);

    public static @Nonnull AssertionError assertion()                                { return new AssertionError(); }
    public static @Nonnull AssertionError assertion(String message)                  { return new AssertionError(message);}
    public static @Nonnull AssertionError assertion(String message, Throwable cause) { return new AssertionError(message, cause);}
    public static @Nonnull AssertionError assertion(Throwable cause)                 { return new AssertionError(cause);}

    public static final WrapingHandler<NotYetImplementedException> notYetImplemented =
            handler(NotYetImplementedException.class, NotYetImplementedException::new, NotYetImplementedException::new);

    public static @Nonnull NotYetImplementedException notYetImplemented()               { return new NotYetImplementedException();}
    public static @Nonnull NotYetImplementedException notYetImplemented(String message) { return new NotYetImplementedException(message);}
    public static @Nonnull NotYetImplementedException notYetImplemented(String message, Throwable cause) {
        return new NotYetImplementedException(message, cause);
    }
    public static @Nonnull NotYetImplementedException notYetImplemented(Throwable cause) { return new NotYetImplementedException(cause);}

    public static @Nonnull NotYetImplementedException notYetImplemented(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new NotYetImplementedException(message, cause, enableSuppression, writableStackTrace);
    }

    public static final WrapingHandler<UnknownCaseException> unknownCase =
            handler(UnknownCaseException.class, UnknownCaseException::new, UnknownCaseException::new);

    public static @Nonnull UnknownCaseException unknownCase()                                { return new UnknownCaseException();}
    public static @Nonnull UnknownCaseException unknownCase(String message)                  { return new UnknownCaseException(message);}
    public static @Nonnull UnknownCaseException unknownCase(String message, Throwable cause) { return new UnknownCaseException(message, cause);}
    public static @Nonnull UnknownCaseException unknownCase(Throwable cause)                 { return new UnknownCaseException(cause);}

    public static @Nonnull UnknownCaseException unknownCase(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new UnknownCaseException(message, cause, enableSuppression, writableStackTrace);
    }

    public static @Nullable Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() == null) {
            return null;
        }

        Throwable cause;
        while ((cause = throwable.getCause()) != null) {
            throwable = cause;
        }
        return throwable;
    }

    //<editor-fold desc="RE section">

    public static final WrapingHandler<RE> RE = handler(RE.class, RE::new, RE::new);

    public static @Nonnull RE RE()                                { return new RE();}
    public static @Nonnull RE RE(String message)                  { return new RE(message);}
    public static @Nonnull RE RE(String message, Throwable cause) { return new RE(message, cause);}
    public static @Nonnull RE RE(Throwable cause)                 { return new RE(cause);}

    public static @Nonnull RE RE(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new RE(message, cause, enableSuppression, writableStackTrace);
    }

    public static final WrapingHandler<InputOutputRE> RE_io = handler(InputOutputRE.class, InputOutputRE::new, InputOutputRE::new);

    public static @Nonnull InputOutputRE RE_io()                                { return new InputOutputRE();}
    public static @Nonnull InputOutputRE RE_io(String message)                  { return new InputOutputRE(message);}
    public static @Nonnull InputOutputRE RE_io(String message, Throwable cause) { return new InputOutputRE(message, cause);}
    public static @Nonnull InputOutputRE RE_io(Throwable cause)                 { return new InputOutputRE(cause);}

    public static @Nonnull InputOutputRE RE_io(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new InputOutputRE(message, cause, enableSuppression, writableStackTrace);
    }

    public static final WrapingHandler<MalformedUrlRE> RE_malformedUrl = handler(MalformedUrlRE.class, MalformedUrlRE::new, MalformedUrlRE::new);

    public static @Nonnull MalformedUrlRE RE_malformedUrl()                                { return new MalformedUrlRE();}
    public static @Nonnull MalformedUrlRE RE_malformedUrl(String message)                  { return new MalformedUrlRE(message);}
    public static @Nonnull MalformedUrlRE RE_malformedUrl(String message, Throwable cause) { return new MalformedUrlRE(message, cause);}
    public static @Nonnull MalformedUrlRE RE_malformedUrl(Throwable cause)                 { return new MalformedUrlRE(cause);}

    public static @Nonnull MalformedUrlRE RE_malformedUrl(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new MalformedUrlRE(message, cause, enableSuppression, writableStackTrace);
    }

    public static final WrapingHandler<ParseRE> RE_parse = handler(ParseRE.class, ParseRE::new, ParseRE::new);

    public static @Nonnull ParseRE RE_parse()                                { return new ParseRE();}
    public static @Nonnull ParseRE RE_parse(String message)                  { return new ParseRE(message);}
    public static @Nonnull ParseRE RE_parse(String message, Throwable cause) { return new ParseRE(message, cause);}
    public static @Nonnull ParseRE RE_parse(Throwable cause)                 { return new ParseRE(cause);}

    public static @Nonnull ParseRE RE_parse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new ParseRE(message, cause, enableSuppression, writableStackTrace);
    }

    public static final WrapingHandler<UnsupportedEncodingRE> RE_unsupportedEncoding = handler(UnsupportedEncodingRE.class,
                                                                                               UnsupportedEncodingRE::new,
                                                                                               UnsupportedEncodingRE::new);

    public static @Nonnull UnsupportedEncodingRE RE_unsupportedEncoding()                                { return new UnsupportedEncodingRE();}
    public static @Nonnull UnsupportedEncodingRE RE_unsupportedEncoding(String message)                  { return new UnsupportedEncodingRE(message);}
    public static @Nonnull UnsupportedEncodingRE RE_unsupportedEncoding(String message, Throwable cause) { return new UnsupportedEncodingRE(message, cause);}
    public static @Nonnull UnsupportedEncodingRE RE_unsupportedEncoding(Throwable cause)                 { return new UnsupportedEncodingRE(cause);}

    public static @Nonnull UnsupportedEncodingRE RE_unsupportedEncoding(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new UnsupportedEncodingRE(message, cause, enableSuppression, writableStackTrace);
    }

    //</editor-fold>
}