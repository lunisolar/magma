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
 * Throwing functional interface (lambda) LPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicateX<T, X extends Throwable> extends java.util.function.Predicate<T>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LPredicateX: boolean doTest(T t) throws X";

	@Override
	@Deprecated
	// calling this method via LPredicateX interface should be discouraged.
	default boolean test(T t) {
		return this.nestingDoTest(t);
	}

	boolean doTest(T t) throws X;

	default boolean nestingDoTest(T t) {
		try {
			return this.doTest(t);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T t) {
		return ((LPredicateX<T, RuntimeException>) this).doTest(t);
	}

	default <Y extends Throwable> boolean handlingDoTest(T t, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t) throws X {
		return doTest(t);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t) throws X {
		return doTest(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capturePred(T t) {
		return () -> this.doTest(t);
	}

	static <T, X extends Throwable> LPredicateX<T, X> constant(boolean r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> lX(final @Nonnull LPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> wrap(final java.util.function.Predicate<T> other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> wrapX(final @Nonnull LPredicate<T> other) {
		return (LPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LPredicateX<T, X> negate() {
		return t -> !doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicateX<T, X> and(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) && other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicateX<T, X> or(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) || other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicateX<T, X> xor(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return t -> doTest(t) ^ other.doTest(t);
	}

	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> predFrom(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> boolToFunction(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> boolToToByteFunction(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> boolToToShortFunction(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> boolToToIntFunction(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> boolToToLongFunction(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> boolToToFloatFunction(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> boolToToDoubleFunction(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> boolToToCharFunction(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LPredicateX<T, X> boolToPredicate(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doTest(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LPredicate<T> nestingPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LPredicateX<T, RuntimeException> nestingPredX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shovingPred() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicateX<T, RuntimeException> shovingPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LPredicate<T> handlePred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return t -> this.handlingDoTest(t, handling);
	}

	@Nonnull
	default <Y extends Throwable> LPredicateX<T, Y> handlePredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return t -> this.handlingDoTest(t, handling);
	}

	// </editor-fold>

}
