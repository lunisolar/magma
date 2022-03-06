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

import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.*;

/** Assert class for LInt1Obj0Int2Cons. */
public final class LInt1Obj0Int2ConsAttest<T> extends FunctionalAttest.Simple<LInt1Obj0Int2ConsAttest<T>, LInt1Obj0Int2Cons<T>, LTieIntConsumer.LInt1Obj0Int2Cons<T>> {

	public LInt1Obj0Int2ConsAttest(LInt1Obj0Int2Cons<T> actual) {
		super(actual);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2ConsAttest<T> attestInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAttest(func);
	}

	@Nonnull
	public SemiEvaluation<LInt1Obj0Int2ConsAttest<T>, LTieIntConsumer.LInt1Obj0Int2Cons<T>, AssertionsCheck> doesAccept(int a2, T a1, int a3) {

		return new SemiEvaluation<LInt1Obj0Int2ConsAttest<T>, LTieIntConsumer.LInt1Obj0Int2Cons<T>, AssertionsCheck>(this, () -> String.format("(%s,%s,%s)", a2, a1, a3), pc -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptInt1Obj0Int2(a2, a1, a3);
			}

			func.acceptInt1Obj0Int2(a2, a1, a3);
			return null;

		}, recurringAssert);
	}

}
