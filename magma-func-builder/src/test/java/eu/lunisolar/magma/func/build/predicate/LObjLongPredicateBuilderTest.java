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

package eu.lunisolar.magma.func.build.predicate;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
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
import static eu.lunisolar.magma.func.build.predicate.LObjLongPredicateBuilder.objLongPredicate;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LObjLongPredicateBuilderTest<T,X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            LObjLongPredicate function = LObjLongPredicateBuilder
                .objLongPredicate()
                .build();

            function.test((T)Integer.valueOf(100),(long)100);

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LObjLongPredicate.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        LObjLongPredicate<Integer > function = objLongPredicate((LObjLongPredicate<Integer > f)-> doNothing())
            .addCase(ce -> ce.of((t, l) -> t == Integer.valueOf(0))
                             .evaluate((t, l) -> false))
            .inCase((t, l) -> t > 0 && t < 10).evaluate((t, l) -> true)
            .inCase((t, l) -> t > 10 && t < 20).evaluate((t, l) -> true)
            .eventually((t, l) -> true)
            .build();


        A.assertThat(function)
            .doesTest(Integer.valueOf(0),(long)0).to(a -> a.isEqualTo(false))
            .doesTest(Integer.valueOf(5),(long)5).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(15),(long)15).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(10),(long)10).to(a -> a.isEqualTo(true))
        ;

    }

}