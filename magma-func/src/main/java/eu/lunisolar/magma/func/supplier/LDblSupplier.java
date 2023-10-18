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
 * Non-throwing functional interface (lambda) LDblSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblSupplier extends DoubleSupplier, MetaSupplier, MetaInterface.NonThrowing, Codomain<aDouble>, Domain0 { // NOSONAR

	String DESCRIPTION = "LDblSupplier: double getAsDbl()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double getAsDouble() {
		return this.getAsDbl();
	}

	// double getAsDbl() ;
	default double getAsDbl() {
		// return nestingGetAsDbl();
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsDbl()
	 */
	double getAsDblX() throws Throwable;

	default double tupleGetAsDbl(LTuple.Void args) {
		return getAsDbl();
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingGetAsDbl(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsDbl(handling);
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsDbl(factory, newMessage);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsDbl(factory, newMessage, param1);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsDbl(factory, newMessage, param1, param1);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsDbl(factory, newMessage, param1, param2, param3);
	}

	default double getAsDbl(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsDbl(factory);
	}

	default double getAsDblThen(@Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LDblSupplier tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return () -> getAsDblThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingGetAsDbl() {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingGetAsDbl() {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double shovingGetAsDbl(LDblSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsDbl();
	}

	static double handlingGetAsDbl(LDblSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsDbl(handling);
	}

	static double tryGetAsDbl(LDblSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsDbl();
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(factory, newMessage);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(factory, newMessage, param1);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(factory, newMessage, param1, param2);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(factory, newMessage, param1, param2, param3);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(factory);
	}

	static double tryGetAsDblThen(LDblSupplier func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsDblThen(handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullGetAsDbl() {
		return getAsDbl();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsDbl();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsDbl();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LDblSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to one that ignores output. */
	default LAction toAction() {
		return this::getAsDbl;
	}

	/** Calls codomain consumer after main function. */
	default LDblSupplier afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final double retval = getAsDbl();
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblSupplier of(double r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblSupplier dblSup(final @Nonnull LDblSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblSupplier {
		private LDblSupplier target = null;
		@Override
		public double getAsDblX() throws Throwable {
			return target.getAsDblX();
		}
	}

	@Nonnull
	static LDblSupplier recursive(final @Nonnull LFunction<LDblSupplier, LDblSupplier> selfLambda) {
		final S single = new S();
		LDblSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(LDblSupplier function) {
		var initialValue = function.getAsDbl();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(double initialValue, LDblSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LDblSupplier function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.getAsDbl();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LDblSupplier function) {
		var initialValue = function.getAsDbl();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(double initialValue, LDblSupplier function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static M memento(double initialBaseValue, double initialValue, LDblSupplier baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LDblSupplier {

		private final LDblSupplier baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LDblSupplier baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double getAsDblX() throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.getAsDblX();

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentGetAsDbl() {
			double x1 = lastBaseValue;
			double x2 = baseFunction.getAsDbl();

			return mementoFunction.applyAsDbl(lastValue, x1, x2);
		}

		public double lastValue() {
			return lastValue;
		}

		public double lastBaseValue() {
			return lastBaseValue;
		}

		public double currentBaseValue() {
			return baseFunction.getAsDbl();
		}
	}

	// </editor-fold>

	@Nonnull
	static LDblSupplier dblSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblSupplier dblSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static double call(final @Nonnull LDblSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsDbl();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblSupplier wrap(final DoubleSupplier other) {
		return other::getAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsDbl());
	}

	// </editor-fold>

	default LDblSupplier shoving() {

		return new LDblSupplier() {

			public double getAsDbl() {
				try {
					return this.getAsDblX();
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public double getAsDblX() throws Throwable {
				return LDblSupplier.this.getAsDblX();
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblSupplier) Supplier */
	public static double doNothing() {
		return Function4U.defaultDouble;
	}

}
