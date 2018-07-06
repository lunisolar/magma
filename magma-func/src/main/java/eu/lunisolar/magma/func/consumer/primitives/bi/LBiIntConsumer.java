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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
 * Non-throwing functional interface (lambda) LBiIntConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): int a1,int a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiIntConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBiIntConsumer: void doAccept(int a1,int a2)";

	// void doAccept(int a1,int a2) ;
	default void doAccept(int a1, int a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(int a1,int a2)
	 */
	void doAcceptX(int a1, int a2) throws Throwable;

	default LTuple.Void tupleAccept(LIntPair args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(int a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(int a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(int a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(int a1, int a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(int a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(int a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(int a1, int a2, LBiIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static void tryDoAccept(int a1, int a2, LBiIntConsumer func) {
		tryDoAccept(a1, a2, func, null);
	}

	static void tryDoAccept(int a1, int a2, LBiIntConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(int a1, int a2, LBiIntConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static void tryDoAcceptThen(int a1, int a2, LBiIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(int a1, int a2, @Nonnull LBiIntConsumer failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static void failSafeDoAccept(int a1, int a2, LBiIntConsumer func, @Nonnull LBiIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static LBiIntConsumer failSafeBiIntCons(LBiIntConsumer func, @Nonnull LBiIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, int a1, int a2, LBiIntConsumer func) {
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
	public static void fromTill(int min_i, int max_i, int a1, int a2, LBiIntConsumer func) {
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
	public static void times(int max_i, int a1, int a2, LBiIntConsumer func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntConsumer lShrink(LIntUnaryOperator left) {
		return a2 -> doAccept(left.doApplyAsInt(a2), a2);
	}

	public default LIntConsumer lShrinkc(int a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static LIntConsumer lShrinked(LIntUnaryOperator left, LBiIntConsumer func) {
		return func.lShrink(left);
	}

	public static LIntConsumer lShrinkedc(int a1, LBiIntConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LIntConsumer rShrink(LIntUnaryOperator right) {
		return a1 -> doAccept(a1, right.doApplyAsInt(a1));
	}

	public default LIntConsumer rShrinkc(int a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static LIntConsumer rShrinked(LIntUnaryOperator right, LBiIntConsumer func) {
		return func.rShrink(right);
	}

	public static LIntConsumer rShrinkedc(int a2, LBiIntConsumer func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiIntConsumer uncurryBiIntCons(LIntFunction<LIntConsumer> func) {
		return (int a1, int a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiIntCons(int a1, int a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiIntConsumer accept1st(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiIntConsumer accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiIntConsumer biIntCons(final @Nonnull LBiIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiIntConsumer recursive(final @Nonnull LFunction<LBiIntConsumer, LBiIntConsumer> selfLambda) {
		final LBiIntConsumerSingle single = new LBiIntConsumerSingle();
		LBiIntConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiIntConsumerSingle implements LSingle<LBiIntConsumer>, LBiIntConsumer {
		private LBiIntConsumer target = null;

		@Override
		public void doAcceptX(int a1, int a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LBiIntConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LBiIntConsumer biIntConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiIntConsumer biIntConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LInt1Int0Cons int1Int0Cons(final @Nonnull LInt1Int0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(int a1, int a2, final @Nonnull LBiIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LBiIntConsumer safe() {
		return LBiIntConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiIntConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiIntConsumer safe(final @Nullable LBiIntConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiIntConsumer> safeSupplier(final @Nullable LSupplier<LBiIntConsumer> supplier) {
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
	default LBiIntConsumer biIntConsComposeInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static LBiIntConsumer composedInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LBiIntConsumer after) {
		return after.biIntConsComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biIntConsCompose(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LBiIntConsumer after) {
		return after.biIntConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiIntConsumer together in a order. */
	@Nonnull
	default LBiIntConsumer andThen(@Nonnull LBiIntConsumer after) {
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
	default LBiIntConsumer nestingBiIntCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiIntConsumer shovingBiIntCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiIntConsumer for method references. */
	@FunctionalInterface
	interface LInt1Int0Cons extends LBiIntConsumer {

		void doAcceptInt1Int0(int a2, int a1);

		@Override
		default void doAcceptX(int a1, int a2) {
			this.doAcceptInt1Int0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiIntConsumer) */
	public static void doNothing(int a1, int a2) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1, C2> int forEach(IndexedRead<C1, aInt> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aInt> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C2> int indexed1stForEach(IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int a1 = 0;
		for (; a1 < size; a1++) {
			int a2 = oiFunc2.doApplyAsInt(source2, a1);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C2, I2> int indexed1stIterate(SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int a1 = 0;
		while (testFunc2.doTest(iterator2)) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			a1++;
		}
		return a1;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1> int indexed2ndForEach(IndexedRead<C1, aInt> ia1, C1 source1, LBiIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			int a1 = oiFunc1.doApplyAsInt(source1, a2);
			consumer.doAccept(a1, a2);
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	public static <C1, I1> int indexed2ndIterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, LBiIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1)) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			consumer.doAccept(a1, a2);
			a2++;
		}
		return a2;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=int a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer,
	// type=CONST}]
	public static <C2> int targetedForEach(int a1, IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=int a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer,
	// type=CONST}]
	public static <C2, I2> int targetedIterate(int a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc2.doTest(iterator2)) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

}
