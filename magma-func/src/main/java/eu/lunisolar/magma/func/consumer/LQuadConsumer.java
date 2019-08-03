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
 * Non-throwing functional interface (lambda) LQuadConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 4): T1 a1,T2 a2,T3 a3,T4 a4
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuadConsumer<T1, T2, T3, T4> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain4<a<T1>, a<T2>, a<T3>, a<T4>> {

	String DESCRIPTION = "LQuadConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4)";

	// void accept(T1 a1,T2 a2,T3 a3,T4 a4) ;
	default void accept(T1 a1, T2 a2, T3 a3, T4 a4) {
		// nestingAccept(a1,a2,a3,a4);
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,T3 a3,T4 a4)
	 */
	void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable;

	default LTuple.Void tupleAccept(LQuad<T1, T2, T3, T4> args) {
		accept(args.first(), args.second(), args.third(), args.fourth());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4) -> handlingAccept(a1, a2, a3, a4, handling);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, exF, newMessage, messageParams);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, exF);
	}

	default void acceptThen(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3, a4) -> acceptThen(a1, a2, a3, a4, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4> void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, a4, handling);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, exF, newMessage, messageParams);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, exF);
	}

	static <T1, T2, T3, T4> void tryAcceptThen(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, a4, handler);
	}

	default void failSafeAccept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> failSafe) {
		try {
			accept(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3, a4);
		}
	}

	static <T1, T2, T3, T4> void failSafeAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull LQuadConsumer<T1, T2, T3, T4> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3, a4);
		} else {
			func.failSafeAccept(a1, a2, a3, a4, failSafe);
		}
	}

	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> failSafe(LQuadConsumer<T1, T2, T3, T4> func, @Nonnull LQuadConsumer<T1, T2, T3, T4> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3, a4) -> failSafeAccept(a1, a2, a3, a4, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuadConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, func);
	}

	public default LTriConsumer<T2, T3, T4> lShrink(@Nonnull LTriFunction<T2, T3, T4, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3, a4) -> accept(left.apply(a2, a3, a4), a2, a3, a4);
	}

	public default LTriConsumer<T2, T3, T4> lShrink_(T1 a1) {
		return (a2, a3, a4) -> accept(a1, a2, a3, a4);
	}

	public static <T2, T3, T4, T1> LTriConsumer<T2, T3, T4> lShrunken(@Nonnull LTriFunction<T2, T3, T4, T1> left, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T3, T4, T1> LTriConsumer<T2, T3, T4> lShrunken_(T1 a1, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LTriConsumer<T1, T2, T3> rShrink(@Nonnull LTriFunction<T1, T2, T3, T4> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2, a3) -> accept(a1, a2, a3, right.apply(a1, a2, a3));
	}

	public default LTriConsumer<T1, T2, T3> rShrink_(T4 a4) {
		return (a1, a2, a3) -> accept(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> LTriConsumer<T1, T2, T3> rShrunken(@Nonnull LTriFunction<T1, T2, T3, T4> right, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, T3, T4> LTriConsumer<T1, T2, T3> rShrunken_(T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a4);
	}

	/**  */
	public static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LConsumer<T4>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> func.apply(a1).apply(a2).apply(a3).accept(a4);
	}

	/** Cast that removes generics. */
	public default LQuadConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3, V4, V5> LQuadConsumer<V2, V3, V4, V5> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, V5, T1, T2, T3, T4> LQuadConsumer<V2, V3, V4, V5> cast(LQuadConsumer<T1, T2, T3, T4> function) {
		return (LQuadConsumer) function;
	}

	/** Calls domain consumer before main function. */
	public default LQuadConsumer<T1, T2, T3, T4> beforeDo(@Nonnull LQuadConsumer<T1, T2, T3, T4> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			before.accept(a1, a2, a3, a4);
			accept(a1, a2, a3, a4);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T1 a1, T2 a2, T3 a3, T4 a4) {
		return () -> this.accept(a1, a2, a3, a4);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3, a4) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3, a4) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> accept3rd(@Nonnull LConsumer<T3> func) {
		return (a1, a2, a3, a4) -> func.accept(a3);
	}

	/** Captures single parameter function into this interface where only 4th parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> accept4th(@Nonnull LConsumer<T4> func) {
		return (a1, a2, a3, a4) -> func.accept(a4);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadCons(final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadCons(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, @Nullable Class<T4> c4, final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> recursive(final @Nonnull LFunction<LQuadConsumer<T1, T2, T3, T4>, LQuadConsumer<T1, T2, T3, T4>> selfLambda) {
		final LQuadConsumerSingle<T1, T2, T3, T4> single = new LQuadConsumerSingle();
		LQuadConsumer<T1, T2, T3, T4> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LQuadConsumerSingle<T1, T2, T3, T4> implements LSingle<LQuadConsumer<T1, T2, T3, T4>>, LQuadConsumer<T1, T2, T3, T4> {
		private LQuadConsumer<T1, T2, T3, T4> target = null;

		@Override
		public void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
			target.acceptX(a1, a2, a3, a4);
		}

		@Override
		public LQuadConsumer<T1, T2, T3, T4> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T4, T3> LBiObj1Obj3Obj2Cons<T1, T2, T4, T3> biObj1Obj3Obj2Cons(final @Nonnull LBiObj1Obj3Obj2Cons<T1, T2, T4, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T2, T4> LObj0Obj2BiObj3Cons<T1, T3, T2, T4> obj0Obj2BiObj3Cons(final @Nonnull LObj0Obj2BiObj3Cons<T1, T3, T2, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T4, T2> LObj0Obj2Obj3Obj1Cons<T1, T3, T4, T2> obj0Obj2Obj3Obj1Cons(final @Nonnull LObj0Obj2Obj3Obj1Cons<T1, T3, T4, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T4, T2, T3> LObj0Obj3Obj1Obj2Cons<T1, T4, T2, T3> obj0Obj3Obj1Obj2Cons(final @Nonnull LObj0Obj3Obj1Obj2Cons<T1, T4, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T4, T3, T2> LObj0BiObj2Obj1Cons<T1, T4, T3, T2> obj0BiObj2Obj1Cons(final @Nonnull LObj0BiObj2Obj1Cons<T1, T4, T3, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T3, T4> LObj1TriObj3Cons<T2, T1, T3, T4> obj1TriObj3Cons(final @Nonnull LObj1TriObj3Cons<T2, T1, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T4, T3> LObj1Obj0Obj3Obj2Cons<T2, T1, T4, T3> obj1Obj0Obj3Obj2Cons(final @Nonnull LObj1Obj0Obj3Obj2Cons<T2, T1, T4, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T3, T1, T4> LObj1Obj2BiObj3Cons<T2, T3, T1, T4> obj1Obj2BiObj3Cons(final @Nonnull LObj1Obj2BiObj3Cons<T2, T3, T1, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T3, T4, T1> LObj1Obj2Obj3Obj0Cons<T2, T3, T4, T1> obj1Obj2Obj3Obj0Cons(final @Nonnull LObj1Obj2Obj3Obj0Cons<T2, T3, T4, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T4, T1, T3> LObj1Obj3Obj0Obj2Cons<T2, T4, T1, T3> obj1Obj3Obj0Obj2Cons(final @Nonnull LObj1Obj3Obj0Obj2Cons<T2, T4, T1, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T4, T3, T1> LObj1BiObj2Obj0Cons<T2, T4, T3, T1> obj1BiObj2Obj0Cons(final @Nonnull LObj1BiObj2Obj0Cons<T2, T4, T3, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T1, T2, T4> LObj2Obj0BiObj3Cons<T3, T1, T2, T4> obj2Obj0BiObj3Cons(final @Nonnull LObj2Obj0BiObj3Cons<T3, T1, T2, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T1, T4, T2> LObj2Obj0Obj3Obj1Cons<T3, T1, T4, T2> obj2Obj0Obj3Obj1Cons(final @Nonnull LObj2Obj0Obj3Obj1Cons<T3, T1, T4, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T2, T1, T4> LBiObj1BiObj3Cons<T3, T2, T1, T4> biObj1BiObj3Cons(final @Nonnull LBiObj1BiObj3Cons<T3, T2, T1, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T2, T4, T1> LBiObj1Obj3Obj0Cons<T3, T2, T4, T1> biObj1Obj3Obj0Cons(final @Nonnull LBiObj1Obj3Obj0Cons<T3, T2, T4, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T4, T1, T2> LObj2Obj3Obj0Obj1Cons<T3, T4, T1, T2> obj2Obj3Obj0Obj1Cons(final @Nonnull LObj2Obj3Obj0Obj1Cons<T3, T4, T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T4, T2, T1> LObj2Obj3Obj1Obj0Cons<T3, T4, T2, T1> obj2Obj3Obj1Obj0Cons(final @Nonnull LObj2Obj3Obj1Obj0Cons<T3, T4, T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T1, T2, T3> LObj3Obj0Obj1Obj2Cons<T4, T1, T2, T3> obj3Obj0Obj1Obj2Cons(final @Nonnull LObj3Obj0Obj1Obj2Cons<T4, T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T1, T3, T2> LObj3BiObj2Obj1Cons<T4, T1, T3, T2> obj3BiObj2Obj1Cons(final @Nonnull LObj3BiObj2Obj1Cons<T4, T1, T3, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T2, T1, T3> LBiObj1Obj0Obj2Cons<T4, T2, T1, T3> biObj1Obj0Obj2Cons(final @Nonnull LBiObj1Obj0Obj2Cons<T4, T2, T1, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T2, T3, T1> LTriObj2Obj0Cons<T4, T2, T3, T1> triObj2Obj0Cons(final @Nonnull LTriObj2Obj0Cons<T4, T2, T3, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T3, T1, T2> LObj3Obj2Obj0Obj1Cons<T4, T3, T1, T2> obj3Obj2Obj0Obj1Cons(final @Nonnull LObj3Obj2Obj0Obj1Cons<T4, T3, T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T4, T3, T2, T1> LObj3Obj2Obj1Obj0Cons<T4, T3, T2, T1> obj3Obj2Obj1Obj0Cons(final @Nonnull LObj3Obj2Obj1Obj0Cons<T4, T3, T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, T3, T4> void call(T1 a1, T2 a2, T3 a3, T4 a4, final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3, a4);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> safe() {
		return LQuadConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LSupplier<LQuadConsumer<T1, T2, T3, T4>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> safe(final @Nullable LQuadConsumer<T1, T2, T3, T4> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LSupplier<LQuadConsumer<T1, T2, T3, T4>> safeSupplier(final @Nullable LSupplier<LQuadConsumer<T1, T2, T3, T4>> supplier) {
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
	default <V1, V2, V3, V4> LQuadConsumer<V1, V2, V3, V4> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3,
			@Nonnull final LFunction<? super V4, ? extends T4> before4) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		return (v1, v2, v3, v4) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4));
	}

	public static <V1, V2, V3, V4, T1, T2, T3, T4> LQuadConsumer<V1, V2, V3, V4> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, LQuadConsumer<T1, T2, T3, T4> after) {
		return after.compose(before1, before2, before3, before4);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LQuadConsumer<T1,T2,T3,T4> together in a order. */
	@Nonnull
	default LQuadConsumer<T1, T2, T3, T4> andThen(@Nonnull LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4) -> {
			this.accept(a1, a2, a3, a4);
			after.accept(a1, a2, a3, a4);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LBiObj1Obj3Obj2Cons<T1, T2, T4, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptBiObj1Obj3Obj2(T1 a1, T2 a2, T4 a4, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptBiObj1Obj3Obj2(a1, a2, a4, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj0Obj2BiObj3Cons<T1, T3, T2, T4> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj0Obj2BiObj3(T1 a1, T3 a3, T2 a2, T4 a4);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj0Obj2BiObj3(a1, a3, a2, a4);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj0Obj2Obj3Obj1Cons<T1, T3, T4, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj0Obj2Obj3Obj1(T1 a1, T3 a3, T4 a4, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj0Obj2Obj3Obj1(a1, a3, a4, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj0Obj3Obj1Obj2Cons<T1, T4, T2, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj0Obj3Obj1Obj2(T1 a1, T4 a4, T2 a2, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj0Obj3Obj1Obj2(a1, a4, a2, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj0BiObj2Obj1Cons<T1, T4, T3, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj0BiObj2Obj1(T1 a1, T4 a4, T3 a3, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj0BiObj2Obj1(a1, a4, a3, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1TriObj3Cons<T2, T1, T3, T4> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1TriObj3(T2 a2, T1 a1, T3 a3, T4 a4);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1TriObj3(a2, a1, a3, a4);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj0Obj3Obj2Cons<T2, T1, T4, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1Obj0Obj3Obj2(T2 a2, T1 a1, T4 a4, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1Obj0Obj3Obj2(a2, a1, a4, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj2BiObj3Cons<T2, T3, T1, T4> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1Obj2BiObj3(T2 a2, T3 a3, T1 a1, T4 a4);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1Obj2BiObj3(a2, a3, a1, a4);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj2Obj3Obj0Cons<T2, T3, T4, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1Obj2Obj3Obj0(T2 a2, T3 a3, T4 a4, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1Obj2Obj3Obj0(a2, a3, a4, a1);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj3Obj0Obj2Cons<T2, T4, T1, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1Obj3Obj0Obj2(T2 a2, T4 a4, T1 a1, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1Obj3Obj0Obj2(a2, a4, a1, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj1BiObj2Obj0Cons<T2, T4, T3, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj1BiObj2Obj0(T2 a2, T4 a4, T3 a3, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj1BiObj2Obj0(a2, a4, a3, a1);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj0BiObj3Cons<T3, T1, T2, T4> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj2Obj0BiObj3(T3 a3, T1 a1, T2 a2, T4 a4);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj2Obj0BiObj3(a3, a1, a2, a4);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj0Obj3Obj1Cons<T3, T1, T4, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj2Obj0Obj3Obj1(T3 a3, T1 a1, T4 a4, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj2Obj0Obj3Obj1(a3, a1, a4, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LBiObj1BiObj3Cons<T3, T2, T1, T4> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptBiObj1BiObj3(T3 a3, T2 a2, T1 a1, T4 a4);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptBiObj1BiObj3(a3, a2, a1, a4);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LBiObj1Obj3Obj0Cons<T3, T2, T4, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptBiObj1Obj3Obj0(T3 a3, T2 a2, T4 a4, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptBiObj1Obj3Obj0(a3, a2, a4, a1);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj3Obj0Obj1Cons<T3, T4, T1, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj2Obj3Obj0Obj1(T3 a3, T4 a4, T1 a1, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj2Obj3Obj0Obj1(a3, a4, a1, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj3Obj1Obj0Cons<T3, T4, T2, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj2Obj3Obj1Obj0(T3 a3, T4 a4, T2 a2, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj2Obj3Obj1Obj0(a3, a4, a2, a1);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj3Obj0Obj1Obj2Cons<T4, T1, T2, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj3Obj0Obj1Obj2(T4 a4, T1 a1, T2 a2, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj3Obj0Obj1Obj2(a4, a1, a2, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj3BiObj2Obj1Cons<T4, T1, T3, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj3BiObj2Obj1(T4 a4, T1 a1, T3 a3, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj3BiObj2Obj1(a4, a1, a3, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LBiObj1Obj0Obj2Cons<T4, T2, T1, T3> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptBiObj1Obj0Obj2(T4 a4, T2 a2, T1 a1, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptBiObj1Obj0Obj2(a4, a2, a1, a3);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LTriObj2Obj0Cons<T4, T2, T3, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptTriObj2Obj0(T4 a4, T2 a2, T3 a3, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptTriObj2Obj0(a4, a2, a3, a1);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj3Obj2Obj0Obj1Cons<T4, T3, T1, T2> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj3Obj2Obj0Obj1(T4 a4, T3 a3, T1 a1, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj3Obj2Obj0Obj1(a4, a3, a1, a2);
		}
	}

	/** Permutation of LQuadConsumer for method references. */
	@FunctionalInterface
	interface LObj3Obj2Obj1Obj0Cons<T4, T3, T2, T1> extends LQuadConsumer<T1, T2, T3, T4> {

		void acceptObj3Obj2Obj1Obj0(T4 a4, T3 a3, T2 a2, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.acceptObj3Obj2Obj1Obj0(a4, a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LQuadConsumer) */
	public static <T1, T2, T3, T4> void doNothing(T1 a1, T2 a2, T3 a3, T4 a4) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, T1, T2, T3, T4> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4,
			C4 source4, LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, C4, I4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, C4, I4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, C4, I4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, C4, I4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, C4, I4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, C4, I4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, C4, I4, T1, T2, T3, T4> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, C4, I4, T1, T2, T3, T4> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4,
			C4 source4, LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
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
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, T2, T3, T4> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, T2, T3, T4> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, T2, T3, T4> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, T2, T3, T4> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, C4, I4, T2, T3, T4> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, C4, I4, T2, T3, T4> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, C4, I4, T2, T3, T4> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, C4, I4, T2, T3, T4> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(a1, a2, a3, a4);
		}
		return a1;

	}

}
