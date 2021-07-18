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
 * Exact equivalent of input parameters used in LTieDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntDblTriple<T> extends LTuple<Object> 
  {

    int SIZE = 3;


    T first();

    default T value() {
        return first();
    }

    int second();

    double third();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LObjIntDblTriple and calculates hash from it. */
    static <T> int argHashCode(T a1,int a2,double a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + Integer.hashCode(a2);
            result = prime * result + Double.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntDblTriple and checks if all values are equal. */
    static <T> boolean argEquals(T a1,int a2,double a3, T b1,int b2,double b3) {
        return
            Null.equals(a1, b1) && //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LObjIntDblTriple interface (among others) and their LObjIntDblTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LObjIntDblTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntDblTriple are allowed.
            if (!(two instanceof LObjIntDblTriple)) {
                return false;
            }

            LObjIntDblTriple other = (LObjIntDblTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LObjIntDblTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LObjIntDblTriple are allowed.
            if (!(two instanceof LObjIntDblTriple)) {
                return false;
            }

            LObjIntDblTriple other = (LObjIntDblTriple) two;

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

    interface ComparableObjIntDblTriple<T extends Comparable<? super T>> extends LObjIntDblTriple<T>, Comparable<LObjIntDblTriple<T>> {
        @Override
        default int compareTo(LObjIntDblTriple<T> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractObjIntDblTriple<T> implements LObjIntDblTriple<T> {

        @Override
        public boolean equals(Object that) {
            return LObjIntDblTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LObjIntDblTriple.argHashCode(first(),second(),third());
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

     interface  Mut<T,SELF extends Mut<T,SELF>>  extends LObjIntDblTriple<T>   {



        SELF first(T first) ; 
        SELF second(int second) ; 
        SELF third(double third) ; 

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
            


        default SELF setThird(double third) {
            this.third(third);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setThirdIfArg(double third, LDblPredicate predicate) {
            if (predicate.test(third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
            if ( arg != null ) {
                return this.third(func.applyAsDbl(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setThirdIf(LDblPredicate predicate, double third) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setThirdIf(double third, LBiDblPredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setThirdIf(LBiDblPredicate predicate, double third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(0);
                this.third(0d);
            return (SELF) this;
        }
    }






  public static <T> MutObjIntDblTriple<T> of() { 
      return of(  null ,  0 ,  0d );
  }
      

  public static <T> MutObjIntDblTriple<T> of(T a1,int a2,double a3){
        return new MutObjIntDblTriple(a1,a2,a3);
  }

  public static <T> MutObjIntDblTriple<T> copyOf(LObjIntDblTriple<T> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutObjIntDblTriple<T>  extends AbstractObjIntDblTriple<T> implements Mut<T,MutObjIntDblTriple<T>>   {

        private  T first;
        private  int second;
        private  double third;

        public MutObjIntDblTriple(T a1,int a2,double a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutObjIntDblTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutObjIntDblTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override double third() {
            return third;
        }

        public @Override MutObjIntDblTriple<T> third(double third)    {
            this.third = third;
            return this;
        }
            




















    }






  public static <T extends Comparable<? super T>> MutCompObjIntDblTriple<T> comparableOf() { 
      return comparableOf(  null ,  0 ,  0d );
  }
      

  public static <T extends Comparable<? super T>> MutCompObjIntDblTriple<T> comparableOf(T a1,int a2,double a3){
        return new MutCompObjIntDblTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> MutCompObjIntDblTriple<T> comparableCopyOf(LObjIntDblTriple<T> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompObjIntDblTriple<T extends Comparable<? super T>>  extends AbstractObjIntDblTriple<T> implements ComparableObjIntDblTriple<T>,Mut<T,MutCompObjIntDblTriple<T>>   {

        private  T first;
        private  int second;
        private  double third;

        public MutCompObjIntDblTriple(T a1,int a2,double a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T first() {
            return first;
        }

        public @Override MutCompObjIntDblTriple<T> first(T first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutCompObjIntDblTriple<T> second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override double third() {
            return third;
        }

        public @Override MutCompObjIntDblTriple<T> third(double third)    {
            this.third = third;
            return this;
        }
            




















    }







  public static <T> ImmObjIntDblTriple<T> immutableOf(T a1,int a2,double a3){
        return new ImmObjIntDblTriple(a1,a2,a3);
  }

  public static <T> ImmObjIntDblTriple<T> immutableCopyOf(LObjIntDblTriple<T> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmObjIntDblTriple<T>  extends AbstractObjIntDblTriple<T>    {

        private final T first;
        private final int second;
        private final double third;

        public ImmObjIntDblTriple(T a1,int a2,double a3){
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

        public @Override double third() {
            return third;
        }



    }







  public static <T extends Comparable<? super T>> ImmCompObjIntDblTriple<T> immutableComparableOf(T a1,int a2,double a3){
        return new ImmCompObjIntDblTriple(a1,a2,a3);
  }

  public static <T extends Comparable<? super T>> ImmCompObjIntDblTriple<T> immutableComparableCopyOf(LObjIntDblTriple<T> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompObjIntDblTriple<T extends Comparable<? super T>>  extends AbstractObjIntDblTriple<T> implements ComparableObjIntDblTriple<T>   {

        private final T first;
        private final int second;
        private final double third;

        public ImmCompObjIntDblTriple(T a1,int a2,double a3){
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

        public @Override double third() {
            return third;
        }



    }



}


