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

package eu.lunisolar.magma.basics.probing;

import javax.annotation.Nullable;
import java.util.function.*;

import static eu.lunisolar.magma.basics.probing.Internal.getRootCause;

/**
 *
 */
public interface ThrowableProbe<X extends Throwable> extends ObjectProbe<X> {

    static <T extends Throwable> ThrowableProbe<T> of(T t) {
        return new ThrowableProbe.The(t);
    }

    default boolean isRuntime() {
        return isInstanceOf(RuntimeException.class);
    }

    default boolean isNotRuntime() {
        return !isInstanceOf(RuntimeException.class);
    }

    default boolean hasCause() {
        return checkWhenTargetNotNull(x -> x.getCause() != null);
    }

    default boolean hasNoCause() {
        return checkWhenTargetNotNull(x -> x.getCause() == null);
    }

    default boolean hasMessageStartingWith(String description) {
        return checkWhenTargetNotNull(x -> x.getMessage() != null && x.getMessage().startsWith(description));
    }

    default boolean hasMessageContaining(String description) {
        return checkWhenTargetNotNull(description, (x, d) -> x.getMessage() != null && x.getMessage().contains(d));
    }

    default boolean hasMessageEndingWith(String description) {
        return checkWhenTargetNotNull(description, (x, d) -> x.getMessage() != null && x.getMessage().endsWith(d));
    }

    default boolean hasCauseThat(Predicate<ThrowableProbe> cause) {
        return checkWhenTargetNotNull(t -> cause.test(ThrowableProbe.of(t.getCause())));
    }

    default boolean hasRootCauseThat(Predicate<ThrowableProbe> cause) {
        return checkWhenTargetNotNull(t -> cause.test(ThrowableProbe.of(getRootCause(t))));
    }

    final class The<T extends Throwable> implements ThrowableProbe<T> {

        private final T target;

        public The(T target) {
            this.target = target;
        }

        @Nullable @Override public T target() {
            return target;
        }
    }
}
