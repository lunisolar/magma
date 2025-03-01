/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.unary;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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

import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR
import java.util.stream.Stream; // NOSONAR

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
 * Non-throwing functional interface (lambda) LLogicalOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalOperator extends MetaInterface.NonThrowing, MetaLogicalOperator, Codomain<aBool>, Domain1<aBool> { //NOSONAR

	String DESCRIPTION = "LLogicalOperator: boolean apply(boolean a)";

	default boolean apply(boolean a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(boolean a)
	 */
	boolean applyX(boolean a) throws Throwable;

	default boolean tupleApply(LBoolSingle args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingApply(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLogicalOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default boolean apply(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean apply(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean apply(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean apply(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLogicalOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> apply(a, factory, newMessage);
	}

	default LLogicalOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> apply(a, factory, newMessage, param1);
	}

	default LLogicalOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> apply(a, factory, newMessage, param1, param1);
	}

	default LLogicalOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> apply(a, factory, newMessage, param1, param2, param3);
	}

	default boolean apply(boolean a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLogicalOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> apply(a, factory);
	}

	default boolean applyThen(boolean a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LLogicalOperator tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingApply(boolean a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingApply(boolean a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean shovingApply(boolean a, LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a);
	}

	static boolean handlingApply(boolean a, LLogicalOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static boolean tryApply(boolean a, LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static boolean tryApply(boolean a, LLogicalOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage);
	}

	static boolean tryApply(boolean a, LLogicalOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1);
	}

	static boolean tryApply(boolean a, LLogicalOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2);
	}

	static boolean tryApply(boolean a, LLogicalOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2, param3);
	}

	static boolean tryApply(boolean a, LLogicalOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory);
	}

	static boolean tryApplyThen(boolean a, LLogicalOperator func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	default boolean doIf(boolean a, LAction action) {
		Null.nonNullArg(action, "action");
		if (apply(a)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(boolean a, @Nonnull LLogicalOperator predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, action);
	}

	static boolean doIf(boolean a, @Nonnull LLogicalOperator predicate, @Nonnull LBoolConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, consumer);
	}

	default boolean doIf(boolean a, @Nonnull LBoolConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (apply(a)) {
			consumer.accept(a);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullApply(boolean a) {
		return apply(a);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a) {
		return apply(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalOperator.DESCRIPTION;
	}

	default <V> boolean doIf(V a1, boolean a2, @Nonnull LObjBoolConsumer<V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (apply(a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	default <V> boolean doIf(V a1, int a2, boolean a3, @Nonnull LTieBoolConsumer<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (apply(a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	default <V> int doIf(V a1, int a2, boolean a3, @Nonnull LTieBoolFunction<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (apply(a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a, @Nonnull LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, boolean a, @Nonnull LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, boolean a, @Nonnull LLogicalOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one that ignores output. */
	default LBoolConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LLogicalOperator beforeDo(@Nonnull LBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return (boolean a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLogicalOperator afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (boolean a) -> {
			final boolean retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull LBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.apply(a)) {
			throw Handling.create(factory, msgFunc.apply(a));
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.apply(a)) {
			throw Handling.create(factory, String.format(msg, a));
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull LBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.apply(a)) {
			throw Handling.create(factory, msgFunc.apply(a));
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.apply(a)) {
			throw Handling.create(factory, String.format(msg, a));
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.apply(a)) {
			throw Handling.create(noArgFactory);
		}
		return a;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a, @Nonnull LLogicalOperator pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.apply(a)) {
			throw Handling.create(noArgFactory);
		}
		return a;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a, @Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a, @Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a) + ' ' + m);
		}
		return a;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a, @Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a, @Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a, @Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a;
	}

	/** Creates function that always returns the same value. */
	static LLogicalOperator constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLogicalOperator logicalOp(final @Nonnull LLogicalOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LLogicalOperator {
		private LLogicalOperator target = null;
		@Override
		public boolean applyX(boolean a) throws Throwable {
			return target.applyX(a);
		}
	}

	@Nonnull
	static LLogicalOperator recursive(final @Nonnull LFunction<LLogicalOperator, LLogicalOperator> selfLambda) {
		final S single = new S();
		LLogicalOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(boolean a, LLogicalOperator function) {
		var initialValue = function.apply(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LLogicalOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(boolean a, LLogicalOperator function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.apply(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(boolean a, LLogicalOperator function) {
		var initialValue = function.apply(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LLogicalOperator function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LLogicalOperator baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLogicalOperator.M)
	 */
	@NotThreadSafe
	final class M implements LLogicalOperator {

		private final LLogicalOperator baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LLogicalOperator baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean applyX(boolean a) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.applyX(a);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentApply(boolean a) {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.apply(a);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue(boolean a) {
			return baseFunction.apply(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static LLogicalOperator logicalOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLogicalOperator logicalOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static boolean call(boolean a, final @Nonnull LLogicalOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LLogicalOperator negate() {
		return a -> !apply(a);
	}

	@Nonnull
	static LLogicalOperator not(@Nonnull LLogicalOperator pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalOperator and(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> apply(a) && other.apply(a);
	}

	@Nonnull
	public static LLogicalOperator and(@Nonnull LLogicalOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return a -> !any(false, a, predicates);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalOperator or(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> apply(a) || other.apply(a);
	}

	@Nonnull
	public static LLogicalOperator or(@Nonnull LLogicalOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return a -> any(true, a, predicates);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalOperator xor(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> apply(a) ^ other.apply(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLogicalOperator isEqual(boolean target) {
		return a -> a == target;
	}

	// </editor-fold>

	public static boolean any(boolean expected, boolean a, @Nonnull Collection<? extends LLogicalOperator> predicates) {
		return any(expected, a, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, boolean a, @Nonnull Stream<? extends LLogicalOperator> predicates) {
		return any(expected, a, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, boolean a, @Nonnull Iterator<? extends LLogicalOperator> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.apply(a)) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(boolean expected, boolean a, @Nonnull LLogicalOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.apply(a)) {
				return true;
			}
		}
		return false;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalOperator compose(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.apply(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> unboxingCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.test(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.apply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LLogicalOperator identity() {
		return t -> t;
	}

	static boolean identity(boolean a) {
		return a;
	}

	default LLogicalOperator shoving() {

		return new LLogicalOperator() {

			public boolean apply(boolean a) {
				try {
					return this.applyX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean applyX(boolean a) throws Throwable {
				return LLogicalOperator.this.applyX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLogicalOperator) Operator */
	public static boolean doNothing(boolean a) {
		return Function4U.defaultBoolean;
	}

	// >>> LLogicalOperator

	/** Returns TRUE. */
	public static boolean alwaysTrue(boolean a) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(boolean a) {
		return false;
	}

	public static boolean isTrue(boolean a) {
		return a;
	}

	public static boolean isFalse(boolean a) {
		return !isTrue(a);
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LBoolConsumer consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.boolGetter(ia);
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.test(source, i);
			consumer.accept(this.apply(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LBoolConsumer consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.boolSupplier(sa);
		while (testFunc0.test(iterator0)) {
			boolean a = nextFunc0.test(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
