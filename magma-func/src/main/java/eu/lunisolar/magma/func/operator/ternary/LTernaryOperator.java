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

package eu.lunisolar.magma.func.operator.ternary;
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
 * Non-throwing functional interface (lambda) LTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): T t1,T t2,T t3
 *
 * Co-domain: T
 *
 * @see LTernaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTernaryOperator<T> extends LTernaryOperatorX<T, RuntimeException>, MetaOperator, MetaInterface.NonThrowing, LTriFunction<T, T, T, T> { // NOSONAR

	static final String DESCRIPTION = "LTernaryOperator: T doApply(T t1,T t2,T t3)";

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default T nestingDoApply(T t1, T t2, T t3) {
		return this.doApply(t1, t2, t3);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default T shovingDoApply(T t1, T t2, T t3) {
		return this.doApply(t1, t2, t3);
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T t1, T t2, T t3) {
		return Null.requireNonNull(doApply(t1, t2, t3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTernaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> captureTernaryOp(T t1, T t2, T t3) {
		return () -> this.doApply(t1, t2, t3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTernaryOperator<T> constant(T r) {
		return (t1, t2, t3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply1st(@Nonnull LUnaryOperator<T> func) {
		return (t1, t2, t3) -> func.doApply(t1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply2nd(@Nonnull LUnaryOperator<T> func) {
		return (t1, t2, t3) -> func.doApply(t2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply3rd(@Nonnull LUnaryOperator<T> func) {
		return (t1, t2, t3) -> func.doApply(t3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTernaryOperator<T> l(final @Nonnull LTernaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperator<T> wrap(final @Nonnull LTernaryOperatorX<T, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LTriFunction<T, T, T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T t1, T t2, T t3) -> after.doApply(this.doApply(t1, t2, t3));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTernaryOperator<T> nestingTernaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTernaryOperatorX<T, RuntimeException> nestingTernaryOpX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperator<T> shovingTernaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperatorX<T, RuntimeException> shovingTernaryOpX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTernaryOperator<T> nonNullTernaryOp() {
		return this::nonNullDoApply;
	}

}
