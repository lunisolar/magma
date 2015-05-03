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

package eu.lunisolar.magma.func.asserts.std;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for java.util.function.Function. */
public interface StdFunctionAssert<S extends StdFunctionAssert<S, A, RS, T, R>, A extends java.util.function.Function<T, R>, RS extends Assert<RS, R>, T, R> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, R, Exception> {

	@Nonnull
	Evaluation<S, A, RS, R, Exception> doesApply(T t);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends java.util.function.Function<T, R>, RS extends Assert<RS, R>, T, R> extends Base<Impl<A, RS, T, R>, A, RS, T, R> {

		public Impl(A actual, java.util.function.Function<R, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T, R>, A extends java.util.function.Function<T, R>, RS extends Assert<RS, R>, T, R> extends FullFunctionalAssert.Base<S, A, RS, R, Exception> implements StdFunctionAssert<S, A, RS, T, R> {

		protected final java.util.function.Function<R, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<R, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, R, Exception> doesApply(T t) {
			return evaluation(() -> assertFactory.apply((R) actual.apply(t)));
		}

	}

}
