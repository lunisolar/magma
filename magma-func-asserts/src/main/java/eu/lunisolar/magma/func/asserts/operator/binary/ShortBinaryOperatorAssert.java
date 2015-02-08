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

package eu.lunisolar.magma.func.asserts.operator.binary;

import eu.lunisolar.magma.basics.asserts.Evaluation; // NOSONAR
import eu.lunisolar.magma.basics.asserts.FunctionalAssert; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.RecurringAsserts; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*;

import static org.assertj.core.api.Fail.fail;

/** Assertions for ShortBinaryOperator. */
public interface ShortBinaryOperatorAssert<S extends ShortBinaryOperatorAssert<S, A, RS>, A extends ShortBinaryOperator, RS extends AbstractShortAssert<RS>>
		extends
			Assert<S, A>,
			FunctionalAssert<S, A, RS, Short, Exception>,
			RecurringAsserts<S, A, RS, Short> {

	@Nonnull
	Evaluation<S, A, RS, Short, Exception> doesApplyAsShort(short s1, short s2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends ShortBinaryOperator, RS extends AbstractShortAssert<RS>> extends Base<Impl<A, RS>, A, RS> {

		public Impl(A actual, java.util.function.Function<Short, RS> assertFunction) {
			super(actual, Impl.class, assertFunction);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends ShortBinaryOperator, RS extends AbstractShortAssert<RS>> extends FunctionalAssert.Base<S, A, RS, Short, Exception> implements ShortBinaryOperatorAssert<S, A, RS> {

		protected final java.util.function.Function<Short, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Short, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Short, Exception> doesApplyAsShort(short s1, short s2) {
			return evaluation(() -> assertFactory.apply((Short) actual.applyAsShort(s1, s2)));
		}
	}

}
