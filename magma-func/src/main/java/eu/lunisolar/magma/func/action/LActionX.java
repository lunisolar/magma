/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.action;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import java.util.function.Predicate; //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

/**
 * LActionX is a replacement for Runnable.
 *
 * - offers default methods
 * - do not rise warnings about Runnable being call directly
 * - two versions (throwing and non-throwing) have conversion methods that mirrors each other)
 *
 * Throwing functional interface (lambda) LActionX for Java 8.
 *
 * Type: action
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LAction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LActionX<X extends Throwable> extends Runnable, MetaAction, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LActionX: void doExecute() throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LActionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void run() {
		this.nestingDoExecute();
	}

	void doExecute() throws X;

	default LTuple.Void tupleExecute(LTuple.Void args) throws X {
		doExecute();
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoExecute() {
		try {
			this.doExecute();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoExecute() {
		((LActionX<RuntimeException>) this).doExecute();
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoExecute(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doExecute();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LActionX.DESCRIPTION;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LActionX<X> lX(final @Nonnull LActionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LActionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LActionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> void call(final @Nonnull LActionX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		lambda.doExecute();
	}

	static <X extends Throwable> void shoving(final @Nonnull LActionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.shovingDoExecute();
	}

	static <X extends Throwable> void nesting(final @Nonnull LActionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.nestingDoExecute();
	}

	static <X extends Throwable, Y extends Throwable> void handling(final HandlingInstructions<Throwable, Y> handling, final @Nonnull LActionX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		lambda.handlingDoExecute(handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LActionX<X> wrap(final Runnable other) {
		return other::run;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LActionX<X> wrapX(final @Nonnull LAction other) {
		return (LActionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <X extends Throwable> LActionX<X> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LActionX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LActionX<X> safe(final @Nullable LActionX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LActionX<X>, Y> safeSupplier(final @Nullable LSupplierX<LActionX<X>, Y> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LActionX<X> together in a order. */
	@Nonnull
	default LActionX<X> andThen(@Nonnull LActionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> {
			this.doExecute();
			after.doExecute();
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LAction nestingAct() {
		return this::nestingDoExecute;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LActionX<RuntimeException> nestingActX() {
		return this::nestingDoExecute;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LAction shovingAct() {
		return this::shovingDoExecute;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LActionX<RuntimeException> shovingActX() {
		return this::shovingDoExecute;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LAction handleAct(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoExecute(handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LActionX<Y> handleActX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoExecute(handling);
	}

	// </editor-fold>

}
