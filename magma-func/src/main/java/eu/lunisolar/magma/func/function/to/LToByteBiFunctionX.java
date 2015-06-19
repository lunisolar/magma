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
 * Throwing functional interface (lambda) LToByteBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: none
 *
 * @see LToByteBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToByteBiFunctionX<T1, T2, X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LToByteBiFunctionX: byte doApplyAsByte(T1 t1,T2 t2) throws X";

	public byte doApplyAsByte(T1 t1, T2 t2) throws X;

	default byte nestingDoApplyAsByte(T1 t1, T2 t2) {
		try {
			return this.doApplyAsByte(t1, t2);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default byte shovingDoApplyAsByte(T1 t1, T2 t2) {
		return ((LToByteBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsByte(t1, t2);
	}

	default <Y extends Throwable> byte handlingDoApplyAsByte(T1 t1, T2 t2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(t1, t2);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(T1 t1, T2 t2) throws X {
		return doApplyAsByte(t1, t2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToByteBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> capture(T1 t1, T2 t2) {
		return () -> this.doApplyAsByte(t1, t2);
	}

	public static <T1, T2, X extends Throwable> LToByteBiFunctionX<T1, T2, X> constant(byte r) {
		return (t1, t2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Throwable> LToByteBiFunctionX<T1, T2, X> lX(final @Nonnull LToByteBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Throwable> LToByteBiFunctionX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToByteBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Throwable> LToByteBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToByteBiFunction<T1, T2> other) {
		return (LToByteBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToByteBiFunctionX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final V2 v2) -> this.doApplyAsByte(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2) -> after.doApply(this.doApplyAsByte(t1, t2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToByteBiFunction<T1, T2> nest() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToByteBiFunctionX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteBiFunction<T1, T2> shove() {
		return this::shovingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToByteBiFunctionX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LToByteBiFunction<T1, T2> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsByte(t1, t2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LToByteBiFunctionX<T1, T2, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsByte(t1, t2, handling);
	}

	// </editor-fold>

}
