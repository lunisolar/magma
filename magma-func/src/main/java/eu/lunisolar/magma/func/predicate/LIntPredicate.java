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
 * Non-throwing functional interface (lambda) LIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntPredicate extends LIntPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LIntPredicate: boolean doTest(int i)";

	@Override
	@Deprecated
	// calling this method via LIntPredicate interface should be discouraged.
	default boolean test(int i) {
		return this.nestingDoTest(i);
	}

	boolean doTest(int i);

	default boolean nestingDoTest(int i) {
		return this.doTest(i);
	}

	default boolean shovingDoTest(int i) {
		return this.doTest(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(int i) {
		return doTest(i);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(int i) {
		return doTest(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier captureIPred(int i) {
		return () -> this.doTest(i);
	}

	static LIntPredicate constant(boolean r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntPredicate l(final @Nonnull LIntPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntPredicate wrap(final java.util.function.IntPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntPredicate wrap(final @Nonnull LIntPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LIntPredicate negate() {
		return i -> !doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LIntPredicate and(@Nonnull LIntPredicate other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) && other.doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LIntPredicate or(@Nonnull LIntPredicate other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) || other.doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LIntPredicate xor(@Nonnull LIntPredicate other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) ^ other.doTest(i);
	}

	@Nonnull
	static LIntPredicate isEqual(int target) {
		return i -> i == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntPredicate iPredFromInt(@Nonnull final LIntUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicate<V1> iPredFrom(@Nonnull final LToIntFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LIntFunction<V> boolToIntFunction(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToByteFunction boolToIntToByteFunction(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToShortFunction boolToIntToShortFunction(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntUnaryOperator boolToIntUnaryOperator(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToLongFunction boolToIntToLongFunction(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToFloatFunction boolToIntToFloatFunction(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToDoubleFunction boolToIntToDoubleFunction(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToCharFunction boolToIntToCharFunction(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntPredicate boolToIntPredicate(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doTest(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntPredicate nestingIPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntPredicateX<RuntimeException> nestingIPredX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicate shovingIPred() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicateX<RuntimeException> shovingIPredX() {
		return this;
	}

	// </editor-fold>

}
