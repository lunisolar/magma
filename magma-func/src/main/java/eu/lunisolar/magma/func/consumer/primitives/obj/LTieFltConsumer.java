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
 * Non-throwing functional interface (lambda) LTieFltConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T a1,int a2,float a3
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, index, element) -> List::set    or  (list, index, element) -> Array::set  
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieFltConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TieConsumer<T, aFloat>, Codomain<aVoid>, Domain3<a<T>, aInt, aFloat> {

	String DESCRIPTION = "LTieFltConsumer: void accept(T a1,int a2,float a3)";

	// void accept(T a1,int a2,float a3) ;
	default void accept(T a1, int a2, float a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T a1,int a2,float a3)
	 */
	void acceptX(T a1, int a2, float a3) throws Throwable;

	default LTuple.Void tupleAccept(LObjIntFltTriple<T> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T a1, int a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieFltConsumer<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTieFltConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage);
	}

	default LTieFltConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1);
	}

	default LTieFltConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTieFltConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default void accept(T a1, int a2, float a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTieFltConsumer<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory);
	}

	default void acceptThen(T a1, int a2, float a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTieFltConsumer<T> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T a1, int a2, float a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T a1, int a2, float a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void shovingAccept(T a1, int a2, float a3, LTieFltConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3);
	}

	static <T> void handlingAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T> void tryAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory);
	}

	static <T> void tryAcceptThen(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(T a1, int a2, float a3, @Nonnull LTieFltConsumer<T> failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static <T> void failSafeAccept(T a1, int a2, float a3, LTieFltConsumer<T> func, @Nonnull LTieFltConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieFltConsumer<T> failSafe(LTieFltConsumer<T> func, @Nonnull LTieFltConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieFltConsumer.DESCRIPTION;
	}

	default LTieFltFunction<T> toTieFunction() {
		return (t, i, e) -> {
			this.accept(t, i, e);
			return 1;
		};
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, float a3, @Nonnull LTieFltConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, float a3, @Nonnull LTieFltConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, float a3, @Nonnull LTieFltConsumer<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	default LObjIntConsumer<T> rShrink(@Nonnull LOiToFltFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> accept(a1, a2, right.applyAsFlt(a1, a2));
	}

	default LObjIntConsumer<T> rShrink_(float a3) {
		return (a1, a2) -> accept(a1, a2, a3);
	}

	public static <T> LObjIntConsumer<T> rShrunken(@Nonnull LOiToFltFunction<T> right, @Nonnull LTieFltConsumer<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LObjIntConsumer<T> rShrunken_(float a3, @Nonnull LTieFltConsumer<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T> LTieFltConsumer<T> uncurry(@Nonnull LFunction<T, LIntFunction<LFltConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, float a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Cast that removes generics. */
	default LTieFltConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LTieFltConsumer<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2> LTieFltConsumer<V2> cast(LTieFltConsumer<?> function) {
		return (LTieFltConsumer) function;
	}

	/** Calls domain consumer before main function. */
	default LTieFltConsumer<T> beforeDo(@Nonnull LTieFltConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, float a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T a1, int a2, float a3) {
		return () -> this.accept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieFltConsumer<T> accept1st(@Nonnull LConsumer<T> func) {
		return (a1, a2, a3) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieFltConsumer<T> accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2, a3) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieFltConsumer<T> accept3rd(@Nonnull LFltConsumer func) {
		return (a1, a2, a3) -> func.accept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer<T> tieFltCons(final @Nonnull LTieFltConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LTieFltConsumer<T> tieFltCons(@Nullable Class<T> c1, final @Nonnull LTieFltConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LTieFltConsumer<T> {
		private LTieFltConsumer<T> target = null;
		@Override
		public void acceptX(T a1, int a2, float a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T> LTieFltConsumer<T> recursive(final @Nonnull LFunction<LTieFltConsumer<T>, LTieFltConsumer<T>> selfLambda) {
		final S<T> single = new S();
		LTieFltConsumer<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static <T> LTieFltConsumer<T> tieFltConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LTieFltConsumer<T> tieFltConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer.LObjFltIntCons<T> objFltIntCons(final @Nonnull LTieFltConsumer.LObjFltIntCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer.LIntObjFltCons<T> intObjFltCons(final @Nonnull LTieFltConsumer.LIntObjFltCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer.LIntFltObjCons<T> intFltObjCons(final @Nonnull LTieFltConsumer.LIntFltObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer.LFltObjIntCons<T> fltObjIntCons(final @Nonnull LTieFltConsumer.LFltObjIntCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieFltConsumer.LFltIntObjCons<T> fltIntObjCons(final @Nonnull LTieFltConsumer.LFltIntObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> void call(T a1, int a2, float a3, final @Nonnull LTieFltConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T> LTieFltConsumer<T> safe() {
		return LTieFltConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieFltConsumer<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieFltConsumer<T> safe(final @Nullable LTieFltConsumer<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieFltConsumer<T>> safeSupplier(final @Nullable LSupplier<LTieFltConsumer<T>> supplier) {
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
	default <V1> LTieFltConsumer<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, T> LTieFltConsumer<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3, LTieFltConsumer<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> tieFltConsCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, V2, V3, T> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3, LTieFltConsumer<T> after) {
		return after.tieFltConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTieFltConsumer<T> together in a order. */
	@Nonnull
	default LTieFltConsumer<T> andThen(@Nonnull LTieFltConsumer<? super T> after) {
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

	/** Permutation of LTieFltConsumer for method references. */
	@FunctionalInterface
	interface LObjFltIntCons<T> extends LTieFltConsumer<T> {

		/**
		 * Implement this, but call accept(T a1,int a2,float a3)
		 */
		default void acceptX(T a1, int a2, float a3) {
			this.acceptObjFltInt(a1, a3, a2);
		}

		// void acceptObjFltInt(T a1,float a3,int a2) ;
		default void acceptObjFltInt(T a1, float a3, int a2) {
			// nestingAcceptObjFltInt(a1,a3,a2);
			try {
				this.acceptObjFltIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptObjFltInt(T a1,float a3,int a2)
		 */
		void acceptObjFltIntX(T a1, float a3, int a2) throws Throwable;
	}

	/** Permutation of LTieFltConsumer for method references. */
	@FunctionalInterface
	interface LIntObjFltCons<T> extends LTieFltConsumer<T> {

		/**
		 * Implement this, but call acceptObjFltInt(T a1,float a3,int a2)
		 */
		default void acceptX(T a1, int a2, float a3) {
			this.acceptIntObjFlt(a2, a1, a3);
		}

		// void acceptIntObjFlt(int a2,T a1,float a3) ;
		default void acceptIntObjFlt(int a2, T a1, float a3) {
			// nestingAcceptIntObjFlt(a2,a1,a3);
			try {
				this.acceptIntObjFltX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptIntObjFlt(int a2,T a1,float a3)
		 */
		void acceptIntObjFltX(int a2, T a1, float a3) throws Throwable;
	}

	/** Permutation of LTieFltConsumer for method references. */
	@FunctionalInterface
	interface LIntFltObjCons<T> extends LTieFltConsumer<T> {

		/**
		 * Implement this, but call acceptIntObjFlt(int a2,T a1,float a3)
		 */
		default void acceptX(T a1, int a2, float a3) {
			this.acceptIntFltObj(a2, a3, a1);
		}

		// void acceptIntFltObj(int a2,float a3,T a1) ;
		default void acceptIntFltObj(int a2, float a3, T a1) {
			// nestingAcceptIntFltObj(a2,a3,a1);
			try {
				this.acceptIntFltObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptIntFltObj(int a2,float a3,T a1)
		 */
		void acceptIntFltObjX(int a2, float a3, T a1) throws Throwable;
	}

	/** Permutation of LTieFltConsumer for method references. */
	@FunctionalInterface
	interface LFltObjIntCons<T> extends LTieFltConsumer<T> {

		/**
		 * Implement this, but call acceptIntFltObj(int a2,float a3,T a1)
		 */
		default void acceptX(T a1, int a2, float a3) {
			this.acceptFltObjInt(a3, a1, a2);
		}

		// void acceptFltObjInt(float a3,T a1,int a2) ;
		default void acceptFltObjInt(float a3, T a1, int a2) {
			// nestingAcceptFltObjInt(a3,a1,a2);
			try {
				this.acceptFltObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptFltObjInt(float a3,T a1,int a2)
		 */
		void acceptFltObjIntX(float a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LTieFltConsumer for method references. */
	@FunctionalInterface
	interface LFltIntObjCons<T> extends LTieFltConsumer<T> {

		/**
		 * Implement this, but call acceptFltObjInt(float a3,T a1,int a2)
		 */
		default void acceptX(T a1, int a2, float a3) {
			this.acceptFltIntObj(a3, a2, a1);
		}

		// void acceptFltIntObj(float a3,int a2,T a1) ;
		default void acceptFltIntObj(float a3, int a2, T a1) {
			// nestingAcceptFltIntObj(a3,a2,a1);
			try {
				this.acceptFltIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptFltIntObj(float a3,int a2,T a1)
		 */
		void acceptFltIntObjX(float a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LTieFltConsumer) */
	public static <T> void doNothing(T a1, int a2, float a3) {
		// NOSONAR
	}

	/** Does nothing (LTieFltConsumer.LObjFltIntCons) */
	public static <T> void doNothing(T a1, float a3, int a2) {
		// NOSONAR
	}

	/** Does nothing (LTieFltConsumer.LIntObjFltCons) */
	public static <T> void doNothing(int a2, T a1, float a3) {
		// NOSONAR
	}

	/** Does nothing (LTieFltConsumer.LIntFltObjCons) */
	public static <T> void doNothing(int a2, float a3, T a1) {
		// NOSONAR
	}

	/** Does nothing (LTieFltConsumer.LFltObjIntCons) */
	public static <T> void doNothing(float a3, T a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LTieFltConsumer.LFltIntObjCons) */
	public static <T> void doNothing(float a3, int a2, T a1) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
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
	public static <C1, C2, I2, C3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
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
	public static <C1, I1, C2, I2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
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
	public static <C1, C2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
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
	public static <C1, I1, C2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
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
	public static <C1, C2, I2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
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
	public static <C1, I1, C2, I2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C3, T> int indexedForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			T a1 = oiFunc1.apply(source1, a2);
			float a3 = oiFunc3.applyAsFlt(source3, a2);
			consumer.accept(a1, a2, a3);
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C3, T> int indexedIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int a2 = 0;
		while (testFunc1.test(iterator1) && a2 < size) {
			T a1 = nextFunc1.apply(iterator1);
			float a3 = oiFunc3.applyAsFlt(source3, a2);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C3, I3, T> int indexedIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int a2 = 0;
		while (a2 < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, a2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C3, I3, T> int indexedIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int a2 = 0;
		while (testFunc1.test(iterator1) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2, C3> T targetedForEach(T a1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2, I2, C3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
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
	public static <T, C2, C3, I3> T targetedIterate(T a1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
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
	public static <T, C2, I2, C3, I3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=float a3, type=TIE_SOURCE}, SourcePurpose{arg=float a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aFloat> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToFltFunction<SRC>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LTieFltConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LTieFltConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LTieFltConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=float a3, type=TIE_SOURCE}, SourcePurpose{arg=float a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieFltConsumer<? super T> consumer, type=CONST}] */
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToFltFunction<SRC> srcAcc3, LTieFltConsumer<? super T> consumer) {
		for (int sIndex = sStart, tIndex = tStart; sIndex < sEnd; sIndex++, tIndex++) {
			float a3 = srcAcc3.applyAsFlt(src3, sIndex);
			consumer.accept(trg1, tIndex, a3);
		}
		return sEnd - sStart;

	}

	/** ***ITERATION:    TIE_CONSUMER2_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=float a3, type=TIE_SOURCE}, SourcePurpose{arg=float a3, type=TE_GEN_PREDICATE}, SourcePurpose{arg=float a3, type=TE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int tStart, T trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, aFloat> srcAcc3) {
		return tieForEach(sStart, tStart, trg1, src3, (LPredicate<SRC>) srcTest3, (LToFltFunction<SRC>) srcAcc3, this);

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToFltFunction<SRC> srcAcc3, LTieFltConsumer<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			float a3 = srcAcc3.applyAsFlt(src3);
			consumer.accept(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=float a3, type=SA}, SourcePurpose{arg=LTieFltConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		T trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// <editor-fold desc="fluentUse">

	public static <T, R> R inlineAcceptR(R retval, T a1, int a2, float a3, LTieFltConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return retval;
	}

	public static <T> T inlineAccept(T a1, int a2, float a3, LTieFltConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return a1;
	}

	// </editor-fold>

}
