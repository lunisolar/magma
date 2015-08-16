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

package eu.lunisolar.magma.func.build.function.to;

import eu.lunisolar.magma.func.function.to.*;
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

/** Builder for LObjIntToIntFunction. */
public final class LObjIntToIntFunctionBuilder<T> extends PerCaseBuilderWithIntProduct.Base<LObjIntToIntFunctionBuilder<T>, LObjIntPredicate<T>, LObjIntToIntFunction<T>> {

	private Consumer<LObjIntToIntFunction<T>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LObjIntToIntFunction EVENTUALLY_THROW = LObjIntToIntFunction.l((Object t, int i) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, i, LObjIntToIntFunction.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LObjIntToIntFunctionBuilder(@Nullable Consumer<LObjIntToIntFunction<T>> consumer) {
		super(EVENTUALLY_THROW, LObjIntToIntFunction::constant, () -> new LObjIntToIntFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LObjIntToIntFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T> LObjIntToIntFunctionBuilder<T> objIntToIntFunction() {
		return new LObjIntToIntFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T> LObjIntToIntFunctionBuilder<T> objIntToIntFunction(Consumer<LObjIntToIntFunction<T>> consumer) {
		return new LObjIntToIntFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LObjIntToIntFunctionBuilder<T> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T> LObjIntToIntFunctionBuilder<T> casesOf(Class<E1> argC1, Consumer<LObjIntToIntFunctionBuilder<E1>> pcpConsumer) {
		PartialCaseWithIntProduct.The pc = partialCaseFactoryMethod((T t, int i) -> (argC1 == null || argC1.isInstance(t)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T> LObjIntToIntFunctionBuilder<T> aCase(Class<E1> argC1, LObjIntToIntFunction<E1> function) {
		PartialCaseWithIntProduct.The pc = partialCaseFactoryMethod((T t, int i) -> (argC1 == null || argC1.isInstance(t)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LObjIntToIntFunction<T> build() {

		final LObjIntToIntFunction<T> eventuallyFinal = this.eventually;

		LObjIntToIntFunction<T> retval;

		final Case<LObjIntPredicate<T>, LObjIntToIntFunction<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LObjIntToIntFunction.<T> l((T t, int i) -> {
			try {
				for (Case<LObjIntPredicate<T>, LObjIntToIntFunction<T>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t, i)) {
						return aCase.caseFunction().doApplyAsInt(t, i);
					}
				}

				return eventuallyFinal.doApplyAsInt(t, i);
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

	public final LObjIntToIntFunction<T> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
