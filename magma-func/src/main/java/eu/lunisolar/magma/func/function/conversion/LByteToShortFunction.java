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
 * Non-throwing functional interface (lambda) LByteToShortFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteToShortFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToShortFunction extends LByteToShortFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LByteToShortFunction: short doApplyAsShort(byte b)";

	public short doApplyAsShort(byte b);

	default short nestingDoApplyAsShort(byte b) {
		return this.doApplyAsShort(b);
	}

	default short shovingDoApplyAsShort(byte b) {
		return this.doApplyAsShort(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(byte b) {
		return doApplyAsShort(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToShortFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier capture(byte b) {
		return () -> this.doApplyAsShort(b);
	}

	public static LByteToShortFunction constant(short r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LByteToShortFunction l(final @Nonnull LByteToShortFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LByteToShortFunction wrap(final @Nonnull LByteToShortFunctionX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteToShortFunction fromByte(@Nonnull final LByteUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final byte v1) -> this.doApplyAsShort(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunction<V1> from(@Nonnull final LToByteFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApply(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LShortToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsByte(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LShortUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsShort(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsInt(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsLong(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LShortToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsFloat(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsDouble(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LShortToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsChar(this.doApplyAsShort(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doTest(this.doApplyAsShort(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToShortFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteToShortFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToShortFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToShortFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
