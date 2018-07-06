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
 * Non-throwing functional interface (lambda) LBiIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): int a1,int a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiIntPredicate extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiIntPredicate: boolean doTest(int a1,int a2)";

	// boolean doTest(int a1,int a2) ;
	default boolean doTest(int a1, int a2) {
		// return nestingDoTest(a1,a2);
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(int a1,int a2)
	 */
	boolean doTestX(int a1, int a2) throws Throwable;

	default boolean tupleTest(LIntPair args) {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(int a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(int a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(int a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(int a1, int a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(int a1, int a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(int a1, int a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoTest(int a1, int a2, LBiIntPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, handling);
	}

	static boolean tryDoTest(int a1, int a2, LBiIntPredicate func) {
		return tryDoTest(a1, a2, func, null);
	}

	static boolean tryDoTest(int a1, int a2, LBiIntPredicate func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoTest(int a1, int a2, LBiIntPredicate func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory);
	}

	static boolean tryDoTestThen(int a1, int a2, LBiIntPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, handler);
	}

	default boolean failSafeDoTest(int a1, int a2, @Nonnull LBiIntPredicate failSafe) {
		try {
			return doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2);
		}
	}

	static boolean failSafeDoTest(int a1, int a2, LBiIntPredicate func, @Nonnull LBiIntPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2);
		} else {
			return func.failSafeDoTest(a1, a2, failSafe);
		}
	}

	static LBiIntPredicate failSafeBiIntPred(LBiIntPredicate func, @Nonnull LBiIntPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoTest(a1, a2, func, failSafe);
	}

	default boolean doIf(int a1, int a2, LAction action) {
		if (doTest(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(int a1, int a2, LBiIntConsumer consumer) {
		if (doTest(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(int a1, int a2, LBiIntPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(int a1, int a2, LBiIntPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(int a1, int a2) {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(int a1, int a2) {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiIntPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, int a2, int a3, LTieIntConsumer<V> consumer) {
		if (doTest(a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIfReverse(V a1, int a2, int a3, LTieIntConsumer<? super V> consumer) {
		if (doTest(a3, a2)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIfReverse(V a1, int a2, int a3, LTieIntFunction<? super V> consumer) {
		if (doTest(a3, a2)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** 2 */
	public default <V> int doIf(V a1, int a2, int a3, LTieIntFunction<V> consumer) {
		if (doTest(a2, a3)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, int a1, int a2, LBiIntPredicate func) {
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
	public static void fromTill(int min_i, int max_i, int a1, int a2, LBiIntPredicate func) {
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
	public static void times(int max_i, int a1, int a2, LBiIntPredicate func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntPredicate lShrink(LIntUnaryOperator left) {
		return a2 -> doTest(left.doApplyAsInt(a2), a2);
	}

	public default LIntPredicate lShrinkc(int a1) {
		return a2 -> doTest(a1, a2);
	}

	public static LIntPredicate lShrinked(LIntUnaryOperator left, LBiIntPredicate func) {
		return func.lShrink(left);
	}

	public static LIntPredicate lShrinkedc(int a1, LBiIntPredicate func) {
		return func.lShrinkc(a1);
	}

	public default LIntPredicate rShrink(LIntUnaryOperator right) {
		return a1 -> doTest(a1, right.doApplyAsInt(a1));
	}

	public default LIntPredicate rShrinkc(int a2) {
		return a1 -> doTest(a1, a2);
	}

	public static LIntPredicate rShrinked(LIntUnaryOperator right, LBiIntPredicate func) {
		return func.rShrink(right);
	}

	public static LIntPredicate rShrinkedc(int a2, LBiIntPredicate func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LBiIntPredicate uncurryBiIntPred(LIntFunction<LIntPredicate> func) {
		return (int a1, int a2) -> func.doApply(a1).doTest(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBiIntPred(int a1, int a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LBiIntPredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiIntPredicate test1st(@Nonnull LIntPredicate func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiIntPredicate test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiIntPredicate biIntPred(final @Nonnull LBiIntPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiIntPredicate recursive(final @Nonnull LFunction<LBiIntPredicate, LBiIntPredicate> selfLambda) {
		final LBiIntPredicateSingle single = new LBiIntPredicateSingle();
		LBiIntPredicate func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiIntPredicateSingle implements LSingle<LBiIntPredicate>, LBiIntPredicate {
		private LBiIntPredicate target = null;

		@Override
		public boolean doTestX(int a1, int a2) throws Throwable {
			return target.doTestX(a1, a2);
		}

		@Override
		public LBiIntPredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBiIntPredicate biIntPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBiIntPredicate biIntPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LInt1Int0Pred int1Int0Pred(final @Nonnull LInt1Int0Pred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(int a1, int a2, final @Nonnull LBiIntPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBiIntPredicate safe() {
		return LBiIntPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiIntPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiIntPredicate safe(final @Nullable LBiIntPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiIntPredicate> safeSupplier(final @Nullable LSupplier<LBiIntPredicate> supplier) {
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
	default LBiIntPredicate negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiIntPredicate and(@Nonnull LBiIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiIntPredicate or(@Nonnull LBiIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiIntPredicate xor(@Nonnull LBiIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBiIntPredicate isEqual(int v1, int v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiIntPredicate biIntPredComposeInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static LBiIntPredicate composedInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LBiIntPredicate after) {
		return after.biIntPredComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> biIntPredCompose(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LBiIntPredicate after) {
		return after.biIntPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiIntFunction<V> boolToBiIntFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntBinaryOperator boolToIntBinaryOp(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiIntPredicate boolToBiIntPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiIntPredicate nestingBiIntPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiIntPredicate shovingBiIntPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiIntPredicate for method references. */
	@FunctionalInterface
	interface LInt1Int0Pred extends LBiIntPredicate {

		boolean doTestInt1Int0(int a2, int a1);

		@Override
		default boolean doTestX(int a1, int a2) {
			return this.doTestInt1Int0(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiIntPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(int a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int a1, int a2) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aInt> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LBiIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aInt> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LBiIntConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LBiIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
