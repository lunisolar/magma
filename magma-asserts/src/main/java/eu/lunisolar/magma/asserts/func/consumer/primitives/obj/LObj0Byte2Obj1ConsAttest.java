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
import org.assertj.core.api.*; // NOSONAR
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

import static org.assertj.core.api.Fail.fail;
import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer.*;

/** Assert class for LObj0Byte2Obj1Cons. */
public final class LObj0Byte2Obj1ConsAttest<T1, T2> extends FunctionalAttest.Simple<LObj0Byte2Obj1ConsAttest<T1, T2>, LObj0Byte2Obj1Cons<T1, T2>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>> {

	public LObj0Byte2Obj1ConsAttest(LObj0Byte2Obj1Cons<T1, T2> actual) {
		super(actual);
	}

	@Nonnull
	public static <T1, T2> LObj0Byte2Obj1ConsAttest<T1, T2> attestObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAttest(func);
	}

	@Nonnull
	public SemiEvaluation<LObj0Byte2Obj1ConsAttest<T1, T2>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, AssertionsCheck> doesAccept(T1 a1, byte a3, T2 a2) {

		return new SemiEvaluation<LObj0Byte2Obj1ConsAttest<T1, T2>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, AssertionsCheck>(this, () -> String.format("(%s,%s,%s)", a1, a3, a2), pc -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptObj0Byte2Obj1(a1, a3, a2);
			}

			func.acceptObj0Byte2Obj1(a1, a3, a2);
			return null;

		}, recurringAssert);
	}

}
