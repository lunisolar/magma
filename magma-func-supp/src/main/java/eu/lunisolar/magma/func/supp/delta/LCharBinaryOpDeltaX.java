/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.delta;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Counts function result delta between sequential calls of the function.
 */
@SuppressWarnings("UnusedDeclaration")
public class LCharBinaryOpDeltaX<X extends Throwable> extends LCharBinaryOpMementoX<X> {

	private final LCharBinaryOperator deltaFunction;

	protected LCharBinaryOpDeltaX(LCharBinaryOperatorX<X> function, LCharBinaryOperator deltaFunction) throws X {
		super(function);
		this.deltaFunction = deltaFunction;
	}

	protected LCharBinaryOpDeltaX(char initialValue, LCharBinaryOperatorX<X> function, LCharBinaryOperator deltaFunction) throws X {
		super(initialValue, function);
		this.deltaFunction = deltaFunction;
	}

	public static <X extends Throwable> LCharBinaryOpDeltaX<X> deltaOf(LCharBinaryOperatorX<X> function, LCharBinaryOperator deltaFunction) throws X {
		return new LCharBinaryOpDeltaX<X>(function, deltaFunction);
	}

	public static <X extends Throwable> LCharBinaryOpDeltaX<X> deltaOf(char initialValue, LCharBinaryOperatorX<X> function, LCharBinaryOperator deltaFunction) throws X {
		return new LCharBinaryOpDeltaX<X>(initialValue, function, deltaFunction);
	}

	public static <X extends Throwable> LCharBinaryOpDeltaX<X> deltaOf(LCharBinaryOperatorX<X> function) throws X {
		return deltaOf(function, LCharBinaryOpDeltaX::mathDelta);
	}

	public static <X extends Throwable> LCharBinaryOpDeltaX<X> deltaOf(char initialValue, LCharBinaryOperatorX<X> function) throws X {
		return deltaOf(initialValue, function, LCharBinaryOpDeltaX::mathDelta);
	}

	public static char mathDelta(char last, char current) {
		return (char) (current - last);
	}

	public char doApplyAsChar(char a1, char a2) throws X {
		return deltaFunction.doApplyAsChar(lastValue(), super.doApplyAsChar(a1, a2));
	}

}
