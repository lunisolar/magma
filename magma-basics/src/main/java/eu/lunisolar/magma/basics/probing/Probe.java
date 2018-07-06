/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;

import static java.util.Objects.*;

/**
 * The point is to not hide the object that is probed, but to provide some _probing_ methods that enclose some simple conditions into a nice named method.
 */
@SuppressWarnings("unused")
public interface Probe<T> {

    @Nullable T target();

    default @Nonnull T nonNullTarget() {
        return requireNonNull(target());
    }

    default boolean isNotNull() {
        return check(Objects::nonNull);
    }

    default boolean isNull() {
        return check(Objects::isNull);
    }

    // <editor-fold desc="check">

    default boolean check(Predicate<T> predicate) {
        return predicate.test(target());
    }

    /**
     * BiPredicate allows to use non-capturing lambda when second argument is required.
     */
    default <A> boolean check(A argument, BiPredicate<T, A> predicate) {
        return predicate.test(target(), argument);
    }

    default boolean checkOnlyWhen(Predicate<T> predicateForWhen, Predicate<T> predicateForCheck) {
        return check(predicateForWhen) && check(predicateForCheck);
    }

    default <A> boolean checkOnlyWhen(Predicate<T> predicateForWhen, A argument, BiPredicate<T, A> predicateForCheck) {

        return check(predicateForWhen) && check(argument, predicateForCheck);
    }

    default boolean checkWhenTargetNotNull(Predicate<T> predicate) {
        return checkOnlyWhen(Objects::nonNull, predicate);
    }

    default <A> boolean checkWhenTargetNotNull(A argument, BiPredicate<T, A> predicate) {
        return checkOnlyWhen(Objects::nonNull, argument, predicate);
    }

    default boolean checkWhenTargetNotNullAnd(Predicate<T> predicateForWhen, Predicate<T> predicateForCheck) {
        return checkWhenTargetNotNull(predicateForWhen) && check(predicateForCheck);
    }

    default <A> boolean checkWhenTargetNotNullAnd(Predicate<T> predicateForWhen, A argument, BiPredicate<T, A> predicateForCheck) {
        return checkWhenTargetNotNull(predicateForWhen) && check(argument, predicateForCheck);
    }

    class Base<T> implements Probe<T> {

        private final T target;

        public Base(T target) {
            this.target = target;
        }

        @Nullable @Override public T target() {
            return target;
        }
    }
}
