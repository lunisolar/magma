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

package eu.lunisolar.magma.asserts.func.consumer;

import eu.lunisolar.magma.asserts.func.FuncAttests;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.LQuadConsumer;
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import java.util.function.*; //NOSONAR

@SuppressWarnings("ALL")
public class LQuadConsumerAttestTest<T1,T2,T3,T4> {

    private Integer testValue = 100;
    private AtomicReference<Object> externalEffect = new AtomicReference(null);

    private LQuadConsumer<Integer,Integer,Integer,Integer> function = (a1,a2,a3,a4) ->
            externalEffect.set(testValue);


    private LQuadConsumer<Integer,Integer,Integer,Integer> functionThrowing = (a1,a2,a3,a4) -> {
        throw new UnsupportedOperationException();
    };

    @Test
    public void testAssertPositive() throws ParseException {

        FuncAttests.attestQuadCons(function)
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        FuncAttests.attestQuadCons(function)
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, 2));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Should evaluate without problem: null")
    public void testAssertThrowsUnexpected() throws ParseException {

        FuncAttests.attestQuadCons(functionThrowing)
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, 1));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        FuncAttests.attestQuadCons(functionThrowing)
         .doesAccept(100,100,100,100).withException(a -> a
                   .mustEx(Be::exactlyInstanceOfEx, UnsupportedOperationException.class)
                   .mustEx(Have::noMsgEx));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestQuadCons(function)
         .inAllFollowingCases(()-> {
            recurringAssertsCalls.incrementAndGet();
            Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue);
         })
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue))
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Recurring assertion failed: .*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestQuadCons(function)
         .inAllFollowingCases(()-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                Checks.attest(externalEffect.get()).mustEx(Be::equalEx, 0);
            }
         })
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue))
         .doesAccept(100,100,100,100)
            .soThat(()->Checks.attest(externalEffect.get()).mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

}
