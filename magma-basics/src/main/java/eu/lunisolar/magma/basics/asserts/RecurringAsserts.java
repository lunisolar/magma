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

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assert;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public interface RecurringAsserts<S extends Assert<S, A>, A, RS extends Assert<RS, R>, R> {

    /**
     * In case of some assertion that could be applied each time a method call result is tested, the argument assertion will be checked before the assertion
     * for specific case.
     */
    @Nonnull S recurringAsserts(@Nonnull Consumer<RS> recurringAssert);

    public abstract static class Base<S extends Base<S, A, RS, R>, A, RS extends Assert<RS, R>, R> extends AbstractObjectAssert<S, A> implements RecurringAsserts<S, A, RS, R> {

        protected java.util.function.Consumer<RS> recurringAssert;

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        @Nonnull
        public S recurringAsserts(@Nonnull java.util.function.Consumer<RS> recurringAssert) {
            this.recurringAssert = recurringAssert;
            return myself;
        }

        @Nonnull
        protected java.util.function.Consumer<RS> recurringAssert() {
            return recurringAssert;
        }

    }

}
