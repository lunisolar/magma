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

package eu.lunisolar.magma.basics.fluent;

import eu.lunisolar.magma.basics.Null;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

public interface Fluent<F> {

    default F fluentCtx() { return (F) this;}

    /** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
    default @Nonnull F fluentUse(@Nonnull Consumer<F> interjection) {
        Null.nonNull(interjection);
        final F self = fluentCtx();
        interjection.accept(self);
        return self;
    }

    /** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
    default @Nonnull <T> F fluentUse(T ctx, @Nonnull BiConsumer<F, T> interjection) {
        Null.nonNull(interjection);
        final F self = fluentCtx();
        interjection.accept(self, ctx);
        return self;
    }

    default @Nonnull <R> R fluentMap(@Nonnull Function<F, R> mapping) {
        Null.nonNull(mapping);
        final F self = fluentCtx();
        return Null.nonNull(mapping.apply(self));
    }

    default @Nonnull <T, R> R fluentMap(T ctx, @Nonnull BiFunction<F, T, R> mapping) {
        Null.nonNull(mapping);
        final F self = fluentCtx();
        return Null.nonNull(mapping.apply(self, ctx));
    }

    default @Nullable <R> R fluentNullableMap(@Nonnull Function<F, R> mapping) {
        Null.nonNull(mapping);
        final F self = fluentCtx();
        return mapping.apply(self);
    }

    default @Nullable <T, R> R fluentNullableMap(T ctx, @Nonnull BiFunction<F, T, R> mapping) {
        Null.nonNull(mapping);
        final F self = fluentCtx();
        return mapping.apply(self, ctx);
    }

}
