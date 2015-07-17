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

package eu.lunisolar.magma.func.function.conversion;
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
 * Throwing functional interface (lambda) LDoubleToLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToLongFunctionX<X extends Throwable> extends java.util.function.DoubleToLongFunction, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LDoubleToLongFunctionX: long doApplyAsLong(double d) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDoubleToLongFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(double d) {
		return this.nestingDoApplyAsLong(d);
	}

	long doApplyAsLong(double d) throws X;

	default long nestingDoApplyAsLong(double d) {
		try {
			return this.doApplyAsLong(d);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(double d) {
		return ((LDoubleToLongFunctionX<RuntimeException>) this).doApplyAsLong(d);
	}

	default <Y extends Throwable> long handlingDoApplyAsLong(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(d);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(double d) throws X {
		return doApplyAsLong(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> captureDToLongFunc(double d) {
		return () -> this.doApplyAsLong(d);
	}

	static <X extends Throwable> LDoubleToLongFunctionX<X> constant(long r) {
		return d -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleToLongFunctionX<X> lX(final @Nonnull LDoubleToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleToLongFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LDoubleToLongFunctionX<X> wrap(final java.util.function.DoubleToLongFunction other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoubleToLongFunctionX<X> wrapX(final @Nonnull LDoubleToLongFunction other) {
		return (LDoubleToLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LDoubleToLongFunctionX<X> dToLongFuncFromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> dToLongFuncFrom(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsByte(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsShort(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsInt(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsLong(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsFloat(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsDouble(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsChar(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doTest(this.doApplyAsLong(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToLongFunction nestingDToLongFunc() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToLongFunctionX<RuntimeException> nestingDToLongFuncX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToLongFunction shovingDToLongFunc() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToLongFunctionX<RuntimeException> shovingDToLongFuncX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleToLongFunction handleDToLongFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return d -> this.handlingDoApplyAsLong(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleToLongFunctionX<Y> handleDToLongFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return d -> this.handlingDoApplyAsLong(d, handling);
	}

	// </editor-fold>

}
