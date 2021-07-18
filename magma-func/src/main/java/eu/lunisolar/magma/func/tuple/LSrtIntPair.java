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
 * Exact equivalent of input parameters used in LSrtIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtIntPair extends LTuple<Number> 
  {

    int SIZE = 2;


    short first();

    default short value() {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LSrtIntPair and calculates hash from it. */
    static  int argHashCode(short a1,int a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Short.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtIntPair and checks if all values are equal. */
    static  boolean argEquals(short a1,int a2, short b1,int b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LSrtIntPair interface (among others) and their LSrtIntPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LSrtIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtIntPair are allowed.
            if (!(two instanceof LSrtIntPair)) {
                return false;
            }

            LSrtIntPair other = (LSrtIntPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LSrtIntPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtIntPair are allowed.
            if (!(two instanceof LSrtIntPair)) {
                return false;
            }

            LSrtIntPair other = (LSrtIntPair) two;

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

    interface ComparableSrtIntPair extends LSrtIntPair, Comparable<LSrtIntPair> {
        @Override
        default int compareTo(LSrtIntPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractSrtIntPair implements LSrtIntPair {

        @Override
        public boolean equals(Object that) {
            return LSrtIntPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LSrtIntPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LSrtIntPair   {



        SELF first(short first) ; 
        SELF second(int second) ; 

        default SELF setFirst(short first) {
            this.first(first);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setFirstIfArg(short first, LSrtPredicate predicate) {
            if (predicate.test(first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
            if ( arg != null ) {
                return this.first(func.applyAsSrt(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setFirstIf(LSrtPredicate predicate, short first) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setFirstIf(short first, LBiSrtPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setFirstIf(LBiSrtPredicate predicate, short first) {
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
                this.first((short)0);
                this.second(0);
            return (SELF) this;
        }
    }






  public static  MutSrtIntPair of() { 
      return of(  (short)0 ,  0 );
  }
      

  public static  MutSrtIntPair of(short a1,int a2){
        return new MutSrtIntPair(a1,a2);
  }

  public static  MutSrtIntPair copyOf(LSrtIntPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutSrtIntPair  extends AbstractSrtIntPair implements Mut<MutSrtIntPair>   {

        private  short first;
        private  int second;

        public MutSrtIntPair(short a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override MutSrtIntPair first(short first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutSrtIntPair second(int second)    {
            this.second = second;
            return this;
        }
            














    }






  public static  MutCompSrtIntPair comparableOf() { 
      return comparableOf(  (short)0 ,  0 );
  }
      

  public static  MutCompSrtIntPair comparableOf(short a1,int a2){
        return new MutCompSrtIntPair(a1,a2);
  }

  public static  MutCompSrtIntPair comparableCopyOf(LSrtIntPair tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompSrtIntPair  extends AbstractSrtIntPair implements ComparableSrtIntPair,Mut<MutCompSrtIntPair>   {

        private  short first;
        private  int second;

        public MutCompSrtIntPair(short a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override MutCompSrtIntPair first(short first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompSrtIntPair second(int second)    {
            this.second = second;
            return this;
        }
            














    }







  public static  ImmSrtIntPair immutableOf(short a1,int a2){
        return new ImmSrtIntPair(a1,a2);
  }

  public static  ImmSrtIntPair immutableCopyOf(LSrtIntPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmSrtIntPair  extends AbstractSrtIntPair    {

        private final short first;
        private final int second;

        public ImmSrtIntPair(short a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }







  public static  ImmCompSrtIntPair immutableComparableOf(short a1,int a2){
        return new ImmCompSrtIntPair(a1,a2);
  }

  public static  ImmCompSrtIntPair immutableComparableCopyOf(LSrtIntPair tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompSrtIntPair  extends AbstractSrtIntPair implements ComparableSrtIntPair   {

        private final short first;
        private final int second;

        public ImmCompSrtIntPair(short a1,int a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override int second() {
            return second;
        }



    }



}


