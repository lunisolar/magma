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

package eu.lunisolar.magma.basics.meta.functional;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.functional.type.TeConsumer;

/**
 *
 */
@SuppressWarnings("unchecked")
public interface SequentialWrite<C, E extends aType> {

    TeConsumer<C, ? super E> genericConsumer();

    default <F extends TeConsumer<C, E>> F consumer() {
        return (F) genericConsumer();
    }

}