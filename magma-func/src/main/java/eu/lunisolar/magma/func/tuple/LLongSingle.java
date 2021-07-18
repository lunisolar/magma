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

package eu.lunisolar.magma.func.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.*;
import eu.lunisolar.magma.func.*;
import eu.lunisolar.magma.func.consumer.*;  ;
import eu.lunisolar.magma.func.consumer.primitives.bi.*;
import eu.lunisolar.magma.func.consumer.primitives.tri.*;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.function.from.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;
import java.util.stream.*;



/**
 * Exact equivalent of input parameters used in LLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongSingle extends LTuple<Long> , Comparable<LLongSingle> 
  {

    int SIZE = 1;


    long value();

    default long first() {
        return value();
    }



    @Override default Long get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default long getLong(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LLongSingle and calculates hash from it. */
    static  int argHashCode(long a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Long.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LLongSingle and checks if all values are equal. */
    static  boolean argEquals(long a, long b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LLongSingle interface (among others) and their LLongSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LLongSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LLongSingle are allowed.
            if (!(two instanceof LLongSingle)) {
                return false;
            }

            LLongSingle other = (LLongSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LLongSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LLongSingle are allowed.
            if (!(two instanceof LLongSingle)) {
                return false;
            }

            LLongSingle other = (LLongSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
        });
    }



        
    @Override default Iterator<Long> iterator() {
        return new Iterator<Long>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Long next() {
                index++;
                return get(index);
            }
        };
    }


    default PrimitiveIterator.OfLong longIterator() {
        return new PrimitiveIterator.OfLong() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public long nextLong() {
                index++;
                return getLong(index);
            }
        };
    }
        @Override
        default int compareTo(LLongSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Long.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractLongSingle extends Number  implements LLongSingle {

        @Override
        public boolean equals(Object that) {
            return LLongSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LLongSingle.argHashCode(value());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(value());
            sb.append(')');
            return sb.toString();
        }


        @Override public byte byteValue() {
            return (byte)value();
        }

        @Override public short shortValue() {
            return (short)value();
        }

        @Override public int intValue() {
            return (int)value();
        }

        @Override public long longValue() {
            return (long)value();
        }

        @Override public float floatValue() {
            return (float)value();
        }

        @Override public double doubleValue() {
            return (double)value();
        }
    }





    /**
     * Mutable tuple.
     */

     interface  Mut<SELF extends Mut<SELF>>  extends LLongSingle   {



        SELF value(long value) ; 

        default SELF setValue(long value) {
            this.value(value);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setValueIfArg(long value, LLongPredicate predicate) {
            if (predicate.test(value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setValueIfArgNotNull(R arg, LToLongFunction<R> func) {
            if ( arg != null ) {
                return this.value(func.applyAsLong(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setValueIf(LLongPredicate predicate, long value) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setValueIf(long value, LBiLongPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setValueIf(LBiLongPredicate predicate, long value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value(0L);
            return (SELF) this;
        }
    }






  public static  MutLongSingle of() { 
      return of(  0L );
  }
      

  public static  MutLongSingle of(long a){
        return new MutLongSingle(a);
  }

  public static  MutLongSingle copyOf(LLongSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutLongSingle  extends AbstractLongSingle implements Mut<MutLongSingle>   {

        private  long value;

        public MutLongSingle(long a){
            this.value = a;
        }


        public @Override long value() {
            return value;
        }

        public @Override MutLongSingle value(long value)    {
            this.value = value;
            return this;
        }
            








    }







  public static  ImmLongSingle immutableOf(long a){
        return new ImmLongSingle(a);
  }

  public static  ImmLongSingle immutableCopyOf(LLongSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmLongSingle  extends AbstractLongSingle    {

        private final long value;

        public ImmLongSingle(long a){
            this.value = a;
        }


        public @Override long value() {
            return value;
        }



    }



}


