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
public class LSrtToLongFuncDelta extends LSrtToLongFuncMemento {

	protected final LLongBinaryOperator deltaFunction;

	protected LSrtToLongFuncDelta(LSrtToLongFunction function, LLongBinaryOperator deltaFunction) {
		super(function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	protected LSrtToLongFuncDelta(long initialValue, LSrtToLongFunction function, LLongBinaryOperator deltaFunction) {
		super(initialValue, function);
		Null.nonNullArg(deltaFunction, "deltaFunction");
		this.deltaFunction = deltaFunction;
	}

	public static LSrtToLongFuncDelta deltaOf(LSrtToLongFunction function, LLongBinaryOperator deltaFunction) {
		return new LSrtToLongFuncDelta(function, deltaFunction);
	}

	public static LSrtToLongFuncDelta deltaOf(long initialValue, LSrtToLongFunction function, LLongBinaryOperator deltaFunction) {
		return new LSrtToLongFuncDelta(initialValue, function, deltaFunction);
	}

	public static LSrtToLongFuncDelta initializedDeltaOf(short a, LSrtToLongFunction function, LLongBinaryOperator deltaFunction) {
		var delta = deltaOf(function, deltaFunction);
		delta.applyAsLong(a);
		return delta;
	}

	public static LSrtToLongFuncDelta deltaOf(LSrtToLongFunction function) {
		return deltaOf(function, LSrtToLongFuncDelta::mathDelta);
	}

	public static LSrtToLongFuncDelta initializedDeltaOf(short a, LSrtToLongFunction function) {
		var delta = deltaOf(function);
		delta.applyAsLong(a);
		return delta;
	}

	public static LSrtToLongFuncDelta deltaOf(long initialValue, LSrtToLongFunction function) {
		return deltaOf(initialValue, function, LSrtToLongFuncDelta::mathDelta);
	}

	public static long mathDelta(long last, long current) {
		return current - last;
	}

	@Override
	public long applyAsLong(short a) {
		return deltaFunction.applyAsLong(lastValue(), super.applyAsLong(a));
	}

	// <editor-fold desc="object">

	public static boolean argEquals(LSrtToLongFuncDelta the, Object that) {
		return Null.<LSrtToLongFuncDelta> equals(the, that, (one, two) -> {
			if (one.getClass() != two.getClass()) {
				return false;
			}

			LSrtToLongFuncDelta other = (LSrtToLongFuncDelta) two;

			return LBiObjLongTriple.argEquals(one.function, one.deltaFunction, one.lastValue(), other.function, other.deltaFunction, other.lastValue());
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LBiObjLongTriple.argHashCode(function, deltaFunction, lastValue);
	}

	// </editor-fold>

}
