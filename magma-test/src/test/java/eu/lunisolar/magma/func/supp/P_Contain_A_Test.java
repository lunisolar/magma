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

import java.util.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_Contain_A_Test {

    public static final List<String> LIST;

    static {
        LIST = new ArrayList<>();
        LIST.add("value1");
        LIST.add("value2");
        LIST.add("value3");
    }

    @Test void containKeys() {
        attest(P.contain(LIST, "value1")).must$(Be::True$);
        attest(P.contain(LIST, "value1", "value2")).must$(Be::True$);
        attest(P.contain(LIST, "other")).must$(Be::False$);
        attest(P.contain(LIST, "value1", "other")).must$(Be::False$);
    }

    @Test void containKeys_empty() {
        attest(P.contain(new ArrayList<>())).must$(Be::True$);
        attest(P.contain(LIST)).must$(Be::False$);
        attest(P.notContain(new ArrayList<>())).must$(Be::False$);
        attest(P.notContain(LIST)).must$(Be::True$);
    }

    @Test void notContainKeys() {
        attest(P.notContain(LIST, "value1")).must$(Be::False$);
        attest(P.notContain(LIST, "value1", "value2")).must$(Be::False$);
        attest(P.notContain(LIST, "other")).must$(Be::True$);
        //
        attest(P.notContain(LIST, "value1", "other")).must$(Be::False$);
        attest(P.notContain(LIST, "other", "other1")).must$(Be::True$);
    }

    @Test void ex() {
        attest(P.contain$(LIST, "other", "other2"))
                .must$(Be::equal$, "Collection <[value1, value2, value3]> must contain elements <[other, other2]>.");

    }
}
