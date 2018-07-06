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
 * Non-throwing functional interface (lambda) LIntConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntConsumer extends IntConsumer, MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LIntConsumer: void doAccept(int a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntConsumer interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void accept(int a) {
		this.doAccept(a);
	}

	// void doAccept(int a) ;
	default void doAccept(int a) {
		// nestingDoAccept(a);
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(int a)
	 */
	void doAcceptX(int a) throws Throwable;

	default LTuple.Void tupleAccept(LIntSingle args) {
		doAccept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(int a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(int a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(int a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(int a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(int a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(int a, LIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a, handling);
	}

	static void tryDoAccept(int a, LIntConsumer func) {
		tryDoAccept(a, func, null);
	}

	static void tryDoAccept(int a, LIntConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(int a, LIntConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory);
	}

	static void tryDoAcceptThen(int a, LIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a, handler);
	}

	default void failSafeDoAccept(int a, @Nonnull LIntConsumer failSafe) {
		try {
			doAccept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a);
		}
	}

	static void failSafeDoAccept(int a, LIntConsumer func, @Nonnull LIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a);
		} else {
			func.failSafeDoAccept(a, failSafe);
		}
	}

	static LIntConsumer failSafeIntCons(LIntConsumer func, @Nonnull LIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, LIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.doAccept(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, LIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a < max_a; a++) {
				func.doAccept(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, LIntConsumer func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureIntCons(int a) {
		return () -> this.doAccept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntConsumer intCons(final @Nonnull LIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntConsumer recursive(final @Nonnull LFunction<LIntConsumer, LIntConsumer> selfLambda) {
		final LIntConsumerSingle single = new LIntConsumerSingle();
		LIntConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntConsumerSingle implements LSingle<LIntConsumer>, LIntConsumer {
		private LIntConsumer target = null;

		@Override
		public void doAcceptX(int a) throws Throwable {
			target.doAcceptX(a);
		}

		@Override
		public LIntConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LIntConsumer intConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntConsumer intConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static void call(int a, final @Nonnull LIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntConsumer wrap(final IntConsumer other) {
		return other::accept;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LIntConsumer safe() {
		return LIntConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntConsumer safe(final @Nullable LIntConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntConsumer> safeSupplier(final @Nullable LSupplier<LIntConsumer> supplier) {
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
	default LIntConsumer intConsComposeInt(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsInt(v));
	}

	public static LIntConsumer composedInt(@Nonnull final LIntUnaryOperator before, LIntConsumer after) {
		return after.intConsComposeInt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> intConsCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsInt(v));
	}

	public static <V> LConsumer<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntConsumer after) {
		return after.intConsCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LIntConsumer together in a order. */
	@Nonnull
	default LIntConsumer andThen(@Nonnull LIntConsumer after) {
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
	default LIntConsumer nestingIntCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntConsumer shovingIntCons() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntConsumer) */
	public static void doNothing(int a) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=int a, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	public static <C0> int forEach(IndexedRead<C0, aInt> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.doApplyAsInt(source, i);
			consumer.doAccept(a);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=int a, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.getter();
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			int a = nextFunc0.doApplyAsInt(iterator0);
			consumer.doAccept(a);
			i++;
		}
		return i;

	}

}
