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

package eu.lunisolar.magma.func.function;
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
 * Non-throwing functional interface (lambda) LFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: R
 *
 * @see LFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFunction<T, R> extends LFunctionX<T, R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LFunction: R doApply(T t)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(T t) {
		return this.nestingDoApply(t);
	}

	@Nullable
	R doApply(T t);

	default R nestingDoApply(T t) {
		return this.doApply(t);
	}

	default R shovingDoApply(T t) {
		return this.doApply(t);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T t) {
		return Null.requireNonNull(doApply(t), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureFunc(T t) {
		return () -> this.doApply(t);
	}

	static <T, R> LFunction<T, R> constant(R r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LFunction<T, R> l(final @Nonnull LFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T, R> LFunction<T, R> wrap(final java.util.function.Function<T, R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, R, X extends Throwable> LFunction<T, R> wrap(final @Nonnull LFunctionX<T, R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LFunction<V1, R> funcFrom(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LConsumer<T> then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doAccept(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doApply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doTest(this.doApply(t));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V> LFunction<V, V> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFunction<T, R> nestingFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFunctionX<T, R, RuntimeException> nestingFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFunction<T, R> shovingFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFunctionX<T, R, RuntimeException> shovingFuncX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LFunction<T, R> nonNullFunc() {
		return this::nonNullDoApply;
	}

}
