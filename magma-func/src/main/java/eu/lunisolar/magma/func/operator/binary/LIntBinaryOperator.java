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

package eu.lunisolar.magma.func.operator.binary;
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
 * Non-throwing functional interface (lambda) LIntBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): int i1,int i2
 *
 * Co-domain: none
 *
 * @see LIntBinaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBinaryOperator extends LIntBinaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LIntBinaryOperator: int doApplyAsInt(int i1,int i2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntBinaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(int i1, int i2) {
		return this.nestingDoApplyAsInt(i1, i2);
	}

	int doApplyAsInt(int i1, int i2);

	default int nestingDoApplyAsInt(int i1, int i2) {
		return this.doApplyAsInt(i1, i2);
	}

	default int shovingDoApplyAsInt(int i1, int i2) {
		return this.doApplyAsInt(i1, i2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int i1, int i2) {
		return doApplyAsInt(i1, i2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBinaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureIBinaryOp(int i1, int i2) {
		return () -> this.doApplyAsInt(i1, i2);
	}

	static LIntBinaryOperator constant(int r) {
		return (i1, i2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntBinaryOperator l(final @Nonnull LIntBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntBinaryOperator wrap(final java.util.function.IntBinaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperator wrap(final @Nonnull LIntBinaryOperatorX<X> other) {
		return other::nestingDoApplyAsInt;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static LIntBinaryOperator min() {
		return Integer::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static LIntBinaryOperator max() {
		return Integer::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntBinaryOperator iBinaryOpFromInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final int v1, final int v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2> LToIntBiFunction<V1, V2> iBinaryOpFrom(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LIntBiFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (int i1, int i2) -> after.doApply(this.doApplyAsInt(i1, i2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBinaryOperator nestingIBinaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntBinaryOperatorX<RuntimeException> nestingIBinaryOpX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperator shovingIBinaryOp() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperatorX<RuntimeException> shovingIBinaryOpX() {
		return this;
	}

	// </editor-fold>

}
