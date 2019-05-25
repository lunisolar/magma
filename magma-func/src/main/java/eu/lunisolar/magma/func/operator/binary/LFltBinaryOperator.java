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
 * Non-throwing functional interface (lambda) LFltBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): float a1,float a2
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aFloat>, Domain2<aFloat, aFloat> { // NOSONAR

	String DESCRIPTION = "LFltBinaryOperator: float applyAsFlt(float a1,float a2)";

	// float applyAsFlt(float a1,float a2) ;
	default float applyAsFlt(float a1, float a2) {
		// return nestingApplyAsFlt(a1,a2);
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(float a1,float a2)
	 */
	float applyAsFltX(float a1, float a2) throws Throwable;

	default float tupleApplyAsFlt(LFltPair args) {
		return applyAsFlt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(float a1, float a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsFlt(a1, a2, handling);
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LFltBinaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsFlt(a1, a2, exF, newMessage, messageParams);
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LFltBinaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsFlt(a1, a2, exF);
	}

	default float applyAsFltThen(float a1, float a2, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LFltBinaryOperator tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return (a1, a2) -> applyAsFltThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(float a1, float a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(float a1, float a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float handlingApplyAsFlt(float a1, float a2, LFltBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a1, a2, handling);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a1, a2);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, exF, newMessage, messageParams);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, exF);
	}

	static float tryApplyAsFltThen(float a1, float a2, LFltBinaryOperator func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a1, a2, handler);
	}

	default float failSafeApplyAsFlt(float a1, float a2, @Nonnull LFltBinaryOperator failSafe) {
		try {
			return applyAsFlt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a1, a2);
		}
	}

	static float failSafeApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull LFltBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a1, a2);
		} else {
			return func.failSafeApplyAsFlt(a1, a2, failSafe);
		}
	}

	static LFltBinaryOperator failSafe(LFltBinaryOperator func, @Nonnull LFltBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsFlt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(float a1, float a2) {
		return applyAsFlt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a1, float a2, LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a1, float a2, LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a1, float a2, LFltBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	public default LFltUnaryOperator lShrink(LFltUnaryOperator left) {
		return a2 -> applyAsFlt(left.applyAsFlt(a2), a2);
	}

	public default LFltUnaryOperator lShrinkc(float a1) {
		return a2 -> applyAsFlt(a1, a2);
	}

	public static LFltUnaryOperator lShrinked(LFltUnaryOperator left, LFltBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LFltUnaryOperator lShrinkedc(float a1, LFltBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LFltUnaryOperator rShrink(LFltUnaryOperator right) {
		return a1 -> applyAsFlt(a1, right.applyAsFlt(a1));
	}

	public default LFltUnaryOperator rShrinkc(float a2) {
		return a1 -> applyAsFlt(a1, a2);
	}

	public static LFltUnaryOperator rShrinked(LFltUnaryOperator right, LFltBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LFltUnaryOperator rShrinkedc(float a2, LFltBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LFltBinaryOperator uncurry(LFltFunction<LFltUnaryOperator> func) {
		return (float a1, float a2) -> func.apply(a1).applyAsFlt(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier capture(float a1, float a2) {
		return () -> this.applyAsFlt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LFltBinaryOperator constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LFltBinaryOperator apply1stAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2) -> func.applyAsFlt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LFltBinaryOperator apply2ndAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2) -> func.applyAsFlt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltBinaryOperator fltBinaryOp(final @Nonnull LFltBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltBinaryOperator recursive(final @Nonnull LFunction<LFltBinaryOperator, LFltBinaryOperator> selfLambda) {
		final LFltBinaryOperatorSingle single = new LFltBinaryOperatorSingle();
		LFltBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LFltBinaryOperatorSingle implements LSingle<LFltBinaryOperator>, LFltBinaryOperator {
		private LFltBinaryOperator target = null;

		@Override
		public float applyAsFltX(float a1, float a2) throws Throwable {
			return target.applyAsFltX(a1, a2);
		}

		@Override
		public LFltBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LFltBinaryOperator fltBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltBinaryOperator fltBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static float call(float a1, float a2, final @Nonnull LFltBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static LFltBinaryOperator safe() {
		return LFltBinaryOperator::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltBinaryOperator safe(final @Nullable LFltBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltBinaryOperator> safeSupplier(final @Nullable LSupplier<LFltBinaryOperator> supplier) {
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
	static LFltBinaryOperator minBy(@Nonnull Comparator<Float> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LFltBinaryOperator maxBy(@Nonnull Comparator<Float> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LFltBinaryOperator min() {
		return Float::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LFltBinaryOperator max() {
		return Float::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFltBinaryOperator compose(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static LFltBinaryOperator composed(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, LFltBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToFltBiFunction<V1, V2> fltBinaryOpCompose(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static <V1, V2> LToFltBiFunction<V1, V2> composed(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2, LFltBinaryOperator after) {
		return after.fltBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFltFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltBinaryOperator thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiFltPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsFlt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltBinaryOperator) Operator */
	public static float produceFloat(float a1, float a2) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aFloat> ia1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aFloat> ia1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

}
