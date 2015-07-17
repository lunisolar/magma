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
 * Non-throwing functional interface (lambda) LLongToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongToIntFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToIntFunction extends LLongToIntFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LLongToIntFunction: int doApplyAsInt(long l)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongToIntFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(long l) {
		return this.nestingDoApplyAsInt(l);
	}

	int doApplyAsInt(long l);

	default int nestingDoApplyAsInt(long l) {
		return this.doApplyAsInt(l);
	}

	default int shovingDoApplyAsInt(long l) {
		return this.doApplyAsInt(l);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(long l) {
		return doApplyAsInt(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToIntFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureLongToIFunc(long l) {
		return () -> this.doApplyAsInt(l);
	}

	static LLongToIntFunction constant(int r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongToIntFunction l(final @Nonnull LLongToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongToIntFunction wrap(final java.util.function.LongToIntFunction other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongToIntFunction wrap(final @Nonnull LLongToIntFunctionX<X> other) {
		return other::nestingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongToIntFunction longToIFuncFromLong(@Nonnull final LLongUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToIntFunction<V1> longToIFuncFrom(@Nonnull final LToLongFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunction thenToShort(@Nonnull LIntToShortFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunction thenToFloat(@Nonnull LIntToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunction thenToDouble(@Nonnull LIntToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBoolean(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApplyAsInt(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToIntFunction nestingLongToIFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongToIntFunctionX<RuntimeException> nestingLongToIFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToIntFunction shovingLongToIFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToIntFunctionX<RuntimeException> shovingLongToIFuncX() {
		return this;
	}

	// </editor-fold>

}
