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

package eu.lunisolar.magma.func.build.consumer.primitives.obj;

import eu.lunisolar.magma.func.consumer.primitives.obj.*;
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

/** Builder for LBiObjDoubleConsumerX. */
public final class LBiObjDoubleConsumerXBuilder<T1, T2, X extends Throwable> extends PerCaseBuilder.Base<LBiObjDoubleConsumerXBuilder<T1, T2, X>, LBiObjDoublePredicateX<T1, T2, X>, LBiObjDoubleConsumerX<T1, T2, X>> {

	private Consumer<LBiObjDoubleConsumerX<T1, T2, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBiObjDoubleConsumerX EVENTUALLY_THROW = LBiObjDoubleConsumerX.lX((Object t1, Object t2, double d) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, d, LBiObjDoubleConsumerX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LBiObjDoubleConsumerXBuilder(@Nullable Consumer<LBiObjDoubleConsumerX<T1, T2, X>> consumer) {
		super(EVENTUALLY_THROW, () -> new LBiObjDoubleConsumerXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiObjDoubleConsumerXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjDoubleConsumerXBuilder<T1, T2, X> biObjDoubleConsumerX() {
		return new LBiObjDoubleConsumerXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjDoubleConsumerXBuilder<T1, T2, X> biObjDoubleConsumerX(Consumer<LBiObjDoubleConsumerX<T1, T2, X>> consumer) {
		return new LBiObjDoubleConsumerXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBiObjDoubleConsumerXBuilder<T1, T2, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T1, E2 extends T2> LBiObjDoubleConsumerXBuilder<T1, T2, X> casesOf(Class<E1> argC1, Class<E2> argC2, Consumer<LBiObjDoubleConsumerXBuilder<E1, E2, X>> pcpConsumer) {
		PartialCase.The pc = partialCaseFactoryMethod((T1 t1, T2 t2, double d) -> (argC1 == null || argC1.isInstance(t1)) && (argC2 == null || argC2.isInstance(t2)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T1, E2 extends T2> LBiObjDoubleConsumerXBuilder<T1, T2, X> aCase(Class<E1> argC1, Class<E2> argC2, LBiObjDoubleConsumerX<E1, E2, X> function) {
		PartialCase.The pc = partialCaseFactoryMethod((T1 t1, T2 t2, double d) -> (argC1 == null || argC1.isInstance(t1)) && (argC2 == null || argC2.isInstance(t2)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiObjDoubleConsumerX<T1, T2, X> build() {

		final LBiObjDoubleConsumerX<T1, T2, X> eventuallyFinal = this.eventually;

		LBiObjDoubleConsumerX<T1, T2, X> retval;

		final Case<LBiObjDoublePredicateX<T1, T2, X>, LBiObjDoubleConsumerX<T1, T2, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBiObjDoubleConsumerX.<T1, T2, X> lX((T1 t1, T2 t2, double d) -> {
			try {
				for (Case<LBiObjDoublePredicateX<T1, T2, X>, LBiObjDoubleConsumerX<T1, T2, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, d)) {
						aCase.caseFunction().doAccept(t1, t2, d);
						return;
					}
				}

				eventuallyFinal.doAccept(t1, t2, d);
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

	public final LBiObjDoubleConsumerX<T1, T2, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
