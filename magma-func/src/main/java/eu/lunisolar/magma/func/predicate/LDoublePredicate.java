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
 * Non-throwing functional interface (lambda) LDoublePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: boolean
 *
 * @see LDoublePredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoublePredicate extends LDoublePredicateX<RuntimeException>, MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDoublePredicate: boolean doTest(double a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDoublePredicate interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(double a) {
		return this.nestingDoTest(a);
	}

	boolean doTest(double a);

	default boolean tupleTest(LDoubleSingle args) {
		return doTest(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(double a) {
		return this.doTest(a);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(double a) {
		return this.doTest(a);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(double a) {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double a) {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoublePredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureDoublePred(double a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static LDoublePredicate constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDoublePredicate l(final @Nonnull LDoublePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static boolean call(double a, final @Nonnull LDoublePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDoublePredicate wrap(final DoublePredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoublePredicate wrap(final @Nonnull LDoublePredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static LDoublePredicate safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDoublePredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDoublePredicate safe(final @Nullable LDoublePredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDoublePredicate> safeSupplier(final @Nullable LSupplier<LDoublePredicate> supplier) {
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
	default LDoublePredicate negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LDoublePredicate and(@Nonnull LDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LDoublePredicate or(@Nonnull LDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LDoublePredicate xor(@Nonnull LDoublePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LDoublePredicate isEqual(double target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDoublePredicate doublePredComposeDouble(@Nonnull final LDoubleUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsDouble(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> doublePredCompose(@Nonnull final LToDoubleFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsDouble(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunction<V> boolToDoubleFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunction boolToDoubleToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunction boolToDoubleToShortFunc(@Nonnull LBoolToShortFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunction boolToDoubleToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunction boolToDoubleToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunction boolToDoubleToFloatFunc(@Nonnull LBoolToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperator boolToDoubleUnaryOp(@Nonnull LBoolToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunction boolToDoubleToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicate boolToDoublePred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoublePredicate nestingDoublePred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoublePredicateX<RuntimeException> nestingDoublePredX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicate shovingDoublePred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicateX<RuntimeException> shovingDoublePredX() {
		return this;
	}

	// </editor-fold>

}
