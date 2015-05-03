/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.asserts.function.to;

import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.NestedException; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

@SuppressWarnings("ALL")
public class ToIntBiFunctionXAssertTest<T1,T2,X extends ParseException> {

    private int testValue = (int)100;

    @SuppressWarnings("unchecked") public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    private ToIntBiFunctionX<Integer ,Integer ,X> function = ToIntBiFunctionX.lX((t1,t2) -> {
            return testValue;
    });

    private ToIntBiFunctionX<Integer ,Integer ,X> functionThrowing = ToIntBiFunctionX.lX((t1,t2) -> {
        throw new UnsupportedOperationException();
    });

    @Test
    public void testAssertPositive() throws ParseException {

        A.assertThat(function)
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to(a -> a.isEqualTo(testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        A.assertThat(function)
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to( a -> a.isEqualTo(2));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Should evaluate without problem.")
    public void testAssertThrowsUnexpected() throws ParseException {

        A.assertThat(functionThrowing)
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to( a -> a.isEqualTo(1));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        A.assertThat(functionThrowing)
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81)).withException(a -> a
                   .isExactlyInstanceOf(UnsupportedOperationException.class)
                   .hasMessage(null));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.assertThat(function)
         .recurringAsserts(a-> {
            recurringAssertsCalls.incrementAndGet();
            a.isEqualTo(testValue);
         })
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to(a -> a.isEqualTo(testValue))
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to(a -> a.isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s).*Recurring assertion failed.*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.assertThat(function)
         .recurringAsserts(a-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                a.isEqualTo(0);
            }
         })
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to(a -> a.isEqualTo(testValue))
         .doesApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81))
            .to(a -> a.isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

}
