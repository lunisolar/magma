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

package eu.lunisolar.magma.func.build.std;

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

/** Builder for java.util.function.ToDoubleBiFunction. */
public final class ToDoubleBiFunctionBuilder<T1, T2> extends PerCaseBuilderWithDoubleProduct.Base<ToDoubleBiFunctionBuilder<T1, T2>, LBiPredicate<T1, T2>, java.util.function.ToDoubleBiFunction<T1, T2>> {

	private Consumer<java.util.function.ToDoubleBiFunction<T1, T2>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final java.util.function.ToDoubleBiFunction EVENTUALLY_THROW = Function4U.toDoubleBiFunction((Object t1, Object t2) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s  as function %s.", t1, t2, "java.util.function.ToDoubleBiFunction: double applyAsDouble(T1 t1,T2 t2)");
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public ToDoubleBiFunctionBuilder(@Nullable Consumer<java.util.function.ToDoubleBiFunction<T1, T2>> consumer) {
		super(EVENTUALLY_THROW, LToDoubleBiFunction::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public ToDoubleBiFunctionBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2> ToDoubleBiFunctionBuilder<T1, T2> toDoubleBiFunction() {
		return new ToDoubleBiFunctionBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2> ToDoubleBiFunctionBuilder<T1, T2> toDoubleBiFunction(Consumer<java.util.function.ToDoubleBiFunction<T1, T2>> consumer) {
		return new ToDoubleBiFunctionBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final ToDoubleBiFunctionBuilder<T1, T2> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final java.util.function.ToDoubleBiFunction<T1, T2> build() {

		final java.util.function.ToDoubleBiFunction<T1, T2> eventuallyFinal = this.eventually;

		java.util.function.ToDoubleBiFunction<T1, T2> retval;

		final Case<LBiPredicate<T1, T2>, java.util.function.ToDoubleBiFunction<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = Function4U.l((T1 t1, T2 t2) -> {
			try {
				for (Case<LBiPredicate<T1, T2>, java.util.function.ToDoubleBiFunction<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2)) {
						return aCase.caseFunction().applyAsDouble(t1, t2);
					}
				}

				return eventuallyFinal.applyAsDouble(t1, t2);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final java.util.function.ToDoubleBiFunction<T1, T2> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
