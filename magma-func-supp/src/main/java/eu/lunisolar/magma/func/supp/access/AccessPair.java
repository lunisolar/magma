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

	default void useWith(LBiConsumer.LObj1Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0(tuple.second(), tuple.first());
	}

	default <T3> void useWith(T3 a3, LTriConsumer<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default <T3> void useWith(T3 a3, LTriConsumer.LObj1BiObjCons<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1BiObj(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObjBoolObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjBoolObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObj1Obj0BoolCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Bool(tuple.second(), tuple.first(), a3);
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LObj1BoolObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1BoolObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LBoolObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptBoolObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(boolean a3, LBiObjBoolConsumer.LBoolObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptBoolObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObjByteObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjByteObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObj1Obj0ByteCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Byte(tuple.second(), tuple.first(), a3);
	}

	default void useWith(byte a3, LBiObjByteConsumer.LObj1ByteObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1ByteObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LByteObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptByteObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(byte a3, LBiObjByteConsumer.LByteObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptByteObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.LObjCharObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjCharObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.LObj1Obj0CharCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Char(tuple.second(), tuple.first(), a3);
	}

	default void useWith(char a3, LBiObjCharConsumer.LObj1CharObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1CharObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(char a3, LBiObjCharConsumer.LCharObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptCharObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(char a3, LBiObjCharConsumer.LCharObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptCharObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(double a3, LBiObjDblConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(double a3, LBiObjDblConsumer.LObjDblObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjDblObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(double a3, LBiObjDblConsumer.LObj1Obj0DblCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Dbl(tuple.second(), tuple.first(), a3);
	}

	default void useWith(double a3, LBiObjDblConsumer.LObj1DblObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1DblObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(double a3, LBiObjDblConsumer.LDblObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptDblObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(double a3, LBiObjDblConsumer.LDblObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptDblObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(float a3, LBiObjFltConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(float a3, LBiObjFltConsumer.LObjFltObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjFltObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(float a3, LBiObjFltConsumer.LObj1Obj0FltCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Flt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(float a3, LBiObjFltConsumer.LObj1FltObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1FltObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(float a3, LBiObjFltConsumer.LFltObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptFltObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(float a3, LBiObjFltConsumer.LFltObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptFltObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.LObjIntObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjIntObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.LObj1Obj0IntCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Int(tuple.second(), tuple.first(), a3);
	}

	default void useWith(int a3, LBiObjIntConsumer.LObj1IntObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1IntObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(int a3, LBiObjIntConsumer.LIntObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptIntObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(int a3, LBiObjIntConsumer.LIntObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptIntObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.LObjLongObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjLongObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.LObj1Obj0LongCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Long(tuple.second(), tuple.first(), a3);
	}

	default void useWith(long a3, LBiObjLongConsumer.LObj1LongObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1LongObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(long a3, LBiObjLongConsumer.LLongObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptLongObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(long a3, LBiObjLongConsumer.LLongObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptLongObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(short a3, LBiObjSrtConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), tuple.second(), a3);
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObjSrtObj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjSrtObj1(tuple.first(), a3, tuple.second());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObj1Obj0SrtCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1Obj0Srt(tuple.second(), tuple.first(), a3);
	}

	default void useWith(short a3, LBiObjSrtConsumer.LObj1SrtObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj1SrtObj0(tuple.second(), a3, tuple.first());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LSrtObj0Obj1Cons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptSrtObj0Obj1(a3, tuple.first(), tuple.second());
	}

	default void useWith(short a3, LBiObjSrtConsumer.LSrtObjObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptSrtObjObj0(a3, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieConsumer<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAccept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieConsumer.LObjObj2IntCons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObjObj2Int(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieConsumer.LIntBiObjCons<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptIntBiObj(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieConsumer.LIntObj2Obj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptIntObj2Obj0(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieConsumer.LObj2Obj0IntCons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj2Obj0Int(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieConsumer.LObj2IntObj0Cons<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		accessFunction.doAcceptObj2IntObj0(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LBiFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(LBiFunction.LObj1Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction<T1, T2, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R, T3> R useWith(T3 a3, LTriFunction.LObj1BiObjFunc<T2, T1, T3, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1BiObj(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObjBoolObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjBoolObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObj1Obj0BoolFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Bool(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LObj1BoolObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1BoolObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LBoolObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyBoolObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LBiObjBoolFunction.LBoolObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyBoolObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObjByteObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjByteObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObj1Obj0ByteFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Byte(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LObj1ByteObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1ByteObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LByteObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyByteObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(byte a3, LBiObjByteFunction.LByteObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyByteObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObjCharObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjCharObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObj1Obj0CharFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Char(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LObj1CharObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1CharObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LCharObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyCharObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(char a3, LBiObjCharFunction.LCharObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyCharObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObjDblObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjDblObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObj1Obj0DblFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Dbl(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LObj1DblObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1DblObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LDblObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyDblObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(double a3, LBiObjDblFunction.LDblObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyDblObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObjFltObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjFltObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObj1Obj0FltFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Flt(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LObj1FltObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1FltObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LFltObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyFltObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(float a3, LBiObjFltFunction.LFltObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyFltObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObjIntObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjIntObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObj1Obj0IntFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Int(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LObj1IntObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1IntObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LIntObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyIntObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a3, LBiObjIntFunction.LIntObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyIntObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObjLongObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjLongObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObj1Obj0LongFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Long(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LObj1LongObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1LongObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LLongObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyLongObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(long a3, LBiObjLongFunction.LLongObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyLongObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObjSrtObj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjSrtObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObj1Obj0SrtFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1Obj0Srt(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LObj1SrtObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj1SrtObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LSrtObj0Obj1Func<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplySrtObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(short a3, LBiObjSrtFunction.LSrtObjObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplySrtObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApply(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObjObj2IntFunc<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObjObj2Int(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LIntBiObjFunc<T1, T2, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyIntBiObj(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LIntObj2Obj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyIntObj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObj2Obj0IntFunc<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj2Obj0Int(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntObjFunction.LObj2IntObj0Func<T2, T1, R> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		R retval = accessFunction.doApplyObj2IntObj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObjObj2IntToIntFunc<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntObjObj2Int(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LIntBiObjToIntFunc<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntIntBiObj(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LIntObj2Obj0ToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntIntObj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObj2Obj0IntToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntObj2Obj0Int(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFunction.LObj2IntObj0ToIntFunc<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntObj2IntObj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.doApplyAsByte(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default byte useWith(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		byte retval = accessFunction.doApplyAsByteObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.doApplyAsChar(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default char useWith(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		char retval = accessFunction.doApplyAsCharObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDblBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.doApplyAsDbl(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default double useWith(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		double retval = accessFunction.doApplyAsDblObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFltBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.doApplyAsFlt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default float useWith(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		float retval = accessFunction.doApplyAsFltObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default int useWith(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <T3> int useWith(T3 a3, LToIntTriFunction<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <T3> int useWith(T3 a3, LToIntTriFunction.LToIntObj1BiObjFunc<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		int retval = accessFunction.doApplyAsIntObj1BiObj(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.doApplyAsLong(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default long useWith(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		long retval = accessFunction.doApplyAsLongObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToSrtBiFunction<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.doApplyAsSrt(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default short useWith(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		short retval = accessFunction.doApplyAsSrtObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObjBoolObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjBoolObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObj1Obj0BoolPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Bool(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LObj1BoolObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1BoolObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LBoolObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestBoolObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LBiObjBoolPredicate.LBoolObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestBoolObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObjByteObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjByteObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObj1Obj0BytePred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Byte(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LObj1ByteObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1ByteObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LByteObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestByteObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(byte a3, LBiObjBytePredicate.LByteObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestByteObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObjCharObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjCharObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObj1Obj0CharPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Char(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LObj1CharObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1CharObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LCharObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestCharObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(char a3, LBiObjCharPredicate.LCharObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestCharObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObjDblObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjDblObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObj1Obj0DblPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Dbl(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LObj1DblObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1DblObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LDblObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestDblObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(double a3, LBiObjDblPredicate.LDblObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestDblObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObjFltObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjFltObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObj1Obj0FltPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Flt(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LObj1FltObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1FltObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LFltObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestFltObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(float a3, LBiObjFltPredicate.LFltObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestFltObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObjIntObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjIntObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObj1Obj0IntPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Int(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LObj1IntObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1IntObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LIntObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestIntObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a3, LBiObjIntPredicate.LIntObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestIntObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObjLongObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjLongObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObj1Obj0LongPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Long(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LObj1LongObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1LongObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LLongObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestLongObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LBiObjLongPredicate.LLongObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestLongObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObjSrtObj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjSrtObj1(tuple.first(), a3, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObj1Obj0SrtPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0Srt(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LObj1SrtObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1SrtObj0(tuple.second(), a3, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LSrtObj0Obj1Pred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestSrtObj0Obj1(a3, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(short a3, LBiObjSrtPredicate.LSrtObjObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestSrtObjObj0(a3, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(LBiPredicate.LObj1Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1Obj0(tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), a2, tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObjObj2IntPred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObjObj2Int(tuple.first(), tuple.second(), a2);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LIntBiObjPred<T1, T2> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestIntBiObj(a2, tuple.first(), tuple.second());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LIntObj2Obj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestIntObj2Obj0(a2, tuple.second(), tuple.first());
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObj2Obj0IntPred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj2Obj0Int(tuple.second(), tuple.first(), a2);
		releasePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntObjPredicate.LObj2IntObj0Pred<T2, T1> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj2IntObj0(tuple.second(), a2, tuple.first());
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate<T1, T2, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), a3);
		releasePair(tuple);
		return retval;
	}

	default <T3> boolean useWith(T3 a3, LTriPredicate.LObj1BiObjPred<T2, T1, T3> accessFunction) {
		LPair<T1, T2> tuple = accessPair();
		boolean retval = accessFunction.doTestObj1BiObj(tuple.second(), tuple.first(), a3);
		releasePair(tuple);
		return retval;
	}

}
