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
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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
 * Non-throwing functional interface (lambda) LBiByteConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): byte a1,byte a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiByteConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain2<aByte, aByte> {

	String DESCRIPTION = "LBiByteConsumer: void accept(byte a1,byte a2)";

	// void accept(byte a1,byte a2) ;
	default void accept(byte a1, byte a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(byte a1,byte a2)
	 */
	void acceptX(byte a1, byte a2) throws Throwable;

	default LTuple.Void tupleAccept(LBytePair args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(byte a1, byte a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiByteConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiByteConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage);
	}

	default LBiByteConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1);
	}

	default LBiByteConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param1);
	}

	default LBiByteConsumer trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default void accept(byte a1, byte a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiByteConsumer trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> accept(a1, a2, factory);
	}

	default void acceptThen(byte a1, byte a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LBiByteConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(byte a1, byte a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(byte a1, byte a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void shovingAccept(byte a1, byte a2, LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2);
	}

	static void handlingAccept(byte a1, byte a2, LBiByteConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static void tryAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory);
	}

	static void tryAcceptThen(byte a1, byte a2, LBiByteConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(byte a1, byte a2, @Nonnull LBiByteConsumer failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static void failSafeAccept(byte a1, byte a2, LBiByteConsumer func, @Nonnull LBiByteConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static LBiByteConsumer failSafe(LBiByteConsumer func, @Nonnull LBiByteConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiByteConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a1, byte a2, @Nonnull LBiByteConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LByteConsumer lShrink(@Nonnull LByteUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.applyAsByte(a2), a2);
	}

	default LByteConsumer lShrink_(byte a1) {
		return a2 -> accept(a1, a2);
	}

	public static LByteConsumer lShrunken(@Nonnull LByteUnaryOperator left, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LByteConsumer lShrunken_(byte a1, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LByteConsumer rShrink(@Nonnull LByteUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.applyAsByte(a1));
	}

	default LByteConsumer rShrink_(byte a2) {
		return a1 -> accept(a1, a2);
	}

	public static LByteConsumer rShrunken(@Nonnull LByteUnaryOperator right, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LByteConsumer rShrunken_(byte a2, @Nonnull LBiByteConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LBiByteConsumer uncurry(@Nonnull LByteFunction<LByteConsumer> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, byte a2) -> func.apply(a1).accept(a2);
	}

	/** Calls domain consumer before main function. */
	default LBiByteConsumer beforeDo(@Nonnull LBiByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, byte a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(byte a1, byte a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiByteConsumer accept1st(@Nonnull LByteConsumer func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiByteConsumer accept2nd(@Nonnull LByteConsumer func) {
		return (a1, a2) -> func.accept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiByteConsumer biByteCons(final @Nonnull LBiByteConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LBiByteConsumer {
		private LBiByteConsumer target = null;
		@Override
		public void acceptX(byte a1, byte a2) throws Throwable {
			target.acceptX(a1, a2);
		}
	}

	@Nonnull
	static LBiByteConsumer recursive(final @Nonnull LFunction<LBiByteConsumer, LBiByteConsumer> selfLambda) {
		final S single = new S();
		LBiByteConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static LBiByteConsumer biByteConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBiByteConsumer biByteConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiByteConsumer.LByte1Byte0Cons byte1Byte0Cons(final @Nonnull LBiByteConsumer.LByte1Byte0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(byte a1, byte a2, final @Nonnull LBiByteConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LBiByteConsumer safe() {
		return LBiByteConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiByteConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiByteConsumer safe(final @Nullable LBiByteConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiByteConsumer> safeSupplier(final @Nullable LSupplier<LBiByteConsumer> supplier) {
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
	default LBiByteConsumer compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	public static LBiByteConsumer composed(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2, LBiByteConsumer after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biByteConsCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2, LBiByteConsumer after) {
		return after.biByteConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiByteConsumer together in a order. */
	@Nonnull
	default LBiByteConsumer andThen(@Nonnull LBiByteConsumer after) {
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

	/** Permutation of LBiByteConsumer for method references. */
	@FunctionalInterface
	interface LByte1Byte0Cons extends LBiByteConsumer {

		/**
		 * Implement this, but call accept(byte a1,byte a2)
		 */
		default void acceptX(byte a1, byte a2) {
			this.acceptByte1Byte0(a2, a1);
		}

		// void acceptByte1Byte0(byte a2,byte a1) ;
		default void acceptByte1Byte0(byte a2, byte a1) {
			// nestingAcceptByte1Byte0(a2,a1);
			try {
				this.acceptByte1Byte0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptByte1Byte0(byte a2,byte a1)
		 */
		void acceptByte1Byte0X(byte a2, byte a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiByteConsumer) */
	public static void doNothing(byte a1, byte a2) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2> int forEach(IndexedRead<C1, aByte> ia1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LBiByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			byte a2 = oiFunc2.applyAsByte(source2, i);
			consumer.accept(a1, a2);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LBiByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			byte a2 = oiFunc2.applyAsByte(source2, i);
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
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aByte> ia1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LBiByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			byte a2 = nextFunc2.applyAsByte(iterator2);
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
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LBiByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			byte a2 = nextFunc2.applyAsByte(iterator2);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <C2> byte targetedForEach(byte a1, IndexedRead<C2, aByte> ia2, C2 source2, LBiByteConsumer consumer) {
		int size = ia2.size(source2);
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a2 = oiFunc2.applyAsByte(source2, i);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <C2, I2> byte targetedIterate(byte a1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LBiByteConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		while (testFunc2.test(iterator2)) {
			byte a2 = nextFunc2.applyAsByte(iterator2);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	// <editor-fold desc="fluentUse">

	public static <R> R inlineAcceptR(R retval, byte a1, byte a2, LBiByteConsumer consumer) {
		consumer.accept(a1, a2);
		return retval;
	}

	public static byte inlineAccept(byte a1, byte a2, LBiByteConsumer consumer) {
		consumer.accept(a1, a2);
		return a1;
	}

	// </editor-fold>

}
