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
 * Throwing functional interface (lambda) LBooleanBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): boolean b1,boolean b2
 *
 * Co-domain: none
 *
 * @see LBooleanBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanBinaryOperatorX<X extends Throwable> extends MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBooleanBinaryOperatorX: boolean doApplyAsBoolean(boolean b1,boolean b2) throws X";

	public boolean doApplyAsBoolean(boolean b1, boolean b2) throws X;

	default boolean nestingDoApplyAsBoolean(boolean b1, boolean b2) {
		try {
			return this.doApplyAsBoolean(b1, b2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoApplyAsBoolean(boolean b1, boolean b2) {
		return ((LBooleanBinaryOperatorX<RuntimeException>) this).doApplyAsBoolean(b1, b2);
	}

	default <Y extends Throwable> boolean handlingDoApplyAsBoolean(boolean b1, boolean b2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsBoolean(b1, b2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApplyAsBoolean(boolean b1, boolean b2) throws X {
		return doApplyAsBoolean(b1, b2);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b1, boolean b2) throws X {
		return doApplyAsBoolean(b1, b2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(boolean b1, boolean b2) {
		return () -> this.doApplyAsBoolean(b1, b2);
	}

	public static <X extends Throwable> LBooleanBinaryOperatorX<X> constant(boolean r) {
		return (b1, b2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> lX(final @Nonnull LBooleanBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LBooleanBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> wrapX(final @Nonnull LBooleanBinaryOperator other) {
		return (LBooleanBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanBinaryOperatorX<X> negate() {
		return (boolean b1, boolean b2) -> !doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanBinaryOperatorX<X> and(@Nonnull LBooleanBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) && other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanBinaryOperatorX<X> or(@Nonnull LBooleanBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) || other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanBinaryOperatorX<X> xor(@Nonnull LBooleanBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2) -> doApplyAsBoolean(b1, b2) ^ other.doApplyAsBoolean(b1, b2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> isEqual(final boolean v1, final boolean v2) {
		return (b1, b2) -> (b1 == v1) && (b2 == v2);
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 *
	 */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> and() {
		return Boolean::logicalAnd;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> xor() {
		return Boolean::logicalXor;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	public static <X extends Throwable> LBooleanBinaryOperatorX<X> or() {
		return Boolean::logicalOr;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanBinaryOperatorX<X> fromBoolean(@Nonnull final LBooleanUnaryOperatorX<X> before1, @Nonnull final LBooleanUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final boolean v1, final boolean v2) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> from(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanBiFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (boolean b1, boolean b2) -> after.doApply(this.doApplyAsBoolean(b1, b2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanBinaryOperator nest() {
		return this::nestingDoApplyAsBoolean;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsBoolean;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanBinaryOperator shove() {
		return this::shovingDoApplyAsBoolean;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsBoolean;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBooleanBinaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean b1, boolean b2) -> this.handlingDoApplyAsBoolean(b1, b2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBooleanBinaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean b1, boolean b2) -> this.handlingDoApplyAsBoolean(b1, b2, handling);
	}

	// </editor-fold>

}
