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
public interface AccessCharX<X extends Throwable> {

	static <X extends Throwable> AccessCharX<X> wrap(AccessCharX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	char accessCharX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseCharX(char a) throws X {
		// NOOP
	}

	default void useWith(LCharConsumerX<X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(char a2, LBiCharConsumerX<X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(char a1, LBiCharConsumerX.V1<X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumerX<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumerX.V1<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumerX.V4<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjCharConsumerX<T, X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjCharConsumerX.V1<T, X> accessFunction) throws X {
		char tuple = accessCharX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default char useWith(char a2, LCharBinaryOperatorX<X> accessFunction) throws X {
		char tuple = accessCharX();
		char retval = accessFunction.doApplyAsChar(tuple, a2);
		releaseCharX(tuple);
		return retval;
	}

	default char useWith(LCharUnaryOperatorX<X> accessFunction) throws X {
		char tuple = accessCharX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default byte useWith(LCharToByteFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default double useWith(LCharToDoubleFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default float useWith(LCharToFloatFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default int useWith(LCharToIntFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default long useWith(LCharToLongFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default short useWith(LCharToShortFunctionX<X> accessFunction) throws X {
		char tuple = accessCharX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LBiCharFunctionX<R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseCharX(tuple);
		return retval;
	}

	default <R> R useWith(char a1, LBiCharFunctionX.V1<R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseCharX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunctionX<T1, T2, R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseCharX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseCharX(tuple);
		return retval;
	}

	default <R> R useWith(LCharFunctionX<R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApply(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunctionX<T, R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunctionX.V1<T, R, X> accessFunction) throws X {
		char tuple = accessCharX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseCharX(tuple);
		return retval;
	}

	default boolean useWith(char a2, LBiCharPredicateX<X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseCharX(tuple);
		return retval;
	}

	default boolean useWith(char a1, LBiCharPredicateX.V1<X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseCharX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicateX<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicateX.V1<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseCharX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicateX.V4<T1, T2, X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseCharX(tuple);
		return retval;
	}

	default boolean useWith(LCharPredicateX<X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTest(tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicateX<T, X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseCharX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicateX.V1<T, X> accessFunction) throws X {
		char tuple = accessCharX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseCharX(tuple);
		return retval;
	}

}
