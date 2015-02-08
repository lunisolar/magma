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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
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
public interface FunctionalAssertions<OS> extends BasicAssertions<OS> {

	// <editor-fold desc="functions">

	@Nonnull
	default <A extends UnaryOperator<T>, T> UnaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(UnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new UnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends UnaryOperatorX<T, X>, T, X extends Exception> UnaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(UnaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new UnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BinaryOperator<T>, T> BinaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(BinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BinaryOperatorX<T, X>, T, X extends Exception> BinaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(BinaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TernaryOperator<T>, T> TernaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(TernaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new TernaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TernaryOperatorX<T, X>, T, X extends Exception> TernaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(TernaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new TernaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteUnaryOperator> ByteUnaryOperatorAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(ByteUnaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteUnaryOperatorX<X>, X extends Exception> ByteUnaryOperatorXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(ByteUnaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortUnaryOperator> ShortUnaryOperatorAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(ShortUnaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortUnaryOperatorX<X>, X extends Exception> ShortUnaryOperatorXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(ShortUnaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntUnaryOperator> IntUnaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(IntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntUnaryOperatorX<X>, X extends Exception> IntUnaryOperatorXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(IntUnaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongUnaryOperator> LongUnaryOperatorAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(LongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongUnaryOperatorX<X>, X extends Exception> LongUnaryOperatorXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(LongUnaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatUnaryOperator> FloatUnaryOperatorAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(FloatUnaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatUnaryOperatorX<X>, X extends Exception> FloatUnaryOperatorXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(FloatUnaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleUnaryOperator> DoubleUnaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(DoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleUnaryOperatorX<X>, X extends Exception> DoubleUnaryOperatorXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(DoubleUnaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharUnaryOperator> CharUnaryOperatorAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(CharUnaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharUnaryOperatorX<X>, X extends Exception> CharUnaryOperatorXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(CharUnaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanUnaryOperator> BooleanUnaryOperatorAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BooleanUnaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanUnaryOperatorX<X>, X extends Exception> BooleanUnaryOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BooleanUnaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteBinaryOperator> ByteBinaryOperatorAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(ByteBinaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteBinaryOperatorX<X>, X extends Exception> ByteBinaryOperatorXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(ByteBinaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortBinaryOperator> ShortBinaryOperatorAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(ShortBinaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortBinaryOperatorX<X>, X extends Exception> ShortBinaryOperatorXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(ShortBinaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntBinaryOperator> IntBinaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(IntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntBinaryOperatorX<X>, X extends Exception> IntBinaryOperatorXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(IntBinaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongBinaryOperator> LongBinaryOperatorAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(LongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongBinaryOperatorX<X>, X extends Exception> LongBinaryOperatorXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(LongBinaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatBinaryOperator> FloatBinaryOperatorAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(FloatBinaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatBinaryOperatorX<X>, X extends Exception> FloatBinaryOperatorXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(FloatBinaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBinaryOperator> DoubleBinaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(DoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBinaryOperatorX<X>, X extends Exception> DoubleBinaryOperatorXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(DoubleBinaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharBinaryOperator> CharBinaryOperatorAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(CharBinaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharBinaryOperatorX<X>, X extends Exception> CharBinaryOperatorXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(CharBinaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanBinaryOperator> BooleanBinaryOperatorAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BooleanBinaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanBinaryOperatorX<X>, X extends Exception> BooleanBinaryOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BooleanBinaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanTernaryOperator> BooleanTernaryOperatorAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BooleanTernaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanTernaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanTernaryOperatorX<X>, X extends Exception> BooleanTernaryOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BooleanTernaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanTernaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends Function<T, R>, T, R> FunctionAssert.Impl<A, ? extends OS, T, R> assertThat(Function<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FunctionX<T, R, X>, T, R, X extends Exception> FunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(FunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiFunction<T1, T2, R>, T1, T2, R> BiFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TriFunction<T1, T2, T3, R>, T1, T2, T3, R> TriFunctionAssert.Impl<A, ? extends OS, T1, T2, T3, R> assertThat(TriFunction<T1, T2, T3, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new TriFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TriFunctionX<T1, T2, T3, R, X>, T1, T2, T3, R, X extends Exception> TriFunctionXAssert.Impl<A, ? extends OS, T1, T2, T3, R, X> assertThat(TriFunctionX<T1, T2, T3, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new TriFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteFunction<R>, R> ByteFunctionAssert.Impl<A, ? extends OS, R> assertThat(ByteFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteFunctionX<R, X>, R, X extends Exception> ByteFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(ByteFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortFunction<R>, R> ShortFunctionAssert.Impl<A, ? extends OS, R> assertThat(ShortFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortFunctionX<R, X>, R, X extends Exception> ShortFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(ShortFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntFunction<R>, R> IntFunctionAssert.Impl<A, ? extends OS, R> assertThat(IntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new IntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntFunctionX<R, X>, R, X extends Exception> IntFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(IntFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new IntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongFunction<R>, R> LongFunctionAssert.Impl<A, ? extends OS, R> assertThat(LongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new LongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongFunctionX<R, X>, R, X extends Exception> LongFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LongFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new LongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatFunction<R>, R> FloatFunctionAssert.Impl<A, ? extends OS, R> assertThat(FloatFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatFunctionX<R, X>, R, X extends Exception> FloatFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(FloatFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleFunction<R>, R> DoubleFunctionAssert.Impl<A, ? extends OS, R> assertThat(DoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new DoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleFunctionX<R, X>, R, X extends Exception> DoubleFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(DoubleFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new DoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharFunction<R>, R> CharFunctionAssert.Impl<A, ? extends OS, R> assertThat(CharFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new CharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharFunctionX<R, X>, R, X extends Exception> CharFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(CharFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new CharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanFunction<R>, R> BooleanFunctionAssert.Impl<A, ? extends OS, R> assertThat(BooleanFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanFunctionX<R, X>, R, X extends Exception> BooleanFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(BooleanFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteBiFunction<R>, R> ByteBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(ByteBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ByteBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteBiFunctionX<R, X>, R, X extends Exception> ByteBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(ByteBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ByteBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortBiFunction<R>, R> ShortBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(ShortBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ShortBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortBiFunctionX<R, X>, R, X extends Exception> ShortBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(ShortBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ShortBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntBiFunction<R>, R> IntBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(IntBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new IntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntBiFunctionX<R, X>, R, X extends Exception> IntBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(IntBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new IntBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongBiFunction<R>, R> LongBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LongBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new LongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongBiFunctionX<R, X>, R, X extends Exception> LongBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LongBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new LongBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatBiFunction<R>, R> FloatBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(FloatBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FloatBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatBiFunctionX<R, X>, R, X extends Exception> FloatBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(FloatBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new FloatBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBiFunction<R>, R> DoubleBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(DoubleBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new DoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBiFunctionX<R, X>, R, X extends Exception> DoubleBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(DoubleBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new DoubleBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharBiFunction<R>, R> CharBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(CharBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new CharBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharBiFunctionX<R, X>, R, X extends Exception> CharBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(CharBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new CharBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanBiFunction<R>, R> BooleanBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(BooleanBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanBiFunctionX<R, X>, R, X extends Exception> BooleanBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(BooleanBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanTriFunction<R>, R> BooleanTriFunctionAssert.Impl<A, ? extends OS, R> assertThat(BooleanTriFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanTriFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanTriFunctionX<R, X>, R, X extends Exception> BooleanTriFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(BooleanTriFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BooleanTriFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjByteFunction<T, R>, T, R> ObjByteFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjByteFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjByteFunctionX<T, R, X>, T, R, X extends Exception> ObjByteFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjByteFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjShortFunction<T, R>, T, R> ObjShortFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjShortFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjShortFunctionX<T, R, X>, T, R, X extends Exception> ObjShortFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjShortFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntFunction<T, R>, T, R> ObjIntFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjIntFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntFunctionX<T, R, X>, T, R, X extends Exception> ObjIntFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjIntFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjLongFunction<T, R>, T, R> ObjLongFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjLongFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjLongFunctionX<T, R, X>, T, R, X extends Exception> ObjLongFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjLongFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjFloatFunction<T, R>, T, R> ObjFloatFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjFloatFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjFloatFunctionX<T, R, X>, T, R, X extends Exception> ObjFloatFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjFloatFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjDoubleFunction<T, R>, T, R> ObjDoubleFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjDoubleFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjDoubleFunctionX<T, R, X>, T, R, X extends Exception> ObjDoubleFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjDoubleFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjCharFunction<T, R>, T, R> ObjCharFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjCharFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjCharFunctionX<T, R, X>, T, R, X extends Exception> ObjCharFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjCharFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBooleanFunction<T, R>, T, R> ObjBooleanFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(ObjBooleanFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjBooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBooleanFunctionX<T, R, X>, T, R, X extends Exception> ObjBooleanFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(ObjBooleanFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new ObjBooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjByteFunction<T1, T2, R>, T1, T2, R> BiObjByteFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjByteFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjByteFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjByteFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjByteFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjShortFunction<T1, T2, R>, T1, T2, R> BiObjShortFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjShortFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjShortFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjShortFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjShortFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjIntFunction<T1, T2, R>, T1, T2, R> BiObjIntFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjIntFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjIntFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjIntFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjIntFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjLongFunction<T1, T2, R>, T1, T2, R> BiObjLongFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjLongFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjLongFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjLongFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjLongFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjFloatFunction<T1, T2, R>, T1, T2, R> BiObjFloatFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjFloatFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjFloatFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjFloatFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjFloatFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjDoubleFunction<T1, T2, R>, T1, T2, R> BiObjDoubleFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjDoubleFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjDoubleFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjDoubleFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjCharFunction<T1, T2, R>, T1, T2, R> BiObjCharFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjCharFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjCharFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjCharFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjCharFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBooleanFunction<T1, T2, R>, T1, T2, R> BiObjBooleanFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(BiObjBooleanFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjBooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBooleanFunctionX<T1, T2, R, X>, T1, T2, R, X extends Exception> BiObjBooleanFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(BiObjBooleanFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new BiObjBooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToByteFunction<T>, T> ToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>, T> assertThat(ToByteFunction<T> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToByteFunctionX<T, X>, T, X extends Exception> ToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, T, X> assertThat(ToByteFunctionX<T, X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToShortFunction<T>, T> ToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>, T> assertThat(ToShortFunction<T> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToShortFunctionX<T, X>, T, X extends Exception> ToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, T, X> assertThat(ToShortFunctionX<T, X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToIntFunction<T>, T> ToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T> assertThat(ToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToIntFunctionX<T, X>, T, X extends Exception> ToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T, X> assertThat(ToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToLongFunction<T>, T> ToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>, T> assertThat(ToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToLongFunctionX<T, X>, T, X extends Exception> ToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, T, X> assertThat(ToLongFunctionX<T, X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToFloatFunction<T>, T> ToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>, T> assertThat(ToFloatFunction<T> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToFloatFunctionX<T, X>, T, X extends Exception> ToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, T, X> assertThat(ToFloatFunctionX<T, X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleFunction<T>, T> ToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T> assertThat(ToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleFunctionX<T, X>, T, X extends Exception> ToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T, X> assertThat(ToDoubleFunctionX<T, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToCharFunction<T>, T> ToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>, T> assertThat(ToCharFunction<T> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToCharFunctionX<T, X>, T, X extends Exception> ToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, T, X> assertThat(ToCharFunctionX<T, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToByteBiFunction<T1, T2>, T1, T2> ToByteBiFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>, T1, T2> assertThat(ToByteBiFunction<T1, T2> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ToByteBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToByteBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToByteBiFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, T1, T2, X> assertThat(ToByteBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ToByteBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToShortBiFunction<T1, T2>, T1, T2> ToShortBiFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>, T1, T2> assertThat(ToShortBiFunction<T1, T2> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ToShortBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToShortBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToShortBiFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, T1, T2, X> assertThat(ToShortBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ToShortBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToIntBiFunction<T1, T2>, T1, T2> ToIntBiFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T1, T2> assertThat(ToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ToIntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToIntBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToIntBiFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T1, T2, X> assertThat(ToIntBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ToIntBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToLongBiFunction<T1, T2>, T1, T2> ToLongBiFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>, T1, T2> assertThat(ToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ToLongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToLongBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToLongBiFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, T1, T2, X> assertThat(ToLongBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ToLongBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToFloatBiFunction<T1, T2>, T1, T2> ToFloatBiFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>, T1, T2> assertThat(ToFloatBiFunction<T1, T2> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ToFloatBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToFloatBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToFloatBiFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, T1, T2, X> assertThat(ToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ToFloatBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleBiFunction<T1, T2>, T1, T2> ToDoubleBiFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T1, T2> assertThat(ToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ToDoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToDoubleBiFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T1, T2, X> assertThat(ToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ToDoubleBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToCharBiFunction<T1, T2>, T1, T2> ToCharBiFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>, T1, T2> assertThat(ToCharBiFunction<T1, T2> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ToCharBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ToCharBiFunctionX<T1, T2, X>, T1, T2, X extends Exception> ToCharBiFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, T1, T2, X> assertThat(ToCharBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ToCharBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntToIntFunction<T>, T> ObjIntToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T> assertThat(ObjIntToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ObjIntToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntToIntFunctionX<T, X>, T, X extends Exception> ObjIntToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T, X> assertThat(ObjIntToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ObjIntToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToShortFunction> ByteToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(ByteToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ByteToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToShortFunctionX<X>, X extends Exception> ByteToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(ByteToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ByteToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToIntFunction> ByteToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(ByteToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ByteToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToIntFunctionX<X>, X extends Exception> ByteToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(ByteToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ByteToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToLongFunction> ByteToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(ByteToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ByteToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToLongFunctionX<X>, X extends Exception> ByteToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(ByteToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ByteToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToFloatFunction> ByteToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(ByteToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ByteToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToFloatFunctionX<X>, X extends Exception> ByteToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(ByteToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ByteToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToDoubleFunction> ByteToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(ByteToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ByteToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToDoubleFunctionX<X>, X extends Exception> ByteToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(ByteToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ByteToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToCharFunction> ByteToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(ByteToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ByteToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteToCharFunctionX<X>, X extends Exception> ByteToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(ByteToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ByteToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToByteFunction> ShortToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(ShortToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ShortToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToByteFunctionX<X>, X extends Exception> ShortToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(ShortToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ShortToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToIntFunction> ShortToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(ShortToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ShortToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToIntFunctionX<X>, X extends Exception> ShortToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(ShortToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new ShortToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToLongFunction> ShortToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(ShortToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ShortToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToLongFunctionX<X>, X extends Exception> ShortToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(ShortToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new ShortToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToFloatFunction> ShortToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(ShortToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ShortToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToFloatFunctionX<X>, X extends Exception> ShortToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(ShortToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new ShortToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToDoubleFunction> ShortToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(ShortToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ShortToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToDoubleFunctionX<X>, X extends Exception> ShortToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(ShortToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new ShortToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToCharFunction> ShortToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(ShortToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ShortToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortToCharFunctionX<X>, X extends Exception> ShortToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(ShortToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new ShortToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToByteFunction> IntToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(IntToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new IntToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToByteFunctionX<X>, X extends Exception> IntToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(IntToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new IntToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToShortFunction> IntToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(IntToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new IntToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToShortFunctionX<X>, X extends Exception> IntToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(IntToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new IntToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToLongFunction> IntToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(IntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new IntToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToLongFunctionX<X>, X extends Exception> IntToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(IntToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new IntToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToFloatFunction> IntToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(IntToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new IntToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToFloatFunctionX<X>, X extends Exception> IntToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(IntToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new IntToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToDoubleFunction> IntToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(IntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new IntToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToDoubleFunctionX<X>, X extends Exception> IntToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(IntToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new IntToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToCharFunction> IntToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(IntToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new IntToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntToCharFunctionX<X>, X extends Exception> IntToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(IntToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new IntToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToByteFunction> LongToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(LongToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new LongToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToByteFunctionX<X>, X extends Exception> LongToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(LongToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new LongToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToShortFunction> LongToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(LongToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new LongToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToShortFunctionX<X>, X extends Exception> LongToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(LongToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new LongToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToIntFunction> LongToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(LongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new LongToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToIntFunctionX<X>, X extends Exception> LongToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(LongToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new LongToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToFloatFunction> LongToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(LongToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new LongToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToFloatFunctionX<X>, X extends Exception> LongToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(LongToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new LongToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToDoubleFunction> LongToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(LongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new LongToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToDoubleFunctionX<X>, X extends Exception> LongToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(LongToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new LongToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToCharFunction> LongToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(LongToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new LongToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongToCharFunctionX<X>, X extends Exception> LongToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(LongToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new LongToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToByteFunction> FloatToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(FloatToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new FloatToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToByteFunctionX<X>, X extends Exception> FloatToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(FloatToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new FloatToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToShortFunction> FloatToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(FloatToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new FloatToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToShortFunctionX<X>, X extends Exception> FloatToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(FloatToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new FloatToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToIntFunction> FloatToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(FloatToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new FloatToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToIntFunctionX<X>, X extends Exception> FloatToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(FloatToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new FloatToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToLongFunction> FloatToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(FloatToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new FloatToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToLongFunctionX<X>, X extends Exception> FloatToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(FloatToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new FloatToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToDoubleFunction> FloatToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(FloatToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new FloatToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToDoubleFunctionX<X>, X extends Exception> FloatToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(FloatToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new FloatToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToCharFunction> FloatToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(FloatToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new FloatToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatToCharFunctionX<X>, X extends Exception> FloatToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(FloatToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new FloatToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToByteFunction> DoubleToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(DoubleToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new DoubleToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToByteFunctionX<X>, X extends Exception> DoubleToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(DoubleToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new DoubleToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToShortFunction> DoubleToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(DoubleToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new DoubleToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToShortFunctionX<X>, X extends Exception> DoubleToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(DoubleToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new DoubleToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToIntFunction> DoubleToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(DoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new DoubleToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToIntFunctionX<X>, X extends Exception> DoubleToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(DoubleToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new DoubleToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToLongFunction> DoubleToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(DoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new DoubleToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToLongFunctionX<X>, X extends Exception> DoubleToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(DoubleToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new DoubleToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToFloatFunction> DoubleToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(DoubleToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new DoubleToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToFloatFunctionX<X>, X extends Exception> DoubleToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(DoubleToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new DoubleToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToCharFunction> DoubleToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(DoubleToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new DoubleToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToCharFunctionX<X>, X extends Exception> DoubleToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(DoubleToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new DoubleToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToByteFunction> CharToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(CharToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new CharToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToByteFunctionX<X>, X extends Exception> CharToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(CharToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new CharToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToShortFunction> CharToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(CharToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new CharToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToShortFunctionX<X>, X extends Exception> CharToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(CharToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new CharToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToIntFunction> CharToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(CharToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new CharToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToIntFunctionX<X>, X extends Exception> CharToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(CharToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new CharToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToLongFunction> CharToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(CharToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new CharToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToLongFunctionX<X>, X extends Exception> CharToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(CharToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new CharToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToFloatFunction> CharToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(CharToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new CharToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToFloatFunctionX<X>, X extends Exception> CharToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(CharToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new CharToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToDoubleFunction> CharToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(CharToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new CharToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharToDoubleFunctionX<X>, X extends Exception> CharToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(CharToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new CharToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToByteFunction> BooleanToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(BooleanToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new BooleanToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToByteFunctionX<X>, X extends Exception> BooleanToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(BooleanToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new BooleanToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToShortFunction> BooleanToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(BooleanToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new BooleanToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToShortFunctionX<X>, X extends Exception> BooleanToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(BooleanToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new BooleanToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToIntFunction> BooleanToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(BooleanToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new BooleanToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToIntFunctionX<X>, X extends Exception> BooleanToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(BooleanToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new BooleanToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToLongFunction> BooleanToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(BooleanToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new BooleanToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToLongFunctionX<X>, X extends Exception> BooleanToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(BooleanToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new BooleanToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToFloatFunction> BooleanToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(BooleanToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new BooleanToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToFloatFunctionX<X>, X extends Exception> BooleanToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(BooleanToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new BooleanToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToDoubleFunction> BooleanToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(BooleanToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new BooleanToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToDoubleFunctionX<X>, X extends Exception> BooleanToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(BooleanToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new BooleanToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToCharFunction> BooleanToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(BooleanToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new BooleanToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanToCharFunctionX<X>, X extends Exception> BooleanToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(BooleanToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new BooleanToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends Predicate<T>, T> PredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(Predicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new PredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends PredicateX<T, X>, T, X extends Exception> PredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(PredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new PredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiPredicate<T1, T2>, T1, T2> BiPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TriPredicate<T1, T2, T3>, T1, T2, T3> TriPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, T3> assertThat(TriPredicate<T1, T2, T3> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new TriPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends TriPredicateX<T1, T2, T3, X>, T1, T2, T3, X extends Exception> TriPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, T3, X> assertThat(TriPredicateX<T1, T2, T3, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new TriPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BytePredicate> BytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BytePredicateX<X>, X extends Exception> BytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortPredicate> ShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(ShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortPredicateX<X>, X extends Exception> ShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(ShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntPredicate> IntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(IntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new IntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntPredicateX<X>, X extends Exception> IntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(IntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new IntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongPredicate> LongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(LongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new LongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongPredicateX<X>, X extends Exception> LongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(LongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new LongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatPredicate> FloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(FloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new FloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatPredicateX<X>, X extends Exception> FloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(FloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new FloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoublePredicate> DoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(DoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new DoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoublePredicateX<X>, X extends Exception> DoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(DoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new DoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharPredicate> CharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(CharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new CharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharPredicateX<X>, X extends Exception> CharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(CharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new CharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiBytePredicate> BiBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiBytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiBytePredicateX<X>, X extends Exception> BiBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiBytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiShortPredicate> BiShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiShortPredicateX<X>, X extends Exception> BiShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiIntPredicate> BiIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiIntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiIntPredicateX<X>, X extends Exception> BiIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiIntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiLongPredicate> BiLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiLongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiLongPredicateX<X>, X extends Exception> BiLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiLongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiFloatPredicate> BiFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiFloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiFloatPredicateX<X>, X extends Exception> BiFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiFloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiDoublePredicate> BiDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiDoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiDoublePredicateX<X>, X extends Exception> BiDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiDoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiCharPredicate> BiCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BiCharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiCharPredicateX<X>, X extends Exception> BiCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BiCharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBytePredicate<T>, T> ObjBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjBytePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBytePredicateX<T, X>, T, X extends Exception> ObjBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjBytePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjShortPredicate<T>, T> ObjShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjShortPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjShortPredicateX<T, X>, T, X extends Exception> ObjShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjShortPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntPredicate<T>, T> ObjIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjIntPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjIntPredicateX<T, X>, T, X extends Exception> ObjIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjIntPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjLongPredicate<T>, T> ObjLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjLongPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjLongPredicateX<T, X>, T, X extends Exception> ObjLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjLongPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjFloatPredicate<T>, T> ObjFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjFloatPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjFloatPredicateX<T, X>, T, X extends Exception> ObjFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjFloatPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjDoublePredicate<T>, T> ObjDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjDoublePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjDoublePredicateX<T, X>, T, X extends Exception> ObjDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjDoublePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjCharPredicate<T>, T> ObjCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjCharPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjCharPredicateX<T, X>, T, X extends Exception> ObjCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjCharPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBooleanPredicate<T>, T> ObjBooleanPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(ObjBooleanPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjBooleanPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ObjBooleanPredicateX<T, X>, T, X extends Exception> ObjBooleanPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T, X> assertThat(ObjBooleanPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new ObjBooleanPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBytePredicate<T1, T2>, T1, T2> BiObjBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjBytePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBytePredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjBytePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjShortPredicate<T1, T2>, T1, T2> BiObjShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjShortPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjShortPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjShortPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjIntPredicate<T1, T2>, T1, T2> BiObjIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjIntPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjIntPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjIntPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjLongPredicate<T1, T2>, T1, T2> BiObjLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjLongPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjLongPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjLongPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjFloatPredicate<T1, T2>, T1, T2> BiObjFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjFloatPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjFloatPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjDoublePredicate<T1, T2>, T1, T2> BiObjDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjDoublePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjDoublePredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjCharPredicate<T1, T2>, T1, T2> BiObjCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjCharPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjCharPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjCharPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBooleanPredicate<T1, T2>, T1, T2> BiObjBooleanPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(BiObjBooleanPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjBooleanPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BiObjBooleanPredicateX<T1, T2, X>, T1, T2, X extends Exception> BiObjBooleanPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2, X> assertThat(BiObjBooleanPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BiObjBooleanPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	// </editor-fold>

	// <editor-fold desc="suppliers">

	@Nonnull
	default <A extends Supplier<R>, R> SupplierAssert.Impl<A, ? extends OS, R> assertThat(Supplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new SupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends SupplierX<R, X>, R, X extends Exception> SupplierXAssert.Impl<A, ? extends OS, R, X> assertThat(SupplierX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new SupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteSupplier> ByteSupplierAssert.Impl<A, ? extends AbstractByteAssert<?>> assertThat(ByteSupplier functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ByteSupplierX<X>, X extends Exception> ByteSupplierXAssert.Impl<A, ? extends AbstractByteAssert<?>, X> assertThat(ByteSupplierX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert<?>> assertFunc = this::assertThat;
		return new ByteSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortSupplier> ShortSupplierAssert.Impl<A, ? extends AbstractShortAssert<?>> assertThat(ShortSupplier functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends ShortSupplierX<X>, X extends Exception> ShortSupplierXAssert.Impl<A, ? extends AbstractShortAssert<?>, X> assertThat(ShortSupplierX<X> functionalInterface) {
		Function<Short, AbstractShortAssert<?>> assertFunc = this::assertThat;
		return new ShortSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntSupplier> IntSupplierAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(IntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends IntSupplierX<X>, X extends Exception> IntSupplierXAssert.Impl<A, ? extends AbstractIntegerAssert<?>, X> assertThat(IntSupplierX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new IntSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongSupplier> LongSupplierAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(LongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LongSupplierX<X>, X extends Exception> LongSupplierXAssert.Impl<A, ? extends AbstractLongAssert<?>, X> assertThat(LongSupplierX<X> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new LongSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatSupplier> FloatSupplierAssert.Impl<A, ? extends AbstractFloatAssert<?>> assertThat(FloatSupplier functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends FloatSupplierX<X>, X extends Exception> FloatSupplierXAssert.Impl<A, ? extends AbstractFloatAssert<?>, X> assertThat(FloatSupplierX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert<?>> assertFunc = this::assertThat;
		return new FloatSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleSupplier> DoubleSupplierAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(DoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends DoubleSupplierX<X>, X extends Exception> DoubleSupplierXAssert.Impl<A, ? extends AbstractDoubleAssert<?>, X> assertThat(DoubleSupplierX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new DoubleSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharSupplier> CharSupplierAssert.Impl<A, ? extends AbstractCharacterAssert<?>> assertThat(CharSupplier functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends CharSupplierX<X>, X extends Exception> CharSupplierXAssert.Impl<A, ? extends AbstractCharacterAssert<?>, X> assertThat(CharSupplierX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert<?>> assertFunc = this::assertThat;
		return new CharSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanSupplier> BooleanSupplierAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(BooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends BooleanSupplierX<X>, X extends Exception> BooleanSupplierXAssert.Impl<A, ? extends AbstractBooleanAssert<?>, X> assertThat(BooleanSupplierX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new BooleanSupplierXAssert.Impl(functionalInterface, assertFunc);
	}
	// </editor-fold>

	// <editor-fold desc="jre">

	@Nonnull
	default <A extends java.util.function.UnaryOperator<T>, T> StdUnaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BinaryOperator<T>, T> StdBinaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntUnaryOperator> StdIntUnaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdIntUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongUnaryOperator> StdLongUnaryOperatorAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdLongUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleUnaryOperator> StdDoubleUnaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdDoubleUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntBinaryOperator> StdIntBinaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdIntBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongBinaryOperator> StdLongBinaryOperatorAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdLongBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleBinaryOperator> StdDoubleBinaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdDoubleBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Function<T, R>, T, R> StdFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiFunction<T1, T2, R>, T1, T2, R> StdBiFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntFunction<R>, R> StdIntFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongFunction<R>, R> StdLongFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleFunction<R>, R> StdDoubleFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntFunction<T>, T> StdToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongFunction<T>, T> StdToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleFunction<T>, T> StdToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntBiFunction<T1, T2>, T1, T2> StdToIntBiFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdToIntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongBiFunction<T1, T2>, T1, T2> StdToLongBiFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdToLongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleBiFunction<T1, T2>, T1, T2> StdToDoubleBiFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdToDoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToLongFunction> StdIntToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdIntToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToDoubleFunction> StdIntToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdIntToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToIntFunction> StdLongToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdLongToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToDoubleFunction> StdLongToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdLongToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToIntFunction> StdDoubleToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdDoubleToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToLongFunction> StdDoubleToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdDoubleToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Predicate<T>, T> StdPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiPredicate<T1, T2>, T1, T2> StdBiPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdBiPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntPredicate> StdIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(java.util.function.IntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongPredicate> StdLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(java.util.function.LongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoublePredicate> StdDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(java.util.function.DoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Supplier<R>, R> StdSupplierAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.Supplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & Assert
		Function<Object, OS> assertFunc = this::assertThat;
		return new StdSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntSupplier> StdIntSupplierAssert.Impl<A, ? extends AbstractIntegerAssert<?>> assertThat(java.util.function.IntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert<?>> assertFunc = this::assertThat;
		return new StdIntSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongSupplier> StdLongSupplierAssert.Impl<A, ? extends AbstractLongAssert<?>> assertThat(java.util.function.LongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert<?>> assertFunc = this::assertThat;
		return new StdLongSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleSupplier> StdDoubleSupplierAssert.Impl<A, ? extends AbstractDoubleAssert<?>> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert<?>> assertFunc = this::assertThat;
		return new StdDoubleSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BooleanSupplier> StdBooleanSupplierAssert.Impl<A, ? extends AbstractBooleanAssert<?>> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert<?>> assertFunc = this::assertThat;
		return new StdBooleanSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	// </editor-fold>
}
