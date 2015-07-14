/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
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
	public static <A extends LAction> LActionAssert.Impl<A> assertThat(LAction functionalInterface) {
		return new LActionAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LActionX<X>, X extends Throwable> LActionXAssert.Impl<A, X> assertThat(LActionX<X> functionalInterface) {
		return new LActionXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LConsumer<T>, T> LConsumerAssert.Impl<A, T> assertThat(LConsumer<T> functionalInterface) {
		return new LConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.Impl<A, T, X> assertThat(LConsumerX<T, X> functionalInterface) {
		return new LConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.Impl<A, T1, T2> assertThat(LBiConsumer<T1, T2> functionalInterface) {
		return new LBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> functionalInterface) {
		return new LBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.Impl<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> functionalInterface) {
		return new LTriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.Impl<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> functionalInterface) {
		return new LTriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteConsumer> LByteConsumerAssert.Impl<A> assertThat(LByteConsumer functionalInterface) {
		return new LByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.Impl<A, X> assertThat(LByteConsumerX<X> functionalInterface) {
		return new LByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortConsumer> LShortConsumerAssert.Impl<A> assertThat(LShortConsumer functionalInterface) {
		return new LShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.Impl<A, X> assertThat(LShortConsumerX<X> functionalInterface) {
		return new LShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntConsumer> LIntConsumerAssert.Impl<A> assertThat(LIntConsumer functionalInterface) {
		return new LIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.Impl<A, X> assertThat(LIntConsumerX<X> functionalInterface) {
		return new LIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongConsumer> LLongConsumerAssert.Impl<A> assertThat(LLongConsumer functionalInterface) {
		return new LLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.Impl<A, X> assertThat(LLongConsumerX<X> functionalInterface) {
		return new LLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatConsumer> LFloatConsumerAssert.Impl<A> assertThat(LFloatConsumer functionalInterface) {
		return new LFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.Impl<A, X> assertThat(LFloatConsumerX<X> functionalInterface) {
		return new LFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleConsumer> LDoubleConsumerAssert.Impl<A> assertThat(LDoubleConsumer functionalInterface) {
		return new LDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.Impl<A, X> assertThat(LDoubleConsumerX<X> functionalInterface) {
		return new LDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharConsumer> LCharConsumerAssert.Impl<A> assertThat(LCharConsumer functionalInterface) {
		return new LCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.Impl<A, X> assertThat(LCharConsumerX<X> functionalInterface) {
		return new LCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanConsumer> LBooleanConsumerAssert.Impl<A> assertThat(LBooleanConsumer functionalInterface) {
		return new LBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanConsumerX<X>, X extends Throwable> LBooleanConsumerXAssert.Impl<A, X> assertThat(LBooleanConsumerX<X> functionalInterface) {
		return new LBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteBiConsumer> LByteBiConsumerAssert.Impl<A> assertThat(LByteBiConsumer functionalInterface) {
		return new LByteBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LByteBiConsumerX<X>, X extends Throwable> LByteBiConsumerXAssert.Impl<A, X> assertThat(LByteBiConsumerX<X> functionalInterface) {
		return new LByteBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortBiConsumer> LShortBiConsumerAssert.Impl<A> assertThat(LShortBiConsumer functionalInterface) {
		return new LShortBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LShortBiConsumerX<X>, X extends Throwable> LShortBiConsumerXAssert.Impl<A, X> assertThat(LShortBiConsumerX<X> functionalInterface) {
		return new LShortBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntBiConsumer> LIntBiConsumerAssert.Impl<A> assertThat(LIntBiConsumer functionalInterface) {
		return new LIntBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LIntBiConsumerX<X>, X extends Throwable> LIntBiConsumerXAssert.Impl<A, X> assertThat(LIntBiConsumerX<X> functionalInterface) {
		return new LIntBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongBiConsumer> LLongBiConsumerAssert.Impl<A> assertThat(LLongBiConsumer functionalInterface) {
		return new LLongBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LLongBiConsumerX<X>, X extends Throwable> LLongBiConsumerXAssert.Impl<A, X> assertThat(LLongBiConsumerX<X> functionalInterface) {
		return new LLongBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatBiConsumer> LFloatBiConsumerAssert.Impl<A> assertThat(LFloatBiConsumer functionalInterface) {
		return new LFloatBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LFloatBiConsumerX<X>, X extends Throwable> LFloatBiConsumerXAssert.Impl<A, X> assertThat(LFloatBiConsumerX<X> functionalInterface) {
		return new LFloatBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleBiConsumer> LDoubleBiConsumerAssert.Impl<A> assertThat(LDoubleBiConsumer functionalInterface) {
		return new LDoubleBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LDoubleBiConsumerX<X>, X extends Throwable> LDoubleBiConsumerXAssert.Impl<A, X> assertThat(LDoubleBiConsumerX<X> functionalInterface) {
		return new LDoubleBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharBiConsumer> LCharBiConsumerAssert.Impl<A> assertThat(LCharBiConsumer functionalInterface) {
		return new LCharBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LCharBiConsumerX<X>, X extends Throwable> LCharBiConsumerXAssert.Impl<A, X> assertThat(LCharBiConsumerX<X> functionalInterface) {
		return new LCharBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanBiConsumer> LBooleanBiConsumerAssert.Impl<A> assertThat(LBooleanBiConsumer functionalInterface) {
		return new LBooleanBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanBiConsumerX<X>, X extends Throwable> LBooleanBiConsumerXAssert.Impl<A, X> assertThat(LBooleanBiConsumerX<X> functionalInterface) {
		return new LBooleanBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanTriConsumer> LBooleanTriConsumerAssert.Impl<A> assertThat(LBooleanTriConsumer functionalInterface) {
		return new LBooleanTriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBooleanTriConsumerX<X>, X extends Throwable> LBooleanTriConsumerXAssert.Impl<A, X> assertThat(LBooleanTriConsumerX<X> functionalInterface) {
		return new LBooleanTriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.Impl<A, T> assertThat(LObjByteConsumer<T> functionalInterface) {
		return new LObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.Impl<A, T, X> assertThat(LObjByteConsumerX<T, X> functionalInterface) {
		return new LObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.Impl<A, T> assertThat(LObjShortConsumer<T> functionalInterface) {
		return new LObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.Impl<A, T, X> assertThat(LObjShortConsumerX<T, X> functionalInterface) {
		return new LObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.Impl<A, T> assertThat(LObjIntConsumer<T> functionalInterface) {
		return new LObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.Impl<A, T, X> assertThat(LObjIntConsumerX<T, X> functionalInterface) {
		return new LObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.Impl<A, T> assertThat(LObjLongConsumer<T> functionalInterface) {
		return new LObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.Impl<A, T, X> assertThat(LObjLongConsumerX<T, X> functionalInterface) {
		return new LObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.Impl<A, T> assertThat(LObjFloatConsumer<T> functionalInterface) {
		return new LObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.Impl<A, T, X> assertThat(LObjFloatConsumerX<T, X> functionalInterface) {
		return new LObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.Impl<A, T> assertThat(LObjDoubleConsumer<T> functionalInterface) {
		return new LObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.Impl<A, T, X> assertThat(LObjDoubleConsumerX<T, X> functionalInterface) {
		return new LObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.Impl<A, T> assertThat(LObjCharConsumer<T> functionalInterface) {
		return new LObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.Impl<A, T, X> assertThat(LObjCharConsumerX<T, X> functionalInterface) {
		return new LObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjBooleanConsumer<T>, T> LObjBooleanConsumerAssert.Impl<A, T> assertThat(LObjBooleanConsumer<T> functionalInterface) {
		return new LObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LObjBooleanConsumerX<T, X>, T, X extends Throwable> LObjBooleanConsumerXAssert.Impl<A, T, X> assertThat(LObjBooleanConsumerX<T, X> functionalInterface) {
		return new LObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> functionalInterface) {
		return new LBiObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> functionalInterface) {
		return new LBiObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> functionalInterface) {
		return new LBiObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> functionalInterface) {
		return new LBiObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> functionalInterface) {
		return new LBiObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> functionalInterface) {
		return new LBiObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> functionalInterface) {
		return new LBiObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjBooleanConsumer<T1, T2>, T1, T2> LBiObjBooleanConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjBooleanConsumer<T1, T2> functionalInterface) {
		return new LBiObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LBiObjBooleanConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBooleanConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjBooleanConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LUnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LUnaryOperatorAssert.Impl<A, RS, T> assertThat(LUnaryOperator<T> functionalInterface) {
		return new LUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LUnaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LUnaryOperatorXAssert.Impl<A, RS, T, X> assertThat(LUnaryOperatorX<T, X> functionalInterface) {
		return new LUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LBinaryOperatorAssert.Impl<A, RS, T> assertThat(LBinaryOperator<T> functionalInterface) {
		return new LBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBinaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LBinaryOperatorXAssert.Impl<A, RS, T, X> assertThat(LBinaryOperatorX<T, X> functionalInterface) {
		return new LBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> LTernaryOperatorAssert.Impl<A, RS, T> assertThat(LTernaryOperator<T> functionalInterface) {
		return new LTernaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTernaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Throwable> LTernaryOperatorXAssert.Impl<A, RS, T, X> assertThat(LTernaryOperatorX<T, X> functionalInterface) {
		return new LTernaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperator, RS extends AbstractByteAssert<RS>> LByteUnaryOperatorAssert.Impl<A, RS> assertThat(LByteUnaryOperator functionalInterface) {
		return new LByteUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteUnaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LByteUnaryOperatorX<X> functionalInterface) {
		return new LByteUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperator, RS extends AbstractShortAssert<RS>> LShortUnaryOperatorAssert.Impl<A, RS> assertThat(LShortUnaryOperator functionalInterface) {
		return new LShortUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortUnaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LShortUnaryOperatorX<X> functionalInterface) {
		return new LShortUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperator, RS extends AbstractIntegerAssert<RS>> LIntUnaryOperatorAssert.Impl<A, RS> assertThat(LIntUnaryOperator functionalInterface) {
		return new LIntUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntUnaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LIntUnaryOperatorX<X> functionalInterface) {
		return new LIntUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperator, RS extends AbstractLongAssert<RS>> LLongUnaryOperatorAssert.Impl<A, RS> assertThat(LLongUnaryOperator functionalInterface) {
		return new LLongUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongUnaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LLongUnaryOperatorX<X> functionalInterface) {
		return new LLongUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperator, RS extends AbstractFloatAssert<RS>> LFloatUnaryOperatorAssert.Impl<A, RS> assertThat(LFloatUnaryOperator functionalInterface) {
		return new LFloatUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatUnaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LFloatUnaryOperatorX<X> functionalInterface) {
		return new LFloatUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleUnaryOperatorAssert.Impl<A, RS> assertThat(LDoubleUnaryOperator functionalInterface) {
		return new LDoubleUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleUnaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LDoubleUnaryOperatorX<X> functionalInterface) {
		return new LDoubleUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperator, RS extends AbstractCharacterAssert<RS>> LCharUnaryOperatorAssert.Impl<A, RS> assertThat(LCharUnaryOperator functionalInterface) {
		return new LCharUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharUnaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LCharUnaryOperatorX<X> functionalInterface) {
		return new LCharUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperator, RS extends AbstractBooleanAssert<RS>> LLogicalOperatorAssert.Impl<A, RS> assertThat(LLogicalOperator functionalInterface) {
		return new LLogicalOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalOperatorXAssert.Impl<A, RS, X> assertThat(LLogicalOperatorX<X> functionalInterface) {
		return new LLogicalOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperator, RS extends AbstractByteAssert<RS>> LByteBinaryOperatorAssert.Impl<A, RS> assertThat(LByteBinaryOperator functionalInterface) {
		return new LByteBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBinaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LByteBinaryOperatorX<X> functionalInterface) {
		return new LByteBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperator, RS extends AbstractShortAssert<RS>> LShortBinaryOperatorAssert.Impl<A, RS> assertThat(LShortBinaryOperator functionalInterface) {
		return new LShortBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBinaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LShortBinaryOperatorX<X> functionalInterface) {
		return new LShortBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperator, RS extends AbstractIntegerAssert<RS>> LIntBinaryOperatorAssert.Impl<A, RS> assertThat(LIntBinaryOperator functionalInterface) {
		return new LIntBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBinaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LIntBinaryOperatorX<X> functionalInterface) {
		return new LIntBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperator, RS extends AbstractLongAssert<RS>> LLongBinaryOperatorAssert.Impl<A, RS> assertThat(LLongBinaryOperator functionalInterface) {
		return new LLongBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBinaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LLongBinaryOperatorX<X> functionalInterface) {
		return new LLongBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperator, RS extends AbstractFloatAssert<RS>> LFloatBinaryOperatorAssert.Impl<A, RS> assertThat(LFloatBinaryOperator functionalInterface) {
		return new LFloatBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBinaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LFloatBinaryOperatorX<X> functionalInterface) {
		return new LFloatBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> LDoubleBinaryOperatorAssert.Impl<A, RS> assertThat(LDoubleBinaryOperator functionalInterface) {
		return new LDoubleBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBinaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LDoubleBinaryOperatorX<X> functionalInterface) {
		return new LDoubleBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperator, RS extends AbstractCharacterAssert<RS>> LCharBinaryOperatorAssert.Impl<A, RS> assertThat(LCharBinaryOperator functionalInterface) {
		return new LCharBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBinaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LCharBinaryOperatorX<X> functionalInterface) {
		return new LCharBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalBinaryOperatorAssert.Impl<A, RS> assertThat(LLogicalBinaryOperator functionalInterface) {
		return new LLogicalBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalBinaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LLogicalBinaryOperatorX<X> functionalInterface) {
		return new LLogicalBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperator, RS extends AbstractBooleanAssert<RS>> LLogicalTernaryOperatorAssert.Impl<A, RS> assertThat(LLogicalTernaryOperator functionalInterface) {
		return new LLogicalTernaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLogicalTernaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLogicalTernaryOperatorXAssert.Impl<A, RS, X> assertThat(LLogicalTernaryOperatorX<X> functionalInterface) {
		return new LLogicalTernaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LFunctionAssert.Impl<A, RS, T, R> assertThat(LFunction<T, R> functionalInterface) {
		return new LFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LFunctionX<T, R, X> functionalInterface) {
		return new LFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> functionalInterface) {
		return new LBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunction<T1, T2, T3, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R> LTriFunctionAssert.Impl<A, RS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> functionalInterface) {
		return new LTriFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriFunctionX<T1, T2, T3, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.Impl<A, RS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> functionalInterface) {
		return new LTriFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LByteFunctionAssert.Impl<A, RS, R> assertThat(LByteFunction<R> functionalInterface) {
		return new LByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LByteFunctionXAssert.Impl<A, RS, R, X> assertThat(LByteFunctionX<R, X> functionalInterface) {
		return new LByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LShortFunctionAssert.Impl<A, RS, R> assertThat(LShortFunction<R> functionalInterface) {
		return new LShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LShortFunctionXAssert.Impl<A, RS, R, X> assertThat(LShortFunctionX<R, X> functionalInterface) {
		return new LShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LIntFunctionAssert.Impl<A, RS, R> assertThat(LIntFunction<R> functionalInterface) {
		return new LIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LIntFunctionXAssert.Impl<A, RS, R, X> assertThat(LIntFunctionX<R, X> functionalInterface) {
		return new LIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LLongFunctionAssert.Impl<A, RS, R> assertThat(LLongFunction<R> functionalInterface) {
		return new LLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LLongFunctionXAssert.Impl<A, RS, R, X> assertThat(LLongFunctionX<R, X> functionalInterface) {
		return new LLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LFloatFunctionAssert.Impl<A, RS, R> assertThat(LFloatFunction<R> functionalInterface) {
		return new LFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LFloatFunctionXAssert.Impl<A, RS, R, X> assertThat(LFloatFunctionX<R, X> functionalInterface) {
		return new LFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LDoubleFunctionAssert.Impl<A, RS, R> assertThat(LDoubleFunction<R> functionalInterface) {
		return new LDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LDoubleFunctionXAssert.Impl<A, RS, R, X> assertThat(LDoubleFunctionX<R, X> functionalInterface) {
		return new LDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LCharFunctionAssert.Impl<A, RS, R> assertThat(LCharFunction<R> functionalInterface) {
		return new LCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LCharFunctionXAssert.Impl<A, RS, R, X> assertThat(LCharFunctionX<R, X> functionalInterface) {
		return new LCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBooleanFunctionAssert.Impl<A, RS, R> assertThat(LBooleanFunction<R> functionalInterface) {
		return new LBooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBooleanFunctionXAssert.Impl<A, RS, R, X> assertThat(LBooleanFunctionX<R, X> functionalInterface) {
		return new LBooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LByteBiFunctionAssert.Impl<A, RS, R> assertThat(LByteBiFunction<R> functionalInterface) {
		return new LByteBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LByteBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LByteBiFunctionX<R, X> functionalInterface) {
		return new LByteBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LShortBiFunctionAssert.Impl<A, RS, R> assertThat(LShortBiFunction<R> functionalInterface) {
		return new LShortBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LShortBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LShortBiFunctionX<R, X> functionalInterface) {
		return new LShortBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LIntBiFunctionAssert.Impl<A, RS, R> assertThat(LIntBiFunction<R> functionalInterface) {
		return new LIntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LIntBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LIntBiFunctionX<R, X> functionalInterface) {
		return new LIntBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LLongBiFunctionAssert.Impl<A, RS, R> assertThat(LLongBiFunction<R> functionalInterface) {
		return new LLongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LLongBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LLongBiFunctionX<R, X> functionalInterface) {
		return new LLongBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LFloatBiFunctionAssert.Impl<A, RS, R> assertThat(LFloatBiFunction<R> functionalInterface) {
		return new LFloatBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LFloatBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LFloatBiFunctionX<R, X> functionalInterface) {
		return new LFloatBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LDoubleBiFunctionAssert.Impl<A, RS, R> assertThat(LDoubleBiFunction<R> functionalInterface) {
		return new LDoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LDoubleBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LDoubleBiFunctionX<R, X> functionalInterface) {
		return new LDoubleBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LCharBiFunctionAssert.Impl<A, RS, R> assertThat(LCharBiFunction<R> functionalInterface) {
		return new LCharBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LCharBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LCharBiFunctionX<R, X> functionalInterface) {
		return new LCharBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBooleanBiFunctionAssert.Impl<A, RS, R> assertThat(LBooleanBiFunction<R> functionalInterface) {
		return new LBooleanBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBooleanBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LBooleanBiFunctionX<R, X> functionalInterface) {
		return new LBooleanBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanTriFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LBooleanTriFunctionAssert.Impl<A, RS, R> assertThat(LBooleanTriFunction<R> functionalInterface) {
		return new LBooleanTriFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanTriFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LBooleanTriFunctionXAssert.Impl<A, RS, R, X> assertThat(LBooleanTriFunctionX<R, X> functionalInterface) {
		return new LBooleanTriFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjByteFunctionAssert.Impl<A, RS, T, R> assertThat(LObjByteFunction<T, R> functionalInterface) {
		return new LObjByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjByteFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjByteFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> functionalInterface) {
		return new LObjByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjShortFunctionAssert.Impl<A, RS, T, R> assertThat(LObjShortFunction<T, R> functionalInterface) {
		return new LObjShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjShortFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> functionalInterface) {
		return new LObjShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjIntFunctionAssert.Impl<A, RS, T, R> assertThat(LObjIntFunction<T, R> functionalInterface) {
		return new LObjIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjIntFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> functionalInterface) {
		return new LObjIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjLongFunctionAssert.Impl<A, RS, T, R> assertThat(LObjLongFunction<T, R> functionalInterface) {
		return new LObjLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjLongFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> functionalInterface) {
		return new LObjLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjFloatFunctionAssert.Impl<A, RS, T, R> assertThat(LObjFloatFunction<T, R> functionalInterface) {
		return new LObjFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjFloatFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> functionalInterface) {
		return new LObjFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjDoubleFunctionAssert.Impl<A, RS, T, R> assertThat(LObjDoubleFunction<T, R> functionalInterface) {
		return new LObjDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoubleFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> functionalInterface) {
		return new LObjDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjCharFunctionAssert.Impl<A, RS, T, R> assertThat(LObjCharFunction<T, R> functionalInterface) {
		return new LObjCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjCharFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> functionalInterface) {
		return new LObjCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBooleanFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> LObjBooleanFunctionAssert.Impl<A, RS, T, R> assertThat(LObjBooleanFunction<T, R> functionalInterface) {
		return new LObjBooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBooleanFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Throwable> LObjBooleanFunctionXAssert.Impl<A, RS, T, R, X> assertThat(LObjBooleanFunctionX<T, R, X> functionalInterface) {
		return new LObjBooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjByteFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> functionalInterface) {
		return new LBiObjByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjByteFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjShortFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> functionalInterface) {
		return new LBiObjShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjIntFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> functionalInterface) {
		return new LBiObjIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjLongFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> functionalInterface) {
		return new LBiObjLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjFloatFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> functionalInterface) {
		return new LBiObjFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjDoubleFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> functionalInterface) {
		return new LBiObjDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(
			LBiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjCharFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> functionalInterface) {
		return new LBiObjCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBooleanFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> LBiObjBooleanFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(LBiObjBooleanFunction<T1, T2, R> functionalInterface) {
		return new LBiObjBooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBooleanFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Throwable> LBiObjBooleanFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(
			LBiObjBooleanFunctionX<T1, T2, R, X> functionalInterface) {
		return new LBiObjBooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> LToByteFunctionAssert.Impl<A, RS, T> assertThat(LToByteFunction<T> functionalInterface) {
		return new LToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Throwable> LToByteFunctionXAssert.Impl<A, RS, T, X> assertThat(LToByteFunctionX<T, X> functionalInterface) {
		return new LToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunction<T>, RS extends AbstractShortAssert<RS>, T> LToShortFunctionAssert.Impl<A, RS, T> assertThat(LToShortFunction<T> functionalInterface) {
		return new LToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortFunctionX<T, X>, RS extends AbstractShortAssert<RS>, T, X extends Throwable> LToShortFunctionXAssert.Impl<A, RS, T, X> assertThat(LToShortFunctionX<T, X> functionalInterface) {
		return new LToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LToIntFunctionAssert.Impl<A, RS, T> assertThat(LToIntFunction<T> functionalInterface) {
		return new LToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LToIntFunctionXAssert.Impl<A, RS, T, X> assertThat(LToIntFunctionX<T, X> functionalInterface) {
		return new LToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> LToLongFunctionAssert.Impl<A, RS, T> assertThat(LToLongFunction<T> functionalInterface) {
		return new LToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongFunctionX<T, X>, RS extends AbstractLongAssert<RS>, T, X extends Throwable> LToLongFunctionXAssert.Impl<A, RS, T, X> assertThat(LToLongFunctionX<T, X> functionalInterface) {
		return new LToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunction<T>, RS extends AbstractFloatAssert<RS>, T> LToFloatFunctionAssert.Impl<A, RS, T> assertThat(LToFloatFunction<T> functionalInterface) {
		return new LToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Throwable> LToFloatFunctionXAssert.Impl<A, RS, T, X> assertThat(LToFloatFunctionX<T, X> functionalInterface) {
		return new LToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> LToDoubleFunctionAssert.Impl<A, RS, T> assertThat(LToDoubleFunction<T> functionalInterface) {
		return new LToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleFunctionX<T, X>, RS extends AbstractDoubleAssert<RS>, T, X extends Throwable> LToDoubleFunctionXAssert.Impl<A, RS, T, X> assertThat(LToDoubleFunctionX<T, X> functionalInterface) {
		return new LToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> LToCharFunctionAssert.Impl<A, RS, T> assertThat(LToCharFunction<T> functionalInterface) {
		return new LToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharFunctionX<T, X>, RS extends AbstractCharacterAssert<RS>, T, X extends Throwable> LToCharFunctionXAssert.Impl<A, RS, T, X> assertThat(LToCharFunctionX<T, X> functionalInterface) {
		return new LToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> LToByteBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToByteBiFunction<T1, T2> functionalInterface) {
		return new LToByteBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToByteBiFunctionX<T1, T2, X>, RS extends AbstractByteAssert<RS>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToByteBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> LToShortBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToShortBiFunction<T1, T2> functionalInterface) {
		return new LToShortBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToShortBiFunctionX<T1, T2, X>, RS extends AbstractShortAssert<RS>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToShortBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> LToIntBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToIntBiFunction<T1, T2> functionalInterface) {
		return new LToIntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToIntBiFunctionX<T1, T2, X>, RS extends AbstractIntegerAssert<RS>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToIntBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> LToLongBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToLongBiFunction<T1, T2> functionalInterface) {
		return new LToLongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToLongBiFunctionX<T1, T2, X>, RS extends AbstractLongAssert<RS>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToLongBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> LToFloatBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToFloatBiFunction<T1, T2> functionalInterface) {
		return new LToFloatBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToFloatBiFunctionX<T1, T2, X>, RS extends AbstractFloatAssert<RS>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToFloatBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> LToDoubleBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> functionalInterface) {
		return new LToDoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToDoubleBiFunctionX<T1, T2, X>, RS extends AbstractDoubleAssert<RS>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToDoubleBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> LToCharBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(LToCharBiFunction<T1, T2> functionalInterface) {
		return new LToCharBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LToCharBiFunctionX<T1, T2, X>, RS extends AbstractCharacterAssert<RS>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> functionalInterface) {
		return new LToCharBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> LObjIntToIntFunctionAssert.Impl<A, RS, T> assertThat(LObjIntToIntFunction<T> functionalInterface) {
		return new LObjIntToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Throwable> LObjIntToIntFunctionXAssert.Impl<A, RS, T, X> assertThat(LObjIntToIntFunctionX<T, X> functionalInterface) {
		return new LObjIntToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunction, RS extends AbstractShortAssert<RS>> LByteToShortFunctionAssert.Impl<A, RS> assertThat(LByteToShortFunction functionalInterface) {
		return new LByteToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LByteToShortFunctionXAssert.Impl<A, RS, X> assertThat(LByteToShortFunctionX<X> functionalInterface) {
		return new LByteToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunction, RS extends AbstractIntegerAssert<RS>> LByteToIntFunctionAssert.Impl<A, RS> assertThat(LByteToIntFunction functionalInterface) {
		return new LByteToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LByteToIntFunctionXAssert.Impl<A, RS, X> assertThat(LByteToIntFunctionX<X> functionalInterface) {
		return new LByteToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunction, RS extends AbstractLongAssert<RS>> LByteToLongFunctionAssert.Impl<A, RS> assertThat(LByteToLongFunction functionalInterface) {
		return new LByteToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LByteToLongFunctionXAssert.Impl<A, RS, X> assertThat(LByteToLongFunctionX<X> functionalInterface) {
		return new LByteToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunction, RS extends AbstractFloatAssert<RS>> LByteToFloatFunctionAssert.Impl<A, RS> assertThat(LByteToFloatFunction functionalInterface) {
		return new LByteToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LByteToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LByteToFloatFunctionX<X> functionalInterface) {
		return new LByteToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LByteToDoubleFunctionAssert.Impl<A, RS> assertThat(LByteToDoubleFunction functionalInterface) {
		return new LByteToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LByteToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LByteToDoubleFunctionX<X> functionalInterface) {
		return new LByteToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunction, RS extends AbstractCharacterAssert<RS>> LByteToCharFunctionAssert.Impl<A, RS> assertThat(LByteToCharFunction functionalInterface) {
		return new LByteToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LByteToCharFunctionXAssert.Impl<A, RS, X> assertThat(LByteToCharFunctionX<X> functionalInterface) {
		return new LByteToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunction, RS extends AbstractByteAssert<RS>> LShortToByteFunctionAssert.Impl<A, RS> assertThat(LShortToByteFunction functionalInterface) {
		return new LShortToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LShortToByteFunctionXAssert.Impl<A, RS, X> assertThat(LShortToByteFunctionX<X> functionalInterface) {
		return new LShortToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunction, RS extends AbstractIntegerAssert<RS>> LShortToIntFunctionAssert.Impl<A, RS> assertThat(LShortToIntFunction functionalInterface) {
		return new LShortToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LShortToIntFunctionXAssert.Impl<A, RS, X> assertThat(LShortToIntFunctionX<X> functionalInterface) {
		return new LShortToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunction, RS extends AbstractLongAssert<RS>> LShortToLongFunctionAssert.Impl<A, RS> assertThat(LShortToLongFunction functionalInterface) {
		return new LShortToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LShortToLongFunctionXAssert.Impl<A, RS, X> assertThat(LShortToLongFunctionX<X> functionalInterface) {
		return new LShortToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunction, RS extends AbstractFloatAssert<RS>> LShortToFloatFunctionAssert.Impl<A, RS> assertThat(LShortToFloatFunction functionalInterface) {
		return new LShortToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LShortToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LShortToFloatFunctionX<X> functionalInterface) {
		return new LShortToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LShortToDoubleFunctionAssert.Impl<A, RS> assertThat(LShortToDoubleFunction functionalInterface) {
		return new LShortToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LShortToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LShortToDoubleFunctionX<X> functionalInterface) {
		return new LShortToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>> LShortToCharFunctionAssert.Impl<A, RS> assertThat(LShortToCharFunction functionalInterface) {
		return new LShortToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LShortToCharFunctionXAssert.Impl<A, RS, X> assertThat(LShortToCharFunctionX<X> functionalInterface) {
		return new LShortToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunction, RS extends AbstractByteAssert<RS>> LIntToByteFunctionAssert.Impl<A, RS> assertThat(LIntToByteFunction functionalInterface) {
		return new LIntToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LIntToByteFunctionXAssert.Impl<A, RS, X> assertThat(LIntToByteFunctionX<X> functionalInterface) {
		return new LIntToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunction, RS extends AbstractShortAssert<RS>> LIntToShortFunctionAssert.Impl<A, RS> assertThat(LIntToShortFunction functionalInterface) {
		return new LIntToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LIntToShortFunctionXAssert.Impl<A, RS, X> assertThat(LIntToShortFunctionX<X> functionalInterface) {
		return new LIntToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunction, RS extends AbstractLongAssert<RS>> LIntToLongFunctionAssert.Impl<A, RS> assertThat(LIntToLongFunction functionalInterface) {
		return new LIntToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LIntToLongFunctionXAssert.Impl<A, RS, X> assertThat(LIntToLongFunctionX<X> functionalInterface) {
		return new LIntToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunction, RS extends AbstractFloatAssert<RS>> LIntToFloatFunctionAssert.Impl<A, RS> assertThat(LIntToFloatFunction functionalInterface) {
		return new LIntToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LIntToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LIntToFloatFunctionX<X> functionalInterface) {
		return new LIntToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LIntToDoubleFunctionAssert.Impl<A, RS> assertThat(LIntToDoubleFunction functionalInterface) {
		return new LIntToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LIntToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LIntToDoubleFunctionX<X> functionalInterface) {
		return new LIntToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunction, RS extends AbstractCharacterAssert<RS>> LIntToCharFunctionAssert.Impl<A, RS> assertThat(LIntToCharFunction functionalInterface) {
		return new LIntToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LIntToCharFunctionXAssert.Impl<A, RS, X> assertThat(LIntToCharFunctionX<X> functionalInterface) {
		return new LIntToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunction, RS extends AbstractByteAssert<RS>> LLongToByteFunctionAssert.Impl<A, RS> assertThat(LLongToByteFunction functionalInterface) {
		return new LLongToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LLongToByteFunctionXAssert.Impl<A, RS, X> assertThat(LLongToByteFunctionX<X> functionalInterface) {
		return new LLongToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunction, RS extends AbstractShortAssert<RS>> LLongToShortFunctionAssert.Impl<A, RS> assertThat(LLongToShortFunction functionalInterface) {
		return new LLongToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LLongToShortFunctionXAssert.Impl<A, RS, X> assertThat(LLongToShortFunctionX<X> functionalInterface) {
		return new LLongToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunction, RS extends AbstractIntegerAssert<RS>> LLongToIntFunctionAssert.Impl<A, RS> assertThat(LLongToIntFunction functionalInterface) {
		return new LLongToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LLongToIntFunctionXAssert.Impl<A, RS, X> assertThat(LLongToIntFunctionX<X> functionalInterface) {
		return new LLongToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunction, RS extends AbstractFloatAssert<RS>> LLongToFloatFunctionAssert.Impl<A, RS> assertThat(LLongToFloatFunction functionalInterface) {
		return new LLongToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LLongToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LLongToFloatFunctionX<X> functionalInterface) {
		return new LLongToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LLongToDoubleFunctionAssert.Impl<A, RS> assertThat(LLongToDoubleFunction functionalInterface) {
		return new LLongToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LLongToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LLongToDoubleFunctionX<X> functionalInterface) {
		return new LLongToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunction, RS extends AbstractCharacterAssert<RS>> LLongToCharFunctionAssert.Impl<A, RS> assertThat(LLongToCharFunction functionalInterface) {
		return new LLongToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LLongToCharFunctionXAssert.Impl<A, RS, X> assertThat(LLongToCharFunctionX<X> functionalInterface) {
		return new LLongToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunction, RS extends AbstractByteAssert<RS>> LFloatToByteFunctionAssert.Impl<A, RS> assertThat(LFloatToByteFunction functionalInterface) {
		return new LFloatToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LFloatToByteFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToByteFunctionX<X> functionalInterface) {
		return new LFloatToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunction, RS extends AbstractShortAssert<RS>> LFloatToShortFunctionAssert.Impl<A, RS> assertThat(LFloatToShortFunction functionalInterface) {
		return new LFloatToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LFloatToShortFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToShortFunctionX<X> functionalInterface) {
		return new LFloatToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunction, RS extends AbstractIntegerAssert<RS>> LFloatToIntFunctionAssert.Impl<A, RS> assertThat(LFloatToIntFunction functionalInterface) {
		return new LFloatToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LFloatToIntFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToIntFunctionX<X> functionalInterface) {
		return new LFloatToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunction, RS extends AbstractLongAssert<RS>> LFloatToLongFunctionAssert.Impl<A, RS> assertThat(LFloatToLongFunction functionalInterface) {
		return new LFloatToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LFloatToLongFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToLongFunctionX<X> functionalInterface) {
		return new LFloatToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LFloatToDoubleFunctionAssert.Impl<A, RS> assertThat(LFloatToDoubleFunction functionalInterface) {
		return new LFloatToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LFloatToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToDoubleFunctionX<X> functionalInterface) {
		return new LFloatToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunction, RS extends AbstractCharacterAssert<RS>> LFloatToCharFunctionAssert.Impl<A, RS> assertThat(LFloatToCharFunction functionalInterface) {
		return new LFloatToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LFloatToCharFunctionXAssert.Impl<A, RS, X> assertThat(LFloatToCharFunctionX<X> functionalInterface) {
		return new LFloatToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunction, RS extends AbstractByteAssert<RS>> LDoubleToByteFunctionAssert.Impl<A, RS> assertThat(LDoubleToByteFunction functionalInterface) {
		return new LDoubleToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LDoubleToByteFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToByteFunctionX<X> functionalInterface) {
		return new LDoubleToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunction, RS extends AbstractShortAssert<RS>> LDoubleToShortFunctionAssert.Impl<A, RS> assertThat(LDoubleToShortFunction functionalInterface) {
		return new LDoubleToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LDoubleToShortFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToShortFunctionX<X> functionalInterface) {
		return new LDoubleToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> LDoubleToIntFunctionAssert.Impl<A, RS> assertThat(LDoubleToIntFunction functionalInterface) {
		return new LDoubleToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LDoubleToIntFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToIntFunctionX<X> functionalInterface) {
		return new LDoubleToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunction, RS extends AbstractLongAssert<RS>> LDoubleToLongFunctionAssert.Impl<A, RS> assertThat(LDoubleToLongFunction functionalInterface) {
		return new LDoubleToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LDoubleToLongFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToLongFunctionX<X> functionalInterface) {
		return new LDoubleToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunction, RS extends AbstractFloatAssert<RS>> LDoubleToFloatFunctionAssert.Impl<A, RS> assertThat(LDoubleToFloatFunction functionalInterface) {
		return new LDoubleToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LDoubleToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToFloatFunctionX<X> functionalInterface) {
		return new LDoubleToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunction, RS extends AbstractCharacterAssert<RS>> LDoubleToCharFunctionAssert.Impl<A, RS> assertThat(LDoubleToCharFunction functionalInterface) {
		return new LDoubleToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LDoubleToCharFunctionXAssert.Impl<A, RS, X> assertThat(LDoubleToCharFunctionX<X> functionalInterface) {
		return new LDoubleToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunction, RS extends AbstractByteAssert<RS>> LCharToByteFunctionAssert.Impl<A, RS> assertThat(LCharToByteFunction functionalInterface) {
		return new LCharToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LCharToByteFunctionXAssert.Impl<A, RS, X> assertThat(LCharToByteFunctionX<X> functionalInterface) {
		return new LCharToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunction, RS extends AbstractShortAssert<RS>> LCharToShortFunctionAssert.Impl<A, RS> assertThat(LCharToShortFunction functionalInterface) {
		return new LCharToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LCharToShortFunctionXAssert.Impl<A, RS, X> assertThat(LCharToShortFunctionX<X> functionalInterface) {
		return new LCharToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunction, RS extends AbstractIntegerAssert<RS>> LCharToIntFunctionAssert.Impl<A, RS> assertThat(LCharToIntFunction functionalInterface) {
		return new LCharToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LCharToIntFunctionXAssert.Impl<A, RS, X> assertThat(LCharToIntFunctionX<X> functionalInterface) {
		return new LCharToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunction, RS extends AbstractLongAssert<RS>> LCharToLongFunctionAssert.Impl<A, RS> assertThat(LCharToLongFunction functionalInterface) {
		return new LCharToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LCharToLongFunctionXAssert.Impl<A, RS, X> assertThat(LCharToLongFunctionX<X> functionalInterface) {
		return new LCharToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunction, RS extends AbstractFloatAssert<RS>> LCharToFloatFunctionAssert.Impl<A, RS> assertThat(LCharToFloatFunction functionalInterface) {
		return new LCharToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LCharToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LCharToFloatFunctionX<X> functionalInterface) {
		return new LCharToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LCharToDoubleFunctionAssert.Impl<A, RS> assertThat(LCharToDoubleFunction functionalInterface) {
		return new LCharToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LCharToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LCharToDoubleFunctionX<X> functionalInterface) {
		return new LCharToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToByteFunction, RS extends AbstractByteAssert<RS>> LBooleanToByteFunctionAssert.Impl<A, RS> assertThat(LBooleanToByteFunction functionalInterface) {
		return new LBooleanToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LBooleanToByteFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToByteFunctionX<X> functionalInterface) {
		return new LBooleanToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToShortFunction, RS extends AbstractShortAssert<RS>> LBooleanToShortFunctionAssert.Impl<A, RS> assertThat(LBooleanToShortFunction functionalInterface) {
		return new LBooleanToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LBooleanToShortFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToShortFunctionX<X> functionalInterface) {
		return new LBooleanToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToIntFunction, RS extends AbstractIntegerAssert<RS>> LBooleanToIntFunctionAssert.Impl<A, RS> assertThat(LBooleanToIntFunction functionalInterface) {
		return new LBooleanToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LBooleanToIntFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToIntFunctionX<X> functionalInterface) {
		return new LBooleanToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToLongFunction, RS extends AbstractLongAssert<RS>> LBooleanToLongFunctionAssert.Impl<A, RS> assertThat(LBooleanToLongFunction functionalInterface) {
		return new LBooleanToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LBooleanToLongFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToLongFunctionX<X> functionalInterface) {
		return new LBooleanToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToFloatFunction, RS extends AbstractFloatAssert<RS>> LBooleanToFloatFunctionAssert.Impl<A, RS> assertThat(LBooleanToFloatFunction functionalInterface) {
		return new LBooleanToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LBooleanToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToFloatFunctionX<X> functionalInterface) {
		return new LBooleanToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LBooleanToDoubleFunctionAssert.Impl<A, RS> assertThat(LBooleanToDoubleFunction functionalInterface) {
		return new LBooleanToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LBooleanToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToDoubleFunctionX<X> functionalInterface) {
		return new LBooleanToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToCharFunction, RS extends AbstractCharacterAssert<RS>> LBooleanToCharFunctionAssert.Impl<A, RS> assertThat(LBooleanToCharFunction functionalInterface) {
		return new LBooleanToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LBooleanToCharFunctionXAssert.Impl<A, RS, X> assertThat(LBooleanToCharFunctionX<X> functionalInterface) {
		return new LBooleanToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LPredicateAssert.Impl<A, RS, T> assertThat(LPredicate<T> functionalInterface) {
		return new LPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LPredicateXAssert.Impl<A, RS, T, X> assertThat(LPredicateX<T, X> functionalInterface) {
		return new LPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiPredicate<T1, T2> functionalInterface) {
		return new LBiPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> functionalInterface) {
		return new LBiPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> LTriPredicateAssert.Impl<A, RS, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> functionalInterface) {
		return new LTriPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LTriPredicateX<T1, T2, T3, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.Impl<A, RS, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> functionalInterface) {
		return new LTriPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicate, RS extends AbstractBooleanAssert<RS>> LBytePredicateAssert.Impl<A, RS> assertThat(LBytePredicate functionalInterface) {
		return new LBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBytePredicateXAssert.Impl<A, RS, X> assertThat(LBytePredicateX<X> functionalInterface) {
		return new LBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicate, RS extends AbstractBooleanAssert<RS>> LShortPredicateAssert.Impl<A, RS> assertThat(LShortPredicate functionalInterface) {
		return new LShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LShortPredicateXAssert.Impl<A, RS, X> assertThat(LShortPredicateX<X> functionalInterface) {
		return new LShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicate, RS extends AbstractBooleanAssert<RS>> LIntPredicateAssert.Impl<A, RS> assertThat(LIntPredicate functionalInterface) {
		return new LIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LIntPredicateXAssert.Impl<A, RS, X> assertThat(LIntPredicateX<X> functionalInterface) {
		return new LIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicate, RS extends AbstractBooleanAssert<RS>> LLongPredicateAssert.Impl<A, RS> assertThat(LLongPredicate functionalInterface) {
		return new LLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LLongPredicateXAssert.Impl<A, RS, X> assertThat(LLongPredicateX<X> functionalInterface) {
		return new LLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicate, RS extends AbstractBooleanAssert<RS>> LFloatPredicateAssert.Impl<A, RS> assertThat(LFloatPredicate functionalInterface) {
		return new LFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LFloatPredicateXAssert.Impl<A, RS, X> assertThat(LFloatPredicateX<X> functionalInterface) {
		return new LFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicate, RS extends AbstractBooleanAssert<RS>> LDoublePredicateAssert.Impl<A, RS> assertThat(LDoublePredicate functionalInterface) {
		return new LDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LDoublePredicateXAssert.Impl<A, RS, X> assertThat(LDoublePredicateX<X> functionalInterface) {
		return new LDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicate, RS extends AbstractBooleanAssert<RS>> LCharPredicateAssert.Impl<A, RS> assertThat(LCharPredicate functionalInterface) {
		return new LCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LCharPredicateXAssert.Impl<A, RS, X> assertThat(LCharPredicateX<X> functionalInterface) {
		return new LCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicate, RS extends AbstractBooleanAssert<RS>> LBiBytePredicateAssert.Impl<A, RS> assertThat(LBiBytePredicate functionalInterface) {
		return new LBiBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiBytePredicateXAssert.Impl<A, RS, X> assertThat(LBiBytePredicateX<X> functionalInterface) {
		return new LBiBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicate, RS extends AbstractBooleanAssert<RS>> LBiShortPredicateAssert.Impl<A, RS> assertThat(LBiShortPredicate functionalInterface) {
		return new LBiShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiShortPredicateXAssert.Impl<A, RS, X> assertThat(LBiShortPredicateX<X> functionalInterface) {
		return new LBiShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicate, RS extends AbstractBooleanAssert<RS>> LBiIntPredicateAssert.Impl<A, RS> assertThat(LBiIntPredicate functionalInterface) {
		return new LBiIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiIntPredicateXAssert.Impl<A, RS, X> assertThat(LBiIntPredicateX<X> functionalInterface) {
		return new LBiIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicate, RS extends AbstractBooleanAssert<RS>> LBiLongPredicateAssert.Impl<A, RS> assertThat(LBiLongPredicate functionalInterface) {
		return new LBiLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiLongPredicateXAssert.Impl<A, RS, X> assertThat(LBiLongPredicateX<X> functionalInterface) {
		return new LBiLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicate, RS extends AbstractBooleanAssert<RS>> LBiFloatPredicateAssert.Impl<A, RS> assertThat(LBiFloatPredicate functionalInterface) {
		return new LBiFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiFloatPredicateXAssert.Impl<A, RS, X> assertThat(LBiFloatPredicateX<X> functionalInterface) {
		return new LBiFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicate, RS extends AbstractBooleanAssert<RS>> LBiDoublePredicateAssert.Impl<A, RS> assertThat(LBiDoublePredicate functionalInterface) {
		return new LBiDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiDoublePredicateXAssert.Impl<A, RS, X> assertThat(LBiDoublePredicateX<X> functionalInterface) {
		return new LBiDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicate, RS extends AbstractBooleanAssert<RS>> LBiCharPredicateAssert.Impl<A, RS> assertThat(LBiCharPredicate functionalInterface) {
		return new LBiCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBiCharPredicateXAssert.Impl<A, RS, X> assertThat(LBiCharPredicateX<X> functionalInterface) {
		return new LBiCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBytePredicateAssert.Impl<A, RS, T> assertThat(LObjBytePredicate<T> functionalInterface) {
		return new LObjBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBytePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBytePredicateXAssert.Impl<A, RS, T, X> assertThat(LObjBytePredicateX<T, X> functionalInterface) {
		return new LObjBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjShortPredicateAssert.Impl<A, RS, T> assertThat(LObjShortPredicate<T> functionalInterface) {
		return new LObjShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjShortPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjShortPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjShortPredicateX<T, X> functionalInterface) {
		return new LObjShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjIntPredicateAssert.Impl<A, RS, T> assertThat(LObjIntPredicate<T> functionalInterface) {
		return new LObjIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjIntPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjIntPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjIntPredicateX<T, X> functionalInterface) {
		return new LObjIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjLongPredicateAssert.Impl<A, RS, T> assertThat(LObjLongPredicate<T> functionalInterface) {
		return new LObjLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjLongPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjLongPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjLongPredicateX<T, X> functionalInterface) {
		return new LObjLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjFloatPredicateAssert.Impl<A, RS, T> assertThat(LObjFloatPredicate<T> functionalInterface) {
		return new LObjFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjFloatPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjFloatPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjFloatPredicateX<T, X> functionalInterface) {
		return new LObjFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjDoublePredicateAssert.Impl<A, RS, T> assertThat(LObjDoublePredicate<T> functionalInterface) {
		return new LObjDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjDoublePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjDoublePredicateXAssert.Impl<A, RS, T, X> assertThat(LObjDoublePredicateX<T, X> functionalInterface) {
		return new LObjDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjCharPredicateAssert.Impl<A, RS, T> assertThat(LObjCharPredicate<T> functionalInterface) {
		return new LObjCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjCharPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjCharPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjCharPredicateX<T, X> functionalInterface) {
		return new LObjCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBooleanPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> LObjBooleanPredicateAssert.Impl<A, RS, T> assertThat(LObjBooleanPredicate<T> functionalInterface) {
		return new LObjBooleanPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LObjBooleanPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Throwable> LObjBooleanPredicateXAssert.Impl<A, RS, T, X> assertThat(LObjBooleanPredicateX<T, X> functionalInterface) {
		return new LObjBooleanPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBytePredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> functionalInterface) {
		return new LBiObjBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBytePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjShortPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> functionalInterface) {
		return new LBiObjShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjShortPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjIntPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> functionalInterface) {
		return new LBiObjIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjIntPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjLongPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> functionalInterface) {
		return new LBiObjLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjLongPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjFloatPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> functionalInterface) {
		return new LBiObjFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjFloatPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjDoublePredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> functionalInterface) {
		return new LBiObjDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjDoublePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjCharPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> functionalInterface) {
		return new LBiObjCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjCharPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBooleanPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> LBiObjBooleanPredicateAssert.Impl<A, RS, T1, T2> assertThat(LBiObjBooleanPredicate<T1, T2> functionalInterface) {
		return new LBiObjBooleanPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBiObjBooleanPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Throwable> LBiObjBooleanPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(LBiObjBooleanPredicateX<T1, T2, X> functionalInterface) {
		return new LBiObjBooleanPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplier<R>, RS extends AbstractObjectAssert<RS, R>, R> LSupplierAssert.Impl<A, RS, R> assertThat(LSupplier<R> functionalInterface) {
		return new LSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LSupplierX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Throwable> LSupplierXAssert.Impl<A, RS, R, X> assertThat(LSupplierX<R, X> functionalInterface) {
		return new LSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplier, RS extends AbstractByteAssert<RS>> LByteSupplierAssert.Impl<A, RS> assertThat(LByteSupplier functionalInterface) {
		return new LByteSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LByteSupplierX<X>, RS extends AbstractByteAssert<RS>, X extends Throwable> LByteSupplierXAssert.Impl<A, RS, X> assertThat(LByteSupplierX<X> functionalInterface) {
		return new LByteSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplier, RS extends AbstractShortAssert<RS>> LShortSupplierAssert.Impl<A, RS> assertThat(LShortSupplier functionalInterface) {
		return new LShortSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LShortSupplierX<X>, RS extends AbstractShortAssert<RS>, X extends Throwable> LShortSupplierXAssert.Impl<A, RS, X> assertThat(LShortSupplierX<X> functionalInterface) {
		return new LShortSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplier, RS extends AbstractIntegerAssert<RS>> LIntSupplierAssert.Impl<A, RS> assertThat(LIntSupplier functionalInterface) {
		return new LIntSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LIntSupplierX<X>, RS extends AbstractIntegerAssert<RS>, X extends Throwable> LIntSupplierXAssert.Impl<A, RS, X> assertThat(LIntSupplierX<X> functionalInterface) {
		return new LIntSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplier, RS extends AbstractLongAssert<RS>> LLongSupplierAssert.Impl<A, RS> assertThat(LLongSupplier functionalInterface) {
		return new LLongSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LLongSupplierX<X>, RS extends AbstractLongAssert<RS>, X extends Throwable> LLongSupplierXAssert.Impl<A, RS, X> assertThat(LLongSupplierX<X> functionalInterface) {
		return new LLongSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplier, RS extends AbstractFloatAssert<RS>> LFloatSupplierAssert.Impl<A, RS> assertThat(LFloatSupplier functionalInterface) {
		return new LFloatSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LFloatSupplierX<X>, RS extends AbstractFloatAssert<RS>, X extends Throwable> LFloatSupplierXAssert.Impl<A, RS, X> assertThat(LFloatSupplierX<X> functionalInterface) {
		return new LFloatSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplier, RS extends AbstractDoubleAssert<RS>> LDoubleSupplierAssert.Impl<A, RS> assertThat(LDoubleSupplier functionalInterface) {
		return new LDoubleSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LDoubleSupplierX<X>, RS extends AbstractDoubleAssert<RS>, X extends Throwable> LDoubleSupplierXAssert.Impl<A, RS, X> assertThat(LDoubleSupplierX<X> functionalInterface) {
		return new LDoubleSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplier, RS extends AbstractCharacterAssert<RS>> LCharSupplierAssert.Impl<A, RS> assertThat(LCharSupplier functionalInterface) {
		return new LCharSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LCharSupplierX<X>, RS extends AbstractCharacterAssert<RS>, X extends Throwable> LCharSupplierXAssert.Impl<A, RS, X> assertThat(LCharSupplierX<X> functionalInterface) {
		return new LCharSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanSupplier, RS extends AbstractBooleanAssert<RS>> LBooleanSupplierAssert.Impl<A, RS> assertThat(LBooleanSupplier functionalInterface) {
		return new LBooleanSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LBooleanSupplierX<X>, RS extends AbstractBooleanAssert<RS>, X extends Throwable> LBooleanSupplierXAssert.Impl<A, RS, X> assertThat(LBooleanSupplierX<X> functionalInterface) {
		return new LBooleanSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.UnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> UnaryOperatorAssert.Impl<A, RS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) {
		return new UnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> BinaryOperatorAssert.Impl<A, RS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) {
		return new BinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> IntUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		return new IntUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongUnaryOperator, RS extends AbstractLongAssert<RS>> LongUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		return new LongUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		return new DoubleUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> IntBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		return new IntBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongBinaryOperator, RS extends AbstractLongAssert<RS>> LongBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		return new LongBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		return new DoubleBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Function<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> FunctionAssert.Impl<A, RS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) {
		return new FunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) {
		return new BiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> IntFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) {
		return new IntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LongFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) {
		return new LongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> DoubleFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) {
		return new DoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> ToIntFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		return new ToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> ToLongFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		return new ToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> ToDoubleFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		return new ToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> ToIntBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		return new ToIntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> ToLongBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		return new ToLongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> ToDoubleBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		return new ToDoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToLongFunction, RS extends AbstractLongAssert<RS>> IntToLongFunctionAssert.Impl<A, RS> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		return new IntToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> IntToDoubleFunctionAssert.Impl<A, RS> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		return new IntToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToIntFunction, RS extends AbstractIntegerAssert<RS>> LongToIntFunctionAssert.Impl<A, RS> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		return new LongToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LongToDoubleFunctionAssert.Impl<A, RS> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		return new LongToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> DoubleToIntFunctionAssert.Impl<A, RS> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		return new DoubleToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToLongFunction, RS extends AbstractLongAssert<RS>> DoubleToLongFunctionAssert.Impl<A, RS> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		return new DoubleToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> PredicateAssert.Impl<A, RS, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		return new PredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiPredicateAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		return new BiPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntPredicate, RS extends AbstractBooleanAssert<RS>> IntPredicateAssert.Impl<A, RS> assertThat(java.util.function.IntPredicate functionalInterface) {
		return new IntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongPredicate, RS extends AbstractBooleanAssert<RS>> LongPredicateAssert.Impl<A, RS> assertThat(java.util.function.LongPredicate functionalInterface) {
		return new LongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoublePredicate, RS extends AbstractBooleanAssert<RS>> DoublePredicateAssert.Impl<A, RS> assertThat(java.util.function.DoublePredicate functionalInterface) {
		return new DoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Supplier<R>, RS extends AbstractObjectAssert<RS, R>, R> SupplierAssert.Impl<A, RS, R> assertThat(java.util.function.Supplier<R> functionalInterface) {
		return new SupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntSupplier, RS extends AbstractIntegerAssert<RS>> IntSupplierAssert.Impl<A, RS> assertThat(java.util.function.IntSupplier functionalInterface) {
		return new IntSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongSupplier, RS extends AbstractLongAssert<RS>> LongSupplierAssert.Impl<A, RS> assertThat(java.util.function.LongSupplier functionalInterface) {
		return new LongSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleSupplier, RS extends AbstractDoubleAssert<RS>> DoubleSupplierAssert.Impl<A, RS> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		return new DoubleSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BooleanSupplier, RS extends AbstractBooleanAssert<RS>> BooleanSupplierAssert.Impl<A, RS> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		return new BooleanSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Consumer<T>, T> ConsumerAssert.Impl<A, T> assertThat(java.util.function.Consumer<T> functionalInterface) {
		return new ConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.Impl<A, T1, T2> assertThat(java.util.function.BiConsumer<T1, T2> functionalInterface) {
		return new BiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.IntConsumer> IntConsumerAssert.Impl<A> assertThat(java.util.function.IntConsumer functionalInterface) {
		return new IntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.LongConsumer> LongConsumerAssert.Impl<A> assertThat(java.util.function.LongConsumer functionalInterface) {
		return new LongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleConsumer> DoubleConsumerAssert.Impl<A> assertThat(java.util.function.DoubleConsumer functionalInterface) {
		return new DoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjIntConsumer<T>, T> ObjIntConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjIntConsumer<T> functionalInterface) {
		return new ObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjLongConsumer<T>, T> ObjLongConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjLongConsumer<T> functionalInterface) {
		return new ObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjDoubleConsumer<T> functionalInterface) {
		return new ObjDoubleConsumerAssert.Impl(functionalInterface);
	}

}