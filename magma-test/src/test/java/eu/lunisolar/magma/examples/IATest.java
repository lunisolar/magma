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

import eu.lunisolar.magma.basics.meta.aType.aInt;
import eu.lunisolar.magma.basics.meta.functional.IndexedRead;
import eu.lunisolar.magma.basics.meta.functional.type.OFunction;
import eu.lunisolar.magma.basics.meta.functional.type.OiFunction;
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.function.to.LOiToIntFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

@SuppressWarnings({"unchecked", "rawtypes"}) public class IATest {

    @Test void toList_array() {
        var list = IA.toList(IA.array(), new Integer[]{1, 2, 3, 4});
        var target = list.stream().collect(Collectors.toList());

        list.set(2, 33);

        attest(target).mustAEx(P::containExactlyEx, 1, 2, 3, 4);
        attest(list).mustAEx(P::containExactlyEx, 1, 2, 33, 4);
    }


    @Test void toList_intArray() {
        attestThrownBy(() -> {
            IA.toList((IndexedRead) new IndexedRead<Object, aInt>(){

                @Override public OFunction<Object, aInt> genericSizeFunc() {
                    return LToIntFunction.toIntFunc(c-> -1);
                }
                @Override public OiFunction<Object, aInt> genericGetter() {
                    return LOiToIntFunction.oiToIntFunc((c, index)-> -1);
                }
            }, IntStream.of(1, 2, 3, 4));
        })
                .mustBeExactlyInstanceOf(IllegalArgumentException.class)
                .mustEx(Have::msgEqualEx, "Argument [indexedRead] is not compatible (e.g. supports only primitive types).");
    }
}
