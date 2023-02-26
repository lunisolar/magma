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
 * Exact equivalent of input parameters used in LBiObjFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFltTriple<T1,T2> extends LTuple<Object> 
  {

    int SIZE = 3;


    T1 first();

    default T1 value() {
        return first();
    }

    T2 second();

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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBiObjFltTriple and calculates hash from it. */
    static <T1,T2> int argHashCode(T1 a1,T2 a2,float a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
            result = prime * result + Float.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjFltTriple and checks if all values are equal. */
    static <T1,T2> boolean argEquals(T1 a1,T2 a2,float a3, T1 b1,T2 b2,float b3) {
        return
            Null.equals(a1, b1) && //
            Null.equals(a2, b2) && //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBiObjFltTriple interface (among others) and their LBiObjFltTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBiObjFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBiObjFltTriple are allowed.
            if (!(two instanceof LBiObjFltTriple)) {
                return false;
            }

            LBiObjFltTriple other = (LBiObjFltTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBiObjFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBiObjFltTriple are allowed.
            if (!(two instanceof LBiObjFltTriple)) {
                return false;
            }

            LBiObjFltTriple other = (LBiObjFltTriple) two;

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

    interface ComparableBiObjFltTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> extends LBiObjFltTriple<T1,T2>, Comparable<LBiObjFltTriple<T1,T2>> {
        @Override
        default int compareTo(LBiObjFltTriple<T1,T2> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractBiObjFltTriple<T1,T2> implements LBiObjFltTriple<T1,T2> {

        @Override
        public boolean equals(Object that) {
            return LBiObjFltTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBiObjFltTriple.argHashCode(first(),second(),third());
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

     interface  Mut<T1,T2,SELF extends Mut<T1,T2,SELF>>  extends LBiObjFltTriple<T1,T2>   {



        SELF first(T1 first) ; 
        SELF second(T2 second) ; 
        SELF third(float third) ; 

        default SELF setFirst(T1 first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(T1 first, LPredicate<T1> predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(T1 first, LBiPredicate<T1,T1> predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiPredicate<T1,T1> predicate, T1 first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(T2 second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(T2 second, LPredicate<T2> predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(T2 second, LBiPredicate<T2,T2> predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiPredicate<T2,T2> predicate, T2 second) {
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
                this.second(null);
                this.third(0f);
            return (SELF) this;
        }
    }






  public static <T1,T2> MutBiObjFltTriple<T1,T2> of() { 
      return of(  null ,  null ,  0f );
  }
      

  public static <T1,T2> MutBiObjFltTriple<T1,T2> of(T1 a1,T2 a2,float a3){
        return new MutBiObjFltTriple(a1,a2,a3);
  }

  public static <T1,T2> MutBiObjFltTriple<T1,T2> copyOf(LBiObjFltTriple<T1,T2> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBiObjFltTriple<T1,T2>  extends AbstractBiObjFltTriple<T1,T2> implements Mut<T1,T2,MutBiObjFltTriple<T1,T2>>   {

        private  T1 first;
        private  T2 second;
        private  float third;

        public MutBiObjFltTriple(T1 a1,T2 a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutBiObjFltTriple<T1,T2> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutBiObjFltTriple<T1,T2> second(T2 second)    {
            this.second = second;
            return this;
        }
            
        public @Override float third() {
            return third;
        }

        public @Override MutBiObjFltTriple<T1,T2> third(float third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjFltTriple<T1,T2> comparableOf() { 
      return comparableOf(  null ,  null ,  0f );
  }
      

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjFltTriple<T1,T2> comparableOf(T1 a1,T2 a2,float a3){
        return new MutCompBiObjFltTriple(a1,a2,a3);
  }

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjFltTriple<T1,T2> comparableCopyOf(LBiObjFltTriple<T1,T2> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompBiObjFltTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>>  extends AbstractBiObjFltTriple<T1,T2> implements ComparableBiObjFltTriple<T1,T2>,Mut<T1,T2,MutCompBiObjFltTriple<T1,T2>>   {

        private  T1 first;
        private  T2 second;
        private  float third;

        public MutCompBiObjFltTriple(T1 a1,T2 a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutCompBiObjFltTriple<T1,T2> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutCompBiObjFltTriple<T1,T2> second(T2 second)    {
            this.second = second;
            return this;
        }
            
        public @Override float third() {
            return third;
        }

        public @Override MutCompBiObjFltTriple<T1,T2> third(float third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static <T1,T2> ImmBiObjFltTriple<T1,T2> immutableOf(T1 a1,T2 a2,float a3){
        return new ImmBiObjFltTriple(a1,a2,a3);
  }

  public static <T1,T2> ImmBiObjFltTriple<T1,T2> immutableCopyOf(LBiObjFltTriple<T1,T2> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBiObjFltTriple<T1,T2>  extends AbstractBiObjFltTriple<T1,T2>    {

        private final T1 first;
        private final T2 second;
        private final float third;

        public ImmBiObjFltTriple(T1 a1,T2 a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override T2 second() {
            return second;
        }

        public @Override float third() {
            return third;
        }



    }







  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> ImmCompBiObjFltTriple<T1,T2> immutableComparableOf(T1 a1,T2 a2,float a3){
        return new ImmCompBiObjFltTriple(a1,a2,a3);
  }

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> ImmCompBiObjFltTriple<T1,T2> immutableComparableCopyOf(LBiObjFltTriple<T1,T2> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompBiObjFltTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>>  extends AbstractBiObjFltTriple<T1,T2> implements ComparableBiObjFltTriple<T1,T2>   {

        private final T1 first;
        private final T2 second;
        private final float third;

        public ImmCompBiObjFltTriple(T1 a1,T2 a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override T2 second() {
            return second;
        }

        public @Override float third() {
            return third;
        }



    }



}


