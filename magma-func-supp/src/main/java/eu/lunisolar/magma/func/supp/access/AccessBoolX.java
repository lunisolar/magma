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
public interface AccessBoolX<X extends Throwable> {

	static <X extends Throwable> AccessBoolX<X> wrap(AccessBoolX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	boolean accessBoolX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBoolX(boolean a) throws X {
		// NOOP
	}

	default void useWith(LBoolConsumerX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAccept(tuple);
	}

	default void useWith(boolean a2, LBiBoolConsumerX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(boolean a1, LBiBoolConsumerX.V1<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumerX<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumerX.V1<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumerX.V4<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjBoolConsumerX<T, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjBoolConsumerX.V1<T, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default void useWith(boolean a2, boolean a3, LTriBoolConsumerX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default boolean useWith(boolean a2, LLogicalBinaryOperatorX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doApply(tuple, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, boolean a3, LLogicalTernaryOperatorX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doApply(tuple, a2, a3);
		releaseBoolX(tuple);
		return retval;
	}

	default boolean useWith(LLogicalOperatorX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doApply(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default byte useWith(LBoolToByteFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default char useWith(LBoolToCharFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default double useWith(LBoolToDoubleFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default float useWith(LBoolToFloatFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default int useWith(LBoolToIntFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default long useWith(LBoolToLongFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default short useWith(LBoolToShortFunctionX<X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LBiBoolFunctionX<R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApply(tuple, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a1, LBiBoolFunctionX.V1<R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseBoolX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunctionX<T1, T2, R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default <R> R useWith(LBoolFunctionX<R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApply(tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunctionX<T, R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApply(a1, tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunctionX.V1<T, R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseBoolX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, boolean a3, LTriBoolFunctionX<R, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		R retval = accessFunction.doApply(tuple, a2, a3);
		releaseBoolX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicateX<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicateX.V1<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicateX.V4<T1, T2, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseBoolX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicateX<T, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseBoolX(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicateX.V1<T, X> accessFunction) throws X {
		boolean tuple = accessBoolX();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseBoolX(tuple);
		return retval;
	}

}
