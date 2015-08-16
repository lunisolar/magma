/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.Null;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 *
 */
@SuppressWarnings("unused")
public interface ObjectProbe<T> extends Probe<T> {

    static <T> ObjectProbe<T> of(T t) {
        return new ObjectProbe.The(t);
    }

    default boolean isSameAs(@Nullable Object other) {
        return check(x -> x == other);
    }

    default boolean isNotSameAs(@Nullable Object other) {
        return !isSameAs(other);
    }

    default boolean isEqualTo(@Nullable Object other) {
        return check(other, (x, o) -> Objects.equals(getTarget(), o));
    }

    default boolean isNotEqualTo(@Nullable Object other) {
        return !isEqualTo(other);
    }

    default boolean isIn(@Nullable Object... values) {
        return check(values, (x, v) -> Internal.isItemInArray(getTarget(), v));
    }

    default boolean isNotIn(@Nullable Object... values) {
        return !isIn(values);
    }

    default boolean isInstanceOf(@Nonnull Class<?> type) {
        Null.nonNullArg(type, "type");
        return check(type, (o, t) -> t.isInstance(o));
    }

    default boolean isNotInstanceOf(@Nonnull Class<?> type) {
        return !isInstanceOf(type);
    }

    default boolean isInstanceOfAny(@Nonnull Class<?>... types) {
        Null.nonNullArg(types, "types");
        for (Class<?> type : types) {
            if (isInstanceOf(type)) {
                return true;
            }
        }
        return false;
    }

    default boolean isNotInstanceOfAny(@Nonnull Class<?>... types) {
        return !isInstanceOfAny(types);
    }

    default boolean hasSameClassAs(@Nonnull Object other) {
        Null.nonNullArg(other, "other");
        return checkWhenTargetNotNull(other, (t, o) -> {
            Class actualClass = t.getClass();
            Class<?> otherClass = o.getClass();
            return actualClass.equals(otherClass);
        });

    }

    default boolean doesNotHaveSameClassAs(@Nonnull Object other) {
        return !hasSameClassAs(other);
    }

    default boolean isExactlyInstanceOf(@Nonnull Class<?> type) {
        Null.nonNullArg(type, "type");
        return checkWhenTargetNotNull(type, (o, t) -> t.equals(o.getClass()));
    }

    default boolean isNotExactlyInstanceOf(@Nonnull Class<?> type) {
        return !isExactlyInstanceOf(type);
    }

    final class The<T> implements ObjectProbe<T> {

        private final T target;

        public The(T target) {
            this.target = target;
        }

        @Nullable @Override public T getTarget() {
            return target;
        }
    }
}
