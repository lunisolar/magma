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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.build.predicate;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.NestedException; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR

import static eu.lunisolar.magma.func.Function4U.doNothing;
import static eu.lunisolar.magma.func.build.predicate.BiObjShortPredicateXBuilder.biObjShortPredicateX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class BiObjShortPredicateXBuilderTest<T1,T2,X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            BiObjShortPredicateX function = BiObjShortPredicateXBuilder
                .biObjShortPredicateX()
                .build();

            function.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(short)100);

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(BiObjShortPredicateX.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        BiObjShortPredicateX<Integer ,Integer ,ParseException> function = biObjShortPredicateX((BiObjShortPredicateX<Integer ,Integer ,ParseException> f)-> doNothing())
            .addCase(ce -> ce.of((t1,t2, s) -> t1 == Integer.valueOf(0))
                             .evaluate((t1,t2, s) -> false))
            .inCase((t1,t2, s) -> t1 > 0 && t1 < 10).evaluate((t1,t2, s) -> true)
            .inCase((t1,t2, s) -> t1 > 10 && t1 < 20).evaluate((t1,t2, s) -> true)
            .eventually((t1,t2, s) -> true)
            .build();


        A.assertThat(function)
            .doesTest(Integer.valueOf(0),Integer.valueOf(0),(short)0).to(a -> a.isEqualTo(false))
            .doesTest(Integer.valueOf(5),Integer.valueOf(5),(short)5).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(15),Integer.valueOf(15),(short)15).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(10),Integer.valueOf(10),(short)10).to(a -> a.isEqualTo(true))
        ;

    }

}
