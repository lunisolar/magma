/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.asserts.predicate;

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

/** Assert class for LBiPredicate. */
public interface LBiPredicateAssert<S extends LBiPredicateAssert<S, A, RS, T1, T2>, A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> extends Assert<S, A>, FullFunctionalAssert<S, LBiConsumer<T1, T2>, A, RS, Boolean> {

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> assertBiPred(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LBiConsumer<T1, T2>, A, RS, Boolean> doesTest(T1 a1, T2 a2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> extends Base<The<A, RS, T1, T2>, A, RS, T1, T2> {

		public The(A actual, LBoolFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, T1, T2>, A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> extends FullFunctionalAssert.Base<S, LBiConsumer<T1, T2>, A, RS, Boolean> implements LBiPredicateAssert<S, A, RS, T1, T2> {

		protected final LBoolFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LBoolFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LBiConsumer<T1, T2>, A, RS, Boolean> doesTest(T1 a1, T2 a2) {

			return evaluation(pc -> {
				if (pc != null) {
					pc.doAccept(a1, a2);
				}
				return assertFactory.doApply(actual.doTest(a1, a2));
			});

		}

	}

}
