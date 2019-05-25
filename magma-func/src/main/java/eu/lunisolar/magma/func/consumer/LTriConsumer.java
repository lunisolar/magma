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
 * Non-throwing functional interface (lambda) LTriConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriConsumer<T1, T2, T3> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<a<T1>, a<T2>, a<T3>> {

	String DESCRIPTION = "LTriConsumer: void accept(T1 a1,T2 a2,T3 a3)";

	// void accept(T1 a1,T2 a2,T3 a3) ;
	default void accept(T1 a1, T2 a2, T3 a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,T3 a3)
	 */
	void acceptX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default LTuple.Void tupleAccept(LTriple<T1, T2, T3> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriConsumer<T1, T2, T3> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage, messageParams);
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF);
	}

	default void acceptThen(T1 a1, T2 a2, T3 a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTriConsumer<T1, T2, T3> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3> void handlingAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF);
	}

	static <T1, T2, T3> void tryAcceptThen(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static <T1, T2, T3> void failSafeAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, T3> LTriConsumer<T1, T2, T3> failSafe(LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
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
	public static <T1, T2, T3> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
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
	public static <T1, T2, T3> void times(int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiConsumer<T2, T3> lShrink(LBiFunction<T2, T3, T1> left) {
		return (a2, a3) -> accept(left.apply(a2, a3), a2, a3);
	}

	public default LBiConsumer<T2, T3> lShrinkc(T1 a1) {
		return (a2, a3) -> accept(a1, a2, a3);
	}

	public static <T2, T3, T1> LBiConsumer<T2, T3> lShrinked(LBiFunction<T2, T3, T1> left, LTriConsumer<T1, T2, T3> func) {
		return func.lShrink(left);
	}

	public static <T2, T3, T1> LBiConsumer<T2, T3> lShrinkedc(T1 a1, LTriConsumer<T1, T2, T3> func) {
		return func.lShrinkc(a1);
	}

	public default LBiConsumer<T1, T2> rShrink(LBiFunction<T1, T2, T3> right) {
		return (a1, a2) -> accept(a1, a2, right.apply(a1, a2));
	}

	public default LBiConsumer<T1, T2> rShrinkc(T3 a3) {
		return (a1, a2) -> accept(a1, a2, a3);
	}

	public static <T1, T2, T3> LBiConsumer<T1, T2> rShrinked(LBiFunction<T1, T2, T3> right, LTriConsumer<T1, T2, T3> func) {
		return func.rShrink(right);
	}

	public static <T1, T2, T3> LBiConsumer<T1, T2> rShrinkedc(T3 a3, LTriConsumer<T1, T2, T3> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2, T3> LTriConsumer<T1, T2, T3> uncurry(LFunction<T1, LFunction<T2, LConsumer<T3>>> func) {
		return (T1 a1, T2 a2, T3 a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Cast that removes generics. */
	public default LTriConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3, V4> LTriConsumer<V2, V3, V4> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, T1, T2, T3> LTriConsumer<V2, V3, V4> cast(LTriConsumer<T1, T2, T3> function) {
		return (LTriConsumer) function;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T1 a1, T2 a2, T3 a3) {
		return () -> this.accept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept3rd(@Nonnull LConsumer<T3> func) {
		return (a1, a2, a3) -> func.accept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triCons(final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> recursive(final @Nonnull LFunction<LTriConsumer<T1, T2, T3>, LTriConsumer<T1, T2, T3>> selfLambda) {
		final LTriConsumerSingle<T1, T2, T3> single = new LTriConsumerSingle();
		LTriConsumer<T1, T2, T3> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTriConsumerSingle<T1, T2, T3> implements LSingle<LTriConsumer<T1, T2, T3>>, LTriConsumer<T1, T2, T3> {
		private LTriConsumer<T1, T2, T3> target = null;

		@Override
		public void acceptX(T1 a1, T2 a2, T3 a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}

		@Override
		public LTriConsumer<T1, T2, T3> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T2> LObj0Obj2Obj1Cons<T1, T3, T2> obj0Obj2Obj1Cons(final @Nonnull LObj0Obj2Obj1Cons<T1, T3, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T3> LObj1BiObj2Cons<T2, T1, T3> obj1BiObj2Cons(final @Nonnull LObj1BiObj2Cons<T2, T1, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T3, T1> LObj1Obj2Obj0Cons<T2, T3, T1> obj1Obj2Obj0Cons(final @Nonnull LObj1Obj2Obj0Cons<T2, T3, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T1, T2> LObj2Obj0Obj1Cons<T3, T1, T2> obj2Obj0Obj1Cons(final @Nonnull LObj2Obj0Obj1Cons<T3, T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T2, T1> LBiObj1Obj0Cons<T3, T2, T1> biObj1Obj0Cons(final @Nonnull LBiObj1Obj0Cons<T3, T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, T3> void call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> safe() {
		return LTriConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3> LSupplier<LTriConsumer<T1, T2, T3>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> safe(final @Nullable LTriConsumer<T1, T2, T3> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3> LSupplier<LTriConsumer<T1, T2, T3>> safeSupplier(final @Nullable LSupplier<LTriConsumer<T1, T2, T3>> supplier) {
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
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	public static <V1, V2, V3, T1, T2, T3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, LTriConsumer<T1, T2, T3> after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriConsumer<T1,T2,T3> together in a order. */
	@Nonnull
	default LTriConsumer<T1, T2, T3> andThen(@Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> after) {
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

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj0Obj2Obj1Cons<T1, T3, T2> extends LTriConsumer<T1, T2, T3> {

		void acceptObj0Obj2Obj1(T1 a1, T3 a3, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3) {
			this.acceptObj0Obj2Obj1(a1, a3, a2);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj1BiObj2Cons<T2, T1, T3> extends LTriConsumer<T1, T2, T3> {

		void acceptObj1BiObj2(T2 a2, T1 a1, T3 a3);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3) {
			this.acceptObj1BiObj2(a2, a1, a3);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj2Obj0Cons<T2, T3, T1> extends LTriConsumer<T1, T2, T3> {

		void acceptObj1Obj2Obj0(T2 a2, T3 a3, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3) {
			this.acceptObj1Obj2Obj0(a2, a3, a1);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj0Obj1Cons<T3, T1, T2> extends LTriConsumer<T1, T2, T3> {

		void acceptObj2Obj0Obj1(T3 a3, T1 a1, T2 a2);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3) {
			this.acceptObj2Obj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LBiObj1Obj0Cons<T3, T2, T1> extends LTriConsumer<T1, T2, T3> {

		void acceptBiObj1Obj0(T3 a3, T2 a2, T1 a1);

		@Override
		default void acceptX(T1 a1, T2 a2, T3 a3) {
			this.acceptBiObj1Obj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTriConsumer) */
	public static <T1, T2, T3> void doNothing(T1 a1, T2 a2, T3 a3) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, T1, T2, T3> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, T2, T3> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, T2, T3> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T1, C2, C3, I3, T2, T3> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T1, C2, I2, C3, I3, T2, T3> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	public static <C, T, T1, T2> T pairForEach(T target, IndexedRead<C, a<? extends Object>> ia, C s, LTriConsumer<T, T1, T2> consumer) {
		int size = ia.size(s);

		if (size % 2 != 0) {
			throw new IllegalArgumentException("Size of container is not multiplication of 2.");
		}

		LOiFunction<C, T1> g1 = (LOiFunction) ia.getter();
		LOiFunction<C, T2> g2 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size;) {
			T1 v1 = g1.apply(s, i++);
			T2 v2 = g2.apply(s, i++);
			consumer.accept(target, v1, v2);
		}
		return target;
	}

	public static <C, I, T, T1, T2> T pairIterate(T target, SequentialRead<C, I, a<? extends Object>> sa, C s, LTriConsumer<T, T1, T2> consumer) {

		LFunction<C, I> adapter = sa.adapter();
		I a = adapter.apply(s);

		LFunction<I, T1> g1 = (LFunction) sa.supplier();
		LFunction<I, T2> g2 = (LFunction) sa.supplier();
		LPredicate<I> tester = (LPredicate) sa.tester();
		int i = 0;
		while (tester.test(a)) {
			T1 v1 = g1.apply(a);
			T2 v2 = g2.apply(a);
			consumer.accept(target, v1, v2);
			i += 2;
		}

		return target;
	}

}
