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
 * Non-throwing functional interface (lambda) LPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: boolean
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicate<T> extends Predicate<T>, MetaPredicate, MetaInterface.NonThrowing, OFunction<T, aBool> { // NOSONAR

	String DESCRIPTION = "LPredicate: boolean doTest(T a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LPredicate interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean test(T a) {
		return this.doTest(a);
	}

	// boolean doTest(T a) ;
	default boolean doTest(T a) {
		// return nestingDoTest(a);
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T a)
	 */
	boolean doTestX(T a) throws Throwable;

	default boolean tupleTest(LSingle<T> args) {
		return doTest(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingDoTest(T a, LPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a, handling);
	}

	static <T> boolean tryDoTest(T a, LPredicate<T> func) {
		return tryDoTest(a, func, null);
	}

	static <T> boolean tryDoTest(T a, LPredicate<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory, newMessage, messageParams);
	}

	static <T> boolean tryDoTest(T a, LPredicate<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory);
	}

	static <T> boolean tryDoTestThen(T a, LPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a, handler);
	}

	default boolean failSafeDoTest(T a, @Nonnull LPredicate<T> failSafe) {
		try {
			return doTest(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a);
		}
	}

	static <T> boolean failSafeDoTest(T a, LPredicate<T> func, @Nonnull LPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a);
		} else {
			return func.failSafeDoTest(a, failSafe);
		}
	}

	static <T> LPredicate<T> failSafePred(LPredicate<T> func, @Nonnull LPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoTest(a, func, failSafe);
	}

	default boolean doIf(T a, LAction action) {
		if (doTest(a)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T a, LConsumer<? super T> consumer) {
		if (doTest(a)) {
			consumer.doAccept(a);
			return true;
		} else {
			return false;
		}
	}

	static <T> void throwIf(T a, LPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T> void throwIfNot(T a, LPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a) {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a) {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, T a2, LBiConsumer<V, ? super T> consumer) {
		if (doTest(a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	/** 2 */
	public default <V> int doIf(V a1, T a2, LToIntBiFunction<V, ? super T> consumer) {
		if (doTest(a2)) {
			return consumer.doApplyAsInt(a1, a2);
		} else {
			return 0;
		}
	}

	public default <V> boolean doIf(V a1, T a2, int a3, LBiObjIntConsumer<? super V, ? super T> consumer) {
		if (doTest(a2)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, T a3, LTieConsumer<? super V, ? super T> consumer) {
		if (doTest(a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, T a3, LTieFunction<? super V, ? super T> consumer) {
		if (doTest(a3)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LPredicate<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a, LPredicate<T> func) {
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
	public static <T> void times(int max_i, T a, LPredicate<T> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capturePred(T a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LPredicate<T> constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LPredicate<T> pred(final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LPredicate<T> recursive(final @Nonnull LFunction<LPredicate<T>, LPredicate<T>> selfLambda) {
		final LPredicateSingle<T> single = new LPredicateSingle();
		LPredicate<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LPredicateSingle<T> implements LSingle<LPredicate<T>>, LPredicate<T> {
		private LPredicate<T> target = null;

		@Override
		public boolean doTestX(T a) throws Throwable {
			return target.doTestX(a);
		}

		@Override
		public LPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LPredicate<T> predThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LPredicate<T> predThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> boolean call(T a, final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LPredicate<T> wrap(final Predicate<T> other) {
		return other::test;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LPredicate<T> safe() {
		return LPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LPredicate<T> safe(final @Nullable LPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LPredicate<T>> safeSupplier(final @Nullable LSupplier<LPredicate<T>> supplier) {
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
	default LPredicate<T> negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicate<T> and(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicate<T> or(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicate<T> xor(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LPredicate<T> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> predCompose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApply(v));
	}

	public static <V, T> LPredicate<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LPredicate<T> after) {
		return after.predCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> boolToFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> boolToToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> boolToToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> boolToToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> boolToToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> boolToToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> boolToToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> boolToToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> boolToPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LPredicate<T> nestingPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shovingPred() {
		return this;
	}

	// </editor-fold>

	// >>> LPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LConsumer<? super T> consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			doIf(a, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LConsumer<? super T> consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			doIf(a, consumer);
		}
	}

	// FILTER_WITH_TARGET_AND_INDEX: FOR, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LBiObjIntConsumer<V,T>
	// consumer, type=CONST}]
	default <V, C0> int targetedForEach(V v, IndexedRead<C0, a<T>> ia, C0 source, LBiObjIntConsumer<V, T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			acceptedIndex += doIf(v, a, acceptedIndex, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	// FILTER_WITH_TARGET_AND_INDEX: WHILE, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LBiObjIntConsumer<V,T>
	// consumer, type=CONST}]
	default <V, C0, I0> int targetedIterate(V v, SequentialRead<C0, I0, a<T>> sa, C0 source, LBiObjIntConsumer<V, T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			acceptedIndex += doIf(v, a, acceptedIndex, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

	// FILTER_WITH_TARGET_AND_INDEX: FOR, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LTieConsumer<V,T> consumer,
	// type=CONST}]
	default <V, C0> int tieForEach(V v, IndexedRead<C0, a<T>> ia, C0 source, LTieConsumer<V, T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	// FILTER_WITH_TARGET_AND_INDEX: WHILE, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LTieConsumer<V,T> consumer,
	// type=CONST}]
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, a<T>> sa, C0 source, LTieConsumer<V, T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
