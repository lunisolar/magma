/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import java.util.Objects;

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
 * Builder for LOiToCharFunction.
 */
public final class LOiToCharFunctionBuilder<T> extends PerCaseBuilderWithCharProduct.Base<LOiToCharFunctionBuilder<T>, LObjIntPredicate<T>, LOiToCharFunction<T>> {
	//extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LOiToCharFunction<T>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LOiToCharFunction OTHERWISE_THROW = LOiToCharFunction.oiToCharFunc((a1, a2) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LOiToCharFunctionBuilder(@Nullable Consumer<LOiToCharFunction<T>> consumer) {
		super(OTHERWISE_THROW, LOiToCharFunction::constant, () -> new LOiToCharFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LOiToCharFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T> LOiToCharFunctionBuilder<T> oiToCharFunction() {
		return new LOiToCharFunctionBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T> LOiToCharFunction<T> oiToCharFunctionFrom(Consumer<LOiToCharFunctionBuilder<T>> buildingFunction) {
		LOiToCharFunctionBuilder builder = new LOiToCharFunctionBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T> LOiToCharFunctionBuilder<T> oiToCharFunction(Consumer<LOiToCharFunction<T>> consumer) {
		return new LOiToCharFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LOiToCharFunctionBuilder<T> withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> LOiToCharFunctionBuilder<T> forValue(T v1, int v2, LOiToCharFunction<V> function) {
		PartialCaseWithCharProduct.The pc = partialCaseFactoryMethod((a1, a2) -> Objects.equals(a1, v1) && a2 == v2);
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> PartialCaseWithCharProduct.The<LOiToCharFunctionBuilder<T>, LObjIntPredicate<T>, LOiToCharFunction<T>> forValue(T v1, int v2) {
		return partialCaseFactoryMethod((a1, a2) -> Objects.equals(a1, v1) && a2 == v2);
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LOiToCharFunctionBuilder<T> casesOf(Class<V> argC1, Consumer<LOiToCharFunctionBuilder<V>> pcpConsumer) {
		PartialCaseWithCharProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)));
		pc.specifySubCases((Consumer) pcpConsumer);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LOiToCharFunctionBuilder<T> forClass(Class<V> argC1, LOiToCharFunction<V> function) {
		PartialCaseWithCharProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> PartialCaseWithCharProduct.The<LOiToCharFunctionBuilder<T>, LObjIntPredicate<T>, LOiToCharFunction<T>> forClass(Class<V> argC1) {
		return partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)));
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LOiToCharFunction<T> build() {

		final LOiToCharFunction<T> otherwiseFinal = this.otherwise;

		LOiToCharFunction<T> retval;

		final Case<LObjIntPredicate<T>, LOiToCharFunction<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LOiToCharFunction.<T>oiToCharFunc((a1, a2) -> {
			try {
				for (Case<LObjIntPredicate<T>, LOiToCharFunction<T>> aCase : casesArray) {
					if (aCase.casePredicate().test(a1, a2)) {
						return aCase.caseFunction().applyAsChar(a1, a2);
					}
				}

				return otherwiseFinal.applyAsChar(a1, a2);
			} catch (Error e) { //NOSONAR
				throw e;
			} catch (Throwable e) { //NOSONAR
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LOiToCharFunction<T> build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
