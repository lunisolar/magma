/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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
import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR

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
 * Non-throwing functional interface (lambda) LFltConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain1<aFloat> {

	String DESCRIPTION = "LFltConsumer: void accept(float a)";

	// void accept(float a) ;
	default void accept(float a) {
		// nestingAccept(a);
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(float a)
	 */
	void acceptX(float a) throws Throwable;

	default LTuple.Void tupleAccept(LFltSingle args) {
		accept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingAccept(a, handling);
	}

	default void accept(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> accept(a, factory, newMessage);
	}

	default LFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> accept(a, factory, newMessage, param1);
	}

	default LFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> accept(a, factory, newMessage, param1, param1);
	}

	default LFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> accept(a, factory, newMessage, param1, param2, param3);
	}

	default void accept(float a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFltConsumer trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> accept(a, factory);
	}

	default void acceptThen(float a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LFltConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return a -> acceptThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(float a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(float a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void shovingAccept(float a, LFltConsumer func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a);
	}

	static void handlingAccept(float a, LFltConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a, handling);
	}

	static void tryAccept(float a, LFltConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a);
	}

	static void tryAccept(float a, LFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a, factory, newMessage);
	}

	static void tryAccept(float a, LFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a, factory, newMessage, param1);
	}

	static void tryAccept(float a, LFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a, factory, newMessage, param1, param2);
	}

	static void tryAccept(float a, LFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a, factory, newMessage, param1, param2, param3);
	}

	static void tryAccept(float a, LFltConsumer func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a, factory);
	}

	static void tryAcceptThen(float a, LFltConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a, handler);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, @Nonnull LFltConsumer func) {
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
	public static void fromTill(int min_i, int max_i, float a, @Nonnull LFltConsumer func) {
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
	public static void times(int max_i, float a, @Nonnull LFltConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LFltFunction<T> returning(T value) {
		return a -> {
			LFltConsumer.this.accept(a);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LFltConsumer beforeDo(@Nonnull LFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a) -> {
			before.accept(a);
			accept(a);
		};
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltConsumer fltCons(final @Nonnull LFltConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LFltConsumer {
		private LFltConsumer target = null;
		@Override
		public void acceptX(float a) throws Throwable {
			target.acceptX(a);
		}
	}

	@Nonnull
	static LFltConsumer recursive(final @Nonnull LFunction<LFltConsumer, LFltConsumer> selfLambda) {
		final S single = new S();
		LFltConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	@Nonnull
	static LFltConsumer fltConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltConsumer fltConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static void call(float a, final @Nonnull LFltConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFltConsumer compose(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsFlt(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> unboxingCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsFlt(v));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LFltConsumer together in a order. */
	@Nonnull
	default LFltConsumer andThen(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return a -> {
			this.accept(a);
			after.accept(a);
		};
	}

	// </editor-fold>

	default LFltConsumer shoving() {

		return new LFltConsumer() {

			public void accept(float a) {
				try {
					this.acceptX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public void acceptX(float a) throws Throwable {
				LFltConsumer.this.acceptX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltConsumer) */
	public static void doNothing(float a) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C0> int forEach(IndexedRead<C0, aFloat> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.applyAsFlt(source, i);
			consumer.accept(a);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.supplier();
		int i = 0;
		while (testFunc0.test(iterator0)) {
			float a = nextFunc0.applyAsFlt(iterator0);
			consumer.accept(a);
			i++;
		}
		return i;

	}

	// <editor-fold desc="fluentUse">

	public static <R> R inlineAcceptR(R retval, float a, LFltConsumer consumer) {
		consumer.accept(a);
		return retval;
	}

	public static float inlineAccept(float a, LFltConsumer consumer) {
		consumer.accept(a);
		return a;
	}

	// </editor-fold>

}
