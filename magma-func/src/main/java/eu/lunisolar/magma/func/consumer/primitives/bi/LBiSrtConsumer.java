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
 * Non-throwing functional interface (lambda) LBiSrtConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): short a1,short a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiSrtConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBiSrtConsumer: void doAccept(short a1,short a2)";

	// void doAccept(short a1,short a2) ;
	default void doAccept(short a1, short a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(short a1,short a2)
	 */
	void doAcceptX(short a1, short a2) throws Throwable;

	default LTuple.Void tupleAccept(LSrtPair args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(short a1, short a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(short a1, short a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(short a1, short a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(short a1, short a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(short a1, short a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(short a1, short a2, LBiSrtConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static void tryDoAccept(short a1, short a2, LBiSrtConsumer func) {
		tryDoAccept(a1, a2, func, null);
	}

	static void tryDoAccept(short a1, short a2, LBiSrtConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(short a1, short a2, LBiSrtConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static void tryDoAcceptThen(short a1, short a2, LBiSrtConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(short a1, short a2, @Nonnull LBiSrtConsumer failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static void failSafeDoAccept(short a1, short a2, LBiSrtConsumer func, @Nonnull LBiSrtConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static LBiSrtConsumer failSafeBiSrtCons(LBiSrtConsumer func, @Nonnull LBiSrtConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiSrtConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, LBiSrtConsumer func) {
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
	public static void fromTill(int min_i, int max_i, short a1, short a2, LBiSrtConsumer func) {
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
	public static void times(int max_i, short a1, short a2, LBiSrtConsumer func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LSrtConsumer lShrink(LSrtUnaryOperator left) {
		return a2 -> doAccept(left.doApplyAsSrt(a2), a2);
	}

	public default LSrtConsumer lShrinkc(short a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static LSrtConsumer lShrinked(LSrtUnaryOperator left, LBiSrtConsumer func) {
		return func.lShrink(left);
	}

	public static LSrtConsumer lShrinkedc(short a1, LBiSrtConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LSrtConsumer rShrink(LSrtUnaryOperator right) {
		return a1 -> doAccept(a1, right.doApplyAsSrt(a1));
	}

	public default LSrtConsumer rShrinkc(short a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static LSrtConsumer rShrinked(LSrtUnaryOperator right, LBiSrtConsumer func) {
		return func.rShrink(right);
	}

	public static LSrtConsumer rShrinkedc(short a2, LBiSrtConsumer func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiSrtConsumer uncurryBiSrtCons(LSrtFunction<LSrtConsumer> func) {
		return (short a1, short a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiSrtCons(short a1, short a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiSrtConsumer accept1st(@Nonnull LSrtConsumer func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiSrtConsumer accept2nd(@Nonnull LSrtConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiSrtConsumer biSrtCons(final @Nonnull LBiSrtConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiSrtConsumer recursive(final @Nonnull LFunction<LBiSrtConsumer, LBiSrtConsumer> selfLambda) {
		final LBiSrtConsumerSingle single = new LBiSrtConsumerSingle();
		LBiSrtConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiSrtConsumerSingle implements LSingle<LBiSrtConsumer>, LBiSrtConsumer {
		private LBiSrtConsumer target = null;

		@Override
		public void doAcceptX(short a1, short a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LBiSrtConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LBiSrtConsumer biSrtConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiSrtConsumer biSrtConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrt1Srt0Cons srt1Srt0Cons(final @Nonnull LSrt1Srt0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(short a1, short a2, final @Nonnull LBiSrtConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LBiSrtConsumer safe() {
		return LBiSrtConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiSrtConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiSrtConsumer safe(final @Nullable LBiSrtConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiSrtConsumer> safeSupplier(final @Nullable LSupplier<LBiSrtConsumer> supplier) {
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
	default LBiSrtConsumer biSrtConsComposeSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static LBiSrtConsumer composedSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, LBiSrtConsumer after) {
		return after.biSrtConsComposeSrt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biSrtConsCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, LBiSrtConsumer after) {
		return after.biSrtConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiSrtConsumer together in a order. */
	@Nonnull
	default LBiSrtConsumer andThen(@Nonnull LBiSrtConsumer after) {
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
	default LBiSrtConsumer nestingBiSrtCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiSrtConsumer shovingBiSrtCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiSrtConsumer for method references. */
	@FunctionalInterface
	interface LSrt1Srt0Cons extends LBiSrtConsumer {

		void doAcceptSrt1Srt0(short a2, short a1);

		@Override
		default void doAcceptX(short a1, short a2) {
			this.doAcceptSrt1Srt0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiSrtConsumer) */
	public static void doNothing(short a1, short a2) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	public static <C1, C2> int forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LBiSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LBiSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LBiSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LBiSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=short a1, type=CONST}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LBiSrtConsumer consumer,
	// type=CONST}]
	public static <C2> short targetedForEach(short a1, IndexedRead<C2, aShort> ia2, C2 source2, LBiSrtConsumer consumer) {
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=short a1, type=CONST}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LBiSrtConsumer consumer,
	// type=CONST}]
	public static <C2, I2> short targetedIterate(short a1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LBiSrtConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		while (testFunc2.doTest(iterator2)) {
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

}
