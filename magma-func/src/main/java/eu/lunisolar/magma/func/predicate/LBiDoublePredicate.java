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
 * Non-throwing functional interface (lambda) LBiDoublePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): double d1,double d2
 *
 * Co-domain: none
 *
 * @see LBiDoublePredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiDoublePredicate extends LBiDoublePredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LBiDoublePredicate: boolean doTest(double d1,double d2)";

	boolean doTest(double d1, double d2);

	default boolean nestingDoTest(double d1, double d2) {
		return this.doTest(d1, d2);
	}

	default boolean shovingDoTest(double d1, double d2) {
		return this.doTest(d1, d2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(double d1, double d2) {
		return doTest(d1, d2);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double d1, double d2) {
		return doTest(d1, d2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiDoublePredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(double d1, double d2) {
		return () -> this.doTest(d1, d2);
	}

	static LBiDoublePredicate constant(boolean r) {
		return (d1, d2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiDoublePredicate l(final @Nonnull LBiDoublePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBiDoublePredicate wrap(final @Nonnull LBiDoublePredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBiDoublePredicate negate() {
		return (double d1, double d2) -> !doTest(d1, d2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiDoublePredicate and(@Nonnull LBiDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return (double d1, double d2) -> doTest(d1, d2) && other.doTest(d1, d2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiDoublePredicate or(@Nonnull LBiDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return (double d1, double d2) -> doTest(d1, d2) || other.doTest(d1, d2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiDoublePredicate xor(@Nonnull LBiDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return (double d1, double d2) -> doTest(d1, d2) ^ other.doTest(d1, d2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static LBiDoublePredicate isEqual(final double v1, final double v2) {
		return (d1, d2) -> (d1 == v1) && (d2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBiDoublePredicate fromDouble(@Nonnull final LDoubleUnaryOperator before1, @Nonnull final LDoubleUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final double v1, final double v2) -> this.doTest(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> from(@Nonnull final LToDoubleFunction<? super V1> before1, @Nonnull final LToDoubleFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LDoubleBiFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (double d1, double d2) -> after.doApply(this.doTest(d1, d2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiDoublePredicate nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiDoublePredicateX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiDoublePredicate shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiDoublePredicateX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
