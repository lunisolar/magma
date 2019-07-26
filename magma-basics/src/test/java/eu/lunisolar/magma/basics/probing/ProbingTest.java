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

import org.testng.annotations.Test;

import java.util.function.*;

import static eu.lunisolar.magma.basics.probing.Probing.probe;
import static eu.lunisolar.magma.basics.probing.Probing.allOf;
import static eu.lunisolar.magma.basics.probing.Probing.oneOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ProbingTest {

    @Test void or() {

        Predicate<Integer> predicate = probe(ObjectProbe::of, Integer.class)
                .that(probe -> probe.isEqualTo(1))
                .or(oneOf(
                        probe -> probe.has(Integer::intValue, i -> i == 12),
                        probe -> probe.has(Integer::intValue, i -> i == 14)
                ))
                .toPredicate();

        assertThat(predicate.test(-1)).isFalse();
        assertThat(predicate.test(0)).isFalse();
        assertThat(predicate.test(1)).isTrue();
        assertThat(predicate.test(12)).isTrue();
        assertThat(predicate.test(13)).isFalse();
        assertThat(predicate.test(14)).isTrue();
        assertThat(predicate.test(20)).isFalse();
    }

    @Test void or2() {

        Predicate<Integer> predicate = probe(ObjectProbe::of, Integer.class)
                .that(probe -> probe.isEqualTo(1))
                .or(probe -> probe.has(Integer::intValue, i -> i == 12))
                .or(probe -> probe.has(Integer::intValue, i -> i == 14))
                .toPredicate();

        assertThat(predicate.test(-1)).isFalse();
        assertThat(predicate.test(0)).isFalse();
        assertThat(predicate.test(1)).isTrue();
        assertThat(predicate.test(12)).isTrue();
        assertThat(predicate.test(13)).isFalse();
        assertThat(predicate.test(14)).isTrue();
        assertThat(predicate.test(20)).isFalse();
    }

    @Test void and() {

        Predicate<Integer> predicate = probe(ObjectProbe::of, Integer.class)
                .that(probe -> probe.isEqualTo(1))
                .and(allOf(
                        probe -> probe.has(Integer::intValue, i -> i == 1),
                        probe -> probe.has(Integer::intValue, i -> i > 0)
                ))
                .toPredicate();

        assertThat(predicate.test(-1)).isFalse();
        assertThat(predicate.test(0)).isFalse();
        assertThat(predicate.test(1)).isTrue();
        assertThat(predicate.test(12)).isFalse();
        assertThat(predicate.test(13)).isFalse();
        assertThat(predicate.test(14)).isFalse();
        assertThat(predicate.test(20)).isFalse();
    }

    @Test void and2() {

        Predicate<Integer> predicate = probe(ObjectProbe::of, Integer.class)
                .that(probe -> probe.isEqualTo(1))
                .and(probe -> probe.has(Integer::intValue, i -> i == 1))
                .and(probe -> probe.has(Integer::intValue, i -> i > 0))
                .toPredicate();

        assertThat(predicate.test(-1)).isFalse();
        assertThat(predicate.test(0)).isFalse();
        assertThat(predicate.test(1)).isTrue();
        assertThat(predicate.test(12)).isFalse();
        assertThat(predicate.test(13)).isFalse();
        assertThat(predicate.test(14)).isFalse();
        assertThat(predicate.test(20)).isFalse();
    }

}