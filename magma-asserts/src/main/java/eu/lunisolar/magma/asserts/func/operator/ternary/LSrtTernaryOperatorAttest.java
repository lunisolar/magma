/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts.func.operator.ternary;

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

import eu.lunisolar.magma.func.operator.ternary.*;

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

/** Assert class for LSrtTernaryOperator. */
public final class LSrtTernaryOperatorAttest extends FunctionalAttest.Full<LSrtTernaryOperatorAttest, LSrtTernaryOperator, LTriSrtConsumer, Checks.CheckSrt> {

	public LSrtTernaryOperatorAttest(LSrtTernaryOperator actual) {
		super(actual);
	}

	public LSrtTernaryOperatorAttest(LSrtTernaryOperator actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static LSrtTernaryOperatorAttest attestSrtTernaryOp(LSrtTernaryOperator func) {
		return new LSrtTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LSrtTernaryOperatorAttest attestSrtTernaryOp(LSrtTernaryOperator func, String name) {
		return new LSrtTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public SrtEvaluation<LSrtTernaryOperatorAttest, LTriSrtConsumer> doesApplyAsSrt(short a1, short a2, short a3) {

		return new SrtEvaluation<LSrtTernaryOperatorAttest, LTriSrtConsumer>(this, () -> String.format("(%s,%s,%s)", a1, a2, a3), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.accept(a1, a2, a3);
			}

			var result = func.applyAsSrt(a1, a2, a3);
			return Checks.attest(result, desc);

		}, recurringAssert);
	}

}
