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

package eu.lunisolar.magma.func.build.supplier;

import eu.lunisolar.magma.func.supplier.*;
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

/** Builder for LDoubleSupplierX. */
public final class LDoubleSupplierXBuilder<X extends Exception> extends PerCaseBuilderWithDoubleProduct.Base<LDoubleSupplierXBuilder<X>, LBooleanSupplierX<X>, LDoubleSupplierX<X>> {

	private Consumer<LDoubleSupplierX<X>> consumer;

	public static final LDoubleSupplierX EVENTUALLY_THROW = LDoubleSupplierX.lX(() -> {
		String message;
		try {
			message = String.format("No case specified for: no arg as function %s.", LDoubleSupplierX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new UnsupportedOperationException(message);
		});

	public LDoubleSupplierXBuilder(@Nullable Consumer<LDoubleSupplierX<X>> consumer) {
		super(EVENTUALLY_THROW, LDoubleSupplierX::of);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LDoubleSupplierXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <X extends Exception> LDoubleSupplierXBuilder<X> doubleSupplierX() {
		return new LDoubleSupplierXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <X extends Exception> LDoubleSupplierXBuilder<X> doubleSupplierX(Consumer<LDoubleSupplierX<X>> consumer) {
		return new LDoubleSupplierXBuilder(consumer);
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LDoubleSupplierX<X> build() {

		final LDoubleSupplierX<X> eventuallyFinal = this.eventually;

		LDoubleSupplierX<X> retval;

		if (cases.isEmpty()) {
			retval = eventuallyFinal;
		} else {
			final Case<LBooleanSupplierX<X>, LDoubleSupplierX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
			retval = LDoubleSupplierX.lX(() -> {
				for (Case<LBooleanSupplierX<X>, LDoubleSupplierX<X>> aCase : casesArray) {
					if (aCase.casePredicate().getAsBoolean()) {
						return aCase.caseFunction().getAsDouble();
					}
				}

				return eventuallyFinal.getAsDouble();
			});
		}

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

}