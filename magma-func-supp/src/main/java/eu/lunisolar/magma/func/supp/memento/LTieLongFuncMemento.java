/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

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
 * Remembers the last function result.
 */
@SuppressWarnings("UnusedDeclaration")
public class LTieLongFuncMemento<T> implements LTieLongFunction<T> {

	protected int lastValue;

	protected LTieLongFunction<T> function;

	protected LTieLongFuncMemento(LTieLongFunction<T> function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	protected LTieLongFuncMemento(int initialValue, LTieLongFunction<T> function) {
		this(function);
		this.lastValue = initialValue;
	}

	/**
	 * Memento of a function, without taking the initial value from it.  
	 */
	public static <T> LTieLongFuncMemento<T> hollowMementoOf(LTieLongFunction<T> supplier) {
		return new LTieLongFuncMemento<T>(supplier);
	}

	/**
	 * Memento of a function, initialized with value from it.   
	 */
	public static <T> LTieLongFuncMemento<T> mementoOf(T a1, int a2, long a3, LTieLongFunction<T> supplier) {
		return new LTieLongFuncMemento<T>(supplier.applyAsInt(a1, a2, a3), supplier);
	}

	@Override
	public int applyAsIntX(T a1, int a2, long a3) {
		return lastValue = function.applyAsInt(a1, a2, a3);
	}

	public int lastValue() {
		return lastValue;
	}

	public int delta(T a1, int a2, long a3) {
		int last = lastValue;
		return (int) (applyAsInt(a1, a2, a3) - last);
	}

	public int delta(T a1, int a2, long a3, LIntBinaryOperator deltaFunction) {
		int last = lastValue;
		return deltaFunction.applyAsInt(applyAsInt(a1, a2, a3), last);
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LTieLongFuncMemento the, Object that) {
		return Null.<LTieLongFuncMemento> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LTieLongFuncMemento other = (LTieLongFuncMemento) two;

			return LObjIntPair.argEquals(one.function, one.lastValue(), other.function, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LObjIntPair.argHashCode(function, lastValue);
	}

	// </editor-fold>

}