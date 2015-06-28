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

package eu.lunisolar.magma.func.function.to;
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
 * Non-throwing functional interface (lambda) LToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LToByteFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToByteFunction<T> extends LToByteFunctionX<T, RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LToByteFunction: byte doApplyAsByte(T t)";

	byte doApplyAsByte(T t);

	default byte nestingDoApplyAsByte(T t) {
		return this.doApplyAsByte(t);
	}

	default byte shovingDoApplyAsByte(T t) {
		return this.doApplyAsByte(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(T t) {
		return doApplyAsByte(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToByteFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(T t) {
		return () -> this.doApplyAsByte(t);
	}

	static <T> LToByteFunction<T> constant(byte r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToByteFunction<T> l(final @Nonnull LToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LToByteFunction<T> wrap(final @Nonnull LToByteFunctionX<T, X> other) {
		return other::nestingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunction<V1> from(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LByteToShortFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LByteToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LByteToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return t -> after.doTest(this.doApplyAsByte(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToByteFunction<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToByteFunctionX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteFunction<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteFunctionX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
