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
 * Throwing functional interface (lambda) LBytePredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LBytePredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBytePredicateX<X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LBytePredicateX: boolean doTest(byte b) throws X";

	boolean doTest(byte b) throws X;

	default boolean nestingDoTest(byte b) {
		try {
			return this.doTest(b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(byte b) {
		return ((LBytePredicateX<RuntimeException>) this).doTest(b);
	}

	default <Y extends Throwable> boolean handlingDoTest(byte b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(byte b) throws X {
		return doTest(b);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(byte b) throws X {
		return doTest(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBytePredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(byte b) {
		return () -> this.doTest(b);
	}

	static <X extends Throwable> LBytePredicateX<X> constant(boolean r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBytePredicateX<X> lX(final @Nonnull LBytePredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBytePredicateX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LBytePredicateX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBytePredicateX<X> wrapX(final @Nonnull LBytePredicate other) {
		return (LBytePredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBytePredicateX<X> negate() {
		return b -> !doTest(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBytePredicateX<X> and(@Nonnull LBytePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return b -> doTest(b) && other.doTest(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBytePredicateX<X> or(@Nonnull LBytePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return b -> doTest(b) || other.doTest(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBytePredicateX<X> xor(@Nonnull LBytePredicateX<X> other) {
		Null.nonNullArg(other, "other");
		return b -> doTest(b) ^ other.doTest(b);
	}

	@Nonnull
	static <X extends Throwable> LBytePredicateX<X> isEqual(byte target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBytePredicateX<X> fromByte(@Nonnull final LByteUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LToByteFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doTest(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LByteFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteUnaryOperatorX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToShortFunctionX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToLongFunctionX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToFloatFunctionX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToDoubleFunctionX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doTest(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LBytePredicateX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsBoolean(this.doTest(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBytePredicate nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBytePredicateX<RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBytePredicate shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBytePredicateX<RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBytePredicate handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return b -> this.handlingDoTest(b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBytePredicateX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return b -> this.handlingDoTest(b, handling);
	}

	// </editor-fold>

}
