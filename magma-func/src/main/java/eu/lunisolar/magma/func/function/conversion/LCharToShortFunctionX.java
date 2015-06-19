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
 * Throwing functional interface (lambda) LCharToShortFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharToShortFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToShortFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LCharToShortFunctionX: short doApplyAsShort(char c) throws X";

	public short doApplyAsShort(char c) throws X;

	default short nestingDoApplyAsShort(char c) {
		try {
			return this.doApplyAsShort(c);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default short shovingDoApplyAsShort(char c) {
		return ((LCharToShortFunctionX<RuntimeException>) this).doApplyAsShort(c);
	}

	default <Y extends Throwable> short handlingDoApplyAsShort(char c, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(c);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(char c) throws X {
		return doApplyAsShort(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToShortFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> capture(char c) {
		return () -> this.doApplyAsShort(c);
	}

	public static <X extends Throwable> LCharToShortFunctionX<X> constant(short r) {
		return (c) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LCharToShortFunctionX<X> lX(final @Nonnull LCharToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LCharToShortFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LCharToShortFunctionX<X> wrapX(final @Nonnull LCharToShortFunction other) {
		return (LCharToShortFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharToShortFunctionX<X> fromChar(@Nonnull final LCharUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final char v1) -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunctionX<V1, X> from(@Nonnull final LToCharFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunctionX<V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApply(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunctionX<X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsByte(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunctionX<X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsShort(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunctionX<X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsInt(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunctionX<X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsLong(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunctionX<X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsFloat(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunctionX<X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsDouble(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperatorX<X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsChar(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicateX<X> thenToBoolean(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doTest(this.doApplyAsShort(c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharToShortFunction nest() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharToShortFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunction shove() {
		return this::shovingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LCharToShortFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (char c) -> this.handlingDoApplyAsShort(c, handling);
	}

	@Nonnull
	default <Y extends Throwable> LCharToShortFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (char c) -> this.handlingDoApplyAsShort(c, handling);
	}

	// </editor-fold>

}
