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

package eu.lunisolar.magma.asserts;

import javax.annotation.Nonnull;  // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.math.*;
import java.time.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.std.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.action.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.supplier.*; // NOSONAR
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
//import eu.lunisolar.magma.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

import org.testng.annotations.Test;

@SuppressWarnings("ALL")
public class DefaultMagmaAssertionsTest {



    @Test
    public void withinOptionalCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractOptionalAssert> specialized = initial.withinOptionalCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractOptionalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBigDecimalCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractBigDecimalAssert> specialized = initial.withinBigDecimalCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBigDecimalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractBooleanAssert> specialized = initial.withinBooleanCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractBooleanArrayAssert> specialized = initial.withinBooleanArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractByteAssert> specialized = initial.withinByteCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractByteArrayAssert> specialized = initial.withinByteArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharacterCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractCharacterAssert> specialized = initial.withinCharacterCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharacterAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractCharArrayAssert> specialized = initial.withinCharArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinClassCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractClassAssert> specialized = initial.withinClassCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractClassAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinComparableCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractComparableAssert> specialized = initial.withinComparableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractComparableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIterableCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractIterableAssert> specialized = initial.withinIterableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIterableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIteratorCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractIteratorAssert> specialized = initial.withinIteratorCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIteratorAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractDoubleAssert> specialized = initial.withinDoubleCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractDoubleArrayAssert> specialized = initial.withinDoubleArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinPathCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractPathAssert> specialized = initial.withinPathCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractPathAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinInputStreamCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractInputStreamAssert> specialized = initial.withinInputStreamCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractInputStreamAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractFloatAssert> specialized = initial.withinFloatCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractFloatArrayAssert> specialized = initial.withinFloatArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntegerCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractIntegerAssert> specialized = initial.withinIntegerCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntegerAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractIntArrayAssert> specialized = initial.withinIntArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinListCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractListAssert> specialized = initial.withinListCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractListAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractLongAssert> specialized = initial.withinLongCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractLongArrayAssert> specialized = initial.withinLongArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinMapCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractMapAssert> specialized = initial.withinMapCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractMapAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractShortAssert> specialized = initial.withinShortCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractShortArrayAssert> specialized = initial.withinShortArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharSequenceCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractCharSequenceAssert> specialized = initial.withinCharSequenceCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinStringCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractCharSequenceAssert> specialized = initial.withinStringCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDateCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractDateAssert> specialized = initial.withinDateCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinZonedDateTimeCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractZonedDateTimeAssert> specialized = initial.withinZonedDateTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractZonedDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateTimeCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractLocalDateTimeAssert> specialized = initial.withinLocalDateTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalTimeCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractLocalTimeAssert> specialized = initial.withinLocalTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractLocalDateAssert> specialized = initial.withinLocalDateCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinThrowableCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractThrowableAssert> specialized = initial.withinThrowableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractThrowableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

@Test
    public void withinTArrayCodomain() {

        DefaultMagmaAssertions initial = new DefaultMagmaAssertions(){
        };

        DefaultMagmaAssertions<AbstractObjectArrayAssert> specialized = initial.withinTArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractObjectArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

}