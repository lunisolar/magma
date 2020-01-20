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
import eu.lunisolar.magma.basics.meta.SelfReferencing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

@Deprecated // simply use FluentTrait from 'support' module
public interface Fluent<SELF extends Fluent<SELF>> extends SelfReferencing<SELF> {

    /** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
    default @Nonnull SELF fluentUse(@Nonnull Consumer<SELF> interjection) {
        Null.nonNull(interjection);
        final SELF self = self();
        interjection.accept(self);
        return self;
    }

    /** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
    default @Nonnull <T> SELF fluentUse(T ctx, @Nonnull BiConsumer<SELF, T> interjection) {
        Null.nonNull(interjection);
        final SELF self = self();
        interjection.accept(self, ctx);
        return self;
    }

    default @Nonnull <R> R fluentMap(@Nonnull Function<SELF, R> mapping) {
        Null.nonNull(mapping);
        final SELF self = self();
        return Null.nonNull(mapping.apply(self));
    }

    default @Nonnull <T, R> R fluentMap(T ctx, @Nonnull BiFunction<SELF, T, R> mapping) {
        Null.nonNull(mapping);
        final SELF self = self();
        return Null.nonNull(mapping.apply(self, ctx));
    }

    default @Nullable <R> R fluentNullableMap(@Nonnull Function<SELF, R> mapping) {
        Null.nonNull(mapping);
        final SELF self = self();
        return mapping.apply(self);
    }

    default @Nullable <T, R> R fluentNullableMap(T ctx, @Nonnull BiFunction<SELF, T, R> mapping) {
        Null.nonNull(mapping);
        final SELF self = self();
        return mapping.apply(self, ctx);
    }

}
