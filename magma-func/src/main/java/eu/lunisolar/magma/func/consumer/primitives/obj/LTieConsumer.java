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
 * Non-throwing functional interface (lambda) LTieConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,int a2,T2 a3
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, index, element) -> List::set    or  (list, index, element) -> Array::set  
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieConsumer<T1, T2> extends MetaConsumer, MetaInterface.NonThrowing, TieConsumer<T1, a<T2>> {

	String DESCRIPTION = "LTieConsumer: void doAccept(T1 a1,int a2,T2 a3)";

	// void doAccept(T1 a1,int a2,T2 a3) ;
	default void doAccept(T1 a1, int a2, T2 a3) {
		// nestingDoAccept(a1,a2,a3);
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T1 a1,int a2,T2 a3)
	 */
	void doAcceptX(T1 a1, int a2, T2 a3) throws Throwable;

	default LTuple.Void tupleAccept(LObjIntObjTriple<T1, T2> args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T1 a1, int a2, T2 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T1 a1, int a2, T2 a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T1 a1, int a2, T2 a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T1 a1, int a2, T2 a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, int a2, T2 a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, int a2, T2 a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> void handlingDoAccept(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, a3, handling);
	}

	static <T1, T2> void tryDoAccept(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func) {
		tryDoAccept(a1, a2, a3, func, null);
	}

	static <T1, T2> void tryDoAccept(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2> void tryDoAccept(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2> void tryDoAcceptThen(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, a3, handler);
	}

	default void failSafeDoAccept(T1 a1, int a2, T2 a3, @Nonnull LTieConsumer<T1, T2> failSafe) {
		try {
			doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2, a3);
		}
	}

	static <T1, T2> void failSafeDoAccept(T1 a1, int a2, T2 a3, LTieConsumer<T1, T2> func, @Nonnull LTieConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2, a3);
		} else {
			func.failSafeDoAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LTieConsumer<T1, T2> failSafeTieCons(LTieConsumer<T1, T2> func, @Nonnull LTieConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieConsumer.DESCRIPTION;
	}

	public default LTieFunction<T1, T2> toTieFunction() {
		return (t, i, e) -> {
			this.doAccept(t, i, e);
			return 1;
		};
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a2, int max_a2, T1 a1, T2 a3, LTieConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_a2, int max_a2, T1 a1, T2 a3, LTieConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_a2, T1 a1, T2 a3, LTieConsumer<T1, T2> func) {
		fromTill(0, max_a2, a1, a3, func);
	}

	/**  */
	public static <T1, T2> LTieConsumer<T1, T2> uncurryTieCons(LFunction<T1, LIntFunction<LConsumer<T2>>> func) {
		return (T1 a1, int a2, T2 a3) -> func.doApply(a1).doApply(a2).doAccept(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureTieCons(T1 a1, int a2, T2 a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> accept3rd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> tieCons(final @Nonnull LTieConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> recursive(final @Nonnull LFunction<LTieConsumer<T1, T2>, LTieConsumer<T1, T2>> selfLambda) {
		final LTieConsumerSingle<T1, T2> single = new LTieConsumerSingle();
		LTieConsumer<T1, T2> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTieConsumerSingle<T1, T2> implements LSingle<LTieConsumer<T1, T2>>, LTieConsumer<T1, T2> {
		private LTieConsumer<T1, T2> target = null;

		@Override
		public void doAcceptX(T1 a1, int a2, T2 a3) throws Throwable {
			target.doAcceptX(a1, a2, a3);
		}

		@Override
		public LTieConsumer<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> tieConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> tieConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObjObj2IntCons<T1, T2> objObj2IntCons(final @Nonnull LObjObj2IntCons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LIntBiObjCons<T1, T2> intBiObjCons(final @Nonnull LIntBiObjCons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LIntObj2Obj0Cons<T2, T1> intObj2Obj0Cons(final @Nonnull LIntObj2Obj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj2Obj0IntCons<T2, T1> obj2Obj0IntCons(final @Nonnull LObj2Obj0IntCons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj2IntObj0Cons<T2, T1> obj2IntObj0Cons(final @Nonnull LObj2IntObj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> void call(T1 a1, int a2, T2 a3, final @Nonnull LTieConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> safe() {
		return LTieConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LTieConsumer<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LTieConsumer<T1, T2> safe(final @Nullable LTieConsumer<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LTieConsumer<T1, T2>> safeSupplier(final @Nullable LSupplier<LTieConsumer<T1, T2>> supplier) {
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
	default <V1, V3> LTieConsumer<V1, V3> tieConsComposeInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApply(v3));
	}

	public static <V1, V3, T1, T2> LTieConsumer<V1, V3> composedInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3,
			LTieConsumer<T1, T2> after) {
		return after.tieConsComposeInt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> tieConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApply(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3,
			LTieConsumer<T1, T2> after) {
		return after.tieConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTieConsumer<T1,T2> together in a order. */
	@Nonnull
	default LTieConsumer<T1, T2> andThen(@Nonnull LTieConsumer<? super T1, ? super T2> after) {
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
	default LTieConsumer<T1, T2> nestingTieCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTieConsumer<T1, T2> shovingTieCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieConsumer for method references. */
	@FunctionalInterface
	interface LObjObj2IntCons<T1, T2> extends LTieConsumer<T1, T2> {

		void doAcceptObjObj2Int(T1 a1, T2 a3, int a2);

		@Override
		default void doAcceptX(T1 a1, int a2, T2 a3) {
			this.doAcceptObjObj2Int(a1, a3, a2);
		}
	}

	/** Permutation of LTieConsumer for method references. */
	@FunctionalInterface
	interface LIntBiObjCons<T1, T2> extends LTieConsumer<T1, T2> {

		void doAcceptIntBiObj(int a2, T1 a1, T2 a3);

		@Override
		default void doAcceptX(T1 a1, int a2, T2 a3) {
			this.doAcceptIntBiObj(a2, a1, a3);
		}
	}

	/** Permutation of LTieConsumer for method references. */
	@FunctionalInterface
	interface LIntObj2Obj0Cons<T2, T1> extends LTieConsumer<T1, T2> {

		void doAcceptIntObj2Obj0(int a2, T2 a3, T1 a1);

		@Override
		default void doAcceptX(T1 a1, int a2, T2 a3) {
			this.doAcceptIntObj2Obj0(a2, a3, a1);
		}
	}

	/** Permutation of LTieConsumer for method references. */
	@FunctionalInterface
	interface LObj2Obj0IntCons<T2, T1> extends LTieConsumer<T1, T2> {

		void doAcceptObj2Obj0Int(T2 a3, T1 a1, int a2);

		@Override
		default void doAcceptX(T1 a1, int a2, T2 a3) {
			this.doAcceptObj2Obj0Int(a3, a1, a2);
		}
	}

	/** Permutation of LTieConsumer for method references. */
	@FunctionalInterface
	interface LObj2IntObj0Cons<T2, T1> extends LTieConsumer<T1, T2> {

		void doAcceptObj2IntObj0(T2 a3, int a2, T1 a1);

		@Override
		default void doAcceptX(T1 a1, int a2, T2 a3) {
			this.doAcceptObj2IntObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieConsumer) */
	public static <T1, T2> void doNothing(T1 a1, int a2, T2 a3) {
		// NOSONAR
	}

	/** Does nothing (LTieConsumer.LObjObj2IntCons) */
	public static <T1, T2> void doNothing(T1 a1, T2 a3, int a2) {
		// NOSONAR
	}

	/** Does nothing (LTieConsumer.LIntBiObjCons) */
	public static <T1, T2> void doNothing(int a2, T1 a1, T2 a3) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, C3, T1, T2> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, C3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, I2, C3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, C3, I3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, C3, I3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, C2, I2, C3, I3, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, I3, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, C3, T1, T2> int indexedForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			T1 a1 = oiFunc1.doApply(source1, a2);
			T2 a3 = oiFunc3.doApply(source3, a2);
			consumer.doAccept(a1, a2, a3);
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C3, T1, T2> int indexedIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1) && a2 < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a3 = oiFunc3.doApply(source3, a2);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, C3, I3, T1, T2> int indexedIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int a2 = 0;
		while (a2 < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, a2);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C3, I3, T1, T2> int indexedIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, C3, T2> T1 targetedForEach(T1 a1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=IA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, I2, C3, T2> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc2.doTest(iterator2) && i < size) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, C3, I3, T2> T1 targetedIterate(T1 a1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=T2 a3, type=SA},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, C2, I2, C3, I3, T2> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.getter();
		while (testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			T2 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// TIE_CONSUMER_GEN: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_GEN_SUPPLIER}]
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, OiFunction<SRC, a<T2>> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiFunction<SRC, T2>) srcAcc3, this);

	}

	// TARGETED_INDEXED_FOR_EACH: FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieConsumer<? super T1,?
	// super T2> consumer, type=CONST}]
	public static <T1, C3, T2> T1 tiForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_FOR_EACH_NEW: FOR, [SourcePurpose{arg=T1 trg1, type=SIZE_FACTORY}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieConsumer<?
	// super T1,? super T2> consumer, type=CONST}]
	public static <T1, C3, T2> T1 ntiForEach(LIntFunction<T1> trgFactory1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		T1 trg1 = trgFactory1.doApply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	// TIE_CONSUMER_SHORT: FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieConsumer<? super T1,? super
	// T2> consumer, type=CONST}]
	public static <T1, C3, T2> int tieForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	// TIE_CONSUMER: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_SUPPLIER},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, SRC, T2> int tieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, LOiFunction<SRC, T2> srcAcc3, LTieConsumer<? super T1, ? super T2> consumer) {
		for (int sIndex = sStart, tIndex = tStart; sIndex < sEnd; sIndex++, tIndex++) {
			T2 a3 = srcAcc3.doApply(src3, sIndex);
			consumer.doAccept(trg1, tIndex, a3);
		}
		return sEnd - sStart;

	}

	// TIE_CONSUMER2: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST},
	// SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TE_PREDICATE}, SourcePurpose{arg=T2 a3, type=TE_SUPPLIER},
	// SourcePurpose{arg=LTieConsumer<? super T1,? super T2> consumer, type=CONST}]
	public static <T1, SRC, T2> int tieForEach(int sStart, int tStart, T1 trg1, SRC src3, LPredicate<SRC> srcTest3, LFunction<SRC, T2> srcAcc3, LTieConsumer<? super T1, ? super T2> consumer) {
		int tIndex = tStart;
		for (; srcTest3.doTest(src3); tIndex++) {
			T2 a3 = srcAcc3.doApply(src3);
			consumer.doAccept(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	// TIE_CONSUMER2_SHORT: WHILE, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieConsumer<? super T1,? super
	// T2> consumer, type=CONST}]
	public static <T1, C3, I3, T2> int tieIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.doApply(source3), sa3.tester(), sa3.getter(), consumer);
	}

	// TARGETED_INDEXED_ITERATE: WHILE, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieConsumer<? super T1,?
	// super T2> consumer, type=CONST}]
	public static <T1, C3, I3, T2> T1 tiIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_ITERATE_NEW: WHILE, [SourcePurpose{arg=T1 trg1, type=SUPPLIER}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieConsumer<?
	// super T1,? super T2> consumer, type=CONST}]
	public static <T1, C3, I3, T2> T1 ntiIterate(LSupplier<T1> source1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		T1 trg1 = source1.doGet();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

}
