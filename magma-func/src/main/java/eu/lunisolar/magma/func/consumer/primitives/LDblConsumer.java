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
public interface LDblConsumer extends DoubleConsumer, MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain1<aDouble> {

	String DESCRIPTION = "LDblConsumer: void accept(double a)";

	// void accept(double a) ;
	default void accept(double a) {
		// nestingAccept(a);
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(double a)
	 */
	void acceptX(double a) throws Throwable;

	default LTuple.Void tupleAccept(LDblSingle args) {
		accept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingAccept(a, handling);
	}

	default void accept(double a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LDblConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> accept(a, exF, newMessage, messageParams);
	}

	default void accept(double a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LDblConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> accept(a, exF);
	}

	default void acceptThen(double a, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LDblConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return a -> acceptThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(double a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(double a) {
		try {
			this.acceptX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(double a, LDblConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a, handling);
	}

	static void tryAccept(double a, LDblConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a);
	}

	static void tryAccept(double a, LDblConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF, newMessage, messageParams);
	}

	static void tryAccept(double a, LDblConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a, exF);
	}

	static void tryAcceptThen(double a, LDblConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a, handler);
	}

	default void failSafeAccept(double a, @Nonnull LDblConsumer failSafe) {
		try {
			accept(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a);
		}
	}

	static void failSafeAccept(double a, LDblConsumer func, @Nonnull LDblConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a);
		} else {
			func.failSafeAccept(a, failSafe);
		}
	}

	static LDblConsumer failSafe(LDblConsumer func, @Nonnull LDblConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeAccept(a, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblConsumer func) {
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
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblConsumer func) {
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
	public static void times(int max_i, double a, @Nonnull LDblConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Calls domain consumer before main function. */
	public default LDblConsumer beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			accept(a);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(double a) {
		return () -> this.accept(a);
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
		LDblConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LDblConsumerSingle implements LSingle<LDblConsumer>, LDblConsumer {
		private LDblConsumer target = null;

		@Override
		public void acceptX(double a) throws Throwable {
			target.acceptX(a);
		}

		@Override
		public LDblConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LDblConsumer dblConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblConsumer dblConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static void call(double a, final @Nonnull LDblConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a);
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
	default LDblConsumer compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsDbl(v));
	}

	public static LDblConsumer composed(@Nonnull final LDblUnaryOperator before, LDblConsumer after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumer<V> dblConsCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.accept(before.applyAsDbl(v));
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
			this.accept(a);
			after.accept(a);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblConsumer) */
	public static void doNothing(double a) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C0> int forEach(IndexedRead<C0, aDouble> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(a);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C0, I0> int iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		int i = 0;
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(a);
			i++;
		}
		return i;

	}

}
