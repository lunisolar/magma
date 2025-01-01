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
 * Builder for LToByteFunction.
 */
public final class LToByteFunctionBuilder<T> extends PerCaseBuilderWithByteProduct.Base<LToByteFunctionBuilder<T>, LPredicate<T>, LToByteFunction<T>> {
	// extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LToByteFunction<T>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LToByteFunction OTHERWISE_THROW = LToByteFunction.toByteFunc(a -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LToByteFunctionBuilder(@Nullable Consumer<LToByteFunction<T>> consumer) {
		super(OTHERWISE_THROW, LToByteFunction::constant, () -> new LToByteFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LToByteFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T> LToByteFunctionBuilder<T> toByteFunction() {
		return new LToByteFunctionBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static <T> LToByteFunction<T> toByteFunctionFrom(Consumer<LToByteFunctionBuilder<T>> buildingFunction) {
		LToByteFunctionBuilder builder = new LToByteFunctionBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T> LToByteFunctionBuilder<T> toByteFunction(Consumer<LToByteFunction<T>> consumer) {
		return new LToByteFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LToByteFunctionBuilder<T> withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> LToByteFunctionBuilder<T> forValue(T v, LToByteFunction<V> function) {
		PartialCaseWithByteProduct.The pc = partialCaseFactoryMethod(a -> Objects.equals(a, v));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public <V extends T> PartialCaseWithByteProduct.The<LToByteFunctionBuilder<T>, LPredicate<T>, LToByteFunction<T>> forValue(T v) {
		return partialCaseFactoryMethod(a -> Objects.equals(a, v));
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <V extends T> LToByteFunctionBuilder<T> casesOf(Class<V> argC, Consumer<LToByteFunctionBuilder<V>> pcpConsumer) {
		PartialCaseWithByteProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
		pc.specifySubCases((Consumer) pcpConsumer);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> LToByteFunctionBuilder<T> forClass(Class<V> argC, LToByteFunction<V> function) {
		PartialCaseWithByteProduct.The pc = partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <V extends T> PartialCaseWithByteProduct.The<LToByteFunctionBuilder<T>, LPredicate<T>, LToByteFunction<T>> forClass(Class<V> argC) {
		return partialCaseFactoryMethod(a -> (argC == null || argC.isInstance(a)));
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LToByteFunction<T> build() {

		final LToByteFunction<T> otherwiseFinal = this.otherwise;

		LToByteFunction<T> retval;

		final Case<LPredicate<T>, LToByteFunction<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LToByteFunction.<T> toByteFunc(a -> {
			try {
				for (Case<LPredicate<T>, LToByteFunction<T>> aCase : casesArray) {
					if (aCase.casePredicate().test(a)) {
						return aCase.caseFunction().applyAsByte(a);
					}
				}

				return otherwiseFinal.applyAsByte(a);
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

	public final LToByteFunction<T> build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
