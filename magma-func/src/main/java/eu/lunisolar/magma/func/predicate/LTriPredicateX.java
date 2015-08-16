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
 * Throwing functional interface (lambda) LTriPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 t1,T2 t2,T3 t3
 *
 * Co-domain: none
 *
 * @see LTriPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriPredicateX<T1, T2, T3, X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LTriPredicateX: boolean doTest(T1 t1,T2 t2,T3 t3) throws X";

	boolean doTest(T1 t1, T2 t2, T3 t3) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(T1 t1, T2 t2, T3 t3) {
		try {
			return this.doTest(t1, t2, t3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T1 t1, T2 t2, T3 t3) {
		return ((LTriPredicateX<T1, T2, T3, RuntimeException>) this).doTest(t1, t2, t3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(T1 t1, T2 t2, T3 t3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t1, t2, t3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 t1, T2 t2, T3 t3) throws X {
		return doTest(t1, t2, t3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 t1, T2 t2, T3 t3) throws X {
		return doTest(t1, t2, t3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureTriPred(T1 t1, T2 t2, T3 t3) {
		return () -> this.doTest(t1, t2, t3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> constant(boolean r) {
		return (t1, t2, t3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> test1st(@Nonnull LPredicateX<T1, X> func) {
		return (t1, t2, t3) -> func.doTest(t1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> test2nd(@Nonnull LPredicateX<T2, X> func) {
		return (t1, t2, t3) -> func.doTest(t2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> test3rd(@Nonnull LPredicateX<T3, X> func) {
		return (t1, t2, t3) -> func.doTest(t3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> lX(final @Nonnull LTriPredicateX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> lX(@Nonnull Class<X> xClass, final @Nonnull LTriPredicateX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> wrapX(final @Nonnull LTriPredicate<T1, T2, T3> other) {
		return (LTriPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LTriPredicateX<T1, T2, T3, X> negate() {
		return (T1 t1, T2 t2, T3 t3) -> !doTest(t1, t2, t3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LTriPredicateX<T1, T2, T3, X> and(@Nonnull LTriPredicateX<? super T1, ? super T2, ? super T3, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, T3 t3) -> doTest(t1, t2, t3) && other.doTest(t1, t2, t3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriPredicateX<T1, T2, T3, X> or(@Nonnull LTriPredicateX<? super T1, ? super T2, ? super T3, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, T3 t3) -> doTest(t1, t2, t3) || other.doTest(t1, t2, t3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriPredicateX<T1, T2, T3, X> xor(@Nonnull LTriPredicateX<? super T1, ? super T2, ? super T3, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, T3 t3) -> doTest(t1, t2, t3) ^ other.doTest(t1, t2, t3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriPredicateX<T1, T2, T3, X> isEqual(final T1 v1, final T2 v2, final T3 v3) {
		return (t1, t2, t3) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2)) && (t3 == null ? v3 == null : t3.equals(v3));
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> triPredCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2,
			@Nonnull final LFunctionX<? super V3, ? extends T3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final V3 v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LTriFunctionX<T1, T2, T3, V, X> boolToTriFunction(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, T3 t3) -> after.doApply(this.doTest(t1, t2, t3));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriPredicate<T1, T2, T3> nestingTriPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTriPredicateX<T1, T2, T3, RuntimeException> nestingTriPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriPredicate<T1, T2, T3> shovingTriPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriPredicateX<T1, T2, T3, RuntimeException> shovingTriPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LTriPredicate<T1, T2, T3> handleTriPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2, T3 t3) -> this.handlingDoTest(t1, t2, t3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LTriPredicateX<T1, T2, T3, Y> handleTriPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2, T3 t3) -> this.handlingDoTest(t1, t2, t3, handling);
	}

	// </editor-fold>

}
