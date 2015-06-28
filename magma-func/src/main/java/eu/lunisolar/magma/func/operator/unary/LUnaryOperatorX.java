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
 * Throwing functional interface (lambda) LUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: T
 *
 * @see LUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LUnaryOperatorX<T, X extends Throwable> extends java.util.function.UnaryOperator<T>, MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LUnaryOperatorX: T doApply(T t) throws X";

	@Override
	@Deprecated
	// calling this method via LUnaryOperatorX interface should be discouraged.
	default T apply(T t) {
		return this.nestingDoApply(t);
	}

	@Nullable
	T doApply(T t) throws X;

	default T nestingDoApply(T t) {
		try {
			return this.doApply(t);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default T shovingDoApply(T t) {
		return ((LUnaryOperatorX<T, RuntimeException>) this).doApply(t);
	}

	default <Y extends Throwable> T handlingDoApply(T t, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(t);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T t) throws X {
		return Null.requireNonNull(doApply(t), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<T, X> capture(T t) {
		return () -> this.doApply(t);
	}

	static <T, X extends Throwable> LUnaryOperatorX<T, X> constant(T r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LUnaryOperatorX<T, X> lX(final @Nonnull LUnaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LUnaryOperatorX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LUnaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T, X extends Throwable> LUnaryOperatorX<T, X> wrap(final java.util.function.UnaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LUnaryOperatorX<T, X> wrapX(final @Nonnull LUnaryOperator<T> other) {
		return (LUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> then(@Nonnull LFunctionX<? super T, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> thenToByte(@Nonnull LToByteFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> thenToShort(@Nonnull LToShortFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> thenToInt(@Nonnull LToIntFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> thenToLong(@Nonnull LToLongFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> thenToFloat(@Nonnull LToFloatFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> thenToDouble(@Nonnull LToDoubleFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> thenToChar(@Nonnull LToCharFunctionX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doApply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LPredicateX<T, X> thenToBoolean(@Nonnull LPredicateX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doTest(this.doApply(t));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V, X extends Throwable> LUnaryOperatorX<V, X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LUnaryOperator<T> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LUnaryOperatorX<T, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LUnaryOperator<T> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LUnaryOperatorX<T, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LUnaryOperatorX<T, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LUnaryOperator<T> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return t -> this.handlingDoApply(t, handling);
	}

	@Nonnull
	default <Y extends Throwable> LUnaryOperatorX<T, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return t -> this.handlingDoApply(t, handling);
	}

	// </editor-fold>

}
