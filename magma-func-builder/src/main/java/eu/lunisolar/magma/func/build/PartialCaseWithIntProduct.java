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

@Immutable
@ThreadSafe
public class PartialCaseWithIntProduct<SELF extends PartialCaseWithIntProduct<SELF, PCB, P, F>, PCB extends PerCaseBuilderWithIntProduct<PCB, P, F, SELF>, P, F> extends PartialCase<SELF, PCB, P, F> {

	public PartialCaseWithIntProduct(@Nonnull PCB superContext, @Nonnull P casePredicate) {
		super(superContext, casePredicate);
	}

	/** Finalize the case build by providing second required value for the Case. */
	public final PCB produce(int directValue) {
		return this.evaluate(superContext().directToFunction.doApply(directValue));
	}

	public static final class The<PCB extends PerCaseBuilderWithIntProduct<PCB, P, F, The<PCB, P, F>>, P, F> extends PartialCaseWithIntProduct<The<PCB, P, F>, PCB, P, F> {
		public The(@Nonnull PCB superContext, @Nonnull P casePredicate) {
			super(superContext, casePredicate);
		}
	}
}
