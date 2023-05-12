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

package eu.lunisolar.magma.basics.fluent;

import eu.lunisolar.magma.basics.Null;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

public interface Fluent<FLUENT> {

    default FLUENT fluentCtx() { return (FLUENT) this;}

    default @Nonnull FLUENT fluentUse(@Nonnull Consumer<FLUENT> interjection) {
        Null.nonNull(interjection);
        final FLUENT self = fluentCtx();
        interjection.accept(self);
        return self;
    }

    default @Nonnull <T> FLUENT fluentUse(T ctx, @Nonnull BiConsumer<FLUENT, T> interjection) {
        Null.nonNull(interjection);
        final FLUENT self = fluentCtx();
        interjection.accept(self, ctx);
        return self;
    }

    default @Nonnull <T> FLUENT fluentUseWith(T ctx, @Nonnull BiConsumer<T, FLUENT> interjection) {
        Null.nonNull(interjection);
        final FLUENT self = fluentCtx();
        interjection.accept(ctx, self);
        return self;
    }

    default @Nonnull <R> R fluentMap(@Nonnull Function<FLUENT, R> mapping) {
        Null.nonNull(mapping);
        return Null.nonNull(fluentNullableMap(mapping));
    }

    default @Nonnull <T, R> R fluentMap(T ctx, @Nonnull BiFunction<FLUENT, T, R> mapping) {
        Null.nonNull(mapping);
        return Null.nonNull(fluentNullableMap(ctx, mapping));
    }

    default @Nullable <R> R fluentNullableMap(@Nonnull Function<FLUENT, R> mapping) {
        Null.nonNull(mapping);
        final FLUENT self = fluentCtx();
        return mapping.apply(self);
    }

    default @Nullable <T, R> R fluentNullableMap(T ctx, @Nonnull BiFunction<FLUENT, T, R> mapping) {
        Null.nonNull(mapping);
        final FLUENT self = fluentCtx();
        return mapping.apply(self, ctx);
    }

}
