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

package eu.lunisolar.magma.func.build;

import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.fluent.FluentSubcontext;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.*;

import eu.lunisolar.magma.basics.builder.*;

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

/**
 * Abstract implementation of a per case builder of a complex implementation of a functional interface.
 *
 * Each case consist of a appropriate condition - predicate, supplier, or operator depending on the functional interface type - and a evaluating function
 * (same type as overall function) that will be called when condition expression evaluates to **true**.
 *
 * Cases are evaluated in a order one by one. First condition that returns **true** will decide what function will be called. Eventually if no condition is
 * evaluating to **true** a last resort function _eventually_ is called. By default _eventually_ will throw an exception that there is no case that will cover
 * the input data. This default _evantually_ behavior can be overridden.
 */
public abstract class PerCaseBuilderWithByteProduct<PCB extends PerCaseBuilderWithByteProduct<PCB, P, F>, P, F> implements Fluent<PCB> {

	protected @Nonnull final List<Case<P, F>> cases = new ArrayList<>();
	protected @Nonnull F eventually;

	protected @Nonnull final ByteFunction<F> directToFunction;

	protected PerCaseBuilderWithByteProduct(@Nonnull F eventually, @Nonnull ByteFunction<F> directToFunction) {
		this.eventually = eventually;
		this.directToFunction = directToFunction;
	}

	// <editor-fold desc="case">

	public final PCB addCase(@Nonnull Case<P, F> theCase) {
		cases.add(theCase);
		return self();
	}

	/** Starts adding the case to the list. Changes also the fluent context. */
	public final PartialCase inCase(@Nonnull P casePredicate) {
		Objects.requireNonNull(casePredicate, "Argument [casePredicate] cannot be null.");
		return new PartialCase(casePredicate);
	}

	/** Starts adding the case to the list. */
	public final PCB addCase(Consumer<CaseBuilder> caseBuilderConsumer) {
		Objects.requireNonNull(caseBuilderConsumer, "Argument [caseBuilderConsumer] cannot be null.");
		caseBuilderConsumer.accept(new CaseBuilder());
		return self();
	}

	/** Sets the function to evaluate _eventually_ when input data do not match any case. */
	public final PCB eventually(@Nonnull F caseFunction) {
		eventually = caseFunction;
		return self();
	}

	/** Sets the function to evaluate _eventually_ when input data do not match any case. */
	public final PCB eventuallyProduce(@Nonnull byte directValue) {
		eventually = directToFunction.apply(directValue);
		return self();
	}

	// </editor-fold>

	@Immutable
	@ThreadSafe
	public final class PartialCase implements FluentSubcontext<PartialCase, PCB> {
		private final P casePredicate;

		private PartialCase(@Nonnull P casePredicate) {
			this.casePredicate = casePredicate;
		}

		/** Finalize the case build by providing second required value for the Case. */
		public final PCB evaluate(@Nonnull F caseFunction) {
			PerCaseBuilderWithByteProduct.this.addCase(Case.of(casePredicate, caseFunction));
			return PerCaseBuilderWithByteProduct.this.self();
		}

		/** Finalize the case build by providing second required value for the Case. */
		public final PCB produce(@Nonnull byte directValue) {
			PerCaseBuilderWithByteProduct.this.addCase(Case.of(casePredicate, directToFunction.apply(directValue)));
			return PerCaseBuilderWithByteProduct.this.self();
		}

	}

	@Immutable
	@ThreadSafe
	public final class CaseBuilder {
		private CaseBuilder() {
		}

		@Nonnull
		public final PartialCase of(@Nonnull P casePredicate) {
			return new PartialCase(casePredicate);
		}
	}
}
