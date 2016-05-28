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

package eu.lunisolar.magma.func.build.function.to;

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

/** Builder for LToFloatFunctionX. */
public final class LToFloatFunctionXBuilder<T, X extends Throwable> extends PerCaseBuilderWithFloatProduct.Base<LToFloatFunctionXBuilder<T, X>, LPredicateX<T, X>, LToFloatFunctionX<T, X>> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LToFloatFunctionX<T, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LToFloatFunctionX EVENTUALLY_THROW = LToFloatFunctionX.lX(a -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LToFloatFunctionXBuilder(@Nullable Consumer<LToFloatFunctionX<T, X>> consumer) {
		super(EVENTUALLY_THROW, LToFloatFunctionX::constant, () -> new LToFloatFunctionXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LToFloatFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T, X extends Throwable> LToFloatFunctionXBuilder<T, X> toFloatFunctionX() {
		return new LToFloatFunctionXBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T, X extends Throwable> LToFloatFunctionX<T, X> toFloatFunctionXFrom(Function<LToFloatFunctionXBuilder<T, X>, LToFloatFunctionX<T, X>> buildingFunction) {
		return buildingFunction.apply(new LToFloatFunctionXBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T, X extends Throwable> LToFloatFunctionXBuilder<T, X> toFloatFunctionX(Consumer<LToFloatFunctionX<T, X>> consumer) {
		return new LToFloatFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LToFloatFunctionXBuilder<T, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LToFloatFunctionXBuilder<T, X> casesOf(Class<V> argC, Consumer<LToFloatFunctionXBuilder<V, X>> pcpConsumer) {
		PartialCaseWithFloatProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LToFloatFunctionXBuilder<T, X> aCase(Class<V> argC, LToFloatFunctionX<V, X> function) {
		PartialCaseWithFloatProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LToFloatFunctionX<T, X> build() {

		final LToFloatFunctionX<T, X> eventuallyFinal = this.eventually;

		LToFloatFunctionX<T, X> retval;

		final Case<LPredicateX<T, X>, LToFloatFunctionX<T, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LToFloatFunctionX.<T, X> lX(a -> {
			try {
				for (Case<LPredicateX<T, X>, LToFloatFunctionX<T, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a)) {
						return aCase.caseFunction().doApplyAsFloat(a);
					}
				}

				return eventuallyFinal.doApplyAsFloat(a);
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

	public final LToFloatFunctionX<T, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
