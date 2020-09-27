/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LBiBytePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): byte a1,byte a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiBytePredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain2<aByte, aByte> { // NOSONAR

	String DESCRIPTION = "LBiBytePredicate: boolean test(byte a1,byte a2)";

	// boolean test(byte a1,byte a2) ;
	default boolean test(byte a1, byte a2) {
		// return nestingTest(a1,a2);
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(byte a1,byte a2)
	 */
	boolean testX(byte a1, byte a2) throws Throwable;

	default boolean tupleTest(LBytePair args) {
		return test(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(byte a1, byte a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiBytePredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingTest(a1, a2, handling);
	}

	default boolean test(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default boolean test(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default boolean test(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default boolean test(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LBiBytePredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2) -> test(a1, a2, exF, newMessage);
	}

	default LBiBytePredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, param1);
	}

	default LBiBytePredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, param1, param1);
	}

	default LBiBytePredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, param1, param2, param3);
	}

	default boolean test(byte a1, byte a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiBytePredicate trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> test(a1, a2, exF);
	}

	default boolean testThen(byte a1, byte a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBiBytePredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2) -> testThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(byte a1, byte a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(byte a1, byte a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingTest(byte a1, byte a2, LBiBytePredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, handling);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, param1);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, param1, param2);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, param1, param2, param3);
	}

	static boolean tryTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF);
	}

	static boolean tryTestThen(byte a1, byte a2, LBiBytePredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, handler);
	}

	default boolean failSafeTest(byte a1, byte a2, @Nonnull LBiBytePredicate failSafe) {
		try {
			return test(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2);
		}
	}

	static boolean failSafeTest(byte a1, byte a2, LBiBytePredicate func, @Nonnull LBiBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2);
		} else {
			return func.failSafeTest(a1, a2, failSafe);
		}
	}

	static LBiBytePredicate failSafe(LBiBytePredicate func, @Nonnull LBiBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeTest(a1, a2, func, failSafe);
	}

	default boolean doIf(byte a1, byte a2, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(byte a1, byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, action);
	}

	static boolean doIf(byte a1, byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull LBiByteConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, consumer);
	}

	default boolean doIf(byte a1, byte a2, @Nonnull LBiByteConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(byte a1, byte a2) {
		return test(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(byte a1, byte a2) {
		return test(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiBytePredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a1, byte a2, @Nonnull LBiBytePredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LBytePredicate lShrink(@Nonnull LByteUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> test(left.applyAsByte(a2), a2);
	}

	default LBytePredicate lShrink_(byte a1) {
		return a2 -> test(a1, a2);
	}

	public static LBytePredicate lShrunken(@Nonnull LByteUnaryOperator left, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LBytePredicate lShrunken_(byte a1, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LBytePredicate rShrink(@Nonnull LByteUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> test(a1, right.applyAsByte(a1));
	}

	default LBytePredicate rShrink_(byte a2) {
		return a1 -> test(a1, a2);
	}

	public static LBytePredicate rShrunken(@Nonnull LByteUnaryOperator right, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LBytePredicate rShrunken_(byte a2, @Nonnull LBiBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LBiBytePredicate uncurry(@Nonnull LByteFunction<LBytePredicate> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, byte a2) -> func.apply(a1).test(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiByteConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LBiBytePredicate beforeDo(@Nonnull LBiByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, byte a2) -> {
			before.accept(a1, a2);
			return test(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBiBytePredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a1, byte a2) -> {
			final boolean retval = test(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull LBiByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull LBiByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, @Nonnull LBiBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull LBiByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull LBiByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LBiBytePredicate pred, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, @Nonnull LBiByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, @Nonnull LBiByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, @Nonnull LBiByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, @Nonnull LBiByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, @Nonnull LBiByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LBiByteFunction<? extends String> specialPredicate, byte a2, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LBiByteFunction<? extends String> specialPredicate, byte a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LBiByteFunction<? extends String> specialPredicate, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LBiByteFunction<? extends String> specialPredicate, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LBiByteFunction<? extends String> specialPredicate, byte a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(byte a1, byte a2) {
		return () -> this.test(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LBiBytePredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiBytePredicate test1st(@Nonnull LBytePredicate func) {
		return (a1, a2) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiBytePredicate test2nd(@Nonnull LBytePredicate func) {
		return (a1, a2) -> func.test(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiBytePredicate biBytePred(final @Nonnull LBiBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LBiBytePredicate {
		private LBiBytePredicate target = null;
		@Override
		public boolean testX(byte a1, byte a2) throws Throwable {
			return target.testX(a1, a2);
		}
	}

	@Nonnull
	static LBiBytePredicate recursive(final @Nonnull LFunction<LBiBytePredicate, LBiBytePredicate> selfLambda) {
		final S single = new S();
		LBiBytePredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(byte a1, byte a2, LBiBytePredicate function) {
		var initialValue = function.test(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LBiBytePredicate function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(byte a1, byte a2, LBiBytePredicate function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(byte a1, byte a2, LBiBytePredicate function) {
		var initialValue = function.test(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LBiBytePredicate function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LBiBytePredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBiBytePredicate.M)
	 */
	@NotThreadSafe
	final class M implements LBiBytePredicate {

		private final LBiBytePredicate baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LBiBytePredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(byte a1, byte a2) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		};

		public boolean lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LBiBytePredicate biBytePredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBiBytePredicate biBytePredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiBytePredicate.LByte1Byte0Pred byte1Byte0Pred(final @Nonnull LBiBytePredicate.LByte1Byte0Pred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(byte a1, byte a2, final @Nonnull LBiBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBiBytePredicate safe() {
		return LBiBytePredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiBytePredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiBytePredicate safe(final @Nullable LBiBytePredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiBytePredicate> safeSupplier(final @Nullable LSupplier<LBiBytePredicate> supplier) {
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
	default LBiBytePredicate negate() {
		return (a1, a2) -> !test(a1, a2);
	}

	@Nonnull
	static LBiBytePredicate not(@Nonnull LBiBytePredicate pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiBytePredicate and(@Nonnull LBiBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) && other.test(a1, a2);
	}

	@Nonnull
	public static LBiBytePredicate and(@Nonnull LBiBytePredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2) -> {
			for (LBiBytePredicate p : predicates) {
				if (!p.test(a1, a2)) {
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
	default LBiBytePredicate or(@Nonnull LBiBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) || other.test(a1, a2);
	}

	@Nonnull
	public static LBiBytePredicate or(@Nonnull LBiBytePredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2) -> {
			for (LBiBytePredicate p : predicates) {
				if (p.test(a1, a2)) {
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
	default LBiBytePredicate xor(@Nonnull LBiBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) ^ other.test(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBiBytePredicate isEqual(byte v1, byte v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiBytePredicate compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	public static LBiBytePredicate composed(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2, LBiBytePredicate after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> biBytePredCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2, LBiBytePredicate after) {
		return after.biBytePredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiByteFunction<V> boolToBiByteFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteBinaryOperator boolToByteBinaryOp(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiBytePredicate boolToBiBytePred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiBytePredicate for method references. */
	@FunctionalInterface
	interface LByte1Byte0Pred extends LBiBytePredicate {

		/**
		 * Implement this, but call test(byte a1,byte a2)
		 */
		default boolean testX(byte a1, byte a2) {
			return this.testByte1Byte0(a2, a1);
		}

		// boolean testByte1Byte0(byte a2,byte a1) ;
		default boolean testByte1Byte0(byte a2, byte a1) {
			// return nestingTestByte1Byte0(a2,a1);
			try {
				return this.testByte1Byte0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testByte1Byte0(byte a2,byte a1)
		 */
		boolean testByte1Byte0X(byte a2, byte a1) throws Throwable;
	}

	// </editor-fold>

	// >>> LBiBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1, byte a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1, byte a2) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void filterForEach(IndexedRead<C1, aByte> ia1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LBiByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			byte a2 = oiFunc2.applyAsByte(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void filterIterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LBiByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			byte a2 = oiFunc2.applyAsByte(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void filterIterate(IndexedRead<C1, aByte> ia1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LBiByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			byte a1 = oiFunc1.applyAsByte(source1, i);
			byte a2 = nextFunc2.applyAsByte(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void filterIterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LBiByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			byte a1 = nextFunc1.applyAsByte(iterator1);
			byte a2 = nextFunc2.applyAsByte(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
