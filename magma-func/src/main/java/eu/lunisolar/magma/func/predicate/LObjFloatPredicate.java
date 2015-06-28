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
 * Non-throwing functional interface (lambda) LObjFloatPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, float f
 *
 * Co-domain: none
 *
 * @see LObjFloatPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjFloatPredicate<T> extends LObjFloatPredicateX<T, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LObjFloatPredicate: boolean doTest(T t, float f)";

	boolean doTest(T t, float f);

	default boolean nestingDoTest(T t, float f) {
		return this.doTest(t, f);
	}

	default boolean shovingDoTest(T t, float f) {
		return this.doTest(t, f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, float f) {
		return doTest(t, f);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, float f) {
		return doTest(t, f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjFloatPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(T t, float f) {
		return () -> this.doTest(t, f);
	}

	static <T> LObjFloatPredicate<T> constant(boolean r) {
		return (t, f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjFloatPredicate<T> l(final @Nonnull LObjFloatPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatPredicate<T> wrap(final @Nonnull LObjFloatPredicateX<T, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> negate() {
		return (T t, float f) -> !doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> and(@Nonnull LObjFloatPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, float f) -> doTest(t, f) && other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> or(@Nonnull LObjFloatPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, float f) -> doTest(t, f) || other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> xor(@Nonnull LObjFloatPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, float f) -> doTest(t, f) ^ other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static <T1> LObjFloatPredicate<T1> isEqual(final T1 v1, final float v2) {
		return (t, f) -> (t == null ? v1 == null : t.equals(v1)) && (f == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjFloatPredicate<V1> fromFloat(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LFloatUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final float v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> from(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToFloatFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjFloatFunction<T, V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T t, float f) -> after.doApply(this.doTest(t, f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjFloatPredicate<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjFloatPredicateX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatPredicate<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatPredicateX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
