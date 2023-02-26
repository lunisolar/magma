/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.tuple.LDblSingle;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class MustDoubleSingleTest {

    @Test
    void setValueIf_match() {
        var a = LDblSingle.of(0);
        attest(a.value()).mustBeEqual(0);
        a.setValueIf(2, v -> v == 0);
        attest(a.value()).mustBeEqual(2);
    }

    @Test
    void setValueIf_noMatch() {
        var a = LDblSingle.of(0);
        attest(a.value()).mustBeEqual(0);
        a.setValueIf(2, v -> v == 2);
        attest(a.value()).mustBeEqual(0);
    }

    @Test
    void setValueIf2_match() {
        var a = LDblSingle.of(0);
        attest(a.value()).mustBeEqual(0);
        a.setValueIf(2, (x, v) -> v == 0);
        attest(a.value()).mustBeEqual(2);
    }

    @Test
    void setValueIf2_noMatch() {
        var a = LDblSingle.of(0);
        attest(a.value()).mustBeEqual(0);
        a.setValueIf(2, (x, v) -> v == 2);
        attest(a.value()).mustBeEqual(0);
    }

}
