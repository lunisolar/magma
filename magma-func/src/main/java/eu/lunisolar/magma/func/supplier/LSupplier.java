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
 * Non-throwing functional interface (lambda) LSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: T
 *
 * @see LSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSupplier<T> extends LSupplierX<T, RuntimeException>, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSupplier: T doGet()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default T get() {
		return this.nestingDoGet();
	}

	@Nullable
	T doGet();

	default T tupleGet(LTuple.Void args) {
		return doGet();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoGet() {
		return this.doGet();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default T shovingDoGet() {
		return this.doGet();
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoGet() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoGet() {
		return Null.requireNonNull(doGet(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static <T> LSupplier<T> of(T r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LSupplier<T> l(final @Nonnull LSupplier<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <T> T call(final @Nonnull LSupplier<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGet();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LSupplier<T> wrap(final Supplier<T> other) {
		return other::get;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LSupplier<T> wrap(final @Nonnull LSupplierX<T, X> other) {
		return other::nestingDoGet;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produce). */
	@Nonnull
	static <T> LSupplier<T> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LSupplier<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LSupplier<T> safe(final @Nullable LSupplier<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LSupplier<T>> safeSupplier(final @Nullable LSupplier<LSupplier<T>> supplier) {
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
	default <V> LSupplier<V> toSupplier(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LAction toAction(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doAccept(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LToShortFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LToFloatFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LToDoubleFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSupplier(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGet());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSupplier<T> nestingSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LSupplierX<T, RuntimeException> nestingSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplier<T> shovingSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplierX<T, RuntimeException> shovingSupX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LSupplier<T> nonNullSup() {
		return this::nonNullDoGet;
	}

}
