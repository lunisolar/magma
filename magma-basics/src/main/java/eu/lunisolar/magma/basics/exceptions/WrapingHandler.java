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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static eu.lunisolar.magma.basics.Null.nonNullArg;

/**
 * @author Jakub Wach
 */
public final class WrapingHandler<X extends Throwable> {
    private final @Nonnull Class<X> clazz;
    private final @Nonnull ExWF<X>  exwf;
    private final @Nonnull ExWMF<X> exmf;

    private WrapingHandler(@Nonnull Class<X> clazz, @Nonnull ExWF<X> exwf, @Nonnull ExWMF<X> exmf) {
        this.clazz = nonNullArg(clazz, "Clazz argument cannot be null.");
        this.exwf = nonNullArg(exwf, "'exwf' argument cannot be null.");
        this.exmf = nonNullArg(exmf, "'exmf' argument cannot be null.");
    }

    public static <X extends Throwable> WrapingHandler<X> handler(
            @Nonnull Class<X> clazz, @Nonnull ExWF<X> exwf, @Nonnull ExWMF<X> exmf) {
        return new WrapingHandler<>(clazz, exwf, exmf);
    }

    //<editor-fold desc="create-ExWF">

    public X wrapIfNot(@Nullable Throwable e) {
        return Handling.wrapIfNot(clazz, e, exwf);
    }

    public X wrap(@Nullable Throwable e, @Nonnull ExWF<X> fx) {
        return Handling.wrap(e, exwf);
    }

    //</editor-fold>

    // <editor-fold desc="create-ExMF">

    public X wrap(@Nullable Throwable e, @Nullable String msg) {
        return Handling.wrap(e, exmf, msg);
    }

    public X wrap(@Nullable Throwable e, @Nullable String msg, @Nullable Object... args) {
        return Handling.wrap(e, exmf, msg, args);
    }

    public X combine(@Nullable Throwable e, @Nullable String msg) {
        return Handling.combine(e, exmf, msg);
    }

    public X combine(@Nullable Throwable e, @Nullable String msg, @Nullable Object... args) {
        return Handling.combine(e, exmf, msg, args);
    }

    public X wrapIfNot(@Nullable Throwable e, @Nullable String msg) {
        return Handling.wrapIfNot(clazz, e, exmf, msg);
    }

    public X wrapIfNot(@Nullable Throwable e, @Nullable String msg, @Nullable Object... args) {
        return Handling.wrapIfNot(clazz, e, exmf, msg, args);
    }

    //</editor-fold>

}
