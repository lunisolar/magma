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

package eu.lunisolar.magma.func.supp;

import eu.lunisolar.magma.asserts.Attest;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_Assignable_Test {

    public static final Class<?> BASE = Number.class;
    public static final Class<?> SPEC = Integer.class;

    @Test void sanityTest() {
        
        attest(BASE.isAssignableFrom(SPEC)).must$(Be::True$);
        attest(SPEC.isAssignableFrom(BASE)).must$(Be::False$);

    }

    @Test void baseAssignableFromSpec() {
        attest(BASE).must$(Be::assignableFrom$, SPEC);
        attest(BASE).must$(SPEC, Be::assignableFrom$);

        attest(SPEC).must$(Be::notAssignableFrom$, BASE);
        attest(SPEC).must$(BASE, Be::notAssignableFrom$);
    }

    @Test void specAssignableToBase() {
        attest(SPEC).must$(Be::assignableTo$, BASE);
        attest(SPEC).must$(BASE, Be::assignableTo$);

        attest(BASE).must$(Be::notAssignableTo$, SPEC);
        attest(BASE).must$(SPEC, Be::notAssignableTo$);
    }

    @Test void baseAssignableFromSpec_negative() {
        attest(P.assignableFrom$(SPEC, BASE)).must$(Be::equal$, "Class <class java.lang.Integer> must be assignable from <class java.lang.Number>.");
    }

    @Test void baseAssignableToSpec_negative() {
        attest(P.assignableTo$(BASE, SPEC)).must$(Be::equal$, "Class <class java.lang.Number> must be assignable to <class java.lang.Integer>.");
    }

}
