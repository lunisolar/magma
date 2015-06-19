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
 * Throwing functional interface (lambda) LDoubleToByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToByteFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoubleToByteFunctionX: byte doApplyAsByte(double d) throws X";

	public byte doApplyAsByte(double d) throws X;

	default byte nestingDoApplyAsByte(double d) {
		try {
			return this.doApplyAsByte(d);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default byte shovingDoApplyAsByte(double d) {
		return ((LDoubleToByteFunctionX<RuntimeException>) this).doApplyAsByte(d);
	}

	default <Y extends Throwable> byte handlingDoApplyAsByte(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(d);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(double d) throws X {
		return doApplyAsByte(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> capture(double d) {
		return () -> this.doApplyAsByte(d);
	}

	public static <X extends Throwable> LDoubleToByteFunctionX<X> constant(byte r) {
		return (d) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleToByteFunctionX<X> lX(final @Nonnull LDoubleToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleToByteFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LDoubleToByteFunctionX<X> wrapX(final @Nonnull LDoubleToByteFunction other) {
		return (LDoubleToByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleToByteFunctionX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final double v1) -> this.doApplyAsByte(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsByte(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApply(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsByte(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsShort(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsInt(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsLong(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsFloat(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsDouble(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsChar(this.doApplyAsByte(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doTest(this.doApplyAsByte(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToByteFunction nest() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToByteFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToByteFunction shove() {
		return this::shovingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToByteFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleToByteFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (double d) -> this.handlingDoApplyAsByte(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleToByteFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (double d) -> this.handlingDoApplyAsByte(d, handling);
	}

	// </editor-fold>

}
