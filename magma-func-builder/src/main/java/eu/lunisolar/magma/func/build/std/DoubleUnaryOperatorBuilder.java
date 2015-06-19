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
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/** Builder for java.util.function.DoubleUnaryOperator. */
public final class DoubleUnaryOperatorBuilder extends PerCaseBuilderWithDoubleProduct.Base<DoubleUnaryOperatorBuilder, LDoublePredicate, java.util.function.DoubleUnaryOperator> {

	private Consumer<java.util.function.DoubleUnaryOperator> consumer;

	private @Nullable HandlingInstructions handling;

	public static final java.util.function.DoubleUnaryOperator EVENTUALLY_THROW = Function4U.doubleUnaryOperator((double d) -> {
		String message;
		try {
			message = String.format("No case specified for: %s  as function %s.", d, "java.util.function.DoubleUnaryOperator: double applyAsDouble(double d)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public DoubleUnaryOperatorBuilder(@Nullable Consumer<java.util.function.DoubleUnaryOperator> consumer) {
		super(EVENTUALLY_THROW, LDoubleUnaryOperator::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public DoubleUnaryOperatorBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final DoubleUnaryOperatorBuilder doubleUnaryOperator() {
		return new DoubleUnaryOperatorBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final DoubleUnaryOperatorBuilder doubleUnaryOperator(Consumer<java.util.function.DoubleUnaryOperator> consumer) {
		return new DoubleUnaryOperatorBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final DoubleUnaryOperatorBuilder withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.DoubleUnaryOperator build() {

		final java.util.function.DoubleUnaryOperator eventuallyFinal = this.eventually;

		java.util.function.DoubleUnaryOperator retval;

		final Case<LDoublePredicate, java.util.function.DoubleUnaryOperator>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = Function4U.l((double d) -> {
			try {
				for (Case<LDoublePredicate, java.util.function.DoubleUnaryOperator> aCase : casesArray) {
					if (aCase.casePredicate().doTest(d)) {
						return aCase.caseFunction().applyAsDouble(d);
					}
				}

				return eventuallyFinal.applyAsDouble(d);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final java.util.function.DoubleUnaryOperator build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
