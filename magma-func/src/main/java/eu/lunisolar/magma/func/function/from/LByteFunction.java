/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte a1
 *
 * Co-domain: R
 *
 * @see LByteFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteFunction<R> extends LByteFunctionX<R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LByteFunction: R doApply(byte a1)";

	@Nullable
	R doApply(byte a1);

	default R tupleApply(LByteSingle args) {
		return doApply(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(byte a1) {
		return this.doApply(a1);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoApply(byte a1) {
		return this.doApply(a1);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(byte a1) {
		return Null.requireNonNull(doApply(a1), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureByteFunc(byte a1) {
		return () -> this.doApply(a1);
	}

	/** Creates function that always returns the same value. */
	static <R> LByteFunction<R> constant(R r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LByteFunction<R> l(final @Nonnull LByteFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <R> R call(byte a1, final @Nonnull LByteFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LByteFunction<R> wrap(final @Nonnull LByteFunctionX<R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <R> LByteFunction<R> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LByteFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LByteFunction<R> safe(final @Nullable LByteFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LByteFunction<R>> safeSupplier(final @Nullable LSupplier<LByteFunction<R>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LByteFunction<R> byteFuncComposeByte(@Nonnull final LByteUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsByte(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LFunction<V1, R> byteFuncCompose(@Nonnull final LToByteFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteConsumer then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doAccept(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApply(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApply(a1));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteFunction<R> nestingByteFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteFunctionX<R, RuntimeException> nestingByteFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteFunction<R> shovingByteFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteFunctionX<R, RuntimeException> shovingByteFuncX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LByteFunction<R> nonNullByteFunc() {
		return this::nonNullDoApply;
	}

}
