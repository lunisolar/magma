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
 * Non-throwing functional interface (lambda) LObjBiIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T a1,int a2,int a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiIntPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LObjBiIntPredicate: boolean doTest(T a1,int a2,int a3)";

	// boolean doTest(T a1,int a2,int a3) ;
	default boolean doTest(T a1, int a2, int a3) {
		// return nestingDoTest(a1,a2,a3);
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T a1,int a2,int a3)
	 */
	boolean doTestX(T a1, int a2, int a3) throws Throwable;

	default boolean tupleTest(LObjBiIntTriple<T> args) {
		return doTest(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T a1, int a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T a1, int a2, int a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T a1, int a2, int a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T a1, int a2, int a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a1, int a2, int a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a1, int a2, int a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingDoTest(T a1, int a2, int a3, LObjBiIntPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, a3, handling);
	}

	static <T> boolean tryDoTest(T a1, int a2, int a3, LObjBiIntPredicate<T> func) {
		return tryDoTest(a1, a2, a3, func, null);
	}

	static <T> boolean tryDoTest(T a1, int a2, int a3, LObjBiIntPredicate<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> boolean tryDoTest(T a1, int a2, int a3, LObjBiIntPredicate<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory);
	}

	static <T> boolean tryDoTestThen(T a1, int a2, int a3, LObjBiIntPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, a3, handler);
	}

	default boolean failSafeDoTest(T a1, int a2, int a3, @Nonnull LObjBiIntPredicate<T> failSafe) {
		try {
			return doTest(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2, a3);
		}
	}

	static <T> boolean failSafeDoTest(T a1, int a2, int a3, LObjBiIntPredicate<T> func, @Nonnull LObjBiIntPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2, a3);
		} else {
			return func.failSafeDoTest(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjBiIntPredicate<T> failSafeObjBiIntPred(LObjBiIntPredicate<T> func, @Nonnull LObjBiIntPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T a1, int a2, int a3, LAction action) {
		if (doTest(a1, a2, a3)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T a1, int a2, int a3, LTieIntConsumer<? super T> consumer) {
		if (doTest(a1, a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	static <T> void throwIf(T a1, int a2, int a3, LObjBiIntPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T> void throwIfNot(T a1, int a2, int a3, LObjBiIntPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a1, int a2, int a3) {
		return doTest(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2, int a3) {
		return doTest(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBiIntPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, int a2, int a3, LObjBiIntPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, int a2, int a3, LObjBiIntPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, int a2, int a3, LObjBiIntPredicate<T> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiIntPredicate lShrink(LBiIntFunction<T> left) {
		return (a2, a3) -> doTest(left.doApply(a2, a3), a2, a3);
	}

	public default LBiIntPredicate lShrinkc(T a1) {
		return (a2, a3) -> doTest(a1, a2, a3);
	}

	public static <T> LBiIntPredicate lShrinked(LBiIntFunction<T> left, LObjBiIntPredicate<T> func) {
		return func.lShrink(left);
	}

	public static <T> LBiIntPredicate lShrinkedc(T a1, LObjBiIntPredicate<T> func) {
		return func.lShrinkc(a1);
	}

	public default LObjIntPredicate<T> rShrink(LOiToIntFunction<T> right) {
		return (a1, a2) -> doTest(a1, a2, right.doApplyAsInt(a1, a2));
	}

	public default LObjIntPredicate<T> rShrinkc(int a3) {
		return (a1, a2) -> doTest(a1, a2, a3);
	}

	public static <T> LObjIntPredicate<T> rShrinked(LOiToIntFunction<T> right, LObjBiIntPredicate<T> func) {
		return func.rShrink(right);
	}

	public static <T> LObjIntPredicate<T> rShrinkedc(int a3, LObjBiIntPredicate<T> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T> LObjBiIntPredicate<T> uncurryObjBiIntPred(LFunction<T, LIntFunction<LIntPredicate>> func) {
		return (T a1, int a2, int a3) -> func.doApply(a1).doApply(a2).doTest(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureObjBiIntPred(T a1, int a2, int a3) {
		return () -> this.doTest(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjBiIntPredicate<T> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjBiIntPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2, a3) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjBiIntPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.doTest(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjBiIntPredicate<T> test3rd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.doTest(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBiIntPredicate<T> objBiIntPred(final @Nonnull LObjBiIntPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjBiIntPredicate<T> recursive(final @Nonnull LFunction<LObjBiIntPredicate<T>, LObjBiIntPredicate<T>> selfLambda) {
		final LObjBiIntPredicateSingle<T> single = new LObjBiIntPredicateSingle();
		LObjBiIntPredicate<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LObjBiIntPredicateSingle<T> implements LSingle<LObjBiIntPredicate<T>>, LObjBiIntPredicate<T> {
		private LObjBiIntPredicate<T> target = null;

		@Override
		public boolean doTestX(T a1, int a2, int a3) throws Throwable {
			return target.doTestX(a1, a2, a3);
		}

		@Override
		public LObjBiIntPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjBiIntPredicate<T> objBiIntPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LObjBiIntPredicate<T> objBiIntPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjInt2Int1Pred<T> objInt2Int1Pred(final @Nonnull LObjInt2Int1Pred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt1ObjIntPred<T> int1ObjIntPred(final @Nonnull LInt1ObjIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt1Int2ObjPred<T> int1Int2ObjPred(final @Nonnull LInt1Int2ObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt2ObjInt1Pred<T> int2ObjInt1Pred(final @Nonnull LInt2ObjInt1Pred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBiIntObjPred<T> biIntObjPred(final @Nonnull LBiIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, int a3, final @Nonnull LObjBiIntPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjBiIntPredicate<T> safe() {
		return LObjBiIntPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjBiIntPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjBiIntPredicate<T> safe(final @Nullable LObjBiIntPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjBiIntPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjBiIntPredicate<T>> supplier) {
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
	default LObjBiIntPredicate<T> negate() {
		return (a1, a2, a3) -> !doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjBiIntPredicate<T> and(@Nonnull LObjBiIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) && other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBiIntPredicate<T> or(@Nonnull LObjBiIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) || other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjBiIntPredicate<T> xor(@Nonnull LObjBiIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) ^ other.doTest(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjBiIntPredicate<T> isEqual(T v1, int v2, int v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjBiIntPredicate<V1> objBiIntPredComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, T> LObjBiIntPredicate<V1> composedInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LIntUnaryOperator before3, LObjBiIntPredicate<T> after) {
		return after.objBiIntPredComposeInt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objBiIntPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, V3, T> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToIntFunction<? super V3> before3,
			LObjBiIntPredicate<T> after) {
		return after.objBiIntPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjBiIntFunction<T, V> boolToObjBiIntFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieIntFunction<T> boolToTieIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doTest(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjBiIntPredicate<T> boolToObjBiIntPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjBiIntPredicate<T> nestingObjBiIntPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBiIntPredicate<T> shovingObjBiIntPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjBiIntPredicate for method references. */
	@FunctionalInterface
	interface LObjInt2Int1Pred<T> extends LObjBiIntPredicate<T> {

		boolean doTestObjInt2Int1(T a1, int a3, int a2);

		@Override
		default boolean doTestX(T a1, int a2, int a3) {
			return this.doTestObjInt2Int1(a1, a3, a2);
		}
	}

	/** Permutation of LObjBiIntPredicate for method references. */
	@FunctionalInterface
	interface LInt1ObjIntPred<T> extends LObjBiIntPredicate<T> {

		boolean doTestInt1ObjInt(int a2, T a1, int a3);

		@Override
		default boolean doTestX(T a1, int a2, int a3) {
			return this.doTestInt1ObjInt(a2, a1, a3);
		}
	}

	/** Permutation of LObjBiIntPredicate for method references. */
	@FunctionalInterface
	interface LInt1Int2ObjPred<T> extends LObjBiIntPredicate<T> {

		boolean doTestInt1Int2Obj(int a2, int a3, T a1);

		@Override
		default boolean doTestX(T a1, int a2, int a3) {
			return this.doTestInt1Int2Obj(a2, a3, a1);
		}
	}

	/** Permutation of LObjBiIntPredicate for method references. */
	@FunctionalInterface
	interface LInt2ObjInt1Pred<T> extends LObjBiIntPredicate<T> {

		boolean doTestInt2ObjInt1(int a3, T a1, int a2);

		@Override
		default boolean doTestX(T a1, int a2, int a3) {
			return this.doTestInt2ObjInt1(a3, a1, a2);
		}
	}

	/** Permutation of LObjBiIntPredicate for method references. */
	@FunctionalInterface
	interface LBiIntObjPred<T> extends LObjBiIntPredicate<T> {

		boolean doTestBiIntObj(int a3, int a2, T a1);

		@Override
		default boolean doTestX(T a1, int a2, int a3) {
			return this.doTestBiIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	// >>> LObjBiIntPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2, int a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2, int a3) {
		return false;
	}

	// >>> LInt1ObjIntPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(int a2, T a1, int a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(int a2, T a1, int a3) {
		return false;
	}

	// >>> LInt1Int2ObjPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(int a2, int a3, T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(int a2, int a3, T a1) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LTieIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
