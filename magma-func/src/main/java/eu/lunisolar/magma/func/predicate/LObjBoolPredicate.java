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
 * Non-throwing functional interface (lambda) LObjBoolPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, boolean b
 *
 * Co-domain: none
 *
 * @see LObjBoolPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolPredicate<T> extends LObjBoolPredicateX<T, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LObjBoolPredicate: boolean doTest(T t, boolean b)";

	boolean doTest(T t, boolean b);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(T t, boolean b) {
		return this.doTest(t, b);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T t, boolean b) {
		return this.doTest(t, b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, boolean b) {
		return doTest(t, b);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, boolean b) {
		return doTest(t, b);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBoolPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureObjBoolPred(T t, boolean b) {
		return () -> this.doTest(t, b);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjBoolPredicate<T> constant(boolean r) {
		return (t, b) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjBoolPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (t, b) -> func.doTest(t);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjBoolPredicate<T> test2nd(@Nonnull LLogicalOperator func) {
		return (t, b) -> func.doApply(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBoolPredicate<T> l(final @Nonnull LObjBoolPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicate<T> wrap(final @Nonnull LObjBoolPredicateX<T, X> other) {
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
	default LObjBoolPredicate<T> negate() {
		return (T t, boolean b) -> !doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjBoolPredicate<T> and(@Nonnull LObjBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) && other.doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBoolPredicate<T> or(@Nonnull LObjBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) || other.doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBoolPredicate<T> xor(@Nonnull LObjBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) ^ other.doTest(t, b);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1> LObjBoolPredicate<T1> isEqual(final T1 v1, final boolean v2) {
		return (t, b) -> (t == null ? v1 == null : t.equals(v1)) && (b == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjBoolPredicate<V1> objBoolPredComposeBoolean(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLogicalOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final boolean v2) -> this.doTest(before1.doApply(v1), before2.doApply(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> objBoolPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doTest(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjBoolFunction<T, V> boolToObjBoolFunction(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T t, boolean b) -> after.doApply(this.doTest(t, b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjBoolPredicate<T> nestingObjBoolPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjBoolPredicateX<T, RuntimeException> nestingObjBoolPredX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBoolPredicate<T> shovingObjBoolPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBoolPredicateX<T, RuntimeException> shovingObjBoolPredX() {
		return this;
	}

	// </editor-fold>

}