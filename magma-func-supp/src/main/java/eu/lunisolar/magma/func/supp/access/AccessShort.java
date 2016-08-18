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
public interface AccessShort {

	static AccessShort wrap(AccessShort lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	short accessShort();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseShort(short a) {
		// NOOP
	}

	default void useWith(LShortConsumer accessFunction) {
		short tuple = accessShort();
		accessFunction.doAccept(tuple);
	}

	default void useWith(short a2, LBiShortConsumer accessFunction) {
		short tuple = accessShort();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a1, LBiShortConsumer.V1 accessFunction) {
		short tuple = accessShort();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumer<T1, T2> accessFunction) {
		short tuple = accessShort();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumer.V1<T1, T2> accessFunction) {
		short tuple = accessShort();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjShortConsumer.V4<T1, T2> accessFunction) {
		short tuple = accessShort();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjShortConsumer<T> accessFunction) {
		short tuple = accessShort();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjShortConsumer.V1<T> accessFunction) {
		short tuple = accessShort();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default short useWith(short a2, LShortBinaryOperator accessFunction) {
		short tuple = accessShort();
		short retval = accessFunction.doApplyAsShort(tuple, a2);
		releaseShort(tuple);
		return retval;
	}

	default short useWith(LShortUnaryOperator accessFunction) {
		short tuple = accessShort();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseShort(tuple);
		return retval;
	}

	default byte useWith(LShortToByteFunction accessFunction) {
		short tuple = accessShort();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseShort(tuple);
		return retval;
	}

	default char useWith(LShortToCharFunction accessFunction) {
		short tuple = accessShort();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseShort(tuple);
		return retval;
	}

	default double useWith(LShortToDoubleFunction accessFunction) {
		short tuple = accessShort();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseShort(tuple);
		return retval;
	}

	default float useWith(LShortToFloatFunction accessFunction) {
		short tuple = accessShort();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseShort(tuple);
		return retval;
	}

	default int useWith(LShortToIntFunction accessFunction) {
		short tuple = accessShort();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseShort(tuple);
		return retval;
	}

	default long useWith(LShortToLongFunction accessFunction) {
		short tuple = accessShort();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseShort(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunction<T1, T2, R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseShort(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunction.V1<T1, T2, R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseShort(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjShortFunction.V4<T1, T2, R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseShort(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LBiShortFunction<R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApply(tuple, a2);
		releaseShort(tuple);
		return retval;
	}

	default <R> R useWith(short a1, LBiShortFunction.V1<R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseShort(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjShortFunction<T, R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApply(a1, tuple);
		releaseShort(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjShortFunction.V1<T, R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseShort(tuple);
		return retval;
	}

	default <R> R useWith(LShortFunction<R> accessFunction) {
		short tuple = accessShort();
		R retval = accessFunction.doApply(tuple);
		releaseShort(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicate<T1, T2> accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseShort(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicate.V1<T1, T2> accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseShort(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjShortPredicate.V4<T1, T2> accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseShort(tuple);
		return retval;
	}

	default boolean useWith(short a2, LBiShortPredicate accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseShort(tuple);
		return retval;
	}

	default boolean useWith(short a1, LBiShortPredicate.V1 accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseShort(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjShortPredicate<T> accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseShort(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjShortPredicate.V1<T> accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseShort(tuple);
		return retval;
	}

	default boolean useWith(LShortPredicate accessFunction) {
		short tuple = accessShort();
		boolean retval = accessFunction.doTest(tuple);
		releaseShort(tuple);
		return retval;
	}

}
