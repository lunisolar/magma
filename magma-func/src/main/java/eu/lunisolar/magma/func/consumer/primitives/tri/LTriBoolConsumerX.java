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
 * Throwing functional interface (lambda) LTriBoolConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): boolean b1,boolean b2,boolean b3
 *
 * Co-domain: none
 *
 * @see LTriBoolConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriBoolConsumerX<X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	static final String DESCRIPTION = "LTriBoolConsumerX: void doAccept(boolean b1,boolean b2,boolean b3) throws X";

	void doAccept(boolean b1, boolean b2, boolean b3) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default void nestingDoAccept(boolean b1, boolean b2, boolean b3) {
		try {
			this.doAccept(b1, b2, b3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(boolean b1, boolean b2, boolean b3) {
		((LTriBoolConsumerX<RuntimeException>) this).doAccept(b1, b2, b3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoAccept(boolean b1, boolean b2, boolean b3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(b1, b2, b3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriBoolConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> captureTriBoolCons(boolean b1, boolean b2, boolean b3) {
		return () -> this.doAccept(b1, b2, b3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> accept1st(@Nonnull LBoolConsumerX<X> func) {
		return (b1, b2, b3) -> func.doAccept(b1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> accept2nd(@Nonnull LBoolConsumerX<X> func) {
		return (b1, b2, b3) -> func.doAccept(b2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> accept3rd(@Nonnull LBoolConsumerX<X> func) {
		return (b1, b2, b3) -> func.doAccept(b3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> lX(final @Nonnull LTriBoolConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LTriBoolConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LTriBoolConsumerX<X> wrapX(final @Nonnull LTriBoolConsumer other) {
		return (LTriBoolConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LTriBoolConsumerX<X> triBoolConsComposeBoolean(@Nonnull final LLogicalOperatorX<X> before1, @Nonnull final LLogicalOperatorX<X> before2, @Nonnull final LLogicalOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final boolean v1, final boolean v2, final boolean v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> triBoolConsCompose(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doAccept(before1.doTest(v1), before2.doTest(v2), before3.doTest(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LTriBoolConsumerX<X> andThen(@Nonnull LTriBoolConsumerX<X> after) {
		Null.nonNullArg(after, "after");
		return (boolean b1, boolean b2, boolean b3) -> {
			this.doAccept(b1, b2, b3);
			after.doAccept(b1, b2, b3);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriBoolConsumer nestingTriBoolCons() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTriBoolConsumerX<RuntimeException> nestingTriBoolConsX() {
		return this::nestingDoAccept;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriBoolConsumer shovingTriBoolCons() {
		return this::shovingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriBoolConsumerX<RuntimeException> shovingTriBoolConsX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LTriBoolConsumer handleTriBoolCons(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoAccept(b1, b2, b3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LTriBoolConsumerX<Y> handleTriBoolConsX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoAccept(b1, b2, b3, handling);
	}

	// </editor-fold>

}