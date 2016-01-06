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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

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
public class LCharToByteFuncDeltaX<X extends Throwable> extends LCharToByteFuncMementoX<X> {

	protected final LByteBinaryOperator deltaFunction;

	protected LCharToByteFuncDeltaX(LCharToByteFunctionX<X> function, LByteBinaryOperator deltaFunction) {
		super(function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	protected LCharToByteFuncDeltaX(byte initialValue, LCharToByteFunctionX<X> function, LByteBinaryOperator deltaFunction) {
		super(initialValue, function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	public static <X extends Throwable> LCharToByteFuncDeltaX<X> deltaOf(LCharToByteFunctionX<X> function, LByteBinaryOperator deltaFunction) {
		return new LCharToByteFuncDeltaX<X>(function, deltaFunction);
	}

	public static <X extends Throwable> LCharToByteFuncDeltaX<X> deltaOf(byte initialValue, LCharToByteFunctionX<X> function, LByteBinaryOperator deltaFunction) {
		return new LCharToByteFuncDeltaX<X>(initialValue, function, deltaFunction);
	}

	public static <X extends Throwable> LCharToByteFuncDeltaX<X> deltaOf(LCharToByteFunctionX<X> function) {
		return deltaOf(function, LCharToByteFuncDeltaX::mathDelta);
	}

	public static <X extends Throwable> LCharToByteFuncDeltaX<X> deltaOf(byte initialValue, LCharToByteFunctionX<X> function) {
		return deltaOf(initialValue, function, LCharToByteFuncDeltaX::mathDelta);
	}

	public static byte mathDelta(byte last, byte current) {
		return (byte) (current - last);
	}

	@Override
	public byte doApplyAsByte(char a1) throws X {
		return deltaFunction.doApplyAsByte(lastValue(), super.doApplyAsByte(a1));
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LCharToByteFuncDeltaX the, Object that) {
		return Null.<LCharToByteFuncDeltaX> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LCharToByteFuncDeltaX other = (LCharToByteFuncDeltaX) two;

			return LBiObjByteTriple.argEquals(one.function, one.deltaFunction, one.lastValue(), other.function, other.deltaFunction, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LBiObjByteTriple.argHashCode(function, deltaFunction, lastValue);
	}

	// </editor-fold>

}
