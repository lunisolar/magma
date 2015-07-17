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

package eu.lunisolar.magma.func.function.to;
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
 * Non-throwing functional interface (lambda) LToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToDoubleFunction<T> extends LToDoubleFunctionX<T, RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LToDoubleFunction: double doApplyAsDouble(T t)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToDoubleFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(T t) {
		return this.nestingDoApplyAsDouble(t);
	}

	double doApplyAsDouble(T t);

	default double nestingDoApplyAsDouble(T t) {
		return this.doApplyAsDouble(t);
	}

	default double shovingDoApplyAsDouble(T t) {
		return this.doApplyAsDouble(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(T t) {
		return doApplyAsDouble(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier captureToDFunc(T t) {
		return () -> this.doApplyAsDouble(t);
	}

	static <T> LToDoubleFunction<T> constant(double r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToDoubleFunction<T> l(final @Nonnull LToDoubleFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LToDoubleFunction<T> wrap(final java.util.function.ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LToDoubleFunction<T> wrap(final @Nonnull LToDoubleFunctionX<T, X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToDoubleFunction<V1> toDFuncFrom(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LDoubleToByteFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LDoubleToShortFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LDoubleToIntFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LDoubleToLongFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LDoubleToCharFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doApplyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LDoublePredicate after) {
		Null.nonNullArg(after, "after");
		return t -> after.doTest(this.doApplyAsDouble(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToDoubleFunction<T> nestingToDFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToDoubleFunctionX<T, RuntimeException> nestingToDFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToDoubleFunction<T> shovingToDFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToDoubleFunctionX<T, RuntimeException> shovingToDFuncX() {
		return this;
	}

	// </editor-fold>

}
