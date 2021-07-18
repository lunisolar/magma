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
 * Exact equivalent of input parameters used in LByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteSingle extends LTuple<Byte> , Comparable<LByteSingle> 
  {

    int SIZE = 1;


    byte value();

    default byte first() {
        return value();
    }



    @Override default Byte get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default byte getByte(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LByteSingle and calculates hash from it. */
    static  int argHashCode(byte a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Byte.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LByteSingle and checks if all values are equal. */
    static  boolean argEquals(byte a, byte b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LByteSingle interface (among others) and their LByteSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LByteSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LByteSingle are allowed.
            if (!(two instanceof LByteSingle)) {
                return false;
            }

            LByteSingle other = (LByteSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LByteSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LByteSingle are allowed.
            if (!(two instanceof LByteSingle)) {
                return false;
            }

            LByteSingle other = (LByteSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
        });
    }



        
    @Override default Iterator<Byte> iterator() {
        return new Iterator<Byte>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Byte next() {
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
                return getByte(index);
            }
        };
    }
        @Override
        default int compareTo(LByteSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Byte.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractByteSingle extends Number  implements LByteSingle {

        @Override
        public boolean equals(Object that) {
            return LByteSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LByteSingle.argHashCode(value());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LByteSingle   {



        SELF value(byte value) ; 

        default SELF setValue(byte value) {
            this.value(value);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setValueIfArg(byte value, LBytePredicate predicate) {
            if (predicate.test(value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setValueIfArgNotNull(R arg, LToByteFunction<R> func) {
            if ( arg != null ) {
                return this.value(func.applyAsByte(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setValueIf(LBytePredicate predicate, byte value) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setValueIf(byte value, LBiBytePredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setValueIf(LBiBytePredicate predicate, byte value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value((byte)0);
            return (SELF) this;
        }
    }






  public static  MutByteSingle of() { 
      return of(  (byte)0 );
  }
      

  public static  MutByteSingle of(byte a){
        return new MutByteSingle(a);
  }

  public static  MutByteSingle copyOf(LByteSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutByteSingle  extends AbstractByteSingle implements Mut<MutByteSingle>   {

        private  byte value;

        public MutByteSingle(byte a){
            this.value = a;
        }


        public @Override byte value() {
            return value;
        }

        public @Override MutByteSingle value(byte value)    {
            this.value = value;
            return this;
        }
            








    }







  public static  ImmByteSingle immutableOf(byte a){
        return new ImmByteSingle(a);
  }

  public static  ImmByteSingle immutableCopyOf(LByteSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmByteSingle  extends AbstractByteSingle    {

        private final byte value;

        public ImmByteSingle(byte a){
            this.value = a;
        }


        public @Override byte value() {
            return value;
        }



    }



}


