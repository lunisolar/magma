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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
 * Throwing functional interface (lambda) LFloatBiConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): float f1,float f2
 *
 * Co-domain: none
 *
 * @see LFloatBiConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatBiConsumerX<X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LFloatBiConsumerX: void doAccept(float f1,float f2) throws X";

	public void doAccept(float f1, float f2) throws X;

	default void nestingDoAccept(float f1, float f2) {
		try {
			this.doAccept(f1, f2);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default void shovingDoAccept(float f1, float f2) {
		((LFloatBiConsumerX<RuntimeException>) this).doAccept(f1, f2);
	}

	default <Y extends Throwable> void handlingDoAccept(float f1, float f2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(f1, f2);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatBiConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(float f1, float f2) {
		return () -> this.doAccept(f1, f2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LFloatBiConsumerX<X> lX(final @Nonnull LFloatBiConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LFloatBiConsumerX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatBiConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LFloatBiConsumerX<X> wrapX(final @Nonnull LFloatBiConsumer other) {
		return (LFloatBiConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatBiConsumerX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1, @Nonnull final LFloatUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final float v1, final float v2) -> this.doAccept(before1.doApplyAsFloat(v1), before2.doApplyAsFloat(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiConsumerX<V1, V2, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1, @Nonnull final LToFloatFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doAccept(before1.doApplyAsFloat(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LFloatBiConsumerX<X> andThen(@Nonnull LFloatBiConsumerX<X> after) {
		Null.nonNullArg(after, "after");
		return (float f1, float f2) -> {
			this.doAccept(f1, f2);
			after.doAccept(f1, f2);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatBiConsumer nest() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatBiConsumerX<RuntimeException> nestX() {
		return this::nestingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatBiConsumer shove() {
		return this::shovingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatBiConsumerX<RuntimeException> shoveX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatBiConsumer handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (float f1, float f2) -> this.handlingDoAccept(f1, f2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatBiConsumerX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (float f1, float f2) -> this.handlingDoAccept(f1, f2, handling);
	}

	// </editor-fold>

}
