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

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LLogicalBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): boolean a1,boolean a2
 *
 * Co-domain: boolean
 *
 * @see LLogicalBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalBinaryOperatorX<X extends Throwable> extends MetaLogicalOperator, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LLogicalBinaryOperatorX: boolean doApply(boolean a1,boolean a2) throws X";

	boolean doApply(boolean a1, boolean a2) throws X;

	default Boolean tupleApply(LBoolPair args) throws X {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoApply(boolean a1, boolean a2) {
		try {
			return this.doApply(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoApply(boolean a1, boolean a2) {
		return ((LLogicalBinaryOperatorX<RuntimeException>) this).doApply(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoApply(boolean a1, boolean a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean a1, boolean a2) throws X {
		return doApply(a1, a2);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a1, boolean a2) throws X {
		return doApply(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureLogicalBinaryOp(boolean a1, boolean a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LLogicalBinaryOperatorX<X> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> apply1st(@Nonnull LLogicalOperatorX<X> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> apply2nd(@Nonnull LLogicalOperatorX<X> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> lX(final @Nonnull LLogicalBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLogicalBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> boolean call(boolean a1, boolean a2, final @Nonnull LLogicalBinaryOperatorX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	static <X extends Throwable> boolean shoving(boolean a1, boolean a2, final @Nonnull LLogicalBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApply(a1, a2);
	}

	static <X extends Throwable> boolean nesting(boolean a1, boolean a2, final @Nonnull LLogicalBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApply(a1, a2);
	}

	static <X extends Throwable, Y extends Throwable> boolean handling(boolean a1, boolean a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LLogicalBinaryOperatorX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApply(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> wrapX(final @Nonnull LLogicalBinaryOperator other) {
		return (LLogicalBinaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> safe() {
		return Function4U::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LLogicalBinaryOperatorX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> safe(final @Nullable LLogicalBinaryOperatorX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LLogicalBinaryOperatorX<X>, Y> safeSupplier(final @Nullable LSupplierX<LLogicalBinaryOperatorX<X>, Y> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LLogicalBinaryOperatorX<X> negate() {
		return (boolean a1, boolean a2) -> !doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalBinaryOperatorX<X> and(@Nonnull LLogicalBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean a1, boolean a2) -> doApply(a1, a2) && other.doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalBinaryOperatorX<X> or(@Nonnull LLogicalBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean a1, boolean a2) -> doApply(a1, a2) || other.doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalBinaryOperatorX<X> xor(@Nonnull LLogicalBinaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean a1, boolean a2) -> doApply(a1, a2) ^ other.doApply(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> isEqual(final boolean v1, final boolean v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	/**
	 * Returns function that applies logical AND operator.
	 */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> and() {
		return Boolean::logicalAnd;
	}

	/**
	 * Returns function that applies logical OR operator.
	 */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> or() {
		return Boolean::logicalOr;
	}

	/**
	 * Returns function that applies logical XOR operator.
	 */
	@Nonnull
	static <X extends Throwable> LLogicalBinaryOperatorX<X> xor() {
		return Boolean::logicalXor;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalBinaryOperatorX<X> logicalBinaryOpComposeBoolean(@Nonnull final LLogicalOperatorX<X> before1, @Nonnull final LLogicalOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final boolean v1, final boolean v2) -> this.doApply(before1.doApply(v1), before2.doApply(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> logicalBinaryOpCompose(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApply(before1.doTest(v1), before2.doTest(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBiBoolFunctionX<V, X> then(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (boolean a1, boolean a2) -> after.doApply(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLogicalBinaryOperator nestingLogicalBinaryOp() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLogicalBinaryOperatorX<RuntimeException> nestingLogicalBinaryOpX() {
		return this::nestingDoApply;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalBinaryOperator shovingLogicalBinaryOp() {
		return this::shovingDoApply;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalBinaryOperatorX<RuntimeException> shovingLogicalBinaryOpX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LLogicalBinaryOperator handleLogicalBinaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean a1, boolean a2) -> this.handlingDoApply(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LLogicalBinaryOperatorX<Y> handleLogicalBinaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean a1, boolean a2) -> this.handlingDoApply(a1, a2, handling);
	}

	// </editor-fold>

}
