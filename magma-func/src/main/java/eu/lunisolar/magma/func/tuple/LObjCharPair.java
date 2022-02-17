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
 * Exact equivalent of input parameters used in LObjCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjCharPair<T> extends LTuple<Object> 
  {

    int SIZE = 2;


    T first();

    default T value() {
        return first();
    }

    char second();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjCharPair and calculates hash from it. */
    static <T> int argHashCode(T a1,char a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Character.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjCharPair and checks if all values are equal. */
    static <T> boolean argEquals(T a1,char a2, T b1,char b2) {
        return
            Null.equals(a1, b1) && //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjCharPair interface (among others) and their LObjCharPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjCharPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjCharPair are allowed.
            if (!(two instanceof LObjCharPair)) {
                return false;
            }

            LObjCharPair other = (LObjCharPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjCharPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjCharPair are allowed.
            if (!(two instanceof LObjCharPair)) {
                return false;
            }

            LObjCharPair other = (LObjCharPair) two;

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

    interface ComparableObjCharPair<T extends Comparable<? super T>> extends LObjCharPair<T>, Comparable<LObjCharPair<T>> {
        @Override
        default int compareTo(LObjCharPair<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Character.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjCharPair<T> implements LObjCharPair<T> {

        @Override
        public boolean equals(Object that) {
            return LObjCharPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjCharPair.argHashCode(first(),second());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjCharPair<T>   {



        SELF first(T first) ; 
        SELF second(char second) ; 

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
            


        default SELF setSecond(char second) {
            this.second(second);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setSecondIfArg(char second, LCharPredicate predicate) {
            if (predicate.test(second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
            if ( arg != null ) {
                return this.second(func.applyAsChar(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setSecondIf(LCharPredicate predicate, char second) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setSecondIf(char second, LBiCharPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setSecondIf(LBiCharPredicate predicate, char second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second('\u0000');
            return (SELF) this;
        }
    }






  public static <T> MutObjCharPair<T> of() { 
      return of(  null ,  '\u0000' );
  }
      

  public static <T> MutObjCharPair<T> of(T a1,char a2){
        return new MutObjCharPair(a1,a2);
  }

  public static <T> MutObjCharPair<T> copyOf(LObjCharPair<T> tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjCharPair<T>  extends AbstractObjCharPair<T> implements Mut<T,MutObjCharPair<T>>   {

        private  T first;
        private  char second;

        public MutObjCharPair(T a1,char a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjCharPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override char second() {
            return second;
        }

        public @Override MutObjCharPair<T> second(char second)    {
            this.second = second;
            return this;
        }
            














    }






  public static <T extends Comparable<? super T>> MutCompObjCharPair<T> comparableOf() { 
      return comparableOf(  null ,  '\u0000' );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjCharPair<T> comparableOf(T a1,char a2){
        return new MutCompObjCharPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> MutCompObjCharPair<T> comparableCopyOf(LObjCharPair<T> tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjCharPair<T extends Comparable<? super T>>  extends AbstractObjCharPair<T> implements ComparableObjCharPair<T>,Mut<T,MutCompObjCharPair<T>>   {

        private  T first;
        private  char second;

        public MutCompObjCharPair(T a1,char a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjCharPair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override char second() {
            return second;
        }

        public @Override MutCompObjCharPair<T> second(char second)    {
            this.second = second;
            return this;
        }
            














    }







  public static <T> ImmObjCharPair<T> immutableOf(T a1,char a2){
        return new ImmObjCharPair(a1,a2);
  }

  public static <T> ImmObjCharPair<T> immutableCopyOf(LObjCharPair<T> tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjCharPair<T>  extends AbstractObjCharPair<T>    {

        private final T first;
        private final char second;

        public ImmObjCharPair(T a1,char a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override char second() {
            return second;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjCharPair<T> immutableComparableOf(T a1,char a2){
        return new ImmCompObjCharPair(a1,a2);
  }

  public static <T extends Comparable<? super T>> ImmCompObjCharPair<T> immutableComparableCopyOf(LObjCharPair<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjCharPair<T extends Comparable<? super T>>  extends AbstractObjCharPair<T> implements ComparableObjCharPair<T>   {

        private final T first;
        private final char second;

        public ImmCompObjCharPair(T a1,char a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override char second() {
            return second;
        }



    }



}


