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

/** Builder for java.util.function.LongBinaryOperator. */
public final class StdLongBinaryOperatorBuilder extends PerCaseBuilderWithLongProduct<StdLongBinaryOperatorBuilder, BiLongPredicate, java.util.function.LongBinaryOperator> {

	private Consumer<java.util.function.LongBinaryOperator> consumer;

	public static final java.util.function.LongBinaryOperator EVENTUALLY_THROW = Function4U.longBinaryOperator((long l1, long l2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", l1, l2, "java.util.function.LongBinaryOperator: long applyAsLong(long l1,long l2)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public StdLongBinaryOperatorBuilder(@Nullable Consumer<java.util.function.LongBinaryOperator> consumer) {
		super(EVENTUALLY_THROW, LongBinaryOperator::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public StdLongBinaryOperatorBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final StdLongBinaryOperatorBuilder stdLongBinaryOperator() {
		return new StdLongBinaryOperatorBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final StdLongBinaryOperatorBuilder stdLongBinaryOperator(Consumer<java.util.function.LongBinaryOperator> consumer) {
		return new StdLongBinaryOperatorBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.LongBinaryOperator build() {

		final java.util.function.LongBinaryOperator eventuallyFinal = this.eventually;

		java.util.function.LongBinaryOperator retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiLongPredicate, java.util.function.LongBinaryOperator>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = Function4U.l((long l1, long l2) -> {
				for (Case<BiLongPredicate, java.util.function.LongBinaryOperator> aCase : casesArray) {
					if (aCase.casePredicate().test(l1, l2)) {
						return aCase.caseFunction().applyAsLong(l1, l2);
					}
				}

				return eventuallyFinal.applyAsLong(l1, l2);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
