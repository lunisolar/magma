/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts.func.consumer.primitives.obj;

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

import eu.lunisolar.magma.func.consumer.primitives.obj.*;

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
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.*;

/** Assert class for LInt2Obj0Int1Cons. */
public interface LInt2Obj0Int1ConsAssert<S extends LInt2Obj0Int1ConsAssert<S, A, T>, A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> extends Assert<S, A>, FunctionalAssert.Simple<S, LTieIntConsumer.LInt1Obj0Int2Cons<T>, A> {

	@Nonnull
	public static <A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> LInt2Obj0Int1ConsAssert.The<A, T> attestInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAssert.The(func);
	}

	@Nonnull
	SemiEvaluation<S, LTieIntConsumer.LInt1Obj0Int2Cons<T>, A> doesAccept(int a3, T a1, int a2);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	final class The<A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> extends Base<The<A, T>, A, T> {

		public The(A actual) {
			super(actual, The.class);
		}
	}

	/** Base implementation. For potential extending (requires to define all generic parameters). */
	class Base<S extends Base<S, A, T>, A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> extends FunctionalAssert.Simple.Base<S, LTieIntConsumer.LInt1Obj0Int2Cons<T>, A> implements LInt2Obj0Int1ConsAssert<S, A, T> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, LTieIntConsumer.LInt1Obj0Int2Cons<T>, A> doesAccept(int a3, T a1, int a2) {

			return evaluation(() -> String.format("(%s,%s,%s)", a3, a1, a2), pc -> {
				if (pc != null) {
					// pc.acceptInt1Obj0Int2(a1,a2,a3);
					// pc.accept(a1,a2,a3);
					pc.acceptInt1Obj0Int2(a3, a1, a2);
				}
				actual.acceptInt2Obj0Int1(a3, a1, a2);
				return null;
			});
		}

	}

}
