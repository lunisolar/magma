/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.build.consumer.primitives.tri;

import eu.lunisolar.magma.func.consumer.primitives.tri.*;
import eu.lunisolar.magma.func.build.*;
import eu.lunisolar.magma.func.Function4U; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
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

/** Builder for BooleanTriConsumer. */
public final class BooleanTriConsumerBuilder extends PerCaseBuilder.Base<BooleanTriConsumerBuilder, BooleanTernaryOperator, BooleanTriConsumer> {

	private Consumer<BooleanTriConsumer> consumer;

	public static final BooleanTriConsumer EVENTUALLY_THROW = BooleanTriConsumer.l((boolean b1, boolean b2, boolean b3) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", b1, b2, b3, BooleanTriConsumer.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BooleanTriConsumerBuilder(@Nullable Consumer<BooleanTriConsumer> consumer) {
		super(EVENTUALLY_THROW);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BooleanTriConsumerBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final BooleanTriConsumerBuilder booleanTriConsumer() {
		return new BooleanTriConsumerBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final BooleanTriConsumerBuilder booleanTriConsumer(Consumer<BooleanTriConsumer> consumer) {
		return new BooleanTriConsumerBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BooleanTriConsumer build() {

		final BooleanTriConsumer eventuallyFinal = this.eventually;

		BooleanTriConsumer retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BooleanTernaryOperator, BooleanTriConsumer>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BooleanTriConsumer.l((boolean b1, boolean b2, boolean b3) -> {
				for (Case<BooleanTernaryOperator, BooleanTriConsumer> aCase : casesArray) {
					if (aCase.casePredicate().apply(b1, b2, b3)) {
						aCase.caseFunction().accept(b1, b2, b3);
						return;
					}
				}

				eventuallyFinal.accept(b1, b2, b3);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
