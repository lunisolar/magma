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

package eu.lunisolar.magma.func.build.function.from;

import eu.lunisolar.magma.func.function.from.*;
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

/** Builder for LObjFloatFunctionX. */
public final class LObjFloatFunctionXBuilder<T, R, X extends Throwable> extends PerCaseBuilderWithProduct.Base<LObjFloatFunctionXBuilder<T, R, X>, LObjFloatPredicateX<T, X>, LObjFloatFunctionX<T, R, X>, R> {

	private Consumer<LObjFloatFunctionX<T, R, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LObjFloatFunctionX EVENTUALLY_THROW = LObjFloatFunctionX.lX((Object t, float f) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, f, LObjFloatFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LObjFloatFunctionXBuilder(@Nullable Consumer<LObjFloatFunctionX<T, R, X>> consumer) {
		super(EVENTUALLY_THROW, LObjFloatFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LObjFloatFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T, R, X extends Throwable> LObjFloatFunctionXBuilder<T, R, X> objFloatFunctionX() {
		return new LObjFloatFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T, R, X extends Throwable> LObjFloatFunctionXBuilder<T, R, X> objFloatFunctionX(Consumer<LObjFloatFunctionX<T, R, X>> consumer) {
		return new LObjFloatFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LObjFloatFunctionXBuilder<T, R, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LObjFloatFunctionX<T, R, X> build() {

		final LObjFloatFunctionX<T, R, X> eventuallyFinal = this.eventually;

		LObjFloatFunctionX<T, R, X> retval;

		final Case<LObjFloatPredicateX<T, X>, LObjFloatFunctionX<T, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LObjFloatFunctionX.lX((T t, float f) -> {
			try {
				for (Case<LObjFloatPredicateX<T, X>, LObjFloatFunctionX<T, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t, f)) {
						return aCase.caseFunction().doApply(t, f);
					}
				}

				return eventuallyFinal.doApply(t, f);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LObjFloatFunctionX<T, R, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
