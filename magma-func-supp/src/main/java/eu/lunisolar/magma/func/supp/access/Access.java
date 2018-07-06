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
public interface Access<T> {

	static <T> Access<T> wrap(Access<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	T access();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void release(T a) {
		// NOOP
	}

	default void useWith(LConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple);
	}

	default void useWith(boolean a2, LObjBoolConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(boolean a2, LObjBoolConsumer.LBoolObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptBoolObj(a2, tuple);
	}

	default void useWith(byte a2, LObjByteConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(byte a2, LObjByteConsumer.LByteObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptByteObj(a2, tuple);
	}

	default void useWith(char a2, LObjCharConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(char a2, LObjCharConsumer.LCharObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptCharObj(a2, tuple);
	}

	default void useWith(double a2, LObjDblConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(double a2, LObjDblConsumer.LDblObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptDblObj(a2, tuple);
	}

	default void useWith(float a2, LObjFltConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a2, LObjFltConsumer.LFltObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptFltObj(a2, tuple);
	}

	default void useWith(int a2, LObjIntConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LObjIntConsumer.LIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObj(a2, tuple);
	}

	default void useWith(long a2, LObjLongConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(long a2, LObjLongConsumer.LLongObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptLongObj(a2, tuple);
	}

	default void useWith(short a2, LObjSrtConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a2, LObjSrtConsumer.LSrtObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptSrtObj(a2, tuple);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjBoolInt(tuple, a3, a2);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjBool(a2, tuple, a3);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntBoolObj(a2, a3, tuple);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptBoolObjInt(a3, tuple, a2);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptBoolIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjByteInt(tuple, a3, a2);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjByte(a2, tuple, a3);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntByteObj(a2, a3, tuple);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptByteObjInt(a3, tuple, a2);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptByteIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, char a3, LTieCharConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjCharInt(tuple, a3, a2);
	}

	default void useWith(int a2, char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjChar(a2, tuple, a3);
	}

	default void useWith(int a2, char a3, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntCharObj(a2, a3, tuple);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptCharObjInt(a3, tuple, a2);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptCharIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, double a3, LTieDblConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjDblInt(tuple, a3, a2);
	}

	default void useWith(int a2, double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjDbl(a2, tuple, a3);
	}

	default void useWith(int a2, double a3, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntDblObj(a2, a3, tuple);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptDblObjInt(a3, tuple, a2);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptDblIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, float a3, LTieFltConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjFltInt(tuple, a3, a2);
	}

	default void useWith(int a2, float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjFlt(a2, tuple, a3);
	}

	default void useWith(int a2, float a3, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntFltObj(a2, a3, tuple);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptFltObjInt(a3, tuple, a2);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptFltIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, int a3, LTieIntConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(int a2, int a3, LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptInt1ObjInt(a2, tuple, a3);
	}

	default void useWith(int a2, int a3, LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptInt1Int2Obj(a2, a3, tuple);
	}

	default void useWith(int a2, long a3, LTieLongConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjLongInt(tuple, a3, a2);
	}

	default void useWith(int a2, long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjLong(a2, tuple, a3);
	}

	default void useWith(int a2, long a3, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntLongObj(a2, a3, tuple);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptLongObjInt(a3, tuple, a2);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptLongIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.doAccept(tuple, a2, a3);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptObjSrtInt(tuple, a3, a2);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntObjSrt(a2, tuple, a3);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptIntSrtObj(a2, a3, tuple);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptSrtObjInt(a3, tuple, a2);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.doAcceptSrtIntObj(a3, a2, tuple);
	}

	default T useWith(T a2, LBinaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default T useWith(T a2, T a3, LTernaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default T useWith(LUnaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.doApply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(LFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyInt1ObjInt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyInt1Int2Obj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction.LBoolObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyBoolObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction.LByteObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyByteObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction.LCharObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyCharObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDblFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDblFunction.LDblObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyDblObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFltFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFltFunction.LFltObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyFltObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplySrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplySrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyLongObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjSrtFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjSrtFunction.LSrtObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplySrtObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LOiFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LOiFunction.LIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.doApplyIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default byte useWith(int a2, LOiToByteFunction<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.doApplyAsByte(tuple, a2);
		release(tuple);
		return retval;
	}

	default byte useWith(int a2, LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.doApplyAsByteIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default char useWith(int a2, LOiToCharFunction<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.doApplyAsChar(tuple, a2);
		release(tuple);
		return retval;
	}

	default char useWith(int a2, LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.doApplyAsCharIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default double useWith(int a2, LOiToDblFunction<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.doApplyAsDbl(tuple, a2);
		release(tuple);
		return retval;
	}

	default double useWith(int a2, LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.doApplyAsDblIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default float useWith(int a2, LOiToFltFunction<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.doApplyAsFlt(tuple, a2);
		release(tuple);
		return retval;
	}

	default float useWith(int a2, LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.doApplyAsFltIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LOiToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default long useWith(int a2, LOiToLongFunction<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.doApplyAsLong(tuple, a2);
		release(tuple);
		return retval;
	}

	default long useWith(int a2, LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.doApplyAsLongIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default short useWith(int a2, LOiToSrtFunction<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.doApplyAsSrt(tuple, a2);
		release(tuple);
		return retval;
	}

	default short useWith(int a2, LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.doApplyAsSrtIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntSrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsIntSrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default byte useWith(LToByteFunction<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.doApplyAsByte(tuple);
		release(tuple);
		return retval;
	}

	default char useWith(LToCharFunction<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.doApplyAsChar(tuple);
		release(tuple);
		return retval;
	}

	default double useWith(LToDblFunction<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.doApplyAsDbl(tuple);
		release(tuple);
		return retval;
	}

	default float useWith(LToFltFunction<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.doApplyAsFlt(tuple);
		release(tuple);
		return retval;
	}

	default int useWith(LToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.doApplyAsInt(tuple);
		release(tuple);
		return retval;
	}

	default long useWith(LToLongFunction<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.doApplyAsLong(tuple);
		release(tuple);
		return retval;
	}

	default short useWith(LToSrtFunction<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.doApplyAsSrt(tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestInt1ObjInt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestInt1Int2Obj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate.LBoolObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestBoolObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate.LByteObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestByteObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate.LCharObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestCharObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDblPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDblPredicate.LDblObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestDblObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFltPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFltPredicate.LFltObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestFltObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate.LIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestSrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestSrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate.LLongObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestLongObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjSrtPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjSrtPredicate.LSrtObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTestSrtObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(LPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.doTest(tuple);
		release(tuple);
		return retval;
	}

}
