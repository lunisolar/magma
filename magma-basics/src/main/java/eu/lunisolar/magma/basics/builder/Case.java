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
    public static <P, F> Case<P, F> of(@Nonnull P casePredicate, @Nonnull F caseFunction) {
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