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
 * Throwing functional interface (lambda) LLongToIntFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongToIntFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToIntFunctionX<X extends Throwable> extends java.util.function.LongToIntFunction, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongToIntFunctionX: int doApplyAsInt(long l) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongToIntFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(long l) {
		return this.nestingDoApplyAsInt(l);
	}

	int doApplyAsInt(long l) throws X;

	default int nestingDoApplyAsInt(long l) {
		try {
			return this.doApplyAsInt(l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default int shovingDoApplyAsInt(long l) {
		return ((LLongToIntFunctionX<RuntimeException>) this).doApplyAsInt(l);
	}

	default <Y extends Throwable> int handlingDoApplyAsInt(long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(long l) throws X {
		return doApplyAsInt(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToIntFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> captureLongToIFunc(long l) {
		return () -> this.doApplyAsInt(l);
	}

	static <X extends Throwable> LLongToIntFunctionX<X> constant(int r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongToIntFunctionX<X> lX(final @Nonnull LLongToIntFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongToIntFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongToIntFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongToIntFunctionX<X> wrap(final java.util.function.LongToIntFunction other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongToIntFunctionX<X> wrapX(final @Nonnull LLongToIntFunction other) {
		return (LLongToIntFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLongToIntFunctionX<X> longToIFuncFromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToIntFunctionX<V1, X> longToIFuncFrom(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LIntToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LIntToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LIntUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LIntToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LIntToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LIntToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LIntToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApplyAsInt(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LIntPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApplyAsInt(l));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToIntFunction nestingLongToIFunc() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongToIntFunctionX<RuntimeException> nestingLongToIFuncX() {
		return this::nestingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToIntFunction shovingLongToIFunc() {
		return this::shovingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToIntFunctionX<RuntimeException> shovingLongToIFuncX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLongToIntFunction handleLongToIFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return l -> this.handlingDoApplyAsInt(l, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLongToIntFunctionX<Y> handleLongToIFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return l -> this.handlingDoApplyAsInt(l, handling);
	}

	// </editor-fold>

}
