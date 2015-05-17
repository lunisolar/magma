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

package eu.lunisolar.magma.func.build.std;

import eu.lunisolar.magma.func.build.*;
import eu.lunisolar.magma.func.Function4U; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
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

	public static final java.util.function.DoubleUnaryOperator EVENTUALLY_THROW = Function4U.doubleUnaryOperator((double d) -> {
		String message;
		try {
			message = String.format("No case specified for: %s  as function %s.", d, "java.util.function.DoubleUnaryOperator: double applyAsDouble(double d)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
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

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.DoubleUnaryOperator build() {

		final java.util.function.DoubleUnaryOperator eventuallyFinal = this.eventually;

		java.util.function.DoubleUnaryOperator retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<LDoublePredicate, java.util.function.DoubleUnaryOperator>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = Function4U.l((double d) -> {
				for (Case<LDoublePredicate, java.util.function.DoubleUnaryOperator> aCase : casesArray) {
					if (aCase.casePredicate().doTest(d)) {
						return aCase.caseFunction().applyAsDouble(d);
					}
				}

				return eventuallyFinal.applyAsDouble(d);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
