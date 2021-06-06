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

package eu.lunisolar.magma.func.consumer;

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
 * Non-throwing functional interface (lambda) LQuintConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 5): T1 a1,T2 a2,T3 a3,T4 a4,T5 a5
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuintConsumer<T1, T2, T3, T4, T5> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain5<a<T1>, a<T2>, a<T3>, a<T4>, a<T5>> {

	String DESCRIPTION = "LQuintConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)";

	// void accept(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5) ;
	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		// nestingAccept(a1,a2,a3,a4,a5);
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)
	 */
	void acceptX(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) throws Throwable;

	default LTuple.Void tupleAccept(LQuint<T1, T2, T3, T4, T5> args) {
		accept(args.first(), args.second(), args.third(), args.fourth(), args.fifth());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4, a5) -> handlingAccept(a1, a2, a3, a4, a5, handling);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5, factory, newMessage);
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5, factory, newMessage, param1);
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5, factory, newMessage, param1, param1);
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5, factory, newMessage, param1, param2, param3);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5, factory);
	}

	default void acceptThen(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LQuintConsumer<T1, T2, T3, T4, T5> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3, a4, a5) -> acceptThen(a1, a2, a3, a4, a5, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		try {
			this.acceptX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> void shovingAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3, a4, a5);
	}

	static <T1, T2, T3, T4, T5> void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, a4, a5, handling);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3, a4, a5);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, a5, factory, newMessage);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, a5, factory, newMessage, param1);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, a5, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, a5, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4, T5> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, a5, factory);
	}

	static <T1, T2, T3, T4, T5> void tryAcceptThen(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, a4, a5, handler);
	}

	default void failSafeAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> failSafe) {
		try {
			accept(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3, a4, a5);
		}
	}

	static <T1, T2, T3, T4, T5> void failSafeAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3, a4, a5);
		} else {
			func.failSafeAccept(a1, a2, a3, a4, a5, failSafe);
		}
	}

	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> failSafe(LQuintConsumer<T1, T2, T3, T4, T5> func, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3, a4, a5) -> failSafeAccept(a1, a2, a3, a4, a5, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuintConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3, a4, a5);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3, a4, a5);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3, a4, a5);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3, a4, a5);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, a5, func);
	}

	default LQuadConsumer<T2, T3, T4, T5> lShrink(@Nonnull LQuadFunction<T2, T3, T4, T5, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3, a4, a5) -> accept(left.apply(a2, a3, a4, a5), a2, a3, a4, a5);
	}

	default LQuadConsumer<T2, T3, T4, T5> lShrink_(T1 a1) {
		return (a2, a3, a4, a5) -> accept(a1, a2, a3, a4, a5);
	}

	public static <T2, T3, T4, T5, T1> LQuadConsumer<T2, T3, T4, T5> lShrunken(@Nonnull LQuadFunction<T2, T3, T4, T5, T1> left, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T3, T4, T5, T1> LQuadConsumer<T2, T3, T4, T5> lShrunken_(T1 a1, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LQuadConsumer<T1, T2, T3, T4> rShrink(@Nonnull LQuadFunction<T1, T2, T3, T4, T5> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, right.apply(a1, a2, a3, a4));
	}

	default LQuadConsumer<T1, T2, T3, T4> rShrink_(T5 a5) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, a5);
	}

	public static <T1, T2, T3, T4, T5> LQuadConsumer<T1, T2, T3, T4> rShrunken(@Nonnull LQuadFunction<T1, T2, T3, T4, T5> right, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, T3, T4, T5> LQuadConsumer<T1, T2, T3, T4> rShrunken_(T5 a5, @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a5);
	}

	/**  */
	public static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LFunction<T4, LConsumer<T5>>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) -> func.apply(a1).apply(a2).apply(a3).apply(a4).accept(a5);
	}

	/** Cast that removes generics. */
	default LQuintConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3, V4, V5, V6> LQuintConsumer<V2, V3, V4, V5, V6> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, V5, V6> LQuintConsumer<V2, V3, V4, V5, V6> cast(LQuintConsumer<?, ?, ?, ?, ?> function) {
		return (LQuintConsumer) function;
	}

	/** Calls domain consumer before main function. */
	default LQuintConsumer<T1, T2, T3, T4, T5> beforeDo(@Nonnull LQuintConsumer<T1, T2, T3, T4, T5> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) -> {
			before.accept(a1, a2, a3, a4, a5);
			accept(a1, a2, a3, a4, a5);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return () -> this.accept(a1, a2, a3, a4, a5);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3, a4, a5) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3, a4, a5) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> accept3rd(@Nonnull LConsumer<T3> func) {
		return (a1, a2, a3, a4, a5) -> func.accept(a3);
	}

	/** Captures single parameter function into this interface where only 4th parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> accept4th(@Nonnull LConsumer<T4> func) {
		return (a1, a2, a3, a4, a5) -> func.accept(a4);
	}

	/** Captures single parameter function into this interface where only 5th parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> accept5th(@Nonnull LConsumer<T5> func) {
		return (a1, a2, a3, a4, a5) -> func.accept(a5);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> quintCons(final @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> quintCons(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, @Nullable Class<T4> c4, @Nullable Class<T5> c5, final @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2, T3, T4, T5> implements LQuintConsumer<T1, T2, T3, T4, T5> {
		private LQuintConsumer<T1, T2, T3, T4, T5> target = null;
		@Override
		public void acceptX(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) throws Throwable {
			target.acceptX(a1, a2, a3, a4, a5);
		}
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> recursive(final @Nonnull LFunction<LQuintConsumer<T1, T2, T3, T4, T5>, LQuintConsumer<T1, T2, T3, T4, T5>> selfLambda) {
		final S<T1, T2, T3, T4, T5> single = new S();
		LQuintConsumer<T1, T2, T3, T4, T5> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> quintConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4, a5) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> quintConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4, a5) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3, T4, T5> void call(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, final @Nonnull LQuintConsumer<T1, T2, T3, T4, T5> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3, a4, a5);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> safe() {
		return LQuintConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LSupplier<LQuintConsumer<T1, T2, T3, T4, T5>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintConsumer<T1, T2, T3, T4, T5> safe(final @Nullable LQuintConsumer<T1, T2, T3, T4, T5> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, T5> LSupplier<LQuintConsumer<T1, T2, T3, T4, T5>> safeSupplier(final @Nullable LSupplier<LQuintConsumer<T1, T2, T3, T4, T5>> supplier) {
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
	default <V1, V2, V3, V4, V5> LQuintConsumer<V1, V2, V3, V4, V5> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, @Nonnull final LFunction<? super V5, ? extends T5> before5) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		Null.nonNullArg(before5, "before5");
		return (v1, v2, v3, v4, v5) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4), before5.apply(v5));
	}

	public static <V1, V2, V3, V4, V5, T1, T2, T3, T4, T5> LQuintConsumer<V1, V2, V3, V4, V5> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, @Nonnull final LFunction<? super V5, ? extends T5> before5, LQuintConsumer<T1, T2, T3, T4, T5> after) {
		return after.compose(before1, before2, before3, before4, before5);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LQuintConsumer<T1,T2,T3,T4,T5> together in a order. */
	@Nonnull
	default LQuintConsumer<T1, T2, T3, T4, T5> andThen(@Nonnull LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4, a5) -> {
			this.accept(a1, a2, a3, a4, a5);
			after.accept(a1, a2, a3, a4, a5);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LQuintConsumer) */
	public static <T1, T2, T3, T4, T5> void doNothing(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, C5, T1, T2, T3, T4, T5> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4,
			C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, I4, C5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			SequentialRead<C4, I4, a<T4>> sa4, C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int size = ia5.size(source5);
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			IndexedRead<C4, a<T4>> ia4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3,
			SequentialRead<C4, I4, a<T4>> sa4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			SequentialRead<C4, I4, a<T4>> sa4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			SequentialRead<C4, I4, a<T4>> sa4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, I4, C5, I5, T1, T2, T3, T4, T5> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			SequentialRead<C4, I4, a<T4>> sa4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, C5, T2, T3, T4, T5> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5,
			LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5,
			LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5,
			LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, IndexedRead<C5, a<T5>> ia5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, I4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, IndexedRead<C5, a<T5>> ia5, C5 source5,
			LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, I4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, IndexedRead<C5, a<T5>> ia5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, I4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, IndexedRead<C5, a<T5>> ia5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		size = Integer.min(size, ia5.size(source5));
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, I4, C5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			IndexedRead<C5, a<T5>> ia5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int size = ia5.size(source5);
		LOiFunction<Object, T5> oiFunc5 = (LOiFunction) ia5.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = oiFunc5.apply(source5, i);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5, C5 source5,
			LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc5.test(iterator5)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size && testFunc5.test(iterator5)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc5.test(iterator5)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size && testFunc5.test(iterator5)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, I4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, SequentialRead<C5, I5, a<T5>> sa5,
			C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, I4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, I4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, I4, C5, I5, T2, T3, T4, T5> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			SequentialRead<C5, I5, a<T5>> sa5, C5 source5, LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		Object iterator5 = ((LFunction) sa5.adapter()).apply(source5);
		LPredicate<Object> testFunc5 = (LPredicate) sa5.tester();
		LFunction<Object, T5> nextFunc5 = (LFunction) sa5.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4) && testFunc5.test(iterator5)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			T5 a5 = nextFunc5.apply(iterator5);
			consumer.accept(a1, a2, a3, a4, a5);
		}
		return a1;

	}

	// <editor-fold desc="fluentUse">

	public static <T1, T2, T3, T4, T5, R> R inlineAcceptR(R retval, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> consumer) {
		consumer.accept(a1, a2, a3, a4, a5);
		return retval;
	}

	public static <T1, T2, T3, T4, T5> T1 inlineAccept(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintConsumer<T1, T2, T3, T4, T5> consumer) {
		consumer.accept(a1, a2, a3, a4, a5);
		return a1;
	}

	// </editor-fold>

}
