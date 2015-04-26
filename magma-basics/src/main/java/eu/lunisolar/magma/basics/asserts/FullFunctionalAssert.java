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
