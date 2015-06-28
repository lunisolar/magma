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
 * Throwing functional interface (lambda) LDoubleBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): double d1,double d2
 *
 * Co-domain: none
 *
 * @see LDoubleBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleBinaryOperatorX<X extends Throwable> extends java.util.function.DoubleBinaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LDoubleBinaryOperatorX: double doApplyAsDouble(double d1,double d2) throws X";

	@Override
	@Deprecated
	// calling this method via LDoubleBinaryOperatorX interface should be discouraged.
	default double applyAsDouble(double d1, double d2) {
		return this.nestingDoApplyAsDouble(d1, d2);
	}

	double doApplyAsDouble(double d1, double d2) throws X;

	default double nestingDoApplyAsDouble(double d1, double d2) {
		try {
			return this.doApplyAsDouble(d1, d2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(double d1, double d2) {
		return ((LDoubleBinaryOperatorX<RuntimeException>) this).doApplyAsDouble(d1, d2);
	}

	default <Y extends Throwable> double handlingDoApplyAsDouble(double d1, double d2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsDouble(d1, d2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(double d1, double d2) throws X {
		return doApplyAsDouble(d1, d2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> capture(double d1, double d2) {
		return () -> this.doApplyAsDouble(d1, d2);
	}

	static <X extends Throwable> LDoubleBinaryOperatorX<X> constant(double r) {
		return (d1, d2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> lX(final @Nonnull LDoubleBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> wrap(final java.util.function.DoubleBinaryOperator other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> wrapX(final @Nonnull LDoubleBinaryOperator other) {
		return (LDoubleBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> min() {
		return Double::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static <X extends Throwable> LDoubleBinaryOperatorX<X> max() {
		return Double::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleBinaryOperatorX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1, @Nonnull final LDoubleUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final double v1, final double v2) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToDoubleBiFunctionX<V1, V2, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1, @Nonnull final LToDoubleFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LDoubleBiFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (double d1, double d2) -> after.doApply(this.doApplyAsDouble(d1, d2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleBinaryOperator nest() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleBinaryOperator shove() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleBinaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (double d1, double d2) -> this.handlingDoApplyAsDouble(d1, d2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleBinaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (double d1, double d2) -> this.handlingDoApplyAsDouble(d1, d2, handling);
	}

	// </editor-fold>

}
