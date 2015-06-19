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
 * Throwing functional interface (lambda) LByteToLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteToLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToLongFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LByteToLongFunctionX: long doApplyAsLong(byte b) throws X";

	public long doApplyAsLong(byte b) throws X;

	default long nestingDoApplyAsLong(byte b) {
		try {
			return this.doApplyAsLong(b);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(byte b) {
		return ((LByteToLongFunctionX<RuntimeException>) this).doApplyAsLong(b);
	}

	default <Y extends Throwable> long handlingDoApplyAsLong(byte b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(b);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(byte b) throws X {
		return doApplyAsLong(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> capture(byte b) {
		return () -> this.doApplyAsLong(b);
	}

	public static <X extends Throwable> LByteToLongFunctionX<X> constant(long r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LByteToLongFunctionX<X> lX(final @Nonnull LByteToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LByteToLongFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LByteToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LByteToLongFunctionX<X> wrapX(final @Nonnull LByteToLongFunction other) {
		return (LByteToLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteToLongFunctionX<X> fromByte(@Nonnull final LByteUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final byte v1) -> this.doApplyAsLong(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> from(@Nonnull final LToByteFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsLong(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApply(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperatorX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsByte(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunctionX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsShort(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunctionX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsInt(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunctionX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsLong(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsFloat(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunctionX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsDouble(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doApplyAsChar(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicateX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (byte b) -> after.doTest(this.doApplyAsLong(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToLongFunction nest() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteToLongFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToLongFunction shove() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToLongFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LByteToLongFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (byte b) -> this.handlingDoApplyAsLong(b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LByteToLongFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (byte b) -> this.handlingDoApplyAsLong(b, handling);
	}

	// </editor-fold>

}
