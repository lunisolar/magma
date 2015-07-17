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
 * Non-throwing functional interface (lambda) LIntToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToLongFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToLongFunction extends LIntToLongFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LIntToLongFunction: long doApplyAsLong(int i)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntToLongFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(int i) {
		return this.nestingDoApplyAsLong(i);
	}

	long doApplyAsLong(int i);

	default long nestingDoApplyAsLong(int i) {
		return this.doApplyAsLong(i);
	}

	default long shovingDoApplyAsLong(int i) {
		return this.doApplyAsLong(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(int i) {
		return doApplyAsLong(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToLongFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureIToLongFunc(int i) {
		return () -> this.doApplyAsLong(i);
	}

	static LIntToLongFunction constant(long r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToLongFunction l(final @Nonnull LIntToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToLongFunction wrap(final java.util.function.IntToLongFunction other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntToLongFunction wrap(final @Nonnull LIntToLongFunctionX<X> other) {
		return other::nestingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntToLongFunction iToLongFuncFromInt(@Nonnull final LIntUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToLongFunction<V1> iToLongFuncFrom(@Nonnull final LToIntFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunction thenToShort(@Nonnull LLongToShortFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunction thenToFloat(@Nonnull LLongToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunction thenToDouble(@Nonnull LLongToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBoolean(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return i -> after.doTest(this.doApplyAsLong(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToLongFunction nestingIToLongFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToLongFunctionX<RuntimeException> nestingIToLongFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToLongFunction shovingIToLongFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToLongFunctionX<RuntimeException> shovingIToLongFuncX() {
		return this;
	}

	// </editor-fold>

}
