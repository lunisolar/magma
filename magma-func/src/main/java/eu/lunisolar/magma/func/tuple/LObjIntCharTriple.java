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
 * Exact equivalent of input parameters used in LTieCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntCharTriple<T> extends LTuple<Object> 
  {

    int SIZE = 3;


    T first();

    default T value() {
        return first();
    }

    int second();

    char third();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjIntCharTriple and calculates hash from it. */
    static <T> int argHashCode(T a1,int a2,char a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Integer.hashCode(a2);
            result = prime * result + Character.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntCharTriple and checks if all values are equal. */
    static <T> boolean argEquals(T a1,int a2,char a3, T b1,int b2,char b3) {
        return
            Null.equals(a1, b1) && //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjIntCharTriple interface (among others) and their LObjIntCharTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjIntCharTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntCharTriple are allowed.
            if (!(two instanceof LObjIntCharTriple)) {
                return false;
            }

            LObjIntCharTriple other = (LObjIntCharTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjIntCharTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntCharTriple are allowed.
            if (!(two instanceof LObjIntCharTriple)) {
                return false;
            }

            LObjIntCharTriple other = (LObjIntCharTriple) two;

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

    interface ComparableObjIntCharTriple<T extends Comparable<? super T>> extends LObjIntCharTriple<T>, Comparable<LObjIntCharTriple<T>> {
        @Override
        default int compareTo(LObjIntCharTriple<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjIntCharTriple<T> implements LObjIntCharTriple<T> {

        @Override
        public boolean equals(Object that) {
            return LObjIntCharTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjIntCharTriple.argHashCode(first(),second(),third());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjIntCharTriple<T>   {



        SELF first(T first) ; 
        SELF second(int second) ; 
        SELF third(char third) ; 

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
            


        default SELF setThird(char third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(char third, LCharPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(char third, LBiCharPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiCharPredicate predicate, char third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(0);
                this.third('\u0000');
            return (SELF) this;
        }
    }






  public static <T> MutObjIntCharTriple<T> of() { 
      return of(  null ,  0 ,  '\u0000' );
  }
      

  public static <T> MutObjIntCharTriple<T> of(T a1,int a2,char a3){
        return new MutObjIntCharTriple(a1,a2,a3);
  }

  public static <T> MutObjIntCharTriple<T> copyOf(LObjIntCharTriple<T> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjIntCharTriple<T>  extends AbstractObjIntCharTriple<T> implements Mut<T,MutObjIntCharTriple<T>>   {

        private  T first;
        private  int second;
        private  char third;

        public MutObjIntCharTriple(T a1,int a2,char a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjIntCharTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutObjIntCharTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override char third() {
            return third;
        }

        public @Override MutObjIntCharTriple<T> third(char third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static <T extends Comparable<? super T>> MutCompObjIntCharTriple<T> comparableOf() { 
      return comparableOf(  null ,  0 ,  '\u0000' );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjIntCharTriple<T> comparableOf(T a1,int a2,char a3){
        return new MutCompObjIntCharTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> MutCompObjIntCharTriple<T> comparableCopyOf(LObjIntCharTriple<T> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjIntCharTriple<T extends Comparable<? super T>>  extends AbstractObjIntCharTriple<T> implements ComparableObjIntCharTriple<T>,Mut<T,MutCompObjIntCharTriple<T>>   {

        private  T first;
        private  int second;
        private  char third;

        public MutCompObjIntCharTriple(T a1,int a2,char a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjIntCharTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompObjIntCharTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override char third() {
            return third;
        }

        public @Override MutCompObjIntCharTriple<T> third(char third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static <T> ImmObjIntCharTriple<T> immutableOf(T a1,int a2,char a3){
        return new ImmObjIntCharTriple(a1,a2,a3);
  }

  public static <T> ImmObjIntCharTriple<T> immutableCopyOf(LObjIntCharTriple<T> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjIntCharTriple<T>  extends AbstractObjIntCharTriple<T>    {

        private final T first;
        private final int second;
        private final char third;

        public ImmObjIntCharTriple(T a1,int a2,char a3){
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

        public @Override char third() {
            return third;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjIntCharTriple<T> immutableComparableOf(T a1,int a2,char a3){
        return new ImmCompObjIntCharTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> ImmCompObjIntCharTriple<T> immutableComparableCopyOf(LObjIntCharTriple<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjIntCharTriple<T extends Comparable<? super T>>  extends AbstractObjIntCharTriple<T> implements ComparableObjIntCharTriple<T>   {

        private final T first;
        private final int second;
        private final char third;

        public ImmCompObjIntCharTriple(T a1,int a2,char a3){
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

        public @Override char third() {
            return third;
        }



    }



}


