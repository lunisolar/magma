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
 * Non-throwing functional interface (lambda) LToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LToIntFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToIntFunction<T> extends LToIntFunctionX<T, RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LToIntFunction: int doApplyAsInt(T t)";

	@Override
	@Deprecated
	// calling this method via LToIntFunction interface should be discouraged.
	default int applyAsInt(T t) {
		return this.nestingDoApplyAsInt(t);
	}

	public int doApplyAsInt(T t);

	default int nestingDoApplyAsInt(T t) {
		return this.doApplyAsInt(t);
	}

	default int shovingDoApplyAsInt(T t) {
		return this.doApplyAsInt(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(T t) {
		return doApplyAsInt(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToIntFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T t) {
		return () -> this.doApplyAsInt(t);
	}

	public static <T> LToIntFunction<T> constant(int r) {
		return t -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> LToIntFunction<T> l(final @Nonnull LToIntFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T> LToIntFunction<T> wrap(final java.util.function.ToIntFunction<T> other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Throwable> LToIntFunction<T> wrap(final @Nonnull LToIntFunctionX<T, X> other) {
		return other::nestingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToIntFunction<V1> from(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApply(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsByte(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToShortFunction<T> thenToShort(@Nonnull LIntToShortFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsShort(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsInt(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsLong(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFloatFunction<T> thenToFloat(@Nonnull LIntToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsFloat(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDoubleFunction<T> thenToDouble(@Nonnull LIntToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsDouble(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return t -> after.doApplyAsChar(this.doApplyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBoolean(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return t -> after.doTest(this.doApplyAsInt(t));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToIntFunction<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToIntFunctionX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToIntFunction<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToIntFunctionX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
