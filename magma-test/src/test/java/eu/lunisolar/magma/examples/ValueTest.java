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

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.value.LInteger;
import eu.lunisolar.magma.func.supp.value.LValue;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class ValueTest {

    @Test
    void valueIsMutable() {

        LValue<Integer> v = LValue.objValue(1);

        var result = v
                .filter(i -> i == 1)
                .uniMap(i -> i + 1);

        attest(result).mustEx(Be::sameEx, v)
                      .mustEx(Have::valueEqualEx, 2)
                      .checkBool(__ -> __.is(i -> i == 2), bool -> bool.mustEx(Be::TrueEx));
    }

    @Test
    void valueIsMutableAndVoidable() {

        LValue<Integer> v = LValue.objValue(1);

        var result = v
                .filter(i -> i != 1)     // v.value() == null
                .value(10)
                .uniMap(i -> i + 1);

        attest(result).mustEx(Be::sameEx, v)
                      .mustEx(Have::valueEqualEx, 11)
                      .checkBool(__ -> __.is(i -> i == 11), bool -> bool.mustEx(Be::TrueEx));
    }

    @Test
    void intValueIsMutableAndVoidable() {

        LInteger v = LInteger.intValue(1);

        var result = v
                .filter(i -> i != 1)     // v.value() == 0
                .map(i -> i + 1);

        attest(result).mustEx(Be::sameEx, v)
                      .mustEx(Have::valueEqualEx, 1)
                      .checkBool(__ -> __.is(i -> i == 1), bool -> bool.mustEx(Be::TrueEx));
    }

}
