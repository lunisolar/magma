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

package eu.lunisolar.magma.func.function.from;
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
 * Throwing functional interface (lambda) LLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: R
 *
 * @see LLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongFunctionX<R, X extends Throwable> extends java.util.function.LongFunction<R>, MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongFunctionX: R doApply(long l) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(long l) {
		return this.nestingDoApply(l);
	}

	@Nullable
	R doApply(long l) throws X;

	default R nestingDoApply(long l) {
		try {
			return this.doApply(l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(long l) {
		return ((LLongFunctionX<R, RuntimeException>) this).doApply(l);
	}

	default <Y extends Throwable> R handlingDoApply(long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(long l) throws X {
		return Null.requireNonNull(doApply(l), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> captureLongFunc(long l) {
		return () -> this.doApply(l);
	}

	static <R, X extends Throwable> LLongFunctionX<R, X> constant(R r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LLongFunctionX<R, X> lX(final @Nonnull LLongFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LLongFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R, X extends Throwable> LLongFunctionX<R, X> wrap(final java.util.function.LongFunction<R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LLongFunctionX<R, X> wrapX(final @Nonnull LLongFunction<R> other) {
		return (LLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongFunctionX<R, X> longFuncFromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LFunctionX<V1, R, X> longFuncFrom(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doAccept(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LToByteFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LToShortFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LToIntFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LToLongFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LToCharFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApply(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LPredicateX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApply(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongFunction<R> nestingLongFunc() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongFunctionX<R, RuntimeException> nestingLongFuncX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongFunction<R> shovingLongFunc() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongFunctionX<R, RuntimeException> shovingLongFuncX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LLongFunctionX<R, X> nonNullLongFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLongFunction<R> handleLongFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return l -> this.handlingDoApply(l, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLongFunctionX<R, Y> handleLongFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return l -> this.handlingDoApply(l, handling);
	}

	// </editor-fold>

}
