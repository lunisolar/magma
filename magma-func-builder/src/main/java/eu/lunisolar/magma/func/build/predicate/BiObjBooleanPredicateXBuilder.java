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

/** Builder for BiObjBooleanPredicateX. */
public final class BiObjBooleanPredicateXBuilder<T1, T2, X extends Exception> extends PerCaseBuilder<BiObjBooleanPredicateXBuilder<T1, T2, X>, BiObjBooleanPredicateX<T1, T2, X>, BiObjBooleanPredicateX<T1, T2, X>> {

	private Consumer<BiObjBooleanPredicateX<T1, T2, X>> consumer;

	public static final BiObjBooleanPredicateX EVENTUALLY_THROW = BiObjBooleanPredicateX.lX((Object t1, Object t2, boolean b) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, b, BiObjBooleanPredicateX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BiObjBooleanPredicateXBuilder(@Nullable Consumer<BiObjBooleanPredicateX<T1, T2, X>> consumer) {
		super(EVENTUALLY_THROW);
		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BiObjBooleanPredicateXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, X extends Exception> BiObjBooleanPredicateXBuilder<T1, T2, X> biObjBooleanPredicateX() {
		return new BiObjBooleanPredicateXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, X extends Exception> BiObjBooleanPredicateXBuilder<T1, T2, X> biObjBooleanPredicateX(Consumer<BiObjBooleanPredicateX<T1, T2, X>> consumer) {
		return new BiObjBooleanPredicateXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BiObjBooleanPredicateX<T1, T2, X> build() {

		final BiObjBooleanPredicateX<T1, T2, X> eventuallyFinal = this.eventually;

		BiObjBooleanPredicateX<T1, T2, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiObjBooleanPredicateX<T1, T2, X>, BiObjBooleanPredicateX<T1, T2, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BiObjBooleanPredicateX.lX((T1 t1, T2 t2, boolean b) -> {
				for (Case<BiObjBooleanPredicateX<T1, T2, X>, BiObjBooleanPredicateX<T1, T2, X>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2, b)) {
						return aCase.caseFunction().test(t1, t2, b);
					}
				}

				return eventuallyFinal.test(t1, t2, b);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
