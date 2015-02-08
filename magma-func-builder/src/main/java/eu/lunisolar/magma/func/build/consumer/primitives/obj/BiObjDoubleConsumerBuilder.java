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

package eu.lunisolar.magma.func.build.consumer.primitives.obj;

import eu.lunisolar.magma.func.consumer.primitives.obj.*;
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

public final class BiObjDoubleConsumerBuilder<T1, T2> extends PerCaseBuilder<BiObjDoubleConsumerBuilder<T1, T2>, BiObjDoublePredicate<T1, T2>, BiObjDoubleConsumer<T1, T2>> {

	private Consumer<BiObjDoubleConsumer<T1, T2>> consumer;

	public static final BiObjDoubleConsumer EVENTUALLY_THROW = BiObjDoubleConsumer.l((Object t1, Object t2, double d) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, d, BiObjDoubleConsumer.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public BiObjDoubleConsumerBuilder(@Nullable Consumer<BiObjDoubleConsumer<T1, T2>> consumer) {
		super(EVENTUALLY_THROW);
		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public BiObjDoubleConsumerBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2> BiObjDoubleConsumerBuilder<T1, T2> biObjDoubleConsumer() {
		return new BiObjDoubleConsumerBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2> BiObjDoubleConsumerBuilder<T1, T2> biObjDoubleConsumer(Consumer<BiObjDoubleConsumer<T1, T2>> consumer) {
		return new BiObjDoubleConsumerBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final BiObjDoubleConsumer<T1, T2> build() {

		final BiObjDoubleConsumer<T1, T2> eventuallyFinal = this.eventually;

		BiObjDoubleConsumer<T1, T2> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<BiObjDoublePredicate<T1, T2>, BiObjDoubleConsumer<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = BiObjDoubleConsumer.l((T1 t1, T2 t2, double d) -> {
				for (Case<BiObjDoublePredicate<T1, T2>, BiObjDoubleConsumer<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().test(t1, t2, d)) {
						aCase.caseFunction().accept(t1, t2, d);
					}
				}

				eventuallyFinal.accept(t1, t2, d);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
