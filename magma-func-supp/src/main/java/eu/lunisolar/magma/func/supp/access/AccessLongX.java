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
public interface AccessLongX<X extends Throwable> {

	static <X extends Throwable> AccessLongX<X> wrap(AccessLongX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	long accessLongX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseLongX(long a) throws X {
		// NOOP
	}

	default void useWith(LLongConsumerX<X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(long a2, LBiLongConsumerX<X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(long a1, LBiLongConsumerX.V1<X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumerX<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumerX.V1<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumerX.V4<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjLongConsumerX<T, X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjLongConsumerX.V1<T, X> accessFunction) throws X {
		long tuple = accessLongX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default long useWith(long a2, LLongBinaryOperatorX<X> accessFunction) throws X {
		long tuple = accessLongX();
		long retval = accessFunction.doApplyAsLong(tuple, a2);
		releaseLongX(tuple);
		return retval;
	}

	default long useWith(LLongUnaryOperatorX<X> accessFunction) throws X {
		long tuple = accessLongX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default byte useWith(LLongToByteFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default char useWith(LLongToCharFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default double useWith(LLongToDoubleFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default float useWith(LLongToFloatFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default int useWith(LLongToIntFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default short useWith(LLongToShortFunctionX<X> accessFunction) throws X {
		long tuple = accessLongX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LBiLongFunctionX<R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseLongX(tuple);
		return retval;
	}

	default <R> R useWith(long a1, LBiLongFunctionX.V1<R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseLongX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunctionX<T1, T2, R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseLongX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseLongX(tuple);
		return retval;
	}

	default <R> R useWith(LLongFunctionX<R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApply(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunctionX<T, R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunctionX.V1<T, R, X> accessFunction) throws X {
		long tuple = accessLongX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseLongX(tuple);
		return retval;
	}

	default boolean useWith(long a2, LBiLongPredicateX<X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseLongX(tuple);
		return retval;
	}

	default boolean useWith(long a1, LBiLongPredicateX.V1<X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseLongX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicateX<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicateX.V1<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseLongX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicateX.V4<T1, T2, X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseLongX(tuple);
		return retval;
	}

	default boolean useWith(LLongPredicateX<X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTest(tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicateX<T, X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseLongX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicateX.V1<T, X> accessFunction) throws X {
		long tuple = accessLongX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseLongX(tuple);
		return retval;
	}

}
