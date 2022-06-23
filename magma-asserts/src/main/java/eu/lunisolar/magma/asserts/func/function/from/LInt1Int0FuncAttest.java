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

import eu.lunisolar.magma.func.function.from.LBiIntFunction.*;

/** Assert class for LInt1Int0Func. */
public final class LInt1Int0FuncAttest<R> extends FunctionalAttest.Full<LInt1Int0FuncAttest<R>, LInt1Int0Func<R>, LBiIntConsumer, Checks.Check<R>> {

	public LInt1Int0FuncAttest(LInt1Int0Func<R> actual) {
		super(actual);
	}

	public LInt1Int0FuncAttest(LInt1Int0Func<R> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <R> LInt1Int0FuncAttest<R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) {
		return new LInt1Int0FuncAttest(func);
	}

	@Nonnull
	public static <R> LInt1Int0FuncAttest<R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func, String name) {
		return new LInt1Int0FuncAttest(func, name);
	}

	@Nonnull
	public Evaluation<LInt1Int0FuncAttest<R>, LBiIntConsumer, R> doesApply(int a2, int a1) {

		return new Evaluation<LInt1Int0FuncAttest<R>, LBiIntConsumer, R>(this, () -> String.format("(%s,%s)", a2, a1), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.accept(a2, a1);
			}

			var result = func.applyInt1Int0(a2, a1);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
