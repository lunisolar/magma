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

package eu.lunisolar.magma.asserts.func.function.from;

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

import eu.lunisolar.magma.func.function.from.*;

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

import eu.lunisolar.magma.func.function.from.LObjBiLongFunction.*;

/** Assert class for LObj0Long2Long1Func. */
public final class LObj0Long2Long1FuncAttest<T, R> extends FunctionalAttest.Full<LObj0Long2Long1FuncAttest<T, R>, LObj0Long2Long1Func<T, R>, LObjBiLongConsumer<T>, Checks.Check<R>> {

	public LObj0Long2Long1FuncAttest(LObj0Long2Long1Func<T, R> actual) {
		super(actual);
	}

	public LObj0Long2Long1FuncAttest(LObj0Long2Long1Func<T, R> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <T, R> LObj0Long2Long1FuncAttest<T, R> attestObj0Long2Long1Func(LObjBiLongFunction.LObj0Long2Long1Func<T, R> func) {
		return new LObj0Long2Long1FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObj0Long2Long1FuncAttest<T, R> attestObj0Long2Long1Func(LObjBiLongFunction.LObj0Long2Long1Func<T, R> func, String name) {
		return new LObj0Long2Long1FuncAttest(func, name);
	}

	@Nonnull
	public Evaluation<LObj0Long2Long1FuncAttest<T, R>, LObjBiLongConsumer<T>, R> doesApply(T a1, long a3, long a2) {

		return new Evaluation<LObj0Long2Long1FuncAttest<T, R>, LObjBiLongConsumer<T>, R>(this, () -> String.format("(%s,%s,%s)", a1, a3, a2), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.accept(a1, a3, a2);
			}

			var result = func.applyObj0Long2Long1(a1, a3, a2);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
