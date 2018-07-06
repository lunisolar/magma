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
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*;

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
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSupplier<T> extends Supplier<T>, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSupplier: T doGet()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default T get() {
		return this.doGet();
	}

	@Nullable
	// T doGet() ;
	default T doGet() {
		// return nestingDoGet();
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGet()
	 */
	T doGetX() throws Throwable;

	default T tupleGet(LTuple.Void args) {
		return doGet();
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingDoGet(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default T tryDoGet(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default T tryDoGet(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default T tryDoGetThen(@Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoGet() {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingDoGet() {
		try {
			return this.doGetX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T handlingDoGet(LSupplier<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGet(handling);
	}

	static <T> T tryDoGet(LSupplier<T> func) {
		return tryDoGet(func, null);
	}

	static <T> T tryDoGet(LSupplier<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGet(exceptionFactory, newMessage, messageParams);
	}

	static <T> T tryDoGet(LSupplier<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGet(exceptionFactory);
	}

	static <T> T tryDoGetThen(LSupplier<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetThen(handler);
	}

	default T failSafeDoGet(@Nonnull LSupplier<T> failSafe) {
		try {
			return doGet();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGet();
		}
	}

	static <T> T failSafeDoGet(LSupplier<T> func, @Nonnull LSupplier<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGet();
		} else {
			return func.failSafeDoGet(failSafe);
		}
	}

	static <T> LSupplier<T> failSafeSup(LSupplier<T> func, @Nonnull LSupplier<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGet(func, failSafe);
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

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, LSupplier<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGet();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGet();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, LSupplier<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGet();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGet();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, LSupplier<T> func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static <T> LSupplier<T> of(T r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LSupplier<T> sup(final @Nonnull LSupplier<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LSupplier<T> recursive(final @Nonnull LFunction<LSupplier<T>, LSupplier<T>> selfLambda) {
		final LSupplierSingle<T> single = new LSupplierSingle();
		LSupplier<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LSupplierSingle<T> implements LSingle<LSupplier<T>>, LSupplier<T> {
		private LSupplier<T> target = null;

		@Override
		public T doGetX() throws Throwable {
			return target.doGetX();
		}

		@Override
		public LSupplier<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LSupplier<T> supThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LSupplier<T> supThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
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
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T> LSupplier<T> safe() {
		return LSupplier::produce;
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
	default <V> LSupplier<V> toSup(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LAction toAct(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doAccept(this.doGet());
	}

	@Nonnull
	default LSupplier<T> before(@Nonnull LAction before) {
		Null.nonNullArg(before, "before");
		return () -> {
			before.doExecute();
			return this.doGet();
		};
	}

	@Nonnull
	default LSupplier<T> after(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> {
			T result = this.doGet();
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGet());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LPredicate<? super T> after) {
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

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplier<T> shovingSup() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LSupplier<T> nonNullSup() {
		return this::nonNullDoGet;
	}

	/** Does nothing (LSupplier) Supplier */
	public static <T> T produce() {
		return (T) Function4U.defaultObject;
	}

}
