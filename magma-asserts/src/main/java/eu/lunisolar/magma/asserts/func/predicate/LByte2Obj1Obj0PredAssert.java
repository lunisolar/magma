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
import eu.lunisolar.magma.func.predicate.LBiObjBytePredicate.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer.*;

/** Assert class for LByte2Obj1Obj0Pred. */
public interface LByte2Obj1Obj0PredAssert<S extends LByte2Obj1Obj0PredAssert<S, A, RS, T2, T1>, A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T2, T1>, A, RS, Boolean> {

	@Nonnull
	public static <A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LByte2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		return new LByte2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T2, T1>, A, RS, Boolean> doesTest(byte a3, T2 a2, T1 a1);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> extends Base<The<A, RS, T2, T1>, A, RS, T2, T1> {

		public The(A actual, LBoolFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, T2, T1>, A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> extends FullFunctionalAssert.Base<S, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T2, T1>, A, RS, Boolean>
			implements
				LByte2Obj1Obj0PredAssert<S, A, RS, T2, T1> {

		protected final LBoolFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LBoolFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T2, T1>, A, RS, Boolean> doesTest(byte a3, T2 a2, T1 a1) {

			return evaluation(() -> String.format("(%s,%s,%s)", a3, a2, a1), pc -> {
				if (pc != null) {
					// pc.acceptByte2Obj0Obj1(a1,a2,a3);
					// pc.accept(a1,a2,a3);
					pc.acceptByte2Obj0Obj1(a3, a2, a1);
				}
				return assertFactory.apply(actual.testByte2Obj1Obj0(a3, a2, a1));
			});
		}

	}

}
