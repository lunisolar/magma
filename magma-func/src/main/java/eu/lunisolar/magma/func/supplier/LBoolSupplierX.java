/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import java.util.function.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LBoolSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: boolean
 *
 * @see LBoolSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSupplierX<X extends Throwable> extends BooleanSupplier, MetaSupplier, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LBoolSupplierX: boolean doGetAsBool() throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBoolSupplierX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean getAsBoolean() {
		return this.nestingDoGetAsBool();
	}

	boolean doGetAsBool() throws X;

	default boolean tupleGetAsBool(LTuple.Void args) throws X {
		return doGetAsBool();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoGetAsBool() {
		try {
			return this.doGetAsBool();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoGetAsBool() {
		return ((LBoolSupplierX<RuntimeException>) this).doGetAsBool();
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoGetAsBool(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGetAsBool();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoGetAsBool() throws X {
		return doGetAsBool();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolSupplierX.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LBoolSupplierX<X> of(boolean r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> lX(final @Nonnull LBoolSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LBoolSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> boolean call(final @Nonnull LBoolSupplierX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsBool();
	}

	static <X extends Throwable> boolean shoving(final @Nonnull LBoolSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoGetAsBool();
	}

	static <X extends Throwable> boolean nesting(final @Nonnull LBoolSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoGetAsBool();
	}

	static <X extends Throwable, Y extends Throwable> boolean handling(final HandlingInstructions<Throwable, Y> handling, final @Nonnull LBoolSupplierX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoGetAsBool(handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> wrap(final BooleanSupplier other) {
		return other::getAsBoolean;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> wrapX(final @Nonnull LBoolSupplier other) {
		return (LBoolSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceBoolean). */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> safe() {
		return Function4U::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LBoolSupplierX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LBoolSupplierX<X> safe(final @Nullable LBoolSupplierX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LBoolSupplierX<X>, Y> safeSupplier(final @Nullable LSupplierX<LBoolSupplierX<X>, Y> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> toSup(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplierX<X> toByteSup(@Nonnull LBoolToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortSupplierX<X> toShortSup(@Nonnull LBoolToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplierX<X> toIntSup(@Nonnull LBoolToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplierX<X> toLongSup(@Nonnull LBoolToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatSupplierX<X> toFloatSup(@Nonnull LBoolToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> toDoubleSup(@Nonnull LBoolToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplierX<X> toCharSup(@Nonnull LBoolToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplierX<X> toBoolSup(@Nonnull LLogicalOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsBool());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolSupplier nestingBoolSup() {
		return this::nestingDoGetAsBool;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBoolSupplierX<RuntimeException> nestingBoolSupX() {
		return this::nestingDoGetAsBool;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolSupplier shovingBoolSup() {
		return this::shovingDoGetAsBool;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolSupplierX<RuntimeException> shovingBoolSupX() {
		return this::shovingDoGetAsBool;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LBoolSupplier handleBoolSup(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGetAsBool(handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LBoolSupplierX<Y> handleBoolSupX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGetAsBool(handling);
	}

	// </editor-fold>

}
