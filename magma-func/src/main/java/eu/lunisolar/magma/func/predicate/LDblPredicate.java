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
 * Non-throwing functional interface (lambda) LDblPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblPredicate extends DoublePredicate, MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblPredicate: boolean doTest(double a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblPredicate interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(double a) {
		return this.doTest(a);
	}

	// boolean doTest(double a) ;
	default boolean doTest(double a) {
		// return nestingDoTest(a);
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(double a)
	 */
	boolean doTestX(double a) throws Throwable;

	default boolean tupleTest(LDblSingle args) {
		return doTest(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(double a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(double a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(double a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(double a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(double a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoTest(double a, LDblPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a, handling);
	}

	static boolean tryDoTest(double a, LDblPredicate func) {
		return tryDoTest(a, func, null);
	}

	static boolean tryDoTest(double a, LDblPredicate func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoTest(double a, LDblPredicate func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory);
	}

	static boolean tryDoTestThen(double a, LDblPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a, handler);
	}

	default boolean failSafeDoTest(double a, @Nonnull LDblPredicate failSafe) {
		try {
			return doTest(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a);
		}
	}

	static boolean failSafeDoTest(double a, LDblPredicate func, @Nonnull LDblPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a);
		} else {
			return func.failSafeDoTest(a, failSafe);
		}
	}

	static LDblPredicate failSafeDblPred(LDblPredicate func, @Nonnull LDblPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoTest(a, func, failSafe);
	}

	default boolean doIf(double a, LAction action) {
		if (doTest(a)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(double a, LDblConsumer consumer) {
		if (doTest(a)) {
			consumer.doAccept(a);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(double a, LDblPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(double a, LDblPredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(double a) {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double a) {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, double a2, LObjDblConsumer<V> consumer) {
		if (doTest(a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, double a3, LTieDblConsumer<? super V> consumer) {
		if (doTest(a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, double a3, LTieDblFunction<? super V> consumer) {
		if (doTest(a3)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, LDblPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doTest(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doTest(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, LDblPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doTest(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doTest(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, LDblPredicate func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureDblPred(double a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static LDblPredicate constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblPredicate dblPred(final @Nonnull LDblPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblPredicate recursive(final @Nonnull LFunction<LDblPredicate, LDblPredicate> selfLambda) {
		final LDblPredicateSingle single = new LDblPredicateSingle();
		LDblPredicate func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblPredicateSingle implements LSingle<LDblPredicate>, LDblPredicate {
		private LDblPredicate target = null;

		@Override
		public boolean doTestX(double a) throws Throwable {
			return target.doTestX(a);
		}

		@Override
		public LDblPredicate value() {
			return target;
		}
	}

	@Nonnull
	static LDblPredicate dblPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblPredicate dblPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static boolean call(double a, final @Nonnull LDblPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblPredicate wrap(final DoublePredicate other) {
		return other::test;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LDblPredicate safe() {
		return LDblPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblPredicate safe(final @Nullable LDblPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblPredicate> safeSupplier(final @Nullable LSupplier<LDblPredicate> supplier) {
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
	default LDblPredicate negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LDblPredicate and(@Nonnull LDblPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LDblPredicate or(@Nonnull LDblPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LDblPredicate xor(@Nonnull LDblPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LDblPredicate isEqual(double target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblPredicate dblPredComposeDbl(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsDbl(v));
	}

	public static LDblPredicate composedDbl(@Nonnull final LDblUnaryOperator before, LDblPredicate after) {
		return after.dblPredComposeDbl(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> dblPredCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsDbl(v));
	}

	public static <V> LPredicate<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblPredicate after) {
		return after.dblPredCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> boolToDblFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction boolToDblToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction boolToDblToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction boolToDblToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction boolToDblToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction boolToDblToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator boolToDblUnaryOp(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction boolToDblToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate boolToDblPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblPredicate nestingDblPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblPredicate shovingDblPred() {
		return this;
	}

	// </editor-fold>

	// >>> LDblPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			doIf(a, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			doIf(a, consumer);
		}
	}

	// FILTER_WITH_TARGET_AND_INDEX: FOR, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LTieDblConsumer<V>
	// consumer, type=CONST}]
	default <V, C0> int tieForEach(V v, IndexedRead<C0, aDouble> ia, C0 source, LTieDblConsumer<V> consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	// FILTER_WITH_TARGET_AND_INDEX: WHILE, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LTieDblConsumer<V>
	// consumer, type=CONST}]
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, aDouble> sa, C0 source, LTieDblConsumer<V> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
