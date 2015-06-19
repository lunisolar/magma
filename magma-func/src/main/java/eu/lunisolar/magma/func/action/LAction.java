/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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

/**
 * LAction is a replacement for Runnable.
 *
 * - offers default methods
 * - do not rise warnings about Runnable being call directly
 * - two versions (throwing and non-throwing) have conversion methods that mirrors each other)
 *
 * Non-throwing functional interface (lambda) LAction for Java 8.
 *
 * Type: action
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LActionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LAction extends LActionX<RuntimeException>, MetaAction, MetaInterface.NonThrowing {

	public static final String DESCRIPTION = "LAction: void doExecute()";

	@Override
	@Deprecated
	// calling this method via LAction interface should be discouraged.
	default void run() {
		this.nestingDoExecute();
	}

	public void doExecute();

	default void nestingDoExecute() {
		this.doExecute();
	}

	default void shovingDoExecute() {
		this.doExecute();
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LAction.DESCRIPTION;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LAction l(final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LAction wrap(final Runnable other) {
		return other::run;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LAction wrap(final @Nonnull LActionX<X> other) {
		return other::nestingDoExecute;
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two actions together in a order. */
	@Nonnull
	default LAction andThen(@Nonnull LAction after) {
		Null.nonNullArg(after, "after");
		return () -> {
			this.doExecute();
			after.doExecute();
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LAction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LActionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LAction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LActionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
