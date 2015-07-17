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
 * Non-throwing functional interface (lambda) LLongUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongUnaryOperator extends LLongUnaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LLongUnaryOperator: long doApplyAsLong(long l)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongUnaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(long l) {
		return this.nestingDoApplyAsLong(l);
	}

	long doApplyAsLong(long l);

	default long nestingDoApplyAsLong(long l) {
		return this.doApplyAsLong(l);
	}

	default long shovingDoApplyAsLong(long l) {
		return this.doApplyAsLong(l);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(long l) {
		return doApplyAsLong(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureLongUnaryOp(long l) {
		return () -> this.doApplyAsLong(l);
	}

	static LLongUnaryOperator constant(long r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongUnaryOperator l(final @Nonnull LLongUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongUnaryOperator wrap(final java.util.function.LongUnaryOperator other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperator wrap(final @Nonnull LLongUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongUnaryOperator longUnaryOpFromLong(@Nonnull final LLongUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToLongFunction<V1> longUnaryOpFrom(@Nonnull final LToLongFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToShortFunction thenToShort(@Nonnull LLongToShortFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToFloatFunction thenToFloat(@Nonnull LLongToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToDoubleFunction thenToDouble(@Nonnull LLongToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongPredicate thenToBoolean(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApplyAsLong(l));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LLongUnaryOperator identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongUnaryOperator nestingLongUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongUnaryOperatorX<RuntimeException> nestingLongUnaryOpX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongUnaryOperator shovingLongUnaryOp() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongUnaryOperatorX<RuntimeException> shovingLongUnaryOpX() {
		return this;
	}

	// </editor-fold>

}
