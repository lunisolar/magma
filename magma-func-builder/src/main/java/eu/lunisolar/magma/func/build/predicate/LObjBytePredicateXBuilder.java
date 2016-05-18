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

package eu.lunisolar.magma.func.build.predicate;

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

/** Builder for LObjBytePredicateX. */
public final class LObjBytePredicateXBuilder<T, X extends Throwable> extends PerCaseBuilderWithBoolProduct.Base<LObjBytePredicateXBuilder<T, X>, LObjBytePredicateX<T, X>, LObjBytePredicateX<T, X>> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LObjBytePredicateX<T, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LObjBytePredicateX EVENTUALLY_THROW = LObjBytePredicateX.lX((a1, a2) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LObjBytePredicateXBuilder(@Nullable Consumer<LObjBytePredicateX<T, X>> consumer) {
		super(EVENTUALLY_THROW, LObjBytePredicateX::constant, () -> new LObjBytePredicateXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LObjBytePredicateXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T, X extends Throwable> LObjBytePredicateXBuilder<T, X> objBytePredicateX() {
		return new LObjBytePredicateXBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T, X extends Throwable> LObjBytePredicateX<T, X> objBytePredicateXFrom(Function<LObjBytePredicateXBuilder<T, X>, LObjBytePredicateX<T, X>> buildingFunction) {
		return buildingFunction.apply(new LObjBytePredicateXBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T, X extends Throwable> LObjBytePredicateXBuilder<T, X> objBytePredicateX(Consumer<LObjBytePredicateX<T, X>> consumer) {
		return new LObjBytePredicateXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LObjBytePredicateXBuilder<T, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LObjBytePredicateXBuilder<T, X> casesOf(Class<V> argC1, Consumer<LObjBytePredicateXBuilder<V, X>> pcpConsumer) {
		PartialCaseWithBoolProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LObjBytePredicateXBuilder<T, X> aCase(Class<V> argC1, LObjBytePredicateX<V, X> function) {
		PartialCaseWithBoolProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LObjBytePredicateX<T, X> build() {

		final LObjBytePredicateX<T, X> eventuallyFinal = this.eventually;

		LObjBytePredicateX<T, X> retval;

		final Case<LObjBytePredicateX<T, X>, LObjBytePredicateX<T, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LObjBytePredicateX.<T, X> lX((a1, a2) -> {
			try {
				for (Case<LObjBytePredicateX<T, X>, LObjBytePredicateX<T, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a1, a2)) {
						return aCase.caseFunction().doTest(a1, a2);
					}
				}

				return eventuallyFinal.doTest(a1, a2);
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

	public final LObjBytePredicateX<T, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
