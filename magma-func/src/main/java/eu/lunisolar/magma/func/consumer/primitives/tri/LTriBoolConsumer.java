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

package eu.lunisolar.magma.func.consumer.primitives.tri;

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
 * Non-throwing functional interface (lambda) LTriBoolConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): boolean a1,boolean a2,boolean a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriBoolConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LTriBoolConsumer: void doAccept(boolean a1,boolean a2,boolean a3)";

	// void doAccept(boolean a1,boolean a2,boolean a3) ;
	default void doAccept(boolean a1, boolean a2, boolean a3) {
		// nestingDoAccept(a1,a2,a3);
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(boolean a1,boolean a2,boolean a3)
	 */
	void doAcceptX(boolean a1, boolean a2, boolean a3) throws Throwable;

	default LTuple.Void tupleAccept(LBoolTriple args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(boolean a1, boolean a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(boolean a1, boolean a2, boolean a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(boolean a1, boolean a2, boolean a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(boolean a1, boolean a2, boolean a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(boolean a1, boolean a2, boolean a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(boolean a1, boolean a2, boolean a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, a3, handling);
	}

	static void tryDoAccept(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func) {
		tryDoAccept(a1, a2, a3, func, null);
	}

	static void tryDoAccept(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory);
	}

	static void tryDoAcceptThen(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, a3, handler);
	}

	default void failSafeDoAccept(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolConsumer failSafe) {
		try {
			doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2, a3);
		}
	}

	static void failSafeDoAccept(boolean a1, boolean a2, boolean a3, LTriBoolConsumer func, @Nonnull LTriBoolConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2, a3);
		} else {
			func.failSafeDoAccept(a1, a2, a3, failSafe);
		}
	}

	static LTriBoolConsumer failSafeTriBoolCons(LTriBoolConsumer func, @Nonnull LTriBoolConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriBoolConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a1, boolean a2, boolean a3, LTriBoolConsumer func) {
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
	public static void fromTill(int min_i, int max_i, boolean a1, boolean a2, boolean a3, LTriBoolConsumer func) {
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
	public static void times(int max_i, boolean a1, boolean a2, boolean a3, LTriBoolConsumer func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiBoolConsumer lShrink(LLogicalBinaryOperator left) {
		return (a2, a3) -> doAccept(left.doApply(a2, a3), a2, a3);
	}

	public default LBiBoolConsumer lShrinkc(boolean a1) {
		return (a2, a3) -> doAccept(a1, a2, a3);
	}

	public static LBiBoolConsumer lShrinked(LLogicalBinaryOperator left, LTriBoolConsumer func) {
		return func.lShrink(left);
	}

	public static LBiBoolConsumer lShrinkedc(boolean a1, LTriBoolConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LBiBoolConsumer rShrink(LLogicalBinaryOperator right) {
		return (a1, a2) -> doAccept(a1, a2, right.doApply(a1, a2));
	}

	public default LBiBoolConsumer rShrinkc(boolean a3) {
		return (a1, a2) -> doAccept(a1, a2, a3);
	}

	public static LBiBoolConsumer rShrinked(LLogicalBinaryOperator right, LTriBoolConsumer func) {
		return func.rShrink(right);
	}

	public static LBiBoolConsumer rShrinkedc(boolean a3, LTriBoolConsumer func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static LTriBoolConsumer uncurryTriBoolCons(LBoolFunction<LBoolFunction<LBoolConsumer>> func) {
		return (boolean a1, boolean a2, boolean a3) -> func.doApply(a1).doApply(a2).doAccept(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureTriBoolCons(boolean a1, boolean a2, boolean a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LTriBoolConsumer accept1st(@Nonnull LBoolConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LTriBoolConsumer accept2nd(@Nonnull LBoolConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LTriBoolConsumer accept3rd(@Nonnull LBoolConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LTriBoolConsumer triBoolCons(final @Nonnull LTriBoolConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LTriBoolConsumer recursive(final @Nonnull LFunction<LTriBoolConsumer, LTriBoolConsumer> selfLambda) {
		final LTriBoolConsumerSingle single = new LTriBoolConsumerSingle();
		LTriBoolConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTriBoolConsumerSingle implements LSingle<LTriBoolConsumer>, LTriBoolConsumer {
		private LTriBoolConsumer target = null;

		@Override
		public void doAcceptX(boolean a1, boolean a2, boolean a3) throws Throwable {
			target.doAcceptX(a1, a2, a3);
		}

		@Override
		public LTriBoolConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LTriBoolConsumer triBoolConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LTriBoolConsumer triBoolConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static void call(boolean a1, boolean a2, boolean a3, final @Nonnull LTriBoolConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LTriBoolConsumer safe() {
		return LTriBoolConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriBoolConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LTriBoolConsumer safe(final @Nullable LTriBoolConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriBoolConsumer> safeSupplier(final @Nullable LSupplier<LTriBoolConsumer> supplier) {
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
	default LTriBoolConsumer triBoolConsComposeBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	public static LTriBoolConsumer composedBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, @Nonnull final LLogicalOperator before3, LTriBoolConsumer after) {
		return after.triBoolConsComposeBool(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> triBoolConsCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doTest(v1), before2.doTest(v2), before3.doTest(v3));
	}

	public static <V1, V2, V3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, @Nonnull final LPredicate<? super V3> before3, LTriBoolConsumer after) {
		return after.triBoolConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriBoolConsumer together in a order. */
	@Nonnull
	default LTriBoolConsumer andThen(@Nonnull LTriBoolConsumer after) {
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
	default LTriBoolConsumer nestingTriBoolCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriBoolConsumer shovingTriBoolCons() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LTriBoolConsumer) */
	public static void doNothing(boolean a1, boolean a2, boolean a3) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, C2, C3> int forEach(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, I1, C2, C3> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, C2, I2, C3> int iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, C2, C3, I3> int iterate(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, I1, C2, C3, I3> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, C2, I2, C3, I3> int iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, I3> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=boolean a1, type=CONST}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C2, C3> boolean targetedForEach(boolean a1, IndexedRead<C2, aBool> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=boolean a1, type=CONST}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C2, I2, C3> boolean targetedIterate(boolean a1, SequentialRead<C2, I2, aBool> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc2.doTest(iterator2) && i < size) {
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=boolean a1, type=CONST}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C2, C3, I3> boolean targetedIterate(boolean a1, IndexedRead<C2, aBool> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=boolean a1, type=CONST}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTriBoolConsumer consumer, type=CONST}]
	public static <C2, I2, C3, I3> boolean targetedIterate(boolean a1, SequentialRead<C2, I2, aBool> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTriBoolConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		while (testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

}
