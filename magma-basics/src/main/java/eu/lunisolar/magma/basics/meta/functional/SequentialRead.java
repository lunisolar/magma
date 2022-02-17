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

package eu.lunisolar.magma.basics.meta.functional;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.aType.aBool;
import eu.lunisolar.magma.basics.meta.functional.type.OFunction;

/**
 *
 */
@SuppressWarnings("unchecked")
public interface SequentialRead<C, I, E extends aType> extends SizeMeasure<C, E> {

    OFunction<C, a<I>> genericAdapter();

    OFunction<I, aBool> genericTester();

    OFunction<I, ? extends E> genericSupplier();

    default <F extends OFunction<C, a<I>>> F adapter() {
        return (F) genericAdapter();
    }

    default <F extends OFunction<I, aBool>> F tester() {
        return (F) genericTester();
    }

    default <F extends OFunction<I, E>> F supplier() {
        return (F) genericSupplier();
    }
    
}