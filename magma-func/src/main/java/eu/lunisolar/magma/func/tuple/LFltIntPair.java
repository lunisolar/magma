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
 * Exact equivalent of input parameters used in LFltIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltIntPair extends LTuple<Number> 
  {

    int SIZE = 2;


    float first();

    default float value() {
        return first();
    }

    int second();



    @Override default Number get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            default: throw new NoSuchElementException();
        }
    }


    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LFltIntPair and calculates hash from it. */
    static  int argHashCode(float a1,int a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Float.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LFltIntPair and checks if all values are equal. */
    static  boolean argEquals(float a1,int a2, float b1,int b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LFltIntPair interface (among others) and their LFltIntPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LFltIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltIntPair are allowed.
            if (!(two instanceof LFltIntPair)) {
                return false;
            }

            LFltIntPair other = (LFltIntPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LFltIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltIntPair are allowed.
            if (!(two instanceof LFltIntPair)) {
                return false;
            }

            LFltIntPair other = (LFltIntPair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Number> iterator() {
        return new Iterator<Number>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Number next() {
                index++;
                return get(index);
            }
        };
    }

    interface ComparableFltIntPair extends LFltIntPair, Comparable<LFltIntPair> {
        @Override
        default int compareTo(LFltIntPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractFltIntPair implements LFltIntPair {

        @Override
        public boolean equals(Object that) {
            return LFltIntPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LFltIntPair.argHashCode(first(),second());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(first());
                sb.append(',');
                sb.append(second());
            sb.append(')');
            return sb.toString();
        }

    }





    /**
     * Mutable tuple.
     */

     interface  Mut<SELF extends Mut<SELF>>  extends LFltIntPair   {



        SELF first(float first) ; 
        SELF second(int second) ; 

        default SELF setFirst(float first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(float first, LFltPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(float first, LBiFltPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiFltPredicate predicate, float first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(int second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(int second, LIntPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(int second, LBiIntPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiIntPredicate predicate, int second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(0f);
                this.second(0);
            return (SELF) this;
        }
    }






  public static  MutFltIntPair of() { 
      return of(  0f ,  0 );
  }
      

  public static  MutFltIntPair of(float a1,int a2){
        return new MutFltIntPair(a1,a2);
  }

  public static  MutFltIntPair copyOf(LFltIntPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutFltIntPair  extends AbstractFltIntPair implements Mut<MutFltIntPair>   {

        private  float first;
        private  int second;

        public MutFltIntPair(float a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override float first() {
            return first;
        }

        public @Override MutFltIntPair first(float first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutFltIntPair second(int second)    {
            this.second = second;
            return this;
        }
            












    }






  public static  MutCompFltIntPair comparableOf() { 
      return comparableOf(  0f ,  0 );
  }
      

  public static  MutCompFltIntPair comparableOf(float a1,int a2){
        return new MutCompFltIntPair(a1,a2);
  }

  public static  MutCompFltIntPair comparableCopyOf(LFltIntPair tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompFltIntPair  extends AbstractFltIntPair implements ComparableFltIntPair,Mut<MutCompFltIntPair>   {

        private  float first;
        private  int second;

        public MutCompFltIntPair(float a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override float first() {
            return first;
        }

        public @Override MutCompFltIntPair first(float first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompFltIntPair second(int second)    {
            this.second = second;
            return this;
        }
            












    }







  public static  ImmFltIntPair immutableOf(float a1,int a2){
        return new ImmFltIntPair(a1,a2);
  }

  public static  ImmFltIntPair immutableCopyOf(LFltIntPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmFltIntPair  extends AbstractFltIntPair    {

        private final float first;
        private final int second;

        public ImmFltIntPair(float a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override float first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }







  public static  ImmCompFltIntPair immutableComparableOf(float a1,int a2){
        return new ImmCompFltIntPair(a1,a2);
  }

  public static  ImmCompFltIntPair immutableComparableCopyOf(LFltIntPair tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompFltIntPair  extends AbstractFltIntPair implements ComparableFltIntPair   {

        private final float first;
        private final int second;

        public ImmCompFltIntPair(float a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override float first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }



}


