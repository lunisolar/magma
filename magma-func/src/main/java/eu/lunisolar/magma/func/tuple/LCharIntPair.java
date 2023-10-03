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
 * Exact equivalent of input parameters used in LCharIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharIntPair extends LTuple<Object> 
  {

    int SIZE = 2;


    char first();

    default char value() {
        return first();
    }

    int second();



    @Override default Object get(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LCharIntPair and calculates hash from it. */
    static  int argHashCode(char a1,int a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Character.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LCharIntPair and checks if all values are equal. */
    static  boolean argEquals(char a1,int a2, char b1,int b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LCharIntPair interface (among others) and their LCharIntPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LCharIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharIntPair are allowed.
            if (!(two instanceof LCharIntPair)) {
                return false;
            }

            LCharIntPair other = (LCharIntPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LCharIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharIntPair are allowed.
            if (!(two instanceof LCharIntPair)) {
                return false;
            }

            LCharIntPair other = (LCharIntPair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Object> iterator() {
        return new Iterator<Object>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Object next() {
                index++;
                return get(index);
            }
        };
    }

    interface ComparableCharIntPair extends LCharIntPair, Comparable<LCharIntPair> {
        @Override
        default int compareTo(LCharIntPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractCharIntPair implements LCharIntPair {

        @Override
        public boolean equals(Object that) {
            return LCharIntPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LCharIntPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LCharIntPair   {



        SELF first(char first) ; 
        SELF second(int second) ; 

        default SELF setFirst(char first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(char first, LCharPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(char first, LBiCharPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiCharPredicate predicate, char first) {
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
                this.first('\u0000');
                this.second(0);
            return (SELF) this;
        }
    }





  public static  MutCharIntPair of() {
      return of(  '\u0000' ,  0 );
  }
      

  public static  MutCharIntPair of(char a1,int a2){
        return new MutCharIntPair(a1,a2);
  }

  public static  MutCharIntPair copyOf(LCharIntPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutCharIntPair  extends AbstractCharIntPair implements Mut<MutCharIntPair>   {

        private  char first;
        private  int second;

        public MutCharIntPair(char a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override char first() {
            return first;
        }

        public @Override MutCharIntPair first(char first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCharIntPair second(int second)    {
            this.second = second;
            return this;
        }
            












    }






  public static  LCharIntPair immutableOf(char a1,int a2){
        return new ImmCharIntPair(a1,a2);
  }

  public static  LCharIntPair immutableCopyOf(LCharIntPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmCharIntPair  extends AbstractCharIntPair    {

        private final char first;
        private final int second;

        public ImmCharIntPair(char a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override char first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }



}


