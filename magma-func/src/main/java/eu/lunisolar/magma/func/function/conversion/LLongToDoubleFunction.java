/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
 * Non-throwing functional interface (lambda) LLongToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToDoubleFunction extends LLongToDoubleFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LLongToDoubleFunction: double doApplyAsDouble(long l)";

	@Override
	@Deprecated
	// calling this method via LLongToDoubleFunction interface should be discouraged.
	default double applyAsDouble(long l) {
		return this.nestingDoApplyAsDouble(l);
	}

	public double doApplyAsDouble(long l);

	default double nestingDoApplyAsDouble(long l) {
		return this.doApplyAsDouble(l);
	}

	default double shovingDoApplyAsDouble(long l) {
		return this.doApplyAsDouble(l);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(long l) {
		return doApplyAsDouble(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier capture(long l) {
		return () -> this.doApplyAsDouble(l);
	}

	public static LLongToDoubleFunction constant(double r) {
		return (l) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LLongToDoubleFunction l(final @Nonnull LLongToDoubleFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LLongToDoubleFunction wrap(final java.util.function.LongToDoubleFunction other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LLongToDoubleFunction wrap(final @Nonnull LLongToDoubleFunctionX<X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LLongToDoubleFunction fromLong(@Nonnull final LLongUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final long v1) -> this.doApplyAsDouble(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunction<V1> from(@Nonnull final LToLongFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApply(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LDoubleToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsByte(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunction thenToShort(@Nonnull LDoubleToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsShort(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LDoubleToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsInt(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LDoubleToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsLong(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunction thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsFloat(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunction thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsDouble(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LDoubleToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doApplyAsChar(this.doApplyAsDouble(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBoolean(@Nonnull LDoublePredicate after) {
		Null.nonNullArg(after, "after");
		return (long l) -> after.doTest(this.doApplyAsDouble(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToDoubleFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongToDoubleFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToDoubleFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToDoubleFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
