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
 * Exact equivalent of input parameters used in LObjBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolPair<T> extends LTuple<Object> 
  {

    int SIZE = 2;


    T first();

    default T value() {
        return first();
    }

    boolean second();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjBoolPair and calculates hash from it. */
    static <T> int argHashCode(T a1,boolean a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Boolean.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBoolPair and checks if all values are equal. */
    static <T> boolean argEquals(T a1,boolean a2, T b1,boolean b2) {
        return
            Null.equals(a1, b1) && //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjBoolPair interface (among others) and their LObjBoolPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjBoolPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBoolPair are allowed.
            if (!(two instanceof LObjBoolPair)) {
                return false;
            }

            LObjBoolPair other = (LObjBoolPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjBoolPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBoolPair are allowed.
            if (!(two instanceof LObjBoolPair)) {
                return false;
            }

            LObjBoolPair other = (LObjBoolPair) two;

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

    interface ComparableObjBoolPair<T extends Comparable<? super T>> extends LObjBoolPair<T>, Comparable<LObjBoolPair<T>> {
        @Override
        default int compareTo(LObjBoolPair<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjBoolPair<T> implements LObjBoolPair<T> {

        @Override
        public boolean equals(Object that) {
            return LObjBoolPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjBoolPair.argHashCode(first(),second());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjBoolPair<T>   {



        SELF first(T first) ; 
        SELF second(boolean second) ; 

        default SELF setFirst(T first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(T first, LPredicate<T> predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(T first, LBiPredicate<T,T> predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiPredicate<T,T> predicate, T first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(boolean second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(boolean second, LLogicalOperator predicate) {
            if (predicate.apply(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
            if (predicate.apply(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LLogicalBinaryOperator predicate, boolean second) {
            if (predicate.apply(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(false);
            return (SELF) this;
        }
    }






  public static <T> MutObjBoolPair<T> of() { 
      return of(  null ,  false );
  }
      

  public static <T> MutObjBoolPair<T> of(T a1,boolean a2){
        return new MutObjBoolPair(a1,a2);
  }

  public static <T> MutObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjBoolPair<T>  extends AbstractObjBoolPair<T> implements Mut<T,MutObjBoolPair<T>>   {

        private  T first;
        private  boolean second;

        public MutObjBoolPair(T a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjBoolPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override boolean second() {
            return second;
        }

        public @Override MutObjBoolPair<T> second(boolean second)    {
            this.second = second;
            return this;
        }
            












    }






  public static <T extends Comparable<? super T>> MutCompObjBoolPair<T> comparableOf() { 
      return comparableOf(  null ,  false );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjBoolPair<T> comparableOf(T a1,boolean a2){
        return new MutCompObjBoolPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> MutCompObjBoolPair<T> comparableCopyOf(LObjBoolPair<T> tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjBoolPair<T extends Comparable<? super T>>  extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T>,Mut<T,MutCompObjBoolPair<T>>   {

        private  T first;
        private  boolean second;

        public MutCompObjBoolPair(T a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjBoolPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override boolean second() {
            return second;
        }

        public @Override MutCompObjBoolPair<T> second(boolean second)    {
            this.second = second;
            return this;
        }
            












    }







  public static <T> ImmObjBoolPair<T> immutableOf(T a1,boolean a2){
        return new ImmObjBoolPair(a1,a2);
  }

  public static <T> ImmObjBoolPair<T> immutableCopyOf(LObjBoolPair<T> tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjBoolPair<T>  extends AbstractObjBoolPair<T>    {

        private final T first;
        private final boolean second;

        public ImmObjBoolPair(T a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override boolean second() {
            return second;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjBoolPair<T> immutableComparableOf(T a1,boolean a2){
        return new ImmCompObjBoolPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> ImmCompObjBoolPair<T> immutableComparableCopyOf(LObjBoolPair<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjBoolPair<T extends Comparable<? super T>>  extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T>   {

        private final T first;
        private final boolean second;

        public ImmCompObjBoolPair(T a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override boolean second() {
            return second;
        }



    }



}


