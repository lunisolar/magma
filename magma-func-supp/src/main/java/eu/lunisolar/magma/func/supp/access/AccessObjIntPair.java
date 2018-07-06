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
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LObjIntConsumer.LIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObj(tuple.second(), tuple.first());
	}

	default void useWith(boolean a3, LTieBoolConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjBoolInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjBool(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntBoolObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptBoolObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptBoolIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LTieByteConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjByteInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjByte(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntByteObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptByteObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptByteIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LTieCharConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjCharInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjChar(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntCharObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptCharObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptCharIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LTieDblConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjDblInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjDbl(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntDblObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptDblObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptDblIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LTieFltConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjFltInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjFlt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntFltObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptFltObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptFltIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LTieIntConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a2, LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjInt2Int1(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a3, LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptInt1ObjInt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptInt1Int2Obj(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a2, LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptInt2ObjInt1(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptBiIntObj(tuple.second(), a2, tuple.first());
	}

	default void useWith(long a3, LTieLongConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjLongInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjLong(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntLongObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptLongObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptLongIntObj(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LTieSrtConsumer<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptObjSrtInt(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntObjSrt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptIntSrtObj(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptSrtObjInt(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		accessFunction.doAcceptSrtIntObj(a3, tuple.second(), tuple.first());
	}

	default <R> R useWith(int a3, LObjBiIntFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjInt2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyInt1ObjInt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyBiIntObj(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplySrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplySrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LOiFunction<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LOiFunction.LIntObjFunc<T, R> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		R retval = accessFunction.doApplyIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default byte useWith(LOiToByteFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		byte retval = accessFunction.doApplyAsByte(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default byte useWith(LOiToByteFunction.LIntObjToByteFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		byte retval = accessFunction.doApplyAsByteIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default char useWith(LOiToCharFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		char retval = accessFunction.doApplyAsChar(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default char useWith(LOiToCharFunction.LIntObjToCharFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		char retval = accessFunction.doApplyAsCharIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default double useWith(LOiToDblFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		double retval = accessFunction.doApplyAsDbl(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default double useWith(LOiToDblFunction.LIntObjToDblFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		double retval = accessFunction.doApplyAsDblIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default float useWith(LOiToFltFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		float retval = accessFunction.doApplyAsFlt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default float useWith(LOiToFltFunction.LIntObjToFltFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		float retval = accessFunction.doApplyAsFltIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(LOiToIntFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(LOiToIntFunction.LIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default long useWith(LOiToLongFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		long retval = accessFunction.doApplyAsLong(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default long useWith(LOiToLongFunction.LIntObjToLongFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		long retval = accessFunction.doApplyAsLongIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default short useWith(LOiToSrtFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		short retval = accessFunction.doApplyAsSrt(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default short useWith(LOiToSrtFunction.LIntObjToSrtFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		short retval = accessFunction.doApplyAsSrtIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(boolean a3, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(byte a3, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(char a3, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(double a3, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(float a3, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a3, LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(long a3, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntSrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default int useWith(short a3, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		int retval = accessFunction.doApplyAsIntSrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjInt2Int1(tuple.first(), tuple.second(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple.second(), tuple.first(), a2);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestBiIntObj(tuple.second(), a2, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjBoolInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjBool(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntBoolObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestBoolObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestBoolIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjByteInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjByte(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntByteObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestByteObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestByteIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjCharInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjChar(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntCharObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestCharObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestCharIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjDblInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjDbl(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntDblObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestDblObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestDblIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjFltInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjFlt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntFltObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestFltObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestFltIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjLongInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjLong(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntLongObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestLongObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestLongIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicate.LIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObj(tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestObjSrtInt(tuple.first(), a3, tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntObjSrt(tuple.second(), tuple.first(), a3);
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestIntSrtObj(tuple.second(), a3, tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestSrtObjInt(a3, tuple.first(), tuple.second());
		releaseObjIntPair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		LObjIntPair<T> tuple = accessObjIntPair();
		boolean retval = accessFunction.doTestSrtIntObj(a3, tuple.second(), tuple.first());
		releaseObjIntPair(tuple);
		return retval;
	}

}
