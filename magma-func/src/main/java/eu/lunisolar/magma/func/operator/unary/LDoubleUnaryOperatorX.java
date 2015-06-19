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
 * Throwing functional interface (lambda) LDoubleUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleUnaryOperatorX<X extends Throwable> extends java.util.function.DoubleUnaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoubleUnaryOperatorX: double doApplyAsDouble(double d) throws X";

	@Override
	@Deprecated
	// calling this method via LDoubleUnaryOperatorX interface should be discouraged.
	default double applyAsDouble(double d) {
		return this.nestingDoApplyAsDouble(d);
	}

	public double doApplyAsDouble(double d) throws X;

	default double nestingDoApplyAsDouble(double d) {
		try {
			return this.doApplyAsDouble(d);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(double d) {
		return ((LDoubleUnaryOperatorX<RuntimeException>) this).doApplyAsDouble(d);
	}

	default <Y extends Throwable> double handlingDoApplyAsDouble(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsDouble(d);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(double d) throws X {
		return doApplyAsDouble(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> capture(double d) {
		return () -> this.doApplyAsDouble(d);
	}

	public static <X extends Throwable> LDoubleUnaryOperatorX<X> constant(double r) {
		return (d) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleUnaryOperatorX<X> lX(final @Nonnull LDoubleUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LDoubleUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Throwable> LDoubleUnaryOperatorX<X> wrap(final java.util.function.DoubleUnaryOperator other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LDoubleUnaryOperatorX<X> wrapX(final @Nonnull LDoubleUnaryOperator other) {
		return (LDoubleUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleUnaryOperatorX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final double v1) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunctionX<V1, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApply(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LDoubleToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsByte(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LDoubleToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsShort(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LDoubleToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsInt(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LDoubleToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsLong(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LDoubleToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsFloat(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LDoubleUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsDouble(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LDoubleToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doApplyAsChar(this.doApplyAsDouble(d));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LDoublePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (double d) -> after.doTest(this.doApplyAsDouble(d));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <X extends Throwable> LDoubleUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleUnaryOperator nest() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleUnaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleUnaryOperator shove() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleUnaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleUnaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (double d) -> this.handlingDoApplyAsDouble(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleUnaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (double d) -> this.handlingDoApplyAsDouble(d, handling);
	}

	// </editor-fold>

}
