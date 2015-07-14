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
 * Throwing functional interface (lambda) LDoublePredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoublePredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoublePredicateX<X extends Throwable> extends java.util.function.DoublePredicate, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LDoublePredicateX: boolean doTest(double d) throws X";

	@Override
	@Deprecated
	// calling this method via LDoublePredicateX interface should be discouraged.
	default boolean test(double d) {
		return this.nestingDoTest(d);
	}

	boolean doTest(double d) throws X;

	default boolean nestingDoTest(double d) {
		try {
			return this.doTest(d);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(double d) {
		return ((LDoublePredicateX<RuntimeException>) this).doTest(d);
	}

	default <Y extends Throwable> boolean handlingDoTest(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(d);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(double d) throws X {
		return doTest(d);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double d) throws X {
		return doTest(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoublePredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> captureDPred(double d) {
		return () -> this.doTest(d);
	}

	static <X extends Throwable> LDoublePredicateX<X> constant(boolean r) {
		return d -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoublePredicateX<X> lX(final @Nonnull LDoublePredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoublePredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoublePredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LDoublePredicateX<X> wrap(final java.util.function.DoublePredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoublePredicateX<X> wrapX(final @Nonnull LDoublePredicate other) {
		return (LDoublePredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LDoublePredicateX<X> negate() {
		return d -> !doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LDoublePredicateX<X> and(@Nonnull LDoublePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return d -> doTest(d) && other.doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LDoublePredicateX<X> or(@Nonnull LDoublePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return d -> doTest(d) || other.doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LDoublePredicateX<X> xor(@Nonnull LDoublePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return d -> doTest(d) ^ other.doTest(d);
	}

	@Nonnull
	static <X extends Throwable> LDoublePredicateX<X> isEqual(double target) {
		return d -> d == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LDoublePredicateX<X> dPredFromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> dPredFrom(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> boolToDoubleFunction(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> boolToDoubleToByteFunction(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsByte(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> boolToDoubleToShortFunction(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsShort(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> boolToDoubleToIntFunction(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsInt(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> boolToDoubleToLongFunction(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsLong(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> boolToDoubleToFloatFunction(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsFloat(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> boolToDoubleUnaryOperator(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsDouble(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> boolToDoubleToCharFunction(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsChar(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoublePredicateX<X> boolToDoublePredicate(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doTest(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoublePredicate nestingDPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoublePredicateX<RuntimeException> nestingDPredX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicate shovingDPred() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicateX<RuntimeException> shovingDPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoublePredicate handleDPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return d -> this.handlingDoTest(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoublePredicateX<Y> handleDPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return d -> this.handlingDoTest(d, handling);
	}

	// </editor-fold>

}
