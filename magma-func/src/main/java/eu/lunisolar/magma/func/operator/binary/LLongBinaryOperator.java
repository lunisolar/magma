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

package eu.lunisolar.magma.func.operator.binary;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LLongBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): long a1,long a2
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongBinaryOperator extends LongBinaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aLong>, Domain2<aLong, aLong> { // NOSONAR

	String DESCRIPTION = "LLongBinaryOperator: long applyAsLong(long a1,long a2)";

	// long applyAsLong(long a1,long a2) ;
	default long applyAsLong(long a1, long a2) {
		// return nestingApplyAsLong(a1,a2);
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(long a1,long a2)
	 */
	long applyAsLongX(long a1, long a2) throws Throwable;

	default long tupleApplyAsLong(LLongPair args) {
		return applyAsLong(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(long a1, long a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsLong(a1, a2, handling);
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LLongBinaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsLong(a1, a2, exF, newMessage, messageParams);
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LLongBinaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsLong(a1, a2, exF);
	}

	default long applyAsLongThen(long a1, long a2, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LLongBinaryOperator tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return (a1, a2) -> applyAsLongThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(long a1, long a2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(long a1, long a2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingApplyAsLong(long a1, long a2, LLongBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a1, a2, handling);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a1, a2);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, exF, newMessage, messageParams);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, exF);
	}

	static long tryApplyAsLongThen(long a1, long a2, LLongBinaryOperator func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a1, a2, handler);
	}

	default long failSafeApplyAsLong(long a1, long a2, @Nonnull LLongBinaryOperator failSafe) {
		try {
			return applyAsLong(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsLong(a1, a2);
		}
	}

	static long failSafeApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull LLongBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsLong(a1, a2);
		} else {
			return func.failSafeApplyAsLong(a1, a2, failSafe);
		}
	}

	static LLongBinaryOperator failSafe(LLongBinaryOperator func, @Nonnull LLongBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsLong(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(long a1, long a2) {
		return applyAsLong(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LLongUnaryOperator lShrink(@Nonnull LLongUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsLong(left.applyAsLong(a2), a2);
	}

	default LLongUnaryOperator lShrink_(long a1) {
		return a2 -> applyAsLong(a1, a2);
	}

	public static LLongUnaryOperator lShrunken(@Nonnull LLongUnaryOperator left, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LLongUnaryOperator lShrunken_(long a1, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LLongUnaryOperator rShrink(@Nonnull LLongUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsLong(a1, right.applyAsLong(a1));
	}

	default LLongUnaryOperator rShrink_(long a2) {
		return a1 -> applyAsLong(a1, a2);
	}

	public static LLongUnaryOperator rShrunken(@Nonnull LLongUnaryOperator right, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LLongUnaryOperator rShrunken_(long a2, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LLongBinaryOperator uncurry(@Nonnull LLongFunction<LLongUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (long a1, long a2) -> func.apply(a1).applyAsLong(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiLongConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LLongBinaryOperator beforeDo(@Nonnull LBiLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, long a2) -> {
			before.accept(a1, a2);
			return applyAsLong(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLongBinaryOperator afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (long a1, long a2) -> {
			final long retval = applyAsLong(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(long a1, long a2) {
		return () -> this.applyAsLong(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LLongBinaryOperator constant(long r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLongBinaryOperator apply1stAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2) -> func.applyAsLong(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLongBinaryOperator apply2ndAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2) -> func.applyAsLong(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongBinaryOperator longBinaryOp(final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLongBinaryOperator recursive(final @Nonnull LFunction<LLongBinaryOperator, LLongBinaryOperator> selfLambda) {
		final LLongBinaryOperatorSingle single = new LLongBinaryOperatorSingle();
		LLongBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LLongBinaryOperatorSingle implements LLongBinaryOperator {
		private LLongBinaryOperator target = null;

		@Override
		public long applyAsLongX(long a1, long a2) throws Throwable {
			return target.applyAsLongX(a1, a2);
		}

	}

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static long call(long a1, long a2, final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongBinaryOperator wrap(final LongBinaryOperator other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LLongBinaryOperator safe() {
		return LLongBinaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongBinaryOperator safe(final @Nullable LLongBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongBinaryOperator> safeSupplier(final @Nullable LSupplier<LLongBinaryOperator> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LLongBinaryOperator minBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator maxBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LLongBinaryOperator min() {
		return Long::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator max() {
		return Long::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongBinaryOperator compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	public static LLongBinaryOperator composed(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, LLongBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToLongBiFunction<V1, V2> longBinaryOpCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	public static <V1, V2> LToLongBiFunction<V1, V2> composed(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2, LLongBinaryOperator after) {
		return after.longBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiLongFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongBinaryOperator thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiLongPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsLong(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLongBinaryOperator) Operator */
	public static long doNothing(long a1, long a2) {
		return Function4U.defaultLong;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aLong> ia1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LLongConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			long a1 = oiFunc1.applyAsLong(source1, i);
			long a2 = oiFunc2.applyAsLong(source2, i);
			consumer.accept(this.applyAsLong(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LLongConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			long a1 = nextFunc1.applyAsLong(iterator1);
			long a2 = oiFunc2.applyAsLong(source2, i);
			consumer.accept(this.applyAsLong(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aLong> ia1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LLongConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			long a1 = oiFunc1.applyAsLong(source1, i);
			long a2 = nextFunc2.applyAsLong(iterator2);
			consumer.accept(this.applyAsLong(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LLongConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			long a1 = nextFunc1.applyAsLong(iterator1);
			long a2 = nextFunc2.applyAsLong(iterator2);
			consumer.accept(this.applyAsLong(a1, a2));
		}
	}

}
