/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

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
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LBiConsumer.LObj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0(tuple.second(), tuple.first());
	}

	default <T3> void useWith(T3 a3, LTriConsumer<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default <T3> void useWith(T3 a3, LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1BiObj2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Bool2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Bool2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Bool2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptBool2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptBool2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Byte2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Byte2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Byte2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptByte2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptByte2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Char2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Char2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Char2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptChar2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptChar2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LBiObjDblConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Dbl2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Dbl2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Dbl2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptDbl2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptDbl2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LBiObjFltConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Flt2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Flt2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Flt2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptFlt2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptFlt2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.LObj0Int2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Int2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Int2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.LObj1Int2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Int2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptInt2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptInt2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Long2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Long2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Long2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptLong2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptLong2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LBiObjSrtConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Srt2Obj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Obj0Srt2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj1Srt2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptSrt2Obj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptSrt2Obj1Obj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieConsumer.LObj0Obj2Int1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj0Obj2Int1(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieConsumer.LInt1BiObj2Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptInt1BiObj2(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptInt1Obj2Obj0(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieConsumer.LObj2Obj0Int1Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj2Obj0Int1(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieConsumer.LObj2Int1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.acceptObj2Int1Obj0(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LBiFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(LBiFunction.LObj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction<T1, T2, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1BiObj2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Bool2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Bool2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Bool2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyBool2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyBool2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Byte2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Byte2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Byte2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyByte2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyByte2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Char2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Char2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Char2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyChar2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyChar2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Dbl2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Dbl2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Dbl2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyDbl2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyDbl2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Flt2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Flt2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Flt2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyFlt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyFlt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObj0Int2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Int2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Int2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObj1Int2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Int2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyInt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyInt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Long2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Long2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Long2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyLong2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyLong2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Srt2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Obj0Srt2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj1Srt2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applySrt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applySrt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObj0Obj2Int1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj0Obj2Int1(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyInt1BiObj2(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyInt1Obj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObj2Obj0Int1Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj2Obj0Int1(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.applyObj2Int1Obj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntObj0Obj2Int1(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntInt1BiObj2(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntInt1Obj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntObj2Obj0Int1(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntObj2Int1Obj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.applyAsByte(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.applyAsByteObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.applyAsChar(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.applyAsCharObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDblBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.applyAsDbl(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.applyAsDblObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFltBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.applyAsFlt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.applyAsFltObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <T3> int useWith(T3 a3, LToIntTriFunction<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <T3> int useWith(T3 a3, LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.applyAsIntObj1BiObj2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.applyAsLong(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.applyAsLongObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToSrtBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.applyAsSrt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.applyAsSrtObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Bool2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Bool2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Bool2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testBool2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testBool2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Byte2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Byte2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Byte2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testByte2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testByte2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Char2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Char2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Char2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testChar2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testChar2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Dbl2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Dbl2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Dbl2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testDbl2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testDbl2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Flt2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Flt2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Flt2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testFlt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testFlt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObj0Int2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Int2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Int2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObj1Int2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Int2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testInt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testInt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Long2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Long2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Long2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testLong2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testLong2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Srt2Obj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0Srt2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Srt2Obj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testSrt2Obj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testSrt2Obj1Obj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate.LObj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObj0Obj2Int1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj0Obj2Int1(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testInt1BiObj2(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testInt1Obj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObj2Obj0Int1Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj2Obj0Int1(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj2Int1Obj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.testObj1BiObj2(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

}
