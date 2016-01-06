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

package eu.lunisolar.magma.func.supp.memento;

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
 * Remembers the last fubction result.
 */
@SuppressWarnings("UnusedDeclaration")
public class LBiObjCharFuncMementoX<T1, T2, R, X extends Throwable> implements LBiObjCharFunctionX<T1, T2, R, X> {

	protected R lastValue;

	protected LBiObjCharFunctionX<T1, T2, R, X> function;

	protected LBiObjCharFuncMementoX(LBiObjCharFunctionX<T1, T2, R, X> function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	protected LBiObjCharFuncMementoX(R initialValue, LBiObjCharFunctionX<T1, T2, R, X> function) {
		this(function);
		this.lastValue = initialValue;
	}

	public static <T1, T2, R, X extends Throwable> LBiObjCharFuncMementoX<T1, T2, R, X> mementoOf(LBiObjCharFunctionX<T1, T2, R, X> supplier) {
		return new LBiObjCharFuncMementoX<T1, T2, R, X>(supplier);
	}

	@Override
	public R doApply(T1 a1, T2 a2, char a3) throws X {
		return lastValue = function.doApply(a1, a2, a3);
	}

	public R lastValue() {
		return lastValue;
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LBiObjCharFuncMementoX the, Object that) {
		return Null.<LBiObjCharFuncMementoX> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LBiObjCharFuncMementoX other = (LBiObjCharFuncMementoX) two;

			return LPair.argEquals(one.function, one.lastValue(), other.function, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LPair.argHashCode(function, lastValue);
	}

	// </editor-fold>

}
