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

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR

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
	public static <A extends LActionX<X>, X extends Throwable> LActionXAssert.The<A, X> assertThat(LActionX<X> functionalInterface) {
		return new LActionXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LAction> LActionAssert.The<A> assertThat(LAction functionalInterface) {
		return new LActionAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.The<A, T, X> assertThat(LObjByteConsumerX<T, X> functionalInterface) {
		return new LObjByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertThat(LObjByteConsumer<T> functionalInterface) {
		return new LObjByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.The<A, T, X> assertThat(LObjShortConsumerX<T, X> functionalInterface) {
		return new LObjShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.The<A, T> assertThat(LObjShortConsumer<T> functionalInterface) {
		return new LObjShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.The<A, T, X> assertThat(LObjIntConsumerX<T, X> functionalInterface) {
		return new LObjIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertThat(LObjIntConsumer<T> functionalInterface) {
		return new LObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.The<A, T, X> assertThat(LObjLongConsumerX<T, X> functionalInterface) {
		return new LObjLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertThat(LObjLongConsumer<T> functionalInterface) {
		return new LObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.The<A, T, X> assertThat(LObjFloatConsumerX<T, X> functionalInterface) {
		return new LObjFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.The<A, T> assertThat(LObjFloatConsumer<T> functionalInterface) {
		return new LObjFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.The<A, T, X> assertThat(LObjDoubleConsumerX<T, X> functionalInterface) {
		return new LObjDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.The<A, T> assertThat(LObjDoubleConsumer<T> functionalInterface) {
		return new LObjDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.The<A, T, X> assertThat(LObjCharConsumerX<T, X> functionalInterface) {
		return new LObjCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertThat(LObjCharConsumer<T> functionalInterface) {
		return new LObjCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjBoolConsumerX<T, X>, T, X extends Throwable> LObjBoolConsumerXAssert.The<A, T, X> assertThat(LObjBoolConsumerX<T, X> functionalInterface) {
		return new LObjBoolConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertThat(LObjBoolConsumer<T> functionalInterface) {
		return new LObjBoolConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> functionalInterface) {
		return new LBiObjByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.The<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> functionalInterface) {
		return new LBiObjShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> functionalInterface) {
		return new LBiObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> functionalInterface) {
		return new LBiObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.The<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> functionalInterface) {
		return new LBiObjFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.The<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> functionalInterface) {
		return new LBiObjDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> functionalInterface) {
		return new LBiObjCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBoolConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjBoolConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjBoolConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertThat(LBiObjBoolConsumer<T1, T2> functionalInterface) {
		return new LBiObjBoolConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiByteConsumerX<X>, X extends Throwable> LBiByteConsumerXAssert.The<A, X> assertThat(LBiByteConsumerX<X> functionalInterface) {
		return new LBiByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertThat(LBiByteConsumer functionalInterface) {
		return new LBiByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiShortConsumerX<X>, X extends Throwable> LBiShortConsumerXAssert.The<A, X> assertThat(LBiShortConsumerX<X> functionalInterface) {
		return new LBiShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiShortConsumer> LBiShortConsumerAssert.The<A> assertThat(LBiShortConsumer functionalInterface) {
		return new LBiShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiIntConsumerX<X>, X extends Throwable> LBiIntConsumerXAssert.The<A, X> assertThat(LBiIntConsumerX<X> functionalInterface) {
		return new LBiIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertThat(LBiIntConsumer functionalInterface) {
		return new LBiIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiLongConsumerX<X>, X extends Throwable> LBiLongConsumerXAssert.The<A, X> assertThat(LBiLongConsumerX<X> functionalInterface) {
		return new LBiLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertThat(LBiLongConsumer functionalInterface) {
		return new LBiLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiFloatConsumerX<X>, X extends Throwable> LBiFloatConsumerXAssert.The<A, X> assertThat(LBiFloatConsumerX<X> functionalInterface) {
		return new LBiFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiFloatConsumer> LBiFloatConsumerAssert.The<A> assertThat(LBiFloatConsumer functionalInterface) {
		return new LBiFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiDoubleConsumerX<X>, X extends Throwable> LBiDoubleConsumerXAssert.The<A, X> assertThat(LBiDoubleConsumerX<X> functionalInterface) {
		return new LBiDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiDoubleConsumer> LBiDoubleConsumerAssert.The<A> assertThat(LBiDoubleConsumer functionalInterface) {
		return new LBiDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiCharConsumerX<X>, X extends Throwable> LBiCharConsumerXAssert.The<A, X> assertThat(LBiCharConsumerX<X> functionalInterface) {
		return new LBiCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertThat(LBiCharConsumer functionalInterface) {
		return new LBiCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiBoolConsumerX<X>, X extends Throwable> LBiBoolConsumerXAssert.The<A, X> assertThat(LBiBoolConsumerX<X> functionalInterface) {
		return new LBiBoolConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> assertThat(LBiBoolConsumer functionalInterface) {
		return new LBiBoolConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriBoolConsumerX<X>, X extends Throwable> LTriBoolConsumerXAssert.The<A, X> assertThat(LTriBoolConsumerX<X> functionalInterface) {
		return new LTriBoolConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertThat(LTriBoolConsumer functionalInterface) {
		return new LTriBoolConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.The<A, X> assertThat(LByteConsumerX<X> functionalInterface) {
		return new LByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteConsumer> LByteConsumerAssert.The<A> assertThat(LByteConsumer functionalInterface) {
		return new LByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.The<A, X> assertThat(LShortConsumerX<X> functionalInterface) {
		return new LShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortConsumer> LShortConsumerAssert.The<A> assertThat(LShortConsumer functionalInterface) {
		return new LShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.The<A, X> assertThat(LIntConsumerX<X> functionalInterface) {
		return new LIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntConsumer> LIntConsumerAssert.The<A> assertThat(LIntConsumer functionalInterface) {
		return new LIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.The<A, X> assertThat(LLongConsumerX<X> functionalInterface) {
		return new LLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongConsumer> LLongConsumerAssert.The<A> assertThat(LLongConsumer functionalInterface) {
		return new LLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.The<A, X> assertThat(LFloatConsumerX<X> functionalInterface) {
		return new LFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatConsumer> LFloatConsumerAssert.The<A> assertThat(LFloatConsumer functionalInterface) {
		return new LFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.The<A, X> assertThat(LDoubleConsumerX<X> functionalInterface) {
		return new LDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleConsumer> LDoubleConsumerAssert.The<A> assertThat(LDoubleConsumer functionalInterface) {
		return new LDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.The<A, X> assertThat(LCharConsumerX<X> functionalInterface) {
		return new LCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharConsumer> LCharConsumerAssert.The<A> assertThat(LCharConsumer functionalInterface) {
		return new LCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBoolConsumerX<X>, X extends Throwable> LBoolConsumerXAssert.The<A, X> assertThat(LBoolConsumerX<X> functionalInterface) {
		return new LBoolConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBoolConsumer> LBoolConsumerAssert.The<A> assertThat(LBoolConsumer functionalInterface) {
		return new LBoolConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.The<A, T, X> assertThat(LConsumerX<T, X> functionalInterface) {
		return new LConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertThat(LConsumer<T> functionalInterface) {
		return new LConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.The<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> functionalInterface) {
		return new LBiConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertThat(LBiConsumer<T1, T2> functionalInterface) {
		return new LBiConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.The<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> functionalInterface) {
		return new LTriConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> functionalInterface) {
		return new LTriConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteUnaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteUnaryOperatorXAssert.The<A, RS, X> assertThat(LByteUnaryOperatorX<X> functionalInterface) {
		return new LByteUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperator, RS extends AbstractByteAssert<RS>> LByteUnaryOperatorAssert.The<A, RS> assertThat(LByteUnaryOperator functionalInterface) {
		return new LByteUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteBinaryOperatorXAssert.The<A, RS, X> assertThat(LByteBinaryOperatorX<X> functionalInterface) {
		return new LByteBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperator, RS extends AbstractByteAssert<RS>> LByteBinaryOperatorAssert.The<A, RS> assertThat(LByteBinaryOperator functionalInterface) {
		return new LByteBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortUnaryOperatorXAssert.The<A, RS, X> assertThat(LShortUnaryOperatorX<X> functionalInterface) {
		return new LShortUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperator, RS extends AbstractShortAssert<RS>> LShortUnaryOperatorAssert.The<A, RS> assertThat(LShortUnaryOperator functionalInterface) {
		return new LShortUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortBinaryOperatorXAssert.The<A, RS, X> assertThat(LShortBinaryOperatorX<X> functionalInterface) {
		return new LShortBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperator, RS extends AbstractShortAssert<RS>> LShortBinaryOperatorAssert.The<A, RS> assertThat(LShortBinaryOperator functionalInterface) {
		return new LShortBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntUnaryOperatorXAssert.The<A, RS, X> assertThat(LIntUnaryOperatorX<X> functionalInterface) {
		return new LIntUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperator, RS extends AbstractIntegerAssert<RS>> LIntUnaryOperatorAssert.The<A, RS> assertThat(LIntUnaryOperator functionalInterface) {
		return new LIntUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntBinaryOperatorXAssert.The<A, RS, X> assertThat(LIntBinaryOperatorX<X> functionalInterface) {
		return new LIntBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperator, RS extends AbstractIntegerAssert<RS>> LIntBinaryOperatorAssert.The<A, RS> assertThat(LIntBinaryOperator functionalInterface) {
		return new LIntBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongUnaryOperatorXAssert.The<A, RS, X> assertThat(LLongUnaryOperatorX<X> functionalInterface) {
		return new LLongUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperator, RS extends AbstractLongAssert<RS>> LLongUnaryOperatorAssert.The<A, RS> assertThat(LLongUnaryOperator functionalInterface) {
		return new LLongUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongBinaryOperatorXAssert.The<A, RS, X> assertThat(LLongBinaryOperatorX<X> functionalInterface) {
		return new LLongBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperator, RS extends AbstractLongAssert<RS>> LLongBinaryOperatorAssert.The<A, RS> assertThat(LLongBinaryOperator functionalInterface) {
		return new LLongBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatUnaryOperatorXAssert.The<A, RS, X> assertThat(LFloatUnaryOperatorX<X> functionalInterface) {
		return new LFloatUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperator, RS extends AbstractFloatAssert<RS>> LFloatUnaryOperatorAssert.The<A, RS> assertThat(LFloatUnaryOperator functionalInterface) {
		return new LFloatUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatBinaryOperatorXAssert.The<A, RS, X> assertThat(LFloatBinaryOperatorX<X> functionalInterface) {
		return new LFloatBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperator, RS extends AbstractFloatAssert<RS>> LFloatBinaryOperatorAssert.The<A, RS> assertThat(LFloatBinaryOperator functionalInterface) {
		return new LFloatBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleUnaryOperatorXAssert.The<A, RS, X> assertThat(LDoubleUnaryOperatorX<X> functionalInterface) {
		return new LDoubleUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleUnaryOperatorAssert.The<A, RS> assertThat(LDoubleUnaryOperator functionalInterface) {
		return new LDoubleUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleBinaryOperatorXAssert.The<A, RS, X> assertThat(LDoubleBinaryOperatorX<X> functionalInterface) {
		return new LDoubleBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleBinaryOperatorAssert.The<A, RS> assertThat(LDoubleBinaryOperator functionalInterface) {
		return new LDoubleBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharUnaryOperatorXAssert.The<A, RS, X> assertThat(LCharUnaryOperatorX<X> functionalInterface) {
		return new LCharUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperator, RS extends AbstractCharacterAssert<RS>> LCharUnaryOperatorAssert.The<A, RS> assertThat(LCharUnaryOperator functionalInterface) {
		return new LCharUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharBinaryOperatorXAssert.The<A, RS, X> assertThat(LCharBinaryOperatorX<X> functionalInterface) {
		return new LCharBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperator, RS extends AbstractCharacterAssert<RS>> LCharBinaryOperatorAssert.The<A, RS> assertThat(LCharBinaryOperator functionalInterface) {
		return new LCharBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalOperatorXAssert.The<A, RS, X> assertThat(LLogicalOperatorX<X> functionalInterface) {
		return new LLogicalOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperator, RS extends AbstractBooleanAssert<RS>> LLogicalOperatorAssert.The<A, RS> assertThat(LLogicalOperator functionalInterface) {
		return new LLogicalOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalBinaryOperatorXAssert.The<A, RS, X> assertThat(LLogicalBinaryOperatorX<X> functionalInterface) {
		return new LLogicalBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalBinaryOperatorAssert.The<A, RS> assertThat(LLogicalBinaryOperator functionalInterface) {
		return new LLogicalBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalTernaryOperatorXAssert.The<A, RS, X> assertThat(LLogicalTernaryOperatorX<X> functionalInterface) {
		return new LLogicalTernaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalTernaryOperatorAssert.The<A, RS> assertThat(LLogicalTernaryOperator functionalInterface) {
		return new LLogicalTernaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LUnaryOperatorXAssert.The<A, RS, T, X> assertThat(LUnaryOperatorX<T, X> functionalInterface) {
		return new LUnaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LUnaryOperatorAssert.The<A, RS, T> assertThat(LUnaryOperator<T> functionalInterface) {
		return new LUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBinaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LBinaryOperatorXAssert.The<A, RS, T, X> assertThat(LBinaryOperatorX<T, X> functionalInterface) {
		return new LBinaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LBinaryOperatorAssert.The<A, RS, T> assertThat(LBinaryOperator<T> functionalInterface) {
		return new LBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LTernaryOperatorXAssert.The<A, RS, T, X> assertThat(LTernaryOperatorX<T, X> functionalInterface) {
		return new LTernaryOperatorXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LTernaryOperatorAssert.The<A, RS, T> assertThat(LTernaryOperator<T> functionalInterface) {
		return new LTernaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LFunctionXAssert.The<A, RS, T, R, X> assertThat(LFunctionX<T, R, X> functionalInterface) {
		return new LFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LFunctionAssert.The<A, RS, T, R> assertThat(LFunction<T, R> functionalInterface) {
		return new LFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> functionalInterface) {
		return new LBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunctionX<T1, T2, T3, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.The<A, RS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> functionalInterface) {
		return new LTriFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, RS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> functionalInterface) {
		return new LTriFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LByteFunctionXAssert.The<A, RS, R, X> assertThat(LByteFunctionX<R, X> functionalInterface) {
		return new LByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LByteFunctionAssert.The<A, RS, R> assertThat(LByteFunction<R> functionalInterface) {
		return new LByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiByteFunctionXAssert.The<A, RS, R, X> assertThat(LBiByteFunctionX<R, X> functionalInterface) {
		return new LBiByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiByteFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiByteFunctionAssert.The<A, RS, R> assertThat(LBiByteFunction<R> functionalInterface) {
		return new LBiByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjByteFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> functionalInterface) {
		return new LObjByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjByteFunctionAssert.The<A, RS, T, R> assertThat(LObjByteFunction<T, R> functionalInterface) {
		return new LObjByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> functionalInterface) {
		return new LBiObjByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LShortFunctionXAssert.The<A, RS, R, X> assertThat(LShortFunctionX<R, X> functionalInterface) {
		return new LShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LShortFunctionAssert.The<A, RS, R> assertThat(LShortFunction<R> functionalInterface) {
		return new LShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiShortFunctionXAssert.The<A, RS, R, X> assertThat(LBiShortFunctionX<R, X> functionalInterface) {
		return new LBiShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiShortFunctionAssert.The<A, RS, R> assertThat(LBiShortFunction<R> functionalInterface) {
		return new LBiShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjShortFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> functionalInterface) {
		return new LObjShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjShortFunctionAssert.The<A, RS, T, R> assertThat(LObjShortFunction<T, R> functionalInterface) {
		return new LObjShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjShortFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> functionalInterface) {
		return new LBiObjShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LIntFunctionXAssert.The<A, RS, R, X> assertThat(LIntFunctionX<R, X> functionalInterface) {
		return new LIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LIntFunctionAssert.The<A, RS, R> assertThat(LIntFunction<R> functionalInterface) {
		return new LIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiIntFunctionXAssert.The<A, RS, R, X> assertThat(LBiIntFunctionX<R, X> functionalInterface) {
		return new LBiIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiIntFunctionAssert.The<A, RS, R> assertThat(LBiIntFunction<R> functionalInterface) {
		return new LBiIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjIntFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> functionalInterface) {
		return new LObjIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjIntFunctionAssert.The<A, RS, T, R> assertThat(LObjIntFunction<T, R> functionalInterface) {
		return new LObjIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> functionalInterface) {
		return new LBiObjIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LLongFunctionXAssert.The<A, RS, R, X> assertThat(LLongFunctionX<R, X> functionalInterface) {
		return new LLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LLongFunctionAssert.The<A, RS, R> assertThat(LLongFunction<R> functionalInterface) {
		return new LLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiLongFunctionXAssert.The<A, RS, R, X> assertThat(LBiLongFunctionX<R, X> functionalInterface) {
		return new LBiLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiLongFunctionAssert.The<A, RS, R> assertThat(LBiLongFunction<R> functionalInterface) {
		return new LBiLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjLongFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> functionalInterface) {
		return new LObjLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjLongFunctionAssert.The<A, RS, T, R> assertThat(LObjLongFunction<T, R> functionalInterface) {
		return new LObjLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> functionalInterface) {
		return new LBiObjLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LFloatFunctionXAssert.The<A, RS, R, X> assertThat(LFloatFunctionX<R, X> functionalInterface) {
		return new LFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LFloatFunctionAssert.The<A, RS, R> assertThat(LFloatFunction<R> functionalInterface) {
		return new LFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiFloatFunctionXAssert.The<A, RS, R, X> assertThat(LBiFloatFunctionX<R, X> functionalInterface) {
		return new LBiFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiFloatFunctionAssert.The<A, RS, R> assertThat(LBiFloatFunction<R> functionalInterface) {
		return new LBiFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjFloatFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> functionalInterface) {
		return new LObjFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjFloatFunctionAssert.The<A, RS, T, R> assertThat(LObjFloatFunction<T, R> functionalInterface) {
		return new LObjFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjFloatFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> functionalInterface) {
		return new LBiObjFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LDoubleFunctionXAssert.The<A, RS, R, X> assertThat(LDoubleFunctionX<R, X> functionalInterface) {
		return new LDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LDoubleFunctionAssert.The<A, RS, R> assertThat(LDoubleFunction<R> functionalInterface) {
		return new LDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoubleFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiDoubleFunctionXAssert.The<A, RS, R, X> assertThat(LBiDoubleFunctionX<R, X> functionalInterface) {
		return new LBiDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiDoubleFunctionAssert.The<A, RS, R> assertThat(LBiDoubleFunction<R> functionalInterface) {
		return new LBiDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> functionalInterface) {
		return new LObjDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjDoubleFunctionAssert.The<A, RS, T, R> assertThat(LObjDoubleFunction<T, R> functionalInterface) {
		return new LObjDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(
			LBiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjDoubleFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> functionalInterface) {
		return new LBiObjDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LCharFunctionXAssert.The<A, RS, R, X> assertThat(LCharFunctionX<R, X> functionalInterface) {
		return new LCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LCharFunctionAssert.The<A, RS, R> assertThat(LCharFunction<R> functionalInterface) {
		return new LCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiCharFunctionXAssert.The<A, RS, R, X> assertThat(LBiCharFunctionX<R, X> functionalInterface) {
		return new LBiCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiCharFunctionAssert.The<A, RS, R> assertThat(LBiCharFunction<R> functionalInterface) {
		return new LBiCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjCharFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> functionalInterface) {
		return new LObjCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjCharFunctionAssert.The<A, RS, T, R> assertThat(LObjCharFunction<T, R> functionalInterface) {
		return new LObjCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> functionalInterface) {
		return new LBiObjCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBoolFunctionXAssert.The<A, RS, R, X> assertThat(LBoolFunctionX<R, X> functionalInterface) {
		return new LBoolFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBoolFunctionAssert.The<A, RS, R> assertThat(LBoolFunction<R> functionalInterface) {
		return new LBoolFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBiBoolFunctionXAssert.The<A, RS, R, X> assertThat(LBiBoolFunctionX<R, X> functionalInterface) {
		return new LBiBoolFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBoolFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBiBoolFunctionAssert.The<A, RS, R> assertThat(LBiBoolFunction<R> functionalInterface) {
		return new LBiBoolFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LTriBoolFunctionXAssert.The<A, RS, R, X> assertThat(LTriBoolFunctionX<R, X> functionalInterface) {
		return new LTriBoolFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriBoolFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LTriBoolFunctionAssert.The<A, RS, R> assertThat(LTriBoolFunction<R> functionalInterface) {
		return new LTriBoolFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjBoolFunctionXAssert.The<A, RS, T, R, X> assertThat(LObjBoolFunctionX<T, R, X> functionalInterface) {
		return new LObjBoolFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjBoolFunctionAssert.The<A, RS, T, R> assertThat(LObjBoolFunction<T, R> functionalInterface) {
		return new LObjBoolFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjBoolFunctionXAssert.The<A, RS, T1, T2, R, X> assertThat(LBiObjBoolFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjBoolFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, RS, T1, T2, R> assertThat(LBiObjBoolFunction<T1, T2, R> functionalInterface) {
		return new LBiObjBoolFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Throwable> LToByteFunctionXAssert.The<A, RS, T, X> assertThat(LToByteFunctionX<T, X> functionalInterface) {
		return new LToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LToByteFunctionAssert.The<A, RS, T> assertThat(LToByteFunction<T> functionalInterface) {
		return new LToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunctionX<T1, T2, X>, RS extends AbstractByteAssert<RS>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToByteBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToByteBiFunction<T1, T2> functionalInterface) {
		return new LToByteBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunctionX<T, X>, RS extends AbstractShortAssert<RS>, T, X extends Throwable> LToShortFunctionXAssert.The<A, RS, T, X> assertThat(LToShortFunctionX<T, X> functionalInterface) {
		return new LToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunction<T>, RS extends AbstractShortAssert<RS>, T> LToShortFunctionAssert.The<A, RS, T> assertThat(LToShortFunction<T> functionalInterface) {
		return new LToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunctionX<T1, T2, X>, RS extends AbstractShortAssert<RS>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToShortBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> LToShortBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToShortBiFunction<T1, T2> functionalInterface) {
		return new LToShortBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LToIntFunctionXAssert.The<A, RS, T, X> assertThat(LToIntFunctionX<T, X> functionalInterface) {
		return new LToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LToIntFunctionAssert.The<A, RS, T> assertThat(LToIntFunction<T> functionalInterface) {
		return new LToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunctionX<T1, T2, X>, RS extends AbstractIntegerAssert<RS>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToIntBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToIntBiFunction<T1, T2> functionalInterface) {
		return new LToIntBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LObjIntToIntFunctionXAssert.The<A, RS, T, X> assertThat(LObjIntToIntFunctionX<T, X> functionalInterface) {
		return new LObjIntToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LObjIntToIntFunctionAssert.The<A, RS, T> assertThat(LObjIntToIntFunction<T> functionalInterface) {
		return new LObjIntToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunctionX<T, X>, RS extends AbstractLongAssert<RS>, T, X extends Throwable> LToLongFunctionXAssert.The<A, RS, T, X> assertThat(LToLongFunctionX<T, X> functionalInterface) {
		return new LToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LToLongFunctionAssert.The<A, RS, T> assertThat(LToLongFunction<T> functionalInterface) {
		return new LToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunctionX<T1, T2, X>, RS extends AbstractLongAssert<RS>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToLongBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToLongBiFunction<T1, T2> functionalInterface) {
		return new LToLongBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Throwable> LToFloatFunctionXAssert.The<A, RS, T, X> assertThat(LToFloatFunctionX<T, X> functionalInterface) {
		return new LToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFloatFunctionAssert.The<A, RS, T> assertThat(LToFloatFunction<T> functionalInterface) {
		return new LToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunctionX<T1, T2, X>, RS extends AbstractFloatAssert<RS>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToFloatBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> LToFloatBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToFloatBiFunction<T1, T2> functionalInterface) {
		return new LToFloatBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunctionX<T, X>, RS extends AbstractDoubleAssert<RS>, T, X extends Throwable> LToDoubleFunctionXAssert.The<A, RS, T, X> assertThat(LToDoubleFunctionX<T, X> functionalInterface) {
		return new LToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LToDoubleFunctionAssert.The<A, RS, T> assertThat(LToDoubleFunction<T> functionalInterface) {
		return new LToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunctionX<T1, T2, X>, RS extends AbstractDoubleAssert<RS>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToDoubleBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDoubleBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> functionalInterface) {
		return new LToDoubleBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunctionX<T, X>, RS extends AbstractCharacterAssert<RS>, T, X extends Throwable> LToCharFunctionXAssert.The<A, RS, T, X> assertThat(LToCharFunctionX<T, X> functionalInterface) {
		return new LToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.The<A, RS, T> assertThat(LToCharFunction<T> functionalInterface) {
		return new LToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunctionX<T1, T2, X>, RS extends AbstractCharacterAssert<RS>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.The<A, RS, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToCharBiFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> LToCharBiFunctionAssert.The<A, RS, T1, T2> assertThat(LToCharBiFunction<T1, T2> functionalInterface) {
		return new LToCharBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LShortToByteFunctionXAssert.The<A, RS, X> assertThat(LShortToByteFunctionX<X> functionalInterface) {
		return new LShortToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunction, RS extends AbstractByteAssert<RS>> LShortToByteFunctionAssert.The<A, RS> assertThat(LShortToByteFunction functionalInterface) {
		return new LShortToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LIntToByteFunctionXAssert.The<A, RS, X> assertThat(LIntToByteFunctionX<X> functionalInterface) {
		return new LIntToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunction, RS extends AbstractByteAssert<RS>> LIntToByteFunctionAssert.The<A, RS> assertThat(LIntToByteFunction functionalInterface) {
		return new LIntToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LLongToByteFunctionXAssert.The<A, RS, X> assertThat(LLongToByteFunctionX<X> functionalInterface) {
		return new LLongToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunction, RS extends AbstractByteAssert<RS>> LLongToByteFunctionAssert.The<A, RS> assertThat(LLongToByteFunction functionalInterface) {
		return new LLongToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LFloatToByteFunctionXAssert.The<A, RS, X> assertThat(LFloatToByteFunctionX<X> functionalInterface) {
		return new LFloatToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunction, RS extends AbstractByteAssert<RS>> LFloatToByteFunctionAssert.The<A, RS> assertThat(LFloatToByteFunction functionalInterface) {
		return new LFloatToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LDoubleToByteFunctionXAssert.The<A, RS, X> assertThat(LDoubleToByteFunctionX<X> functionalInterface) {
		return new LDoubleToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunction, RS extends AbstractByteAssert<RS>> LDoubleToByteFunctionAssert.The<A, RS> assertThat(LDoubleToByteFunction functionalInterface) {
		return new LDoubleToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LCharToByteFunctionXAssert.The<A, RS, X> assertThat(LCharToByteFunctionX<X> functionalInterface) {
		return new LCharToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunction, RS extends AbstractByteAssert<RS>> LCharToByteFunctionAssert.The<A, RS> assertThat(LCharToByteFunction functionalInterface) {
		return new LCharToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LBoolToByteFunctionXAssert.The<A, RS, X> assertThat(LBoolToByteFunctionX<X> functionalInterface) {
		return new LBoolToByteFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToByteFunction, RS extends AbstractByteAssert<RS>> LBoolToByteFunctionAssert.The<A, RS> assertThat(LBoolToByteFunction functionalInterface) {
		return new LBoolToByteFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LByteToShortFunctionXAssert.The<A, RS, X> assertThat(LByteToShortFunctionX<X> functionalInterface) {
		return new LByteToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunction, RS extends AbstractShortAssert<RS>> LByteToShortFunctionAssert.The<A, RS> assertThat(LByteToShortFunction functionalInterface) {
		return new LByteToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LIntToShortFunctionXAssert.The<A, RS, X> assertThat(LIntToShortFunctionX<X> functionalInterface) {
		return new LIntToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunction, RS extends AbstractShortAssert<RS>> LIntToShortFunctionAssert.The<A, RS> assertThat(LIntToShortFunction functionalInterface) {
		return new LIntToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LLongToShortFunctionXAssert.The<A, RS, X> assertThat(LLongToShortFunctionX<X> functionalInterface) {
		return new LLongToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunction, RS extends AbstractShortAssert<RS>> LLongToShortFunctionAssert.The<A, RS> assertThat(LLongToShortFunction functionalInterface) {
		return new LLongToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LFloatToShortFunctionXAssert.The<A, RS, X> assertThat(LFloatToShortFunctionX<X> functionalInterface) {
		return new LFloatToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunction, RS extends AbstractShortAssert<RS>> LFloatToShortFunctionAssert.The<A, RS> assertThat(LFloatToShortFunction functionalInterface) {
		return new LFloatToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LDoubleToShortFunctionXAssert.The<A, RS, X> assertThat(LDoubleToShortFunctionX<X> functionalInterface) {
		return new LDoubleToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunction, RS extends AbstractShortAssert<RS>> LDoubleToShortFunctionAssert.The<A, RS> assertThat(LDoubleToShortFunction functionalInterface) {
		return new LDoubleToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LCharToShortFunctionXAssert.The<A, RS, X> assertThat(LCharToShortFunctionX<X> functionalInterface) {
		return new LCharToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunction, RS extends AbstractShortAssert<RS>> LCharToShortFunctionAssert.The<A, RS> assertThat(LCharToShortFunction functionalInterface) {
		return new LCharToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LBoolToShortFunctionXAssert.The<A, RS, X> assertThat(LBoolToShortFunctionX<X> functionalInterface) {
		return new LBoolToShortFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToShortFunction, RS extends AbstractShortAssert<RS>> LBoolToShortFunctionAssert.The<A, RS> assertThat(LBoolToShortFunction functionalInterface) {
		return new LBoolToShortFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LByteToIntFunctionXAssert.The<A, RS, X> assertThat(LByteToIntFunctionX<X> functionalInterface) {
		return new LByteToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunction, RS extends AbstractIntegerAssert<RS>> LByteToIntFunctionAssert.The<A, RS> assertThat(LByteToIntFunction functionalInterface) {
		return new LByteToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LShortToIntFunctionXAssert.The<A, RS, X> assertThat(LShortToIntFunctionX<X> functionalInterface) {
		return new LShortToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunction, RS extends AbstractIntegerAssert<RS>> LShortToIntFunctionAssert.The<A, RS> assertThat(LShortToIntFunction functionalInterface) {
		return new LShortToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LLongToIntFunctionXAssert.The<A, RS, X> assertThat(LLongToIntFunctionX<X> functionalInterface) {
		return new LLongToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunction, RS extends AbstractIntegerAssert<RS>> LLongToIntFunctionAssert.The<A, RS> assertThat(LLongToIntFunction functionalInterface) {
		return new LLongToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LFloatToIntFunctionXAssert.The<A, RS, X> assertThat(LFloatToIntFunctionX<X> functionalInterface) {
		return new LFloatToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunction, RS extends AbstractIntegerAssert<RS>> LFloatToIntFunctionAssert.The<A, RS> assertThat(LFloatToIntFunction functionalInterface) {
		return new LFloatToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LDoubleToIntFunctionXAssert.The<A, RS, X> assertThat(LDoubleToIntFunctionX<X> functionalInterface) {
		return new LDoubleToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> LDoubleToIntFunctionAssert.The<A, RS> assertThat(LDoubleToIntFunction functionalInterface) {
		return new LDoubleToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LCharToIntFunctionXAssert.The<A, RS, X> assertThat(LCharToIntFunctionX<X> functionalInterface) {
		return new LCharToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunction, RS extends AbstractIntegerAssert<RS>> LCharToIntFunctionAssert.The<A, RS> assertThat(LCharToIntFunction functionalInterface) {
		return new LCharToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LBoolToIntFunctionXAssert.The<A, RS, X> assertThat(LBoolToIntFunctionX<X> functionalInterface) {
		return new LBoolToIntFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToIntFunction, RS extends AbstractIntegerAssert<RS>> LBoolToIntFunctionAssert.The<A, RS> assertThat(LBoolToIntFunction functionalInterface) {
		return new LBoolToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LByteToLongFunctionXAssert.The<A, RS, X> assertThat(LByteToLongFunctionX<X> functionalInterface) {
		return new LByteToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunction, RS extends AbstractLongAssert<RS>> LByteToLongFunctionAssert.The<A, RS> assertThat(LByteToLongFunction functionalInterface) {
		return new LByteToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LShortToLongFunctionXAssert.The<A, RS, X> assertThat(LShortToLongFunctionX<X> functionalInterface) {
		return new LShortToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunction, RS extends AbstractLongAssert<RS>> LShortToLongFunctionAssert.The<A, RS> assertThat(LShortToLongFunction functionalInterface) {
		return new LShortToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LIntToLongFunctionXAssert.The<A, RS, X> assertThat(LIntToLongFunctionX<X> functionalInterface) {
		return new LIntToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunction, RS extends AbstractLongAssert<RS>> LIntToLongFunctionAssert.The<A, RS> assertThat(LIntToLongFunction functionalInterface) {
		return new LIntToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LFloatToLongFunctionXAssert.The<A, RS, X> assertThat(LFloatToLongFunctionX<X> functionalInterface) {
		return new LFloatToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunction, RS extends AbstractLongAssert<RS>> LFloatToLongFunctionAssert.The<A, RS> assertThat(LFloatToLongFunction functionalInterface) {
		return new LFloatToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LDoubleToLongFunctionXAssert.The<A, RS, X> assertThat(LDoubleToLongFunctionX<X> functionalInterface) {
		return new LDoubleToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunction, RS extends AbstractLongAssert<RS>> LDoubleToLongFunctionAssert.The<A, RS> assertThat(LDoubleToLongFunction functionalInterface) {
		return new LDoubleToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LCharToLongFunctionXAssert.The<A, RS, X> assertThat(LCharToLongFunctionX<X> functionalInterface) {
		return new LCharToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunction, RS extends AbstractLongAssert<RS>> LCharToLongFunctionAssert.The<A, RS> assertThat(LCharToLongFunction functionalInterface) {
		return new LCharToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LBoolToLongFunctionXAssert.The<A, RS, X> assertThat(LBoolToLongFunctionX<X> functionalInterface) {
		return new LBoolToLongFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToLongFunction, RS extends AbstractLongAssert<RS>> LBoolToLongFunctionAssert.The<A, RS> assertThat(LBoolToLongFunction functionalInterface) {
		return new LBoolToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LByteToFloatFunctionXAssert.The<A, RS, X> assertThat(LByteToFloatFunctionX<X> functionalInterface) {
		return new LByteToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunction, RS extends AbstractFloatAssert<RS>> LByteToFloatFunctionAssert.The<A, RS> assertThat(LByteToFloatFunction functionalInterface) {
		return new LByteToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LShortToFloatFunctionXAssert.The<A, RS, X> assertThat(LShortToFloatFunctionX<X> functionalInterface) {
		return new LShortToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunction, RS extends AbstractFloatAssert<RS>> LShortToFloatFunctionAssert.The<A, RS> assertThat(LShortToFloatFunction functionalInterface) {
		return new LShortToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LIntToFloatFunctionXAssert.The<A, RS, X> assertThat(LIntToFloatFunctionX<X> functionalInterface) {
		return new LIntToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunction, RS extends AbstractFloatAssert<RS>> LIntToFloatFunctionAssert.The<A, RS> assertThat(LIntToFloatFunction functionalInterface) {
		return new LIntToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LLongToFloatFunctionXAssert.The<A, RS, X> assertThat(LLongToFloatFunctionX<X> functionalInterface) {
		return new LLongToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunction, RS extends AbstractFloatAssert<RS>> LLongToFloatFunctionAssert.The<A, RS> assertThat(LLongToFloatFunction functionalInterface) {
		return new LLongToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LDoubleToFloatFunctionXAssert.The<A, RS, X> assertThat(LDoubleToFloatFunctionX<X> functionalInterface) {
		return new LDoubleToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunction, RS extends AbstractFloatAssert<RS>> LDoubleToFloatFunctionAssert.The<A, RS> assertThat(LDoubleToFloatFunction functionalInterface) {
		return new LDoubleToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LCharToFloatFunctionXAssert.The<A, RS, X> assertThat(LCharToFloatFunctionX<X> functionalInterface) {
		return new LCharToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunction, RS extends AbstractFloatAssert<RS>> LCharToFloatFunctionAssert.The<A, RS> assertThat(LCharToFloatFunction functionalInterface) {
		return new LCharToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LBoolToFloatFunctionXAssert.The<A, RS, X> assertThat(LBoolToFloatFunctionX<X> functionalInterface) {
		return new LBoolToFloatFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToFloatFunction, RS extends AbstractFloatAssert<RS>> LBoolToFloatFunctionAssert.The<A, RS> assertThat(LBoolToFloatFunction functionalInterface) {
		return new LBoolToFloatFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LByteToDoubleFunctionXAssert.The<A, RS, X> assertThat(LByteToDoubleFunctionX<X> functionalInterface) {
		return new LByteToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LByteToDoubleFunctionAssert.The<A, RS> assertThat(LByteToDoubleFunction functionalInterface) {
		return new LByteToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LShortToDoubleFunctionXAssert.The<A, RS, X> assertThat(LShortToDoubleFunctionX<X> functionalInterface) {
		return new LShortToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LShortToDoubleFunctionAssert.The<A, RS> assertThat(LShortToDoubleFunction functionalInterface) {
		return new LShortToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LIntToDoubleFunctionXAssert.The<A, RS, X> assertThat(LIntToDoubleFunctionX<X> functionalInterface) {
		return new LIntToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LIntToDoubleFunctionAssert.The<A, RS> assertThat(LIntToDoubleFunction functionalInterface) {
		return new LIntToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LLongToDoubleFunctionXAssert.The<A, RS, X> assertThat(LLongToDoubleFunctionX<X> functionalInterface) {
		return new LLongToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LLongToDoubleFunctionAssert.The<A, RS> assertThat(LLongToDoubleFunction functionalInterface) {
		return new LLongToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LFloatToDoubleFunctionXAssert.The<A, RS, X> assertThat(LFloatToDoubleFunctionX<X> functionalInterface) {
		return new LFloatToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LFloatToDoubleFunctionAssert.The<A, RS> assertThat(LFloatToDoubleFunction functionalInterface) {
		return new LFloatToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LCharToDoubleFunctionXAssert.The<A, RS, X> assertThat(LCharToDoubleFunctionX<X> functionalInterface) {
		return new LCharToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LCharToDoubleFunctionAssert.The<A, RS> assertThat(LCharToDoubleFunction functionalInterface) {
		return new LCharToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LBoolToDoubleFunctionXAssert.The<A, RS, X> assertThat(LBoolToDoubleFunctionX<X> functionalInterface) {
		return new LBoolToDoubleFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LBoolToDoubleFunctionAssert.The<A, RS> assertThat(LBoolToDoubleFunction functionalInterface) {
		return new LBoolToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LByteToCharFunctionXAssert.The<A, RS, X> assertThat(LByteToCharFunctionX<X> functionalInterface) {
		return new LByteToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunction, RS extends AbstractCharacterAssert<RS>> LByteToCharFunctionAssert.The<A, RS> assertThat(LByteToCharFunction functionalInterface) {
		return new LByteToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LShortToCharFunctionXAssert.The<A, RS, X> assertThat(LShortToCharFunctionX<X> functionalInterface) {
		return new LShortToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>> LShortToCharFunctionAssert.The<A, RS> assertThat(LShortToCharFunction functionalInterface) {
		return new LShortToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LIntToCharFunctionXAssert.The<A, RS, X> assertThat(LIntToCharFunctionX<X> functionalInterface) {
		return new LIntToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunction, RS extends AbstractCharacterAssert<RS>> LIntToCharFunctionAssert.The<A, RS> assertThat(LIntToCharFunction functionalInterface) {
		return new LIntToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LLongToCharFunctionXAssert.The<A, RS, X> assertThat(LLongToCharFunctionX<X> functionalInterface) {
		return new LLongToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunction, RS extends AbstractCharacterAssert<RS>> LLongToCharFunctionAssert.The<A, RS> assertThat(LLongToCharFunction functionalInterface) {
		return new LLongToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LFloatToCharFunctionXAssert.The<A, RS, X> assertThat(LFloatToCharFunctionX<X> functionalInterface) {
		return new LFloatToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunction, RS extends AbstractCharacterAssert<RS>> LFloatToCharFunctionAssert.The<A, RS> assertThat(LFloatToCharFunction functionalInterface) {
		return new LFloatToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LDoubleToCharFunctionXAssert.The<A, RS, X> assertThat(LDoubleToCharFunctionX<X> functionalInterface) {
		return new LDoubleToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunction, RS extends AbstractCharacterAssert<RS>> LDoubleToCharFunctionAssert.The<A, RS> assertThat(LDoubleToCharFunction functionalInterface) {
		return new LDoubleToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LBoolToCharFunctionXAssert.The<A, RS, X> assertThat(LBoolToCharFunctionX<X> functionalInterface) {
		return new LBoolToCharFunctionXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolToCharFunction, RS extends AbstractCharacterAssert<RS>> LBoolToCharFunctionAssert.The<A, RS> assertThat(LBoolToCharFunction functionalInterface) {
		return new LBoolToCharFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBytePredicateXAssert.The<A, RS, T, X> assertThat(LObjBytePredicateX<T, X> functionalInterface) {
		return new LObjBytePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.The<A, RS, T> assertThat(LObjBytePredicate<T> functionalInterface) {
		return new LObjBytePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjShortPredicateXAssert.The<A, RS, T, X> assertThat(LObjShortPredicateX<T, X> functionalInterface) {
		return new LObjShortPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjShortPredicateAssert.The<A, RS, T> assertThat(LObjShortPredicate<T> functionalInterface) {
		return new LObjShortPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjIntPredicateXAssert.The<A, RS, T, X> assertThat(LObjIntPredicateX<T, X> functionalInterface) {
		return new LObjIntPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.The<A, RS, T> assertThat(LObjIntPredicate<T> functionalInterface) {
		return new LObjIntPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjLongPredicateXAssert.The<A, RS, T, X> assertThat(LObjLongPredicateX<T, X> functionalInterface) {
		return new LObjLongPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.The<A, RS, T> assertThat(LObjLongPredicate<T> functionalInterface) {
		return new LObjLongPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjFloatPredicateXAssert.The<A, RS, T, X> assertThat(LObjFloatPredicateX<T, X> functionalInterface) {
		return new LObjFloatPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFloatPredicateAssert.The<A, RS, T> assertThat(LObjFloatPredicate<T> functionalInterface) {
		return new LObjFloatPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjDoublePredicateXAssert.The<A, RS, T, X> assertThat(LObjDoublePredicateX<T, X> functionalInterface) {
		return new LObjDoublePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDoublePredicateAssert.The<A, RS, T> assertThat(LObjDoublePredicate<T> functionalInterface) {
		return new LObjDoublePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjCharPredicateXAssert.The<A, RS, T, X> assertThat(LObjCharPredicateX<T, X> functionalInterface) {
		return new LObjCharPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.The<A, RS, T> assertThat(LObjCharPredicate<T> functionalInterface) {
		return new LObjCharPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBoolPredicateXAssert.The<A, RS, T, X> assertThat(LObjBoolPredicateX<T, X> functionalInterface) {
		return new LObjBoolPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBoolPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBoolPredicateAssert.The<A, RS, T> assertThat(LObjBoolPredicate<T> functionalInterface) {
		return new LObjBoolPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjBytePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> functionalInterface) {
		return new LBiObjBytePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjShortPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjShortPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> functionalInterface) {
		return new LBiObjShortPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjIntPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> functionalInterface) {
		return new LBiObjIntPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjLongPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> functionalInterface) {
		return new LBiObjLongPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFloatPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> functionalInterface) {
		return new LBiObjFloatPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjDoublePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDoublePredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> functionalInterface) {
		return new LBiObjDoublePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjCharPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> functionalInterface) {
		return new LBiObjCharPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBoolPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiObjBoolPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjBoolPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBoolPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBoolPredicateAssert.The<A, RS, T1, T2> assertThat(LBiObjBoolPredicate<T1, T2> functionalInterface) {
		return new LBiObjBoolPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiBytePredicateXAssert.The<A, RS, X> assertThat(LBiBytePredicateX<X> functionalInterface) {
		return new LBiBytePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.The<A, RS> assertThat(LBiBytePredicate functionalInterface) {
		return new LBiBytePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiShortPredicateXAssert.The<A, RS, X> assertThat(LBiShortPredicateX<X> functionalInterface) {
		return new LBiShortPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicate, RS extends AbstractBooleanAssert<RS>> LBiShortPredicateAssert.The<A, RS> assertThat(LBiShortPredicate functionalInterface) {
		return new LBiShortPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiIntPredicateXAssert.The<A, RS, X> assertThat(LBiIntPredicateX<X> functionalInterface) {
		return new LBiIntPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.The<A, RS> assertThat(LBiIntPredicate functionalInterface) {
		return new LBiIntPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiLongPredicateXAssert.The<A, RS, X> assertThat(LBiLongPredicateX<X> functionalInterface) {
		return new LBiLongPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.The<A, RS> assertThat(LBiLongPredicate functionalInterface) {
		return new LBiLongPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiFloatPredicateXAssert.The<A, RS, X> assertThat(LBiFloatPredicateX<X> functionalInterface) {
		return new LBiFloatPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicate, RS extends AbstractBooleanAssert<RS>> LBiFloatPredicateAssert.The<A, RS> assertThat(LBiFloatPredicate functionalInterface) {
		return new LBiFloatPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiDoublePredicateXAssert.The<A, RS, X> assertThat(LBiDoublePredicateX<X> functionalInterface) {
		return new LBiDoublePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicate, RS extends AbstractBooleanAssert<RS>> LBiDoublePredicateAssert.The<A, RS> assertThat(LBiDoublePredicate functionalInterface) {
		return new LBiDoublePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiCharPredicateXAssert.The<A, RS, X> assertThat(LBiCharPredicateX<X> functionalInterface) {
		return new LBiCharPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.The<A, RS> assertThat(LBiCharPredicate functionalInterface) {
		return new LBiCharPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBytePredicateXAssert.The<A, RS, X> assertThat(LBytePredicateX<X> functionalInterface) {
		return new LBytePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicate, RS extends AbstractBooleanAssert<RS>> LBytePredicateAssert.The<A, RS> assertThat(LBytePredicate functionalInterface) {
		return new LBytePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LShortPredicateXAssert.The<A, RS, X> assertThat(LShortPredicateX<X> functionalInterface) {
		return new LShortPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicate, RS extends AbstractBooleanAssert<RS>> LShortPredicateAssert.The<A, RS> assertThat(LShortPredicate functionalInterface) {
		return new LShortPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LIntPredicateXAssert.The<A, RS, X> assertThat(LIntPredicateX<X> functionalInterface) {
		return new LIntPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicate, RS extends AbstractBooleanAssert<RS>> LIntPredicateAssert.The<A, RS> assertThat(LIntPredicate functionalInterface) {
		return new LIntPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLongPredicateXAssert.The<A, RS, X> assertThat(LLongPredicateX<X> functionalInterface) {
		return new LLongPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.The<A, RS> assertThat(LLongPredicate functionalInterface) {
		return new LLongPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LFloatPredicateXAssert.The<A, RS, X> assertThat(LFloatPredicateX<X> functionalInterface) {
		return new LFloatPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicate, RS extends AbstractBooleanAssert<RS>> LFloatPredicateAssert.The<A, RS> assertThat(LFloatPredicate functionalInterface) {
		return new LFloatPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LDoublePredicateXAssert.The<A, RS, X> assertThat(LDoublePredicateX<X> functionalInterface) {
		return new LDoublePredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicate, RS extends AbstractBooleanAssert<RS>> LDoublePredicateAssert.The<A, RS> assertThat(LDoublePredicate functionalInterface) {
		return new LDoublePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LCharPredicateXAssert.The<A, RS, X> assertThat(LCharPredicateX<X> functionalInterface) {
		return new LCharPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.The<A, RS> assertThat(LCharPredicate functionalInterface) {
		return new LCharPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LPredicateXAssert.The<A, RS, T, X> assertThat(LPredicateX<T, X> functionalInterface) {
		return new LPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LPredicateAssert.The<A, RS, T> assertThat(LPredicate<T> functionalInterface) {
		return new LPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiPredicateXAssert.The<A, RS, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> functionalInterface) {
		return new LBiPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.The<A, RS, T1, T2> assertThat(LBiPredicate<T1, T2> functionalInterface) {
		return new LBiPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicateX<T1, T2, T3, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.The<A, RS, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> functionalInterface) {
		return new LTriPredicateXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.The<A, RS, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> functionalInterface) {
		return new LTriPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplierX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteSupplierXAssert.The<A, RS, X> assertThat(LByteSupplierX<X> functionalInterface) {
		return new LByteSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplier, RS extends AbstractByteAssert<RS>> LByteSupplierAssert.The<A, RS> assertThat(LByteSupplier functionalInterface) {
		return new LByteSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplierX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortSupplierXAssert.The<A, RS, X> assertThat(LShortSupplierX<X> functionalInterface) {
		return new LShortSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplier, RS extends AbstractShortAssert<RS>> LShortSupplierAssert.The<A, RS> assertThat(LShortSupplier functionalInterface) {
		return new LShortSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplierX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntSupplierXAssert.The<A, RS, X> assertThat(LIntSupplierX<X> functionalInterface) {
		return new LIntSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplier, RS extends AbstractIntegerAssert<RS>> LIntSupplierAssert.The<A, RS> assertThat(LIntSupplier functionalInterface) {
		return new LIntSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplierX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongSupplierXAssert.The<A, RS, X> assertThat(LLongSupplierX<X> functionalInterface) {
		return new LLongSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplier, RS extends AbstractLongAssert<RS>> LLongSupplierAssert.The<A, RS> assertThat(LLongSupplier functionalInterface) {
		return new LLongSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplierX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatSupplierXAssert.The<A, RS, X> assertThat(LFloatSupplierX<X> functionalInterface) {
		return new LFloatSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplier, RS extends AbstractFloatAssert<RS>> LFloatSupplierAssert.The<A, RS> assertThat(LFloatSupplier functionalInterface) {
		return new LFloatSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplierX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleSupplierXAssert.The<A, RS, X> assertThat(LDoubleSupplierX<X> functionalInterface) {
		return new LDoubleSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplier, RS extends AbstractDoubleAssert<RS>> LDoubleSupplierAssert.The<A, RS> assertThat(LDoubleSupplier functionalInterface) {
		return new LDoubleSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplierX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharSupplierXAssert.The<A, RS, X> assertThat(LCharSupplierX<X> functionalInterface) {
		return new LCharSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplier, RS extends AbstractCharacterAssert<RS>> LCharSupplierAssert.The<A, RS> assertThat(LCharSupplier functionalInterface) {
		return new LCharSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplierX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBoolSupplierXAssert.The<A, RS, X> assertThat(LBoolSupplierX<X> functionalInterface) {
		return new LBoolSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBoolSupplier, RS extends AbstractBooleanAssert<RS>> LBoolSupplierAssert.The<A, RS> assertThat(LBoolSupplier functionalInterface) {
		return new LBoolSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplierX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LSupplierXAssert.The<A, RS, R, X> assertThat(LSupplierX<R, X> functionalInterface) {
		return new LSupplierXAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplier<R>, RS extends AbstractObjectAssert<RS, R>, R> LSupplierAssert.The<A, RS, R> assertThat(LSupplier<R> functionalInterface) {
		return new LSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.UnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> UnaryOperatorAssert.The<A, RS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) {
		return new UnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> BinaryOperatorAssert.The<A, RS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) {
		return new BinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> IntUnaryOperatorAssert.The<A, RS> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		return new IntUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongUnaryOperator, RS extends AbstractLongAssert<RS>> LongUnaryOperatorAssert.The<A, RS> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		return new LongUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleUnaryOperatorAssert.The<A, RS> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		return new DoubleUnaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> IntBinaryOperatorAssert.The<A, RS> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		return new IntBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongBinaryOperator, RS extends AbstractLongAssert<RS>> LongBinaryOperatorAssert.The<A, RS> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		return new LongBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleBinaryOperatorAssert.The<A, RS> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		return new DoubleBinaryOperatorAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Function<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> FunctionAssert.The<A, RS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) {
		return new FunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiFunctionAssert.The<A, RS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) {
		return new BiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> IntFunctionAssert.The<A, RS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) {
		return new IntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LongFunctionAssert.The<A, RS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) {
		return new LongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> DoubleFunctionAssert.The<A, RS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) {
		return new DoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> ToIntFunctionAssert.The<A, RS, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		return new ToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> ToLongFunctionAssert.The<A, RS, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		return new ToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> ToDoubleFunctionAssert.The<A, RS, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		return new ToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> ToIntBiFunctionAssert.The<A, RS, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		return new ToIntBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> ToLongBiFunctionAssert.The<A, RS, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		return new ToLongBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> ToDoubleBiFunctionAssert.The<A, RS, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		return new ToDoubleBiFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToLongFunction, RS extends AbstractLongAssert<RS>> IntToLongFunctionAssert.The<A, RS> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		return new IntToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> IntToDoubleFunctionAssert.The<A, RS> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		return new IntToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToIntFunction, RS extends AbstractIntegerAssert<RS>> LongToIntFunctionAssert.The<A, RS> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		return new LongToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LongToDoubleFunctionAssert.The<A, RS> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		return new LongToDoubleFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> DoubleToIntFunctionAssert.The<A, RS> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		return new DoubleToIntFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToLongFunction, RS extends AbstractLongAssert<RS>> DoubleToLongFunctionAssert.The<A, RS> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		return new DoubleToLongFunctionAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> PredicateAssert.The<A, RS, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		return new PredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiPredicateAssert.The<A, RS, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		return new BiPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntPredicate, RS extends AbstractBooleanAssert<RS>> IntPredicateAssert.The<A, RS> assertThat(java.util.function.IntPredicate functionalInterface) {
		return new IntPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongPredicate, RS extends AbstractBooleanAssert<RS>> LongPredicateAssert.The<A, RS> assertThat(java.util.function.LongPredicate functionalInterface) {
		return new LongPredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoublePredicate, RS extends AbstractBooleanAssert<RS>> DoublePredicateAssert.The<A, RS> assertThat(java.util.function.DoublePredicate functionalInterface) {
		return new DoublePredicateAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Supplier<R>, RS extends AbstractObjectAssert<RS, R>, R> SupplierAssert.The<A, RS, R> assertThat(java.util.function.Supplier<R> functionalInterface) {
		return new SupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntSupplier, RS extends AbstractIntegerAssert<RS>> IntSupplierAssert.The<A, RS> assertThat(java.util.function.IntSupplier functionalInterface) {
		return new IntSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongSupplier, RS extends AbstractLongAssert<RS>> LongSupplierAssert.The<A, RS> assertThat(java.util.function.LongSupplier functionalInterface) {
		return new LongSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleSupplier, RS extends AbstractDoubleAssert<RS>> DoubleSupplierAssert.The<A, RS> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		return new DoubleSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BooleanSupplier, RS extends AbstractBooleanAssert<RS>> BooleanSupplierAssert.The<A, RS> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		return new BooleanSupplierAssert.The(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Consumer<T>, T> ConsumerAssert.The<A, T> assertThat(java.util.function.Consumer<T> functionalInterface) {
		return new ConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.The<A, T1, T2> assertThat(java.util.function.BiConsumer<T1, T2> functionalInterface) {
		return new BiConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.IntConsumer> IntConsumerAssert.The<A> assertThat(java.util.function.IntConsumer functionalInterface) {
		return new IntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.LongConsumer> LongConsumerAssert.The<A> assertThat(java.util.function.LongConsumer functionalInterface) {
		return new LongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleConsumer> DoubleConsumerAssert.The<A> assertThat(java.util.function.DoubleConsumer functionalInterface) {
		return new DoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjIntConsumer<T>, T> ObjIntConsumerAssert.The<A, T> assertThat(java.util.function.ObjIntConsumer<T> functionalInterface) {
		return new ObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjLongConsumer<T>, T> ObjLongConsumerAssert.The<A, T> assertThat(java.util.function.ObjLongConsumer<T> functionalInterface) {
		return new ObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.The<A, T> assertThat(java.util.function.ObjDoubleConsumer<T> functionalInterface) {
		return new ObjDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	public static <A extends java.lang.Runnable> RunnableAssert.The<A> assertThat(java.lang.Runnable functionalInterface) {
		return new RunnableAssert.The(functionalInterface);
	}

}