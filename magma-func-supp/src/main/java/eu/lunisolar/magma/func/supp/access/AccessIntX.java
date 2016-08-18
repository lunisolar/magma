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
public interface AccessIntX<X extends Throwable> {

	static <X extends Throwable> AccessIntX<X> wrap(AccessIntX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	int accessIntX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseIntX(int a) throws X {
		// NOOP
	}

	default void useWith(LIntConsumerX<X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(int a2, LBiIntConsumerX<X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a1, LBiIntConsumerX.V1<X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumerX<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumerX.V1<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumerX.V4<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjIntConsumerX<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjIntConsumerX.V1<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default int useWith(int a2, LIntBinaryOperatorX<X> accessFunction) throws X {
		int tuple = accessIntX();
		int retval = accessFunction.doApplyAsInt(tuple, a2);
		releaseIntX(tuple);
		return retval;
	}

	default int useWith(LIntUnaryOperatorX<X> accessFunction) throws X {
		int tuple = accessIntX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default byte useWith(LIntToByteFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default char useWith(LIntToCharFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default double useWith(LIntToDoubleFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default float useWith(LIntToFloatFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default long useWith(LIntToLongFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default short useWith(LIntToShortFunctionX<X> accessFunction) throws X {
		int tuple = accessIntX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LBiIntFunctionX<R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseIntX(tuple);
		return retval;
	}

	default <R> R useWith(int a1, LBiIntFunctionX.V1<R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseIntX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunctionX<T1, T2, R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseIntX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseIntX(tuple);
		return retval;
	}

	default <R> R useWith(LIntFunctionX<R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApply(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFunctionX<T, R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFunctionX.V1<T, R, X> accessFunction) throws X {
		int tuple = accessIntX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseIntX(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LObjIntToIntFunctionX<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		int retval = accessFunction.doApplyAsInt(a1, tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LObjIntToIntFunctionX.V1<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		int retval = accessFunction.doApplyAsIntV1(tuple, a1);
		releaseIntX(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBiIntPredicateX<X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseIntX(tuple);
		return retval;
	}

	default boolean useWith(int a1, LBiIntPredicateX.V1<X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseIntX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicateX<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicateX.V1<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseIntX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicateX.V4<T1, T2, X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseIntX(tuple);
		return retval;
	}

	default boolean useWith(LIntPredicateX<X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTest(tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicateX<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseIntX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicateX.V1<T, X> accessFunction) throws X {
		int tuple = accessIntX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseIntX(tuple);
		return retval;
	}

}
