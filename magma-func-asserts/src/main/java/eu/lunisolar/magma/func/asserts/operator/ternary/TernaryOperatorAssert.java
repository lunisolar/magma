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

package eu.lunisolar.magma.func.asserts.operator.ternary;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for TernaryOperator. */
public interface TernaryOperatorAssert<S extends TernaryOperatorAssert<S, A, RS, T>, A extends TernaryOperator<T>, RS extends Assert<RS, T>, T> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, T, Exception> {

	@Nonnull
	Evaluation<S, A, RS, T, Exception> doesApply(T t1, T t2, T t3);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends TernaryOperator<T>, RS extends Assert<RS, T>, T> extends Base<Impl<A, RS, T>, A, RS, T> {

		public Impl(A actual, java.util.function.Function<T, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T>, A extends TernaryOperator<T>, RS extends Assert<RS, T>, T> extends FullFunctionalAssert.Base<S, A, RS, T, Exception> implements TernaryOperatorAssert<S, A, RS, T> {

		protected final java.util.function.Function<T, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<T, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, T, Exception> doesApply(T t1, T t2, T t3) {
			return evaluation(() -> assertFactory.apply((T) actual.apply(t1, t2, t3)));
		}

	}

}
