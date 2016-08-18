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
public interface Access<T> {

	static <T> Access<T> wrap(Access<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	T access();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void release(T a) {
		// NOOP
	}

	default void useWith(LConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple);
	}

	default void useWith(boolean a2, LObjBoolConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(boolean a2, LObjBoolConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(byte a2, LObjByteConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(byte a2, LObjByteConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(char a2, LObjCharConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(char a2, LObjCharConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(double a2, LObjDoubleConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(double a2, LObjDoubleConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(float a2, LObjFloatConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a2, LObjFloatConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(int a2, LObjIntConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LObjIntConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(long a2, LObjLongConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(long a2, LObjLongConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default void useWith(short a2, LObjShortConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a2, LObjShortConsumer.V1<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptV1(a2, tuple);
	}

	default T useWith(T a2, LBinaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default T useWith(T a2, T a3, LTernaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default T useWith(LUnaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(LFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDoubleFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDoubleFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFloatFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFloatFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjShortFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjShortFunction.V1<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LObjIntToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LObjIntToIntFunction.V1<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default byte useWith(LToByteFunction<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.doApplyAsByte(tuple);
		release(tuple);
		return retval;
	}

	default char useWith(LToCharFunction<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.doApplyAsChar(tuple);
		release(tuple);
		return retval;
	}

	default double useWith(LToDoubleFunction<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.doApplyAsDouble(tuple);
		release(tuple);
		return retval;
	}

	default float useWith(LToFloatFunction<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.doApplyAsFloat(tuple);
		release(tuple);
		return retval;
	}

	default int useWith(LToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple);
		release(tuple);
		return retval;
	}

	default long useWith(LToLongFunction<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.doApplyAsLong(tuple);
		release(tuple);
		return retval;
	}

	default short useWith(LToShortFunction<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.doApplyAsShort(tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDoublePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDoublePredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFloatPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFloatPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjShortPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjShortPredicate.V1<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestV1(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(LPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple);
		release(tuple);
		return retval;
	}

}
