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

package eu.lunisolar.magma.func.build.operator.unary;

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
 * Builder for LLogicalOperator.
 */
public final class LLogicalOperatorBuilder extends PerCaseBuilderWithBoolProduct.Base<LLogicalOperatorBuilder, LLogicalOperator, LLogicalOperator> {
	//extends PER_CASE_BUILDER<BUILDER_NAME func.B(the_case.class_args_ref), CASE_PREDICATE func.B(the_case.domain_class_argsX_ref), the_case.name_ref RRR> {

	private Consumer<LLogicalOperator> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LLogicalOperator OTHERWISE_THROW = LLogicalOperator.logicalOp(a -> {
		throw new IllegalStateException("There is no case configured for the arguments (if any).");
	});

	public LLogicalOperatorBuilder(@Nullable Consumer<LLogicalOperator> consumer) {
		super(OTHERWISE_THROW, LLogicalOperator::constant, () -> new LLogicalOperatorBuilder(null));

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LLogicalOperatorBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static LLogicalOperatorBuilder logicalOperator() {
		return new LLogicalOperatorBuilder();
	}

	/** One of ways of creating builder. This is possibly the least verbose way where compiler should be able to guess the generic parameters. */
	@Nonnull
	public static LLogicalOperator logicalOperatorFrom(Consumer<LLogicalOperatorBuilder> buildingFunction) {
		LLogicalOperatorBuilder builder = new LLogicalOperatorBuilder();
		buildingFunction.accept(builder);
		return builder.build();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static LLogicalOperatorBuilder logicalOperator(Consumer<LLogicalOperator> consumer) {
		return new LLogicalOperatorBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LLogicalOperatorBuilder withHandling(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public LLogicalOperatorBuilder forValue(boolean v, LLogicalOperator function) {
		PartialCaseWithBoolProduct.The pc = partialCaseFactoryMethod(a -> a == v);
		pc.evaluate(function);
		return fluentCtx();
	}

	/** Allows to specify additional cases for a specific values of arguments (matched by equals).*/
	public PartialCaseWithBoolProduct.The<LLogicalOperatorBuilder, LLogicalOperator, LLogicalOperator> forValue(boolean v) {
		return partialCaseFactoryMethod(a -> a == v);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LLogicalOperator build() {

		final LLogicalOperator otherwiseFinal = this.otherwise;

		LLogicalOperator retval;

		final Case<LLogicalOperator, LLogicalOperator>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LLogicalOperator.logicalOp(a -> {
			try {
				for (Case<LLogicalOperator, LLogicalOperator> aCase : casesArray) {
					if (aCase.casePredicate().apply(a)) {
						return aCase.caseFunction().apply(a);
					}
				}

				return otherwiseFinal.apply(a);
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

	public final LLogicalOperator build(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
