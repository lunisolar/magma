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
 * Non-throwing functional interface (lambda) LBooleanBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): boolean b1,boolean b2
 *
 * Co-domain: none
 *
 * @see LBooleanBinaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanBinaryOperator extends LBooleanBinaryOperatorX<RuntimeException>, MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LBooleanBinaryOperator: boolean doApplyAsBoolean(boolean b1,boolean b2)";

	boolean doApplyAsBoolean(boolean b1, boolean b2);

	default boolean nestingDoApplyAsBoolean(boolean b1, boolean b2) {
		return this.doApplyAsBoolean(b1, b2);
	}

	default boolean shovingDoApplyAsBoolean(boolean b1, boolean b2) {
		return this.doApplyAsBoolean(b1, b2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApplyAsBoolean(boolean b1, boolean b2) {
		return doApplyAsBoolean(b1, b2);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b1, boolean b2) {
		return doApplyAsBoolean(b1, b2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanBinaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(boolean b1, boolean b2) {
		return () -> this.doApplyAsBoolean(b1, b2);
	}

	static LBooleanBinaryOperator constant(boolean r) {
		return (b1, b2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBooleanBinaryOperator l(final @Nonnull LBooleanBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBooleanBinaryOperator wrap(final @Nonnull LBooleanBinaryOperatorX<X> other) {
		return other::nestingDoApplyAsBoolean;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanBinaryOperator negate() {
		return (boolean b1, boolean b2) -> !doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanBinaryOperator and(@Nonnull LBooleanBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) && other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanBinaryOperator or(@Nonnull LBooleanBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) || other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanBinaryOperator xor(@Nonnull LBooleanBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) ^ other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static LBooleanBinaryOperator isEqual(final boolean v1, final boolean v2) {
		return (b1, b2) -> (b1 == v1) && (b2 == v2);
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 *
	 */
	@Nonnull
	static LBooleanBinaryOperator and() {
		return Boolean::logicalAnd;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static LBooleanBinaryOperator xor() {
		return Boolean::logicalXor;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static LBooleanBinaryOperator or() {
		return Boolean::logicalOr;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanBinaryOperator fromBoolean(@Nonnull final LBooleanUnaryOperator before1, @Nonnull final LBooleanUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final boolean v1, final boolean v2) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> from(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanBiFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (boolean b1, boolean b2) -> after.doApply(this.doApplyAsBoolean(b1, b2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanBinaryOperator nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanBinaryOperatorX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanBinaryOperator shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanBinaryOperatorX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
