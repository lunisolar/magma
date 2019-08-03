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
public interface LSrtSupplier extends MetaSupplier, MetaInterface.NonThrowing, Codomain<aShort>, Domain0 { // NOSONAR

	String DESCRIPTION = "LSrtSupplier: short getAsSrt()";

	// short getAsSrt() ;
	default short getAsSrt() {
		// return nestingGetAsSrt();
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
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsSrt(handling);
	}

	default short getAsSrt(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return () -> getAsSrt(exF, newMessage, messageParams);
	}

	default short getAsSrt(@Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtSupplier trying(@Nonnull ExWF<RuntimeException> exF) {
		return () -> getAsSrt(exF);
	}

	default short getAsSrtThen(@Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.getAsSrtX();
		} catch (Throwable e) { // NOSONAR
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
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingGetAsSrt(LSrtSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsSrt(handling);
	}

	static short tryGetAsSrt(LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsSrt();
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(exF, newMessage, messageParams);
	}

	static short tryGetAsSrt(LSrtSupplier func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.getAsSrt(exF);
	}

	static short tryGetAsSrtThen(LSrtSupplier func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsSrtThen(handler);
	}

	default short failSafeGetAsSrt(@Nonnull LSrtSupplier failSafe) {
		try {
			return getAsSrt();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.getAsSrt();
		}
	}

	static short failSafeGetAsSrt(LSrtSupplier func, @Nonnull LSrtSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.getAsSrt();
		} else {
			return func.failSafeGetAsSrt(failSafe);
		}
	}

	static LSrtSupplier failSafe(LSrtSupplier func, @Nonnull LSrtSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGetAsSrt(func, failSafe);
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

	/** Change function to consumer that ignores output. */
	public default LAction toConsumer() {
		return this::getAsSrt;
	}

	/** Calls codomain consumer after main function. */
	public default LSrtSupplier afterDo(@Nonnull LSrtConsumer after) {
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

	@Nonnull
	static LSrtSupplier recursive(final @Nonnull LFunction<LSrtSupplier, LSrtSupplier> selfLambda) {
		final LSrtSupplierSingle single = new LSrtSupplierSingle();
		LSrtSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtSupplierSingle implements LSingle<LSrtSupplier>, LSrtSupplier {
		private LSrtSupplier target = null;

		@Override
		public short getAsSrtX() throws Throwable {
			return target.getAsSrtX();
		}

		@Override
		public LSrtSupplier value() {
			return target;
		}
	}

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

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceShort). */
	@Nonnull
	static LSrtSupplier safe() {
		return LSrtSupplier::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtSupplier safe(final @Nullable LSrtSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtSupplier> safeSupplier(final @Nullable LSupplier<LSrtSupplier> supplier) {
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

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtSupplier) Supplier */
	public static short produceShort() {
		return Function4U.defaultShort;
	}

}
