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
import java.util.*;
import java.util.function.*;

/**
 * The point is to not hide the object that is probed, but to provide some _probing_ methods that enclose some simple conditions into a nice named method.
 */
@SuppressWarnings("unused")
public interface Probe<T> {

    @Nullable T getTarget();

    default boolean isNotNull() {
        return check(x -> x != null);
    }

    default boolean isNull() {
        return check(x -> x == null);
    }

    // <editor-fold desc="check">

    default boolean check(Predicate<T> predicate) {
        return predicate.test(getTarget());
    }

    /**
     * BiPredicate allows to use non-capturing lambda when second argument is required.
     */
    default <A> boolean check(A argument, BiPredicate<T, A> predicate) {
        return predicate.test(getTarget(), argument);
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

}
