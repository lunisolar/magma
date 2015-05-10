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

package eu.lunisolar.magma.func.build.function.conversion;

import eu.lunisolar.magma.func.function.conversion.*;
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

/** Builder for CharToDoubleFunctionX. */
public final class CharToDoubleFunctionXBuilder<X extends Exception> extends PerCaseBuilderWithDoubleProduct.Base<CharToDoubleFunctionXBuilder<X>, CharPredicateX<X>, CharToDoubleFunctionX<X>> {

	private Consumer<CharToDoubleFunctionX<X>> consumer;

	public static final CharToDoubleFunctionX EVENTUALLY_THROW = CharToDoubleFunctionX.lX((char c) -> {
		String message;
		try {
			message = String.format("No case specified for: %s  as function %s.", c, CharToDoubleFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public CharToDoubleFunctionXBuilder(@Nullable Consumer<CharToDoubleFunctionX<X>> consumer) {
		super(EVENTUALLY_THROW, CharToDoubleFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public CharToDoubleFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <X extends Exception> CharToDoubleFunctionXBuilder<X> charToDoubleFunctionX() {
		return new CharToDoubleFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <X extends Exception> CharToDoubleFunctionXBuilder<X> charToDoubleFunctionX(Consumer<CharToDoubleFunctionX<X>> consumer) {
		return new CharToDoubleFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final CharToDoubleFunctionX<X> build() {

		final CharToDoubleFunctionX<X> eventuallyFinal = this.eventually;

		CharToDoubleFunctionX<X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<CharPredicateX<X>, CharToDoubleFunctionX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = CharToDoubleFunctionX.lX((char c) -> {
				for (Case<CharPredicateX<X>, CharToDoubleFunctionX<X>> aCase : casesArray) {
					if (aCase.casePredicate().test(c)) {
						return aCase.caseFunction().applyAsDouble(c);
					}
				}

				return eventuallyFinal.applyAsDouble(c);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
