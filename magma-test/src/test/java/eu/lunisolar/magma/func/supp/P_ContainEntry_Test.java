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

public class P_ContainEntry_Test {

    public static final Map<String, String> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put("key1", "value1");
        MAP.put("key2", "value2");
        MAP.put("key3", "value3");
    }

    @Test void containKeys() {
        attest(P.containEntry(MAP, "key1", "value1")).must$(Be::True$);
        attest(P.containEntry(MAP, "key1", "OTHER")).must$(Be::False$);
        attest(P.containEntry(MAP, "OTHER", "value1")).must$(Be::False$);
        attest(P.containEntry(MAP, "OTHER", "OTHER")).must$(Be::False$);
    }

    @Test void notContainKeys() {
        attest(P.notContainEntry(MAP, "key1", "value1")).must$(Be::False$);
        attest(P.notContainEntry(MAP, "key1", "OTHER")).must$(Be::True$);
        attest(P.notContainEntry(MAP, "OTHER", "value1")).must$(Be::True$);
        attest(P.notContainEntry(MAP, "OTHER", "OTHER")).must$(Be::True$);
    }

    @Test void ex() {
        attest(P.notContainEntry$(MAP, "key1", "value1"))
                .must$(Be::equal$, "Map <{key1=value1, key2=value2, key3=value3}> must NOT contain entry with key <key1> and value <value1>.");

    }
}
