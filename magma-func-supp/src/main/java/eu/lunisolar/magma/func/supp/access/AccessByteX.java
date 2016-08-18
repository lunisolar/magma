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
public interface AccessByteX<X extends Throwable> {

	static <X extends Throwable> AccessByteX<X> wrap(AccessByteX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	byte accessByteX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseByteX(byte a) throws X {
		// NOOP
	}

	default void useWith(LByteConsumerX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(byte a2, LBiByteConsumerX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(byte a1, LBiByteConsumerX.V1<X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumerX<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumerX.V1<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumerX.V4<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjByteConsumerX<T, X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjByteConsumerX.V1<T, X> accessFunction) throws X {
		byte tuple = accessByteX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default byte useWith(byte a2, LByteBinaryOperatorX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		byte retval = accessFunction.doApplyAsByte(tuple, a2);
		releaseByteX(tuple);
		return retval;
	}

	default byte useWith(LByteUnaryOperatorX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default char useWith(LByteToCharFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default double useWith(LByteToDoubleFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default float useWith(LByteToFloatFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default int useWith(LByteToIntFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default long useWith(LByteToLongFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default short useWith(LByteToShortFunctionX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LBiByteFunctionX<R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseByteX(tuple);
		return retval;
	}

	default <R> R useWith(byte a1, LBiByteFunctionX.V1<R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseByteX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunctionX<T1, T2, R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseByteX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseByteX(tuple);
		return retval;
	}

	default <R> R useWith(LByteFunctionX<R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApply(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunctionX<T, R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunctionX.V1<T, R, X> accessFunction) throws X {
		byte tuple = accessByteX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseByteX(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LBiBytePredicateX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseByteX(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LBiBytePredicateX.V1<X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseByteX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicateX<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicateX.V1<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseByteX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicateX.V4<T1, T2, X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseByteX(tuple);
		return retval;
	}

	default boolean useWith(LBytePredicateX<X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTest(tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicateX<T, X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseByteX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicateX.V1<T, X> accessFunction) throws X {
		byte tuple = accessByteX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseByteX(tuple);
		return retval;
	}

}
