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
 * Throwing functional interface (lambda) LIntToDoubleFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToDoubleFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToDoubleFunctionX<X extends Throwable> extends java.util.function.IntToDoubleFunction, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntToDoubleFunctionX: double doApplyAsDouble(int i) throws X";

	@Override
	@Deprecated
	// calling this method via LIntToDoubleFunctionX interface should be discouraged.
	default double applyAsDouble(int i) {
		return this.nestingDoApplyAsDouble(i);
	}

	public double doApplyAsDouble(int i) throws X;

	default double nestingDoApplyAsDouble(int i) {
		try {
			return this.doApplyAsDouble(i);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(int i) {
		return ((LIntToDoubleFunctionX<RuntimeException>) this).doApplyAsDouble(i);
	}

	default <Y extends Throwable> double handlingDoApplyAsDouble(int i, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsDouble(i);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(int i) throws X {
		return doApplyAsDouble(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToDoubleFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> capture(int i) {
		return () -> this.doApplyAsDouble(i);
	}

	public static <X extends Throwable> LIntToDoubleFunctionX<X> constant(double r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToDoubleFunctionX<X> lX(final @Nonnull LIntToDoubleFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LIntToDoubleFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntToDoubleFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Throwable> LIntToDoubleFunctionX<X> wrap(final java.util.function.IntToDoubleFunction other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LIntToDoubleFunctionX<X> wrapX(final @Nonnull LIntToDoubleFunction other) {
		return (LIntToDoubleFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntToDoubleFunctionX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return (final int v1) -> this.doApplyAsDouble(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunctionX<V1, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApply(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LDoubleToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsByte(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LDoubleToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsShort(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LDoubleToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsInt(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LDoubleToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsLong(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LDoubleToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsFloat(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LDoubleUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsDouble(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LDoubleToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doApplyAsChar(this.doApplyAsDouble(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LDoublePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return (int i) -> after.doTest(this.doApplyAsDouble(i));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToDoubleFunction nest() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToDoubleFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToDoubleFunction shove() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToDoubleFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntToDoubleFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i) -> this.handlingDoApplyAsDouble(i, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntToDoubleFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i) -> this.handlingDoApplyAsDouble(i, handling);
	}

	// </editor-fold>

}
