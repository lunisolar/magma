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

package eu.lunisolar.magma.func.supp.access;

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
 * Interface representing a value(s) that can be optionally combined with additional arguments and function call that might produce some other value.
 * Interface implementation is not necessarily holding nor owning the value(s).
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccessX<T, X extends Throwable> {

	static <T, X extends Throwable> AccessX<T, X> wrap(AccessX<T, X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	T accessX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseX(T a) throws X {
		// NOOP
	}

	default void useWith(LConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(boolean a2, LObjBoolConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(boolean a2, LObjBoolConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(byte a2, LObjByteConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(byte a2, LObjByteConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(char a2, LObjCharConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(char a2, LObjCharConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(double a2, LObjDoubleConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(double a2, LObjDoubleConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(float a2, LObjFloatConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a2, LObjFloatConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(int a2, LObjIntConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LObjIntConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(long a2, LObjLongConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(long a2, LObjLongConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(short a2, LObjShortConsumerX<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a2, LObjShortConsumerX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default T useWith(T a2, LBinaryOperatorX<T, X> accessFunction) throws X {
		T tuple = accessX();
		T retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default T useWith(T a2, T a3, LTernaryOperatorX<T, X> accessFunction) throws X {
		T tuple = accessX();
		T retval = accessFunction.doApply(tuple, a2, a3);
		releaseX(tuple);
		return retval;
	}

	default T useWith(LUnaryOperatorX<T, X> accessFunction) throws X {
		T tuple = accessX();
		T retval = accessFunction.doApply(tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(LFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDoubleFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDoubleFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFloatFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFloatFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjShortFunctionX<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjShortFunctionX.V1<T, R, X> accessFunction) throws X {
		T tuple = accessX();
		R retval = accessFunction.doApplyV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default int useWith(int a2, LObjIntToIntFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		int retval = accessFunction.doApplyAsInt(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default int useWith(int a2, LObjIntToIntFunctionX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		int retval = accessFunction.doApplyAsIntV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default byte useWith(LToByteFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseX(tuple);
		return retval;
	}

	default char useWith(LToCharFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseX(tuple);
		return retval;
	}

	default double useWith(LToDoubleFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseX(tuple);
		return retval;
	}

	default float useWith(LToFloatFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseX(tuple);
		return retval;
	}

	default int useWith(LToIntFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseX(tuple);
		return retval;
	}

	default long useWith(LToLongFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseX(tuple);
		return retval;
	}

	default short useWith(LToShortFunctionX<T, X> accessFunction) throws X {
		T tuple = accessX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDoublePredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDoublePredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFloatPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFloatPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjShortPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjShortPredicateX.V1<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		releaseX(tuple);
		return retval;
	}

	default boolean useWith(LPredicateX<T, X> accessFunction) throws X {
		T tuple = accessX();
		boolean retval = accessFunction.doTest(tuple);
		releaseX(tuple);
		return retval;
	}

}
