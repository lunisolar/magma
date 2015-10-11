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
 * Non-throwing functional interface (lambda) LLongPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): long a1
 *
 * Co-domain: boolean
 *
 * @see LLongPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongPredicate extends LLongPredicateX<RuntimeException>, MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LLongPredicate: boolean doTest(long a1)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongPredicate interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(long a1) {
		return this.nestingDoTest(a1);
	}

	boolean doTest(long a1);

	default Boolean tupleTest(LLongSingle args) {
		return doTest(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(long a1) {
		return this.doTest(a1);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(long a1) {
		return this.doTest(a1);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(long a1) {
		return doTest(a1);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(long a1) {
		return doTest(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureLongPred(long a1) {
		return () -> this.doTest(a1);
	}

	/** Creates function that always returns the same value. */
	static LLongPredicate constant(boolean r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongPredicate l(final @Nonnull LLongPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static boolean call(long a1, final @Nonnull LLongPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongPredicate wrap(final LongPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicate wrap(final @Nonnull LLongPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LLongPredicate negate() {
		return a1 -> !doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLongPredicate and(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) && other.doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLongPredicate or(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) || other.doTest(a1);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLongPredicate xor(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return a1 -> doTest(a1) ^ other.doTest(a1);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLongPredicate isEqual(long target) {
		return a1 -> a1 == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongPredicate longPredComposeLong(@Nonnull final LLongUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LPredicate<V1> longPredCompose(@Nonnull final LToLongFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LLongFunction<V> boolToLongFunction(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToByteFunction boolToLongToByteFunction(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToShortFunction boolToLongToShortFunction(@Nonnull LBoolToShortFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToIntFunction boolToLongToIntFunction(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongUnaryOperator boolToLongUnaryOperator(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToFloatFunction boolToLongToFloatFunction(@Nonnull LBoolToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToDoubleFunction boolToLongToDoubleFunction(@Nonnull LBoolToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToCharFunction boolToLongToCharFunction(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doTest(a1));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongPredicate boolToLongPredicate(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doTest(a1));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongPredicate nestingLongPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongPredicateX<RuntimeException> nestingLongPredX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicate shovingLongPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicateX<RuntimeException> shovingLongPredX() {
		return this;
	}

	// </editor-fold>

}
