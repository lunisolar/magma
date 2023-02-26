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
 * Exact equivalent of input parameters used in LTieIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiIntTriple<T> extends LTuple<Object> 
  {

    int SIZE = 3;


    T first();

    default T value() {
        return first();
    }

    int second();

    int third();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjBiIntTriple and calculates hash from it. */
    static <T> int argHashCode(T a1,int a2,int a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Integer.hashCode(a2);
            result = prime * result + Integer.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBiIntTriple and checks if all values are equal. */
    static <T> boolean argEquals(T a1,int a2,int a3, T b1,int b2,int b3) {
        return
            Null.equals(a1, b1) && //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjBiIntTriple interface (among others) and their LObjBiIntTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjBiIntTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBiIntTriple are allowed.
            if (!(two instanceof LObjBiIntTriple)) {
                return false;
            }

            LObjBiIntTriple other = (LObjBiIntTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjBiIntTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjBiIntTriple are allowed.
            if (!(two instanceof LObjBiIntTriple)) {
                return false;
            }

            LObjBiIntTriple other = (LObjBiIntTriple) two;

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

    interface ComparableObjBiIntTriple<T extends Comparable<? super T>> extends LObjBiIntTriple<T>, Comparable<LObjBiIntTriple<T>> {
        @Override
        default int compareTo(LObjBiIntTriple<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Integer.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjBiIntTriple<T> implements LObjBiIntTriple<T> {

        @Override
        public boolean equals(Object that) {
            return LObjBiIntTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjBiIntTriple.argHashCode(first(),second(),third());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjBiIntTriple<T>   {



        SELF first(T first) ; 
        SELF second(int second) ; 
        SELF third(int third) ; 

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
            


        default SELF setThird(int third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(int third, LIntPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(int third, LBiIntPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiIntPredicate predicate, int third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(0);
                this.third(0);
            return (SELF) this;
        }
    }






  public static <T> MutObjBiIntTriple<T> of() { 
      return of(  null ,  0 ,  0 );
  }
      

  public static <T> MutObjBiIntTriple<T> of(T a1,int a2,int a3){
        return new MutObjBiIntTriple(a1,a2,a3);
  }

  public static <T> MutObjBiIntTriple<T> copyOf(LObjBiIntTriple<T> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjBiIntTriple<T>  extends AbstractObjBiIntTriple<T> implements Mut<T,MutObjBiIntTriple<T>>   {

        private  T first;
        private  int second;
        private  int third;

        public MutObjBiIntTriple(T a1,int a2,int a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjBiIntTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutObjBiIntTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override int third() {
            return third;
        }

        public @Override MutObjBiIntTriple<T> third(int third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static <T extends Comparable<? super T>> MutCompObjBiIntTriple<T> comparableOf() { 
      return comparableOf(  null ,  0 ,  0 );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjBiIntTriple<T> comparableOf(T a1,int a2,int a3){
        return new MutCompObjBiIntTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> MutCompObjBiIntTriple<T> comparableCopyOf(LObjBiIntTriple<T> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjBiIntTriple<T extends Comparable<? super T>>  extends AbstractObjBiIntTriple<T> implements ComparableObjBiIntTriple<T>,Mut<T,MutCompObjBiIntTriple<T>>   {

        private  T first;
        private  int second;
        private  int third;

        public MutCompObjBiIntTriple(T a1,int a2,int a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjBiIntTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompObjBiIntTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override int third() {
            return third;
        }

        public @Override MutCompObjBiIntTriple<T> third(int third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static <T> ImmObjBiIntTriple<T> immutableOf(T a1,int a2,int a3){
        return new ImmObjBiIntTriple(a1,a2,a3);
  }

  public static <T> ImmObjBiIntTriple<T> immutableCopyOf(LObjBiIntTriple<T> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjBiIntTriple<T>  extends AbstractObjBiIntTriple<T>    {

        private final T first;
        private final int second;
        private final int third;

        public ImmObjBiIntTriple(T a1,int a2,int a3){
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

        public @Override int third() {
            return third;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjBiIntTriple<T> immutableComparableOf(T a1,int a2,int a3){
        return new ImmCompObjBiIntTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> ImmCompObjBiIntTriple<T> immutableComparableCopyOf(LObjBiIntTriple<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjBiIntTriple<T extends Comparable<? super T>>  extends AbstractObjBiIntTriple<T> implements ComparableObjBiIntTriple<T>   {

        private final T first;
        private final int second;
        private final int third;

        public ImmCompObjBiIntTriple(T a1,int a2,int a3){
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

        public @Override int third() {
            return third;
        }



    }



}


