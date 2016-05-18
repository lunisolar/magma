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

package eu.lunisolar.magma.func.build.operator.binary;

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

/** Builder for LFloatBinaryOperatorX. */
public final class LFloatBinaryOperatorXBuilder<X extends Throwable> extends PerCaseBuilderWithFloatProduct.Base<LFloatBinaryOperatorXBuilder<X>, LBiFloatPredicateX<X>, LFloatBinaryOperatorX<X>> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LFloatBinaryOperatorX<X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LFloatBinaryOperatorX EVENTUALLY_THROW = LFloatBinaryOperatorX.lX((a1, a2) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LFloatBinaryOperatorXBuilder(@Nullable Consumer<LFloatBinaryOperatorX<X>> consumer) {
		super(EVENTUALLY_THROW, LFloatBinaryOperatorX::constant, () -> new LFloatBinaryOperatorXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LFloatBinaryOperatorXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <X extends Throwable> LFloatBinaryOperatorXBuilder<X> floatBinaryOperatorX() {
		return new LFloatBinaryOperatorXBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <X extends Throwable> LFloatBinaryOperatorX<X> floatBinaryOperatorXFrom(Function<LFloatBinaryOperatorXBuilder<X>, LFloatBinaryOperatorX<X>> buildingFunction) {
		return buildingFunction.apply(new LFloatBinaryOperatorXBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <X extends Throwable> LFloatBinaryOperatorXBuilder<X> floatBinaryOperatorX(Consumer<LFloatBinaryOperatorX<X>> consumer) {
		return new LFloatBinaryOperatorXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LFloatBinaryOperatorXBuilder<X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LFloatBinaryOperatorX<X> build() {

		final LFloatBinaryOperatorX<X> eventuallyFinal = this.eventually;

		LFloatBinaryOperatorX<X> retval;

		final Case<LBiFloatPredicateX<X>, LFloatBinaryOperatorX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LFloatBinaryOperatorX.<X> lX((a1, a2) -> {
			try {
				for (Case<LBiFloatPredicateX<X>, LFloatBinaryOperatorX<X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a1, a2)) {
						return aCase.caseFunction().doApplyAsFloat(a1, a2);
					}
				}

				return eventuallyFinal.doApplyAsFloat(a1, a2);
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

	public final LFloatBinaryOperatorX<X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
