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

package eu.lunisolar.magma.func.asserts.function.conversion;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for DoubleToByteFunctionX. */
public interface DoubleToByteFunctionXAssert<S extends DoubleToByteFunctionXAssert<S, A, RS, X>, A extends DoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, A, RS, Byte, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(double d);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends DoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> extends Base<Impl<A, RS, X>, A, RS, X> {

		public Impl(A actual, java.util.function.Function<Byte, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, X>, A extends DoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> extends FullFunctionalAssert.Base<S, A, RS, Byte, Exception>
			implements
				DoubleToByteFunctionXAssert<S, A, RS, X> {

		protected final java.util.function.Function<Byte, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Byte, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(double d) {
			return evaluation(() -> assertFactory.apply((Byte) actual.applyAsByte(d)));
		}

	}

}
