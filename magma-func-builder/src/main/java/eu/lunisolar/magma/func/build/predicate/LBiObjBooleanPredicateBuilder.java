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

package eu.lunisolar.magma.func.build.predicate;

import eu.lunisolar.magma.func.predicate.*;
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

/** Builder for LBiObjBooleanPredicate. */
public final class LBiObjBooleanPredicateBuilder<T1, T2> extends PerCaseBuilderWithBooleanProduct.Base<LBiObjBooleanPredicateBuilder<T1, T2>, LBiObjBooleanPredicate<T1, T2>, LBiObjBooleanPredicate<T1, T2>> {

	private Consumer<LBiObjBooleanPredicate<T1, T2>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBiObjBooleanPredicate EVENTUALLY_THROW = LBiObjBooleanPredicate.l((Object t1, Object t2, boolean b) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, b, LBiObjBooleanPredicate.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LBiObjBooleanPredicateBuilder(@Nullable Consumer<LBiObjBooleanPredicate<T1, T2>> consumer) {
		super(EVENTUALLY_THROW, LBiObjBooleanPredicate::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiObjBooleanPredicateBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2> LBiObjBooleanPredicateBuilder<T1, T2> biObjBooleanPredicate() {
		return new LBiObjBooleanPredicateBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2> LBiObjBooleanPredicateBuilder<T1, T2> biObjBooleanPredicate(Consumer<LBiObjBooleanPredicate<T1, T2>> consumer) {
		return new LBiObjBooleanPredicateBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBiObjBooleanPredicateBuilder<T1, T2> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiObjBooleanPredicate<T1, T2> build() {

		final LBiObjBooleanPredicate<T1, T2> eventuallyFinal = this.eventually;

		LBiObjBooleanPredicate<T1, T2> retval;

		final Case<LBiObjBooleanPredicate<T1, T2>, LBiObjBooleanPredicate<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBiObjBooleanPredicate.l((T1 t1, T2 t2, boolean b) -> {
			try {
				for (Case<LBiObjBooleanPredicate<T1, T2>, LBiObjBooleanPredicate<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, b)) {
						return aCase.caseFunction().doTest(t1, t2, b);
					}
				}

				return eventuallyFinal.doTest(t1, t2, b);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LBiObjBooleanPredicate<T1, T2> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
