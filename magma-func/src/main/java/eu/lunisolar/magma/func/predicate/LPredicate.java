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
 * Non-throwing functional interface (lambda) LPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicate<T> extends LPredicateX<T, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LPredicate: boolean doTest(T t)";

	@Override
	@Deprecated
	// calling this method via LPredicate interface should be discouraged.
	default boolean test(T t) {
		return this.nestingDoTest(t);
	}

	public boolean doTest(T t);

	default boolean nestingDoTest(T t) {
		return this.doTest(t);
	}

	default boolean shovingDoTest(T t) {
		return this.doTest(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t) {
		return doTest(t);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t) {
		return doTest(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(T t) {
		return () -> this.doTest(t);
	}

	public static <T> LPredicate<T> constant(boolean r) {
		return (t) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> LPredicate<T> l(final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T> LPredicate<T> wrap(final java.util.function.Predicate<T> other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LPredicate<T> wrap(final @Nonnull LPredicateX<T, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LPredicate<T> negate() {
		return (T t) -> !doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicate<T> and(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t) -> doTest(t) && other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicate<T> or(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t) -> doTest(t) || other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicate<T> xor(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (T t) -> doTest(t) ^ other.doTest(t);
	}

	@Nonnull
	public static <T> LPredicate<T> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return (final V1 v1) -> this.doTest(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApply(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LBooleanToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsByte(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LBooleanToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsShort(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LBooleanToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsInt(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LBooleanToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsLong(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsFloat(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsDouble(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LBooleanToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsChar(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (T t) -> after.doApplyAsBoolean(this.doTest(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LPredicate<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LPredicateX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicateX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
