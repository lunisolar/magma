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

package eu.lunisolar.magma.func.consumer.primitives;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*;
import java.lang.reflect.*;

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
 * Non-throwing functional interface (lambda) LLongConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): long a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongConsumer extends LongConsumer, MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LLongConsumer: void doAccept(long a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongConsumer interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void accept(long a) {
		this.doAccept(a);
	}

	// void doAccept(long a) ;
	default void doAccept(long a) {
		// nestingDoAccept(a);
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(long a)
	 */
	void doAcceptX(long a) throws Throwable;

	default LTuple.Void tupleAccept(LLongSingle args) {
		doAccept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(long a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(long a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(long a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(long a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(long a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(long a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(long a, LLongConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a, handling);
	}

	static void tryDoAccept(long a, LLongConsumer func) {
		tryDoAccept(a, func, null);
	}

	static void tryDoAccept(long a, LLongConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(long a, LLongConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory);
	}

	static void tryDoAcceptThen(long a, LLongConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a, handler);
	}

	default void failSafeDoAccept(long a, @Nonnull LLongConsumer failSafe) {
		try {
			doAccept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a);
		}
	}

	static void failSafeDoAccept(long a, LLongConsumer func, @Nonnull LLongConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a);
		} else {
			func.failSafeDoAccept(a, failSafe);
		}
	}

	static LLongConsumer failSafeLongCons(LLongConsumer func, @Nonnull LLongConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(long min_a, long max_a, LLongConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a <= max_a; a++) {
				func.doAccept(a);
			}
		} else {
			for (long a = min_a; a >= max_a; a--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(long min_a, long max_a, LLongConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a < max_a; a++) {
				func.doAccept(a);
			}
		} else {
			for (long a = min_a; a > max_a; a--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(long max_a, LLongConsumer func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureLongCons(long a) {
		return () -> this.doAccept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongConsumer longCons(final @Nonnull LLongConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLongConsumer recursive(final @Nonnull LFunction<LLongConsumer, LLongConsumer> selfLambda) {
		final LLongConsumerSingle single = new LLongConsumerSingle();
		LLongConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLongConsumerSingle implements LSingle<LLongConsumer>, LLongConsumer {
		private LLongConsumer target = null;

		@Override
		public void doAcceptX(long a) throws Throwable {
			target.doAcceptX(a);
		}

		@Override
		public LLongConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LLongConsumer longConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLongConsumer longConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static void call(long a, final @Nonnull LLongConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongConsumer wrap(final LongConsumer other) {
		return other::accept;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LLongConsumer safe() {
		return LLongConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongConsumer safe(final @Nullable LLongConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongConsumer> safeSupplier(final @Nullable LSupplier<LLongConsumer> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongConsumer longConsComposeLong(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsLong(v));
	}

	public static LLongConsumer composedLong(@Nonnull final LLongUnaryOperator before, LLongConsumer after) {
		return after.longConsComposeLong(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> longConsCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsLong(v));
	}

	public static <V> LConsumer<V> composed(@Nonnull final LToLongFunction<? super V> before, LLongConsumer after) {
		return after.longConsCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LLongConsumer together in a order. */
	@Nonnull
	default LLongConsumer andThen(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return a -> {
			this.doAccept(a);
			after.doAccept(a);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongConsumer nestingLongCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongConsumer shovingLongCons() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLongConsumer) */
	public static void doNothing(long a) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=long a, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	public static <C0> int forEach(IndexedRead<C0, aLong> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToLongFunction<Object> oiFunc0 = (LOiToLongFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			long a = oiFunc0.doApplyAsLong(source, i);
			consumer.doAccept(a);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=long a, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aLong> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToLongFunction<Object> nextFunc0 = (LToLongFunction) sa.getter();
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			long a = nextFunc0.doApplyAsLong(iterator0);
			consumer.doAccept(a);
			i++;
		}
		return i;

	}

}
