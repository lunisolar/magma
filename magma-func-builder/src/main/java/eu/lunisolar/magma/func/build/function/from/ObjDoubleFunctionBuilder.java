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

/** Builder for ObjDoubleFunction. */
public final class ObjDoubleFunctionBuilder<T, R> extends PerCaseBuilderWithProduct<ObjDoubleFunctionBuilder<T, R>, ObjDoublePredicate<T>, ObjDoubleFunction<T, R>, R> {

	private Consumer<ObjDoubleFunction<T, R>> consumer;

	public static final ObjDoubleFunction EVENTUALLY_THROW = ObjDoubleFunction.l((Object t, double d) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t, d, ObjDoubleFunction.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public ObjDoubleFunctionBuilder(@Nullable Consumer<ObjDoubleFunction<T, R>> consumer) {
		super(EVENTUALLY_THROW, ObjDoubleFunction::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public ObjDoubleFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T, R> ObjDoubleFunctionBuilder<T, R> objDoubleFunction() {
		return new ObjDoubleFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T, R> ObjDoubleFunctionBuilder<T, R> objDoubleFunction(Consumer<ObjDoubleFunction<T, R>> consumer) {
		return new ObjDoubleFunctionBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final ObjDoubleFunction<T, R> build() {

		final ObjDoubleFunction<T, R> eventuallyFinal = this.eventually;

		ObjDoubleFunction<T, R> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<ObjDoublePredicate<T>, ObjDoubleFunction<T, R>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = ObjDoubleFunction.l((T t, double d) -> {
				for (Case<ObjDoublePredicate<T>, ObjDoubleFunction<T, R>> aCase : casesArray) {
					if (aCase.casePredicate().test(t, d)) {
						return aCase.caseFunction().apply(t, d);
					}
				}

				return eventuallyFinal.apply(t, d);
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}
