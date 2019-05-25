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
 * Non-throwing functional interface (lambda) LBoolSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSupplier extends BooleanSupplier, MetaSupplier, MetaInterface.NonThrowing, Codomain<aBool>, Domain0 { // NOSONAR

	String DESCRIPTION = "LBoolSupplier: boolean getAsBool()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBoolSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean getAsBoolean() {
		return this.getAsBool();
	}

	// boolean getAsBool() ;
	default boolean getAsBool() {
		// return nestingGetAsBool();
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsBool()
	 */
	boolean getAsBoolX() throws Throwable;

	default boolean tupleGetAsBool(LTuple.Void args) {
		return getAsBool();
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingGetAsBool(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBoolSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsBool(handling);
	}

	default boolean getAsBool(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBoolSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return () -> getAsBool(exF, newMessage, messageParams);
	}

	default boolean getAsBool(@Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBoolSupplier trying(@Nonnull ExWF<RuntimeException> exF) {
		return () -> getAsBool(exF);
	}

	default boolean getAsBoolThen(@Nonnull LPredicate<Throwable> handler) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBoolSupplier tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return () -> getAsBoolThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingGetAsBool() {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingGetAsBool() {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingGetAsBool(LBoolSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsBool(handling);
	}

	static boolean tryGetAsBool(LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsBool();
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(exF, newMessage, messageParams);
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(exF);
	}

	static boolean tryGetAsBoolThen(LBoolSupplier func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsBoolThen(handler);
	}

	default boolean failSafeGetAsBool(@Nonnull LBoolSupplier failSafe) {
		try {
			return getAsBool();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.getAsBool();
		}
	}

	static boolean failSafeGetAsBool(LBoolSupplier func, @Nonnull LBoolSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.getAsBool();
		} else {
			return func.failSafeGetAsBool(failSafe);
		}
	}

	static LBoolSupplier failSafe(LBoolSupplier func, @Nonnull LBoolSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGetAsBool(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullGetAsBool() {
		return getAsBool();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsBool();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsBool();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsBool();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsBool();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LBoolSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LBoolSupplier of(boolean r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolSupplier boolSup(final @Nonnull LBoolSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBoolSupplier recursive(final @Nonnull LFunction<LBoolSupplier, LBoolSupplier> selfLambda) {
		final LBoolSupplierSingle single = new LBoolSupplierSingle();
		LBoolSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBoolSupplierSingle implements LSingle<LBoolSupplier>, LBoolSupplier {
		private LBoolSupplier target = null;

		@Override
		public boolean getAsBoolX() throws Throwable {
			return target.getAsBoolX();
		}

		@Override
		public LBoolSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LBoolSupplier boolSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBoolSupplier boolSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static boolean call(final @Nonnull LBoolSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsBool();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LBoolSupplier wrap(final BooleanSupplier other) {
		return other::getAsBoolean;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceBoolean). */
	@Nonnull
	static LBoolSupplier safe() {
		return LBoolSupplier::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBoolSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBoolSupplier safe(final @Nullable LBoolSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBoolSupplier> safeSupplier(final @Nullable LSupplier<LBoolSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsBool());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LBoolSupplier) Supplier */
	public static boolean produceBoolean() {
		return Function4U.defaultBoolean;
	}

}
