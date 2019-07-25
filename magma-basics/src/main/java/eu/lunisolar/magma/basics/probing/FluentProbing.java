/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics.probing;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

/**
 * @author Jakub Wach
 */
public final class FluentProbing<P extends Probe<T>, T> implements Fluent<FluentProbing<P, T>> {

    protected final Function<T, P> probeFactory;

    protected final Predicate<P> probeTest;

    public FluentProbing(@Nonnull Function<T, P> probeFactory, @Nullable Predicate<P> probeTest) {
        Null.requireNonNull(probeFactory, () -> "probeFactory argument cannot be null.");

        this.probeFactory = probeFactory;
        this.probeTest = probeTest;
    }

    public static <T, P extends Probe<T>> FluentProbing<P, T> probe(@Nonnull Function<T, P> probeFactory, @Nullable Predicate<P> probeTest) {
        return new FluentProbing<>(probeFactory, probeTest);
    }

    public static <T, P extends Probe<T>> FluentProbing<P, T> probe(@Nonnull Function<T, P> probeFactory, Class<T> clazz) {
        return probe(probeFactory, (Predicate) null);
    }

    public final FluentProbing<P, T> and(@Nonnull Predicate<P> probeTest) {
        return operation(probeTest, p -> this.probeTest.test(p) && probeTest.test(p));
    }

    @SafeVarargs public final FluentProbing<P, T> and(@Nonnull Predicate<P>... otherTests) {
        return operation(probeTest, p -> this.probeTest.test(p) && andOperation(p, otherTests));
    }

    public final FluentProbing<P, T> or(@Nonnull Predicate<P> probeTest) {
        return operation(probeTest, p -> this.probeTest.test(p) || probeTest.test(p));
    }

    @SafeVarargs public final FluentProbing<P, T> or(@Nonnull Predicate<P>... otherTests) {
        return operation(probeTest, p -> this.probeTest.test(p) || orOperation(p, otherTests));
    }

    @Nonnull private FluentProbing<P, T> operation(@Nonnull Predicate<P> probeTest, Predicate<P> newTest) {

        FluentProbing<P, T> retval;
        if (this.probeTest == null) {
            retval = probe(probeFactory, probeTest);
        } else {
            retval = probe(probeFactory, newTest);
        }
        return retval;
    }

    //<editor-fold desc="predicate">

    public Predicate<T> toPredicate() {
        Null.requireNonNull(probeTest, () -> "probeTest argument cannot be null.");
        return t -> {
            P probe = probeFactory.apply(t);
            if (probe == null) {
                throw new IllegalStateException("Created probe is null.");
            }
            return probeTest.test(probe);
        };

    }

    //</editor-fold>

    //<editor-fold desc="logic operations">

    public static <P extends Probe<?>> boolean andOperation(P probe, Predicate<P>... probeTests) {
        if (probeTests == null) {
            return false;
        }

        for (Predicate<P> probeTest : probeTests) {
            if (!probeTest.test(probe)) {
                return false;
            }
        }

        return true;
    }

    public static <P extends Probe<?>> boolean orOperation(P probe, Predicate<P>... probeTests) {
        if (probeTests == null) {
            return false;
        }

        for (Predicate<P> probeTest : probeTests) {
            if (probeTest.test(probe)) {
                return true;
            }
        }

        return false;
    }

    //</editor-fold>
}
