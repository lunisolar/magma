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
 * Exact equivalent of input parameters used in LObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBytePair<T> extends LTuple<Object> 
  {

    int SIZE = 2;


    T first();

    default T value() {
        return first();
    }

    byte second();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjBytePair and calculates hash from it. */
    static <T> int argHashCode(T a1,byte a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Byte.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBytePair and checks if all values are equal. */
    static <T> boolean argEquals(T a1,byte a2, T b1,byte b2) {
        return
            Null.equals(a1, b1) && //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjBytePair interface (among others) and their LObjBytePair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjBytePair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBytePair are allowed.
            if (!(two instanceof LObjBytePair)) {
                return false;
            }

            LObjBytePair other = (LObjBytePair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjBytePair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBytePair are allowed.
            if (!(two instanceof LObjBytePair)) {
                return false;
            }

            LObjBytePair other = (LObjBytePair) two;

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

    interface ComparableObjBytePair<T extends Comparable<? super T>> extends LObjBytePair<T>, Comparable<LObjBytePair<T>> {
        @Override
        default int compareTo(LObjBytePair<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Byte.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjBytePair<T> implements LObjBytePair<T> {

        @Override
        public boolean equals(Object that) {
            return LObjBytePair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjBytePair.argHashCode(first(),second());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjBytePair<T>   {



        SELF first(T first) ; 
        SELF second(byte second) ; 

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
            


        default SELF setSecond(byte second) {
            this.second(second);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setSecondIfArg(byte second, LBytePredicate predicate) {
            if (predicate.test(second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setSecondIfArgNotNull(R arg, LToByteFunction<R> func) {
            if ( arg != null ) {
                return this.second(func.applyAsByte(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setSecondIf(LBytePredicate predicate, byte second) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setSecondIf(byte second, LBiBytePredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setSecondIf(LBiBytePredicate predicate, byte second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second((byte)0);
            return (SELF) this;
        }
    }






  public static <T> MutObjBytePair<T> of() { 
      return of(  null ,  (byte)0 );
  }
      

  public static <T> MutObjBytePair<T> of(T a1,byte a2){
        return new MutObjBytePair(a1,a2);
  }

  public static <T> MutObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjBytePair<T>  extends AbstractObjBytePair<T> implements Mut<T,MutObjBytePair<T>>   {

        private  T first;
        private  byte second;

        public MutObjBytePair(T a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjBytePair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override byte second() {
            return second;
        }

        public @Override MutObjBytePair<T> second(byte second)    {
            this.second = second;
            return this;
        }
            














    }






  public static <T extends Comparable<? super T>> MutCompObjBytePair<T> comparableOf() { 
      return comparableOf(  null ,  (byte)0 );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjBytePair<T> comparableOf(T a1,byte a2){
        return new MutCompObjBytePair(a1,a2);
  }

  public static <T extends Comparable<? super T>> MutCompObjBytePair<T> comparableCopyOf(LObjBytePair<T> tuple) {
        return comparableOf(tuple.first(), tuple.second());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjBytePair<T extends Comparable<? super T>>  extends AbstractObjBytePair<T> implements ComparableObjBytePair<T>,Mut<T,MutCompObjBytePair<T>>   {

        private  T first;
        private  byte second;

        public MutCompObjBytePair(T a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjBytePair<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override byte second() {
            return second;
        }

        public @Override MutCompObjBytePair<T> second(byte second)    {
            this.second = second;
            return this;
        }
            














    }







  public static <T> ImmObjBytePair<T> immutableOf(T a1,byte a2){
        return new ImmObjBytePair(a1,a2);
  }

  public static <T> ImmObjBytePair<T> immutableCopyOf(LObjBytePair<T> tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjBytePair<T>  extends AbstractObjBytePair<T>    {

        private final T first;
        private final byte second;

        public ImmObjBytePair(T a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override byte second() {
            return second;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjBytePair<T> immutableComparableOf(T a1,byte a2){
        return new ImmCompObjBytePair(a1,a2);
  }

  public static <T extends Comparable<? super T>> ImmCompObjBytePair<T> immutableComparableCopyOf(LObjBytePair<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjBytePair<T extends Comparable<? super T>>  extends AbstractObjBytePair<T> implements ComparableObjBytePair<T>   {

        private final T first;
        private final byte second;

        public ImmCompObjBytePair(T a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T first() {
            return first;
        }

        public @Override byte second() {
            return second;
        }



    }



}


