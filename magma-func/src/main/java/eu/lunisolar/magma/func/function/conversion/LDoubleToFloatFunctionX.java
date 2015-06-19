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
 * Throwing functional interface (lambda) LDoubleToFloatFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToFloatFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToFloatFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoubleToFloatFunctionX: float doApplyAsFloat(double d) throws X";

	public float doApplyAsFloat(double d) throws X;

	default float nestingDoApplyAsFloat(double d) {
		try {
			return this.doApplyAsFloat(d);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default float shovingDoApplyAsFloat(double d) {
		return ((LDoubleToFloatFunctionX<RuntimeException>) this).doApplyAsFloat(d);
	}

	default <Y extends Throwable> float handlingDoApplyAsFloat(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(d);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(double d) throws X {
		return doApplyAsFloat(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToFloatFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> capture(double d) {
		return () -> this.doApplyAsFloat(d);
	}

	public static <X extends Throwable> LDoubleToFloatFunctionX<X> constant(float r) {
		return (d) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleToFloatFunctionX<X> lX(final @Nonnull LDoubleToFloatFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleToFloatFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleToFloatFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LDoubleToFloatFunctionX<X> wrapX(final @Nonnull LDoubleToFloatFunction other) {
		return (LDoubleToFloatFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleToFloatFunctionX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final double v1) -> this.doApplyAsFloat(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunctionX<V1, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsFloat(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LFloatFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApply(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LFloatToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsByte(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LFloatToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsShort(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LFloatToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsInt(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LFloatToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsLong(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LFloatUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsFloat(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LFloatToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsDouble(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LFloatToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsChar(this.doApplyAsFloat(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LFloatPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doTest(this.doApplyAsFloat(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToFloatFunction nest() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToFloatFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToFloatFunction shove() {
		return this::shovingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToFloatFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleToFloatFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (double d) -> this.handlingDoApplyAsFloat(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleToFloatFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (double d) -> this.handlingDoApplyAsFloat(d, handling);
	}

	// </editor-fold>

}
