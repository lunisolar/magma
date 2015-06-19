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

/** Assert for LToFloatBiFunction. */
public interface LToFloatBiFunctionAssert<S extends LToFloatBiFunctionAssert<S, A, RS, T1, T2>, A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> extends Assert<S, A>, FullFunctionalAssert<S, A, RS, Float, Exception> {

	@Nonnull
	Evaluation<S, A, RS, Float, Exception> doesApplyAsFloat(T1 t1, T2 t2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> extends Base<Impl<A, RS, T1, T2>, A, RS, T1, T2> {

		public Impl(A actual, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, Impl.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS, T1, T2>, A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> extends FullFunctionalAssert.Base<S, A, RS, Float, Exception>
			implements
				LToFloatBiFunctionAssert<S, A, RS, T1, T2> {

		protected final java.util.function.Function<Float, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Float, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Float, Exception> doesApplyAsFloat(T1 t1, T2 t2) {
			return evaluation(() -> assertFactory.apply((Float) actual.doApplyAsFloat(t1, t2)));
		}

	}

}
