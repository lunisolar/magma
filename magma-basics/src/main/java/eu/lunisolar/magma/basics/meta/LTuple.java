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

package eu.lunisolar.magma.basics.meta;

import java.util.*;

public interface LTuple<T> extends Cloneable, Iterable<T> {

    int size();

    final class Void implements LTuple<java.lang.Void> {

        private static final Iterator<java.lang.Void> VOID_ITERATOR = new Iterator<java.lang.Void>() {
            @Override public boolean hasNext() {
                return false;
            }

            @Override public java.lang.Void next() {
                throw new NoSuchElementException();
            }
        };

        public static LTuple.Void INSTANCE = LTuple.Void.Instance.VOID.instance();

        @Override public Iterator<java.lang.Void> iterator() {
            return VOID_ITERATOR;
        }

        @Override public int size() {
            return 0;
        }

        private enum Instance {
            VOID {
                public LTuple.Void instance() {
                    return new LTuple.Void();
                }
            };

            public abstract LTuple.Void instance();
        }

        @Override protected Object clone() throws CloneNotSupportedException {
            return this;
        }
    }

}
