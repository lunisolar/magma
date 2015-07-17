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
 * Non-throwing functional interface (lambda) LIntUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntUnaryOperator extends LIntUnaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LIntUnaryOperator: int doApplyAsInt(int i)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntUnaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(int i) {
		return this.nestingDoApplyAsInt(i);
	}

	int doApplyAsInt(int i);

	default int nestingDoApplyAsInt(int i) {
		return this.doApplyAsInt(i);
	}

	default int shovingDoApplyAsInt(int i) {
		return this.doApplyAsInt(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int i) {
		return doApplyAsInt(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureIUnaryOp(int i) {
		return () -> this.doApplyAsInt(i);
	}

	static LIntUnaryOperator constant(int r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntUnaryOperator l(final @Nonnull LIntUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntUnaryOperator wrap(final java.util.function.IntUnaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperator wrap(final @Nonnull LIntUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntUnaryOperator iUnaryOpFromInt(@Nonnull final LIntUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToIntFunction<V1> iUnaryOpFrom(@Nonnull final LToIntFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToShortFunction thenToShort(@Nonnull LIntToShortFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToFloatFunction thenToFloat(@Nonnull LIntToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToDoubleFunction thenToDouble(@Nonnull LIntToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntPredicate thenToBoolean(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return i -> after.doTest(this.doApplyAsInt(i));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LIntUnaryOperator identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntUnaryOperator nestingIUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntUnaryOperatorX<RuntimeException> nestingIUnaryOpX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperator shovingIUnaryOp() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperatorX<RuntimeException> shovingIUnaryOpX() {
		return this;
	}

	// </editor-fold>

}
