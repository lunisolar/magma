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
 * Builder for LToIntFunction.
 */
public final class LToIntFunctionBuilder<T> extends PerCaseBuilderWithIntProduct.Base<LToIntFunctionBuilder<T>, LPredicate<T>, LToIntFunction<T>> {
	//extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LToIntFunction<T>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LToIntFunction OTHERWISE_THROW = LToIntFunction.toIntFunc(a -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LToIntFunctionBuilder(@Nullable Consumer<LToIntFunction<T>> consumer) {
		super(OTHERWISE_THROW, LToIntFunction::constant, () -> new LToIntFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LToIntFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T> LToIntFunctionBuilder<T> toIntFunction() {
		return new LToIntFunctionBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T> LToIntFunction<T> toIntFunctionFrom(Consumer<LToIntFunctionBuilder<T>> buildingFunction) {
		LToIntFunctionBuilder builder = new LToIntFunctionBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T> LToIntFunctionBuilder<T> toIntFunction(Consumer<LToIntFunction<T>> consumer) {
		return new LToIntFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LToIntFunctionBuilder<T> withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> LToIntFunctionBuilder<T> forValue(T v, LToIntFunction<V> function) {
		PartialCaseWithIntProduct.The pc = partialCaseFactoryMethod(a -> Objects.equals(a, v));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> PartialCaseWithIntProduct.The<LToIntFunctionBuilder<T>, LPredicate<T>, LToIntFunction<T>> forValue(T v) {
		return partialCaseFactoryMethod(a -> Objects.equals(a, v));
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LToIntFunctionBuilder<T> casesOf(Class<V> argC, Consumer<LToIntFunctionBuilder<V>> pcpConsumer) {
		PartialCaseWithIntProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
		pc.specifySubCases((Consumer) pcpConsumer);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LToIntFunctionBuilder<T> forClass(Class<V> argC, LToIntFunction<V> function) {
		PartialCaseWithIntProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> PartialCaseWithIntProduct.The<LToIntFunctionBuilder<T>, LPredicate<T>, LToIntFunction<T>> forClass(Class<V> argC) {
		return partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LToIntFunction<T> build() {

		final LToIntFunction<T> otherwiseFinal = this.otherwise;

		LToIntFunction<T> retval;

		final Case<LPredicate<T>, LToIntFunction<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LToIntFunction.<T>toIntFunc(a -> {
			try {
				for (Case<LPredicate<T>, LToIntFunction<T>> aCase : casesArray) {
					if (aCase.casePredicate().test(a)) {
						return aCase.caseFunction().applyAsInt(a);
					}
				}

				return otherwiseFinal.applyAsInt(a);
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

	public final LToIntFunction<T> build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
