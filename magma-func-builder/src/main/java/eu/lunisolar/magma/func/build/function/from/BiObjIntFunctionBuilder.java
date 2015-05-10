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

/** Builder for BiObjIntFunction. */
public final class BiObjIntFunctionBuilder<T1, T2, R> extends PerCaseBuilderWithProduct.Base<BiObjIntFunctionBuilder<T1, T2, R>, BiObjIntPredicate<T1, T2>, BiObjIntFunction<T1, T2, R>, R> {

	private Consumer<BiObjIntFunction<T1, T2, R>> consumer;

	public static final BiObjIntFunction EVENTUALLY_THROW = BiObjIntFunction.l((Object t1, Object t2, int i) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, i, BiObjIntFunction.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BiObjIntFunctionBuilder(@Nullable Consumer<BiObjIntFunction<T1, T2, R>> consumer) {
		super(EVENTUALLY_THROW, BiObjIntFunction::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BiObjIntFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, R> BiObjIntFunctionBuilder<T1, T2, R> biObjIntFunction() {
		return new BiObjIntFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, R> BiObjIntFunctionBuilder<T1, T2, R> biObjIntFunction(Consumer<BiObjIntFunction<T1, T2, R>> consumer) {
		return new BiObjIntFunctionBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BiObjIntFunction<T1, T2, R> build() {

		final BiObjIntFunction<T1, T2, R> eventuallyFinal = this.eventually;

		BiObjIntFunction<T1, T2, R> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiObjIntPredicate<T1, T2>, BiObjIntFunction<T1, T2, R>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BiObjIntFunction.l((T1 t1, T2 t2, int i) -> {
				for (Case<BiObjIntPredicate<T1, T2>, BiObjIntFunction<T1, T2, R>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2, i)) {
						return aCase.caseFunction().apply(t1, t2, i);
					}
				}

				return eventuallyFinal.apply(t1, t2, i);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
