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
 * Throwing functional interface (lambda) LIntBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): int i1,int i2
 *
 * Co-domain: none
 *
 * @see LIntBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBinaryOperatorX<X extends Throwable> extends java.util.function.IntBinaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LIntBinaryOperatorX: int doApplyAsInt(int i1,int i2) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntBinaryOperatorX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(int i1, int i2) {
		return this.nestingDoApplyAsInt(i1, i2);
	}

	int doApplyAsInt(int i1, int i2) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default int nestingDoApplyAsInt(int i1, int i2) {
		try {
			return this.doApplyAsInt(i1, i2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(int i1, int i2) {
		return ((LIntBinaryOperatorX<RuntimeException>) this).doApplyAsInt(i1, i2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> int handlingDoApplyAsInt(int i1, int i2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(i1, i2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int i1, int i2) throws X {
		return doApplyAsInt(i1, i2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> captureIntBinaryOp(int i1, int i2) {
		return () -> this.doApplyAsInt(i1, i2);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LIntBinaryOperatorX<X> constant(int r) {
		return (i1, i2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> apply1stAsInt(@Nonnull LIntUnaryOperatorX<X> func) {
		return (i1, i2) -> func.doApplyAsInt(i1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> apply2ndAsInt(@Nonnull LIntUnaryOperatorX<X> func) {
		return (i1, i2) -> func.doApplyAsInt(i2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> lX(final @Nonnull LIntBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> wrap(final java.util.function.IntBinaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> wrapX(final @Nonnull LIntBinaryOperator other) {
		return (LIntBinaryOperatorX) other;
	}

	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> minBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> maxBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> min() {
		return Integer::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static <X extends Throwable> LIntBinaryOperatorX<X> max() {
		return Integer::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntBinaryOperatorX<X> intBinaryOpComposeInt(@Nonnull final LIntUnaryOperatorX<X> before1, @Nonnull final LIntUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final int v1, final int v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToIntBiFunctionX<V1, V2, X> intBinaryOpCompose(@Nonnull final LToIntFunctionX<? super V1, X> before1, @Nonnull final LToIntFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBiIntFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i1, int i2) -> after.doApply(this.doApplyAsInt(i1, i2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBinaryOperator nestingIntBinaryOp() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntBinaryOperatorX<RuntimeException> nestingIntBinaryOpX() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperator shovingIntBinaryOp() {
		return this::shovingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperatorX<RuntimeException> shovingIntBinaryOpX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LIntBinaryOperator handleIntBinaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i1, int i2) -> this.handlingDoApplyAsInt(i1, i2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LIntBinaryOperatorX<Y> handleIntBinaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i1, int i2) -> this.handlingDoApplyAsInt(i1, i2, handling);
	}

	// </editor-fold>

}
