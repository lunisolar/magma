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
public interface AccessInt {

	static AccessInt wrap(AccessInt lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	int accessInt();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseInt(int a) {
		// NOOP
	}

	default void useWith(LIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(tuple);
	}

	default void useWith(int a2, LBiIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a1, LBiIntConsumer.V1 accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.V1<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.V4<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjIntConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjIntConsumer.V1<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default int useWith(int a2, LIntBinaryOperator accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default int useWith(LIntUnaryOperator accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default byte useWith(LIntToByteFunction accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseInt(tuple);
		return retval;
	}

	default char useWith(LIntToCharFunction accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseInt(tuple);
		return retval;
	}

	default double useWith(LIntToDoubleFunction accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseInt(tuple);
		return retval;
	}

	default float useWith(LIntToFloatFunction accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseInt(tuple);
		return retval;
	}

	default long useWith(LIntToLongFunction accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseInt(tuple);
		return retval;
	}

	default short useWith(LIntToShortFunction accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LBiIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a1, LBiIntFunction.V1<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.V1<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.V4<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(LIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFunction.V1<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LObjIntToIntFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LObjIntToIntFunction.V1<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntV1(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBiIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a1, LBiIntPredicate.V1 accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.V1<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.V4<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(LIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate.V1<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

}
