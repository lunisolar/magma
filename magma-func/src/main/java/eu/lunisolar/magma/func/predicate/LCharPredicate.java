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
 * Non-throwing functional interface (lambda) LCharPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharPredicate extends LCharPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LCharPredicate: boolean doTest(char c)";

	boolean doTest(char c);

	default boolean nestingDoTest(char c) {
		return this.doTest(c);
	}

	default boolean shovingDoTest(char c) {
		return this.doTest(c);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(char c) {
		return doTest(c);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(char c) {
		return doTest(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(char c) {
		return () -> this.doTest(c);
	}

	static LCharPredicate constant(boolean r) {
		return c -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharPredicate l(final @Nonnull LCharPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharPredicate wrap(final @Nonnull LCharPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LCharPredicate negate() {
		return c -> !doTest(c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LCharPredicate and(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) && other.doTest(c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LCharPredicate or(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) || other.doTest(c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LCharPredicate xor(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) ^ other.doTest(c);
	}

	@Nonnull
	static LCharPredicate isEqual(char target) {
		return c -> c == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharPredicate fromChar(@Nonnull final LCharUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LToCharFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApply(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsByte(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsShort(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsInt(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsLong(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsFloat(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsDouble(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsChar(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharPredicate thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsBoolean(this.doTest(c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharPredicate nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharPredicateX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharPredicate shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharPredicateX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
