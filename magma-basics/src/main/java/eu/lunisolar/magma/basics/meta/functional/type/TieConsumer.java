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

package eu.lunisolar.magma.basics.meta.functional.type;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.aType.aBool;
import eu.lunisolar.magma.basics.meta.aType.aInt;
import eu.lunisolar.magma.basics.meta.aType.aVoid;
import eu.lunisolar.magma.basics.meta.functional.domain.Codomain;
import eu.lunisolar.magma.basics.meta.functional.domain.Domain3;

/**
 * Function that have assumed special meaning of operands: (target, index, element) -> void
 * General form: (object, int, *) -> void
 * Function named "LTie*Consumer" replaces any function "LObjInt*Consumer"
 *
 * @param <T> Supposed collection.
 * @param <E> Supposed element type to put into collection.
 */
public interface TieConsumer<T, E extends aType> extends MetaConsumer, Codomain<aVoid>, Domain3<a<T>, aInt, E> {

    /** Generalized from of tie* method. */
    <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, E> srcAcc3);

    /** Generalized from of ti* method. */
    <SRC> int genericTieForEach(int sStart, int tStart, T trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, E> srcAcc3);

    TieFunction<T, E> toTieFunction();

}
