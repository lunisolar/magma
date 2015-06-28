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
 * Throwing functional interface (lambda) LCharBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): char c1,char c2
 *
 * Co-domain: none
 *
 * @see LCharBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharBinaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LCharBinaryOperatorX: char doApplyAsChar(char c1,char c2) throws X";

	char doApplyAsChar(char c1, char c2) throws X;

	default char nestingDoApplyAsChar(char c1, char c2) {
		try {
			return this.doApplyAsChar(c1, c2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default char shovingDoApplyAsChar(char c1, char c2) {
		return ((LCharBinaryOperatorX<RuntimeException>) this).doApplyAsChar(c1, c2);
	}

	default <Y extends Throwable> char handlingDoApplyAsChar(char c1, char c2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsChar(c1, c2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(char c1, char c2) throws X {
		return doApplyAsChar(c1, c2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> capture(char c1, char c2) {
		return () -> this.doApplyAsChar(c1, c2);
	}

	static <X extends Throwable> LCharBinaryOperatorX<X> constant(char r) {
		return (c1, c2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharBinaryOperatorX<X> lX(final @Nonnull LCharBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharBinaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharBinaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharBinaryOperatorX<X> wrapX(final @Nonnull LCharBinaryOperator other) {
		return (LCharBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	static <X extends Throwable> LCharBinaryOperatorX<X> min() {
		return (a, b) -> (a <= b) ? a : b;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	static <X extends Throwable> LCharBinaryOperatorX<X> max() {
		return (a, b) -> (a >= b) ? a : b;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharBinaryOperatorX<X> fromChar(@Nonnull final LCharUnaryOperatorX<X> before1, @Nonnull final LCharUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final char v1, final char v2) -> this.doApplyAsChar(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToCharBiFunctionX<V1, V2, X> from(@Nonnull final LToCharFunctionX<? super V1, X> before1, @Nonnull final LToCharFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApplyAsChar(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LCharBiFunctionX<V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (char c1, char c2) -> after.doApply(this.doApplyAsChar(c1, c2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharBinaryOperator nest() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharBinaryOperator shove() {
		return this::shovingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LCharBinaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (char c1, char c2) -> this.handlingDoApplyAsChar(c1, c2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LCharBinaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (char c1, char c2) -> this.handlingDoApplyAsChar(c1, c2, handling);
	}

	// </editor-fold>

}
