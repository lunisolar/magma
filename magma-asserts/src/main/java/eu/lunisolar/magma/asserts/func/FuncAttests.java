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

package eu.lunisolar.magma.asserts.func;

import java.util.function.*;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
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

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the usecase otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface FuncAttests {

	@Nonnull
	public static LActionAttest attestAct(LAction func) {
		return new LActionAttest(func);
	}

	@Nonnull
	public static LActionAttest attestAct(LAction func, String name) {
		return new LActionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiConsumerAttest<T1, T2> attestBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiConsumerAttest<T1, T2> attestBiCons(LBiConsumer<T1, T2> func, String name) {
		return new LBiConsumerAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0ConsAttest<T2, T1> attestObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func) {
		return new LObj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0ConsAttest<T2, T1> attestObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func, String name) {
		return new LObj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LConsumerAttest<T> attestCons(LConsumer<T> func) {
		return new LConsumerAttest(func);
	}

	@Nonnull
	public static <T> LConsumerAttest<T> attestCons(LConsumer<T> func, String name) {
		return new LConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4> LQuadConsumerAttest<T1, T2, T3, T4> attestQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4> LQuadConsumerAttest<T1, T2, T3, T4> attestQuadCons(LQuadConsumer<T1, T2, T3, T4> func, String name) {
		return new LQuadConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintConsumerAttest<T1, T2, T3, T4, T5> attestQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintConsumerAttest<T1, T2, T3, T4, T5> attestQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func, String name) {
		return new LQuintConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3> LTriConsumerAttest<T1, T2, T3> attestTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3> LTriConsumerAttest<T1, T2, T3> attestTriCons(LTriConsumer<T1, T2, T3> func, String name) {
		return new LTriConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T3, T2> LObj0Obj2Obj1ConsAttest<T1, T3, T2> attestObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func) {
		return new LObj0Obj2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T3, T2> LObj0Obj2Obj1ConsAttest<T1, T3, T2> attestObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func, String name) {
		return new LObj0Obj2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, T3> LObj1BiObj2ConsAttest<T2, T1, T3> attestObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func) {
		return new LObj1BiObj2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1, T3> LObj1BiObj2ConsAttest<T2, T1, T3> attestObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func, String name) {
		return new LObj1BiObj2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T3, T1> LObj1Obj2Obj0ConsAttest<T2, T3, T1> attestObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func) {
		return new LObj1Obj2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T3, T1> LObj1Obj2Obj0ConsAttest<T2, T3, T1> attestObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func, String name) {
		return new LObj1Obj2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T3, T1, T2> LObj2Obj0Obj1ConsAttest<T3, T1, T2> attestObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func) {
		return new LObj2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T3, T1, T2> LObj2Obj0Obj1ConsAttest<T3, T1, T2> attestObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func, String name) {
		return new LObj2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T3, T2, T1> LBiObj1Obj0ConsAttest<T3, T2, T1> attestBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func) {
		return new LBiObj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T3, T2, T1> LBiObj1Obj0ConsAttest<T3, T2, T1> attestBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func, String name) {
		return new LBiObj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static LBoolConsumerAttest attestBoolCons(LBoolConsumer func) {
		return new LBoolConsumerAttest(func);
	}

	@Nonnull
	public static LBoolConsumerAttest attestBoolCons(LBoolConsumer func, String name) {
		return new LBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static LByteConsumerAttest attestByteCons(LByteConsumer func) {
		return new LByteConsumerAttest(func);
	}

	@Nonnull
	public static LByteConsumerAttest attestByteCons(LByteConsumer func, String name) {
		return new LByteConsumerAttest(func, name);
	}

	@Nonnull
	public static LCharConsumerAttest attestCharCons(LCharConsumer func) {
		return new LCharConsumerAttest(func);
	}

	@Nonnull
	public static LCharConsumerAttest attestCharCons(LCharConsumer func, String name) {
		return new LCharConsumerAttest(func, name);
	}

	@Nonnull
	public static LDblConsumerAttest attestDblCons(LDblConsumer func) {
		return new LDblConsumerAttest(func);
	}

	@Nonnull
	public static LDblConsumerAttest attestDblCons(LDblConsumer func, String name) {
		return new LDblConsumerAttest(func, name);
	}

	@Nonnull
	public static LFltConsumerAttest attestFltCons(LFltConsumer func) {
		return new LFltConsumerAttest(func);
	}

	@Nonnull
	public static LFltConsumerAttest attestFltCons(LFltConsumer func, String name) {
		return new LFltConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntConsumerAttest attestIntCons(LIntConsumer func) {
		return new LIntConsumerAttest(func);
	}

	@Nonnull
	public static LIntConsumerAttest attestIntCons(LIntConsumer func, String name) {
		return new LIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LLongConsumerAttest attestLongCons(LLongConsumer func) {
		return new LLongConsumerAttest(func);
	}

	@Nonnull
	public static LLongConsumerAttest attestLongCons(LLongConsumer func, String name) {
		return new LLongConsumerAttest(func, name);
	}

	@Nonnull
	public static LSrtConsumerAttest attestSrtCons(LSrtConsumer func) {
		return new LSrtConsumerAttest(func);
	}

	@Nonnull
	public static LSrtConsumerAttest attestSrtCons(LSrtConsumer func, String name) {
		return new LSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static LBiBoolConsumerAttest attestBiBoolCons(LBiBoolConsumer func) {
		return new LBiBoolConsumerAttest(func);
	}

	@Nonnull
	public static LBiBoolConsumerAttest attestBiBoolCons(LBiBoolConsumer func, String name) {
		return new LBiBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static LBool1Bool0ConsAttest attestBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func) {
		return new LBool1Bool0ConsAttest(func);
	}

	@Nonnull
	public static LBool1Bool0ConsAttest attestBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func, String name) {
		return new LBool1Bool0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiByteConsumerAttest attestBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAttest(func);
	}

	@Nonnull
	public static LBiByteConsumerAttest attestBiByteCons(LBiByteConsumer func, String name) {
		return new LBiByteConsumerAttest(func, name);
	}

	@Nonnull
	public static LByte1Byte0ConsAttest attestByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func) {
		return new LByte1Byte0ConsAttest(func);
	}

	@Nonnull
	public static LByte1Byte0ConsAttest attestByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func, String name) {
		return new LByte1Byte0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiCharConsumerAttest attestBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAttest(func);
	}

	@Nonnull
	public static LBiCharConsumerAttest attestBiCharCons(LBiCharConsumer func, String name) {
		return new LBiCharConsumerAttest(func, name);
	}

	@Nonnull
	public static LChar1Char0ConsAttest attestChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func) {
		return new LChar1Char0ConsAttest(func);
	}

	@Nonnull
	public static LChar1Char0ConsAttest attestChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func, String name) {
		return new LChar1Char0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiDblConsumerAttest attestBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAttest(func);
	}

	@Nonnull
	public static LBiDblConsumerAttest attestBiDblCons(LBiDblConsumer func, String name) {
		return new LBiDblConsumerAttest(func, name);
	}

	@Nonnull
	public static LDbl1Dbl0ConsAttest attestDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func) {
		return new LDbl1Dbl0ConsAttest(func);
	}

	@Nonnull
	public static LDbl1Dbl0ConsAttest attestDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func, String name) {
		return new LDbl1Dbl0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiFltConsumerAttest attestBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAttest(func);
	}

	@Nonnull
	public static LBiFltConsumerAttest attestBiFltCons(LBiFltConsumer func, String name) {
		return new LBiFltConsumerAttest(func, name);
	}

	@Nonnull
	public static LFlt1Flt0ConsAttest attestFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func) {
		return new LFlt1Flt0ConsAttest(func);
	}

	@Nonnull
	public static LFlt1Flt0ConsAttest attestFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func, String name) {
		return new LFlt1Flt0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiIntConsumerAttest attestBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAttest(func);
	}

	@Nonnull
	public static LBiIntConsumerAttest attestBiIntCons(LBiIntConsumer func, String name) {
		return new LBiIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LInt1Int0ConsAttest attestInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func) {
		return new LInt1Int0ConsAttest(func);
	}

	@Nonnull
	public static LInt1Int0ConsAttest attestInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func, String name) {
		return new LInt1Int0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiLongConsumerAttest attestBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAttest(func);
	}

	@Nonnull
	public static LBiLongConsumerAttest attestBiLongCons(LBiLongConsumer func, String name) {
		return new LBiLongConsumerAttest(func, name);
	}

	@Nonnull
	public static LLong1Long0ConsAttest attestLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func) {
		return new LLong1Long0ConsAttest(func);
	}

	@Nonnull
	public static LLong1Long0ConsAttest attestLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func, String name) {
		return new LLong1Long0ConsAttest(func, name);
	}

	@Nonnull
	public static LBiSrtConsumerAttest attestBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAttest(func);
	}

	@Nonnull
	public static LBiSrtConsumerAttest attestBiSrtCons(LBiSrtConsumer func, String name) {
		return new LBiSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static LSrt1Srt0ConsAttest attestSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func) {
		return new LSrt1Srt0ConsAttest(func);
	}

	@Nonnull
	public static LSrt1Srt0ConsAttest attestSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func, String name) {
		return new LSrt1Srt0ConsAttest(func, name);
	}

	@Nonnull
	public static LBoolIntConsumerAttest attestBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAttest(func);
	}

	@Nonnull
	public static LBoolIntConsumerAttest attestBoolIntCons(LBoolIntConsumer func, String name) {
		return new LBoolIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntBoolConsAttest attestIntBoolCons(LBoolIntConsumer.LIntBoolCons func) {
		return new LIntBoolConsAttest(func);
	}

	@Nonnull
	public static LIntBoolConsAttest attestIntBoolCons(LBoolIntConsumer.LIntBoolCons func, String name) {
		return new LIntBoolConsAttest(func, name);
	}

	@Nonnull
	public static LByteIntConsumerAttest attestByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAttest(func);
	}

	@Nonnull
	public static LByteIntConsumerAttest attestByteIntCons(LByteIntConsumer func, String name) {
		return new LByteIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntByteConsAttest attestIntByteCons(LByteIntConsumer.LIntByteCons func) {
		return new LIntByteConsAttest(func);
	}

	@Nonnull
	public static LIntByteConsAttest attestIntByteCons(LByteIntConsumer.LIntByteCons func, String name) {
		return new LIntByteConsAttest(func, name);
	}

	@Nonnull
	public static LCharIntConsumerAttest attestCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAttest(func);
	}

	@Nonnull
	public static LCharIntConsumerAttest attestCharIntCons(LCharIntConsumer func, String name) {
		return new LCharIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntCharConsAttest attestIntCharCons(LCharIntConsumer.LIntCharCons func) {
		return new LIntCharConsAttest(func);
	}

	@Nonnull
	public static LIntCharConsAttest attestIntCharCons(LCharIntConsumer.LIntCharCons func, String name) {
		return new LIntCharConsAttest(func, name);
	}

	@Nonnull
	public static LDblIntConsumerAttest attestDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAttest(func);
	}

	@Nonnull
	public static LDblIntConsumerAttest attestDblIntCons(LDblIntConsumer func, String name) {
		return new LDblIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntDblConsAttest attestIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAttest(func);
	}

	@Nonnull
	public static LIntDblConsAttest attestIntDblCons(LDblIntConsumer.LIntDblCons func, String name) {
		return new LIntDblConsAttest(func, name);
	}

	@Nonnull
	public static LFltIntConsumerAttest attestFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAttest(func);
	}

	@Nonnull
	public static LFltIntConsumerAttest attestFltIntCons(LFltIntConsumer func, String name) {
		return new LFltIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntFltConsAttest attestIntFltCons(LFltIntConsumer.LIntFltCons func) {
		return new LIntFltConsAttest(func);
	}

	@Nonnull
	public static LIntFltConsAttest attestIntFltCons(LFltIntConsumer.LIntFltCons func, String name) {
		return new LIntFltConsAttest(func, name);
	}

	@Nonnull
	public static LLongIntConsumerAttest attestLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAttest(func);
	}

	@Nonnull
	public static LLongIntConsumerAttest attestLongIntCons(LLongIntConsumer func, String name) {
		return new LLongIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntLongConsAttest attestIntLongCons(LLongIntConsumer.LIntLongCons func) {
		return new LIntLongConsAttest(func);
	}

	@Nonnull
	public static LIntLongConsAttest attestIntLongCons(LLongIntConsumer.LIntLongCons func, String name) {
		return new LIntLongConsAttest(func, name);
	}

	@Nonnull
	public static LSrtIntConsumerAttest attestSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAttest(func);
	}

	@Nonnull
	public static LSrtIntConsumerAttest attestSrtIntCons(LSrtIntConsumer func, String name) {
		return new LSrtIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LIntSrtConsAttest attestIntSrtCons(LSrtIntConsumer.LIntSrtCons func) {
		return new LIntSrtConsAttest(func);
	}

	@Nonnull
	public static LIntSrtConsAttest attestIntSrtCons(LSrtIntConsumer.LIntSrtCons func, String name) {
		return new LIntSrtConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjBoolConsumerAttest<T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjBoolConsumerAttest<T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func, String name) {
		return new LBiObjBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Bool2Obj1ConsAttest<T1, T2> attestObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func) {
		return new LObj0Bool2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Bool2Obj1ConsAttest<T1, T2> attestObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Bool2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Bool2ConsAttest<T2, T1> attestObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func) {
		return new LObj1Obj0Bool2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Bool2ConsAttest<T2, T1> attestObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Bool2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Bool2Obj0ConsAttest<T2, T1> attestObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func) {
		return new LObj1Bool2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Bool2Obj0ConsAttest<T2, T1> attestObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Bool2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBool2Obj0Obj1ConsAttest<T1, T2> attestBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func) {
		return new LBool2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBool2Obj0Obj1ConsAttest<T1, T2> attestBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LBool2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LBool2Obj1Obj0ConsAttest<T2, T1> attestBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func) {
		return new LBool2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LBool2Obj1Obj0ConsAttest<T2, T1> attestBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LBool2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjByteConsumerAttest<T1, T2> attestBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjByteConsumerAttest<T1, T2> attestBiObjByteCons(LBiObjByteConsumer<T1, T2> func, String name) {
		return new LBiObjByteConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Byte2Obj1ConsAttest<T1, T2> attestObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Byte2Obj1ConsAttest<T1, T2> attestObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Byte2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Byte2ConsAttest<T2, T1> attestObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func) {
		return new LObj1Obj0Byte2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Byte2ConsAttest<T2, T1> attestObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Byte2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Byte2Obj0ConsAttest<T2, T1> attestObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Byte2Obj0ConsAttest<T2, T1> attestObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Byte2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LByte2Obj0Obj1ConsAttest<T1, T2> attestByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func) {
		return new LByte2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LByte2Obj0Obj1ConsAttest<T1, T2> attestByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LByte2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LByte2Obj1Obj0ConsAttest<T2, T1> attestByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func) {
		return new LByte2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LByte2Obj1Obj0ConsAttest<T2, T1> attestByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LByte2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjCharConsumerAttest<T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjCharConsumerAttest<T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func, String name) {
		return new LBiObjCharConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Char2Obj1ConsAttest<T1, T2> attestObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func) {
		return new LObj0Char2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Char2Obj1ConsAttest<T1, T2> attestObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Char2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Char2ConsAttest<T2, T1> attestObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func) {
		return new LObj1Obj0Char2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Char2ConsAttest<T2, T1> attestObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Char2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Char2Obj0ConsAttest<T2, T1> attestObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func) {
		return new LObj1Char2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Char2Obj0ConsAttest<T2, T1> attestObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Char2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LChar2Obj0Obj1ConsAttest<T1, T2> attestChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func) {
		return new LChar2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LChar2Obj0Obj1ConsAttest<T1, T2> attestChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LChar2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LChar2Obj1Obj0ConsAttest<T2, T1> attestChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func) {
		return new LChar2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LChar2Obj1Obj0ConsAttest<T2, T1> attestChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LChar2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjDblConsumerAttest<T1, T2> attestBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjDblConsumerAttest<T1, T2> attestBiObjDblCons(LBiObjDblConsumer<T1, T2> func, String name) {
		return new LBiObjDblConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Dbl2Obj1ConsAttest<T1, T2> attestObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func) {
		return new LObj0Dbl2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Dbl2Obj1ConsAttest<T1, T2> attestObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Dbl2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Dbl2ConsAttest<T2, T1> attestObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func) {
		return new LObj1Obj0Dbl2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Dbl2ConsAttest<T2, T1> attestObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Dbl2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Dbl2Obj0ConsAttest<T2, T1> attestObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func) {
		return new LObj1Dbl2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Dbl2Obj0ConsAttest<T2, T1> attestObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Dbl2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LDbl2Obj0Obj1ConsAttest<T1, T2> attestDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func) {
		return new LDbl2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LDbl2Obj0Obj1ConsAttest<T1, T2> attestDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LDbl2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LDbl2Obj1Obj0ConsAttest<T2, T1> attestDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func) {
		return new LDbl2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LDbl2Obj1Obj0ConsAttest<T2, T1> attestDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LDbl2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjFltConsumerAttest<T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjFltConsumerAttest<T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func, String name) {
		return new LBiObjFltConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Flt2Obj1ConsAttest<T1, T2> attestObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func) {
		return new LObj0Flt2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Flt2Obj1ConsAttest<T1, T2> attestObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Flt2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Flt2ConsAttest<T2, T1> attestObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func) {
		return new LObj1Obj0Flt2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Flt2ConsAttest<T2, T1> attestObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Flt2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Flt2Obj0ConsAttest<T2, T1> attestObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func) {
		return new LObj1Flt2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Flt2Obj0ConsAttest<T2, T1> attestObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Flt2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LFlt2Obj0Obj1ConsAttest<T1, T2> attestFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func) {
		return new LFlt2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LFlt2Obj0Obj1ConsAttest<T1, T2> attestFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LFlt2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LFlt2Obj1Obj0ConsAttest<T2, T1> attestFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func) {
		return new LFlt2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LFlt2Obj1Obj0ConsAttest<T2, T1> attestFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LFlt2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjIntConsumerAttest<T1, T2> attestBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjIntConsumerAttest<T1, T2> attestBiObjIntCons(LBiObjIntConsumer<T1, T2> func, String name) {
		return new LBiObjIntConsumerAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Int2ConsAttest<T2, T1> attestObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func) {
		return new LObj1Obj0Int2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Int2ConsAttest<T2, T1> attestObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Int2ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LInt2Obj0Obj1ConsAttest<T1, T2> attestInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func) {
		return new LInt2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LInt2Obj0Obj1ConsAttest<T1, T2> attestInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LInt2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LInt2Obj1Obj0ConsAttest<T2, T1> attestInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func) {
		return new LInt2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LInt2Obj1Obj0ConsAttest<T2, T1> attestInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LInt2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjLongConsumerAttest<T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjLongConsumerAttest<T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func, String name) {
		return new LBiObjLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Long2Obj1ConsAttest<T1, T2> attestObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func) {
		return new LObj0Long2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Long2Obj1ConsAttest<T1, T2> attestObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Long2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Long2ConsAttest<T2, T1> attestObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func) {
		return new LObj1Obj0Long2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Long2ConsAttest<T2, T1> attestObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Long2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Long2Obj0ConsAttest<T2, T1> attestObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func) {
		return new LObj1Long2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Long2Obj0ConsAttest<T2, T1> attestObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Long2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LLong2Obj0Obj1ConsAttest<T1, T2> attestLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func) {
		return new LLong2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LLong2Obj0Obj1ConsAttest<T1, T2> attestLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LLong2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LLong2Obj1Obj0ConsAttest<T2, T1> attestLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func) {
		return new LLong2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LLong2Obj1Obj0ConsAttest<T2, T1> attestLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LLong2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjSrtConsumerAttest<T1, T2> attestBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjSrtConsumerAttest<T1, T2> attestBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func, String name) {
		return new LBiObjSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Srt2Obj1ConsAttest<T1, T2> attestObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func) {
		return new LObj0Srt2Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Srt2Obj1ConsAttest<T1, T2> attestObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func, String name) {
		return new LObj0Srt2Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Srt2ConsAttest<T2, T1> attestObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func) {
		return new LObj1Obj0Srt2ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Srt2ConsAttest<T2, T1> attestObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func, String name) {
		return new LObj1Obj0Srt2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Srt2Obj0ConsAttest<T2, T1> attestObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func) {
		return new LObj1Srt2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Srt2Obj0ConsAttest<T2, T1> attestObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func, String name) {
		return new LObj1Srt2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LSrt2Obj0Obj1ConsAttest<T1, T2> attestSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func) {
		return new LSrt2Obj0Obj1ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LSrt2Obj0Obj1ConsAttest<T1, T2> attestSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func, String name) {
		return new LSrt2Obj0Obj1ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LSrt2Obj1Obj0ConsAttest<T2, T1> attestSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func) {
		return new LSrt2Obj1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LSrt2Obj1Obj0ConsAttest<T2, T1> attestSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func, String name) {
		return new LSrt2Obj1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBiLongConsumerAttest<T> attestObjBiLongCons(LObjBiLongConsumer<T> func) {
		return new LObjBiLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjBiLongConsumerAttest<T> attestObjBiLongCons(LObjBiLongConsumer<T> func, String name) {
		return new LObjBiLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObj0Long2Long1ConsAttest<T> attestObj0Long2Long1Cons(LObjBiLongConsumer.LObj0Long2Long1Cons<T> func) {
		return new LObj0Long2Long1ConsAttest(func);
	}

	@Nonnull
	public static <T> LObj0Long2Long1ConsAttest<T> attestObj0Long2Long1Cons(LObjBiLongConsumer.LObj0Long2Long1Cons<T> func, String name) {
		return new LObj0Long2Long1ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LLong1Obj0Long2ConsAttest<T> attestLong1Obj0Long2Cons(LObjBiLongConsumer.LLong1Obj0Long2Cons<T> func) {
		return new LLong1Obj0Long2ConsAttest(func);
	}

	@Nonnull
	public static <T> LLong1Obj0Long2ConsAttest<T> attestLong1Obj0Long2Cons(LObjBiLongConsumer.LLong1Obj0Long2Cons<T> func, String name) {
		return new LLong1Obj0Long2ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LLong1Long2Obj0ConsAttest<T> attestLong1Long2Obj0Cons(LObjBiLongConsumer.LLong1Long2Obj0Cons<T> func) {
		return new LLong1Long2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T> LLong1Long2Obj0ConsAttest<T> attestLong1Long2Obj0Cons(LObjBiLongConsumer.LLong1Long2Obj0Cons<T> func, String name) {
		return new LLong1Long2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LLong2Obj0Long1ConsAttest<T> attestLong2Obj0Long1Cons(LObjBiLongConsumer.LLong2Obj0Long1Cons<T> func) {
		return new LLong2Obj0Long1ConsAttest(func);
	}

	@Nonnull
	public static <T> LLong2Obj0Long1ConsAttest<T> attestLong2Obj0Long1Cons(LObjBiLongConsumer.LLong2Obj0Long1Cons<T> func, String name) {
		return new LLong2Obj0Long1ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LBiLong1Obj0ConsAttest<T> attestBiLong1Obj0Cons(LObjBiLongConsumer.LBiLong1Obj0Cons<T> func) {
		return new LBiLong1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T> LBiLong1Obj0ConsAttest<T> attestBiLong1Obj0Cons(LObjBiLongConsumer.LBiLong1Obj0Cons<T> func, String name) {
		return new LBiLong1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBoolConsumerAttest<T> attestObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjBoolConsumerAttest<T> attestObjBoolCons(LObjBoolConsumer<T> func, String name) {
		return new LObjBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolObjConsAttest<T> attestBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func) {
		return new LBoolObjConsAttest(func);
	}

	@Nonnull
	public static <T> LBoolObjConsAttest<T> attestBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func, String name) {
		return new LBoolObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjByteConsumerAttest<T> attestObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjByteConsumerAttest<T> attestObjByteCons(LObjByteConsumer<T> func, String name) {
		return new LObjByteConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LByteObjConsAttest<T> attestByteObjCons(LObjByteConsumer.LByteObjCons<T> func) {
		return new LByteObjConsAttest(func);
	}

	@Nonnull
	public static <T> LByteObjConsAttest<T> attestByteObjCons(LObjByteConsumer.LByteObjCons<T> func, String name) {
		return new LByteObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjCharConsumerAttest<T> attestObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjCharConsumerAttest<T> attestObjCharCons(LObjCharConsumer<T> func, String name) {
		return new LObjCharConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LCharObjConsAttest<T> attestCharObjCons(LObjCharConsumer.LCharObjCons<T> func) {
		return new LCharObjConsAttest(func);
	}

	@Nonnull
	public static <T> LCharObjConsAttest<T> attestCharObjCons(LObjCharConsumer.LCharObjCons<T> func, String name) {
		return new LCharObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjDblConsumerAttest<T> attestObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjDblConsumerAttest<T> attestObjDblCons(LObjDblConsumer<T> func, String name) {
		return new LObjDblConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LDblObjConsAttest<T> attestDblObjCons(LObjDblConsumer.LDblObjCons<T> func) {
		return new LDblObjConsAttest(func);
	}

	@Nonnull
	public static <T> LDblObjConsAttest<T> attestDblObjCons(LObjDblConsumer.LDblObjCons<T> func, String name) {
		return new LDblObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjFltConsumerAttest<T> attestObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjFltConsumerAttest<T> attestObjFltCons(LObjFltConsumer<T> func, String name) {
		return new LObjFltConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LFltObjConsAttest<T> attestFltObjCons(LObjFltConsumer.LFltObjCons<T> func) {
		return new LFltObjConsAttest(func);
	}

	@Nonnull
	public static <T> LFltObjConsAttest<T> attestFltObjCons(LObjFltConsumer.LFltObjCons<T> func, String name) {
		return new LFltObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntConsumerAttest<T> attestObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjIntConsumerAttest<T> attestObjIntCons(LObjIntConsumer<T> func, String name) {
		return new LObjIntConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjConsAttest<T> attestIntObjCons(LObjIntConsumer.LIntObjCons<T> func) {
		return new LIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjConsAttest<T> attestIntObjCons(LObjIntConsumer.LIntObjCons<T> func, String name) {
		return new LIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjLongConsumerAttest<T> attestObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjLongConsumerAttest<T> attestObjLongCons(LObjLongConsumer<T> func, String name) {
		return new LObjLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LLongObjConsAttest<T> attestLongObjCons(LObjLongConsumer.LLongObjCons<T> func) {
		return new LLongObjConsAttest(func);
	}

	@Nonnull
	public static <T> LLongObjConsAttest<T> attestLongObjCons(LObjLongConsumer.LLongObjCons<T> func, String name) {
		return new LLongObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LObjSrtConsumerAttest<T> attestObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtConsumerAttest<T> attestObjSrtCons(LObjSrtConsumer<T> func, String name) {
		return new LObjSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtObjConsAttest<T> attestSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func) {
		return new LSrtObjConsAttest(func);
	}

	@Nonnull
	public static <T> LSrtObjConsAttest<T> attestSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func, String name) {
		return new LSrtObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieBoolConsumerAttest<T> attestTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieBoolConsumerAttest<T> attestTieBoolCons(LTieBoolConsumer<T> func, String name) {
		return new LTieBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBoolIntConsAttest<T> attestObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func) {
		return new LObjBoolIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjBoolIntConsAttest<T> attestObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func, String name) {
		return new LObjBoolIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjBoolConsAttest<T> attestIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func) {
		return new LIntObjBoolConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjBoolConsAttest<T> attestIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func, String name) {
		return new LIntObjBoolConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntBoolObjConsAttest<T> attestIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func) {
		return new LIntBoolObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntBoolObjConsAttest<T> attestIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func, String name) {
		return new LIntBoolObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolObjIntConsAttest<T> attestBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func) {
		return new LBoolObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LBoolObjIntConsAttest<T> attestBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func, String name) {
		return new LBoolObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolIntObjConsAttest<T> attestBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func) {
		return new LBoolIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LBoolIntObjConsAttest<T> attestBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func, String name) {
		return new LBoolIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieByteConsumerAttest<T> attestTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieByteConsumerAttest<T> attestTieByteCons(LTieByteConsumer<T> func, String name) {
		return new LTieByteConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjByteIntConsAttest<T> attestObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func) {
		return new LObjByteIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjByteIntConsAttest<T> attestObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func, String name) {
		return new LObjByteIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjByteConsAttest<T> attestIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func) {
		return new LIntObjByteConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjByteConsAttest<T> attestIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func, String name) {
		return new LIntObjByteConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntByteObjConsAttest<T> attestIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func) {
		return new LIntByteObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntByteObjConsAttest<T> attestIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func, String name) {
		return new LIntByteObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LByteObjIntConsAttest<T> attestByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func) {
		return new LByteObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LByteObjIntConsAttest<T> attestByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func, String name) {
		return new LByteObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LByteIntObjConsAttest<T> attestByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func) {
		return new LByteIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LByteIntObjConsAttest<T> attestByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func, String name) {
		return new LByteIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieCharConsumerAttest<T> attestTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieCharConsumerAttest<T> attestTieCharCons(LTieCharConsumer<T> func, String name) {
		return new LTieCharConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjCharIntConsAttest<T> attestObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func) {
		return new LObjCharIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjCharIntConsAttest<T> attestObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func, String name) {
		return new LObjCharIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjCharConsAttest<T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjCharConsAttest<T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func, String name) {
		return new LIntObjCharConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntCharObjConsAttest<T> attestIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func) {
		return new LIntCharObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntCharObjConsAttest<T> attestIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func, String name) {
		return new LIntCharObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LCharObjIntConsAttest<T> attestCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func) {
		return new LCharObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LCharObjIntConsAttest<T> attestCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func, String name) {
		return new LCharObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LCharIntObjConsAttest<T> attestCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func) {
		return new LCharIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LCharIntObjConsAttest<T> attestCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func, String name) {
		return new LCharIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LTieConsumerAttest<T1, T2> attestTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LTieConsumerAttest<T1, T2> attestTieCons(LTieConsumer<T1, T2> func, String name) {
		return new LTieConsumerAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2ConsAttest<T1, T2> attestInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func) {
		return new LInt1BiObj2ConsAttest(func);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2ConsAttest<T1, T2> attestInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func, String name) {
		return new LInt1BiObj2ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0ConsAttest<T2, T1> attestInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func) {
		return new LInt1Obj2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0ConsAttest<T2, T1> attestInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func, String name) {
		return new LInt1Obj2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0ConsAttest<T2, T1> attestObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func) {
		return new LObj2Int1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0ConsAttest<T2, T1> attestObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func, String name) {
		return new LObj2Int1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieDblConsumerAttest<T> attestTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieDblConsumerAttest<T> attestTieDblCons(LTieDblConsumer<T> func, String name) {
		return new LTieDblConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjDblIntConsAttest<T> attestObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func) {
		return new LObjDblIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjDblIntConsAttest<T> attestObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func, String name) {
		return new LObjDblIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjDblConsAttest<T> attestIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func) {
		return new LIntObjDblConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjDblConsAttest<T> attestIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func, String name) {
		return new LIntObjDblConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntDblObjConsAttest<T> attestIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func) {
		return new LIntDblObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntDblObjConsAttest<T> attestIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func, String name) {
		return new LIntDblObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LDblObjIntConsAttest<T> attestDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func) {
		return new LDblObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LDblObjIntConsAttest<T> attestDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func, String name) {
		return new LDblObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LDblIntObjConsAttest<T> attestDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func) {
		return new LDblIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LDblIntObjConsAttest<T> attestDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func, String name) {
		return new LDblIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieFltConsumerAttest<T> attestTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieFltConsumerAttest<T> attestTieFltCons(LTieFltConsumer<T> func, String name) {
		return new LTieFltConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjFltIntConsAttest<T> attestObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func) {
		return new LObjFltIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjFltIntConsAttest<T> attestObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func, String name) {
		return new LObjFltIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjFltConsAttest<T> attestIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func) {
		return new LIntObjFltConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjFltConsAttest<T> attestIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func, String name) {
		return new LIntObjFltConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntFltObjConsAttest<T> attestIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func) {
		return new LIntFltObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntFltObjConsAttest<T> attestIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func, String name) {
		return new LIntFltObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LFltObjIntConsAttest<T> attestFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func) {
		return new LFltObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LFltObjIntConsAttest<T> attestFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func, String name) {
		return new LFltObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LFltIntObjConsAttest<T> attestFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func) {
		return new LFltIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LFltIntObjConsAttest<T> attestFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func, String name) {
		return new LFltIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieIntConsumerAttest<T> attestTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieIntConsumerAttest<T> attestTieIntCons(LTieIntConsumer<T> func, String name) {
		return new LTieIntConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObj0Int2Int1ConsAttest<T> attestObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func) {
		return new LObj0Int2Int1ConsAttest(func);
	}

	@Nonnull
	public static <T> LObj0Int2Int1ConsAttest<T> attestObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func, String name) {
		return new LObj0Int2Int1ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2ConsAttest<T> attestInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAttest(func);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2ConsAttest<T> attestInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func, String name) {
		return new LInt1Obj0Int2ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0ConsAttest<T> attestInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func) {
		return new LInt1Int2Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0ConsAttest<T> attestInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func, String name) {
		return new LInt1Int2Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1ConsAttest<T> attestInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAttest(func);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1ConsAttest<T> attestInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func, String name) {
		return new LInt2Obj0Int1ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LBiInt1Obj0ConsAttest<T> attestBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func) {
		return new LBiInt1Obj0ConsAttest(func);
	}

	@Nonnull
	public static <T> LBiInt1Obj0ConsAttest<T> attestBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func, String name) {
		return new LBiInt1Obj0ConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieLongConsumerAttest<T> attestTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieLongConsumerAttest<T> attestTieLongCons(LTieLongConsumer<T> func, String name) {
		return new LTieLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjLongIntConsAttest<T> attestObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func) {
		return new LObjLongIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjLongIntConsAttest<T> attestObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func, String name) {
		return new LObjLongIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjLongConsAttest<T> attestIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func) {
		return new LIntObjLongConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjLongConsAttest<T> attestIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func, String name) {
		return new LIntObjLongConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntLongObjConsAttest<T> attestIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func) {
		return new LIntLongObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntLongObjConsAttest<T> attestIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func, String name) {
		return new LIntLongObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LLongObjIntConsAttest<T> attestLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func) {
		return new LLongObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LLongObjIntConsAttest<T> attestLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func, String name) {
		return new LLongObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LLongIntObjConsAttest<T> attestLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func) {
		return new LLongIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LLongIntObjConsAttest<T> attestLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func, String name) {
		return new LLongIntObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LTieSrtConsumerAttest<T> attestTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieSrtConsumerAttest<T> attestTieSrtCons(LTieSrtConsumer<T> func, String name) {
		return new LTieSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LObjSrtIntConsAttest<T> attestObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func) {
		return new LObjSrtIntConsAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtIntConsAttest<T> attestObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func, String name) {
		return new LObjSrtIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjSrtConsAttest<T> attestIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func) {
		return new LIntObjSrtConsAttest(func);
	}

	@Nonnull
	public static <T> LIntObjSrtConsAttest<T> attestIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func, String name) {
		return new LIntObjSrtConsAttest(func, name);
	}

	@Nonnull
	public static <T> LIntSrtObjConsAttest<T> attestIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func) {
		return new LIntSrtObjConsAttest(func);
	}

	@Nonnull
	public static <T> LIntSrtObjConsAttest<T> attestIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func, String name) {
		return new LIntSrtObjConsAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtObjIntConsAttest<T> attestSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func) {
		return new LSrtObjIntConsAttest(func);
	}

	@Nonnull
	public static <T> LSrtObjIntConsAttest<T> attestSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func, String name) {
		return new LSrtObjIntConsAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtIntObjConsAttest<T> attestSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func) {
		return new LSrtIntObjConsAttest(func);
	}

	@Nonnull
	public static <T> LSrtIntObjConsAttest<T> attestSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func, String name) {
		return new LSrtIntObjConsAttest(func, name);
	}

	@Nonnull
	public static LTriBoolConsumerAttest attestTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAttest(func);
	}

	@Nonnull
	public static LTriBoolConsumerAttest attestTriBoolCons(LTriBoolConsumer func, String name) {
		return new LTriBoolConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriByteConsumerAttest attestTriByteCons(LTriByteConsumer func) {
		return new LTriByteConsumerAttest(func);
	}

	@Nonnull
	public static LTriByteConsumerAttest attestTriByteCons(LTriByteConsumer func, String name) {
		return new LTriByteConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriCharConsumerAttest attestTriCharCons(LTriCharConsumer func) {
		return new LTriCharConsumerAttest(func);
	}

	@Nonnull
	public static LTriCharConsumerAttest attestTriCharCons(LTriCharConsumer func, String name) {
		return new LTriCharConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriDblConsumerAttest attestTriDblCons(LTriDblConsumer func) {
		return new LTriDblConsumerAttest(func);
	}

	@Nonnull
	public static LTriDblConsumerAttest attestTriDblCons(LTriDblConsumer func, String name) {
		return new LTriDblConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriFltConsumerAttest attestTriFltCons(LTriFltConsumer func) {
		return new LTriFltConsumerAttest(func);
	}

	@Nonnull
	public static LTriFltConsumerAttest attestTriFltCons(LTriFltConsumer func, String name) {
		return new LTriFltConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriIntConsumerAttest attestTriIntCons(LTriIntConsumer func) {
		return new LTriIntConsumerAttest(func);
	}

	@Nonnull
	public static LTriIntConsumerAttest attestTriIntCons(LTriIntConsumer func, String name) {
		return new LTriIntConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriLongConsumerAttest attestTriLongCons(LTriLongConsumer func) {
		return new LTriLongConsumerAttest(func);
	}

	@Nonnull
	public static LTriLongConsumerAttest attestTriLongCons(LTriLongConsumer func, String name) {
		return new LTriLongConsumerAttest(func, name);
	}

	@Nonnull
	public static LTriSrtConsumerAttest attestTriSrtCons(LTriSrtConsumer func) {
		return new LTriSrtConsumerAttest(func);
	}

	@Nonnull
	public static LTriSrtConsumerAttest attestTriSrtCons(LTriSrtConsumer func, String name) {
		return new LTriSrtConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> LBinaryOperatorAttest<T> attestBinaryOp(LBinaryOperator<T> func) {
		return new LBinaryOperatorAttest(func);
	}

	@Nonnull
	public static <T> LBinaryOperatorAttest<T> attestBinaryOp(LBinaryOperator<T> func, String name) {
		return new LBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LByteBinaryOperatorAttest attestByteBinaryOp(LByteBinaryOperator func) {
		return new LByteBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LByteBinaryOperatorAttest attestByteBinaryOp(LByteBinaryOperator func, String name) {
		return new LByteBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LCharBinaryOperatorAttest attestCharBinaryOp(LCharBinaryOperator func) {
		return new LCharBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LCharBinaryOperatorAttest attestCharBinaryOp(LCharBinaryOperator func, String name) {
		return new LCharBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LDblBinaryOperatorAttest attestDblBinaryOp(LDblBinaryOperator func) {
		return new LDblBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LDblBinaryOperatorAttest attestDblBinaryOp(LDblBinaryOperator func, String name) {
		return new LDblBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LFltBinaryOperatorAttest attestFltBinaryOp(LFltBinaryOperator func) {
		return new LFltBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LFltBinaryOperatorAttest attestFltBinaryOp(LFltBinaryOperator func, String name) {
		return new LFltBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LIntBinaryOperatorAttest attestIntBinaryOp(LIntBinaryOperator func) {
		return new LIntBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LIntBinaryOperatorAttest attestIntBinaryOp(LIntBinaryOperator func, String name) {
		return new LIntBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LLogicalBinaryOperatorAttest attestLogicalBinaryOp(LLogicalBinaryOperator func) {
		return new LLogicalBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LLogicalBinaryOperatorAttest attestLogicalBinaryOp(LLogicalBinaryOperator func, String name) {
		return new LLogicalBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LLongBinaryOperatorAttest attestLongBinaryOp(LLongBinaryOperator func) {
		return new LLongBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LLongBinaryOperatorAttest attestLongBinaryOp(LLongBinaryOperator func, String name) {
		return new LLongBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LSrtBinaryOperatorAttest attestSrtBinaryOp(LSrtBinaryOperator func) {
		return new LSrtBinaryOperatorAttest(func);
	}

	@Nonnull
	public static LSrtBinaryOperatorAttest attestSrtBinaryOp(LSrtBinaryOperator func, String name) {
		return new LSrtBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LByteTernaryOperatorAttest attestByteTernaryOp(LByteTernaryOperator func) {
		return new LByteTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LByteTernaryOperatorAttest attestByteTernaryOp(LByteTernaryOperator func, String name) {
		return new LByteTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LCharTernaryOperatorAttest attestCharTernaryOp(LCharTernaryOperator func) {
		return new LCharTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LCharTernaryOperatorAttest attestCharTernaryOp(LCharTernaryOperator func, String name) {
		return new LCharTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LDblTernaryOperatorAttest attestDblTernaryOp(LDblTernaryOperator func) {
		return new LDblTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LDblTernaryOperatorAttest attestDblTernaryOp(LDblTernaryOperator func, String name) {
		return new LDblTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LFltTernaryOperatorAttest attestFltTernaryOp(LFltTernaryOperator func) {
		return new LFltTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LFltTernaryOperatorAttest attestFltTernaryOp(LFltTernaryOperator func, String name) {
		return new LFltTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LIntTernaryOperatorAttest attestIntTernaryOp(LIntTernaryOperator func) {
		return new LIntTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LIntTernaryOperatorAttest attestIntTernaryOp(LIntTernaryOperator func, String name) {
		return new LIntTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LLogicalTernaryOperatorAttest attestLogicalTernaryOp(LLogicalTernaryOperator func) {
		return new LLogicalTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LLogicalTernaryOperatorAttest attestLogicalTernaryOp(LLogicalTernaryOperator func, String name) {
		return new LLogicalTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LLongTernaryOperatorAttest attestLongTernaryOp(LLongTernaryOperator func) {
		return new LLongTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LLongTernaryOperatorAttest attestLongTernaryOp(LLongTernaryOperator func, String name) {
		return new LLongTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LSrtTernaryOperatorAttest attestSrtTernaryOp(LSrtTernaryOperator func) {
		return new LSrtTernaryOperatorAttest(func);
	}

	@Nonnull
	public static LSrtTernaryOperatorAttest attestSrtTernaryOp(LSrtTernaryOperator func, String name) {
		return new LSrtTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static <T> LTernaryOperatorAttest<T> attestTernaryOp(LTernaryOperator<T> func) {
		return new LTernaryOperatorAttest(func);
	}

	@Nonnull
	public static <T> LTernaryOperatorAttest<T> attestTernaryOp(LTernaryOperator<T> func, String name) {
		return new LTernaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LByteUnaryOperatorAttest attestByteUnaryOp(LByteUnaryOperator func) {
		return new LByteUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LByteUnaryOperatorAttest attestByteUnaryOp(LByteUnaryOperator func, String name) {
		return new LByteUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LCharUnaryOperatorAttest attestCharUnaryOp(LCharUnaryOperator func) {
		return new LCharUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LCharUnaryOperatorAttest attestCharUnaryOp(LCharUnaryOperator func, String name) {
		return new LCharUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LDblUnaryOperatorAttest attestDblUnaryOp(LDblUnaryOperator func) {
		return new LDblUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LDblUnaryOperatorAttest attestDblUnaryOp(LDblUnaryOperator func, String name) {
		return new LDblUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LFltUnaryOperatorAttest attestFltUnaryOp(LFltUnaryOperator func) {
		return new LFltUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LFltUnaryOperatorAttest attestFltUnaryOp(LFltUnaryOperator func, String name) {
		return new LFltUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LIntUnaryOperatorAttest attestIntUnaryOp(LIntUnaryOperator func) {
		return new LIntUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LIntUnaryOperatorAttest attestIntUnaryOp(LIntUnaryOperator func, String name) {
		return new LIntUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LLogicalOperatorAttest attestLogicalOp(LLogicalOperator func) {
		return new LLogicalOperatorAttest(func);
	}

	@Nonnull
	public static LLogicalOperatorAttest attestLogicalOp(LLogicalOperator func, String name) {
		return new LLogicalOperatorAttest(func, name);
	}

	@Nonnull
	public static LLongUnaryOperatorAttest attestLongUnaryOp(LLongUnaryOperator func) {
		return new LLongUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LLongUnaryOperatorAttest attestLongUnaryOp(LLongUnaryOperator func, String name) {
		return new LLongUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static LSrtUnaryOperatorAttest attestSrtUnaryOp(LSrtUnaryOperator func) {
		return new LSrtUnaryOperatorAttest(func);
	}

	@Nonnull
	public static LSrtUnaryOperatorAttest attestSrtUnaryOp(LSrtUnaryOperator func, String name) {
		return new LSrtUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static <T> LUnaryOperatorAttest<T> attestUnaryOp(LUnaryOperator<T> func) {
		return new LUnaryOperatorAttest(func);
	}

	@Nonnull
	public static <T> LUnaryOperatorAttest<T> attestUnaryOp(LUnaryOperator<T> func, String name) {
		return new LUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiFunctionAttest<T1, T2, R> attestBiFunc(LBiFunction<T1, T2, R> func) {
		return new LBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiFunctionAttest<T1, T2, R> attestBiFunc(LBiFunction<T1, T2, R> func, String name) {
		return new LBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0FuncAttest<T2, T1, R> attestObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func) {
		return new LObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0FuncAttest<T2, T1, R> attestObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LFunctionAttest<T, R> attestFunc(LFunction<T, R> func) {
		return new LFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LFunctionAttest<T, R> attestFunc(LFunction<T, R> func, String name) {
		return new LFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4, R> LQuadFunctionAttest<T1, T2, T3, T4, R> attestQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) {
		return new LQuadFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4, R> LQuadFunctionAttest<T1, T2, T3, T4, R> attestQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func, String name) {
		return new LQuadFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5, R> LQuintFunctionAttest<T1, T2, T3, T4, T5, R> attestQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func) {
		return new LQuintFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5, R> LQuintFunctionAttest<T1, T2, T3, T4, T5, R> attestQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func, String name) {
		return new LQuintFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, R> LTriFunctionAttest<T1, T2, T3, R> attestTriFunc(LTriFunction<T1, T2, T3, R> func) {
		return new LTriFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, R> LTriFunctionAttest<T1, T2, T3, R> attestTriFunc(LTriFunction<T1, T2, T3, R> func, String name) {
		return new LTriFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T3, T2, R> LObj0Obj2Obj1FuncAttest<T1, T3, T2, R> attestObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func) {
		return new LObj0Obj2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T3, T2, R> LObj0Obj2Obj1FuncAttest<T1, T3, T2, R> attestObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func, String name) {
		return new LObj0Obj2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, T3, R> LObj1BiObj2FuncAttest<T2, T1, T3, R> attestObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func) {
		return new LObj1BiObj2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, T3, R> LObj1BiObj2FuncAttest<T2, T1, T3, R> attestObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func, String name) {
		return new LObj1BiObj2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T3, T1, R> LObj1Obj2Obj0FuncAttest<T2, T3, T1, R> attestObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func) {
		return new LObj1Obj2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T3, T1, R> LObj1Obj2Obj0FuncAttest<T2, T3, T1, R> attestObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func, String name) {
		return new LObj1Obj2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T3, T1, T2, R> LObj2Obj0Obj1FuncAttest<T3, T1, T2, R> attestObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func) {
		return new LObj2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T3, T1, T2, R> LObj2Obj0Obj1FuncAttest<T3, T1, T2, R> attestObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func, String name) {
		return new LObj2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T3, T2, T1, R> LBiObj1Obj0FuncAttest<T3, T2, T1, R> attestBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func) {
		return new LBiObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T3, T2, T1, R> LBiObj1Obj0FuncAttest<T3, T2, T1, R> attestBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func, String name) {
		return new LBiObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static LBoolToByteFunctionAttest attestBoolToByteFunc(LBoolToByteFunction func) {
		return new LBoolToByteFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToByteFunctionAttest attestBoolToByteFunc(LBoolToByteFunction func, String name) {
		return new LBoolToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToCharFunctionAttest attestBoolToCharFunc(LBoolToCharFunction func) {
		return new LBoolToCharFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToCharFunctionAttest attestBoolToCharFunc(LBoolToCharFunction func, String name) {
		return new LBoolToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToDblFunctionAttest attestBoolToDblFunc(LBoolToDblFunction func) {
		return new LBoolToDblFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToDblFunctionAttest attestBoolToDblFunc(LBoolToDblFunction func, String name) {
		return new LBoolToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToFltFunctionAttest attestBoolToFltFunc(LBoolToFltFunction func) {
		return new LBoolToFltFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToFltFunctionAttest attestBoolToFltFunc(LBoolToFltFunction func, String name) {
		return new LBoolToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToIntFunctionAttest attestBoolToIntFunc(LBoolToIntFunction func) {
		return new LBoolToIntFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToIntFunctionAttest attestBoolToIntFunc(LBoolToIntFunction func, String name) {
		return new LBoolToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToLongFunctionAttest attestBoolToLongFunc(LBoolToLongFunction func) {
		return new LBoolToLongFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToLongFunctionAttest attestBoolToLongFunc(LBoolToLongFunction func, String name) {
		return new LBoolToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LBoolToSrtFunctionAttest attestBoolToSrtFunc(LBoolToSrtFunction func) {
		return new LBoolToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LBoolToSrtFunctionAttest attestBoolToSrtFunc(LBoolToSrtFunction func, String name) {
		return new LBoolToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToCharFunctionAttest attestByteToCharFunc(LByteToCharFunction func) {
		return new LByteToCharFunctionAttest(func);
	}

	@Nonnull
	public static LByteToCharFunctionAttest attestByteToCharFunc(LByteToCharFunction func, String name) {
		return new LByteToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToDblFunctionAttest attestByteToDblFunc(LByteToDblFunction func) {
		return new LByteToDblFunctionAttest(func);
	}

	@Nonnull
	public static LByteToDblFunctionAttest attestByteToDblFunc(LByteToDblFunction func, String name) {
		return new LByteToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToFltFunctionAttest attestByteToFltFunc(LByteToFltFunction func) {
		return new LByteToFltFunctionAttest(func);
	}

	@Nonnull
	public static LByteToFltFunctionAttest attestByteToFltFunc(LByteToFltFunction func, String name) {
		return new LByteToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToIntFunctionAttest attestByteToIntFunc(LByteToIntFunction func) {
		return new LByteToIntFunctionAttest(func);
	}

	@Nonnull
	public static LByteToIntFunctionAttest attestByteToIntFunc(LByteToIntFunction func, String name) {
		return new LByteToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToLongFunctionAttest attestByteToLongFunc(LByteToLongFunction func) {
		return new LByteToLongFunctionAttest(func);
	}

	@Nonnull
	public static LByteToLongFunctionAttest attestByteToLongFunc(LByteToLongFunction func, String name) {
		return new LByteToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LByteToSrtFunctionAttest attestByteToSrtFunc(LByteToSrtFunction func) {
		return new LByteToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LByteToSrtFunctionAttest attestByteToSrtFunc(LByteToSrtFunction func, String name) {
		return new LByteToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToByteFunctionAttest attestCharToByteFunc(LCharToByteFunction func) {
		return new LCharToByteFunctionAttest(func);
	}

	@Nonnull
	public static LCharToByteFunctionAttest attestCharToByteFunc(LCharToByteFunction func, String name) {
		return new LCharToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToDblFunctionAttest attestCharToDblFunc(LCharToDblFunction func) {
		return new LCharToDblFunctionAttest(func);
	}

	@Nonnull
	public static LCharToDblFunctionAttest attestCharToDblFunc(LCharToDblFunction func, String name) {
		return new LCharToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToFltFunctionAttest attestCharToFltFunc(LCharToFltFunction func) {
		return new LCharToFltFunctionAttest(func);
	}

	@Nonnull
	public static LCharToFltFunctionAttest attestCharToFltFunc(LCharToFltFunction func, String name) {
		return new LCharToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToIntFunctionAttest attestCharToIntFunc(LCharToIntFunction func) {
		return new LCharToIntFunctionAttest(func);
	}

	@Nonnull
	public static LCharToIntFunctionAttest attestCharToIntFunc(LCharToIntFunction func, String name) {
		return new LCharToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToLongFunctionAttest attestCharToLongFunc(LCharToLongFunction func) {
		return new LCharToLongFunctionAttest(func);
	}

	@Nonnull
	public static LCharToLongFunctionAttest attestCharToLongFunc(LCharToLongFunction func, String name) {
		return new LCharToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LCharToSrtFunctionAttest attestCharToSrtFunc(LCharToSrtFunction func) {
		return new LCharToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LCharToSrtFunctionAttest attestCharToSrtFunc(LCharToSrtFunction func, String name) {
		return new LCharToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToByteFunctionAttest attestDblToByteFunc(LDblToByteFunction func) {
		return new LDblToByteFunctionAttest(func);
	}

	@Nonnull
	public static LDblToByteFunctionAttest attestDblToByteFunc(LDblToByteFunction func, String name) {
		return new LDblToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToCharFunctionAttest attestDblToCharFunc(LDblToCharFunction func) {
		return new LDblToCharFunctionAttest(func);
	}

	@Nonnull
	public static LDblToCharFunctionAttest attestDblToCharFunc(LDblToCharFunction func, String name) {
		return new LDblToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToFltFunctionAttest attestDblToFltFunc(LDblToFltFunction func) {
		return new LDblToFltFunctionAttest(func);
	}

	@Nonnull
	public static LDblToFltFunctionAttest attestDblToFltFunc(LDblToFltFunction func, String name) {
		return new LDblToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToIntFunctionAttest attestDblToIntFunc(LDblToIntFunction func) {
		return new LDblToIntFunctionAttest(func);
	}

	@Nonnull
	public static LDblToIntFunctionAttest attestDblToIntFunc(LDblToIntFunction func, String name) {
		return new LDblToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToLongFunctionAttest attestDblToLongFunc(LDblToLongFunction func) {
		return new LDblToLongFunctionAttest(func);
	}

	@Nonnull
	public static LDblToLongFunctionAttest attestDblToLongFunc(LDblToLongFunction func, String name) {
		return new LDblToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LDblToSrtFunctionAttest attestDblToSrtFunc(LDblToSrtFunction func) {
		return new LDblToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LDblToSrtFunctionAttest attestDblToSrtFunc(LDblToSrtFunction func, String name) {
		return new LDblToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToByteFunctionAttest attestFltToByteFunc(LFltToByteFunction func) {
		return new LFltToByteFunctionAttest(func);
	}

	@Nonnull
	public static LFltToByteFunctionAttest attestFltToByteFunc(LFltToByteFunction func, String name) {
		return new LFltToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToCharFunctionAttest attestFltToCharFunc(LFltToCharFunction func) {
		return new LFltToCharFunctionAttest(func);
	}

	@Nonnull
	public static LFltToCharFunctionAttest attestFltToCharFunc(LFltToCharFunction func, String name) {
		return new LFltToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToDblFunctionAttest attestFltToDblFunc(LFltToDblFunction func) {
		return new LFltToDblFunctionAttest(func);
	}

	@Nonnull
	public static LFltToDblFunctionAttest attestFltToDblFunc(LFltToDblFunction func, String name) {
		return new LFltToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToIntFunctionAttest attestFltToIntFunc(LFltToIntFunction func) {
		return new LFltToIntFunctionAttest(func);
	}

	@Nonnull
	public static LFltToIntFunctionAttest attestFltToIntFunc(LFltToIntFunction func, String name) {
		return new LFltToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToLongFunctionAttest attestFltToLongFunc(LFltToLongFunction func) {
		return new LFltToLongFunctionAttest(func);
	}

	@Nonnull
	public static LFltToLongFunctionAttest attestFltToLongFunc(LFltToLongFunction func, String name) {
		return new LFltToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LFltToSrtFunctionAttest attestFltToSrtFunc(LFltToSrtFunction func) {
		return new LFltToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LFltToSrtFunctionAttest attestFltToSrtFunc(LFltToSrtFunction func, String name) {
		return new LFltToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToByteFunctionAttest attestIntToByteFunc(LIntToByteFunction func) {
		return new LIntToByteFunctionAttest(func);
	}

	@Nonnull
	public static LIntToByteFunctionAttest attestIntToByteFunc(LIntToByteFunction func, String name) {
		return new LIntToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToCharFunctionAttest attestIntToCharFunc(LIntToCharFunction func) {
		return new LIntToCharFunctionAttest(func);
	}

	@Nonnull
	public static LIntToCharFunctionAttest attestIntToCharFunc(LIntToCharFunction func, String name) {
		return new LIntToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToDblFunctionAttest attestIntToDblFunc(LIntToDblFunction func) {
		return new LIntToDblFunctionAttest(func);
	}

	@Nonnull
	public static LIntToDblFunctionAttest attestIntToDblFunc(LIntToDblFunction func, String name) {
		return new LIntToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToFltFunctionAttest attestIntToFltFunc(LIntToFltFunction func) {
		return new LIntToFltFunctionAttest(func);
	}

	@Nonnull
	public static LIntToFltFunctionAttest attestIntToFltFunc(LIntToFltFunction func, String name) {
		return new LIntToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToLongFunctionAttest attestIntToLongFunc(LIntToLongFunction func) {
		return new LIntToLongFunctionAttest(func);
	}

	@Nonnull
	public static LIntToLongFunctionAttest attestIntToLongFunc(LIntToLongFunction func, String name) {
		return new LIntToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static LIntToSrtFunctionAttest attestIntToSrtFunc(LIntToSrtFunction func) {
		return new LIntToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LIntToSrtFunctionAttest attestIntToSrtFunc(LIntToSrtFunction func, String name) {
		return new LIntToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToByteFunctionAttest attestLongToByteFunc(LLongToByteFunction func) {
		return new LLongToByteFunctionAttest(func);
	}

	@Nonnull
	public static LLongToByteFunctionAttest attestLongToByteFunc(LLongToByteFunction func, String name) {
		return new LLongToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToCharFunctionAttest attestLongToCharFunc(LLongToCharFunction func) {
		return new LLongToCharFunctionAttest(func);
	}

	@Nonnull
	public static LLongToCharFunctionAttest attestLongToCharFunc(LLongToCharFunction func, String name) {
		return new LLongToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToDblFunctionAttest attestLongToDblFunc(LLongToDblFunction func) {
		return new LLongToDblFunctionAttest(func);
	}

	@Nonnull
	public static LLongToDblFunctionAttest attestLongToDblFunc(LLongToDblFunction func, String name) {
		return new LLongToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToFltFunctionAttest attestLongToFltFunc(LLongToFltFunction func) {
		return new LLongToFltFunctionAttest(func);
	}

	@Nonnull
	public static LLongToFltFunctionAttest attestLongToFltFunc(LLongToFltFunction func, String name) {
		return new LLongToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToIntFunctionAttest attestLongToIntFunc(LLongToIntFunction func) {
		return new LLongToIntFunctionAttest(func);
	}

	@Nonnull
	public static LLongToIntFunctionAttest attestLongToIntFunc(LLongToIntFunction func, String name) {
		return new LLongToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LLongToSrtFunctionAttest attestLongToSrtFunc(LLongToSrtFunction func) {
		return new LLongToSrtFunctionAttest(func);
	}

	@Nonnull
	public static LLongToSrtFunctionAttest attestLongToSrtFunc(LLongToSrtFunction func, String name) {
		return new LLongToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToByteFunctionAttest attestSrtToByteFunc(LSrtToByteFunction func) {
		return new LSrtToByteFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToByteFunctionAttest attestSrtToByteFunc(LSrtToByteFunction func, String name) {
		return new LSrtToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToCharFunctionAttest attestSrtToCharFunc(LSrtToCharFunction func) {
		return new LSrtToCharFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToCharFunctionAttest attestSrtToCharFunc(LSrtToCharFunction func, String name) {
		return new LSrtToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToDblFunctionAttest attestSrtToDblFunc(LSrtToDblFunction func) {
		return new LSrtToDblFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToDblFunctionAttest attestSrtToDblFunc(LSrtToDblFunction func, String name) {
		return new LSrtToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToFltFunctionAttest attestSrtToFltFunc(LSrtToFltFunction func) {
		return new LSrtToFltFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToFltFunctionAttest attestSrtToFltFunc(LSrtToFltFunction func, String name) {
		return new LSrtToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToIntFunctionAttest attestSrtToIntFunc(LSrtToIntFunction func) {
		return new LSrtToIntFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToIntFunctionAttest attestSrtToIntFunc(LSrtToIntFunction func, String name) {
		return new LSrtToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static LSrtToLongFunctionAttest attestSrtToLongFunc(LSrtToLongFunction func) {
		return new LSrtToLongFunctionAttest(func);
	}

	@Nonnull
	public static LSrtToLongFunctionAttest attestSrtToLongFunc(LSrtToLongFunction func, String name) {
		return new LSrtToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LBiBoolFunctionAttest<R> attestBiBoolFunc(LBiBoolFunction<R> func) {
		return new LBiBoolFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiBoolFunctionAttest<R> attestBiBoolFunc(LBiBoolFunction<R> func, String name) {
		return new LBiBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LBool1Bool0FuncAttest<R> attestBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func) {
		return new LBool1Bool0FuncAttest(func);
	}

	@Nonnull
	public static <R> LBool1Bool0FuncAttest<R> attestBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func, String name) {
		return new LBool1Bool0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiByteFunctionAttest<R> attestBiByteFunc(LBiByteFunction<R> func) {
		return new LBiByteFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiByteFunctionAttest<R> attestBiByteFunc(LBiByteFunction<R> func, String name) {
		return new LBiByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LByte1Byte0FuncAttest<R> attestByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func) {
		return new LByte1Byte0FuncAttest(func);
	}

	@Nonnull
	public static <R> LByte1Byte0FuncAttest<R> attestByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func, String name) {
		return new LByte1Byte0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiCharFunctionAttest<R> attestBiCharFunc(LBiCharFunction<R> func) {
		return new LBiCharFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiCharFunctionAttest<R> attestBiCharFunc(LBiCharFunction<R> func, String name) {
		return new LBiCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LChar1Char0FuncAttest<R> attestChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func) {
		return new LChar1Char0FuncAttest(func);
	}

	@Nonnull
	public static <R> LChar1Char0FuncAttest<R> attestChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func, String name) {
		return new LChar1Char0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiDblFunctionAttest<R> attestBiDblFunc(LBiDblFunction<R> func) {
		return new LBiDblFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiDblFunctionAttest<R> attestBiDblFunc(LBiDblFunction<R> func, String name) {
		return new LBiDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LDbl1Dbl0FuncAttest<R> attestDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func) {
		return new LDbl1Dbl0FuncAttest(func);
	}

	@Nonnull
	public static <R> LDbl1Dbl0FuncAttest<R> attestDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func, String name) {
		return new LDbl1Dbl0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiFltFunctionAttest<R> attestBiFltFunc(LBiFltFunction<R> func) {
		return new LBiFltFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiFltFunctionAttest<R> attestBiFltFunc(LBiFltFunction<R> func, String name) {
		return new LBiFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LFlt1Flt0FuncAttest<R> attestFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func) {
		return new LFlt1Flt0FuncAttest(func);
	}

	@Nonnull
	public static <R> LFlt1Flt0FuncAttest<R> attestFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func, String name) {
		return new LFlt1Flt0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiIntFunctionAttest<R> attestBiIntFunc(LBiIntFunction<R> func) {
		return new LBiIntFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiIntFunctionAttest<R> attestBiIntFunc(LBiIntFunction<R> func, String name) {
		return new LBiIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LInt1Int0FuncAttest<R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) {
		return new LInt1Int0FuncAttest(func);
	}

	@Nonnull
	public static <R> LInt1Int0FuncAttest<R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func, String name) {
		return new LInt1Int0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiLongFunctionAttest<R> attestBiLongFunc(LBiLongFunction<R> func) {
		return new LBiLongFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiLongFunctionAttest<R> attestBiLongFunc(LBiLongFunction<R> func, String name) {
		return new LBiLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LLong1Long0FuncAttest<R> attestLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func) {
		return new LLong1Long0FuncAttest(func);
	}

	@Nonnull
	public static <R> LLong1Long0FuncAttest<R> attestLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func, String name) {
		return new LLong1Long0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjBoolFunctionAttest<T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjBoolFunctionAttest<T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func, String name) {
		return new LBiObjBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Bool2Obj1FuncAttest<T1, T2, R> attestObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func) {
		return new LObj0Bool2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Bool2Obj1FuncAttest<T1, T2, R> attestObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Bool2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Bool2FuncAttest<T2, T1, R> attestObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func) {
		return new LObj1Obj0Bool2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Bool2FuncAttest<T2, T1, R> attestObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Bool2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Bool2Obj0FuncAttest<T2, T1, R> attestObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func) {
		return new LObj1Bool2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Bool2Obj0FuncAttest<T2, T1, R> attestObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Bool2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBool2Obj0Obj1FuncAttest<T1, T2, R> attestBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func) {
		return new LBool2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBool2Obj0Obj1FuncAttest<T1, T2, R> attestBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LBool2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LBool2Obj1Obj0FuncAttest<T2, T1, R> attestBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func) {
		return new LBool2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LBool2Obj1Obj0FuncAttest<T2, T1, R> attestBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LBool2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjByteFunctionAttest<T1, T2, R> attestBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) {
		return new LBiObjByteFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjByteFunctionAttest<T1, T2, R> attestBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func, String name) {
		return new LBiObjByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Byte2Obj1FuncAttest<T1, T2, R> attestObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func) {
		return new LObj0Byte2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Byte2Obj1FuncAttest<T1, T2, R> attestObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Byte2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Byte2FuncAttest<T2, T1, R> attestObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func) {
		return new LObj1Obj0Byte2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Byte2FuncAttest<T2, T1, R> attestObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Byte2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Byte2Obj0FuncAttest<T2, T1, R> attestObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func) {
		return new LObj1Byte2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Byte2Obj0FuncAttest<T2, T1, R> attestObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Byte2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LByte2Obj0Obj1FuncAttest<T1, T2, R> attestByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func) {
		return new LByte2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LByte2Obj0Obj1FuncAttest<T1, T2, R> attestByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LByte2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LByte2Obj1Obj0FuncAttest<T2, T1, R> attestByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func) {
		return new LByte2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LByte2Obj1Obj0FuncAttest<T2, T1, R> attestByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LByte2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjCharFunctionAttest<T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjCharFunctionAttest<T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func, String name) {
		return new LBiObjCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Char2Obj1FuncAttest<T1, T2, R> attestObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func) {
		return new LObj0Char2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Char2Obj1FuncAttest<T1, T2, R> attestObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Char2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Char2FuncAttest<T2, T1, R> attestObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func) {
		return new LObj1Obj0Char2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Char2FuncAttest<T2, T1, R> attestObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Char2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Char2Obj0FuncAttest<T2, T1, R> attestObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func) {
		return new LObj1Char2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Char2Obj0FuncAttest<T2, T1, R> attestObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Char2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LChar2Obj0Obj1FuncAttest<T1, T2, R> attestChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func) {
		return new LChar2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LChar2Obj0Obj1FuncAttest<T1, T2, R> attestChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LChar2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LChar2Obj1Obj0FuncAttest<T2, T1, R> attestChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func) {
		return new LChar2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LChar2Obj1Obj0FuncAttest<T2, T1, R> attestChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LChar2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjDblFunctionAttest<T1, T2, R> attestBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) {
		return new LBiObjDblFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjDblFunctionAttest<T1, T2, R> attestBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func, String name) {
		return new LBiObjDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Dbl2Obj1FuncAttest<T1, T2, R> attestObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func) {
		return new LObj0Dbl2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Dbl2Obj1FuncAttest<T1, T2, R> attestObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Dbl2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Dbl2FuncAttest<T2, T1, R> attestObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func) {
		return new LObj1Obj0Dbl2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Dbl2FuncAttest<T2, T1, R> attestObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Dbl2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Dbl2Obj0FuncAttest<T2, T1, R> attestObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func) {
		return new LObj1Dbl2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Dbl2Obj0FuncAttest<T2, T1, R> attestObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Dbl2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LDbl2Obj0Obj1FuncAttest<T1, T2, R> attestDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func) {
		return new LDbl2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LDbl2Obj0Obj1FuncAttest<T1, T2, R> attestDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LDbl2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LDbl2Obj1Obj0FuncAttest<T2, T1, R> attestDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func) {
		return new LDbl2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LDbl2Obj1Obj0FuncAttest<T2, T1, R> attestDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LDbl2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjFltFunctionAttest<T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) {
		return new LBiObjFltFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjFltFunctionAttest<T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func, String name) {
		return new LBiObjFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Flt2Obj1FuncAttest<T1, T2, R> attestObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func) {
		return new LObj0Flt2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Flt2Obj1FuncAttest<T1, T2, R> attestObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Flt2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Flt2FuncAttest<T2, T1, R> attestObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func) {
		return new LObj1Obj0Flt2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Flt2FuncAttest<T2, T1, R> attestObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Flt2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Flt2Obj0FuncAttest<T2, T1, R> attestObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func) {
		return new LObj1Flt2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Flt2Obj0FuncAttest<T2, T1, R> attestObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Flt2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LFlt2Obj0Obj1FuncAttest<T1, T2, R> attestFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func) {
		return new LFlt2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LFlt2Obj0Obj1FuncAttest<T1, T2, R> attestFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LFlt2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LFlt2Obj1Obj0FuncAttest<T2, T1, R> attestFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func) {
		return new LFlt2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LFlt2Obj1Obj0FuncAttest<T2, T1, R> attestFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LFlt2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjIntFunctionAttest<T1, T2, R> attestBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) {
		return new LBiObjIntFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjIntFunctionAttest<T1, T2, R> attestBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func, String name) {
		return new LBiObjIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Int2FuncAttest<T2, T1, R> attestObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func) {
		return new LObj1Obj0Int2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Int2FuncAttest<T2, T1, R> attestObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Int2FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LInt2Obj0Obj1FuncAttest<T1, T2, R> attestInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func) {
		return new LInt2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LInt2Obj0Obj1FuncAttest<T1, T2, R> attestInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LInt2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LInt2Obj1Obj0FuncAttest<T2, T1, R> attestInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func) {
		return new LInt2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LInt2Obj1Obj0FuncAttest<T2, T1, R> attestInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LInt2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjLongFunctionAttest<T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjLongFunctionAttest<T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func, String name) {
		return new LBiObjLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Long2Obj1FuncAttest<T1, T2, R> attestObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func) {
		return new LObj0Long2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Long2Obj1FuncAttest<T1, T2, R> attestObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Long2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Long2FuncAttest<T2, T1, R> attestObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func) {
		return new LObj1Obj0Long2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Long2FuncAttest<T2, T1, R> attestObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Long2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Long2Obj0FuncAttest<T2, T1, R> attestObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func) {
		return new LObj1Long2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Long2Obj0FuncAttest<T2, T1, R> attestObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Long2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LLong2Obj0Obj1FuncAttest<T1, T2, R> attestLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func) {
		return new LLong2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LLong2Obj0Obj1FuncAttest<T1, T2, R> attestLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LLong2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LLong2Obj1Obj0FuncAttest<T2, T1, R> attestLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func) {
		return new LLong2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LLong2Obj1Obj0FuncAttest<T2, T1, R> attestLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LLong2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjSrtFunctionAttest<T1, T2, R> attestBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) {
		return new LBiObjSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjSrtFunctionAttest<T1, T2, R> attestBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func, String name) {
		return new LBiObjSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Srt2Obj1FuncAttest<T1, T2, R> attestObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func) {
		return new LObj0Srt2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObj0Srt2Obj1FuncAttest<T1, T2, R> attestObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func, String name) {
		return new LObj0Srt2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Srt2FuncAttest<T2, T1, R> attestObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func) {
		return new LObj1Obj0Srt2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Obj0Srt2FuncAttest<T2, T1, R> attestObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func, String name) {
		return new LObj1Obj0Srt2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Srt2Obj0FuncAttest<T2, T1, R> attestObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func) {
		return new LObj1Srt2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj1Srt2Obj0FuncAttest<T2, T1, R> attestObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func, String name) {
		return new LObj1Srt2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LSrt2Obj0Obj1FuncAttest<T1, T2, R> attestSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func) {
		return new LSrt2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LSrt2Obj0Obj1FuncAttest<T1, T2, R> attestSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func, String name) {
		return new LSrt2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LSrt2Obj1Obj0FuncAttest<T2, T1, R> attestSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func) {
		return new LSrt2Obj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LSrt2Obj1Obj0FuncAttest<T2, T1, R> attestSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func, String name) {
		return new LSrt2Obj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBiSrtFunctionAttest<R> attestBiSrtFunc(LBiSrtFunction<R> func) {
		return new LBiSrtFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiSrtFunctionAttest<R> attestBiSrtFunc(LBiSrtFunction<R> func, String name) {
		return new LBiSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LSrt1Srt0FuncAttest<R> attestSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func) {
		return new LSrt1Srt0FuncAttest(func);
	}

	@Nonnull
	public static <R> LSrt1Srt0FuncAttest<R> attestSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func, String name) {
		return new LSrt1Srt0FuncAttest(func, name);
	}

	@Nonnull
	public static <R> LBoolFunctionAttest<R> attestBoolFunc(LBoolFunction<R> func) {
		return new LBoolFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBoolFunctionAttest<R> attestBoolFunc(LBoolFunction<R> func, String name) {
		return new LBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LByteFunctionAttest<R> attestByteFunc(LByteFunction<R> func) {
		return new LByteFunctionAttest(func);
	}

	@Nonnull
	public static <R> LByteFunctionAttest<R> attestByteFunc(LByteFunction<R> func, String name) {
		return new LByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LCharFunctionAttest<R> attestCharFunc(LCharFunction<R> func) {
		return new LCharFunctionAttest(func);
	}

	@Nonnull
	public static <R> LCharFunctionAttest<R> attestCharFunc(LCharFunction<R> func, String name) {
		return new LCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LDblFunctionAttest<R> attestDblFunc(LDblFunction<R> func) {
		return new LDblFunctionAttest(func);
	}

	@Nonnull
	public static <R> LDblFunctionAttest<R> attestDblFunc(LDblFunction<R> func, String name) {
		return new LDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LFltFunctionAttest<R> attestFltFunc(LFltFunction<R> func) {
		return new LFltFunctionAttest(func);
	}

	@Nonnull
	public static <R> LFltFunctionAttest<R> attestFltFunc(LFltFunction<R> func, String name) {
		return new LFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LIntFunctionAttest<R> attestIntFunc(LIntFunction<R> func) {
		return new LIntFunctionAttest(func);
	}

	@Nonnull
	public static <R> LIntFunctionAttest<R> attestIntFunc(LIntFunction<R> func, String name) {
		return new LIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LLongFunctionAttest<R> attestLongFunc(LLongFunction<R> func) {
		return new LLongFunctionAttest(func);
	}

	@Nonnull
	public static <R> LLongFunctionAttest<R> attestLongFunc(LLongFunction<R> func, String name) {
		return new LLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjBiIntFunctionAttest<T, R> attestObjBiIntFunc(LObjBiIntFunction<T, R> func) {
		return new LObjBiIntFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjBiIntFunctionAttest<T, R> attestObjBiIntFunc(LObjBiIntFunction<T, R> func, String name) {
		return new LObjBiIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObj0Int2Int1FuncAttest<T, R> attestObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func) {
		return new LObj0Int2Int1FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObj0Int2Int1FuncAttest<T, R> attestObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func, String name) {
		return new LObj0Int2Int1FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LInt1Obj0Int2FuncAttest<T, R> attestInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func) {
		return new LInt1Obj0Int2FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LInt1Obj0Int2FuncAttest<T, R> attestInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func, String name) {
		return new LInt1Obj0Int2FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LInt1Int2Obj0FuncAttest<T, R> attestInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func) {
		return new LInt1Int2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LInt1Int2Obj0FuncAttest<T, R> attestInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func, String name) {
		return new LInt1Int2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LInt2Obj0Int1FuncAttest<T, R> attestInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func) {
		return new LInt2Obj0Int1FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LInt2Obj0Int1FuncAttest<T, R> attestInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func, String name) {
		return new LInt2Obj0Int1FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LBiInt1Obj0FuncAttest<T, R> attestBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func) {
		return new LBiInt1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LBiInt1Obj0FuncAttest<T, R> attestBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func, String name) {
		return new LBiInt1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjBiLongFunctionAttest<T, R> attestObjBiLongFunc(LObjBiLongFunction<T, R> func) {
		return new LObjBiLongFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjBiLongFunctionAttest<T, R> attestObjBiLongFunc(LObjBiLongFunction<T, R> func, String name) {
		return new LObjBiLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObj0Long2Long1FuncAttest<T, R> attestObj0Long2Long1Func(LObjBiLongFunction.LObj0Long2Long1Func<T, R> func) {
		return new LObj0Long2Long1FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObj0Long2Long1FuncAttest<T, R> attestObj0Long2Long1Func(LObjBiLongFunction.LObj0Long2Long1Func<T, R> func, String name) {
		return new LObj0Long2Long1FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLong1Obj0Long2FuncAttest<T, R> attestLong1Obj0Long2Func(LObjBiLongFunction.LLong1Obj0Long2Func<T, R> func) {
		return new LLong1Obj0Long2FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLong1Obj0Long2FuncAttest<T, R> attestLong1Obj0Long2Func(LObjBiLongFunction.LLong1Obj0Long2Func<T, R> func, String name) {
		return new LLong1Obj0Long2FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLong1Long2Obj0FuncAttest<T, R> attestLong1Long2Obj0Func(LObjBiLongFunction.LLong1Long2Obj0Func<T, R> func) {
		return new LLong1Long2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLong1Long2Obj0FuncAttest<T, R> attestLong1Long2Obj0Func(LObjBiLongFunction.LLong1Long2Obj0Func<T, R> func, String name) {
		return new LLong1Long2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLong2Obj0Long1FuncAttest<T, R> attestLong2Obj0Long1Func(LObjBiLongFunction.LLong2Obj0Long1Func<T, R> func) {
		return new LLong2Obj0Long1FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLong2Obj0Long1FuncAttest<T, R> attestLong2Obj0Long1Func(LObjBiLongFunction.LLong2Obj0Long1Func<T, R> func, String name) {
		return new LLong2Obj0Long1FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LBiLong1Obj0FuncAttest<T, R> attestBiLong1Obj0Func(LObjBiLongFunction.LBiLong1Obj0Func<T, R> func) {
		return new LBiLong1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T, R> LBiLong1Obj0FuncAttest<T, R> attestBiLong1Obj0Func(LObjBiLongFunction.LBiLong1Obj0Func<T, R> func, String name) {
		return new LBiLong1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjBoolFunctionAttest<T, R> attestObjBoolFunc(LObjBoolFunction<T, R> func) {
		return new LObjBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjBoolFunctionAttest<T, R> attestObjBoolFunc(LObjBoolFunction<T, R> func, String name) {
		return new LObjBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LBoolObjFuncAttest<T, R> attestBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func) {
		return new LBoolObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LBoolObjFuncAttest<T, R> attestBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func, String name) {
		return new LBoolObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjByteFunctionAttest<T, R> attestObjByteFunc(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjByteFunctionAttest<T, R> attestObjByteFunc(LObjByteFunction<T, R> func, String name) {
		return new LObjByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LByteObjFuncAttest<T, R> attestByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func) {
		return new LByteObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LByteObjFuncAttest<T, R> attestByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func, String name) {
		return new LByteObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjCharFunctionAttest<T, R> attestObjCharFunc(LObjCharFunction<T, R> func) {
		return new LObjCharFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjCharFunctionAttest<T, R> attestObjCharFunc(LObjCharFunction<T, R> func, String name) {
		return new LObjCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LCharObjFuncAttest<T, R> attestCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func) {
		return new LCharObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LCharObjFuncAttest<T, R> attestCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func, String name) {
		return new LCharObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjDblFunctionAttest<T, R> attestObjDblFunc(LObjDblFunction<T, R> func) {
		return new LObjDblFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjDblFunctionAttest<T, R> attestObjDblFunc(LObjDblFunction<T, R> func, String name) {
		return new LObjDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LDblObjFuncAttest<T, R> attestDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func) {
		return new LDblObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LDblObjFuncAttest<T, R> attestDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func, String name) {
		return new LDblObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjFltFunctionAttest<T, R> attestObjFltFunc(LObjFltFunction<T, R> func) {
		return new LObjFltFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjFltFunctionAttest<T, R> attestObjFltFunc(LObjFltFunction<T, R> func, String name) {
		return new LObjFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LFltObjFuncAttest<T, R> attestFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func) {
		return new LFltObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LFltObjFuncAttest<T, R> attestFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func, String name) {
		return new LFltObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntBoolFunctionAttest<T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func) {
		return new LObjIntBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntBoolFunctionAttest<T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func, String name) {
		return new LObjIntBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjBoolIntFuncAttest<T, R> attestObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func) {
		return new LObjBoolIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjBoolIntFuncAttest<T, R> attestObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func, String name) {
		return new LObjBoolIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjBoolFuncAttest<T, R> attestIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func) {
		return new LIntObjBoolFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjBoolFuncAttest<T, R> attestIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func, String name) {
		return new LIntObjBoolFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntBoolObjFuncAttest<T, R> attestIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func) {
		return new LIntBoolObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntBoolObjFuncAttest<T, R> attestIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func, String name) {
		return new LIntBoolObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LBoolObjIntFuncAttest<T, R> attestBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func) {
		return new LBoolObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LBoolObjIntFuncAttest<T, R> attestBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func, String name) {
		return new LBoolObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LBoolIntObjFuncAttest<T, R> attestBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func) {
		return new LBoolIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LBoolIntObjFuncAttest<T, R> attestBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func, String name) {
		return new LBoolIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntByteFunctionAttest<T, R> attestObjIntByteFunc(LObjIntByteFunction<T, R> func) {
		return new LObjIntByteFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntByteFunctionAttest<T, R> attestObjIntByteFunc(LObjIntByteFunction<T, R> func, String name) {
		return new LObjIntByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjByteIntFuncAttest<T, R> attestObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func) {
		return new LObjByteIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjByteIntFuncAttest<T, R> attestObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func, String name) {
		return new LObjByteIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjByteFuncAttest<T, R> attestIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func) {
		return new LIntObjByteFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjByteFuncAttest<T, R> attestIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func, String name) {
		return new LIntObjByteFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntByteObjFuncAttest<T, R> attestIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func) {
		return new LIntByteObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntByteObjFuncAttest<T, R> attestIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func, String name) {
		return new LIntByteObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LByteObjIntFuncAttest<T, R> attestByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func) {
		return new LByteObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LByteObjIntFuncAttest<T, R> attestByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func, String name) {
		return new LByteObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LByteIntObjFuncAttest<T, R> attestByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func) {
		return new LByteIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LByteIntObjFuncAttest<T, R> attestByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func, String name) {
		return new LByteIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntCharFunctionAttest<T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func) {
		return new LObjIntCharFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntCharFunctionAttest<T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func, String name) {
		return new LObjIntCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjCharIntFuncAttest<T, R> attestObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func) {
		return new LObjCharIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjCharIntFuncAttest<T, R> attestObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func, String name) {
		return new LObjCharIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjCharFuncAttest<T, R> attestIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func) {
		return new LIntObjCharFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjCharFuncAttest<T, R> attestIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func, String name) {
		return new LIntObjCharFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntCharObjFuncAttest<T, R> attestIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func) {
		return new LIntCharObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntCharObjFuncAttest<T, R> attestIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func, String name) {
		return new LIntCharObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LCharObjIntFuncAttest<T, R> attestCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func) {
		return new LCharObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LCharObjIntFuncAttest<T, R> attestCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func, String name) {
		return new LCharObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LCharIntObjFuncAttest<T, R> attestCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func) {
		return new LCharIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LCharIntObjFuncAttest<T, R> attestCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func, String name) {
		return new LCharIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntDblFunctionAttest<T, R> attestObjIntDblFunc(LObjIntDblFunction<T, R> func) {
		return new LObjIntDblFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntDblFunctionAttest<T, R> attestObjIntDblFunc(LObjIntDblFunction<T, R> func, String name) {
		return new LObjIntDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjDblIntFuncAttest<T, R> attestObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func) {
		return new LObjDblIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjDblIntFuncAttest<T, R> attestObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func, String name) {
		return new LObjDblIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjDblFuncAttest<T, R> attestIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func) {
		return new LIntObjDblFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjDblFuncAttest<T, R> attestIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func, String name) {
		return new LIntObjDblFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntDblObjFuncAttest<T, R> attestIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func) {
		return new LIntDblObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntDblObjFuncAttest<T, R> attestIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func, String name) {
		return new LIntDblObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LDblObjIntFuncAttest<T, R> attestDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func) {
		return new LDblObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LDblObjIntFuncAttest<T, R> attestDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func, String name) {
		return new LDblObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LDblIntObjFuncAttest<T, R> attestDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func) {
		return new LDblIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LDblIntObjFuncAttest<T, R> attestDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func, String name) {
		return new LDblIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntFltFunctionAttest<T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func) {
		return new LObjIntFltFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntFltFunctionAttest<T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func, String name) {
		return new LObjIntFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjFltIntFuncAttest<T, R> attestObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func) {
		return new LObjFltIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjFltIntFuncAttest<T, R> attestObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func, String name) {
		return new LObjFltIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjFltFuncAttest<T, R> attestIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func) {
		return new LIntObjFltFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjFltFuncAttest<T, R> attestIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func, String name) {
		return new LIntObjFltFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntFltObjFuncAttest<T, R> attestIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func) {
		return new LIntFltObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntFltObjFuncAttest<T, R> attestIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func, String name) {
		return new LIntFltObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LFltObjIntFuncAttest<T, R> attestFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func) {
		return new LFltObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LFltObjIntFuncAttest<T, R> attestFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func, String name) {
		return new LFltObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LFltIntObjFuncAttest<T, R> attestFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func) {
		return new LFltIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LFltIntObjFuncAttest<T, R> attestFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func, String name) {
		return new LFltIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntLongFunctionAttest<T, R> attestObjIntLongFunc(LObjIntLongFunction<T, R> func) {
		return new LObjIntLongFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntLongFunctionAttest<T, R> attestObjIntLongFunc(LObjIntLongFunction<T, R> func, String name) {
		return new LObjIntLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjLongIntFuncAttest<T, R> attestObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func) {
		return new LObjLongIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjLongIntFuncAttest<T, R> attestObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func, String name) {
		return new LObjLongIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjLongFuncAttest<T, R> attestIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func) {
		return new LIntObjLongFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjLongFuncAttest<T, R> attestIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func, String name) {
		return new LIntObjLongFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntLongObjFuncAttest<T, R> attestIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func) {
		return new LIntLongObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntLongObjFuncAttest<T, R> attestIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func, String name) {
		return new LIntLongObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLongObjIntFuncAttest<T, R> attestLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func) {
		return new LLongObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLongObjIntFuncAttest<T, R> attestLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func, String name) {
		return new LLongObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLongIntObjFuncAttest<T, R> attestLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func) {
		return new LLongIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLongIntObjFuncAttest<T, R> attestLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func, String name) {
		return new LLongIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LObjIntObjFunctionAttest<T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) {
		return new LObjIntObjFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObjIntObjFunctionAttest<T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func, String name) {
		return new LObjIntObjFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> LInt1BiObj2FuncAttest<T1, T2, R> attestInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func) {
		return new LInt1BiObj2FuncAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LInt1BiObj2FuncAttest<T1, T2, R> attestInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func, String name) {
		return new LInt1BiObj2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LInt1Obj2Obj0FuncAttest<T2, T1, R> attestInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func) {
		return new LInt1Obj2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LInt1Obj2Obj0FuncAttest<T2, T1, R> attestInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func, String name) {
		return new LInt1Obj2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, R> LObj2Int1Obj0FuncAttest<T2, T1, R> attestObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func) {
		return new LObj2Int1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, R> LObj2Int1Obj0FuncAttest<T2, T1, R> attestObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func, String name) {
		return new LObj2Int1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjIntSrtFunctionAttest<T, R> attestObjIntSrtFunc(LObjIntSrtFunction<T, R> func) {
		return new LObjIntSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntSrtFunctionAttest<T, R> attestObjIntSrtFunc(LObjIntSrtFunction<T, R> func, String name) {
		return new LObjIntSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjSrtIntFuncAttest<T, R> attestObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func) {
		return new LObjSrtIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LObjSrtIntFuncAttest<T, R> attestObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func, String name) {
		return new LObjSrtIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjSrtFuncAttest<T, R> attestIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func) {
		return new LIntObjSrtFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjSrtFuncAttest<T, R> attestIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func, String name) {
		return new LIntObjSrtFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntSrtObjFuncAttest<T, R> attestIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func) {
		return new LIntSrtObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntSrtObjFuncAttest<T, R> attestIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func, String name) {
		return new LIntSrtObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LSrtObjIntFuncAttest<T, R> attestSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func) {
		return new LSrtObjIntFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LSrtObjIntFuncAttest<T, R> attestSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func, String name) {
		return new LSrtObjIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LSrtIntObjFuncAttest<T, R> attestSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func) {
		return new LSrtIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LSrtIntObjFuncAttest<T, R> attestSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func, String name) {
		return new LSrtIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjLongFunctionAttest<T, R> attestObjLongFunc(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjLongFunctionAttest<T, R> attestObjLongFunc(LObjLongFunction<T, R> func, String name) {
		return new LObjLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LLongObjFuncAttest<T, R> attestLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func) {
		return new LLongObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LLongObjFuncAttest<T, R> attestLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func, String name) {
		return new LLongObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LObjSrtFunctionAttest<T, R> attestObjSrtFunc(LObjSrtFunction<T, R> func) {
		return new LObjSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjSrtFunctionAttest<T, R> attestObjSrtFunc(LObjSrtFunction<T, R> func, String name) {
		return new LObjSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LSrtObjFuncAttest<T, R> attestSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func) {
		return new LSrtObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LSrtObjFuncAttest<T, R> attestSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func, String name) {
		return new LSrtObjFuncAttest(func, name);
	}

	@Nonnull
	public static <T, R> LOiFunctionAttest<T, R> attestOiFunc(LOiFunction<T, R> func) {
		return new LOiFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LOiFunctionAttest<T, R> attestOiFunc(LOiFunction<T, R> func, String name) {
		return new LOiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> LIntObjFuncAttest<T, R> attestIntObjFunc(LOiFunction.LIntObjFunc<T, R> func) {
		return new LIntObjFuncAttest(func);
	}

	@Nonnull
	public static <T, R> LIntObjFuncAttest<T, R> attestIntObjFunc(LOiFunction.LIntObjFunc<T, R> func, String name) {
		return new LIntObjFuncAttest(func, name);
	}

	@Nonnull
	public static <R> LSrtFunctionAttest<R> attestSrtFunc(LSrtFunction<R> func) {
		return new LSrtFunctionAttest(func);
	}

	@Nonnull
	public static <R> LSrtFunctionAttest<R> attestSrtFunc(LSrtFunction<R> func, String name) {
		return new LSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriBoolFunctionAttest<R> attestTriBoolFunc(LTriBoolFunction<R> func) {
		return new LTriBoolFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriBoolFunctionAttest<R> attestTriBoolFunc(LTriBoolFunction<R> func, String name) {
		return new LTriBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriByteFunctionAttest<R> attestTriByteFunc(LTriByteFunction<R> func) {
		return new LTriByteFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriByteFunctionAttest<R> attestTriByteFunc(LTriByteFunction<R> func, String name) {
		return new LTriByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriCharFunctionAttest<R> attestTriCharFunc(LTriCharFunction<R> func) {
		return new LTriCharFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriCharFunctionAttest<R> attestTriCharFunc(LTriCharFunction<R> func, String name) {
		return new LTriCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriDblFunctionAttest<R> attestTriDblFunc(LTriDblFunction<R> func) {
		return new LTriDblFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriDblFunctionAttest<R> attestTriDblFunc(LTriDblFunction<R> func, String name) {
		return new LTriDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriFltFunctionAttest<R> attestTriFltFunc(LTriFltFunction<R> func) {
		return new LTriFltFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriFltFunctionAttest<R> attestTriFltFunc(LTriFltFunction<R> func, String name) {
		return new LTriFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriIntFunctionAttest<R> attestTriIntFunc(LTriIntFunction<R> func) {
		return new LTriIntFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriIntFunctionAttest<R> attestTriIntFunc(LTriIntFunction<R> func, String name) {
		return new LTriIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriLongFunctionAttest<R> attestTriLongFunc(LTriLongFunction<R> func) {
		return new LTriLongFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriLongFunctionAttest<R> attestTriLongFunc(LTriLongFunction<R> func, String name) {
		return new LTriLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> LTriSrtFunctionAttest<R> attestTriSrtFunc(LTriSrtFunction<R> func) {
		return new LTriSrtFunctionAttest(func);
	}

	@Nonnull
	public static <R> LTriSrtFunctionAttest<R> attestTriSrtFunc(LTriSrtFunction<R> func, String name) {
		return new LTriSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToByteFunctionAttest<T> attestOiToByteFunc(LOiToByteFunction<T> func) {
		return new LOiToByteFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToByteFunctionAttest<T> attestOiToByteFunc(LOiToByteFunction<T> func, String name) {
		return new LOiToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToByteFuncAttest<T> attestIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func) {
		return new LIntObjToByteFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToByteFuncAttest<T> attestIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func, String name) {
		return new LIntObjToByteFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToCharFunctionAttest<T> attestOiToCharFunc(LOiToCharFunction<T> func) {
		return new LOiToCharFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToCharFunctionAttest<T> attestOiToCharFunc(LOiToCharFunction<T> func, String name) {
		return new LOiToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToCharFuncAttest<T> attestIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func) {
		return new LIntObjToCharFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToCharFuncAttest<T> attestIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func, String name) {
		return new LIntObjToCharFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToDblFunctionAttest<T> attestOiToDblFunc(LOiToDblFunction<T> func) {
		return new LOiToDblFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToDblFunctionAttest<T> attestOiToDblFunc(LOiToDblFunction<T> func, String name) {
		return new LOiToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToDblFuncAttest<T> attestIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func) {
		return new LIntObjToDblFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToDblFuncAttest<T> attestIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func, String name) {
		return new LIntObjToDblFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToFltFunctionAttest<T> attestOiToFltFunc(LOiToFltFunction<T> func) {
		return new LOiToFltFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToFltFunctionAttest<T> attestOiToFltFunc(LOiToFltFunction<T> func, String name) {
		return new LOiToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToFltFuncAttest<T> attestIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func) {
		return new LIntObjToFltFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToFltFuncAttest<T> attestIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func, String name) {
		return new LIntObjToFltFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToIntFunctionAttest<T> attestOiToIntFunc(LOiToIntFunction<T> func) {
		return new LOiToIntFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToIntFunctionAttest<T> attestOiToIntFunc(LOiToIntFunction<T> func, String name) {
		return new LOiToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToIntFuncAttest<T> attestIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func) {
		return new LIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToIntFuncAttest<T> attestIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func, String name) {
		return new LIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToLongFunctionAttest<T> attestOiToLongFunc(LOiToLongFunction<T> func) {
		return new LOiToLongFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToLongFunctionAttest<T> attestOiToLongFunc(LOiToLongFunction<T> func, String name) {
		return new LOiToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToLongFuncAttest<T> attestIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		return new LIntObjToLongFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToLongFuncAttest<T> attestIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func, String name) {
		return new LIntObjToLongFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LOiToSrtFunctionAttest<T> attestOiToSrtFunc(LOiToSrtFunction<T> func) {
		return new LOiToSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToSrtFunctionAttest<T> attestOiToSrtFunc(LOiToSrtFunction<T> func, String name) {
		return new LOiToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjToSrtFuncAttest<T> attestIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func) {
		return new LIntObjToSrtFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjToSrtFuncAttest<T> attestIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func, String name) {
		return new LIntObjToSrtFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieBoolFunctionAttest<T> attestTieBoolFunc(LTieBoolFunction<T> func) {
		return new LTieBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieBoolFunctionAttest<T> attestTieBoolFunc(LTieBoolFunction<T> func, String name) {
		return new LTieBoolFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBoolIntToIntFuncAttest<T> attestObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func) {
		return new LObjBoolIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjBoolIntToIntFuncAttest<T> attestObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func, String name) {
		return new LObjBoolIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjBoolToIntFuncAttest<T> attestIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func) {
		return new LIntObjBoolToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjBoolToIntFuncAttest<T> attestIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func, String name) {
		return new LIntObjBoolToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntBoolObjToIntFuncAttest<T> attestIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func) {
		return new LIntBoolObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntBoolObjToIntFuncAttest<T> attestIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func, String name) {
		return new LIntBoolObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolObjIntToIntFuncAttest<T> attestBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func) {
		return new LBoolObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LBoolObjIntToIntFuncAttest<T> attestBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func, String name) {
		return new LBoolObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolIntObjToIntFuncAttest<T> attestBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func) {
		return new LBoolIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LBoolIntObjToIntFuncAttest<T> attestBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func, String name) {
		return new LBoolIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieByteFunctionAttest<T> attestTieByteFunc(LTieByteFunction<T> func) {
		return new LTieByteFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieByteFunctionAttest<T> attestTieByteFunc(LTieByteFunction<T> func, String name) {
		return new LTieByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjByteIntToIntFuncAttest<T> attestObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		return new LObjByteIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjByteIntToIntFuncAttest<T> attestObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func, String name) {
		return new LObjByteIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjByteToIntFuncAttest<T> attestIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func) {
		return new LIntObjByteToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjByteToIntFuncAttest<T> attestIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func, String name) {
		return new LIntObjByteToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntByteObjToIntFuncAttest<T> attestIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func) {
		return new LIntByteObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntByteObjToIntFuncAttest<T> attestIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func, String name) {
		return new LIntByteObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LByteObjIntToIntFuncAttest<T> attestByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func) {
		return new LByteObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LByteObjIntToIntFuncAttest<T> attestByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func, String name) {
		return new LByteObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LByteIntObjToIntFuncAttest<T> attestByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func) {
		return new LByteIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LByteIntObjToIntFuncAttest<T> attestByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func, String name) {
		return new LByteIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieCharFunctionAttest<T> attestTieCharFunc(LTieCharFunction<T> func) {
		return new LTieCharFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieCharFunctionAttest<T> attestTieCharFunc(LTieCharFunction<T> func, String name) {
		return new LTieCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjCharIntToIntFuncAttest<T> attestObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func) {
		return new LObjCharIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjCharIntToIntFuncAttest<T> attestObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func, String name) {
		return new LObjCharIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjCharToIntFuncAttest<T> attestIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func) {
		return new LIntObjCharToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjCharToIntFuncAttest<T> attestIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func, String name) {
		return new LIntObjCharToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntCharObjToIntFuncAttest<T> attestIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func) {
		return new LIntCharObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntCharObjToIntFuncAttest<T> attestIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func, String name) {
		return new LIntCharObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LCharObjIntToIntFuncAttest<T> attestCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func) {
		return new LCharObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LCharObjIntToIntFuncAttest<T> attestCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func, String name) {
		return new LCharObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LCharIntObjToIntFuncAttest<T> attestCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func) {
		return new LCharIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LCharIntObjToIntFuncAttest<T> attestCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func, String name) {
		return new LCharIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieDblFunctionAttest<T> attestTieDblFunc(LTieDblFunction<T> func) {
		return new LTieDblFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieDblFunctionAttest<T> attestTieDblFunc(LTieDblFunction<T> func, String name) {
		return new LTieDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjDblIntToIntFuncAttest<T> attestObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func) {
		return new LObjDblIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjDblIntToIntFuncAttest<T> attestObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func, String name) {
		return new LObjDblIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjDblToIntFuncAttest<T> attestIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func) {
		return new LIntObjDblToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjDblToIntFuncAttest<T> attestIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func, String name) {
		return new LIntObjDblToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntDblObjToIntFuncAttest<T> attestIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func) {
		return new LIntDblObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntDblObjToIntFuncAttest<T> attestIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func, String name) {
		return new LIntDblObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LDblObjIntToIntFuncAttest<T> attestDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func) {
		return new LDblObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LDblObjIntToIntFuncAttest<T> attestDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func, String name) {
		return new LDblObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LDblIntObjToIntFuncAttest<T> attestDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func) {
		return new LDblIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LDblIntObjToIntFuncAttest<T> attestDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func, String name) {
		return new LDblIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieFltFunctionAttest<T> attestTieFltFunc(LTieFltFunction<T> func) {
		return new LTieFltFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieFltFunctionAttest<T> attestTieFltFunc(LTieFltFunction<T> func, String name) {
		return new LTieFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjFltIntToIntFuncAttest<T> attestObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func) {
		return new LObjFltIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjFltIntToIntFuncAttest<T> attestObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func, String name) {
		return new LObjFltIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjFltToIntFuncAttest<T> attestIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func) {
		return new LIntObjFltToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjFltToIntFuncAttest<T> attestIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func, String name) {
		return new LIntObjFltToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntFltObjToIntFuncAttest<T> attestIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func) {
		return new LIntFltObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntFltObjToIntFuncAttest<T> attestIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func, String name) {
		return new LIntFltObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LFltObjIntToIntFuncAttest<T> attestFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func) {
		return new LFltObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LFltObjIntToIntFuncAttest<T> attestFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func, String name) {
		return new LFltObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LFltIntObjToIntFuncAttest<T> attestFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func) {
		return new LFltIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LFltIntObjToIntFuncAttest<T> attestFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func, String name) {
		return new LFltIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LTieFunctionAttest<T1, T2> attestTieFunc(LTieFunction<T1, T2> func) {
		return new LTieFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LTieFunctionAttest<T1, T2> attestTieFunc(LTieFunction<T1, T2> func, String name) {
		return new LTieFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Obj2Int1ToIntFuncAttest<T1, T2> attestObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func) {
		return new LObj0Obj2Int1ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Obj2Int1ToIntFuncAttest<T1, T2> attestObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func, String name) {
		return new LObj0Obj2Int1ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2ToIntFuncAttest<T1, T2> attestInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func) {
		return new LInt1BiObj2ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2ToIntFuncAttest<T1, T2> attestInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func, String name) {
		return new LInt1BiObj2ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0ToIntFuncAttest<T2, T1> attestInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func) {
		return new LInt1Obj2Obj0ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0ToIntFuncAttest<T2, T1> attestInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func, String name) {
		return new LInt1Obj2Obj0ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj2Obj0Int1ToIntFuncAttest<T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		return new LObj2Obj0Int1ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj2Obj0Int1ToIntFuncAttest<T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func, String name) {
		return new LObj2Obj0Int1ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0ToIntFuncAttest<T2, T1> attestObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func) {
		return new LObj2Int1Obj0ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0ToIntFuncAttest<T2, T1> attestObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func, String name) {
		return new LObj2Int1Obj0ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieIntFunctionAttest<T> attestTieIntFunc(LTieIntFunction<T> func) {
		return new LTieIntFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieIntFunctionAttest<T> attestTieIntFunc(LTieIntFunction<T> func, String name) {
		return new LTieIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObj0Int2Int1ToIntFuncAttest<T> attestObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func) {
		return new LObj0Int2Int1ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObj0Int2Int1ToIntFuncAttest<T> attestObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func, String name) {
		return new LObj0Int2Int1ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2ToIntFuncAttest<T> attestInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func) {
		return new LInt1Obj0Int2ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2ToIntFuncAttest<T> attestInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func, String name) {
		return new LInt1Obj0Int2ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0ToIntFuncAttest<T> attestInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func) {
		return new LInt1Int2Obj0ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0ToIntFuncAttest<T> attestInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func, String name) {
		return new LInt1Int2Obj0ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1ToIntFuncAttest<T> attestInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func) {
		return new LInt2Obj0Int1ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1ToIntFuncAttest<T> attestInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func, String name) {
		return new LInt2Obj0Int1ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LBiInt1Obj0ToIntFuncAttest<T> attestBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func) {
		return new LBiInt1Obj0ToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LBiInt1Obj0ToIntFuncAttest<T> attestBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func, String name) {
		return new LBiInt1Obj0ToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieLongFunctionAttest<T> attestTieLongFunc(LTieLongFunction<T> func) {
		return new LTieLongFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieLongFunctionAttest<T> attestTieLongFunc(LTieLongFunction<T> func, String name) {
		return new LTieLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjLongIntToIntFuncAttest<T> attestObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func) {
		return new LObjLongIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjLongIntToIntFuncAttest<T> attestObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func, String name) {
		return new LObjLongIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjLongToIntFuncAttest<T> attestIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func) {
		return new LIntObjLongToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjLongToIntFuncAttest<T> attestIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func, String name) {
		return new LIntObjLongToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntLongObjToIntFuncAttest<T> attestIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func) {
		return new LIntLongObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntLongObjToIntFuncAttest<T> attestIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func, String name) {
		return new LIntLongObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LLongObjIntToIntFuncAttest<T> attestLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func) {
		return new LLongObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LLongObjIntToIntFuncAttest<T> attestLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func, String name) {
		return new LLongObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LLongIntObjToIntFuncAttest<T> attestLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func) {
		return new LLongIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LLongIntObjToIntFuncAttest<T> attestLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func, String name) {
		return new LLongIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LTieSrtFunctionAttest<T> attestTieSrtFunc(LTieSrtFunction<T> func) {
		return new LTieSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieSrtFunctionAttest<T> attestTieSrtFunc(LTieSrtFunction<T> func, String name) {
		return new LTieSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> LObjSrtIntToIntFuncAttest<T> attestObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func) {
		return new LObjSrtIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtIntToIntFuncAttest<T> attestObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func, String name) {
		return new LObjSrtIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjSrtToIntFuncAttest<T> attestIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func) {
		return new LIntObjSrtToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntObjSrtToIntFuncAttest<T> attestIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func, String name) {
		return new LIntObjSrtToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LIntSrtObjToIntFuncAttest<T> attestIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func) {
		return new LIntSrtObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LIntSrtObjToIntFuncAttest<T> attestIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func, String name) {
		return new LIntSrtObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtObjIntToIntFuncAttest<T> attestSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func) {
		return new LSrtObjIntToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LSrtObjIntToIntFuncAttest<T> attestSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func, String name) {
		return new LSrtObjIntToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtIntObjToIntFuncAttest<T> attestSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func) {
		return new LSrtIntObjToIntFuncAttest(func);
	}

	@Nonnull
	public static <T> LSrtIntObjToIntFuncAttest<T> attestSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func, String name) {
		return new LSrtIntObjToIntFuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToByteBiFunctionAttest<T1, T2> attestToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		return new LToByteBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToByteBiFunctionAttest<T1, T2> attestToByteBiFunc(LToByteBiFunction<T1, T2> func, String name) {
		return new LToByteBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToByteObj1Obj0FuncAttest<T2, T1> attestToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func) {
		return new LToByteObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToByteObj1Obj0FuncAttest<T2, T1> attestToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func, String name) {
		return new LToByteObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToByteFunctionAttest<T> attestToByteFunc(LToByteFunction<T> func) {
		return new LToByteFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToByteFunctionAttest<T> attestToByteFunc(LToByteFunction<T> func, String name) {
		return new LToByteFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToCharBiFunctionAttest<T1, T2> attestToCharBiFunc(LToCharBiFunction<T1, T2> func) {
		return new LToCharBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToCharBiFunctionAttest<T1, T2> attestToCharBiFunc(LToCharBiFunction<T1, T2> func, String name) {
		return new LToCharBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToCharObj1Obj0FuncAttest<T2, T1> attestToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func) {
		return new LToCharObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToCharObj1Obj0FuncAttest<T2, T1> attestToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func, String name) {
		return new LToCharObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToCharFunctionAttest<T> attestToCharFunc(LToCharFunction<T> func) {
		return new LToCharFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToCharFunctionAttest<T> attestToCharFunc(LToCharFunction<T> func, String name) {
		return new LToCharFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToDblBiFunctionAttest<T1, T2> attestToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		return new LToDblBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToDblBiFunctionAttest<T1, T2> attestToDblBiFunc(LToDblBiFunction<T1, T2> func, String name) {
		return new LToDblBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToDblObj1Obj0FuncAttest<T2, T1> attestToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func) {
		return new LToDblObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToDblObj1Obj0FuncAttest<T2, T1> attestToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func, String name) {
		return new LToDblObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToDblFunctionAttest<T> attestToDblFunc(LToDblFunction<T> func) {
		return new LToDblFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToDblFunctionAttest<T> attestToDblFunc(LToDblFunction<T> func, String name) {
		return new LToDblFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToFltBiFunctionAttest<T1, T2> attestToFltBiFunc(LToFltBiFunction<T1, T2> func) {
		return new LToFltBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToFltBiFunctionAttest<T1, T2> attestToFltBiFunc(LToFltBiFunction<T1, T2> func, String name) {
		return new LToFltBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToFltObj1Obj0FuncAttest<T2, T1> attestToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func) {
		return new LToFltObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToFltObj1Obj0FuncAttest<T2, T1> attestToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func, String name) {
		return new LToFltObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToFltFunctionAttest<T> attestToFltFunc(LToFltFunction<T> func) {
		return new LToFltFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToFltFunctionAttest<T> attestToFltFunc(LToFltFunction<T> func, String name) {
		return new LToFltFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToIntBiFunctionAttest<T1, T2> attestToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		return new LToIntBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToIntBiFunctionAttest<T1, T2> attestToIntBiFunc(LToIntBiFunction<T1, T2> func, String name) {
		return new LToIntBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToIntObj1Obj0FuncAttest<T2, T1> attestToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func) {
		return new LToIntObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToIntObj1Obj0FuncAttest<T2, T1> attestToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func, String name) {
		return new LToIntObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToIntFunctionAttest<T> attestToIntFunc(LToIntFunction<T> func) {
		return new LToIntFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToIntFunctionAttest<T> attestToIntFunc(LToIntFunction<T> func, String name) {
		return new LToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3> LToIntTriFunctionAttest<T1, T2, T3> attestToIntTriFunc(LToIntTriFunction<T1, T2, T3> func) {
		return new LToIntTriFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3> LToIntTriFunctionAttest<T1, T2, T3> attestToIntTriFunc(LToIntTriFunction<T1, T2, T3> func, String name) {
		return new LToIntTriFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T3, T2> LToIntObj0Obj2Obj1FuncAttest<T1, T3, T2> attestToIntObj0Obj2Obj1Func(LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func) {
		return new LToIntObj0Obj2Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T1, T3, T2> LToIntObj0Obj2Obj1FuncAttest<T1, T3, T2> attestToIntObj0Obj2Obj1Func(LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func, String name) {
		return new LToIntObj0Obj2Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, T3> LToIntObj1BiObj2FuncAttest<T2, T1, T3> attestToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		return new LToIntObj1BiObj2FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1, T3> LToIntObj1BiObj2FuncAttest<T2, T1, T3> attestToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func, String name) {
		return new LToIntObj1BiObj2FuncAttest(func, name);
	}

	@Nonnull
	public static <T2, T3, T1> LToIntObj1Obj2Obj0FuncAttest<T2, T3, T1> attestToIntObj1Obj2Obj0Func(LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func) {
		return new LToIntObj1Obj2Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T3, T1> LToIntObj1Obj2Obj0FuncAttest<T2, T3, T1> attestToIntObj1Obj2Obj0Func(LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func, String name) {
		return new LToIntObj1Obj2Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T3, T1, T2> LToIntObj2Obj0Obj1FuncAttest<T3, T1, T2> attestToIntObj2Obj0Obj1Func(LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func) {
		return new LToIntObj2Obj0Obj1FuncAttest(func);
	}

	@Nonnull
	public static <T3, T1, T2> LToIntObj2Obj0Obj1FuncAttest<T3, T1, T2> attestToIntObj2Obj0Obj1Func(LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func, String name) {
		return new LToIntObj2Obj0Obj1FuncAttest(func, name);
	}

	@Nonnull
	public static <T3, T2, T1> LToIntBiObj1Obj0FuncAttest<T3, T2, T1> attestToIntBiObj1Obj0Func(LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func) {
		return new LToIntBiObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T3, T2, T1> LToIntBiObj1Obj0FuncAttest<T3, T2, T1> attestToIntBiObj1Obj0Func(LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func, String name) {
		return new LToIntBiObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func, String name) {
		return new LToLongBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToLongObj1Obj0FuncAttest<T2, T1> attestToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func) {
		return new LToLongObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToLongObj1Obj0FuncAttest<T2, T1> attestToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func, String name) {
		return new LToLongObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToLongFunctionAttest<T> attestToLongFunc(LToLongFunction<T> func) {
		return new LToLongFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToLongFunctionAttest<T> attestToLongFunc(LToLongFunction<T> func, String name) {
		return new LToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LToSrtBiFunctionAttest<T1, T2> attestToSrtBiFunc(LToSrtBiFunction<T1, T2> func) {
		return new LToSrtBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToSrtBiFunctionAttest<T1, T2> attestToSrtBiFunc(LToSrtBiFunction<T1, T2> func, String name) {
		return new LToSrtBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LToSrtObj1Obj0FuncAttest<T2, T1> attestToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func) {
		return new LToSrtObj1Obj0FuncAttest(func);
	}

	@Nonnull
	public static <T2, T1> LToSrtObj1Obj0FuncAttest<T2, T1> attestToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func, String name) {
		return new LToSrtObj1Obj0FuncAttest(func, name);
	}

	@Nonnull
	public static <T> LToSrtFunctionAttest<T> attestToSrtFunc(LToSrtFunction<T> func) {
		return new LToSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T> LToSrtFunctionAttest<T> attestToSrtFunc(LToSrtFunction<T> func, String name) {
		return new LToSrtFunctionAttest(func, name);
	}

	@Nonnull
	public static LBiBytePredicateAttest attestBiBytePred(LBiBytePredicate func) {
		return new LBiBytePredicateAttest(func);
	}

	@Nonnull
	public static LBiBytePredicateAttest attestBiBytePred(LBiBytePredicate func, String name) {
		return new LBiBytePredicateAttest(func, name);
	}

	@Nonnull
	public static LByte1Byte0PredAttest attestByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func) {
		return new LByte1Byte0PredAttest(func);
	}

	@Nonnull
	public static LByte1Byte0PredAttest attestByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func, String name) {
		return new LByte1Byte0PredAttest(func, name);
	}

	@Nonnull
	public static LBiCharPredicateAttest attestBiCharPred(LBiCharPredicate func) {
		return new LBiCharPredicateAttest(func);
	}

	@Nonnull
	public static LBiCharPredicateAttest attestBiCharPred(LBiCharPredicate func, String name) {
		return new LBiCharPredicateAttest(func, name);
	}

	@Nonnull
	public static LChar1Char0PredAttest attestChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func) {
		return new LChar1Char0PredAttest(func);
	}

	@Nonnull
	public static LChar1Char0PredAttest attestChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func, String name) {
		return new LChar1Char0PredAttest(func, name);
	}

	@Nonnull
	public static LBiDblPredicateAttest attestBiDblPred(LBiDblPredicate func) {
		return new LBiDblPredicateAttest(func);
	}

	@Nonnull
	public static LBiDblPredicateAttest attestBiDblPred(LBiDblPredicate func, String name) {
		return new LBiDblPredicateAttest(func, name);
	}

	@Nonnull
	public static LDbl1Dbl0PredAttest attestDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func) {
		return new LDbl1Dbl0PredAttest(func);
	}

	@Nonnull
	public static LDbl1Dbl0PredAttest attestDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func, String name) {
		return new LDbl1Dbl0PredAttest(func, name);
	}

	@Nonnull
	public static LBiFltPredicateAttest attestBiFltPred(LBiFltPredicate func) {
		return new LBiFltPredicateAttest(func);
	}

	@Nonnull
	public static LBiFltPredicateAttest attestBiFltPred(LBiFltPredicate func, String name) {
		return new LBiFltPredicateAttest(func, name);
	}

	@Nonnull
	public static LFlt1Flt0PredAttest attestFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func) {
		return new LFlt1Flt0PredAttest(func);
	}

	@Nonnull
	public static LFlt1Flt0PredAttest attestFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func, String name) {
		return new LFlt1Flt0PredAttest(func, name);
	}

	@Nonnull
	public static LBiIntPredicateAttest attestBiIntPred(LBiIntPredicate func) {
		return new LBiIntPredicateAttest(func);
	}

	@Nonnull
	public static LBiIntPredicateAttest attestBiIntPred(LBiIntPredicate func, String name) {
		return new LBiIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LInt1Int0PredAttest attestInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func) {
		return new LInt1Int0PredAttest(func);
	}

	@Nonnull
	public static LInt1Int0PredAttest attestInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func, String name) {
		return new LInt1Int0PredAttest(func, name);
	}

	@Nonnull
	public static LBiLongPredicateAttest attestBiLongPred(LBiLongPredicate func) {
		return new LBiLongPredicateAttest(func);
	}

	@Nonnull
	public static LBiLongPredicateAttest attestBiLongPred(LBiLongPredicate func, String name) {
		return new LBiLongPredicateAttest(func, name);
	}

	@Nonnull
	public static LLong1Long0PredAttest attestLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func) {
		return new LLong1Long0PredAttest(func);
	}

	@Nonnull
	public static LLong1Long0PredAttest attestLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func, String name) {
		return new LLong1Long0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjBoolPredicateAttest<T1, T2> attestBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		return new LBiObjBoolPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjBoolPredicateAttest<T1, T2> attestBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func, String name) {
		return new LBiObjBoolPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Bool2Obj1PredAttest<T1, T2> attestObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func) {
		return new LObj0Bool2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Bool2Obj1PredAttest<T1, T2> attestObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Bool2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Bool2PredAttest<T2, T1> attestObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func) {
		return new LObj1Obj0Bool2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Bool2PredAttest<T2, T1> attestObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Bool2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Bool2Obj0PredAttest<T2, T1> attestObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func) {
		return new LObj1Bool2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Bool2Obj0PredAttest<T2, T1> attestObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Bool2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBool2Obj0Obj1PredAttest<T1, T2> attestBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func) {
		return new LBool2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBool2Obj0Obj1PredAttest<T1, T2> attestBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LBool2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LBool2Obj1Obj0PredAttest<T2, T1> attestBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func) {
		return new LBool2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LBool2Obj1Obj0PredAttest<T2, T1> attestBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LBool2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjBytePredicateAttest<T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjBytePredicateAttest<T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func, String name) {
		return new LBiObjBytePredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Byte2Obj1PredAttest<T1, T2> attestObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func) {
		return new LObj0Byte2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Byte2Obj1PredAttest<T1, T2> attestObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Byte2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Byte2PredAttest<T2, T1> attestObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func) {
		return new LObj1Obj0Byte2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Byte2PredAttest<T2, T1> attestObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Byte2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Byte2Obj0PredAttest<T2, T1> attestObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func) {
		return new LObj1Byte2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Byte2Obj0PredAttest<T2, T1> attestObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Byte2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LByte2Obj0Obj1PredAttest<T1, T2> attestByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func) {
		return new LByte2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LByte2Obj0Obj1PredAttest<T1, T2> attestByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LByte2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LByte2Obj1Obj0PredAttest<T2, T1> attestByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		return new LByte2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LByte2Obj1Obj0PredAttest<T2, T1> attestByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LByte2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjCharPredicateAttest<T1, T2> attestBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		return new LBiObjCharPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjCharPredicateAttest<T1, T2> attestBiObjCharPred(LBiObjCharPredicate<T1, T2> func, String name) {
		return new LBiObjCharPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Char2Obj1PredAttest<T1, T2> attestObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func) {
		return new LObj0Char2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Char2Obj1PredAttest<T1, T2> attestObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Char2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Char2PredAttest<T2, T1> attestObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func) {
		return new LObj1Obj0Char2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Char2PredAttest<T2, T1> attestObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Char2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Char2Obj0PredAttest<T2, T1> attestObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func) {
		return new LObj1Char2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Char2Obj0PredAttest<T2, T1> attestObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Char2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LChar2Obj0Obj1PredAttest<T1, T2> attestChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func) {
		return new LChar2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LChar2Obj0Obj1PredAttest<T1, T2> attestChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LChar2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LChar2Obj1Obj0PredAttest<T2, T1> attestChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func) {
		return new LChar2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LChar2Obj1Obj0PredAttest<T2, T1> attestChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LChar2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjDblPredicateAttest<T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		return new LBiObjDblPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjDblPredicateAttest<T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func, String name) {
		return new LBiObjDblPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Dbl2Obj1PredAttest<T1, T2> attestObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func) {
		return new LObj0Dbl2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Dbl2Obj1PredAttest<T1, T2> attestObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Dbl2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Dbl2PredAttest<T2, T1> attestObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func) {
		return new LObj1Obj0Dbl2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Dbl2PredAttest<T2, T1> attestObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Dbl2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Dbl2Obj0PredAttest<T2, T1> attestObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func) {
		return new LObj1Dbl2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Dbl2Obj0PredAttest<T2, T1> attestObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Dbl2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LDbl2Obj0Obj1PredAttest<T1, T2> attestDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func) {
		return new LDbl2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LDbl2Obj0Obj1PredAttest<T1, T2> attestDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LDbl2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LDbl2Obj1Obj0PredAttest<T2, T1> attestDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func) {
		return new LDbl2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LDbl2Obj1Obj0PredAttest<T2, T1> attestDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LDbl2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjFltPredicateAttest<T1, T2> attestBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		return new LBiObjFltPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjFltPredicateAttest<T1, T2> attestBiObjFltPred(LBiObjFltPredicate<T1, T2> func, String name) {
		return new LBiObjFltPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Flt2Obj1PredAttest<T1, T2> attestObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func) {
		return new LObj0Flt2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Flt2Obj1PredAttest<T1, T2> attestObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Flt2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Flt2PredAttest<T2, T1> attestObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func) {
		return new LObj1Obj0Flt2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Flt2PredAttest<T2, T1> attestObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Flt2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Flt2Obj0PredAttest<T2, T1> attestObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func) {
		return new LObj1Flt2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Flt2Obj0PredAttest<T2, T1> attestObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Flt2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LFlt2Obj0Obj1PredAttest<T1, T2> attestFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func) {
		return new LFlt2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LFlt2Obj0Obj1PredAttest<T1, T2> attestFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LFlt2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LFlt2Obj1Obj0PredAttest<T2, T1> attestFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func) {
		return new LFlt2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LFlt2Obj1Obj0PredAttest<T2, T1> attestFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LFlt2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjIntPredicateAttest<T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjIntPredicateAttest<T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func, String name) {
		return new LBiObjIntPredicateAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Int2PredAttest<T2, T1> attestObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func) {
		return new LObj1Obj0Int2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Int2PredAttest<T2, T1> attestObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Int2PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LInt2Obj0Obj1PredAttest<T1, T2> attestInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func) {
		return new LInt2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LInt2Obj0Obj1PredAttest<T1, T2> attestInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LInt2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LInt2Obj1Obj0PredAttest<T2, T1> attestInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func) {
		return new LInt2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LInt2Obj1Obj0PredAttest<T2, T1> attestInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LInt2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjLongPredicateAttest<T1, T2> attestBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		return new LBiObjLongPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjLongPredicateAttest<T1, T2> attestBiObjLongPred(LBiObjLongPredicate<T1, T2> func, String name) {
		return new LBiObjLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Long2Obj1PredAttest<T1, T2> attestObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func) {
		return new LObj0Long2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Long2Obj1PredAttest<T1, T2> attestObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Long2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Long2PredAttest<T2, T1> attestObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func) {
		return new LObj1Obj0Long2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Long2PredAttest<T2, T1> attestObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Long2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Long2Obj0PredAttest<T2, T1> attestObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func) {
		return new LObj1Long2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Long2Obj0PredAttest<T2, T1> attestObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Long2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LLong2Obj0Obj1PredAttest<T1, T2> attestLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func) {
		return new LLong2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LLong2Obj0Obj1PredAttest<T1, T2> attestLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LLong2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LLong2Obj1Obj0PredAttest<T2, T1> attestLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func) {
		return new LLong2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LLong2Obj1Obj0PredAttest<T2, T1> attestLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LLong2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiObjSrtPredicateAttest<T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		return new LBiObjSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjSrtPredicateAttest<T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func, String name) {
		return new LBiObjSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObj0Srt2Obj1PredAttest<T1, T2> attestObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func) {
		return new LObj0Srt2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObj0Srt2Obj1PredAttest<T1, T2> attestObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func, String name) {
		return new LObj0Srt2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Srt2PredAttest<T2, T1> attestObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func) {
		return new LObj1Obj0Srt2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0Srt2PredAttest<T2, T1> attestObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func, String name) {
		return new LObj1Obj0Srt2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Srt2Obj0PredAttest<T2, T1> attestObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func) {
		return new LObj1Srt2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Srt2Obj0PredAttest<T2, T1> attestObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func, String name) {
		return new LObj1Srt2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LSrt2Obj0Obj1PredAttest<T1, T2> attestSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func) {
		return new LSrt2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LSrt2Obj0Obj1PredAttest<T1, T2> attestSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func, String name) {
		return new LSrt2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LSrt2Obj1Obj0PredAttest<T2, T1> attestSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func) {
		return new LSrt2Obj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LSrt2Obj1Obj0PredAttest<T2, T1> attestSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func, String name) {
		return new LSrt2Obj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LBiPredicateAttest<T1, T2> attestBiPred(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiPredicateAttest<T1, T2> attestBiPred(LBiPredicate<T1, T2> func, String name) {
		return new LBiPredicateAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0PredAttest<T2, T1> attestObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func) {
		return new LObj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj1Obj0PredAttest<T2, T1> attestObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func, String name) {
		return new LObj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static LBiSrtPredicateAttest attestBiSrtPred(LBiSrtPredicate func) {
		return new LBiSrtPredicateAttest(func);
	}

	@Nonnull
	public static LBiSrtPredicateAttest attestBiSrtPred(LBiSrtPredicate func, String name) {
		return new LBiSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static LSrt1Srt0PredAttest attestSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func) {
		return new LSrt1Srt0PredAttest(func);
	}

	@Nonnull
	public static LSrt1Srt0PredAttest attestSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func, String name) {
		return new LSrt1Srt0PredAttest(func, name);
	}

	@Nonnull
	public static LBoolIntPredicateAttest attestBoolIntPred(LBoolIntPredicate func) {
		return new LBoolIntPredicateAttest(func);
	}

	@Nonnull
	public static LBoolIntPredicateAttest attestBoolIntPred(LBoolIntPredicate func, String name) {
		return new LBoolIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntBoolPredAttest attestIntBoolPred(LBoolIntPredicate.LIntBoolPred func) {
		return new LIntBoolPredAttest(func);
	}

	@Nonnull
	public static LIntBoolPredAttest attestIntBoolPred(LBoolIntPredicate.LIntBoolPred func, String name) {
		return new LIntBoolPredAttest(func, name);
	}

	@Nonnull
	public static LByteIntPredicateAttest attestByteIntPred(LByteIntPredicate func) {
		return new LByteIntPredicateAttest(func);
	}

	@Nonnull
	public static LByteIntPredicateAttest attestByteIntPred(LByteIntPredicate func, String name) {
		return new LByteIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntBytePredAttest attestIntBytePred(LByteIntPredicate.LIntBytePred func) {
		return new LIntBytePredAttest(func);
	}

	@Nonnull
	public static LIntBytePredAttest attestIntBytePred(LByteIntPredicate.LIntBytePred func, String name) {
		return new LIntBytePredAttest(func, name);
	}

	@Nonnull
	public static LBytePredicateAttest attestBytePred(LBytePredicate func) {
		return new LBytePredicateAttest(func);
	}

	@Nonnull
	public static LBytePredicateAttest attestBytePred(LBytePredicate func, String name) {
		return new LBytePredicateAttest(func, name);
	}

	@Nonnull
	public static LCharIntPredicateAttest attestCharIntPred(LCharIntPredicate func) {
		return new LCharIntPredicateAttest(func);
	}

	@Nonnull
	public static LCharIntPredicateAttest attestCharIntPred(LCharIntPredicate func, String name) {
		return new LCharIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntCharPredAttest attestIntCharPred(LCharIntPredicate.LIntCharPred func) {
		return new LIntCharPredAttest(func);
	}

	@Nonnull
	public static LIntCharPredAttest attestIntCharPred(LCharIntPredicate.LIntCharPred func, String name) {
		return new LIntCharPredAttest(func, name);
	}

	@Nonnull
	public static LCharPredicateAttest attestCharPred(LCharPredicate func) {
		return new LCharPredicateAttest(func);
	}

	@Nonnull
	public static LCharPredicateAttest attestCharPred(LCharPredicate func, String name) {
		return new LCharPredicateAttest(func, name);
	}

	@Nonnull
	public static LDblIntPredicateAttest attestDblIntPred(LDblIntPredicate func) {
		return new LDblIntPredicateAttest(func);
	}

	@Nonnull
	public static LDblIntPredicateAttest attestDblIntPred(LDblIntPredicate func, String name) {
		return new LDblIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntDblPredAttest attestIntDblPred(LDblIntPredicate.LIntDblPred func) {
		return new LIntDblPredAttest(func);
	}

	@Nonnull
	public static LIntDblPredAttest attestIntDblPred(LDblIntPredicate.LIntDblPred func, String name) {
		return new LIntDblPredAttest(func, name);
	}

	@Nonnull
	public static LDblPredicateAttest attestDblPred(LDblPredicate func) {
		return new LDblPredicateAttest(func);
	}

	@Nonnull
	public static LDblPredicateAttest attestDblPred(LDblPredicate func, String name) {
		return new LDblPredicateAttest(func, name);
	}

	@Nonnull
	public static LFltIntPredicateAttest attestFltIntPred(LFltIntPredicate func) {
		return new LFltIntPredicateAttest(func);
	}

	@Nonnull
	public static LFltIntPredicateAttest attestFltIntPred(LFltIntPredicate func, String name) {
		return new LFltIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntFltPredAttest attestIntFltPred(LFltIntPredicate.LIntFltPred func) {
		return new LIntFltPredAttest(func);
	}

	@Nonnull
	public static LIntFltPredAttest attestIntFltPred(LFltIntPredicate.LIntFltPred func, String name) {
		return new LIntFltPredAttest(func, name);
	}

	@Nonnull
	public static LFltPredicateAttest attestFltPred(LFltPredicate func) {
		return new LFltPredicateAttest(func);
	}

	@Nonnull
	public static LFltPredicateAttest attestFltPred(LFltPredicate func, String name) {
		return new LFltPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntPredicateAttest attestIntPred(LIntPredicate func) {
		return new LIntPredicateAttest(func);
	}

	@Nonnull
	public static LIntPredicateAttest attestIntPred(LIntPredicate func, String name) {
		return new LIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LLongIntPredicateAttest attestLongIntPred(LLongIntPredicate func) {
		return new LLongIntPredicateAttest(func);
	}

	@Nonnull
	public static LLongIntPredicateAttest attestLongIntPred(LLongIntPredicate func, String name) {
		return new LLongIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntLongPredAttest attestIntLongPred(LLongIntPredicate.LIntLongPred func) {
		return new LIntLongPredAttest(func);
	}

	@Nonnull
	public static LIntLongPredAttest attestIntLongPred(LLongIntPredicate.LIntLongPred func, String name) {
		return new LIntLongPredAttest(func, name);
	}

	@Nonnull
	public static LLongPredicateAttest attestLongPred(LLongPredicate func) {
		return new LLongPredicateAttest(func);
	}

	@Nonnull
	public static LLongPredicateAttest attestLongPred(LLongPredicate func, String name) {
		return new LLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBiIntPredicateAttest<T> attestObjBiIntPred(LObjBiIntPredicate<T> func) {
		return new LObjBiIntPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBiIntPredicateAttest<T> attestObjBiIntPred(LObjBiIntPredicate<T> func, String name) {
		return new LObjBiIntPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObj0Int2Int1PredAttest<T> attestObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func) {
		return new LObj0Int2Int1PredAttest(func);
	}

	@Nonnull
	public static <T> LObj0Int2Int1PredAttest<T> attestObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func, String name) {
		return new LObj0Int2Int1PredAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2PredAttest<T> attestInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func) {
		return new LInt1Obj0Int2PredAttest(func);
	}

	@Nonnull
	public static <T> LInt1Obj0Int2PredAttest<T> attestInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func, String name) {
		return new LInt1Obj0Int2PredAttest(func, name);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0PredAttest<T> attestInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func) {
		return new LInt1Int2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T> LInt1Int2Obj0PredAttest<T> attestInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func, String name) {
		return new LInt1Int2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1PredAttest<T> attestInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func) {
		return new LInt2Obj0Int1PredAttest(func);
	}

	@Nonnull
	public static <T> LInt2Obj0Int1PredAttest<T> attestInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func, String name) {
		return new LInt2Obj0Int1PredAttest(func, name);
	}

	@Nonnull
	public static <T> LBiInt1Obj0PredAttest<T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		return new LBiInt1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T> LBiInt1Obj0PredAttest<T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func, String name) {
		return new LBiInt1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBiLongPredicateAttest<T> attestObjBiLongPred(LObjBiLongPredicate<T> func) {
		return new LObjBiLongPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBiLongPredicateAttest<T> attestObjBiLongPred(LObjBiLongPredicate<T> func, String name) {
		return new LObjBiLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObj0Long2Long1PredAttest<T> attestObj0Long2Long1Pred(LObjBiLongPredicate.LObj0Long2Long1Pred<T> func) {
		return new LObj0Long2Long1PredAttest(func);
	}

	@Nonnull
	public static <T> LObj0Long2Long1PredAttest<T> attestObj0Long2Long1Pred(LObjBiLongPredicate.LObj0Long2Long1Pred<T> func, String name) {
		return new LObj0Long2Long1PredAttest(func, name);
	}

	@Nonnull
	public static <T> LLong1Obj0Long2PredAttest<T> attestLong1Obj0Long2Pred(LObjBiLongPredicate.LLong1Obj0Long2Pred<T> func) {
		return new LLong1Obj0Long2PredAttest(func);
	}

	@Nonnull
	public static <T> LLong1Obj0Long2PredAttest<T> attestLong1Obj0Long2Pred(LObjBiLongPredicate.LLong1Obj0Long2Pred<T> func, String name) {
		return new LLong1Obj0Long2PredAttest(func, name);
	}

	@Nonnull
	public static <T> LLong1Long2Obj0PredAttest<T> attestLong1Long2Obj0Pred(LObjBiLongPredicate.LLong1Long2Obj0Pred<T> func) {
		return new LLong1Long2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T> LLong1Long2Obj0PredAttest<T> attestLong1Long2Obj0Pred(LObjBiLongPredicate.LLong1Long2Obj0Pred<T> func, String name) {
		return new LLong1Long2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T> LLong2Obj0Long1PredAttest<T> attestLong2Obj0Long1Pred(LObjBiLongPredicate.LLong2Obj0Long1Pred<T> func) {
		return new LLong2Obj0Long1PredAttest(func);
	}

	@Nonnull
	public static <T> LLong2Obj0Long1PredAttest<T> attestLong2Obj0Long1Pred(LObjBiLongPredicate.LLong2Obj0Long1Pred<T> func, String name) {
		return new LLong2Obj0Long1PredAttest(func, name);
	}

	@Nonnull
	public static <T> LBiLong1Obj0PredAttest<T> attestBiLong1Obj0Pred(LObjBiLongPredicate.LBiLong1Obj0Pred<T> func) {
		return new LBiLong1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T> LBiLong1Obj0PredAttest<T> attestBiLong1Obj0Pred(LObjBiLongPredicate.LBiLong1Obj0Pred<T> func, String name) {
		return new LBiLong1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBoolPredicateAttest<T> attestObjBoolPred(LObjBoolPredicate<T> func) {
		return new LObjBoolPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBoolPredicateAttest<T> attestObjBoolPred(LObjBoolPredicate<T> func, String name) {
		return new LObjBoolPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolObjPredAttest<T> attestBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func) {
		return new LBoolObjPredAttest(func);
	}

	@Nonnull
	public static <T> LBoolObjPredAttest<T> attestBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func, String name) {
		return new LBoolObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBytePredicateAttest<T> attestObjBytePred(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBytePredicateAttest<T> attestObjBytePred(LObjBytePredicate<T> func, String name) {
		return new LObjBytePredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LByteObjPredAttest<T> attestByteObjPred(LObjBytePredicate.LByteObjPred<T> func) {
		return new LByteObjPredAttest(func);
	}

	@Nonnull
	public static <T> LByteObjPredAttest<T> attestByteObjPred(LObjBytePredicate.LByteObjPred<T> func, String name) {
		return new LByteObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjCharPredicateAttest<T> attestObjCharPred(LObjCharPredicate<T> func) {
		return new LObjCharPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjCharPredicateAttest<T> attestObjCharPred(LObjCharPredicate<T> func, String name) {
		return new LObjCharPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LCharObjPredAttest<T> attestCharObjPred(LObjCharPredicate.LCharObjPred<T> func) {
		return new LCharObjPredAttest(func);
	}

	@Nonnull
	public static <T> LCharObjPredAttest<T> attestCharObjPred(LObjCharPredicate.LCharObjPred<T> func, String name) {
		return new LCharObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjDblPredicateAttest<T> attestObjDblPred(LObjDblPredicate<T> func) {
		return new LObjDblPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjDblPredicateAttest<T> attestObjDblPred(LObjDblPredicate<T> func, String name) {
		return new LObjDblPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LDblObjPredAttest<T> attestDblObjPred(LObjDblPredicate.LDblObjPred<T> func) {
		return new LDblObjPredAttest(func);
	}

	@Nonnull
	public static <T> LDblObjPredAttest<T> attestDblObjPred(LObjDblPredicate.LDblObjPred<T> func, String name) {
		return new LDblObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjFltPredicateAttest<T> attestObjFltPred(LObjFltPredicate<T> func) {
		return new LObjFltPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjFltPredicateAttest<T> attestObjFltPred(LObjFltPredicate<T> func, String name) {
		return new LObjFltPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LFltObjPredAttest<T> attestFltObjPred(LObjFltPredicate.LFltObjPred<T> func) {
		return new LFltObjPredAttest(func);
	}

	@Nonnull
	public static <T> LFltObjPredAttest<T> attestFltObjPred(LObjFltPredicate.LFltObjPred<T> func, String name) {
		return new LFltObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntBoolPredicateAttest<T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		return new LObjIntBoolPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntBoolPredicateAttest<T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func, String name) {
		return new LObjIntBoolPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjBoolIntPredAttest<T> attestObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func) {
		return new LObjBoolIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjBoolIntPredAttest<T> attestObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func, String name) {
		return new LObjBoolIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjBoolPredAttest<T> attestIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func) {
		return new LIntObjBoolPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjBoolPredAttest<T> attestIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func, String name) {
		return new LIntObjBoolPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntBoolObjPredAttest<T> attestIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func) {
		return new LIntBoolObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntBoolObjPredAttest<T> attestIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func, String name) {
		return new LIntBoolObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolObjIntPredAttest<T> attestBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func) {
		return new LBoolObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LBoolObjIntPredAttest<T> attestBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func, String name) {
		return new LBoolObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LBoolIntObjPredAttest<T> attestBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func) {
		return new LBoolIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LBoolIntObjPredAttest<T> attestBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func, String name) {
		return new LBoolIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntBytePredicateAttest<T> attestObjIntBytePred(LObjIntBytePredicate<T> func) {
		return new LObjIntBytePredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntBytePredicateAttest<T> attestObjIntBytePred(LObjIntBytePredicate<T> func, String name) {
		return new LObjIntBytePredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjByteIntPredAttest<T> attestObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func) {
		return new LObjByteIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjByteIntPredAttest<T> attestObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func, String name) {
		return new LObjByteIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjBytePredAttest<T> attestIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func) {
		return new LIntObjBytePredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjBytePredAttest<T> attestIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func, String name) {
		return new LIntObjBytePredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntByteObjPredAttest<T> attestIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func) {
		return new LIntByteObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntByteObjPredAttest<T> attestIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func, String name) {
		return new LIntByteObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LByteObjIntPredAttest<T> attestByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func) {
		return new LByteObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LByteObjIntPredAttest<T> attestByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func, String name) {
		return new LByteObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LByteIntObjPredAttest<T> attestByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func) {
		return new LByteIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LByteIntObjPredAttest<T> attestByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func, String name) {
		return new LByteIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntCharPredicateAttest<T> attestObjIntCharPred(LObjIntCharPredicate<T> func) {
		return new LObjIntCharPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntCharPredicateAttest<T> attestObjIntCharPred(LObjIntCharPredicate<T> func, String name) {
		return new LObjIntCharPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjCharIntPredAttest<T> attestObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func) {
		return new LObjCharIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjCharIntPredAttest<T> attestObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func, String name) {
		return new LObjCharIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjCharPredAttest<T> attestIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func) {
		return new LIntObjCharPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjCharPredAttest<T> attestIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func, String name) {
		return new LIntObjCharPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntCharObjPredAttest<T> attestIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func) {
		return new LIntCharObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntCharObjPredAttest<T> attestIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func, String name) {
		return new LIntCharObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LCharObjIntPredAttest<T> attestCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func) {
		return new LCharObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LCharObjIntPredAttest<T> attestCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func, String name) {
		return new LCharObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LCharIntObjPredAttest<T> attestCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func) {
		return new LCharIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LCharIntObjPredAttest<T> attestCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func, String name) {
		return new LCharIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntDblPredicateAttest<T> attestObjIntDblPred(LObjIntDblPredicate<T> func) {
		return new LObjIntDblPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntDblPredicateAttest<T> attestObjIntDblPred(LObjIntDblPredicate<T> func, String name) {
		return new LObjIntDblPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjDblIntPredAttest<T> attestObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func) {
		return new LObjDblIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjDblIntPredAttest<T> attestObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func, String name) {
		return new LObjDblIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjDblPredAttest<T> attestIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func) {
		return new LIntObjDblPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjDblPredAttest<T> attestIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func, String name) {
		return new LIntObjDblPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntDblObjPredAttest<T> attestIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func) {
		return new LIntDblObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntDblObjPredAttest<T> attestIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func, String name) {
		return new LIntDblObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LDblObjIntPredAttest<T> attestDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func) {
		return new LDblObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LDblObjIntPredAttest<T> attestDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func, String name) {
		return new LDblObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LDblIntObjPredAttest<T> attestDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func) {
		return new LDblIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LDblIntObjPredAttest<T> attestDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func, String name) {
		return new LDblIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntFltPredicateAttest<T> attestObjIntFltPred(LObjIntFltPredicate<T> func) {
		return new LObjIntFltPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntFltPredicateAttest<T> attestObjIntFltPred(LObjIntFltPredicate<T> func, String name) {
		return new LObjIntFltPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjFltIntPredAttest<T> attestObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func) {
		return new LObjFltIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjFltIntPredAttest<T> attestObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func, String name) {
		return new LObjFltIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjFltPredAttest<T> attestIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func) {
		return new LIntObjFltPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjFltPredAttest<T> attestIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func, String name) {
		return new LIntObjFltPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntFltObjPredAttest<T> attestIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func) {
		return new LIntFltObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntFltObjPredAttest<T> attestIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func, String name) {
		return new LIntFltObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LFltObjIntPredAttest<T> attestFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func) {
		return new LFltObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LFltObjIntPredAttest<T> attestFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func, String name) {
		return new LFltObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LFltIntObjPredAttest<T> attestFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func) {
		return new LFltIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LFltIntObjPredAttest<T> attestFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func, String name) {
		return new LFltIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntLongPredicateAttest<T> attestObjIntLongPred(LObjIntLongPredicate<T> func) {
		return new LObjIntLongPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntLongPredicateAttest<T> attestObjIntLongPred(LObjIntLongPredicate<T> func, String name) {
		return new LObjIntLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjLongIntPredAttest<T> attestObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func) {
		return new LObjLongIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjLongIntPredAttest<T> attestObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func, String name) {
		return new LObjLongIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjLongPredAttest<T> attestIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func) {
		return new LIntObjLongPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjLongPredAttest<T> attestIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func, String name) {
		return new LIntObjLongPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntLongObjPredAttest<T> attestIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func) {
		return new LIntLongObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntLongObjPredAttest<T> attestIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func, String name) {
		return new LIntLongObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LLongObjIntPredAttest<T> attestLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func) {
		return new LLongObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LLongObjIntPredAttest<T> attestLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func, String name) {
		return new LLongObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LLongIntObjPredAttest<T> attestLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func) {
		return new LLongIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LLongIntObjPredAttest<T> attestLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func, String name) {
		return new LLongIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LObjIntObjPredicateAttest<T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		return new LObjIntObjPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObjIntObjPredicateAttest<T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func, String name) {
		return new LObjIntObjPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2PredAttest<T1, T2> attestInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func) {
		return new LInt1BiObj2PredAttest(func);
	}

	@Nonnull
	public static <T1, T2> LInt1BiObj2PredAttest<T1, T2> attestInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func, String name) {
		return new LInt1BiObj2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0PredAttest<T2, T1> attestInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func) {
		return new LInt1Obj2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LInt1Obj2Obj0PredAttest<T2, T1> attestInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func, String name) {
		return new LInt1Obj2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0PredAttest<T2, T1> attestObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func) {
		return new LObj2Int1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T1> LObj2Int1Obj0PredAttest<T2, T1> attestObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func, String name) {
		return new LObj2Int1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntPredicateAttest<T> attestObjIntPred(LObjIntPredicate<T> func) {
		return new LObjIntPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntPredicateAttest<T> attestObjIntPred(LObjIntPredicate<T> func, String name) {
		return new LObjIntPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjPredAttest<T> attestIntObjPred(LObjIntPredicate.LIntObjPred<T> func) {
		return new LIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjPredAttest<T> attestIntObjPred(LObjIntPredicate.LIntObjPred<T> func, String name) {
		return new LIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjIntSrtPredicateAttest<T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		return new LObjIntSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntSrtPredicateAttest<T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func, String name) {
		return new LObjIntSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LObjSrtIntPredAttest<T> attestObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func) {
		return new LObjSrtIntPredAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtIntPredAttest<T> attestObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func, String name) {
		return new LObjSrtIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntObjSrtPredAttest<T> attestIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		return new LIntObjSrtPredAttest(func);
	}

	@Nonnull
	public static <T> LIntObjSrtPredAttest<T> attestIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func, String name) {
		return new LIntObjSrtPredAttest(func, name);
	}

	@Nonnull
	public static <T> LIntSrtObjPredAttest<T> attestIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func) {
		return new LIntSrtObjPredAttest(func);
	}

	@Nonnull
	public static <T> LIntSrtObjPredAttest<T> attestIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func, String name) {
		return new LIntSrtObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtObjIntPredAttest<T> attestSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func) {
		return new LSrtObjIntPredAttest(func);
	}

	@Nonnull
	public static <T> LSrtObjIntPredAttest<T> attestSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func, String name) {
		return new LSrtObjIntPredAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtIntObjPredAttest<T> attestSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func) {
		return new LSrtIntObjPredAttest(func);
	}

	@Nonnull
	public static <T> LSrtIntObjPredAttest<T> attestSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func, String name) {
		return new LSrtIntObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjLongPredicateAttest<T> attestObjLongPred(LObjLongPredicate<T> func) {
		return new LObjLongPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjLongPredicateAttest<T> attestObjLongPred(LObjLongPredicate<T> func, String name) {
		return new LObjLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LLongObjPredAttest<T> attestLongObjPred(LObjLongPredicate.LLongObjPred<T> func) {
		return new LLongObjPredAttest(func);
	}

	@Nonnull
	public static <T> LLongObjPredAttest<T> attestLongObjPred(LObjLongPredicate.LLongObjPred<T> func, String name) {
		return new LLongObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LObjSrtPredicateAttest<T> attestObjSrtPred(LObjSrtPredicate<T> func) {
		return new LObjSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtPredicateAttest<T> attestObjSrtPred(LObjSrtPredicate<T> func, String name) {
		return new LObjSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> LSrtObjPredAttest<T> attestSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func) {
		return new LSrtObjPredAttest(func);
	}

	@Nonnull
	public static <T> LSrtObjPredAttest<T> attestSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func, String name) {
		return new LSrtObjPredAttest(func, name);
	}

	@Nonnull
	public static <T> LPredicateAttest<T> attestPred(LPredicate<T> func) {
		return new LPredicateAttest(func);
	}

	@Nonnull
	public static <T> LPredicateAttest<T> attestPred(LPredicate<T> func, String name) {
		return new LPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4> LQuadPredicateAttest<T1, T2, T3, T4> attestQuadPred(LQuadPredicate<T1, T2, T3, T4> func) {
		return new LQuadPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4> LQuadPredicateAttest<T1, T2, T3, T4> attestQuadPred(LQuadPredicate<T1, T2, T3, T4> func, String name) {
		return new LQuadPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintPredicateAttest<T1, T2, T3, T4, T5> attestQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func) {
		return new LQuintPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintPredicateAttest<T1, T2, T3, T4, T5> attestQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func, String name) {
		return new LQuintPredicateAttest(func, name);
	}

	@Nonnull
	public static LSrtIntPredicateAttest attestSrtIntPred(LSrtIntPredicate func) {
		return new LSrtIntPredicateAttest(func);
	}

	@Nonnull
	public static LSrtIntPredicateAttest attestSrtIntPred(LSrtIntPredicate func, String name) {
		return new LSrtIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LIntSrtPredAttest attestIntSrtPred(LSrtIntPredicate.LIntSrtPred func) {
		return new LIntSrtPredAttest(func);
	}

	@Nonnull
	public static LIntSrtPredAttest attestIntSrtPred(LSrtIntPredicate.LIntSrtPred func, String name) {
		return new LIntSrtPredAttest(func, name);
	}

	@Nonnull
	public static LSrtPredicateAttest attestSrtPred(LSrtPredicate func) {
		return new LSrtPredicateAttest(func);
	}

	@Nonnull
	public static LSrtPredicateAttest attestSrtPred(LSrtPredicate func, String name) {
		return new LSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static LTriBytePredicateAttest attestTriBytePred(LTriBytePredicate func) {
		return new LTriBytePredicateAttest(func);
	}

	@Nonnull
	public static LTriBytePredicateAttest attestTriBytePred(LTriBytePredicate func, String name) {
		return new LTriBytePredicateAttest(func, name);
	}

	@Nonnull
	public static LTriCharPredicateAttest attestTriCharPred(LTriCharPredicate func) {
		return new LTriCharPredicateAttest(func);
	}

	@Nonnull
	public static LTriCharPredicateAttest attestTriCharPred(LTriCharPredicate func, String name) {
		return new LTriCharPredicateAttest(func, name);
	}

	@Nonnull
	public static LTriDblPredicateAttest attestTriDblPred(LTriDblPredicate func) {
		return new LTriDblPredicateAttest(func);
	}

	@Nonnull
	public static LTriDblPredicateAttest attestTriDblPred(LTriDblPredicate func, String name) {
		return new LTriDblPredicateAttest(func, name);
	}

	@Nonnull
	public static LTriFltPredicateAttest attestTriFltPred(LTriFltPredicate func) {
		return new LTriFltPredicateAttest(func);
	}

	@Nonnull
	public static LTriFltPredicateAttest attestTriFltPred(LTriFltPredicate func, String name) {
		return new LTriFltPredicateAttest(func, name);
	}

	@Nonnull
	public static LTriIntPredicateAttest attestTriIntPred(LTriIntPredicate func) {
		return new LTriIntPredicateAttest(func);
	}

	@Nonnull
	public static LTriIntPredicateAttest attestTriIntPred(LTriIntPredicate func, String name) {
		return new LTriIntPredicateAttest(func, name);
	}

	@Nonnull
	public static LTriLongPredicateAttest attestTriLongPred(LTriLongPredicate func) {
		return new LTriLongPredicateAttest(func);
	}

	@Nonnull
	public static LTriLongPredicateAttest attestTriLongPred(LTriLongPredicate func, String name) {
		return new LTriLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, T3> LTriPredicateAttest<T1, T2, T3> attestTriPred(LTriPredicate<T1, T2, T3> func) {
		return new LTriPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2, T3> LTriPredicateAttest<T1, T2, T3> attestTriPred(LTriPredicate<T1, T2, T3> func, String name) {
		return new LTriPredicateAttest(func, name);
	}

	@Nonnull
	public static <T1, T3, T2> LObj0Obj2Obj1PredAttest<T1, T3, T2> attestObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func) {
		return new LObj0Obj2Obj1PredAttest(func);
	}

	@Nonnull
	public static <T1, T3, T2> LObj0Obj2Obj1PredAttest<T1, T3, T2> attestObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func, String name) {
		return new LObj0Obj2Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T1, T3> LObj1BiObj2PredAttest<T2, T1, T3> attestObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func) {
		return new LObj1BiObj2PredAttest(func);
	}

	@Nonnull
	public static <T2, T1, T3> LObj1BiObj2PredAttest<T2, T1, T3> attestObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func, String name) {
		return new LObj1BiObj2PredAttest(func, name);
	}

	@Nonnull
	public static <T2, T3, T1> LObj1Obj2Obj0PredAttest<T2, T3, T1> attestObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func) {
		return new LObj1Obj2Obj0PredAttest(func);
	}

	@Nonnull
	public static <T2, T3, T1> LObj1Obj2Obj0PredAttest<T2, T3, T1> attestObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func, String name) {
		return new LObj1Obj2Obj0PredAttest(func, name);
	}

	@Nonnull
	public static <T3, T1, T2> LObj2Obj0Obj1PredAttest<T3, T1, T2> attestObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func) {
		return new LObj2Obj0Obj1PredAttest(func);
	}

	@Nonnull
	public static <T3, T1, T2> LObj2Obj0Obj1PredAttest<T3, T1, T2> attestObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func, String name) {
		return new LObj2Obj0Obj1PredAttest(func, name);
	}

	@Nonnull
	public static <T3, T2, T1> LBiObj1Obj0PredAttest<T3, T2, T1> attestBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func) {
		return new LBiObj1Obj0PredAttest(func);
	}

	@Nonnull
	public static <T3, T2, T1> LBiObj1Obj0PredAttest<T3, T2, T1> attestBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func, String name) {
		return new LBiObj1Obj0PredAttest(func, name);
	}

	@Nonnull
	public static LTriSrtPredicateAttest attestTriSrtPred(LTriSrtPredicate func) {
		return new LTriSrtPredicateAttest(func);
	}

	@Nonnull
	public static LTriSrtPredicateAttest attestTriSrtPred(LTriSrtPredicate func, String name) {
		return new LTriSrtPredicateAttest(func, name);
	}

	@Nonnull
	public static LBoolSupplierAttest attestBoolSup(LBoolSupplier func) {
		return new LBoolSupplierAttest(func);
	}

	@Nonnull
	public static LBoolSupplierAttest attestBoolSup(LBoolSupplier func, String name) {
		return new LBoolSupplierAttest(func, name);
	}

	@Nonnull
	public static LByteSupplierAttest attestByteSup(LByteSupplier func) {
		return new LByteSupplierAttest(func);
	}

	@Nonnull
	public static LByteSupplierAttest attestByteSup(LByteSupplier func, String name) {
		return new LByteSupplierAttest(func, name);
	}

	@Nonnull
	public static LCharSupplierAttest attestCharSup(LCharSupplier func) {
		return new LCharSupplierAttest(func);
	}

	@Nonnull
	public static LCharSupplierAttest attestCharSup(LCharSupplier func, String name) {
		return new LCharSupplierAttest(func, name);
	}

	@Nonnull
	public static LDblSupplierAttest attestDblSup(LDblSupplier func) {
		return new LDblSupplierAttest(func);
	}

	@Nonnull
	public static LDblSupplierAttest attestDblSup(LDblSupplier func, String name) {
		return new LDblSupplierAttest(func, name);
	}

	@Nonnull
	public static LFltSupplierAttest attestFltSup(LFltSupplier func) {
		return new LFltSupplierAttest(func);
	}

	@Nonnull
	public static LFltSupplierAttest attestFltSup(LFltSupplier func, String name) {
		return new LFltSupplierAttest(func, name);
	}

	@Nonnull
	public static LIntSupplierAttest attestIntSup(LIntSupplier func) {
		return new LIntSupplierAttest(func);
	}

	@Nonnull
	public static LIntSupplierAttest attestIntSup(LIntSupplier func, String name) {
		return new LIntSupplierAttest(func, name);
	}

	@Nonnull
	public static LLongSupplierAttest attestLongSup(LLongSupplier func) {
		return new LLongSupplierAttest(func);
	}

	@Nonnull
	public static LLongSupplierAttest attestLongSup(LLongSupplier func, String name) {
		return new LLongSupplierAttest(func, name);
	}

	@Nonnull
	public static LSrtSupplierAttest attestSrtSup(LSrtSupplier func) {
		return new LSrtSupplierAttest(func);
	}

	@Nonnull
	public static LSrtSupplierAttest attestSrtSup(LSrtSupplier func, String name) {
		return new LSrtSupplierAttest(func, name);
	}

	@Nonnull
	public static <T> LSupplierAttest<T> attestSup(LSupplier<T> func) {
		return new LSupplierAttest(func);
	}

	@Nonnull
	public static <T> LSupplierAttest<T> attestSup(LSupplier<T> func, String name) {
		return new LSupplierAttest(func, name);
	}

	@Nonnull
	public static JreRunnableAttest attestAct(Runnable func) {
		return new JreRunnableAttest(func);
	}

	@Nonnull
	public static JreRunnableAttest attestAct(Runnable func, String name) {
		return new JreRunnableAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> JreBiConsumerAttest<T1, T2> attestBiCons(BiConsumer<T1, T2> func) {
		return new JreBiConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> JreBiConsumerAttest<T1, T2> attestBiCons(BiConsumer<T1, T2> func, String name) {
		return new JreBiConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> JreConsumerAttest<T> attestCons(Consumer<T> func) {
		return new JreConsumerAttest(func);
	}

	@Nonnull
	public static <T> JreConsumerAttest<T> attestCons(Consumer<T> func, String name) {
		return new JreConsumerAttest(func, name);
	}

	@Nonnull
	public static JreDoubleConsumerAttest attestDblCons(DoubleConsumer func) {
		return new JreDoubleConsumerAttest(func);
	}

	@Nonnull
	public static JreDoubleConsumerAttest attestDblCons(DoubleConsumer func, String name) {
		return new JreDoubleConsumerAttest(func, name);
	}

	@Nonnull
	public static JreIntConsumerAttest attestIntCons(IntConsumer func) {
		return new JreIntConsumerAttest(func);
	}

	@Nonnull
	public static JreIntConsumerAttest attestIntCons(IntConsumer func, String name) {
		return new JreIntConsumerAttest(func, name);
	}

	@Nonnull
	public static JreLongConsumerAttest attestLongCons(LongConsumer func) {
		return new JreLongConsumerAttest(func);
	}

	@Nonnull
	public static JreLongConsumerAttest attestLongCons(LongConsumer func, String name) {
		return new JreLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> JreObjDoubleConsumerAttest<T> attestObjDblCons(ObjDoubleConsumer<T> func) {
		return new JreObjDoubleConsumerAttest(func);
	}

	@Nonnull
	public static <T> JreObjDoubleConsumerAttest<T> attestObjDblCons(ObjDoubleConsumer<T> func, String name) {
		return new JreObjDoubleConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> JreObjIntConsumerAttest<T> attestObjIntCons(ObjIntConsumer<T> func) {
		return new JreObjIntConsumerAttest(func);
	}

	@Nonnull
	public static <T> JreObjIntConsumerAttest<T> attestObjIntCons(ObjIntConsumer<T> func, String name) {
		return new JreObjIntConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> JreObjLongConsumerAttest<T> attestObjLongCons(ObjLongConsumer<T> func) {
		return new JreObjLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> JreObjLongConsumerAttest<T> attestObjLongCons(ObjLongConsumer<T> func, String name) {
		return new JreObjLongConsumerAttest(func, name);
	}

	@Nonnull
	public static <T> JreBinaryOperatorAttest<T> attestBinaryOp(BinaryOperator<T> func) {
		return new JreBinaryOperatorAttest(func);
	}

	@Nonnull
	public static <T> JreBinaryOperatorAttest<T> attestBinaryOp(BinaryOperator<T> func, String name) {
		return new JreBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreDoubleBinaryOperatorAttest attestDblBinaryOp(DoubleBinaryOperator func) {
		return new JreDoubleBinaryOperatorAttest(func);
	}

	@Nonnull
	public static JreDoubleBinaryOperatorAttest attestDblBinaryOp(DoubleBinaryOperator func, String name) {
		return new JreDoubleBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreDoubleUnaryOperatorAttest attestDblUnaryOp(DoubleUnaryOperator func) {
		return new JreDoubleUnaryOperatorAttest(func);
	}

	@Nonnull
	public static JreDoubleUnaryOperatorAttest attestDblUnaryOp(DoubleUnaryOperator func, String name) {
		return new JreDoubleUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreIntBinaryOperatorAttest attestIntBinaryOp(IntBinaryOperator func) {
		return new JreIntBinaryOperatorAttest(func);
	}

	@Nonnull
	public static JreIntBinaryOperatorAttest attestIntBinaryOp(IntBinaryOperator func, String name) {
		return new JreIntBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreIntUnaryOperatorAttest attestIntUnaryOp(IntUnaryOperator func) {
		return new JreIntUnaryOperatorAttest(func);
	}

	@Nonnull
	public static JreIntUnaryOperatorAttest attestIntUnaryOp(IntUnaryOperator func, String name) {
		return new JreIntUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreLongBinaryOperatorAttest attestLongBinaryOp(LongBinaryOperator func) {
		return new JreLongBinaryOperatorAttest(func);
	}

	@Nonnull
	public static JreLongBinaryOperatorAttest attestLongBinaryOp(LongBinaryOperator func, String name) {
		return new JreLongBinaryOperatorAttest(func, name);
	}

	@Nonnull
	public static JreLongUnaryOperatorAttest attestLongUnaryOp(LongUnaryOperator func) {
		return new JreLongUnaryOperatorAttest(func);
	}

	@Nonnull
	public static JreLongUnaryOperatorAttest attestLongUnaryOp(LongUnaryOperator func, String name) {
		return new JreLongUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static <T> JreUnaryOperatorAttest<T> attestUnaryOp(UnaryOperator<T> func) {
		return new JreUnaryOperatorAttest(func);
	}

	@Nonnull
	public static <T> JreUnaryOperatorAttest<T> attestUnaryOp(UnaryOperator<T> func, String name) {
		return new JreUnaryOperatorAttest(func, name);
	}

	@Nonnull
	public static <T1, T2, R> JreBiFunctionAttest<T1, T2, R> attestBiFunc(BiFunction<T1, T2, R> func) {
		return new JreBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> JreBiFunctionAttest<T1, T2, R> attestBiFunc(BiFunction<T1, T2, R> func, String name) {
		return new JreBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> JreDoubleFunctionAttest<R> attestDblFunc(DoubleFunction<R> func) {
		return new JreDoubleFunctionAttest(func);
	}

	@Nonnull
	public static <R> JreDoubleFunctionAttest<R> attestDblFunc(DoubleFunction<R> func, String name) {
		return new JreDoubleFunctionAttest(func, name);
	}

	@Nonnull
	public static JreDoubleToIntFunctionAttest attestDblToIntFunc(DoubleToIntFunction func) {
		return new JreDoubleToIntFunctionAttest(func);
	}

	@Nonnull
	public static JreDoubleToIntFunctionAttest attestDblToIntFunc(DoubleToIntFunction func, String name) {
		return new JreDoubleToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static JreDoubleToLongFunctionAttest attestDblToLongFunc(DoubleToLongFunction func) {
		return new JreDoubleToLongFunctionAttest(func);
	}

	@Nonnull
	public static JreDoubleToLongFunctionAttest attestDblToLongFunc(DoubleToLongFunction func, String name) {
		return new JreDoubleToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T, R> JreFunctionAttest<T, R> attestFunc(Function<T, R> func) {
		return new JreFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> JreFunctionAttest<T, R> attestFunc(Function<T, R> func, String name) {
		return new JreFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> JreIntFunctionAttest<R> attestIntFunc(IntFunction<R> func) {
		return new JreIntFunctionAttest(func);
	}

	@Nonnull
	public static <R> JreIntFunctionAttest<R> attestIntFunc(IntFunction<R> func, String name) {
		return new JreIntFunctionAttest(func, name);
	}

	@Nonnull
	public static JreIntToDoubleFunctionAttest attestIntToDblFunc(IntToDoubleFunction func) {
		return new JreIntToDoubleFunctionAttest(func);
	}

	@Nonnull
	public static JreIntToDoubleFunctionAttest attestIntToDblFunc(IntToDoubleFunction func, String name) {
		return new JreIntToDoubleFunctionAttest(func, name);
	}

	@Nonnull
	public static JreIntToLongFunctionAttest attestIntToLongFunc(IntToLongFunction func) {
		return new JreIntToLongFunctionAttest(func);
	}

	@Nonnull
	public static JreIntToLongFunctionAttest attestIntToLongFunc(IntToLongFunction func, String name) {
		return new JreIntToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <R> JreLongFunctionAttest<R> attestLongFunc(LongFunction<R> func) {
		return new JreLongFunctionAttest(func);
	}

	@Nonnull
	public static <R> JreLongFunctionAttest<R> attestLongFunc(LongFunction<R> func, String name) {
		return new JreLongFunctionAttest(func, name);
	}

	@Nonnull
	public static JreLongToDoubleFunctionAttest attestLongToDblFunc(LongToDoubleFunction func) {
		return new JreLongToDoubleFunctionAttest(func);
	}

	@Nonnull
	public static JreLongToDoubleFunctionAttest attestLongToDblFunc(LongToDoubleFunction func, String name) {
		return new JreLongToDoubleFunctionAttest(func, name);
	}

	@Nonnull
	public static JreLongToIntFunctionAttest attestLongToIntFunc(LongToIntFunction func) {
		return new JreLongToIntFunctionAttest(func);
	}

	@Nonnull
	public static JreLongToIntFunctionAttest attestLongToIntFunc(LongToIntFunction func, String name) {
		return new JreLongToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> JreToDoubleBiFunctionAttest<T1, T2> attestToDblBiFunc(ToDoubleBiFunction<T1, T2> func) {
		return new JreToDoubleBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> JreToDoubleBiFunctionAttest<T1, T2> attestToDblBiFunc(ToDoubleBiFunction<T1, T2> func, String name) {
		return new JreToDoubleBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> JreToDoubleFunctionAttest<T> attestToDblFunc(ToDoubleFunction<T> func) {
		return new JreToDoubleFunctionAttest(func);
	}

	@Nonnull
	public static <T> JreToDoubleFunctionAttest<T> attestToDblFunc(ToDoubleFunction<T> func, String name) {
		return new JreToDoubleFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> JreToIntBiFunctionAttest<T1, T2> attestToIntBiFunc(ToIntBiFunction<T1, T2> func) {
		return new JreToIntBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> JreToIntBiFunctionAttest<T1, T2> attestToIntBiFunc(ToIntBiFunction<T1, T2> func, String name) {
		return new JreToIntBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> JreToIntFunctionAttest<T> attestToIntFunc(ToIntFunction<T> func) {
		return new JreToIntFunctionAttest(func);
	}

	@Nonnull
	public static <T> JreToIntFunctionAttest<T> attestToIntFunc(ToIntFunction<T> func, String name) {
		return new JreToIntFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> JreToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(ToLongBiFunction<T1, T2> func) {
		return new JreToLongBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> JreToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(ToLongBiFunction<T1, T2> func, String name) {
		return new JreToLongBiFunctionAttest(func, name);
	}

	@Nonnull
	public static <T> JreToLongFunctionAttest<T> attestToLongFunc(ToLongFunction<T> func) {
		return new JreToLongFunctionAttest(func);
	}

	@Nonnull
	public static <T> JreToLongFunctionAttest<T> attestToLongFunc(ToLongFunction<T> func, String name) {
		return new JreToLongFunctionAttest(func, name);
	}

	@Nonnull
	public static <T1, T2> JreBiPredicateAttest<T1, T2> attestBiPred(BiPredicate<T1, T2> func) {
		return new JreBiPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> JreBiPredicateAttest<T1, T2> attestBiPred(BiPredicate<T1, T2> func, String name) {
		return new JreBiPredicateAttest(func, name);
	}

	@Nonnull
	public static JreDoublePredicateAttest attestDblPred(DoublePredicate func) {
		return new JreDoublePredicateAttest(func);
	}

	@Nonnull
	public static JreDoublePredicateAttest attestDblPred(DoublePredicate func, String name) {
		return new JreDoublePredicateAttest(func, name);
	}

	@Nonnull
	public static JreIntPredicateAttest attestIntPred(IntPredicate func) {
		return new JreIntPredicateAttest(func);
	}

	@Nonnull
	public static JreIntPredicateAttest attestIntPred(IntPredicate func, String name) {
		return new JreIntPredicateAttest(func, name);
	}

	@Nonnull
	public static JreLongPredicateAttest attestLongPred(LongPredicate func) {
		return new JreLongPredicateAttest(func);
	}

	@Nonnull
	public static JreLongPredicateAttest attestLongPred(LongPredicate func, String name) {
		return new JreLongPredicateAttest(func, name);
	}

	@Nonnull
	public static <T> JrePredicateAttest<T> attestPred(Predicate<T> func) {
		return new JrePredicateAttest(func);
	}

	@Nonnull
	public static <T> JrePredicateAttest<T> attestPred(Predicate<T> func, String name) {
		return new JrePredicateAttest(func, name);
	}

	@Nonnull
	public static JreBooleanSupplierAttest attestBoolSup(BooleanSupplier func) {
		return new JreBooleanSupplierAttest(func);
	}

	@Nonnull
	public static JreBooleanSupplierAttest attestBoolSup(BooleanSupplier func, String name) {
		return new JreBooleanSupplierAttest(func, name);
	}

	@Nonnull
	public static JreDoubleSupplierAttest attestDblSup(DoubleSupplier func) {
		return new JreDoubleSupplierAttest(func);
	}

	@Nonnull
	public static JreDoubleSupplierAttest attestDblSup(DoubleSupplier func, String name) {
		return new JreDoubleSupplierAttest(func, name);
	}

	@Nonnull
	public static JreIntSupplierAttest attestIntSup(IntSupplier func) {
		return new JreIntSupplierAttest(func);
	}

	@Nonnull
	public static JreIntSupplierAttest attestIntSup(IntSupplier func, String name) {
		return new JreIntSupplierAttest(func, name);
	}

	@Nonnull
	public static JreLongSupplierAttest attestLongSup(LongSupplier func) {
		return new JreLongSupplierAttest(func);
	}

	@Nonnull
	public static JreLongSupplierAttest attestLongSup(LongSupplier func, String name) {
		return new JreLongSupplierAttest(func, name);
	}

	@Nonnull
	public static <T> JreSupplierAttest<T> attestSup(Supplier<T> func) {
		return new JreSupplierAttest(func);
	}

	@Nonnull
	public static <T> JreSupplierAttest<T> attestSup(Supplier<T> func, String name) {
		return new JreSupplierAttest(func, name);
	}

}