/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.consumer.primitives.tri;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LBooleanTriConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): boolean b1,boolean b2,boolean b3
 *
 * Co-domain: none
 *
 * @see LBooleanTriConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanTriConsumerX<X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	static final String DESCRIPTION = "LBooleanTriConsumerX: void doAccept(boolean b1,boolean b2,boolean b3) throws X";

	void doAccept(boolean b1, boolean b2, boolean b3) throws X;

	default void nestingDoAccept(boolean b1, boolean b2, boolean b3) {
		try {
			this.doAccept(b1, b2, b3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default void shovingDoAccept(boolean b1, boolean b2, boolean b3) {
		((LBooleanTriConsumerX<RuntimeException>) this).doAccept(b1, b2, b3);
	}

	default <Y extends Throwable> void handlingDoAccept(boolean b1, boolean b2, boolean b3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(b1, b2, b3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanTriConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(boolean b1, boolean b2, boolean b3) {
		return () -> this.doAccept(b1, b2, b3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBooleanTriConsumerX<X> lX(final @Nonnull LBooleanTriConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LBooleanTriConsumerX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LBooleanTriConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBooleanTriConsumerX<X> wrapX(final @Nonnull LBooleanTriConsumer other) {
		return (LBooleanTriConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanTriConsumerX<X> fromBoolean(@Nonnull final LBooleanUnaryOperatorX<X> before1, @Nonnull final LBooleanUnaryOperatorX<X> before2, @Nonnull final LBooleanUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final boolean v1, final boolean v2, final boolean v3) -> this.doAccept(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2), before3.doApplyAsBoolean(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> from(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doAccept(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2), before3.doApplyAsBoolean(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LBooleanTriConsumerX<X> andThen(@Nonnull LBooleanTriConsumerX<X> after) {
		Null.nonNullArg(after, "after");
		return (boolean b1, boolean b2, boolean b3) -> {
			this.doAccept(b1, b2, b3);
			after.doAccept(b1, b2, b3);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanTriConsumer nest() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanTriConsumerX<RuntimeException> nestX() {
		return this::nestingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanTriConsumer shove() {
		return this::shovingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanTriConsumerX<RuntimeException> shoveX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBooleanTriConsumer handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoAccept(b1, b2, b3, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBooleanTriConsumerX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoAccept(b1, b2, b3, handling);
	}

	// </editor-fold>

}
