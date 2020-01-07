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
 * Non-throwing functional interface (lambda) LCharBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): char a1,char a2
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aChar>, Domain2<aChar, aChar> { // NOSONAR

	String DESCRIPTION = "LCharBinaryOperator: char applyAsChar(char a1,char a2)";

	// char applyAsChar(char a1,char a2) ;
	default char applyAsChar(char a1, char a2) {
		// return nestingApplyAsChar(a1,a2);
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(char a1,char a2)
	 */
	char applyAsCharX(char a1, char a2) throws Throwable;

	default char tupleApplyAsChar(LCharPair args) {
		return applyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(char a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsChar(a1, a2, handling);
	}

	default char applyAsChar(char a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LCharBinaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsChar(a1, a2, exF, newMessage, messageParams);
	}

	default char applyAsChar(char a1, char a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LCharBinaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsChar(a1, a2, exF);
	}

	default char applyAsCharThen(char a1, char a2, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LCharBinaryOperator tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return (a1, a2) -> applyAsCharThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(char a1, char a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(char a1, char a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char handlingApplyAsChar(char a1, char a2, LCharBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a1, a2, handling);
	}

	static char tryApplyAsChar(char a1, char a2, LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a1, a2);
	}

	static char tryApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, exF, newMessage, messageParams);
	}

	static char tryApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, exF);
	}

	static char tryApplyAsCharThen(char a1, char a2, LCharBinaryOperator func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a1, a2, handler);
	}

	default char failSafeApplyAsChar(char a1, char a2, @Nonnull LCharBinaryOperator failSafe) {
		try {
			return applyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsChar(a1, a2);
		}
	}

	static char failSafeApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull LCharBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsChar(a1, a2);
		} else {
			return func.failSafeApplyAsChar(a1, a2, failSafe);
		}
	}

	static LCharBinaryOperator failSafe(LCharBinaryOperator func, @Nonnull LCharBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsChar(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(char a1, char a2) {
		return applyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a1, char a2, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a1, char a2, @Nonnull LCharBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LCharUnaryOperator lShrink(@Nonnull LCharUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsChar(left.applyAsChar(a2), a2);
	}

	default LCharUnaryOperator lShrink_(char a1) {
		return a2 -> applyAsChar(a1, a2);
	}

	public static LCharUnaryOperator lShrunken(@Nonnull LCharUnaryOperator left, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LCharUnaryOperator lShrunken_(char a1, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LCharUnaryOperator rShrink(@Nonnull LCharUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsChar(a1, right.applyAsChar(a1));
	}

	default LCharUnaryOperator rShrink_(char a2) {
		return a1 -> applyAsChar(a1, a2);
	}

	public static LCharUnaryOperator rShrunken(@Nonnull LCharUnaryOperator right, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LCharUnaryOperator rShrunken_(char a2, @Nonnull LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LCharBinaryOperator uncurry(@Nonnull LCharFunction<LCharUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (char a1, char a2) -> func.apply(a1).applyAsChar(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiCharConsumer toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LCharBinaryOperator beforeDo(@Nonnull LBiCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a1, char a2) -> {
			before.accept(a1, a2);
			return applyAsChar(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LCharBinaryOperator afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (char a1, char a2) -> {
			final char retval = applyAsChar(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier capture(char a1, char a2) {
		return () -> this.applyAsChar(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LCharBinaryOperator constant(char r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LCharBinaryOperator apply1stAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2) -> func.applyAsChar(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LCharBinaryOperator apply2ndAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2) -> func.applyAsChar(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharBinaryOperator charBinaryOp(final @Nonnull LCharBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LCharBinaryOperator recursive(final @Nonnull LFunction<LCharBinaryOperator, LCharBinaryOperator> selfLambda) {
		final LCharBinaryOperatorSingle single = new LCharBinaryOperatorSingle();
		LCharBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LCharBinaryOperatorSingle implements LCharBinaryOperator {
		private LCharBinaryOperator target = null;

		@Override
		public char applyAsCharX(char a1, char a2) throws Throwable {
			return target.applyAsCharX(a1, a2);
		}

	}

	@Nonnull
	static LCharBinaryOperator charBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharBinaryOperator charBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static char call(char a1, char a2, final @Nonnull LCharBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LCharBinaryOperator safe() {
		return LCharBinaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharBinaryOperator safe(final @Nullable LCharBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharBinaryOperator> safeSupplier(final @Nullable LSupplier<LCharBinaryOperator> supplier) {
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
	static LCharBinaryOperator minBy(@Nonnull Comparator<Character> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LCharBinaryOperator maxBy(@Nonnull Comparator<Character> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LCharBinaryOperator min() {
		return (a, b) -> (a <= b) ? a : b;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LCharBinaryOperator max() {
		return (a, b) -> (a >= b) ? a : b;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharBinaryOperator compose(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsChar(before1.applyAsChar(v1), before2.applyAsChar(v2));
	}

	public static LCharBinaryOperator composed(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, LCharBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToCharBiFunction<V1, V2> charBinaryOpCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsChar(before1.applyAsChar(v1), before2.applyAsChar(v2));
	}

	public static <V1, V2> LToCharBiFunction<V1, V2> composed(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, LCharBinaryOperator after) {
		return after.charBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiCharFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharBinaryOperator thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiCharPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharBinaryOperator) Operator */
	public static char doNothing(char a1, char a2) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(this.applyAsChar(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(this.applyAsChar(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(this.applyAsChar(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(this.applyAsChar(a1, a2));
		}
	}

}
