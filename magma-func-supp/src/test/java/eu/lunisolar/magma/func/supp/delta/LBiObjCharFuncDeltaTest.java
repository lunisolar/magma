/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.delta;

import javax.annotation.Nonnull;  // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.delta.*; // NOSONAR

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("UnusedDeclaration")
public class LBiObjCharFuncDeltaTest<T1,T2,R> {

    private int functionCallCount = 0;
    private final R initialTestValue = (R)Integer.valueOf(1);
    private R testValue = initialTestValue;

    private final R initialLastValue = (R)Integer.valueOf(0);

    private LBiObjCharFuncDelta<T1,T2,R> sut =  LBiObjCharFuncDelta.<T1,T2,R>deltaOf(initialLastValue, (a1,a2,a3) ->{
        functionCallCount++;
        return testValue;
    }, (last, current) -> (R) (Integer) ((Integer)current-(Integer)last));

    @Test
    public void testReturnsLastResult() throws Throwable {

        assertThat(sut.lastValue())
            .isEqualTo(initialLastValue);

        assertThat(sut.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isSameAs(testValue);
        assertThat(functionCallCount).isEqualTo(1);

        assertThat(sut.lastValue())
            .isSameAs(testValue);
        assertThat(functionCallCount).isEqualTo(1);

        testValue = (R)Integer.valueOf(10);

        assertThat(sut.lastValue())
            .isSameAs(initialTestValue);
        assertThat(functionCallCount).isEqualTo(1);

        assertThat(sut.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo((R)Integer.valueOf(9));

        assertThat(functionCallCount).isEqualTo(2);

        assertThat(sut.lastValue())
            .isEqualTo(testValue);

        assertThat(functionCallCount).isEqualTo(2);

    }



}
