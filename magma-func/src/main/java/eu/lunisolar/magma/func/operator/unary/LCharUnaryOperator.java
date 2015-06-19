/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
 * Non-throwing functional interface (lambda) LCharUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharUnaryOperator extends LCharUnaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LCharUnaryOperator: char doApplyAsChar(char c)";

	public char doApplyAsChar(char c);

	default char nestingDoApplyAsChar(char c) {
		return this.doApplyAsChar(c);
	}

	default char shovingDoApplyAsChar(char c) {
		return this.doApplyAsChar(c);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(char c) {
		return doApplyAsChar(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier capture(char c) {
		return () -> this.doApplyAsChar(c);
	}

	public static LCharUnaryOperator constant(char r) {
		return (c) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LCharUnaryOperator l(final @Nonnull LCharUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LCharUnaryOperator wrap(final @Nonnull LCharUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharUnaryOperator fromChar(@Nonnull final LCharUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final char v1) -> this.doApplyAsChar(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToCharFunction<V1> from(@Nonnull final LToCharFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsChar(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApply(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsByte(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToShortFunction thenToShort(@Nonnull LCharToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsShort(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsInt(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsLong(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToFloatFunction thenToFloat(@Nonnull LCharToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsFloat(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharToDoubleFunction thenToDouble(@Nonnull LCharToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsDouble(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsChar(this.doApplyAsChar(c));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LCharPredicate thenToBoolean(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doTest(this.doApplyAsChar(c));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static LCharUnaryOperator identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharUnaryOperator nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharUnaryOperatorX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperator shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperatorX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
