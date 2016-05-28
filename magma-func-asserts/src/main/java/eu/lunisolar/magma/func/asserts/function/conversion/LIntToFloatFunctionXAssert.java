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

package eu.lunisolar.magma.func.asserts.function.conversion;

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

import eu.lunisolar.magma.func.function.conversion.*;

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

/** Assert class for LIntToFloatFunctionX. */
public interface LIntToFloatFunctionXAssert<S extends LIntToFloatFunctionXAssert<S, A, RS, X>, A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, LIntConsumerX<X>, A, RS, Float> {

	@Nonnull
	Evaluation<S, LIntConsumerX<X>, A, RS, Float> doesApplyAsFloat(int a);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> extends Base<The<A, RS, X>, A, RS, X> {

		public The(A actual, LFloatFunction<RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, RS, X>, A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> extends FullFunctionalAssert.Base<S, LIntConsumerX<X>, A, RS, Float>
			implements
				LIntToFloatFunctionXAssert<S, A, RS, X> {

		protected final LFloatFunction<RS> assertFactory;

		public Base(A actual, Class<?> selfType, LFloatFunction<RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LIntConsumerX<X>, A, RS, Float> doesApplyAsFloat(int a) {

			return evaluation(pc -> {
				if (pc != null) {
					pc.doAccept(a);
				}
				return assertFactory.doApply(actual.doApplyAsFloat(a));
			});

		}

	}

}
