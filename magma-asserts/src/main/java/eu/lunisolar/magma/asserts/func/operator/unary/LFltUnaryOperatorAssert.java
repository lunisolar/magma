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

package eu.lunisolar.magma.asserts.func.operator.unary;

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

import eu.lunisolar.magma.func.operator.unary.*;

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

/** Assert class for LFltUnaryOperator. */
public interface LFltUnaryOperatorAssert<S extends LFltUnaryOperatorAssert<S, A, RS>, A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> extends Assert<S, A>, FullFunctionalAssert<S, LFltConsumer, A, RS, Float> {

	@Nonnull
	public static <A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> LFltUnaryOperatorAssert.The<A, RS> attestFltUnaryOp(LFltUnaryOperator func) {
		return new LFltUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	Evaluation<S, LFltConsumer, A, RS, Float> doesApplyAsFlt(float a);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> extends Base<The<A, RS>, A, RS> {

		public The(A actual, LFltFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS>, A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> extends FullFunctionalAssert.Base<S, LFltConsumer, A, RS, Float> implements LFltUnaryOperatorAssert<S, A, RS> {

		protected final LFltFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LFltFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LFltConsumer, A, RS, Float> doesApplyAsFlt(float a) {

			return evaluation(() -> String.format("(%s)", a), pc -> {
				if (pc != null) {
					pc.accept(a);
				}
				return assertFactory.apply(actual.applyAsFlt(a));
			});
		}

	}

}
