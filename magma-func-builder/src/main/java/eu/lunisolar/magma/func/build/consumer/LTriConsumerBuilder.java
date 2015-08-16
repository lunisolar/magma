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

package eu.lunisolar.magma.func.build.consumer;

import eu.lunisolar.magma.func.consumer.*;
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

/** Builder for LTriConsumer. */
public final class LTriConsumerBuilder<T1, T2, T3> extends PerCaseBuilder.Base<LTriConsumerBuilder<T1, T2, T3>, LTriPredicate<T1, T2, T3>, LTriConsumer<T1, T2, T3>> {

	private Consumer<LTriConsumer<T1, T2, T3>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LTriConsumer EVENTUALLY_THROW = LTriConsumer.l((Object t1, Object t2, Object t3) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, t3, LTriConsumer.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LTriConsumerBuilder(@Nullable Consumer<LTriConsumer<T1, T2, T3>> consumer) {
		super(EVENTUALLY_THROW, () -> new LTriConsumerBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LTriConsumerBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2, T3> LTriConsumerBuilder<T1, T2, T3> triConsumer() {
		return new LTriConsumerBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2, T3> LTriConsumerBuilder<T1, T2, T3> triConsumer(Consumer<LTriConsumer<T1, T2, T3>> consumer) {
		return new LTriConsumerBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LTriConsumerBuilder<T1, T2, T3> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T1, E2 extends T2, E3 extends T3> LTriConsumerBuilder<T1, T2, T3> casesOf(Class<E1> argC1, Class<E2> argC2, Class<E3> argC3, Consumer<LTriConsumerBuilder<E1, E2, E3>> pcpConsumer) {
		PartialCase.The pc = partialCaseFactoryMethod((T1 t1, T2 t2, T3 t3) -> (argC1 == null || argC1.isInstance(t1)) && (argC2 == null || argC2.isInstance(t2)) && (argC3 == null || argC3.isInstance(t3)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T1, E2 extends T2, E3 extends T3> LTriConsumerBuilder<T1, T2, T3> aCase(Class<E1> argC1, Class<E2> argC2, Class<E3> argC3, LTriConsumer<E1, E2, E3> function) {
		PartialCase.The pc = partialCaseFactoryMethod((T1 t1, T2 t2, T3 t3) -> (argC1 == null || argC1.isInstance(t1)) && (argC2 == null || argC2.isInstance(t2)) && (argC3 == null || argC3.isInstance(t3)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LTriConsumer<T1, T2, T3> build() {

		final LTriConsumer<T1, T2, T3> eventuallyFinal = this.eventually;

		LTriConsumer<T1, T2, T3> retval;

		final Case<LTriPredicate<T1, T2, T3>, LTriConsumer<T1, T2, T3>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LTriConsumer.<T1, T2, T3> l((T1 t1, T2 t2, T3 t3) -> {
			try {
				for (Case<LTriPredicate<T1, T2, T3>, LTriConsumer<T1, T2, T3>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, t3)) {
						aCase.caseFunction().doAccept(t1, t2, t3);
						return;
					}
				}

				eventuallyFinal.doAccept(t1, t2, t3);
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

	public final LTriConsumer<T1, T2, T3> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
