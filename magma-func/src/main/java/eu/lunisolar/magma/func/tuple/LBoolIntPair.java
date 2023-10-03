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
 * Exact equivalent of input parameters used in LBoolIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolIntPair extends LTuple<Object> 
  {

    int SIZE = 2;


    boolean first();

    default boolean value() {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBoolIntPair and calculates hash from it. */
    static  int argHashCode(boolean a1,int a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Boolean.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolIntPair and checks if all values are equal. */
    static  boolean argEquals(boolean a1,int a2, boolean b1,int b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBoolIntPair interface (among others) and their LBoolIntPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBoolIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolIntPair are allowed.
            if (!(two instanceof LBoolIntPair)) {
                return false;
            }

            LBoolIntPair other = (LBoolIntPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBoolIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolIntPair are allowed.
            if (!(two instanceof LBoolIntPair)) {
                return false;
            }

            LBoolIntPair other = (LBoolIntPair) two;

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

    interface ComparableBoolIntPair extends LBoolIntPair, Comparable<LBoolIntPair> {
        @Override
        default int compareTo(LBoolIntPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractBoolIntPair implements LBoolIntPair {

        @Override
        public boolean equals(Object that) {
            return LBoolIntPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBoolIntPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LBoolIntPair   {



        SELF first(boolean first) ; 
        SELF second(int second) ; 

        default SELF setFirst(boolean first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(boolean first, LLogicalOperator predicate) {
            if (predicate.apply(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
            if (predicate.apply(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LLogicalBinaryOperator predicate, boolean first) {
            if (predicate.apply(this.first(), first)) {
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
                this.first(false);
                this.second(0);
            return (SELF) this;
        }
    }





  public static  MutBoolIntPair of() {
      return of(  false ,  0 );
  }
      

  public static  MutBoolIntPair of(boolean a1,int a2){
        return new MutBoolIntPair(a1,a2);
  }

  public static  MutBoolIntPair copyOf(LBoolIntPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBoolIntPair  extends AbstractBoolIntPair implements Mut<MutBoolIntPair>   {

        private  boolean first;
        private  int second;

        public MutBoolIntPair(boolean a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override MutBoolIntPair first(boolean first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutBoolIntPair second(int second)    {
            this.second = second;
            return this;
        }
            












    }






  public static  LBoolIntPair immutableOf(boolean a1,int a2){
        return new ImmBoolIntPair(a1,a2);
  }

  public static  LBoolIntPair immutableCopyOf(LBoolIntPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBoolIntPair  extends AbstractBoolIntPair    {

        private final boolean first;
        private final int second;

        public ImmBoolIntPair(boolean a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }



}


