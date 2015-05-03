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

/** Builder for ObjCharFunctionX. */
public final class ObjCharFunctionXBuilder<T, R, X extends Exception> extends PerCaseBuilderWithProduct<ObjCharFunctionXBuilder<T, R, X>, ObjCharPredicateX<T, X>, ObjCharFunctionX<T, R, X>, R> {

	private Consumer<ObjCharFunctionX<T, R, X>> consumer;

	public static final ObjCharFunctionX EVENTUALLY_THROW = ObjCharFunctionX.lX((Object t, char c) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, c, ObjCharFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public ObjCharFunctionXBuilder(@Nullable Consumer<ObjCharFunctionX<T, R, X>> consumer) {
		super(EVENTUALLY_THROW, ObjCharFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public ObjCharFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T, R, X extends Exception> ObjCharFunctionXBuilder<T, R, X> objCharFunctionX() {
		return new ObjCharFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T, R, X extends Exception> ObjCharFunctionXBuilder<T, R, X> objCharFunctionX(Consumer<ObjCharFunctionX<T, R, X>> consumer) {
		return new ObjCharFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final ObjCharFunctionX<T, R, X> build() {

		final ObjCharFunctionX<T, R, X> eventuallyFinal = this.eventually;

		ObjCharFunctionX<T, R, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<ObjCharPredicateX<T, X>, ObjCharFunctionX<T, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = ObjCharFunctionX.lX((T t, char c) -> {
				for (Case<ObjCharPredicateX<T, X>, ObjCharFunctionX<T, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().test(t, c)) {
						return aCase.caseFunction().apply(t, c);
					}
				}

				return eventuallyFinal.apply(t, c);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
