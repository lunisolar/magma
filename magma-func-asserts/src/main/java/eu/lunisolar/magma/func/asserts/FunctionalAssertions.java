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
	public static <A extends LAction> LActionAssert.The<A> assertThat(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	public static <A extends LActionX<X>, X extends Throwable> LActionXAssert.The<A, X> assertThat(LActionX<X> func) {
		return new LActionXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertThat(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.The<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> func) {
		return new LBiConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertThat(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.The<A, T, X> assertThat(LConsumerX<T, X> func) {
		return new LConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.The<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> func) {
		return new LTriConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolConsumer> LBoolConsumerAssert.The<A> assertThat(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBoolConsumerX<X>, X extends Throwable> LBoolConsumerXAssert.The<A, X> assertThat(LBoolConsumerX<X> func) {
		return new LBoolConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteConsumer> LByteConsumerAssert.The<A> assertThat(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.The<A, X> assertThat(LByteConsumerX<X> func) {
		return new LByteConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharConsumer> LCharConsumerAssert.The<A> assertThat(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.The<A, X> assertThat(LCharConsumerX<X> func) {
		return new LCharConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LDoubleConsumer> LDoubleConsumerAssert.The<A> assertThat(LDoubleConsumer func) {
		return new LDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.The<A, X> assertThat(LDoubleConsumerX<X> func) {
		return new LDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LFloatConsumer> LFloatConsumerAssert.The<A> assertThat(LFloatConsumer func) {
		return new LFloatConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.The<A, X> assertThat(LFloatConsumerX<X> func) {
		return new LFloatConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LIntConsumer> LIntConsumerAssert.The<A> assertThat(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.The<A, X> assertThat(LIntConsumerX<X> func) {
		return new LIntConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongConsumer> LLongConsumerAssert.The<A> assertThat(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.The<A, X> assertThat(LLongConsumerX<X> func) {
		return new LLongConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LShortConsumer> LShortConsumerAssert.The<A> assertThat(LShortConsumer func) {
		return new LShortConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.The<A, X> assertThat(LShortConsumerX<X> func) {
		return new LShortConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> assertThat(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiBoolConsumerX<X>, X extends Throwable> LBiBoolConsumerXAssert.The<A, X> assertThat(LBiBoolConsumerX<X> func) {
		return new LBiBoolConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertThat(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiByteConsumerX<X>, X extends Throwable> LBiByteConsumerXAssert.The<A, X> assertThat(LBiByteConsumerX<X> func) {
		return new LBiByteConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertThat(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiCharConsumerX<X>, X extends Throwable> LBiCharConsumerXAssert.The<A, X> assertThat(LBiCharConsumerX<X> func) {
		return new LBiCharConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDoubleConsumer> LBiDoubleConsumerAssert.The<A> assertThat(LBiDoubleConsumer func) {
		return new LBiDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiDoubleConsumerX<X>, X extends Throwable> LBiDoubleConsumerXAssert.The<A, X> assertThat(LBiDoubleConsumerX<X> func) {
		return new LBiDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFloatConsumer> LBiFloatConsumerAssert.The<A> assertThat(LBiFloatConsumer func) {
		return new LBiFloatConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiFloatConsumerX<X>, X extends Throwable> LBiFloatConsumerXAssert.The<A, X> assertThat(LBiFloatConsumerX<X> func) {
		return new LBiFloatConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertThat(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiIntConsumerX<X>, X extends Throwable> LBiIntConsumerXAssert.The<A, X> assertThat(LBiIntConsumerX<X> func) {
		return new LBiIntConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertThat(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiLongConsumerX<X>, X extends Throwable> LBiLongConsumerXAssert.The<A, X> assertThat(LBiLongConsumerX<X> func) {
		return new LBiLongConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiShortConsumer> LBiShortConsumerAssert.The<A> assertThat(LBiShortConsumer func) {
		return new LBiShortConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiShortConsumerX<X>, X extends Throwable> LBiShortConsumerXAssert.The<A, X> assertThat(LBiShortConsumerX<X> func) {
		return new LBiShortConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertThat(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBoolConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjBoolConsumerX<T1, T2, X> func) {
		return new LBiObjBoolConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> func) {
		return new LBiObjByteConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> func) {
		return new LBiObjCharConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.The<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> func) {
		return new LBiObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> func) {
		return new LBiObjDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.The<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> func) {
		return new LBiObjFloatConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> func) {
		return new LBiObjFloatConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> func) {
		return new LBiObjIntConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> func) {
		return new LBiObjLongConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.The<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> func) {
		return new LBiObjShortConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> func) {
		return new LBiObjShortConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertThat(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjBoolConsumerX<T, X>, T, X extends Throwable> LObjBoolConsumerXAssert.The<A, T, X> assertThat(LObjBoolConsumerX<T, X> func) {
		return new LObjBoolConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertThat(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.The<A, T, X> assertThat(LObjByteConsumerX<T, X> func) {
		return new LObjByteConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertThat(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.The<A, T, X> assertThat(LObjCharConsumerX<T, X> func) {
		return new LObjCharConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.The<A, T> assertThat(LObjDoubleConsumer<T> func) {
		return new LObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.The<A, T, X> assertThat(LObjDoubleConsumerX<T, X> func) {
		return new LObjDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.The<A, T> assertThat(LObjFloatConsumer<T> func) {
		return new LObjFloatConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.The<A, T, X> assertThat(LObjFloatConsumerX<T, X> func) {
		return new LObjFloatConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertThat(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.The<A, T, X> assertThat(LObjIntConsumerX<T, X> func) {
		return new LObjIntConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertThat(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.The<A, T, X> assertThat(LObjLongConsumerX<T, X> func) {
		return new LObjLongConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.The<A, T> assertThat(LObjShortConsumer<T> func) {
		return new LObjShortConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.The<A, T, X> assertThat(LObjShortConsumerX<T, X> func) {
		return new LObjShortConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertThat(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LTriBoolConsumerX<X>, X extends Throwable> LTriBoolConsumerXAssert.The<A, X> assertThat(LTriBoolConsumerX<X> func) {
		return new LTriBoolConsumerXAssert.The(func);
	}

	@Nonnull
	public static <A extends LBinaryOperator<T>, RS extends Assert<RS, T>, T> LBinaryOperatorAssert.The<A, RS, T> assertThat(LBinaryOperator<T> func) {
		return new LBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBinaryOperatorX<T, X>, RS extends Assert<RS, T>, T, X extends Throwable> LBinaryOperatorXAssert.The<A, RS, T, X> assertThat(LBinaryOperatorX<T, X> func) {
		return new LBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperator, RS extends AbstractByteAssert<RS>> LByteBinaryOperatorAssert.The<A, RS> assertThat(LByteBinaryOperator func) {
		return new LByteBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteBinaryOperatorXAssert.The<A, RS, X> assertThat(LByteBinaryOperatorX<X> func) {
		return new LByteBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperator, RS extends AbstractCharacterAssert<RS>> LCharBinaryOperatorAssert.The<A, RS> assertThat(LCharBinaryOperator func) {
		return new LCharBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharBinaryOperatorXAssert.The<A, RS, X> assertThat(LCharBinaryOperatorX<X> func) {
		return new LCharBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleBinaryOperatorAssert.The<A, RS> assertThat(LDoubleBinaryOperator func) {
		return new LDoubleBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleBinaryOperatorXAssert.The<A, RS, X> assertThat(LDoubleBinaryOperatorX<X> func) {
		return new LDoubleBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperator, RS extends AbstractFloatAssert<RS>> LFloatBinaryOperatorAssert.The<A, RS> assertThat(LFloatBinaryOperator func) {
		return new LFloatBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatBinaryOperatorXAssert.The<A, RS, X> assertThat(LFloatBinaryOperatorX<X> func) {
		return new LFloatBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperator, RS extends AbstractIntegerAssert<RS>> LIntBinaryOperatorAssert.The<A, RS> assertThat(LIntBinaryOperator func) {
		return new LIntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntBinaryOperatorXAssert.The<A, RS, X> assertThat(LIntBinaryOperatorX<X> func) {
		return new LIntBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalBinaryOperatorAssert.The<A, RS> assertThat(LLogicalBinaryOperator func) {
		return new LLogicalBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalBinaryOperatorXAssert.The<A, RS, X> assertThat(LLogicalBinaryOperatorX<X> func) {
		return new LLogicalBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperator, RS extends AbstractLongAssert<RS>> LLongBinaryOperatorAssert.The<A, RS> assertThat(LLongBinaryOperator func) {
		return new LLongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongBinaryOperatorXAssert.The<A, RS, X> assertThat(LLongBinaryOperatorX<X> func) {
		return new LLongBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperator, RS extends AbstractShortAssert<RS>> LShortBinaryOperatorAssert.The<A, RS> assertThat(LShortBinaryOperator func) {
		return new LShortBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortBinaryOperatorXAssert.The<A, RS, X> assertThat(LShortBinaryOperatorX<X> func) {
		return new LShortBinaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalTernaryOperatorAssert.The<A, RS> assertThat(LLogicalTernaryOperator func) {
		return new LLogicalTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalTernaryOperatorXAssert.The<A, RS, X> assertThat(LLogicalTernaryOperatorX<X> func) {
		return new LLogicalTernaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperator<T>, RS extends Assert<RS, T>, T> LTernaryOperatorAssert.The<A, RS, T> assertThat(LTernaryOperator<T> func) {
		return new LTernaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperatorX<T, X>, RS extends Assert<RS, T>, T, X extends Throwable> LTernaryOperatorXAssert.The<A, RS, T, X> assertThat(LTernaryOperatorX<T, X> func) {
		return new LTernaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperator, RS extends AbstractByteAssert<RS>> LByteUnaryOperatorAssert.The<A, RS> assertThat(LByteUnaryOperator func) {
		return new LByteUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteUnaryOperatorXAssert.The<A, RS, X> assertThat(LByteUnaryOperatorX<X> func) {
		return new LByteUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperator, RS extends AbstractCharacterAssert<RS>> LCharUnaryOperatorAssert.The<A, RS> assertThat(LCharUnaryOperator func) {
		return new LCharUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharUnaryOperatorXAssert.The<A, RS, X> assertThat(LCharUnaryOperatorX<X> func) {
		return new LCharUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleUnaryOperatorAssert.The<A, RS> assertThat(LDoubleUnaryOperator func) {
		return new LDoubleUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleUnaryOperatorXAssert.The<A, RS, X> assertThat(LDoubleUnaryOperatorX<X> func) {
		return new LDoubleUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperator, RS extends AbstractFloatAssert<RS>> LFloatUnaryOperatorAssert.The<A, RS> assertThat(LFloatUnaryOperator func) {
		return new LFloatUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatUnaryOperatorXAssert.The<A, RS, X> assertThat(LFloatUnaryOperatorX<X> func) {
		return new LFloatUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperator, RS extends AbstractIntegerAssert<RS>> LIntUnaryOperatorAssert.The<A, RS> assertThat(LIntUnaryOperator func) {
		return new LIntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntUnaryOperatorXAssert.The<A, RS, X> assertThat(LIntUnaryOperatorX<X> func) {
		return new LIntUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperator, RS extends AbstractBooleanAssert<RS>> LLogicalOperatorAssert.The<A, RS> assertThat(LLogicalOperator func) {
		return new LLogicalOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalOperatorXAssert.The<A, RS, X> assertThat(LLogicalOperatorX<X> func) {
		return new LLogicalOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperator, RS extends AbstractLongAssert<RS>> LLongUnaryOperatorAssert.The<A, RS> assertThat(LLongUnaryOperator func) {
		return new LLongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongUnaryOperatorXAssert.The<A, RS, X> assertThat(LLongUnaryOperatorX<X> func) {
		return new LLongUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperator, RS extends AbstractShortAssert<RS>> LShortUnaryOperatorAssert.The<A, RS> assertThat(LShortUnaryOperator func) {
		return new LShortUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortUnaryOperatorXAssert.The<A, RS, X> assertThat(LShortUnaryOperatorX<X> func) {
		return new LShortUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperator<T>, RS extends Assert<RS, T>, T> LUnaryOperatorAssert.The<A, RS, T> assertThat(LUnaryOperator<T> func) {
		return new LUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperatorX<T, X>, RS extends Assert<RS, T>, T, X extends Throwable> LUnaryOperatorXAssert.The<A, RS, T, X> assertThat(LUnaryOperatorX<T, X> func) {
		return new LUnaryOperatorXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> func) {
		return new LBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> func) {
		return new LBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunction<T, R>, RS extends Assert<RS, R>, T, R> LFunctionAssert.The<A, RS, T, R> assertThat(LFunction<T, R> func) {
		return new LFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LFunctionXAssert.The<A, RS, T, R, X> assertThat(LFunctionX<T, R, X> func) {
		return new LFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends Assert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, RS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> func) {
		return new LTriFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunctionX<T1, T2, T3, R, X>, RS extends Assert<RS, R>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.The<A, RS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> func) {
		return new LTriFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunction, RS extends AbstractByteAssert<RS>> LBoolToByteFunctionAssert.The<A, RS> assertThat(LBoolToByteFunction func) {
		return new LBoolToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LBoolToByteFunctionXAssert.The<A, RS, X> assertThat(LBoolToByteFunctionX<X> func) {
		return new LBoolToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunction, RS extends AbstractCharacterAssert<RS>> LBoolToCharFunctionAssert.The<A, RS> assertThat(LBoolToCharFunction func) {
		return new LBoolToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LBoolToCharFunctionXAssert.The<A, RS, X> assertThat(LBoolToCharFunctionX<X> func) {
		return new LBoolToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LBoolToDoubleFunctionAssert.The<A, RS> assertThat(LBoolToDoubleFunction func) {
		return new LBoolToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LBoolToDoubleFunctionXAssert.The<A, RS, X> assertThat(LBoolToDoubleFunctionX<X> func) {
		return new LBoolToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFloatFunction, RS extends AbstractFloatAssert<RS>> LBoolToFloatFunctionAssert.The<A, RS> assertThat(LBoolToFloatFunction func) {
		return new LBoolToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LBoolToFloatFunctionXAssert.The<A, RS, X> assertThat(LBoolToFloatFunctionX<X> func) {
		return new LBoolToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunction, RS extends AbstractIntegerAssert<RS>> LBoolToIntFunctionAssert.The<A, RS> assertThat(LBoolToIntFunction func) {
		return new LBoolToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LBoolToIntFunctionXAssert.The<A, RS, X> assertThat(LBoolToIntFunctionX<X> func) {
		return new LBoolToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunction, RS extends AbstractLongAssert<RS>> LBoolToLongFunctionAssert.The<A, RS> assertThat(LBoolToLongFunction func) {
		return new LBoolToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LBoolToLongFunctionXAssert.The<A, RS, X> assertThat(LBoolToLongFunctionX<X> func) {
		return new LBoolToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToShortFunction, RS extends AbstractShortAssert<RS>> LBoolToShortFunctionAssert.The<A, RS> assertThat(LBoolToShortFunction func) {
		return new LBoolToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LBoolToShortFunctionXAssert.The<A, RS, X> assertThat(LBoolToShortFunctionX<X> func) {
		return new LBoolToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunction, RS extends AbstractCharacterAssert<RS>> LByteToCharFunctionAssert.The<A, RS> assertThat(LByteToCharFunction func) {
		return new LByteToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LByteToCharFunctionXAssert.The<A, RS, X> assertThat(LByteToCharFunctionX<X> func) {
		return new LByteToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LByteToDoubleFunctionAssert.The<A, RS> assertThat(LByteToDoubleFunction func) {
		return new LByteToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LByteToDoubleFunctionXAssert.The<A, RS, X> assertThat(LByteToDoubleFunctionX<X> func) {
		return new LByteToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunction, RS extends AbstractFloatAssert<RS>> LByteToFloatFunctionAssert.The<A, RS> assertThat(LByteToFloatFunction func) {
		return new LByteToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LByteToFloatFunctionXAssert.The<A, RS, X> assertThat(LByteToFloatFunctionX<X> func) {
		return new LByteToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunction, RS extends AbstractIntegerAssert<RS>> LByteToIntFunctionAssert.The<A, RS> assertThat(LByteToIntFunction func) {
		return new LByteToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LByteToIntFunctionXAssert.The<A, RS, X> assertThat(LByteToIntFunctionX<X> func) {
		return new LByteToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunction, RS extends AbstractLongAssert<RS>> LByteToLongFunctionAssert.The<A, RS> assertThat(LByteToLongFunction func) {
		return new LByteToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LByteToLongFunctionXAssert.The<A, RS, X> assertThat(LByteToLongFunctionX<X> func) {
		return new LByteToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunction, RS extends AbstractShortAssert<RS>> LByteToShortFunctionAssert.The<A, RS> assertThat(LByteToShortFunction func) {
		return new LByteToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LByteToShortFunctionXAssert.The<A, RS, X> assertThat(LByteToShortFunctionX<X> func) {
		return new LByteToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunction, RS extends AbstractByteAssert<RS>> LCharToByteFunctionAssert.The<A, RS> assertThat(LCharToByteFunction func) {
		return new LCharToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LCharToByteFunctionXAssert.The<A, RS, X> assertThat(LCharToByteFunctionX<X> func) {
		return new LCharToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LCharToDoubleFunctionAssert.The<A, RS> assertThat(LCharToDoubleFunction func) {
		return new LCharToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LCharToDoubleFunctionXAssert.The<A, RS, X> assertThat(LCharToDoubleFunctionX<X> func) {
		return new LCharToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunction, RS extends AbstractFloatAssert<RS>> LCharToFloatFunctionAssert.The<A, RS> assertThat(LCharToFloatFunction func) {
		return new LCharToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LCharToFloatFunctionXAssert.The<A, RS, X> assertThat(LCharToFloatFunctionX<X> func) {
		return new LCharToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunction, RS extends AbstractIntegerAssert<RS>> LCharToIntFunctionAssert.The<A, RS> assertThat(LCharToIntFunction func) {
		return new LCharToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LCharToIntFunctionXAssert.The<A, RS, X> assertThat(LCharToIntFunctionX<X> func) {
		return new LCharToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunction, RS extends AbstractLongAssert<RS>> LCharToLongFunctionAssert.The<A, RS> assertThat(LCharToLongFunction func) {
		return new LCharToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LCharToLongFunctionXAssert.The<A, RS, X> assertThat(LCharToLongFunctionX<X> func) {
		return new LCharToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunction, RS extends AbstractShortAssert<RS>> LCharToShortFunctionAssert.The<A, RS> assertThat(LCharToShortFunction func) {
		return new LCharToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LCharToShortFunctionXAssert.The<A, RS, X> assertThat(LCharToShortFunctionX<X> func) {
		return new LCharToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunction, RS extends AbstractByteAssert<RS>> LDoubleToByteFunctionAssert.The<A, RS> assertThat(LDoubleToByteFunction func) {
		return new LDoubleToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LDoubleToByteFunctionXAssert.The<A, RS, X> assertThat(LDoubleToByteFunctionX<X> func) {
		return new LDoubleToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunction, RS extends AbstractCharacterAssert<RS>> LDoubleToCharFunctionAssert.The<A, RS> assertThat(LDoubleToCharFunction func) {
		return new LDoubleToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LDoubleToCharFunctionXAssert.The<A, RS, X> assertThat(LDoubleToCharFunctionX<X> func) {
		return new LDoubleToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunction, RS extends AbstractFloatAssert<RS>> LDoubleToFloatFunctionAssert.The<A, RS> assertThat(LDoubleToFloatFunction func) {
		return new LDoubleToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LDoubleToFloatFunctionXAssert.The<A, RS, X> assertThat(LDoubleToFloatFunctionX<X> func) {
		return new LDoubleToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> LDoubleToIntFunctionAssert.The<A, RS> assertThat(LDoubleToIntFunction func) {
		return new LDoubleToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LDoubleToIntFunctionXAssert.The<A, RS, X> assertThat(LDoubleToIntFunctionX<X> func) {
		return new LDoubleToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunction, RS extends AbstractLongAssert<RS>> LDoubleToLongFunctionAssert.The<A, RS> assertThat(LDoubleToLongFunction func) {
		return new LDoubleToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LDoubleToLongFunctionXAssert.The<A, RS, X> assertThat(LDoubleToLongFunctionX<X> func) {
		return new LDoubleToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunction, RS extends AbstractShortAssert<RS>> LDoubleToShortFunctionAssert.The<A, RS> assertThat(LDoubleToShortFunction func) {
		return new LDoubleToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LDoubleToShortFunctionXAssert.The<A, RS, X> assertThat(LDoubleToShortFunctionX<X> func) {
		return new LDoubleToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunction, RS extends AbstractByteAssert<RS>> LFloatToByteFunctionAssert.The<A, RS> assertThat(LFloatToByteFunction func) {
		return new LFloatToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LFloatToByteFunctionXAssert.The<A, RS, X> assertThat(LFloatToByteFunctionX<X> func) {
		return new LFloatToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunction, RS extends AbstractCharacterAssert<RS>> LFloatToCharFunctionAssert.The<A, RS> assertThat(LFloatToCharFunction func) {
		return new LFloatToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LFloatToCharFunctionXAssert.The<A, RS, X> assertThat(LFloatToCharFunctionX<X> func) {
		return new LFloatToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LFloatToDoubleFunctionAssert.The<A, RS> assertThat(LFloatToDoubleFunction func) {
		return new LFloatToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LFloatToDoubleFunctionXAssert.The<A, RS, X> assertThat(LFloatToDoubleFunctionX<X> func) {
		return new LFloatToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunction, RS extends AbstractIntegerAssert<RS>> LFloatToIntFunctionAssert.The<A, RS> assertThat(LFloatToIntFunction func) {
		return new LFloatToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LFloatToIntFunctionXAssert.The<A, RS, X> assertThat(LFloatToIntFunctionX<X> func) {
		return new LFloatToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunction, RS extends AbstractLongAssert<RS>> LFloatToLongFunctionAssert.The<A, RS> assertThat(LFloatToLongFunction func) {
		return new LFloatToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LFloatToLongFunctionXAssert.The<A, RS, X> assertThat(LFloatToLongFunctionX<X> func) {
		return new LFloatToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunction, RS extends AbstractShortAssert<RS>> LFloatToShortFunctionAssert.The<A, RS> assertThat(LFloatToShortFunction func) {
		return new LFloatToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LFloatToShortFunctionXAssert.The<A, RS, X> assertThat(LFloatToShortFunctionX<X> func) {
		return new LFloatToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunction, RS extends AbstractByteAssert<RS>> LIntToByteFunctionAssert.The<A, RS> assertThat(LIntToByteFunction func) {
		return new LIntToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LIntToByteFunctionXAssert.The<A, RS, X> assertThat(LIntToByteFunctionX<X> func) {
		return new LIntToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunction, RS extends AbstractCharacterAssert<RS>> LIntToCharFunctionAssert.The<A, RS> assertThat(LIntToCharFunction func) {
		return new LIntToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LIntToCharFunctionXAssert.The<A, RS, X> assertThat(LIntToCharFunctionX<X> func) {
		return new LIntToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LIntToDoubleFunctionAssert.The<A, RS> assertThat(LIntToDoubleFunction func) {
		return new LIntToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LIntToDoubleFunctionXAssert.The<A, RS, X> assertThat(LIntToDoubleFunctionX<X> func) {
		return new LIntToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunction, RS extends AbstractFloatAssert<RS>> LIntToFloatFunctionAssert.The<A, RS> assertThat(LIntToFloatFunction func) {
		return new LIntToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LIntToFloatFunctionXAssert.The<A, RS, X> assertThat(LIntToFloatFunctionX<X> func) {
		return new LIntToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunction, RS extends AbstractLongAssert<RS>> LIntToLongFunctionAssert.The<A, RS> assertThat(LIntToLongFunction func) {
		return new LIntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LIntToLongFunctionXAssert.The<A, RS, X> assertThat(LIntToLongFunctionX<X> func) {
		return new LIntToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunction, RS extends AbstractShortAssert<RS>> LIntToShortFunctionAssert.The<A, RS> assertThat(LIntToShortFunction func) {
		return new LIntToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LIntToShortFunctionXAssert.The<A, RS, X> assertThat(LIntToShortFunctionX<X> func) {
		return new LIntToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunction, RS extends AbstractByteAssert<RS>> LLongToByteFunctionAssert.The<A, RS> assertThat(LLongToByteFunction func) {
		return new LLongToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LLongToByteFunctionXAssert.The<A, RS, X> assertThat(LLongToByteFunctionX<X> func) {
		return new LLongToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunction, RS extends AbstractCharacterAssert<RS>> LLongToCharFunctionAssert.The<A, RS> assertThat(LLongToCharFunction func) {
		return new LLongToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LLongToCharFunctionXAssert.The<A, RS, X> assertThat(LLongToCharFunctionX<X> func) {
		return new LLongToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LLongToDoubleFunctionAssert.The<A, RS> assertThat(LLongToDoubleFunction func) {
		return new LLongToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LLongToDoubleFunctionXAssert.The<A, RS, X> assertThat(LLongToDoubleFunctionX<X> func) {
		return new LLongToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunction, RS extends AbstractFloatAssert<RS>> LLongToFloatFunctionAssert.The<A, RS> assertThat(LLongToFloatFunction func) {
		return new LLongToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LLongToFloatFunctionXAssert.The<A, RS, X> assertThat(LLongToFloatFunctionX<X> func) {
		return new LLongToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunction, RS extends AbstractIntegerAssert<RS>> LLongToIntFunctionAssert.The<A, RS> assertThat(LLongToIntFunction func) {
		return new LLongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LLongToIntFunctionXAssert.The<A, RS, X> assertThat(LLongToIntFunctionX<X> func) {
		return new LLongToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunction, RS extends AbstractShortAssert<RS>> LLongToShortFunctionAssert.The<A, RS> assertThat(LLongToShortFunction func) {
		return new LLongToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LLongToShortFunctionXAssert.The<A, RS, X> assertThat(LLongToShortFunctionX<X> func) {
		return new LLongToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunction, RS extends AbstractByteAssert<RS>> LShortToByteFunctionAssert.The<A, RS> assertThat(LShortToByteFunction func) {
		return new LShortToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LShortToByteFunctionXAssert.The<A, RS, X> assertThat(LShortToByteFunctionX<X> func) {
		return new LShortToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>> LShortToCharFunctionAssert.The<A, RS> assertThat(LShortToCharFunction func) {
		return new LShortToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LShortToCharFunctionXAssert.The<A, RS, X> assertThat(LShortToCharFunctionX<X> func) {
		return new LShortToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LShortToDoubleFunctionAssert.The<A, RS> assertThat(LShortToDoubleFunction func) {
		return new LShortToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LShortToDoubleFunctionXAssert.The<A, RS, X> assertThat(LShortToDoubleFunctionX<X> func) {
		return new LShortToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunction, RS extends AbstractFloatAssert<RS>> LShortToFloatFunctionAssert.The<A, RS> assertThat(LShortToFloatFunction func) {
		return new LShortToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LShortToFloatFunctionXAssert.The<A, RS, X> assertThat(LShortToFloatFunctionX<X> func) {
		return new LShortToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunction, RS extends AbstractIntegerAssert<RS>> LShortToIntFunctionAssert.The<A, RS> assertThat(LShortToIntFunction func) {
		return new LShortToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LShortToIntFunctionXAssert.The<A, RS, X> assertThat(LShortToIntFunctionX<X> func) {
		return new LShortToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunction, RS extends AbstractLongAssert<RS>> LShortToLongFunctionAssert.The<A, RS> assertThat(LShortToLongFunction func) {
		return new LShortToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LShortToLongFunctionXAssert.The<A, RS, X> assertThat(LShortToLongFunctionX<X> func) {
		return new LShortToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction<R>, RS extends Assert<RS, R>, R> LBiBoolFunctionAssert.The<A, RS, R> assertThat(LBiBoolFunction<R> func) {
		return new LBiBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiBoolFunctionXAssert.The<A, RS, R, X> assertThat(LBiBoolFunctionX<R, X> func) {
		return new LBiBoolFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction<R>, RS extends Assert<RS, R>, R> LBiByteFunctionAssert.The<A, RS, R> assertThat(LBiByteFunction<R> func) {
		return new LBiByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiByteFunctionXAssert.The<A, RS, R, X> assertThat(LBiByteFunctionX<R, X> func) {
		return new LBiByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction<R>, RS extends Assert<RS, R>, R> LBiCharFunctionAssert.The<A, RS, R> assertThat(LBiCharFunction<R> func) {
		return new LBiCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiCharFunctionXAssert.The<A, RS, R, X> assertThat(LBiCharFunctionX<R, X> func) {
		return new LBiCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoubleFunction<R>, RS extends Assert<RS, R>, R> LBiDoubleFunctionAssert.The<A, RS, R> assertThat(LBiDoubleFunction<R> func) {
		return new LBiDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoubleFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiDoubleFunctionXAssert.The<A, RS, R, X> assertThat(LBiDoubleFunctionX<R, X> func) {
		return new LBiDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatFunction<R>, RS extends Assert<RS, R>, R> LBiFloatFunctionAssert.The<A, RS, R> assertThat(LBiFloatFunction<R> func) {
		return new LBiFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiFloatFunctionXAssert.The<A, RS, R, X> assertThat(LBiFloatFunctionX<R, X> func) {
		return new LBiFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction<R>, RS extends Assert<RS, R>, R> LBiIntFunctionAssert.The<A, RS, R> assertThat(LBiIntFunction<R> func) {
		return new LBiIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiIntFunctionXAssert.The<A, RS, R, X> assertThat(LBiIntFunctionX<R, X> func) {
		return new LBiIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction<R>, RS extends Assert<RS, R>, R> LBiLongFunctionAssert.The<A, RS, R> assertThat(LBiLongFunction<R> func) {
		return new LBiLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiLongFunctionXAssert.The<A, RS, R, X> assertThat(LBiLongFunctionX<R, X> func) {
		return new LBiLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjBoolFunction<T1, T2, R> func) {
		return new LBiObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjBoolFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjBoolFunctionX<T1, T2, R, X> func) {
		return new LBiObjBoolFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> func) {
		return new LBiObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> func) {
		return new LBiObjByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> func) {
		return new LBiObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> func) {
		return new LBiObjCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjDoubleFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> func) {
		return new LBiObjDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjDoubleFunctionX<T1, T2, R, X> func) {
		return new LBiObjDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjFloatFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> func) {
		return new LBiObjFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> func) {
		return new LBiObjFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> func) {
		return new LBiObjIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> func) {
		return new LBiObjIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> func) {
		return new LBiObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> func) {
		return new LBiObjLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> LBiObjShortFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> func) {
		return new LBiObjShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunctionX<T1, T2, R, X>, RS extends Assert<RS, R>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> func) {
		return new LBiObjShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortFunction<R>, RS extends Assert<RS, R>, R> LBiShortFunctionAssert.The<A, RS, R> assertThat(LBiShortFunction<R> func) {
		return new LBiShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBiShortFunctionXAssert.The<A, RS, R, X> assertThat(LBiShortFunctionX<R, X> func) {
		return new LBiShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunction<R>, RS extends Assert<RS, R>, R> LBoolFunctionAssert.The<A, RS, R> assertThat(LBoolFunction<R> func) {
		return new LBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LBoolFunctionXAssert.The<A, RS, R, X> assertThat(LBoolFunctionX<R, X> func) {
		return new LBoolFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunction<R>, RS extends Assert<RS, R>, R> LByteFunctionAssert.The<A, RS, R> assertThat(LByteFunction<R> func) {
		return new LByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LByteFunctionXAssert.The<A, RS, R, X> assertThat(LByteFunctionX<R, X> func) {
		return new LByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunction<R>, RS extends Assert<RS, R>, R> LCharFunctionAssert.The<A, RS, R> assertThat(LCharFunction<R> func) {
		return new LCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LCharFunctionXAssert.The<A, RS, R, X> assertThat(LCharFunctionX<R, X> func) {
		return new LCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunction<R>, RS extends Assert<RS, R>, R> LDoubleFunctionAssert.The<A, RS, R> assertThat(LDoubleFunction<R> func) {
		return new LDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LDoubleFunctionXAssert.The<A, RS, R, X> assertThat(LDoubleFunctionX<R, X> func) {
		return new LDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunction<R>, RS extends Assert<RS, R>, R> LFloatFunctionAssert.The<A, RS, R> assertThat(LFloatFunction<R> func) {
		return new LFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LFloatFunctionXAssert.The<A, RS, R, X> assertThat(LFloatFunctionX<R, X> func) {
		return new LFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunction<R>, RS extends Assert<RS, R>, R> LIntFunctionAssert.The<A, RS, R> assertThat(LIntFunction<R> func) {
		return new LIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LIntFunctionXAssert.The<A, RS, R, X> assertThat(LIntFunctionX<R, X> func) {
		return new LIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunction<R>, RS extends Assert<RS, R>, R> LLongFunctionAssert.The<A, RS, R> assertThat(LLongFunction<R> func) {
		return new LLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LLongFunctionXAssert.The<A, RS, R, X> assertThat(LLongFunctionX<R, X> func) {
		return new LLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjBoolFunctionAssert.The<A, RS, T, R> assertThat(LObjBoolFunction<T, R> func) {
		return new LObjBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjBoolFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjBoolFunctionX<T, R, X> func) {
		return new LObjBoolFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjByteFunctionAssert.The<A, RS, T, R> assertThat(LObjByteFunction<T, R> func) {
		return new LObjByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjByteFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> func) {
		return new LObjByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjCharFunctionAssert.The<A, RS, T, R> assertThat(LObjCharFunction<T, R> func) {
		return new LObjCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjCharFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> func) {
		return new LObjCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjDoubleFunctionAssert.The<A, RS, T, R> assertThat(LObjDoubleFunction<T, R> func) {
		return new LObjDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> func) {
		return new LObjDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjFloatFunctionAssert.The<A, RS, T, R> assertThat(LObjFloatFunction<T, R> func) {
		return new LObjFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjFloatFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> func) {
		return new LObjFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjIntFunctionAssert.The<A, RS, T, R> assertThat(LObjIntFunction<T, R> func) {
		return new LObjIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjIntFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> func) {
		return new LObjIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjLongFunctionAssert.The<A, RS, T, R> assertThat(LObjLongFunction<T, R> func) {
		return new LObjLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjLongFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> func) {
		return new LObjLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunction<T, R>, RS extends Assert<RS, R>, T, R> LObjShortFunctionAssert.The<A, RS, T, R> assertThat(LObjShortFunction<T, R> func) {
		return new LObjShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunctionX<T, R, X>, RS extends Assert<RS, R>, T, R, X extends Throwable> LObjShortFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> func) {
		return new LObjShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunction<R>, RS extends Assert<RS, R>, R> LShortFunctionAssert.The<A, RS, R> assertThat(LShortFunction<R> func) {
		return new LShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LShortFunctionXAssert.The<A, RS, R, X> assertThat(LShortFunctionX<R, X> func) {
		return new LShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunction<R>, RS extends Assert<RS, R>, R> LTriBoolFunctionAssert.The<A, RS, R> assertThat(LTriBoolFunction<R> func) {
		return new LTriBoolFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunctionX<R, X>, RS extends Assert<RS, R>, R, X extends Throwable> LTriBoolFunctionXAssert.The<A, RS, R, X> assertThat(LTriBoolFunctionX<R, X> func) {
		return new LTriBoolFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LObjIntToIntFunctionAssert.The<A, RS, T> assertThat(LObjIntToIntFunction<T> func) {
		return new LObjIntToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LObjIntToIntFunctionXAssert.The<A, RS, T, X> assertThat(LObjIntToIntFunctionX<T, X> func) {
		return new LObjIntToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToByteBiFunction<T1, T2> func) {
		return new LToByteBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunctionX<T1, T2, X>, RS extends AbstractByteAssert<RS>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> func) {
		return new LToByteBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LToByteFunctionAssert.The<A, RS, T> assertThat(LToByteFunction<T> func) {
		return new LToByteFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Throwable> LToByteFunctionXAssert.The<A, RS, T, X> assertThat(LToByteFunctionX<T, X> func) {
		return new LToByteFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> LToCharBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToCharBiFunction<T1, T2> func) {
		return new LToCharBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunctionX<T1, T2, X>, RS extends AbstractCharacterAssert<RS>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> func) {
		return new LToCharBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.The<A, RS, T> assertThat(LToCharFunction<T> func) {
		return new LToCharFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunctionX<T, X>, RS extends AbstractCharacterAssert<RS>, T, X extends Throwable> LToCharFunctionXAssert.The<A, RS, T, X> assertThat(LToCharFunctionX<T, X> func) {
		return new LToCharFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDoubleBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> func) {
		return new LToDoubleBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunctionX<T1, T2, X>, RS extends AbstractDoubleAssert<RS>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> func) {
		return new LToDoubleBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LToDoubleFunctionAssert.The<A, RS, T> assertThat(LToDoubleFunction<T> func) {
		return new LToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunctionX<T, X>, RS extends AbstractDoubleAssert<RS>, T, X extends Throwable> LToDoubleFunctionXAssert.The<A, RS, T, X> assertThat(LToDoubleFunctionX<T, X> func) {
		return new LToDoubleFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> LToFloatBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToFloatBiFunction<T1, T2> func) {
		return new LToFloatBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunctionX<T1, T2, X>, RS extends AbstractFloatAssert<RS>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> func) {
		return new LToFloatBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFloatFunctionAssert.The<A, RS, T> assertThat(LToFloatFunction<T> func) {
		return new LToFloatFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Throwable> LToFloatFunctionXAssert.The<A, RS, T, X> assertThat(LToFloatFunctionX<T, X> func) {
		return new LToFloatFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToIntBiFunction<T1, T2> func) {
		return new LToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunctionX<T1, T2, X>, RS extends AbstractIntegerAssert<RS>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> func) {
		return new LToIntBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LToIntFunctionAssert.The<A, RS, T> assertThat(LToIntFunction<T> func) {
		return new LToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LToIntFunctionXAssert.The<A, RS, T, X> assertThat(LToIntFunctionX<T, X> func) {
		return new LToIntFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToLongBiFunction<T1, T2> func) {
		return new LToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunctionX<T1, T2, X>, RS extends AbstractLongAssert<RS>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> func) {
		return new LToLongBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LToLongFunctionAssert.The<A, RS, T> assertThat(LToLongFunction<T> func) {
		return new LToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunctionX<T, X>, RS extends AbstractLongAssert<RS>, T, X extends Throwable> LToLongFunctionXAssert.The<A, RS, T, X> assertThat(LToLongFunctionX<T, X> func) {
		return new LToLongFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> LToShortBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToShortBiFunction<T1, T2> func) {
		return new LToShortBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunctionX<T1, T2, X>, RS extends AbstractShortAssert<RS>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> func) {
		return new LToShortBiFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunction<T>, RS extends AbstractShortAssert<RS>, T> LToShortFunctionAssert.The<A, RS, T> assertThat(LToShortFunction<T> func) {
		return new LToShortFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunctionX<T, X>, RS extends AbstractShortAssert<RS>, T, X extends Throwable> LToShortFunctionXAssert.The<A, RS, T, X> assertThat(LToShortFunctionX<T, X> func) {
		return new LToShortFunctionXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.The<A, RS> assertThat(LBiBytePredicate func) {
		return new LBiBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiBytePredicateXAssert.The<A, RS, X> assertThat(LBiBytePredicateX<X> func) {
		return new LBiBytePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.The<A, RS> assertThat(LBiCharPredicate func) {
		return new LBiCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiCharPredicateXAssert.The<A, RS, X> assertThat(LBiCharPredicateX<X> func) {
		return new LBiCharPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicate, RS extends AbstractBooleanAssert<RS>> LBiDoublePredicateAssert.The<A, RS> assertThat(LBiDoublePredicate func) {
		return new LBiDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiDoublePredicateXAssert.The<A, RS, X> assertThat(LBiDoublePredicateX<X> func) {
		return new LBiDoublePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicate, RS extends AbstractBooleanAssert<RS>> LBiFloatPredicateAssert.The<A, RS> assertThat(LBiFloatPredicate func) {
		return new LBiFloatPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiFloatPredicateXAssert.The<A, RS, X> assertThat(LBiFloatPredicateX<X> func) {
		return new LBiFloatPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.The<A, RS> assertThat(LBiIntPredicate func) {
		return new LBiIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiIntPredicateXAssert.The<A, RS, X> assertThat(LBiIntPredicateX<X> func) {
		return new LBiIntPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.The<A, RS> assertThat(LBiLongPredicate func) {
		return new LBiLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiLongPredicateXAssert.The<A, RS, X> assertThat(LBiLongPredicateX<X> func) {
		return new LBiLongPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBoolPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjBoolPredicate<T1, T2> func) {
		return new LBiObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBoolPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjBoolPredicateX<T1, T2, X> func) {
		return new LBiObjBoolPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> func) {
		return new LBiObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> func) {
		return new LBiObjBytePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> func) {
		return new LBiObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> func) {
		return new LBiObjCharPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDoublePredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> func) {
		return new LBiObjDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> func) {
		return new LBiObjDoublePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFloatPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> func) {
		return new LBiObjFloatPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> func) {
		return new LBiObjFloatPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> func) {
		return new LBiObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> func) {
		return new LBiObjIntPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> func) {
		return new LBiObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> func) {
		return new LBiObjLongPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjShortPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> func) {
		return new LBiObjShortPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> func) {
		return new LBiObjShortPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> assertThat(LBiPredicate<T1, T2> func) {
		return new LBiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> func) {
		return new LBiPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicate, RS extends AbstractBooleanAssert<RS>> LBiShortPredicateAssert.The<A, RS> assertThat(LBiShortPredicate func) {
		return new LBiShortPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiShortPredicateXAssert.The<A, RS, X> assertThat(LBiShortPredicateX<X> func) {
		return new LBiShortPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicate, RS extends AbstractBooleanAssert<RS>> LBytePredicateAssert.The<A, RS> assertThat(LBytePredicate func) {
		return new LBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBytePredicateXAssert.The<A, RS, X> assertThat(LBytePredicateX<X> func) {
		return new LBytePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.The<A, RS> assertThat(LCharPredicate func) {
		return new LCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LCharPredicateXAssert.The<A, RS, X> assertThat(LCharPredicateX<X> func) {
		return new LCharPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicate, RS extends AbstractBooleanAssert<RS>> LDoublePredicateAssert.The<A, RS> assertThat(LDoublePredicate func) {
		return new LDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LDoublePredicateXAssert.The<A, RS, X> assertThat(LDoublePredicateX<X> func) {
		return new LDoublePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicate, RS extends AbstractBooleanAssert<RS>> LFloatPredicateAssert.The<A, RS> assertThat(LFloatPredicate func) {
		return new LFloatPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LFloatPredicateXAssert.The<A, RS, X> assertThat(LFloatPredicateX<X> func) {
		return new LFloatPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicate, RS extends AbstractBooleanAssert<RS>> LIntPredicateAssert.The<A, RS> assertThat(LIntPredicate func) {
		return new LIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LIntPredicateXAssert.The<A, RS, X> assertThat(LIntPredicateX<X> func) {
		return new LIntPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.The<A, RS> assertThat(LLongPredicate func) {
		return new LLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLongPredicateXAssert.The<A, RS, X> assertThat(LLongPredicateX<X> func) {
		return new LLongPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolPredicateAssert.The<A, RS, T> assertThat(LObjBoolPredicate<T> func) {
		return new LObjBoolPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBoolPredicateXAssert.The<A, RS, T, X> assertThat(LObjBoolPredicateX<T, X> func) {
		return new LObjBoolPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.The<A, RS, T> assertThat(LObjBytePredicate<T> func) {
		return new LObjBytePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBytePredicateXAssert.The<A, RS, T, X> assertThat(LObjBytePredicateX<T, X> func) {
		return new LObjBytePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.The<A, RS, T> assertThat(LObjCharPredicate<T> func) {
		return new LObjCharPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjCharPredicateXAssert.The<A, RS, T, X> assertThat(LObjCharPredicateX<T, X> func) {
		return new LObjCharPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDoublePredicateAssert.The<A, RS, T> assertThat(LObjDoublePredicate<T> func) {
		return new LObjDoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjDoublePredicateXAssert.The<A, RS, T, X> assertThat(LObjDoublePredicateX<T, X> func) {
		return new LObjDoublePredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFloatPredicateAssert.The<A, RS, T> assertThat(LObjFloatPredicate<T> func) {
		return new LObjFloatPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjFloatPredicateXAssert.The<A, RS, T, X> assertThat(LObjFloatPredicateX<T, X> func) {
		return new LObjFloatPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.The<A, RS, T> assertThat(LObjIntPredicate<T> func) {
		return new LObjIntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjIntPredicateXAssert.The<A, RS, T, X> assertThat(LObjIntPredicateX<T, X> func) {
		return new LObjIntPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.The<A, RS, T> assertThat(LObjLongPredicate<T> func) {
		return new LObjLongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjLongPredicateXAssert.The<A, RS, T, X> assertThat(LObjLongPredicateX<T, X> func) {
		return new LObjLongPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjShortPredicateAssert.The<A, RS, T> assertThat(LObjShortPredicate<T> func) {
		return new LObjShortPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjShortPredicateXAssert.The<A, RS, T, X> assertThat(LObjShortPredicateX<T, X> func) {
		return new LObjShortPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LPredicateAssert.The<A, RS, T> assertThat(LPredicate<T> func) {
		return new LPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LPredicateXAssert.The<A, RS, T, X> assertThat(LPredicateX<T, X> func) {
		return new LPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicate, RS extends AbstractBooleanAssert<RS>> LShortPredicateAssert.The<A, RS> assertThat(LShortPredicate func) {
		return new LShortPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LShortPredicateXAssert.The<A, RS, X> assertThat(LShortPredicateX<X> func) {
		return new LShortPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.The<A, RS, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> func) {
		return new LTriPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicateX<T1, T2, T3, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.The<A, RS, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> func) {
		return new LTriPredicateXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplier, RS extends AbstractBooleanAssert<RS>> LBoolSupplierAssert.The<A, RS> assertThat(LBoolSupplier func) {
		return new LBoolSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplierX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBoolSupplierXAssert.The<A, RS, X> assertThat(LBoolSupplierX<X> func) {
		return new LBoolSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplier, RS extends AbstractByteAssert<RS>> LByteSupplierAssert.The<A, RS> assertThat(LByteSupplier func) {
		return new LByteSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplierX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteSupplierXAssert.The<A, RS, X> assertThat(LByteSupplierX<X> func) {
		return new LByteSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplier, RS extends AbstractCharacterAssert<RS>> LCharSupplierAssert.The<A, RS> assertThat(LCharSupplier func) {
		return new LCharSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplierX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharSupplierXAssert.The<A, RS, X> assertThat(LCharSupplierX<X> func) {
		return new LCharSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplier, RS extends AbstractDoubleAssert<RS>> LDoubleSupplierAssert.The<A, RS> assertThat(LDoubleSupplier func) {
		return new LDoubleSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplierX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleSupplierXAssert.The<A, RS, X> assertThat(LDoubleSupplierX<X> func) {
		return new LDoubleSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplier, RS extends AbstractFloatAssert<RS>> LFloatSupplierAssert.The<A, RS> assertThat(LFloatSupplier func) {
		return new LFloatSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplierX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatSupplierXAssert.The<A, RS, X> assertThat(LFloatSupplierX<X> func) {
		return new LFloatSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplier, RS extends AbstractIntegerAssert<RS>> LIntSupplierAssert.The<A, RS> assertThat(LIntSupplier func) {
		return new LIntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplierX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntSupplierXAssert.The<A, RS, X> assertThat(LIntSupplierX<X> func) {
		return new LIntSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplier, RS extends AbstractLongAssert<RS>> LLongSupplierAssert.The<A, RS> assertThat(LLongSupplier func) {
		return new LLongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplierX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongSupplierXAssert.The<A, RS, X> assertThat(LLongSupplierX<X> func) {
		return new LLongSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplier, RS extends AbstractShortAssert<RS>> LShortSupplierAssert.The<A, RS> assertThat(LShortSupplier func) {
		return new LShortSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplierX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortSupplierXAssert.The<A, RS, X> assertThat(LShortSupplierX<X> func) {
		return new LShortSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplier<T>, RS extends Assert<RS, T>, T> LSupplierAssert.The<A, RS, T> assertThat(LSupplier<T> func) {
		return new LSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplierX<T, X>, RS extends Assert<RS, T>, T, X extends Throwable> LSupplierXAssert.The<A, RS, T, X> assertThat(LSupplierX<T, X> func) {
		return new LSupplierXAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Runnable> RunnableAssert.The<A> assertThat(Runnable func) {
		return new RunnableAssert.The(func);
	}

	@Nonnull
	public static <A extends BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.The<A, T1, T2> assertThat(BiConsumer<T1, T2> func) {
		return new BiConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends Consumer<T>, T> ConsumerAssert.The<A, T> assertThat(Consumer<T> func) {
		return new ConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends DoubleConsumer> DoubleConsumerAssert.The<A> assertThat(DoubleConsumer func) {
		return new DoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends IntConsumer> IntConsumerAssert.The<A> assertThat(IntConsumer func) {
		return new IntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends LongConsumer> LongConsumerAssert.The<A> assertThat(LongConsumer func) {
		return new LongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.The<A, T> assertThat(ObjDoubleConsumer<T> func) {
		return new ObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjIntConsumer<T>, T> ObjIntConsumerAssert.The<A, T> assertThat(ObjIntConsumer<T> func) {
		return new ObjIntConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends ObjLongConsumer<T>, T> ObjLongConsumerAssert.The<A, T> assertThat(ObjLongConsumer<T> func) {
		return new ObjLongConsumerAssert.The(func);
	}

	@Nonnull
	public static <A extends BinaryOperator<T>, RS extends Assert<RS, T>, T> BinaryOperatorAssert.The<A, RS, T> assertThat(BinaryOperator<T> func) {
		return new BinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleBinaryOperatorAssert.The<A, RS> assertThat(DoubleBinaryOperator func) {
		return new DoubleBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleUnaryOperatorAssert.The<A, RS> assertThat(DoubleUnaryOperator func) {
		return new DoubleUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> IntBinaryOperatorAssert.The<A, RS> assertThat(IntBinaryOperator func) {
		return new IntBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> IntUnaryOperatorAssert.The<A, RS> assertThat(IntUnaryOperator func) {
		return new IntUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBinaryOperator, RS extends AbstractLongAssert<RS>> LongBinaryOperatorAssert.The<A, RS> assertThat(LongBinaryOperator func) {
		return new LongBinaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongUnaryOperator, RS extends AbstractLongAssert<RS>> LongUnaryOperatorAssert.The<A, RS> assertThat(LongUnaryOperator func) {
		return new LongUnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends UnaryOperator<T>, RS extends Assert<RS, T>, T> UnaryOperatorAssert.The<A, RS, T> assertThat(UnaryOperator<T> func) {
		return new UnaryOperatorAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFunction<T1, T2, R>, RS extends Assert<RS, R>, T1, T2, R> BiFunctionAssert.The<A, RS, T1, T2, R> assertThat(BiFunction<T1, T2, R> func) {
		return new BiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleFunction<R>, RS extends Assert<RS, R>, R> DoubleFunctionAssert.The<A, RS, R> assertThat(DoubleFunction<R> func) {
		return new DoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> DoubleToIntFunctionAssert.The<A, RS> assertThat(DoubleToIntFunction func) {
		return new DoubleToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToLongFunction, RS extends AbstractLongAssert<RS>> DoubleToLongFunctionAssert.The<A, RS> assertThat(DoubleToLongFunction func) {
		return new DoubleToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Function<T, R>, RS extends Assert<RS, R>, T, R> FunctionAssert.The<A, RS, T, R> assertThat(Function<T, R> func) {
		return new FunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntFunction<R>, RS extends Assert<RS, R>, R> IntFunctionAssert.The<A, RS, R> assertThat(IntFunction<R> func) {
		return new IntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> IntToDoubleFunctionAssert.The<A, RS> assertThat(IntToDoubleFunction func) {
		return new IntToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToLongFunction, RS extends AbstractLongAssert<RS>> IntToLongFunctionAssert.The<A, RS> assertThat(IntToLongFunction func) {
		return new IntToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongFunction<R>, RS extends Assert<RS, R>, R> LongFunctionAssert.The<A, RS, R> assertThat(LongFunction<R> func) {
		return new LongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LongToDoubleFunctionAssert.The<A, RS> assertThat(LongToDoubleFunction func) {
		return new LongToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToIntFunction, RS extends AbstractIntegerAssert<RS>> LongToIntFunctionAssert.The<A, RS> assertThat(LongToIntFunction func) {
		return new LongToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> ToDoubleBiFunctionAssert.The<A, RS, T1, T2> assertThat(ToDoubleBiFunction<T1, T2> func) {
		return new ToDoubleBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> ToDoubleFunctionAssert.The<A, RS, T> assertThat(ToDoubleFunction<T> func) {
		return new ToDoubleFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> ToIntBiFunctionAssert.The<A, RS, T1, T2> assertThat(ToIntBiFunction<T1, T2> func) {
		return new ToIntBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> ToIntFunctionAssert.The<A, RS, T> assertThat(ToIntFunction<T> func) {
		return new ToIntFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> ToLongBiFunctionAssert.The<A, RS, T1, T2> assertThat(ToLongBiFunction<T1, T2> func) {
		return new ToLongBiFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> ToLongFunctionAssert.The<A, RS, T> assertThat(ToLongFunction<T> func) {
		return new ToLongFunctionAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiPredicateAssert.The<A, RS, T1, T2> assertThat(BiPredicate<T1, T2> func) {
		return new BiPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoublePredicate, RS extends AbstractBooleanAssert<RS>> DoublePredicateAssert.The<A, RS> assertThat(DoublePredicate func) {
		return new DoublePredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntPredicate, RS extends AbstractBooleanAssert<RS>> IntPredicateAssert.The<A, RS> assertThat(IntPredicate func) {
		return new IntPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongPredicate, RS extends AbstractBooleanAssert<RS>> LongPredicateAssert.The<A, RS> assertThat(LongPredicate func) {
		return new LongPredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> PredicateAssert.The<A, RS, T> assertThat(Predicate<T> func) {
		return new PredicateAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> BooleanSupplierAssert.The<A, RS> assertThat(BooleanSupplier func) {
		return new BooleanSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleSupplier, RS extends AbstractDoubleAssert<RS>> DoubleSupplierAssert.The<A, RS> assertThat(DoubleSupplier func) {
		return new DoubleSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntSupplier, RS extends AbstractIntegerAssert<RS>> IntSupplierAssert.The<A, RS> assertThat(IntSupplier func) {
		return new IntSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongSupplier, RS extends AbstractLongAssert<RS>> LongSupplierAssert.The<A, RS> assertThat(LongSupplier func) {
		return new LongSupplierAssert.The(func, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Supplier<T>, RS extends Assert<RS, T>, T> SupplierAssert.The<A, RS, T> assertThat(Supplier<T> func) {
		return new SupplierAssert.The(func, Assertions::assertThat);
	}

}