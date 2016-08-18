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
public interface AccessFloat {

	static AccessFloat wrap(AccessFloat lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	float accessFloat();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseFloat(float a) {
		// NOOP
	}

	default void useWith(LFloatConsumer accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAccept(tuple);
	}

	default void useWith(float a2, LBiFloatConsumer accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a1, LBiFloatConsumer.V1 accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumer<T1, T2> accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumer.V1<T1, T2> accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFloatConsumer.V4<T1, T2> accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjFloatConsumer<T> accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjFloatConsumer.V1<T> accessFunction) {
		float tuple = accessFloat();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default float useWith(float a2, LFloatBinaryOperator accessFunction) {
		float tuple = accessFloat();
		float retval = accessFunction.doApplyAsFloat(tuple, a2);
		releaseFloat(tuple);
		return retval;
	}

	default float useWith(LFloatUnaryOperator accessFunction) {
		float tuple = accessFloat();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default byte useWith(LFloatToByteFunction accessFunction) {
		float tuple = accessFloat();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default char useWith(LFloatToCharFunction accessFunction) {
		float tuple = accessFloat();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default double useWith(LFloatToDoubleFunction accessFunction) {
		float tuple = accessFloat();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default int useWith(LFloatToIntFunction accessFunction) {
		float tuple = accessFloat();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default long useWith(LFloatToLongFunction accessFunction) {
		float tuple = accessFloat();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default short useWith(LFloatToShortFunction accessFunction) {
		float tuple = accessFloat();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LBiFloatFunction<R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApply(tuple, a2);
		releaseFloat(tuple);
		return retval;
	}

	default <R> R useWith(float a1, LBiFloatFunction.V1<R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseFloat(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunction<T1, T2, R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunction.V1<T1, T2, R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseFloat(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFloatFunction.V4<T1, T2, R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseFloat(tuple);
		return retval;
	}

	default <R> R useWith(LFloatFunction<R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApply(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFloatFunction<T, R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApply(a1, tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFloatFunction.V1<T, R> accessFunction) {
		float tuple = accessFloat();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseFloat(tuple);
		return retval;
	}

	default boolean useWith(float a2, LBiFloatPredicate accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseFloat(tuple);
		return retval;
	}

	default boolean useWith(float a1, LBiFloatPredicate.V1 accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseFloat(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicate<T1, T2> accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicate.V1<T1, T2> accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseFloat(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFloatPredicate.V4<T1, T2> accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseFloat(tuple);
		return retval;
	}

	default boolean useWith(LFloatPredicate accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTest(tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFloatPredicate<T> accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseFloat(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFloatPredicate.V1<T> accessFunction) {
		float tuple = accessFloat();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseFloat(tuple);
		return retval;
	}

}
