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
import static eu.lunisolar.magma.func.build.function.from.IntBiFunctionBuilder.intBiFunction;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class IntBiFunctionBuilderTest<R,X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final FunctionalAssertions<ObjectAssert> A = new FunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            IntBiFunction function = IntBiFunctionBuilder
                .intBiFunction()
                .build();

            function.apply((int)100,(int)100);

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(IntBiFunction.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        IntBiFunction<Integer > function = intBiFunction((IntBiFunction<Integer > f)-> doNothing())
            .addCase(ce -> ce.of((i1,i2) -> i1 == 0)
                             .evaluate((i1,i2) -> Integer.valueOf(0)))
            .inCase((i1,i2) -> i1 > 0 && i1 < 10).evaluate((i1,i2) -> Integer.valueOf(1))
            .inCase((i1,i2) -> i1 > 10 && i1 < 20).evaluate((i1,i2) -> Integer.valueOf(2))
            .eventually((i1,i2) -> Integer.valueOf(99))
            .build();

        // int i1,int i2
        // (int)0,(int)0

        A.assertThat(function)
            .doesApply((int)0,(int)0).to(a -> a.isEqualTo(Integer.valueOf(0)))
            .doesApply((int)5,(int)5).to(a -> a.isEqualTo(Integer.valueOf(1)))
            .doesApply((int)15,(int)15).to(a -> a.isEqualTo(Integer.valueOf(2)))
            .doesApply((int)10,(int)10).to(a -> a.isEqualTo(Integer.valueOf(99)));
    }


}
