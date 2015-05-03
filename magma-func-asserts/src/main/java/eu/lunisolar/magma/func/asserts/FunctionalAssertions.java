/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
	public static <A extends Action> ActionAssert.Impl<A> assertThat(Action functionalInterface) {
		return new ActionAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ActionX<X>, X extends Exception> ActionXAssert.Impl<A, X> assertThat(ActionX<X> functionalInterface) {
		return new ActionXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends Consumer<T>, T> ConsumerAssert.Impl<A, T> assertThat(Consumer<T> functionalInterface) {
		return new ConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ConsumerX<T, X>, T, X extends Exception> ConsumerXAssert.Impl<A, T, X> assertThat(ConsumerX<T, X> functionalInterface) {
		return new ConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.Impl<A, T1, T2> assertThat(BiConsumer<T1, T2> functionalInterface) {
		return new BiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiConsumerX<T1, T2, X> functionalInterface) {
		return new BiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends TriConsumer<T1, T2, T3>, T1, T2, T3> TriConsumerAssert.Impl<A, T1, T2, T3> assertThat(TriConsumer<T1, T2, T3> functionalInterface) {
		return new TriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends TriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Exception> TriConsumerXAssert.Impl<A, T1, T2, T3, X> assertThat(TriConsumerX<T1, T2, T3, X> functionalInterface) {
		return new TriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ByteConsumer> ByteConsumerAssert.Impl<A> assertThat(ByteConsumer functionalInterface) {
		return new ByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ByteConsumerX<X>, X extends Exception> ByteConsumerXAssert.Impl<A, X> assertThat(ByteConsumerX<X> functionalInterface) {
		return new ByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ShortConsumer> ShortConsumerAssert.Impl<A> assertThat(ShortConsumer functionalInterface) {
		return new ShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ShortConsumerX<X>, X extends Exception> ShortConsumerXAssert.Impl<A, X> assertThat(ShortConsumerX<X> functionalInterface) {
		return new ShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends IntConsumer> IntConsumerAssert.Impl<A> assertThat(IntConsumer functionalInterface) {
		return new IntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends IntConsumerX<X>, X extends Exception> IntConsumerXAssert.Impl<A, X> assertThat(IntConsumerX<X> functionalInterface) {
		return new IntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LongConsumer> LongConsumerAssert.Impl<A> assertThat(LongConsumer functionalInterface) {
		return new LongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LongConsumerX<X>, X extends Exception> LongConsumerXAssert.Impl<A, X> assertThat(LongConsumerX<X> functionalInterface) {
		return new LongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends FloatConsumer> FloatConsumerAssert.Impl<A> assertThat(FloatConsumer functionalInterface) {
		return new FloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends FloatConsumerX<X>, X extends Exception> FloatConsumerXAssert.Impl<A, X> assertThat(FloatConsumerX<X> functionalInterface) {
		return new FloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends DoubleConsumer> DoubleConsumerAssert.Impl<A> assertThat(DoubleConsumer functionalInterface) {
		return new DoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends DoubleConsumerX<X>, X extends Exception> DoubleConsumerXAssert.Impl<A, X> assertThat(DoubleConsumerX<X> functionalInterface) {
		return new DoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends CharConsumer> CharConsumerAssert.Impl<A> assertThat(CharConsumer functionalInterface) {
		return new CharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends CharConsumerX<X>, X extends Exception> CharConsumerXAssert.Impl<A, X> assertThat(CharConsumerX<X> functionalInterface) {
		return new CharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanConsumer> BooleanConsumerAssert.Impl<A> assertThat(BooleanConsumer functionalInterface) {
		return new BooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanConsumerX<X>, X extends Exception> BooleanConsumerXAssert.Impl<A, X> assertThat(BooleanConsumerX<X> functionalInterface) {
		return new BooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ByteBiConsumer> ByteBiConsumerAssert.Impl<A> assertThat(ByteBiConsumer functionalInterface) {
		return new ByteBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ByteBiConsumerX<X>, X extends Exception> ByteBiConsumerXAssert.Impl<A, X> assertThat(ByteBiConsumerX<X> functionalInterface) {
		return new ByteBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ShortBiConsumer> ShortBiConsumerAssert.Impl<A> assertThat(ShortBiConsumer functionalInterface) {
		return new ShortBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ShortBiConsumerX<X>, X extends Exception> ShortBiConsumerXAssert.Impl<A, X> assertThat(ShortBiConsumerX<X> functionalInterface) {
		return new ShortBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends IntBiConsumer> IntBiConsumerAssert.Impl<A> assertThat(IntBiConsumer functionalInterface) {
		return new IntBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends IntBiConsumerX<X>, X extends Exception> IntBiConsumerXAssert.Impl<A, X> assertThat(IntBiConsumerX<X> functionalInterface) {
		return new IntBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LongBiConsumer> LongBiConsumerAssert.Impl<A> assertThat(LongBiConsumer functionalInterface) {
		return new LongBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends LongBiConsumerX<X>, X extends Exception> LongBiConsumerXAssert.Impl<A, X> assertThat(LongBiConsumerX<X> functionalInterface) {
		return new LongBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends FloatBiConsumer> FloatBiConsumerAssert.Impl<A> assertThat(FloatBiConsumer functionalInterface) {
		return new FloatBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends FloatBiConsumerX<X>, X extends Exception> FloatBiConsumerXAssert.Impl<A, X> assertThat(FloatBiConsumerX<X> functionalInterface) {
		return new FloatBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends DoubleBiConsumer> DoubleBiConsumerAssert.Impl<A> assertThat(DoubleBiConsumer functionalInterface) {
		return new DoubleBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends DoubleBiConsumerX<X>, X extends Exception> DoubleBiConsumerXAssert.Impl<A, X> assertThat(DoubleBiConsumerX<X> functionalInterface) {
		return new DoubleBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends CharBiConsumer> CharBiConsumerAssert.Impl<A> assertThat(CharBiConsumer functionalInterface) {
		return new CharBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends CharBiConsumerX<X>, X extends Exception> CharBiConsumerXAssert.Impl<A, X> assertThat(CharBiConsumerX<X> functionalInterface) {
		return new CharBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanBiConsumer> BooleanBiConsumerAssert.Impl<A> assertThat(BooleanBiConsumer functionalInterface) {
		return new BooleanBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanBiConsumerX<X>, X extends Exception> BooleanBiConsumerXAssert.Impl<A, X> assertThat(BooleanBiConsumerX<X> functionalInterface) {
		return new BooleanBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanTriConsumer> BooleanTriConsumerAssert.Impl<A> assertThat(BooleanTriConsumer functionalInterface) {
		return new BooleanTriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BooleanTriConsumerX<X>, X extends Exception> BooleanTriConsumerXAssert.Impl<A, X> assertThat(BooleanTriConsumerX<X> functionalInterface) {
		return new BooleanTriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjByteConsumer<T>, T> ObjByteConsumerAssert.Impl<A, T> assertThat(ObjByteConsumer<T> functionalInterface) {
		return new ObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjByteConsumerX<T, X>, T, X extends Exception> ObjByteConsumerXAssert.Impl<A, T, X> assertThat(ObjByteConsumerX<T, X> functionalInterface) {
		return new ObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjShortConsumer<T>, T> ObjShortConsumerAssert.Impl<A, T> assertThat(ObjShortConsumer<T> functionalInterface) {
		return new ObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjShortConsumerX<T, X>, T, X extends Exception> ObjShortConsumerXAssert.Impl<A, T, X> assertThat(ObjShortConsumerX<T, X> functionalInterface) {
		return new ObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjIntConsumer<T>, T> ObjIntConsumerAssert.Impl<A, T> assertThat(ObjIntConsumer<T> functionalInterface) {
		return new ObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjIntConsumerX<T, X>, T, X extends Exception> ObjIntConsumerXAssert.Impl<A, T, X> assertThat(ObjIntConsumerX<T, X> functionalInterface) {
		return new ObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjLongConsumer<T>, T> ObjLongConsumerAssert.Impl<A, T> assertThat(ObjLongConsumer<T> functionalInterface) {
		return new ObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjLongConsumerX<T, X>, T, X extends Exception> ObjLongConsumerXAssert.Impl<A, T, X> assertThat(ObjLongConsumerX<T, X> functionalInterface) {
		return new ObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjFloatConsumer<T>, T> ObjFloatConsumerAssert.Impl<A, T> assertThat(ObjFloatConsumer<T> functionalInterface) {
		return new ObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjFloatConsumerX<T, X>, T, X extends Exception> ObjFloatConsumerXAssert.Impl<A, T, X> assertThat(ObjFloatConsumerX<T, X> functionalInterface) {
		return new ObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.Impl<A, T> assertThat(ObjDoubleConsumer<T> functionalInterface) {
		return new ObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjDoubleConsumerX<T, X>, T, X extends Exception> ObjDoubleConsumerXAssert.Impl<A, T, X> assertThat(ObjDoubleConsumerX<T, X> functionalInterface) {
		return new ObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjCharConsumer<T>, T> ObjCharConsumerAssert.Impl<A, T> assertThat(ObjCharConsumer<T> functionalInterface) {
		return new ObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjCharConsumerX<T, X>, T, X extends Exception> ObjCharConsumerXAssert.Impl<A, T, X> assertThat(ObjCharConsumerX<T, X> functionalInterface) {
		return new ObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjBooleanConsumer<T>, T> ObjBooleanConsumerAssert.Impl<A, T> assertThat(ObjBooleanConsumer<T> functionalInterface) {
		return new ObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends ObjBooleanConsumerX<T, X>, T, X extends Exception> ObjBooleanConsumerXAssert.Impl<A, T, X> assertThat(ObjBooleanConsumerX<T, X> functionalInterface) {
		return new ObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjByteConsumer<T1, T2>, T1, T2> BiObjByteConsumerAssert.Impl<A, T1, T2> assertThat(BiObjByteConsumer<T1, T2> functionalInterface) {
		return new BiObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjByteConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjByteConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjShortConsumer<T1, T2>, T1, T2> BiObjShortConsumerAssert.Impl<A, T1, T2> assertThat(BiObjShortConsumer<T1, T2> functionalInterface) {
		return new BiObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjShortConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjShortConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjIntConsumer<T1, T2>, T1, T2> BiObjIntConsumerAssert.Impl<A, T1, T2> assertThat(BiObjIntConsumer<T1, T2> functionalInterface) {
		return new BiObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjIntConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjIntConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjLongConsumer<T1, T2>, T1, T2> BiObjLongConsumerAssert.Impl<A, T1, T2> assertThat(BiObjLongConsumer<T1, T2> functionalInterface) {
		return new BiObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjLongConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjLongConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjFloatConsumer<T1, T2>, T1, T2> BiObjFloatConsumerAssert.Impl<A, T1, T2> assertThat(BiObjFloatConsumer<T1, T2> functionalInterface) {
		return new BiObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjFloatConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjFloatConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjDoubleConsumer<T1, T2>, T1, T2> BiObjDoubleConsumerAssert.Impl<A, T1, T2> assertThat(BiObjDoubleConsumer<T1, T2> functionalInterface) {
		return new BiObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjDoubleConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjDoubleConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjCharConsumer<T1, T2>, T1, T2> BiObjCharConsumerAssert.Impl<A, T1, T2> assertThat(BiObjCharConsumer<T1, T2> functionalInterface) {
		return new BiObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjCharConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjCharConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjBooleanConsumer<T1, T2>, T1, T2> BiObjBooleanConsumerAssert.Impl<A, T1, T2> assertThat(BiObjBooleanConsumer<T1, T2> functionalInterface) {
		return new BiObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends BiObjBooleanConsumerX<T1, T2, X>, T1, T2, X extends Exception> BiObjBooleanConsumerXAssert.Impl<A, T1, T2, X> assertThat(BiObjBooleanConsumerX<T1, T2, X> functionalInterface) {
		return new BiObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends UnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> UnaryOperatorAssert.Impl<A, RS, T> assertThat(UnaryOperator<T> functionalInterface) {
		return new UnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends UnaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Exception> UnaryOperatorXAssert.Impl<A, RS, T, X> assertThat(UnaryOperatorX<T, X> functionalInterface) {
		return new UnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> BinaryOperatorAssert.Impl<A, RS, T> assertThat(BinaryOperator<T> functionalInterface) {
		return new BinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BinaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Exception> BinaryOperatorXAssert.Impl<A, RS, T, X> assertThat(BinaryOperatorX<T, X> functionalInterface) {
		return new BinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TernaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> TernaryOperatorAssert.Impl<A, RS, T> assertThat(TernaryOperator<T> functionalInterface) {
		return new TernaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TernaryOperatorX<T, X>, RS extends AbstractObjectAssert<RS, T>, T, X extends Exception> TernaryOperatorXAssert.Impl<A, RS, T, X> assertThat(TernaryOperatorX<T, X> functionalInterface) {
		return new TernaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteUnaryOperator, RS extends AbstractByteAssert<RS>> ByteUnaryOperatorAssert.Impl<A, RS> assertThat(ByteUnaryOperator functionalInterface) {
		return new ByteUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteUnaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> ByteUnaryOperatorXAssert.Impl<A, RS, X> assertThat(ByteUnaryOperatorX<X> functionalInterface) {
		return new ByteUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortUnaryOperator, RS extends AbstractShortAssert<RS>> ShortUnaryOperatorAssert.Impl<A, RS> assertThat(ShortUnaryOperator functionalInterface) {
		return new ShortUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortUnaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> ShortUnaryOperatorXAssert.Impl<A, RS, X> assertThat(ShortUnaryOperatorX<X> functionalInterface) {
		return new ShortUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> IntUnaryOperatorAssert.Impl<A, RS> assertThat(IntUnaryOperator functionalInterface) {
		return new IntUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntUnaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> IntUnaryOperatorXAssert.Impl<A, RS, X> assertThat(IntUnaryOperatorX<X> functionalInterface) {
		return new IntUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongUnaryOperator, RS extends AbstractLongAssert<RS>> LongUnaryOperatorAssert.Impl<A, RS> assertThat(LongUnaryOperator functionalInterface) {
		return new LongUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongUnaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> LongUnaryOperatorXAssert.Impl<A, RS, X> assertThat(LongUnaryOperatorX<X> functionalInterface) {
		return new LongUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatUnaryOperator, RS extends AbstractFloatAssert<RS>> FloatUnaryOperatorAssert.Impl<A, RS> assertThat(FloatUnaryOperator functionalInterface) {
		return new FloatUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatUnaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> FloatUnaryOperatorXAssert.Impl<A, RS, X> assertThat(FloatUnaryOperatorX<X> functionalInterface) {
		return new FloatUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleUnaryOperatorAssert.Impl<A, RS> assertThat(DoubleUnaryOperator functionalInterface) {
		return new DoubleUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleUnaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> DoubleUnaryOperatorXAssert.Impl<A, RS, X> assertThat(DoubleUnaryOperatorX<X> functionalInterface) {
		return new DoubleUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharUnaryOperator, RS extends AbstractCharacterAssert<RS>> CharUnaryOperatorAssert.Impl<A, RS> assertThat(CharUnaryOperator functionalInterface) {
		return new CharUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharUnaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> CharUnaryOperatorXAssert.Impl<A, RS, X> assertThat(CharUnaryOperatorX<X> functionalInterface) {
		return new CharUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanUnaryOperator, RS extends AbstractBooleanAssert<RS>> BooleanUnaryOperatorAssert.Impl<A, RS> assertThat(BooleanUnaryOperator functionalInterface) {
		return new BooleanUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanUnaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BooleanUnaryOperatorXAssert.Impl<A, RS, X> assertThat(BooleanUnaryOperatorX<X> functionalInterface) {
		return new BooleanUnaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteBinaryOperator, RS extends AbstractByteAssert<RS>> ByteBinaryOperatorAssert.Impl<A, RS> assertThat(ByteBinaryOperator functionalInterface) {
		return new ByteBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteBinaryOperatorX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> ByteBinaryOperatorXAssert.Impl<A, RS, X> assertThat(ByteBinaryOperatorX<X> functionalInterface) {
		return new ByteBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortBinaryOperator, RS extends AbstractShortAssert<RS>> ShortBinaryOperatorAssert.Impl<A, RS> assertThat(ShortBinaryOperator functionalInterface) {
		return new ShortBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortBinaryOperatorX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> ShortBinaryOperatorXAssert.Impl<A, RS, X> assertThat(ShortBinaryOperatorX<X> functionalInterface) {
		return new ShortBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> IntBinaryOperatorAssert.Impl<A, RS> assertThat(IntBinaryOperator functionalInterface) {
		return new IntBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBinaryOperatorX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> IntBinaryOperatorXAssert.Impl<A, RS, X> assertThat(IntBinaryOperatorX<X> functionalInterface) {
		return new IntBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBinaryOperator, RS extends AbstractLongAssert<RS>> LongBinaryOperatorAssert.Impl<A, RS> assertThat(LongBinaryOperator functionalInterface) {
		return new LongBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBinaryOperatorX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> LongBinaryOperatorXAssert.Impl<A, RS, X> assertThat(LongBinaryOperatorX<X> functionalInterface) {
		return new LongBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatBinaryOperator, RS extends AbstractFloatAssert<RS>> FloatBinaryOperatorAssert.Impl<A, RS> assertThat(FloatBinaryOperator functionalInterface) {
		return new FloatBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatBinaryOperatorX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> FloatBinaryOperatorXAssert.Impl<A, RS, X> assertThat(FloatBinaryOperatorX<X> functionalInterface) {
		return new FloatBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> DoubleBinaryOperatorAssert.Impl<A, RS> assertThat(DoubleBinaryOperator functionalInterface) {
		return new DoubleBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBinaryOperatorX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> DoubleBinaryOperatorXAssert.Impl<A, RS, X> assertThat(DoubleBinaryOperatorX<X> functionalInterface) {
		return new DoubleBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharBinaryOperator, RS extends AbstractCharacterAssert<RS>> CharBinaryOperatorAssert.Impl<A, RS> assertThat(CharBinaryOperator functionalInterface) {
		return new CharBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharBinaryOperatorX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> CharBinaryOperatorXAssert.Impl<A, RS, X> assertThat(CharBinaryOperatorX<X> functionalInterface) {
		return new CharBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanBinaryOperator, RS extends AbstractBooleanAssert<RS>> BooleanBinaryOperatorAssert.Impl<A, RS> assertThat(BooleanBinaryOperator functionalInterface) {
		return new BooleanBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanBinaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BooleanBinaryOperatorXAssert.Impl<A, RS, X> assertThat(BooleanBinaryOperatorX<X> functionalInterface) {
		return new BooleanBinaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanTernaryOperator, RS extends AbstractBooleanAssert<RS>> BooleanTernaryOperatorAssert.Impl<A, RS> assertThat(BooleanTernaryOperator functionalInterface) {
		return new BooleanTernaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanTernaryOperatorX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BooleanTernaryOperatorXAssert.Impl<A, RS, X> assertThat(BooleanTernaryOperatorX<X> functionalInterface) {
		return new BooleanTernaryOperatorXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Function<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> FunctionAssert.Impl<A, RS, T, R> assertThat(Function<T, R> functionalInterface) {
		return new FunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> FunctionXAssert.Impl<A, RS, T, R, X> assertThat(FunctionX<T, R, X> functionalInterface) {
		return new FunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiFunction<T1, T2, R> functionalInterface) {
		return new BiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TriFunction<T1, T2, T3, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R> TriFunctionAssert.Impl<A, RS, T1, T2, T3, R> assertThat(TriFunction<T1, T2, T3, R> functionalInterface) {
		return new TriFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TriFunctionX<T1, T2, T3, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, T3, R, X extends Exception> TriFunctionXAssert.Impl<A, RS, T1, T2, T3, R, X> assertThat(TriFunctionX<T1, T2, T3, R, X> functionalInterface) {
		return new TriFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> ByteFunctionAssert.Impl<A, RS, R> assertThat(ByteFunction<R> functionalInterface) {
		return new ByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> ByteFunctionXAssert.Impl<A, RS, R, X> assertThat(ByteFunctionX<R, X> functionalInterface) {
		return new ByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> ShortFunctionAssert.Impl<A, RS, R> assertThat(ShortFunction<R> functionalInterface) {
		return new ShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> ShortFunctionXAssert.Impl<A, RS, R, X> assertThat(ShortFunctionX<R, X> functionalInterface) {
		return new ShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> IntFunctionAssert.Impl<A, RS, R> assertThat(IntFunction<R> functionalInterface) {
		return new IntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> IntFunctionXAssert.Impl<A, RS, R, X> assertThat(IntFunctionX<R, X> functionalInterface) {
		return new IntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LongFunctionAssert.Impl<A, RS, R> assertThat(LongFunction<R> functionalInterface) {
		return new LongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> LongFunctionXAssert.Impl<A, RS, R, X> assertThat(LongFunctionX<R, X> functionalInterface) {
		return new LongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> FloatFunctionAssert.Impl<A, RS, R> assertThat(FloatFunction<R> functionalInterface) {
		return new FloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> FloatFunctionXAssert.Impl<A, RS, R, X> assertThat(FloatFunctionX<R, X> functionalInterface) {
		return new FloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> DoubleFunctionAssert.Impl<A, RS, R> assertThat(DoubleFunction<R> functionalInterface) {
		return new DoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> DoubleFunctionXAssert.Impl<A, RS, R, X> assertThat(DoubleFunctionX<R, X> functionalInterface) {
		return new DoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> CharFunctionAssert.Impl<A, RS, R> assertThat(CharFunction<R> functionalInterface) {
		return new CharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> CharFunctionXAssert.Impl<A, RS, R, X> assertThat(CharFunctionX<R, X> functionalInterface) {
		return new CharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> BooleanFunctionAssert.Impl<A, RS, R> assertThat(BooleanFunction<R> functionalInterface) {
		return new BooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> BooleanFunctionXAssert.Impl<A, RS, R, X> assertThat(BooleanFunctionX<R, X> functionalInterface) {
		return new BooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> ByteBiFunctionAssert.Impl<A, RS, R> assertThat(ByteBiFunction<R> functionalInterface) {
		return new ByteBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> ByteBiFunctionXAssert.Impl<A, RS, R, X> assertThat(ByteBiFunctionX<R, X> functionalInterface) {
		return new ByteBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> ShortBiFunctionAssert.Impl<A, RS, R> assertThat(ShortBiFunction<R> functionalInterface) {
		return new ShortBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> ShortBiFunctionXAssert.Impl<A, RS, R, X> assertThat(ShortBiFunctionX<R, X> functionalInterface) {
		return new ShortBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> IntBiFunctionAssert.Impl<A, RS, R> assertThat(IntBiFunction<R> functionalInterface) {
		return new IntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> IntBiFunctionXAssert.Impl<A, RS, R, X> assertThat(IntBiFunctionX<R, X> functionalInterface) {
		return new IntBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> LongBiFunctionAssert.Impl<A, RS, R> assertThat(LongBiFunction<R> functionalInterface) {
		return new LongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> LongBiFunctionXAssert.Impl<A, RS, R, X> assertThat(LongBiFunctionX<R, X> functionalInterface) {
		return new LongBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> FloatBiFunctionAssert.Impl<A, RS, R> assertThat(FloatBiFunction<R> functionalInterface) {
		return new FloatBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> FloatBiFunctionXAssert.Impl<A, RS, R, X> assertThat(FloatBiFunctionX<R, X> functionalInterface) {
		return new FloatBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> DoubleBiFunctionAssert.Impl<A, RS, R> assertThat(DoubleBiFunction<R> functionalInterface) {
		return new DoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> DoubleBiFunctionXAssert.Impl<A, RS, R, X> assertThat(DoubleBiFunctionX<R, X> functionalInterface) {
		return new DoubleBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> CharBiFunctionAssert.Impl<A, RS, R> assertThat(CharBiFunction<R> functionalInterface) {
		return new CharBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> CharBiFunctionXAssert.Impl<A, RS, R, X> assertThat(CharBiFunctionX<R, X> functionalInterface) {
		return new CharBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanBiFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> BooleanBiFunctionAssert.Impl<A, RS, R> assertThat(BooleanBiFunction<R> functionalInterface) {
		return new BooleanBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanBiFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> BooleanBiFunctionXAssert.Impl<A, RS, R, X> assertThat(BooleanBiFunctionX<R, X> functionalInterface) {
		return new BooleanBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanTriFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> BooleanTriFunctionAssert.Impl<A, RS, R> assertThat(BooleanTriFunction<R> functionalInterface) {
		return new BooleanTriFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanTriFunctionX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> BooleanTriFunctionXAssert.Impl<A, RS, R, X> assertThat(BooleanTriFunctionX<R, X> functionalInterface) {
		return new BooleanTriFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjByteFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjByteFunctionAssert.Impl<A, RS, T, R> assertThat(ObjByteFunction<T, R> functionalInterface) {
		return new ObjByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjByteFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjByteFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjByteFunctionX<T, R, X> functionalInterface) {
		return new ObjByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjShortFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjShortFunctionAssert.Impl<A, RS, T, R> assertThat(ObjShortFunction<T, R> functionalInterface) {
		return new ObjShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjShortFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjShortFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjShortFunctionX<T, R, X> functionalInterface) {
		return new ObjShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjIntFunctionAssert.Impl<A, RS, T, R> assertThat(ObjIntFunction<T, R> functionalInterface) {
		return new ObjIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjIntFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjIntFunctionX<T, R, X> functionalInterface) {
		return new ObjIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjLongFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjLongFunctionAssert.Impl<A, RS, T, R> assertThat(ObjLongFunction<T, R> functionalInterface) {
		return new ObjLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjLongFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjLongFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjLongFunctionX<T, R, X> functionalInterface) {
		return new ObjLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjFloatFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjFloatFunctionAssert.Impl<A, RS, T, R> assertThat(ObjFloatFunction<T, R> functionalInterface) {
		return new ObjFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjFloatFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjFloatFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjFloatFunctionX<T, R, X> functionalInterface) {
		return new ObjFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjDoubleFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjDoubleFunctionAssert.Impl<A, RS, T, R> assertThat(ObjDoubleFunction<T, R> functionalInterface) {
		return new ObjDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjDoubleFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjDoubleFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjDoubleFunctionX<T, R, X> functionalInterface) {
		return new ObjDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjCharFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjCharFunctionAssert.Impl<A, RS, T, R> assertThat(ObjCharFunction<T, R> functionalInterface) {
		return new ObjCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjCharFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjCharFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjCharFunctionX<T, R, X> functionalInterface) {
		return new ObjCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBooleanFunction<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> ObjBooleanFunctionAssert.Impl<A, RS, T, R> assertThat(ObjBooleanFunction<T, R> functionalInterface) {
		return new ObjBooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBooleanFunctionX<T, R, X>, RS extends AbstractObjectAssert<RS, R>, T, R, X extends Exception> ObjBooleanFunctionXAssert.Impl<A, RS, T, R, X> assertThat(ObjBooleanFunctionX<T, R, X> functionalInterface) {
		return new ObjBooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjByteFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjByteFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjByteFunction<T1, T2, R> functionalInterface) {
		return new BiObjByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjByteFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjByteFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjByteFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjShortFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjShortFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjShortFunction<T1, T2, R> functionalInterface) {
		return new BiObjShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjShortFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjShortFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjShortFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjIntFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjIntFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjIntFunction<T1, T2, R> functionalInterface) {
		return new BiObjIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjIntFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjIntFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjIntFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjLongFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjLongFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjLongFunction<T1, T2, R> functionalInterface) {
		return new BiObjLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjLongFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjLongFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjLongFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjFloatFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjFloatFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjFloatFunction<T1, T2, R> functionalInterface) {
		return new BiObjFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjFloatFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjFloatFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjFloatFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjDoubleFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjDoubleFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjDoubleFunction<T1, T2, R> functionalInterface) {
		return new BiObjDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjDoubleFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjDoubleFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjCharFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjCharFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjCharFunction<T1, T2, R> functionalInterface) {
		return new BiObjCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjCharFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjCharFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(BiObjCharFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBooleanFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> BiObjBooleanFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(BiObjBooleanFunction<T1, T2, R> functionalInterface) {
		return new BiObjBooleanFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBooleanFunctionX<T1, T2, R, X>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R, X extends Exception> BiObjBooleanFunctionXAssert.Impl<A, RS, T1, T2, R, X> assertThat(
			BiObjBooleanFunctionX<T1, T2, R, X> functionalInterface) {
		return new BiObjBooleanFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToByteFunction<T>, RS extends AbstractByteAssert<RS>, T> ToByteFunctionAssert.Impl<A, RS, T> assertThat(ToByteFunction<T> functionalInterface) {
		return new ToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToByteFunctionX<T, X>, RS extends AbstractByteAssert<RS>, T, X extends Exception> ToByteFunctionXAssert.Impl<A, RS, T, X> assertThat(ToByteFunctionX<T, X> functionalInterface) {
		return new ToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToShortFunction<T>, RS extends AbstractShortAssert<RS>, T> ToShortFunctionAssert.Impl<A, RS, T> assertThat(ToShortFunction<T> functionalInterface) {
		return new ToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToShortFunctionX<T, X>, RS extends AbstractShortAssert<RS>, T, X extends Exception> ToShortFunctionXAssert.Impl<A, RS, T, X> assertThat(ToShortFunctionX<T, X> functionalInterface) {
		return new ToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> ToIntFunctionAssert.Impl<A, RS, T> assertThat(ToIntFunction<T> functionalInterface) {
		return new ToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Exception> ToIntFunctionXAssert.Impl<A, RS, T, X> assertThat(ToIntFunctionX<T, X> functionalInterface) {
		return new ToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> ToLongFunctionAssert.Impl<A, RS, T> assertThat(ToLongFunction<T> functionalInterface) {
		return new ToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongFunctionX<T, X>, RS extends AbstractLongAssert<RS>, T, X extends Exception> ToLongFunctionXAssert.Impl<A, RS, T, X> assertThat(ToLongFunctionX<T, X> functionalInterface) {
		return new ToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToFloatFunction<T>, RS extends AbstractFloatAssert<RS>, T> ToFloatFunctionAssert.Impl<A, RS, T> assertThat(ToFloatFunction<T> functionalInterface) {
		return new ToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToFloatFunctionX<T, X>, RS extends AbstractFloatAssert<RS>, T, X extends Exception> ToFloatFunctionXAssert.Impl<A, RS, T, X> assertThat(ToFloatFunctionX<T, X> functionalInterface) {
		return new ToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> ToDoubleFunctionAssert.Impl<A, RS, T> assertThat(ToDoubleFunction<T> functionalInterface) {
		return new ToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleFunctionX<T, X>, RS extends AbstractDoubleAssert<RS>, T, X extends Exception> ToDoubleFunctionXAssert.Impl<A, RS, T, X> assertThat(ToDoubleFunctionX<T, X> functionalInterface) {
		return new ToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToCharFunction<T>, RS extends AbstractCharacterAssert<RS>, T> ToCharFunctionAssert.Impl<A, RS, T> assertThat(ToCharFunction<T> functionalInterface) {
		return new ToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToCharFunctionX<T, X>, RS extends AbstractCharacterAssert<RS>, T, X extends Exception> ToCharFunctionXAssert.Impl<A, RS, T, X> assertThat(ToCharFunctionX<T, X> functionalInterface) {
		return new ToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToByteBiFunction<T1, T2>, RS extends AbstractByteAssert<RS>, T1, T2> ToByteBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToByteBiFunction<T1, T2> functionalInterface) {
		return new ToByteBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToByteBiFunctionX<T1, T2, X>, RS extends AbstractByteAssert<RS>, T1, T2, X extends Exception> ToByteBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToByteBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToByteBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToShortBiFunction<T1, T2>, RS extends AbstractShortAssert<RS>, T1, T2> ToShortBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToShortBiFunction<T1, T2> functionalInterface) {
		return new ToShortBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToShortBiFunctionX<T1, T2, X>, RS extends AbstractShortAssert<RS>, T1, T2, X extends Exception> ToShortBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToShortBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToShortBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> ToIntBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToIntBiFunction<T1, T2> functionalInterface) {
		return new ToIntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToIntBiFunctionX<T1, T2, X>, RS extends AbstractIntegerAssert<RS>, T1, T2, X extends Exception> ToIntBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToIntBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToIntBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> ToLongBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToLongBiFunction<T1, T2> functionalInterface) {
		return new ToLongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToLongBiFunctionX<T1, T2, X>, RS extends AbstractLongAssert<RS>, T1, T2, X extends Exception> ToLongBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToLongBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToLongBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToFloatBiFunction<T1, T2>, RS extends AbstractFloatAssert<RS>, T1, T2> ToFloatBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToFloatBiFunction<T1, T2> functionalInterface) {
		return new ToFloatBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToFloatBiFunctionX<T1, T2, X>, RS extends AbstractFloatAssert<RS>, T1, T2, X extends Exception> ToFloatBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToFloatBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> ToDoubleBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToDoubleBiFunction<T1, T2> functionalInterface) {
		return new ToDoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToDoubleBiFunctionX<T1, T2, X>, RS extends AbstractDoubleAssert<RS>, T1, T2, X extends Exception> ToDoubleBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToDoubleBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToCharBiFunction<T1, T2>, RS extends AbstractCharacterAssert<RS>, T1, T2> ToCharBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(ToCharBiFunction<T1, T2> functionalInterface) {
		return new ToCharBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ToCharBiFunctionX<T1, T2, X>, RS extends AbstractCharacterAssert<RS>, T1, T2, X extends Exception> ToCharBiFunctionXAssert.Impl<A, RS, T1, T2, X> assertThat(ToCharBiFunctionX<T1, T2, X> functionalInterface) {
		return new ToCharBiFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> ObjIntToIntFunctionAssert.Impl<A, RS, T> assertThat(ObjIntToIntFunction<T> functionalInterface) {
		return new ObjIntToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntToIntFunctionX<T, X>, RS extends AbstractIntegerAssert<RS>, T, X extends Exception> ObjIntToIntFunctionXAssert.Impl<A, RS, T, X> assertThat(ObjIntToIntFunctionX<T, X> functionalInterface) {
		return new ObjIntToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToShortFunction, RS extends AbstractShortAssert<RS>> ByteToShortFunctionAssert.Impl<A, RS> assertThat(ByteToShortFunction functionalInterface) {
		return new ByteToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> ByteToShortFunctionXAssert.Impl<A, RS, X> assertThat(ByteToShortFunctionX<X> functionalInterface) {
		return new ByteToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToIntFunction, RS extends AbstractIntegerAssert<RS>> ByteToIntFunctionAssert.Impl<A, RS> assertThat(ByteToIntFunction functionalInterface) {
		return new ByteToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> ByteToIntFunctionXAssert.Impl<A, RS, X> assertThat(ByteToIntFunctionX<X> functionalInterface) {
		return new ByteToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToLongFunction, RS extends AbstractLongAssert<RS>> ByteToLongFunctionAssert.Impl<A, RS> assertThat(ByteToLongFunction functionalInterface) {
		return new ByteToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> ByteToLongFunctionXAssert.Impl<A, RS, X> assertThat(ByteToLongFunctionX<X> functionalInterface) {
		return new ByteToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToFloatFunction, RS extends AbstractFloatAssert<RS>> ByteToFloatFunctionAssert.Impl<A, RS> assertThat(ByteToFloatFunction functionalInterface) {
		return new ByteToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> ByteToFloatFunctionXAssert.Impl<A, RS, X> assertThat(ByteToFloatFunctionX<X> functionalInterface) {
		return new ByteToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToDoubleFunction, RS extends AbstractDoubleAssert<RS>> ByteToDoubleFunctionAssert.Impl<A, RS> assertThat(ByteToDoubleFunction functionalInterface) {
		return new ByteToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> ByteToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(ByteToDoubleFunctionX<X> functionalInterface) {
		return new ByteToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToCharFunction, RS extends AbstractCharacterAssert<RS>> ByteToCharFunctionAssert.Impl<A, RS> assertThat(ByteToCharFunction functionalInterface) {
		return new ByteToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> ByteToCharFunctionXAssert.Impl<A, RS, X> assertThat(ByteToCharFunctionX<X> functionalInterface) {
		return new ByteToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToByteFunction, RS extends AbstractByteAssert<RS>> ShortToByteFunctionAssert.Impl<A, RS> assertThat(ShortToByteFunction functionalInterface) {
		return new ShortToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> ShortToByteFunctionXAssert.Impl<A, RS, X> assertThat(ShortToByteFunctionX<X> functionalInterface) {
		return new ShortToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToIntFunction, RS extends AbstractIntegerAssert<RS>> ShortToIntFunctionAssert.Impl<A, RS> assertThat(ShortToIntFunction functionalInterface) {
		return new ShortToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> ShortToIntFunctionXAssert.Impl<A, RS, X> assertThat(ShortToIntFunctionX<X> functionalInterface) {
		return new ShortToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToLongFunction, RS extends AbstractLongAssert<RS>> ShortToLongFunctionAssert.Impl<A, RS> assertThat(ShortToLongFunction functionalInterface) {
		return new ShortToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> ShortToLongFunctionXAssert.Impl<A, RS, X> assertThat(ShortToLongFunctionX<X> functionalInterface) {
		return new ShortToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToFloatFunction, RS extends AbstractFloatAssert<RS>> ShortToFloatFunctionAssert.Impl<A, RS> assertThat(ShortToFloatFunction functionalInterface) {
		return new ShortToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> ShortToFloatFunctionXAssert.Impl<A, RS, X> assertThat(ShortToFloatFunctionX<X> functionalInterface) {
		return new ShortToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToDoubleFunction, RS extends AbstractDoubleAssert<RS>> ShortToDoubleFunctionAssert.Impl<A, RS> assertThat(ShortToDoubleFunction functionalInterface) {
		return new ShortToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> ShortToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(ShortToDoubleFunctionX<X> functionalInterface) {
		return new ShortToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToCharFunction, RS extends AbstractCharacterAssert<RS>> ShortToCharFunctionAssert.Impl<A, RS> assertThat(ShortToCharFunction functionalInterface) {
		return new ShortToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> ShortToCharFunctionXAssert.Impl<A, RS, X> assertThat(ShortToCharFunctionX<X> functionalInterface) {
		return new ShortToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToByteFunction, RS extends AbstractByteAssert<RS>> IntToByteFunctionAssert.Impl<A, RS> assertThat(IntToByteFunction functionalInterface) {
		return new IntToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> IntToByteFunctionXAssert.Impl<A, RS, X> assertThat(IntToByteFunctionX<X> functionalInterface) {
		return new IntToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToShortFunction, RS extends AbstractShortAssert<RS>> IntToShortFunctionAssert.Impl<A, RS> assertThat(IntToShortFunction functionalInterface) {
		return new IntToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> IntToShortFunctionXAssert.Impl<A, RS, X> assertThat(IntToShortFunctionX<X> functionalInterface) {
		return new IntToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToLongFunction, RS extends AbstractLongAssert<RS>> IntToLongFunctionAssert.Impl<A, RS> assertThat(IntToLongFunction functionalInterface) {
		return new IntToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> IntToLongFunctionXAssert.Impl<A, RS, X> assertThat(IntToLongFunctionX<X> functionalInterface) {
		return new IntToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToFloatFunction, RS extends AbstractFloatAssert<RS>> IntToFloatFunctionAssert.Impl<A, RS> assertThat(IntToFloatFunction functionalInterface) {
		return new IntToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> IntToFloatFunctionXAssert.Impl<A, RS, X> assertThat(IntToFloatFunctionX<X> functionalInterface) {
		return new IntToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> IntToDoubleFunctionAssert.Impl<A, RS> assertThat(IntToDoubleFunction functionalInterface) {
		return new IntToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> IntToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(IntToDoubleFunctionX<X> functionalInterface) {
		return new IntToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToCharFunction, RS extends AbstractCharacterAssert<RS>> IntToCharFunctionAssert.Impl<A, RS> assertThat(IntToCharFunction functionalInterface) {
		return new IntToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> IntToCharFunctionXAssert.Impl<A, RS, X> assertThat(IntToCharFunctionX<X> functionalInterface) {
		return new IntToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToByteFunction, RS extends AbstractByteAssert<RS>> LongToByteFunctionAssert.Impl<A, RS> assertThat(LongToByteFunction functionalInterface) {
		return new LongToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> LongToByteFunctionXAssert.Impl<A, RS, X> assertThat(LongToByteFunctionX<X> functionalInterface) {
		return new LongToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToShortFunction, RS extends AbstractShortAssert<RS>> LongToShortFunctionAssert.Impl<A, RS> assertThat(LongToShortFunction functionalInterface) {
		return new LongToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> LongToShortFunctionXAssert.Impl<A, RS, X> assertThat(LongToShortFunctionX<X> functionalInterface) {
		return new LongToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToIntFunction, RS extends AbstractIntegerAssert<RS>> LongToIntFunctionAssert.Impl<A, RS> assertThat(LongToIntFunction functionalInterface) {
		return new LongToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> LongToIntFunctionXAssert.Impl<A, RS, X> assertThat(LongToIntFunctionX<X> functionalInterface) {
		return new LongToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToFloatFunction, RS extends AbstractFloatAssert<RS>> LongToFloatFunctionAssert.Impl<A, RS> assertThat(LongToFloatFunction functionalInterface) {
		return new LongToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> LongToFloatFunctionXAssert.Impl<A, RS, X> assertThat(LongToFloatFunctionX<X> functionalInterface) {
		return new LongToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> LongToDoubleFunctionAssert.Impl<A, RS> assertThat(LongToDoubleFunction functionalInterface) {
		return new LongToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> LongToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(LongToDoubleFunctionX<X> functionalInterface) {
		return new LongToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToCharFunction, RS extends AbstractCharacterAssert<RS>> LongToCharFunctionAssert.Impl<A, RS> assertThat(LongToCharFunction functionalInterface) {
		return new LongToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> LongToCharFunctionXAssert.Impl<A, RS, X> assertThat(LongToCharFunctionX<X> functionalInterface) {
		return new LongToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToByteFunction, RS extends AbstractByteAssert<RS>> FloatToByteFunctionAssert.Impl<A, RS> assertThat(FloatToByteFunction functionalInterface) {
		return new FloatToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> FloatToByteFunctionXAssert.Impl<A, RS, X> assertThat(FloatToByteFunctionX<X> functionalInterface) {
		return new FloatToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToShortFunction, RS extends AbstractShortAssert<RS>> FloatToShortFunctionAssert.Impl<A, RS> assertThat(FloatToShortFunction functionalInterface) {
		return new FloatToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> FloatToShortFunctionXAssert.Impl<A, RS, X> assertThat(FloatToShortFunctionX<X> functionalInterface) {
		return new FloatToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToIntFunction, RS extends AbstractIntegerAssert<RS>> FloatToIntFunctionAssert.Impl<A, RS> assertThat(FloatToIntFunction functionalInterface) {
		return new FloatToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> FloatToIntFunctionXAssert.Impl<A, RS, X> assertThat(FloatToIntFunctionX<X> functionalInterface) {
		return new FloatToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToLongFunction, RS extends AbstractLongAssert<RS>> FloatToLongFunctionAssert.Impl<A, RS> assertThat(FloatToLongFunction functionalInterface) {
		return new FloatToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> FloatToLongFunctionXAssert.Impl<A, RS, X> assertThat(FloatToLongFunctionX<X> functionalInterface) {
		return new FloatToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToDoubleFunction, RS extends AbstractDoubleAssert<RS>> FloatToDoubleFunctionAssert.Impl<A, RS> assertThat(FloatToDoubleFunction functionalInterface) {
		return new FloatToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> FloatToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(FloatToDoubleFunctionX<X> functionalInterface) {
		return new FloatToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToCharFunction, RS extends AbstractCharacterAssert<RS>> FloatToCharFunctionAssert.Impl<A, RS> assertThat(FloatToCharFunction functionalInterface) {
		return new FloatToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> FloatToCharFunctionXAssert.Impl<A, RS, X> assertThat(FloatToCharFunctionX<X> functionalInterface) {
		return new FloatToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToByteFunction, RS extends AbstractByteAssert<RS>> DoubleToByteFunctionAssert.Impl<A, RS> assertThat(DoubleToByteFunction functionalInterface) {
		return new DoubleToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> DoubleToByteFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToByteFunctionX<X> functionalInterface) {
		return new DoubleToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToShortFunction, RS extends AbstractShortAssert<RS>> DoubleToShortFunctionAssert.Impl<A, RS> assertThat(DoubleToShortFunction functionalInterface) {
		return new DoubleToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> DoubleToShortFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToShortFunctionX<X> functionalInterface) {
		return new DoubleToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> DoubleToIntFunctionAssert.Impl<A, RS> assertThat(DoubleToIntFunction functionalInterface) {
		return new DoubleToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> DoubleToIntFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToIntFunctionX<X> functionalInterface) {
		return new DoubleToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToLongFunction, RS extends AbstractLongAssert<RS>> DoubleToLongFunctionAssert.Impl<A, RS> assertThat(DoubleToLongFunction functionalInterface) {
		return new DoubleToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> DoubleToLongFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToLongFunctionX<X> functionalInterface) {
		return new DoubleToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToFloatFunction, RS extends AbstractFloatAssert<RS>> DoubleToFloatFunctionAssert.Impl<A, RS> assertThat(DoubleToFloatFunction functionalInterface) {
		return new DoubleToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> DoubleToFloatFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToFloatFunctionX<X> functionalInterface) {
		return new DoubleToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToCharFunction, RS extends AbstractCharacterAssert<RS>> DoubleToCharFunctionAssert.Impl<A, RS> assertThat(DoubleToCharFunction functionalInterface) {
		return new DoubleToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> DoubleToCharFunctionXAssert.Impl<A, RS, X> assertThat(DoubleToCharFunctionX<X> functionalInterface) {
		return new DoubleToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToByteFunction, RS extends AbstractByteAssert<RS>> CharToByteFunctionAssert.Impl<A, RS> assertThat(CharToByteFunction functionalInterface) {
		return new CharToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> CharToByteFunctionXAssert.Impl<A, RS, X> assertThat(CharToByteFunctionX<X> functionalInterface) {
		return new CharToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToShortFunction, RS extends AbstractShortAssert<RS>> CharToShortFunctionAssert.Impl<A, RS> assertThat(CharToShortFunction functionalInterface) {
		return new CharToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> CharToShortFunctionXAssert.Impl<A, RS, X> assertThat(CharToShortFunctionX<X> functionalInterface) {
		return new CharToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToIntFunction, RS extends AbstractIntegerAssert<RS>> CharToIntFunctionAssert.Impl<A, RS> assertThat(CharToIntFunction functionalInterface) {
		return new CharToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> CharToIntFunctionXAssert.Impl<A, RS, X> assertThat(CharToIntFunctionX<X> functionalInterface) {
		return new CharToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToLongFunction, RS extends AbstractLongAssert<RS>> CharToLongFunctionAssert.Impl<A, RS> assertThat(CharToLongFunction functionalInterface) {
		return new CharToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> CharToLongFunctionXAssert.Impl<A, RS, X> assertThat(CharToLongFunctionX<X> functionalInterface) {
		return new CharToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToFloatFunction, RS extends AbstractFloatAssert<RS>> CharToFloatFunctionAssert.Impl<A, RS> assertThat(CharToFloatFunction functionalInterface) {
		return new CharToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> CharToFloatFunctionXAssert.Impl<A, RS, X> assertThat(CharToFloatFunctionX<X> functionalInterface) {
		return new CharToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToDoubleFunction, RS extends AbstractDoubleAssert<RS>> CharToDoubleFunctionAssert.Impl<A, RS> assertThat(CharToDoubleFunction functionalInterface) {
		return new CharToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> CharToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(CharToDoubleFunctionX<X> functionalInterface) {
		return new CharToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToByteFunction, RS extends AbstractByteAssert<RS>> BooleanToByteFunctionAssert.Impl<A, RS> assertThat(BooleanToByteFunction functionalInterface) {
		return new BooleanToByteFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToByteFunctionX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> BooleanToByteFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToByteFunctionX<X> functionalInterface) {
		return new BooleanToByteFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToShortFunction, RS extends AbstractShortAssert<RS>> BooleanToShortFunctionAssert.Impl<A, RS> assertThat(BooleanToShortFunction functionalInterface) {
		return new BooleanToShortFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToShortFunctionX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> BooleanToShortFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToShortFunctionX<X> functionalInterface) {
		return new BooleanToShortFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToIntFunction, RS extends AbstractIntegerAssert<RS>> BooleanToIntFunctionAssert.Impl<A, RS> assertThat(BooleanToIntFunction functionalInterface) {
		return new BooleanToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToIntFunctionX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> BooleanToIntFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToIntFunctionX<X> functionalInterface) {
		return new BooleanToIntFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToLongFunction, RS extends AbstractLongAssert<RS>> BooleanToLongFunctionAssert.Impl<A, RS> assertThat(BooleanToLongFunction functionalInterface) {
		return new BooleanToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToLongFunctionX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> BooleanToLongFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToLongFunctionX<X> functionalInterface) {
		return new BooleanToLongFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToFloatFunction, RS extends AbstractFloatAssert<RS>> BooleanToFloatFunctionAssert.Impl<A, RS> assertThat(BooleanToFloatFunction functionalInterface) {
		return new BooleanToFloatFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToFloatFunctionX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> BooleanToFloatFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToFloatFunctionX<X> functionalInterface) {
		return new BooleanToFloatFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToDoubleFunction, RS extends AbstractDoubleAssert<RS>> BooleanToDoubleFunctionAssert.Impl<A, RS> assertThat(BooleanToDoubleFunction functionalInterface) {
		return new BooleanToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToDoubleFunctionX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> BooleanToDoubleFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToDoubleFunctionX<X> functionalInterface) {
		return new BooleanToDoubleFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToCharFunction, RS extends AbstractCharacterAssert<RS>> BooleanToCharFunctionAssert.Impl<A, RS> assertThat(BooleanToCharFunction functionalInterface) {
		return new BooleanToCharFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanToCharFunctionX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> BooleanToCharFunctionXAssert.Impl<A, RS, X> assertThat(BooleanToCharFunctionX<X> functionalInterface) {
		return new BooleanToCharFunctionXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> PredicateAssert.Impl<A, RS, T> assertThat(Predicate<T> functionalInterface) {
		return new PredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends PredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> PredicateXAssert.Impl<A, RS, T, X> assertThat(PredicateX<T, X> functionalInterface) {
		return new PredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiPredicate<T1, T2> functionalInterface) {
		return new BiPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiPredicateX<T1, T2, X> functionalInterface) {
		return new BiPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TriPredicate<T1, T2, T3>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3> TriPredicateAssert.Impl<A, RS, T1, T2, T3> assertThat(TriPredicate<T1, T2, T3> functionalInterface) {
		return new TriPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends TriPredicateX<T1, T2, T3, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, T3, X extends Exception> TriPredicateXAssert.Impl<A, RS, T1, T2, T3, X> assertThat(TriPredicateX<T1, T2, T3, X> functionalInterface) {
		return new TriPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BytePredicate, RS extends AbstractBooleanAssert<RS>> BytePredicateAssert.Impl<A, RS> assertThat(BytePredicate functionalInterface) {
		return new BytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BytePredicateXAssert.Impl<A, RS, X> assertThat(BytePredicateX<X> functionalInterface) {
		return new BytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortPredicate, RS extends AbstractBooleanAssert<RS>> ShortPredicateAssert.Impl<A, RS> assertThat(ShortPredicate functionalInterface) {
		return new ShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> ShortPredicateXAssert.Impl<A, RS, X> assertThat(ShortPredicateX<X> functionalInterface) {
		return new ShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntPredicate, RS extends AbstractBooleanAssert<RS>> IntPredicateAssert.Impl<A, RS> assertThat(IntPredicate functionalInterface) {
		return new IntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> IntPredicateXAssert.Impl<A, RS, X> assertThat(IntPredicateX<X> functionalInterface) {
		return new IntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongPredicate, RS extends AbstractBooleanAssert<RS>> LongPredicateAssert.Impl<A, RS> assertThat(LongPredicate functionalInterface) {
		return new LongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> LongPredicateXAssert.Impl<A, RS, X> assertThat(LongPredicateX<X> functionalInterface) {
		return new LongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatPredicate, RS extends AbstractBooleanAssert<RS>> FloatPredicateAssert.Impl<A, RS> assertThat(FloatPredicate functionalInterface) {
		return new FloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> FloatPredicateXAssert.Impl<A, RS, X> assertThat(FloatPredicateX<X> functionalInterface) {
		return new FloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoublePredicate, RS extends AbstractBooleanAssert<RS>> DoublePredicateAssert.Impl<A, RS> assertThat(DoublePredicate functionalInterface) {
		return new DoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> DoublePredicateXAssert.Impl<A, RS, X> assertThat(DoublePredicateX<X> functionalInterface) {
		return new DoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharPredicate, RS extends AbstractBooleanAssert<RS>> CharPredicateAssert.Impl<A, RS> assertThat(CharPredicate functionalInterface) {
		return new CharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> CharPredicateXAssert.Impl<A, RS, X> assertThat(CharPredicateX<X> functionalInterface) {
		return new CharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiBytePredicate, RS extends AbstractBooleanAssert<RS>> BiBytePredicateAssert.Impl<A, RS> assertThat(BiBytePredicate functionalInterface) {
		return new BiBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiBytePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiBytePredicateXAssert.Impl<A, RS, X> assertThat(BiBytePredicateX<X> functionalInterface) {
		return new BiBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiShortPredicate, RS extends AbstractBooleanAssert<RS>> BiShortPredicateAssert.Impl<A, RS> assertThat(BiShortPredicate functionalInterface) {
		return new BiShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiShortPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiShortPredicateXAssert.Impl<A, RS, X> assertThat(BiShortPredicateX<X> functionalInterface) {
		return new BiShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiIntPredicate, RS extends AbstractBooleanAssert<RS>> BiIntPredicateAssert.Impl<A, RS> assertThat(BiIntPredicate functionalInterface) {
		return new BiIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiIntPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiIntPredicateXAssert.Impl<A, RS, X> assertThat(BiIntPredicateX<X> functionalInterface) {
		return new BiIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiLongPredicate, RS extends AbstractBooleanAssert<RS>> BiLongPredicateAssert.Impl<A, RS> assertThat(BiLongPredicate functionalInterface) {
		return new BiLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiLongPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiLongPredicateXAssert.Impl<A, RS, X> assertThat(BiLongPredicateX<X> functionalInterface) {
		return new BiLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFloatPredicate, RS extends AbstractBooleanAssert<RS>> BiFloatPredicateAssert.Impl<A, RS> assertThat(BiFloatPredicate functionalInterface) {
		return new BiFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiFloatPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiFloatPredicateXAssert.Impl<A, RS, X> assertThat(BiFloatPredicateX<X> functionalInterface) {
		return new BiFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiDoublePredicate, RS extends AbstractBooleanAssert<RS>> BiDoublePredicateAssert.Impl<A, RS> assertThat(BiDoublePredicate functionalInterface) {
		return new BiDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiDoublePredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiDoublePredicateXAssert.Impl<A, RS, X> assertThat(BiDoublePredicateX<X> functionalInterface) {
		return new BiDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiCharPredicate, RS extends AbstractBooleanAssert<RS>> BiCharPredicateAssert.Impl<A, RS> assertThat(BiCharPredicate functionalInterface) {
		return new BiCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiCharPredicateX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BiCharPredicateXAssert.Impl<A, RS, X> assertThat(BiCharPredicateX<X> functionalInterface) {
		return new BiCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBytePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjBytePredicateAssert.Impl<A, RS, T> assertThat(ObjBytePredicate<T> functionalInterface) {
		return new ObjBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBytePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjBytePredicateXAssert.Impl<A, RS, T, X> assertThat(ObjBytePredicateX<T, X> functionalInterface) {
		return new ObjBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjShortPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjShortPredicateAssert.Impl<A, RS, T> assertThat(ObjShortPredicate<T> functionalInterface) {
		return new ObjShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjShortPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjShortPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjShortPredicateX<T, X> functionalInterface) {
		return new ObjShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjIntPredicateAssert.Impl<A, RS, T> assertThat(ObjIntPredicate<T> functionalInterface) {
		return new ObjIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjIntPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjIntPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjIntPredicateX<T, X> functionalInterface) {
		return new ObjIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjLongPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjLongPredicateAssert.Impl<A, RS, T> assertThat(ObjLongPredicate<T> functionalInterface) {
		return new ObjLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjLongPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjLongPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjLongPredicateX<T, X> functionalInterface) {
		return new ObjLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjFloatPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjFloatPredicateAssert.Impl<A, RS, T> assertThat(ObjFloatPredicate<T> functionalInterface) {
		return new ObjFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjFloatPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjFloatPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjFloatPredicateX<T, X> functionalInterface) {
		return new ObjFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjDoublePredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjDoublePredicateAssert.Impl<A, RS, T> assertThat(ObjDoublePredicate<T> functionalInterface) {
		return new ObjDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjDoublePredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjDoublePredicateXAssert.Impl<A, RS, T, X> assertThat(ObjDoublePredicateX<T, X> functionalInterface) {
		return new ObjDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjCharPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjCharPredicateAssert.Impl<A, RS, T> assertThat(ObjCharPredicate<T> functionalInterface) {
		return new ObjCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjCharPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjCharPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjCharPredicateX<T, X> functionalInterface) {
		return new ObjCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBooleanPredicate<T>, RS extends AbstractBooleanAssert<RS>, T> ObjBooleanPredicateAssert.Impl<A, RS, T> assertThat(ObjBooleanPredicate<T> functionalInterface) {
		return new ObjBooleanPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ObjBooleanPredicateX<T, X>, RS extends AbstractBooleanAssert<RS>, T, X extends Exception> ObjBooleanPredicateXAssert.Impl<A, RS, T, X> assertThat(ObjBooleanPredicateX<T, X> functionalInterface) {
		return new ObjBooleanPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBytePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjBytePredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjBytePredicate<T1, T2> functionalInterface) {
		return new BiObjBytePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBytePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjBytePredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjBytePredicateX<T1, T2, X> functionalInterface) {
		return new BiObjBytePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjShortPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjShortPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjShortPredicate<T1, T2> functionalInterface) {
		return new BiObjShortPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjShortPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjShortPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjShortPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjShortPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjIntPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjIntPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjIntPredicate<T1, T2> functionalInterface) {
		return new BiObjIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjIntPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjIntPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjIntPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjIntPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjLongPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjLongPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjLongPredicate<T1, T2> functionalInterface) {
		return new BiObjLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjLongPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjLongPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjLongPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjLongPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjFloatPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjFloatPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjFloatPredicate<T1, T2> functionalInterface) {
		return new BiObjFloatPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjFloatPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjFloatPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjFloatPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjDoublePredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjDoublePredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjDoublePredicate<T1, T2> functionalInterface) {
		return new BiObjDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjDoublePredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjDoublePredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		return new BiObjDoublePredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjCharPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjCharPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjCharPredicate<T1, T2> functionalInterface) {
		return new BiObjCharPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjCharPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjCharPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjCharPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjCharPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBooleanPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> BiObjBooleanPredicateAssert.Impl<A, RS, T1, T2> assertThat(BiObjBooleanPredicate<T1, T2> functionalInterface) {
		return new BiObjBooleanPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BiObjBooleanPredicateX<T1, T2, X>, RS extends AbstractBooleanAssert<RS>, T1, T2, X extends Exception> BiObjBooleanPredicateXAssert.Impl<A, RS, T1, T2, X> assertThat(BiObjBooleanPredicateX<T1, T2, X> functionalInterface) {
		return new BiObjBooleanPredicateXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends Supplier<R>, RS extends AbstractObjectAssert<RS, R>, R> SupplierAssert.Impl<A, RS, R> assertThat(Supplier<R> functionalInterface) {
		return new SupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends SupplierX<R, X>, RS extends AbstractObjectAssert<RS, R>, R, X extends Exception> SupplierXAssert.Impl<A, RS, R, X> assertThat(SupplierX<R, X> functionalInterface) {
		return new SupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteSupplier, RS extends AbstractByteAssert<RS>> ByteSupplierAssert.Impl<A, RS> assertThat(ByteSupplier functionalInterface) {
		return new ByteSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ByteSupplierX<X>, RS extends AbstractByteAssert<RS>, X extends Exception> ByteSupplierXAssert.Impl<A, RS, X> assertThat(ByteSupplierX<X> functionalInterface) {
		return new ByteSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortSupplier, RS extends AbstractShortAssert<RS>> ShortSupplierAssert.Impl<A, RS> assertThat(ShortSupplier functionalInterface) {
		return new ShortSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends ShortSupplierX<X>, RS extends AbstractShortAssert<RS>, X extends Exception> ShortSupplierXAssert.Impl<A, RS, X> assertThat(ShortSupplierX<X> functionalInterface) {
		return new ShortSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntSupplier, RS extends AbstractIntegerAssert<RS>> IntSupplierAssert.Impl<A, RS> assertThat(IntSupplier functionalInterface) {
		return new IntSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends IntSupplierX<X>, RS extends AbstractIntegerAssert<RS>, X extends Exception> IntSupplierXAssert.Impl<A, RS, X> assertThat(IntSupplierX<X> functionalInterface) {
		return new IntSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongSupplier, RS extends AbstractLongAssert<RS>> LongSupplierAssert.Impl<A, RS> assertThat(LongSupplier functionalInterface) {
		return new LongSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends LongSupplierX<X>, RS extends AbstractLongAssert<RS>, X extends Exception> LongSupplierXAssert.Impl<A, RS, X> assertThat(LongSupplierX<X> functionalInterface) {
		return new LongSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatSupplier, RS extends AbstractFloatAssert<RS>> FloatSupplierAssert.Impl<A, RS> assertThat(FloatSupplier functionalInterface) {
		return new FloatSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends FloatSupplierX<X>, RS extends AbstractFloatAssert<RS>, X extends Exception> FloatSupplierXAssert.Impl<A, RS, X> assertThat(FloatSupplierX<X> functionalInterface) {
		return new FloatSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleSupplier, RS extends AbstractDoubleAssert<RS>> DoubleSupplierAssert.Impl<A, RS> assertThat(DoubleSupplier functionalInterface) {
		return new DoubleSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends DoubleSupplierX<X>, RS extends AbstractDoubleAssert<RS>, X extends Exception> DoubleSupplierXAssert.Impl<A, RS, X> assertThat(DoubleSupplierX<X> functionalInterface) {
		return new DoubleSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharSupplier, RS extends AbstractCharacterAssert<RS>> CharSupplierAssert.Impl<A, RS> assertThat(CharSupplier functionalInterface) {
		return new CharSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends CharSupplierX<X>, RS extends AbstractCharacterAssert<RS>, X extends Exception> CharSupplierXAssert.Impl<A, RS, X> assertThat(CharSupplierX<X> functionalInterface) {
		return new CharSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanSupplier, RS extends AbstractBooleanAssert<RS>> BooleanSupplierAssert.Impl<A, RS> assertThat(BooleanSupplier functionalInterface) {
		return new BooleanSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends BooleanSupplierX<X>, RS extends AbstractBooleanAssert<RS>, X extends Exception> BooleanSupplierXAssert.Impl<A, RS, X> assertThat(BooleanSupplierX<X> functionalInterface) {
		return new BooleanSupplierXAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.UnaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> StdUnaryOperatorAssert.Impl<A, RS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) {
		return new StdUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BinaryOperator<T>, RS extends AbstractObjectAssert<RS, T>, T> StdBinaryOperatorAssert.Impl<A, RS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) {
		return new StdBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntUnaryOperator, RS extends AbstractIntegerAssert<RS>> StdIntUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		return new StdIntUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongUnaryOperator, RS extends AbstractLongAssert<RS>> StdLongUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		return new StdLongUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleUnaryOperator, RS extends AbstractDoubleAssert<RS>> StdDoubleUnaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		return new StdDoubleUnaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntBinaryOperator, RS extends AbstractIntegerAssert<RS>> StdIntBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		return new StdIntBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongBinaryOperator, RS extends AbstractLongAssert<RS>> StdLongBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		return new StdLongBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleBinaryOperator, RS extends AbstractDoubleAssert<RS>> StdDoubleBinaryOperatorAssert.Impl<A, RS> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		return new StdDoubleBinaryOperatorAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Function<T, R>, RS extends AbstractObjectAssert<RS, R>, T, R> StdFunctionAssert.Impl<A, RS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) {
		return new StdFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiFunction<T1, T2, R>, RS extends AbstractObjectAssert<RS, R>, T1, T2, R> StdBiFunctionAssert.Impl<A, RS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) {
		return new StdBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> StdIntFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) {
		return new StdIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> StdLongFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) {
		return new StdLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleFunction<R>, RS extends AbstractObjectAssert<RS, R>, R> StdDoubleFunctionAssert.Impl<A, RS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) {
		return new StdDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntFunction<T>, RS extends AbstractIntegerAssert<RS>, T> StdToIntFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		return new StdToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongFunction<T>, RS extends AbstractLongAssert<RS>, T> StdToLongFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		return new StdToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleFunction<T>, RS extends AbstractDoubleAssert<RS>, T> StdToDoubleFunctionAssert.Impl<A, RS, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		return new StdToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToIntBiFunction<T1, T2>, RS extends AbstractIntegerAssert<RS>, T1, T2> StdToIntBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		return new StdToIntBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToLongBiFunction<T1, T2>, RS extends AbstractLongAssert<RS>, T1, T2> StdToLongBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		return new StdToLongBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.ToDoubleBiFunction<T1, T2>, RS extends AbstractDoubleAssert<RS>, T1, T2> StdToDoubleBiFunctionAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		return new StdToDoubleBiFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToLongFunction, RS extends AbstractLongAssert<RS>> StdIntToLongFunctionAssert.Impl<A, RS> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		return new StdIntToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntToDoubleFunction, RS extends AbstractDoubleAssert<RS>> StdIntToDoubleFunctionAssert.Impl<A, RS> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		return new StdIntToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToIntFunction, RS extends AbstractIntegerAssert<RS>> StdLongToIntFunctionAssert.Impl<A, RS> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		return new StdLongToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongToDoubleFunction, RS extends AbstractDoubleAssert<RS>> StdLongToDoubleFunctionAssert.Impl<A, RS> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		return new StdLongToDoubleFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToIntFunction, RS extends AbstractIntegerAssert<RS>> StdDoubleToIntFunctionAssert.Impl<A, RS> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		return new StdDoubleToIntFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleToLongFunction, RS extends AbstractLongAssert<RS>> StdDoubleToLongFunctionAssert.Impl<A, RS> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		return new StdDoubleToLongFunctionAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Predicate<T>, RS extends AbstractBooleanAssert<RS>, T> StdPredicateAssert.Impl<A, RS, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		return new StdPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BiPredicate<T1, T2>, RS extends AbstractBooleanAssert<RS>, T1, T2> StdBiPredicateAssert.Impl<A, RS, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		return new StdBiPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntPredicate, RS extends AbstractBooleanAssert<RS>> StdIntPredicateAssert.Impl<A, RS> assertThat(java.util.function.IntPredicate functionalInterface) {
		return new StdIntPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongPredicate, RS extends AbstractBooleanAssert<RS>> StdLongPredicateAssert.Impl<A, RS> assertThat(java.util.function.LongPredicate functionalInterface) {
		return new StdLongPredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoublePredicate, RS extends AbstractBooleanAssert<RS>> StdDoublePredicateAssert.Impl<A, RS> assertThat(java.util.function.DoublePredicate functionalInterface) {
		return new StdDoublePredicateAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Supplier<R>, RS extends AbstractObjectAssert<RS, R>, R> StdSupplierAssert.Impl<A, RS, R> assertThat(java.util.function.Supplier<R> functionalInterface) {
		return new StdSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.IntSupplier, RS extends AbstractIntegerAssert<RS>> StdIntSupplierAssert.Impl<A, RS> assertThat(java.util.function.IntSupplier functionalInterface) {
		return new StdIntSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.LongSupplier, RS extends AbstractLongAssert<RS>> StdLongSupplierAssert.Impl<A, RS> assertThat(java.util.function.LongSupplier functionalInterface) {
		return new StdLongSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleSupplier, RS extends AbstractDoubleAssert<RS>> StdDoubleSupplierAssert.Impl<A, RS> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		return new StdDoubleSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.BooleanSupplier, RS extends AbstractBooleanAssert<RS>> StdBooleanSupplierAssert.Impl<A, RS> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		return new StdBooleanSupplierAssert.Impl(functionalInterface, Assertions::assertThat);
	}

	@Nonnull
	public static <A extends java.util.function.Consumer<T>, T> StdConsumerAssert.Impl<A, T> assertThat(java.util.function.Consumer<T> functionalInterface) {
		return new StdConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.BiConsumer<T1, T2>, T1, T2> StdBiConsumerAssert.Impl<A, T1, T2> assertThat(java.util.function.BiConsumer<T1, T2> functionalInterface) {
		return new StdBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.IntConsumer> StdIntConsumerAssert.Impl<A> assertThat(java.util.function.IntConsumer functionalInterface) {
		return new StdIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.LongConsumer> StdLongConsumerAssert.Impl<A> assertThat(java.util.function.LongConsumer functionalInterface) {
		return new StdLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.DoubleConsumer> StdDoubleConsumerAssert.Impl<A> assertThat(java.util.function.DoubleConsumer functionalInterface) {
		return new StdDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjIntConsumer<T>, T> StdObjIntConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjIntConsumer<T> functionalInterface) {
		return new StdObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjLongConsumer<T>, T> StdObjLongConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjLongConsumer<T> functionalInterface) {
		return new StdObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	public static <A extends java.util.function.ObjDoubleConsumer<T>, T> StdObjDoubleConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjDoubleConsumer<T> functionalInterface) {
		return new StdObjDoubleConsumerAssert.Impl(functionalInterface);
	}

}
