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

package eu.lunisolar.magma.func.function;
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
 * Non-throwing functional interface (lambda) LBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: R
 *
 * @see LBiFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiFunction<T1, T2, R> extends LBiFunctionX<T1, T2, R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LBiFunction: R doApply(T1 t1,T2 t2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBiFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(T1 t1, T2 t2) {
		return this.nestingDoApply(t1, t2);
	}

	@Nullable
	R doApply(T1 t1, T2 t2);

	default R nestingDoApply(T1 t1, T2 t2) {
		return this.doApply(t1, t2);
	}

	default R shovingDoApply(T1 t1, T2 t2) {
		return this.doApply(t1, t2);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 t1, T2 t2) {
		return Null.requireNonNull(doApply(t1, t2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiFunc(T1 t1, T2 t2) {
		return () -> this.doApply(t1, t2);
	}

	static <T1, T2, R> LBiFunction<T1, T2, R> constant(R r) {
		return (t1, t2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> l(final @Nonnull LBiFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> wrap(final java.util.function.BiFunction<T1, T2, R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiFunction<T1, T2, R> wrap(final @Nonnull LBiFunctionX<T1, T2, R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biFuncFrom(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final V2 v2) -> this.doApply(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2) -> after.doApply(this.doApply(t1, t2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiConsumer<T1, T2> then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2) -> after.doAccept(this.doApply(t1, t2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiFunction<T1, T2, R> nestingBiFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiFunctionX<T1, T2, R, RuntimeException> nestingBiFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiFunction<T1, T2, R> shovingBiFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiFunctionX<T1, T2, R, RuntimeException> shovingBiFuncX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LBiFunction<T1, T2, R> nonNullBiFunc() {
		return this::nonNullDoApply;
	}

}
