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
public interface AccessBool {

	static AccessBool wrap(AccessBool lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	boolean accessBool();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBool(boolean a) {
		// NOOP
	}

	default void useWith(LBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAccept(tuple);
	}

	default void useWith(boolean a2, LBiBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(boolean a1, LBiBoolConsumer.V1 accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer.V1<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer.V4<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjBoolConsumer<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjBoolConsumer.V1<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default void useWith(boolean a2, boolean a3, LTriBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default boolean useWith(boolean a2, LLogicalBinaryOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doApply(tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, boolean a3, LLogicalTernaryOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doApply(tuple, a2, a3);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(LLogicalOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doApply(tuple);
		releaseBool(tuple);
		return retval;
	}

	default byte useWith(LBoolToByteFunction accessFunction) {
		boolean tuple = accessBool();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseBool(tuple);
		return retval;
	}

	default char useWith(LBoolToCharFunction accessFunction) {
		boolean tuple = accessBool();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseBool(tuple);
		return retval;
	}

	default double useWith(LBoolToDoubleFunction accessFunction) {
		boolean tuple = accessBool();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseBool(tuple);
		return retval;
	}

	default float useWith(LBoolToFloatFunction accessFunction) {
		boolean tuple = accessBool();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseBool(tuple);
		return retval;
	}

	default int useWith(LBoolToIntFunction accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseBool(tuple);
		return retval;
	}

	default long useWith(LBoolToLongFunction accessFunction) {
		boolean tuple = accessBool();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseBool(tuple);
		return retval;
	}

	default short useWith(LBoolToShortFunction accessFunction) {
		boolean tuple = accessBool();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LBiBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApply(tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a1, LBiBoolFunction.V1<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction.V1<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction.V4<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(LBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApply(tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunction<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApply(a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunction.V1<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, boolean a3, LTriBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.doApply(tuple, a2, a3);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate.V1<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate.V4<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicate<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicate.V1<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

}
