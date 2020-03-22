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

package eu.lunisolar.magma.asserts.func.consumer.primitives.bi;

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

import eu.lunisolar.magma.func.consumer.primitives.bi.*;

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
import eu.lunisolar.magma.func.consumer.primitives.bi.LDblIntConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.bi.LDblIntConsumer.*;

/** Assert class for LIntDblCons. */
public interface LIntDblConsAssert<S extends LIntDblConsAssert<S, A>, A extends LDblIntConsumer.LIntDblCons> extends Assert<S, A>, FunctionalAssert.Simple<S, LDblIntConsumer.LIntDblCons, A> {

	@Nonnull
	public static <A extends LDblIntConsumer.LIntDblCons> LIntDblConsAssert.The<A> attestIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAssert.The(func);
	}

	@Nonnull
	SemiEvaluation<S, LDblIntConsumer.LIntDblCons, A> doesAccept(int a2, double a1);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LDblIntConsumer.LIntDblCons> extends Base<The<A>, A> {

		public The(A actual) {
			super(actual, The.class);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A>, A extends LDblIntConsumer.LIntDblCons> extends FunctionalAssert.Simple.Base<S, LDblIntConsumer.LIntDblCons, A> implements LIntDblConsAssert<S, A> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, LDblIntConsumer.LIntDblCons, A> doesAccept(int a2, double a1) {

			return evaluation(() -> String.format("(%s,%s)", a2, a1), pc -> {
				if (pc != null) {
					// pc.acceptIntDbl(a1,a2);
					// pc.accept(a1,a2);
					pc.acceptIntDbl(a2, a1);
				}
				actual.acceptIntDbl(a2, a1);
				return null;
			});
		}

	}

}
