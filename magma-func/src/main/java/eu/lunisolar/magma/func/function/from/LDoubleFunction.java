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
 * Non-throwing functional interface (lambda) LDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: R
 *
 * @see LDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleFunction<R> extends LDoubleFunctionX<R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LDoubleFunction: R doApply(double d)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDoubleFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(double d) {
		return this.nestingDoApply(d);
	}

	@Nullable
	R doApply(double d);

	default R nestingDoApply(double d) {
		return this.doApply(d);
	}

	default R shovingDoApply(double d) {
		return this.doApply(d);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(double d) {
		return Null.requireNonNull(doApply(d), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureDFunc(double d) {
		return () -> this.doApply(d);
	}

	static <R> LDoubleFunction<R> constant(R r) {
		return d -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LDoubleFunction<R> l(final @Nonnull LDoubleFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R> LDoubleFunction<R> wrap(final java.util.function.DoubleFunction<R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LDoubleFunction<R> wrap(final @Nonnull LDoubleFunctionX<R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LDoubleFunction<R> dFuncFromDouble(@Nonnull final LDoubleUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LFunction<V1, R> dFuncFrom(@Nonnull final LToDoubleFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleConsumer then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doAccept(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsByte(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunction thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsShort(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsInt(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsLong(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunction thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsFloat(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperator thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsDouble(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsChar(this.doApply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicate thenToBoolean(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doTest(this.doApply(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleFunction<R> nestingDFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleFunctionX<R, RuntimeException> nestingDFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleFunction<R> shovingDFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleFunctionX<R, RuntimeException> shovingDFuncX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LDoubleFunction<R> nonNullDFunc() {
		return this::nonNullDoApply;
	}

}
