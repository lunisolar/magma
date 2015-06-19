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

package eu.lunisolar.magma.basics.builder;

import eu.lunisolar.magma.basics.Null;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@Immutable
@ThreadSafe
public final class Case<P, F> {

    private final P casePredicate;
    private final F caseFunction;

    public Case(@Nonnull P casePredicate, @Nonnull F caseFunction) {
        Null.nonNullArg(casePredicate, "casePredicate");
        Null.nonNullArg(caseFunction, "caseFunction");
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