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

package eu.lunisolar.magma.basics.builder;

import eu.lunisolar.magma.basics.fluent.AbstractFluentSubcontext;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@Immutable
@ThreadSafe
public class PartialCase<SELF extends PartialCase<SELF, PCB, P, F>, PCB extends PerCaseBuilder<PCB, P, F, SELF>, P, F> extends AbstractFluentSubcontext<SELF, PCB> {
    protected final P casePredicate;

    public PartialCase(@Nonnull PCB superContext, @Nonnull P casePredicate) {
        super(superContext);
        this.casePredicate = casePredicate;
    }

    /** Finalize the case build by providing second required value for the Case. */
    public final PCB evaluate(@Nonnull F caseFunction) {
        PCB superContext = superContext();
        superContext.addCase(Case.of(casePredicate, caseFunction));
        return superContext;
    }

    public static final class The<PCB extends PerCaseBuilder.Base<PCB, P, F>, P, F> extends PartialCase<The<PCB, P, F>, PCB, P, F> {

        public The(@Nonnull PCB superContext, @Nonnull P casePredicate) {
            super(superContext, casePredicate);
        }
    }

}