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
 * Non-throwing functional interface (lambda) LFltSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltSupplier extends MetaSupplier, MetaInterface.NonThrowing, Codomain<aFloat>, Domain0 { // NOSONAR

	String DESCRIPTION = "LFltSupplier: float getAsFlt()";

	// float getAsFlt() ;
	default float getAsFlt() {
		// return nestingGetAsFlt();
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsFlt()
	 */
	float getAsFltX() throws Throwable;

	default float tupleGetAsFlt(LTuple.Void args) {
		return getAsFlt();
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingGetAsFlt(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsFlt(handling);
	}

	default float getAsFlt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float getAsFlt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float getAsFlt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float getAsFlt(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFltSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsFlt(factory, newMessage);
	}

	default LFltSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsFlt(factory, newMessage, param1);
	}

	default LFltSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsFlt(factory, newMessage, param1, param1);
	}

	default LFltSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsFlt(factory, newMessage, param1, param2, param3);
	}

	default float getAsFlt(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFltSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsFlt(factory);
	}

	default float getAsFltThen(@Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LFltSupplier tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return () -> getAsFltThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingGetAsFlt() {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingGetAsFlt() {
		try {
			return this.getAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float shovingGetAsFlt(LFltSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsFlt();
	}

	static float handlingGetAsFlt(LFltSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsFlt(handling);
	}

	static float tryGetAsFlt(LFltSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsFlt();
	}

	static float tryGetAsFlt(LFltSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsFlt(factory, newMessage);
	}

	static float tryGetAsFlt(LFltSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsFlt(factory, newMessage, param1);
	}

	static float tryGetAsFlt(LFltSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsFlt(factory, newMessage, param1, param2);
	}

	static float tryGetAsFlt(LFltSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsFlt(factory, newMessage, param1, param2, param3);
	}

	static float tryGetAsFlt(LFltSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsFlt(factory);
	}

	static float tryGetAsFltThen(LFltSupplier func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsFltThen(handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullGetAsFlt() {
		return getAsFlt();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LFltSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsFlt();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsFlt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LFltSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsFlt();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsFlt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LFltSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to one that ignores output. */
	default LAction toAction() {
		return this::getAsFlt;
	}

	/** Calls codomain consumer after main function. */
	default LFltSupplier afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final float retval = getAsFlt();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LFltSupplier of(float r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltSupplier fltSup(final @Nonnull LFltSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LFltSupplier {
		private LFltSupplier target = null;
		@Override
		public float getAsFltX() throws Throwable {
			return target.getAsFltX();
		}
	}

	@Nonnull
	static LFltSupplier recursive(final @Nonnull LFunction<LFltSupplier, LFltSupplier> selfLambda) {
		final S single = new S();
		LFltSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(LFltSupplier function) {
		var initialValue = function.getAsFlt();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(float initialValue, LFltSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LFltSupplier function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.getAsFlt();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LFltSupplier function) {
		var initialValue = function.getAsFlt();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(float initialValue, LFltSupplier function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static M memento(float initialBaseValue, float initialValue, LFltSupplier baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LFltSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LFltSupplier {

		private final LFltSupplier baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LFltSupplier baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float getAsFltX() throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.getAsFltX();

			return lastValue = mementoFunction.applyAsFltX(lastValue, x1, x2);
		}

		public float currentGetAsFlt() {
			float x1 = lastBaseValue;
			float x2 = baseFunction.getAsFlt();

			return mementoFunction.applyAsFlt(lastValue, x1, x2);
		}

		public float lastValue() {
			return lastValue;
		};

		public float lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LFltSupplier fltSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltSupplier fltSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static float call(final @Nonnull LFltSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsFlt();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsFlt());
	}

	// </editor-fold>

	default LFltSupplier shoving() {

		return new LFltSupplier() {

			public float getAsFlt() {
				try {
					return this.getAsFltX();
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public float getAsFltX() throws Throwable {
				return LFltSupplier.this.getAsFltX();
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltSupplier) Supplier */
	public static float doNothing() {
		return Function4U.defaultFloat;
	}

}
