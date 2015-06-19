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
 * Throwing functional interface (lambda) LIntToCharFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToCharFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToCharFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntToCharFunctionX: char doApplyAsChar(int i) throws X";

	public char doApplyAsChar(int i) throws X;

	default char nestingDoApplyAsChar(int i) {
		try {
			return this.doApplyAsChar(i);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default char shovingDoApplyAsChar(int i) {
		return ((LIntToCharFunctionX<RuntimeException>) this).doApplyAsChar(i);
	}

	default <Y extends Throwable> char handlingDoApplyAsChar(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsChar(i);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(int i) throws X {
		return doApplyAsChar(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToCharFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> capture(int i) {
		return () -> this.doApplyAsChar(i);
	}

	public static <X extends Throwable> LIntToCharFunctionX<X> constant(char r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToCharFunctionX<X> lX(final @Nonnull LIntToCharFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToCharFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntToCharFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LIntToCharFunctionX<X> wrapX(final @Nonnull LIntToCharFunction other) {
		return (LIntToCharFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntToCharFunctionX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final int v1) -> this.doApplyAsChar(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToCharFunctionX<V1, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsChar(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApply(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LCharToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsByte(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LCharToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsShort(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LCharToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsInt(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LCharToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsLong(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LCharToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsFloat(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LCharToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsDouble(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LCharUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsChar(this.doApplyAsChar(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LCharPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doTest(this.doApplyAsChar(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToCharFunction nest() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToCharFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToCharFunction shove() {
		return this::shovingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToCharFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntToCharFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i) -> this.handlingDoApplyAsChar(i, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntToCharFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i) -> this.handlingDoApplyAsChar(i, handling);
	}

	// </editor-fold>

}
