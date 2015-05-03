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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.build.predicate;

import eu.lunisolar.magma.func.predicate.*;
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

/** Builder for BiIntPredicateX. */
public final class BiIntPredicateXBuilder<X extends Exception> extends PerCaseBuilderWithBooleanProduct<BiIntPredicateXBuilder<X>, BiIntPredicateX<X>, BiIntPredicateX<X>> {

	private Consumer<BiIntPredicateX<X>> consumer;

	public static final BiIntPredicateX EVENTUALLY_THROW = BiIntPredicateX.lX((int i1, int i2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", i1, i2, BiIntPredicateX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BiIntPredicateXBuilder(@Nullable Consumer<BiIntPredicateX<X>> consumer) {
		super(EVENTUALLY_THROW, BiIntPredicateX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BiIntPredicateXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <X extends Exception> BiIntPredicateXBuilder<X> biIntPredicateX() {
		return new BiIntPredicateXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <X extends Exception> BiIntPredicateXBuilder<X> biIntPredicateX(Consumer<BiIntPredicateX<X>> consumer) {
		return new BiIntPredicateXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BiIntPredicateX<X> build() {

		final BiIntPredicateX<X> eventuallyFinal = this.eventually;

		BiIntPredicateX<X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiIntPredicateX<X>, BiIntPredicateX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BiIntPredicateX.lX((int i1, int i2) -> {
				for (Case<BiIntPredicateX<X>, BiIntPredicateX<X>> aCase : casesArray) {
					if (aCase.casePredicate().test(i1, i2)) {
						return aCase.caseFunction().test(i1, i2);
					}
				}

				return eventuallyFinal.test(i1, i2);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
