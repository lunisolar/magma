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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@Immutable
@ThreadSafe
public class PartialCaseWithProduct<SELF extends PartialCaseWithProduct<SELF, PCB, P, F, R>, PCB extends PerCaseBuilderWithProduct<PCB, P, F, R, SELF>, P, F, R> extends PartialCase<SELF, PCB, P, F> {

    public PartialCaseWithProduct(@Nonnull PCB superContext, @Nonnull P casePredicate) {
        super(superContext, casePredicate);
    }

    /** Finalize the case build by providing second required value for the Case. */
    public final PCB produce(@Nonnull R directValue) {
        return this.evaluate(superContext().directToFunction.apply(directValue));
    }

    public static final class The<PCB extends PerCaseBuilderWithProduct.Base<PCB, P, F, R>, P, F, R> extends PartialCaseWithProduct<The<PCB, P, F, R>, PCB, P, F, R> {
        public The(@Nonnull PCB superContext, @Nonnull P casePredicate) {
            super(superContext, casePredicate);
        }
    }
}