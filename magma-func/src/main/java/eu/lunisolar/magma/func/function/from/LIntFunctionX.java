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
 * Throwing functional interface (lambda) LIntFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: R
 *
 * @see LIntFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntFunctionX<R, X extends Throwable> extends java.util.function.IntFunction<R>, MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LIntFunctionX: R doApply(int i) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(int i) {
		return this.nestingDoApply(i);
	}

	@Nullable
	R doApply(int i) throws X;

	default R nestingDoApply(int i) {
		try {
			return this.doApply(i);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(int i) {
		return ((LIntFunctionX<R, RuntimeException>) this).doApply(i);
	}

	default <Y extends Throwable> R handlingDoApply(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(i);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(int i) throws X {
		return Null.requireNonNull(doApply(i), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> captureIFunc(int i) {
		return () -> this.doApply(i);
	}

	static <R, X extends Throwable> LIntFunctionX<R, X> constant(R r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LIntFunctionX<R, X> lX(final @Nonnull LIntFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LIntFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R, X extends Throwable> LIntFunctionX<R, X> wrap(final java.util.function.IntFunction<R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LIntFunctionX<R, X> wrapX(final @Nonnull LIntFunction<R> other) {
		return (LIntFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntFunctionX<R, X> iFuncFromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LFunctionX<V1, R, X> iFuncFrom(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doAccept(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LToByteFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LToShortFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LToIntFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LToLongFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LToCharFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doApply(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LPredicateX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doTest(this.doApply(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntFunction<R> nestingIFunc() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntFunctionX<R, RuntimeException> nestingIFuncX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntFunction<R> shovingIFunc() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntFunctionX<R, RuntimeException> shovingIFuncX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LIntFunctionX<R, X> nonNullIFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntFunction<R> handleIFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return i -> this.handlingDoApply(i, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntFunctionX<R, Y> handleIFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return i -> this.handlingDoApply(i, handling);
	}

	// </editor-fold>

}
