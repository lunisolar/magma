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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.lunisolar.magma.basics.builder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Objects;

@Immutable
@ThreadSafe
public final class Case<P, F> {
    private static final String ARGUMENT_CASE_PREDICATE_CANNOT_BE_NULL = "Argument [casePredicate] cannot be null.";
    private static final String ARGUMENT_CASE_FUNCTION_CANNOT_BE_NULL  = "Argument [caseFunction] cannot be null.";

    private final P casePredicate;
    private final F caseFunction;

    public Case(@Nonnull P casePredicate, @Nonnull F caseFunction) {
        Objects.requireNonNull(casePredicate, ARGUMENT_CASE_PREDICATE_CANNOT_BE_NULL);
        Objects.requireNonNull(caseFunction, ARGUMENT_CASE_FUNCTION_CANNOT_BE_NULL);
        this.casePredicate = casePredicate;
        this.caseFunction = caseFunction;
    }

    @Nonnull
    public static final <P, F> Case<P, F> of(@Nonnull P casePredicate, @Nonnull F caseFunction) {
        return new Case<>(casePredicate, caseFunction);
    }

    @Nonnull
    public final F caseFunction() {
        return caseFunction;
    }

    @Nonnull
    public final P casePredicate() {
        return casePredicate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Case)) {
            return false;
        }

        Case aCase = (Case) o;

        if (!caseFunction.equals(aCase.caseFunction)) {
            return false;
        }
        if (!casePredicate.equals(aCase.casePredicate)) {
            return false;
        }

        return true;
    }

    @Override
    public final int hashCode() {
        int result = casePredicate.hashCode();
        result = 31 * result + caseFunction.hashCode();
        return result;
    }
}