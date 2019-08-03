/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
public interface LByteIntConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain2<aByte, aInt> {

	String DESCRIPTION = "LByteIntConsumer: void accept(byte a1,int a2)";

	// void accept(byte a1,int a2) ;
	default void accept(byte a1, int a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(byte a1,int a2)
	 */
	void acceptX(byte a1, int a2) throws Throwable;

	default LTuple.Void tupleAccept(LByteIntPair args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(byte a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteIntConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(byte a1, int a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LByteIntConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, messageParams);
	}

	default void accept(byte a1, int a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LByteIntConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> accept(a1, a2, exF);
	}

	default void acceptThen(byte a1, int a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LByteIntConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(byte a1, int a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(byte a1, int a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(byte a1, int a2, LByteIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static void tryAccept(byte a1, int a2, LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static void tryAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, messageParams);
	}

	static void tryAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF);
	}

	static void tryAcceptThen(byte a1, int a2, LByteIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(byte a1, int a2, @Nonnull LByteIntConsumer failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static void failSafeAccept(byte a1, int a2, LByteIntConsumer func, @Nonnull LByteIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static LByteIntConsumer failSafe(LByteIntConsumer func, @Nonnull LByteIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a2, int max_a2, byte a1, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.accept(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a2, int max_a2, byte a1, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.accept(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a2, byte a1, @Nonnull LByteIntConsumer func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	public default LIntConsumer lShrink(@Nonnull LIntToByteFunction left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.applyAsByte(a2), a2);
	}

	public default LIntConsumer lShrink_(byte a1) {
		return a2 -> accept(a1, a2);
	}

	public static LIntConsumer lShrunken(@Nonnull LIntToByteFunction left, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LIntConsumer lShrunken_(byte a1, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LByteConsumer rShrink(@Nonnull LByteToIntFunction right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.applyAsInt(a1));
	}

	public default LByteConsumer rShrink_(int a2) {
		return a1 -> accept(a1, a2);
	}

	public static LByteConsumer rShrunken(@Nonnull LByteToIntFunction right, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LByteConsumer rShrunken_(int a2, @Nonnull LByteIntConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LByteIntConsumer uncurry(@Nonnull LByteFunction<LIntConsumer> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, int a2) -> func.apply(a1).accept(a2);
	}

	/** Calls domain consumer before main function. */
	public default LByteIntConsumer beforeDo(@Nonnull LByteIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, int a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(byte a1, int a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LByteIntConsumer accept1st(@Nonnull LByteConsumer func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LByteIntConsumer accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.accept(a2);
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
		LByteIntConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LByteIntConsumerSingle implements LSingle<LByteIntConsumer>, LByteIntConsumer {
		private LByteIntConsumer target = null;

		@Override
		public void acceptX(byte a1, int a2) throws Throwable {
			target.acceptX(a1, a2);
		}

		@Override
		public LByteIntConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LByteIntConsumer byteIntConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteIntConsumer byteIntConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
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
		lambda.accept(a1, a2);
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
	default LByteIntConsumer compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsByte(v1), before2.applyAsInt(v2));
	}

	public static LByteIntConsumer composed(@Nonnull final LByteUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LByteIntConsumer after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> byteIntConsCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsByte(v1), before2.applyAsInt(v2));
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
			this.accept(a1, a2);
			after.accept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LByteIntConsumer for method references. */
	@FunctionalInterface
	interface LIntByteCons extends LByteIntConsumer {

		void acceptIntByte(int a2, byte a1);

		@Override
		default void acceptX(byte a1, int a2) {
			this.acceptIntByte(a2, a1);
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

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2> int forEach(IndexedRead<C1, aByte> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(a1, a2);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aByte> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1> int indexedForEach(IndexedRead<C1, aByte> ia1, C1 source1, LByteIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			byte a1 = oiFunc1.applyAsByte(source1, a2);
			consumer.accept(a1, a2);
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1> int indexedIterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, LByteIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		int a2 = 0;
		while (testFunc1.test(iterator1)) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			consumer.accept(a1, a2);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <C2> byte targetedForEach(byte a1, IndexedRead<C2, aInt> ia2, C2 source2, LByteIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <C2, I2> byte targetedIterate(byte a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteIntConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		while (testFunc2.test(iterator2)) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2);
		}
		return a1;

	}

}
