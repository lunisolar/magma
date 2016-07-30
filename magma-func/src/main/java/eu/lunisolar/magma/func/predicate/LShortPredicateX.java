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
 * Throwing functional interface (lambda) LShortPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: boolean
 *
 * @see LShortPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortPredicateX<X extends Throwable> extends MetaPredicate, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LShortPredicateX: boolean doTest(short a) throws X";

	boolean doTest(short a) throws X;

	default boolean tupleTest(LShortSingle args) throws X {
		return doTest(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(short a) {
		try {
			return this.doTest(a);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(short a) {
		return ((LShortPredicateX<RuntimeException>) this).doTest(a);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(short a, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(short a) throws X {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(short a) throws X {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureShortPred(short a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LShortPredicateX<X> constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> lX(final @Nonnull LShortPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LShortPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> boolean call(short a, final @Nonnull LShortPredicateX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	static <X extends Throwable> boolean shoving(short a, final @Nonnull LShortPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoTest(a);
	}

	static <X extends Throwable> boolean nesting(short a, final @Nonnull LShortPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoTest(a);
	}

	static <X extends Throwable, Y extends Throwable> boolean handling(short a, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LShortPredicateX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoTest(a, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> wrapX(final @Nonnull LShortPredicate other) {
		return (LShortPredicateX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortPredicateX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> safe(final @Nullable LShortPredicateX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortPredicateX<X>, Y> safeSupplier(final @Nullable LSupplierX<LShortPredicateX<X>, Y> supplier) {
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
	default LShortPredicateX<X> negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LShortPredicateX<X> and(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LShortPredicateX<X> or(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LShortPredicateX<X> xor(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> isEqual(short target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LShortPredicateX<X> shortPredComposeShort(@Nonnull final LShortUnaryOperatorX<X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsShort(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicateX<V, X> shortPredCompose(@Nonnull final LToShortFunctionX<? super V, X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsShort(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunctionX<V, X> boolToShortFunc(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunctionX<X> boolToShortToByteFunc(@Nonnull LBoolToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperatorX<X> boolToShortUnaryOp(@Nonnull LBoolToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunctionX<X> boolToShortToIntFunc(@Nonnull LBoolToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunctionX<X> boolToShortToLongFunc(@Nonnull LBoolToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunctionX<X> boolToShortToFloatFunc(@Nonnull LBoolToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunctionX<X> boolToShortToDoubleFunc(@Nonnull LBoolToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunctionX<X> boolToShortToCharFunc(@Nonnull LBoolToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicateX<X> boolToShortPred(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortPredicate nestingShortPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortPredicateX<RuntimeException> nestingShortPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicate shovingShortPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicateX<RuntimeException> shovingShortPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LShortPredicate handleShortPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> this.handlingDoTest(a, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LShortPredicateX<Y> handleShortPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a -> this.handlingDoTest(a, handling);
	}

	// </editor-fold>

}
