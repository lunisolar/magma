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

package eu.lunisolar.magma.asserts.func.predicate;

import eu.lunisolar.magma.asserts.func.FunctionalAttest.AssertionsCheck;
import eu.lunisolar.magma.asserts.func.FunctionalAttest.SemiEvaluation;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.asserts.func.FunctionalAttest;
import eu.lunisolar.magma.asserts.func.FunctionalAttest.*;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import eu.lunisolar.magma.func.supp.check.Checks; // NOSONAR
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

import eu.lunisolar.magma.func.predicate.LObjBiIntPredicate.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.*;

/** Assert class for LBiInt1Obj0Pred. */
public final class LBiInt1Obj0PredAttest<T> extends FunctionalAttest.Full<LBiInt1Obj0PredAttest<T>, LBiInt1Obj0Pred<T>, LTieIntConsumer.LInt1Int2Obj0Cons<T>, Checks.CheckBool> {

	public LBiInt1Obj0PredAttest(LBiInt1Obj0Pred<T> actual) {
		super(actual);
	}

	public LBiInt1Obj0PredAttest(LBiInt1Obj0Pred<T> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <T> LBiInt1Obj0PredAttest<T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		return new LBiInt1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T> LBiInt1Obj0PredAttest<T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func, String name) {
		return new LBiInt1Obj0PredAttest(func, name);
	}

	@Nonnull
	public BoolEvaluation<LBiInt1Obj0PredAttest<T>, LTieIntConsumer.LInt1Int2Obj0Cons<T>> doesTest(int a3, int a2, T a1) {

		return new BoolEvaluation<LBiInt1Obj0PredAttest<T>, LTieIntConsumer.LInt1Int2Obj0Cons<T>>(this, () -> String.format("(%s,%s,%s)", a3, a2, a1), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptInt1Int2Obj0(a3, a2, a1);
			}

			var result = func.testBiInt1Obj0(a3, a2, a1);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
