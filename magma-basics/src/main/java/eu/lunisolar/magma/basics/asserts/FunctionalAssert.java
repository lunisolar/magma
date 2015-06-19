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
