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

package eu.lunisolar.magma.func.function.to;
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
 * Throwing functional interface (lambda) LToByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LToByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToByteFunctionX<T, X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LToByteFunctionX: byte doApplyAsByte(T t) throws X";

	public byte doApplyAsByte(T t) throws X;

	default byte nestingDoApplyAsByte(T t) {
		try {
			return this.doApplyAsByte(t);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default byte shovingDoApplyAsByte(T t) {
		return ((LToByteFunctionX<T, RuntimeException>) this).doApplyAsByte(t);
	}

	default <Y extends Throwable> byte handlingDoApplyAsByte(T t, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(t);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(T t) throws X {
		return doApplyAsByte(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> capture(T t) {
		return () -> this.doApplyAsByte(t);
	}

	public static <T, X extends Throwable> LToByteFunctionX<T, X> constant(byte r) {
		return (t) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LToByteFunctionX<T, X> lX(final @Nonnull LToByteFunctionX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LToByteFunctionX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToByteFunctionX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LToByteFunctionX<T, X> wrapX(final @Nonnull LToByteFunction<T> other) {
		return (LToByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final V1 v1) -> this.doApplyAsByte(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApply(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsByte(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsShort(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsInt(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsLong(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsFloat(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsDouble(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsChar(this.doApplyAsByte(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicateX<T, X> thenToBoolean(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doTest(this.doApplyAsByte(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToByteFunction<T> nest() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToByteFunctionX<T, RuntimeException> nestX() {
		return this::nestingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteFunction<T> shove() {
		return this::shovingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteFunctionX<T, RuntimeException> shoveX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LToByteFunction<T> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t) -> this.handlingDoApplyAsByte(t, handling);
	}

	@Nonnull
	default <Y extends Throwable> LToByteFunctionX<T, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t) -> this.handlingDoApplyAsByte(t, handling);
	}

	// </editor-fold>

}
