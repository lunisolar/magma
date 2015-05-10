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

import eu.lunisolar.magma.basics.fluent.Fluent;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.*;

import static java.util.Objects.*;

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
@SuppressWarnings("unchecked")
public abstract class PerCaseBuilder<PCB extends PerCaseBuilder<PCB, P, F, PC>, P, F, PC extends PartialCase<PC, PCB, P, F>> implements Fluent<PCB> {

    protected @Nonnull final List<Case<P, F>> cases = new ArrayList<>();
    protected @Nonnull F eventually;

    public PerCaseBuilder(@Nonnull F eventually) {
        this.eventually = requireNonNull(eventually, "Argument [eventually] cannot be null.");
    }

    // <editor-fold desc="case">

    public final PCB addCase(@Nonnull Case<P, F> theCase) {
        cases.add(theCase);
        return self();
    }

    /** Starts adding the case to the list. Changes also the fluent context. */
    public final PC inCase(@Nonnull P casePredicate) {
        requireNonNull(casePredicate, "Argument [casePredicate] cannot be null.");
        return partialCaseFactoryMethod(casePredicate);
    }

    /** Starts adding the case to the list. */
    public final PCB addCase(Consumer<CaseBuilder<P, PC>> caseBuilderConsumer) {
        requireNonNull(caseBuilderConsumer, "Argument [caseBuilderConsumer] cannot be null.");
        caseBuilderConsumer.accept(new CaseBuilder<>(this::partialCaseFactoryMethod));
        return self();
    }

    /** Sets the function to evaluate _eventually_ when input data do not match any case. */
    public final PCB eventually(@Nonnull F caseFunction) {
        eventually = caseFunction;
        return self();
    }

    // </editor-fold>

    protected PC partialCaseFactoryMethod(P casePredicate) {
        return (PC) new PartialCase(PerCaseBuilder.this, casePredicate);
    }

    public static class Base<SELF extends Base<SELF, P, F>, P, F> extends PerCaseBuilder<SELF, P, F, PartialCase.The<SELF, P, F>> {
        public Base(@Nonnull F eventually) {
            super(eventually);
        }

        @Override
        protected PartialCase.The<SELF, P, F> partialCaseFactoryMethod(P casePredicate) {
            return new PartialCase.The(self(), casePredicate);
        }
    }

}
