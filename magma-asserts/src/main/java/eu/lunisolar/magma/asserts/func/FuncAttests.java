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
	public static LBiByteConsumerAttest attestBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAttest(func);
	}

	@Nonnull
	public static LBiByteConsumerAttest attestBiByteCons(LBiByteConsumer func, String name) {
		return new LBiByteConsumerAttest(func, name);
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
	public static LBiDblConsumerAttest attestBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAttest(func);
	}

	@Nonnull
	public static LBiDblConsumerAttest attestBiDblCons(LBiDblConsumer func, String name) {
		return new LBiDblConsumerAttest(func, name);
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
	public static LBiIntConsumerAttest attestBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAttest(func);
	}

	@Nonnull
	public static LBiIntConsumerAttest attestBiIntCons(LBiIntConsumer func, String name) {
		return new LBiIntConsumerAttest(func, name);
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
	public static LBiSrtConsumerAttest attestBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAttest(func);
	}

	@Nonnull
	public static LBiSrtConsumerAttest attestBiSrtCons(LBiSrtConsumer func, String name) {
		return new LBiSrtConsumerAttest(func, name);
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
	public static LByteIntConsumerAttest attestByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAttest(func);
	}

	@Nonnull
	public static LByteIntConsumerAttest attestByteIntCons(LByteIntConsumer func, String name) {
		return new LByteIntConsumerAttest(func, name);
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
	public static LDblIntConsumerAttest attestDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAttest(func);
	}

	@Nonnull
	public static LDblIntConsumerAttest attestDblIntCons(LDblIntConsumer func, String name) {
		return new LDblIntConsumerAttest(func, name);
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
	public static LLongIntConsumerAttest attestLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAttest(func);
	}

	@Nonnull
	public static LLongIntConsumerAttest attestLongIntCons(LLongIntConsumer func, String name) {
		return new LLongIntConsumerAttest(func, name);
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
	public static <T1, T2> LBiObjBoolConsumerAttest<T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjBoolConsumerAttest<T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func, String name) {
		return new LBiObjBoolConsumerAttest(func, name);
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
	public static <T1, T2> LBiObjCharConsumerAttest<T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjCharConsumerAttest<T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func, String name) {
		return new LBiObjCharConsumerAttest(func, name);
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
	public static <T1, T2> LBiObjFltConsumerAttest<T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjFltConsumerAttest<T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func, String name) {
		return new LBiObjFltConsumerAttest(func, name);
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
	public static <T1, T2> LBiObjLongConsumerAttest<T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjLongConsumerAttest<T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func, String name) {
		return new LBiObjLongConsumerAttest(func, name);
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
	public static <T> LObjBiLongConsumerAttest<T> attestObjBiLongCons(LObjBiLongConsumer<T> func) {
		return new LObjBiLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjBiLongConsumerAttest<T> attestObjBiLongCons(LObjBiLongConsumer<T> func, String name) {
		return new LObjBiLongConsumerAttest(func, name);
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
	public static <T> LObjByteConsumerAttest<T> attestObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjByteConsumerAttest<T> attestObjByteCons(LObjByteConsumer<T> func, String name) {
		return new LObjByteConsumerAttest(func, name);
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
	public static <T> LObjDblConsumerAttest<T> attestObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjDblConsumerAttest<T> attestObjDblCons(LObjDblConsumer<T> func, String name) {
		return new LObjDblConsumerAttest(func, name);
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
	public static <T> LObjIntConsumerAttest<T> attestObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjIntConsumerAttest<T> attestObjIntCons(LObjIntConsumer<T> func, String name) {
		return new LObjIntConsumerAttest(func, name);
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
	public static <T> LObjSrtConsumerAttest<T> attestObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtConsumerAttest<T> attestObjSrtCons(LObjSrtConsumer<T> func, String name) {
		return new LObjSrtConsumerAttest(func, name);
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
	public static <T> LTieByteConsumerAttest<T> attestTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieByteConsumerAttest<T> attestTieByteCons(LTieByteConsumer<T> func, String name) {
		return new LTieByteConsumerAttest(func, name);
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
	public static <T1, T2> LTieConsumerAttest<T1, T2> attestTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAttest(func);
	}

	@Nonnull
	public static <T1, T2> LTieConsumerAttest<T1, T2> attestTieCons(LTieConsumer<T1, T2> func, String name) {
		return new LTieConsumerAttest(func, name);
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
	public static <T> LTieFltConsumerAttest<T> attestTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieFltConsumerAttest<T> attestTieFltCons(LTieFltConsumer<T> func, String name) {
		return new LTieFltConsumerAttest(func, name);
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
	public static <T> LTieLongConsumerAttest<T> attestTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAttest(func);
	}

	@Nonnull
	public static <T> LTieLongConsumerAttest<T> attestTieLongCons(LTieLongConsumer<T> func, String name) {
		return new LTieLongConsumerAttest(func, name);
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
	public static <R> LBiByteFunctionAttest<R> attestBiByteFunc(LBiByteFunction<R> func) {
		return new LBiByteFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiByteFunctionAttest<R> attestBiByteFunc(LBiByteFunction<R> func, String name) {
		return new LBiByteFunctionAttest(func, name);
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
	public static <R> LBiDblFunctionAttest<R> attestBiDblFunc(LBiDblFunction<R> func) {
		return new LBiDblFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiDblFunctionAttest<R> attestBiDblFunc(LBiDblFunction<R> func, String name) {
		return new LBiDblFunctionAttest(func, name);
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
	public static <R> LBiIntFunctionAttest<R> attestBiIntFunc(LBiIntFunction<R> func) {
		return new LBiIntFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiIntFunctionAttest<R> attestBiIntFunc(LBiIntFunction<R> func, String name) {
		return new LBiIntFunctionAttest(func, name);
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
	public static <T1, T2, R> LBiObjBoolFunctionAttest<T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjBoolFunctionAttest<T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func, String name) {
		return new LBiObjBoolFunctionAttest(func, name);
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
	public static <T1, T2, R> LBiObjCharFunctionAttest<T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjCharFunctionAttest<T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func, String name) {
		return new LBiObjCharFunctionAttest(func, name);
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
	public static <T1, T2, R> LBiObjFltFunctionAttest<T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) {
		return new LBiObjFltFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjFltFunctionAttest<T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func, String name) {
		return new LBiObjFltFunctionAttest(func, name);
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
	public static <T1, T2, R> LBiObjLongFunctionAttest<T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LBiObjLongFunctionAttest<T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func, String name) {
		return new LBiObjLongFunctionAttest(func, name);
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
	public static <R> LBiSrtFunctionAttest<R> attestBiSrtFunc(LBiSrtFunction<R> func) {
		return new LBiSrtFunctionAttest(func);
	}

	@Nonnull
	public static <R> LBiSrtFunctionAttest<R> attestBiSrtFunc(LBiSrtFunction<R> func, String name) {
		return new LBiSrtFunctionAttest(func, name);
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
	public static <T, R> LObjBiLongFunctionAttest<T, R> attestObjBiLongFunc(LObjBiLongFunction<T, R> func) {
		return new LObjBiLongFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjBiLongFunctionAttest<T, R> attestObjBiLongFunc(LObjBiLongFunction<T, R> func, String name) {
		return new LObjBiLongFunctionAttest(func, name);
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
	public static <T, R> LObjByteFunctionAttest<T, R> attestObjByteFunc(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjByteFunctionAttest<T, R> attestObjByteFunc(LObjByteFunction<T, R> func, String name) {
		return new LObjByteFunctionAttest(func, name);
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
	public static <T, R> LObjDblFunctionAttest<T, R> attestObjDblFunc(LObjDblFunction<T, R> func) {
		return new LObjDblFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjDblFunctionAttest<T, R> attestObjDblFunc(LObjDblFunction<T, R> func, String name) {
		return new LObjDblFunctionAttest(func, name);
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
	public static <T, R> LObjIntBoolFunctionAttest<T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func) {
		return new LObjIntBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntBoolFunctionAttest<T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func, String name) {
		return new LObjIntBoolFunctionAttest(func, name);
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
	public static <T, R> LObjIntCharFunctionAttest<T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func) {
		return new LObjIntCharFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntCharFunctionAttest<T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func, String name) {
		return new LObjIntCharFunctionAttest(func, name);
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
	public static <T, R> LObjIntFltFunctionAttest<T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func) {
		return new LObjIntFltFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjIntFltFunctionAttest<T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func, String name) {
		return new LObjIntFltFunctionAttest(func, name);
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
	public static <T1, T2, R> LObjIntObjFunctionAttest<T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) {
		return new LObjIntObjFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2, R> LObjIntObjFunctionAttest<T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func, String name) {
		return new LObjIntObjFunctionAttest(func, name);
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
	public static <T, R> LObjLongFunctionAttest<T, R> attestObjLongFunc(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LObjLongFunctionAttest<T, R> attestObjLongFunc(LObjLongFunction<T, R> func, String name) {
		return new LObjLongFunctionAttest(func, name);
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
	public static <T, R> LOiFunctionAttest<T, R> attestOiFunc(LOiFunction<T, R> func) {
		return new LOiFunctionAttest(func);
	}

	@Nonnull
	public static <T, R> LOiFunctionAttest<T, R> attestOiFunc(LOiFunction<T, R> func, String name) {
		return new LOiFunctionAttest(func, name);
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
	public static <T> LOiToCharFunctionAttest<T> attestOiToCharFunc(LOiToCharFunction<T> func) {
		return new LOiToCharFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToCharFunctionAttest<T> attestOiToCharFunc(LOiToCharFunction<T> func, String name) {
		return new LOiToCharFunctionAttest(func, name);
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
	public static <T> LOiToFltFunctionAttest<T> attestOiToFltFunc(LOiToFltFunction<T> func) {
		return new LOiToFltFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToFltFunctionAttest<T> attestOiToFltFunc(LOiToFltFunction<T> func, String name) {
		return new LOiToFltFunctionAttest(func, name);
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
	public static <T> LOiToLongFunctionAttest<T> attestOiToLongFunc(LOiToLongFunction<T> func) {
		return new LOiToLongFunctionAttest(func);
	}

	@Nonnull
	public static <T> LOiToLongFunctionAttest<T> attestOiToLongFunc(LOiToLongFunction<T> func, String name) {
		return new LOiToLongFunctionAttest(func, name);
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
	public static <T> LTieBoolFunctionAttest<T> attestTieBoolFunc(LTieBoolFunction<T> func) {
		return new LTieBoolFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieBoolFunctionAttest<T> attestTieBoolFunc(LTieBoolFunction<T> func, String name) {
		return new LTieBoolFunctionAttest(func, name);
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
	public static <T> LTieCharFunctionAttest<T> attestTieCharFunc(LTieCharFunction<T> func) {
		return new LTieCharFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieCharFunctionAttest<T> attestTieCharFunc(LTieCharFunction<T> func, String name) {
		return new LTieCharFunctionAttest(func, name);
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
	public static <T> LTieFltFunctionAttest<T> attestTieFltFunc(LTieFltFunction<T> func) {
		return new LTieFltFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieFltFunctionAttest<T> attestTieFltFunc(LTieFltFunction<T> func, String name) {
		return new LTieFltFunctionAttest(func, name);
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
	public static <T> LTieIntFunctionAttest<T> attestTieIntFunc(LTieIntFunction<T> func) {
		return new LTieIntFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieIntFunctionAttest<T> attestTieIntFunc(LTieIntFunction<T> func, String name) {
		return new LTieIntFunctionAttest(func, name);
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
	public static <T> LTieSrtFunctionAttest<T> attestTieSrtFunc(LTieSrtFunction<T> func) {
		return new LTieSrtFunctionAttest(func);
	}

	@Nonnull
	public static <T> LTieSrtFunctionAttest<T> attestTieSrtFunc(LTieSrtFunction<T> func, String name) {
		return new LTieSrtFunctionAttest(func, name);
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
	public static <T1, T2> LToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAttest(func);
	}

	@Nonnull
	public static <T1, T2> LToLongBiFunctionAttest<T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func, String name) {
		return new LToLongBiFunctionAttest(func, name);
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
	public static LBiCharPredicateAttest attestBiCharPred(LBiCharPredicate func) {
		return new LBiCharPredicateAttest(func);
	}

	@Nonnull
	public static LBiCharPredicateAttest attestBiCharPred(LBiCharPredicate func, String name) {
		return new LBiCharPredicateAttest(func, name);
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
	public static LBiFltPredicateAttest attestBiFltPred(LBiFltPredicate func) {
		return new LBiFltPredicateAttest(func);
	}

	@Nonnull
	public static LBiFltPredicateAttest attestBiFltPred(LBiFltPredicate func, String name) {
		return new LBiFltPredicateAttest(func, name);
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
	public static LBiLongPredicateAttest attestBiLongPred(LBiLongPredicate func) {
		return new LBiLongPredicateAttest(func);
	}

	@Nonnull
	public static LBiLongPredicateAttest attestBiLongPred(LBiLongPredicate func, String name) {
		return new LBiLongPredicateAttest(func, name);
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
	public static <T1, T2> LBiObjBytePredicateAttest<T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjBytePredicateAttest<T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func, String name) {
		return new LBiObjBytePredicateAttest(func, name);
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
	public static <T1, T2> LBiObjDblPredicateAttest<T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		return new LBiObjDblPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjDblPredicateAttest<T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func, String name) {
		return new LBiObjDblPredicateAttest(func, name);
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
	public static <T1, T2> LBiObjIntPredicateAttest<T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjIntPredicateAttest<T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func, String name) {
		return new LBiObjIntPredicateAttest(func, name);
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
	public static <T1, T2> LBiObjSrtPredicateAttest<T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		return new LBiObjSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LBiObjSrtPredicateAttest<T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func, String name) {
		return new LBiObjSrtPredicateAttest(func, name);
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
	public static LBiSrtPredicateAttest attestBiSrtPred(LBiSrtPredicate func) {
		return new LBiSrtPredicateAttest(func);
	}

	@Nonnull
	public static LBiSrtPredicateAttest attestBiSrtPred(LBiSrtPredicate func, String name) {
		return new LBiSrtPredicateAttest(func, name);
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
	public static LByteIntPredicateAttest attestByteIntPred(LByteIntPredicate func) {
		return new LByteIntPredicateAttest(func);
	}

	@Nonnull
	public static LByteIntPredicateAttest attestByteIntPred(LByteIntPredicate func, String name) {
		return new LByteIntPredicateAttest(func, name);
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
	public static <T> LObjBiLongPredicateAttest<T> attestObjBiLongPred(LObjBiLongPredicate<T> func) {
		return new LObjBiLongPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBiLongPredicateAttest<T> attestObjBiLongPred(LObjBiLongPredicate<T> func, String name) {
		return new LObjBiLongPredicateAttest(func, name);
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
	public static <T> LObjBytePredicateAttest<T> attestObjBytePred(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjBytePredicateAttest<T> attestObjBytePred(LObjBytePredicate<T> func, String name) {
		return new LObjBytePredicateAttest(func, name);
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
	public static <T> LObjDblPredicateAttest<T> attestObjDblPred(LObjDblPredicate<T> func) {
		return new LObjDblPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjDblPredicateAttest<T> attestObjDblPred(LObjDblPredicate<T> func, String name) {
		return new LObjDblPredicateAttest(func, name);
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
	public static <T> LObjIntBoolPredicateAttest<T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		return new LObjIntBoolPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntBoolPredicateAttest<T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func, String name) {
		return new LObjIntBoolPredicateAttest(func, name);
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
	public static <T> LObjIntCharPredicateAttest<T> attestObjIntCharPred(LObjIntCharPredicate<T> func) {
		return new LObjIntCharPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntCharPredicateAttest<T> attestObjIntCharPred(LObjIntCharPredicate<T> func, String name) {
		return new LObjIntCharPredicateAttest(func, name);
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
	public static <T> LObjIntFltPredicateAttest<T> attestObjIntFltPred(LObjIntFltPredicate<T> func) {
		return new LObjIntFltPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntFltPredicateAttest<T> attestObjIntFltPred(LObjIntFltPredicate<T> func, String name) {
		return new LObjIntFltPredicateAttest(func, name);
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
	public static <T1, T2> LObjIntObjPredicateAttest<T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		return new LObjIntObjPredicateAttest(func);
	}

	@Nonnull
	public static <T1, T2> LObjIntObjPredicateAttest<T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func, String name) {
		return new LObjIntObjPredicateAttest(func, name);
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
	public static <T> LObjIntSrtPredicateAttest<T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		return new LObjIntSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjIntSrtPredicateAttest<T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func, String name) {
		return new LObjIntSrtPredicateAttest(func, name);
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
	public static <T> LObjSrtPredicateAttest<T> attestObjSrtPred(LObjSrtPredicate<T> func) {
		return new LObjSrtPredicateAttest(func);
	}

	@Nonnull
	public static <T> LObjSrtPredicateAttest<T> attestObjSrtPred(LObjSrtPredicate<T> func, String name) {
		return new LObjSrtPredicateAttest(func, name);
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