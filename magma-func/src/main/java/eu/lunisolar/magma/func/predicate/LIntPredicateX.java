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
 * Throwing functional interface (lambda) LIntPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntPredicateX<X extends Throwable> extends java.util.function.IntPredicate, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LIntPredicateX: boolean doTest(int i) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntPredicateX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(int i) {
		return this.nestingDoTest(i);
	}

	boolean doTest(int i) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(int i) {
		try {
			return this.doTest(i);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(int i) {
		return ((LIntPredicateX<RuntimeException>) this).doTest(i);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(i);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(int i) throws X {
		return doTest(i);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(int i) throws X {
		return doTest(i);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> captureIntPred(int i) {
		return () -> this.doTest(i);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LIntPredicateX<X> constant(boolean r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntPredicateX<X> lX(final @Nonnull LIntPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LIntPredicateX<X> wrap(final java.util.function.IntPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntPredicateX<X> wrapX(final @Nonnull LIntPredicate other) {
		return (LIntPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LIntPredicateX<X> negate() {
		return i -> !doTest(i);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LIntPredicateX<X> and(@Nonnull LIntPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) && other.doTest(i);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LIntPredicateX<X> or(@Nonnull LIntPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) || other.doTest(i);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LIntPredicateX<X> xor(@Nonnull LIntPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return i -> doTest(i) ^ other.doTest(i);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <X extends Throwable> LIntPredicateX<X> isEqual(int target) {
		return i -> i == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntPredicateX<X> intPredComposeInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsInt(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LPredicateX<V1, X> intPredCompose(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> boolToIntFunction(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> boolToIntToByteFunction(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> boolToIntToShortFunction(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> boolToIntUnaryOperator(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> boolToIntToLongFunction(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> boolToIntToFloatFunction(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> boolToIntToDoubleFunction(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> boolToIntToCharFunction(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntPredicateX<X> boolToIntPredicate(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doTest(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntPredicate nestingIntPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntPredicateX<RuntimeException> nestingIntPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicate shovingIntPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicateX<RuntimeException> shovingIntPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LIntPredicate handleIntPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return i -> this.handlingDoTest(i, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LIntPredicateX<Y> handleIntPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return i -> this.handlingDoTest(i, handling);
	}

	// </editor-fold>

}
