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
 * Non-throwing functional interface (lambda) LObjDblPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T a1,double a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjDblPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LObjDblPredicate: boolean doTest(T a1,double a2)";

	// boolean doTest(T a1,double a2) ;
	default boolean doTest(T a1, double a2) {
		// return nestingDoTest(a1,a2);
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T a1,double a2)
	 */
	boolean doTestX(T a1, double a2) throws Throwable;

	default boolean tupleTest(LObjDblPair<T> args) {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T a1, double a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T a1, double a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T a1, double a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T a1, double a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a1, double a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a1, double a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingDoTest(T a1, double a2, LObjDblPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, handling);
	}

	static <T> boolean tryDoTest(T a1, double a2, LObjDblPredicate<T> func) {
		return tryDoTest(a1, a2, func, null);
	}

	static <T> boolean tryDoTest(T a1, double a2, LObjDblPredicate<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T> boolean tryDoTest(T a1, double a2, LObjDblPredicate<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory);
	}

	static <T> boolean tryDoTestThen(T a1, double a2, LObjDblPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, handler);
	}

	default boolean failSafeDoTest(T a1, double a2, @Nonnull LObjDblPredicate<T> failSafe) {
		try {
			return doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2);
		}
	}

	static <T> boolean failSafeDoTest(T a1, double a2, LObjDblPredicate<T> func, @Nonnull LObjDblPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2);
		} else {
			return func.failSafeDoTest(a1, a2, failSafe);
		}
	}

	static <T> LObjDblPredicate<T> failSafeObjDblPred(LObjDblPredicate<T> func, @Nonnull LObjDblPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoTest(a1, a2, func, failSafe);
	}

	default boolean doIf(T a1, double a2, LAction action) {
		if (doTest(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T a1, double a2, LObjDblConsumer<? super T> consumer) {
		if (doTest(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static <T> void throwIf(T a1, double a2, LObjDblPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T> void throwIfNot(T a1, double a2, LObjDblPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a1, double a2) {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, double a2) {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjDblPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, T a2, double a3, LBiObjDblConsumer<V, ? super T> consumer) {
		if (doTest(a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, double a2, LObjDblPredicate<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a1, double a2, LObjDblPredicate<T> func) {
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
	public static <T> void times(int max_i, T a1, double a2, LObjDblPredicate<T> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LDblPredicate lShrink(LDblFunction<T> left) {
		return a2 -> doTest(left.doApply(a2), a2);
	}

	public default LDblPredicate lShrinkc(T a1) {
		return a2 -> doTest(a1, a2);
	}

	public static <T> LDblPredicate lShrinked(LDblFunction<T> left, LObjDblPredicate<T> func) {
		return func.lShrink(left);
	}

	public static <T> LDblPredicate lShrinkedc(T a1, LObjDblPredicate<T> func) {
		return func.lShrinkc(a1);
	}

	public default LPredicate<T> rShrink(LToDblFunction<T> right) {
		return a1 -> doTest(a1, right.doApplyAsDbl(a1));
	}

	public default LPredicate<T> rShrinkc(double a2) {
		return a1 -> doTest(a1, a2);
	}

	public static <T> LPredicate<T> rShrinked(LToDblFunction<T> right, LObjDblPredicate<T> func) {
		return func.rShrink(right);
	}

	public static <T> LPredicate<T> rShrinkedc(double a2, LObjDblPredicate<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LObjDblPredicate<T> uncurryObjDblPred(LFunction<T, LDblPredicate> func) {
		return (T a1, double a2) -> func.doApply(a1).doTest(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureObjDblPred(T a1, double a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjDblPredicate<T> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjDblPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjDblPredicate<T> test2nd(@Nonnull LDblPredicate func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjDblPredicate<T> objDblPred(final @Nonnull LObjDblPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjDblPredicate<T> recursive(final @Nonnull LFunction<LObjDblPredicate<T>, LObjDblPredicate<T>> selfLambda) {
		final LObjDblPredicateSingle<T> single = new LObjDblPredicateSingle();
		LObjDblPredicate<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LObjDblPredicateSingle<T> implements LSingle<LObjDblPredicate<T>>, LObjDblPredicate<T> {
		private LObjDblPredicate<T> target = null;

		@Override
		public boolean doTestX(T a1, double a2) throws Throwable {
			return target.doTestX(a1, a2);
		}

		@Override
		public LObjDblPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjDblPredicate<T> objDblPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LObjDblPredicate<T> objDblPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LDblObjPred<T> dblObjPred(final @Nonnull LDblObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, double a2, final @Nonnull LObjDblPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjDblPredicate<T> safe() {
		return LObjDblPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjDblPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjDblPredicate<T> safe(final @Nullable LObjDblPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjDblPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjDblPredicate<T>> supplier) {
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
	default LObjDblPredicate<T> negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjDblPredicate<T> and(@Nonnull LObjDblPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjDblPredicate<T> or(@Nonnull LObjDblPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjDblPredicate<T> xor(@Nonnull LObjDblPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjDblPredicate<T> isEqual(T v1, double v2) {
		return (a1, a2) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjDblPredicate<V1> objDblPredComposeDbl(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LDblUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsDbl(v2));
	}

	public static <V1, T> LObjDblPredicate<V1> composedDbl(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LDblUnaryOperator before2, LObjDblPredicate<T> after) {
		return after.objDblPredComposeDbl(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> objDblPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToDblFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsDbl(v2));
	}

	public static <V1, V2, T> LBiPredicate<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToDblFunction<? super V2> before2, LObjDblPredicate<T> after) {
		return after.objDblPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjDblFunction<T, V> boolToObjDblFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjDblPredicate<T> boolToObjDblPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjDblPredicate<T> nestingObjDblPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjDblPredicate<T> shovingObjDblPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjDblPredicate for method references. */
	@FunctionalInterface
	interface LDblObjPred<T> extends LObjDblPredicate<T> {

		boolean doTestDblObj(double a2, T a1);

		@Override
		default boolean doTestX(T a1, double a2) {
			return this.doTestDblObj(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LObjDblPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, double a2) {
		return false;
	}

	// >>> LDblObjPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(double a2, T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(double a2, T a1) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=double a2, type=IA}, SourcePurpose{arg=LObjDblConsumer<? super T> consumer,
	// type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LObjDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			double a2 = oiFunc2.doApplyAsDbl(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=double a2, type=IA}, SourcePurpose{arg=LObjDblConsumer<? super T> consumer,
	// type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LObjDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			double a2 = oiFunc2.doApplyAsDbl(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=double a2, type=SA}, SourcePurpose{arg=LObjDblConsumer<? super T> consumer,
	// type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LObjDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			double a2 = nextFunc2.doApplyAsDbl(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=double a2, type=SA}, SourcePurpose{arg=LObjDblConsumer<? super T> consumer,
	// type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LObjDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T a1 = nextFunc1.doApply(iterator1);
			double a2 = nextFunc2.doApplyAsDbl(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
