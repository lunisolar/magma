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

import eu.lunisolar.magma.func.function.to.LToIntTriFunction.*;

/** Assert class for LToIntObj1BiObj2Func. */
public final class LToIntObj1BiObj2FuncAttest<T2, T1, T3> extends FunctionalAttest.Full<LToIntObj1BiObj2FuncAttest<T2, T1, T3>, LToIntObj1BiObj2Func<T2, T1, T3>, LTriConsumer<T2, T1, T3>, Checks.CheckInt> {

	public LToIntObj1BiObj2FuncAttest(LToIntObj1BiObj2Func<T2, T1, T3> actual) {
		super(actual);
	}

	public LToIntObj1BiObj2FuncAttest(LToIntObj1BiObj2Func<T2, T1, T3> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <T2, T1, T3> LToIntObj1BiObj2FuncAttest<T2, T1, T3> attestToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		return new LToIntObj1BiObj2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, T3> LToIntObj1BiObj2FuncAttest<T2, T1, T3> attestToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func, String name) {
		return new LToIntObj1BiObj2FuncAttest(func, name);
	}

	@Nonnull
	public IntEvaluation<LToIntObj1BiObj2FuncAttest<T2, T1, T3>, LTriConsumer<T2, T1, T3>> doesApplyAsInt(T2 a2, T1 a1, T3 a3) {

		return new IntEvaluation<LToIntObj1BiObj2FuncAttest<T2, T1, T3>, LTriConsumer<T2, T1, T3>>(this, () -> String.format("(%s,%s,%s)", a2, a1, a3), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.accept(a2, a1, a3);
			}

			var result = func.applyAsIntObj1BiObj2(a2, a1, a3);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
