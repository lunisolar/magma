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
 * Builder for LQuadFunction.
 */
public final class LQuadFunctionBuilder<T1, T2, T3, T4, R> extends PerCaseBuilderWithProduct.Base<LQuadFunctionBuilder<T1, T2, T3, T4, R>, LQuadPredicate<T1, T2, T3, T4>, LQuadFunction<T1, T2, T3, T4, R>, R> {
	//extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LQuadFunction<T1, T2, T3, T4, R>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LQuadFunction OTHERWISE_THROW = LQuadFunction.quadFunc((a1, a2, a3, a4) -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LQuadFunctionBuilder(@Nullable Consumer<LQuadFunction<T1, T2, T3, T4, R>> consumer) {
		super(OTHERWISE_THROW, LQuadFunction::constant, () -> new LQuadFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LQuadFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2, T3, T4, R> LQuadFunctionBuilder<T1, T2, T3, T4, R> quadFunction() {
		return new LQuadFunctionBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> quadFunctionFrom(Consumer<LQuadFunctionBuilder<T1, T2, T3, T4, R>> buildingFunction) {
		LQuadFunctionBuilder builder = new LQuadFunctionBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2, T3, T4, R> LQuadFunctionBuilder<T1, T2, T3, T4, R> quadFunction(Consumer<LQuadFunction<T1, T2, T3, T4, R>> consumer) {
		return new LQuadFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LQuadFunctionBuilder<T1, T2, T3, T4, R> withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V1 extends T1, V2 extends T2, V3 extends T3, V4 extends T4> LQuadFunctionBuilder<T1, T2, T3, T4, R> forValue(T1 v1, T2 v2, T3 v3, T4 v4, LQuadFunction<V1, V2, V3, V4, R> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3, a4) -> Objects.equals(a1, v1) && Objects.equals(a2, v2) && Objects.equals(a3, v3) && Objects.equals(a4, v4));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V1 extends T1, V2 extends T2, V3 extends T3, V4 extends T4> PartialCaseWithProduct.The<LQuadFunctionBuilder<T1, T2, T3, T4, R>, LQuadPredicate<T1, T2, T3, T4>, LQuadFunction<T1, T2, T3, T4, R>, R> forValue(T1 v1, T2 v2, T3 v3, T4 v4) {
		return partialCaseFactoryMethod((a1, a2, a3, a4) -> Objects.equals(a1, v1) && Objects.equals(a2, v2) && Objects.equals(a3, v3) && Objects.equals(a4, v4));
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V1 extends T1, V2 extends T2, V3 extends T3, V4 extends T4> LQuadFunctionBuilder<T1, T2, T3, T4, R> casesOf(Class<V1> argC1, Class<V2> argC2, Class<V3> argC3, Class<V4> argC4, Consumer<LQuadFunctionBuilder<V1, V2, V3, V4, R>> pcpConsumer) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3, a4) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)) && (argC4 == null || argC4.isInstance(a4)));
		pc.specifySubCases((Consumer) pcpConsumer);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V1 extends T1, V2 extends T2, V3 extends T3, V4 extends T4> LQuadFunctionBuilder<T1, T2, T3, T4, R> forClass(Class<V1> argC1, Class<V2> argC2, Class<V3> argC3, Class<V4> argC4, LQuadFunction<V1, V2, V3, V4, R> function) {
		PartialCaseWithProduct.The pc = partialCaseFactoryMethod((a1, a2, a3, a4) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)) && (argC4 == null || argC4.isInstance(a4)));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V1 extends T1, V2 extends T2, V3 extends T3, V4 extends T4> PartialCaseWithProduct.The<LQuadFunctionBuilder<T1, T2, T3, T4, R>, LQuadPredicate<T1, T2, T3, T4>, LQuadFunction<T1, T2, T3, T4, R>, R> forClass(Class<V1> argC1, Class<V2> argC2,
			Class<V3> argC3, Class<V4> argC4) {
		return partialCaseFactoryMethod((a1, a2, a3, a4) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)) && (argC3 == null || argC3.isInstance(a3)) && (argC4 == null || argC4.isInstance(a4)));
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LQuadFunction<T1, T2, T3, T4, R> build() {

		final LQuadFunction<T1, T2, T3, T4, R> otherwiseFinal = this.otherwise;

		LQuadFunction<T1, T2, T3, T4, R> retval;

		final Case<LQuadPredicate<T1, T2, T3, T4>, LQuadFunction<T1, T2, T3, T4, R>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LQuadFunction.<T1, T2, T3, T4, R>quadFunc((a1, a2, a3, a4) -> {
			try {
				for (Case<LQuadPredicate<T1, T2, T3, T4>, LQuadFunction<T1, T2, T3, T4, R>> aCase : casesArray) {
					if (aCase.casePredicate().test(a1, a2, a3, a4)) {
						return aCase.caseFunction().apply(a1, a2, a3, a4);
					}
				}

				return otherwiseFinal.apply(a1, a2, a3, a4);
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

	public final LQuadFunction<T1, T2, T3, T4, R> build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
