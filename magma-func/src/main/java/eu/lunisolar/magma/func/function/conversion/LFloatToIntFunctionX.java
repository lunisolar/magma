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
 * Throwing functional interface (lambda) LFloatToIntFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatToIntFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToIntFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LFloatToIntFunctionX: int doApplyAsInt(float f) throws X";

	public int doApplyAsInt(float f) throws X;

	default int nestingDoApplyAsInt(float f) {
		try {
			return this.doApplyAsInt(f);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default int shovingDoApplyAsInt(float f) {
		return ((LFloatToIntFunctionX<RuntimeException>) this).doApplyAsInt(f);
	}

	default <Y extends Throwable> int handlingDoApplyAsInt(float f, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(f);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(float f) throws X {
		return doApplyAsInt(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToIntFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> capture(float f) {
		return () -> this.doApplyAsInt(f);
	}

	public static <X extends Throwable> LFloatToIntFunctionX<X> constant(int r) {
		return (f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LFloatToIntFunctionX<X> lX(final @Nonnull LFloatToIntFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LFloatToIntFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatToIntFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LFloatToIntFunctionX<X> wrapX(final @Nonnull LFloatToIntFunction other) {
		return (LFloatToIntFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatToIntFunctionX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final float v1) -> this.doApplyAsInt(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToIntFunctionX<V1, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsInt(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApply(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LIntToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsByte(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LIntToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsShort(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LIntUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsInt(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LIntToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsLong(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LIntToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsFloat(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LIntToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsDouble(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LIntToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doApplyAsChar(this.doApplyAsInt(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LIntPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f) -> after.doTest(this.doApplyAsInt(f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToIntFunction nest() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToIntFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToIntFunction shove() {
		return this::shovingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToIntFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatToIntFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (float f) -> this.handlingDoApplyAsInt(f, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatToIntFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (float f) -> this.handlingDoApplyAsInt(f, handling);
	}

	// </editor-fold>

}
