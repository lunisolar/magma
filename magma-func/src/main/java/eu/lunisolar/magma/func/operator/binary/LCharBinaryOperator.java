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
public interface LCharBinaryOperator extends MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LCharBinaryOperator: char doApplyAsChar(char a1,char a2)";

	// char doApplyAsChar(char a1,char a2) ;
	default char doApplyAsChar(char a1, char a2) {
		// return nestingDoApplyAsChar(a1,a2);
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsChar(char a1,char a2)
	 */
	char doApplyAsCharX(char a1, char a2) throws Throwable;

	default char tupleApplyAsChar(LCharPair args) {
		return doApplyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingDoApplyAsChar(char a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default char tryDoApplyAsChar(char a1, char a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default char tryDoApplyAsChar(char a1, char a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default char tryDoApplyAsCharThen(char a1, char a2, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsChar(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(char a1, char a2) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(char a1, char a2) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char handlingDoApplyAsChar(char a1, char a2, LCharBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsChar(a1, a2, handling);
	}

	static char tryDoApplyAsChar(char a1, char a2, LCharBinaryOperator func) {
		return tryDoApplyAsChar(a1, a2, func, null);
	}

	static char tryDoApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static char tryDoApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a1, a2, exceptionFactory);
	}

	static char tryDoApplyAsCharThen(char a1, char a2, LCharBinaryOperator func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsCharThen(a1, a2, handler);
	}

	default char failSafeDoApplyAsChar(char a1, char a2, @Nonnull LCharBinaryOperator failSafe) {
		try {
			return doApplyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsChar(a1, a2);
		}
	}

	static char failSafeDoApplyAsChar(char a1, char a2, LCharBinaryOperator func, @Nonnull LCharBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsChar(a1, a2);
		} else {
			return func.failSafeDoApplyAsChar(a1, a2, failSafe);
		}
	}

	static LCharBinaryOperator failSafeCharBinaryOp(LCharBinaryOperator func, @Nonnull LCharBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsChar(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(char a1, char a2) {
		return doApplyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a1, char a2, LCharBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a1, char a2, LCharBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LCharUnaryOperator lShrink(LCharUnaryOperator left) {
		return a2 -> doApplyAsChar(left.doApplyAsChar(a2), a2);
	}

	public default LCharUnaryOperator lShrinkc(char a1) {
		return a2 -> doApplyAsChar(a1, a2);
	}

	public static LCharUnaryOperator lShrinked(LCharUnaryOperator left, LCharBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LCharUnaryOperator lShrinkedc(char a1, LCharBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LCharUnaryOperator rShrink(LCharUnaryOperator right) {
		return a1 -> doApplyAsChar(a1, right.doApplyAsChar(a1));
	}

	public default LCharUnaryOperator rShrinkc(char a2) {
		return a1 -> doApplyAsChar(a1, a2);
	}

	public static LCharUnaryOperator rShrinked(LCharUnaryOperator right, LCharBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LCharUnaryOperator rShrinkedc(char a2, LCharBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LCharBinaryOperator uncurryCharBinaryOp(LCharFunction<LCharUnaryOperator> func) {
		return (char a1, char a2) -> func.doApply(a1).doApplyAsChar(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier captureCharBinaryOp(char a1, char a2) {
		return () -> this.doApplyAsChar(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LCharBinaryOperator constant(char r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LCharBinaryOperator apply1stAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsChar(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LCharBinaryOperator apply2ndAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsChar(a2);
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
		LCharBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LCharBinaryOperatorSingle implements LSingle<LCharBinaryOperator>, LCharBinaryOperator {
		private LCharBinaryOperator target = null;

		@Override
		public char doApplyAsCharX(char a1, char a2) throws Throwable {
			return target.doApplyAsCharX(a1, a2);
		}

		@Override
		public LCharBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LCharBinaryOperator charBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LCharBinaryOperator charBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static char call(char a1, char a2, final @Nonnull LCharBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceChar). */
	@Nonnull
	static LCharBinaryOperator safe() {
		return LCharBinaryOperator::produceChar;
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
	default LCharBinaryOperator charBinaryOpComposeChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsChar(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	public static LCharBinaryOperator composedChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, LCharBinaryOperator after) {
		return after.charBinaryOpComposeChar(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToCharBiFunction<V1, V2> charBinaryOpCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsChar(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
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
		return (a1, a2) -> after.doApply(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharBinaryOperator thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiCharPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharBinaryOperator nestingCharBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharBinaryOperator shovingCharBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LCharBinaryOperator) Operator */
	public static char produceChar(char a1, char a2) {
		return Function4U.defaultCharacter;
	}

	// MAP: FOR, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
		}
	}

}
