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
 * Non-throwing functional interface (lambda) LShortUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortUnaryOperator extends LShortUnaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LShortUnaryOperator: short doApplyAsShort(short s)";

	short doApplyAsShort(short s);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default short nestingDoApplyAsShort(short s) {
		return this.doApplyAsShort(s);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default short shovingDoApplyAsShort(short s) {
		return this.doApplyAsShort(s);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(short s) {
		return doApplyAsShort(s);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier captureShortUnaryOp(short s) {
		return () -> this.doApplyAsShort(s);
	}

	/** Creates function that always returns the same value. */
	static LShortUnaryOperator constant(short r) {
		return s -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LShortUnaryOperator l(final @Nonnull LShortUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperator wrap(final @Nonnull LShortUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LShortUnaryOperator shortUnaryOpComposeShort(@Nonnull final LShortUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsShort(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToShortFunction<V1> shortUnaryOpCompose(@Nonnull final LToShortFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LShortFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApply(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToByteFunction thenToByte(@Nonnull LShortToByteFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsByte(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortUnaryOperator thenToShort(@Nonnull LShortUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsShort(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsInt(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsLong(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToFloatFunction thenToFloat(@Nonnull LShortToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsFloat(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToDoubleFunction thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsDouble(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToCharFunction thenToChar(@Nonnull LShortToCharFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsChar(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortPredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Null.nonNullArg(after, "after");
		return s -> after.doTest(this.doApplyAsShort(s));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LShortUnaryOperator identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortUnaryOperator nestingShortUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortUnaryOperatorX<RuntimeException> nestingShortUnaryOpX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperator shovingShortUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperatorX<RuntimeException> shovingShortUnaryOpX() {
		return this;
	}

	// </editor-fold>

}
