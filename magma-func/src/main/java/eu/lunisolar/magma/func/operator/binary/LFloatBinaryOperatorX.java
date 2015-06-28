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
 * Throwing functional interface (lambda) LFloatBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): float f1,float f2
 *
 * Co-domain: none
 *
 * @see LFloatBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatBinaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LFloatBinaryOperatorX: float doApplyAsFloat(float f1,float f2) throws X";

	float doApplyAsFloat(float f1, float f2) throws X;

	default float nestingDoApplyAsFloat(float f1, float f2) {
		try {
			return this.doApplyAsFloat(f1, f2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default float shovingDoApplyAsFloat(float f1, float f2) {
		return ((LFloatBinaryOperatorX<RuntimeException>) this).doApplyAsFloat(f1, f2);
	}

	default <Y extends Throwable> float handlingDoApplyAsFloat(float f1, float f2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(f1, f2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(float f1, float f2) throws X {
		return doApplyAsFloat(f1, f2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> capture(float f1, float f2) {
		return () -> this.doApplyAsFloat(f1, f2);
	}

	static <X extends Throwable> LFloatBinaryOperatorX<X> constant(float r) {
		return (f1, f2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatBinaryOperatorX<X> lX(final @Nonnull LFloatBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatBinaryOperatorX<X> wrapX(final @Nonnull LFloatBinaryOperator other) {
		return (LFloatBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static <X extends Throwable> LFloatBinaryOperatorX<X> min() {
		return Float::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static <X extends Throwable> LFloatBinaryOperatorX<X> max() {
		return Float::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatBinaryOperatorX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1, @Nonnull final LFloatUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final float v1, final float v2) -> this.doApplyAsFloat(before1.doApplyAsFloat(v1), before2.doApplyAsFloat(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToFloatBiFunctionX<V1, V2, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1, @Nonnull final LToFloatFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsFloat(before1.doApplyAsFloat(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LFloatBiFunctionX<V, X> then(@Nonnull LFloatFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (float f1, float f2) -> after.doApply(this.doApplyAsFloat(f1, f2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatBinaryOperator nest() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatBinaryOperator shove() {
		return this::shovingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatBinaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (float f1, float f2) -> this.handlingDoApplyAsFloat(f1, f2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatBinaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (float f1, float f2) -> this.handlingDoApplyAsFloat(f1, f2, handling);
	}

	// </editor-fold>

}
