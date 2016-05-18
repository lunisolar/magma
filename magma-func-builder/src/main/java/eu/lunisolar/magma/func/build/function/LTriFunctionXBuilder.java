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

package eu.lunisolar.magma.func.build.function;

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

/** Builder for LTriFunctionX. */
public final class LTriFunctionXBuilder<T1, T2, T3, R, X extends Throwable> extends PerCaseBuilderWithProduct.Base<LTriFunctionXBuilder<T1, T2, T3, R, X>, LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>, R> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LTriFunctionX EVENTUALLY_THROW = LTriFunctionX.lX((a1, a2, a3) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LTriFunctionXBuilder(@Nullable Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer) {
		super(EVENTUALLY_THROW, LTriFunctionX::constant, () -> new LTriFunctionXBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LTriFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2, T3, R, X extends Throwable> LTriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX() {
		return new LTriFunctionXBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T1, T2, T3, R, X extends Throwable> LTriFunctionX<T1, T2, T3, R, X> triFunctionXFrom(Function<LTriFunctionXBuilder<T1, T2, T3, R, X>, LTriFunctionX<T1, T2, T3, R, X>> buildingFunction) {
		return buildingFunction.apply(new LTriFunctionXBuilder());
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2, T3, R, X extends Throwable> LTriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX(Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer) {
		return new LTriFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LTriFunctionXBuilder<T1, T2, T3, R, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V1 extends T1, V2 extends T2, V3 extends T3> LTriFunctionXBuilder<T1, T2, T3, R, X> casesOf(Class<V1> argC1, Class<V2> argC2, Class<V3> argC3, Consumer<LTriFunctionXBuilder<V1, V2, V3, R, X>> pcpConsumer) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V1 extends T1, V2 extends T2, V3 extends T3> LTriFunctionXBuilder<T1, T2, T3, R, X> aCase(Class<V1> argC1, Class<V2> argC2, Class<V3> argC3, LTriFunctionX<V1, V2, V3, R, X> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LTriFunctionX<T1, T2, T3, R, X> build() {

		final LTriFunctionX<T1, T2, T3, R, X> eventuallyFinal = this.eventually;

		LTriFunctionX<T1, T2, T3, R, X> retval;

		final Case<LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LTriFunctionX.<T1, T2, T3, R, X> lX((a1, a2, a3) -> {
			try {
				for (Case<LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>> aCase : casesArray) {
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

	public final LTriFunctionX<T1, T2, T3, R, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
