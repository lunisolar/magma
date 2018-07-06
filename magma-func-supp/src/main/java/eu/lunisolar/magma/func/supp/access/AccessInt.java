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
		accessFunction.doAccept(tuple);
	}

	default void useWith(int a2, LBiIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a1, LBiIntConsumer.LInt1Int0Cons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptInt1Int0(tuple, a1);
	}

	default void useWith(boolean a1, LBoolIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(boolean a1, LBoolIntConsumer.LIntBoolCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntBool(tuple, a1);
	}

	default void useWith(byte a1, LByteIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(byte a1, LByteIntConsumer.LIntByteCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntByte(tuple, a1);
	}

	default void useWith(char a1, LCharIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(char a1, LCharIntConsumer.LIntCharCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntChar(tuple, a1);
	}

	default void useWith(double a1, LDblIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(double a1, LDblIntConsumer.LIntDblCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntDbl(tuple, a1);
	}

	default void useWith(float a1, LFltIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(float a1, LFltIntConsumer.LIntFltCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntFlt(tuple, a1);
	}

	default void useWith(long a1, LLongIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(long a1, LLongIntConsumer.LIntLongCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntLong(tuple, a1);
	}

	default void useWith(short a1, LSrtIntConsumer accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default void useWith(short a1, LSrtIntConsumer.LIntSrtCons accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntSrt(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.LObjIntObj1Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjIntObj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjIntConsumer.LIntObj0Obj1Cons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjIntConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjIntConsumer.LIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObj(tuple, a1);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjBoolInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjBool(tuple, a1, a3);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntBoolObj(tuple, a3, a1);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptBoolObjInt(a3, a1, tuple);
	}

	default <T> void useWith(boolean a3, T a1, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptBoolIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjByteInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjByte(tuple, a1, a3);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntByteObj(tuple, a3, a1);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptByteObjInt(a3, a1, tuple);
	}

	default <T> void useWith(byte a3, T a1, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptByteIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjCharInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjChar(tuple, a1, a3);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntCharObj(tuple, a3, a1);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptCharObjInt(a3, a1, tuple);
	}

	default <T> void useWith(char a3, T a1, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptCharIntObj(a3, tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer.LObjObj2IntCons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjObj2Int(a1, a3, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a3, LTieConsumer.LIntBiObjCons<T1, T2> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntBiObj(tuple, a1, a3);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjDblInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjDbl(tuple, a1, a3);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntDblObj(tuple, a3, a1);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptDblObjInt(a3, a1, tuple);
	}

	default <T> void useWith(double a3, T a1, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptDblIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjFltInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjFlt(tuple, a1, a3);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntFltObj(tuple, a3, a1);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptFltObjInt(a3, a1, tuple);
	}

	default <T> void useWith(float a3, T a1, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptFltIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, int a3, LTieIntConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, int a2, LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjInt2Int1(a1, tuple, a2);
	}

	default <T> void useWith(T a1, int a3, LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptInt1ObjInt(tuple, a1, a3);
	}

	default <T> void useWith(int a3, T a1, LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptInt1Int2Obj(tuple, a3, a1);
	}

	default <T> void useWith(T a1, int a2, LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptInt2ObjInt1(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptBiIntObj(tuple, a2, a1);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjLongInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjLong(tuple, a1, a3);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntLongObj(tuple, a3, a1);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptLongObjInt(a3, a1, tuple);
	}

	default <T> void useWith(long a3, T a1, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptLongIntObj(a3, tuple, a1);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAccept(a1, tuple, a3);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptObjSrtInt(a1, a3, tuple);
	}

	default <T> void useWith(T a1, short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntObjSrt(tuple, a1, a3);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptIntSrtObj(tuple, a3, a1);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptSrtObjInt(a3, a1, tuple);
	}

	default <T> void useWith(short a3, T a1, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		int tuple = accessInt();
		accessFunction.doAcceptSrtIntObj(a3, tuple, a1);
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

	default double useWith(LIntToDblFunction accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.doApplyAsDbl(tuple);
		releaseInt(tuple);
		return retval;
	}

	default float useWith(LIntToFltFunction accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.doApplyAsFlt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default long useWith(LIntToLongFunction accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseInt(tuple);
		return retval;
	}

	default short useWith(LIntToSrtFunction accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.doApplyAsSrt(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LBiIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(int a1, LBiIntFunction.LInt1Int0Func<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyInt1Int0(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.LObjIntObj1Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjIntObj1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjIntFunction.LIntObj0Obj1Func<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObj0Obj1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R> R useWith(LIntFunction<R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a3, LObjBiIntFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjInt2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a3, LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyInt1ObjInt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a3, T a1, LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyBiIntObj(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(boolean a3, T a1, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(byte a3, T a1, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(char a3, T a1, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(double a3, T a1, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(float a3, T a1, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(long a3, T a1, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction.LObjObj2IntFunc<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjObj2Int(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a3, LObjIntObjFunction.LIntBiObjFunc<T1, T2, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntBiObj(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplySrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(short a3, T a1, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplySrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LOiFunction<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApply(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LOiFunction.LIntObjFunc<T, R> accessFunction) {
		int tuple = accessInt();
		R retval = accessFunction.doApplyIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> byte useWith(T a1, LOiToByteFunction<T> accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.doApplyAsByte(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> byte useWith(T a1, LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		int tuple = accessInt();
		byte retval = accessFunction.doApplyAsByteIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> char useWith(T a1, LOiToCharFunction<T> accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.doApplyAsChar(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> char useWith(T a1, LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		int tuple = accessInt();
		char retval = accessFunction.doApplyAsCharIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> double useWith(T a1, LOiToDblFunction<T> accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.doApplyAsDbl(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> double useWith(T a1, LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		int tuple = accessInt();
		double retval = accessFunction.doApplyAsDblIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> float useWith(T a1, LOiToFltFunction<T> accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.doApplyAsFlt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> float useWith(T a1, LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		int tuple = accessInt();
		float retval = accessFunction.doApplyAsFltIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LOiToIntFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> long useWith(T a1, LOiToLongFunction<T> accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.doApplyAsLong(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> long useWith(T a1, LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		int tuple = accessInt();
		long retval = accessFunction.doApplyAsLongIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> short useWith(T a1, LOiToSrtFunction<T> accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.doApplyAsSrt(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> short useWith(T a1, LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		int tuple = accessInt();
		short retval = accessFunction.doApplyAsSrtIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(boolean a3, T a1, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(byte a3, T a1, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(char a3, T a1, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(double a3, T a1, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(float a3, T a1, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction.LObjObj2IntToIntFunc<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjObj2Int(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> int useWith(T1 a1, T2 a3, LTieFunction.LIntBiObjToIntFunc<T1, T2> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntBiObj(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a3, LTieIntFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a3, LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(int a3, T a1, LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(long a3, T a1, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsInt(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntSrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> int useWith(short a3, T a1, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		int tuple = accessInt();
		int retval = accessFunction.doApplyAsIntSrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBiIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(int a1, LBiIntPredicate.LInt1Int0Pred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestInt1Int0(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.LObjIntObj1Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjIntObj1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjIntPredicate.LIntObj0Obj1Pred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObj0Obj1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(boolean a1, LBoolIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(boolean a1, LBoolIntPredicate.LIntBoolPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntBool(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LByteIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LByteIntPredicate.LIntBytePred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntByte(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(char a1, LCharIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(char a1, LCharIntPredicate.LIntCharPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntChar(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(double a1, LDblIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(double a1, LDblIntPredicate.LIntDblPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntDbl(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LFltIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LFltIntPredicate.LIntFltPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntFlt(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(LIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(long a1, LLongIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(long a1, LLongIntPredicate.LIntLongPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntLong(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a3, LObjBiIntPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjInt2Int1(a1, tuple, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a3, LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a3, T a1, LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple, a1, a2);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestBiIntObj(tuple, a2, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjBoolInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjBool(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntBoolObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestBoolObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(boolean a3, T a1, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestBoolIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjByteInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjByte(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntByteObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestByteObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(byte a3, T a1, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestByteIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjCharInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjChar(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntCharObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestCharObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(char a3, T a1, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestCharIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjDblInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjDbl(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntDblObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestDblObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(double a3, T a1, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestDblIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjFltInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjFlt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntFltObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestFltObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(float a3, T a1, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestFltIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjLongInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjLong(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntLongObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestLongObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(long a3, T a1, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestLongIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate.LObjObj2IntPred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjObj2Int(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a3, LObjIntObjPredicate.LIntBiObjPred<T1, T2> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntBiObj(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntPredicate.LIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObj(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestObjSrtInt(a1, a3, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntObjSrt(tuple, a1, a3);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntSrtObj(tuple, a3, a1);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestSrtObjInt(a3, a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default <T> boolean useWith(short a3, T a1, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestSrtIntObj(a3, tuple, a1);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LSrtIntPredicate accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseInt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LSrtIntPredicate.LIntSrtPred accessFunction) {
		int tuple = accessInt();
		boolean retval = accessFunction.doTestIntSrt(tuple, a1);
		releaseInt(tuple);
		return retval;
	}

}
