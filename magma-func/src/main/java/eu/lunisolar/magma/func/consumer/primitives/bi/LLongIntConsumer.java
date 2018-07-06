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
 * Non-throwing functional interface (lambda) LLongIntConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): long a1,int a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongIntConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LLongIntConsumer: void doAccept(long a1,int a2)";

	// void doAccept(long a1,int a2) ;
	default void doAccept(long a1, int a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(long a1,int a2)
	 */
	void doAcceptX(long a1, int a2) throws Throwable;

	default LTuple.Void tupleAccept(LLongIntPair args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(long a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(long a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(long a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(long a1, int a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(long a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(long a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(long a1, int a2, LLongIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static void tryDoAccept(long a1, int a2, LLongIntConsumer func) {
		tryDoAccept(a1, a2, func, null);
	}

	static void tryDoAccept(long a1, int a2, LLongIntConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(long a1, int a2, LLongIntConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static void tryDoAcceptThen(long a1, int a2, LLongIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(long a1, int a2, @Nonnull LLongIntConsumer failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static void failSafeDoAccept(long a1, int a2, LLongIntConsumer func, @Nonnull LLongIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static LLongIntConsumer failSafeLongIntCons(LLongIntConsumer func, @Nonnull LLongIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, int a2, LLongIntConsumer func) {
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
	public static void fromTill(int min_i, int max_i, long a1, int a2, LLongIntConsumer func) {
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
	public static void times(int max_i, long a1, int a2, LLongIntConsumer func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntConsumer lShrink(LIntToLongFunction left) {
		return a2 -> doAccept(left.doApplyAsLong(a2), a2);
	}

	public default LIntConsumer lShrinkc(long a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static LIntConsumer lShrinked(LIntToLongFunction left, LLongIntConsumer func) {
		return func.lShrink(left);
	}

	public static LIntConsumer lShrinkedc(long a1, LLongIntConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LLongConsumer rShrink(LLongToIntFunction right) {
		return a1 -> doAccept(a1, right.doApplyAsInt(a1));
	}

	public default LLongConsumer rShrinkc(int a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static LLongConsumer rShrinked(LLongToIntFunction right, LLongIntConsumer func) {
		return func.rShrink(right);
	}

	public static LLongConsumer rShrinkedc(int a2, LLongIntConsumer func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LLongIntConsumer uncurryLongIntCons(LLongFunction<LIntConsumer> func) {
		return (long a1, int a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureLongIntCons(long a1, int a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLongIntConsumer accept1st(@Nonnull LLongConsumer func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLongIntConsumer accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongIntConsumer longIntCons(final @Nonnull LLongIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLongIntConsumer recursive(final @Nonnull LFunction<LLongIntConsumer, LLongIntConsumer> selfLambda) {
		final LLongIntConsumerSingle single = new LLongIntConsumerSingle();
		LLongIntConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLongIntConsumerSingle implements LSingle<LLongIntConsumer>, LLongIntConsumer {
		private LLongIntConsumer target = null;

		@Override
		public void doAcceptX(long a1, int a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LLongIntConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LLongIntConsumer longIntConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLongIntConsumer longIntConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntLongCons intLongCons(final @Nonnull LIntLongCons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(long a1, int a2, final @Nonnull LLongIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LLongIntConsumer safe() {
		return LLongIntConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongIntConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongIntConsumer safe(final @Nullable LLongIntConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongIntConsumer> safeSupplier(final @Nullable LSupplier<LLongIntConsumer> supplier) {
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
	default LLongIntConsumer longIntConsComposeLongInt(@Nonnull final LLongUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsLong(v1), before2.doApplyAsInt(v2));
	}

	public static LLongIntConsumer composedLongInt(@Nonnull final LLongUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LLongIntConsumer after) {
		return after.longIntConsComposeLongInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> longIntConsCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsLong(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LLongIntConsumer after) {
		return after.longIntConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LLongIntConsumer together in a order. */
	@Nonnull
	default LLongIntConsumer andThen(@Nonnull LLongIntConsumer after) {
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
	default LLongIntConsumer nestingLongIntCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongIntConsumer shovingLongIntCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LLongIntConsumer for method references. */
	@FunctionalInterface
	interface LIntLongCons extends LLongIntConsumer {

		void doAcceptIntLong(int a2, long a1);

		@Override
		default void doAcceptX(long a1, int a2) {
			this.doAcceptIntLong(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LLongIntConsumer) */
	public static void doNothing(long a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LLongIntConsumer.LIntLongCons) */
	public static void doNothing(int a2, long a1) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=long a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1, C2> int forEach(IndexedRead<C1, aLong> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			long a1 = oiFunc1.doApplyAsLong(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=long a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			long a1 = nextFunc1.doApplyAsLong(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=long a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aLong> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			long a1 = oiFunc1.doApplyAsLong(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=long a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			long a1 = nextFunc1.doApplyAsLong(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C2> long indexed1stForEach(IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		long a1 = 0;
		for (; a1 < size; a1++) {
			int a2 = oiFunc2.doApplyAsInt(source2, (int) a1);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C2, I2> long indexed1stIterate(SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		long a1 = 0;
		while (testFunc2.doTest(iterator2)) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			a1++;
		}
		return a1;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=long a1, type=IA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1> int indexed2ndForEach(IndexedRead<C1, aLong> ia1, C1 source1, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			long a1 = oiFunc1.doApplyAsLong(source1, a2);
			consumer.doAccept(a1, a2);
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=long a1, type=SA}, SourcePurpose{arg=LLongIntConsumer consumer, type=CONST}]
	public static <C1, I1> int indexed2ndIterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1)) {
			long a1 = nextFunc1.doApplyAsLong(iterator1);
			consumer.doAccept(a1, a2);
			a2++;
		}
		return a2;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=long a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LLongIntConsumer consumer,
	// type=CONST}]
	public static <C2> long targetedForEach(long a1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=long a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LLongIntConsumer consumer,
	// type=CONST}]
	public static <C2, I2> long targetedIterate(long a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
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
