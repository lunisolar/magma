/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.supplier;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import java.util.function.*; //NOSONAR

import static eu.lunisolar.magma.func.build.supplier.LSrtSupplierBuilder.srtSupplier;
import static eu.lunisolar.magma.func.build.supplier.LSrtSupplierBuilder.srtSupplierFrom;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LSrtSupplierBuilderTest{

    @SuppressWarnings("unchecked")
    public static final DefaultAttests<ObjectAssert> A = new DefaultAttests() {
    };

    @Test
    public void testOtherwiseThrow()  {

        assertThatThrownBy(() -> {
            LSrtSupplier function = srtSupplierFrom(b-> b
                .build()
            );

            function.getAsSrt();

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("There is no case configured for the arguments (if any).");
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {


        assertThatThrownBy(() -> {
            LSrtSupplier function = srtSupplierFrom(b-> b
                .withHandling(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
                .build(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
            );

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is already set for this builder.");
    }

    @Test
    public void testHandling()  {

        assertThatThrownBy(() -> {
            LSrtSupplier function = srtSupplierFrom(b -> b
                .otherwise(() -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.getAsSrt();

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild()  {
        final AtomicInteger externalInfluence = new AtomicInteger(0);

        LSrtSupplier function = srtSupplierFrom( b -> b
            .aCase(ce -> ce.of(() -> externalInfluence.get() == 0)
                             .evaluate(() -> (short)0))
            .inCase(() -> externalInfluence.get() > 0 && externalInfluence.get() < 10).evaluate(() -> (short)1)
            .inCase(() -> externalInfluence.get() > 10 && externalInfluence.get() < 20).evaluate(() -> (short)2)
            .otherwise(() -> (short)99)
            .build()
        );


        A.attestSrtSup(function)
            .doesGetAsSrt().when(()->externalInfluence.set(0)).to(a -> a.isEqualTo((short)0))
            .doesGetAsSrt().when(()->externalInfluence.set(5)).to(a -> a.isEqualTo((short)1))
            .doesGetAsSrt().when(()->externalInfluence.set(15)).to(a -> a.isEqualTo((short)2))
            .doesGetAsSrt().when(()->externalInfluence.set(10)).to(a -> a.isEqualTo((short)99))
        ;

    }

}
