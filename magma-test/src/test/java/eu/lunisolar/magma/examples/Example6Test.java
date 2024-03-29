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

import eu.lunisolar.magma.asserts.func.FuncAttests;
import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.LToByteFunction;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.asserts.func.FuncAttests.attestFunc;
import static eu.lunisolar.magma.asserts.func.FuncAttests.attestToByteFunc;

public class Example6Test {

    public static final LFunction<Integer, Integer> potentiallyThrowing = LFunction.func(Example6Test::potentiallyThrowing);

    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i;
    }

    @Test
    public void example1() throws CheckedException {

        LFunction<Integer, Integer> after = i -> -i;
        LToByteFunction<Integer> func = potentiallyThrowing
                .then(after)
                .thenToDbl(i -> i)
                .thenToFlt(d -> (float) d + 1)
                .thenToLong(f -> (long) f + 1)
                .thenToInt(l -> (int) l + 1)
                .thenToSrt(i -> (short) (i + 1))
                .thenToByte(s -> (byte) (s + 1));

        attestToByteFunc(func)
                .doesApplyAsByte(1).toEqualTo((byte) 4)
                .doesApplyAsByte(10).toEqualTo((byte) -5)
                .doesApplyAsByte(3000).toEqualTo((byte) 77);

        LFunction<Integer, String> func2 = func.then(Byte::toString);

        attestFunc(func2)
                .doesApply(1).toEqualTo("4")
                .doesApply(10).toEqualTo("-5")
                .doesApply(3000).toEqualTo("77");

    }

}
