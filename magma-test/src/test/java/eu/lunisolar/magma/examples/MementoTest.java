/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supplier.LLongSupplier;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

/**
 * @author Jakub Wach
 */
public class MementoTest {

    @Test
    void memento1() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.memento(-1, -1, i::getAndIncrement, (m, x1, x2) -> x2);

        attest(sut.lastValue()).mustEx(Be::equalEx, -1);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, -1);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 0);

        attest(sut.lastValue()).mustEx(Be::equalEx, 0);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 0);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 1);

        attest(sut.lastValue()).mustEx(Be::equalEx, 1);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 1);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 2);
    }

    @Test
    void memento2() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.memento(-1, 5, i::getAndIncrement, (m, x1, x2) -> Long.sum(m, x2));

        attest(sut.lastValue()).mustEx(Be::equalEx, 5);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, -1);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 5); // 5 + 0

        attest(sut.lastValue()).mustEx(Be::equalEx, 5);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 0);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 6);  // 5 + 0 + 1

        attest(sut.lastValue()).mustEx(Be::equalEx, 6);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 1);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 8);  // 5 + 0 + 1 + 2

        attest(sut.lastValue()).mustEx(Be::equalEx, 8);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 2);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 11);  // 5 + 0 + 1 + 2 + 3
    }

    @Test
    void delta() {
        AtomicLong i = new AtomicLong(0);
        var sut = LLongSupplier.deltaOf(i::getAndIncrement);

        attest(sut.lastValue()).mustEx(Be::equalEx, 0);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 0);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 1); // increment 1

        attest(sut.lastValue()).mustEx(Be::equalEx, 1);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 1);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 1);  // increment 1

        attest(sut.lastValue()).mustEx(Be::equalEx, 1);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 2);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 1);

        attest(sut.lastValue()).mustEx(Be::equalEx, 1);
        attest(sut.lastBaseValue()).mustEx(Be::equalEx, 3);
        attest(sut.getAsLong()).mustEx(Be::equalEx, 1);
    }
}
