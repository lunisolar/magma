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
 * Throwing functional interface (lambda) LShortToLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortToLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortToLongFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LShortToLongFunctionX: long doApplyAsLong(short s) throws X";

	public long doApplyAsLong(short s) throws X;

	default long nestingDoApplyAsLong(short s) {
		try {
			return this.doApplyAsLong(s);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(short s) {
		return ((LShortToLongFunctionX<RuntimeException>) this).doApplyAsLong(s);
	}

	default <Y extends Throwable> long handlingDoApplyAsLong(short s, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(s);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(short s) throws X {
		return doApplyAsLong(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortToLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> capture(short s) {
		return () -> this.doApplyAsLong(s);
	}

	public static <X extends Throwable> LShortToLongFunctionX<X> constant(long r) {
		return (s) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LShortToLongFunctionX<X> lX(final @Nonnull LShortToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LShortToLongFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LShortToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LShortToLongFunctionX<X> wrapX(final @Nonnull LShortToLongFunction other) {
		return (LShortToLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortToLongFunctionX<X> fromShort(@Nonnull final LShortUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final short v1) -> this.doApplyAsLong(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> from(@Nonnull final LToShortFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsLong(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApply(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunctionX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsByte(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperatorX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsShort(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunctionX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsInt(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunctionX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsLong(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsFloat(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunctionX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsDouble(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doApplyAsChar(this.doApplyAsLong(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicateX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (short s) -> after.doTest(this.doApplyAsLong(s));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortToLongFunction nest() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortToLongFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToLongFunction shove() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToLongFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LShortToLongFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (short s) -> this.handlingDoApplyAsLong(s, handling);
	}

	@Nonnull
	default <Y extends Throwable> LShortToLongFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (short s) -> this.handlingDoApplyAsLong(s, handling);
	}

	// </editor-fold>

}
