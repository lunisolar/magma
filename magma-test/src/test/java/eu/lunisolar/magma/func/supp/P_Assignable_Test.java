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

import eu.lunisolar.magma.asserts.Attest;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_Assignable_Test {

    public static final Class<?> BASE = Number.class;
    public static final Class<?> SPEC = Integer.class;

    @Test void sanityTest() {
        
        attest(BASE.isAssignableFrom(SPEC)).mustEx(Be::TrueEx);
        attest(SPEC.isAssignableFrom(BASE)).mustEx(Be::FalseEx);

    }

    @Test void baseAssignableFromSpec() {
        attest(BASE).mustEx(Be::assignableFromEx, SPEC);
        attest(BASE).mustEx(SPEC, Be::assignableFromEx);

        attest(SPEC).mustEx(Be::notAssignableFromEx, BASE);
        attest(SPEC).mustEx(BASE, Be::notAssignableFromEx);
    }

    @Test void specAssignableToBase() {
        attest(SPEC).mustEx(Be::assignableToEx, BASE);
        attest(SPEC).mustEx(BASE, Be::assignableToEx);

        attest(BASE).mustEx(Be::notAssignableToEx, SPEC);
        attest(BASE).mustEx(SPEC, Be::notAssignableToEx);
    }

    @Test void baseAssignableFromSpec_negative() {
        attest(P.assignableFromEx(SPEC, BASE)).mustEx(Be::equalEx, "Class <class java.lang.Integer> must be assignable from <class java.lang.Number>.");
    }

    @Test void baseAssignableToSpec_negative() {
        attest(P.assignableToEx(BASE, SPEC)).mustEx(Be::equalEx, "Class <class java.lang.Number> must be assignable to <class java.lang.Integer>.");
    }

}
