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
 * Exact equivalent of input parameters used in LObjLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongPair<T> extends LTuple<Object> 
  {

    int SIZE = 2;


    T first();

    default T value() {
        return first();
    }

    long second();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjLongPair and calculates hash from it. */
    static <T> int argHashCode(T a1,long a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Long.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjLongPair and checks if all values are equal. */
    static <T> boolean argEquals(T a1,long a2, T b1,long b2) {
        return
            Null.equals(a1, b1) && //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjLongPair interface (among others) and their LObjLongPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjLongPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjLongPair are allowed.
            if (!(two instanceof LObjLongPair)) {
                return false;
            }

            LObjLongPair other = (LObjLongPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjLongPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjLongPair are allowed.
            if (!(two instanceof LObjLongPair)) {
                return false;
            }

            LObjLongPair other = (LObjLongPair) two;

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

    interface ComparableObjLongPair<T extends Comparable<? super T>> extends LObjLongPair<T>, Comparable<LObjLongPair<T>> {
        @Override
        default int compareTo(LObjLongPair<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjLongPair<T> implements LObjLongPair<T> {

        @Override
        public boolean equals(Object that) {
            return LObjLongPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjLongPair.argHashCode(first(),second());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjLongPair<T>   {



        SELF first(T first) ; 
        SELF second(long second) ; 

        default SELF setFirst(T first) {
            this.first(first);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setFirstIfArg(T first, LPredicate<T> predicate) {
            if (predicate.test(first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setFirstIfArgNotNull(R arg, LFunction<R,T> func) {
            if ( arg != null ) {
                return this.first(func.apply(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setFirstIf(LPredicate<T> predicate, T first) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setFirstIf(T first, LBiPredicate<T,T> predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setFirstIf(LBiPredicate<T,T> predicate, T first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(long second) {
            this.second(second);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setSecondIfArg(long second, LLongPredicate predicate) {
            if (predicate.test(second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
            if ( arg != null ) {
                return this.second(func.applyAsLong(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setSecondIf(LLongPredicate predicate, long second) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setSecondIf(long second, LBiLongPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setSecondIf(LBiLongPredicate predicate, long second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(0L);
            return (SELF) this;
        }
    }






  public static <T> MutObjLongPair<T> of() { 
      return of(  null ,  0L );
  }
      

  public static <T> MutObjLongPair<T> of(T a1,long a2){
        return new MutObjLongPair(a1,a2);
  }

  public static <T> MutObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjLongPair<T>  extends AbstractObjLongPair<T> implements Mut<T,MutObjLongPair<T>>   {

        private  T first;
        private  long second;

        public MutObjLongPair(T a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjLongPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override long second() {
            return second;
        }

        public @Override MutObjLongPair<T> second(long second)    {
            this.second = second;
            return this;
        }
            














    }






  public static <T extends Comparable<? super T>> MutCompObjLongPair<T> comparableOf() { 
      return comparableOf(  null ,  0L );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjLongPair<T> comparableOf(T a1,long a2){
        return new MutCompObjLongPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> MutCompObjLongPair<T> comparableCopyOf(LObjLongPair<T> tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjLongPair<T extends Comparable<? super T>>  extends AbstractObjLongPair<T> implements ComparableObjLongPair<T>,Mut<T,MutCompObjLongPair<T>>   {

        private  T first;
        private  long second;

        public MutCompObjLongPair(T a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjLongPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override long second() {
            return second;
        }

        public @Override MutCompObjLongPair<T> second(long second)    {
            this.second = second;
            return this;
        }
            














    }







  public static <T> ImmObjLongPair<T> immutableOf(T a1,long a2){
        return new ImmObjLongPair(a1,a2);
  }

  public static <T> ImmObjLongPair<T> immutableCopyOf(LObjLongPair<T> tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjLongPair<T>  extends AbstractObjLongPair<T>    {

        private final T first;
        private final long second;

        public ImmObjLongPair(T a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override long second() {
            return second;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjLongPair<T> immutableComparableOf(T a1,long a2){
        return new ImmCompObjLongPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> ImmCompObjLongPair<T> immutableComparableCopyOf(LObjLongPair<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjLongPair<T extends Comparable<? super T>>  extends AbstractObjLongPair<T> implements ComparableObjLongPair<T>   {

        private final T first;
        private final long second;

        public ImmCompObjLongPair(T a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override long second() {
            return second;
        }



    }



}


