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

package eu.lunisolar.magma.func.operator.ternary;

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
 * Non-throwing functional interface (lambda) LLogicalTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): boolean a1,boolean a2,boolean a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalTernaryOperator extends MetaInterface.NonThrowing, MetaLogicalOperator, Codomain<aBool>, Domain3<aBool, aBool, aBool> { //NOSONAR

	String DESCRIPTION = "LLogicalTernaryOperator: boolean apply(boolean a1,boolean a2,boolean a3)";

	default boolean apply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(boolean a1,boolean a2,boolean a3)
	 */
	boolean applyX(boolean a1, boolean a2, boolean a3) throws Throwable;

	default boolean tupleApply(LBoolTriple args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingApply(boolean a1, boolean a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLogicalTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default boolean apply(boolean a1, boolean a2, boolean a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean apply(boolean a1, boolean a2, boolean a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean apply(boolean a1, boolean a2, boolean a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean apply(boolean a1, boolean a2, boolean a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLogicalTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LLogicalTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LLogicalTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LLogicalTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default boolean apply(boolean a1, boolean a2, boolean a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLogicalTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default boolean applyThen(boolean a1, boolean a2, boolean a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LLogicalTernaryOperator tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingApply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingApply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean shovingApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static boolean handlingApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static boolean tryApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static boolean tryApplyThen(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default boolean doIf(boolean a1, boolean a2, boolean a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (apply(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static boolean doIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull LTriBoolConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (apply(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullApply(boolean a1, boolean a2, boolean a3) {
		return apply(a1, a2, a3);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a1, boolean a2, boolean a3) {
		return apply(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LLogicalBinaryOperator _with(boolean a1) {
		return (a2, a3) -> apply(a1, a2, a3);
	}

	default LLogicalBinaryOperator with(boolean a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	default LLogicalOperator _with(boolean a1, boolean a2) {
		return a3 -> apply(a1, a2, a3);
	}

	default LLogicalOperator with(boolean a2, boolean a3) {
		return a1 -> apply(a1, a2, a3);
	}

	/**  */
	public static LLogicalTernaryOperator uncurry(@Nonnull LBoolFunction<LBoolFunction<LLogicalOperator>> func) {
		Null.nonNullArg(func, "func");
		return (boolean a1, boolean a2, boolean a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Change function to one that ignores output. */
	default LTriBoolConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LLogicalTernaryOperator beforeDo(@Nonnull LTriBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return (boolean a1, boolean a2, boolean a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLogicalTernaryOperator afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (boolean a1, boolean a2, boolean a3) -> {
			final boolean retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull LTriBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull LTriBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull LTriBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull LTriBoolFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> boolean throwIf(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.apply(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> boolean throwIfNot(boolean a1, @Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.apply(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, @Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, @Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, @Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, @Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> boolean throwIfNotEx(boolean a1, @Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Creates function that always returns the same value. */
	static LLogicalTernaryOperator constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOp(final @Nonnull LLogicalTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LLogicalTernaryOperator {
		private LLogicalTernaryOperator target = null;
		@Override
		public boolean applyX(boolean a1, boolean a2, boolean a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static LLogicalTernaryOperator recursive(final @Nonnull LFunction<LLogicalTernaryOperator, LLogicalTernaryOperator> selfLambda) {
		final S single = new S();
		LLogicalTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LLogicalTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LLogicalTernaryOperator function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LLogicalTernaryOperator baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLogicalTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LLogicalTernaryOperator {

		private final LLogicalTernaryOperator baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LLogicalTernaryOperator baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean applyX(boolean a1, boolean a2, boolean a3) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentApply(boolean a1, boolean a2, boolean a3) {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.apply(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue(boolean a1, boolean a2, boolean a3) {
			return baseFunction.apply(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static boolean call(boolean a1, boolean a2, boolean a3, final @Nonnull LLogicalTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
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
	default LLogicalTernaryOperator negate() {
		return (a1, a2, a3) -> !apply(a1, a2, a3);
	}

	@Nonnull
	static LLogicalTernaryOperator not(@Nonnull LLogicalTernaryOperator pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalTernaryOperator and(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> apply(a1, a2, a3) && other.apply(a1, a2, a3);
	}

	@Nonnull
	public static LLogicalTernaryOperator and(@Nonnull LLogicalTernaryOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> !any(false, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalTernaryOperator or(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> apply(a1, a2, a3) || other.apply(a1, a2, a3);
	}

	@Nonnull
	public static LLogicalTernaryOperator or(@Nonnull LLogicalTernaryOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> any(true, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalTernaryOperator xor(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> apply(a1, a2, a3) ^ other.apply(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLogicalTernaryOperator isEqual(boolean v1, boolean v2, boolean v3) {
		return (a1, a2, a3) -> (a1 == v1) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	public static boolean any(boolean expected, boolean a1, boolean a2, boolean a3, @Nonnull Collection<? extends LLogicalTernaryOperator> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, boolean a1, boolean a2, boolean a3, @Nonnull Stream<? extends LLogicalTernaryOperator> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, boolean a1, boolean a2, boolean a3, @Nonnull Iterator<? extends LLogicalTernaryOperator> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.apply(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(boolean expected, boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.apply(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns function that applies logical AND operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator and() {
		return (a1, a2, a3) -> a1 && a2 && a3;
	}

	/**
	 * Returns function that applies logical OR operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator or() {
		return (a1, a2, a3) -> a1 || a2 || a3;
	}

	/**
	 * Returns function that applies logical XOR operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator xor() {
		return (a1, a2, a3) -> a1 ^ a2 ^ a3;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalTernaryOperator compose(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> unboxingCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.test(v1), before2.test(v2), before3.test(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriBoolFunction<V> then(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalTernaryOperator thenToBool(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	default LLogicalTernaryOperator shoving() {

		return new LLogicalTernaryOperator() {

			public boolean apply(boolean a1, boolean a2, boolean a3) {
				try {
					return this.applyX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean applyX(boolean a1, boolean a2, boolean a3) throws Throwable {
				return LLogicalTernaryOperator.this.applyX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLogicalTernaryOperator) Operator */
	public static boolean doNothing(boolean a1, boolean a2, boolean a3) {
		return Function4U.defaultBoolean;
	}

	// >>> LLogicalTernaryOperator

	/** Returns TRUE. */
	public static boolean alwaysTrue(boolean a1, boolean a2, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(boolean a1, boolean a2, boolean a3) {
		return false;
	}

	public static boolean and(boolean a1, boolean a2, boolean a3) {
		return a1 && a2 && a3;
	}

	public static boolean nand(boolean a1, boolean a2, boolean a3) {
		return !and(a1, a2, a3);
	}

	public static boolean or(boolean a1, boolean a2, boolean a3) {
		return a1 || a2 || a3;
	}

	public static boolean nor(boolean a1, boolean a2, boolean a3) {
		return !or(a1, a2, a3);
	}

	public static boolean xor(boolean a1, boolean a2, boolean a3) {
		return a1 ^ a2 ^ a3;
	}

}
