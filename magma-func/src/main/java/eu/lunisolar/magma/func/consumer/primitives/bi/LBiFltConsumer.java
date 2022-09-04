/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
 * Non-throwing functional interface (lambda) LBiFltConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): float a1,float a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiFltConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain2<aFloat, aFloat> {

	String DESCRIPTION = "LBiFltConsumer: void accept(float a1,float a2)";

	// void accept(float a1,float a2) ;
	default void accept(float a1, float a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(float a1,float a2)
	 */
	void acceptX(float a1, float a2) throws Throwable;

	default LTuple.Void tupleAccept(LFltPair args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(float a1, float a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiFltConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage);
	}

	default LBiFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1);
	}

	default LBiFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param1);
	}

	default LBiFltConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default void accept(float a1, float a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiFltConsumer trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> accept(a1, a2, factory);
	}

	default void acceptThen(float a1, float a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LBiFltConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(float a1, float a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(float a1, float a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void shovingAccept(float a1, float a2, LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2);
	}

	static void handlingAccept(float a1, float a2, LBiFltConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static void tryAccept(float a1, float a2, LBiFltConsumer func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory);
	}

	static void tryAcceptThen(float a1, float a2, LBiFltConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(float a1, float a2, @Nonnull LBiFltConsumer failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static void failSafeAccept(float a1, float a2, LBiFltConsumer func, @Nonnull LBiFltConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static LBiFltConsumer failSafe(LBiFltConsumer func, @Nonnull LBiFltConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiFltConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a1, float a2, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a1, float a2, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a1, float a2, @Nonnull LBiFltConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LFltConsumer lShrink(@Nonnull LFltUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.applyAsFlt(a2), a2);
	}

	default LFltConsumer lShrink_(float a1) {
		return a2 -> accept(a1, a2);
	}

	public static LFltConsumer lShrunken(@Nonnull LFltUnaryOperator left, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LFltConsumer lShrunken_(float a1, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LFltConsumer rShrink(@Nonnull LFltUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.applyAsFlt(a1));
	}

	default LFltConsumer rShrink_(float a2) {
		return a1 -> accept(a1, a2);
	}

	public static LFltConsumer rShrunken(@Nonnull LFltUnaryOperator right, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LFltConsumer rShrunken_(float a2, @Nonnull LBiFltConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LBiFltConsumer uncurry(@Nonnull LFltFunction<LFltConsumer> func) {
		Null.nonNullArg(func, "func");
		return (float a1, float a2) -> func.apply(a1).accept(a2);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LBiFltFunction<T> returning(T value) {
		return (a1, a2) -> {
			LBiFltConsumer.this.accept(a1, a2);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LBiFltConsumer beforeDo(@Nonnull LBiFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a1, float a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(float a1, float a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiFltConsumer accept1st(@Nonnull LFltConsumer func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiFltConsumer accept2nd(@Nonnull LFltConsumer func) {
		return (a1, a2) -> func.accept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiFltConsumer biFltCons(final @Nonnull LBiFltConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LBiFltConsumer {
		private LBiFltConsumer target = null;
		@Override
		public void acceptX(float a1, float a2) throws Throwable {
			target.acceptX(a1, a2);
		}
	}

	@Nonnull
	static LBiFltConsumer recursive(final @Nonnull LFunction<LBiFltConsumer, LBiFltConsumer> selfLambda) {
		final S single = new S();
		LBiFltConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static LBiFltConsumer biFltConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBiFltConsumer biFltConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiFltConsumer.LFlt1Flt0Cons flt1Flt0Cons(final @Nonnull LBiFltConsumer.LFlt1Flt0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(float a1, float a2, final @Nonnull LBiFltConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiFltConsumer compose(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static LBiFltConsumer composed(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, LBiFltConsumer after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biFltConsCompose(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2, LBiFltConsumer after) {
		return after.biFltConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiFltConsumer together in a order. */
	@Nonnull
	default LBiFltConsumer andThen(@Nonnull LBiFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			this.accept(a1, a2);
			after.accept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiFltConsumer for method references. */
	@FunctionalInterface
	interface LFlt1Flt0Cons extends LBiFltConsumer {

		/**
		 * Implement this, but call accept(float a1,float a2)
		 */
		default void acceptX(float a1, float a2) {
			this.acceptFlt1Flt0(a2, a1);
		}

		// void acceptFlt1Flt0(float a2,float a1) ;
		default void acceptFlt1Flt0(float a2, float a1) {
			// nestingAcceptFlt1Flt0(a2,a1);
			try {
				this.acceptFlt1Flt0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptFlt1Flt0(float a2,float a1)
		 */
		void acceptFlt1Flt0X(float a2, float a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiFltConsumer) */
	public static void doNothing(float a1, float a2) {
		// NOSONAR
	}

	// <editor-fold desc="fluentUse">

	public static <R> R inlineAcceptR(R retval, float a1, float a2, LBiFltConsumer consumer) {
		consumer.accept(a1, a2);
		return retval;
	}

	public static float inlineAccept(float a1, float a2, LBiFltConsumer consumer) {
		consumer.accept(a1, a2);
		return a1;
	}

	// </editor-fold>

}
