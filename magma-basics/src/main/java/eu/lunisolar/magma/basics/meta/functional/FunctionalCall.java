/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.meta.functional.domain.Codomain;
import eu.lunisolar.magma.basics.meta.functional.domain.Domain;

/**  */
@FunctionalInterface
public interface FunctionalCall<D extends LTuple, C, X extends Throwable> extends Domain<D>, Codomain<C> {

    C call(D domain) throws X;

}