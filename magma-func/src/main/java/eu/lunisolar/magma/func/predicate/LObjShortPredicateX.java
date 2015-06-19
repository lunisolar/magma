/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
 * Throwing functional interface (lambda) LObjShortPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, short s
 *
 * Co-domain: none
 *
 * @see LObjShortPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjShortPredicateX<T, X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LObjShortPredicateX: boolean doTest(T t, short s) throws X";

	public boolean doTest(T t, short s) throws X;

	default boolean nestingDoTest(T t, short s) {
		try {
			return this.doTest(t, s);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T t, short s) {
		return ((LObjShortPredicateX<T, RuntimeException>) this).doTest(t, s);
	}

	default <Y extends Throwable> boolean handlingDoTest(T t, short s, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t, s);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, short s) throws X {
		return doTest(t, s);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, short s) throws X {
		return doTest(t, s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjShortPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T t, short s) {
		return () -> this.doTest(t, s);
	}

	public static <T, X extends Throwable> LObjShortPredicateX<T, X> constant(boolean r) {
		return (t, s) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LObjShortPredicateX<T, X> lX(final @Nonnull LObjShortPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Throwable> LObjShortPredicateX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjShortPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LObjShortPredicateX<T, X> wrapX(final @Nonnull LObjShortPredicate<T> other) {
		return (LObjShortPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LObjShortPredicateX<T, X> negate() {
		return (T t, short s) -> !doTest(t, s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjShortPredicateX<T, X> and(@Nonnull LObjShortPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, short s) -> doTest(t, s) && other.doTest(t, s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjShortPredicateX<T, X> or(@Nonnull LObjShortPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, short s) -> doTest(t, s) || other.doTest(t, s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjShortPredicateX<T, X> xor(@Nonnull LObjShortPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (T t, short s) -> doTest(t, s) ^ other.doTest(t, s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1, X extends Throwable> LObjShortPredicateX<T1, X> isEqual(final T1 v1, final short v2) {
		return (t, s) -> (t == null ? v1 == null : t.equals(v1)) && (s == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjShortPredicateX<V1, X> fromShort(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LShortUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final short v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsShort(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToShortFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsShort(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjShortFunctionX<T, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T t, short s) -> after.doApply(this.doTest(t, s));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjShortPredicate<T> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjShortPredicateX<T, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjShortPredicate<T> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjShortPredicateX<T, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LObjShortPredicate<T> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T t, short s) -> this.handlingDoTest(t, s, handling);
	}

	@Nonnull
	default <Y extends Throwable> LObjShortPredicateX<T, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T t, short s) -> this.handlingDoTest(t, s, handling);
	}

	// </editor-fold>

}
