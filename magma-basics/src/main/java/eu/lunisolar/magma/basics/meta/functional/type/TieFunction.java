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

package eu.lunisolar.magma.basics.meta.functional.type;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.aType.aInt;
import eu.lunisolar.magma.basics.meta.functional.domain.Codomain;
import eu.lunisolar.magma.basics.meta.functional.domain.Domain3;

/**
 * Function that have assumed special meaning of operands: (target, index, element) -> increment
 * General form: (object, int, *) -> int
 * Function named "LTie*Function" replaces any function "LObjInt*ToIntFunction"
 *
 * @param <T> Supposed collection.
 * @param <E> Supposed element type to put into collection.
 */
public interface TieFunction<T, E extends aType> extends MetaFunction, Codomain<aInt>, Domain3<a<T>, aInt, E> {

    /** Generalized from of tie* method. */
    <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, E> srcAcc3);

}
