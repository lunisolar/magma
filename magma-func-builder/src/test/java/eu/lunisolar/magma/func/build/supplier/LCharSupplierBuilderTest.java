/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

import static eu.lunisolar.magma.func.build.supplier.LCharSupplierBuilder.charSupplier;
import static eu.lunisolar.magma.func.build.supplier.LCharSupplierBuilder.charSupplierFrom;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LCharSupplierBuilderTest{

    @SuppressWarnings("unchecked")
    public static final DefaultAttests<ObjectAssert> A = new DefaultAttests() {
    };

    @Test
    public void testOtherwiseThrow()  {

        assertThatThrownBy(() -> {
            LCharSupplier function = charSupplierFrom(b-> b
                .build()
            );

            function.getAsChar();

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("There is no case configured for the arguments (if any).");
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {


        assertThatThrownBy(() -> {
            LCharSupplier function = charSupplierFrom(b-> b
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
            LCharSupplier function = charSupplierFrom(b -> b
                .otherwise(() -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.getAsChar();

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild()  {
        final AtomicInteger externalInfluence = new AtomicInteger(0);

        LCharSupplier function = charSupplierFrom( b -> b
            .aCase(ce -> ce.of(() -> externalInfluence.get() == 0)
                             .evaluate(() -> '\u0000'))
            .inCase(() -> externalInfluence.get() > 0 && externalInfluence.get() < 10).evaluate(() -> '\u0001')
            .inCase(() -> externalInfluence.get() > 10 && externalInfluence.get() < 20).evaluate(() -> '\u0002')
            .otherwise(() -> '\u0099')
            .build()
        );


        A.attestCharSup(function)
            .doesGetAsChar().when(()->externalInfluence.set(0)).to(a -> a.isEqualTo('\u0000'))
            .doesGetAsChar().when(()->externalInfluence.set(5)).to(a -> a.isEqualTo('\u0001'))
            .doesGetAsChar().when(()->externalInfluence.set(15)).to(a -> a.isEqualTo('\u0002'))
            .doesGetAsChar().when(()->externalInfluence.set(10)).to(a -> a.isEqualTo('\u0099'))
        ;

    }

}
