/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.basics.meta.aType.aInt;
import eu.lunisolar.magma.basics.meta.functional.SequentialRead;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SATest {

    @Test void toIterator_stream() {

        var target = new ArrayList<>();

        var iterator = SA.toIterator(SA.stream(), Stream.of(1, 2, 3, 4));
        iterator.forEachRemaining(target::add);

        attest(target).mustAEx(P::containExactlyEx, 1, 2, 3, 4);
    }

    @Test void toIterator_list() {

        var target = new ArrayList<>();

        var iterator = SA.toIterator(SA.collection(), List.of(1, 2, 3, 4));
        iterator.forEachRemaining(target::add);

        attest(target).mustAEx(P::containExactlyEx, 1, 2, 3, 4);
    }

    @Test void toIterator_intStream() {

        var target = new ArrayList<>();

        // Some SequentialRead instances are using iterator that is actually implementing Iterator<E> interface.
        // Thus calling toIterator and casting the SequentialRead might work.  
        var iterator = SA.toIterator((SA) SA.intStream(), IntStream.of(1, 2, 3, 4));
        iterator.forEachRemaining(target::add);

        attest(target).mustAEx(P::containExactlyEx, 1, 2, 3, 4);
    }

    @Test void toIterator_intArray() {
        attestThrownBy(() -> {
            SA.toIterator((SequentialRead) new SequentialRead<IntStream, Object, aInt>() {
                @Override public LFunction<IntStream, Object> genericAdapter() {
                    return LFunction.func(c -> c);
                }
                @Override public LPredicate<Object> genericTester() {
                    return LPredicate.pred(c -> true);
                }
                @Override public LToIntFunction genericSupplier() {
                    return LToIntFunction.toIntFunc(c -> -1);
                }
                @Override public LToIntFunction genericSizeFunc() {
                    return LToIntFunction.toIntFunc(c -> -1);
                }
            }, IntStream.of(1, 2, 3, 4));
        })
                .mustBeExactlyInstanceOf(IllegalArgumentException.class)
                .mustEx(Have::msgEqualEx, "Argument [sequentialRead] is not compatible (e.g. supports only primitive types).");
    }
}
