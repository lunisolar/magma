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
 * Throwing functional interface (lambda) LShortSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: short
 *
 * @see LShortSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortSupplierX<X extends Throwable> extends MetaSupplier, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LShortSupplierX: short doGetAsShort() throws X";

	short doGetAsShort() throws X;

	default short tupleGetAsShort(LTuple.Void args) throws X {
		return doGetAsShort();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingDoGetAsShort() {
		try {
			return this.doGetAsShort();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default short shovingDoGetAsShort() {
		return ((LShortSupplierX<RuntimeException>) this).doGetAsShort();
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> short handlingDoGetAsShort(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGetAsShort();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoGetAsShort() throws X {
		return doGetAsShort();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortSupplierX.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LShortSupplierX<X> of(short r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortSupplierX<X> lX(final @Nonnull LShortSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortSupplierX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LShortSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> short call(final @Nonnull LShortSupplierX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsShort();
	}

	static <X extends Throwable> short shoving(final @Nonnull LShortSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoGetAsShort();
	}

	static <X extends Throwable> short nesting(final @Nonnull LShortSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoGetAsShort();
	}

	static <X extends Throwable, Y extends Throwable> short handling(final HandlingInstructions<Throwable, Y> handling, final @Nonnull LShortSupplierX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoGetAsShort(handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortSupplierX<X> wrapX(final @Nonnull LShortSupplier other) {
		return (LShortSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceShort). */
	@Nonnull
	static <X extends Throwable> LShortSupplierX<X> safe() {
		return Function4U::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortSupplierX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LShortSupplierX<X> safe(final @Nullable LShortSupplierX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortSupplierX<X>, Y> safeSupplier(final @Nullable LSupplierX<LShortSupplierX<X>, Y> supplier) {
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
	default <V> LSupplierX<V, X> toSup(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplierX<X> toByteSup(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortSupplierX<X> toShortSup(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplierX<X> toIntSup(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplierX<X> toLongSup(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatSupplierX<X> toFloatSup(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> toDoubleSup(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplierX<X> toCharSup(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsShort());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplierX<X> toBoolSup(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsShort());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortSupplier nestingShortSup() {
		return this::nestingDoGetAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortSupplierX<RuntimeException> nestingShortSupX() {
		return this::nestingDoGetAsShort;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortSupplier shovingShortSup() {
		return this::shovingDoGetAsShort;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortSupplierX<RuntimeException> shovingShortSupX() {
		return this::shovingDoGetAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LShortSupplier handleShortSup(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGetAsShort(handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LShortSupplierX<Y> handleShortSupX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGetAsShort(handling);
	}

	// </editor-fold>

}
