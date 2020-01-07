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
 * Non-throwing functional interface (lambda) LSrtBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): short a1,short a2
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aShort>, Domain2<aShort, aShort> { // NOSONAR

	String DESCRIPTION = "LSrtBinaryOperator: short applyAsSrt(short a1,short a2)";

	// short applyAsSrt(short a1,short a2) ;
	default short applyAsSrt(short a1, short a2) {
		// return nestingApplyAsSrt(a1,a2);
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(short a1,short a2)
	 */
	short applyAsSrtX(short a1, short a2) throws Throwable;

	default short tupleApplyAsSrt(LSrtPair args) {
		return applyAsSrt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsSrt(a1, a2, handling);
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtBinaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsSrt(a1, a2, exF, newMessage, messageParams);
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtBinaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsSrt(a1, a2, exF);
	}

	default short applyAsSrtThen(short a1, short a2, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LSrtBinaryOperator tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return (a1, a2) -> applyAsSrtThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(short a1, short a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(short a1, short a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a1, a2, handling);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a1, a2);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, exF, newMessage, messageParams);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, exF);
	}

	static short tryApplyAsSrtThen(short a1, short a2, LSrtBinaryOperator func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a1, a2, handler);
	}

	default short failSafeApplyAsSrt(short a1, short a2, @Nonnull LSrtBinaryOperator failSafe) {
		try {
			return applyAsSrt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsSrt(a1, a2);
		}
	}

	static short failSafeApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull LSrtBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsSrt(a1, a2);
		} else {
			return func.failSafeApplyAsSrt(a1, a2, failSafe);
		}
	}

	static LSrtBinaryOperator failSafe(LSrtBinaryOperator func, @Nonnull LSrtBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsSrt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(short a1, short a2) {
		return applyAsSrt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LSrtUnaryOperator lShrink(@Nonnull LSrtUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsSrt(left.applyAsSrt(a2), a2);
	}

	default LSrtUnaryOperator lShrink_(short a1) {
		return a2 -> applyAsSrt(a1, a2);
	}

	public static LSrtUnaryOperator lShrunken(@Nonnull LSrtUnaryOperator left, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LSrtUnaryOperator lShrunken_(short a1, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LSrtUnaryOperator rShrink(@Nonnull LSrtUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsSrt(a1, right.applyAsSrt(a1));
	}

	default LSrtUnaryOperator rShrink_(short a2) {
		return a1 -> applyAsSrt(a1, a2);
	}

	public static LSrtUnaryOperator rShrunken(@Nonnull LSrtUnaryOperator right, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LSrtUnaryOperator rShrunken_(short a2, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LSrtBinaryOperator uncurry(@Nonnull LSrtFunction<LSrtUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (short a1, short a2) -> func.apply(a1).applyAsSrt(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiSrtConsumer toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LSrtBinaryOperator beforeDo(@Nonnull LBiSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, short a2) -> {
			before.accept(a1, a2);
			return applyAsSrt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LSrtBinaryOperator afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a1, short a2) -> {
			final short retval = applyAsSrt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSrtSupplier capture(short a1, short a2) {
		return () -> this.applyAsSrt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LSrtBinaryOperator constant(short r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LSrtBinaryOperator apply1stAsSrt(@Nonnull LSrtUnaryOperator func) {
		return (a1, a2) -> func.applyAsSrt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LSrtBinaryOperator apply2ndAsSrt(@Nonnull LSrtUnaryOperator func) {
		return (a1, a2) -> func.applyAsSrt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtBinaryOperator srtBinaryOp(final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtBinaryOperator recursive(final @Nonnull LFunction<LSrtBinaryOperator, LSrtBinaryOperator> selfLambda) {
		final LSrtBinaryOperatorSingle single = new LSrtBinaryOperatorSingle();
		LSrtBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtBinaryOperatorSingle implements LSrtBinaryOperator {
		private LSrtBinaryOperator target = null;

		@Override
		public short applyAsSrtX(short a1, short a2) throws Throwable {
			return target.applyAsSrtX(a1, a2);
		}

	}

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static short call(short a1, short a2, final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LSrtBinaryOperator safe() {
		return LSrtBinaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtBinaryOperator safe(final @Nullable LSrtBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtBinaryOperator> safeSupplier(final @Nullable LSupplier<LSrtBinaryOperator> supplier) {
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
	static LSrtBinaryOperator minBy(@Nonnull Comparator<Short> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LSrtBinaryOperator maxBy(@Nonnull Comparator<Short> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LSrtBinaryOperator min() {
		return (a, b) -> (a <= b) ? a : b;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LSrtBinaryOperator max() {
		return (a, b) -> (a >= b) ? a : b;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LSrtBinaryOperator compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.applyAsSrt(v1), before2.applyAsSrt(v2));
	}

	public static LSrtBinaryOperator composed(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, LSrtBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToSrtBiFunction<V1, V2> srtBinaryOpCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.applyAsSrt(v1), before2.applyAsSrt(v2));
	}

	public static <V1, V2> LToSrtBiFunction<V1, V2> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, LSrtBinaryOperator after) {
		return after.srtBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiSrtFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtBinaryOperator thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsSrt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtBinaryOperator) Operator */
	public static short doNothing(short a1, short a2) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			consumer.accept(this.applyAsSrt(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			consumer.accept(this.applyAsSrt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			consumer.accept(this.applyAsSrt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			consumer.accept(this.applyAsSrt(a1, a2));
		}
	}

}
