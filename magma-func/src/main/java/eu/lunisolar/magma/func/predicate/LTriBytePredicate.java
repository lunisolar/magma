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
 * Non-throwing functional interface (lambda) LTriBytePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): byte a1,byte a2,byte a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriBytePredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<aByte, aByte, aByte> { //NOSONAR

	String DESCRIPTION = "LTriBytePredicate: boolean test(byte a1,byte a2,byte a3)";

	default boolean test(byte a1, byte a2, byte a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(byte a1,byte a2,byte a3)
	 */
	boolean testX(byte a1, byte a2, byte a3) throws Throwable;

	default boolean tupleTest(LByteTriple args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(byte a1, byte a2, byte a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriBytePredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriBytePredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage);
	}

	default LTriBytePredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriBytePredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriBytePredicate trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default boolean test(byte a1, byte a2, byte a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriBytePredicate trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory);
	}

	default boolean testThen(byte a1, byte a2, byte a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LTriBytePredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(byte a1, byte a2, byte a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(byte a1, byte a2, byte a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean shovingTest(byte a1, byte a2, byte a3, LTriBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3);
	}

	static boolean handlingTest(byte a1, byte a2, byte a3, LTriBytePredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static boolean tryTest(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory);
	}

	static boolean tryTestThen(byte a1, byte a2, byte a3, LTriBytePredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean doIf(byte a1, byte a2, byte a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static boolean doIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull LTriByteConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(byte a1, byte a2, byte a3, @Nonnull LTriByteConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(byte a1, byte a2, byte a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(byte a1, byte a2, byte a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriBytePredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate func) {
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
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate func) {
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
	public static void times(int max_i, byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LBiBytePredicate _with(byte a1) {
		return (a2, a3) -> test(a1, a2, a3);
	}

	default LBiBytePredicate with(byte a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	default LBytePredicate _with(byte a1, byte a2) {
		return a3 -> test(a1, a2, a3);
	}

	default LBytePredicate with(byte a2, byte a3) {
		return a1 -> test(a1, a2, a3);
	}

	/**  */
	public static LTriBytePredicate uncurry(@Nonnull LByteFunction<LByteFunction<LBytePredicate>> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, byte a2, byte a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Change function to one that ignores output. */
	default LTriByteConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LTriBytePredicate beforeDo(@Nonnull LTriByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, byte a2, byte a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTriBytePredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a1, byte a2, byte a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull LTriByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull LTriByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull LTriByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull LTriByteFunction<? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> byte throwIf(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <X extends Throwable> byte throwIfNot(byte a1, @Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, byte a3, @Nonnull LTriByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, byte a3, @Nonnull LTriByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, byte a3, @Nonnull LTriByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, byte a3, @Nonnull LTriByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, byte a2, byte a3, @Nonnull LTriByteFunction<? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LTriByteFunction<? extends String> specialPredicate, byte a2, byte a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LTriByteFunction<? extends String> specialPredicate, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LTriByteFunction<? extends String> specialPredicate, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LTriByteFunction<? extends String> specialPredicate, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
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
	public static <X extends Throwable> byte throwIfNotEx(byte a1, @Nonnull LTriByteFunction<? extends String> specialPredicate, byte a2, byte a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	static LTriBytePredicate constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LTriBytePredicate triBytePred(final @Nonnull LTriBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LTriBytePredicate {
		private LTriBytePredicate target = null;
		@Override
		public boolean testX(byte a1, byte a2, byte a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}
	}

	@Nonnull
	static LTriBytePredicate recursive(final @Nonnull LFunction<LTriBytePredicate, LTriBytePredicate> selfLambda) {
		final S single = new S();
		LTriBytePredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(byte a1, byte a2, byte a3, LTriBytePredicate function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LTriBytePredicate function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(byte a1, byte a2, byte a3, LTriBytePredicate function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(byte a1, byte a2, byte a3, LTriBytePredicate function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LTriBytePredicate function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LTriBytePredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTriBytePredicate.M)
	 */
	@NotThreadSafe
	final class M implements LTriBytePredicate {

		private final LTriBytePredicate baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LTriBytePredicate baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(byte a1, byte a2, byte a3) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentTest(byte a1, byte a2, byte a3) {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.test(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue(byte a1, byte a2, byte a3) {
			return baseFunction.test(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static LTriBytePredicate triBytePredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LTriBytePredicate triBytePredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static boolean call(byte a1, byte a2, byte a3, final @Nonnull LTriBytePredicate lambda) {
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
	default LTriBytePredicate negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static LTriBytePredicate not(@Nonnull LTriBytePredicate pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LTriBytePredicate and(@Nonnull LTriBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	@Nonnull
	public static LTriBytePredicate and(@Nonnull LTriBytePredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> !any(false, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriBytePredicate or(@Nonnull LTriBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	@Nonnull
	public static LTriBytePredicate or(@Nonnull LTriBytePredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> any(true, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriBytePredicate xor(@Nonnull LTriBytePredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LTriBytePredicate isEqual(byte v1, byte v2, byte v3) {
		return (a1, a2, a3) -> (a1 == v1) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	public static boolean any(boolean expected, byte a1, byte a2, byte a3, @Nonnull Collection<? extends LTriBytePredicate> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, byte a1, byte a2, byte a3, @Nonnull Stream<? extends LTriBytePredicate> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static boolean any(boolean expected, byte a1, byte a2, byte a3, @Nonnull Iterator<? extends LTriBytePredicate> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	public static boolean any(boolean expected, byte a1, byte a2, byte a3, @Nonnull LTriBytePredicate... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LTriBytePredicate compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2, @Nonnull final LByteUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.applyAsByte(v1), before2.applyAsByte(v2), before3.applyAsByte(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> unboxingCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2, @Nonnull final LToByteFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.applyAsByte(v1), before2.applyAsByte(v2), before3.applyAsByte(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriByteFunction<V> boolToTriByteFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteTernaryOperator boolToByteTernaryOp(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsByte(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriBytePredicate boolToTriBytePred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	default LTriBytePredicate shoving() {

		return new LTriBytePredicate() {

			public boolean test(byte a1, byte a2, byte a3) {
				try {
					return this.testX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean testX(byte a1, byte a2, byte a3) throws Throwable {
				return LTriBytePredicate.this.testX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LTriBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1, byte a2, byte a3) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1, byte a2, byte a3) {
		return false;
	}

}
