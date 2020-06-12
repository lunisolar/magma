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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.supplier.LLongSupplier;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jakub Wach
 */
public class MementoTest {

    @Test
    void memento1() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.memento(-1, -1, i::getAndIncrement, (m, x1, x2) -> x2);

        assertThat(sut.lastValue()).isEqualTo(-1);
        assertThat(sut.lastBaseValue()).isEqualTo(-1);
        assertThat(sut.getAsLong()).isEqualTo(0);

        assertThat(sut.lastValue()).isEqualTo(0);
        assertThat(sut.lastBaseValue()).isEqualTo(0);
        assertThat(sut.getAsLong()).isEqualTo(1);

        assertThat(sut.lastValue()).isEqualTo(1);
        assertThat(sut.lastBaseValue()).isEqualTo(1);
        assertThat(sut.getAsLong()).isEqualTo(2);
    }

    @Test
    void memento2() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.memento(-1, 5, i::getAndIncrement, (m, x1, x2) -> Long.sum(m, x2));

        assertThat(sut.lastValue()).isEqualTo(5);
        assertThat(sut.lastBaseValue()).isEqualTo(-1);
        assertThat(sut.getAsLong()).isEqualTo(5); // 5 + 0

        assertThat(sut.lastValue()).isEqualTo(5);
        assertThat(sut.lastBaseValue()).isEqualTo(0);
        assertThat(sut.getAsLong()).isEqualTo(6);  // 5 + 0 + 1

        assertThat(sut.lastValue()).isEqualTo(6);
        assertThat(sut.lastBaseValue()).isEqualTo(1);
        assertThat(sut.getAsLong()).isEqualTo(8);  // 5 + 0 + 1 + 2

        assertThat(sut.lastValue()).isEqualTo(8);
        assertThat(sut.lastBaseValue()).isEqualTo(2);
        assertThat(sut.getAsLong()).isEqualTo(11);  // 5 + 0 + 1 + 2 + 3
    }

    @Test
    void delta() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.deltaOf(i::getAndIncrement);

        assertThat(sut.lastValue()).isEqualTo(0);
        assertThat(sut.lastBaseValue()).isEqualTo(0);
        assertThat(sut.getAsLong()).isEqualTo(1); // increment 1

        assertThat(sut.lastValue()).isEqualTo(1);
        assertThat(sut.lastBaseValue()).isEqualTo(1);
        assertThat(sut.getAsLong()).isEqualTo(1);  // increment 1

        assertThat(sut.lastValue()).isEqualTo(1);
        assertThat(sut.lastBaseValue()).isEqualTo(2);
        assertThat(sut.getAsLong()).isEqualTo(1);

        assertThat(sut.lastValue()).isEqualTo(1);
        assertThat(sut.lastBaseValue()).isEqualTo(3);
        assertThat(sut.getAsLong()).isEqualTo(1);
    }
}
