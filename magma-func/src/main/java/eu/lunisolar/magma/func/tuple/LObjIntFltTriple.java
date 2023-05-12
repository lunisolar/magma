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
 * Exact equivalent of input parameters used in LTieFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntFltTriple<T> extends LTuple<Object> 
  {

    int SIZE = 3;


    T first();

    default T value() {
        return first();
    }

    int second();

    float third();



    @Override default Object get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }


    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjIntFltTriple and calculates hash from it. */
    static <T> int argHashCode(T a1,int a2,float a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Integer.hashCode(a2);
            result = prime * result + Float.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntFltTriple and checks if all values are equal. */
    static <T> boolean argEquals(T a1,int a2,float a3, T b1,int b2,float b3) {
        return
            Null.equals(a1, b1) && //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjIntFltTriple interface (among others) and their LObjIntFltTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjIntFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntFltTriple are allowed.
            if (!(two instanceof LObjIntFltTriple)) {
                return false;
            }

            LObjIntFltTriple other = (LObjIntFltTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjIntFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntFltTriple are allowed.
            if (!(two instanceof LObjIntFltTriple)) {
                return false;
            }

            LObjIntFltTriple other = (LObjIntFltTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

    interface ComparableObjIntFltTriple<T extends Comparable<? super T>> extends LObjIntFltTriple<T>, Comparable<LObjIntFltTriple<T>> {
        @Override
        default int compareTo(LObjIntFltTriple<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjIntFltTriple<T> implements LObjIntFltTriple<T> {

        @Override
        public boolean equals(Object that) {
            return LObjIntFltTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjIntFltTriple.argHashCode(first(),second(),third());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(first());
                sb.append(',');
                sb.append(second());
                sb.append(',');
                sb.append(third());
            sb.append(')');
            return sb.toString();
        }

    }





    /**
     * Mutable tuple.
     */

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjIntFltTriple<T>   {



        SELF first(T first) ; 
        SELF second(int second) ; 
        SELF third(float third) ; 

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
            


        default SELF setThird(float third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(float third, LFltPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(float third, LBiFltPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiFltPredicate predicate, float third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(0);
                this.third(0f);
            return (SELF) this;
        }
    }






  public static <T> MutObjIntFltTriple<T> of() { 
      return of(  null ,  0 ,  0f );
  }
      

  public static <T> MutObjIntFltTriple<T> of(T a1,int a2,float a3){
        return new MutObjIntFltTriple(a1,a2,a3);
  }

  public static <T> MutObjIntFltTriple<T> copyOf(LObjIntFltTriple<T> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjIntFltTriple<T>  extends AbstractObjIntFltTriple<T> implements Mut<T,MutObjIntFltTriple<T>>   {

        private  T first;
        private  int second;
        private  float third;

        public MutObjIntFltTriple(T a1,int a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjIntFltTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutObjIntFltTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override float third() {
            return third;
        }

        public @Override MutObjIntFltTriple<T> third(float third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static <T extends Comparable<? super T>> MutCompObjIntFltTriple<T> comparableOf() { 
      return comparableOf(  null ,  0 ,  0f );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjIntFltTriple<T> comparableOf(T a1,int a2,float a3){
        return new MutCompObjIntFltTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> MutCompObjIntFltTriple<T> comparableCopyOf(LObjIntFltTriple<T> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjIntFltTriple<T extends Comparable<? super T>>  extends AbstractObjIntFltTriple<T> implements ComparableObjIntFltTriple<T>,Mut<T,MutCompObjIntFltTriple<T>>   {

        private  T first;
        private  int second;
        private  float third;

        public MutCompObjIntFltTriple(T a1,int a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjIntFltTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompObjIntFltTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override float third() {
            return third;
        }

        public @Override MutCompObjIntFltTriple<T> third(float third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static <T> ImmObjIntFltTriple<T> immutableOf(T a1,int a2,float a3){
        return new ImmObjIntFltTriple(a1,a2,a3);
  }

  public static <T> ImmObjIntFltTriple<T> immutableCopyOf(LObjIntFltTriple<T> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjIntFltTriple<T>  extends AbstractObjIntFltTriple<T>    {

        private final T first;
        private final int second;
        private final float third;

        public ImmObjIntFltTriple(T a1,int a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override int second() {
            return second;
        }

        public @Override float third() {
            return third;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjIntFltTriple<T> immutableComparableOf(T a1,int a2,float a3){
        return new ImmCompObjIntFltTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> ImmCompObjIntFltTriple<T> immutableComparableCopyOf(LObjIntFltTriple<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjIntFltTriple<T extends Comparable<? super T>>  extends AbstractObjIntFltTriple<T> implements ComparableObjIntFltTriple<T>   {

        private final T first;
        private final int second;
        private final float third;

        public ImmCompObjIntFltTriple(T a1,int a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override int second() {
            return second;
        }

        public @Override float third() {
            return third;
        }



    }



}


