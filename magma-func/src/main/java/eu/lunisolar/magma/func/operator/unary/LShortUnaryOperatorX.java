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
 * Throwing functional interface (lambda) LShortUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortUnaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LShortUnaryOperatorX: short doApplyAsShort(short s) throws X";

	public short doApplyAsShort(short s) throws X;

	default short nestingDoApplyAsShort(short s) {
		try {
			return this.doApplyAsShort(s);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default short shovingDoApplyAsShort(short s) {
		return ((LShortUnaryOperatorX<RuntimeException>) this).doApplyAsShort(s);
	}

	default <Y extends Throwable> short handlingDoApplyAsShort(short s, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(s);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(short s) throws X {
		return doApplyAsShort(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> capture(short s) {
		return () -> this.doApplyAsShort(s);
	}

	public static <X extends Throwable> LShortUnaryOperatorX<X> constant(short r) {
		return (s) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LShortUnaryOperatorX<X> lX(final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LShortUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LShortUnaryOperatorX<X> wrapX(final @Nonnull LShortUnaryOperator other) {
		return (LShortUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortUnaryOperatorX<X> fromShort(@Nonnull final LShortUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final short v1) -> this.doApplyAsShort(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunctionX<V1, X> from(@Nonnull final LToShortFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LShortFunctionX<V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApply(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToByteFunctionX<X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsByte(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortUnaryOperatorX<X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsShort(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToIntFunctionX<X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsInt(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToLongFunctionX<X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsLong(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToFloatFunctionX<X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsFloat(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToDoubleFunctionX<X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsDouble(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortToCharFunctionX<X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsChar(this.doApplyAsShort(s));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LShortPredicateX<X> thenToBoolean(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doTest(this.doApplyAsShort(s));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <X extends Throwable> LShortUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortUnaryOperator nest() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortUnaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperator shove() {
		return this::shovingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LShortUnaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (short s) -> this.handlingDoApplyAsShort(s, handling);
	}

	@Nonnull
	default <Y extends Throwable> LShortUnaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (short s) -> this.handlingDoApplyAsShort(s, handling);
	}

	// </editor-fold>

}
