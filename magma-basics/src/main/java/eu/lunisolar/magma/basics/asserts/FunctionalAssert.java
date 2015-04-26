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

package eu.lunisolar.magma.basics.asserts;

import eu.lunisolar.magma.basics.fluent.Fluent;
import org.assertj.core.api.Assert;
import org.assertj.core.api.ThrowableAssert;

import javax.annotation.Nonnull;

public interface FunctionalAssert<S extends FunctionalAssert<S, A, RA, X>, A, RA, X extends Exception>
        extends RecurringAsserts<S, A, RA>, Assert<S, A>, Fluent<S> {

    interface Simple<S extends Simple<S, A, X>, A, X extends Exception> extends FunctionalAssert<S, A, AssertionsCheck, X> {

    }

    @SuppressWarnings("unchecked")
    abstract class Base<S extends Base<S, A, X>, A, X extends Exception>
            extends RecurringAsserts.Base<S, A, AssertionsCheck>
            implements FunctionalAssert.Simple<S, A, X>, Fluent<S> {

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Nonnull
        protected SemiEvaluation<S, A, X> evaluation(ThrowableAssert.ThrowingCallable callWrapper) {
            return new SemiEvaluation(self(), recurringAssert, callWrapper);
        }

    }
}
