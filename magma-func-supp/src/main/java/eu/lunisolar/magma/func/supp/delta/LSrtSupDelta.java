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
 * Counts function result delta between sequential calls of the function.
 */
@SuppressWarnings("UnusedDeclaration")
public class LSrtSupDelta extends LSrtSupMemento {

	protected final LSrtBinaryOperator deltaFunction;

	protected LSrtSupDelta(LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		super(function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	protected LSrtSupDelta(short initialValue, LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		super(initialValue, function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	public static LSrtSupDelta deltaOf(LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		return new LSrtSupDelta(function, deltaFunction);
	}

	public static LSrtSupDelta deltaOf(short initialValue, LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		return new LSrtSupDelta(initialValue, function, deltaFunction);
	}

	public static LSrtSupDelta initializedDeltaOf(LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		var delta = deltaOf(function, deltaFunction);
		delta.getAsSrt();
		return delta;
	}

	public static LSrtSupDelta deltaOf(LSrtSupplier function) {
		return deltaOf(function, LSrtSupDelta::mathDelta);
	}

	public static LSrtSupDelta initializedDeltaOf(LSrtSupplier function) {
		var delta = deltaOf(function);
		delta.getAsSrt();
		return delta;
	}

	public static LSrtSupDelta deltaOf(short initialValue, LSrtSupplier function) {
		return deltaOf(initialValue, function, LSrtSupDelta::mathDelta);
	}

	public static short mathDelta(short last, short current) {
		return (short) (current - last);
	}

	@Override
	public short getAsSrt() {
		return deltaFunction.applyAsSrt(lastValue(), super.getAsSrt());
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LSrtSupDelta the, Object that) {
		return Null.<LSrtSupDelta> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LSrtSupDelta other = (LSrtSupDelta) two;

			return LBiObjSrtTriple.argEquals(one.function, one.deltaFunction, one.lastValue(), other.function, other.deltaFunction, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LBiObjSrtTriple.argHashCode(function, deltaFunction, lastValue);
	}

	// </editor-fold>

	/** Overrides delta() method from memento generalization to make result consistent with provided delta function. */
	@Override
	public short delta() {
		return getAsSrt();
	}

	@Override
	public short delta(LSrtBinaryOperator deltaFunction) {
		throw new IllegalStateException("Application of another delta function is impossible.");
	}

}
