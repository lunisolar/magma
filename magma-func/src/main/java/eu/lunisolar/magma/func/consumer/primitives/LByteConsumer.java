/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LByteConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): byte a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain1<aByte> {

	String DESCRIPTION = "LByteConsumer: void accept(byte a)";

	// void accept(byte a) ;
	default void accept(byte a) {
		// nestingAccept(a);
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(byte a)
	 */
	void acceptX(byte a) throws Throwable;

	default LTuple.Void tupleAccept(LByteSingle args) {
		accept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(byte a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingAccept(a, handling);
	}

	default void accept(byte a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LByteConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> accept(a, exF, newMessage, messageParams);
	}

	default void accept(byte a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LByteConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> accept(a, exF);
	}

	default void acceptThen(byte a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LByteConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return a -> acceptThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(byte a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(byte a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(byte a, LByteConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a, handling);
	}

	static void tryAccept(byte a, LByteConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a);
	}

	static void tryAccept(byte a, LByteConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage, messageParams);
	}

	static void tryAccept(byte a, LByteConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF);
	}

	static void tryAcceptThen(byte a, LByteConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a, handler);
	}

	default void failSafeAccept(byte a, @Nonnull LByteConsumer failSafe) {
		try {
			accept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a);
		}
	}

	static void failSafeAccept(byte a, LByteConsumer func, @Nonnull LByteConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a);
		} else {
			func.failSafeAccept(a, failSafe);
		}
	}

	static LByteConsumer failSafe(LByteConsumer func, @Nonnull LByteConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a, LByteConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a, LByteConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a, LByteConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(byte a) {
		return () -> this.accept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteConsumer byteCons(final @Nonnull LByteConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LByteConsumer recursive(final @Nonnull LFunction<LByteConsumer, LByteConsumer> selfLambda) {
		final LByteConsumerSingle single = new LByteConsumerSingle();
		LByteConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LByteConsumerSingle implements LSingle<LByteConsumer>, LByteConsumer {
		private LByteConsumer target = null;

		@Override
		public void acceptX(byte a) throws Throwable {
			target.acceptX(a);
		}

		@Override
		public LByteConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LByteConsumer byteConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteConsumer byteConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static void call(byte a, final @Nonnull LByteConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LByteConsumer safe() {
		return LByteConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteConsumer safe(final @Nullable LByteConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteConsumer> safeSupplier(final @Nullable LSupplier<LByteConsumer> supplier) {
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
	default LByteConsumer compose(@Nonnull final LByteUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsByte(v));
	}

	public static LByteConsumer composed(@Nonnull final LByteUnaryOperator before, LByteConsumer after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> byteConsCompose(@Nonnull final LToByteFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsByte(v));
	}

	public static <V> LConsumer<V> composed(@Nonnull final LToByteFunction<? super V> before, LByteConsumer after) {
		return after.byteConsCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LByteConsumer together in a order. */
	@Nonnull
	default LByteConsumer andThen(@Nonnull LByteConsumer after) {
		Null.nonNullArg(after, "after");
		return a -> {
			this.accept(a);
			after.accept(a);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LByteConsumer) */
	public static void doNothing(byte a) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C0> int forEach(IndexedRead<C0, aByte> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.applyAsByte(source, i);
			consumer.accept(a);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aByte> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.supplier();
		int i = 0;
		while (testFunc0.test(iterator0)) {
			byte a = nextFunc0.applyAsByte(iterator0);
			consumer.accept(a);
			i++;
		}
		return i;

	}

}
