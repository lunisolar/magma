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
public interface AccessFloatX<X extends Throwable> {

	static <X extends Throwable> AccessFloatX<X> wrap(AccessFloatX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	float accessFloatX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseFloatX(float a) throws X {
		// NOOP
	}

	default void useWith(LFloatConsumerX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(float a2, LBiFloatConsumerX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a1, LBiFloatConsumerX.V1<X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumerX<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumerX.V1<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumerX.V4<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjFloatConsumerX<T, X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjFloatConsumerX.V1<T, X> accessFunction) throws X {
		float tuple = accessFloatX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default float useWith(float a2, LFloatBinaryOperatorX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		float retval = accessFunction.doApplyAsFloat(tuple, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default float useWith(LFloatUnaryOperatorX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default byte useWith(LFloatToByteFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default char useWith(LFloatToCharFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default double useWith(LFloatToDoubleFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default int useWith(LFloatToIntFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default long useWith(LFloatToLongFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default short useWith(LFloatToShortFunctionX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LBiFloatFunctionX<R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default <R> R useWith(float a1, LBiFloatFunctionX.V1<R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseFloatX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunctionX<T1, T2, R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default <R> R useWith(LFloatFunctionX<R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApply(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFloatFunctionX<T, R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFloatFunctionX.V1<T, R, X> accessFunction) throws X {
		float tuple = accessFloatX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseFloatX(tuple);
		return retval;
	}

	default boolean useWith(float a2, LBiFloatPredicateX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default boolean useWith(float a1, LBiFloatPredicateX.V1<X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseFloatX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicateX<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicateX.V1<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicateX.V4<T1, T2, X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseFloatX(tuple);
		return retval;
	}

	default boolean useWith(LFloatPredicateX<X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTest(tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFloatPredicateX<T, X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseFloatX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFloatPredicateX.V1<T, X> accessFunction) throws X {
		float tuple = accessFloatX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseFloatX(tuple);
		return retval;
	}

}
