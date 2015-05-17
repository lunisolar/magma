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

package eu.lunisolar.magma.func.asserts.function.to;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.action.LAction;

import static org.assertj.core.api.Fail.fail;

/** Assert for LToShortBiFunction. */
public interface LToShortBiFunctionAssert<S extends LToShortBiFunctionAssert<S, A, RS, T1, T2>, A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Short, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Short, Exception> doesApplyAsShort(T1 t1, T2 t2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> extends Base<Impl<A, RS, T1, T2>, A, RS, T1, T2> {

		public Impl(A actual, java.util.function.Function<Short, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T1, T2>, A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> extends FullFunctionalAssert.Base<S, A, RS, Short, Exception>
			implements
				LToShortBiFunctionAssert<S, A, RS, T1, T2> {

		protected final java.util.function.Function<Short, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Short, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Short, Exception> doesApplyAsShort(T1 t1, T2 t2) {
			return evaluation(() -> assertFactory.apply((Short) actual.doApplyAsShort(t1, t2)));
		}

	}

}
