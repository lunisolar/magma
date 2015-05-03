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

/** Builder for BooleanTriFunctionX. */
public final class BooleanTriFunctionXBuilder<R, X extends Exception> extends PerCaseBuilderWithProduct<BooleanTriFunctionXBuilder<R, X>, BooleanTernaryOperatorX<X>, BooleanTriFunctionX<R, X>, R> {

	private Consumer<BooleanTriFunctionX<R, X>> consumer;

	public static final BooleanTriFunctionX EVENTUALLY_THROW = BooleanTriFunctionX.lX((boolean b1, boolean b2, boolean b3) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", b1, b2, b3, BooleanTriFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BooleanTriFunctionXBuilder(@Nullable Consumer<BooleanTriFunctionX<R, X>> consumer) {
		super(EVENTUALLY_THROW, BooleanTriFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BooleanTriFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <R, X extends Exception> BooleanTriFunctionXBuilder<R, X> booleanTriFunctionX() {
		return new BooleanTriFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <R, X extends Exception> BooleanTriFunctionXBuilder<R, X> booleanTriFunctionX(Consumer<BooleanTriFunctionX<R, X>> consumer) {
		return new BooleanTriFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BooleanTriFunctionX<R, X> build() {

		final BooleanTriFunctionX<R, X> eventuallyFinal = this.eventually;

		BooleanTriFunctionX<R, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BooleanTernaryOperatorX<X>, BooleanTriFunctionX<R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BooleanTriFunctionX.lX((boolean b1, boolean b2, boolean b3) -> {
				for (Case<BooleanTernaryOperatorX<X>, BooleanTriFunctionX<R, X>> aCase : casesArray) {
					if (aCase.casePredicate().apply(b1, b2, b3)) {
						return aCase.caseFunction().apply(b1, b2, b3);
					}
				}

				return eventuallyFinal.apply(b1, b2, b3);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
