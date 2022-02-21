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

import static eu.lunisolar.magma.asserts.Attests.attestThat;

public class P_ContainAnyKey_Test {

    public static final Map<String, String> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put("key1", "value11");
        MAP.put("key2", "value12");
        MAP.put("key3", "value13");
    }

    @Test void containAnyKey() {
        attestThat(P.containAnyKey(MAP, "key1")).must$(Be::True$);
        attestThat(P.containAnyKey(MAP, "key1", "key2")).must$(Be::True$);
        attestThat(P.containAnyKey(MAP, "other")).must$(Be::False$);
        attestThat(P.containAnyKey(MAP, "key1", "other")).must$(Be::True$);
    }

    @Test void ex() {
        attestThat(P.containAnyKey$(MAP, "other", "other2")).must$(Be::equal$, "Map <{key1=value11, key2=value12, key3=value13}> must contain any key from <[other, other2]>.");

    }
}
