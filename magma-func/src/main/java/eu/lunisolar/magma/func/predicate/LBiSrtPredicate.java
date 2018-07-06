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
 * Non-throwing functional interface (lambda) LBiSrtPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): short a1,short a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiSrtPredicate extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiSrtPredicate: boolean doTest(short a1,short a2)";

	// boolean doTest(short a1,short a2) ;
	default boolean doTest(short a1, short a2) {
		// return nestingDoTest(a1,a2);
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(short a1,short a2)
	 */
	boolean doTestX(short a1, short a2) throws Throwable;

	default boolean tupleTest(LSrtPair args) {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(short a1, short a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(short a1, short a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(short a1, short a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(short a1, short a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(short a1, short a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoTest(short a1, short a2, LBiSrtPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, handling);
	}

	static boolean tryDoTest(short a1, short a2, LBiSrtPredicate func) {
		return tryDoTest(a1, a2, func, null);
	}

	static boolean tryDoTest(short a1, short a2, LBiSrtPredicate func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoTest(short a1, short a2, LBiSrtPredicate func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory);
	}

	static boolean tryDoTestThen(short a1, short a2, LBiSrtPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, handler);
	}

	default boolean failSafeDoTest(short a1, short a2, @Nonnull LBiSrtPredicate failSafe) {
		try {
			return doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2);
		}
	}

	static boolean failSafeDoTest(short a1, short a2, LBiSrtPredicate func, @Nonnull LBiSrtPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2);
		} else {
			return func.failSafeDoTest(a1, a2, failSafe);
		}
	}

	static LBiSrtPredicate failSafeBiSrtPred(LBiSrtPredicate func, @Nonnull LBiSrtPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoTest(a1, a2, func, failSafe);
	}

	default boolean doIf(short a1, short a2, LAction action) {
		if (doTest(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(short a1, short a2, LBiSrtConsumer consumer) {
		if (doTest(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(short a1, short a2, LBiSrtPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(short a1, short a2, LBiSrtPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(short a1, short a2) {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(short a1, short a2) {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiSrtPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, LBiSrtPredicate func) {
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
	public static void fromTill(int min_i, int max_i, short a1, short a2, LBiSrtPredicate func) {
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
	public static void times(int max_i, short a1, short a2, LBiSrtPredicate func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LSrtPredicate lShrink(LSrtUnaryOperator left) {
		return a2 -> doTest(left.doApplyAsSrt(a2), a2);
	}

	public default LSrtPredicate lShrinkc(short a1) {
		return a2 -> doTest(a1, a2);
	}

	public static LSrtPredicate lShrinked(LSrtUnaryOperator left, LBiSrtPredicate func) {
		return func.lShrink(left);
	}

	public static LSrtPredicate lShrinkedc(short a1, LBiSrtPredicate func) {
		return func.lShrinkc(a1);
	}

	public default LSrtPredicate rShrink(LSrtUnaryOperator right) {
		return a1 -> doTest(a1, right.doApplyAsSrt(a1));
	}

	public default LSrtPredicate rShrinkc(short a2) {
		return a1 -> doTest(a1, a2);
	}

	public static LSrtPredicate rShrinked(LSrtUnaryOperator right, LBiSrtPredicate func) {
		return func.rShrink(right);
	}

	public static LSrtPredicate rShrinkedc(short a2, LBiSrtPredicate func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiSrtPredicate uncurryBiSrtPred(LSrtFunction<LSrtPredicate> func) {
		return (short a1, short a2) -> func.doApply(a1).doTest(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBiSrtPred(short a1, short a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LBiSrtPredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiSrtPredicate test1st(@Nonnull LSrtPredicate func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiSrtPredicate test2nd(@Nonnull LSrtPredicate func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiSrtPredicate biSrtPred(final @Nonnull LBiSrtPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiSrtPredicate recursive(final @Nonnull LFunction<LBiSrtPredicate, LBiSrtPredicate> selfLambda) {
		final LBiSrtPredicateSingle single = new LBiSrtPredicateSingle();
		LBiSrtPredicate func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiSrtPredicateSingle implements LSingle<LBiSrtPredicate>, LBiSrtPredicate {
		private LBiSrtPredicate target = null;

		@Override
		public boolean doTestX(short a1, short a2) throws Throwable {
			return target.doTestX(a1, a2);
		}

		@Override
		public LBiSrtPredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBiSrtPredicate biSrtPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiSrtPredicate biSrtPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrt1Srt0Pred srt1Srt0Pred(final @Nonnull LSrt1Srt0Pred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(short a1, short a2, final @Nonnull LBiSrtPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBiSrtPredicate safe() {
		return LBiSrtPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiSrtPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiSrtPredicate safe(final @Nullable LBiSrtPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiSrtPredicate> safeSupplier(final @Nullable LSupplier<LBiSrtPredicate> supplier) {
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
	default LBiSrtPredicate negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiSrtPredicate and(@Nonnull LBiSrtPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiSrtPredicate or(@Nonnull LBiSrtPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiSrtPredicate xor(@Nonnull LBiSrtPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBiSrtPredicate isEqual(short v1, short v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiSrtPredicate biSrtPredComposeSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static LBiSrtPredicate composedSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, LBiSrtPredicate after) {
		return after.biSrtPredComposeSrt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> biSrtPredCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, LBiSrtPredicate after) {
		return after.biSrtPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiSrtFunction<V> boolToBiSrtFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtBinaryOperator boolToSrtBinaryOp(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtPredicate boolToBiSrtPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiSrtPredicate nestingBiSrtPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiSrtPredicate shovingBiSrtPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiSrtPredicate for method references. */
	@FunctionalInterface
	interface LSrt1Srt0Pred extends LBiSrtPredicate {

		boolean doTestSrt1Srt0(short a2, short a1);

		@Override
		default boolean doTestX(short a1, short a2) {
			return this.doTestSrt1Srt0(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiSrtPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1, short a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1, short a2) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LBiSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LBiSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LBiSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LBiSrtConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LBiSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
