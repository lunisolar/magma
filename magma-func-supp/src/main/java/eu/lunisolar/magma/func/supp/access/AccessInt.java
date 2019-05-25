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
		accessFunction.accept(tuple);
	}

	default void useWith(int a2, LBiIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a1, LBiIntConsumer.LInt1Int0Cons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt1Int0(tuple, a1);
	}

	default void useWith(boolean a1, LBoolIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(boolean a1, LBoolIntConsumer.LIntBoolCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntBool(tuple, a1);
	}

	default void useWith(byte a1, LByteIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(byte a1, LByteIntConsumer.LIntByteCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntByte(tuple, a1);
	}

	default void useWith(char a1, LCharIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(char a1, LCharIntConsumer.LIntCharCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntChar(tuple, a1);
	}

	default void useWith(double a1, LDblIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(double a1, LDblIntConsumer.LIntDblCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntDbl(tuple, a1);
	}

	default void useWith(float a1, LFltIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(float a1, LFltIntConsumer.LIntFltCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntFlt(tuple, a1);
	}

	default void useWith(long a1, LLongIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(long a1, LLongIntConsumer.LIntLongCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntLong(tuple, a1);
	}

	default void useWith(short a1, LSrtIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default void useWith(short a1, LSrtIntConsumer.LIntSrtCons accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntSrt(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.LObj0Int2Obj1Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObj0Int2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjIntConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjIntConsumer.LIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObj(tuple, a1);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjBoolInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjBool(tuple, a1, a3);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntBoolObj(tuple, a3, a1);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptBoolObjInt(a3, a1, tuple);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptBoolIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjByteInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjByte(tuple, a1, a3);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntByteObj(tuple, a3, a1);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptByteObjInt(a3, a1, tuple);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptByteIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjCharInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjChar(tuple, a1, a3);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntCharObj(tuple, a3, a1);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptCharObjInt(a3, a1, tuple);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptCharIntObj(a3, tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer.LObj0Obj2Int1Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObj0Obj2Int1(a1, a3, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer.LInt1BiObj2Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt1BiObj2(tuple, a1, a3);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjDblInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjDbl(tuple, a1, a3);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntDblObj(tuple, a3, a1);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptDblObjInt(a3, a1, tuple);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptDblIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjFltInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjFlt(tuple, a1, a3);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntFltObj(tuple, a3, a1);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptFltObjInt(a3, a1, tuple);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptFltIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, int a3, LTieIntConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, int a2, LTieIntConsumer.LObj0Int2Int1Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObj0Int2Int1(a1, tuple, a2);
	}

	default <T> void useWith(T a1, int a3, LTieIntConsumer.LInt1Obj0Int2Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt1Obj0Int2(tuple, a1, a3);
	}

	default <T> void useWith(int a3, T a1, LTieIntConsumer.LInt1Int2Obj0Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt1Int2Obj0(tuple, a3, a1);
	}

	default <T> void useWith(T a1, int a2, LTieIntConsumer.LInt2Obj0Int1Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptInt2Obj0Int1(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieIntConsumer.LBiInt1Obj0Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptBiInt1Obj0(tuple, a2, a1);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjLongInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjLong(tuple, a1, a3);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntLongObj(tuple, a3, a1);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptLongObjInt(a3, a1, tuple);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptLongIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.accept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptObjSrtInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntObjSrt(tuple, a1, a3);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptIntSrtObj(tuple, a3, a1);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptSrtObjInt(a3, a1, tuple);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.acceptSrtIntObj(a3, tuple, a1);
	}

	default int useWith(int a2, LIntBinaryOperator accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default int useWith(LIntUnaryOperator accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default byte useWith(LIntToByteFunction accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseInt(tuple);
		return retval;
	}

	default char useWith(LIntToCharFunction accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.applyAsChar(tuple);
		releaseInt(tuple);
		return retval;
	}

	default double useWith(LIntToDblFunction accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseInt(tuple);
		return retval;
	}

	default float useWith(LIntToFltFunction accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default long useWith(LIntToLongFunction accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.applyAsLong(tuple);
		releaseInt(tuple);
		return retval;
	}

	default short useWith(LIntToSrtFunction accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LBiIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a1, LBiIntFunction.LInt1Int0Func<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt1Int0(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.LObj0Int2Obj1Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObj0Int2Obj1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt2Obj0Obj1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(LIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a3, LObjBiIntFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjBiIntFunction.LObj0Int2Int1Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObj0Int2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a3, LObjBiIntFunction.LInt1Obj0Int2Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt1Obj0Int2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a3, T a1, LObjBiIntFunction.LInt1Int2Obj0Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt1Int2Obj0(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjBiIntFunction.LInt2Obj0Int1Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt2Obj0Int1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjBiIntFunction.LBiInt1Obj0Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyBiInt1Obj0(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction.LObj0Obj2Int1Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObj0Obj2Int1(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyInt1BiObj2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applySrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applySrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LOiFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.apply(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LOiFunction.LIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.applyIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> byte useWith(T a1, LOiToByteFunction<T> accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.applyAsByte(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> byte useWith(T a1, LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.applyAsByteIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> char useWith(T a1, LOiToCharFunction<T> accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.applyAsChar(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> char useWith(T a1, LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.applyAsCharIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> double useWith(T a1, LOiToDblFunction<T> accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.applyAsDbl(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> double useWith(T a1, LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.applyAsDblIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> float useWith(T a1, LOiToFltFunction<T> accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.applyAsFlt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> float useWith(T a1, LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.applyAsFltIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LOiToIntFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> long useWith(T a1, LOiToLongFunction<T> accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.applyAsLong(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> long useWith(T a1, LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.applyAsLongIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> short useWith(T a1, LOiToSrtFunction<T> accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.applyAsSrt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> short useWith(T a1, LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.applyAsSrtIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObj0Obj2Int1(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntInt1BiObj2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a3, LTieIntFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieIntFunction.LObj0Int2Int1ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObj0Int2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a3, LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntInt1Obj0Int2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(int a3, T a1, LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntInt1Int2Obj0(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntInt2Obj0Int1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieIntFunction.LBiInt1Obj0ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntBiInt1Obj0(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntSrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.applyAsIntSrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBiIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a1, LBiIntPredicate.LInt1Int0Pred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt1Int0(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.LObj0Int2Obj1Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObj0Int2Obj1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt2Obj0Obj1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(boolean a1, LBoolIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(boolean a1, LBoolIntPredicate.LIntBoolPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntBool(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LByteIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LByteIntPredicate.LIntBytePred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntByte(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(char a1, LCharIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(char a1, LCharIntPredicate.LIntCharPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntChar(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(double a1, LDblIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(double a1, LDblIntPredicate.LIntDblPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntDbl(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LFltIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LFltIntPredicate.LIntFltPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntFlt(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(LIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(long a1, LLongIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(long a1, LLongIntPredicate.LIntLongPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntLong(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a3, LObjBiIntPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjBiIntPredicate.LObj0Int2Int1Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObj0Int2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a3, LObjBiIntPredicate.LInt1Obj0Int2Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt1Obj0Int2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a3, T a1, LObjBiIntPredicate.LInt1Int2Obj0Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt1Int2Obj0(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjBiIntPredicate.LInt2Obj0Int1Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt2Obj0Int1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjBiIntPredicate.LBiInt1Obj0Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testBiInt1Obj0(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate.LObj0Obj2Int1Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObj0Obj2Int1(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testInt1BiObj2(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate.LIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testSrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testSrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LSrtIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LSrtIntPredicate.LIntSrtPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.testIntSrt(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

}
