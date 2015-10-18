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
public class LIntToFloatFuncDelta extends LIntToFloatFuncMemento {

	private final LFloatBinaryOperator deltaFunction;

	protected LIntToFloatFuncDelta(LIntToFloatFunction function, LFloatBinaryOperator deltaFunction) {
		super(function);
		this.deltaFunction = deltaFunction;
	}

	protected LIntToFloatFuncDelta(float initialValue, LIntToFloatFunction function, LFloatBinaryOperator deltaFunction) {
		super(initialValue, function);
		this.deltaFunction = deltaFunction;
	}

	public static LIntToFloatFuncDelta deltaOf(LIntToFloatFunction function, LFloatBinaryOperator deltaFunction) {
		return new LIntToFloatFuncDelta(function, deltaFunction);
	}

	public static LIntToFloatFuncDelta deltaOf(float initialValue, LIntToFloatFunction function, LFloatBinaryOperator deltaFunction) {
		return new LIntToFloatFuncDelta(initialValue, function, deltaFunction);
	}

	public static LIntToFloatFuncDelta deltaOf(LIntToFloatFunction function) {
		return deltaOf(function, LIntToFloatFuncDelta::mathDelta);
	}

	public static LIntToFloatFuncDelta deltaOf(float initialValue, LIntToFloatFunction function) {
		return deltaOf(initialValue, function, LIntToFloatFuncDelta::mathDelta);
	}

	public static float mathDelta(float last, float current) {
		return current - last;
	}

	public float doApplyAsFloat(int a1) {
		return deltaFunction.doApplyAsFloat(lastValue(), super.doApplyAsFloat(a1));
	}

}
