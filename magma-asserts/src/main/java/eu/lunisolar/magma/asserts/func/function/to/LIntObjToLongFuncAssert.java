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
import eu.lunisolar.magma.func.function.to.LOiToLongFunction.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer.*;

/** Assert class for LIntObjToLongFunc. */
public interface LIntObjToLongFuncAssert<S extends LIntObjToLongFuncAssert<S, A, RS, T>, A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, LObjIntConsumer.LIntObjCons<T>, A, RS, Long> {

	@Nonnull
	public static <A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T> LIntObjToLongFuncAssert.The<A, RS, T> attestIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		return new LIntObjToLongFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LObjIntConsumer.LIntObjCons<T>, A, RS, Long> doesApplyAsLong(int a2, T a1);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T> extends Base<The<A, RS, T>, A, RS, T> {

		public The(A actual, LLongFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, T>, A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T> extends FullFunctionalAssert.Base<S, LObjIntConsumer.LIntObjCons<T>, A, RS, Long>
			implements
				LIntObjToLongFuncAssert<S, A, RS, T> {

		protected final LLongFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LLongFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LObjIntConsumer.LIntObjCons<T>, A, RS, Long> doesApplyAsLong(int a2, T a1) {

			return evaluation(() -> String.format("(%s,%s)", a2, a1), pc -> {
				if (pc != null) {
					// pc.acceptIntObj(a1,a2);
					// pc.accept(a1,a2);
					pc.acceptIntObj(a2, a1);
				}
				return assertFactory.apply(actual.applyAsLongIntObj(a2, a1));
			});
		}

	}

}
