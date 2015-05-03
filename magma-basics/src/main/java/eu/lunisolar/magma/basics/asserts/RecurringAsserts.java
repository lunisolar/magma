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

package eu.lunisolar.magma.basics.asserts;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assert;

import javax.annotation.Nonnull;

public interface RecurringAsserts<S extends Assert<S, A>, A, RA> {

    /**
     * In case of some assertion that could be applied each time a method call result is tested, the argument assertion will be checked before the assertion
     * for specific case.
     */
    @Nonnull S recurringAsserts(@Nonnull RA recurringAssert);

    abstract class Base<S extends Base<S, A, RA>, A, RA> extends AbstractObjectAssert<S, A> implements RecurringAsserts<S, A, RA> {

        protected RA recurringAssert;

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        @Nonnull
        public S recurringAsserts(@Nonnull RA recurringAssert) {
            this.recurringAssert = recurringAssert;
            return myself;
        }

        @Nonnull
        protected RA recurringAssert() {
            return recurringAssert;
        }

    }

}
