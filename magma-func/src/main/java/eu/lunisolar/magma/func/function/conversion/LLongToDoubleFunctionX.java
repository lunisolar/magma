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
 * Throwing functional interface (lambda) LLongToDoubleFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongToDoubleFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToDoubleFunctionX<X extends Throwable> extends java.util.function.LongToDoubleFunction, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongToDoubleFunctionX: double doApplyAsDouble(long l) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongToDoubleFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(long l) {
		return this.nestingDoApplyAsDouble(l);
	}

	double doApplyAsDouble(long l) throws X;

	default double nestingDoApplyAsDouble(long l) {
		try {
			return this.doApplyAsDouble(l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(long l) {
		return ((LLongToDoubleFunctionX<RuntimeException>) this).doApplyAsDouble(l);
	}

	default <Y extends Throwable> double handlingDoApplyAsDouble(long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsDouble(l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(long l) throws X {
		return doApplyAsDouble(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToDoubleFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> captureLongToDFunc(long l) {
		return () -> this.doApplyAsDouble(l);
	}

	static <X extends Throwable> LLongToDoubleFunctionX<X> constant(double r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongToDoubleFunctionX<X> lX(final @Nonnull LLongToDoubleFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongToDoubleFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongToDoubleFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongToDoubleFunctionX<X> wrap(final java.util.function.LongToDoubleFunction other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongToDoubleFunctionX<X> wrapX(final @Nonnull LLongToDoubleFunction other) {
		return (LLongToDoubleFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongToDoubleFunctionX<X> longToDFuncFromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToDoubleFunctionX<V1, X> longToDFuncFrom(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LDoubleToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LDoubleToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LDoubleToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LDoubleToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LDoubleToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LDoubleUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LDoubleToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LDoublePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApplyAsDouble(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToDoubleFunction nestingLongToDFunc() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongToDoubleFunctionX<RuntimeException> nestingLongToDFuncX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToDoubleFunction shovingLongToDFunc() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToDoubleFunctionX<RuntimeException> shovingLongToDFuncX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLongToDoubleFunction handleLongToDFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return l -> this.handlingDoApplyAsDouble(l, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLongToDoubleFunctionX<Y> handleLongToDFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return l -> this.handlingDoApplyAsDouble(l, handling);
	}

	// </editor-fold>

}
