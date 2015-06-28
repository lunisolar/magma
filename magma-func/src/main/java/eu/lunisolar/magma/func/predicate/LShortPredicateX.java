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
 * Throwing functional interface (lambda) LShortPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortPredicateX<X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LShortPredicateX: boolean doTest(short s) throws X";

	boolean doTest(short s) throws X;

	default boolean nestingDoTest(short s) {
		try {
			return this.doTest(s);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(short s) {
		return ((LShortPredicateX<RuntimeException>) this).doTest(s);
	}

	default <Y extends Throwable> boolean handlingDoTest(short s, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(s);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(short s) throws X {
		return doTest(s);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(short s) throws X {
		return doTest(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(short s) {
		return () -> this.doTest(s);
	}

	static <X extends Throwable> LShortPredicateX<X> constant(boolean r) {
		return s -> r;
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

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> wrapX(final @Nonnull LShortPredicate other) {
		return (LShortPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LShortPredicateX<X> negate() {
		return s -> !doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LShortPredicateX<X> and(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) && other.doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LShortPredicateX<X> or(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) || other.doTest(s);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LShortPredicateX<X> xor(@Nonnull LShortPredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return s -> doTest(s) ^ other.doTest(s);
	}

	@Nonnull
	static <X extends Throwable> LShortPredicateX<X> isEqual(short target) {
		return s -> s == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortPredicateX<X> fromShort(@Nonnull final LShortUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LToShortFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LShortFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApply(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToByteFunctionX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsByte(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortUnaryOperatorX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsShort(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsInt(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToLongFunctionX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsLong(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToFloatFunctionX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsFloat(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToDoubleFunctionX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsDouble(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsChar(this.doTest(s));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LShortPredicateX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsBoolean(this.doTest(s));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortPredicate nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortPredicateX<RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicate shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortPredicateX<RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LShortPredicate handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return s -> this.handlingDoTest(s, handling);
	}

	@Nonnull
	default <Y extends Throwable> LShortPredicateX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return s -> this.handlingDoTest(s, handling);
	}

	// </editor-fold>

}
