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

/** Builder for LObjBoolFunction. */
public final class LObjBoolFunctionBuilder<T, R> extends PerCaseBuilderWithProduct.Base<LObjBoolFunctionBuilder<T, R>, LObjBoolPredicate<T>, LObjBoolFunction<T, R>, R> {

	private Consumer<LObjBoolFunction<T, R>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LObjBoolFunction EVENTUALLY_THROW = LObjBoolFunction.l((Object t, boolean b) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, b, LObjBoolFunction.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LObjBoolFunctionBuilder(@Nullable Consumer<LObjBoolFunction<T, R>> consumer) {
		super(EVENTUALLY_THROW, LObjBoolFunction::constant, () -> new LObjBoolFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LObjBoolFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T, R> LObjBoolFunctionBuilder<T, R> objBoolFunction() {
		return new LObjBoolFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T, R> LObjBoolFunctionBuilder<T, R> objBoolFunction(Consumer<LObjBoolFunction<T, R>> consumer) {
		return new LObjBoolFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LObjBoolFunctionBuilder<T, R> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T> LObjBoolFunctionBuilder<T, R> casesOf(Class<E1> argC1, Consumer<LObjBoolFunctionBuilder<E1, R>> pcpConsumer) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((T t, boolean b) -> (argC1 == null || argC1.isInstance(t)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T> LObjBoolFunctionBuilder<T, R> aCase(Class<E1> argC1, LObjBoolFunction<E1, R> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((T t, boolean b) -> (argC1 == null || argC1.isInstance(t)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LObjBoolFunction<T, R> build() {

		final LObjBoolFunction<T, R> eventuallyFinal = this.eventually;

		LObjBoolFunction<T, R> retval;

		final Case<LObjBoolPredicate<T>, LObjBoolFunction<T, R>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LObjBoolFunction.<T, R> l((T t, boolean b) -> {
			try {
				for (Case<LObjBoolPredicate<T>, LObjBoolFunction<T, R>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t, b)) {
						return aCase.caseFunction().doApply(t, b);
					}
				}

				return eventuallyFinal.doApply(t, b);
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

	public final LObjBoolFunction<T, R> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}