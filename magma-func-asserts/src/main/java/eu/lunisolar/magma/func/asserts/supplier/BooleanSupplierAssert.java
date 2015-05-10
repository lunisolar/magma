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

package eu.lunisolar.magma.func.asserts.supplier;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for BooleanSupplier. */
public interface BooleanSupplierAssert<S extends BooleanSupplierAssert<S, A, RS>, A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Boolean, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Boolean, Exception> doesGetAsBoolean();

	@Nonnull
	Evaluation<S, A, RS, Boolean, Exception> doesGetAsBoolean(Action before);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> extends Base<Impl<A, RS>, A, RS> {

		public Impl(A actual, java.util.function.Function<Boolean, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> extends FullFunctionalAssert.Base<S, A, RS, Boolean, Exception> implements BooleanSupplierAssert<S, A, RS> {

		protected final java.util.function.Function<Boolean, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Boolean, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Boolean, Exception> doesGetAsBoolean() {
			return evaluation(() -> assertFactory.apply((Boolean) actual.getAsBoolean()));
		}

		@Nonnull
		public S doesReturn(boolean value) {
			doesGetAsBoolean().asEqualTo(value);
			return self();
		}

		@Nonnull
		public Evaluation<S, A, RS, Boolean, Exception> doesGetAsBoolean(Action before) {
			before.execute();
			return doesGetAsBoolean();
		}
	}

}
