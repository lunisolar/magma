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
 * Non-throwing functional interface (lambda) LBooleanFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: R
 *
 * @see LBooleanFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanFunction<R> extends LBooleanFunctionX<R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LBooleanFunction: R doApply(boolean b)";

	@Nullable
	R doApply(boolean b);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default R nestingDoApply(boolean b) {
		return this.doApply(b);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoApply(boolean b) {
		return this.doApply(b);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(boolean b) {
		return Null.requireNonNull(doApply(b), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBoolFunc(boolean b) {
		return () -> this.doApply(b);
	}

	/** Creates function that always returns the same value. */
	static <R> LBooleanFunction<R> constant(R r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBooleanFunction<R> l(final @Nonnull LBooleanFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LBooleanFunction<R> wrap(final @Nonnull LBooleanFunctionX<R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBooleanFunction<R> boolFuncComposeBoolean(@Nonnull final LLogicalOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApply(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LFunction<V1, R> boolFuncCompose(@Nonnull final LPredicate<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doTest(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBooleanFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanConsumer then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doAccept(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToShortFunction thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToFloatFunction thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToDoubleFunction thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBoolean(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApply(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanFunction<R> nestingBoolFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanFunctionX<R, RuntimeException> nestingBoolFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanFunction<R> shovingBoolFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanFunctionX<R, RuntimeException> shovingBoolFuncX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBooleanFunction<R> nonNullBoolFunc() {
		return this::nonNullDoApply;
	}

}
