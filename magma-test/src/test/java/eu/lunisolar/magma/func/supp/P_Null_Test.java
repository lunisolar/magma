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

import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_Null_Test {

    public static final String STR = "str";

    @Test void Null_P() {
        attest(P.Null(null)).must$(Be::True$);
        attest(P.notNull(null)).must$(Be::False$);

        attest(P.Null(STR)).must$(Be::False$);
        attest(P.notNull(STR)).must$(Be::True$);
    }

    @Test void Null_Be() {
        attest(Be.Null(null)).must$(Be::True$);
        attest(Be.notNull(null)).must$(Be::False$);

        attest(Be.Null(STR)).must$(Be::False$);
        attest(Be.notNull(STR)).must$(Be::True$);
    }

    @Test void Null$() {
        attest(Be.Null$(null)).must$(Be::equal$, (String) null);
        attest(Be.notNull$(null)).must$(Be::equal$, "Reference must NOT be null, currently is pointing to <null>.");

        attest(Be.Null$(STR)).must$(Be::equal$, "Reference must be null, currently is pointing to <str>.");
        attest(Be.notNull$(STR)).must$(Be::equal$, (String) null);
    }

}
