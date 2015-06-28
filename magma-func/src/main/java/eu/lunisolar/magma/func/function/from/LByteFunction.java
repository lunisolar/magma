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

package eu.lunisolar.magma.func.function.from;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: R
 *
 * @see LByteFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteFunction<R> extends LByteFunctionX<R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LByteFunction: R doApply(byte b)";

	@Nullable
	R doApply(byte b);

	default R nestingDoApply(byte b) {
		return this.doApply(b);
	}

	default R shovingDoApply(byte b) {
		return this.doApply(b);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(byte b) {
		return Null.requireNonNull(doApply(b), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(byte b) {
		return () -> this.doApply(b);
	}

	static <R> LByteFunction<R> constant(R r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LByteFunction<R> l(final @Nonnull LByteFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LByteFunction<R> wrap(final @Nonnull LByteFunctionX<R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteFunction<R> fromByte(@Nonnull final LByteUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LFunction<V1, R> from(@Nonnull final LToByteFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteConsumer then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doAccept(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBoolean(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApply(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteFunction<R> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteFunctionX<R, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteFunction<R> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteFunctionX<R, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LByteFunction<R> nonNullable() {
		return this::nonNullDoApply;
	}

}
