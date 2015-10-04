/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.std;

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
import java.util.function.Consumer;
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/** Builder for java.util.function.ToLongBiFunction. */
public final class ToLongBiFunctionBuilder<T1, T2> extends PerCaseBuilderWithLongProduct.Base<ToLongBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, java.util.function.ToLongBiFunction<T1, T2>> {

	private Consumer<java.util.function.ToLongBiFunction<T1, T2>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final java.util.function.ToLongBiFunction EVENTUALLY_THROW = Function4U.toLongBiFunction((Object a1, Object a2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", a1, a2, "java.util.function.ToLongBiFunction: long applyAsLong(T1 a1,T2 a2)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public ToLongBiFunctionBuilder(@Nullable Consumer<java.util.function.ToLongBiFunction<T1, T2>> consumer) {
		super(EVENTUALLY_THROW, LToLongBiFunction::constant, () -> new ToLongBiFunctionBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public ToLongBiFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static <T1, T2> ToLongBiFunctionBuilder<T1, T2> toLongBiFunction() {
		return new ToLongBiFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static <T1, T2> ToLongBiFunctionBuilder<T1, T2> toLongBiFunction(Consumer<java.util.function.ToLongBiFunction<T1, T2>> consumer) {
		return new ToLongBiFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final ToLongBiFunctionBuilder<T1, T2> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Allows to specify additional cases for a specific type of generic arguments (matched by instanceOf). Null classes can be provided in case of arguments that do not matter. */
	@Nonnull
	public <E1 extends T1, E2 extends T2> ToLongBiFunctionBuilder<T1, T2> casesOf(Class<E1> argC1, Class<E2> argC2, Consumer<ToLongBiFunctionBuilder<E1, E2>> pcpConsumer) {
		PartialCaseWithLongProduct.The pc = partialCaseFactoryMethod((T1 a1, T2 a2) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)));

		pc.specifySubCases((Consumer) pcpConsumer);
		return self();
	}

	/** Adds full new case for the argument that are of specific classes (matched by instanceOf, null is a wildcard). */
	@Nonnull
	public <E1 extends T1, E2 extends T2> ToLongBiFunctionBuilder<T1, T2> aCase(Class<E1> argC1, Class<E2> argC2, java.util.function.ToLongBiFunction<E1, E2> function) {
		PartialCaseWithLongProduct.The pc = partialCaseFactoryMethod((T1 a1, T2 a2) -> (argC1 == null || argC1.isInstance(a1)) && (argC2 == null || argC2.isInstance(a2)));

		pc.evaluate(function);
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.ToLongBiFunction<T1, T2> build() {

		final java.util.function.ToLongBiFunction<T1, T2> eventuallyFinal = this.eventually;

		java.util.function.ToLongBiFunction<T1, T2> retval;

		final Case<LBiPredicate<T1, T2>, java.util.function.ToLongBiFunction<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = Function4U.<T1, T2> toLongBiFunction((T1 a1, T2 a2) -> {
			try {
				for (Case<LBiPredicate<T1, T2>, java.util.function.ToLongBiFunction<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(a1, a2)) {
						return aCase.caseFunction().applyAsLong(a1, a2);
					}
				}

				return eventuallyFinal.applyAsLong(a1, a2);
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

	public final java.util.function.ToLongBiFunction<T1, T2> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
