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
 * Throwing functional interface (lambda) LBoolToLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBoolToLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolToLongFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LBoolToLongFunctionX: long doApplyAsLong(boolean b) throws X";

	long doApplyAsLong(boolean b) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default long nestingDoApplyAsLong(boolean b) {
		try {
			return this.doApplyAsLong(b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(boolean b) {
		return ((LBoolToLongFunctionX<RuntimeException>) this).doApplyAsLong(b);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> long handlingDoApplyAsLong(boolean b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(boolean b) throws X {
		return doApplyAsLong(b);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolToLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> captureBoolToLongFunc(boolean b) {
		return () -> this.doApplyAsLong(b);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LBoolToLongFunctionX<X> constant(long r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBoolToLongFunctionX<X> lX(final @Nonnull LBoolToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBoolToLongFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LBoolToLongFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBoolToLongFunctionX<X> wrapX(final @Nonnull LBoolToLongFunction other) {
		return (LBoolToLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBoolToLongFunctionX<X> boolToLongFuncComposeBoolean(@Nonnull final LLogicalOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApply(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> boolToLongFuncCompose(@Nonnull final LPredicateX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doTest(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunctionX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToShortFunctionX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunctionX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunctionX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDoubleFunctionX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApplyAsLong(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperatorX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApplyAsLong(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolToLongFunction nestingBoolToLongFunc() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBoolToLongFunctionX<RuntimeException> nestingBoolToLongFuncX() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolToLongFunction shovingBoolToLongFunc() {
		return this::shovingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolToLongFunctionX<RuntimeException> shovingBoolToLongFuncX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LBoolToLongFunction handleBoolToLongFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return b -> this.handlingDoApplyAsLong(b, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LBoolToLongFunctionX<Y> handleBoolToLongFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return b -> this.handlingDoApplyAsLong(b, handling);
	}

	// </editor-fold>

}