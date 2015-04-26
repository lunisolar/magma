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

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for ToFloatFunctionX. */
public interface ToFloatFunctionXAssert<S extends ToFloatFunctionXAssert<S, A, RS, T, X>, A extends ToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Exception> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Float, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Float, Exception> doesApplyAsFloat(T t);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends ToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Exception> extends Base<Impl<A, RS, T, X>, A, RS, T, X> {

		public Impl(A actual, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T, X>, A extends ToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Exception> extends FullFunctionalAssert.Base<S, A, RS, Float, Exception>
			implements
				ToFloatFunctionXAssert<S, A, RS, T, X> {

		protected final java.util.function.Function<Float, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Float, Exception> doesApplyAsFloat(T t) {
			return evaluation(() -> assertFactory.apply((Float) actual.applyAsFloat(t)));
		}

	}

}
