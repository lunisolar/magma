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
 * Non-throwing functional interface (lambda) LByteIntConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): byte a1,int a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteIntConsumer extends MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LByteIntConsumer: void doAccept(byte a1,int a2)";

	// void doAccept(byte a1,int a2) ;
	default void doAccept(byte a1, int a2) {
		// nestingDoAccept(a1,a2);
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(byte a1,int a2)
	 */
	void doAcceptX(byte a1, int a2) throws Throwable;

	default LTuple.Void tupleAccept(LByteIntPair args) {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(byte a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(byte a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(byte a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(byte a1, int a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(byte a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(byte a1, int a2) {
		try {
			this.doAcceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoAccept(byte a1, int a2, LByteIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, handling);
	}

	static void tryDoAccept(byte a1, int a2, LByteIntConsumer func) {
		tryDoAccept(a1, a2, func, null);
	}

	static void tryDoAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static void tryDoAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, exceptionFactory);
	}

	static void tryDoAcceptThen(byte a1, int a2, LByteIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, handler);
	}

	default void failSafeDoAccept(byte a1, int a2, @Nonnull LByteIntConsumer failSafe) {
		try {
			doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2);
		}
	}

	static void failSafeDoAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull LByteIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2);
		} else {
			func.failSafeDoAccept(a1, a2, failSafe);
		}
	}

	static LByteIntConsumer failSafeByteIntCons(LByteIntConsumer func, @Nonnull LByteIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a2, int max_a2, byte a1, LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doAccept(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doAccept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a2, int max_a2, byte a1, LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doAccept(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doAccept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a2, byte a1, LByteIntConsumer func) {
		fromTill(0, max_a2, a1, func);
	}

	public default LIntConsumer lShrink(LIntToByteFunction left) {
		return a2 -> doAccept(left.doApplyAsByte(a2), a2);
	}

	public default LIntConsumer lShrinkc(byte a1) {
		return a2 -> doAccept(a1, a2);
	}

	public static LIntConsumer lShrinked(LIntToByteFunction left, LByteIntConsumer func) {
		return func.lShrink(left);
	}

	public static LIntConsumer lShrinkedc(byte a1, LByteIntConsumer func) {
		return func.lShrinkc(a1);
	}

	public default LByteConsumer rShrink(LByteToIntFunction right) {
		return a1 -> doAccept(a1, right.doApplyAsInt(a1));
	}

	public default LByteConsumer rShrinkc(int a2) {
		return a1 -> doAccept(a1, a2);
	}

	public static LByteConsumer rShrinked(LByteToIntFunction right, LByteIntConsumer func) {
		return func.rShrink(right);
	}

	public static LByteConsumer rShrinkedc(int a2, LByteIntConsumer func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LByteIntConsumer uncurryByteIntCons(LByteFunction<LIntConsumer> func) {
		return (byte a1, int a2) -> func.doApply(a1).doAccept(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureByteIntCons(byte a1, int a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LByteIntConsumer accept1st(@Nonnull LByteConsumer func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LByteIntConsumer accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteIntConsumer byteIntCons(final @Nonnull LByteIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LByteIntConsumer recursive(final @Nonnull LFunction<LByteIntConsumer, LByteIntConsumer> selfLambda) {
		final LByteIntConsumerSingle single = new LByteIntConsumerSingle();
		LByteIntConsumer func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LByteIntConsumerSingle implements LSingle<LByteIntConsumer>, LByteIntConsumer {
		private LByteIntConsumer target = null;

		@Override
		public void doAcceptX(byte a1, int a2) throws Throwable {
			target.doAcceptX(a1, a2);
		}

		@Override
		public LByteIntConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LByteIntConsumer byteIntConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LByteIntConsumer byteIntConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntByteCons intByteCons(final @Nonnull LIntByteCons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(byte a1, int a2, final @Nonnull LByteIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LByteIntConsumer safe() {
		return LByteIntConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteIntConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteIntConsumer safe(final @Nullable LByteIntConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteIntConsumer> safeSupplier(final @Nullable LSupplier<LByteIntConsumer> supplier) {
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
	default LByteIntConsumer byteIntConsComposeByteInt(@Nonnull final LByteUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsByte(v1), before2.doApplyAsInt(v2));
	}

	public static LByteIntConsumer composedByteInt(@Nonnull final LByteUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LByteIntConsumer after) {
		return after.byteIntConsComposeByteInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> byteIntConsCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doAccept(before1.doApplyAsByte(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LByteIntConsumer after) {
		return after.byteIntConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LByteIntConsumer together in a order. */
	@Nonnull
	default LByteIntConsumer andThen(@Nonnull LByteIntConsumer after) {
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
	default LByteIntConsumer nestingByteIntCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteIntConsumer shovingByteIntCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LByteIntConsumer for method references. */
	@FunctionalInterface
	interface LIntByteCons extends LByteIntConsumer {

		void doAcceptIntByte(int a2, byte a1);

		@Override
		default void doAcceptX(byte a1, int a2) {
			this.doAcceptIntByte(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LByteIntConsumer) */
	public static void doNothing(byte a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LByteIntConsumer.LIntByteCons) */
	public static void doNothing(int a2, byte a1) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=byte a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1, C2> int forEach(IndexedRead<C1, aByte> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a1 = oiFunc1.doApplyAsByte(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=byte a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			byte a1 = nextFunc1.doApplyAsByte(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=byte a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aByte> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			byte a1 = oiFunc1.doApplyAsByte(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=byte a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			byte a1 = nextFunc1.doApplyAsByte(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(a1, a2);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=byte a1, type=IA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1> int indexedForEach(IndexedRead<C1, aByte> ia1, C1 source1, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			byte a1 = oiFunc1.doApplyAsByte(source1, a2);
			consumer.doAccept(a1, a2);
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=byte a1, type=SA}, SourcePurpose{arg=LByteIntConsumer consumer, type=CONST}]
	public static <C1, I1> int indexedIterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1)) {
			byte a1 = nextFunc1.doApplyAsByte(iterator1);
			consumer.doAccept(a1, a2);
			a2++;
		}
		return a2;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=byte a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LByteIntConsumer consumer,
	// type=CONST}]
	public static <C2> byte targetedForEach(byte a1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(a1, a2);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=byte a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LByteIntConsumer consumer,
	// type=CONST}]
	public static <C2, I2> byte targetedIterate(byte a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
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
