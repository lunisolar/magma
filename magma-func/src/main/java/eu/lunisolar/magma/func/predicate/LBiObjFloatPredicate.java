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
 * Non-throwing functional interface (lambda) LBiObjFloatPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 t1,T2 t2, float f
 *
 * Co-domain: none
 *
 * @see LBiObjFloatPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFloatPredicate<T1, T2> extends LBiObjFloatPredicateX<T1, T2, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBiObjFloatPredicate: boolean doTest(T1 t1,T2 t2, float f)";

	public boolean doTest(T1 t1, T2 t2, float f);

	default boolean nestingDoTest(T1 t1, T2 t2, float f) {
		return this.doTest(t1, t2, f);
	}

	default boolean shovingDoTest(T1 t1, T2 t2, float f) {
		return this.doTest(t1, t2, f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 t1, T2 t2, float f) {
		return doTest(t1, t2, f);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 t1, T2 t2, float f) {
		return doTest(t1, t2, f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjFloatPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(T1 t1, T2 t2, float f) {
		return () -> this.doTest(t1, t2, f);
	}

	public static <T1, T2> LBiObjFloatPredicate<T1, T2> constant(boolean r) {
		return (t1, t2, f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2> LBiObjFloatPredicate<T1, T2> l(final @Nonnull LBiObjFloatPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjFloatPredicate<T1, T2> wrap(final @Nonnull LBiObjFloatPredicateX<T1, T2, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBiObjFloatPredicate<T1, T2> negate() {
		return (T1 t1, T2 t2, float f) -> !doTest(t1, t2, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjFloatPredicate<T1, T2> and(@Nonnull LBiObjFloatPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, float f) -> doTest(t1, t2, f) && other.doTest(t1, t2, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjFloatPredicate<T1, T2> or(@Nonnull LBiObjFloatPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, float f) -> doTest(t1, t2, f) || other.doTest(t1, t2, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjFloatPredicate<T1, T2> xor(@Nonnull LBiObjFloatPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, float f) -> doTest(t1, t2, f) ^ other.doTest(t1, t2, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1, T2> LBiObjFloatPredicate<T1, T2> isEqual(final T1 v1, final T2 v2, final float v3) {
		return (t1, t2, f) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2)) && (f == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjFloatPredicate<V1, V2> fromFloat(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFloatUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final float v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFloat(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> from(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFloatFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFloat(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LBiObjFloatFunction<T1, T2, V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, float f) -> after.doApply(this.doTest(t1, t2, f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjFloatPredicate<T1, T2> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjFloatPredicateX<T1, T2, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjFloatPredicate<T1, T2> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjFloatPredicateX<T1, T2, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
