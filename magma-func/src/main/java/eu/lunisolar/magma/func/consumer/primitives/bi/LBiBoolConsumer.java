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
 * Non-throwing functional interface (lambda) LBiBoolConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): boolean a1,boolean a2
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiBoolConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain2<aBool, aBool> {

	String DESCRIPTION = "LBiBoolConsumer: void accept(boolean a1,boolean a2)";

	// void accept(boolean a1,boolean a2) ;
	default void accept(boolean a1, boolean a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(boolean a1,boolean a2)
	 */
	void acceptX(boolean a1, boolean a2) throws Throwable;

	default LTuple.Void tupleAccept(LBoolPair args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(boolean a1, boolean a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiBoolConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(boolean a1, boolean a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default void accept(boolean a1, boolean a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default void accept(boolean a1, boolean a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default void accept(boolean a1, boolean a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LBiBoolConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage);
	}

	default LBiBoolConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1);
	}

	default LBiBoolConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1, param1);
	}

	default LBiBoolConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1, param2, param3);
	}

	default void accept(boolean a1, boolean a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiBoolConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> accept(a1, a2, exF);
	}

	default void acceptThen(boolean a1, boolean a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LBiBoolConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(boolean a1, boolean a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(boolean a1, boolean a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(boolean a1, boolean a2, LBiBoolConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1, param2);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1, param2, param3);
	}

	static void tryAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF);
	}

	static void tryAcceptThen(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(boolean a1, boolean a2, @Nonnull LBiBoolConsumer failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static void failSafeAccept(boolean a1, boolean a2, LBiBoolConsumer func, @Nonnull LBiBoolConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static LBiBoolConsumer failSafe(LBiBoolConsumer func, @Nonnull LBiBoolConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiBoolConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a1, boolean a2, @Nonnull LBiBoolConsumer func) {
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
	public static void fromTill(int min_i, int max_i, boolean a1, boolean a2, @Nonnull LBiBoolConsumer func) {
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
	public static void times(int max_i, boolean a1, boolean a2, @Nonnull LBiBoolConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LBoolConsumer lShrink(@Nonnull LLogicalOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.apply(a2), a2);
	}

	default LBoolConsumer lShrink_(boolean a1) {
		return a2 -> accept(a1, a2);
	}

	public static LBoolConsumer lShrunken(@Nonnull LLogicalOperator left, @Nonnull LBiBoolConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LBoolConsumer lShrunken_(boolean a1, @Nonnull LBiBoolConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LBoolConsumer rShrink(@Nonnull LLogicalOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.apply(a1));
	}

	default LBoolConsumer rShrink_(boolean a2) {
		return a1 -> accept(a1, a2);
	}

	public static LBoolConsumer rShrunken(@Nonnull LLogicalOperator right, @Nonnull LBiBoolConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LBoolConsumer rShrunken_(boolean a2, @Nonnull LBiBoolConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LBiBoolConsumer uncurry(@Nonnull LBoolFunction<LBoolConsumer> func) {
		Null.nonNullArg(func, "func");
		return (boolean a1, boolean a2) -> func.apply(a1).accept(a2);
	}

	/** Calls domain consumer before main function. */
	default LBiBoolConsumer beforeDo(@Nonnull LBiBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return (boolean a1, boolean a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(boolean a1, boolean a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiBoolConsumer accept1st(@Nonnull LBoolConsumer func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiBoolConsumer accept2nd(@Nonnull LBoolConsumer func) {
		return (a1, a2) -> func.accept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiBoolConsumer biBoolCons(final @Nonnull LBiBoolConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LBiBoolConsumer {
		private LBiBoolConsumer target = null;
		@Override
		public void acceptX(boolean a1, boolean a2) throws Throwable {
			target.acceptX(a1, a2);
		}
	}

	@Nonnull
	static LBiBoolConsumer recursive(final @Nonnull LFunction<LBiBoolConsumer, LBiBoolConsumer> selfLambda) {
		final S single = new S();
		LBiBoolConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static LBiBoolConsumer biBoolConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBiBoolConsumer biBoolConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiBoolConsumer.LBool1Bool0Cons bool1Bool0Cons(final @Nonnull LBiBoolConsumer.LBool1Bool0Cons lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static void call(boolean a1, boolean a2, final @Nonnull LBiBoolConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LBiBoolConsumer safe() {
		return LBiBoolConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiBoolConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiBoolConsumer safe(final @Nullable LBiBoolConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiBoolConsumer> safeSupplier(final @Nullable LSupplier<LBiBoolConsumer> supplier) {
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
	default LBiBoolConsumer compose(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.apply(v1), before2.apply(v2));
	}

	public static LBiBoolConsumer composed(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, LBiBoolConsumer after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> biBoolConsCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.test(v1), before2.test(v2));
	}

	public static <V1, V2> LBiConsumer<V1, V2> composed(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, LBiBoolConsumer after) {
		return after.biBoolConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiBoolConsumer together in a order. */
	@Nonnull
	default LBiBoolConsumer andThen(@Nonnull LBiBoolConsumer after) {
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

	/** Permutation of LBiBoolConsumer for method references. */
	@FunctionalInterface
	interface LBool1Bool0Cons extends LBiBoolConsumer {

		/**
		 * Implement this, but call accept(boolean a1,boolean a2)
		 */
		default void acceptX(boolean a1, boolean a2) {
			this.acceptBool1Bool0(a2, a1);
		}

		// void acceptBool1Bool0(boolean a2,boolean a1) ;
		default void acceptBool1Bool0(boolean a2, boolean a1) {
			// nestingAcceptBool1Bool0(a2,a1);
			try {
				this.acceptBool1Bool0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptBool1Bool0(boolean a2,boolean a1)
		 */
		void acceptBool1Bool0X(boolean a2, boolean a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiBoolConsumer) */
	public static void doNothing(boolean a1, boolean a2) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2> int forEach(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LBiBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a1 = oiFunc1.test(source1, i);
			boolean a2 = oiFunc2.test(source2, i);
			consumer.accept(a1, a2);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LBiBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.supplier();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			boolean a1 = nextFunc1.test(iterator1);
			boolean a2 = oiFunc2.test(source2, i);
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
	public static <C1, C2, I2> int iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LBiBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			boolean a1 = oiFunc1.test(source1, i);
			boolean a2 = nextFunc2.test(iterator2);
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
	public static <C1, I1, C2, I2> int iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LBiBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			boolean a1 = nextFunc1.test(iterator1);
			boolean a2 = nextFunc2.test(iterator2);
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
	public static <C2> boolean targetedForEach(boolean a1, IndexedRead<C2, aBool> ia2, C2 source2, LBiBoolConsumer consumer) {
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a2 = oiFunc2.test(source2, i);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <C2, I2> boolean targetedIterate(boolean a1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LBiBoolConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.supplier();
		while (testFunc2.test(iterator2)) {
			boolean a2 = nextFunc2.test(iterator2);
			consumer.accept(a1, a2);
		}
		return a1;

	}

}
