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

import eu.lunisolar.magma.func.supp.check.Checks;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

@SuppressWarnings("rawtypes") public class P_Messages_Test {

    @Test void sizeOrLength() {
        attest(Has.lengthEx("1234", 99)).mustBeEqual("String <1234> must be 99 characters long (actual: 4).");
        attest(Has.lengthEx(new Object[]{1, 2}, 99)).mustBeEqual("Array <[1, 2]> must be of size 99 (actual: 2).");

        attest(Has.sizeEx(List.of(1), 99)).mustBeEqual("Collection <[1]> must be of size 99 (actual: 1).");
        attest(Has.sizeEx(Map.of(1, 1), 99)).mustBeEqual("Map <{1=1}> must be of size 99 (actual: 1).");
    }

    @Test void empty() {

        attest(Is.emptyEx("1234")).mustBeEqual("String <1234> must be empty (actual: 4).");
        attest(Is.blankEx("1234")).mustBeEqual("String <1234> must be blank (empty or consisting of only white characters; actual: 4).");
        attest(Is.nullOrEmptyEx("1234")).mustBeEqual("String <1234> must be null or empty (actual: 4).");
        attest(Is.nullOrEmptyEx((String)null)).mustBeNull();
        attest(Is.nullOrBlankEx("1234")).mustBeEqual("String <1234> must be null or blank (actual 4).");
        attest(Is.nullOrBlankEx((String)null)).mustBeNull();

        attest(Is.emptyEx(new Object[]{1, 2})).mustBeEqual("Array <[1, 2]> must be empty (actual: 2).");
        attest(Is.nullOrEmptyEx(new Object[]{1, 2})).mustBeEqual("Array <[1, 2]> must be null or empty (actual: 2).");
        attest(Is.nullOrEmptyEx((Object[])null)).mustBeNull();
        attest(Is.singletonEx(new Object[]{1, 2})).mustBeEqual("Array <[1, 2]> must be exactly of size 1 (singleton; actual:2)");
        attest(Is.equalEx(new Object[]{1, 2}, null)).mustBeEqual("Array <[1, 2]> must be equal to array <null>.");

        attest(Is.emptyEx(List.of(1, 2))).mustBeEqual("Collection <[1, 2]> must be empty (actual: 2).");
        attest(Is.nullOrEmptyEx(List.of(1, 2))).mustBeEqual("Collection <[1, 2]> must be null or empty (actual: 2).");
        attest(Is.nullOrEmptyEx((Collection) null)).mustBeNull();
        attest(Is.singletonEx(List.of(1, 2))).mustBeEqual("Collection <[1, 2]> must be exactly of size 1 (singleton; actual: 2).");

        attest(Is.emptyEx(new TreeMap<>(Map.of(1, 1, 2, 2)))).mustBeEqual("Map <{1=1, 2=2}> must be empty (actual: 2).");
        attest(Is.nullOrEmptyEx(new TreeMap<>(Map.of(1, 1, 2, 2)))).mustBeEqual("Map <{1=1, 2=2}> must be null or empty (actual: 2).");
        attest(Is.nullOrEmptyEx((Map) null)).mustBeNull();
        attest(Is.singletonEx(new TreeMap<>(Map.of(1, 1, 2, 2)))).mustBeEqual("Map <{1=1, 2=2}> must be exactly of size 1 (singleton; actual: 2).");
    }


}
