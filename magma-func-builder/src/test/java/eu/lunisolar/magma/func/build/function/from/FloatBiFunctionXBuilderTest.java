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

package eu.lunisolar.magma.func.build.function.from;

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
import static eu.lunisolar.magma.func.build.function.from.FloatBiFunctionXBuilder.floatBiFunctionX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class FloatBiFunctionXBuilderTest<R,X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            FloatBiFunctionX function = FloatBiFunctionXBuilder
                .floatBiFunctionX()
                .build();

            function.apply((float)100,(float)100);

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(FloatBiFunctionX.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        FloatBiFunctionX<Integer ,ParseException> function = floatBiFunctionX((FloatBiFunctionX<Integer ,ParseException> f)-> doNothing())
            .addCase(ce -> ce.of((f1,f2) -> f1 == (float)0)
                             .evaluate((f1,f2) -> Integer.valueOf(0)))
            .inCase((f1,f2) -> f1 > 0 && f1 < 10).evaluate((f1,f2) -> Integer.valueOf(1))
            .inCase((f1,f2) -> f1 > 10 && f1 < 20).evaluate((f1,f2) -> Integer.valueOf(2))
            .eventually((f1,f2) -> Integer.valueOf(99))
            .build();


        A.assertThat(function)
            .doesApply((float)0,(float)0).to(a -> a.isEqualTo(Integer.valueOf(0)))
            .doesApply((float)5,(float)5).to(a -> a.isEqualTo(Integer.valueOf(1)))
            .doesApply((float)15,(float)15).to(a -> a.isEqualTo(Integer.valueOf(2)))
            .doesApply((float)10,(float)10).to(a -> a.isEqualTo(Integer.valueOf(99)))
        ;

    }

}
