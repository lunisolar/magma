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

package eu.lunisolar.magma.func.asserts;

import java.util.function.*;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.supplier.*; // NOSONAR
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
//import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the usecase otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface FunctionalAssertions {

	@Nonnull
	public static <A extends LAction> LActionAssert.The<A> assertAct(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
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
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
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
	public static <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> assertBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> assertBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> assertBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> assertBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> assertByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> assertCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> assertDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> assertFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> assertLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> assertSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> assertBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> assertBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> assertBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> assertObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> assertObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> assertObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> assertTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> assertTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> assertTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> assertTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> assertTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> assertTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> assertTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> assertTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> assertTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
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
	public static <A extends LFunction<T, R>, RS extends Assert<RS, R>, T, R> LFunctionAssert.The<A, RS, T, R> assertFunc(LFunction<T, R> func) {
		return new LFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LQuadFunction<T1, T2, T3, T4, R>, RS extends Assert<RS, R>, T1, T2, T3, T4, R> LQuadFunctionAssert.The<A, RS, T1, T2, T3, T4, R> assertQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) {
		return new LQuadFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends Assert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, RS, T1, T2, T3, R> assertTriFunc(LTriFunction<T1, T2, T3, R> func) {
		return new LTriFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LBiByteFunction<R>, RS extends Assert<RS, R>, R> LBiByteFunctionAssert.The<A, RS, R> assertBiByteFunc(LBiByteFunction<R> func) {
		return new LBiByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction<R>, RS extends Assert<RS, R>, R> LBiCharFunctionAssert.The<A, RS, R> assertBiCharFunc(LBiCharFunction<R> func) {
		return new LBiCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblFunction<R>, RS extends Assert<RS, R>, R> LBiDblFunctionAssert.The<A, RS, R> assertBiDblFunc(LBiDblFunction<R> func) {
		return new LBiDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltFunction<R>, RS extends Assert<RS, R>, R> LBiFltFunctionAssert.The<A, RS, R> assertBiFltFunc(LBiFltFunction<R> func) {
		return new LBiFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction<R>, RS extends Assert<RS, R>, R> LBiIntFunctionAssert.The<A, RS, R> assertBiIntFunc(LBiIntFunction<R> func) {
		return new LBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction<R>, RS extends Assert<RS, R>, R> LBiLongFunctionAssert.The<A, RS, R> assertBiLongFunc(LBiLongFunction<R> func) {
		return new LBiLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, RS, T1, T2, R> assertBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, RS, T1, T2, R> assertBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) {
		return new LBiObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, RS, T1, T2, R> assertBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, RS, T1, T2, R> assertBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) {
		return new LBiObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, RS, T1, T2, R> assertBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) {
		return new LBiObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, RS, T1, T2, R> assertBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) {
		return new LBiObjIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, RS, T1, T2, R> assertBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, RS, T1, T2, R> assertBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) {
		return new LBiObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtFunction<R>, RS extends Assert<RS, R>, R> LBiSrtFunctionAssert.The<A, RS, R> assertBiSrtFunc(LBiSrtFunction<R> func) {
		return new LBiSrtFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LObjBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolFunctionAssert.The<A, RS, T, R> assertObjBoolFunc(LObjBoolFunction<T, R> func) {
		return new LObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjByteFunctionAssert.The<A, RS, T, R> assertObjByteFunc(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjCharFunctionAssert.The<A, RS, T, R> assertObjCharFunc(LObjCharFunction<T, R> func) {
		return new LObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjDblFunctionAssert.The<A, RS, T, R> assertObjDblFunc(LObjDblFunction<T, R> func) {
		return new LObjDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjFltFunctionAssert.The<A, RS, T, R> assertObjFltFunc(LObjFltFunction<T, R> func) {
		return new LObjFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntBoolFunctionAssert.The<A, RS, T, R> assertObjIntBoolFunc(LObjIntBoolFunction<T, R> func) {
		return new LObjIntBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntByteFunctionAssert.The<A, RS, T, R> assertObjIntByteFunc(LObjIntByteFunction<T, R> func) {
		return new LObjIntByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntCharFunctionAssert.The<A, RS, T, R> assertObjIntCharFunc(LObjIntCharFunction<T, R> func) {
		return new LObjIntCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntDblFunctionAssert.The<A, RS, T, R> assertObjIntDblFunc(LObjIntDblFunction<T, R> func) {
		return new LObjIntDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntFltFunctionAssert.The<A, RS, T, R> assertObjIntFltFunc(LObjIntFltFunction<T, R> func) {
		return new LObjIntFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntLongFunctionAssert.The<A, RS, T, R> assertObjIntLongFunc(LObjIntLongFunction<T, R> func) {
		return new LObjIntLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, RS, T1, T2, R> assertObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) {
		return new LObjIntObjFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntSrtFunctionAssert.The<A, RS, T, R> assertObjIntSrtFunc(LObjIntSrtFunction<T, R> func) {
		return new LObjIntSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjLongFunctionAssert.The<A, RS, T, R> assertObjLongFunc(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjSrtFunctionAssert.The<A, RS, T, R> assertObjSrtFunc(LObjSrtFunction<T, R> func) {
		return new LObjSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiFunction<T, R>, RS extends Assert<RS, R>, T, R> LOiFunctionAssert.The<A, RS, T, R> assertOiFunc(LOiFunction<T, R> func) {
		return new LOiFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LOiToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LOiToCharFunctionAssert.The<A, RS, T> assertOiToCharFunc(LOiToCharFunction<T> func) {
		return new LOiToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToDblFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LOiToDblFunctionAssert.The<A, RS, T> assertOiToDblFunc(LOiToDblFunction<T> func) {
		return new LOiToDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LOiToFltFunctionAssert.The<A, RS, T> assertOiToFltFunc(LOiToFltFunction<T> func) {
		return new LOiToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LOiToIntFunctionAssert.The<A, RS, T> assertOiToIntFunc(LOiToIntFunction<T> func) {
		return new LOiToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LOiToLongFunctionAssert.The<A, RS, T> assertOiToLongFunc(LOiToLongFunction<T> func) {
		return new LOiToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LOiToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LOiToSrtFunctionAssert.The<A, RS, T> assertOiToSrtFunc(LOiToSrtFunction<T> func) {
		return new LOiToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieBoolFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieBoolFunctionAssert.The<A, RS, T> assertTieBoolFunc(LTieBoolFunction<T> func) {
		return new LTieBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieByteFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieByteFunctionAssert.The<A, RS, T> assertTieByteFunc(LTieByteFunction<T> func) {
		return new LTieByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieCharFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieCharFunctionAssert.The<A, RS, T> assertTieCharFunc(LTieCharFunction<T> func) {
		return new LTieCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieDblFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieDblFunctionAssert.The<A, RS, T> assertTieDblFunc(LTieDblFunction<T> func) {
		return new LTieDblFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFltFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieFltFunctionAssert.The<A, RS, T> assertTieFltFunc(LTieFltFunction<T> func) {
		return new LTieFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LTieFunctionAssert.The<A, RS, T1, T2> assertTieFunc(LTieFunction<T1, T2> func) {
		return new LTieFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieIntFunctionAssert.The<A, RS, T> assertTieIntFunc(LTieIntFunction<T> func) {
		return new LTieIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieLongFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieLongFunctionAssert.The<A, RS, T> assertTieLongFunc(LTieLongFunction<T> func) {
		return new LTieLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTieSrtFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LTieSrtFunctionAssert.The<A, RS, T> assertTieSrtFunc(LTieSrtFunction<T> func) {
		return new LTieSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.The<A, RS, T1, T2> assertToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		return new LToByteBiFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.The<A, RS, T> assertToCharFunc(LToCharFunction<T> func) {
		return new LToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDblBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDblBiFunctionAssert.The<A, RS, T1, T2> assertToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		return new LToDblBiFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LToFltFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFltFunctionAssert.The<A, RS, T> assertToFltFunc(LToFltFunction<T> func) {
		return new LToFltFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.The<A, RS, T1, T2> assertToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		return new LToIntBiFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.The<A, RS, T1, T2> assertToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAssert.The(func, Assertions::assertThat);
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
	public static <A extends LToSrtFunction<T>, RS extends AbstractShortAssert<RS>, T> LToSrtFunctionAssert.The<A, RS, T> assertToSrtFunc(LToSrtFunction<T> func) {
		return new LToSrtFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.The<A, RS> assertBiBytePred(LBiBytePredicate func) {
		return new LBiBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.The<A, RS> assertBiCharPred(LBiCharPredicate func) {
		return new LBiCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDblPredicate, RS extends AbstractBooleanAssert<RS>> LBiDblPredicateAssert.The<A, RS> assertBiDblPred(LBiDblPredicate func) {
		return new LBiDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFltPredicate, RS extends AbstractBooleanAssert<RS>> LBiFltPredicateAssert.The<A, RS> assertBiFltPred(LBiFltPredicate func) {
		return new LBiFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.The<A, RS> assertBiIntPred(LBiIntPredicate func) {
		return new LBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.The<A, RS> assertBiLongPred(LBiLongPredicate func) {
		return new LBiLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBoolPredicateAssert.The<A, RS, T1, T2> assertBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		return new LBiObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.The<A, RS, T1, T2> assertBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.The<A, RS, T1, T2> assertBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		return new LBiObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDblPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDblPredicateAssert.The<A, RS, T1, T2> assertBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		return new LBiObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFltPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFltPredicateAssert.The<A, RS, T1, T2> assertBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		return new LBiObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.The<A, RS, T1, T2> assertBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.The<A, RS, T1, T2> assertBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		return new LBiObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjSrtPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjSrtPredicateAssert.The<A, RS, T1, T2> assertBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		return new LBiObjSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> assertBiPred(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiSrtPredicate, RS extends AbstractBooleanAssert<RS>> LBiSrtPredicateAssert.The<A, RS> assertBiSrtPred(LBiSrtPredicate func) {
		return new LBiSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolIntPredicate, RS extends AbstractBooleanAssert<RS>> LBoolIntPredicateAssert.The<A, RS> assertBoolIntPred(LBoolIntPredicate func) {
		return new LBoolIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteIntPredicate, RS extends AbstractBooleanAssert<RS>> LByteIntPredicateAssert.The<A, RS> assertByteIntPred(LByteIntPredicate func) {
		return new LByteIntPredicateAssert.The(func, Assertions::assertThat);
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
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.The<A, RS> assertCharPred(LCharPredicate func) {
		return new LCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDblIntPredicate, RS extends AbstractBooleanAssert<RS>> LDblIntPredicateAssert.The<A, RS> assertDblIntPred(LDblIntPredicate func) {
		return new LDblIntPredicateAssert.The(func, Assertions::assertThat);
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
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.The<A, RS> assertLongPred(LLongPredicate func) {
		return new LLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBiIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBiIntPredicateAssert.The<A, RS, T> assertObjBiIntPred(LObjBiIntPredicate<T> func) {
		return new LObjBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolPredicateAssert.The<A, RS, T> assertObjBoolPred(LObjBoolPredicate<T> func) {
		return new LObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.The<A, RS, T> assertObjBytePred(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.The<A, RS, T> assertObjCharPred(LObjCharPredicate<T> func) {
		return new LObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDblPredicateAssert.The<A, RS, T> assertObjDblPred(LObjDblPredicate<T> func) {
		return new LObjDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFltPredicateAssert.The<A, RS, T> assertObjFltPred(LObjFltPredicate<T> func) {
		return new LObjFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBoolPredicateAssert.The<A, RS, T> assertObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		return new LObjIntBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntBytePredicateAssert.The<A, RS, T> assertObjIntBytePred(LObjIntBytePredicate<T> func) {
		return new LObjIntBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntCharPredicateAssert.The<A, RS, T> assertObjIntCharPred(LObjIntCharPredicate<T> func) {
		return new LObjIntCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntDblPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntDblPredicateAssert.The<A, RS, T> assertObjIntDblPred(LObjIntDblPredicate<T> func) {
		return new LObjIntDblPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFltPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntFltPredicateAssert.The<A, RS, T> assertObjIntFltPred(LObjIntFltPredicate<T> func) {
		return new LObjIntFltPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntLongPredicateAssert.The<A, RS, T> assertObjIntLongPred(LObjIntLongPredicate<T> func) {
		return new LObjIntLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntObjPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LObjIntObjPredicateAssert.The<A, RS, T1, T2> assertObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		return new LObjIntObjPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.The<A, RS, T> assertObjIntPred(LObjIntPredicate<T> func) {
		return new LObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntSrtPredicateAssert.The<A, RS, T> assertObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		return new LObjIntSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.The<A, RS, T> assertObjLongPred(LObjLongPredicate<T> func) {
		return new LObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjSrtPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjSrtPredicateAssert.The<A, RS, T> assertObjSrtPred(LObjSrtPredicate<T> func) {
		return new LObjSrtPredicateAssert.The(func, Assertions::assertThat);
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
	public static <A extends LSrtIntPredicate, RS extends AbstractBooleanAssert<RS>> LSrtIntPredicateAssert.The<A, RS> assertSrtIntPred(LSrtIntPredicate func) {
		return new LSrtIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSrtPredicate, RS extends AbstractBooleanAssert<RS>> LSrtPredicateAssert.The<A, RS> assertSrtPred(LSrtPredicate func) {
		return new LSrtPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.The<A, RS, T1, T2, T3> assertTriPred(LTriPredicate<T1, T2, T3> func) {
		return new LTriPredicateAssert.The(func, Assertions::assertThat);
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

}