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
 * Throwing functional interface (lambda) LCharPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharPredicateX<X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LCharPredicateX: boolean doTest(char c) throws X";

	boolean doTest(char c) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoTest(char c) {
		try {
			return this.doTest(c);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(char c) {
		return ((LCharPredicateX<RuntimeException>) this).doTest(c);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(char c, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(c);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(char c) throws X {
		return doTest(c);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(char c) throws X {
		return doTest(c);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> captureCharPred(char c) {
		return () -> this.doTest(c);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LCharPredicateX<X> constant(boolean r) {
		return c -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharPredicateX<X> lX(final @Nonnull LCharPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharPredicateX<X> wrapX(final @Nonnull LCharPredicate other) {
		return (LCharPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LCharPredicateX<X> negate() {
		return c -> !doTest(c);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LCharPredicateX<X> and(@Nonnull LCharPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) && other.doTest(c);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LCharPredicateX<X> or(@Nonnull LCharPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) || other.doTest(c);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LCharPredicateX<X> xor(@Nonnull LCharPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return c -> doTest(c) ^ other.doTest(c);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <X extends Throwable> LCharPredicateX<X> isEqual(char target) {
		return c -> c == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharPredicateX<X> charPredComposeChar(@Nonnull final LCharUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsChar(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LPredicateX<V1, X> charPredCompose(@Nonnull final LToCharFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LCharFunctionX<V, X> boolToCharFunction(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApply(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToByteFunctionX<X> boolToCharToByteFunction(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsByte(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToShortFunctionX<X> boolToCharToShortFunction(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsShort(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToIntFunctionX<X> boolToCharToIntFunction(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsInt(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToLongFunctionX<X> boolToCharToLongFunction(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsLong(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToFloatFunctionX<X> boolToCharToFloatFunction(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsFloat(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharToDoubleFunctionX<X> boolToCharToDoubleFunction(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsDouble(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharUnaryOperatorX<X> boolToCharUnaryOperator(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsChar(this.doTest(c));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LCharPredicateX<X> boolToCharPredicate(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApply(this.doTest(c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharPredicate nestingCharPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharPredicateX<RuntimeException> nestingCharPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharPredicate shovingCharPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharPredicateX<RuntimeException> shovingCharPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LCharPredicate handleCharPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return c -> this.handlingDoTest(c, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LCharPredicateX<Y> handleCharPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return c -> this.handlingDoTest(c, handling);
	}

	// </editor-fold>

}
