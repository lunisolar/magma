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

package eu.lunisolar.magma.func.build.predicate;

import eu.lunisolar.magma.func.predicate.*;
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

/** Builder for LBiObjFloatPredicate. */
public final class LBiObjFloatPredicateBuilder<T1, T2> extends PerCaseBuilderWithBooleanProduct.Base<LBiObjFloatPredicateBuilder<T1, T2>, LBiObjFloatPredicate<T1, T2>, LBiObjFloatPredicate<T1, T2>> {

	private Consumer<LBiObjFloatPredicate<T1, T2>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LBiObjFloatPredicate EVENTUALLY_THROW = LBiObjFloatPredicate.l((Object t1, Object t2, float f) -> {
		String message;
		try {
			message = String.format("No case specified for: %s ,%s ,%s  as function %s.", t1, t2, f, LBiObjFloatPredicate.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LBiObjFloatPredicateBuilder(@Nullable Consumer<LBiObjFloatPredicate<T1, T2>> consumer) {
		super(EVENTUALLY_THROW, LBiObjFloatPredicate::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LBiObjFloatPredicateBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <T1, T2> LBiObjFloatPredicateBuilder<T1, T2> biObjFloatPredicate() {
		return new LBiObjFloatPredicateBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <T1, T2> LBiObjFloatPredicateBuilder<T1, T2> biObjFloatPredicate(Consumer<LBiObjFloatPredicate<T1, T2>> consumer) {
		return new LBiObjFloatPredicateBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LBiObjFloatPredicateBuilder<T1, T2> withHandling(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is allready set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LBiObjFloatPredicate<T1, T2> build() {

		final LBiObjFloatPredicate<T1, T2> eventuallyFinal = this.eventually;

		LBiObjFloatPredicate<T1, T2> retval;

		final Case<LBiObjFloatPredicate<T1, T2>, LBiObjFloatPredicate<T1, T2>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LBiObjFloatPredicate.l((T1 t1, T2 t2, float f) -> {
			try {
				for (Case<LBiObjFloatPredicate<T1, T2>, LBiObjFloatPredicate<T1, T2>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(t1, t2, f)) {
						return aCase.caseFunction().doTest(t1, t2, f);
					}
				}

				return eventuallyFinal.doTest(t1, t2, f);
			} catch (Throwable e) {
				throw Handler.handleOrPropagate(e, handling);
			}
		});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LBiObjFloatPredicate<T1, T2> build(@Nonnull HandlingInstructions<RuntimeException, RuntimeException> handling) {
		this.withHandling(handling);
		return build();
	}

}
