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
public interface AccessObjIntPair<T> {

	static <T> AccessObjIntPair<T> wrap(AccessObjIntPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntPair<T> accessObjIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntPair(LObjIntPair<T> a) {
		// NOOP
	}

	default void useWith(LObjIntConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjIntConsumer.LIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObj(tuple.second(), tuple.first());
	}

	default void useWith(boolean a3, LTieBoolConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjBoolInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjBool(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntBoolObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptBoolObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptBoolIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LTieByteConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjByteInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjByte(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntByteObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptByteObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptByteIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LTieCharConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjCharInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjChar(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntCharObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptCharObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptCharIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LTieDblConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjDblInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjDbl(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntDblObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptDblObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptDblIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LTieFltConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjFltInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjFlt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntFltObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptFltObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptFltIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LTieIntConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a2, LTieIntConsumer.LObj0Int2Int1Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObj0Int2Int1(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a3, LTieIntConsumer.LInt1Obj0Int2Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptInt1Obj0Int2(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LTieIntConsumer.LInt1Int2Obj0Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptInt1Int2Obj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a2, LTieIntConsumer.LInt2Obj0Int1Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptInt2Obj0Int1(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieIntConsumer.LBiInt1Obj0Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptBiInt1Obj0(tuple.second(), a2, tuple.first());
	}

	default void useWith(long a3, LTieLongConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjLongInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjLong(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntLongObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptLongObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptLongIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LTieSrtConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptObjSrtInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntObjSrt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptIntSrtObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptSrtObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.acceptSrtIntObj(a3, tuple.second(), tuple.first());
	}

	default <R> R useWith(int a3, LObjBiIntFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LObj0Int2Int1Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObj0Int2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LObjBiIntFunction.LInt1Obj0Int2Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyInt1Obj0Int2(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LObjBiIntFunction.LInt1Int2Obj0Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyInt1Int2Obj0(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LInt2Obj0Int1Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyInt2Obj0Int1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LBiInt1Obj0Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyBiInt1Obj0(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applySrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applySrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LOiFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LOiFunction.LIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.applyIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default byte useWith(LOiToByteFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		byte retval = accessFunction.applyAsByte(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default byte useWith(LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		byte retval = accessFunction.applyAsByteIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default char useWith(LOiToCharFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		char retval = accessFunction.applyAsChar(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default char useWith(LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		char retval = accessFunction.applyAsCharIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default double useWith(LOiToDblFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		double retval = accessFunction.applyAsDbl(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default double useWith(LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		double retval = accessFunction.applyAsDblIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default float useWith(LOiToFltFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		float retval = accessFunction.applyAsFlt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default float useWith(LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		float retval = accessFunction.applyAsFltIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(LOiToIntFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default long useWith(LOiToLongFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		long retval = accessFunction.applyAsLong(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default long useWith(LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		long retval = accessFunction.applyAsLongIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default short useWith(LOiToSrtFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		short retval = accessFunction.applyAsSrt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default short useWith(LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		short retval = accessFunction.applyAsSrtIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LObj0Int2Int1ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObj0Int2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntInt1Obj0Int2(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntInt1Int2Obj0(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntInt2Obj0Int1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LBiInt1Obj0ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntBiInt1Obj0(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntSrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.applyAsIntSrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LObj0Int2Int1Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObj0Int2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate.LInt1Obj0Int2Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testInt1Obj0Int2(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate.LInt1Int2Obj0Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testInt1Int2Obj0(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LInt2Obj0Int1Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testInt2Obj0Int1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LBiInt1Obj0Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testBiInt1Obj0(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicate.LIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testSrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.testSrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

}
