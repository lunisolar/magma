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

public abstract class aType {

    private aType() {
    }

    abstract Class clazz();

    abstract boolean isPrimitive();

    public static class a<T> extends aType {
        @Override Class clazz() {
            return Object.class;
        }

        @Override boolean isPrimitive() {
            return false;
        }
    }

    public static class aInt extends aType {
        @Override Class clazz() {
            return Integer.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aLong extends aType {
        @Override Class clazz() {
            return Long.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aDouble extends aType {
        @Override Class clazz() {
            return Double.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aByte extends aType {
        @Override Class clazz() {
            return Byte.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aShort extends aType {
        @Override Class clazz() {
            return Short.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aChar extends aType {
        @Override Class clazz() {
            return Character.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aFloat extends aType {
        @Override Class clazz() {
            return Float.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aBool extends aType {
        @Override Class clazz() {
            return Boolean.TYPE;
        }

        @Override boolean isPrimitive() {
            return true;
        }
    }

    public static class aVoid extends aType {
        @Override Class clazz() {
            return Void.class;
        }

        @Override boolean isPrimitive() {
            return false;
        }
    }
}
