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

package eu.lunisolar.magma.func.asserts.function.to;

import eu.lunisolar.magma.basics.asserts.Evaluation; // NOSONAR
import eu.lunisolar.magma.basics.asserts.FunctionalAssert; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.RecurringAsserts; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*;

import static org.assertj.core.api.Fail.fail;

/** Assertions for ToByteFunctionX. */
public interface ToByteFunctionXAssert<S extends ToByteFunctionXAssert<S, A, RS, T, X>, A extends ToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Exception>
		extends
			Assert<S, A>,
			FunctionalAssert<S, A, RS, Byte, Exception>,
			RecurringAsserts<S, A, RS, Byte> {

	@Nonnull
	Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(T t);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends ToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Exception> extends Base<Impl<A, RS, T, X>, A, RS, T, X> {

		public Impl(A actual, java.util.function.Function<Byte, RS> assertFunction) {
			super(actual, Impl.class, assertFunction);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T, X>, A extends ToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Exception> extends FunctionalAssert.Base<S, A, RS, Byte, Exception>
			implements
				ToByteFunctionXAssert<S, A, RS, T, X> {

		protected final java.util.function.Function<Byte, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Byte, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(T t) {
			return evaluation(() -> assertFactory.apply((Byte) actual.applyAsByte(t)));
		}
	}

}
