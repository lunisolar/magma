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
import java.util.function.*;

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/** Builder for LBiCharFunctionX. */
public final class LBiCharFunctionXBuilder<R, X extends Throwable> extends PerCaseBuilderWithProduct.Base<LBiCharFunctionXBuilder<R, X>, LBiCharPredicateX<X>, LBiCharFunctionX<R, X>, R> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LBiCharFunctionX<R, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBiCharFunctionX EVENTUALLY_THROW = LBiCharFunctionX.lX((a1, a2) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LBiCharFunctionXBuilder(@Nullable Consumer<LBiCharFunctionX<R, X>> consumer) {
		super(EVENTUALLY_THROW, LBiCharFunctionX::constant, () -> new LBiCharFunctionXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiCharFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <R, X extends Throwable> LBiCharFunctionXBuilder<R, X> biCharFunctionX() {
		return new LBiCharFunctionXBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <R, X extends Throwable> LBiCharFunctionX<R, X> biCharFunctionXFrom(Function<LBiCharFunctionXBuilder<R, X>, LBiCharFunctionX<R, X>> buildingFunction) {
		return buildingFunction.apply(new LBiCharFunctionXBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <R, X extends Throwable> LBiCharFunctionXBuilder<R, X> biCharFunctionX(Consumer<LBiCharFunctionX<R, X>> consumer) {
		return new LBiCharFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBiCharFunctionXBuilder<R, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiCharFunctionX<R, X> build() {

		final LBiCharFunctionX<R, X> eventuallyFinal = this.eventually;

		LBiCharFunctionX<R, X> retval;

		final Case<LBiCharPredicateX<X>, LBiCharFunctionX<R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBiCharFunctionX.<R, X> lX((a1, a2) -> {
			try {
				for (Case<LBiCharPredicateX<X>, LBiCharFunctionX<R, X>> aCase : casesArray) {
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

	public final LBiCharFunctionX<R, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
