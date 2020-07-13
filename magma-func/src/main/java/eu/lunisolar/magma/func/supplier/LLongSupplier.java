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
 * Non-throwing functional interface (lambda) LLongSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongSupplier extends LongSupplier, MetaSupplier, MetaInterface.NonThrowing, Codomain<aLong>, Domain0 { // NOSONAR

	String DESCRIPTION = "LLongSupplier: long getAsLong()";

	// long getAsLong() ;
	default long getAsLong() {
		// return nestingGetAsLong();
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsLong()
	 */
	long getAsLongX() throws Throwable;

	default long tupleGetAsLong(LTuple.Void args) {
		return getAsLong();
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingGetAsLong(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsLong(handling);
	}

	default long getAsLong(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default long getAsLong(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default long getAsLong(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default long getAsLong(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LLongSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return () -> getAsLong(exF, newMessage);
	}

	default LLongSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsLong(exF, newMessage, param1);
	}

	default LLongSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsLong(exF, newMessage, param1, param1);
	}

	default LLongSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsLong(exF, newMessage, param1, param2, param3);
	}

	default long getAsLong(@Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LLongSupplier trying(@Nonnull ExWF<RuntimeException> exF) {
		return () -> getAsLong(exF);
	}

	default long getAsLongThen(@Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LLongSupplier tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return () -> getAsLongThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingGetAsLong() {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingGetAsLong() {
		try {
			return this.getAsLongX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingGetAsLong(LLongSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsLong(handling);
	}

	static long tryGetAsLong(LLongSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsLong();
	}

	static long tryGetAsLong(LLongSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsLong(exF, newMessage);
	}

	static long tryGetAsLong(LLongSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsLong(exF, newMessage, param1);
	}

	static long tryGetAsLong(LLongSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsLong(exF, newMessage, param1, param2);
	}

	static long tryGetAsLong(LLongSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsLong(exF, newMessage, param1, param2, param3);
	}

	static long tryGetAsLong(LLongSupplier func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.getAsLong(exF);
	}

	static long tryGetAsLongThen(LLongSupplier func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsLongThen(handler);
	}

	default long failSafeGetAsLong(@Nonnull LLongSupplier failSafe) {
		try {
			return getAsLong();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.getAsLong();
		}
	}

	static long failSafeGetAsLong(LLongSupplier func, @Nonnull LLongSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.getAsLong();
		} else {
			return func.failSafeGetAsLong(failSafe);
		}
	}

	static LLongSupplier failSafe(LLongSupplier func, @Nonnull LLongSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGetAsLong(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullGetAsLong() {
		return getAsLong();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LLongSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsLong();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsLong();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LLongSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsLong();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsLong();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LLongSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to consumer that ignores output. */
	default LAction toConsumer() {
		return this::getAsLong;
	}

	/** Calls codomain consumer after main function. */
	default LLongSupplier afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final long retval = getAsLong();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LLongSupplier of(long r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongSupplier longSup(final @Nonnull LLongSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LLongSupplier {
		private LLongSupplier target = null;
		@Override
		public long getAsLongX() throws Throwable {
			return target.getAsLongX();
		}
	}

	@Nonnull
	static LLongSupplier recursive(final @Nonnull LFunction<LLongSupplier, LLongSupplier> selfLambda) {
		final S single = new S();
		LLongSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(LLongSupplier function) {
		var initialValue = function.getAsLong();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LLongSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LLongSupplier function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.getAsLong();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LLongSupplier function) {
		var initialValue = function.getAsLong();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LLongSupplier function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LLongSupplier baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLongSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LLongSupplier {

		private final LLongSupplier baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LLongSupplier baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long getAsLongX() throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.getAsLongX();

			return lastValue = mementoFunction.applyAsLong(lastValue, x1, x2);
		}

		public long lastValue() {
			return lastValue;
		};

		public long lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LLongSupplier longSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongSupplier longSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static long call(final @Nonnull LLongSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsLong();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongSupplier wrap(final LongSupplier other) {
		return other::getAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LLongSupplier safe() {
		return LLongSupplier::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongSupplier safe(final @Nullable LLongSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongSupplier> safeSupplier(final @Nullable LSupplier<LLongSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsLong());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsLong());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLongSupplier) Supplier */
	public static long doNothing() {
		return Function4U.defaultLong;
	}

}
