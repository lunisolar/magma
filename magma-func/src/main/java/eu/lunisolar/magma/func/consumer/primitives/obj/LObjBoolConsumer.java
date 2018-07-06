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
 * Non-throwing functional interface (lambda) LObjBoolConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T a1,boolean a2
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, element) -> List::add
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TeConsumer<T, aBool> {

	String DESCRIPTION = "LObjBoolConsumer: void doAccept(T a1,boolean a2)";

	// void doAccept(T a1,boolean a2) ;
	default void doAccept(T a1, boolean a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T a1,boolean a2)
	 */
	void doAcceptX(T a1, boolean a2) throws Throwable;

	default LTuple.Void tupleAccept(LObjBoolPair<T> args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T a1, boolean a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T a1, boolean a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T a1, boolean a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T a1, boolean a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T a1, boolean a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T a1, boolean a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void handlingDoAccept(T a1, boolean a2, LObjBoolConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static <T> void tryDoAccept(T a1, boolean a2, LObjBoolConsumer<T> func) {
		tryDoAccept(a1, a2, func, null);
	}

	static <T> void tryDoAccept(T a1, boolean a2, LObjBoolConsumer<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T> void tryDoAccept(T a1, boolean a2, LObjBoolConsumer<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static <T> void tryDoAcceptThen(T a1, boolean a2, LObjBoolConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(T a1, boolean a2, @Nonnull LObjBoolConsumer<T> failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static <T> void failSafeDoAccept(T a1, boolean a2, LObjBoolConsumer<T> func, @Nonnull LObjBoolConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static <T> LObjBoolConsumer<T> failSafeObjBoolCons(LObjBoolConsumer<T> func, @Nonnull LObjBoolConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBoolConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, boolean a2, LObjBoolConsumer<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a1, boolean a2, LObjBoolConsumer<T> func) {
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
	public static <T> void times(int max_i, T a1, boolean a2, LObjBoolConsumer<T> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LBoolConsumer lShrink(LBoolFunction<T> left) {
		return a2 -> doAccept(left.doApply(a2), a2);
	}

	public default LBoolConsumer lShrinkc(T a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static <T> LBoolConsumer lShrinked(LBoolFunction<T> left, LObjBoolConsumer<T> func) {
		return func.lShrink(left);
	}

	public static <T> LBoolConsumer lShrinkedc(T a1, LObjBoolConsumer<T> func) {
		return func.lShrinkc(a1);
	}

	public default LConsumer<T> rShrink(LPredicate<T> right) {
		return a1 -> doAccept(a1, right.doTest(a1));
	}

	public default LConsumer<T> rShrinkc(boolean a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static <T> LConsumer<T> rShrinked(LPredicate<T> right, LObjBoolConsumer<T> func) {
		return func.rShrink(right);
	}

	public static <T> LConsumer<T> rShrinkedc(boolean a2, LObjBoolConsumer<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LObjBoolConsumer<T> uncurryObjBoolCons(LFunction<T, LBoolConsumer> func) {
		return (T a1, boolean a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureObjBoolCons(T a1, boolean a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjBoolConsumer<T> accept1st(@Nonnull LConsumer<T> func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjBoolConsumer<T> accept2nd(@Nonnull LBoolConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBoolConsumer<T> objBoolCons(final @Nonnull LObjBoolConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjBoolConsumer<T> recursive(final @Nonnull LFunction<LObjBoolConsumer<T>, LObjBoolConsumer<T>> selfLambda) {
		final LObjBoolConsumerSingle<T> single = new LObjBoolConsumerSingle();
		LObjBoolConsumer<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LObjBoolConsumerSingle<T> implements LSingle<LObjBoolConsumer<T>>, LObjBoolConsumer<T> {
		private LObjBoolConsumer<T> target = null;

		@Override
		public void doAcceptX(T a1, boolean a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LObjBoolConsumer<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjBoolConsumer<T> objBoolConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LObjBoolConsumer<T> objBoolConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBoolObjCons<T> boolObjCons(final @Nonnull LBoolObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> void call(T a1, boolean a2, final @Nonnull LObjBoolConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T> LObjBoolConsumer<T> safe() {
		return LObjBoolConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjBoolConsumer<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjBoolConsumer<T> safe(final @Nullable LObjBoolConsumer<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjBoolConsumer<T>> safeSupplier(final @Nullable LSupplier<LObjBoolConsumer<T>> supplier) {
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
	default <V1> LObjBoolConsumer<V1> objBoolConsComposeBool(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLogicalOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApply(v1), before2.doApply(v2));
	}

	public static <V1, T> LObjBoolConsumer<V1> composedBool(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLogicalOperator before2, LObjBoolConsumer<T> after) {
		return after.objBoolConsComposeBool(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> objBoolConsCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApply(v1), before2.doTest(v2));
	}

	public static <V1, V2, T> LBiConsumer<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LPredicate<? super V2> before2, LObjBoolConsumer<T> after) {
		return after.objBoolConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LObjBoolConsumer<T> together in a order. */
	@Nonnull
	default LObjBoolConsumer<T> andThen(@Nonnull LObjBoolConsumer<? super T> after) {
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
	default LObjBoolConsumer<T> nestingObjBoolCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBoolConsumer<T> shovingObjBoolCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjBoolConsumer for method references. */
	@FunctionalInterface
	interface LBoolObjCons<T> extends LObjBoolConsumer<T> {

		void doAcceptBoolObj(boolean a2, T a1);

		@Override
		default void doAcceptX(T a1, boolean a2) {
			this.doAcceptBoolObj(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LObjBoolConsumer) */
	public static <T> void doNothing(T a1, boolean a2) {
		// NOSONAR
	}

	/** Does nothing (LObjBoolConsumer.LBoolObjCons) */
	public static <T> void doNothing(boolean a2, T a1) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LObjBoolConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, C2, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LObjBoolConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, I1, C2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LObjBoolConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, C2, I2, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LObjBoolConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, I1, C2, I2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T a1 = nextFunc1.doApply(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LObjBoolConsumer<? super T>
	// consumer, type=CONST}]
	public static <T, C2> T targetedForEach(T a1, IndexedRead<C2, aBool> ia2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a2 = oiFunc2.doTest(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LObjBoolConsumer<? super T>
	// consumer, type=CONST}]
	public static <T, C2, I2> T targetedIterate(T a1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LObjBoolConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		while (testFunc2.doTest(iterator2)) {
			boolean a2 = nextFunc2.doTest(iterator2);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// TE_CONSUMER_GEN_IA: FOR, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=boolean a2, type=IA}]
	default <C2> T genericForEach(T a1, IndexedRead<C2, aBool> ia2, C2 source2) {
		return targetedForEach(a1, ia2, source2, (LObjBoolConsumer<T>) this);
	}

	// TE_CONSUMER_GEN_SA: WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=boolean a2, type=SA}]
	default <C2, I2> T genericIterate(T a1, SequentialRead<C2, I2, aBool> sa2, C2 source2) {
		return targetedIterate(a1, sa2, source2, (LObjBoolConsumer<T>) this);
	}

}
