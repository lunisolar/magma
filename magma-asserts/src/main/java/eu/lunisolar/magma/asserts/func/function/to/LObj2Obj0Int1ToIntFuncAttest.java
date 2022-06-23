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

package eu.lunisolar.magma.asserts.func.function.to;

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

import eu.lunisolar.magma.func.function.to.*;

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

import eu.lunisolar.magma.func.function.to.LTieFunction.*;

/** Assert class for LObj2Obj0Int1ToIntFunc. */
public final class LObj2Obj0Int1ToIntFuncAttest<T2, T1> extends FunctionalAttest.Full<LObj2Obj0Int1ToIntFuncAttest<T2, T1>, LObj2Obj0Int1ToIntFunc<T2, T1>, LBiObjIntConsumer<T2, T1>, Checks.CheckInt> {

	public LObj2Obj0Int1ToIntFuncAttest(LObj2Obj0Int1ToIntFunc<T2, T1> actual) {
		super(actual);
	}

	public LObj2Obj0Int1ToIntFuncAttest(LObj2Obj0Int1ToIntFunc<T2, T1> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <T2, T1> LObj2Obj0Int1ToIntFuncAttest<T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		return new LObj2Obj0Int1ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj2Obj0Int1ToIntFuncAttest<T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func, String name) {
		return new LObj2Obj0Int1ToIntFuncAttest(func, name);
	}

	@Nonnull
	public IntEvaluation<LObj2Obj0Int1ToIntFuncAttest<T2, T1>, LBiObjIntConsumer<T2, T1>> doesApplyAsInt(T2 a3, T1 a1, int a2) {

		return new IntEvaluation<LObj2Obj0Int1ToIntFuncAttest<T2, T1>, LBiObjIntConsumer<T2, T1>>(this, () -> String.format("(%s,%s,%s)", a3, a1, a2), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.accept(a3, a1, a2);
			}

			var result = func.applyAsIntObj2Obj0Int1(a3, a1, a2);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
