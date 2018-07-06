/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LBiObjIntConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,int a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntConsumer<T1, T2> extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBiObjIntConsumer: void doAccept(T1 a1,T2 a2,int a3)";

	// void doAccept(T1 a1,T2 a2,int a3) ;
	default void doAccept(T1 a1, T2 a2, int a3) {
		// nestingDoAccept(a1,a2,a3);
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T1 a1,T2 a2,int a3)
	 */
	void doAcceptX(T1 a1, T2 a2, int a3) throws Throwable;

	default LTuple.Void tupleAccept(LBiObjIntTriple<T1, T2> args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T1 a1, T2 a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, int a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, int a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T1 a1, T2 a2, int a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2, int a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2, int a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> void handlingDoAccept(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, a3, handling);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func) {
		tryDoAccept(a1, a2, a3, func, null);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2> void tryDoAcceptThen(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, a3, handler);
	}

	default void failSafeDoAccept(T1 a1, T2 a2, int a3, @Nonnull LBiObjIntConsumer<T1, T2> failSafe) {
		try {
			doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2, a3);
		}
	}

	static <T1, T2> void failSafeDoAccept(T1 a1, T2 a2, int a3, LBiObjIntConsumer<T1, T2> func, @Nonnull LBiObjIntConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2, a3);
		} else {
			func.failSafeDoAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LBiObjIntConsumer<T1, T2> failSafeBiObjIntCons(LBiObjIntConsumer<T1, T2> func, @Nonnull LBiObjIntConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a3, int max_a3, T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= min_a3) {
			for (int a3 = min_a3; a3 <= max_a3; a3++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 >= max_a3; a3--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_a3, int max_a3, T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= min_a3) {
			for (int a3 = min_a3; a3 < max_a3; a3++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 > max_a3; a3--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_a3, T1 a1, T2 a2, LBiObjIntConsumer<T1, T2> func) {
		fromTill(0, max_a3, a1, a2, func);
	}

	public default LObjIntConsumer<T2> lShrink(LOiFunction<T2, T1> left) {
		return (a2, a3) -> doAccept(left.doApply(a2, a3), a2, a3);
	}

	public default LObjIntConsumer<T2> lShrinkc(T1 a1) {
		return (a2, a3) -> doAccept(a1, a2, a3);
	}

	public static <T2, T1> LObjIntConsumer<T2> lShrinked(LOiFunction<T2, T1> left, LBiObjIntConsumer<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LObjIntConsumer<T2> lShrinkedc(T1 a1, LBiObjIntConsumer<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LBiConsumer<T1, T2> rShrink(LToIntBiFunction<T1, T2> right) {
		return (a1, a2) -> doAccept(a1, a2, right.doApplyAsInt(a1, a2));
	}

	public default LBiConsumer<T1, T2> rShrinkc(int a3) {
		return (a1, a2) -> doAccept(a1, a2, a3);
	}

	public static <T1, T2> LBiConsumer<T1, T2> rShrinked(LToIntBiFunction<T1, T2> right, LBiObjIntConsumer<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LBiConsumer<T1, T2> rShrinkedc(int a3, LBiObjIntConsumer<T1, T2> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2> LBiObjIntConsumer<T1, T2> uncurryBiObjIntCons(LFunction<T1, LFunction<T2, LIntConsumer>> func) {
		return (T1 a1, T2 a2, int a3) -> func.doApply(a1).doApply(a2).doAccept(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiObjIntCons(T1 a1, T2 a2, int a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> accept3rd(@Nonnull LIntConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> biObjIntCons(final @Nonnull LBiObjIntConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> recursive(final @Nonnull LFunction<LBiObjIntConsumer<T1, T2>, LBiObjIntConsumer<T1, T2>> selfLambda) {
		final LBiObjIntConsumerSingle<T1, T2> single = new LBiObjIntConsumerSingle();
		LBiObjIntConsumer<T1, T2> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiObjIntConsumerSingle<T1, T2> implements LSingle<LBiObjIntConsumer<T1, T2>>, LBiObjIntConsumer<T1, T2> {
		private LBiObjIntConsumer<T1, T2> target = null;

		@Override
		public void doAcceptX(T1 a1, T2 a2, int a3) throws Throwable {
			target.doAcceptX(a1, a2, a3);
		}

		@Override
		public LBiObjIntConsumer<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> biObjIntConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> biObjIntConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObjIntObj1Cons<T1, T2> objIntObj1Cons(final @Nonnull LObjIntObj1Cons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Obj0IntCons<T2, T1> obj1Obj0IntCons(final @Nonnull LObj1Obj0IntCons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1IntObj0Cons<T2, T1> obj1IntObj0Cons(final @Nonnull LObj1IntObj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LIntObj0Obj1Cons<T1, T2> intObj0Obj1Cons(final @Nonnull LIntObj0Obj1Cons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LIntObjObj0Cons<T2, T1> intObjObj0Cons(final @Nonnull LIntObjObj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> void call(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> safe() {
		return LBiObjIntConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjIntConsumer<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiObjIntConsumer<T1, T2> safe(final @Nullable LBiObjIntConsumer<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjIntConsumer<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiObjIntConsumer<T1, T2>> supplier) {
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
	default <V1, V2> LBiObjIntConsumer<V1, V2> biObjIntConsComposeInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, T1, T2> LBiObjIntConsumer<V1, V2> composedInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3,
			LBiObjIntConsumer<T1, T2> after) {
		return after.biObjIntConsComposeInt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> biObjIntConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3,
			LBiObjIntConsumer<T1, T2> after) {
		return after.biObjIntConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiObjIntConsumer<T1,T2> together in a order. */
	@Nonnull
	default LBiObjIntConsumer<T1, T2> andThen(@Nonnull LBiObjIntConsumer<? super T1, ? super T2> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.doAccept(a1, a2, a3);
			after.doAccept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjIntConsumer<T1, T2> nestingBiObjIntCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntConsumer<T1, T2> shovingBiObjIntCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjIntConsumer for method references. */
	@FunctionalInterface
	interface LObjIntObj1Cons<T1, T2> extends LBiObjIntConsumer<T1, T2> {

		void doAcceptObjIntObj1(T1 a1, int a3, T2 a2);

		@Override
		default void doAcceptX(T1 a1, T2 a2, int a3) {
			this.doAcceptObjIntObj1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjIntConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj0IntCons<T2, T1> extends LBiObjIntConsumer<T1, T2> {

		void doAcceptObj1Obj0Int(T2 a2, T1 a1, int a3);

		@Override
		default void doAcceptX(T1 a1, T2 a2, int a3) {
			this.doAcceptObj1Obj0Int(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjIntConsumer for method references. */
	@FunctionalInterface
	interface LObj1IntObj0Cons<T2, T1> extends LBiObjIntConsumer<T1, T2> {

		void doAcceptObj1IntObj0(T2 a2, int a3, T1 a1);

		@Override
		default void doAcceptX(T1 a1, T2 a2, int a3) {
			this.doAcceptObj1IntObj0(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjIntConsumer for method references. */
	@FunctionalInterface
	interface LIntObj0Obj1Cons<T1, T2> extends LBiObjIntConsumer<T1, T2> {

		void doAcceptIntObj0Obj1(int a3, T1 a1, T2 a2);

		@Override
		default void doAcceptX(T1 a1, T2 a2, int a3) {
			this.doAcceptIntObj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjIntConsumer for method references. */
	@FunctionalInterface
	interface LIntObjObj0Cons<T2, T1> extends LBiObjIntConsumer<T1, T2> {

		void doAcceptIntObjObj0(int a3, T2 a2, T1 a1);

		@Override
		default void doAcceptX(T1 a1, T2 a2, int a3) {
			this.doAcceptIntObjObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiObjIntConsumer) */
	public static <T1, T2> void doNothing(T1 a1, T2 a2, int a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer.LObjIntObj1Cons) */
	public static <T1, T2> void doNothing(T1 a1, int a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer.LIntObj0Obj1Cons) */
	public static <T1, T2> void doNothing(int a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, C3, T1, T2> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, C3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, I2, C3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, C3, I3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, C3, I3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, I2, C3, I3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, I3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, C2, T1, T2> int indexedForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int a3 = 0;
		for (; a3 < size; a3++) {
			T1 a1 = oiFunc1.doApply(source1, a3);
			T2 a2 = oiFunc2.doApply(source2, a3);
			consumer.doAccept(a1, a2, a3);
		}
		return a3;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C2, T1, T2> int indexedIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int a3 = 0;
		while (testFunc1.doTest(iterator1) && a3 < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, a3);
			consumer.doAccept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, C2, I2, T1, T2> int indexedIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int a3 = 0;
		while (a3 < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, a3);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C2, I2, T1, T2> int indexedIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int a3 = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, C3, T2> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, I2, C3, T2> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc2.doTest(iterator2) && i < size) {
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, C3, I3, T2> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, I2, C3, I3, T2> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		while (testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

}
