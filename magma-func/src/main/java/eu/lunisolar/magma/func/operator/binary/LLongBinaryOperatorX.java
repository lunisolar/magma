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

package eu.lunisolar.magma.func.operator.binary;
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
 * Throwing functional interface (lambda) LLongBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): long l1,long l2
 *
 * Co-domain: none
 *
 * @see LLongBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongBinaryOperatorX<X extends Throwable> extends java.util.function.LongBinaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongBinaryOperatorX: long doApplyAsLong(long l1,long l2) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongBinaryOperatorX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(long l1, long l2) {
		return this.nestingDoApplyAsLong(l1, l2);
	}

	long doApplyAsLong(long l1, long l2) throws X;

	default long nestingDoApplyAsLong(long l1, long l2) {
		try {
			return this.doApplyAsLong(l1, l2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(long l1, long l2) {
		return ((LLongBinaryOperatorX<RuntimeException>) this).doApplyAsLong(l1, l2);
	}

	default <Y extends Throwable> long handlingDoApplyAsLong(long l1, long l2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(l1, l2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(long l1, long l2) throws X {
		return doApplyAsLong(l1, l2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> captureLongBinaryOp(long l1, long l2) {
		return () -> this.doApplyAsLong(l1, l2);
	}

	static <X extends Throwable> LLongBinaryOperatorX<X> constant(long r) {
		return (l1, l2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> lX(final @Nonnull LLongBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> wrap(final java.util.function.LongBinaryOperator other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> wrapX(final @Nonnull LLongBinaryOperator other) {
		return (LLongBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> min() {
		return Long::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static <X extends Throwable> LLongBinaryOperatorX<X> max() {
		return Long::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongBinaryOperatorX<X> longBinaryOpFromLong(@Nonnull final LLongUnaryOperatorX<X> before1, @Nonnull final LLongUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final long v1, final long v2) -> this.doApplyAsLong(before1.doApplyAsLong(v1), before2.doApplyAsLong(v2));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2> LToLongBiFunctionX<V1, V2, X> longBinaryOpFrom(@Nonnull final LToLongFunctionX<? super V1, X> before1, @Nonnull final LToLongFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsLong(before1.doApplyAsLong(v1), before2.doApplyAsLong(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LLongBiFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (long l1, long l2) -> after.doApply(this.doApplyAsLong(l1, l2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongBinaryOperator nestingLongBinaryOp() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongBinaryOperatorX<RuntimeException> nestingLongBinaryOpX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongBinaryOperator shovingLongBinaryOp() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongBinaryOperatorX<RuntimeException> shovingLongBinaryOpX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLongBinaryOperator handleLongBinaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (long l1, long l2) -> this.handlingDoApplyAsLong(l1, l2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLongBinaryOperatorX<Y> handleLongBinaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (long l1, long l2) -> this.handlingDoApplyAsLong(l1, l2, handling);
	}

	// </editor-fold>

}
