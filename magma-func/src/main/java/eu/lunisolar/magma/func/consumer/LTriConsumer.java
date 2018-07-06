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
public interface LTriConsumer<T1, T2, T3> extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LTriConsumer: void doAccept(T1 a1,T2 a2,T3 a3)";

	// void doAccept(T1 a1,T2 a2,T3 a3) ;
	default void doAccept(T1 a1, T2 a2, T3 a3) {
		// nestingDoAccept(a1,a2,a3);
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T1 a1,T2 a2,T3 a3)
	 */
	void doAcceptX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default LTuple.Void tupleAccept(LTriple<T1, T2, T3> args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, T3 a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, T3 a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T1 a1, T2 a2, T3 a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3> void handlingDoAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, a3, handling);
	}

	static <T1, T2, T3> void tryDoAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		tryDoAccept(a1, a2, a3, func, null);
	}

	static <T1, T2, T3> void tryDoAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2, T3> void tryDoAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2, T3> void tryDoAcceptThen(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, a3, handler);
	}

	default void failSafeDoAccept(T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		try {
			doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2, a3);
		}
	}

	static <T1, T2, T3> void failSafeDoAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2, a3);
		} else {
			func.failSafeDoAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, T3> LTriConsumer<T1, T2, T3> failSafeTriCons(LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void times(int max_i, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiConsumer<T2, T3> lShrink(LBiFunction<T2, T3, T1> left) {
		return (a2, a3) -> doAccept(left.doApply(a2, a3), a2, a3);
	}

	public default LBiConsumer<T2, T3> lShrinkc(T1 a1) {
		return (a2, a3) -> doAccept(a1, a2, a3);
	}

	public static <T2, T3, T1> LBiConsumer<T2, T3> lShrinked(LBiFunction<T2, T3, T1> left, LTriConsumer<T1, T2, T3> func) {
		return func.lShrink(left);
	}

	public static <T2, T3, T1> LBiConsumer<T2, T3> lShrinkedc(T1 a1, LTriConsumer<T1, T2, T3> func) {
		return func.lShrinkc(a1);
	}

	public default LBiConsumer<T1, T2> rShrink(LBiFunction<T1, T2, T3> right) {
		return (a1, a2) -> doAccept(a1, a2, right.doApply(a1, a2));
	}

	public default LBiConsumer<T1, T2> rShrinkc(T3 a3) {
		return (a1, a2) -> doAccept(a1, a2, a3);
	}

	public static <T1, T2, T3> LBiConsumer<T1, T2> rShrinked(LBiFunction<T1, T2, T3> right, LTriConsumer<T1, T2, T3> func) {
		return func.rShrink(right);
	}

	public static <T1, T2, T3> LBiConsumer<T1, T2> rShrinkedc(T3 a3, LTriConsumer<T1, T2, T3> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2, T3> LTriConsumer<T1, T2, T3> uncurryTriCons(LFunction<T1, LFunction<T2, LConsumer<T3>>> func) {
		return (T1 a1, T2 a2, T3 a3) -> func.doApply(a1).doApply(a2).doAccept(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureTriCons(T1 a1, T2 a2, T3 a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> accept3rd(@Nonnull LConsumer<T3> func) {
		return (a1, a2, a3) -> func.doAccept(a3);
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
		LTriConsumer<T1, T2, T3> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTriConsumerSingle<T1, T2, T3> implements LSingle<LTriConsumer<T1, T2, T3>>, LTriConsumer<T1, T2, T3> {
		private LTriConsumer<T1, T2, T3> target = null;

		@Override
		public void doAcceptX(T1 a1, T2 a2, T3 a3) throws Throwable {
			target.doAcceptX(a1, a2, a3);
		}

		@Override
		public LTriConsumer<T1, T2, T3> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T2> LObjObj2Obj1Cons<T1, T3, T2> objObj2Obj1Cons(final @Nonnull LObjObj2Obj1Cons<T1, T3, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T3> LObj1BiObjCons<T2, T1, T3> obj1BiObjCons(final @Nonnull LObj1BiObjCons<T2, T1, T3> lambda) {
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
	static <T3, T2, T1> LBiObjObj0Cons<T3, T2, T1> biObjObj0Cons(final @Nonnull LBiObjObj0Cons<T3, T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, T3> void call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
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
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> triConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	public static <V1, V2, V3, T1, T2, T3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, LTriConsumer<T1, T2, T3> after) {
		return after.triConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriConsumer<T1,T2,T3> together in a order. */
	@Nonnull
	default LTriConsumer<T1, T2, T3> andThen(@Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> after) {
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
	default LTriConsumer<T1, T2, T3> nestingTriCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriConsumer<T1, T2, T3> shovingTriCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObjObj2Obj1Cons<T1, T3, T2> extends LTriConsumer<T1, T2, T3> {

		void doAcceptObjObj2Obj1(T1 a1, T3 a3, T2 a2);

		@Override
		default void doAcceptX(T1 a1, T2 a2, T3 a3) {
			this.doAcceptObjObj2Obj1(a1, a3, a2);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj1BiObjCons<T2, T1, T3> extends LTriConsumer<T1, T2, T3> {

		void doAcceptObj1BiObj(T2 a2, T1 a1, T3 a3);

		@Override
		default void doAcceptX(T1 a1, T2 a2, T3 a3) {
			this.doAcceptObj1BiObj(a2, a1, a3);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj2Obj0Cons<T2, T3, T1> extends LTriConsumer<T1, T2, T3> {

		void doAcceptObj1Obj2Obj0(T2 a2, T3 a3, T1 a1);

		@Override
		default void doAcceptX(T1 a1, T2 a2, T3 a3) {
			this.doAcceptObj1Obj2Obj0(a2, a3, a1);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj0Obj1Cons<T3, T1, T2> extends LTriConsumer<T1, T2, T3> {

		void doAcceptObj2Obj0Obj1(T3 a3, T1 a1, T2 a2);

		@Override
		default void doAcceptX(T1 a1, T2 a2, T3 a3) {
			this.doAcceptObj2Obj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LTriConsumer for method references. */
	@FunctionalInterface
	interface LBiObjObj0Cons<T3, T2, T1> extends LTriConsumer<T1, T2, T3> {

		void doAcceptBiObjObj0(T3 a3, T2 a2, T1 a1);

		@Override
		default void doAcceptX(T1 a1, T2 a2, T3 a3) {
			this.doAcceptBiObjObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTriConsumer) */
	public static <T1, T2, T3> void doNothing(T1 a1, T2 a2, T3 a3) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, C2, C3, T1, T2, T3> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, I1, C2, C3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, C2, I2, C3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, C2, C3, I3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, I1, C2, C3, I3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, C2, I2, C3, I3, T1, T2, T3> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, I3, T1, T2, T3> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3,
			LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <T1, C2, C3, T2, T3> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=IA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <T1, C2, I2, C3, T2, T3> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc2.doTest(iterator2) && i < size) {
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <T1, C2, C3, I3, T2, T3> T1 targetedIterate(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=SA},
	// SourcePurpose{arg=LTriConsumer<? super T1,? super T2,? super T3> consumer, type=CONST}]
	public static <T1, C2, I2, C3, I3, T2, T3> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		while (testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	public static <C, T, T1, T2> T pairForEach(T target, IndexedRead<C, a<? extends Object>> ia, C s, LTriConsumer<T, T1, T2> consumer) {
		int size = ia.size(s);

		if (size % 2 != 0) {
			throw new IllegalArgumentException("Size of container is not multipplication of 2.");
		}

		LOiFunction<C, T1> g1 = (LOiFunction) ia.getter();
		LOiFunction<C, T2> g2 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size;) {
			T1 v1 = g1.doApply(s, i++);
			T2 v2 = g2.doApply(s, i++);
			consumer.doAccept(target, v1, v2);
		}
		return target;
	}

	public static <C, I, T, T1, T2> T pairIterate(T target, SequentialRead<C, I, a<? extends Object>> sa, C s, LTriConsumer<T, T1, T2> consumer) {

		LFunction<C, I> adapter = sa.adapter();
		I a = adapter.doApply(s);

		LFunction<I, T1> g1 = (LFunction) sa.getter();
		LFunction<I, T2> g2 = (LFunction) sa.getter();
		LPredicate<I> tester = (LPredicate) sa.tester();
		int i = 0;
		while (tester.doTest(a)) {
			T1 v1 = g1.doApply(a);
			T2 v2 = g2.doApply(a);
			consumer.doAccept(target, v1, v2);
			i += 2;
		}

		return target;
	}

}
