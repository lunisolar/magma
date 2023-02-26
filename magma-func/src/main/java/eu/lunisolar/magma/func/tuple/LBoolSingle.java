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
 * Exact equivalent of input parameters used in LBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSingle extends LTuple<Boolean> , Comparable<LBoolSingle> 
  {

    int SIZE = 1;


    boolean value();

    default boolean first() {
        return value();
    }



    @Override default Boolean get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default boolean getBoolean(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBoolSingle and calculates hash from it. */
    static  int argHashCode(boolean a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Boolean.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolSingle and checks if all values are equal. */
    static  boolean argEquals(boolean a, boolean b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBoolSingle interface (among others) and their LBoolSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBoolSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolSingle are allowed.
            if (!(two instanceof LBoolSingle)) {
                return false;
            }

            LBoolSingle other = (LBoolSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBoolSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolSingle are allowed.
            if (!(two instanceof LBoolSingle)) {
                return false;
            }

            LBoolSingle other = (LBoolSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
        });
    }



        
    @Override default Iterator<Boolean> iterator() {
        return new Iterator<Boolean>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Boolean next() {
                index++;
                return get(index);
            }
        };
    }

        @Override
        default int compareTo(LBoolSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Boolean.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractBoolSingle implements LBoolSingle {

        @Override
        public boolean equals(Object that) {
            return LBoolSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBoolSingle.argHashCode(value());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(value());
            sb.append(')');
            return sb.toString();
        }

    }





    /**
     * Mutable tuple.
     */

     interface  Mut<SELF extends Mut<SELF>>  extends LBoolSingle   {



        SELF value(boolean value) ; 

        default SELF setValue(boolean value) {
            this.value(value);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setValueIf(boolean value, LLogicalOperator predicate) {
            if (predicate.apply(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setValueIf(boolean value, LLogicalBinaryOperator predicate) {
            if (predicate.apply(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setValueIf(LLogicalBinaryOperator predicate, boolean value) {
            if (predicate.apply(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value(false);
            return (SELF) this;
        }

    
    }






  public static  MutBoolSingle of() { 
      return of(  false );
  }
      

  public static  MutBoolSingle of(boolean a){
        return new MutBoolSingle(a);
  }

  public static  MutBoolSingle copyOf(LBoolSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBoolSingle  extends AbstractBoolSingle implements Mut<MutBoolSingle>   {

        private  boolean value;

        public MutBoolSingle(boolean a){
            this.value = a;
        }


        public @Override boolean value() {
            return value;
        }

        public @Override MutBoolSingle value(boolean value)    {
            this.value = value;
            return this;
        }
            







    }







  public static  ImmBoolSingle immutableOf(boolean a){
        return new ImmBoolSingle(a);
  }

  public static  ImmBoolSingle immutableCopyOf(LBoolSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBoolSingle  extends AbstractBoolSingle    {

        private final boolean value;

        public ImmBoolSingle(boolean a){
            this.value = a;
        }


        public @Override boolean value() {
            return value;
        }



    }






  public static  AtomicBoolSingle atomicOf() { 
      return atomicOf(  false );
  }
      

  public static  AtomicBoolSingle atomicOf(boolean a){
        return new AtomicBoolSingle(a);
  }

  public static  AtomicBoolSingle atomicCopyOf(LBoolSingle tuple) {
        return atomicOf(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

    final  class  AtomicBoolSingle  extends AtomicBoolean implements Mut<AtomicBoolSingle>   {


        public AtomicBoolSingle(boolean a){
            set(a);
        }


        public @Override boolean value() {
            return get();
        }

        public @Override AtomicBoolSingle value(boolean value)    {
                set( value);
                return this;
        }
            








    

        @Override
        public boolean equals(Object that) {
            return LBoolSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBoolSingle.argHashCode(value());
        }
        
    }



}


