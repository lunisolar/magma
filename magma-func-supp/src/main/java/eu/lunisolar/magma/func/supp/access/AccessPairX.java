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
public interface AccessPairX<T1, T2, X extends Throwable> {

	static <T1, T2, X extends Throwable> AccessPairX<T1, T2, X> wrap(AccessPairX<T1, T2, X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LPair<T1, T2> accessPairX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releasePairX(LPair<T1, T2> a) throws X {
		// NOOP
	}

	default void useWith(LBiConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LBiConsumerX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.second(), tuple.first());
	}

	default <T3> void useWith(T3 a3, LTriConsumerX<T1, T2, T3, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default <T3> void useWith(T3 a3, LTriConsumerX.V2<T2, T1, T3, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LBiObjDoubleConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LBiObjDoubleConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LBiObjDoubleConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LBiObjDoubleConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LBiObjDoubleConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LBiObjDoubleConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LBiObjFloatConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LBiObjFloatConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LBiObjFloatConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LBiObjFloatConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LBiObjFloatConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LBiObjFloatConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LBiObjShortConsumerX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LBiObjShortConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LBiObjShortConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LBiObjShortConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LBiObjShortConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LBiObjShortConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(LBiFunctionX.V1<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunctionX<T1, T2, T3, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunctionX.V2<T2, T1, T3, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		byte retval = accessFunction.doApplyAsByte(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		byte retval = accessFunction.doApplyAsByteV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		char retval = accessFunction.doApplyAsChar(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		char retval = accessFunction.doApplyAsCharV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default double useWith(LToDoubleBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		double retval = accessFunction.doApplyAsDouble(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default double useWith(LToDoubleBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		double retval = accessFunction.doApplyAsDoubleV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default float useWith(LToFloatBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		float retval = accessFunction.doApplyAsFloat(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default float useWith(LToFloatBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		float retval = accessFunction.doApplyAsFloatV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		int retval = accessFunction.doApplyAsIntV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		long retval = accessFunction.doApplyAsLong(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		long retval = accessFunction.doApplyAsLongV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default short useWith(LToShortBiFunctionX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		short retval = accessFunction.doApplyAsShort(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default short useWith(LToShortBiFunctionX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		short retval = accessFunction.doApplyAsShortV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicateX<T1, T2, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releasePairX(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicateX.V1<T2, T1, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV1(tuple.second(), tuple.first());
		releasePairX(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicateX<T1, T2, T3, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePairX(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicateX.V2<T2, T1, T3, X> accessFunction) throws X {
		LPair<T1, T2> tuple = accessPairX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePairX(tuple);
		return retval;
	}

}
