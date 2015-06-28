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
 * Throwing functional interface (lambda) LFloatPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatPredicateX<X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LFloatPredicateX: boolean doTest(float f) throws X";

	boolean doTest(float f) throws X;

	default boolean nestingDoTest(float f) {
		try {
			return this.doTest(f);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(float f) {
		return ((LFloatPredicateX<RuntimeException>) this).doTest(f);
	}

	default <Y extends Throwable> boolean handlingDoTest(float f, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(f);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(float f) throws X {
		return doTest(f);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(float f) throws X {
		return doTest(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(float f) {
		return () -> this.doTest(f);
	}

	static <X extends Throwable> LFloatPredicateX<X> constant(boolean r) {
		return f -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatPredicateX<X> lX(final @Nonnull LFloatPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatPredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatPredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatPredicateX<X> wrapX(final @Nonnull LFloatPredicate other) {
		return (LFloatPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LFloatPredicateX<X> negate() {
		return f -> !doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LFloatPredicateX<X> and(@Nonnull LFloatPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) && other.doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LFloatPredicateX<X> or(@Nonnull LFloatPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) || other.doTest(f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LFloatPredicateX<X> xor(@Nonnull LFloatPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return f -> doTest(f) ^ other.doTest(f);
	}

	@Nonnull
	static <X extends Throwable> LFloatPredicateX<X> isEqual(float target) {
		return f -> f == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatPredicateX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsByte(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsShort(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsInt(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsLong(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsFloat(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsDouble(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsChar(this.doTest(f));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsBoolean(this.doTest(f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatPredicate nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatPredicateX<RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatPredicate shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatPredicateX<RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatPredicate handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return f -> this.handlingDoTest(f, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatPredicateX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return f -> this.handlingDoTest(f, handling);
	}

	// </editor-fold>

}
