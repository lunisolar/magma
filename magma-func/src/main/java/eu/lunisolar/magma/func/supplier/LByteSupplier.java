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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
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
import java.util.*;

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
 * Non-throwing functional interface (lambda) LByteSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteSupplier extends MetaSupplier, MetaInterface.NonThrowing, Codomain<aByte>, Domain0 { // NOSONAR

	String DESCRIPTION = "LByteSupplier: byte getAsByte()";

	// byte getAsByte() ;
	default byte getAsByte() {
		// return nestingGetAsByte();
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsByte()
	 */
	byte getAsByteX() throws Throwable;

	default byte tupleGetAsByte(LTuple.Void args) {
		return getAsByte();
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingGetAsByte(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsByte(handling);
	}

	default byte getAsByte(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default byte getAsByte(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default byte getAsByte(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default byte getAsByte(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LByteSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsByte(factory, newMessage);
	}

	default LByteSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsByte(factory, newMessage, param1);
	}

	default LByteSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsByte(factory, newMessage, param1, param1);
	}

	default LByteSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsByte(factory, newMessage, param1, param2, param3);
	}

	default byte getAsByte(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LByteSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsByte(factory);
	}

	default byte getAsByteThen(@Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LByteSupplier tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return () -> getAsByteThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingGetAsByte() {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingGetAsByte() {
		try {
			return this.getAsByteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte shovingGetAsByte(LByteSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsByte();
	}

	static byte handlingGetAsByte(LByteSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsByte(handling);
	}

	static byte tryGetAsByte(LByteSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsByte();
	}

	static byte tryGetAsByte(LByteSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsByte(factory, newMessage);
	}

	static byte tryGetAsByte(LByteSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsByte(factory, newMessage, param1);
	}

	static byte tryGetAsByte(LByteSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsByte(factory, newMessage, param1, param2);
	}

	static byte tryGetAsByte(LByteSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsByte(factory, newMessage, param1, param2, param3);
	}

	static byte tryGetAsByte(LByteSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsByte(factory);
	}

	static byte tryGetAsByteThen(LByteSupplier func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsByteThen(handler);
	}

	default byte failSafeGetAsByte(@Nonnull LByteSupplier failSafe) {
		try {
			return getAsByte();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.getAsByte();
		}
	}

	static byte failSafeGetAsByte(LByteSupplier func, @Nonnull LByteSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.getAsByte();
		} else {
			return func.failSafeGetAsByte(failSafe);
		}
	}

	static LByteSupplier failSafe(LByteSupplier func, @Nonnull LByteSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGetAsByte(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullGetAsByte() {
		return getAsByte();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LByteSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsByte();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsByte();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LByteSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsByte();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsByte();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LByteSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to consumer that ignores output. */
	default LAction toConsumer() {
		return this::getAsByte;
	}

	/** Calls codomain consumer after main function. */
	default LByteSupplier afterDo(@Nonnull LByteConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final byte retval = getAsByte();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LByteSupplier of(byte r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteSupplier byteSup(final @Nonnull LByteSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LByteSupplier {
		private LByteSupplier target = null;
		@Override
		public byte getAsByteX() throws Throwable {
			return target.getAsByteX();
		}
	}

	@Nonnull
	static LByteSupplier recursive(final @Nonnull LFunction<LByteSupplier, LByteSupplier> selfLambda) {
		final S single = new S();
		LByteSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(LByteSupplier function) {
		var initialValue = function.getAsByte();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(byte initialValue, LByteSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LByteSupplier function, LByteBinaryOperator deltaFunction) {
		var initialValue = function.getAsByte();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LByteSupplier function) {
		var initialValue = function.getAsByte();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (byte) (x2 - x1));
	}

	public static M initializedDeltaOf(byte initialValue, LByteSupplier function, LByteBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsByte(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsByte(x1, x2));
	}

	public static M memento(byte initialBaseValue, byte initialValue, LByteSupplier baseFunction, LByteTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LByteSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LByteSupplier {

		private final LByteSupplier baseFunction;
		private byte lastBaseValue;
		private byte lastValue;
		private final LByteTernaryOperator mementoFunction;

		private M(byte lastBaseValue, byte lastValue, LByteSupplier baseFunction, LByteTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public byte getAsByteX() throws Throwable {
			byte x1 = lastBaseValue;
			byte x2 = lastBaseValue = baseFunction.getAsByteX();

			return lastValue = mementoFunction.applyAsByte(lastValue, x1, x2);
		}

		public byte lastValue() {
			return lastValue;
		};

		public byte lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LByteSupplier byteSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteSupplier byteSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static byte call(final @Nonnull LByteSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsByte();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LByteSupplier safe() {
		return LByteSupplier::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteSupplier safe(final @Nullable LByteSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteSupplier> safeSupplier(final @Nullable LSupplier<LByteSupplier> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsByte());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsByte());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LByteSupplier) Supplier */
	public static byte doNothing() {
		return Function4U.defaultByte;
	}

}
