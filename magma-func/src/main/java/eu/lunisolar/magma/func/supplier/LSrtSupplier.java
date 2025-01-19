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
 * Non-throwing functional interface (lambda) LSrtSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtSupplier extends MetaSupplier, MetaInterface.NonThrowing, Codomain<aShort>, Domain0 { //NOSONAR

	String DESCRIPTION = "LSrtSupplier: short getAsSrt()";

	default short getAsSrt() {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsSrt()
	 */
	short getAsSrtX() throws Throwable;

	default short tupleGetAsSrt(LTuple.Void args) {
		return getAsSrt();
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingGetAsSrt(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsSrt(handling);
	}

	default short getAsSrt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short getAsSrt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short getAsSrt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short getAsSrt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LSrtSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsSrt(factory, newMessage);
	}

	default LSrtSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsSrt(factory, newMessage, param1);
	}

	default LSrtSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsSrt(factory, newMessage, param1, param1);
	}

	default LSrtSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsSrt(factory, newMessage, param1, param2, param3);
	}

	default short getAsSrt(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LSrtSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsSrt(factory);
	}

	default short getAsSrtThen(@Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LSrtSupplier tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return () -> getAsSrtThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingGetAsSrt() {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingGetAsSrt() {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short shovingGetAsSrt(LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsSrt();
	}

	static short handlingGetAsSrt(LSrtSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsSrt(handling);
	}

	static short tryGetAsSrt(LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsSrt();
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(factory, newMessage);
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(factory, newMessage, param1);
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(factory, newMessage, param1, param2);
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(factory, newMessage, param1, param2, param3);
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(factory);
	}

	static short tryGetAsSrtThen(LSrtSupplier func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsSrtThen(handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullGetAsSrt() {
		return getAsSrt();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsSrt();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsSrt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsSrt();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsSrt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LSrtSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to one that ignores output. */
	default LAction toAction() {
		return this::getAsSrt;
	}

	/** Calls codomain consumer after main function. */
	default LSrtSupplier afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final short retval = getAsSrt();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LSrtSupplier of(short r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtSupplier srtSup(final @Nonnull LSrtSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LSrtSupplier {
		private LSrtSupplier target = null;
		@Override
		public short getAsSrtX() throws Throwable {
			return target.getAsSrtX();
		}
	}

	@Nonnull
	static LSrtSupplier recursive(final @Nonnull LFunction<LSrtSupplier, LSrtSupplier> selfLambda) {
		final S single = new S();
		LSrtSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(LSrtSupplier function) {
		var initialValue = function.getAsSrt();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(short initialValue, LSrtSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.getAsSrt();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LSrtSupplier function) {
		var initialValue = function.getAsSrt();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static M initializedDeltaOf(short initialValue, LSrtSupplier function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static M memento(short initialBaseValue, short initialValue, LSrtSupplier baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LSrtSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LSrtSupplier {

		private final LSrtSupplier baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LSrtSupplier baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short getAsSrtX() throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.getAsSrtX();

			return lastValue = mementoFunction.applyAsSrtX(lastValue, x1, x2);
		}

		public short currentGetAsSrt() {
			short x1 = lastBaseValue;
			short x2 = baseFunction.getAsSrt();

			return mementoFunction.applyAsSrt(lastValue, x1, x2);
		}

		public short lastValue() {
			return lastValue;
		}

		public short lastBaseValue() {
			return lastBaseValue;
		}

		public short currentBaseValue() {
			return baseFunction.getAsSrt();
		}
	}

	// </editor-fold>

	@Nonnull
	static LSrtSupplier srtSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtSupplier srtSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static short call(final @Nonnull LSrtSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsSrt();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsSrt());
	}

	// </editor-fold>

	default LSrtSupplier shoving() {

		return new LSrtSupplier() {

			public short getAsSrt() {
				try {
					return this.getAsSrtX();
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public short getAsSrtX() throws Throwable {
				return LSrtSupplier.this.getAsSrtX();
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtSupplier) Supplier */
	public static short doNothing() {
		return Function4U.defaultShort;
	}

}
