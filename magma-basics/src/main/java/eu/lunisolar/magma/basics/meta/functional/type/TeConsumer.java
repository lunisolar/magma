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
import eu.lunisolar.magma.basics.meta.aType.aVoid;
import eu.lunisolar.magma.basics.meta.functional.IndexedRead;
import eu.lunisolar.magma.basics.meta.functional.SequentialRead;
import eu.lunisolar.magma.basics.meta.functional.domain.Codomain;
import eu.lunisolar.magma.basics.meta.functional.domain.Domain2;

/**
 * @param <T> Supposed collection.
 * @param <E> Supposed element type to put into collection.
 */
public interface TeConsumer<T, E extends aType> extends MetaConsumer, Codomain<aVoid>, Domain2<a<T>, E> {

    /** Generalized from of forEach method. */
    <SRC> T genericForEach(T trg1, IndexedRead<SRC, E> ia, SRC src3);

    /** Generalized from of forEach method. */
    <SRC, I> T genericIterate(T trg1, SequentialRead<SRC, I, E> sa, SRC src3);
}
