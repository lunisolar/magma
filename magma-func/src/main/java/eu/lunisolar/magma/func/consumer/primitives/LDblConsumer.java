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
 * Non-throwing functional interface (lambda) LDblConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblConsumer extends DoubleConsumer, MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LDblConsumer: void doAccept(double a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblConsumer interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void accept(double a) {
		this.doAccept(a);
	}

	// void doAccept(double a) ;
	default void doAccept(double a) {
		// nestingDoAccept(a);
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(double a)
	 */
	void doAcceptX(double a) throws Throwable;

	default LTuple.Void tupleAccept(LDblSingle args) {
		doAccept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(double a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(double a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(double a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(double a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(double a) {
		try {
			this.doAcceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(double a, LDblConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a, handling);
	}

	static void tryDoAccept(double a, LDblConsumer func) {
		tryDoAccept(a, func, null);
	}

	static void tryDoAccept(double a, LDblConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(double a, LDblConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a, exceptionFactory);
	}

	static void tryDoAcceptThen(double a, LDblConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a, handler);
	}

	default void failSafeDoAccept(double a, @Nonnull LDblConsumer failSafe) {
		try {
			doAccept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a);
		}
	}

	static void failSafeDoAccept(double a, LDblConsumer func, @Nonnull LDblConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a);
		} else {
			func.failSafeDoAccept(a, failSafe);
		}
	}

	static LDblConsumer failSafeDblCons(LDblConsumer func, @Nonnull LDblConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, LDblConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doAccept(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, LDblConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doAccept(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doAccept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, LDblConsumer func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureDblCons(double a) {
		return () -> this.doAccept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblConsumer dblCons(final @Nonnull LDblConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblConsumer recursive(final @Nonnull LFunction<LDblConsumer, LDblConsumer> selfLambda) {
		final LDblConsumerSingle single = new LDblConsumerSingle();
		LDblConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblConsumerSingle implements LSingle<LDblConsumer>, LDblConsumer {
		private LDblConsumer target = null;

		@Override
		public void doAcceptX(double a) throws Throwable {
			target.doAcceptX(a);
		}

		@Override
		public LDblConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LDblConsumer dblConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblConsumer dblConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static void call(double a, final @Nonnull LDblConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblConsumer wrap(final DoubleConsumer other) {
		return other::accept;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LDblConsumer safe() {
		return LDblConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblConsumer safe(final @Nullable LDblConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblConsumer> safeSupplier(final @Nullable LSupplier<LDblConsumer> supplier) {
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
	default LDblConsumer dblConsComposeDbl(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsDbl(v));
	}

	public static LDblConsumer composedDbl(@Nonnull final LDblUnaryOperator before, LDblConsumer after) {
		return after.dblConsComposeDbl(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> dblConsCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsDbl(v));
	}

	public static <V> LConsumer<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblConsumer after) {
		return after.dblConsCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LDblConsumer together in a order. */
	@Nonnull
	default LDblConsumer andThen(@Nonnull LDblConsumer after) {
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
	default LDblConsumer nestingDblCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblConsumer shovingDblCons() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LDblConsumer) */
	public static void doNothing(double a) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	public static <C0> int forEach(IndexedRead<C0, aDouble> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			consumer.doAccept(a);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			consumer.doAccept(a);
			i++;
		}
		return i;

	}

}
