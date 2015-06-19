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
 * Throwing functional interface (lambda) LIntToShortFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToShortFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToShortFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntToShortFunctionX: short doApplyAsShort(int i) throws X";

	public short doApplyAsShort(int i) throws X;

	default short nestingDoApplyAsShort(int i) {
		try {
			return this.doApplyAsShort(i);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default short shovingDoApplyAsShort(int i) {
		return ((LIntToShortFunctionX<RuntimeException>) this).doApplyAsShort(i);
	}

	default <Y extends Throwable> short handlingDoApplyAsShort(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(i);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(int i) throws X {
		return doApplyAsShort(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToShortFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> capture(int i) {
		return () -> this.doApplyAsShort(i);
	}

	public static <X extends Throwable> LIntToShortFunctionX<X> constant(short r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToShortFunctionX<X> lX(final @Nonnull LIntToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToShortFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LIntToShortFunctionX<X> wrapX(final @Nonnull LIntToShortFunction other) {
		return (LIntToShortFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntToShortFunctionX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final int v1) -> this.doApplyAsShort(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunctionX<V1, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApply(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsByte(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsShort(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsInt(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsLong(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsFloat(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsDouble(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsChar(this.doApplyAsShort(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doTest(this.doApplyAsShort(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToShortFunction nest() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToShortFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToShortFunction shove() {
		return this::shovingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToShortFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntToShortFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i) -> this.handlingDoApplyAsShort(i, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntToShortFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i) -> this.handlingDoApplyAsShort(i, handling);
	}

	// </editor-fold>

}
