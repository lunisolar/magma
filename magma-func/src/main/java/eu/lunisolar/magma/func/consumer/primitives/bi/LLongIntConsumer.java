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
public interface LLongIntConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain2<aLong, aInt> {

	String DESCRIPTION = "LLongIntConsumer: void accept(long a1,int a2)";

	// void accept(long a1,int a2) ;
	default void accept(long a1, int a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(long a1,int a2)
	 */
	void acceptX(long a1, int a2) throws Throwable;

	default LTuple.Void tupleAccept(LLongIntPair args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(long a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongIntConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(long a1, int a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LLongIntConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, messageParams);
	}

	default void accept(long a1, int a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LLongIntConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> accept(a1, a2, exF);
	}

	default void acceptThen(long a1, int a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LLongIntConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(long a1, int a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(long a1, int a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(long a1, int a2, LLongIntConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static void tryAccept(long a1, int a2, LLongIntConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static void tryAccept(long a1, int a2, LLongIntConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, messageParams);
	}

	static void tryAccept(long a1, int a2, LLongIntConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF);
	}

	static void tryAcceptThen(long a1, int a2, LLongIntConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(long a1, int a2, @Nonnull LLongIntConsumer failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static void failSafeAccept(long a1, int a2, LLongIntConsumer func, @Nonnull LLongIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static LLongIntConsumer failSafe(LLongIntConsumer func, @Nonnull LLongIntConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongIntConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, int a2, @Nonnull LLongIntConsumer func) {
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
	public static void fromTill(int min_i, int max_i, long a1, int a2, @Nonnull LLongIntConsumer func) {
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
	public static void times(int max_i, long a1, int a2, @Nonnull LLongIntConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntConsumer lShrink(@Nonnull LIntToLongFunction left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.applyAsLong(a2), a2);
	}

	public default LIntConsumer lShrink_(long a1) {
		return a2 -> accept(a1, a2);
	}

	public static LIntConsumer lShrunken(@Nonnull LIntToLongFunction left, @Nonnull LLongIntConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LIntConsumer lShrunken_(long a1, @Nonnull LLongIntConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LLongConsumer rShrink(@Nonnull LLongToIntFunction right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.applyAsInt(a1));
	}

	public default LLongConsumer rShrink_(int a2) {
		return a1 -> accept(a1, a2);
	}

	public static LLongConsumer rShrunken(@Nonnull LLongToIntFunction right, @Nonnull LLongIntConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LLongConsumer rShrunken_(int a2, @Nonnull LLongIntConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LLongIntConsumer uncurry(@Nonnull LLongFunction<LIntConsumer> func) {
		Null.nonNullArg(func, "func");
		return (long a1, int a2) -> func.apply(a1).accept(a2);
	}

	/** Calls domain consumer before main function. */
	public default LLongIntConsumer beforeDo(@Nonnull LLongIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, int a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(long a1, int a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLongIntConsumer accept1st(@Nonnull LLongConsumer func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLongIntConsumer accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2) -> func.accept(a2);
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
		LLongIntConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LLongIntConsumerSingle implements LLongIntConsumer {
		private LLongIntConsumer target = null;

		@Override
		public void acceptX(long a1, int a2) throws Throwable {
			target.acceptX(a1, a2);
		}

	}

	@Nonnull
	static LLongIntConsumer longIntConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongIntConsumer longIntConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongIntConsumer.LIntLongCons intLongCons(final @Nonnull LLongIntConsumer.LIntLongCons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(long a1, int a2, final @Nonnull LLongIntConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
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
	default LLongIntConsumer compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsLong(v1), before2.applyAsInt(v2));
	}

	public static LLongIntConsumer composed(@Nonnull final LLongUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LLongIntConsumer after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> longIntConsCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.applyAsLong(v1), before2.applyAsInt(v2));
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
			this.accept(a1, a2);
			after.accept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LLongIntConsumer for method references. */
	@FunctionalInterface
	interface LIntLongCons extends LLongIntConsumer {

		/**
		 * Implement this, but call accept(long a1,int a2)
		 */
		default void acceptX(long a1, int a2) {
			this.acceptIntLong(a2, a1);
		}

		// void acceptIntLong(int a2,long a1) ;
		default void acceptIntLong(int a2, long a1) {
			// nestingAcceptIntLong(a2,a1);
			try {
				this.acceptIntLongX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptIntLong(int a2,long a1)
		 */
		void acceptIntLongX(int a2, long a1) throws Throwable;
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

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2> int forEach(IndexedRead<C1, aLong> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			long a1 = oiFunc1.applyAsLong(source1, i);
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
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			long a1 = nextFunc1.applyAsLong(iterator1);
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
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aLong> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			long a1 = oiFunc1.applyAsLong(source1, i);
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
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			long a1 = nextFunc1.applyAsLong(iterator1);
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
	public static <C2> long indexed1stForEach(IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		long a1 = 0;
		for (; a1 < size; a1++) {
			int a2 = oiFunc2.applyAsInt(source2, (int) a1);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C2, I2> long indexed1stIterate(SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		long a1 = 0;
		while (testFunc2.test(iterator2)) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2);
			a1++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1> int indexed2ndForEach(IndexedRead<C1, aLong> ia1, C1 source1, LLongIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			long a1 = oiFunc1.applyAsLong(source1, a2);
			consumer.accept(a1, a2);
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1> int indexed2ndIterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, LLongIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		int a2 = 0;
		while (testFunc1.test(iterator1)) {
			long a1 = nextFunc1.applyAsLong(iterator1);
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
	public static <C2> long targetedForEach(long a1, IndexedRead<C2, aInt> ia2, C2 source2, LLongIntConsumer consumer) {
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
	public static <C2, I2> long targetedIterate(long a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LLongIntConsumer consumer) {
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
