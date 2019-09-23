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
import eu.lunisolar.magma.basics.fluent.FluentSyntax;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.function.*;

/**
 * Fluent API for logical operation for predicates on specific subject.
 * The {@link Probing} is the syntax while specialization of {@link Probe} is the actual dictionary.
 */
public final class Probing {
    //<editor-fold desc="no instance">
    private Probing() {
    }
    //</editor-fold>

    public static <T, P extends Probe<T>> Probing.OrAnd<P, T> probe(@Nonnull Function<T, P> probeFactory, @Nullable Predicate<P> probeTest) {
        return new Probing.The<>(probeFactory, probeTest);
    }

    public static <T, P extends Probe<T>> Probing.Start<P, T> probe(@Nonnull Function<T, P> probeFactory, Class<T> clazz) {
        return new Probing.The<>(probeFactory, null);
    }

    public interface Start<P extends Probe<T>, T> extends FluentSyntax {
        @Nonnull OrAnd<P, T> that(@Nonnull Predicate<P> test);
    }

    public interface OrAnd<P extends Probe<T>, T> extends And<P, T>, Or<P, T> {

    }

    /**
     * AND path.
     * There is no such thing like operator priority in chained method (or more precisely lets not go the confusing and complex way that would allow this).
     * Once you choose main operation You are committed.
     *
     * @see Probing#allOf
     * @see Probing#oneOf
     */
    public interface And<P extends Probe<T>, T> extends End<P, T>, FluentSyntax {
        @Nonnull And<P, T> and(@Nonnull Predicate<P> test);
    }

    /**
     * AND path.
     * There is no such thing like operator priority in chained method (or more precisely lets not go the confusing and complex way that would allow this).
     * Once you choose main operation You are committed.
     *
     * @see Probing#allOf
     * @see Probing#oneOf
     */
    public interface Or<P extends Probe<T>, T> extends End<P, T>, FluentSyntax {
        @Nonnull Or<P, T> or(@Nonnull Predicate<P> test);
    }

    public interface End<P extends Probe<T>, T> extends FluentSyntax {
        Predicate<T> toPredicate();

        default boolean isTrue(T subject) {
            return toPredicate().test(subject);
        }

        default boolean isFalse(T subject) {
            return !isTrue(subject);
        }
    }

    //<editor-fold desc="logic vararg operations">

    public static <P extends Probe<T>, T> Predicate<P> not(Predicate<P> test) {
        Null.nonNullArg(test, "test");
        return test.negate();
    }

    @SafeVarargs
    public static <P extends Probe<T>, T> Predicate<P> allOf(Predicate<P>... tests) {

        if (tests == null) {
            return p -> false;
        }

        Null.nonNullArg("test", tests);

        return p -> {
            for (Predicate<P> probeTest : tests) {
                if (!probeTest.test(p)) {
                    return false;
                }
            }

            return true;
        };

    }

    @SafeVarargs
    public static <P extends Probe<T>, T> Predicate<P> oneOf(Predicate<P>... tests) {
        if (tests == null) {
            return p -> false;
        }

        Null.nonNullArg("test", tests);

        return p -> {
            for (Predicate<P> probeTest : tests) {
                if (probeTest.test(p)) {
                    return true;
                }
            }

            return false;
        };
    }

    //</editor-fold>

    //<editor-fold desc="The">

    @Immutable
    public final static class The<P extends Probe<T>, T> implements Fluent<The<P, T>>, Probing.Start<P, T>, Probing.OrAnd<P, T> {

        protected final Function<T, P> probeFactory;

        protected final Predicate<P> probeTest;

        public The(@Nonnull Function<T, P> probeFactory, @Nullable Predicate<P> probeTest) {
            Null.nonNull(probeFactory, () -> "probeFactory argument cannot be null.");

            this.probeFactory = probeFactory;
            this.probeTest = probeTest;
        }

        @Nonnull @Override public Probing.OrAnd<P, T> that(@Nonnull Predicate<P> test) {
            Null.nonNull(test, () -> "[test] argument cannot be null.");
            if (probeTest != null) {
                throw new IllegalStateException("First condition is already set.");
            }

            return probe(probeFactory, test);
        }

        @Nonnull @Override public Probing.And<P, T> and(@Nonnull Predicate<P> test) {
            Null.nonNull(test, () -> "[test] argument cannot be null.");
            return probe(probeFactory, p -> probeTest.test(p) && test.test(p));
        }

        @Nonnull @Override public Probing.Or<P, T> or(@Nonnull Predicate<P> test) {
            Null.nonNull(test, () -> "[test] argument cannot be null.");
            return probe(probeFactory, p -> probeTest.test(p) || test.test(p));
        }

        @Override public Predicate<T> toPredicate() {
            Null.nonNull(probeTest, () -> "[test] argument cannot be null.");
            return t -> {
                P probe = probeFactory.apply(t);
                if (probe == null) {
                    throw new IllegalStateException("Created probe is null.");
                }
                return probeTest.test(probe);
            };
        }
    }

    //</editor-fold>

}
