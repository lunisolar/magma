/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.binary;

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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): T a1,T a2
 *
 * Co-domain: T
 *
 * @see LBinaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBinaryOperator<T> extends LBinaryOperatorX<T, RuntimeException>, MetaOperator, MetaInterface.NonThrowing, LBiFunction<T, T, T> { // NOSONAR

	String DESCRIPTION = "LBinaryOperator: T doApply(T a1,T a2)";

	default T tupleApply(LPair<T, T> args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoApply(T a1, T a2) {
		return this.doApply(a1, a2);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default T shovingDoApply(T a1, T a2) {
		return this.doApply(a1, a2);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T a1, T a2) {
		return Null.requireNonNull(doApply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBinaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> captureBinaryOp(T a1, T a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LBinaryOperator<T> constant(T r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LBinaryOperator<T> apply1st(@Nonnull LUnaryOperator<T> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LBinaryOperator<T> apply2nd(@Nonnull LUnaryOperator<T> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBinaryOperator<T> l(final @Nonnull LBinaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <T> T call(T a1, T a2, final @Nonnull LBinaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LBinaryOperator<T> wrap(final BinaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LBinaryOperator<T> wrap(final @Nonnull LBinaryOperatorX<T, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produce). */
	@Nonnull
	static <T> LBinaryOperator<T> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LBinaryOperator<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LBinaryOperator<T> safe(final @Nullable LBinaryOperator<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LBinaryOperator<T>> safeSupplier(final @Nullable LSupplier<LBinaryOperator<T>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static <T> LBinaryOperator<T> minBy(@Nonnull Comparator<T> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static <T> LBinaryOperator<T> maxBy(@Nonnull Comparator<T> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T, T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBinaryOperator<T> nestingBinaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBinaryOperatorX<T, RuntimeException> nestingBinaryOpX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBinaryOperator<T> shovingBinaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBinaryOperatorX<T, RuntimeException> shovingBinaryOpX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBinaryOperator<T> nonNullBinaryOp() {
		return this::nonNullDoApply;
	}

}
