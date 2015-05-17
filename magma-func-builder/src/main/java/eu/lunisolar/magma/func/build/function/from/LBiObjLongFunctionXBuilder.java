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

/** Builder for LBiObjLongFunctionX. */
public final class LBiObjLongFunctionXBuilder<T1, T2, R, X extends Exception> extends PerCaseBuilderWithProduct.Base<LBiObjLongFunctionXBuilder<T1, T2, R, X>, LBiObjLongPredicateX<T1, T2, X>, LBiObjLongFunctionX<T1, T2, R, X>, R> {

	private Consumer<LBiObjLongFunctionX<T1, T2, R, X>> consumer;

	public static final LBiObjLongFunctionX EVENTUALLY_THROW = LBiObjLongFunctionX.lX((Object t1, Object t2, long l) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, l, LBiObjLongFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public LBiObjLongFunctionXBuilder(@Nullable Consumer<LBiObjLongFunctionX<T1, T2, R, X>> consumer) {
		super(EVENTUALLY_THROW, LBiObjLongFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiObjLongFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, R, X extends Exception> LBiObjLongFunctionXBuilder<T1, T2, R, X> biObjLongFunctionX() {
		return new LBiObjLongFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, R, X extends Exception> LBiObjLongFunctionXBuilder<T1, T2, R, X> biObjLongFunctionX(Consumer<LBiObjLongFunctionX<T1, T2, R, X>> consumer) {
		return new LBiObjLongFunctionXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiObjLongFunctionX<T1, T2, R, X> build() {

		final LBiObjLongFunctionX<T1, T2, R, X> eventuallyFinal = this.eventually;

		LBiObjLongFunctionX<T1, T2, R, X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<LBiObjLongPredicateX<T1, T2, X>, LBiObjLongFunctionX<T1, T2, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = LBiObjLongFunctionX.lX((T1 t1, T2 t2, long l) -> {
				for (Case<LBiObjLongPredicateX<T1, T2, X>, LBiObjLongFunctionX<T1, T2, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2, l)) {
						return aCase.caseFunction().apply(t1, t2, l);
					}
				}

				return eventuallyFinal.apply(t1, t2, l);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}