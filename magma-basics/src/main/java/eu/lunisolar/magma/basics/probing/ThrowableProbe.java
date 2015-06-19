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

import javax.annotation.Nullable;
import java.util.function.*;

import static eu.lunisolar.magma.basics.probing.Private.getRootCause;

/**
 *
 */
public interface ThrowableProbe<X extends Throwable> extends ObjectProbe<X> {

    static <T extends Throwable> ThrowableProbe<T> of(T t) {
        return new ThrowableProbe.The(t);
    }

//    default <A> boolean checkMessageIfExists(String argument,  BiPredicate<String, A> predicate) {
//        checkOnlyWhen(x-> x!=null && x.getMessage()!=null,argument, predicate);
//
//        if ( getTarget() != null && getTarget().getMessage()!=null) {
//            return predicate.test(getTarget().getMessage(), argument);
//        }
//        return false;
//    }
//
//    default <A> boolean checkCauseIfExists(A argument,  BiPredicate<Throwable, A> predicate) {
//        if ( getTarget() != null && getTarget().getCause()!=null) {
//            return predicate.test(getTarget().getCause(), argument);
//        }
//        return false;
//    }

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
        return checkWhenTargetNotNull((t) -> cause.test(ThrowableProbe.of(t.getCause())));
    }

    default boolean hasRootCauseThat(Predicate<ThrowableProbe> cause) {
        return checkWhenTargetNotNull((t) -> cause.test(ThrowableProbe.of(getRootCause(t))));
    }

    class The<T extends Throwable> implements ThrowableProbe<T> {

        private final T target;

        public The(T target) {
            this.target = target;
        }

        @Nullable @Override public T getTarget() {
            return target;
        }
    }
}
