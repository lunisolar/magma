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
 * Builder for LToSrtBiFunction.
 */
public final class LToSrtBiFunctionBuilder<T1, T2> extends PerCaseBuilderWithSrtProduct.Base<LToSrtBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, LToSrtBiFunction<T1, T2>> {
	//extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LToSrtBiFunction<T1, T2>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LToSrtBiFunction OTHERWISE_THROW = LToSrtBiFunction.toSrtBiFunc((a1, a2) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LToSrtBiFunctionBuilder(@Nullable Consumer<LToSrtBiFunction<T1, T2>> consumer) {
		super(OTHERWISE_THROW, LToSrtBiFunction::constant, () -> new LToSrtBiFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LToSrtBiFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2> LToSrtBiFunctionBuilder<T1, T2> toSrtBiFunction() {
		return new LToSrtBiFunctionBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T1, T2> LToSrtBiFunction<T1, T2> toSrtBiFunctionFrom(Consumer<LToSrtBiFunctionBuilder<T1, T2>> buildingFunction) {
		LToSrtBiFunctionBuilder builder = new LToSrtBiFunctionBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2> LToSrtBiFunctionBuilder<T1, T2> toSrtBiFunction(Consumer<LToSrtBiFunction<T1, T2>> consumer) {
		return new LToSrtBiFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LToSrtBiFunctionBuilder<T1, T2> withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V1 extends T1, V2 extends T2> LToSrtBiFunctionBuilder<T1, T2> forValue(T1 v1, T2 v2, LToSrtBiFunction<V1, V2> function) {
		PartialCaseWithSrtProduct.The pc = partialCaseFactoryMethod((a1, a2) -> Objects.equals(a1, v1) && Objects.equals(a2, v2));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V1 extends T1, V2 extends T2> PartialCaseWithSrtProduct.The<LToSrtBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, LToSrtBiFunction<T1, T2>> forValue(T1 v1, T2 v2) {
		return partialCaseFactoryMethod((a1, a2) -> Objects.equals(a1, v1) && Objects.equals(a2, v2));
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V1 extends T1, V2 extends T2> LToSrtBiFunctionBuilder<T1, T2> casesOf(Class<V1> argC1, Class<V2> argC2, Consumer<LToSrtBiFunctionBuilder<V1, V2>> pcpConsumer) {
		PartialCaseWithSrtProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)));
		pc.specifySubCases((Consumer) pcpConsumer);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V1 extends T1, V2 extends T2> LToSrtBiFunctionBuilder<T1, T2> forClass(Class<V1> argC1, Class<V2> argC2, LToSrtBiFunction<V1, V2> function) {
		PartialCaseWithSrtProduct.The pc = partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V1 extends T1, V2 extends T2> PartialCaseWithSrtProduct.The<LToSrtBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, LToSrtBiFunction<T1, T2>> forClass(Class<V1> argC1, Class<V2> argC2) {
		return partialCaseFactoryMethod((a1, a2) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)));
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LToSrtBiFunction<T1, T2> build() {

		final LToSrtBiFunction<T1, T2> otherwiseFinal = this.otherwise;

		LToSrtBiFunction<T1, T2> retval;

		final Case<LBiPredicate<T1, T2>, LToSrtBiFunction<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LToSrtBiFunction.<T1, T2>toSrtBiFunc((a1, a2) -> {
			try {
				for (Case<LBiPredicate<T1, T2>, LToSrtBiFunction<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().test(a1, a2)) {
						return aCase.caseFunction().applyAsSrt(a1, a2);
					}
				}

				return otherwiseFinal.applyAsSrt(a1, a2);
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

	public final LToSrtBiFunction<T1, T2> build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
