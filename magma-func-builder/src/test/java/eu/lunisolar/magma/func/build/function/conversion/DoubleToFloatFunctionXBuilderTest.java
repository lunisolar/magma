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

package eu.lunisolar.magma.func.build.function.conversion;

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
import static eu.lunisolar.magma.func.build.function.conversion.DoubleToFloatFunctionXBuilder.doubleToFloatFunctionX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class DoubleToFloatFunctionXBuilderTest<X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            DoubleToFloatFunctionX function = DoubleToFloatFunctionXBuilder
                .doubleToFloatFunctionX()
                .build();

            function.applyAsFloat((double)100);

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(DoubleToFloatFunctionX.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        DoubleToFloatFunctionX<ParseException> function = doubleToFloatFunctionX((DoubleToFloatFunctionX<ParseException> f)-> doNothing())
            .addCase(ce -> ce.of((d) -> d == (double)0)
                             .evaluate((d) -> (float)0))
            .inCase((d) -> d > 0 && d < 10).evaluate((d) -> (float)1)
            .inCase((d) -> d > 10 && d < 20).evaluate((d) -> (float)2)
            .eventually((d) -> (float)99)
            .build();


        A.assertThat(function)
            .doesApplyAsFloat((double)0).to(a -> a.isEqualTo((float)0))
            .doesApplyAsFloat((double)5).to(a -> a.isEqualTo((float)1))
            .doesApplyAsFloat((double)15).to(a -> a.isEqualTo((float)2))
            .doesApplyAsFloat((double)10).to(a -> a.isEqualTo((float)99))
        ;

    }

}
