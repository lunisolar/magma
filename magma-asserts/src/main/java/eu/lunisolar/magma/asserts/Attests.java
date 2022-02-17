/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts;

import java.util.function.*;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.std.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.action.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.supplier.*; // NOSONAR
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
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
//import eu.lunisolar.magma.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the usecase otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface Attests {

	public static final DefaultAttests<ObjectAssert> THEN = new DefaultAttests() {
	};

	@Nonnull
	public static <A extends LAction> LActionAssert.The<A> attestAct(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> attestBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer.LObj1Obj0Cons<T2, T1>, T2, T1> LObj1Obj0ConsAssert.The<A, T2, T1> attestObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func) {
		return new LObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> attestCons(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LQuadConsumer<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadConsumerAssert.The<A, T1, T2, T3, T4> attestQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintConsumerAssert.The<A, T1, T2, T3, T4, T5> attestQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> attestTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1ConsAssert.The<A, T1, T3, T2> attestObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func) {
		return new LObj0Obj2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj1BiObj2Cons<T2, T1, T3>, T2, T1, T3> LObj1BiObj2ConsAssert.The<A, T2, T1, T3> attestObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func) {
		return new LObj1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0ConsAssert.The<A, T2, T3, T1> attestObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func) {
		return new LObj1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1ConsAssert.The<A, T3, T1, T2> attestObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func) {
		return new LObj2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0ConsAssert.The<A, T3, T2, T1> attestBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func) {
		return new LBiObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolConsumer> LBoolConsumerAssert.The<A> attestBoolCons(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteConsumer> LByteConsumerAssert.The<A> attestByteCons(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharConsumer> LCharConsumerAssert.The<A> attestCharCons(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblConsumer> LDblConsumerAssert.The<A> attestDblCons(LDblConsumer func) {
		return new LDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltConsumer> LFltConsumerAssert.The<A> attestFltCons(LFltConsumer func) {
		return new LFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LIntConsumer> LIntConsumerAssert.The<A> attestIntCons(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongConsumer> LLongConsumerAssert.The<A> attestLongCons(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtConsumer> LSrtConsumerAssert.The<A> attestSrtCons(LSrtConsumer func) {
		return new LSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> attestBiBoolCons(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer.LBool1Bool0Cons> LBool1Bool0ConsAssert.The<A> attestBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func) {
		return new LBool1Bool0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> attestBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumer.LByte1Byte0Cons> LByte1Byte0ConsAssert.The<A> attestByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func) {
		return new LByte1Byte0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> attestBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer.LChar1Char0Cons> LChar1Char0ConsAssert.The<A> attestChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func) {
		return new LChar1Char0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> attestBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDblConsumer.LDbl1Dbl0Cons> LDbl1Dbl0ConsAssert.The<A> attestDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func) {
		return new LDbl1Dbl0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> attestBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFltConsumer.LFlt1Flt0Cons> LFlt1Flt0ConsAssert.The<A> attestFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func) {
		return new LFlt1Flt0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> attestBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer.LInt1Int0Cons> LInt1Int0ConsAssert.The<A> attestInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func) {
		return new LInt1Int0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> attestBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer.LLong1Long0Cons> LLong1Long0ConsAssert.The<A> attestLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func) {
		return new LLong1Long0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> attestBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiSrtConsumer.LSrt1Srt0Cons> LSrt1Srt0ConsAssert.The<A> attestSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func) {
		return new LSrt1Srt0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> attestBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolIntConsumer.LIntBoolCons> LIntBoolConsAssert.The<A> attestIntBoolCons(LBoolIntConsumer.LIntBoolCons func) {
		return new LIntBoolConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> attestByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteIntConsumer.LIntByteCons> LIntByteConsAssert.The<A> attestIntByteCons(LByteIntConsumer.LIntByteCons func) {
		return new LIntByteConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> attestCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharIntConsumer.LIntCharCons> LIntCharConsAssert.The<A> attestIntCharCons(LCharIntConsumer.LIntCharCons func) {
		return new LIntCharConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> attestDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblIntConsumer.LIntDblCons> LIntDblConsAssert.The<A> attestIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> attestFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltIntConsumer.LIntFltCons> LIntFltConsAssert.The<A> attestIntFltCons(LFltIntConsumer.LIntFltCons func) {
		return new LIntFltConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> attestLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongIntConsumer.LIntLongCons> LIntLongConsAssert.The<A> attestIntLongCons(LLongIntConsumer.LIntLongCons func) {
		return new LIntLongConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> attestSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtIntConsumer.LIntSrtCons> LIntSrtConsAssert.The<A> attestIntSrtCons(LSrtIntConsumer.LIntSrtCons func) {
		return new LIntSrtConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2>, T1, T2> LObj0Bool2Obj1ConsAssert.The<A, T1, T2> attestObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func) {
		return new LObj0Bool2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1>, T2, T1> LObj1Obj0Bool2ConsAssert.The<A, T2, T1> attestObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func) {
		return new LObj1Obj0Bool2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1>, T2, T1> LObj1Bool2Obj0ConsAssert.The<A, T2, T1> attestObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func) {
		return new LObj1Bool2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2>, T1, T2> LBool2Obj0Obj1ConsAssert.The<A, T1, T2> attestBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func) {
		return new LBool2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1>, T2, T1> LBool2Obj1Obj0ConsAssert.The<A, T2, T1> attestBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func) {
		return new LBool2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> attestBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, T1, T2> LObj0Byte2Obj1ConsAssert.The<A, T1, T2> attestObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1>, T2, T1> LObj1Obj0Byte2ConsAssert.The<A, T2, T1> attestObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func) {
		return new LObj1Obj0Byte2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1>, T2, T1> LObj1Byte2Obj0ConsAssert.The<A, T2, T1> attestObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2>, T1, T2> LByte2Obj0Obj1ConsAssert.The<A, T1, T2> attestByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func) {
		return new LByte2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1>, T2, T1> LByte2Obj1Obj0ConsAssert.The<A, T2, T1> attestByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func) {
		return new LByte2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2>, T1, T2> LObj0Char2Obj1ConsAssert.The<A, T1, T2> attestObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func) {
		return new LObj0Char2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1>, T2, T1> LObj1Obj0Char2ConsAssert.The<A, T2, T1> attestObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func) {
		return new LObj1Obj0Char2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1>, T2, T1> LObj1Char2Obj0ConsAssert.The<A, T2, T1> attestObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func) {
		return new LObj1Char2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2>, T1, T2> LChar2Obj0Obj1ConsAssert.The<A, T1, T2> attestChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func) {
		return new LChar2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1>, T2, T1> LChar2Obj1Obj0ConsAssert.The<A, T2, T1> attestChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func) {
		return new LChar2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> attestBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2>, T1, T2> LObj0Dbl2Obj1ConsAssert.The<A, T1, T2> attestObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func) {
		return new LObj0Dbl2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1>, T2, T1> LObj1Obj0Dbl2ConsAssert.The<A, T2, T1> attestObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func) {
		return new LObj1Obj0Dbl2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1>, T2, T1> LObj1Dbl2Obj0ConsAssert.The<A, T2, T1> attestObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func) {
		return new LObj1Dbl2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2>, T1, T2> LDbl2Obj0Obj1ConsAssert.The<A, T1, T2> attestDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func) {
		return new LDbl2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1>, T2, T1> LDbl2Obj1Obj0ConsAssert.The<A, T2, T1> attestDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func) {
		return new LDbl2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2>, T1, T2> LObj0Flt2Obj1ConsAssert.The<A, T1, T2> attestObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func) {
		return new LObj0Flt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1>, T2, T1> LObj1Obj0Flt2ConsAssert.The<A, T2, T1> attestObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func) {
		return new LObj1Obj0Flt2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1>, T2, T1> LObj1Flt2Obj0ConsAssert.The<A, T2, T1> attestObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func) {
		return new LObj1Flt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2>, T1, T2> LFlt2Obj0Obj1ConsAssert.The<A, T1, T2> attestFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func) {
		return new LFlt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1>, T2, T1> LFlt2Obj1Obj0ConsAssert.The<A, T2, T1> attestFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func) {
		return new LFlt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> attestBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1>, T2, T1> LObj1Obj0Int2ConsAssert.The<A, T2, T1> attestObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func) {
		return new LObj1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2>, T1, T2> LInt2Obj0Obj1ConsAssert.The<A, T1, T2> attestInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func) {
		return new LInt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1>, T2, T1> LInt2Obj1Obj0ConsAssert.The<A, T2, T1> attestInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func) {
		return new LInt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2>, T1, T2> LObj0Long2Obj1ConsAssert.The<A, T1, T2> attestObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func) {
		return new LObj0Long2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1>, T2, T1> LObj1Obj0Long2ConsAssert.The<A, T2, T1> attestObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func) {
		return new LObj1Obj0Long2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1>, T2, T1> LObj1Long2Obj0ConsAssert.The<A, T2, T1> attestObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func) {
		return new LObj1Long2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2>, T1, T2> LLong2Obj0Obj1ConsAssert.The<A, T1, T2> attestLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func) {
		return new LLong2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1>, T2, T1> LLong2Obj1Obj0ConsAssert.The<A, T2, T1> attestLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func) {
		return new LLong2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> attestBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2>, T1, T2> LObj0Srt2Obj1ConsAssert.The<A, T1, T2> attestObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func) {
		return new LObj0Srt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1>, T2, T1> LObj1Obj0Srt2ConsAssert.The<A, T2, T1> attestObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func) {
		return new LObj1Obj0Srt2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1>, T2, T1> LObj1Srt2Obj0ConsAssert.The<A, T2, T1> attestObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func) {
		return new LObj1Srt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2>, T1, T2> LSrt2Obj0Obj1ConsAssert.The<A, T1, T2> attestSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func) {
		return new LSrt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1>, T2, T1> LSrt2Obj1Obj0ConsAssert.The<A, T2, T1> attestSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func) {
		return new LSrt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer<T>, T> LObjBiLongConsumerAssert.The<A, T> attestObjBiLongCons(LObjBiLongConsumer<T> func) {
		return new LObjBiLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer.LObj0Long2Long1Cons<T>, T> LObj0Long2Long1ConsAssert.The<A, T> attestObj0Long2Long1Cons(LObjBiLongConsumer.LObj0Long2Long1Cons<T> func) {
		return new LObj0Long2Long1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer.LLong1Obj0Long2Cons<T>, T> LLong1Obj0Long2ConsAssert.The<A, T> attestLong1Obj0Long2Cons(LObjBiLongConsumer.LLong1Obj0Long2Cons<T> func) {
		return new LLong1Obj0Long2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer.LLong1Long2Obj0Cons<T>, T> LLong1Long2Obj0ConsAssert.The<A, T> attestLong1Long2Obj0Cons(LObjBiLongConsumer.LLong1Long2Obj0Cons<T> func) {
		return new LLong1Long2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer.LLong2Obj0Long1Cons<T>, T> LLong2Obj0Long1ConsAssert.The<A, T> attestLong2Obj0Long1Cons(LObjBiLongConsumer.LLong2Obj0Long1Cons<T> func) {
		return new LLong2Obj0Long1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBiLongConsumer.LBiLong1Obj0Cons<T>, T> LBiLong1Obj0ConsAssert.The<A, T> attestBiLong1Obj0Cons(LObjBiLongConsumer.LBiLong1Obj0Cons<T> func) {
		return new LBiLong1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> attestObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer.LBoolObjCons<T>, T> LBoolObjConsAssert.The<A, T> attestBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func) {
		return new LBoolObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> attestObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer.LByteObjCons<T>, T> LByteObjConsAssert.The<A, T> attestByteObjCons(LObjByteConsumer.LByteObjCons<T> func) {
		return new LByteObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> attestObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer.LCharObjCons<T>, T> LCharObjConsAssert.The<A, T> attestCharObjCons(LObjCharConsumer.LCharObjCons<T> func) {
		return new LCharObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> attestObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDblConsumer.LDblObjCons<T>, T> LDblObjConsAssert.The<A, T> attestDblObjCons(LObjDblConsumer.LDblObjCons<T> func) {
		return new LDblObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> attestObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFltConsumer.LFltObjCons<T>, T> LFltObjConsAssert.The<A, T> attestFltObjCons(LObjFltConsumer.LFltObjCons<T> func) {
		return new LFltObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> attestObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer.LIntObjCons<T>, T> LIntObjConsAssert.The<A, T> attestIntObjCons(LObjIntConsumer.LIntObjCons<T> func) {
		return new LIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> attestObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer.LLongObjCons<T>, T> LLongObjConsAssert.The<A, T> attestLongObjCons(LObjLongConsumer.LLongObjCons<T> func) {
		return new LLongObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> attestObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjSrtConsumer.LSrtObjCons<T>, T> LSrtObjConsAssert.The<A, T> attestSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func) {
		return new LSrtObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> attestTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LObjBoolIntCons<T>, T> LObjBoolIntConsAssert.The<A, T> attestObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func) {
		return new LObjBoolIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LIntObjBoolCons<T>, T> LIntObjBoolConsAssert.The<A, T> attestIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func) {
		return new LIntObjBoolConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LIntBoolObjCons<T>, T> LIntBoolObjConsAssert.The<A, T> attestIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func) {
		return new LIntBoolObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LBoolObjIntCons<T>, T> LBoolObjIntConsAssert.The<A, T> attestBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func) {
		return new LBoolObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LBoolIntObjCons<T>, T> LBoolIntObjConsAssert.The<A, T> attestBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func) {
		return new LBoolIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> attestTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LObjByteIntCons<T>, T> LObjByteIntConsAssert.The<A, T> attestObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func) {
		return new LObjByteIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LIntObjByteCons<T>, T> LIntObjByteConsAssert.The<A, T> attestIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func) {
		return new LIntObjByteConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LIntByteObjCons<T>, T> LIntByteObjConsAssert.The<A, T> attestIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func) {
		return new LIntByteObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LByteObjIntCons<T>, T> LByteObjIntConsAssert.The<A, T> attestByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func) {
		return new LByteObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LByteIntObjCons<T>, T> LByteIntObjConsAssert.The<A, T> attestByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func) {
		return new LByteIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> attestTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LObjCharIntCons<T>, T> LObjCharIntConsAssert.The<A, T> attestObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func) {
		return new LObjCharIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LIntObjCharCons<T>, T> LIntObjCharConsAssert.The<A, T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LIntCharObjCons<T>, T> LIntCharObjConsAssert.The<A, T> attestIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func) {
		return new LIntCharObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LCharObjIntCons<T>, T> LCharObjIntConsAssert.The<A, T> attestCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func) {
		return new LCharObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LCharIntObjCons<T>, T> LCharIntObjConsAssert.The<A, T> attestCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func) {
		return new LCharIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> attestTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LInt1BiObj2Cons<T1, T2>, T1, T2> LInt1BiObj2ConsAssert.The<A, T1, T2> attestInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func) {
		return new LInt1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LInt1Obj2Obj0Cons<T2, T1>, T2, T1> LInt1Obj2Obj0ConsAssert.The<A, T2, T1> attestInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func) {
		return new LInt1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LObj2Int1Obj0Cons<T2, T1>, T2, T1> LObj2Int1Obj0ConsAssert.The<A, T2, T1> attestObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func) {
		return new LObj2Int1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> attestTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LObjDblIntCons<T>, T> LObjDblIntConsAssert.The<A, T> attestObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func) {
		return new LObjDblIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LIntObjDblCons<T>, T> LIntObjDblConsAssert.The<A, T> attestIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func) {
		return new LIntObjDblConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LIntDblObjCons<T>, T> LIntDblObjConsAssert.The<A, T> attestIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func) {
		return new LIntDblObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LDblObjIntCons<T>, T> LDblObjIntConsAssert.The<A, T> attestDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func) {
		return new LDblObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LDblIntObjCons<T>, T> LDblIntObjConsAssert.The<A, T> attestDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func) {
		return new LDblIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> attestTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LObjFltIntCons<T>, T> LObjFltIntConsAssert.The<A, T> attestObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func) {
		return new LObjFltIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LIntObjFltCons<T>, T> LIntObjFltConsAssert.The<A, T> attestIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func) {
		return new LIntObjFltConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LIntFltObjCons<T>, T> LIntFltObjConsAssert.The<A, T> attestIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func) {
		return new LIntFltObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LFltObjIntCons<T>, T> LFltObjIntConsAssert.The<A, T> attestFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func) {
		return new LFltObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LFltIntObjCons<T>, T> LFltIntObjConsAssert.The<A, T> attestFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func) {
		return new LFltIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> attestTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LObj0Int2Int1Cons<T>, T> LObj0Int2Int1ConsAssert.The<A, T> attestObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func) {
		return new LObj0Int2Int1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt1Obj0Int2Cons<T>, T> LInt1Obj0Int2ConsAssert.The<A, T> attestInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt1Int2Obj0Cons<T>, T> LInt1Int2Obj0ConsAssert.The<A, T> attestInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func) {
		return new LInt1Int2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> LInt2Obj0Int1ConsAssert.The<A, T> attestInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LBiInt1Obj0Cons<T>, T> LBiInt1Obj0ConsAssert.The<A, T> attestBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func) {
		return new LBiInt1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> attestTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LObjLongIntCons<T>, T> LObjLongIntConsAssert.The<A, T> attestObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func) {
		return new LObjLongIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LIntObjLongCons<T>, T> LIntObjLongConsAssert.The<A, T> attestIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func) {
		return new LIntObjLongConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LIntLongObjCons<T>, T> LIntLongObjConsAssert.The<A, T> attestIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func) {
		return new LIntLongObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LLongObjIntCons<T>, T> LLongObjIntConsAssert.The<A, T> attestLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func) {
		return new LLongObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LLongIntObjCons<T>, T> LLongIntObjConsAssert.The<A, T> attestLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func) {
		return new LLongIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> attestTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LObjSrtIntCons<T>, T> LObjSrtIntConsAssert.The<A, T> attestObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func) {
		return new LObjSrtIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LIntObjSrtCons<T>, T> LIntObjSrtConsAssert.The<A, T> attestIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func) {
		return new LIntObjSrtConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LIntSrtObjCons<T>, T> LIntSrtObjConsAssert.The<A, T> attestIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func) {
		return new LIntSrtObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LSrtObjIntCons<T>, T> LSrtObjIntConsAssert.The<A, T> attestSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func) {
		return new LSrtObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LSrtIntObjCons<T>, T> LSrtIntObjConsAssert.The<A, T> attestSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func) {
		return new LSrtIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> attestTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriByteConsumer> LTriByteConsumerAssert.The<A> attestTriByteCons(LTriByteConsumer func) {
		return new LTriByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriCharConsumer> LTriCharConsumerAssert.The<A> attestTriCharCons(LTriCharConsumer func) {
		return new LTriCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriDblConsumer> LTriDblConsumerAssert.The<A> attestTriDblCons(LTriDblConsumer func) {
		return new LTriDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriFltConsumer> LTriFltConsumerAssert.The<A> attestTriFltCons(LTriFltConsumer func) {
		return new LTriFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriIntConsumer> LTriIntConsumerAssert.The<A> attestTriIntCons(LTriIntConsumer func) {
		return new LTriIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriLongConsumer> LTriLongConsumerAssert.The<A> attestTriLongCons(LTriLongConsumer func) {
		return new LTriLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriSrtConsumer> LTriSrtConsumerAssert.The<A> attestTriSrtCons(LTriSrtConsumer func) {
		return new LTriSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBinaryOperator<T>, RS extends Assert<RS, T>, T> LBinaryOperatorAssert.The<A, RS, T> attestBinaryOp(LBinaryOperator<T> func) {
		return new LBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperator, RS extends AbstractByteAssert<RS>> LByteBinaryOperatorAssert.The<A, RS> attestByteBinaryOp(LByteBinaryOperator func) {
		return new LByteBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperator, RS extends AbstractCharacterAssert<RS>> LCharBinaryOperatorAssert.The<A, RS> attestCharBinaryOp(LCharBinaryOperator func) {
		return new LCharBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblBinaryOperator, RS extends AbstractDoubleAssert<RS>> LDblBinaryOperatorAssert.The<A, RS> attestDblBinaryOp(LDblBinaryOperator func) {
		return new LDblBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltBinaryOperator, RS extends AbstractFloatAssert<RS>> LFltBinaryOperatorAssert.The<A, RS> attestFltBinaryOp(LFltBinaryOperator func) {
		return new LFltBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperator, RS extends AbstractIntegerAssert<RS>> LIntBinaryOperatorAssert.The<A, RS> attestIntBinaryOp(LIntBinaryOperator func) {
		return new LIntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalBinaryOperatorAssert.The<A, RS> attestLogicalBinaryOp(LLogicalBinaryOperator func) {
		return new LLogicalBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperator, RS extends AbstractLongAssert<RS>> LLongBinaryOperatorAssert.The<A, RS> attestLongBinaryOp(LLongBinaryOperator func) {
		return new LLongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtBinaryOperator, RS extends AbstractShortAssert<RS>> LSrtBinaryOperatorAssert.The<A, RS> attestSrtBinaryOp(LSrtBinaryOperator func) {
		return new LSrtBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteTernaryOperator, RS extends AbstractByteAssert<RS>> LByteTernaryOperatorAssert.The<A, RS> attestByteTernaryOp(LByteTernaryOperator func) {
		return new LByteTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharTernaryOperator, RS extends AbstractCharacterAssert<RS>> LCharTernaryOperatorAssert.The<A, RS> attestCharTernaryOp(LCharTernaryOperator func) {
		return new LCharTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblTernaryOperator, RS extends AbstractDoubleAssert<RS>> LDblTernaryOperatorAssert.The<A, RS> attestDblTernaryOp(LDblTernaryOperator func) {
		return new LDblTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltTernaryOperator, RS extends AbstractFloatAssert<RS>> LFltTernaryOperatorAssert.The<A, RS> attestFltTernaryOp(LFltTernaryOperator func) {
		return new LFltTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntTernaryOperator, RS extends AbstractIntegerAssert<RS>> LIntTernaryOperatorAssert.The<A, RS> attestIntTernaryOp(LIntTernaryOperator func) {
		return new LIntTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalTernaryOperatorAssert.The<A, RS> attestLogicalTernaryOp(LLogicalTernaryOperator func) {
		return new LLogicalTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongTernaryOperator, RS extends AbstractLongAssert<RS>> LLongTernaryOperatorAssert.The<A, RS> attestLongTernaryOp(LLongTernaryOperator func) {
		return new LLongTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtTernaryOperator, RS extends AbstractShortAssert<RS>> LSrtTernaryOperatorAssert.The<A, RS> attestSrtTernaryOp(LSrtTernaryOperator func) {
		return new LSrtTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperator<T>, RS extends Assert<RS, T>, T> LTernaryOperatorAssert.The<A, RS, T> attestTernaryOp(LTernaryOperator<T> func) {
		return new LTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperator, RS extends AbstractByteAssert<RS>> LByteUnaryOperatorAssert.The<A, RS> attestByteUnaryOp(LByteUnaryOperator func) {
		return new LByteUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperator, RS extends AbstractCharacterAssert<RS>> LCharUnaryOperatorAssert.The<A, RS> attestCharUnaryOp(LCharUnaryOperator func) {
		return new LCharUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblUnaryOperator, RS extends AbstractDoubleAssert<RS>> LDblUnaryOperatorAssert.The<A, RS> attestDblUnaryOp(LDblUnaryOperator func) {
		return new LDblUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> LFltUnaryOperatorAssert.The<A, RS> attestFltUnaryOp(LFltUnaryOperator func) {
		return new LFltUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperator, RS extends AbstractIntegerAssert<RS>> LIntUnaryOperatorAssert.The<A, RS> attestIntUnaryOp(LIntUnaryOperator func) {
		return new LIntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperator, RS extends AbstractBooleanAssert<RS>> LLogicalOperatorAssert.The<A, RS> attestLogicalOp(LLogicalOperator func) {
		return new LLogicalOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperator, RS extends AbstractLongAssert<RS>> LLongUnaryOperatorAssert.The<A, RS> attestLongUnaryOp(LLongUnaryOperator func) {
		return new LLongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtUnaryOperator, RS extends AbstractShortAssert<RS>> LSrtUnaryOperatorAssert.The<A, RS> attestSrtUnaryOp(LSrtUnaryOperator func) {
		return new LSrtUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperator<T>, RS extends Assert<RS, T>, T> LUnaryOperatorAssert.The<A, RS, T> attestUnaryOp(LUnaryOperator<T> func) {
		return new LUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiFunctionAssert.The<A, RS, T1, T2, R> attestBiFunc(LBiFunction<T1, T2, R> func) {
		return new LBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction.LObj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func) {
		return new LObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunction<T, R>, RS extends Assert<RS, R>, T, R> LFunctionAssert.The<A, RS, T, R> attestFunc(LFunction<T, R> func) {
		return new LFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuadFunction<T1, T2, T3, T4, R>, RS extends Assert<RS, R>, T1, T2, T3, T4, R> LQuadFunctionAssert.The<A, RS, T1, T2, T3, T4, R> attestQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) {
		return new LQuadFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuintFunction<T1, T2, T3, T4, T5, R>, RS extends Assert<RS, R>, T1, T2, T3, T4, T5, R> LQuintFunctionAssert.The<A, RS, T1, T2, T3, T4, T5, R> attestQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func) {
		return new LQuintFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends Assert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, RS, T1, T2, T3, R> attestTriFunc(LTriFunction<T1, T2, T3, R> func) {
		return new LTriFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R>, RS extends Assert<RS, R>, T1, T3, T2, R> LObj0Obj2Obj1FuncAssert.The<A, RS, T1, T3, T2, R> attestObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func) {
		return new LObj0Obj2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj1BiObj2Func<T2, T1, T3, R>, RS extends Assert<RS, R>, T2, T1, T3, R> LObj1BiObj2FuncAssert.The<A, RS, T2, T1, T3, R> attestObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func) {
		return new LObj1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R>, RS extends Assert<RS, R>, T2, T3, T1, R> LObj1Obj2Obj0FuncAssert.The<A, RS, T2, T3, T1, R> attestObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func) {
		return new LObj1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R>, RS extends Assert<RS, R>, T3, T1, T2, R> LObj2Obj0Obj1FuncAssert.The<A, RS, T3, T1, T2, R> attestObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func) {
		return new LObj2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R>, RS extends Assert<RS, R>, T3, T2, T1, R> LBiObj1Obj0FuncAssert.The<A, RS, T3, T2, T1, R> attestBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func) {
		return new LBiObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunction, RS extends AbstractByteAssert<RS>> LBoolToByteFunctionAssert.The<A, RS> attestBoolToByteFunc(LBoolToByteFunction func) {
		return new LBoolToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunction, RS extends AbstractCharacterAssert<RS>> LBoolToCharFunctionAssert.The<A, RS> attestBoolToCharFunc(LBoolToCharFunction func) {
		return new LBoolToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDblFunction, RS extends AbstractDoubleAssert<RS>> LBoolToDblFunctionAssert.The<A, RS> attestBoolToDblFunc(LBoolToDblFunction func) {
		return new LBoolToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFltFunction, RS extends AbstractFloatAssert<RS>> LBoolToFltFunctionAssert.The<A, RS> attestBoolToFltFunc(LBoolToFltFunction func) {
		return new LBoolToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunction, RS extends AbstractIntegerAssert<RS>> LBoolToIntFunctionAssert.The<A, RS> attestBoolToIntFunc(LBoolToIntFunction func) {
		return new LBoolToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunction, RS extends AbstractLongAssert<RS>> LBoolToLongFunctionAssert.The<A, RS> attestBoolToLongFunc(LBoolToLongFunction func) {
		return new LBoolToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToSrtFunction, RS extends AbstractShortAssert<RS>> LBoolToSrtFunctionAssert.The<A, RS> attestBoolToSrtFunc(LBoolToSrtFunction func) {
		return new LBoolToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunction, RS extends AbstractCharacterAssert<RS>> LByteToCharFunctionAssert.The<A, RS> attestByteToCharFunc(LByteToCharFunction func) {
		return new LByteToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDblFunction, RS extends AbstractDoubleAssert<RS>> LByteToDblFunctionAssert.The<A, RS> attestByteToDblFunc(LByteToDblFunction func) {
		return new LByteToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFltFunction, RS extends AbstractFloatAssert<RS>> LByteToFltFunctionAssert.The<A, RS> attestByteToFltFunc(LByteToFltFunction func) {
		return new LByteToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunction, RS extends AbstractIntegerAssert<RS>> LByteToIntFunctionAssert.The<A, RS> attestByteToIntFunc(LByteToIntFunction func) {
		return new LByteToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunction, RS extends AbstractLongAssert<RS>> LByteToLongFunctionAssert.The<A, RS> attestByteToLongFunc(LByteToLongFunction func) {
		return new LByteToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToSrtFunction, RS extends AbstractShortAssert<RS>> LByteToSrtFunctionAssert.The<A, RS> attestByteToSrtFunc(LByteToSrtFunction func) {
		return new LByteToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunction, RS extends AbstractByteAssert<RS>> LCharToByteFunctionAssert.The<A, RS> attestCharToByteFunc(LCharToByteFunction func) {
		return new LCharToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDblFunction, RS extends AbstractDoubleAssert<RS>> LCharToDblFunctionAssert.The<A, RS> attestCharToDblFunc(LCharToDblFunction func) {
		return new LCharToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFltFunction, RS extends AbstractFloatAssert<RS>> LCharToFltFunctionAssert.The<A, RS> attestCharToFltFunc(LCharToFltFunction func) {
		return new LCharToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunction, RS extends AbstractIntegerAssert<RS>> LCharToIntFunctionAssert.The<A, RS> attestCharToIntFunc(LCharToIntFunction func) {
		return new LCharToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunction, RS extends AbstractLongAssert<RS>> LCharToLongFunctionAssert.The<A, RS> attestCharToLongFunc(LCharToLongFunction func) {
		return new LCharToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToSrtFunction, RS extends AbstractShortAssert<RS>> LCharToSrtFunctionAssert.The<A, RS> attestCharToSrtFunc(LCharToSrtFunction func) {
		return new LCharToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToByteFunction, RS extends AbstractByteAssert<RS>> LDblToByteFunctionAssert.The<A, RS> attestDblToByteFunc(LDblToByteFunction func) {
		return new LDblToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToCharFunction, RS extends AbstractCharacterAssert<RS>> LDblToCharFunctionAssert.The<A, RS> attestDblToCharFunc(LDblToCharFunction func) {
		return new LDblToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToFltFunction, RS extends AbstractFloatAssert<RS>> LDblToFltFunctionAssert.The<A, RS> attestDblToFltFunc(LDblToFltFunction func) {
		return new LDblToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToIntFunction, RS extends AbstractIntegerAssert<RS>> LDblToIntFunctionAssert.The<A, RS> attestDblToIntFunc(LDblToIntFunction func) {
		return new LDblToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToLongFunction, RS extends AbstractLongAssert<RS>> LDblToLongFunctionAssert.The<A, RS> attestDblToLongFunc(LDblToLongFunction func) {
		return new LDblToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToSrtFunction, RS extends AbstractShortAssert<RS>> LDblToSrtFunctionAssert.The<A, RS> attestDblToSrtFunc(LDblToSrtFunction func) {
		return new LDblToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToByteFunction, RS extends AbstractByteAssert<RS>> LFltToByteFunctionAssert.The<A, RS> attestFltToByteFunc(LFltToByteFunction func) {
		return new LFltToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToCharFunction, RS extends AbstractCharacterAssert<RS>> LFltToCharFunctionAssert.The<A, RS> attestFltToCharFunc(LFltToCharFunction func) {
		return new LFltToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToDblFunction, RS extends AbstractDoubleAssert<RS>> LFltToDblFunctionAssert.The<A, RS> attestFltToDblFunc(LFltToDblFunction func) {
		return new LFltToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToIntFunction, RS extends AbstractIntegerAssert<RS>> LFltToIntFunctionAssert.The<A, RS> attestFltToIntFunc(LFltToIntFunction func) {
		return new LFltToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToLongFunction, RS extends AbstractLongAssert<RS>> LFltToLongFunctionAssert.The<A, RS> attestFltToLongFunc(LFltToLongFunction func) {
		return new LFltToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToSrtFunction, RS extends AbstractShortAssert<RS>> LFltToSrtFunctionAssert.The<A, RS> attestFltToSrtFunc(LFltToSrtFunction func) {
		return new LFltToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunction, RS extends AbstractByteAssert<RS>> LIntToByteFunctionAssert.The<A, RS> attestIntToByteFunc(LIntToByteFunction func) {
		return new LIntToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunction, RS extends AbstractCharacterAssert<RS>> LIntToCharFunctionAssert.The<A, RS> attestIntToCharFunc(LIntToCharFunction func) {
		return new LIntToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDblFunction, RS extends AbstractDoubleAssert<RS>> LIntToDblFunctionAssert.The<A, RS> attestIntToDblFunc(LIntToDblFunction func) {
		return new LIntToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFltFunction, RS extends AbstractFloatAssert<RS>> LIntToFltFunctionAssert.The<A, RS> attestIntToFltFunc(LIntToFltFunction func) {
		return new LIntToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunction, RS extends AbstractLongAssert<RS>> LIntToLongFunctionAssert.The<A, RS> attestIntToLongFunc(LIntToLongFunction func) {
		return new LIntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToSrtFunction, RS extends AbstractShortAssert<RS>> LIntToSrtFunctionAssert.The<A, RS> attestIntToSrtFunc(LIntToSrtFunction func) {
		return new LIntToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunction, RS extends AbstractByteAssert<RS>> LLongToByteFunctionAssert.The<A, RS> attestLongToByteFunc(LLongToByteFunction func) {
		return new LLongToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunction, RS extends AbstractCharacterAssert<RS>> LLongToCharFunctionAssert.The<A, RS> attestLongToCharFunc(LLongToCharFunction func) {
		return new LLongToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDblFunction, RS extends AbstractDoubleAssert<RS>> LLongToDblFunctionAssert.The<A, RS> attestLongToDblFunc(LLongToDblFunction func) {
		return new LLongToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFltFunction, RS extends AbstractFloatAssert<RS>> LLongToFltFunctionAssert.The<A, RS> attestLongToFltFunc(LLongToFltFunction func) {
		return new LLongToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunction, RS extends AbstractIntegerAssert<RS>> LLongToIntFunctionAssert.The<A, RS> attestLongToIntFunc(LLongToIntFunction func) {
		return new LLongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToSrtFunction, RS extends AbstractShortAssert<RS>> LLongToSrtFunctionAssert.The<A, RS> attestLongToSrtFunc(LLongToSrtFunction func) {
		return new LLongToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToByteFunction, RS extends AbstractByteAssert<RS>> LSrtToByteFunctionAssert.The<A, RS> attestSrtToByteFunc(LSrtToByteFunction func) {
		return new LSrtToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToCharFunction, RS extends AbstractCharacterAssert<RS>> LSrtToCharFunctionAssert.The<A, RS> attestSrtToCharFunc(LSrtToCharFunction func) {
		return new LSrtToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToDblFunction, RS extends AbstractDoubleAssert<RS>> LSrtToDblFunctionAssert.The<A, RS> attestSrtToDblFunc(LSrtToDblFunction func) {
		return new LSrtToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToFltFunction, RS extends AbstractFloatAssert<RS>> LSrtToFltFunctionAssert.The<A, RS> attestSrtToFltFunc(LSrtToFltFunction func) {
		return new LSrtToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToIntFunction, RS extends AbstractIntegerAssert<RS>> LSrtToIntFunctionAssert.The<A, RS> attestSrtToIntFunc(LSrtToIntFunction func) {
		return new LSrtToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToLongFunction, RS extends AbstractLongAssert<RS>> LSrtToLongFunctionAssert.The<A, RS> attestSrtToLongFunc(LSrtToLongFunction func) {
		return new LSrtToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction<R>, RS extends Assert<RS, R>, R> LBiBoolFunctionAssert.The<A, RS, R> attestBiBoolFunc(LBiBoolFunction<R> func) {
		return new LBiBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction.LBool1Bool0Func<R>, RS extends Assert<RS, R>, R> LBool1Bool0FuncAssert.The<A, RS, R> attestBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func) {
		return new LBool1Bool0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction<R>, RS extends Assert<RS, R>, R> LBiByteFunctionAssert.The<A, RS, R> attestBiByteFunc(LBiByteFunction<R> func) {
		return new LBiByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction.LByte1Byte0Func<R>, RS extends Assert<RS, R>, R> LByte1Byte0FuncAssert.The<A, RS, R> attestByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func) {
		return new LByte1Byte0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction<R>, RS extends Assert<RS, R>, R> LBiCharFunctionAssert.The<A, RS, R> attestBiCharFunc(LBiCharFunction<R> func) {
		return new LBiCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction.LChar1Char0Func<R>, RS extends Assert<RS, R>, R> LChar1Char0FuncAssert.The<A, RS, R> attestChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func) {
		return new LChar1Char0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblFunction<R>, RS extends Assert<RS, R>, R> LBiDblFunctionAssert.The<A, RS, R> attestBiDblFunc(LBiDblFunction<R> func) {
		return new LBiDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblFunction.LDbl1Dbl0Func<R>, RS extends Assert<RS, R>, R> LDbl1Dbl0FuncAssert.The<A, RS, R> attestDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func) {
		return new LDbl1Dbl0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltFunction<R>, RS extends Assert<RS, R>, R> LBiFltFunctionAssert.The<A, RS, R> attestBiFltFunc(LBiFltFunction<R> func) {
		return new LBiFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltFunction.LFlt1Flt0Func<R>, RS extends Assert<RS, R>, R> LFlt1Flt0FuncAssert.The<A, RS, R> attestFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func) {
		return new LFlt1Flt0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction<R>, RS extends Assert<RS, R>, R> LBiIntFunctionAssert.The<A, RS, R> attestBiIntFunc(LBiIntFunction<R> func) {
		return new LBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction.LInt1Int0Func<R>, RS extends Assert<RS, R>, R> LInt1Int0FuncAssert.The<A, RS, R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) {
		return new LInt1Int0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction<R>, RS extends Assert<RS, R>, R> LBiLongFunctionAssert.The<A, RS, R> attestBiLongFunc(LBiLongFunction<R> func) {
		return new LBiLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction.LLong1Long0Func<R>, RS extends Assert<RS, R>, R> LLong1Long0FuncAssert.The<A, RS, R> attestLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func) {
		return new LLong1Long0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, RS, T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Bool2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func) {
		return new LObj0Bool2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Bool2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func) {
		return new LObj1Obj0Bool2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Bool2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func) {
		return new LObj1Bool2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBool2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func) {
		return new LBool2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LBool2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func) {
		return new LBool2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, RS, T1, T2, R> attestBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) {
		return new LBiObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Byte2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func) {
		return new LObj0Byte2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Byte2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func) {
		return new LObj1Obj0Byte2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Byte2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func) {
		return new LObj1Byte2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LByte2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func) {
		return new LByte2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LByte2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func) {
		return new LByte2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, RS, T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Char2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func) {
		return new LObj0Char2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Char2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func) {
		return new LObj1Obj0Char2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Char2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func) {
		return new LObj1Char2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LChar2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func) {
		return new LChar2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LChar2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func) {
		return new LChar2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, RS, T1, T2, R> attestBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) {
		return new LBiObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Dbl2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func) {
		return new LObj0Dbl2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Dbl2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func) {
		return new LObj1Obj0Dbl2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Dbl2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func) {
		return new LObj1Dbl2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LDbl2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func) {
		return new LDbl2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LDbl2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func) {
		return new LDbl2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, RS, T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) {
		return new LBiObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Flt2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func) {
		return new LObj0Flt2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Flt2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func) {
		return new LObj1Obj0Flt2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Flt2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func) {
		return new LObj1Flt2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LFlt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func) {
		return new LFlt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LFlt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func) {
		return new LFlt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, RS, T1, T2, R> attestBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) {
		return new LBiObjIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Int2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func) {
		return new LObj1Obj0Int2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LInt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func) {
		return new LInt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LInt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func) {
		return new LInt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, RS, T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Long2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func) {
		return new LObj0Long2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Long2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func) {
		return new LObj1Obj0Long2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Long2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func) {
		return new LObj1Long2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LLong2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func) {
		return new LLong2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LLong2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func) {
		return new LLong2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, RS, T1, T2, R> attestBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) {
		return new LBiObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Srt2Obj1FuncAssert.The<A, RS, T1, T2, R> attestObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func) {
		return new LObj0Srt2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Srt2FuncAssert.The<A, RS, T2, T1, R> attestObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func) {
		return new LObj1Obj0Srt2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Srt2Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func) {
		return new LObj1Srt2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LSrt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> attestSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func) {
		return new LSrt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LSrt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> attestSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func) {
		return new LSrt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtFunction<R>, RS extends Assert<RS, R>, R> LBiSrtFunctionAssert.The<A, RS, R> attestBiSrtFunc(LBiSrtFunction<R> func) {
		return new LBiSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtFunction.LSrt1Srt0Func<R>, RS extends Assert<RS, R>, R> LSrt1Srt0FuncAssert.The<A, RS, R> attestSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func) {
		return new LSrt1Srt0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunction<R>, RS extends Assert<RS, R>, R> LBoolFunctionAssert.The<A, RS, R> attestBoolFunc(LBoolFunction<R> func) {
		return new LBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunction<R>, RS extends Assert<RS, R>, R> LByteFunctionAssert.The<A, RS, R> attestByteFunc(LByteFunction<R> func) {
		return new LByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunction<R>, RS extends Assert<RS, R>, R> LCharFunctionAssert.The<A, RS, R> attestCharFunc(LCharFunction<R> func) {
		return new LCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblFunction<R>, RS extends Assert<RS, R>, R> LDblFunctionAssert.The<A, RS, R> attestDblFunc(LDblFunction<R> func) {
		return new LDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltFunction<R>, RS extends Assert<RS, R>, R> LFltFunctionAssert.The<A, RS, R> attestFltFunc(LFltFunction<R> func) {
		return new LFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunction<R>, RS extends Assert<RS, R>, R> LIntFunctionAssert.The<A, RS, R> attestIntFunc(LIntFunction<R> func) {
		return new LIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunction<R>, RS extends Assert<RS, R>, R> LLongFunctionAssert.The<A, RS, R> attestLongFunc(LLongFunction<R> func) {
		return new LLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBiIntFunctionAssert.The<A, RS, T, R> attestObjBiIntFunc(LObjBiIntFunction<T, R> func) {
		return new LObjBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LObj0Int2Int1Func<T, R>, RS extends Assert<RS, R>, T, R> LObj0Int2Int1FuncAssert.The<A, RS, T, R> attestObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func) {
		return new LObj0Int2Int1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt1Obj0Int2Func<T, R>, RS extends Assert<RS, R>, T, R> LInt1Obj0Int2FuncAssert.The<A, RS, T, R> attestInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func) {
		return new LInt1Obj0Int2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt1Int2Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LInt1Int2Obj0FuncAssert.The<A, RS, T, R> attestInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func) {
		return new LInt1Int2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt2Obj0Int1Func<T, R>, RS extends Assert<RS, R>, T, R> LInt2Obj0Int1FuncAssert.The<A, RS, T, R> attestInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func) {
		return new LInt2Obj0Int1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LBiInt1Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LBiInt1Obj0FuncAssert.The<A, RS, T, R> attestBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func) {
		return new LBiInt1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBiLongFunctionAssert.The<A, RS, T, R> attestObjBiLongFunc(LObjBiLongFunction<T, R> func) {
		return new LObjBiLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction.LObj0Long2Long1Func<T, R>, RS extends Assert<RS, R>, T, R> LObj0Long2Long1FuncAssert.The<A, RS, T, R> attestObj0Long2Long1Func(LObjBiLongFunction.LObj0Long2Long1Func<T, R> func) {
		return new LObj0Long2Long1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction.LLong1Obj0Long2Func<T, R>, RS extends Assert<RS, R>, T, R> LLong1Obj0Long2FuncAssert.The<A, RS, T, R> attestLong1Obj0Long2Func(LObjBiLongFunction.LLong1Obj0Long2Func<T, R> func) {
		return new LLong1Obj0Long2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction.LLong1Long2Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LLong1Long2Obj0FuncAssert.The<A, RS, T, R> attestLong1Long2Obj0Func(LObjBiLongFunction.LLong1Long2Obj0Func<T, R> func) {
		return new LLong1Long2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction.LLong2Obj0Long1Func<T, R>, RS extends Assert<RS, R>, T, R> LLong2Obj0Long1FuncAssert.The<A, RS, T, R> attestLong2Obj0Long1Func(LObjBiLongFunction.LLong2Obj0Long1Func<T, R> func) {
		return new LLong2Obj0Long1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongFunction.LBiLong1Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LBiLong1Obj0FuncAssert.The<A, RS, T, R> attestBiLong1Obj0Func(LObjBiLongFunction.LBiLong1Obj0Func<T, R> func) {
		return new LBiLong1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolFunctionAssert.The<A, RS, T, R> attestObjBoolFunc(LObjBoolFunction<T, R> func) {
		return new LObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction.LBoolObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolObjFuncAssert.The<A, RS, T, R> attestBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func) {
		return new LBoolObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjByteFunctionAssert.The<A, RS, T, R> attestObjByteFunc(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction.LByteObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteObjFuncAssert.The<A, RS, T, R> attestByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func) {
		return new LByteObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjCharFunctionAssert.The<A, RS, T, R> attestObjCharFunc(LObjCharFunction<T, R> func) {
		return new LObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction.LCharObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharObjFuncAssert.The<A, RS, T, R> attestCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func) {
		return new LCharObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjDblFunctionAssert.The<A, RS, T, R> attestObjDblFunc(LObjDblFunction<T, R> func) {
		return new LObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblFunction.LDblObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblObjFuncAssert.The<A, RS, T, R> attestDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func) {
		return new LDblObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjFltFunctionAssert.The<A, RS, T, R> attestObjFltFunc(LObjFltFunction<T, R> func) {
		return new LObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltFunction.LFltObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltObjFuncAssert.The<A, RS, T, R> attestFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func) {
		return new LFltObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntBoolFunctionAssert.The<A, RS, T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func) {
		return new LObjIntBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LObjBoolIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolIntFuncAssert.The<A, RS, T, R> attestObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func) {
		return new LObjBoolIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LIntObjBoolFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjBoolFuncAssert.The<A, RS, T, R> attestIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func) {
		return new LIntObjBoolFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LIntBoolObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntBoolObjFuncAssert.The<A, RS, T, R> attestIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func) {
		return new LIntBoolObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LBoolObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolObjIntFuncAssert.The<A, RS, T, R> attestBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func) {
		return new LBoolObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LBoolIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolIntObjFuncAssert.The<A, RS, T, R> attestBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func) {
		return new LBoolIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntByteFunctionAssert.The<A, RS, T, R> attestObjIntByteFunc(LObjIntByteFunction<T, R> func) {
		return new LObjIntByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LObjByteIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjByteIntFuncAssert.The<A, RS, T, R> attestObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func) {
		return new LObjByteIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LIntObjByteFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjByteFuncAssert.The<A, RS, T, R> attestIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func) {
		return new LIntObjByteFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LIntByteObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntByteObjFuncAssert.The<A, RS, T, R> attestIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func) {
		return new LIntByteObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LByteObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteObjIntFuncAssert.The<A, RS, T, R> attestByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func) {
		return new LByteObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LByteIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteIntObjFuncAssert.The<A, RS, T, R> attestByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func) {
		return new LByteIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntCharFunctionAssert.The<A, RS, T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func) {
		return new LObjIntCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LObjCharIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjCharIntFuncAssert.The<A, RS, T, R> attestObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func) {
		return new LObjCharIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LIntObjCharFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjCharFuncAssert.The<A, RS, T, R> attestIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func) {
		return new LIntObjCharFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LIntCharObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntCharObjFuncAssert.The<A, RS, T, R> attestIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func) {
		return new LIntCharObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LCharObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharObjIntFuncAssert.The<A, RS, T, R> attestCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func) {
		return new LCharObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LCharIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharIntObjFuncAssert.The<A, RS, T, R> attestCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func) {
		return new LCharIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntDblFunctionAssert.The<A, RS, T, R> attestObjIntDblFunc(LObjIntDblFunction<T, R> func) {
		return new LObjIntDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LObjDblIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjDblIntFuncAssert.The<A, RS, T, R> attestObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func) {
		return new LObjDblIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LIntObjDblFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjDblFuncAssert.The<A, RS, T, R> attestIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func) {
		return new LIntObjDblFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LIntDblObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntDblObjFuncAssert.The<A, RS, T, R> attestIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func) {
		return new LIntDblObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LDblObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblObjIntFuncAssert.The<A, RS, T, R> attestDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func) {
		return new LDblObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LDblIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblIntObjFuncAssert.The<A, RS, T, R> attestDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func) {
		return new LDblIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntFltFunctionAssert.The<A, RS, T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func) {
		return new LObjIntFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LObjFltIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjFltIntFuncAssert.The<A, RS, T, R> attestObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func) {
		return new LObjFltIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LIntObjFltFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjFltFuncAssert.The<A, RS, T, R> attestIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func) {
		return new LIntObjFltFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LIntFltObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntFltObjFuncAssert.The<A, RS, T, R> attestIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func) {
		return new LIntFltObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LFltObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltObjIntFuncAssert.The<A, RS, T, R> attestFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func) {
		return new LFltObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LFltIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltIntObjFuncAssert.The<A, RS, T, R> attestFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func) {
		return new LFltIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntLongFunctionAssert.The<A, RS, T, R> attestObjIntLongFunc(LObjIntLongFunction<T, R> func) {
		return new LObjIntLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LObjLongIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjLongIntFuncAssert.The<A, RS, T, R> attestObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func) {
		return new LObjLongIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LIntObjLongFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjLongFuncAssert.The<A, RS, T, R> attestIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func) {
		return new LIntObjLongFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LIntLongObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntLongObjFuncAssert.The<A, RS, T, R> attestIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func) {
		return new LIntLongObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LLongObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongObjIntFuncAssert.The<A, RS, T, R> attestLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func) {
		return new LLongObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LLongIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongIntObjFuncAssert.The<A, RS, T, R> attestLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func) {
		return new LLongIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, RS, T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) {
		return new LObjIntObjFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LInt1BiObj2FuncAssert.The<A, RS, T1, T2, R> attestInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func) {
		return new LInt1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LInt1Obj2Obj0FuncAssert.The<A, RS, T2, T1, R> attestInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func) {
		return new LInt1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj2Int1Obj0FuncAssert.The<A, RS, T2, T1, R> attestObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func) {
		return new LObj2Int1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntSrtFunctionAssert.The<A, RS, T, R> attestObjIntSrtFunc(LObjIntSrtFunction<T, R> func) {
		return new LObjIntSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LObjSrtIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjSrtIntFuncAssert.The<A, RS, T, R> attestObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func) {
		return new LObjSrtIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LIntObjSrtFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjSrtFuncAssert.The<A, RS, T, R> attestIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func) {
		return new LIntObjSrtFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LIntSrtObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntSrtObjFuncAssert.The<A, RS, T, R> attestIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func) {
		return new LIntSrtObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LSrtObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtObjIntFuncAssert.The<A, RS, T, R> attestSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func) {
		return new LSrtObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LSrtIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtIntObjFuncAssert.The<A, RS, T, R> attestSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func) {
		return new LSrtIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjLongFunctionAssert.The<A, RS, T, R> attestObjLongFunc(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction.LLongObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongObjFuncAssert.The<A, RS, T, R> attestLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func) {
		return new LLongObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjSrtFunctionAssert.The<A, RS, T, R> attestObjSrtFunc(LObjSrtFunction<T, R> func) {
		return new LObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtFunction.LSrtObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtObjFuncAssert.The<A, RS, T, R> attestSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func) {
		return new LSrtObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiFunction<T, R>, RS extends Assert<RS, R>, T, R> LOiFunctionAssert.The<A, RS, T, R> attestOiFunc(LOiFunction<T, R> func) {
		return new LOiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiFunction.LIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjFuncAssert.The<A, RS, T, R> attestIntObjFunc(LOiFunction.LIntObjFunc<T, R> func) {
		return new LIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtFunction<R>, RS extends Assert<RS, R>, R> LSrtFunctionAssert.The<A, RS, R> attestSrtFunc(LSrtFunction<R> func) {
		return new LSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunction<R>, RS extends Assert<RS, R>, R> LTriBoolFunctionAssert.The<A, RS, R> attestTriBoolFunc(LTriBoolFunction<R> func) {
		return new LTriBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriByteFunction<R>, RS extends Assert<RS, R>, R> LTriByteFunctionAssert.The<A, RS, R> attestTriByteFunc(LTriByteFunction<R> func) {
		return new LTriByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriCharFunction<R>, RS extends Assert<RS, R>, R> LTriCharFunctionAssert.The<A, RS, R> attestTriCharFunc(LTriCharFunction<R> func) {
		return new LTriCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriDblFunction<R>, RS extends Assert<RS, R>, R> LTriDblFunctionAssert.The<A, RS, R> attestTriDblFunc(LTriDblFunction<R> func) {
		return new LTriDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFltFunction<R>, RS extends Assert<RS, R>, R> LTriFltFunctionAssert.The<A, RS, R> attestTriFltFunc(LTriFltFunction<R> func) {
		return new LTriFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriIntFunction<R>, RS extends Assert<RS, R>, R> LTriIntFunctionAssert.The<A, RS, R> attestTriIntFunc(LTriIntFunction<R> func) {
		return new LTriIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriLongFunction<R>, RS extends Assert<RS, R>, R> LTriLongFunctionAssert.The<A, RS, R> attestTriLongFunc(LTriLongFunction<R> func) {
		return new LTriLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriSrtFunction<R>, RS extends Assert<RS, R>, R> LTriSrtFunctionAssert.The<A, RS, R> attestTriSrtFunc(LTriSrtFunction<R> func) {
		return new LTriSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LOiToByteFunctionAssert.The<A, RS, T> attestOiToByteFunc(LOiToByteFunction<T> func) {
		return new LOiToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToByteFunction.LIntObjToByteFunc<T>, RS extends AbstractByteAssert<RS>, T> LIntObjToByteFuncAssert.The<A, RS, T> attestIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func) {
		return new LIntObjToByteFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LOiToCharFunctionAssert.The<A, RS, T> attestOiToCharFunc(LOiToCharFunction<T> func) {
		return new LOiToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToCharFunction.LIntObjToCharFunc<T>, RS extends AbstractCharacterAssert<RS>, T> LIntObjToCharFuncAssert.The<A, RS, T> attestIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func) {
		return new LIntObjToCharFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToDblFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LOiToDblFunctionAssert.The<A, RS, T> attestOiToDblFunc(LOiToDblFunction<T> func) {
		return new LOiToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToDblFunction.LIntObjToDblFunc<T>, RS extends AbstractDoubleAssert<RS>, T> LIntObjToDblFuncAssert.The<A, RS, T> attestIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func) {
		return new LIntObjToDblFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LOiToFltFunctionAssert.The<A, RS, T> attestOiToFltFunc(LOiToFltFunction<T> func) {
		return new LOiToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToFltFunction.LIntObjToFltFunc<T>, RS extends AbstractFloatAssert<RS>, T> LIntObjToFltFuncAssert.The<A, RS, T> attestIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func) {
		return new LIntObjToFltFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LOiToIntFunctionAssert.The<A, RS, T> attestOiToIntFunc(LOiToIntFunction<T> func) {
		return new LOiToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToIntFunction.LIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjToIntFuncAssert.The<A, RS, T> attestIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func) {
		return new LIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LOiToLongFunctionAssert.The<A, RS, T> attestOiToLongFunc(LOiToLongFunction<T> func) {
		return new LOiToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T> LIntObjToLongFuncAssert.The<A, RS, T> attestIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		return new LIntObjToLongFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LOiToSrtFunctionAssert.The<A, RS, T> attestOiToSrtFunc(LOiToSrtFunction<T> func) {
		return new LOiToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToSrtFunction.LIntObjToSrtFunc<T>, RS extends AbstractShortAssert<RS>, T> LIntObjToSrtFuncAssert.The<A, RS, T> attestIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func) {
		return new LIntObjToSrtFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieBoolFunctionAssert.The<A, RS, T> attestTieBoolFunc(LTieBoolFunction<T> func) {
		return new LTieBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LObjBoolIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjBoolIntToIntFuncAssert.The<A, RS, T> attestObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func) {
		return new LObjBoolIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LIntObjBoolToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjBoolToIntFuncAssert.The<A, RS, T> attestIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func) {
		return new LIntObjBoolToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LIntBoolObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntBoolObjToIntFuncAssert.The<A, RS, T> attestIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func) {
		return new LIntBoolObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LBoolObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBoolObjIntToIntFuncAssert.The<A, RS, T> attestBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func) {
		return new LBoolObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LBoolIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBoolIntObjToIntFuncAssert.The<A, RS, T> attestBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func) {
		return new LBoolIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieByteFunctionAssert.The<A, RS, T> attestTieByteFunc(LTieByteFunction<T> func) {
		return new LTieByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LObjByteIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjByteIntToIntFuncAssert.The<A, RS, T> attestObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		return new LObjByteIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LIntObjByteToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjByteToIntFuncAssert.The<A, RS, T> attestIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func) {
		return new LIntObjByteToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LIntByteObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntByteObjToIntFuncAssert.The<A, RS, T> attestIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func) {
		return new LIntByteObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LByteObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LByteObjIntToIntFuncAssert.The<A, RS, T> attestByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func) {
		return new LByteObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LByteIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LByteIntObjToIntFuncAssert.The<A, RS, T> attestByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func) {
		return new LByteIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieCharFunctionAssert.The<A, RS, T> attestTieCharFunc(LTieCharFunction<T> func) {
		return new LTieCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LObjCharIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjCharIntToIntFuncAssert.The<A, RS, T> attestObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func) {
		return new LObjCharIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LIntObjCharToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjCharToIntFuncAssert.The<A, RS, T> attestIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func) {
		return new LIntObjCharToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LIntCharObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntCharObjToIntFuncAssert.The<A, RS, T> attestIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func) {
		return new LIntCharObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LCharObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LCharObjIntToIntFuncAssert.The<A, RS, T> attestCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func) {
		return new LCharObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LCharIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LCharIntObjToIntFuncAssert.The<A, RS, T> attestCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func) {
		return new LCharIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieDblFunctionAssert.The<A, RS, T> attestTieDblFunc(LTieDblFunction<T> func) {
		return new LTieDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LObjDblIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjDblIntToIntFuncAssert.The<A, RS, T> attestObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func) {
		return new LObjDblIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LIntObjDblToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjDblToIntFuncAssert.The<A, RS, T> attestIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func) {
		return new LIntObjDblToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LIntDblObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntDblObjToIntFuncAssert.The<A, RS, T> attestIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func) {
		return new LIntDblObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LDblObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LDblObjIntToIntFuncAssert.The<A, RS, T> attestDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func) {
		return new LDblObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LDblIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LDblIntObjToIntFuncAssert.The<A, RS, T> attestDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func) {
		return new LDblIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieFltFunctionAssert.The<A, RS, T> attestTieFltFunc(LTieFltFunction<T> func) {
		return new LTieFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LObjFltIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjFltIntToIntFuncAssert.The<A, RS, T> attestObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func) {
		return new LObjFltIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LIntObjFltToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjFltToIntFuncAssert.The<A, RS, T> attestIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func) {
		return new LIntObjFltToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LIntFltObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntFltObjToIntFuncAssert.The<A, RS, T> attestIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func) {
		return new LIntFltObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LFltObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LFltObjIntToIntFuncAssert.The<A, RS, T> attestFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func) {
		return new LFltObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LFltIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LFltIntObjToIntFuncAssert.The<A, RS, T> attestFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func) {
		return new LFltIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LTieFunctionAssert.The<A, RS, T1, T2> attestTieFunc(LTieFunction<T1, T2> func) {
		return new LTieFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LObj0Obj2Int1ToIntFuncAssert.The<A, RS, T1, T2> attestObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func) {
		return new LObj0Obj2Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LInt1BiObj2ToIntFunc<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LInt1BiObj2ToIntFuncAssert.The<A, RS, T1, T2> attestInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func) {
		return new LInt1BiObj2ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LInt1Obj2Obj0ToIntFuncAssert.The<A, RS, T2, T1> attestInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func) {
		return new LInt1Obj2Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LObj2Obj0Int1ToIntFuncAssert.The<A, RS, T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		return new LObj2Obj0Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LObj2Int1Obj0ToIntFuncAssert.The<A, RS, T2, T1> attestObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func) {
		return new LObj2Int1Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieIntFunctionAssert.The<A, RS, T> attestTieIntFunc(LTieIntFunction<T> func) {
		return new LTieIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LObj0Int2Int1ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObj0Int2Int1ToIntFuncAssert.The<A, RS, T> attestObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func) {
		return new LObj0Int2Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt1Obj0Int2ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt1Obj0Int2ToIntFuncAssert.The<A, RS, T> attestInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func) {
		return new LInt1Obj0Int2ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt1Int2Obj0ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt1Int2Obj0ToIntFuncAssert.The<A, RS, T> attestInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func) {
		return new LInt1Int2Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt2Obj0Int1ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt2Obj0Int1ToIntFuncAssert.The<A, RS, T> attestInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func) {
		return new LInt2Obj0Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LBiInt1Obj0ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBiInt1Obj0ToIntFuncAssert.The<A, RS, T> attestBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func) {
		return new LBiInt1Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieLongFunctionAssert.The<A, RS, T> attestTieLongFunc(LTieLongFunction<T> func) {
		return new LTieLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LObjLongIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjLongIntToIntFuncAssert.The<A, RS, T> attestObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func) {
		return new LObjLongIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LIntObjLongToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjLongToIntFuncAssert.The<A, RS, T> attestIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func) {
		return new LIntObjLongToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LIntLongObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntLongObjToIntFuncAssert.The<A, RS, T> attestIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func) {
		return new LIntLongObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LLongObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LLongObjIntToIntFuncAssert.The<A, RS, T> attestLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func) {
		return new LLongObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LLongIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LLongIntObjToIntFuncAssert.The<A, RS, T> attestLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func) {
		return new LLongIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieSrtFunctionAssert.The<A, RS, T> attestTieSrtFunc(LTieSrtFunction<T> func) {
		return new LTieSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LObjSrtIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjSrtIntToIntFuncAssert.The<A, RS, T> attestObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func) {
		return new LObjSrtIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LIntObjSrtToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjSrtToIntFuncAssert.The<A, RS, T> attestIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func) {
		return new LIntObjSrtToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LIntSrtObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntSrtObjToIntFuncAssert.The<A, RS, T> attestIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func) {
		return new LIntSrtObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LSrtObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LSrtObjIntToIntFuncAssert.The<A, RS, T> attestSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func) {
		return new LSrtObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LSrtIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LSrtIntObjToIntFuncAssert.The<A, RS, T> attestSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func) {
		return new LSrtIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.The<A, RS, T1, T2> attestToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		return new LToByteBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction.LToByteObj1Obj0Func<T2, T1>, RS extends AbstractByteAssert<RS>, T2, T1> LToByteObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func) {
		return new LToByteObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LToByteFunctionAssert.The<A, RS, T> attestToByteFunc(LToByteFunction<T> func) {
		return new LToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> LToCharBiFunctionAssert.The<A, RS, T1, T2> attestToCharBiFunc(LToCharBiFunction<T1, T2> func) {
		return new LToCharBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction.LToCharObj1Obj0Func<T2, T1>, RS extends AbstractCharacterAssert<RS>, T2, T1> LToCharObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func) {
		return new LToCharObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.The<A, RS, T> attestToCharFunc(LToCharFunction<T> func) {
		return new LToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDblBiFunctionAssert.The<A, RS, T1, T2> attestToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		return new LToDblBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblBiFunction.LToDblObj1Obj0Func<T2, T1>, RS extends AbstractDoubleAssert<RS>, T2, T1> LToDblObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func) {
		return new LToDblObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LToDblFunctionAssert.The<A, RS, T> attestToDblFunc(LToDblFunction<T> func) {
		return new LToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> LToFltBiFunctionAssert.The<A, RS, T1, T2> attestToFltBiFunc(LToFltBiFunction<T1, T2> func) {
		return new LToFltBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltBiFunction.LToFltObj1Obj0Func<T2, T1>, RS extends AbstractFloatAssert<RS>, T2, T1> LToFltObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func) {
		return new LToFltObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFltFunctionAssert.The<A, RS, T> attestToFltFunc(LToFltFunction<T> func) {
		return new LToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.The<A, RS, T1, T2> attestToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		return new LToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction.LToIntObj1Obj0Func<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LToIntObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func) {
		return new LToIntObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LToIntFunctionAssert.The<A, RS, T> attestToIntFunc(LToIntFunction<T> func) {
		return new LToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction<T1, T2, T3>, RS extends AbstractIntegerAssert<RS>, T1, T2, T3> LToIntTriFunctionAssert.The<A, RS, T1, T2, T3> attestToIntTriFunc(LToIntTriFunction<T1, T2, T3> func) {
		return new LToIntTriFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2>, RS extends AbstractIntegerAssert<RS>, T1, T3, T2> LToIntObj0Obj2Obj1FuncAssert.The<A, RS, T1, T3, T2> attestToIntObj0Obj2Obj1Func(
			LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func) {
		return new LToIntObj0Obj2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3>, RS extends AbstractIntegerAssert<RS>, T2, T1, T3> LToIntObj1BiObj2FuncAssert.The<A, RS, T2, T1, T3> attestToIntObj1BiObj2Func(
			LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		return new LToIntObj1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1>, RS extends AbstractIntegerAssert<RS>, T2, T3, T1> LToIntObj1Obj2Obj0FuncAssert.The<A, RS, T2, T3, T1> attestToIntObj1Obj2Obj0Func(
			LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func) {
		return new LToIntObj1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2>, RS extends AbstractIntegerAssert<RS>, T3, T1, T2> LToIntObj2Obj0Obj1FuncAssert.The<A, RS, T3, T1, T2> attestToIntObj2Obj0Obj1Func(
			LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func) {
		return new LToIntObj2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1>, RS extends AbstractIntegerAssert<RS>, T3, T2, T1> LToIntBiObj1Obj0FuncAssert.The<A, RS, T3, T2, T1> attestToIntBiObj1Obj0Func(
			LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func) {
		return new LToIntBiObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.The<A, RS, T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction.LToLongObj1Obj0Func<T2, T1>, RS extends AbstractLongAssert<RS>, T2, T1> LToLongObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func) {
		return new LToLongObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LToLongFunctionAssert.The<A, RS, T> attestToLongFunc(LToLongFunction<T> func) {
		return new LToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> LToSrtBiFunctionAssert.The<A, RS, T1, T2> attestToSrtBiFunc(LToSrtBiFunction<T1, T2> func) {
		return new LToSrtBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1>, RS extends AbstractShortAssert<RS>, T2, T1> LToSrtObj1Obj0FuncAssert.The<A, RS, T2, T1> attestToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func) {
		return new LToSrtObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LToSrtFunctionAssert.The<A, RS, T> attestToSrtFunc(LToSrtFunction<T> func) {
		return new LToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.The<A, RS> attestBiBytePred(LBiBytePredicate func) {
		return new LBiBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate.LByte1Byte0Pred, RS extends AbstractBooleanAssert<RS>> LByte1Byte0PredAssert.The<A, RS> attestByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func) {
		return new LByte1Byte0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.The<A, RS> attestBiCharPred(LBiCharPredicate func) {
		return new LBiCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate.LChar1Char0Pred, RS extends AbstractBooleanAssert<RS>> LChar1Char0PredAssert.The<A, RS> attestChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func) {
		return new LChar1Char0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblPredicate, RS extends AbstractBooleanAssert<RS>> LBiDblPredicateAssert.The<A, RS> attestBiDblPred(LBiDblPredicate func) {
		return new LBiDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblPredicate.LDbl1Dbl0Pred, RS extends AbstractBooleanAssert<RS>> LDbl1Dbl0PredAssert.The<A, RS> attestDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func) {
		return new LDbl1Dbl0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltPredicate, RS extends AbstractBooleanAssert<RS>> LBiFltPredicateAssert.The<A, RS> attestBiFltPred(LBiFltPredicate func) {
		return new LBiFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltPredicate.LFlt1Flt0Pred, RS extends AbstractBooleanAssert<RS>> LFlt1Flt0PredAssert.The<A, RS> attestFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func) {
		return new LFlt1Flt0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.The<A, RS> attestBiIntPred(LBiIntPredicate func) {
		return new LBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate.LInt1Int0Pred, RS extends AbstractBooleanAssert<RS>> LInt1Int0PredAssert.The<A, RS> attestInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func) {
		return new LInt1Int0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.The<A, RS> attestBiLongPred(LBiLongPredicate func) {
		return new LBiLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate.LLong1Long0Pred, RS extends AbstractBooleanAssert<RS>> LLong1Long0PredAssert.The<A, RS> attestLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func) {
		return new LLong1Long0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBoolPredicateAssert.The<A, RS, T1, T2> attestBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		return new LBiObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Bool2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func) {
		return new LObj0Bool2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Bool2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func) {
		return new LObj1Obj0Bool2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Bool2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func) {
		return new LObj1Bool2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBool2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func) {
		return new LBool2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LBool2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func) {
		return new LBool2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.The<A, RS, T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Byte2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func) {
		return new LObj0Byte2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Byte2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func) {
		return new LObj1Obj0Byte2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Byte2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func) {
		return new LObj1Byte2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LByte2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func) {
		return new LByte2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LByte2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		return new LByte2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.The<A, RS, T1, T2> attestBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		return new LBiObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Char2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func) {
		return new LObj0Char2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Char2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func) {
		return new LObj1Obj0Char2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Char2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func) {
		return new LObj1Char2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LChar2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func) {
		return new LChar2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LChar2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func) {
		return new LChar2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDblPredicateAssert.The<A, RS, T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		return new LBiObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Dbl2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func) {
		return new LObj0Dbl2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Dbl2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func) {
		return new LObj1Obj0Dbl2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Dbl2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func) {
		return new LObj1Dbl2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LDbl2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func) {
		return new LDbl2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LDbl2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func) {
		return new LDbl2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFltPredicateAssert.The<A, RS, T1, T2> attestBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		return new LBiObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Flt2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func) {
		return new LObj0Flt2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Flt2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func) {
		return new LObj1Obj0Flt2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Flt2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func) {
		return new LObj1Flt2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LFlt2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func) {
		return new LFlt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LFlt2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func) {
		return new LFlt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.The<A, RS, T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Int2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func) {
		return new LObj1Obj0Int2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LInt2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func) {
		return new LInt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LInt2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func) {
		return new LInt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.The<A, RS, T1, T2> attestBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		return new LBiObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Long2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func) {
		return new LObj0Long2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Long2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func) {
		return new LObj1Obj0Long2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Long2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func) {
		return new LObj1Long2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LLong2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func) {
		return new LLong2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LLong2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func) {
		return new LLong2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjSrtPredicateAssert.The<A, RS, T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		return new LBiObjSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Srt2Obj1PredAssert.The<A, RS, T1, T2> attestObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func) {
		return new LObj0Srt2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Srt2PredAssert.The<A, RS, T2, T1> attestObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func) {
		return new LObj1Obj0Srt2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Srt2Obj0PredAssert.The<A, RS, T2, T1> attestObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func) {
		return new LObj1Srt2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LSrt2Obj0Obj1PredAssert.The<A, RS, T1, T2> attestSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func) {
		return new LSrt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LSrt2Obj1Obj0PredAssert.The<A, RS, T2, T1> attestSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func) {
		return new LSrt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> attestBiPred(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate.LObj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0PredAssert.The<A, RS, T2, T1> attestObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func) {
		return new LObj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtPredicate, RS extends AbstractBooleanAssert<RS>> LBiSrtPredicateAssert.The<A, RS> attestBiSrtPred(LBiSrtPredicate func) {
		return new LBiSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtPredicate.LSrt1Srt0Pred, RS extends AbstractBooleanAssert<RS>> LSrt1Srt0PredAssert.The<A, RS> attestSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func) {
		return new LSrt1Srt0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolIntPredicate, RS extends AbstractBooleanAssert<RS>> LBoolIntPredicateAssert.The<A, RS> attestBoolIntPred(LBoolIntPredicate func) {
		return new LBoolIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolIntPredicate.LIntBoolPred, RS extends AbstractBooleanAssert<RS>> LIntBoolPredAssert.The<A, RS> attestIntBoolPred(LBoolIntPredicate.LIntBoolPred func) {
		return new LIntBoolPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteIntPredicate, RS extends AbstractBooleanAssert<RS>> LByteIntPredicateAssert.The<A, RS> attestByteIntPred(LByteIntPredicate func) {
		return new LByteIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteIntPredicate.LIntBytePred, RS extends AbstractBooleanAssert<RS>> LIntBytePredAssert.The<A, RS> attestIntBytePred(LByteIntPredicate.LIntBytePred func) {
		return new LIntBytePredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicate, RS extends AbstractBooleanAssert<RS>> LBytePredicateAssert.The<A, RS> attestBytePred(LBytePredicate func) {
		return new LBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharIntPredicate, RS extends AbstractBooleanAssert<RS>> LCharIntPredicateAssert.The<A, RS> attestCharIntPred(LCharIntPredicate func) {
		return new LCharIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharIntPredicate.LIntCharPred, RS extends AbstractBooleanAssert<RS>> LIntCharPredAssert.The<A, RS> attestIntCharPred(LCharIntPredicate.LIntCharPred func) {
		return new LIntCharPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.The<A, RS> attestCharPred(LCharPredicate func) {
		return new LCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblIntPredicate, RS extends AbstractBooleanAssert<RS>> LDblIntPredicateAssert.The<A, RS> attestDblIntPred(LDblIntPredicate func) {
		return new LDblIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblIntPredicate.LIntDblPred, RS extends AbstractBooleanAssert<RS>> LIntDblPredAssert.The<A, RS> attestIntDblPred(LDblIntPredicate.LIntDblPred func) {
		return new LIntDblPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblPredicate, RS extends AbstractBooleanAssert<RS>> LDblPredicateAssert.The<A, RS> attestDblPred(LDblPredicate func) {
		return new LDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltIntPredicate, RS extends AbstractBooleanAssert<RS>> LFltIntPredicateAssert.The<A, RS> attestFltIntPred(LFltIntPredicate func) {
		return new LFltIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltIntPredicate.LIntFltPred, RS extends AbstractBooleanAssert<RS>> LIntFltPredAssert.The<A, RS> attestIntFltPred(LFltIntPredicate.LIntFltPred func) {
		return new LIntFltPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltPredicate, RS extends AbstractBooleanAssert<RS>> LFltPredicateAssert.The<A, RS> attestFltPred(LFltPredicate func) {
		return new LFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicate, RS extends AbstractBooleanAssert<RS>> LIntPredicateAssert.The<A, RS> attestIntPred(LIntPredicate func) {
		return new LIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongIntPredicate, RS extends AbstractBooleanAssert<RS>> LLongIntPredicateAssert.The<A, RS> attestLongIntPred(LLongIntPredicate func) {
		return new LLongIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongIntPredicate.LIntLongPred, RS extends AbstractBooleanAssert<RS>> LIntLongPredAssert.The<A, RS> attestIntLongPred(LLongIntPredicate.LIntLongPred func) {
		return new LIntLongPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.The<A, RS> attestLongPred(LLongPredicate func) {
		return new LLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBiIntPredicateAssert.The<A, RS, T> attestObjBiIntPred(LObjBiIntPredicate<T> func) {
		return new LObjBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LObj0Int2Int1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LObj0Int2Int1PredAssert.The<A, RS, T> attestObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func) {
		return new LObj0Int2Int1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt1Obj0Int2Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt1Obj0Int2PredAssert.The<A, RS, T> attestInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func) {
		return new LInt1Obj0Int2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt1Int2Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt1Int2Obj0PredAssert.The<A, RS, T> attestInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func) {
		return new LInt1Int2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt2Obj0Int1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt2Obj0Int1PredAssert.The<A, RS, T> attestInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func) {
		return new LInt2Obj0Int1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LBiInt1Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LBiInt1Obj0PredAssert.The<A, RS, T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		return new LBiInt1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBiLongPredicateAssert.The<A, RS, T> attestObjBiLongPred(LObjBiLongPredicate<T> func) {
		return new LObjBiLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate.LObj0Long2Long1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LObj0Long2Long1PredAssert.The<A, RS, T> attestObj0Long2Long1Pred(LObjBiLongPredicate.LObj0Long2Long1Pred<T> func) {
		return new LObj0Long2Long1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate.LLong1Obj0Long2Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LLong1Obj0Long2PredAssert.The<A, RS, T> attestLong1Obj0Long2Pred(LObjBiLongPredicate.LLong1Obj0Long2Pred<T> func) {
		return new LLong1Obj0Long2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate.LLong1Long2Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LLong1Long2Obj0PredAssert.The<A, RS, T> attestLong1Long2Obj0Pred(LObjBiLongPredicate.LLong1Long2Obj0Pred<T> func) {
		return new LLong1Long2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate.LLong2Obj0Long1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LLong2Obj0Long1PredAssert.The<A, RS, T> attestLong2Obj0Long1Pred(LObjBiLongPredicate.LLong2Obj0Long1Pred<T> func) {
		return new LLong2Obj0Long1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiLongPredicate.LBiLong1Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LBiLong1Obj0PredAssert.The<A, RS, T> attestBiLong1Obj0Pred(LObjBiLongPredicate.LBiLong1Obj0Pred<T> func) {
		return new LBiLong1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolPredicateAssert.The<A, RS, T> attestObjBoolPred(LObjBoolPredicate<T> func) {
		return new LObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate.LBoolObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolObjPredAssert.The<A, RS, T> attestBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func) {
		return new LBoolObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.The<A, RS, T> attestObjBytePred(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate.LByteObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteObjPredAssert.The<A, RS, T> attestByteObjPred(LObjBytePredicate.LByteObjPred<T> func) {
		return new LByteObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.The<A, RS, T> attestObjCharPred(LObjCharPredicate<T> func) {
		return new LObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate.LCharObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharObjPredAssert.The<A, RS, T> attestCharObjPred(LObjCharPredicate.LCharObjPred<T> func) {
		return new LCharObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDblPredicateAssert.The<A, RS, T> attestObjDblPred(LObjDblPredicate<T> func) {
		return new LObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblPredicate.LDblObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblObjPredAssert.The<A, RS, T> attestDblObjPred(LObjDblPredicate.LDblObjPred<T> func) {
		return new LDblObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFltPredicateAssert.The<A, RS, T> attestObjFltPred(LObjFltPredicate<T> func) {
		return new LObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltPredicate.LFltObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltObjPredAssert.The<A, RS, T> attestFltObjPred(LObjFltPredicate.LFltObjPred<T> func) {
		return new LFltObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBoolPredicateAssert.The<A, RS, T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		return new LObjIntBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LObjBoolIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolIntPredAssert.The<A, RS, T> attestObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func) {
		return new LObjBoolIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LIntObjBoolPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjBoolPredAssert.The<A, RS, T> attestIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func) {
		return new LIntObjBoolPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LIntBoolObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntBoolObjPredAssert.The<A, RS, T> attestIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func) {
		return new LIntBoolObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LBoolObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolObjIntPredAssert.The<A, RS, T> attestBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func) {
		return new LBoolObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LBoolIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolIntObjPredAssert.The<A, RS, T> attestBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func) {
		return new LBoolIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBytePredicateAssert.The<A, RS, T> attestObjIntBytePred(LObjIntBytePredicate<T> func) {
		return new LObjIntBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LObjByteIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjByteIntPredAssert.The<A, RS, T> attestObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func) {
		return new LObjByteIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LIntObjBytePred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjBytePredAssert.The<A, RS, T> attestIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func) {
		return new LIntObjBytePredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LIntByteObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntByteObjPredAssert.The<A, RS, T> attestIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func) {
		return new LIntByteObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LByteObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteObjIntPredAssert.The<A, RS, T> attestByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func) {
		return new LByteObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LByteIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteIntObjPredAssert.The<A, RS, T> attestByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func) {
		return new LByteIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntCharPredicateAssert.The<A, RS, T> attestObjIntCharPred(LObjIntCharPredicate<T> func) {
		return new LObjIntCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LObjCharIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharIntPredAssert.The<A, RS, T> attestObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func) {
		return new LObjCharIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LIntObjCharPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjCharPredAssert.The<A, RS, T> attestIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func) {
		return new LIntObjCharPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LIntCharObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntCharObjPredAssert.The<A, RS, T> attestIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func) {
		return new LIntCharObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LCharObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharObjIntPredAssert.The<A, RS, T> attestCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func) {
		return new LCharObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LCharIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharIntObjPredAssert.The<A, RS, T> attestCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func) {
		return new LCharIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntDblPredicateAssert.The<A, RS, T> attestObjIntDblPred(LObjIntDblPredicate<T> func) {
		return new LObjIntDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LObjDblIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDblIntPredAssert.The<A, RS, T> attestObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func) {
		return new LObjDblIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LIntObjDblPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjDblPredAssert.The<A, RS, T> attestIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func) {
		return new LIntObjDblPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LIntDblObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntDblObjPredAssert.The<A, RS, T> attestIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func) {
		return new LIntDblObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LDblObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblObjIntPredAssert.The<A, RS, T> attestDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func) {
		return new LDblObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LDblIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblIntObjPredAssert.The<A, RS, T> attestDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func) {
		return new LDblIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntFltPredicateAssert.The<A, RS, T> attestObjIntFltPred(LObjIntFltPredicate<T> func) {
		return new LObjIntFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LObjFltIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFltIntPredAssert.The<A, RS, T> attestObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func) {
		return new LObjFltIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LIntObjFltPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjFltPredAssert.The<A, RS, T> attestIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func) {
		return new LIntObjFltPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LIntFltObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntFltObjPredAssert.The<A, RS, T> attestIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func) {
		return new LIntFltObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LFltObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltObjIntPredAssert.The<A, RS, T> attestFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func) {
		return new LFltObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LFltIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltIntObjPredAssert.The<A, RS, T> attestFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func) {
		return new LFltIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntLongPredicateAssert.The<A, RS, T> attestObjIntLongPred(LObjIntLongPredicate<T> func) {
		return new LObjIntLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LObjLongIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongIntPredAssert.The<A, RS, T> attestObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func) {
		return new LObjLongIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LIntObjLongPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjLongPredAssert.The<A, RS, T> attestIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func) {
		return new LIntObjLongPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LIntLongObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntLongObjPredAssert.The<A, RS, T> attestIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func) {
		return new LIntLongObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LLongObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongObjIntPredAssert.The<A, RS, T> attestLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func) {
		return new LLongObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LLongIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongIntObjPredAssert.The<A, RS, T> attestLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func) {
		return new LLongIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObjIntObjPredicateAssert.The<A, RS, T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		return new LObjIntObjPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LInt1BiObj2PredAssert.The<A, RS, T1, T2> attestInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func) {
		return new LInt1BiObj2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LInt1Obj2Obj0PredAssert.The<A, RS, T2, T1> attestInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func) {
		return new LInt1Obj2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj2Int1Obj0PredAssert.The<A, RS, T2, T1> attestObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func) {
		return new LObj2Int1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.The<A, RS, T> attestObjIntPred(LObjIntPredicate<T> func) {
		return new LObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate.LIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjPredAssert.The<A, RS, T> attestIntObjPred(LObjIntPredicate.LIntObjPred<T> func) {
		return new LIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntSrtPredicateAssert.The<A, RS, T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		return new LObjIntSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LObjSrtIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjSrtIntPredAssert.The<A, RS, T> attestObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func) {
		return new LObjSrtIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjSrtPredAssert.The<A, RS, T> attestIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		return new LIntObjSrtPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LIntSrtObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntSrtObjPredAssert.The<A, RS, T> attestIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func) {
		return new LIntSrtObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LSrtObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtObjIntPredAssert.The<A, RS, T> attestSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func) {
		return new LSrtObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LSrtIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtIntObjPredAssert.The<A, RS, T> attestSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func) {
		return new LSrtIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.The<A, RS, T> attestObjLongPred(LObjLongPredicate<T> func) {
		return new LObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate.LLongObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongObjPredAssert.The<A, RS, T> attestLongObjPred(LObjLongPredicate.LLongObjPred<T> func) {
		return new LLongObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjSrtPredicateAssert.The<A, RS, T> attestObjSrtPred(LObjSrtPredicate<T> func) {
		return new LObjSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtPredicate.LSrtObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtObjPredAssert.The<A, RS, T> attestSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func) {
		return new LSrtObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LPredicateAssert.The<A, RS, T> attestPred(LPredicate<T> func) {
		return new LPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuadPredicate<T1, T2, T3, T4>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, T4> LQuadPredicateAssert.The<A, RS, T1, T2, T3, T4> attestQuadPred(LQuadPredicate<T1, T2, T3, T4> func) {
		return new LQuadPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuintPredicate<T1, T2, T3, T4, T5>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, T4, T5> LQuintPredicateAssert.The<A, RS, T1, T2, T3, T4, T5> attestQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func) {
		return new LQuintPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtIntPredicate, RS extends AbstractBooleanAssert<RS>> LSrtIntPredicateAssert.The<A, RS> attestSrtIntPred(LSrtIntPredicate func) {
		return new LSrtIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtIntPredicate.LIntSrtPred, RS extends AbstractBooleanAssert<RS>> LIntSrtPredAssert.The<A, RS> attestIntSrtPred(LSrtIntPredicate.LIntSrtPred func) {
		return new LIntSrtPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtPredicate, RS extends AbstractBooleanAssert<RS>> LSrtPredicateAssert.The<A, RS> attestSrtPred(LSrtPredicate func) {
		return new LSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBytePredicate, RS extends AbstractBooleanAssert<RS>> LTriBytePredicateAssert.The<A, RS> attestTriBytePred(LTriBytePredicate func) {
		return new LTriBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriCharPredicate, RS extends AbstractBooleanAssert<RS>> LTriCharPredicateAssert.The<A, RS> attestTriCharPred(LTriCharPredicate func) {
		return new LTriCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriDblPredicate, RS extends AbstractBooleanAssert<RS>> LTriDblPredicateAssert.The<A, RS> attestTriDblPred(LTriDblPredicate func) {
		return new LTriDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFltPredicate, RS extends AbstractBooleanAssert<RS>> LTriFltPredicateAssert.The<A, RS> attestTriFltPred(LTriFltPredicate func) {
		return new LTriFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriIntPredicate, RS extends AbstractBooleanAssert<RS>> LTriIntPredicateAssert.The<A, RS> attestTriIntPred(LTriIntPredicate func) {
		return new LTriIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriLongPredicate, RS extends AbstractBooleanAssert<RS>> LTriLongPredicateAssert.The<A, RS> attestTriLongPred(LTriLongPredicate func) {
		return new LTriLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.The<A, RS, T1, T2, T3> attestTriPred(LTriPredicate<T1, T2, T3> func) {
		return new LTriPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2>, RS extends AbstractBooleanAssert<RS>, T1, T3, T2> LObj0Obj2Obj1PredAssert.The<A, RS, T1, T3, T2> attestObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func) {
		return new LObj0Obj2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj1BiObj2Pred<T2, T1, T3>, RS extends AbstractBooleanAssert<RS>, T2, T1, T3> LObj1BiObj2PredAssert.The<A, RS, T2, T1, T3> attestObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func) {
		return new LObj1BiObj2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1>, RS extends AbstractBooleanAssert<RS>, T2, T3, T1> LObj1Obj2Obj0PredAssert.The<A, RS, T2, T3, T1> attestObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func) {
		return new LObj1Obj2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2>, RS extends AbstractBooleanAssert<RS>, T3, T1, T2> LObj2Obj0Obj1PredAssert.The<A, RS, T3, T1, T2> attestObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func) {
		return new LObj2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1>, RS extends AbstractBooleanAssert<RS>, T3, T2, T1> LBiObj1Obj0PredAssert.The<A, RS, T3, T2, T1> attestBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func) {
		return new LBiObj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriSrtPredicate, RS extends AbstractBooleanAssert<RS>> LTriSrtPredicateAssert.The<A, RS> attestTriSrtPred(LTriSrtPredicate func) {
		return new LTriSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplier, RS extends AbstractBooleanAssert<RS>> LBoolSupplierAssert.The<A, RS> attestBoolSup(LBoolSupplier func) {
		return new LBoolSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplier, RS extends AbstractByteAssert<RS>> LByteSupplierAssert.The<A, RS> attestByteSup(LByteSupplier func) {
		return new LByteSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplier, RS extends AbstractCharacterAssert<RS>> LCharSupplierAssert.The<A, RS> attestCharSup(LCharSupplier func) {
		return new LCharSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblSupplier, RS extends AbstractDoubleAssert<RS>> LDblSupplierAssert.The<A, RS> attestDblSup(LDblSupplier func) {
		return new LDblSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltSupplier, RS extends AbstractFloatAssert<RS>> LFltSupplierAssert.The<A, RS> attestFltSup(LFltSupplier func) {
		return new LFltSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplier, RS extends AbstractIntegerAssert<RS>> LIntSupplierAssert.The<A, RS> attestIntSup(LIntSupplier func) {
		return new LIntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplier, RS extends AbstractLongAssert<RS>> LLongSupplierAssert.The<A, RS> attestLongSup(LLongSupplier func) {
		return new LLongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtSupplier, RS extends AbstractShortAssert<RS>> LSrtSupplierAssert.The<A, RS> attestSrtSup(LSrtSupplier func) {
		return new LSrtSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplier<T>, RS extends Assert<RS, T>, T> LSupplierAssert.The<A, RS, T> attestSup(LSupplier<T> func) {
		return new LSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Runnable> JreRunnableAssert.The<A> attestAct(Runnable func) {
		return new JreRunnableAssert.The(func);
	}

	@Nonnull
	public static <A extends BiConsumer<T1, T2>, T1, T2> JreBiConsumerAssert.The<A, T1, T2> attestBiCons(BiConsumer<T1, T2> func) {
		return new JreBiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends Consumer<T>, T> JreConsumerAssert.The<A, T> attestCons(Consumer<T> func) {
		return new JreConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends DoubleConsumer> JreDoubleConsumerAssert.The<A> attestDblCons(DoubleConsumer func) {
		return new JreDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends IntConsumer> JreIntConsumerAssert.The<A> attestIntCons(IntConsumer func) {
		return new JreIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LongConsumer> JreLongConsumerAssert.The<A> attestLongCons(LongConsumer func) {
		return new JreLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjDoubleConsumer<T>, T> JreObjDoubleConsumerAssert.The<A, T> attestObjDblCons(ObjDoubleConsumer<T> func) {
		return new JreObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjIntConsumer<T>, T> JreObjIntConsumerAssert.The<A, T> attestObjIntCons(ObjIntConsumer<T> func) {
		return new JreObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjLongConsumer<T>, T> JreObjLongConsumerAssert.The<A, T> attestObjLongCons(ObjLongConsumer<T> func) {
		return new JreObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends BinaryOperator<T>, RS extends Assert<RS, T>, T> JreBinaryOperatorAssert.The<A, RS, T> attestBinaryOp(BinaryOperator<T> func) {
		return new JreBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> JreDoubleBinaryOperatorAssert.The<A, RS> attestDblBinaryOp(DoubleBinaryOperator func) {
		return new JreDoubleBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> JreDoubleUnaryOperatorAssert.The<A, RS> attestDblUnaryOp(DoubleUnaryOperator func) {
		return new JreDoubleUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> JreIntBinaryOperatorAssert.The<A, RS> attestIntBinaryOp(IntBinaryOperator func) {
		return new JreIntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> JreIntUnaryOperatorAssert.The<A, RS> attestIntUnaryOp(IntUnaryOperator func) {
		return new JreIntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBinaryOperator, RS extends AbstractLongAssert<RS>> JreLongBinaryOperatorAssert.The<A, RS> attestLongBinaryOp(LongBinaryOperator func) {
		return new JreLongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongUnaryOperator, RS extends AbstractLongAssert<RS>> JreLongUnaryOperatorAssert.The<A, RS> attestLongUnaryOp(LongUnaryOperator func) {
		return new JreLongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends UnaryOperator<T>, RS extends Assert<RS, T>, T> JreUnaryOperatorAssert.The<A, RS, T> attestUnaryOp(UnaryOperator<T> func) {
		return new JreUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> JreBiFunctionAssert.The<A, RS, T1, T2, R> attestBiFunc(BiFunction<T1, T2, R> func) {
		return new JreBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleFunction<R>, RS extends Assert<RS, R>, R> JreDoubleFunctionAssert.The<A, RS, R> attestDblFunc(DoubleFunction<R> func) {
		return new JreDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> JreDoubleToIntFunctionAssert.The<A, RS> attestDblToIntFunc(DoubleToIntFunction func) {
		return new JreDoubleToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToLongFunction, RS extends AbstractLongAssert<RS>> JreDoubleToLongFunctionAssert.The<A, RS> attestDblToLongFunc(DoubleToLongFunction func) {
		return new JreDoubleToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Function<T, R>, RS extends Assert<RS, R>, T, R> JreFunctionAssert.The<A, RS, T, R> attestFunc(Function<T, R> func) {
		return new JreFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntFunction<R>, RS extends Assert<RS, R>, R> JreIntFunctionAssert.The<A, RS, R> attestIntFunc(IntFunction<R> func) {
		return new JreIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> JreIntToDoubleFunctionAssert.The<A, RS> attestIntToDblFunc(IntToDoubleFunction func) {
		return new JreIntToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToLongFunction, RS extends AbstractLongAssert<RS>> JreIntToLongFunctionAssert.The<A, RS> attestIntToLongFunc(IntToLongFunction func) {
		return new JreIntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongFunction<R>, RS extends Assert<RS, R>, R> JreLongFunctionAssert.The<A, RS, R> attestLongFunc(LongFunction<R> func) {
		return new JreLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> JreLongToDoubleFunctionAssert.The<A, RS> attestLongToDblFunc(LongToDoubleFunction func) {
		return new JreLongToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToIntFunction, RS extends AbstractIntegerAssert<RS>> JreLongToIntFunctionAssert.The<A, RS> attestLongToIntFunc(LongToIntFunction func) {
		return new JreLongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> JreToDoubleBiFunctionAssert.The<A, RS, T1, T2> attestToDblBiFunc(ToDoubleBiFunction<T1, T2> func) {
		return new JreToDoubleBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> JreToDoubleFunctionAssert.The<A, RS, T> attestToDblFunc(ToDoubleFunction<T> func) {
		return new JreToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> JreToIntBiFunctionAssert.The<A, RS, T1, T2> attestToIntBiFunc(ToIntBiFunction<T1, T2> func) {
		return new JreToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> JreToIntFunctionAssert.The<A, RS, T> attestToIntFunc(ToIntFunction<T> func) {
		return new JreToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> JreToLongBiFunctionAssert.The<A, RS, T1, T2> attestToLongBiFunc(ToLongBiFunction<T1, T2> func) {
		return new JreToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> JreToLongFunctionAssert.The<A, RS, T> attestToLongFunc(ToLongFunction<T> func) {
		return new JreToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> JreBiPredicateAssert.The<A, RS, T1, T2> attestBiPred(BiPredicate<T1, T2> func) {
		return new JreBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoublePredicate, RS extends AbstractBooleanAssert<RS>> JreDoublePredicateAssert.The<A, RS> attestDblPred(DoublePredicate func) {
		return new JreDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntPredicate, RS extends AbstractBooleanAssert<RS>> JreIntPredicateAssert.The<A, RS> attestIntPred(IntPredicate func) {
		return new JreIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongPredicate, RS extends AbstractBooleanAssert<RS>> JreLongPredicateAssert.The<A, RS> attestLongPred(LongPredicate func) {
		return new JreLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> JrePredicateAssert.The<A, RS, T> attestPred(Predicate<T> func) {
		return new JrePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> JreBooleanSupplierAssert.The<A, RS> attestBoolSup(BooleanSupplier func) {
		return new JreBooleanSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleSupplier, RS extends AbstractDoubleAssert<RS>> JreDoubleSupplierAssert.The<A, RS> attestDblSup(DoubleSupplier func) {
		return new JreDoubleSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntSupplier, RS extends AbstractIntegerAssert<RS>> JreIntSupplierAssert.The<A, RS> attestIntSup(IntSupplier func) {
		return new JreIntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongSupplier, RS extends AbstractLongAssert<RS>> JreLongSupplierAssert.The<A, RS> attestLongSup(LongSupplier func) {
		return new JreLongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Supplier<T>, RS extends Assert<RS, T>, T> JreSupplierAssert.The<A, RS, T> attestSup(Supplier<T> func) {
		return new JreSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A> Attest.ObjAttest<A> attestThat(A actual) {
		return THEN.attestThat(actual);
	}

	@Nonnull
	public static <A extends Throwable> Attest.ThrowableAttest<A> attestThat(A actual) {
		return new Attest.ThrowableAttest(actual);
	}

	@Nonnull
	public static Attest.ThrowableAttest<? extends Throwable> attestThatThrownBy(ThrowableAssert.ThrowingCallable shouldRaiseThrowable) {
		return attestThat(Assertions.catchThrowable(shouldRaiseThrowable)).hasBeenThrown();
	}

}