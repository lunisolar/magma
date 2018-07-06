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

package eu.lunisolar.magma.func.predicate;

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
 * Non-throwing functional interface (lambda) LBiCharPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): char a1,char a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiCharPredicate extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiCharPredicate: boolean doTest(char a1,char a2)";

	// boolean doTest(char a1,char a2) ;
	default boolean doTest(char a1, char a2) {
		// return nestingDoTest(a1,a2);
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(char a1,char a2)
	 */
	boolean doTestX(char a1, char a2) throws Throwable;

	default boolean tupleTest(LCharPair args) {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(char a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(char a1, char a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(char a1, char a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(char a1, char a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(char a1, char a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(char a1, char a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoTest(char a1, char a2, LBiCharPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, handling);
	}

	static boolean tryDoTest(char a1, char a2, LBiCharPredicate func) {
		return tryDoTest(a1, a2, func, null);
	}

	static boolean tryDoTest(char a1, char a2, LBiCharPredicate func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoTest(char a1, char a2, LBiCharPredicate func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory);
	}

	static boolean tryDoTestThen(char a1, char a2, LBiCharPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, handler);
	}

	default boolean failSafeDoTest(char a1, char a2, @Nonnull LBiCharPredicate failSafe) {
		try {
			return doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2);
		}
	}

	static boolean failSafeDoTest(char a1, char a2, LBiCharPredicate func, @Nonnull LBiCharPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2);
		} else {
			return func.failSafeDoTest(a1, a2, failSafe);
		}
	}

	static LBiCharPredicate failSafeBiCharPred(LBiCharPredicate func, @Nonnull LBiCharPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoTest(a1, a2, func, failSafe);
	}

	default boolean doIf(char a1, char a2, LAction action) {
		if (doTest(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(char a1, char a2, LBiCharConsumer consumer) {
		if (doTest(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(char a1, char a2, LBiCharPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(char a1, char a2, LBiCharPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(char a1, char a2) {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(char a1, char a2) {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiCharPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, LBiCharPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doTest(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doTest(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a1, char a2, LBiCharPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doTest(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doTest(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a1, char a2, LBiCharPredicate func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LCharPredicate lShrink(LCharUnaryOperator left) {
		return a2 -> doTest(left.doApplyAsChar(a2), a2);
	}

	public default LCharPredicate lShrinkc(char a1) {
		return a2 -> doTest(a1, a2);
	}

	public static LCharPredicate lShrinked(LCharUnaryOperator left, LBiCharPredicate func) {
		return func.lShrink(left);
	}

	public static LCharPredicate lShrinkedc(char a1, LBiCharPredicate func) {
		return func.lShrinkc(a1);
	}

	public default LCharPredicate rShrink(LCharUnaryOperator right) {
		return a1 -> doTest(a1, right.doApplyAsChar(a1));
	}

	public default LCharPredicate rShrinkc(char a2) {
		return a1 -> doTest(a1, a2);
	}

	public static LCharPredicate rShrinked(LCharUnaryOperator right, LBiCharPredicate func) {
		return func.rShrink(right);
	}

	public static LCharPredicate rShrinkedc(char a2, LBiCharPredicate func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiCharPredicate uncurryBiCharPred(LCharFunction<LCharPredicate> func) {
		return (char a1, char a2) -> func.doApply(a1).doTest(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBiCharPred(char a1, char a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LBiCharPredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiCharPredicate test1st(@Nonnull LCharPredicate func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiCharPredicate test2nd(@Nonnull LCharPredicate func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiCharPredicate biCharPred(final @Nonnull LBiCharPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiCharPredicate recursive(final @Nonnull LFunction<LBiCharPredicate, LBiCharPredicate> selfLambda) {
		final LBiCharPredicateSingle single = new LBiCharPredicateSingle();
		LBiCharPredicate func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiCharPredicateSingle implements LSingle<LBiCharPredicate>, LBiCharPredicate {
		private LBiCharPredicate target = null;

		@Override
		public boolean doTestX(char a1, char a2) throws Throwable {
			return target.doTestX(a1, a2);
		}

		@Override
		public LBiCharPredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBiCharPredicate biCharPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiCharPredicate biCharPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LChar1Char0Pred char1Char0Pred(final @Nonnull LChar1Char0Pred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(char a1, char a2, final @Nonnull LBiCharPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBiCharPredicate safe() {
		return LBiCharPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiCharPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiCharPredicate safe(final @Nullable LBiCharPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiCharPredicate> safeSupplier(final @Nullable LSupplier<LBiCharPredicate> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LBiCharPredicate negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiCharPredicate and(@Nonnull LBiCharPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiCharPredicate or(@Nonnull LBiCharPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiCharPredicate xor(@Nonnull LBiCharPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBiCharPredicate isEqual(char v1, char v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiCharPredicate biCharPredComposeChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	public static LBiCharPredicate composedChar(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, LBiCharPredicate after) {
		return after.biCharPredComposeChar(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> biCharPredCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsChar(v1), before2.doApplyAsChar(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, LBiCharPredicate after) {
		return after.biCharPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiCharFunction<V> boolToBiCharFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharBinaryOperator boolToCharBinaryOp(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiCharPredicate boolToBiCharPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiCharPredicate nestingBiCharPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiCharPredicate shovingBiCharPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiCharPredicate for method references. */
	@FunctionalInterface
	interface LChar1Char0Pred extends LBiCharPredicate {

		boolean doTestChar1Char0(char a2, char a1);

		@Override
		default boolean doTestX(char a1, char a2) {
			return this.doTestChar1Char0(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiCharPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a1, char a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a1, char a2) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LBiCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=IA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LBiCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = oiFunc2.doApplyAsChar(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=char a1, type=IA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LBiCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			char a1 = oiFunc1.doApplyAsChar(source1, i);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=char a1, type=SA}, SourcePurpose{arg=char a2, type=SA}, SourcePurpose{arg=LBiCharConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LBiCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			char a1 = nextFunc1.doApplyAsChar(iterator1);
			char a2 = nextFunc2.doApplyAsChar(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
