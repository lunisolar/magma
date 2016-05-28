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
 * Throwing functional interface (lambda) LTernaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): T a1,T a2,T a3
 *
 * Co-domain: T
 *
 * @see LTernaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTernaryOperatorX<T, X extends Throwable> extends MetaOperator, MetaInterface.Throwing<X>, LTriFunctionX<T, T, T, T, X> { // NOSONAR

	String DESCRIPTION = "LTernaryOperatorX: T doApply(T a1,T a2,T a3) throws X";

	default T tupleApply(LTriple<T, T, T> args) throws X {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoApply(T a1, T a2, T a3) {
		try {
			return this.doApply(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default T shovingDoApply(T a1, T a2, T a3) {
		return ((LTernaryOperatorX<T, RuntimeException>) this).doApply(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> T handlingDoApply(T a1, T a2, T a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T a1, T a2, T a3) throws X {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTernaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<T, X> captureTernaryOp(T a1, T a2, T a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T, X extends Throwable> LTernaryOperatorX<T, X> constant(T r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> apply1st(@Nonnull LUnaryOperatorX<T, X> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> apply2nd(@Nonnull LUnaryOperatorX<T, X> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> apply3rd(@Nonnull LUnaryOperatorX<T, X> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> lX(final @Nonnull LTernaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LTernaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <T, X extends Throwable> T call(T a1, T a2, T a3, final @Nonnull LTernaryOperatorX<T, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	static <T, X extends Throwable> T shoving(T a1, T a2, T a3, final @Nonnull LTernaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApply(a1, a2, a3);
	}

	static <T, X extends Throwable> T nesting(T a1, T a2, T a3, final @Nonnull LTernaryOperatorX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApply(a1, a2, a3);
	}

	static <T, X extends Throwable, Y extends Throwable> T handling(T a1, T a2, T a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LTernaryOperatorX<T, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApply(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> wrapX(final @Nonnull LTernaryOperator<T> other) {
		return (LTernaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produce). */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LTernaryOperatorX<T, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, X extends Throwable> LTernaryOperatorX<T, X> safe(final @Nullable LTernaryOperatorX<T, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LTernaryOperatorX<T, X>, Y> safeSupplier(final @Nullable LSupplierX<LTernaryOperatorX<T, X>, Y> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFunctionX<T, T, T, V, X> then(@Nonnull LFunctionX<? super T, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTernaryOperator<T> nestingTernaryOp() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTernaryOperatorX<T, RuntimeException> nestingTernaryOpX() {
		return this::nestingDoApply;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperator<T> shovingTernaryOp() {
		return this::shovingDoApply;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperatorX<T, RuntimeException> shovingTernaryOpX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTernaryOperatorX<T, X> nonNullTernaryOp() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LTernaryOperator<T> handleTernaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LTernaryOperatorX<T, Y> handleTernaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2, a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	// </editor-fold>

}
