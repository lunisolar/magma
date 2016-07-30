/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: boolean
 *
 * @see LPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicateX<T, X extends Throwable> extends Predicate<T>, MetaPredicate, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LPredicateX: boolean doTest(T a) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LPredicateX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(T a) {
		return this.nestingDoTest(a);
	}

	boolean doTest(T a) throws X;

	default boolean tupleTest(LSingle<T> args) throws X {
		return doTest(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a) {
		try {
			return this.doTest(a);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a) {
		return ((LPredicateX<T, RuntimeException>) this).doTest(a);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(T a, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a) throws X {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a) throws X {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> capturePred(T a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static <T, X extends Throwable> LPredicateX<T, X> constant(boolean r) {
		return a -> r;
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

	static <T, X extends Throwable> boolean call(T a, final @Nonnull LPredicateX<T, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	static <T, X extends Throwable> boolean shoving(T a, final @Nonnull LPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoTest(a);
	}

	static <T, X extends Throwable> boolean nesting(T a, final @Nonnull LPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoTest(a);
	}

	static <T, X extends Throwable, Y extends Throwable> boolean handling(T a, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LPredicateX<T, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoTest(a, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> wrap(final Predicate<T> other) {
		return other::test;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> wrapX(final @Nonnull LPredicate<T> other) {
		return (LPredicateX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LPredicateX<T, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> safe(final @Nullable LPredicateX<T, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LPredicateX<T, X>, Y> safeSupplier(final @Nullable LSupplierX<LPredicateX<T, X>, Y> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LPredicateX<T, X> negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicateX<T, X> and(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicateX<T, X> or(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicateX<T, X> xor(@Nonnull LPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T, X extends Throwable> LPredicateX<T, X> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicateX<V, X> predCompose(@Nonnull final LFunctionX<? super V, ? extends T, X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApply(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> boolToFunc(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> boolToToByteFunc(@Nonnull LBoolToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> boolToToShortFunc(@Nonnull LBoolToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> boolToToIntFunc(@Nonnull LBoolToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> boolToToLongFunc(@Nonnull LBoolToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> boolToToFloatFunc(@Nonnull LBoolToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> boolToToDoubleFunc(@Nonnull LBoolToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> boolToToCharFunc(@Nonnull LBoolToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicateX<T, X> boolToPred(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
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

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shovingPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicateX<T, RuntimeException> shovingPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LPredicate<T> handlePred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> this.handlingDoTest(a, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LPredicateX<T, Y> handlePredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a -> this.handlingDoTest(a, handling);
	}

	// </editor-fold>

}
