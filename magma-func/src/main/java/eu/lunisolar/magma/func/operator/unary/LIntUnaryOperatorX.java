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
 * Throwing functional interface (lambda) LIntUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntUnaryOperatorX<X extends Throwable> extends java.util.function.IntUnaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntUnaryOperatorX: int doApplyAsInt(int i) throws X";

	@Override
	@Deprecated
	// calling this method via LIntUnaryOperatorX interface should be discouraged.
	default int applyAsInt(int i) {
		return this.nestingDoApplyAsInt(i);
	}

	public int doApplyAsInt(int i) throws X;

	default int nestingDoApplyAsInt(int i) {
		try {
			return this.doApplyAsInt(i);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default int shovingDoApplyAsInt(int i) {
		return ((LIntUnaryOperatorX<RuntimeException>) this).doApplyAsInt(i);
	}

	default <Y extends Throwable> int handlingDoApplyAsInt(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(i);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int i) throws X {
		return doApplyAsInt(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> capture(int i) {
		return () -> this.doApplyAsInt(i);
	}

	public static <X extends Throwable> LIntUnaryOperatorX<X> constant(int r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntUnaryOperatorX<X> lX(final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Throwable> LIntUnaryOperatorX<X> wrap(final java.util.function.IntUnaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LIntUnaryOperatorX<X> wrapX(final @Nonnull LIntUnaryOperator other) {
		return (LIntUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntUnaryOperatorX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final int v1) -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToIntFunctionX<V1, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApply(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LIntToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsByte(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LIntToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsShort(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LIntUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsInt(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LIntToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsLong(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LIntToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsFloat(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LIntToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsDouble(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LIntToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsChar(this.doApplyAsInt(i));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LIntPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doTest(this.doApplyAsInt(i));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <X extends Throwable> LIntUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntUnaryOperator nest() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntUnaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperator shove() {
		return this::shovingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntUnaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i) -> this.handlingDoApplyAsInt(i, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntUnaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i) -> this.handlingDoApplyAsInt(i, handling);
	}

	// </editor-fold>

}
