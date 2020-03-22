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
public class DefaultAttestsTest {



    @Test
    public void withinOptionalCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractOptionalAssert> specialized = initial.withinOptionalCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractOptionalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBigDecimalCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractBigDecimalAssert> specialized = initial.withinBigDecimalCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBigDecimalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractBooleanAssert> specialized = initial.withinBooleanCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractBooleanArrayAssert> specialized = initial.withinBooleanArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractByteAssert> specialized = initial.withinByteCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractByteArrayAssert> specialized = initial.withinByteArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharacterCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractCharacterAssert> specialized = initial.withinCharacterCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharacterAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractCharArrayAssert> specialized = initial.withinCharArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinClassCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractClassAssert> specialized = initial.withinClassCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractClassAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinComparableCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractComparableAssert> specialized = initial.withinComparableCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractComparableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIterableCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractIterableAssert> specialized = initial.withinIterableCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIterableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIteratorCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractIteratorAssert> specialized = initial.withinIteratorCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIteratorAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractDoubleAssert> specialized = initial.withinDoubleCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractDoubleArrayAssert> specialized = initial.withinDoubleArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinPathCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractPathAssert> specialized = initial.withinPathCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractPathAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinInputStreamCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractInputStreamAssert> specialized = initial.withinInputStreamCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractInputStreamAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractFloatAssert> specialized = initial.withinFloatCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractFloatArrayAssert> specialized = initial.withinFloatArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntegerCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractIntegerAssert> specialized = initial.withinIntegerCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntegerAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractIntArrayAssert> specialized = initial.withinIntArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinListCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractListAssert> specialized = initial.withinListCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractListAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractLongAssert> specialized = initial.withinLongCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractLongArrayAssert> specialized = initial.withinLongArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinMapCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractMapAssert> specialized = initial.withinMapCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractMapAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractShortAssert> specialized = initial.withinShortCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractShortArrayAssert> specialized = initial.withinShortArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharSequenceCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractCharSequenceAssert> specialized = initial.withinCharSequenceCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinStringCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractCharSequenceAssert> specialized = initial.withinStringCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDateCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractDateAssert> specialized = initial.withinDateCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinZonedDateTimeCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractZonedDateTimeAssert> specialized = initial.withinZonedDateTimeCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractZonedDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateTimeCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractLocalDateTimeAssert> specialized = initial.withinLocalDateTimeCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalTimeCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractLocalTimeAssert> specialized = initial.withinLocalTimeCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractLocalDateAssert> specialized = initial.withinLocalDateCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinThrowableCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractThrowableAssert> specialized = initial.withinThrowableCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractThrowableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

@Test
    public void withinTArrayCodomain() {

        DefaultAttests initial = new DefaultAttests(){
        };

        DefaultAttests<AbstractObjectArrayAssert> specialized = initial.withinTArrayCodomain();

        specialized.attestFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractObjectArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

}