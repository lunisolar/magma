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

package eu.lunisolar.magma.func.build.operator.ternary;

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

/**
 * Builder for LTernaryOperator.
 */
public final class LTernaryOperatorBuilder<T> extends PerCaseBuilderWithProduct.Base<LTernaryOperatorBuilder<T>, LTriPredicate<T, T, T>, LTernaryOperator<T>, T> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LTernaryOperator<T>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LTernaryOperator EVENTUALLY_THROW = LTernaryOperator.ternaryOp((a1, a2, a3) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LTernaryOperatorBuilder(@Nullable Consumer<LTernaryOperator<T>> consumer) {
		super(EVENTUALLY_THROW, LTernaryOperator::constant, () -> new LTernaryOperatorBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LTernaryOperatorBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T> LTernaryOperatorBuilder<T> ternaryOperator() {
		return new LTernaryOperatorBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T> LTernaryOperator<T> ternaryOperatorFrom(Function<LTernaryOperatorBuilder<T>, LTernaryOperator<T>> buildingFunction) {
		return buildingFunction.apply(new LTernaryOperatorBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T> LTernaryOperatorBuilder<T> ternaryOperator(Consumer<LTernaryOperator<T>> consumer) {
		return new LTernaryOperatorBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LTernaryOperatorBuilder<T> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LTernaryOperatorBuilder<T> casesOf(Class<V> argC1, Class<V> argC2, Class<V> argC3, Consumer<LTernaryOperatorBuilder<V>> pcpConsumer) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LTernaryOperatorBuilder<T> aCase(Class<V> argC1, Class<V> argC2, Class<V> argC3, LTernaryOperator<V> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LTernaryOperator<T> build() {

		final LTernaryOperator<T> eventuallyFinal = this.eventually;

		LTernaryOperator<T> retval;

		final Case<LTriPredicate<T, T, T>, LTernaryOperator<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LTernaryOperator.<T> ternaryOp((a1, a2, a3) -> {
			try {
				for (Case<LTriPredicate<T, T, T>, LTernaryOperator<T>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a1, a2, a3)) {
						return aCase.caseFunction().doApply(a1, a2, a3);
					}
				}

				return eventuallyFinal.doApply(a1, a2, a3);
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

	public final LTernaryOperator<T> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
