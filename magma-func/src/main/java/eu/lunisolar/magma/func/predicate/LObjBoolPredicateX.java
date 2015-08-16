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
 * Throwing functional interface (lambda) LObjBoolPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, boolean b
 *
 * Co-domain: none
 *
 * @see LObjBoolPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolPredicateX<T, X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LObjBoolPredicateX: boolean doTest(T t, boolean b) throws X";

	boolean doTest(T t, boolean b) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(T t, boolean b) {
		try {
			return this.doTest(t, b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T t, boolean b) {
		return ((LObjBoolPredicateX<T, RuntimeException>) this).doTest(t, b);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(T t, boolean b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t, b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, boolean b) throws X {
		return doTest(t, b);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, boolean b) throws X {
		return doTest(t, b);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBoolPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureObjBoolPred(T t, boolean b) {
		return () -> this.doTest(t, b);
	}

	/** Creates function that always returns the same value. */
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> constant(boolean r) {
		return (t, b) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> test1st(@Nonnull LPredicateX<T, X> func) {
		return (t, b) -> func.doTest(t);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> test2nd(@Nonnull LLogicalOperatorX<X> func) {
		return (t, b) -> func.doApply(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> lX(final @Nonnull LObjBoolPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjBoolPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjBoolPredicateX<T, X> wrapX(final @Nonnull LObjBoolPredicate<T> other) {
		return (LObjBoolPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LObjBoolPredicateX<T, X> negate() {
		return (T t, boolean b) -> !doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjBoolPredicateX<T, X> and(@Nonnull LObjBoolPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) && other.doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBoolPredicateX<T, X> or(@Nonnull LObjBoolPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) || other.doTest(t, b);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBoolPredicateX<T, X> xor(@Nonnull LObjBoolPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, boolean b) -> doTest(t, b) ^ other.doTest(t, b);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, X extends Throwable> LObjBoolPredicateX<T1, X> isEqual(final T1 v1, final boolean v2) {
		return (t, b) -> (t == null ? v1 == null : t.equals(v1)) && (b == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjBoolPredicateX<V1, X> objBoolPredComposeBoolean(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LLogicalOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final boolean v2) -> this.doTest(before1.doApply(v1), before2.doApply(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> objBoolPredCompose(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LPredicateX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doTest(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjBoolFunctionX<T, V, X> boolToObjBoolFunction(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t, boolean b) -> after.doApply(this.doTest(t, b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjBoolPredicate<T> nestingObjBoolPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjBoolPredicateX<T, RuntimeException> nestingObjBoolPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBoolPredicate<T> shovingObjBoolPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBoolPredicateX<T, RuntimeException> shovingObjBoolPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LObjBoolPredicate<T> handleObjBoolPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t, boolean b) -> this.handlingDoTest(t, b, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LObjBoolPredicateX<T, Y> handleObjBoolPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t, boolean b) -> this.handlingDoTest(t, b, handling);
	}

	// </editor-fold>

}