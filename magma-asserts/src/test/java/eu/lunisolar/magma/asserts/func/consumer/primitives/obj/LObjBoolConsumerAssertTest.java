/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts.func.consumer.primitives.obj;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.asserts.DefaultAttests;
import eu.lunisolar.magma.func.consumer.primitives.obj.LObjBoolConsumer;
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; //NOSONAR

@SuppressWarnings("ALL")
public class LObjBoolConsumerAssertTest<T> {

    private Integer testValue = 100;
    private AtomicReference<Object> externalEffect = new AtomicReference(null);

    @SuppressWarnings("unchecked") public static final DefaultAttests<ObjectAssert> A = new DefaultAttests() {
    };

    private LObjBoolConsumer<Integer> function = (a1,a2) ->
            externalEffect.set(testValue);


    private LObjBoolConsumer<Integer> functionThrowing = (a1,a2) -> {
        throw new UnsupportedOperationException();
    };

    @Test
    public void testAssertPositive() throws ParseException {

        A.attestObjBoolCons(function)
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        A.attestObjBoolCons(function)
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(2));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Case .* should evaluate without problem.")
    public void testAssertThrowsUnexpected() throws ParseException {

        A.attestObjBoolCons(functionThrowing)
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(1));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        A.attestObjBoolCons(functionThrowing)
         .doesAccept(100,true).withException(a -> a
                   .isExactlyInstanceOf(UnsupportedOperationException.class)
                   .hasMessage(null));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.attestObjBoolCons(function)
         .inAllFollowingCases(()-> {
            recurringAssertsCalls.incrementAndGet();
            assertThat(externalEffect.get()).isEqualTo(testValue);
         })
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(testValue))
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s).*Recurring assertion failed.*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.attestObjBoolCons(function)
         .inAllFollowingCases(()-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                assertThat(externalEffect.get()).isEqualTo(0);
            }
         })
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(testValue))
         .doesAccept(100,true)
            .soThat(()->assertThat(externalEffect.get()).isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

}
