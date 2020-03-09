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
import eu.lunisolar.magma.asserts.opt.*; // NOSONAR
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
public interface MagmaAssertions {

	public static final DefaultMagmaAssertions<ObjectAssert> THEN = new DefaultMagmaAssertions() {
	};

	@Nonnull
	public static <A extends LAction> LActionAssert.The<A> assertAct(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer.LObj1Obj0Cons<T2, T1>, T2, T1> LObj1Obj0ConsAssert.The<A, T2, T1> assertObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func) {
		return new LObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertCons(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LQuadConsumer<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadConsumerAssert.The<A, T1, T2, T3, T4> assertQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintConsumerAssert.The<A, T1, T2, T3, T4, T5> assertQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1ConsAssert.The<A, T1, T3, T2> assertObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func) {
		return new LObj0Obj2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj1BiObj2Cons<T2, T1, T3>, T2, T1, T3> LObj1BiObj2ConsAssert.The<A, T2, T1, T3> assertObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func) {
		return new LObj1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0ConsAssert.The<A, T2, T3, T1> assertObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func) {
		return new LObj1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1ConsAssert.The<A, T3, T1, T2> assertObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func) {
		return new LObj2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0ConsAssert.The<A, T3, T2, T1> assertBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func) {
		return new LBiObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolConsumer> LBoolConsumerAssert.The<A> assertBoolCons(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteConsumer> LByteConsumerAssert.The<A> assertByteCons(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharConsumer> LCharConsumerAssert.The<A> assertCharCons(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblConsumer> LDblConsumerAssert.The<A> assertDblCons(LDblConsumer func) {
		return new LDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltConsumer> LFltConsumerAssert.The<A> assertFltCons(LFltConsumer func) {
		return new LFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LIntConsumer> LIntConsumerAssert.The<A> assertIntCons(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongConsumer> LLongConsumerAssert.The<A> assertLongCons(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtConsumer> LSrtConsumerAssert.The<A> assertSrtCons(LSrtConsumer func) {
		return new LSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> assertBiBoolCons(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer.LBool1Bool0Cons> LBool1Bool0ConsAssert.The<A> assertBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func) {
		return new LBool1Bool0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumer.LByte1Byte0Cons> LByte1Byte0ConsAssert.The<A> assertByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func) {
		return new LByte1Byte0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer.LChar1Char0Cons> LChar1Char0ConsAssert.The<A> assertChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func) {
		return new LChar1Char0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> assertBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDblConsumer.LDbl1Dbl0Cons> LDbl1Dbl0ConsAssert.The<A> assertDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func) {
		return new LDbl1Dbl0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> assertBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFltConsumer.LFlt1Flt0Cons> LFlt1Flt0ConsAssert.The<A> assertFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func) {
		return new LFlt1Flt0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer.LInt1Int0Cons> LInt1Int0ConsAssert.The<A> assertInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func) {
		return new LInt1Int0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer.LLong1Long0Cons> LLong1Long0ConsAssert.The<A> assertLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func) {
		return new LLong1Long0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> assertBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiSrtConsumer.LSrt1Srt0Cons> LSrt1Srt0ConsAssert.The<A> assertSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func) {
		return new LSrt1Srt0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> assertBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolIntConsumer.LIntBoolCons> LIntBoolConsAssert.The<A> assertIntBoolCons(LBoolIntConsumer.LIntBoolCons func) {
		return new LIntBoolConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> assertByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteIntConsumer.LIntByteCons> LIntByteConsAssert.The<A> assertIntByteCons(LByteIntConsumer.LIntByteCons func) {
		return new LIntByteConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> assertCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharIntConsumer.LIntCharCons> LIntCharConsAssert.The<A> assertIntCharCons(LCharIntConsumer.LIntCharCons func) {
		return new LIntCharConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> assertDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblIntConsumer.LIntDblCons> LIntDblConsAssert.The<A> assertIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> assertFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltIntConsumer.LIntFltCons> LIntFltConsAssert.The<A> assertIntFltCons(LFltIntConsumer.LIntFltCons func) {
		return new LIntFltConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> assertLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongIntConsumer.LIntLongCons> LIntLongConsAssert.The<A> assertIntLongCons(LLongIntConsumer.LIntLongCons func) {
		return new LIntLongConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> assertSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtIntConsumer.LIntSrtCons> LIntSrtConsAssert.The<A> assertIntSrtCons(LSrtIntConsumer.LIntSrtCons func) {
		return new LIntSrtConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2>, T1, T2> LObj0Bool2Obj1ConsAssert.The<A, T1, T2> assertObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func) {
		return new LObj0Bool2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1>, T2, T1> LObj1Obj0Bool2ConsAssert.The<A, T2, T1> assertObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func) {
		return new LObj1Obj0Bool2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1>, T2, T1> LObj1Bool2Obj0ConsAssert.The<A, T2, T1> assertObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func) {
		return new LObj1Bool2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2>, T1, T2> LBool2Obj0Obj1ConsAssert.The<A, T1, T2> assertBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func) {
		return new LBool2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1>, T2, T1> LBool2Obj1Obj0ConsAssert.The<A, T2, T1> assertBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func) {
		return new LBool2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, T1, T2> LObj0Byte2Obj1ConsAssert.The<A, T1, T2> assertObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1>, T2, T1> LObj1Obj0Byte2ConsAssert.The<A, T2, T1> assertObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func) {
		return new LObj1Obj0Byte2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1>, T2, T1> LObj1Byte2Obj0ConsAssert.The<A, T2, T1> assertObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2>, T1, T2> LByte2Obj0Obj1ConsAssert.The<A, T1, T2> assertByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func) {
		return new LByte2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1>, T2, T1> LByte2Obj1Obj0ConsAssert.The<A, T2, T1> assertByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func) {
		return new LByte2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2>, T1, T2> LObj0Char2Obj1ConsAssert.The<A, T1, T2> assertObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func) {
		return new LObj0Char2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1>, T2, T1> LObj1Obj0Char2ConsAssert.The<A, T2, T1> assertObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func) {
		return new LObj1Obj0Char2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1>, T2, T1> LObj1Char2Obj0ConsAssert.The<A, T2, T1> assertObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func) {
		return new LObj1Char2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2>, T1, T2> LChar2Obj0Obj1ConsAssert.The<A, T1, T2> assertChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func) {
		return new LChar2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1>, T2, T1> LChar2Obj1Obj0ConsAssert.The<A, T2, T1> assertChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func) {
		return new LChar2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> assertBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2>, T1, T2> LObj0Dbl2Obj1ConsAssert.The<A, T1, T2> assertObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func) {
		return new LObj0Dbl2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1>, T2, T1> LObj1Obj0Dbl2ConsAssert.The<A, T2, T1> assertObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func) {
		return new LObj1Obj0Dbl2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1>, T2, T1> LObj1Dbl2Obj0ConsAssert.The<A, T2, T1> assertObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func) {
		return new LObj1Dbl2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2>, T1, T2> LDbl2Obj0Obj1ConsAssert.The<A, T1, T2> assertDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func) {
		return new LDbl2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1>, T2, T1> LDbl2Obj1Obj0ConsAssert.The<A, T2, T1> assertDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func) {
		return new LDbl2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> assertBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2>, T1, T2> LObj0Flt2Obj1ConsAssert.The<A, T1, T2> assertObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func) {
		return new LObj0Flt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1>, T2, T1> LObj1Obj0Flt2ConsAssert.The<A, T2, T1> assertObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func) {
		return new LObj1Obj0Flt2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1>, T2, T1> LObj1Flt2Obj0ConsAssert.The<A, T2, T1> assertObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func) {
		return new LObj1Flt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2>, T1, T2> LFlt2Obj0Obj1ConsAssert.The<A, T1, T2> assertFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func) {
		return new LFlt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1>, T2, T1> LFlt2Obj1Obj0ConsAssert.The<A, T2, T1> assertFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func) {
		return new LFlt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1>, T2, T1> LObj1Obj0Int2ConsAssert.The<A, T2, T1> assertObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func) {
		return new LObj1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2>, T1, T2> LInt2Obj0Obj1ConsAssert.The<A, T1, T2> assertInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func) {
		return new LInt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1>, T2, T1> LInt2Obj1Obj0ConsAssert.The<A, T2, T1> assertInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func) {
		return new LInt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2>, T1, T2> LObj0Long2Obj1ConsAssert.The<A, T1, T2> assertObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func) {
		return new LObj0Long2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1>, T2, T1> LObj1Obj0Long2ConsAssert.The<A, T2, T1> assertObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func) {
		return new LObj1Obj0Long2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1>, T2, T1> LObj1Long2Obj0ConsAssert.The<A, T2, T1> assertObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func) {
		return new LObj1Long2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2>, T1, T2> LLong2Obj0Obj1ConsAssert.The<A, T1, T2> assertLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func) {
		return new LLong2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1>, T2, T1> LLong2Obj1Obj0ConsAssert.The<A, T2, T1> assertLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func) {
		return new LLong2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> assertBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2>, T1, T2> LObj0Srt2Obj1ConsAssert.The<A, T1, T2> assertObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func) {
		return new LObj0Srt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1>, T2, T1> LObj1Obj0Srt2ConsAssert.The<A, T2, T1> assertObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func) {
		return new LObj1Obj0Srt2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1>, T2, T1> LObj1Srt2Obj0ConsAssert.The<A, T2, T1> assertObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func) {
		return new LObj1Srt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2>, T1, T2> LSrt2Obj0Obj1ConsAssert.The<A, T1, T2> assertSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func) {
		return new LSrt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1>, T2, T1> LSrt2Obj1Obj0ConsAssert.The<A, T2, T1> assertSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func) {
		return new LSrt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer.LBoolObjCons<T>, T> LBoolObjConsAssert.The<A, T> assertBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func) {
		return new LBoolObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer.LByteObjCons<T>, T> LByteObjConsAssert.The<A, T> assertByteObjCons(LObjByteConsumer.LByteObjCons<T> func) {
		return new LByteObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer.LCharObjCons<T>, T> LCharObjConsAssert.The<A, T> assertCharObjCons(LObjCharConsumer.LCharObjCons<T> func) {
		return new LCharObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> assertObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDblConsumer.LDblObjCons<T>, T> LDblObjConsAssert.The<A, T> assertDblObjCons(LObjDblConsumer.LDblObjCons<T> func) {
		return new LDblObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> assertObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFltConsumer.LFltObjCons<T>, T> LFltObjConsAssert.The<A, T> assertFltObjCons(LObjFltConsumer.LFltObjCons<T> func) {
		return new LFltObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer.LIntObjCons<T>, T> LIntObjConsAssert.The<A, T> assertIntObjCons(LObjIntConsumer.LIntObjCons<T> func) {
		return new LIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer.LLongObjCons<T>, T> LLongObjConsAssert.The<A, T> assertLongObjCons(LObjLongConsumer.LLongObjCons<T> func) {
		return new LLongObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> assertObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjSrtConsumer.LSrtObjCons<T>, T> LSrtObjConsAssert.The<A, T> assertSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func) {
		return new LSrtObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> assertTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LObjBoolIntCons<T>, T> LObjBoolIntConsAssert.The<A, T> assertObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func) {
		return new LObjBoolIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LIntObjBoolCons<T>, T> LIntObjBoolConsAssert.The<A, T> assertIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func) {
		return new LIntObjBoolConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LIntBoolObjCons<T>, T> LIntBoolObjConsAssert.The<A, T> assertIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func) {
		return new LIntBoolObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LBoolObjIntCons<T>, T> LBoolObjIntConsAssert.The<A, T> assertBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func) {
		return new LBoolObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer.LBoolIntObjCons<T>, T> LBoolIntObjConsAssert.The<A, T> assertBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func) {
		return new LBoolIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> assertTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LObjByteIntCons<T>, T> LObjByteIntConsAssert.The<A, T> assertObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func) {
		return new LObjByteIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LIntObjByteCons<T>, T> LIntObjByteConsAssert.The<A, T> assertIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func) {
		return new LIntObjByteConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LIntByteObjCons<T>, T> LIntByteObjConsAssert.The<A, T> assertIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func) {
		return new LIntByteObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LByteObjIntCons<T>, T> LByteObjIntConsAssert.The<A, T> assertByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func) {
		return new LByteObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer.LByteIntObjCons<T>, T> LByteIntObjConsAssert.The<A, T> assertByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func) {
		return new LByteIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> assertTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LObjCharIntCons<T>, T> LObjCharIntConsAssert.The<A, T> assertObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func) {
		return new LObjCharIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LIntObjCharCons<T>, T> LIntObjCharConsAssert.The<A, T> assertIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LIntCharObjCons<T>, T> LIntCharObjConsAssert.The<A, T> assertIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func) {
		return new LIntCharObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LCharObjIntCons<T>, T> LCharObjIntConsAssert.The<A, T> assertCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func) {
		return new LCharObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer.LCharIntObjCons<T>, T> LCharIntObjConsAssert.The<A, T> assertCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func) {
		return new LCharIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> assertTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LInt1BiObj2Cons<T1, T2>, T1, T2> LInt1BiObj2ConsAssert.The<A, T1, T2> assertInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func) {
		return new LInt1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LInt1Obj2Obj0Cons<T2, T1>, T2, T1> LInt1Obj2Obj0ConsAssert.The<A, T2, T1> assertInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func) {
		return new LInt1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer.LObj2Int1Obj0Cons<T2, T1>, T2, T1> LObj2Int1Obj0ConsAssert.The<A, T2, T1> assertObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func) {
		return new LObj2Int1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> assertTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LObjDblIntCons<T>, T> LObjDblIntConsAssert.The<A, T> assertObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func) {
		return new LObjDblIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LIntObjDblCons<T>, T> LIntObjDblConsAssert.The<A, T> assertIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func) {
		return new LIntObjDblConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LIntDblObjCons<T>, T> LIntDblObjConsAssert.The<A, T> assertIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func) {
		return new LIntDblObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LDblObjIntCons<T>, T> LDblObjIntConsAssert.The<A, T> assertDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func) {
		return new LDblObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer.LDblIntObjCons<T>, T> LDblIntObjConsAssert.The<A, T> assertDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func) {
		return new LDblIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> assertTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LObjFltIntCons<T>, T> LObjFltIntConsAssert.The<A, T> assertObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func) {
		return new LObjFltIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LIntObjFltCons<T>, T> LIntObjFltConsAssert.The<A, T> assertIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func) {
		return new LIntObjFltConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LIntFltObjCons<T>, T> LIntFltObjConsAssert.The<A, T> assertIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func) {
		return new LIntFltObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LFltObjIntCons<T>, T> LFltObjIntConsAssert.The<A, T> assertFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func) {
		return new LFltObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer.LFltIntObjCons<T>, T> LFltIntObjConsAssert.The<A, T> assertFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func) {
		return new LFltIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> assertTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LObj0Int2Int1Cons<T>, T> LObj0Int2Int1ConsAssert.The<A, T> assertObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func) {
		return new LObj0Int2Int1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt1Obj0Int2Cons<T>, T> LInt1Obj0Int2ConsAssert.The<A, T> assertInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt1Int2Obj0Cons<T>, T> LInt1Int2Obj0ConsAssert.The<A, T> assertInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func) {
		return new LInt1Int2Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> LInt2Obj0Int1ConsAssert.The<A, T> assertInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer.LBiInt1Obj0Cons<T>, T> LBiInt1Obj0ConsAssert.The<A, T> assertBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func) {
		return new LBiInt1Obj0ConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> assertTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LObjLongIntCons<T>, T> LObjLongIntConsAssert.The<A, T> assertObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func) {
		return new LObjLongIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LIntObjLongCons<T>, T> LIntObjLongConsAssert.The<A, T> assertIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func) {
		return new LIntObjLongConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LIntLongObjCons<T>, T> LIntLongObjConsAssert.The<A, T> assertIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func) {
		return new LIntLongObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LLongObjIntCons<T>, T> LLongObjIntConsAssert.The<A, T> assertLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func) {
		return new LLongObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer.LLongIntObjCons<T>, T> LLongIntObjConsAssert.The<A, T> assertLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func) {
		return new LLongIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> assertTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LObjSrtIntCons<T>, T> LObjSrtIntConsAssert.The<A, T> assertObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func) {
		return new LObjSrtIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LIntObjSrtCons<T>, T> LIntObjSrtConsAssert.The<A, T> assertIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func) {
		return new LIntObjSrtConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LIntSrtObjCons<T>, T> LIntSrtObjConsAssert.The<A, T> assertIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func) {
		return new LIntSrtObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LSrtObjIntCons<T>, T> LSrtObjIntConsAssert.The<A, T> assertSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func) {
		return new LSrtObjIntConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer.LSrtIntObjCons<T>, T> LSrtIntObjConsAssert.The<A, T> assertSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func) {
		return new LSrtIntObjConsAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriByteConsumer> LTriByteConsumerAssert.The<A> assertTriByteCons(LTriByteConsumer func) {
		return new LTriByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriCharConsumer> LTriCharConsumerAssert.The<A> assertTriCharCons(LTriCharConsumer func) {
		return new LTriCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriDblConsumer> LTriDblConsumerAssert.The<A> assertTriDblCons(LTriDblConsumer func) {
		return new LTriDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriFltConsumer> LTriFltConsumerAssert.The<A> assertTriFltCons(LTriFltConsumer func) {
		return new LTriFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriIntConsumer> LTriIntConsumerAssert.The<A> assertTriIntCons(LTriIntConsumer func) {
		return new LTriIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriLongConsumer> LTriLongConsumerAssert.The<A> assertTriLongCons(LTriLongConsumer func) {
		return new LTriLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriSrtConsumer> LTriSrtConsumerAssert.The<A> assertTriSrtCons(LTriSrtConsumer func) {
		return new LTriSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBinaryOperator<T>, RS extends Assert<RS, T>, T> LBinaryOperatorAssert.The<A, RS, T> assertBinaryOp(LBinaryOperator<T> func) {
		return new LBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperator, RS extends AbstractByteAssert<RS>> LByteBinaryOperatorAssert.The<A, RS> assertByteBinaryOp(LByteBinaryOperator func) {
		return new LByteBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperator, RS extends AbstractCharacterAssert<RS>> LCharBinaryOperatorAssert.The<A, RS> assertCharBinaryOp(LCharBinaryOperator func) {
		return new LCharBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblBinaryOperator, RS extends AbstractDoubleAssert<RS>> LDblBinaryOperatorAssert.The<A, RS> assertDblBinaryOp(LDblBinaryOperator func) {
		return new LDblBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltBinaryOperator, RS extends AbstractFloatAssert<RS>> LFltBinaryOperatorAssert.The<A, RS> assertFltBinaryOp(LFltBinaryOperator func) {
		return new LFltBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperator, RS extends AbstractIntegerAssert<RS>> LIntBinaryOperatorAssert.The<A, RS> assertIntBinaryOp(LIntBinaryOperator func) {
		return new LIntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalBinaryOperatorAssert.The<A, RS> assertLogicalBinaryOp(LLogicalBinaryOperator func) {
		return new LLogicalBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperator, RS extends AbstractLongAssert<RS>> LLongBinaryOperatorAssert.The<A, RS> assertLongBinaryOp(LLongBinaryOperator func) {
		return new LLongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtBinaryOperator, RS extends AbstractShortAssert<RS>> LSrtBinaryOperatorAssert.The<A, RS> assertSrtBinaryOp(LSrtBinaryOperator func) {
		return new LSrtBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalTernaryOperatorAssert.The<A, RS> assertLogicalTernaryOp(LLogicalTernaryOperator func) {
		return new LLogicalTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperator<T>, RS extends Assert<RS, T>, T> LTernaryOperatorAssert.The<A, RS, T> assertTernaryOp(LTernaryOperator<T> func) {
		return new LTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperator, RS extends AbstractByteAssert<RS>> LByteUnaryOperatorAssert.The<A, RS> assertByteUnaryOp(LByteUnaryOperator func) {
		return new LByteUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperator, RS extends AbstractCharacterAssert<RS>> LCharUnaryOperatorAssert.The<A, RS> assertCharUnaryOp(LCharUnaryOperator func) {
		return new LCharUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblUnaryOperator, RS extends AbstractDoubleAssert<RS>> LDblUnaryOperatorAssert.The<A, RS> assertDblUnaryOp(LDblUnaryOperator func) {
		return new LDblUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltUnaryOperator, RS extends AbstractFloatAssert<RS>> LFltUnaryOperatorAssert.The<A, RS> assertFltUnaryOp(LFltUnaryOperator func) {
		return new LFltUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperator, RS extends AbstractIntegerAssert<RS>> LIntUnaryOperatorAssert.The<A, RS> assertIntUnaryOp(LIntUnaryOperator func) {
		return new LIntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperator, RS extends AbstractBooleanAssert<RS>> LLogicalOperatorAssert.The<A, RS> assertLogicalOp(LLogicalOperator func) {
		return new LLogicalOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperator, RS extends AbstractLongAssert<RS>> LLongUnaryOperatorAssert.The<A, RS> assertLongUnaryOp(LLongUnaryOperator func) {
		return new LLongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtUnaryOperator, RS extends AbstractShortAssert<RS>> LSrtUnaryOperatorAssert.The<A, RS> assertSrtUnaryOp(LSrtUnaryOperator func) {
		return new LSrtUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperator<T>, RS extends Assert<RS, T>, T> LUnaryOperatorAssert.The<A, RS, T> assertUnaryOp(LUnaryOperator<T> func) {
		return new LUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiFunctionAssert.The<A, RS, T1, T2, R> assertBiFunc(LBiFunction<T1, T2, R> func) {
		return new LBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction.LObj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func) {
		return new LObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunction<T, R>, RS extends Assert<RS, R>, T, R> LFunctionAssert.The<A, RS, T, R> assertFunc(LFunction<T, R> func) {
		return new LFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuadFunction<T1, T2, T3, T4, R>, RS extends Assert<RS, R>, T1, T2, T3, T4, R> LQuadFunctionAssert.The<A, RS, T1, T2, T3, T4, R> assertQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) {
		return new LQuadFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuintFunction<T1, T2, T3, T4, T5, R>, RS extends Assert<RS, R>, T1, T2, T3, T4, T5, R> LQuintFunctionAssert.The<A, RS, T1, T2, T3, T4, T5, R> assertQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func) {
		return new LQuintFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends Assert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, RS, T1, T2, T3, R> assertTriFunc(LTriFunction<T1, T2, T3, R> func) {
		return new LTriFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R>, RS extends Assert<RS, R>, T1, T3, T2, R> LObj0Obj2Obj1FuncAssert.The<A, RS, T1, T3, T2, R> assertObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func) {
		return new LObj0Obj2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj1BiObj2Func<T2, T1, T3, R>, RS extends Assert<RS, R>, T2, T1, T3, R> LObj1BiObj2FuncAssert.The<A, RS, T2, T1, T3, R> assertObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func) {
		return new LObj1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R>, RS extends Assert<RS, R>, T2, T3, T1, R> LObj1Obj2Obj0FuncAssert.The<A, RS, T2, T3, T1, R> assertObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func) {
		return new LObj1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R>, RS extends Assert<RS, R>, T3, T1, T2, R> LObj2Obj0Obj1FuncAssert.The<A, RS, T3, T1, T2, R> assertObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func) {
		return new LObj2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R>, RS extends Assert<RS, R>, T3, T2, T1, R> LBiObj1Obj0FuncAssert.The<A, RS, T3, T2, T1, R> assertBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func) {
		return new LBiObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunction, RS extends AbstractByteAssert<RS>> LBoolToByteFunctionAssert.The<A, RS> assertBoolToByteFunc(LBoolToByteFunction func) {
		return new LBoolToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunction, RS extends AbstractCharacterAssert<RS>> LBoolToCharFunctionAssert.The<A, RS> assertBoolToCharFunc(LBoolToCharFunction func) {
		return new LBoolToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDblFunction, RS extends AbstractDoubleAssert<RS>> LBoolToDblFunctionAssert.The<A, RS> assertBoolToDblFunc(LBoolToDblFunction func) {
		return new LBoolToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFltFunction, RS extends AbstractFloatAssert<RS>> LBoolToFltFunctionAssert.The<A, RS> assertBoolToFltFunc(LBoolToFltFunction func) {
		return new LBoolToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunction, RS extends AbstractIntegerAssert<RS>> LBoolToIntFunctionAssert.The<A, RS> assertBoolToIntFunc(LBoolToIntFunction func) {
		return new LBoolToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunction, RS extends AbstractLongAssert<RS>> LBoolToLongFunctionAssert.The<A, RS> assertBoolToLongFunc(LBoolToLongFunction func) {
		return new LBoolToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToSrtFunction, RS extends AbstractShortAssert<RS>> LBoolToSrtFunctionAssert.The<A, RS> assertBoolToSrtFunc(LBoolToSrtFunction func) {
		return new LBoolToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunction, RS extends AbstractCharacterAssert<RS>> LByteToCharFunctionAssert.The<A, RS> assertByteToCharFunc(LByteToCharFunction func) {
		return new LByteToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDblFunction, RS extends AbstractDoubleAssert<RS>> LByteToDblFunctionAssert.The<A, RS> assertByteToDblFunc(LByteToDblFunction func) {
		return new LByteToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFltFunction, RS extends AbstractFloatAssert<RS>> LByteToFltFunctionAssert.The<A, RS> assertByteToFltFunc(LByteToFltFunction func) {
		return new LByteToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunction, RS extends AbstractIntegerAssert<RS>> LByteToIntFunctionAssert.The<A, RS> assertByteToIntFunc(LByteToIntFunction func) {
		return new LByteToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunction, RS extends AbstractLongAssert<RS>> LByteToLongFunctionAssert.The<A, RS> assertByteToLongFunc(LByteToLongFunction func) {
		return new LByteToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToSrtFunction, RS extends AbstractShortAssert<RS>> LByteToSrtFunctionAssert.The<A, RS> assertByteToSrtFunc(LByteToSrtFunction func) {
		return new LByteToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunction, RS extends AbstractByteAssert<RS>> LCharToByteFunctionAssert.The<A, RS> assertCharToByteFunc(LCharToByteFunction func) {
		return new LCharToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDblFunction, RS extends AbstractDoubleAssert<RS>> LCharToDblFunctionAssert.The<A, RS> assertCharToDblFunc(LCharToDblFunction func) {
		return new LCharToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFltFunction, RS extends AbstractFloatAssert<RS>> LCharToFltFunctionAssert.The<A, RS> assertCharToFltFunc(LCharToFltFunction func) {
		return new LCharToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunction, RS extends AbstractIntegerAssert<RS>> LCharToIntFunctionAssert.The<A, RS> assertCharToIntFunc(LCharToIntFunction func) {
		return new LCharToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunction, RS extends AbstractLongAssert<RS>> LCharToLongFunctionAssert.The<A, RS> assertCharToLongFunc(LCharToLongFunction func) {
		return new LCharToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToSrtFunction, RS extends AbstractShortAssert<RS>> LCharToSrtFunctionAssert.The<A, RS> assertCharToSrtFunc(LCharToSrtFunction func) {
		return new LCharToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToByteFunction, RS extends AbstractByteAssert<RS>> LDblToByteFunctionAssert.The<A, RS> assertDblToByteFunc(LDblToByteFunction func) {
		return new LDblToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToCharFunction, RS extends AbstractCharacterAssert<RS>> LDblToCharFunctionAssert.The<A, RS> assertDblToCharFunc(LDblToCharFunction func) {
		return new LDblToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToFltFunction, RS extends AbstractFloatAssert<RS>> LDblToFltFunctionAssert.The<A, RS> assertDblToFltFunc(LDblToFltFunction func) {
		return new LDblToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToIntFunction, RS extends AbstractIntegerAssert<RS>> LDblToIntFunctionAssert.The<A, RS> assertDblToIntFunc(LDblToIntFunction func) {
		return new LDblToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToLongFunction, RS extends AbstractLongAssert<RS>> LDblToLongFunctionAssert.The<A, RS> assertDblToLongFunc(LDblToLongFunction func) {
		return new LDblToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblToSrtFunction, RS extends AbstractShortAssert<RS>> LDblToSrtFunctionAssert.The<A, RS> assertDblToSrtFunc(LDblToSrtFunction func) {
		return new LDblToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToByteFunction, RS extends AbstractByteAssert<RS>> LFltToByteFunctionAssert.The<A, RS> assertFltToByteFunc(LFltToByteFunction func) {
		return new LFltToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToCharFunction, RS extends AbstractCharacterAssert<RS>> LFltToCharFunctionAssert.The<A, RS> assertFltToCharFunc(LFltToCharFunction func) {
		return new LFltToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToDblFunction, RS extends AbstractDoubleAssert<RS>> LFltToDblFunctionAssert.The<A, RS> assertFltToDblFunc(LFltToDblFunction func) {
		return new LFltToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToIntFunction, RS extends AbstractIntegerAssert<RS>> LFltToIntFunctionAssert.The<A, RS> assertFltToIntFunc(LFltToIntFunction func) {
		return new LFltToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToLongFunction, RS extends AbstractLongAssert<RS>> LFltToLongFunctionAssert.The<A, RS> assertFltToLongFunc(LFltToLongFunction func) {
		return new LFltToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltToSrtFunction, RS extends AbstractShortAssert<RS>> LFltToSrtFunctionAssert.The<A, RS> assertFltToSrtFunc(LFltToSrtFunction func) {
		return new LFltToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunction, RS extends AbstractByteAssert<RS>> LIntToByteFunctionAssert.The<A, RS> assertIntToByteFunc(LIntToByteFunction func) {
		return new LIntToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunction, RS extends AbstractCharacterAssert<RS>> LIntToCharFunctionAssert.The<A, RS> assertIntToCharFunc(LIntToCharFunction func) {
		return new LIntToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDblFunction, RS extends AbstractDoubleAssert<RS>> LIntToDblFunctionAssert.The<A, RS> assertIntToDblFunc(LIntToDblFunction func) {
		return new LIntToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFltFunction, RS extends AbstractFloatAssert<RS>> LIntToFltFunctionAssert.The<A, RS> assertIntToFltFunc(LIntToFltFunction func) {
		return new LIntToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunction, RS extends AbstractLongAssert<RS>> LIntToLongFunctionAssert.The<A, RS> assertIntToLongFunc(LIntToLongFunction func) {
		return new LIntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToSrtFunction, RS extends AbstractShortAssert<RS>> LIntToSrtFunctionAssert.The<A, RS> assertIntToSrtFunc(LIntToSrtFunction func) {
		return new LIntToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunction, RS extends AbstractByteAssert<RS>> LLongToByteFunctionAssert.The<A, RS> assertLongToByteFunc(LLongToByteFunction func) {
		return new LLongToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunction, RS extends AbstractCharacterAssert<RS>> LLongToCharFunctionAssert.The<A, RS> assertLongToCharFunc(LLongToCharFunction func) {
		return new LLongToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDblFunction, RS extends AbstractDoubleAssert<RS>> LLongToDblFunctionAssert.The<A, RS> assertLongToDblFunc(LLongToDblFunction func) {
		return new LLongToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFltFunction, RS extends AbstractFloatAssert<RS>> LLongToFltFunctionAssert.The<A, RS> assertLongToFltFunc(LLongToFltFunction func) {
		return new LLongToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunction, RS extends AbstractIntegerAssert<RS>> LLongToIntFunctionAssert.The<A, RS> assertLongToIntFunc(LLongToIntFunction func) {
		return new LLongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToSrtFunction, RS extends AbstractShortAssert<RS>> LLongToSrtFunctionAssert.The<A, RS> assertLongToSrtFunc(LLongToSrtFunction func) {
		return new LLongToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToByteFunction, RS extends AbstractByteAssert<RS>> LSrtToByteFunctionAssert.The<A, RS> assertSrtToByteFunc(LSrtToByteFunction func) {
		return new LSrtToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToCharFunction, RS extends AbstractCharacterAssert<RS>> LSrtToCharFunctionAssert.The<A, RS> assertSrtToCharFunc(LSrtToCharFunction func) {
		return new LSrtToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToDblFunction, RS extends AbstractDoubleAssert<RS>> LSrtToDblFunctionAssert.The<A, RS> assertSrtToDblFunc(LSrtToDblFunction func) {
		return new LSrtToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToFltFunction, RS extends AbstractFloatAssert<RS>> LSrtToFltFunctionAssert.The<A, RS> assertSrtToFltFunc(LSrtToFltFunction func) {
		return new LSrtToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToIntFunction, RS extends AbstractIntegerAssert<RS>> LSrtToIntFunctionAssert.The<A, RS> assertSrtToIntFunc(LSrtToIntFunction func) {
		return new LSrtToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtToLongFunction, RS extends AbstractLongAssert<RS>> LSrtToLongFunctionAssert.The<A, RS> assertSrtToLongFunc(LSrtToLongFunction func) {
		return new LSrtToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction<R>, RS extends Assert<RS, R>, R> LBiBoolFunctionAssert.The<A, RS, R> assertBiBoolFunc(LBiBoolFunction<R> func) {
		return new LBiBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction.LBool1Bool0Func<R>, RS extends Assert<RS, R>, R> LBool1Bool0FuncAssert.The<A, RS, R> assertBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func) {
		return new LBool1Bool0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction<R>, RS extends Assert<RS, R>, R> LBiByteFunctionAssert.The<A, RS, R> assertBiByteFunc(LBiByteFunction<R> func) {
		return new LBiByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction.LByte1Byte0Func<R>, RS extends Assert<RS, R>, R> LByte1Byte0FuncAssert.The<A, RS, R> assertByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func) {
		return new LByte1Byte0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction<R>, RS extends Assert<RS, R>, R> LBiCharFunctionAssert.The<A, RS, R> assertBiCharFunc(LBiCharFunction<R> func) {
		return new LBiCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction.LChar1Char0Func<R>, RS extends Assert<RS, R>, R> LChar1Char0FuncAssert.The<A, RS, R> assertChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func) {
		return new LChar1Char0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblFunction<R>, RS extends Assert<RS, R>, R> LBiDblFunctionAssert.The<A, RS, R> assertBiDblFunc(LBiDblFunction<R> func) {
		return new LBiDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblFunction.LDbl1Dbl0Func<R>, RS extends Assert<RS, R>, R> LDbl1Dbl0FuncAssert.The<A, RS, R> assertDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func) {
		return new LDbl1Dbl0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltFunction<R>, RS extends Assert<RS, R>, R> LBiFltFunctionAssert.The<A, RS, R> assertBiFltFunc(LBiFltFunction<R> func) {
		return new LBiFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltFunction.LFlt1Flt0Func<R>, RS extends Assert<RS, R>, R> LFlt1Flt0FuncAssert.The<A, RS, R> assertFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func) {
		return new LFlt1Flt0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction<R>, RS extends Assert<RS, R>, R> LBiIntFunctionAssert.The<A, RS, R> assertBiIntFunc(LBiIntFunction<R> func) {
		return new LBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction.LInt1Int0Func<R>, RS extends Assert<RS, R>, R> LInt1Int0FuncAssert.The<A, RS, R> assertInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) {
		return new LInt1Int0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction<R>, RS extends Assert<RS, R>, R> LBiLongFunctionAssert.The<A, RS, R> assertBiLongFunc(LBiLongFunction<R> func) {
		return new LBiLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction.LLong1Long0Func<R>, RS extends Assert<RS, R>, R> LLong1Long0FuncAssert.The<A, RS, R> assertLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func) {
		return new LLong1Long0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, RS, T1, T2, R> assertBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Bool2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func) {
		return new LObj0Bool2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Bool2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func) {
		return new LObj1Obj0Bool2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Bool2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func) {
		return new LObj1Bool2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBool2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func) {
		return new LBool2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LBool2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func) {
		return new LBool2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, RS, T1, T2, R> assertBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) {
		return new LBiObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Byte2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func) {
		return new LObj0Byte2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Byte2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func) {
		return new LObj1Obj0Byte2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Byte2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func) {
		return new LObj1Byte2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LByte2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func) {
		return new LByte2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LByte2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func) {
		return new LByte2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, RS, T1, T2, R> assertBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Char2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func) {
		return new LObj0Char2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Char2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func) {
		return new LObj1Obj0Char2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Char2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func) {
		return new LObj1Char2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LChar2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func) {
		return new LChar2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LChar2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func) {
		return new LChar2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, RS, T1, T2, R> assertBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) {
		return new LBiObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Dbl2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func) {
		return new LObj0Dbl2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Dbl2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func) {
		return new LObj1Obj0Dbl2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Dbl2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func) {
		return new LObj1Dbl2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LDbl2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func) {
		return new LDbl2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LDbl2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func) {
		return new LDbl2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, RS, T1, T2, R> assertBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) {
		return new LBiObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Flt2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func) {
		return new LObj0Flt2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Flt2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func) {
		return new LObj1Obj0Flt2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Flt2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func) {
		return new LObj1Flt2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LFlt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func) {
		return new LFlt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LFlt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func) {
		return new LFlt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, RS, T1, T2, R> assertBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) {
		return new LBiObjIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Int2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func) {
		return new LObj1Obj0Int2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LInt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func) {
		return new LInt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LInt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func) {
		return new LInt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, RS, T1, T2, R> assertBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Long2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func) {
		return new LObj0Long2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Long2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func) {
		return new LObj1Obj0Long2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Long2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func) {
		return new LObj1Long2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LLong2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func) {
		return new LLong2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LLong2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func) {
		return new LLong2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, RS, T1, T2, R> assertBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) {
		return new LBiObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObj0Srt2Obj1FuncAssert.The<A, RS, T1, T2, R> assertObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func) {
		return new LObj0Srt2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Obj0Srt2FuncAssert.The<A, RS, T2, T1, R> assertObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func) {
		return new LObj1Obj0Srt2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj1Srt2Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func) {
		return new LObj1Srt2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LSrt2Obj0Obj1FuncAssert.The<A, RS, T1, T2, R> assertSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func) {
		return new LSrt2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LSrt2Obj1Obj0FuncAssert.The<A, RS, T2, T1, R> assertSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func) {
		return new LSrt2Obj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtFunction<R>, RS extends Assert<RS, R>, R> LBiSrtFunctionAssert.The<A, RS, R> assertBiSrtFunc(LBiSrtFunction<R> func) {
		return new LBiSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtFunction.LSrt1Srt0Func<R>, RS extends Assert<RS, R>, R> LSrt1Srt0FuncAssert.The<A, RS, R> assertSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func) {
		return new LSrt1Srt0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunction<R>, RS extends Assert<RS, R>, R> LBoolFunctionAssert.The<A, RS, R> assertBoolFunc(LBoolFunction<R> func) {
		return new LBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunction<R>, RS extends Assert<RS, R>, R> LByteFunctionAssert.The<A, RS, R> assertByteFunc(LByteFunction<R> func) {
		return new LByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunction<R>, RS extends Assert<RS, R>, R> LCharFunctionAssert.The<A, RS, R> assertCharFunc(LCharFunction<R> func) {
		return new LCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblFunction<R>, RS extends Assert<RS, R>, R> LDblFunctionAssert.The<A, RS, R> assertDblFunc(LDblFunction<R> func) {
		return new LDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltFunction<R>, RS extends Assert<RS, R>, R> LFltFunctionAssert.The<A, RS, R> assertFltFunc(LFltFunction<R> func) {
		return new LFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunction<R>, RS extends Assert<RS, R>, R> LIntFunctionAssert.The<A, RS, R> assertIntFunc(LIntFunction<R> func) {
		return new LIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunction<R>, RS extends Assert<RS, R>, R> LLongFunctionAssert.The<A, RS, R> assertLongFunc(LLongFunction<R> func) {
		return new LLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBiIntFunctionAssert.The<A, RS, T, R> assertObjBiIntFunc(LObjBiIntFunction<T, R> func) {
		return new LObjBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LObj0Int2Int1Func<T, R>, RS extends Assert<RS, R>, T, R> LObj0Int2Int1FuncAssert.The<A, RS, T, R> assertObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func) {
		return new LObj0Int2Int1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt1Obj0Int2Func<T, R>, RS extends Assert<RS, R>, T, R> LInt1Obj0Int2FuncAssert.The<A, RS, T, R> assertInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func) {
		return new LInt1Obj0Int2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt1Int2Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LInt1Int2Obj0FuncAssert.The<A, RS, T, R> assertInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func) {
		return new LInt1Int2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LInt2Obj0Int1Func<T, R>, RS extends Assert<RS, R>, T, R> LInt2Obj0Int1FuncAssert.The<A, RS, T, R> assertInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func) {
		return new LInt2Obj0Int1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntFunction.LBiInt1Obj0Func<T, R>, RS extends Assert<RS, R>, T, R> LBiInt1Obj0FuncAssert.The<A, RS, T, R> assertBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func) {
		return new LBiInt1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolFunctionAssert.The<A, RS, T, R> assertObjBoolFunc(LObjBoolFunction<T, R> func) {
		return new LObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction.LBoolObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolObjFuncAssert.The<A, RS, T, R> assertBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func) {
		return new LBoolObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjByteFunctionAssert.The<A, RS, T, R> assertObjByteFunc(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction.LByteObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteObjFuncAssert.The<A, RS, T, R> assertByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func) {
		return new LByteObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjCharFunctionAssert.The<A, RS, T, R> assertObjCharFunc(LObjCharFunction<T, R> func) {
		return new LObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction.LCharObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharObjFuncAssert.The<A, RS, T, R> assertCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func) {
		return new LCharObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjDblFunctionAssert.The<A, RS, T, R> assertObjDblFunc(LObjDblFunction<T, R> func) {
		return new LObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblFunction.LDblObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblObjFuncAssert.The<A, RS, T, R> assertDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func) {
		return new LDblObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjFltFunctionAssert.The<A, RS, T, R> assertObjFltFunc(LObjFltFunction<T, R> func) {
		return new LObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltFunction.LFltObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltObjFuncAssert.The<A, RS, T, R> assertFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func) {
		return new LFltObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntBoolFunctionAssert.The<A, RS, T, R> assertObjIntBoolFunc(LObjIntBoolFunction<T, R> func) {
		return new LObjIntBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LObjBoolIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolIntFuncAssert.The<A, RS, T, R> assertObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func) {
		return new LObjBoolIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LIntObjBoolFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjBoolFuncAssert.The<A, RS, T, R> assertIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func) {
		return new LIntObjBoolFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LIntBoolObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntBoolObjFuncAssert.The<A, RS, T, R> assertIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func) {
		return new LIntBoolObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LBoolObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolObjIntFuncAssert.The<A, RS, T, R> assertBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func) {
		return new LBoolObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction.LBoolIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LBoolIntObjFuncAssert.The<A, RS, T, R> assertBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func) {
		return new LBoolIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntByteFunctionAssert.The<A, RS, T, R> assertObjIntByteFunc(LObjIntByteFunction<T, R> func) {
		return new LObjIntByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LObjByteIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjByteIntFuncAssert.The<A, RS, T, R> assertObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func) {
		return new LObjByteIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LIntObjByteFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjByteFuncAssert.The<A, RS, T, R> assertIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func) {
		return new LIntObjByteFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LIntByteObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntByteObjFuncAssert.The<A, RS, T, R> assertIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func) {
		return new LIntByteObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LByteObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteObjIntFuncAssert.The<A, RS, T, R> assertByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func) {
		return new LByteObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction.LByteIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LByteIntObjFuncAssert.The<A, RS, T, R> assertByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func) {
		return new LByteIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntCharFunctionAssert.The<A, RS, T, R> assertObjIntCharFunc(LObjIntCharFunction<T, R> func) {
		return new LObjIntCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LObjCharIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjCharIntFuncAssert.The<A, RS, T, R> assertObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func) {
		return new LObjCharIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LIntObjCharFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjCharFuncAssert.The<A, RS, T, R> assertIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func) {
		return new LIntObjCharFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LIntCharObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntCharObjFuncAssert.The<A, RS, T, R> assertIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func) {
		return new LIntCharObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LCharObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharObjIntFuncAssert.The<A, RS, T, R> assertCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func) {
		return new LCharObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction.LCharIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LCharIntObjFuncAssert.The<A, RS, T, R> assertCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func) {
		return new LCharIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntDblFunctionAssert.The<A, RS, T, R> assertObjIntDblFunc(LObjIntDblFunction<T, R> func) {
		return new LObjIntDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LObjDblIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjDblIntFuncAssert.The<A, RS, T, R> assertObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func) {
		return new LObjDblIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LIntObjDblFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjDblFuncAssert.The<A, RS, T, R> assertIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func) {
		return new LIntObjDblFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LIntDblObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntDblObjFuncAssert.The<A, RS, T, R> assertIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func) {
		return new LIntDblObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LDblObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblObjIntFuncAssert.The<A, RS, T, R> assertDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func) {
		return new LDblObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction.LDblIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LDblIntObjFuncAssert.The<A, RS, T, R> assertDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func) {
		return new LDblIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntFltFunctionAssert.The<A, RS, T, R> assertObjIntFltFunc(LObjIntFltFunction<T, R> func) {
		return new LObjIntFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LObjFltIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjFltIntFuncAssert.The<A, RS, T, R> assertObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func) {
		return new LObjFltIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LIntObjFltFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjFltFuncAssert.The<A, RS, T, R> assertIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func) {
		return new LIntObjFltFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LIntFltObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntFltObjFuncAssert.The<A, RS, T, R> assertIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func) {
		return new LIntFltObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LFltObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltObjIntFuncAssert.The<A, RS, T, R> assertFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func) {
		return new LFltObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction.LFltIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LFltIntObjFuncAssert.The<A, RS, T, R> assertFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func) {
		return new LFltIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntLongFunctionAssert.The<A, RS, T, R> assertObjIntLongFunc(LObjIntLongFunction<T, R> func) {
		return new LObjIntLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LObjLongIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjLongIntFuncAssert.The<A, RS, T, R> assertObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func) {
		return new LObjLongIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LIntObjLongFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjLongFuncAssert.The<A, RS, T, R> assertIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func) {
		return new LIntObjLongFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LIntLongObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntLongObjFuncAssert.The<A, RS, T, R> assertIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func) {
		return new LIntLongObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LLongObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongObjIntFuncAssert.The<A, RS, T, R> assertLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func) {
		return new LLongObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction.LLongIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongIntObjFuncAssert.The<A, RS, T, R> assertLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func) {
		return new LLongIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, RS, T1, T2, R> assertObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) {
		return new LObjIntObjFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LInt1BiObj2FuncAssert.The<A, RS, T1, T2, R> assertInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func) {
		return new LInt1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LInt1Obj2Obj0FuncAssert.The<A, RS, T2, T1, R> assertInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func) {
		return new LInt1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R>, RS extends Assert<RS, R>, T2, T1, R> LObj2Int1Obj0FuncAssert.The<A, RS, T2, T1, R> assertObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func) {
		return new LObj2Int1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntSrtFunctionAssert.The<A, RS, T, R> assertObjIntSrtFunc(LObjIntSrtFunction<T, R> func) {
		return new LObjIntSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LObjSrtIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LObjSrtIntFuncAssert.The<A, RS, T, R> assertObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func) {
		return new LObjSrtIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LIntObjSrtFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjSrtFuncAssert.The<A, RS, T, R> assertIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func) {
		return new LIntObjSrtFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LIntSrtObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntSrtObjFuncAssert.The<A, RS, T, R> assertIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func) {
		return new LIntSrtObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LSrtObjIntFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtObjIntFuncAssert.The<A, RS, T, R> assertSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func) {
		return new LSrtObjIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction.LSrtIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtIntObjFuncAssert.The<A, RS, T, R> assertSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func) {
		return new LSrtIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjLongFunctionAssert.The<A, RS, T, R> assertObjLongFunc(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction.LLongObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LLongObjFuncAssert.The<A, RS, T, R> assertLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func) {
		return new LLongObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjSrtFunctionAssert.The<A, RS, T, R> assertObjSrtFunc(LObjSrtFunction<T, R> func) {
		return new LObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtFunction.LSrtObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LSrtObjFuncAssert.The<A, RS, T, R> assertSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func) {
		return new LSrtObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiFunction<T, R>, RS extends Assert<RS, R>, T, R> LOiFunctionAssert.The<A, RS, T, R> assertOiFunc(LOiFunction<T, R> func) {
		return new LOiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiFunction.LIntObjFunc<T, R>, RS extends Assert<RS, R>, T, R> LIntObjFuncAssert.The<A, RS, T, R> assertIntObjFunc(LOiFunction.LIntObjFunc<T, R> func) {
		return new LIntObjFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtFunction<R>, RS extends Assert<RS, R>, R> LSrtFunctionAssert.The<A, RS, R> assertSrtFunc(LSrtFunction<R> func) {
		return new LSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunction<R>, RS extends Assert<RS, R>, R> LTriBoolFunctionAssert.The<A, RS, R> assertTriBoolFunc(LTriBoolFunction<R> func) {
		return new LTriBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LOiToByteFunctionAssert.The<A, RS, T> assertOiToByteFunc(LOiToByteFunction<T> func) {
		return new LOiToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToByteFunction.LIntObjToByteFunc<T>, RS extends AbstractByteAssert<RS>, T> LIntObjToByteFuncAssert.The<A, RS, T> assertIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func) {
		return new LIntObjToByteFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LOiToCharFunctionAssert.The<A, RS, T> assertOiToCharFunc(LOiToCharFunction<T> func) {
		return new LOiToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToCharFunction.LIntObjToCharFunc<T>, RS extends AbstractCharacterAssert<RS>, T> LIntObjToCharFuncAssert.The<A, RS, T> assertIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func) {
		return new LIntObjToCharFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToDblFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LOiToDblFunctionAssert.The<A, RS, T> assertOiToDblFunc(LOiToDblFunction<T> func) {
		return new LOiToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToDblFunction.LIntObjToDblFunc<T>, RS extends AbstractDoubleAssert<RS>, T> LIntObjToDblFuncAssert.The<A, RS, T> assertIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func) {
		return new LIntObjToDblFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LOiToFltFunctionAssert.The<A, RS, T> assertOiToFltFunc(LOiToFltFunction<T> func) {
		return new LOiToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToFltFunction.LIntObjToFltFunc<T>, RS extends AbstractFloatAssert<RS>, T> LIntObjToFltFuncAssert.The<A, RS, T> assertIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func) {
		return new LIntObjToFltFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LOiToIntFunctionAssert.The<A, RS, T> assertOiToIntFunc(LOiToIntFunction<T> func) {
		return new LOiToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToIntFunction.LIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjToIntFuncAssert.The<A, RS, T> assertIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func) {
		return new LIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LOiToLongFunctionAssert.The<A, RS, T> assertOiToLongFunc(LOiToLongFunction<T> func) {
		return new LOiToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToLongFunction.LIntObjToLongFunc<T>, RS extends AbstractLongAssert<RS>, T> LIntObjToLongFuncAssert.The<A, RS, T> assertIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		return new LIntObjToLongFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LOiToSrtFunctionAssert.The<A, RS, T> assertOiToSrtFunc(LOiToSrtFunction<T> func) {
		return new LOiToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToSrtFunction.LIntObjToSrtFunc<T>, RS extends AbstractShortAssert<RS>, T> LIntObjToSrtFuncAssert.The<A, RS, T> assertIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func) {
		return new LIntObjToSrtFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieBoolFunctionAssert.The<A, RS, T> assertTieBoolFunc(LTieBoolFunction<T> func) {
		return new LTieBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LObjBoolIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjBoolIntToIntFuncAssert.The<A, RS, T> assertObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func) {
		return new LObjBoolIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LIntObjBoolToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjBoolToIntFuncAssert.The<A, RS, T> assertIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func) {
		return new LIntObjBoolToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LIntBoolObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntBoolObjToIntFuncAssert.The<A, RS, T> assertIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func) {
		return new LIntBoolObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LBoolObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBoolObjIntToIntFuncAssert.The<A, RS, T> assertBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func) {
		return new LBoolObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction.LBoolIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBoolIntObjToIntFuncAssert.The<A, RS, T> assertBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func) {
		return new LBoolIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieByteFunctionAssert.The<A, RS, T> assertTieByteFunc(LTieByteFunction<T> func) {
		return new LTieByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LObjByteIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjByteIntToIntFuncAssert.The<A, RS, T> assertObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		return new LObjByteIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LIntObjByteToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjByteToIntFuncAssert.The<A, RS, T> assertIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func) {
		return new LIntObjByteToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LIntByteObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntByteObjToIntFuncAssert.The<A, RS, T> assertIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func) {
		return new LIntByteObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LByteObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LByteObjIntToIntFuncAssert.The<A, RS, T> assertByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func) {
		return new LByteObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction.LByteIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LByteIntObjToIntFuncAssert.The<A, RS, T> assertByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func) {
		return new LByteIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieCharFunctionAssert.The<A, RS, T> assertTieCharFunc(LTieCharFunction<T> func) {
		return new LTieCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LObjCharIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjCharIntToIntFuncAssert.The<A, RS, T> assertObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func) {
		return new LObjCharIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LIntObjCharToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjCharToIntFuncAssert.The<A, RS, T> assertIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func) {
		return new LIntObjCharToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LIntCharObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntCharObjToIntFuncAssert.The<A, RS, T> assertIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func) {
		return new LIntCharObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LCharObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LCharObjIntToIntFuncAssert.The<A, RS, T> assertCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func) {
		return new LCharObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction.LCharIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LCharIntObjToIntFuncAssert.The<A, RS, T> assertCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func) {
		return new LCharIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieDblFunctionAssert.The<A, RS, T> assertTieDblFunc(LTieDblFunction<T> func) {
		return new LTieDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LObjDblIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjDblIntToIntFuncAssert.The<A, RS, T> assertObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func) {
		return new LObjDblIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LIntObjDblToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjDblToIntFuncAssert.The<A, RS, T> assertIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func) {
		return new LIntObjDblToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LIntDblObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntDblObjToIntFuncAssert.The<A, RS, T> assertIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func) {
		return new LIntDblObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LDblObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LDblObjIntToIntFuncAssert.The<A, RS, T> assertDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func) {
		return new LDblObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction.LDblIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LDblIntObjToIntFuncAssert.The<A, RS, T> assertDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func) {
		return new LDblIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieFltFunctionAssert.The<A, RS, T> assertTieFltFunc(LTieFltFunction<T> func) {
		return new LTieFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LObjFltIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjFltIntToIntFuncAssert.The<A, RS, T> assertObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func) {
		return new LObjFltIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LIntObjFltToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjFltToIntFuncAssert.The<A, RS, T> assertIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func) {
		return new LIntObjFltToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LIntFltObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntFltObjToIntFuncAssert.The<A, RS, T> assertIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func) {
		return new LIntFltObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LFltObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LFltObjIntToIntFuncAssert.The<A, RS, T> assertFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func) {
		return new LFltObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction.LFltIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LFltIntObjToIntFuncAssert.The<A, RS, T> assertFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func) {
		return new LFltIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LTieFunctionAssert.The<A, RS, T1, T2> assertTieFunc(LTieFunction<T1, T2> func) {
		return new LTieFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LObj0Obj2Int1ToIntFuncAssert.The<A, RS, T1, T2> assertObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func) {
		return new LObj0Obj2Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LInt1BiObj2ToIntFunc<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LInt1BiObj2ToIntFuncAssert.The<A, RS, T1, T2> assertInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func) {
		return new LInt1BiObj2ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LInt1Obj2Obj0ToIntFuncAssert.The<A, RS, T2, T1> assertInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func) {
		return new LInt1Obj2Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LObj2Obj0Int1ToIntFuncAssert.The<A, RS, T2, T1> assertObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		return new LObj2Obj0Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LObj2Int1Obj0ToIntFuncAssert.The<A, RS, T2, T1> assertObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func) {
		return new LObj2Int1Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieIntFunctionAssert.The<A, RS, T> assertTieIntFunc(LTieIntFunction<T> func) {
		return new LTieIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LObj0Int2Int1ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObj0Int2Int1ToIntFuncAssert.The<A, RS, T> assertObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func) {
		return new LObj0Int2Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt1Obj0Int2ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt1Obj0Int2ToIntFuncAssert.The<A, RS, T> assertInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func) {
		return new LInt1Obj0Int2ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt1Int2Obj0ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt1Int2Obj0ToIntFuncAssert.The<A, RS, T> assertInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func) {
		return new LInt1Int2Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LInt2Obj0Int1ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LInt2Obj0Int1ToIntFuncAssert.The<A, RS, T> assertInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func) {
		return new LInt2Obj0Int1ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction.LBiInt1Obj0ToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LBiInt1Obj0ToIntFuncAssert.The<A, RS, T> assertBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func) {
		return new LBiInt1Obj0ToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieLongFunctionAssert.The<A, RS, T> assertTieLongFunc(LTieLongFunction<T> func) {
		return new LTieLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LObjLongIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjLongIntToIntFuncAssert.The<A, RS, T> assertObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func) {
		return new LObjLongIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LIntObjLongToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjLongToIntFuncAssert.The<A, RS, T> assertIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func) {
		return new LIntObjLongToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LIntLongObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntLongObjToIntFuncAssert.The<A, RS, T> assertIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func) {
		return new LIntLongObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LLongObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LLongObjIntToIntFuncAssert.The<A, RS, T> assertLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func) {
		return new LLongObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction.LLongIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LLongIntObjToIntFuncAssert.The<A, RS, T> assertLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func) {
		return new LLongIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieSrtFunctionAssert.The<A, RS, T> assertTieSrtFunc(LTieSrtFunction<T> func) {
		return new LTieSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LObjSrtIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LObjSrtIntToIntFuncAssert.The<A, RS, T> assertObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func) {
		return new LObjSrtIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LIntObjSrtToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntObjSrtToIntFuncAssert.The<A, RS, T> assertIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func) {
		return new LIntObjSrtToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LIntSrtObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LIntSrtObjToIntFuncAssert.The<A, RS, T> assertIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func) {
		return new LIntSrtObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LSrtObjIntToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LSrtObjIntToIntFuncAssert.The<A, RS, T> assertSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func) {
		return new LSrtObjIntToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction.LSrtIntObjToIntFunc<T>, RS extends AbstractIntegerAssert<RS>, T> LSrtIntObjToIntFuncAssert.The<A, RS, T> assertSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func) {
		return new LSrtIntObjToIntFuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.The<A, RS, T1, T2> assertToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		return new LToByteBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction.LToByteObj1Obj0Func<T2, T1>, RS extends AbstractByteAssert<RS>, T2, T1> LToByteObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func) {
		return new LToByteObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LToByteFunctionAssert.The<A, RS, T> assertToByteFunc(LToByteFunction<T> func) {
		return new LToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> LToCharBiFunctionAssert.The<A, RS, T1, T2> assertToCharBiFunc(LToCharBiFunction<T1, T2> func) {
		return new LToCharBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction.LToCharObj1Obj0Func<T2, T1>, RS extends AbstractCharacterAssert<RS>, T2, T1> LToCharObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func) {
		return new LToCharObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.The<A, RS, T> assertToCharFunc(LToCharFunction<T> func) {
		return new LToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDblBiFunctionAssert.The<A, RS, T1, T2> assertToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		return new LToDblBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblBiFunction.LToDblObj1Obj0Func<T2, T1>, RS extends AbstractDoubleAssert<RS>, T2, T1> LToDblObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func) {
		return new LToDblObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LToDblFunctionAssert.The<A, RS, T> assertToDblFunc(LToDblFunction<T> func) {
		return new LToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> LToFltBiFunctionAssert.The<A, RS, T1, T2> assertToFltBiFunc(LToFltBiFunction<T1, T2> func) {
		return new LToFltBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltBiFunction.LToFltObj1Obj0Func<T2, T1>, RS extends AbstractFloatAssert<RS>, T2, T1> LToFltObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func) {
		return new LToFltObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFltFunctionAssert.The<A, RS, T> assertToFltFunc(LToFltFunction<T> func) {
		return new LToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.The<A, RS, T1, T2> assertToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		return new LToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction.LToIntObj1Obj0Func<T2, T1>, RS extends AbstractIntegerAssert<RS>, T2, T1> LToIntObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func) {
		return new LToIntObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LToIntFunctionAssert.The<A, RS, T> assertToIntFunc(LToIntFunction<T> func) {
		return new LToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction<T1, T2, T3>, RS extends AbstractIntegerAssert<RS>, T1, T2, T3> LToIntTriFunctionAssert.The<A, RS, T1, T2, T3> assertToIntTriFunc(LToIntTriFunction<T1, T2, T3> func) {
		return new LToIntTriFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2>, RS extends AbstractIntegerAssert<RS>, T1, T3, T2> LToIntObj0Obj2Obj1FuncAssert.The<A, RS, T1, T3, T2> assertToIntObj0Obj2Obj1Func(
			LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func) {
		return new LToIntObj0Obj2Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3>, RS extends AbstractIntegerAssert<RS>, T2, T1, T3> LToIntObj1BiObj2FuncAssert.The<A, RS, T2, T1, T3> assertToIntObj1BiObj2Func(
			LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		return new LToIntObj1BiObj2FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1>, RS extends AbstractIntegerAssert<RS>, T2, T3, T1> LToIntObj1Obj2Obj0FuncAssert.The<A, RS, T2, T3, T1> assertToIntObj1Obj2Obj0Func(
			LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func) {
		return new LToIntObj1Obj2Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2>, RS extends AbstractIntegerAssert<RS>, T3, T1, T2> LToIntObj2Obj0Obj1FuncAssert.The<A, RS, T3, T1, T2> assertToIntObj2Obj0Obj1Func(
			LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func) {
		return new LToIntObj2Obj0Obj1FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1>, RS extends AbstractIntegerAssert<RS>, T3, T2, T1> LToIntBiObj1Obj0FuncAssert.The<A, RS, T3, T2, T1> assertToIntBiObj1Obj0Func(
			LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func) {
		return new LToIntBiObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.The<A, RS, T1, T2> assertToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction.LToLongObj1Obj0Func<T2, T1>, RS extends AbstractLongAssert<RS>, T2, T1> LToLongObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func) {
		return new LToLongObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LToLongFunctionAssert.The<A, RS, T> assertToLongFunc(LToLongFunction<T> func) {
		return new LToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> LToSrtBiFunctionAssert.The<A, RS, T1, T2> assertToSrtBiFunc(LToSrtBiFunction<T1, T2> func) {
		return new LToSrtBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1>, RS extends AbstractShortAssert<RS>, T2, T1> LToSrtObj1Obj0FuncAssert.The<A, RS, T2, T1> assertToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func) {
		return new LToSrtObj1Obj0FuncAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LToSrtFunctionAssert.The<A, RS, T> assertToSrtFunc(LToSrtFunction<T> func) {
		return new LToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.The<A, RS> assertBiBytePred(LBiBytePredicate func) {
		return new LBiBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate.LByte1Byte0Pred, RS extends AbstractBooleanAssert<RS>> LByte1Byte0PredAssert.The<A, RS> assertByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func) {
		return new LByte1Byte0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.The<A, RS> assertBiCharPred(LBiCharPredicate func) {
		return new LBiCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate.LChar1Char0Pred, RS extends AbstractBooleanAssert<RS>> LChar1Char0PredAssert.The<A, RS> assertChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func) {
		return new LChar1Char0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblPredicate, RS extends AbstractBooleanAssert<RS>> LBiDblPredicateAssert.The<A, RS> assertBiDblPred(LBiDblPredicate func) {
		return new LBiDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblPredicate.LDbl1Dbl0Pred, RS extends AbstractBooleanAssert<RS>> LDbl1Dbl0PredAssert.The<A, RS> assertDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func) {
		return new LDbl1Dbl0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltPredicate, RS extends AbstractBooleanAssert<RS>> LBiFltPredicateAssert.The<A, RS> assertBiFltPred(LBiFltPredicate func) {
		return new LBiFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltPredicate.LFlt1Flt0Pred, RS extends AbstractBooleanAssert<RS>> LFlt1Flt0PredAssert.The<A, RS> assertFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func) {
		return new LFlt1Flt0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.The<A, RS> assertBiIntPred(LBiIntPredicate func) {
		return new LBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate.LInt1Int0Pred, RS extends AbstractBooleanAssert<RS>> LInt1Int0PredAssert.The<A, RS> assertInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func) {
		return new LInt1Int0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.The<A, RS> assertBiLongPred(LBiLongPredicate func) {
		return new LBiLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate.LLong1Long0Pred, RS extends AbstractBooleanAssert<RS>> LLong1Long0PredAssert.The<A, RS> assertLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func) {
		return new LLong1Long0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBoolPredicateAssert.The<A, RS, T1, T2> assertBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		return new LBiObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Bool2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func) {
		return new LObj0Bool2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Bool2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func) {
		return new LObj1Obj0Bool2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Bool2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func) {
		return new LObj1Bool2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBool2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func) {
		return new LBool2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LBool2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func) {
		return new LBool2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.The<A, RS, T1, T2> assertBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Byte2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func) {
		return new LObj0Byte2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Byte2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func) {
		return new LObj1Obj0Byte2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Byte2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func) {
		return new LObj1Byte2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LByte2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func) {
		return new LByte2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LByte2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		return new LByte2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.The<A, RS, T1, T2> assertBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		return new LBiObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Char2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func) {
		return new LObj0Char2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Char2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func) {
		return new LObj1Obj0Char2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Char2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func) {
		return new LObj1Char2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LChar2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func) {
		return new LChar2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LChar2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func) {
		return new LChar2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDblPredicateAssert.The<A, RS, T1, T2> assertBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		return new LBiObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Dbl2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func) {
		return new LObj0Dbl2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Dbl2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func) {
		return new LObj1Obj0Dbl2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Dbl2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func) {
		return new LObj1Dbl2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LDbl2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func) {
		return new LDbl2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LDbl2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func) {
		return new LDbl2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFltPredicateAssert.The<A, RS, T1, T2> assertBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		return new LBiObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Flt2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func) {
		return new LObj0Flt2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Flt2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func) {
		return new LObj1Obj0Flt2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Flt2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func) {
		return new LObj1Flt2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LFlt2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func) {
		return new LFlt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LFlt2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func) {
		return new LFlt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.The<A, RS, T1, T2> assertBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Int2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func) {
		return new LObj1Obj0Int2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LInt2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func) {
		return new LInt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LInt2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func) {
		return new LInt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.The<A, RS, T1, T2> assertBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		return new LBiObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Long2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func) {
		return new LObj0Long2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Long2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func) {
		return new LObj1Obj0Long2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Long2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func) {
		return new LObj1Long2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LLong2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func) {
		return new LLong2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LLong2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func) {
		return new LLong2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjSrtPredicateAssert.The<A, RS, T1, T2> assertBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		return new LBiObjSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObj0Srt2Obj1PredAssert.The<A, RS, T1, T2> assertObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func) {
		return new LObj0Srt2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0Srt2PredAssert.The<A, RS, T2, T1> assertObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func) {
		return new LObj1Obj0Srt2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Srt2Obj0PredAssert.The<A, RS, T2, T1> assertObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func) {
		return new LObj1Srt2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LSrt2Obj0Obj1PredAssert.The<A, RS, T1, T2> assertSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func) {
		return new LSrt2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LSrt2Obj1Obj0PredAssert.The<A, RS, T2, T1> assertSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func) {
		return new LSrt2Obj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> assertBiPred(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate.LObj1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj1Obj0PredAssert.The<A, RS, T2, T1> assertObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func) {
		return new LObj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtPredicate, RS extends AbstractBooleanAssert<RS>> LBiSrtPredicateAssert.The<A, RS> assertBiSrtPred(LBiSrtPredicate func) {
		return new LBiSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtPredicate.LSrt1Srt0Pred, RS extends AbstractBooleanAssert<RS>> LSrt1Srt0PredAssert.The<A, RS> assertSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func) {
		return new LSrt1Srt0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolIntPredicate, RS extends AbstractBooleanAssert<RS>> LBoolIntPredicateAssert.The<A, RS> assertBoolIntPred(LBoolIntPredicate func) {
		return new LBoolIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolIntPredicate.LIntBoolPred, RS extends AbstractBooleanAssert<RS>> LIntBoolPredAssert.The<A, RS> assertIntBoolPred(LBoolIntPredicate.LIntBoolPred func) {
		return new LIntBoolPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteIntPredicate, RS extends AbstractBooleanAssert<RS>> LByteIntPredicateAssert.The<A, RS> assertByteIntPred(LByteIntPredicate func) {
		return new LByteIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteIntPredicate.LIntBytePred, RS extends AbstractBooleanAssert<RS>> LIntBytePredAssert.The<A, RS> assertIntBytePred(LByteIntPredicate.LIntBytePred func) {
		return new LIntBytePredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicate, RS extends AbstractBooleanAssert<RS>> LBytePredicateAssert.The<A, RS> assertBytePred(LBytePredicate func) {
		return new LBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharIntPredicate, RS extends AbstractBooleanAssert<RS>> LCharIntPredicateAssert.The<A, RS> assertCharIntPred(LCharIntPredicate func) {
		return new LCharIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharIntPredicate.LIntCharPred, RS extends AbstractBooleanAssert<RS>> LIntCharPredAssert.The<A, RS> assertIntCharPred(LCharIntPredicate.LIntCharPred func) {
		return new LIntCharPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.The<A, RS> assertCharPred(LCharPredicate func) {
		return new LCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblIntPredicate, RS extends AbstractBooleanAssert<RS>> LDblIntPredicateAssert.The<A, RS> assertDblIntPred(LDblIntPredicate func) {
		return new LDblIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblIntPredicate.LIntDblPred, RS extends AbstractBooleanAssert<RS>> LIntDblPredAssert.The<A, RS> assertIntDblPred(LDblIntPredicate.LIntDblPred func) {
		return new LIntDblPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblPredicate, RS extends AbstractBooleanAssert<RS>> LDblPredicateAssert.The<A, RS> assertDblPred(LDblPredicate func) {
		return new LDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltIntPredicate, RS extends AbstractBooleanAssert<RS>> LFltIntPredicateAssert.The<A, RS> assertFltIntPred(LFltIntPredicate func) {
		return new LFltIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltIntPredicate.LIntFltPred, RS extends AbstractBooleanAssert<RS>> LIntFltPredAssert.The<A, RS> assertIntFltPred(LFltIntPredicate.LIntFltPred func) {
		return new LIntFltPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltPredicate, RS extends AbstractBooleanAssert<RS>> LFltPredicateAssert.The<A, RS> assertFltPred(LFltPredicate func) {
		return new LFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicate, RS extends AbstractBooleanAssert<RS>> LIntPredicateAssert.The<A, RS> assertIntPred(LIntPredicate func) {
		return new LIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongIntPredicate, RS extends AbstractBooleanAssert<RS>> LLongIntPredicateAssert.The<A, RS> assertLongIntPred(LLongIntPredicate func) {
		return new LLongIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongIntPredicate.LIntLongPred, RS extends AbstractBooleanAssert<RS>> LIntLongPredAssert.The<A, RS> assertIntLongPred(LLongIntPredicate.LIntLongPred func) {
		return new LIntLongPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.The<A, RS> assertLongPred(LLongPredicate func) {
		return new LLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBiIntPredicateAssert.The<A, RS, T> assertObjBiIntPred(LObjBiIntPredicate<T> func) {
		return new LObjBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LObj0Int2Int1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LObj0Int2Int1PredAssert.The<A, RS, T> assertObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func) {
		return new LObj0Int2Int1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt1Obj0Int2Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt1Obj0Int2PredAssert.The<A, RS, T> assertInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func) {
		return new LInt1Obj0Int2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt1Int2Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt1Int2Obj0PredAssert.The<A, RS, T> assertInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func) {
		return new LInt1Int2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LInt2Obj0Int1Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LInt2Obj0Int1PredAssert.The<A, RS, T> assertInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func) {
		return new LInt2Obj0Int1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate.LBiInt1Obj0Pred<T>, RS extends AbstractBooleanAssert<RS>, T> LBiInt1Obj0PredAssert.The<A, RS, T> assertBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		return new LBiInt1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolPredicateAssert.The<A, RS, T> assertObjBoolPred(LObjBoolPredicate<T> func) {
		return new LObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate.LBoolObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolObjPredAssert.The<A, RS, T> assertBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func) {
		return new LBoolObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.The<A, RS, T> assertObjBytePred(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate.LByteObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteObjPredAssert.The<A, RS, T> assertByteObjPred(LObjBytePredicate.LByteObjPred<T> func) {
		return new LByteObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.The<A, RS, T> assertObjCharPred(LObjCharPredicate<T> func) {
		return new LObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate.LCharObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharObjPredAssert.The<A, RS, T> assertCharObjPred(LObjCharPredicate.LCharObjPred<T> func) {
		return new LCharObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDblPredicateAssert.The<A, RS, T> assertObjDblPred(LObjDblPredicate<T> func) {
		return new LObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblPredicate.LDblObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblObjPredAssert.The<A, RS, T> assertDblObjPred(LObjDblPredicate.LDblObjPred<T> func) {
		return new LDblObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFltPredicateAssert.The<A, RS, T> assertObjFltPred(LObjFltPredicate<T> func) {
		return new LObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltPredicate.LFltObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltObjPredAssert.The<A, RS, T> assertFltObjPred(LObjFltPredicate.LFltObjPred<T> func) {
		return new LFltObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBoolPredicateAssert.The<A, RS, T> assertObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		return new LObjIntBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LObjBoolIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolIntPredAssert.The<A, RS, T> assertObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func) {
		return new LObjBoolIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LIntObjBoolPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjBoolPredAssert.The<A, RS, T> assertIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func) {
		return new LIntObjBoolPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LIntBoolObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntBoolObjPredAssert.The<A, RS, T> assertIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func) {
		return new LIntBoolObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LBoolObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolObjIntPredAssert.The<A, RS, T> assertBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func) {
		return new LBoolObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate.LBoolIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LBoolIntObjPredAssert.The<A, RS, T> assertBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func) {
		return new LBoolIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBytePredicateAssert.The<A, RS, T> assertObjIntBytePred(LObjIntBytePredicate<T> func) {
		return new LObjIntBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LObjByteIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjByteIntPredAssert.The<A, RS, T> assertObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func) {
		return new LObjByteIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LIntObjBytePred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjBytePredAssert.The<A, RS, T> assertIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func) {
		return new LIntObjBytePredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LIntByteObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntByteObjPredAssert.The<A, RS, T> assertIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func) {
		return new LIntByteObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LByteObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteObjIntPredAssert.The<A, RS, T> assertByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func) {
		return new LByteObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate.LByteIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LByteIntObjPredAssert.The<A, RS, T> assertByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func) {
		return new LByteIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntCharPredicateAssert.The<A, RS, T> assertObjIntCharPred(LObjIntCharPredicate<T> func) {
		return new LObjIntCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LObjCharIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharIntPredAssert.The<A, RS, T> assertObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func) {
		return new LObjCharIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LIntObjCharPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjCharPredAssert.The<A, RS, T> assertIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func) {
		return new LIntObjCharPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LIntCharObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntCharObjPredAssert.The<A, RS, T> assertIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func) {
		return new LIntCharObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LCharObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharObjIntPredAssert.The<A, RS, T> assertCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func) {
		return new LCharObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate.LCharIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LCharIntObjPredAssert.The<A, RS, T> assertCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func) {
		return new LCharIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntDblPredicateAssert.The<A, RS, T> assertObjIntDblPred(LObjIntDblPredicate<T> func) {
		return new LObjIntDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LObjDblIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDblIntPredAssert.The<A, RS, T> assertObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func) {
		return new LObjDblIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LIntObjDblPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjDblPredAssert.The<A, RS, T> assertIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func) {
		return new LIntObjDblPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LIntDblObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntDblObjPredAssert.The<A, RS, T> assertIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func) {
		return new LIntDblObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LDblObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblObjIntPredAssert.The<A, RS, T> assertDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func) {
		return new LDblObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate.LDblIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LDblIntObjPredAssert.The<A, RS, T> assertDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func) {
		return new LDblIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntFltPredicateAssert.The<A, RS, T> assertObjIntFltPred(LObjIntFltPredicate<T> func) {
		return new LObjIntFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LObjFltIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFltIntPredAssert.The<A, RS, T> assertObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func) {
		return new LObjFltIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LIntObjFltPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjFltPredAssert.The<A, RS, T> assertIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func) {
		return new LIntObjFltPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LIntFltObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntFltObjPredAssert.The<A, RS, T> assertIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func) {
		return new LIntFltObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LFltObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltObjIntPredAssert.The<A, RS, T> assertFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func) {
		return new LFltObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate.LFltIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LFltIntObjPredAssert.The<A, RS, T> assertFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func) {
		return new LFltIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntLongPredicateAssert.The<A, RS, T> assertObjIntLongPred(LObjIntLongPredicate<T> func) {
		return new LObjIntLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LObjLongIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongIntPredAssert.The<A, RS, T> assertObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func) {
		return new LObjLongIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LIntObjLongPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjLongPredAssert.The<A, RS, T> assertIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func) {
		return new LIntObjLongPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LIntLongObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntLongObjPredAssert.The<A, RS, T> assertIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func) {
		return new LIntLongObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LLongObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongObjIntPredAssert.The<A, RS, T> assertLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func) {
		return new LLongObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate.LLongIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongIntObjPredAssert.The<A, RS, T> assertLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func) {
		return new LLongIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObjIntObjPredicateAssert.The<A, RS, T1, T2> assertObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		return new LObjIntObjPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LInt1BiObj2PredAssert.The<A, RS, T1, T2> assertInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func) {
		return new LInt1BiObj2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LInt1Obj2Obj0PredAssert.The<A, RS, T2, T1> assertInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func) {
		return new LInt1Obj2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1>, RS extends AbstractBooleanAssert<RS>, T2, T1> LObj2Int1Obj0PredAssert.The<A, RS, T2, T1> assertObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func) {
		return new LObj2Int1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.The<A, RS, T> assertObjIntPred(LObjIntPredicate<T> func) {
		return new LObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate.LIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjPredAssert.The<A, RS, T> assertIntObjPred(LObjIntPredicate.LIntObjPred<T> func) {
		return new LIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntSrtPredicateAssert.The<A, RS, T> assertObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		return new LObjIntSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LObjSrtIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LObjSrtIntPredAssert.The<A, RS, T> assertObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func) {
		return new LObjSrtIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntObjSrtPredAssert.The<A, RS, T> assertIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		return new LIntObjSrtPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LIntSrtObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LIntSrtObjPredAssert.The<A, RS, T> assertIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func) {
		return new LIntSrtObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LSrtObjIntPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtObjIntPredAssert.The<A, RS, T> assertSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func) {
		return new LSrtObjIntPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate.LSrtIntObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtIntObjPredAssert.The<A, RS, T> assertSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func) {
		return new LSrtIntObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.The<A, RS, T> assertObjLongPred(LObjLongPredicate<T> func) {
		return new LObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate.LLongObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LLongObjPredAssert.The<A, RS, T> assertLongObjPred(LObjLongPredicate.LLongObjPred<T> func) {
		return new LLongObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjSrtPredicateAssert.The<A, RS, T> assertObjSrtPred(LObjSrtPredicate<T> func) {
		return new LObjSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtPredicate.LSrtObjPred<T>, RS extends AbstractBooleanAssert<RS>, T> LSrtObjPredAssert.The<A, RS, T> assertSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func) {
		return new LSrtObjPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LPredicateAssert.The<A, RS, T> assertPred(LPredicate<T> func) {
		return new LPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuadPredicate<T1, T2, T3, T4>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, T4> LQuadPredicateAssert.The<A, RS, T1, T2, T3, T4> assertQuadPred(LQuadPredicate<T1, T2, T3, T4> func) {
		return new LQuadPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuintPredicate<T1, T2, T3, T4, T5>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, T4, T5> LQuintPredicateAssert.The<A, RS, T1, T2, T3, T4, T5> assertQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func) {
		return new LQuintPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtIntPredicate, RS extends AbstractBooleanAssert<RS>> LSrtIntPredicateAssert.The<A, RS> assertSrtIntPred(LSrtIntPredicate func) {
		return new LSrtIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtIntPredicate.LIntSrtPred, RS extends AbstractBooleanAssert<RS>> LIntSrtPredAssert.The<A, RS> assertIntSrtPred(LSrtIntPredicate.LIntSrtPred func) {
		return new LIntSrtPredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtPredicate, RS extends AbstractBooleanAssert<RS>> LSrtPredicateAssert.The<A, RS> assertSrtPred(LSrtPredicate func) {
		return new LSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBytePredicate, RS extends AbstractBooleanAssert<RS>> LTriBytePredicateAssert.The<A, RS> assertTriBytePred(LTriBytePredicate func) {
		return new LTriBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriCharPredicate, RS extends AbstractBooleanAssert<RS>> LTriCharPredicateAssert.The<A, RS> assertTriCharPred(LTriCharPredicate func) {
		return new LTriCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriDblPredicate, RS extends AbstractBooleanAssert<RS>> LTriDblPredicateAssert.The<A, RS> assertTriDblPred(LTriDblPredicate func) {
		return new LTriDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFltPredicate, RS extends AbstractBooleanAssert<RS>> LTriFltPredicateAssert.The<A, RS> assertTriFltPred(LTriFltPredicate func) {
		return new LTriFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriIntPredicate, RS extends AbstractBooleanAssert<RS>> LTriIntPredicateAssert.The<A, RS> assertTriIntPred(LTriIntPredicate func) {
		return new LTriIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriLongPredicate, RS extends AbstractBooleanAssert<RS>> LTriLongPredicateAssert.The<A, RS> assertTriLongPred(LTriLongPredicate func) {
		return new LTriLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.The<A, RS, T1, T2, T3> assertTriPred(LTriPredicate<T1, T2, T3> func) {
		return new LTriPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2>, RS extends AbstractBooleanAssert<RS>, T1, T3, T2> LObj0Obj2Obj1PredAssert.The<A, RS, T1, T3, T2> assertObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func) {
		return new LObj0Obj2Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj1BiObj2Pred<T2, T1, T3>, RS extends AbstractBooleanAssert<RS>, T2, T1, T3> LObj1BiObj2PredAssert.The<A, RS, T2, T1, T3> assertObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func) {
		return new LObj1BiObj2PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1>, RS extends AbstractBooleanAssert<RS>, T2, T3, T1> LObj1Obj2Obj0PredAssert.The<A, RS, T2, T3, T1> assertObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func) {
		return new LObj1Obj2Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2>, RS extends AbstractBooleanAssert<RS>, T3, T1, T2> LObj2Obj0Obj1PredAssert.The<A, RS, T3, T1, T2> assertObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func) {
		return new LObj2Obj0Obj1PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1>, RS extends AbstractBooleanAssert<RS>, T3, T2, T1> LBiObj1Obj0PredAssert.The<A, RS, T3, T2, T1> assertBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func) {
		return new LBiObj1Obj0PredAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriSrtPredicate, RS extends AbstractBooleanAssert<RS>> LTriSrtPredicateAssert.The<A, RS> assertTriSrtPred(LTriSrtPredicate func) {
		return new LTriSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplier, RS extends AbstractBooleanAssert<RS>> LBoolSupplierAssert.The<A, RS> assertBoolSup(LBoolSupplier func) {
		return new LBoolSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplier, RS extends AbstractByteAssert<RS>> LByteSupplierAssert.The<A, RS> assertByteSup(LByteSupplier func) {
		return new LByteSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplier, RS extends AbstractCharacterAssert<RS>> LCharSupplierAssert.The<A, RS> assertCharSup(LCharSupplier func) {
		return new LCharSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblSupplier, RS extends AbstractDoubleAssert<RS>> LDblSupplierAssert.The<A, RS> assertDblSup(LDblSupplier func) {
		return new LDblSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFltSupplier, RS extends AbstractFloatAssert<RS>> LFltSupplierAssert.The<A, RS> assertFltSup(LFltSupplier func) {
		return new LFltSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplier, RS extends AbstractIntegerAssert<RS>> LIntSupplierAssert.The<A, RS> assertIntSup(LIntSupplier func) {
		return new LIntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplier, RS extends AbstractLongAssert<RS>> LLongSupplierAssert.The<A, RS> assertLongSup(LLongSupplier func) {
		return new LLongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtSupplier, RS extends AbstractShortAssert<RS>> LSrtSupplierAssert.The<A, RS> assertSrtSup(LSrtSupplier func) {
		return new LSrtSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplier<T>, RS extends Assert<RS, T>, T> LSupplierAssert.The<A, RS, T> assertSup(LSupplier<T> func) {
		return new LSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Runnable> JreRunnableAssert.The<A> assertAct(Runnable func) {
		return new JreRunnableAssert.The(func);
	}

	@Nonnull
	public static <A extends BiConsumer<T1, T2>, T1, T2> JreBiConsumerAssert.The<A, T1, T2> assertBiCons(BiConsumer<T1, T2> func) {
		return new JreBiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends Consumer<T>, T> JreConsumerAssert.The<A, T> assertCons(Consumer<T> func) {
		return new JreConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends DoubleConsumer> JreDoubleConsumerAssert.The<A> assertDblCons(DoubleConsumer func) {
		return new JreDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends IntConsumer> JreIntConsumerAssert.The<A> assertIntCons(IntConsumer func) {
		return new JreIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LongConsumer> JreLongConsumerAssert.The<A> assertLongCons(LongConsumer func) {
		return new JreLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjDoubleConsumer<T>, T> JreObjDoubleConsumerAssert.The<A, T> assertObjDblCons(ObjDoubleConsumer<T> func) {
		return new JreObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjIntConsumer<T>, T> JreObjIntConsumerAssert.The<A, T> assertObjIntCons(ObjIntConsumer<T> func) {
		return new JreObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjLongConsumer<T>, T> JreObjLongConsumerAssert.The<A, T> assertObjLongCons(ObjLongConsumer<T> func) {
		return new JreObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends BinaryOperator<T>, RS extends Assert<RS, T>, T> JreBinaryOperatorAssert.The<A, RS, T> assertBinaryOp(BinaryOperator<T> func) {
		return new JreBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> JreDoubleBinaryOperatorAssert.The<A, RS> assertDblBinaryOp(DoubleBinaryOperator func) {
		return new JreDoubleBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> JreDoubleUnaryOperatorAssert.The<A, RS> assertDblUnaryOp(DoubleUnaryOperator func) {
		return new JreDoubleUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> JreIntBinaryOperatorAssert.The<A, RS> assertIntBinaryOp(IntBinaryOperator func) {
		return new JreIntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> JreIntUnaryOperatorAssert.The<A, RS> assertIntUnaryOp(IntUnaryOperator func) {
		return new JreIntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBinaryOperator, RS extends AbstractLongAssert<RS>> JreLongBinaryOperatorAssert.The<A, RS> assertLongBinaryOp(LongBinaryOperator func) {
		return new JreLongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongUnaryOperator, RS extends AbstractLongAssert<RS>> JreLongUnaryOperatorAssert.The<A, RS> assertLongUnaryOp(LongUnaryOperator func) {
		return new JreLongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends UnaryOperator<T>, RS extends Assert<RS, T>, T> JreUnaryOperatorAssert.The<A, RS, T> assertUnaryOp(UnaryOperator<T> func) {
		return new JreUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> JreBiFunctionAssert.The<A, RS, T1, T2, R> assertBiFunc(BiFunction<T1, T2, R> func) {
		return new JreBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleFunction<R>, RS extends Assert<RS, R>, R> JreDoubleFunctionAssert.The<A, RS, R> assertDblFunc(DoubleFunction<R> func) {
		return new JreDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> JreDoubleToIntFunctionAssert.The<A, RS> assertDblToIntFunc(DoubleToIntFunction func) {
		return new JreDoubleToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToLongFunction, RS extends AbstractLongAssert<RS>> JreDoubleToLongFunctionAssert.The<A, RS> assertDblToLongFunc(DoubleToLongFunction func) {
		return new JreDoubleToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Function<T, R>, RS extends Assert<RS, R>, T, R> JreFunctionAssert.The<A, RS, T, R> assertFunc(Function<T, R> func) {
		return new JreFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntFunction<R>, RS extends Assert<RS, R>, R> JreIntFunctionAssert.The<A, RS, R> assertIntFunc(IntFunction<R> func) {
		return new JreIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> JreIntToDoubleFunctionAssert.The<A, RS> assertIntToDblFunc(IntToDoubleFunction func) {
		return new JreIntToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToLongFunction, RS extends AbstractLongAssert<RS>> JreIntToLongFunctionAssert.The<A, RS> assertIntToLongFunc(IntToLongFunction func) {
		return new JreIntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongFunction<R>, RS extends Assert<RS, R>, R> JreLongFunctionAssert.The<A, RS, R> assertLongFunc(LongFunction<R> func) {
		return new JreLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> JreLongToDoubleFunctionAssert.The<A, RS> assertLongToDblFunc(LongToDoubleFunction func) {
		return new JreLongToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToIntFunction, RS extends AbstractIntegerAssert<RS>> JreLongToIntFunctionAssert.The<A, RS> assertLongToIntFunc(LongToIntFunction func) {
		return new JreLongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> JreToDoubleBiFunctionAssert.The<A, RS, T1, T2> assertToDblBiFunc(ToDoubleBiFunction<T1, T2> func) {
		return new JreToDoubleBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> JreToDoubleFunctionAssert.The<A, RS, T> assertToDblFunc(ToDoubleFunction<T> func) {
		return new JreToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> JreToIntBiFunctionAssert.The<A, RS, T1, T2> assertToIntBiFunc(ToIntBiFunction<T1, T2> func) {
		return new JreToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> JreToIntFunctionAssert.The<A, RS, T> assertToIntFunc(ToIntFunction<T> func) {
		return new JreToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> JreToLongBiFunctionAssert.The<A, RS, T1, T2> assertToLongBiFunc(ToLongBiFunction<T1, T2> func) {
		return new JreToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> JreToLongFunctionAssert.The<A, RS, T> assertToLongFunc(ToLongFunction<T> func) {
		return new JreToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> JreBiPredicateAssert.The<A, RS, T1, T2> assertBiPred(BiPredicate<T1, T2> func) {
		return new JreBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoublePredicate, RS extends AbstractBooleanAssert<RS>> JreDoublePredicateAssert.The<A, RS> assertDblPred(DoublePredicate func) {
		return new JreDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntPredicate, RS extends AbstractBooleanAssert<RS>> JreIntPredicateAssert.The<A, RS> assertIntPred(IntPredicate func) {
		return new JreIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongPredicate, RS extends AbstractBooleanAssert<RS>> JreLongPredicateAssert.The<A, RS> assertLongPred(LongPredicate func) {
		return new JreLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> JrePredicateAssert.The<A, RS, T> assertPred(Predicate<T> func) {
		return new JrePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> JreBooleanSupplierAssert.The<A, RS> assertBoolSup(BooleanSupplier func) {
		return new JreBooleanSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleSupplier, RS extends AbstractDoubleAssert<RS>> JreDoubleSupplierAssert.The<A, RS> assertDblSup(DoubleSupplier func) {
		return new JreDoubleSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntSupplier, RS extends AbstractIntegerAssert<RS>> JreIntSupplierAssert.The<A, RS> assertIntSup(IntSupplier func) {
		return new JreIntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongSupplier, RS extends AbstractLongAssert<RS>> JreLongSupplierAssert.The<A, RS> assertLongSup(LongSupplier func) {
		return new JreLongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Supplier<T>, RS extends Assert<RS, T>, T> JreSupplierAssert.The<A, RS, T> assertSup(Supplier<T> func) {
		return new JreSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static OptBoolTraitAssert assertThat(OptBoolTrait<?> actual) {
		return new OptBoolTraitAssert(actual);
	}

	@Nonnull
	public static <T> OptTraitAssert<T> assertThat(OptTrait<T, ?> actual) {
		return new OptTraitAssert<T>(actual);
	}

	@Nonnull
	public static OptByteTraitAssert assertThat(OptByteTrait<?> actual) {
		return new OptByteTraitAssert(actual);
	}

	@Nonnull
	public static OptDblTraitAssert assertThat(OptDblTrait<?> actual) {
		return new OptDblTraitAssert(actual);
	}

	@Nonnull
	public static OptCharTraitAssert assertThat(OptCharTrait<?> actual) {
		return new OptCharTraitAssert(actual);
	}

	@Nonnull
	public static OptSrtTraitAssert assertThat(OptSrtTrait<?> actual) {
		return new OptSrtTraitAssert(actual);
	}

	@Nonnull
	public static OptFltTraitAssert assertThat(OptFltTrait<?> actual) {
		return new OptFltTraitAssert(actual);
	}

	@Nonnull
	public static OptIntTraitAssert assertThat(OptIntTrait<?> actual) {
		return new OptIntTraitAssert(actual);
	}

	@Nonnull
	public static OptLongTraitAssert assertThat(OptLongTrait<?> actual) {
		return new OptLongTraitAssert(actual);
	}

	@Nonnull
	public static <A> MagmaAssert.ObjAssert<A> assertThat(A actual) {
		return THEN.assertThat(actual);
	}

}