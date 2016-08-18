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
public interface AccessPair<T1, T2> {

	static <T1, T2> AccessPair<T1, T2> wrap(AccessPair<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LPair<T1, T2> accessPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releasePair(LPair<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LBiConsumer.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.second(), tuple.first());
	}

	default <T3> void useWith(T3 a3, LTriConsumer<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default <T3> void useWith(T3 a3, LTriConsumer.V2<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LBiObjDoubleConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LBiObjDoubleConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LBiObjDoubleConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LBiObjDoubleConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LBiObjDoubleConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LBiObjDoubleConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LBiObjFloatConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LBiObjFloatConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LBiObjFloatConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LBiObjFloatConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LBiObjFloatConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LBiObjFloatConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LBiObjShortConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LBiObjShortConsumer.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV1(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LBiObjShortConsumer.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LBiObjShortConsumer.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV3(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LBiObjShortConsumer.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV4(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LBiObjShortConsumer.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptV5(a3, tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(LBiFunction.V1<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction<T1, T2, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction.V2<T2, T1, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDoubleFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFloatFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction.V1<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction.V2<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction.V3<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction.V4<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjShortFunction.V5<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.doApplyAsByte(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.doApplyAsByteV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.doApplyAsChar(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.doApplyAsCharV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDoubleBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.doApplyAsDouble(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDoubleBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.doApplyAsDoubleV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFloatBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.doApplyAsFloat(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFloatBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.doApplyAsFloatV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.doApplyAsLong(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.doApplyAsLongV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToShortBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.doApplyAsShort(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToShortBiFunction.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.doApplyAsShortV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDoublePredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFloatPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate.V1<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate.V2<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate.V3<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV3(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate.V4<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV4(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjShortPredicate.V5<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV5(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate.V1<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV1(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate.V2<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

}
