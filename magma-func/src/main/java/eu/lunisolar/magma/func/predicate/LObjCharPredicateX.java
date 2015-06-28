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
 * Throwing functional interface (lambda) LObjCharPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, char c
 *
 * Co-domain: none
 *
 * @see LObjCharPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjCharPredicateX<T, X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LObjCharPredicateX: boolean doTest(T t, char c) throws X";

	boolean doTest(T t, char c) throws X;

	default boolean nestingDoTest(T t, char c) {
		try {
			return this.doTest(t, c);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T t, char c) {
		return ((LObjCharPredicateX<T, RuntimeException>) this).doTest(t, c);
	}

	default <Y extends Throwable> boolean handlingDoTest(T t, char c, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t, c);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, char c) throws X {
		return doTest(t, c);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, char c) throws X {
		return doTest(t, c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjCharPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T t, char c) {
		return () -> this.doTest(t, c);
	}

	static <T, X extends Throwable> LObjCharPredicateX<T, X> constant(boolean r) {
		return (t, c) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjCharPredicateX<T, X> lX(final @Nonnull LObjCharPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjCharPredicateX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjCharPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjCharPredicateX<T, X> wrapX(final @Nonnull LObjCharPredicate<T> other) {
		return (LObjCharPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LObjCharPredicateX<T, X> negate() {
		return (T t, char c) -> !doTest(t, c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjCharPredicateX<T, X> and(@Nonnull LObjCharPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, char c) -> doTest(t, c) && other.doTest(t, c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjCharPredicateX<T, X> or(@Nonnull LObjCharPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, char c) -> doTest(t, c) || other.doTest(t, c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjCharPredicateX<T, X> xor(@Nonnull LObjCharPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, char c) -> doTest(t, c) ^ other.doTest(t, c);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static <T1, X extends Throwable> LObjCharPredicateX<T1, X> isEqual(final T1 v1, final char v2) {
		return (t, c) -> (t == null ? v1 == null : t.equals(v1)) && (c == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjCharPredicateX<V1, X> fromChar(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LCharUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final char v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsChar(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToCharFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsChar(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjCharFunctionX<T, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t, char c) -> after.doApply(this.doTest(t, c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjCharPredicate<T> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjCharPredicateX<T, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjCharPredicate<T> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjCharPredicateX<T, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LObjCharPredicate<T> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t, char c) -> this.handlingDoTest(t, c, handling);
	}

	@Nonnull
	default <Y extends Throwable> LObjCharPredicateX<T, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t, char c) -> this.handlingDoTest(t, c, handling);
	}

	// </editor-fold>

}
