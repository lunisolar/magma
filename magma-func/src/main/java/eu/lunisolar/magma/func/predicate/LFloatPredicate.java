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
 * Non-throwing functional interface (lambda) LFloatPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatPredicate extends LFloatPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LFloatPredicate: boolean doTest(float f)";

	boolean doTest(float f);

	default boolean nestingDoTest(float f) {
		return this.doTest(f);
	}

	default boolean shovingDoTest(float f) {
		return this.doTest(f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(float f) {
		return doTest(f);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(float f) {
		return doTest(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier captureFPred(float f) {
		return () -> this.doTest(f);
	}

	static LFloatPredicate constant(boolean r) {
		return f -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFloatPredicate l(final @Nonnull LFloatPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatPredicate wrap(final @Nonnull LFloatPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LFloatPredicate negate() {
		return f -> !doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LFloatPredicate and(@Nonnull LFloatPredicate other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) && other.doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LFloatPredicate or(@Nonnull LFloatPredicate other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) || other.doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LFloatPredicate xor(@Nonnull LFloatPredicate other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) ^ other.doTest(f);
	}

	@Nonnull
	static LFloatPredicate isEqual(float target) {
		return f -> f == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LFloatPredicate fPredFromFloat(@Nonnull final LFloatUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicate<V1> fPredFrom(@Nonnull final LToFloatFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFloatFunction<V> boolToFloatFunction(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToByteFunction boolToFloatToByteFunction(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsByte(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToShortFunction boolToFloatToShortFunction(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsShort(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToIntFunction boolToFloatToIntFunction(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsInt(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToLongFunction boolToFloatToLongFunction(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsLong(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatUnaryOperator boolToFloatUnaryOperator(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsFloat(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToDoubleFunction boolToFloatToDoubleFunction(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsDouble(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToCharFunction boolToFloatToCharFunction(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsChar(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatPredicate boolToFloatPredicate(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doTest(f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatPredicate nestingFPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatPredicateX<RuntimeException> nestingFPredX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatPredicate shovingFPred() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatPredicateX<RuntimeException> shovingFPredX() {
		return this;
	}

	// </editor-fold>

}
