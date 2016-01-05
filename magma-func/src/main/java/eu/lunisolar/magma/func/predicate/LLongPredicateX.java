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

package eu.lunisolar.magma.func.predicate;
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
 * Throwing functional interface (lambda) LLongPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): long a1
 *
 * Co-domain: boolean
 *
 * @see LLongPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongPredicateX<X extends Throwable> extends LongPredicate, MetaPredicate, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LLongPredicateX: boolean doTest(long a1) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongPredicateX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(long a1) {
		return this.nestingDoTest(a1);
	}

	boolean doTest(long a1) throws X;

	default Boolean tupleTest(LLongSingle args) throws X {
		return doTest(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(long a1) {
		try {
			return this.doTest(a1);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(long a1) {
		return ((LLongPredicateX<RuntimeException>) this).doTest(a1);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(long a1, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(a1);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(long a1) throws X {
		return doTest(a1);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(long a1) throws X {
		return doTest(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureLongPred(long a1) {
		return () -> this.doTest(a1);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LLongPredicateX<X> constant(boolean r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> lX(final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> boolean call(long a1, final @Nonnull LLongPredicateX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1);
	}

	static <X extends Throwable> boolean shoving(long a1, final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoTest(a1);
	}

	static <X extends Throwable> boolean nesting(long a1, final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoTest(a1);
	}

	static <X extends Throwable, Y extends Throwable> boolean handling(long a1, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LLongPredicateX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoTest(a1, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> wrap(final LongPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> wrapX(final @Nonnull LLongPredicate other) {
		return (LLongPredicateX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LLongPredicateX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> safe(final @Nullable LLongPredicateX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LLongPredicateX<X>, Y> safeSupplier(final @Nullable LSupplierX<LLongPredicateX<X>, Y> supplier) {
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
	default LLongPredicateX<X> negate() {
		return a1 -> !doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLongPredicateX<X> and(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) && other.doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLongPredicateX<X> or(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) || other.doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLongPredicateX<X> xor(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) ^ other.doTest(a1);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> isEqual(long target) {
		return a1 -> a1 == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongPredicateX<X> longPredComposeLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LPredicateX<V1, X> longPredCompose(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> boolToLongFunction(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> boolToLongToByteFunction(@Nonnull LBoolToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> boolToLongToShortFunction(@Nonnull LBoolToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> boolToLongToIntFunction(@Nonnull LBoolToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> boolToLongUnaryOperator(@Nonnull LBoolToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> boolToLongToFloatFunction(@Nonnull LBoolToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> boolToLongToDoubleFunction(@Nonnull LBoolToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> boolToLongToCharFunction(@Nonnull LBoolToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongPredicateX<X> boolToLongPredicate(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doTest(a1));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongPredicate nestingLongPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongPredicateX<RuntimeException> nestingLongPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicate shovingLongPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicateX<RuntimeException> shovingLongPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LLongPredicate handleLongPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a1 -> this.handlingDoTest(a1, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LLongPredicateX<Y> handleLongPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a1 -> this.handlingDoTest(a1, handling);
	}

	// </editor-fold>

}
