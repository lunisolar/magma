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

package eu.lunisolar.magma.func.build.function;

import eu.lunisolar.magma.func.function.*;
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

/** Builder for TriFunctionX. */
public final class TriFunctionXBuilder<T1, T2, T3, R, X extends Exception> extends PerCaseBuilderWithProduct<TriFunctionXBuilder<T1, T2, T3, R, X>, TriPredicateX<T1, T2, T3, X>, TriFunctionX<T1, T2, T3, R, X>, R> {

	private Consumer<TriFunctionX<T1, T2, T3, R, X>> consumer;

	public static final TriFunctionX EVENTUALLY_THROW = TriFunctionX.lX((Object t1, Object t2, Object t3) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, t3, TriFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public TriFunctionXBuilder(@Nullable Consumer<TriFunctionX<T1, T2, T3, R, X>> consumer) {
		super(EVENTUALLY_THROW, TriFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public TriFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, T3, R, X extends Exception> TriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX() {
		return new TriFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, T3, R, X extends Exception> TriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX(Consumer<TriFunctionX<T1, T2, T3, R, X>> consumer) {
		return new TriFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final TriFunctionX<T1, T2, T3, R, X> build() {

		final TriFunctionX<T1, T2, T3, R, X> eventuallyFinal = this.eventually;

		TriFunctionX<T1, T2, T3, R, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<TriPredicateX<T1, T2, T3, X>, TriFunctionX<T1, T2, T3, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = TriFunctionX.lX((T1 t1, T2 t2, T3 t3) -> {
				for (Case<TriPredicateX<T1, T2, T3, X>, TriFunctionX<T1, T2, T3, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2, t3)) {
						return aCase.caseFunction().apply(t1, t2, t3);
					}
				}

				return eventuallyFinal.apply(t1, t2, t3);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
