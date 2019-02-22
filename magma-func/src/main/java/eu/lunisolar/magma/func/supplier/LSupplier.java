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
public interface LSupplier<T> extends Supplier<T>, MetaSupplier, MetaInterface.NonThrowing, Codomain<a<T>>, Domain0 { // NOSONAR

	String DESCRIPTION = "LSupplier: T get()";

	@Nullable
	// T get() ;
	default T get() {
		// return nestingGet();
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call get()
	 */
	T getX() throws Throwable;

	default T tupleGet(LTuple.Void args) {
		return get();
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingGet(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSupplier<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGet(handling);
	}

	default T get(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSupplier<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return () -> get(exF, newMessage, messageParams);
	}

	default T get(@Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSupplier<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return () -> get(exF);
	}

	default T getThen(@Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LSupplier<T> tryingThen(@Nonnull LFunction<Throwable, T> handler) {
		return () -> getThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingGet() {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingGet() {
		try {
			return this.getX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T handlingGet(LSupplier<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGet(handling);
	}

	static <T> T tryGet(LSupplier<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingGet();
	}

	static <T> T tryGet(LSupplier<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.get(exF, newMessage, messageParams);
	}

	static <T> T tryGet(LSupplier<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.get(exF);
	}

	static <T> T tryGetThen(LSupplier<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.getThen(handler);
	}

	default T failSafeGet(@Nonnull LSupplier<T> failSafe) {
		try {
			return get();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.get();
		}
	}

	static <T> T failSafeGet(LSupplier<T> func, @Nonnull LSupplier<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.get();
		} else {
			return func.failSafeGet(failSafe);
		}
	}

	static <T> LSupplier<T> failSafe(LSupplier<T> func, @Nonnull LSupplier<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGet(func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullGet() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullGet() {
		return Null.requireNonNull(get(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, LSupplier<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.get();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.get();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, LSupplier<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.get();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.get();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, LSupplier<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Cast that removes generics. */
	public default LSupplier untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LSupplier<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LSupplier<V2> cast(LSupplier<T> function) {
		return (LSupplier) function;
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
		LSupplier<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSupplierSingle<T> implements LSingle<LSupplier<T>>, LSupplier<T> {
		private LSupplier<T> target = null;

		@Override
		public T getX() throws Throwable {
			return target.getX();
		}

		@Override
		public LSupplier<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LSupplier<T> supThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LSupplier<T> supThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static <T> T call(final @Nonnull LSupplier<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.get();
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
		return () -> after.apply(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LAction toAct(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.accept(this.get());
	}

	@Nonnull
	default LSupplier<T> before(@Nonnull LAction before) {
		Null.nonNullArg(before, "before");
		return () -> {
			before.execute();
			return this.get();
		};
	}

	@Nonnull
	default LSupplier<T> after(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> {
			T result = this.get();
			after.accept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.get());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.get());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LSupplier<T> nonNullable() {
		return this::nonNullGet;
	}

	/** Does nothing (LSupplier) Supplier */
	public static <T> T produce() {
		return (T) Function4U.defaultObject;
	}

}
