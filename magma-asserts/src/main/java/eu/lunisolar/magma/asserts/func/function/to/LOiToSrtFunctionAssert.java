/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts.func.function.to;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*;

import eu.lunisolar.magma.func.function.to.*;

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/** Assert class for LOiToSrtFunction. */
public interface LOiToSrtFunctionAssert<S extends LOiToSrtFunctionAssert<S, A, RS, T>, A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> extends Assert<S, A>, FullFunctionalAssert<S, LObjIntConsumer<T>, A, RS, Short> {

	@Nonnull
	public static <A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LOiToSrtFunctionAssert.The<A, RS, T> attestOiToSrtFunc(LOiToSrtFunction<T> func) {
		return new LOiToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LObjIntConsumer<T>, A, RS, Short> doesApplyAsSrt(T a1, int a2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> extends Base<The<A, RS, T>, A, RS, T> {

		public The(A actual, LSrtFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, T>, A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> extends FullFunctionalAssert.Base<S, LObjIntConsumer<T>, A, RS, Short> implements LOiToSrtFunctionAssert<S, A, RS, T> {

		protected final LSrtFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LSrtFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LObjIntConsumer<T>, A, RS, Short> doesApplyAsSrt(T a1, int a2) {

			return evaluation(() -> String.format("(%s,%s)", a1, a2), pc -> {
				if (pc != null) {
					pc.accept(a1, a2);
				}
				return assertFactory.apply(actual.applyAsSrt(a1, a2));
			});
		}

	}

}
