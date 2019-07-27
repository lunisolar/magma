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
public class LDblToCharFuncMemento implements LDblToCharFunction {

	protected char lastValue;

	protected LDblToCharFunction function;

	protected LDblToCharFuncMemento(LDblToCharFunction function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	protected LDblToCharFuncMemento(char initialValue, LDblToCharFunction function) {
		this(function);
		this.lastValue = initialValue;
	}

	/**
	 * Memento of a function, without taking the initial value from it.  
	 */
	public static LDblToCharFuncMemento hollowMementoOf(LDblToCharFunction supplier) {
		return new LDblToCharFuncMemento(supplier);
	}

	/**
	 * Memento of a function, initialized with value from it.   
	 */
	public static LDblToCharFuncMemento mementoOf(double a, LDblToCharFunction supplier) {
		return new LDblToCharFuncMemento(supplier.applyAsChar(a), supplier);
	}

	@Override
	public char applyAsCharX(double a) {
		return lastValue = function.applyAsChar(a);
	}

	public char lastValue() {
		return lastValue;
	}

	public char delta(double a) {
		char last = lastValue;
		return (char) (applyAsChar(a) - last);
	}

	public char delta(double a, LCharBinaryOperator deltaFunction) {
		char last = lastValue;
		return deltaFunction.applyAsChar(applyAsChar(a), last);
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LDblToCharFuncMemento the, Object that) {
		return Null.<LDblToCharFuncMemento> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LDblToCharFuncMemento other = (LDblToCharFuncMemento) two;

			return LObjCharPair.argEquals(one.function, one.lastValue(), other.function, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LObjCharPair.argHashCode(function, lastValue);
	}

	// </editor-fold>

}