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
import java.util.concurrent.atomic.*;
import java.lang.invoke.*;



/**
 * Exact equivalent of input parameters used in LIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntSingle extends LTuple<Integer> , Comparable<LIntSingle> 
  {

    int SIZE = 1;


    int value();

    default int first() {
        return value();
    }



    @Override default Integer get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default int getInt(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LIntSingle and calculates hash from it. */
    static  int argHashCode(int a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Integer.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LIntSingle and checks if all values are equal. */
    static  boolean argEquals(int a, int b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LIntSingle interface (among others) and their LIntSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LIntSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LIntSingle are allowed.
            if (!(two instanceof LIntSingle)) {
                return false;
            }

            LIntSingle other = (LIntSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LIntSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LIntSingle are allowed.
            if (!(two instanceof LIntSingle)) {
                return false;
            }

            LIntSingle other = (LIntSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
        });
    }



        
    @Override default Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Integer next() {
                index++;
                return get(index);
            }
        };
    }


    default PrimitiveIterator.OfInt intIterator() {
        return new PrimitiveIterator.OfInt() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public int nextInt() {
                index++;
                return getInt(index);
            }
        };
    }
        @Override
        default int compareTo(LIntSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Integer.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractIntSingle extends Number  implements LIntSingle {

        @Override
        public boolean equals(Object that) {
            return LIntSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LIntSingle.argHashCode(value());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LIntSingle   {



        SELF value(int value) ; 

        default SELF setValue(int value) {
            this.value(value);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setValueIf(int value, LIntPredicate predicate) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setValueIf(int value, LBiIntPredicate predicate) {
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setValueIf(LBiIntPredicate predicate, int value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value(0);
            return (SELF) this;
        }

    default SELF add(int a1) {
        return value( (value()+a1));
    }

    default SELF sub(int a1) {
        return value( (value()-a1));
    }

    default SELF inc() {
        return add(1);
    }

    default int incAndGet() {
        return inc().value();
    }

    default int getAndInc() {
        int v = value();
        inc();
        return v;
    }

    default SELF dec() {
        return sub(1);
    }

    default int decAndGet() {
        return dec().value();
    }

    default int getAndDec() {
        int v = value();
        dec();
        return v;
    }
    
    }






  public static  MutIntSingle of() { 
      return of(  0 );
  }
      

  public static  MutIntSingle of(int a){
        return new MutIntSingle(a);
  }

  public static  MutIntSingle copyOf(LIntSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutIntSingle  extends AbstractIntSingle implements Mut<MutIntSingle>   {

        private  int value;

        public MutIntSingle(int a){
            this.value = a;
        }


        public @Override int value() {
            return value;
        }

        public @Override MutIntSingle value(int value)    {
            this.value = value;
            return this;
        }
            







    }







  public static  ImmIntSingle immutableOf(int a){
        return new ImmIntSingle(a);
  }

  public static  ImmIntSingle immutableCopyOf(LIntSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmIntSingle  extends AbstractIntSingle    {

        private final int value;

        public ImmIntSingle(int a){
            this.value = a;
        }


        public @Override int value() {
            return value;
        }



    }






  public static  AtomicIntSingle atomicOf() { 
      return atomicOf(  0 );
  }
      

  public static  AtomicIntSingle atomicOf(int a){
        return new AtomicIntSingle(a);
  }

  public static  AtomicIntSingle atomicCopyOf(LIntSingle tuple) {
        return atomicOf(tuple.value());
  }

  public static  Mut<?> of(boolean atomic, int a){
      return atomic? atomicOf(a) : of(a);
  }


    /**
     * Mutable, non-comparable tuple.
     */

    final  class  AtomicIntSingle  extends AtomicInteger implements Mut<AtomicIntSingle>   {


        public AtomicIntSingle(int a){
            set(a);
        }


        public @Override int value() {
            return get();
        }

        public @Override AtomicIntSingle value(int value)    {
                set( value);
                return this;
        }
            








    public AtomicIntSingle add(


        int a1) {
        addAndGet(a1);
        return this;
    }

    public AtomicIntSingle sub(int a1) {
        addAndGet(-a1);
        return this;
    }

    public AtomicIntSingle inc() {
        incrementAndGet();
        return this;
    }

    public int incAndGet() {
        return incrementAndGet();
    }

    public int getAndInc() {
        return getAndIncrement();
    }

    public AtomicIntSingle dec() {
        decrementAndGet();
        return this;
    }

    public int decAndGet() {
        return decrementAndGet();
    }

    public int getAndDec() {
        return getAndDecrement();
    }
    

        @Override
        public boolean equals(Object that) {
            return LIntSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LIntSingle.argHashCode(value());
        }
        
    }



}


