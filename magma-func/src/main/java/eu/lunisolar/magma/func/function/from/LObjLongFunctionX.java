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

package eu.lunisolar.magma.func.function.from;
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
 * Throwing functional interface (lambda) LObjLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T t, long l
 *
 * Co-domain: R
 *
 * @see LObjLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongFunctionX<T, R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LObjLongFunctionX: R doApply(T t, long l) throws X";

	@Nullable
	R doApply(T t, long l) throws X;

	default R nestingDoApply(T t, long l) {
		try {
			return this.doApply(t, l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(T t, long l) {
		return ((LObjLongFunctionX<T, R, RuntimeException>) this).doApply(t, l);
	}

	default <Y extends Throwable> R handlingDoApply(T t, long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(t, l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T t, long l) throws X {
		return Null.requireNonNull(doApply(t, l), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(T t, long l) {
		return () -> this.doApply(t, l);
	}

	static <T, R, X extends Throwable> LObjLongFunctionX<T, R, X> constant(R r) {
		return (t, l) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R, X extends Throwable> LObjLongFunctionX<T, R, X> lX(final @Nonnull LObjLongFunctionX<T, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R, X extends Throwable> LObjLongFunctionX<T, R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjLongFunctionX<T, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, R, X extends Throwable> LObjLongFunctionX<T, R, X> wrapX(final @Nonnull LObjLongFunction<T, R> other) {
		return (LObjLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjLongFunctionX<V1, R, X> fromLong(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LLongUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final long v2) -> this.doApply(before1.doApply(v1), before2.doApplyAsLong(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiFunctionX<V1, V2, R, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToLongFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApply(before1.doApply(v1), before2.doApplyAsLong(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjLongFunctionX<T, V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t, long l) -> after.doApply(this.doApply(t, l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjLongConsumerX<T, X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return (T t, long l) -> after.doAccept(this.doApply(t, l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjLongFunction<T, R> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjLongFunctionX<T, R, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjLongFunction<T, R> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjLongFunctionX<T, R, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LObjLongFunctionX<T, R, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LObjLongFunction<T, R> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t, long l) -> this.handlingDoApply(t, l, handling);
	}

	@Nonnull
	default <Y extends Throwable> LObjLongFunctionX<T, R, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t, long l) -> this.handlingDoApply(t, l, handling);
	}

	// </editor-fold>

}
