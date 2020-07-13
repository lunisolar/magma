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
 * Non-throwing functional interface (lambda) LCharConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain1<aChar> {

	String DESCRIPTION = "LCharConsumer: void accept(char a)";

	// void accept(char a) ;
	default void accept(char a) {
		// nestingAccept(a);
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(char a)
	 */
	void acceptX(char a) throws Throwable;

	default LTuple.Void tupleAccept(LCharSingle args) {
		accept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(char a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingAccept(a, handling);
	}

	default void accept(char a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default void accept(char a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default void accept(char a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default void accept(char a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return a -> accept(a, exF, newMessage);
	}

	default LCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> accept(a, exF, newMessage, param1);
	}

	default LCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> accept(a, exF, newMessage, param1, param1);
	}

	default LCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> accept(a, exF, newMessage, param1, param2, param3);
	}

	default void accept(char a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LCharConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> accept(a, exF);
	}

	default void acceptThen(char a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LCharConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return a -> acceptThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(char a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(char a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(char a, LCharConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a, handling);
	}

	static void tryAccept(char a, LCharConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a);
	}

	static void tryAccept(char a, LCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage);
	}

	static void tryAccept(char a, LCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage, param1);
	}

	static void tryAccept(char a, LCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage, param1, param2);
	}

	static void tryAccept(char a, LCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage, param1, param2, param3);
	}

	static void tryAccept(char a, LCharConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF);
	}

	static void tryAcceptThen(char a, LCharConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a, handler);
	}

	default void failSafeAccept(char a, @Nonnull LCharConsumer failSafe) {
		try {
			accept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a);
		}
	}

	static void failSafeAccept(char a, LCharConsumer func, @Nonnull LCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a);
		} else {
			func.failSafeAccept(a, failSafe);
		}
	}

	static LCharConsumer failSafe(LCharConsumer func, @Nonnull LCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a, @Nonnull LCharConsumer func) {
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
	public static void fromTill(int min_i, int max_i, char a, @Nonnull LCharConsumer func) {
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
	public static void times(int max_i, char a, @Nonnull LCharConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Calls domain consumer before main function. */
	default LCharConsumer beforeDo(@Nonnull LCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a) -> {
			before.accept(a);
			accept(a);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(char a) {
		return () -> this.accept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharConsumer charCons(final @Nonnull LCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LCharConsumer {
		private LCharConsumer target = null;
		@Override
		public void acceptX(char a) throws Throwable {
			target.acceptX(a);
		}
	}

	@Nonnull
	static LCharConsumer recursive(final @Nonnull LFunction<LCharConsumer, LCharConsumer> selfLambda) {
		final S single = new S();
		LCharConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static LCharConsumer charConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharConsumer charConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static void call(char a, final @Nonnull LCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LCharConsumer safe() {
		return LCharConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharConsumer safe(final @Nullable LCharConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharConsumer> safeSupplier(final @Nullable LSupplier<LCharConsumer> supplier) {
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
	default LCharConsumer compose(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsChar(v));
	}

	public static LCharConsumer composed(@Nonnull final LCharUnaryOperator before, LCharConsumer after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> charConsCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsChar(v));
	}

	public static <V> LConsumer<V> composed(@Nonnull final LToCharFunction<? super V> before, LCharConsumer after) {
		return after.charConsCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LCharConsumer together in a order. */
	@Nonnull
	default LCharConsumer andThen(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return a -> {
			this.accept(a);
			after.accept(a);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharConsumer) */
	public static void doNothing(char a) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C0> int forEach(IndexedRead<C0, aChar> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiToCharFunction<Object> oiFunc0 = (LOiToCharFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.applyAsChar(source, i);
			consumer.accept(a);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aChar> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToCharFunction<Object> nextFunc0 = (LToCharFunction) sa.supplier();
		int i = 0;
		while (testFunc0.test(iterator0)) {
			char a = nextFunc0.applyAsChar(iterator0);
			consumer.accept(a);
			i++;
		}
		return i;

	}

}
