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

import eu.lunisolar.magma.func.consumer.primitives.obj.LTieCharConsumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieCharConsumer.*;

/** Assert class for LIntObjCharCons. */
public final class LIntObjCharConsAttest<T> extends FunctionalAttest.Simple<LIntObjCharConsAttest<T>, LIntObjCharCons<T>, LTieCharConsumer.LIntObjCharCons<T>> {

	public LIntObjCharConsAttest(LIntObjCharCons<T> actual) {
		super(actual);
	}

	public LIntObjCharConsAttest(LIntObjCharCons<T> actual, String name) {
		super(actual, name);
	}

	@Nonnull
	public static <T> LIntObjCharConsAttest<T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjCharConsAttest<T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func, String name) {
		return new LIntObjCharConsAttest(func, name);
	}

	@Nonnull
	public SemiEvaluation<LIntObjCharConsAttest<T>, LTieCharConsumer.LIntObjCharCons<T>, AssertionsCheck> doesAccept(int a2, T a1, char a3) {

		return new SemiEvaluation<LIntObjCharConsAttest<T>, LTieCharConsumer.LIntObjCharCons<T>, AssertionsCheck>(this, () -> String.format("(%s,%s,%s)", a2, a1, a3), (desc, pc) -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptIntObjChar(a2, a1, a3);
			}

			func.acceptIntObjChar(a2, a1, a3);
			return null;

		}, recurringAssert);
	}

}
