/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * Throwing functional interface (lambda) LToShortFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LToShortFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToShortFunctionX<T, X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LToShortFunctionX: short doApplyAsShort(T t) throws X";

	public short doApplyAsShort(T t) throws X;

	default short nestingDoApplyAsShort(T t) {
		try {
			return this.doApplyAsShort(t);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default short shovingDoApplyAsShort(T t) {
		return ((LToShortFunctionX<T, RuntimeException>) this).doApplyAsShort(t);
	}

	default <Y extends Throwable> short handlingDoApplyAsShort(T t, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(t);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(T t) throws X {
		return doApplyAsShort(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToShortFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> capture(T t) {
		return () -> this.doApplyAsShort(t);
	}

	public static <T, X extends Throwable> LToShortFunctionX<T, X> constant(short r) {
		return (t) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LToShortFunctionX<T, X> lX(final @Nonnull LToShortFunctionX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LToShortFunctionX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToShortFunctionX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LToShortFunctionX<T, X> wrapX(final @Nonnull LToShortFunction<T> other) {
		return (LToShortFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunctionX<V1, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final V1 v1) -> this.doApplyAsShort(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApply(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsByte(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsShort(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsInt(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsLong(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsFloat(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsDouble(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsChar(this.doApplyAsShort(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicateX<T, X> thenToBoolean(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doTest(this.doApplyAsShort(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToShortFunction<T> nest() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToShortFunctionX<T, RuntimeException> nestX() {
		return this::nestingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToShortFunction<T> shove() {
		return this::shovingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToShortFunctionX<T, RuntimeException> shoveX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LToShortFunction<T> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t) -> this.handlingDoApplyAsShort(t, handling);
	}

	@Nonnull
	default <Y extends Throwable> LToShortFunctionX<T, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t) -> this.handlingDoApplyAsShort(t, handling);
	}

	// </editor-fold>

}
