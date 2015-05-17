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

package eu.lunisolar.magma.func.build.function.to;

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
import static eu.lunisolar.magma.func.build.function.to.LToShortBiFunctionXBuilder.toShortBiFunctionX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LToShortBiFunctionXBuilderTest<T1,T2,X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Exception {

        try {
            LToShortBiFunctionX function = LToShortBiFunctionXBuilder
                .toShortBiFunctionX()
                .build();

            function.applyAsShort((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));

            fail("No exception were thrown.");
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LToShortBiFunctionX.DESCRIPTION);

        }
    }

    @Test
    public void testBuild() throws Exception {

        LToShortBiFunctionX<Integer ,Integer ,ParseException> function = toShortBiFunctionX((LToShortBiFunctionX<Integer ,Integer ,ParseException> f)-> doNothing())
            .addCase(ce -> ce.of((t1,t2) -> t1 == Integer.valueOf(0))
                             .evaluate((t1,t2) -> (short)0))
            .inCase((t1,t2) -> t1 > 0 && t1 < 10).evaluate((t1,t2) -> (short)1)
            .inCase((t1,t2) -> t1 > 10 && t1 < 20).evaluate((t1,t2) -> (short)2)
            .eventually((t1,t2) -> (short)99)
            .build();


        A.assertThat(function)
            .doesApplyAsShort(Integer.valueOf(0),Integer.valueOf(0)).to(a -> a.isEqualTo((short)0))
            .doesApplyAsShort(Integer.valueOf(5),Integer.valueOf(5)).to(a -> a.isEqualTo((short)1))
            .doesApplyAsShort(Integer.valueOf(15),Integer.valueOf(15)).to(a -> a.isEqualTo((short)2))
            .doesApplyAsShort(Integer.valueOf(10),Integer.valueOf(10)).to(a -> a.isEqualTo((short)99))
        ;

    }

}