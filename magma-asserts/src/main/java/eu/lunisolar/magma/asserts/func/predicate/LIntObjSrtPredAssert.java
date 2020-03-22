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

package eu.lunisolar.magma.asserts.func.predicate;

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

import eu.lunisolar.magma.func.predicate.*;

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
import eu.lunisolar.magma.func.predicate.LObjIntSrtPredicate.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieSrtConsumer.*;

/** Assert class for LIntObjSrtPred. */
public interface LIntObjSrtPredAssert<S extends LIntObjSrtPredAssert<S, A, RS, T>, A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, LTieSrtConsumer.LIntObjSrtCons<T>, A, RS, Boolean> {

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjSrtPredAssert.The<A, RS, T> attestIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		return new LIntObjSrtPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LTieSrtConsumer.LIntObjSrtCons<T>, A, RS, Boolean> doesTest(int a2, T a1, short a3);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T> extends Base<The<A, RS, T>, A, RS, T> {

		public The(A actual, LBoolFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, T>, A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T> extends FullFunctionalAssert.Base<S, LTieSrtConsumer.LIntObjSrtCons<T>, A, RS, Boolean>
			implements
				LIntObjSrtPredAssert<S, A, RS, T> {

		protected final LBoolFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LBoolFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LTieSrtConsumer.LIntObjSrtCons<T>, A, RS, Boolean> doesTest(int a2, T a1, short a3) {

			return evaluation(() -> String.format("(%s,%s,%s)", a2, a1, a3), pc -> {
				if (pc != null) {
					// pc.acceptIntObjSrt(a1,a2,a3);
					// pc.accept(a1,a2,a3);
					pc.acceptIntObjSrt(a2, a1, a3);
				}
				return assertFactory.apply(actual.testIntObjSrt(a2, a1, a3));
			});
		}

	}

}
