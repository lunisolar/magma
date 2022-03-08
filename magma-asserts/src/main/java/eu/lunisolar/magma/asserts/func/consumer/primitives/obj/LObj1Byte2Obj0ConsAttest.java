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

import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer.*;

/** Assert class for LObj1Byte2Obj0Cons. */
public final class LObj1Byte2Obj0ConsAttest<T2, T1> extends FunctionalAttest.Simple<LObj1Byte2Obj0ConsAttest<T2, T1>, LObj1Byte2Obj0Cons<T2, T1>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T2, T1>> {

	public LObj1Byte2Obj0ConsAttest(LObj1Byte2Obj0Cons<T2, T1> actual) {
		super(actual);
	}

	@Nonnull
	public static <T2, T1> LObj1Byte2Obj0ConsAttest<T2, T1> attestObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAttest(func);
	}

	@Nonnull
	public SemiEvaluation<LObj1Byte2Obj0ConsAttest<T2, T1>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T2, T1>, AssertionsCheck> doesAccept(T2 a2, byte a3, T1 a1) {

		return new SemiEvaluation<LObj1Byte2Obj0ConsAttest<T2, T1>, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T2, T1>, AssertionsCheck>(this, () -> String.format("(%s,%s,%s)", a2, a3, a1), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptObj0Byte2Obj1(a2, a3, a1);
			}

			func.acceptObj1Byte2Obj0(a2, a3, a1);
			return null;

		}, recurringAssert);
	}

}
