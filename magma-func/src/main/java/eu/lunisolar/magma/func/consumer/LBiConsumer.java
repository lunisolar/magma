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
 * Non-throwing functional interface (lambda) LBiConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, element) -> List::add
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiConsumer<T1, T2> extends BiConsumer<T1, T2>, MetaConsumer, MetaInterface.NonThrowing, TeConsumer<T1, a<T2>> {

	String DESCRIPTION = "LBiConsumer: void doAccept(T1 a1,T2 a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBiConsumer interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void accept(T1 a1, T2 a2) {
		this.doAccept(a1, a2);
	}

	// void doAccept(T1 a1,T2 a2) ;
	default void doAccept(T1 a1, T2 a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T1 a1,T2 a2)
	 */
	void doAcceptX(T1 a1, T2 a2) throws Throwable;

	default LTuple.Void tupleAccept(LPair<T1, T2> args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T1 a1, T2 a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T1 a1, T2 a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> void handlingDoAccept(T1 a1, T2 a2, LBiConsumer<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, LBiConsumer<T1, T2> func) {
		tryDoAccept(a1, a2, func, null);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, LBiConsumer<T1, T2> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2> void tryDoAccept(T1 a1, T2 a2, LBiConsumer<T1, T2> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static <T1, T2> void tryDoAcceptThen(T1 a1, T2 a2, LBiConsumer<T1, T2> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(T1 a1, T2 a2, @Nonnull LBiConsumer<T1, T2> failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static <T1, T2> void failSafeDoAccept(T1 a1, T2 a2, LBiConsumer<T1, T2> func, @Nonnull LBiConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static <T1, T2> LBiConsumer<T1, T2> failSafeBiCons(LBiConsumer<T1, T2> func, @Nonnull LBiConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, LBiConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doAccept(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doAccept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, LBiConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doAccept(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doAccept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, LBiConsumer<T1, T2> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LConsumer<T2> lShrink(LFunction<T2, T1> left) {
		return a2 -> doAccept(left.doApply(a2), a2);
	}

	public default LConsumer<T2> lShrinkc(T1 a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static <T2, T1> LConsumer<T2> lShrinked(LFunction<T2, T1> left, LBiConsumer<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LConsumer<T2> lShrinkedc(T1 a1, LBiConsumer<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LConsumer<T1> rShrink(LFunction<T1, T2> right) {
		return a1 -> doAccept(a1, right.doApply(a1));
	}

	public default LConsumer<T1> rShrinkc(T2 a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static <T1, T2> LConsumer<T1> rShrinked(LFunction<T1, T2> right, LBiConsumer<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LConsumer<T1> rShrinkedc(T2 a2, LBiConsumer<T1, T2> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T1, T2> LBiConsumer<T1, T2> uncurryBiCons(LFunction<T1, LConsumer<T2>> func) {
		return (T1 a1, T2 a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiCons(T1 a1, T2 a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> biCons(final @Nonnull LBiConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> recursive(final @Nonnull LFunction<LBiConsumer<T1, T2>, LBiConsumer<T1, T2>> selfLambda) {
		final LBiConsumerSingle<T1, T2> single = new LBiConsumerSingle();
		LBiConsumer<T1, T2> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiConsumerSingle<T1, T2> implements LSingle<LBiConsumer<T1, T2>>, LBiConsumer<T1, T2> {
		private LBiConsumer<T1, T2> target = null;

		@Override
		public void doAcceptX(T1 a1, T2 a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LBiConsumer<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> biConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> biConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Obj0Cons<T2, T1> obj1Obj0Cons(final @Nonnull LObj1Obj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> void call(T1 a1, T2 a2, final @Nonnull LBiConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> wrap(final BiConsumer<T1, T2> other) {
		return other::accept;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> safe() {
		return LBiConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiConsumer<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiConsumer<T1, T2> safe(final @Nullable LBiConsumer<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiConsumer<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiConsumer<T1, T2>> supplier) {
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
	default <V1, V2> LBiConsumer<V1, V2> biConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApply(v1), before2.doApply(v2));
	}

	public static <V1, V2, T1, T2> LBiConsumer<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LBiConsumer<T1, T2> after) {
		return after.biConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiConsumer<T1,T2> together in a order. */
	@Nonnull
	default LBiConsumer<T1, T2> andThen(@Nonnull LBiConsumer<? super T1, ? super T2> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			this.doAccept(a1, a2);
			after.doAccept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiConsumer<T1, T2> nestingBiCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiConsumer<T1, T2> shovingBiCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj0Cons<T2, T1> extends LBiConsumer<T1, T2> {

		void doAcceptObj1Obj0(T2 a2, T1 a1);

		@Override
		default void doAcceptX(T1 a1, T2 a2) {
			this.doAcceptObj1Obj0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiConsumer) */
	public static <T1, T2> void doNothing(T1 a1, T2 a2) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LBiConsumer<? super T1,? super T2> consumer,
	// type=CONST}]
	public static <C1, C2, T1, T2> int forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LBiConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C2, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LBiConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, C2, I2, T1, T2> int iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LBiConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <C1, I1, C2, I2, T1, T2> int iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LBiConsumer<? super T1,? super T2>
	// consumer, type=CONST}]
	public static <T1, C2, T2> T1 targetedForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LBiConsumer<? super T1,? super
	// T2> consumer, type=CONST}]
	public static <T1, C2, I2, T2> T1 targetedIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		while (testFunc2.doTest(iterator2)) {
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// TE_CONSUMER_GEN_IA: FOR, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=IA}]
	default <C2> T1 genericForEach(T1 a1, IndexedRead<C2, a<T2>> ia2, C2 source2) {
		return targetedForEach(a1, ia2, source2, (LBiConsumer<T1, T2>) this);
	}

	// TE_CONSUMER_GEN_SA: WHILE, [SourcePurpose{arg=T1 a1, type=CONST}, SourcePurpose{arg=T2 a2, type=SA}]
	default <C2, I2> T1 genericIterate(T1 a1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2) {
		return targetedIterate(a1, sa2, source2, (LBiConsumer<T1, T2>) this);
	}

	public static <C, T1, T2> int pairForEach(IndexedRead<C, a<? extends Object>> ia, C s, LBiConsumer<T1, T2> consumer) {
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
			consumer.doAccept(v1, v2);
		}
		return i / 2;
	}

	public static <C, I, T1, T2> int pairIterate(SequentialRead<C, I, a<? extends Object>> sa, C s, LBiConsumer<T1, T2> consumer) {

		LFunction<C, I> adapter = sa.adapter();
		I a = adapter.doApply(s);

		LFunction<I, T1> g1 = (LFunction) sa.getter();
		LFunction<I, T2> g2 = (LFunction) sa.getter();
		LPredicate<I> tester = (LPredicate) sa.tester();
		int i = 0;
		while (tester.doTest(a)) {
			T1 v1 = g1.doApply(a);
			T2 v2 = g2.doApply(a);
			consumer.doAccept(v1, v2);
			i += 2;
		}

		return i / 2;
	}

}
