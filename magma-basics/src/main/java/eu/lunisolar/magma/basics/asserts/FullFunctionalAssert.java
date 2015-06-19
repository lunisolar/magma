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

import javax.annotation.Nonnull;
import java.util.function.*;

public interface FullFunctionalAssert<S extends FullFunctionalAssert<S, A, RS, R, X>, A, RS extends Assert<RS, R>, R, X extends Exception>
        extends FunctionalAssert<S, A, Consumer<RS>, X>, Assert<S, A>, Fluent<S> {

    @SuppressWarnings("unchecked")
    abstract class Base<S extends Base<S, A, RS, R, X>, A, RS extends Assert<RS, R>, R, X extends Exception>
            extends RecurringAsserts.Base<S, A, Consumer<RS>>
            implements FullFunctionalAssert<S, A, RS, R, X>, Fluent<S> {

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Nonnull
        protected Evaluation<S, A, RS, R, X> evaluation(AssertionSupplier<RS> assertSupplier) {
            return new Evaluation(self(), recurringAssert, () -> {
                isNotNull();
                return assertSupplier.get();
            });
        }

    }
}
