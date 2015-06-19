/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.consumer.primitives.obj;

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
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR

import static eu.lunisolar.magma.func.Function4U.doNothing;
import static eu.lunisolar.magma.func.build.consumer.primitives.obj.LBiObjByteConsumerXBuilder.biObjByteConsumerX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LBiObjByteConsumerXBuilderTest<T1,T2,X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Throwable {

        assertThatThrownBy(() -> {
            LBiObjByteConsumerX function = LBiObjByteConsumerXBuilder.biObjByteConsumerX()
                .build();

            function.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(byte)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LBiObjByteConsumerX.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws Throwable {


        assertThatThrownBy(() -> {
            LBiObjByteConsumerX function = LBiObjByteConsumerXBuilder.biObjByteConsumerX()
                .withHandling(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new))
                .build(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new));

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is allready set for this builder.");
    }

    @Test
    public void testHandling() throws Throwable {

        assertThatThrownBy(() -> {
            LBiObjByteConsumerX function = LBiObjByteConsumerXBuilder.biObjByteConsumerX()
                .eventually((t1,t2, b) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(byte)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws Throwable {
        final AtomicInteger externalEffect = new AtomicInteger(0);

        LBiObjByteConsumerX<Integer ,Integer ,ParseException> function = biObjByteConsumerX((LBiObjByteConsumerX<Integer ,Integer ,ParseException> f)-> doNothing())
            .addCase(ce -> ce.of((t1,t2, b) -> t1 == Integer.valueOf(0))
                             .evaluate((t1,t2, b) -> externalEffect.set(0)))
            .inCase((t1,t2, b) -> t1 > 0 && t1 < 10).evaluate((t1,t2, b) -> externalEffect.set(1))
            .inCase((t1,t2, b) -> t1 > 10 && t1 < 20).evaluate((t1,t2, b) -> externalEffect.set(2))
            .eventually((t1,t2, b) -> externalEffect.set(99))
            .build();


        A.assertThat(function)
            .doesAccept(Integer.valueOf(0),Integer.valueOf(0),(byte)0).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(0)))
            .doesAccept(Integer.valueOf(5),Integer.valueOf(5),(byte)5).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(1)))
            .doesAccept(Integer.valueOf(15),Integer.valueOf(15),(byte)15).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(2)))
            .doesAccept(Integer.valueOf(10),Integer.valueOf(10),(byte)10).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(99)))
        ;

    }

}
