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
 * Non-throwing functional interface (lambda) LShortPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortPredicate extends LShortPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LShortPredicate: boolean doTest(short s)";

	boolean doTest(short s);

	default boolean nestingDoTest(short s) {
		return this.doTest(s);
	}

	default boolean shovingDoTest(short s) {
		return this.doTest(s);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(short s) {
		return doTest(s);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(short s) {
		return doTest(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier captureSPred(short s) {
		return () -> this.doTest(s);
	}

	static LShortPredicate constant(boolean r) {
		return s -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LShortPredicate l(final @Nonnull LShortPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortPredicate wrap(final @Nonnull LShortPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LShortPredicate negate() {
		return s -> !doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LShortPredicate and(@Nonnull LShortPredicate other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) && other.doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LShortPredicate or(@Nonnull LShortPredicate other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) || other.doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LShortPredicate xor(@Nonnull LShortPredicate other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) ^ other.doTest(s);
	}

	@Nonnull
	static LShortPredicate isEqual(short target) {
		return s -> s == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LShortPredicate sPredFromShort(@Nonnull final LShortUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicate<V1> sPredFrom(@Nonnull final LToShortFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LShortFunction<V> boolToShortFunction(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApply(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToByteFunction boolToShortToByteFunction(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsByte(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortUnaryOperator boolToShortUnaryOperator(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsShort(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToIntFunction boolToShortToIntFunction(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsInt(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToLongFunction boolToShortToLongFunction(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsLong(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToFloatFunction boolToShortToFloatFunction(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsFloat(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToDoubleFunction boolToShortToDoubleFunction(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsDouble(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToCharFunction boolToShortToCharFunction(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsChar(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortPredicate boolToShortPredicate(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApply(this.doTest(s));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortPredicate nestingSPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortPredicateX<RuntimeException> nestingSPredX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicate shovingSPred() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicateX<RuntimeException> shovingSPredX() {
		return this;
	}

	// </editor-fold>

}
