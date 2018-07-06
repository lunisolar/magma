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
 * Non-throwing functional interface (lambda) LBiCharConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): char a1,char a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiCharConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBiCharConsumer: void doAccept(char a1,char a2)";

	// void doAccept(char a1,char a2) ;
	default void doAccept(char a1, char a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(char a1,char a2)
	 */
	void doAcceptX(char a1, char a2) throws Throwable;

	default LTuple.Void tupleAccept(LCharPair args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(char a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(char a1, char a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(char a1, char a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(char a1, char a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(char a1, char a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(char a1, char a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(char a1, char a2, LBiCharConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static void tryDoAccept(char a1, char a2, LBiCharConsumer func) {
		tryDoAccept(a1, a2, func, null);
	}

	static void tryDoAccept(char a1, char a2, LBiCharConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(char a1, char a2, LBiCharConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static void tryDoAcceptThen(char a1, char a2, LBiCharConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(char a1, char a2, @Nonnull LBiCharConsumer failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static void failSafeDoAccept(char a1, char a2, LBiCharConsumer func, @Nonnull LBiCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static LBiCharConsumer failSafeBiCharCons(LBiCharConsumer func, @Nonnull LBiCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiCharConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, LBiCharConsumer func) {
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
	public static void fromTill(int min_i, int max_i, char a1, char a2, LBiCharConsumer func) {
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
	public static void times(int max_i, char a1, char a2, LBiCharConsumer func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LCharConsumer lShrink(LCharUnaryOperator left) {
		return a2 -> doAccept(left.doApplyAsChar(a2), a2);
	}

	public default LCharConsumer lShrinkc(char a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static LCharConsumer lShrinked(LCharUnaryOperator left, LBiCharConsumer func) {
		return func.lShrink(left);
	}

	public static LCharConsumer lShrinkedc(char a1, LBiCharConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LCharConsumer rShrink(LCharUnaryOperator right) {
		return a1 -> doAccept(a1, right.doApplyAsChar(a1));
	}

	public default LCharConsumer rShrinkc(char a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static LCharConsumer rShrinked(LCharUnaryOperator right, LBiCharConsumer func) {
		return func.rShrink(right);
	}

	public static LCharConsumer rShrinkedc(char a2, LBiCharConsumer func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiCharConsumer uncurryBiCharCons(LCharFunction<LCharConsumer> func) {
		return (char a1, char a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiCharCons(char a1, char a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiCharConsumer accept1st(@Nonnull LCharConsumer func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiCharConsumer accept2nd(@Nonnull LCharConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiCharConsumer biCharCons(final @Nonnull LBiCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiCharConsumer recursive(final @Nonnull LFunction<LBiCharConsumer, LBiCharConsumer> selfLambda) {
		final LBiCharConsumerSingle single = new LBiCharConsumerSingle();
		LBiCharConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiCharConsumerSingle implements LSingle<LBiCharConsumer>, LBiCharConsumer {
		private LBiCharConsumer target = null;

		@Override
		public void doAcceptX(char a1, char a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LBiCharConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LBiCharConsumer biCharConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiCharConsumer biCharConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LChar1Char0Cons char1Char0Cons(final @Nonnull LChar1Char0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(char a1, char a2, final @Nonnull LBiCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LBiCharConsumer safe() {
		return LBiCharConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiCharConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiCharConsumer safe(final @Nullable LBiCharConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiCharConsumer> safeSupplier(final @Nullable LSupplier<LBiCharConsumer> supplier) {
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
	default LBiCharConsumer biCharConsComposeChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	public static LBiCharConsumer composedChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, LBiCharConsumer after) {
		return after.biCharConsComposeChar(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biCharConsCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, LBiCharConsumer after) {
		return after.biCharConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiCharConsumer together in a order. */
	@Nonnull
	default LBiCharConsumer andThen(@Nonnull LBiCharConsumer after) {
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
	default LBiCharConsumer nestingBiCharCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiCharConsumer shovingBiCharCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiCharConsumer for method references. */
	@FunctionalInterface
	interface LChar1Char0Cons extends LBiCharConsumer {

		void doAcceptChar1Char0(char a2, char a1);

		@Override
		default void doAcceptX(char a1, char a2) {
			this.doAcceptChar1Char0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiCharConsumer) */
	public static void doNothing(char a1, char a2) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	public static <C1, C2> int forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LBiCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LBiCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LBiCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LBiCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=char a1, type=CONST}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LBiCharConsumer consumer,
	// type=CONST}]
	public static <C2> char targetedForEach(char a1, IndexedRead<C2, aChar> ia2, C2 source2, LBiCharConsumer consumer) {
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=char a1, type=CONST}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LBiCharConsumer consumer,
	// type=CONST}]
	public static <C2, I2> char targetedIterate(char a1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LBiCharConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		while (testFunc2.doTest(iterator2)) {
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

}
