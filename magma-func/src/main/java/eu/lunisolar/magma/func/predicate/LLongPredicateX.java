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
 * Throwing functional interface (lambda) LLongPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongPredicateX<X extends Throwable> extends java.util.function.LongPredicate, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongPredicateX: boolean doTest(long l) throws X";

	@Override
	@Deprecated
	// calling this method via LLongPredicateX interface should be discouraged.
	default boolean test(long l) {
		return this.nestingDoTest(l);
	}

	boolean doTest(long l) throws X;

	default boolean nestingDoTest(long l) {
		try {
			return this.doTest(l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(long l) {
		return ((LLongPredicateX<RuntimeException>) this).doTest(l);
	}

	default <Y extends Throwable> boolean handlingDoTest(long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(long l) throws X {
		return doTest(l);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(long l) throws X {
		return doTest(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(long l) {
		return () -> this.doTest(l);
	}

	static <X extends Throwable> LLongPredicateX<X> constant(boolean r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> lX(final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> wrap(final java.util.function.LongPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> wrapX(final @Nonnull LLongPredicate other) {
		return (LLongPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LLongPredicateX<X> negate() {
		return l -> !doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLongPredicateX<X> and(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return l -> doTest(l) && other.doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLongPredicateX<X> or(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return l -> doTest(l) || other.doTest(l);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLongPredicateX<X> xor(@Nonnull LLongPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return l -> doTest(l) ^ other.doTest(l);
	}

	@Nonnull
	static <X extends Throwable> LLongPredicateX<X> isEqual(long target) {
		return l -> l == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LLongPredicateX<X> fromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doTest(l));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsBoolean(this.doTest(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongPredicate nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongPredicateX<RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicate shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongPredicateX<RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLongPredicate handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return l -> this.handlingDoTest(l, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLongPredicateX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return l -> this.handlingDoTest(l, handling);
	}

	// </editor-fold>

}
