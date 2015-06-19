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
 * Throwing functional interface (lambda) LCharToFloatFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharToFloatFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToFloatFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LCharToFloatFunctionX: float doApplyAsFloat(char c) throws X";

	public float doApplyAsFloat(char c) throws X;

	default float nestingDoApplyAsFloat(char c) {
		try {
			return this.doApplyAsFloat(c);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default float shovingDoApplyAsFloat(char c) {
		return ((LCharToFloatFunctionX<RuntimeException>) this).doApplyAsFloat(c);
	}

	default <Y extends Throwable> float handlingDoApplyAsFloat(char c, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(c);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(char c) throws X {
		return doApplyAsFloat(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToFloatFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> capture(char c) {
		return () -> this.doApplyAsFloat(c);
	}

	public static <X extends Throwable> LCharToFloatFunctionX<X> constant(float r) {
		return (c) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LCharToFloatFunctionX<X> lX(final @Nonnull LCharToFloatFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LCharToFloatFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharToFloatFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LCharToFloatFunctionX<X> wrapX(final @Nonnull LCharToFloatFunction other) {
		return (LCharToFloatFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharToFloatFunctionX<X> fromChar(@Nonnull final LCharUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final char v1) -> this.doApplyAsFloat(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunctionX<V1, X> from(@Nonnull final LToCharFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsFloat(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunctionX<V, X> then(@Nonnull LFloatFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApply(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunctionX<X> thenToByte(@Nonnull LFloatToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsByte(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunctionX<X> thenToShort(@Nonnull LFloatToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsShort(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunctionX<X> thenToInt(@Nonnull LFloatToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsInt(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunctionX<X> thenToLong(@Nonnull LFloatToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsLong(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunctionX<X> thenToFloat(@Nonnull LFloatUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsFloat(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunctionX<X> thenToDouble(@Nonnull LFloatToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsDouble(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperatorX<X> thenToChar(@Nonnull LFloatToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doApplyAsChar(this.doApplyAsFloat(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicateX<X> thenToBoolean(@Nonnull LFloatPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (char c) -> after.doTest(this.doApplyAsFloat(c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharToFloatFunction nest() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharToFloatFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToFloatFunction shove() {
		return this::shovingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToFloatFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LCharToFloatFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (char c) -> this.handlingDoApplyAsFloat(c, handling);
	}

	@Nonnull
	default <Y extends Throwable> LCharToFloatFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (char c) -> this.handlingDoApplyAsFloat(c, handling);
	}

	// </editor-fold>

}
