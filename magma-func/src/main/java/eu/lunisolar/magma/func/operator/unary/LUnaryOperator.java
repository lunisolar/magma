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

package eu.lunisolar.magma.func.operator.unary;
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
 * Non-throwing functional interface (lambda) LUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: T
 *
 * @see LUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LUnaryOperator<T> extends LUnaryOperatorX<T, RuntimeException>, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LUnaryOperator: T doApply(T t)";

	@Override
	@Deprecated
	// calling this method via LUnaryOperator interface should be discouraged.
	default T apply(T t) {
		return this.nestingDoApply(t);
	}

	@Nullable
	public T doApply(T t);

	default T nestingDoApply(T t) {
		return this.doApply(t);
	}

	default T shovingDoApply(T t) {
		return this.doApply(t);
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T t) {
		return Null.requireNonNull(doApply(t), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> capture(T t) {
		return () -> this.doApply(t);
	}

	public static <T> LUnaryOperator<T> constant(T r) {
		return (t) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> LUnaryOperator<T> l(final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T> LUnaryOperator<T> wrap(final java.util.function.UnaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LUnaryOperator<T> wrap(final @Nonnull LUnaryOperatorX<T, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApply(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsByte(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LToShortFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsShort(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsInt(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsLong(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LToFloatFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsFloat(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LToDoubleFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsDouble(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsChar(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doTest(this.doApply(t));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <V> LUnaryOperator<V> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LUnaryOperator<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LUnaryOperatorX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LUnaryOperator<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LUnaryOperatorX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LUnaryOperator<T> nonNullable() {
		return this::nonNullDoApply;
	}

}
