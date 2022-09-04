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

package eu.lunisolar.magma.func.consumer.primitives.obj;

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
 * Non-throwing functional interface (lambda) LObjBiLongConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T a1,long a2,long a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiLongConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<a<T>, aLong, aLong> {

	String DESCRIPTION = "LObjBiLongConsumer: void accept(T a1,long a2,long a3)";

	// void accept(T a1,long a2,long a3) ;
	default void accept(T a1, long a2, long a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T a1,long a2,long a3)
	 */
	void acceptX(T a1, long a2, long a3) throws Throwable;

	default LTuple.Void tupleAccept(LObjBiLongTriple<T> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T a1, long a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjBiLongConsumer<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjBiLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage);
	}

	default LObjBiLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1);
	}

	default LObjBiLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LObjBiLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default void accept(T a1, long a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjBiLongConsumer<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory);
	}

	default void acceptThen(T a1, long a2, long a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LObjBiLongConsumer<T> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T a1, long a2, long a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T a1, long a2, long a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void shovingAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3);
	}

	static <T> void handlingAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T> void tryAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory);
	}

	static <T> void tryAcceptThen(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(T a1, long a2, long a3, @Nonnull LObjBiLongConsumer<T> failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static <T> void failSafeAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> func, @Nonnull LObjBiLongConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjBiLongConsumer<T> failSafe(LObjBiLongConsumer<T> func, @Nonnull LObjBiLongConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBiLongConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongConsumer<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static <T> LObjBiLongConsumer<T> uncurry(@Nonnull LFunction<T, LLongFunction<LLongConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, long a2, long a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Cast that removes generics. */
	default LObjBiLongConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LObjBiLongConsumer<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2> LObjBiLongConsumer<V2> cast(LObjBiLongConsumer<?> function) {
		return (LObjBiLongConsumer) function;
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default LObjBiLongFunction<T, T> returning(T value) {
		return (a1, a2, a3) -> {
			LObjBiLongConsumer.this.accept(a1, a2, a3);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LObjBiLongConsumer<T> beforeDo(@Nonnull LObjBiLongConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, long a2, long a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T a1, long a2, long a3) {
		return () -> this.accept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjBiLongConsumer<T> accept1st(@Nonnull LConsumer<T> func) {
		return (a1, a2, a3) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjBiLongConsumer<T> accept2nd(@Nonnull LLongConsumer func) {
		return (a1, a2, a3) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjBiLongConsumer<T> accept3rd(@Nonnull LLongConsumer func) {
		return (a1, a2, a3) -> func.accept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer<T> objBiLongCons(final @Nonnull LObjBiLongConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LObjBiLongConsumer<T> objBiLongCons(@Nullable Class<T> c1, final @Nonnull LObjBiLongConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LObjBiLongConsumer<T> {
		private LObjBiLongConsumer<T> target = null;
		@Override
		public void acceptX(T a1, long a2, long a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T> LObjBiLongConsumer<T> recursive(final @Nonnull LFunction<LObjBiLongConsumer<T>, LObjBiLongConsumer<T>> selfLambda) {
		final S<T> single = new S();
		LObjBiLongConsumer<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static <T> LObjBiLongConsumer<T> objBiLongConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjBiLongConsumer<T> objBiLongConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer.LObj0Long2Long1Cons<T> obj0Long2Long1Cons(final @Nonnull LObjBiLongConsumer.LObj0Long2Long1Cons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer.LLong1Obj0Long2Cons<T> long1Obj0Long2Cons(final @Nonnull LObjBiLongConsumer.LLong1Obj0Long2Cons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer.LLong1Long2Obj0Cons<T> long1Long2Obj0Cons(final @Nonnull LObjBiLongConsumer.LLong1Long2Obj0Cons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer.LLong2Obj0Long1Cons<T> long2Obj0Long1Cons(final @Nonnull LObjBiLongConsumer.LLong2Obj0Long1Cons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiLongConsumer.LBiLong1Obj0Cons<T> biLong1Obj0Cons(final @Nonnull LObjBiLongConsumer.LBiLong1Obj0Cons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> void call(T a1, long a2, long a3, final @Nonnull LObjBiLongConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjBiLongConsumer<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	public static <V1, T> LObjBiLongConsumer<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3, LObjBiLongConsumer<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> objBiLongConsCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToLongFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	public static <V1, V2, V3, T> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToLongFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3,
			LObjBiLongConsumer<T> after) {
		return after.objBiLongConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LObjBiLongConsumer<T> together in a order. */
	@Nonnull
	default LObjBiLongConsumer<T> andThen(@Nonnull LObjBiLongConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjBiLongConsumer for method references. */
	@FunctionalInterface
	interface LObj0Long2Long1Cons<T> extends LObjBiLongConsumer<T> {

		/**
		 * Implement this, but call accept(T a1,long a2,long a3)
		 */
		default void acceptX(T a1, long a2, long a3) {
			this.acceptObj0Long2Long1(a1, a3, a2);
		}

		// void acceptObj0Long2Long1(T a1,long a3,long a2) ;
		default void acceptObj0Long2Long1(T a1, long a3, long a2) {
			// nestingAcceptObj0Long2Long1(a1,a3,a2);
			try {
				this.acceptObj0Long2Long1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptObj0Long2Long1(T a1,long a3,long a2)
		 */
		void acceptObj0Long2Long1X(T a1, long a3, long a2) throws Throwable;
	}

	/** Permutation of LObjBiLongConsumer for method references. */
	@FunctionalInterface
	interface LLong1Obj0Long2Cons<T> extends LObjBiLongConsumer<T> {

		/**
		 * Implement this, but call acceptObj0Long2Long1(T a1,long a3,long a2)
		 */
		default void acceptX(T a1, long a2, long a3) {
			this.acceptLong1Obj0Long2(a2, a1, a3);
		}

		// void acceptLong1Obj0Long2(long a2,T a1,long a3) ;
		default void acceptLong1Obj0Long2(long a2, T a1, long a3) {
			// nestingAcceptLong1Obj0Long2(a2,a1,a3);
			try {
				this.acceptLong1Obj0Long2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptLong1Obj0Long2(long a2,T a1,long a3)
		 */
		void acceptLong1Obj0Long2X(long a2, T a1, long a3) throws Throwable;
	}

	/** Permutation of LObjBiLongConsumer for method references. */
	@FunctionalInterface
	interface LLong1Long2Obj0Cons<T> extends LObjBiLongConsumer<T> {

		/**
		 * Implement this, but call acceptLong1Obj0Long2(long a2,T a1,long a3)
		 */
		default void acceptX(T a1, long a2, long a3) {
			this.acceptLong1Long2Obj0(a2, a3, a1);
		}

		// void acceptLong1Long2Obj0(long a2,long a3,T a1) ;
		default void acceptLong1Long2Obj0(long a2, long a3, T a1) {
			// nestingAcceptLong1Long2Obj0(a2,a3,a1);
			try {
				this.acceptLong1Long2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptLong1Long2Obj0(long a2,long a3,T a1)
		 */
		void acceptLong1Long2Obj0X(long a2, long a3, T a1) throws Throwable;
	}

	/** Permutation of LObjBiLongConsumer for method references. */
	@FunctionalInterface
	interface LLong2Obj0Long1Cons<T> extends LObjBiLongConsumer<T> {

		/**
		 * Implement this, but call acceptLong1Long2Obj0(long a2,long a3,T a1)
		 */
		default void acceptX(T a1, long a2, long a3) {
			this.acceptLong2Obj0Long1(a3, a1, a2);
		}

		// void acceptLong2Obj0Long1(long a3,T a1,long a2) ;
		default void acceptLong2Obj0Long1(long a3, T a1, long a2) {
			// nestingAcceptLong2Obj0Long1(a3,a1,a2);
			try {
				this.acceptLong2Obj0Long1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptLong2Obj0Long1(long a3,T a1,long a2)
		 */
		void acceptLong2Obj0Long1X(long a3, T a1, long a2) throws Throwable;
	}

	/** Permutation of LObjBiLongConsumer for method references. */
	@FunctionalInterface
	interface LBiLong1Obj0Cons<T> extends LObjBiLongConsumer<T> {

		/**
		 * Implement this, but call acceptLong2Obj0Long1(long a3,T a1,long a2)
		 */
		default void acceptX(T a1, long a2, long a3) {
			this.acceptBiLong1Obj0(a3, a2, a1);
		}

		// void acceptBiLong1Obj0(long a3,long a2,T a1) ;
		default void acceptBiLong1Obj0(long a3, long a2, T a1) {
			// nestingAcceptBiLong1Obj0(a3,a2,a1);
			try {
				this.acceptBiLong1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptBiLong1Obj0(long a3,long a2,T a1)
		 */
		void acceptBiLong1Obj0X(long a3, long a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjBiLongConsumer) */
	public static <T> void doNothing(T a1, long a2, long a3) {
		// NOSONAR
	}

	/** Does nothing (LObjBiLongConsumer.LLong1Obj0Long2Cons) */
	public static <T> void doNothing(long a2, T a1, long a3) {
		// NOSONAR
	}

	/** Does nothing (LObjBiLongConsumer.LLong1Long2Obj0Cons) */
	public static <T> void doNothing(long a2, long a3, T a1) {
		// NOSONAR
	}

	// <editor-fold desc="fluentUse">

	public static <T, R> R inlineAcceptR(R retval, T a1, long a2, long a3, LObjBiLongConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return retval;
	}

	public static <T> T inlineAccept(T a1, long a2, long a3, LObjBiLongConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return a1;
	}

	// </editor-fold>

}
