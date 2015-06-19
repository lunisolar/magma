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
 * Non-throwing functional interface (lambda) LBooleanUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBooleanUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanUnaryOperator extends LBooleanUnaryOperatorX<RuntimeException>, MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBooleanUnaryOperator: boolean doApplyAsBoolean(boolean b)";

	public boolean doApplyAsBoolean(boolean b);

	default boolean nestingDoApplyAsBoolean(boolean b) {
		return this.doApplyAsBoolean(b);
	}

	default boolean shovingDoApplyAsBoolean(boolean b) {
		return this.doApplyAsBoolean(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApplyAsBoolean(boolean b) {
		return doApplyAsBoolean(b);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b) {
		return doApplyAsBoolean(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(boolean b) {
		return () -> this.doApplyAsBoolean(b);
	}

	public static LBooleanUnaryOperator constant(boolean r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LBooleanUnaryOperator l(final @Nonnull LBooleanUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LBooleanUnaryOperator wrap(final @Nonnull LBooleanUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsBoolean;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanUnaryOperator negate() {
		return (boolean b) -> !doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanUnaryOperator and(@Nonnull LBooleanUnaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b) -> doApplyAsBoolean(b) && other.doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperator or(@Nonnull LBooleanUnaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b) -> doApplyAsBoolean(b) || other.doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperator xor(@Nonnull LBooleanUnaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b) -> doApplyAsBoolean(b) ^ other.doApplyAsBoolean(b);
	}

	@Nonnull
	public static LBooleanUnaryOperator isEqual(boolean target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanUnaryOperator fromBoolean(@Nonnull final LBooleanUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final boolean v1) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LPredicate<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApply(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToByteFunction thenToByte(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsByte(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsShort(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToIntFunction thenToInt(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsInt(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToLongFunction thenToLong(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsLong(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsFloat(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsDouble(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToCharFunction thenToChar(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsChar(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanUnaryOperator thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsBoolean(this.doApplyAsBoolean(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static LBooleanUnaryOperator identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanUnaryOperator nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanUnaryOperatorX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanUnaryOperator shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanUnaryOperatorX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
