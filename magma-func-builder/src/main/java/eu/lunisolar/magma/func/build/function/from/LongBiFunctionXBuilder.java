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

package eu.lunisolar.magma.func.build.function.from;

import eu.lunisolar.magma.func.function.from.*;
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

/** Builder for LongBiFunctionX. */
public final class LongBiFunctionXBuilder<R, X extends Exception> extends PerCaseBuilderWithProduct.Base<LongBiFunctionXBuilder<R, X>, BiLongPredicateX<X>, LongBiFunctionX<R, X>, R> {

	private Consumer<LongBiFunctionX<R, X>> consumer;

	public static final LongBiFunctionX EVENTUALLY_THROW = LongBiFunctionX.lX((long l1, long l2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", l1, l2, LongBiFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public LongBiFunctionXBuilder(@Nullable Consumer<LongBiFunctionX<R, X>> consumer) {
		super(EVENTUALLY_THROW, LongBiFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LongBiFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <R, X extends Exception> LongBiFunctionXBuilder<R, X> longBiFunctionX() {
		return new LongBiFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <R, X extends Exception> LongBiFunctionXBuilder<R, X> longBiFunctionX(Consumer<LongBiFunctionX<R, X>> consumer) {
		return new LongBiFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LongBiFunctionX<R, X> build() {

		final LongBiFunctionX<R, X> eventuallyFinal = this.eventually;

		LongBiFunctionX<R, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiLongPredicateX<X>, LongBiFunctionX<R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = LongBiFunctionX.lX((long l1, long l2) -> {
				for (Case<BiLongPredicateX<X>, LongBiFunctionX<R, X>> aCase : casesArray) {
					if (aCase.casePredicate().test(l1, l2)) {
						return aCase.caseFunction().apply(l1, l2);
					}
				}

				return eventuallyFinal.apply(l1, l2);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
