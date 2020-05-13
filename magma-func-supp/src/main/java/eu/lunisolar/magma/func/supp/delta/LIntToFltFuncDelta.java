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
public class LIntToFltFuncDelta extends LIntToFltFuncMemento {

	protected final LFltBinaryOperator deltaFunction;

	protected LIntToFltFuncDelta(LIntToFltFunction function, LFltBinaryOperator deltaFunction) {
		super(function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	protected LIntToFltFuncDelta(float initialValue, LIntToFltFunction function, LFltBinaryOperator deltaFunction) {
		super(initialValue, function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	public static LIntToFltFuncDelta deltaOf(LIntToFltFunction function, LFltBinaryOperator deltaFunction) {
		return new LIntToFltFuncDelta(function, deltaFunction);
	}

	public static LIntToFltFuncDelta deltaOf(float initialValue, LIntToFltFunction function, LFltBinaryOperator deltaFunction) {
		return new LIntToFltFuncDelta(initialValue, function, deltaFunction);
	}

	public static LIntToFltFuncDelta initializedDeltaOf(int a, LIntToFltFunction function, LFltBinaryOperator deltaFunction) {
		var delta = deltaOf(function, deltaFunction);
		delta.applyAsFlt(a);
		return delta;
	}

	public static LIntToFltFuncDelta deltaOf(LIntToFltFunction function) {
		return deltaOf(function, LIntToFltFuncDelta::mathDelta);
	}

	public static LIntToFltFuncDelta initializedDeltaOf(int a, LIntToFltFunction function) {
		var delta = deltaOf(function);
		delta.applyAsFlt(a);
		return delta;
	}

	public static LIntToFltFuncDelta deltaOf(float initialValue, LIntToFltFunction function) {
		return deltaOf(initialValue, function, LIntToFltFuncDelta::mathDelta);
	}

	public static float mathDelta(float last, float current) {
		return current - last;
	}

	@Override
	public float applyAsFlt(int a) {
		return deltaFunction.applyAsFlt(lastValue(), super.applyAsFlt(a));
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LIntToFltFuncDelta the, Object that) {
		return Null.<LIntToFltFuncDelta> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LIntToFltFuncDelta other = (LIntToFltFuncDelta) two;

			return LBiObjFltTriple.argEquals(one.function, one.deltaFunction, one.lastValue(), other.function, other.deltaFunction, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LBiObjFltTriple.argHashCode(function, deltaFunction, lastValue);
	}

	// </editor-fold>

	/** Overrides delta() method from memento generalization to make result consistent with provided delta function. */
	@Override
	public float delta(int a) {
		return applyAsFlt(a);
	}

	@Override
	public float delta(int a, LFltBinaryOperator deltaFunction) {
		throw new IllegalStateException("Application of another delta function is impossible.");
	}

}
