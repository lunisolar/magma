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

import java.util.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_ContainAnyKey_Test {

    public static final Map<String, String> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put("key1", "value1");
        MAP.put("key2", "value2");
        MAP.put("key3", "value3");
    }

    @Test void containAnyKey() {
        attest(P.containAnyKey(MAP, "key1")).mustEx(Be::TrueEx);
        attest(P.containAnyKey(MAP, "key1", "key2")).mustEx(Be::TrueEx);
        attest(P.containAnyKey(MAP, "other")).mustEx(Be::FalseEx);
        attest(P.containAnyKey(MAP, "key1", "other")).mustEx(Be::TrueEx);
    }

    @Test void ex() {
        attest(P.containAnyKeyEx(MAP, "other", "other2"))
                .mustEx(Be::equalEx, "Map <{key1=value1, key2=value2, key3=value3}> must contain any key from <[other, other2]>.");
    }
}
