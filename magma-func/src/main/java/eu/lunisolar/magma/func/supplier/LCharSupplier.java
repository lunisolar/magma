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
 * Non-throwing functional interface (lambda) LCharSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharSupplier extends MetaSupplier, MetaInterface.NonThrowing, Codomain<aChar>, Domain0 { // NOSONAR

	String DESCRIPTION = "LCharSupplier: char getAsChar()";

	// char getAsChar() ;
	default char getAsChar() {
		// return nestingGetAsChar();
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsChar()
	 */
	char getAsCharX() throws Throwable;

	default char tupleGetAsChar(LTuple.Void args) {
		return getAsChar();
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingGetAsChar(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsChar(handling);
	}

	default char getAsChar(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char getAsChar(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char getAsChar(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char getAsChar(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LCharSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsChar(factory, newMessage);
	}

	default LCharSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsChar(factory, newMessage, param1);
	}

	default LCharSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsChar(factory, newMessage, param1, param1);
	}

	default LCharSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsChar(factory, newMessage, param1, param2, param3);
	}

	default char getAsChar(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LCharSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsChar(factory);
	}

	default char getAsCharThen(@Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LCharSupplier tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return () -> getAsCharThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingGetAsChar() {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingGetAsChar() {
		try {
			return this.getAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char shovingGetAsChar(LCharSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsChar();
	}

	static char handlingGetAsChar(LCharSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsChar(handling);
	}

	static char tryGetAsChar(LCharSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsChar();
	}

	static char tryGetAsChar(LCharSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsChar(factory, newMessage);
	}

	static char tryGetAsChar(LCharSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsChar(factory, newMessage, param1);
	}

	static char tryGetAsChar(LCharSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsChar(factory, newMessage, param1, param2);
	}

	static char tryGetAsChar(LCharSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsChar(factory, newMessage, param1, param2, param3);
	}

	static char tryGetAsChar(LCharSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsChar(factory);
	}

	static char tryGetAsCharThen(LCharSupplier func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsCharThen(handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullGetAsChar() {
		return getAsChar();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LCharSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsChar();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsChar();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LCharSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsChar();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsChar();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LCharSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to consumer that ignores output. */
	default LAction toConsumer() {
		return this::getAsChar;
	}

	/** Calls codomain consumer after main function. */
	default LCharSupplier afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final char retval = getAsChar();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LCharSupplier of(char r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharSupplier charSup(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LCharSupplier {
		private LCharSupplier target = null;
		@Override
		public char getAsCharX() throws Throwable {
			return target.getAsCharX();
		}
	}

	@Nonnull
	static LCharSupplier recursive(final @Nonnull LFunction<LCharSupplier, LCharSupplier> selfLambda) {
		final S single = new S();
		LCharSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(LCharSupplier function) {
		var initialValue = function.getAsChar();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(char initialValue, LCharSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LCharSupplier function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.getAsChar();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LCharSupplier function) {
		var initialValue = function.getAsChar();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static M initializedDeltaOf(char initialValue, LCharSupplier function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static M memento(char initialBaseValue, char initialValue, LCharSupplier baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LCharSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LCharSupplier {

		private final LCharSupplier baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LCharSupplier baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char getAsCharX() throws Throwable {
			char x1 = lastBaseValue;
			char x2 = lastBaseValue = baseFunction.getAsCharX();

			return lastValue = mementoFunction.applyAsCharX(lastValue, x1, x2);
		}

		public char currentGetAsChar() {
			char x1 = lastBaseValue;
			char x2 = baseFunction.getAsChar();

			return mementoFunction.applyAsChar(lastValue, x1, x2);
		}

		public char lastValue() {
			return lastValue;
		};

		public char lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LCharSupplier charSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharSupplier charSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static char call(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsChar();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsChar());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharSupplier) Supplier */
	public static char doNothing() {
		return Function4U.defaultCharacter;
	}

}
