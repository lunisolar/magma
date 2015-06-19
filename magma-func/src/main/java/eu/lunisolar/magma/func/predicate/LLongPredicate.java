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
 * Non-throwing functional interface (lambda) LLongPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongPredicate extends LLongPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LLongPredicate: boolean doTest(long l)";

	@Override
	@Deprecated
	// calling this method via LLongPredicate interface should be discouraged.
	default boolean test(long l) {
		return this.nestingDoTest(l);
	}

	public boolean doTest(long l);

	default boolean nestingDoTest(long l) {
		return this.doTest(l);
	}

	default boolean shovingDoTest(long l) {
		return this.doTest(l);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(long l) {
		return doTest(l);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(long l) {
		return doTest(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(long l) {
		return () -> this.doTest(l);
	}

	public static LLongPredicate constant(boolean r) {
		return (l) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LLongPredicate l(final @Nonnull LLongPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LLongPredicate wrap(final java.util.function.LongPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LLongPredicate wrap(final @Nonnull LLongPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LLongPredicate negate() {
		return (long l) -> !doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLongPredicate and(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return (long l) -> doTest(l) && other.doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLongPredicate or(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return (long l) -> doTest(l) || other.doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLongPredicate xor(@Nonnull LLongPredicate other) {
		Null.nonNullArg(other, "other");
		return (long l) -> doTest(l) ^ other.doTest(l);
	}

	@Nonnull
	public static LLongPredicate isEqual(long target) {
		return l -> l == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LLongPredicate fromLong(@Nonnull final LLongUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final long v1) -> this.doTest(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LToLongFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doTest(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApply(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsByte(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsShort(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsInt(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsLong(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsFloat(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsDouble(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsChar(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongPredicate thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsBoolean(this.doTest(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongPredicate nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongPredicateX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicate shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicateX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
