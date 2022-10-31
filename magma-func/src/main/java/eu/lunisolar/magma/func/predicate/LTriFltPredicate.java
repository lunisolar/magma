/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LTriFltPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): float a1,float a2,float a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriFltPredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<aFloat, aFloat, aFloat> { // NOSONAR

	String DESCRIPTION = "LTriFltPredicate: boolean test(float a1,float a2,float a3)";

	// boolean test(float a1,float a2,float a3) ;
	default boolean test(float a1, float a2, float a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(float a1,float a2,float a3)
	 */
	boolean testX(float a1, float a2, float a3) throws Throwable;

	default boolean tupleTest(LFltTriple args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(float a1, float a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriFltPredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriFltPredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage);
	}

	default LTriFltPredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriFltPredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriFltPredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default boolean test(float a1, float a2, float a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriFltPredicate trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory);
	}

	default boolean testThen(float a1, float a2, float a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LTriFltPredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(float a1, float a2, float a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(float a1, float a2, float a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean shovingTest(float a1, float a2, float a3, LTriFltPredicate func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3);
	}

	static boolean handlingTest(float a1, float a2, float a3, LTriFltPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static boolean tryTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory);
	}

	static boolean tryTestThen(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(float a1, float a2, float a3, @Nonnull LTriFltPredicate failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static boolean failSafeTest(float a1, float a2, float a3, LTriFltPredicate func, @Nonnull LTriFltPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static LTriFltPredicate failSafe(LTriFltPredicate func, @Nonnull LTriFltPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(float a1, float a2, float a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static boolean doIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate predicate, @Nonnull LTriFltConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(float a1, float a2, float a3, @Nonnull LTriFltConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(float a1, float a2, float a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(float a1, float a2, float a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriFltPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a1, float a2, float a3, @Nonnull LTriFltPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a1, float a2, float a3, @Nonnull LTriFltPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a1, float a2, float a3, @Nonnull LTriFltPredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static LTriFltPredicate uncurry(@Nonnull LFltFunction<LFltFunction<LFltPredicate>> func) {
		Null.nonNullArg(func, "func");
		return (float a1, float a2, float a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriFltConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LTriFltPredicate beforeDo(@Nonnull LTriFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a1, float a2, float a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTriFltPredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (float a1, float a2, float a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull LTriFltFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull LTriFltFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, float a2, float a3, @Nonnull LTriFltPredicate pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull LTriFltFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull LTriFltFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> float throwIf(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> float throwIfNot(float a1, @Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> float throwIfNotEx(float a1, float a2, float a3, @Nonnull LTriFltFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> float throwIfNotEx(float a1, float a2, float a3, @Nonnull LTriFltFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, float a2, float a3, @Nonnull LTriFltFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, float a2, float a3, @Nonnull LTriFltFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, float a2, float a3, @Nonnull LTriFltFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, @Nonnull LTriFltFunction<? extends String> specialPredicate, float a2, float a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> float throwIfNotEx(float a1, @Nonnull LTriFltFunction<? extends String> specialPredicate, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, @Nonnull LTriFltFunction<? extends String> specialPredicate, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, @Nonnull LTriFltFunction<? extends String> specialPredicate, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
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
	public static <X extends Throwable> float throwIfNotEx(float a1, @Nonnull LTriFltFunction<? extends String> specialPredicate, float a2, float a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
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
	static LTriFltPredicate constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LTriFltPredicate triFltPred(final @Nonnull LTriFltPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LTriFltPredicate {
		private LTriFltPredicate target = null;
		@Override
		public boolean testX(float a1, float a2, float a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}
	}

	@Nonnull
	static LTriFltPredicate recursive(final @Nonnull LFunction<LTriFltPredicate, LTriFltPredicate> selfLambda) {
		final S single = new S();
		LTriFltPredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(float a1, float a2, float a3, LTriFltPredicate function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LTriFltPredicate function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(float a1, float a2, float a3, LTriFltPredicate function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(float a1, float a2, float a3, LTriFltPredicate function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LTriFltPredicate function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LTriFltPredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTriFltPredicate.M)
	 */
	@NotThreadSafe
	final class M implements LTriFltPredicate {

		private final LTriFltPredicate baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LTriFltPredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(float a1, float a2, float a3) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2, a3);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		};

		public boolean lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LTriFltPredicate triFltPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LTriFltPredicate triFltPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static boolean call(float a1, float a2, float a3, final @Nonnull LTriFltPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
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
	default LTriFltPredicate negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static LTriFltPredicate not(@Nonnull LTriFltPredicate pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LTriFltPredicate and(@Nonnull LTriFltPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	@Nonnull
	public static LTriFltPredicate and(@Nonnull LTriFltPredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LTriFltPredicate p : predicates) {
				if (!p.test(a1, a2, a3)) {
					return false;
				}
			}
			return true;
		};
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriFltPredicate or(@Nonnull LTriFltPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	@Nonnull
	public static LTriFltPredicate or(@Nonnull LTriFltPredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LTriFltPredicate p : predicates) {
				if (p.test(a1, a2, a3)) {
					return true;
				}
			}
			return false;
		};
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriFltPredicate xor(@Nonnull LTriFltPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LTriFltPredicate isEqual(float v1, float v2, float v3) {
		return (a1, a2, a3) -> (a1 == v1) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LTriFltPredicate compose(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.applyAsFlt(v1), before2.applyAsFlt(v2), before3.applyAsFlt(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> unboxingCompose(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.applyAsFlt(v1), before2.applyAsFlt(v2), before3.applyAsFlt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFltFunction<V> boolToTriFltFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltTernaryOperator boolToFltTernaryOp(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsFlt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriFltPredicate boolToTriFltPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LTriFltPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(float a1, float a2, float a3) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float a1, float a2, float a3) {
		return false;
	}

}
