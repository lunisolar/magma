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
public interface AccessShortX<X extends Throwable> {

	static <X extends Throwable> AccessShortX<X> wrap(AccessShortX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	short accessShortX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseShortX(short a) throws X {
		// NOOP
	}

	default void useWith(LShortConsumerX<X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(short a2, LBiShortConsumerX<X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a1, LBiShortConsumerX.V1<X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumerX<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumerX.V1<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumerX.V4<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjShortConsumerX<T, X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjShortConsumerX.V1<T, X> accessFunction) throws X {
		short tuple = accessShortX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default short useWith(short a2, LShortBinaryOperatorX<X> accessFunction) throws X {
		short tuple = accessShortX();
		short retval = accessFunction.doApplyAsShort(tuple, a2);
		releaseShortX(tuple);
		return retval;
	}

	default short useWith(LShortUnaryOperatorX<X> accessFunction) throws X {
		short tuple = accessShortX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default byte useWith(LShortToByteFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default char useWith(LShortToCharFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default double useWith(LShortToDoubleFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default float useWith(LShortToFloatFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default int useWith(LShortToIntFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default long useWith(LShortToLongFunctionX<X> accessFunction) throws X {
		short tuple = accessShortX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunctionX<T1, T2, R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseShortX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseShortX(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LBiShortFunctionX<R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseShortX(tuple);
		return retval;
	}

	default <R> R useWith(short a1, LBiShortFunctionX.V1<R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseShortX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjShortFunctionX<T, R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjShortFunctionX.V1<T, R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseShortX(tuple);
		return retval;
	}

	default <R> R useWith(LShortFunctionX<R, X> accessFunction) throws X {
		short tuple = accessShortX();
		R retval = accessFunction.doApply(tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicateX<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicateX.V1<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseShortX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicateX.V4<T1, T2, X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseShortX(tuple);
		return retval;
	}

	default boolean useWith(short a2, LBiShortPredicateX<X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseShortX(tuple);
		return retval;
	}

	default boolean useWith(short a1, LBiShortPredicateX.V1<X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseShortX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjShortPredicateX<T, X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseShortX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjShortPredicateX.V1<T, X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseShortX(tuple);
		return retval;
	}

	default boolean useWith(LShortPredicateX<X> accessFunction) throws X {
		short tuple = accessShortX();
		boolean retval = accessFunction.doTest(tuple);
		releaseShortX(tuple);
		return retval;
	}

}
