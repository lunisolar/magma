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

package eu.lunisolar.magma.asserts.func.consumer;

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

import eu.lunisolar.magma.func.consumer.*;

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

/** Assert class for LQuintConsumer. */
public interface LQuintConsumerAssert<S extends LQuintConsumerAssert<S, A, T1, T2, T3, T4, T5>, A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> extends Assert<S, A>, FunctionalAssert.Simple<S, LQuintConsumer<T1, T2, T3, T4, T5>, A> {

	@Nonnull
	public static <A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintConsumerAssert.The<A, T1, T2, T3, T4, T5> attestQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAssert.The(func);
	}

	@Nonnull
	SemiEvaluation<S, LQuintConsumer<T1, T2, T3, T4, T5>, A> doesAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> extends Base<The<A, T1, T2, T3, T4, T5>, A, T1, T2, T3, T4, T5> {

		public The(A actual) {
			super(actual, The.class);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, T1, T2, T3, T4, T5>, A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> extends FunctionalAssert.Simple.Base<S, LQuintConsumer<T1, T2, T3, T4, T5>, A>
			implements
				LQuintConsumerAssert<S, A, T1, T2, T3, T4, T5> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, LQuintConsumer<T1, T2, T3, T4, T5>, A> doesAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {

			return evaluation(() -> String.format("(%s,%s,%s,%s,%s)", a1, a2, a3, a4, a5), pc -> {
				if (pc != null) {
					pc.accept(a1, a2, a3, a4, a5);
				}
				actual.accept(a1, a2, a3, a4, a5);
				return null;
			});
		}

	}

}
