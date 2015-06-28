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
 * Non-throwing functional interface (lambda) LIntToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToDoubleFunction extends LIntToDoubleFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LIntToDoubleFunction: double doApplyAsDouble(int i)";

	@Override
	@Deprecated
	// calling this method via LIntToDoubleFunction interface should be discouraged.
	default double applyAsDouble(int i) {
		return this.nestingDoApplyAsDouble(i);
	}

	double doApplyAsDouble(int i);

	default double nestingDoApplyAsDouble(int i) {
		return this.doApplyAsDouble(i);
	}

	default double shovingDoApplyAsDouble(int i) {
		return this.doApplyAsDouble(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(int i) {
		return doApplyAsDouble(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier capture(int i) {
		return () -> this.doApplyAsDouble(i);
	}

	static LIntToDoubleFunction constant(double r) {
		return i -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToDoubleFunction l(final @Nonnull LIntToDoubleFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToDoubleFunction wrap(final java.util.function.IntToDoubleFunction other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntToDoubleFunction wrap(final @Nonnull LIntToDoubleFunctionX<X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntToDoubleFunction fromInt(@Nonnull final LIntUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunction<V1> from(@Nonnull final LToIntFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApply(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LDoubleToByteFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsByte(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunction thenToShort(@Nonnull LDoubleToShortFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsShort(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LDoubleToIntFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsInt(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LDoubleToLongFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsLong(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunction thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsFloat(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunction thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsDouble(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LDoubleToCharFunction after) {
		Null.nonNullArg(after, "after");
		return i -> after.doApplyAsChar(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBoolean(@Nonnull LDoublePredicate after) {
		Null.nonNullArg(after, "after");
		return i -> after.doTest(this.doApplyAsDouble(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToDoubleFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToDoubleFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToDoubleFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToDoubleFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
