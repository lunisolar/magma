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

/** Builder for LBiObjShortPredicateX. */
public final class LBiObjShortPredicateXBuilder<T1, T2, X extends Throwable> extends PerCaseBuilderWithBooleanProduct.Base<LBiObjShortPredicateXBuilder<T1, T2, X>, LBiObjShortPredicateX<T1, T2, X>, LBiObjShortPredicateX<T1, T2, X>> {

	private Consumer<LBiObjShortPredicateX<T1, T2, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBiObjShortPredicateX EVENTUALLY_THROW = LBiObjShortPredicateX.lX((Object t1, Object t2, short s) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, s, LBiObjShortPredicateX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LBiObjShortPredicateXBuilder(@Nullable Consumer<LBiObjShortPredicateX<T1, T2, X>> consumer) {
		super(EVENTUALLY_THROW, LBiObjShortPredicateX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiObjShortPredicateXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, X extends Throwable> LBiObjShortPredicateXBuilder<T1, T2, X> biObjShortPredicateX() {
		return new LBiObjShortPredicateXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, X extends Throwable> LBiObjShortPredicateXBuilder<T1, T2, X> biObjShortPredicateX(Consumer<LBiObjShortPredicateX<T1, T2, X>> consumer) {
		return new LBiObjShortPredicateXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBiObjShortPredicateXBuilder<T1, T2, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiObjShortPredicateX<T1, T2, X> build() {

		final LBiObjShortPredicateX<T1, T2, X> eventuallyFinal = this.eventually;

		LBiObjShortPredicateX<T1, T2, X> retval;

		final Case<LBiObjShortPredicateX<T1, T2, X>, LBiObjShortPredicateX<T1, T2, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBiObjShortPredicateX.<T1, T2, X> lX((T1 t1, T2 t2, short s) -> {
			try {
				for (Case<LBiObjShortPredicateX<T1, T2, X>, LBiObjShortPredicateX<T1, T2, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, s)) {
						return aCase.caseFunction().doTest(t1, t2, s);
					}
				}

				return eventuallyFinal.doTest(t1, t2, s);
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

	public final LBiObjShortPredicateX<T1, T2, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
