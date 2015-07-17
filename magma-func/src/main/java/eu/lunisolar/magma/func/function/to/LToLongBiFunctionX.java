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
 * Throwing functional interface (lambda) LToLongBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: none
 *
 * @see LToLongBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToLongBiFunctionX<T1, T2, X extends Throwable> extends java.util.function.ToLongBiFunction<T1, T2>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LToLongBiFunctionX: long doApplyAsLong(T1 t1,T2 t2) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToLongBiFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(T1 t1, T2 t2) {
		return this.nestingDoApplyAsLong(t1, t2);
	}

	long doApplyAsLong(T1 t1, T2 t2) throws X;

	default long nestingDoApplyAsLong(T1 t1, T2 t2) {
		try {
			return this.doApplyAsLong(t1, t2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(T1 t1, T2 t2) {
		return ((LToLongBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsLong(t1, t2);
	}

	default <Y extends Throwable> long handlingDoApplyAsLong(T1 t1, T2 t2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(t1, t2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(T1 t1, T2 t2) throws X {
		return doApplyAsLong(t1, t2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToLongBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> captureToLongBiFunc(T1 t1, T2 t2) {
		return () -> this.doApplyAsLong(t1, t2);
	}

	static <T1, T2, X extends Throwable> LToLongBiFunctionX<T1, T2, X> constant(long r) {
		return (t1, t2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToLongBiFunctionX<T1, T2, X> lX(final @Nonnull LToLongBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToLongBiFunctionX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToLongBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToLongBiFunctionX<T1, T2, X> wrap(final java.util.function.ToLongBiFunction<T1, T2> other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToLongBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToLongBiFunction<T1, T2> other) {
		return (LToLongBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2> LToLongBiFunctionX<V1, V2, X> toLongBiFuncFrom(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final V2 v2) -> this.doApplyAsLong(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2) -> after.doApply(this.doApplyAsLong(t1, t2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToLongBiFunction<T1, T2> nestingToLongBiFunc() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToLongBiFunctionX<T1, T2, RuntimeException> nestingToLongBiFuncX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToLongBiFunction<T1, T2> shovingToLongBiFunc() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToLongBiFunctionX<T1, T2, RuntimeException> shovingToLongBiFuncX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LToLongBiFunction<T1, T2> handleToLongBiFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsLong(t1, t2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LToLongBiFunctionX<T1, T2, Y> handleToLongBiFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsLong(t1, t2, handling);
	}

	// </editor-fold>

}
