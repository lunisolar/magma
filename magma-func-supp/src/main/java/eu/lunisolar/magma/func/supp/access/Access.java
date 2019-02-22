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
		accessFunction.accept(tuple);
	}

	default void useWith(boolean a2, LObjBoolConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(boolean a2, LObjBoolConsumer.LBoolObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptBoolObj(a2, tuple);
	}

	default void useWith(byte a2, LObjByteConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(byte a2, LObjByteConsumer.LByteObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptByteObj(a2, tuple);
	}

	default void useWith(char a2, LObjCharConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(char a2, LObjCharConsumer.LCharObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptCharObj(a2, tuple);
	}

	default void useWith(double a2, LObjDblConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(double a2, LObjDblConsumer.LDblObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptDblObj(a2, tuple);
	}

	default void useWith(float a2, LObjFltConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(float a2, LObjFltConsumer.LFltObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptFltObj(a2, tuple);
	}

	default void useWith(int a2, LObjIntConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LObjIntConsumer.LIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObj(a2, tuple);
	}

	default void useWith(long a2, LObjLongConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(long a2, LObjLongConsumer.LLongObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptLongObj(a2, tuple);
	}

	default void useWith(short a2, LObjSrtConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(short a2, LObjSrtConsumer.LSrtObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptSrtObj(a2, tuple);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjBoolInt(tuple, a3, a2);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjBool(a2, tuple, a3);
	}

	default void useWith(int a2, boolean a3, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntBoolObj(a2, a3, tuple);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptBoolObjInt(a3, tuple, a2);
	}

	default void useWith(boolean a3, int a2, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptBoolIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjByteInt(tuple, a3, a2);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjByte(a2, tuple, a3);
	}

	default void useWith(int a2, byte a3, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntByteObj(a2, a3, tuple);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptByteObjInt(a3, tuple, a2);
	}

	default void useWith(byte a3, int a2, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptByteIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, char a3, LTieCharConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjCharInt(tuple, a3, a2);
	}

	default void useWith(int a2, char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjChar(a2, tuple, a3);
	}

	default void useWith(int a2, char a3, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntCharObj(a2, a3, tuple);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptCharObjInt(a3, tuple, a2);
	}

	default void useWith(char a3, int a2, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptCharIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, double a3, LTieDblConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjDblInt(tuple, a3, a2);
	}

	default void useWith(int a2, double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjDbl(a2, tuple, a3);
	}

	default void useWith(int a2, double a3, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntDblObj(a2, a3, tuple);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptDblObjInt(a3, tuple, a2);
	}

	default void useWith(double a3, int a2, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptDblIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, float a3, LTieFltConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjFltInt(tuple, a3, a2);
	}

	default void useWith(int a2, float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjFlt(a2, tuple, a3);
	}

	default void useWith(int a2, float a3, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntFltObj(a2, a3, tuple);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptFltObjInt(a3, tuple, a2);
	}

	default void useWith(float a3, int a2, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptFltIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, int a3, LTieIntConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(int a2, int a3, LTieIntConsumer.LInt1Obj0Int2Cons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptInt1Obj0Int2(a2, tuple, a3);
	}

	default void useWith(int a2, int a3, LTieIntConsumer.LInt1Int2Obj0Cons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptInt1Int2Obj0(a2, a3, tuple);
	}

	default void useWith(int a2, long a3, LTieLongConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjLongInt(tuple, a3, a2);
	}

	default void useWith(int a2, long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjLong(a2, tuple, a3);
	}

	default void useWith(int a2, long a3, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntLongObj(a2, a3, tuple);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptLongObjInt(a3, tuple, a2);
	}

	default void useWith(long a3, int a2, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptLongIntObj(a3, a2, tuple);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer<T> accessFunction) {
		T tuple = access();
		accessFunction.accept(tuple, a2, a3);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptObjSrtInt(tuple, a3, a2);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntObjSrt(a2, tuple, a3);
	}

	default void useWith(int a2, short a3, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptIntSrtObj(a2, a3, tuple);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptSrtObjInt(a3, tuple, a2);
	}

	default void useWith(short a3, int a2, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		T tuple = access();
		accessFunction.acceptSrtIntObj(a3, a2, tuple);
	}

	default T useWith(T a2, LBinaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default T useWith(T a2, T a3, LTernaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default T useWith(LUnaryOperator<T> accessFunction) {
		T tuple = access();
		T retval = accessFunction.apply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(LFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction.LInt1Obj0Int2Func<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyInt1Obj0Int2(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, int a3, LObjBiIntFunction.LInt1Int2Obj0Func<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyInt1Int2Obj0(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LObjBoolFunction.LBoolObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyBoolObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LObjByteFunction.LByteObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyByteObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LObjCharFunction.LCharObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyCharObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDblFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LObjDblFunction.LDblObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyDblObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFltFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LObjFltFunction.LFltObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyFltObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, boolean a3, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, int a2, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, byte a3, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, int a2, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, char a3, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(char a3, int a2, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, double a3, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(double a3, int a2, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, float a3, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(float a3, int a2, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, long a3, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a3, int a2, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, short a3, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applySrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a3, int a2, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applySrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyLongObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjSrtFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LObjSrtFunction.LSrtObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applySrtObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LOiFunction<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.apply(tuple, a2);
		release(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LOiFunction.LIntObjFunc<T, R> accessFunction) {
		T tuple = access();
		R retval = accessFunction.applyIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default byte useWith(int a2, LOiToByteFunction<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.applyAsByte(tuple, a2);
		release(tuple);
		return retval;
	}

	default byte useWith(int a2, LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.applyAsByteIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default char useWith(int a2, LOiToCharFunction<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.applyAsChar(tuple, a2);
		release(tuple);
		return retval;
	}

	default char useWith(int a2, LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.applyAsCharIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default double useWith(int a2, LOiToDblFunction<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.applyAsDbl(tuple, a2);
		release(tuple);
		return retval;
	}

	default double useWith(int a2, LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.applyAsDblIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default float useWith(int a2, LOiToFltFunction<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.applyAsFlt(tuple, a2);
		release(tuple);
		return retval;
	}

	default float useWith(int a2, LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.applyAsFltIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LOiToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default long useWith(int a2, LOiToLongFunction<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.applyAsLong(tuple, a2);
		release(tuple);
		return retval;
	}

	default long useWith(int a2, LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.applyAsLongIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default short useWith(int a2, LOiToSrtFunction<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.applyAsSrt(tuple, a2);
		release(tuple);
		return retval;
	}

	default short useWith(int a2, LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.applyAsSrtIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, boolean a3, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(boolean a3, int a2, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, byte a3, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(byte a3, int a2, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, char a3, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(char a3, int a2, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, double a3, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(double a3, int a2, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, float a3, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(float a3, int a2, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntInt1Obj0Int2(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, int a3, LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntInt1Int2Obj0(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, long a3, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(long a3, int a2, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default int useWith(int a2, short a3, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntSrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default int useWith(short a3, int a2, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsIntSrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default byte useWith(LToByteFunction<T> accessFunction) {
		T tuple = access();
		byte retval = accessFunction.applyAsByte(tuple);
		release(tuple);
		return retval;
	}

	default char useWith(LToCharFunction<T> accessFunction) {
		T tuple = access();
		char retval = accessFunction.applyAsChar(tuple);
		release(tuple);
		return retval;
	}

	default double useWith(LToDblFunction<T> accessFunction) {
		T tuple = access();
		double retval = accessFunction.applyAsDbl(tuple);
		release(tuple);
		return retval;
	}

	default float useWith(LToFltFunction<T> accessFunction) {
		T tuple = access();
		float retval = accessFunction.applyAsFlt(tuple);
		release(tuple);
		return retval;
	}

	default int useWith(LToIntFunction<T> accessFunction) {
		T tuple = access();
		int retval = accessFunction.applyAsInt(tuple);
		release(tuple);
		return retval;
	}

	default long useWith(LToLongFunction<T> accessFunction) {
		T tuple = access();
		long retval = accessFunction.applyAsLong(tuple);
		release(tuple);
		return retval;
	}

	default short useWith(LToSrtFunction<T> accessFunction) {
		T tuple = access();
		short retval = accessFunction.applyAsSrt(tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate.LInt1Obj0Int2Pred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testInt1Obj0Int2(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, int a3, LObjBiIntPredicate.LInt1Int2Obj0Pred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testInt1Int2Obj0(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, LObjBoolPredicate.LBoolObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testBoolObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LObjBytePredicate.LByteObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testByteObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a2, LObjCharPredicate.LCharObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testCharObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDblPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a2, LObjDblPredicate.LDblObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testDblObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFltPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a2, LObjFltPredicate.LFltObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testFltObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjBoolInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjBool(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, boolean a3, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntBoolObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testBoolObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, int a2, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testBoolIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjByteInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjByte(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, byte a3, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntByteObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testByteObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(byte a3, int a2, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testByteIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjCharInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjChar(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, char a3, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntCharObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testCharObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(char a3, int a2, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testCharIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjDblInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjDbl(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, double a3, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntDblObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testDblObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(double a3, int a2, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testDblIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjFltInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjFlt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, float a3, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntFltObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testFltObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(float a3, int a2, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testFltIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjLongInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjLong(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, long a3, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntLongObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testLongObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a3, int a2, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testLongIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntPredicate.LIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testObjSrtInt(tuple, a3, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntObjSrt(a2, tuple, a3);
		release(tuple);
		return retval;
	}

	default boolean useWith(int a2, short a3, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testIntSrtObj(a2, a3, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testSrtObjInt(a3, tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a3, int a2, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testSrtIntObj(a3, a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(long a2, LObjLongPredicate.LLongObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testLongObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjSrtPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple, a2);
		release(tuple);
		return retval;
	}

	default boolean useWith(short a2, LObjSrtPredicate.LSrtObjPred<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.testSrtObj(a2, tuple);
		release(tuple);
		return retval;
	}

	default boolean useWith(LPredicate<T> accessFunction) {
		T tuple = access();
		boolean retval = accessFunction.test(tuple);
		release(tuple);
		return retval;
	}

}
