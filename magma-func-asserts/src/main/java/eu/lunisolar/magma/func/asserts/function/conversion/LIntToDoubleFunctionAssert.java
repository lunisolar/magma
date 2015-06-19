/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import eu.lunisolar.magma.func.action.LAction;

import static org.assertj.core.api.Fail.fail;

/** Assert for LIntToDoubleFunction. */
public interface LIntToDoubleFunctionAssert<S extends LIntToDoubleFunctionAssert<S, A, RS>, A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Double, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Double, Exception> doesApplyAsDouble(int i);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> extends Base<Impl<A, RS>, A, RS> {

		public Impl(A actual, java.util.function.Function<Double, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> extends FullFunctionalAssert.Base<S, A, RS, Double, Exception> implements LIntToDoubleFunctionAssert<S, A, RS> {

		protected final java.util.function.Function<Double, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Double, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Double, Exception> doesApplyAsDouble(int i) {
			return evaluation(() -> assertFactory.apply((Double) actual.doApplyAsDouble(i)));
		}

	}

}
