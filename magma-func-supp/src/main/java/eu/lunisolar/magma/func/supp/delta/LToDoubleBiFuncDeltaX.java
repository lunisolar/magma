/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

/**
 * Counts function result delta between sequential calls of the function.
 */
@SuppressWarnings("UnusedDeclaration")
public class LToDoubleBiFuncDeltaX<T1, T2, X extends Throwable> extends LToDoubleBiFuncMementoX<T1, T2, X> {

	protected final LDoubleBinaryOperatorX<X> deltaFunction;

	protected LToDoubleBiFuncDeltaX(LToDoubleBiFunctionX<T1, T2, X> function, LDoubleBinaryOperatorX<X> deltaFunction) {
		super(function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	protected LToDoubleBiFuncDeltaX(double initialValue, LToDoubleBiFunctionX<T1, T2, X> function, LDoubleBinaryOperatorX<X> deltaFunction) {
		super(initialValue, function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	public static <T1, T2, X extends Throwable> LToDoubleBiFuncDeltaX<T1, T2, X> deltaOf(LToDoubleBiFunctionX<T1, T2, X> function, LDoubleBinaryOperatorX<X> deltaFunction) {
		return new LToDoubleBiFuncDeltaX<T1, T2, X>(function, deltaFunction);
	}

	public static <T1, T2, X extends Throwable> LToDoubleBiFuncDeltaX<T1, T2, X> deltaOf(double initialValue, LToDoubleBiFunctionX<T1, T2, X> function, LDoubleBinaryOperatorX<X> deltaFunction) {
		return new LToDoubleBiFuncDeltaX<T1, T2, X>(initialValue, function, deltaFunction);
	}

	public static <T1, T2, X extends Throwable> LToDoubleBiFuncDeltaX<T1, T2, X> deltaOf(LToDoubleBiFunctionX<T1, T2, X> function) {
		return deltaOf(function, LToDoubleBiFuncDeltaX::mathDelta);
	}

	public static <T1, T2, X extends Throwable> LToDoubleBiFuncDeltaX<T1, T2, X> deltaOf(double initialValue, LToDoubleBiFunctionX<T1, T2, X> function) {
		return deltaOf(initialValue, function, LToDoubleBiFuncDeltaX::mathDelta);
	}

	public static double mathDelta(double last, double current) {
		return current - last;
	}

	@Override
	public double doApplyAsDouble(T1 a1, T2 a2) throws X {
		return deltaFunction.doApplyAsDouble(lastValue(), super.doApplyAsDouble(a1, a2));
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LToDoubleBiFuncDeltaX the, Object that) {
		return Null.<LToDoubleBiFuncDeltaX> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LToDoubleBiFuncDeltaX other = (LToDoubleBiFuncDeltaX) two;

			return LBiObjDoubleTriple.argEquals(one.function, one.deltaFunction, one.lastValue(), other.function, other.deltaFunction, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LBiObjDoubleTriple.argHashCode(function, deltaFunction, lastValue);
	}

	// </editor-fold>

}
