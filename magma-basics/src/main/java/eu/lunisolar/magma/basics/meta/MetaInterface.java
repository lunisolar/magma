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

package eu.lunisolar.magma.basics.meta;

public interface MetaInterface {

    boolean isThrowing();

    /**
     * Meta interface for all non throwing interfaces. _Definition_ would be that non-throwing is the special case where exceptions supposed to be of
     * RuntimeException.
     */
    interface NonThrowing extends Throwing<RuntimeException> {

        default boolean isThrowing() {
            return false;
        }
    }

    /** Meta interface for all throwing interfaces. */
    interface Throwing<X> extends MetaInterface{

        default boolean isThrowing() {
            return true;
        }
    }
}
