/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.func.build.operator.unary;

import eu.lunisolar.magma.func.operator.unary.*;
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

/** Builder for LByteUnaryOperatorX. */
public final class LByteUnaryOperatorXBuilder<X extends Throwable> extends PerCaseBuilderWithByteProduct.Base<LByteUnaryOperatorXBuilder<X>, LBytePredicateX<X>, LByteUnaryOperatorX<X>> {

	private Consumer<LByteUnaryOperatorX<X>> consumer;

	private @Nullable HandlingInstructions handling;

	public static final LByteUnaryOperatorX EVENTUALLY_THROW = LByteUnaryOperatorX.lX((byte b) -> {
		String message;
		try {
			message = String.format("No case specified for: %s  as function %s.", b, LByteUnaryOperatorX.DESCRIPTION);
		} catch (Exception e) { // NOSONAR
				message = "No case specified for input data (no details can be provided).";
			}

			throw new IllegalStateException(message);
		});

	public LByteUnaryOperatorXBuilder(@Nullable Consumer<LByteUnaryOperatorX<X>> consumer) {
		super(EVENTUALLY_THROW, LByteUnaryOperatorX::constant);

		this.consumer = consumer;
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	public LByteUnaryOperatorXBuilder() {
		this(null);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public static final <X extends Throwable> LByteUnaryOperatorXBuilder<X> byteUnaryOperatorX() {
		return new LByteUnaryOperatorXBuilder();
	}

	/** One of ways of creating builder. This might be the only way (considering all _functional_ builders) that might be utilize to specify generic params only once. */
	@Nonnull
	public static final <X extends Throwable> LByteUnaryOperatorXBuilder<X> byteUnaryOperatorX(Consumer<LByteUnaryOperatorX<X>> consumer) {
		return new LByteUnaryOperatorXBuilder(consumer);
	}

	/** One of ways of creating builder. In most cases (considering all _functional_ builders) it requires to provide generic parameters (in most cases redundantly) */
	@Nonnull
	public final LByteUnaryOperatorXBuilder<X> withHandling(@Nonnull HandlingInstructions<X, X> handling) {
		Null.nonNullArg(handling, "handling");
		if (this.handling != null) {
			throw new UnsupportedOperationException("Handling is already set for this builder.");
		}
		this.handling = handling;
		return self();
	}

	/** Builds the functional interface implementation and if previously provided calls the consumer. */
	@Nonnull
	public final LByteUnaryOperatorX<X> build() {

		final LByteUnaryOperatorX<X> eventuallyFinal = this.eventually;

		LByteUnaryOperatorX<X> retval;

		final Case<LBytePredicateX<X>, LByteUnaryOperatorX<X>>[] casesArray = cases.toArray(new Case[cases.size()]);
		retval = LByteUnaryOperatorX.<X> lX(b -> {
			try {
				for (Case<LBytePredicateX<X>, LByteUnaryOperatorX<X>> aCase : casesArray) {
					if (aCase.casePredicate().doTest(b)) {
						return aCase.caseFunction().doApplyAsByte(b);
					}
				}

				return eventuallyFinal.doApplyAsByte(b);
			} catch (Error e) { // NOSONAR
					throw e;
				} catch (Throwable e) { // NOSONAR
					throw Handler.handleOrPropagate(e, handling);
				}
			});

		if (consumer != null) {
			consumer.accept(retval);
		}
		return retval;
	}

	public final LByteUnaryOperatorX<X> build(@Nonnull HandlingInstructions<X, X> handling) {
		this.withHandling(handling);
		return build();
	}

}
