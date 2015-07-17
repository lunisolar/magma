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
 * Non-throwing functional interface (lambda) LPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicate<T> extends LPredicateX<T, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LPredicate: boolean doTest(T t)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LPredicate interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(T t) {
		return this.nestingDoTest(t);
	}

	boolean doTest(T t);

	default boolean nestingDoTest(T t) {
		return this.doTest(t);
	}

	default boolean shovingDoTest(T t) {
		return this.doTest(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t) {
		return doTest(t);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t) {
		return doTest(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capturePred(T t) {
		return () -> this.doTest(t);
	}

	static <T> LPredicate<T> constant(boolean r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LPredicate<T> l(final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LPredicate<T> wrap(final java.util.function.Predicate<T> other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicate<T> wrap(final @Nonnull LPredicateX<T, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LPredicate<T> negate() {
		return t -> !doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicate<T> and(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) && other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicate<T> or(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) || other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicate<T> xor(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) ^ other.doTest(t);
	}

	@Nonnull
	static <T> LPredicate<T> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicate<V1> predFrom(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFunction<T, V> boolToFunction(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToByteFunction<T> boolToToByteFunction(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToShortFunction<T> boolToToShortFunction(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToIntFunction<T> boolToToIntFunction(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToLongFunction<T> boolToToLongFunction(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToFloatFunction<T> boolToToFloatFunction(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToDoubleFunction<T> boolToToDoubleFunction(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToCharFunction<T> boolToToCharFunction(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LPredicate<T> boolToPredicate(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doTest(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LPredicate<T> nestingPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LPredicateX<T, RuntimeException> nestingPredX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shovingPred() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicateX<T, RuntimeException> shovingPredX() {
		return this;
	}

	// </editor-fold>

}
