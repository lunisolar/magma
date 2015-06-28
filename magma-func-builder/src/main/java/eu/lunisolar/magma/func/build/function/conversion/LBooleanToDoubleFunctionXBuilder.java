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

package eu.lunisolar.magma.func.build.function.conversion;

import eu.lunisolar.magma.func.function.conversion.*;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.func.build.*;
import eu.lunisolar.magma.func.Function4U; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import java.util.function.Consumer;
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

/** Builder for LBooleanToDoubleFunctionX. */
public final class LBooleanToDoubleFunctionXBuilder<X extends Throwable> extends PerCaseBuilderWithDoubleProduct.Base<LBooleanToDoubleFunctionXBuilder<X>, LBooleanUnaryOperatorX<X>, LBooleanToDoubleFunctionX<X>> {

	private Consumer<LBooleanToDoubleFunctionX<X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBooleanToDoubleFunctionX EVENTUALLY_THROW = LBooleanToDoubleFunctionX.lX((boolean b) -> {
		String message;
		try {
			message = String.format("No case specified for: %s  as function %s.", b, LBooleanToDoubleFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LBooleanToDoubleFunctionXBuilder(@Nullable Consumer<LBooleanToDoubleFunctionX<X>> consumer) {
		super(EVENTUALLY_THROW, LBooleanToDoubleFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBooleanToDoubleFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <X extends Throwable> LBooleanToDoubleFunctionXBuilder<X> booleanToDoubleFunctionX() {
		return new LBooleanToDoubleFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <X extends Throwable> LBooleanToDoubleFunctionXBuilder<X> booleanToDoubleFunctionX(Consumer<LBooleanToDoubleFunctionX<X>> consumer) {
		return new LBooleanToDoubleFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBooleanToDoubleFunctionXBuilder<X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBooleanToDoubleFunctionX<X> build() {

		final LBooleanToDoubleFunctionX<X> eventuallyFinal = this.eventually;

		LBooleanToDoubleFunctionX<X> retval;

		final Case<LBooleanUnaryOperatorX<X>, LBooleanToDoubleFunctionX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBooleanToDoubleFunctionX.<X> lX(b -> {
			try {
				for (Case<LBooleanUnaryOperatorX<X>, LBooleanToDoubleFunctionX<X>> aCase : casesArray) {
					if (aCase.casePredicate().doApplyAsBoolean(b)) {
						return aCase.caseFunction().doApplyAsDouble(b);
					}
				}

				return eventuallyFinal.doApplyAsDouble(b);
			} catch (Error e) { // NOSONAR
					throw e;
				} catch (Throwable e) { // NOSONAR
					throw Handler.handleOrPropagate(e, handling);
				}
			});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LBooleanToDoubleFunctionX<X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
