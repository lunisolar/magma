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

package eu.lunisolar.magma.func.asserts.supplier;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for FloatSupplier. */
public interface FloatSupplierAssert<S extends FloatSupplierAssert<S, A, RS>, A extends FloatSupplier, RS extends AbstractFloatAssert<RS>> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Float, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Float, Exception> doesGetAsFloat();

	@Nonnull
	Evaluation<S, A, RS, Float, Exception> doesGetAsFloat(Action before);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends FloatSupplier, RS extends AbstractFloatAssert<RS>> extends Base<Impl<A, RS>, A, RS> {

		public Impl(A actual, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends FloatSupplier, RS extends AbstractFloatAssert<RS>> extends FullFunctionalAssert.Base<S, A, RS, Float, Exception> implements FloatSupplierAssert<S, A, RS> {

		protected final java.util.function.Function<Float, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Float, Exception> doesGetAsFloat() {
			return evaluation(() -> assertFactory.apply((Float) actual.getAsFloat()));
		}

		@Nonnull
		public Evaluation<S, A, RS, Float, Exception> doesGetAsFloat(Action before) {
			before.execute();
			return doesGetAsFloat();
		}
	}

}
