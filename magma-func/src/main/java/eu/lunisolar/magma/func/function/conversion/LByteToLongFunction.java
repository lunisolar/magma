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

package eu.lunisolar.magma.func.function.conversion;
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
 * Non-throwing functional interface (lambda) LByteToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteToLongFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToLongFunction extends LByteToLongFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LByteToLongFunction: long doApplyAsLong(byte b)";

	public long doApplyAsLong(byte b);

	default long nestingDoApplyAsLong(byte b) {
		return this.doApplyAsLong(b);
	}

	default long shovingDoApplyAsLong(byte b) {
		return this.doApplyAsLong(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(byte b) {
		return doApplyAsLong(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToLongFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(byte b) {
		return () -> this.doApplyAsLong(b);
	}

	public static LByteToLongFunction constant(long r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LByteToLongFunction l(final @Nonnull LByteToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LByteToLongFunction wrap(final @Nonnull LByteToLongFunctionX<X> other) {
		return other::nestingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteToLongFunction fromByte(@Nonnull final LByteUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final byte v1) -> this.doApplyAsLong(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToLongFunction<V1> from(@Nonnull final LToByteFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsLong(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApply(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsByte(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LLongToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsShort(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsInt(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsLong(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LLongToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsFloat(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LLongToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsDouble(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsChar(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBoolean(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doTest(this.doApplyAsLong(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToLongFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteToLongFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToLongFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToLongFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
