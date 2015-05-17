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

package eu.lunisolar.magma.func.build.function.to;

import eu.lunisolar.magma.func.function.to.*;
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

/** Builder for LToShortBiFunction. */
public final class LToShortBiFunctionBuilder<T1, T2> extends PerCaseBuilderWithShortProduct.Base<LToShortBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, LToShortBiFunction<T1, T2>> {

	private Consumer<LToShortBiFunction<T1, T2>> consumer;

	public static final LToShortBiFunction EVENTUALLY_THROW = LToShortBiFunction.l((Object t1, Object t2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t1, t2, LToShortBiFunction.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public LToShortBiFunctionBuilder(@Nullable Consumer<LToShortBiFunction<T1, T2>> consumer) {
		super(EVENTUALLY_THROW, LToShortBiFunction::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LToShortBiFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2> LToShortBiFunctionBuilder<T1, T2> toShortBiFunction() {
		return new LToShortBiFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2> LToShortBiFunctionBuilder<T1, T2> toShortBiFunction(Consumer<LToShortBiFunction<T1, T2>> consumer) {
		return new LToShortBiFunctionBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LToShortBiFunction<T1, T2> build() {

		final LToShortBiFunction<T1, T2> eventuallyFinal = this.eventually;

		LToShortBiFunction<T1, T2> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<LBiPredicate<T1, T2>, LToShortBiFunction<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = LToShortBiFunction.l((T1 t1, T2 t2) -> {
				for (Case<LBiPredicate<T1, T2>, LToShortBiFunction<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2)) {
						return aCase.caseFunction().applyAsShort(t1, t2);
					}
				}

				return eventuallyFinal.applyAsShort(t1, t2);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}