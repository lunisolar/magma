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
 * Throwing functional interface (lambda) LDoubleToShortFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToShortFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToShortFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LDoubleToShortFunctionX: short doApplyAsShort(double d) throws X";

	short doApplyAsShort(double d) throws X;

	default short nestingDoApplyAsShort(double d) {
		try {
			return this.doApplyAsShort(d);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default short shovingDoApplyAsShort(double d) {
		return ((LDoubleToShortFunctionX<RuntimeException>) this).doApplyAsShort(d);
	}

	default <Y extends Throwable> short handlingDoApplyAsShort(double d, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(d);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(double d) throws X {
		return doApplyAsShort(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToShortFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> capture(double d) {
		return () -> this.doApplyAsShort(d);
	}

	static <X extends Throwable> LDoubleToShortFunctionX<X> constant(short r) {
		return d -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleToShortFunctionX<X> lX(final @Nonnull LDoubleToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LDoubleToShortFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LDoubleToShortFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoubleToShortFunctionX<X> wrapX(final @Nonnull LDoubleToShortFunction other) {
		return (LDoubleToShortFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleToShortFunctionX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunctionX<V1, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsByte(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsShort(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsInt(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsLong(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsFloat(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsDouble(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsChar(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doTest(this.doApplyAsShort(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToShortFunction nest() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToShortFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToShortFunction shove() {
		return this::shovingDoApplyAsShort;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToShortFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LDoubleToShortFunction handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return d -> this.handlingDoApplyAsShort(d, handling);
	}

	@Nonnull
	default <Y extends Throwable> LDoubleToShortFunctionX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return d -> this.handlingDoApplyAsShort(d, handling);
	}

	// </editor-fold>

}
