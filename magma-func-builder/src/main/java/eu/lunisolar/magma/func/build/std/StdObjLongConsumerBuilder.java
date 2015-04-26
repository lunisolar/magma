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

package eu.lunisolar.magma.func.build.std;

import eu.lunisolar.magma.func.std.*;
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

/** Builder for java.util.function.ObjLongConsumer. */
public final class StdObjLongConsumerBuilder<T> extends PerCaseBuilder<StdObjLongConsumerBuilder<T>, ObjLongPredicate<T>, java.util.function.ObjLongConsumer<T>> {

	private Consumer<java.util.function.ObjLongConsumer<T>> consumer;

	public static final java.util.function.ObjLongConsumer EVENTUALLY_THROW = Function4U.objLongConsumer((Object t, long l) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, l, "java.util.function.ObjLongConsumer: void accept(T t, long l)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public StdObjLongConsumerBuilder(@Nullable Consumer<java.util.function.ObjLongConsumer<T>> consumer) {
		super(EVENTUALLY_THROW);
		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public StdObjLongConsumerBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T> StdObjLongConsumerBuilder<T> stdObjLongConsumer() {
		return new StdObjLongConsumerBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T> StdObjLongConsumerBuilder<T> stdObjLongConsumer(Consumer<java.util.function.ObjLongConsumer<T>> consumer) {
		return new StdObjLongConsumerBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.ObjLongConsumer<T> build() {

		final java.util.function.ObjLongConsumer<T> eventuallyFinal = this.eventually;

		java.util.function.ObjLongConsumer<T> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<ObjLongPredicate<T>, java.util.function.ObjLongConsumer<T>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = Function4U.l((T t, long l) -> {
				for (Case<ObjLongPredicate<T>, java.util.function.ObjLongConsumer<T>> aCase : casesArray) {
					if (aCase.casePredicate().test(t, l)) {
						aCase.caseFunction().accept(t, l);
						return;
					}
				}

				eventuallyFinal.accept(t, l);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}