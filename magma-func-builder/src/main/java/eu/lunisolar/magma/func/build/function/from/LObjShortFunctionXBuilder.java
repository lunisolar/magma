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
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/** Builder for LObjShortFunctionX. */
public final class LObjShortFunctionXBuilder<T, R, X extends Throwable> extends PerCaseBuilderWithProduct.Base<LObjShortFunctionXBuilder<T, R, X>, LObjShortPredicateX<T, X>, LObjShortFunctionX<T, R, X>, R> {

	private Consumer<LObjShortFunctionX<T, R, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LObjShortFunctionX EVENTUALLY_THROW = LObjShortFunctionX.lX((Object a1, short a2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", a1, a2, LObjShortFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LObjShortFunctionXBuilder(@Nullable Consumer<LObjShortFunctionX<T, R, X>> consumer) {
		super(EVENTUALLY_THROW, LObjShortFunctionX::constant, () -> new LObjShortFunctionXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LObjShortFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T, R, X extends Throwable> LObjShortFunctionXBuilder<T, R, X> objShortFunctionX() {
		return new LObjShortFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T, R, X extends Throwable> LObjShortFunctionXBuilder<T, R, X> objShortFunctionX(Consumer<LObjShortFunctionX<T, R, X>> consumer) {
		return new LObjShortFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LObjShortFunctionXBuilder<T, R, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T> LObjShortFunctionXBuilder<T, R, X> casesOf(Class<E1> argC1, Consumer<LObjShortFunctionXBuilder<E1, R, X>> pcpConsumer) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((T a1, short a2) -> (argC1 == null || argC1.isInstance(a1)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T> LObjShortFunctionXBuilder<T, R, X> aCase(Class<E1> argC1, LObjShortFunctionX<E1, R, X> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((T a1, short a2) -> (argC1 == null || argC1.isInstance(a1)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LObjShortFunctionX<T, R, X> build() {

		final LObjShortFunctionX<T, R, X> eventuallyFinal = this.eventually;

		LObjShortFunctionX<T, R, X> retval;

		final Case<LObjShortPredicateX<T, X>, LObjShortFunctionX<T, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LObjShortFunctionX.<T, R, X> lX((T a1, short a2) -> {
			try {
				for (Case<LObjShortPredicateX<T, X>, LObjShortFunctionX<T, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a1, a2)) {
						return aCase.caseFunction().doApply(a1, a2);
					}
				}

				return eventuallyFinal.doApply(a1, a2);
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

	public final LObjShortFunctionX<T, R, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
