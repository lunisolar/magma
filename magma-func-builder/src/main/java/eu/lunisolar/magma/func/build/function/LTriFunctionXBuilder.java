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

package eu.lunisolar.magma.func.build.function;

import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.func.build.*;
import eu.lunisolar.magma.func.Function4U; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
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

/** Builder for LTriFunctionX. */
public final class LTriFunctionXBuilder<T1, T2, T3, R, X extends Throwable> extends PerCaseBuilderWithProduct.Base<LTriFunctionXBuilder<T1, T2, T3, R, X>, LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>, R> {

	private Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LTriFunctionX EVENTUALLY_THROW = LTriFunctionX.lX((Object t1, Object t2, Object t3) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, t3, LTriFunctionX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LTriFunctionXBuilder(@Nullable Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer) {
		super(EVENTUALLY_THROW, LTriFunctionX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LTriFunctionXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2, T3, R, X extends Throwable> LTriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX() {
		return new LTriFunctionXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2, T3, R, X extends Throwable> LTriFunctionXBuilder<T1, T2, T3, R, X> triFunctionX(Consumer<LTriFunctionX<T1, T2, T3, R, X>> consumer) {
		return new LTriFunctionXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LTriFunctionXBuilder<T1, T2, T3, R, X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LTriFunctionX<T1, T2, T3, R, X> build() {

		final LTriFunctionX<T1, T2, T3, R, X> eventuallyFinal = this.eventually;

		LTriFunctionX<T1, T2, T3, R, X> retval;

		final Case<LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LTriFunctionX.lX((T1 t1, T2 t2, T3 t3) -> {
			try {
				for (Case<LTriPredicateX<T1, T2, T3, X>, LTriFunctionX<T1, T2, T3, R, X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, t3)) {
						return aCase.caseFunction().doApply(t1, t2, t3);
					}
				}

				return eventuallyFinal.doApply(t1, t2, t3);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LTriFunctionX<T1, T2, T3, R, X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
