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

package eu.lunisolar.magma.func.supp;

import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static java.util.Arrays.*;

public class P_ContainsExactly_Test {

    @Test void containExactlyTest() {
        attest(P.containExactly(asList(1, 2, 3), 1, 2, 3)).mustEx(Be::TrueEx);
        attest(P.containExactly(asList(1, 2, 3), 1, 2)).mustEx(Be::FalseEx);
        attest(P.containExactly(asList(1, 2, 3), 2, 3)).mustEx(Be::FalseEx);
        attest(P.containExactly(asList(1, 2, 3), 3, 2, 1)).mustEx(Be::FalseEx);
        attest(P.containExactly(asList(1, 2, 3))).mustEx(Be::FalseEx);
        attest(P.containExactly(asList())).mustEx(Be::TrueEx);

        attest(Does.containExactly(asList(1, 2, 3), 1, 2, 3)).mustEx(Be::TrueEx);
        attest(Does.containExactly(asList(1, 2, 3), 1, 2)).mustEx(Be::FalseEx);
        attest(Does.containExactly(asList(1, 2, 3), 2, 3)).mustEx(Be::FalseEx);
        attest(Does.containExactly(asList(1, 2, 3), 3, 2, 1)).mustEx(Be::FalseEx);
        attest(Does.containExactly(asList(1, 2, 3))).mustEx(Be::FalseEx);
        attest(Does.containExactly(asList())).mustEx(Be::TrueEx);
    }

    @Test void notContainExactlyTest() {
        attest(P.notContainExactly(asList(1, 2, 3), 1, 2, 3)).mustEx(Be::FalseEx);
        attest(P.notContainExactly(asList(1, 2, 3), 1, 3, 4)).mustEx(Be::TrueEx);

        attest(Does.notContainExactly(asList(1, 2, 3), 1, 2, 3)).mustEx(Be::FalseEx);
        attest(Does.notContainExactly(asList(1, 2, 3), 1, 3, 4)).mustEx(Be::TrueEx);
    }

    @Test void array_in_messages() {

        var array = new Integer[]{1, 2, 3};

        attest(Has.lengthEx(array, 5)).mustEx(Be::equalEx, "Array <[1, 2, 3]> must be of size 5.");
        attest(Is.ofLengthEx(array, 5)).mustEx(Be::equalEx, "Array <[1, 2, 3]> must be of size 5.");

        attest(P.containExactlyEx(asList(array), 5, 6, 7)).mustEx(Be::equalEx, "Collection <[1, 2, 3]> must contain exactly elements in order: <[5, 6, 7]>.");

    }
}
