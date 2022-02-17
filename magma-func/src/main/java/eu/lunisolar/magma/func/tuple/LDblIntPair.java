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



/**
 * Exact equivalent of input parameters used in LDblIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblIntPair extends LTuple<Number> 
  {

    int SIZE = 2;


    double first();

    default double value() {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LDblIntPair and calculates hash from it. */
    static  int argHashCode(double a1,int a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Double.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LDblIntPair and checks if all values are equal. */
    static  boolean argEquals(double a1,int a2, double b1,int b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LDblIntPair interface (among others) and their LDblIntPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LDblIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LDblIntPair are allowed.
            if (!(two instanceof LDblIntPair)) {
                return false;
            }

            LDblIntPair other = (LDblIntPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LDblIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LDblIntPair are allowed.
            if (!(two instanceof LDblIntPair)) {
                return false;
            }

            LDblIntPair other = (LDblIntPair) two;

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

    interface ComparableDblIntPair extends LDblIntPair, Comparable<LDblIntPair> {
        @Override
        default int compareTo(LDblIntPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractDblIntPair implements LDblIntPair {

        @Override
        public boolean equals(Object that) {
            return LDblIntPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LDblIntPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LDblIntPair   {



        SELF first(double first) ; 
        SELF second(int second) ; 

        default SELF setFirst(double first) {
            this.first(first);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setFirstIfArg(double first, LDblPredicate predicate) {
            if (predicate.test(first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
            if ( arg != null ) {
                return this.first(func.applyAsDbl(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setFirstIf(LDblPredicate predicate, double first) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setFirstIf(double first, LBiDblPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setFirstIf(LBiDblPredicate predicate, double first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(int second) {
            this.second(second);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setSecondIfArg(int second, LIntPredicate predicate) {
            if (predicate.test(second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
            if ( arg != null ) {
                return this.second(func.applyAsInt(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setSecondIf(LIntPredicate predicate, int second) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setSecondIf(int second, LBiIntPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setSecondIf(LBiIntPredicate predicate, int second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(0d);
                this.second(0);
            return (SELF) this;
        }
    }






  public static  MutDblIntPair of() { 
      return of(  0d ,  0 );
  }
      

  public static  MutDblIntPair of(double a1,int a2){
        return new MutDblIntPair(a1,a2);
  }

  public static  MutDblIntPair copyOf(LDblIntPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutDblIntPair  extends AbstractDblIntPair implements Mut<MutDblIntPair>   {

        private  double first;
        private  int second;

        public MutDblIntPair(double a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override double first() {
            return first;
        }

        public @Override MutDblIntPair first(double first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutDblIntPair second(int second)    {
            this.second = second;
            return this;
        }
            














    }






  public static  MutCompDblIntPair comparableOf() { 
      return comparableOf(  0d ,  0 );
  }
      

  public static  MutCompDblIntPair comparableOf(double a1,int a2){
        return new MutCompDblIntPair(a1,a2);
  }

  public static  MutCompDblIntPair comparableCopyOf(LDblIntPair tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompDblIntPair  extends AbstractDblIntPair implements ComparableDblIntPair,Mut<MutCompDblIntPair>   {

        private  double first;
        private  int second;

        public MutCompDblIntPair(double a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override double first() {
            return first;
        }

        public @Override MutCompDblIntPair first(double first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompDblIntPair second(int second)    {
            this.second = second;
            return this;
        }
            














    }







  public static  ImmDblIntPair immutableOf(double a1,int a2){
        return new ImmDblIntPair(a1,a2);
  }

  public static  ImmDblIntPair immutableCopyOf(LDblIntPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmDblIntPair  extends AbstractDblIntPair    {

        private final double first;
        private final int second;

        public ImmDblIntPair(double a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override double first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }







  public static  ImmCompDblIntPair immutableComparableOf(double a1,int a2){
        return new ImmCompDblIntPair(a1,a2);
  }

  public static  ImmCompDblIntPair immutableComparableCopyOf(LDblIntPair tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompDblIntPair  extends AbstractDblIntPair implements ComparableDblIntPair   {

        private final double first;
        private final int second;

        public ImmCompDblIntPair(double a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override double first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }



}


