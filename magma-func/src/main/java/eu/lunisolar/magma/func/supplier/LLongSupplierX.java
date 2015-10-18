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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LLongSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: long
 *
 * @see LLongSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongSupplierX<X extends Throwable> extends LongSupplier, MetaSupplier, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LLongSupplierX: long doGetAsLong() throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongSupplierX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long getAsLong() {
		return this.nestingDoGetAsLong();
	}

	long doGetAsLong() throws X;

	default Long tupleGetAsLong(LTuple.Void args) throws X {
		return doGetAsLong();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default long nestingDoGetAsLong() {
		try {
			return this.doGetAsLong();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default long shovingDoGetAsLong() {
		return ((LLongSupplierX<RuntimeException>) this).doGetAsLong();
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> long handlingDoGetAsLong(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGetAsLong();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoGetAsLong() throws X {
		return doGetAsLong();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongSupplierX.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LLongSupplierX<X> of(long r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongSupplierX<X> lX(final @Nonnull LLongSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongSupplierX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> long call(final @Nonnull LLongSupplierX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsLong();
	}

	static <X extends Throwable> long shoving(final @Nonnull LLongSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoGetAsLong();
	}

	static <X extends Throwable> long nesting(final @Nonnull LLongSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoGetAsLong();
	}

	static <X extends Throwable, Y extends Throwable> long handling(final HandlingInstructions<Throwable, Y> handling, final @Nonnull LLongSupplierX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoGetAsLong(handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongSupplierX<X> wrap(final LongSupplier other) {
		return other::getAsLong;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongSupplierX<X> wrapX(final @Nonnull LLongSupplier other) {
		return (LLongSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> toSupplier(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplierX<X> toByteSupplier(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplierX<X> toShortSupplier(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplierX<X> toIntSupplier(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplierX<X> toLongSupplier(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplierX<X> toFloatSupplier(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> toDoubleSupplier(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplierX<X> toCharSupplier(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBoolSupplierX<X> toBoolSupplier(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsLong());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongSupplier nestingLongSup() {
		return this::nestingDoGetAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongSupplierX<RuntimeException> nestingLongSupX() {
		return this::nestingDoGetAsLong;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongSupplier shovingLongSup() {
		return this::shovingDoGetAsLong;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongSupplierX<RuntimeException> shovingLongSupX() {
		return this::shovingDoGetAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LLongSupplier handleLongSup(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGetAsLong(handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LLongSupplierX<Y> handleLongSupX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGetAsLong(handling);
	}

	// </editor-fold>

}
