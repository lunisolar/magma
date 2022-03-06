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
import org.assertj.core.api.*; // NOSONAR
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

import static org.assertj.core.api.Fail.fail;
import eu.lunisolar.magma.func.function.to.LTieByteFunction.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieByteConsumer.*;

/** Assert class for LObjByteIntToIntFunc. */
public final class LObjByteIntToIntFuncAttest<T> extends FunctionalAttest.Full<LObjByteIntToIntFuncAttest<T>, LObjByteIntToIntFunc<T>, LTieByteConsumer.LObjByteIntCons<T>, Checks.CheckInt> {

	public LObjByteIntToIntFuncAttest(LObjByteIntToIntFunc<T> actual) {
		super(actual);
	}

	@Nonnull
	public static <T> LObjByteIntToIntFuncAttest<T> attestObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		return new LObjByteIntToIntFuncAttest(func);
	}

	@Nonnull
	public IntEvaluation<LObjByteIntToIntFuncAttest<T>, LTieByteConsumer.LObjByteIntCons<T>> doesApplyAsInt(T a1, byte a3, int a2) {

		return new IntEvaluation<LObjByteIntToIntFuncAttest<T>, LTieByteConsumer.LObjByteIntCons<T>>(this, () -> String.format("(%s,%s,%s)", a1, a3, a2), pc -> {

			var func = value();
			Checks.check(func).must(Be::notNull, "Actual function is null.");

			if (pc != null) {
				pc.acceptObjByteInt(a1, a3, a2);
			}

			var result = func.applyAsIntObjByteInt(a1, a3, a2);
			return Checks.attest(result);

		}, recurringAssert);
	}

}
