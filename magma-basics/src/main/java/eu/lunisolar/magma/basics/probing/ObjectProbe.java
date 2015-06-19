/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
        return check(values, (x, v) -> Private.isItemInArray(getTarget(), v));
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

    class The<T> implements ObjectProbe<T> {

        private final T target;

        public The(T target) {
            this.target = target;
        }

        @Nullable @Override public T getTarget() {
            return target;
        }
    }
}
