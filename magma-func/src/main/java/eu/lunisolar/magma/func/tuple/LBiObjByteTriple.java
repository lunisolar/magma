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
 * Exact equivalent of input parameters used in LBiObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteTriple<T1,T2> extends LTuple<Object> 
  {

    int SIZE = 3;


    T1 first();

    default T1 value() {
        return first();
    }

    T2 second();

    byte third();



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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBiObjByteTriple and calculates hash from it. */
    static <T1,T2> int argHashCode(T1 a1,T2 a2,byte a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
            result = prime * result + Byte.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjByteTriple and checks if all values are equal. */
    static <T1,T2> boolean argEquals(T1 a1,T2 a2,byte a3, T1 b1,T2 b2,byte b3) {
        return
            Null.equals(a1, b1) && //
            Null.equals(a2, b2) && //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBiObjByteTriple interface (among others) and their LBiObjByteTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBiObjByteTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBiObjByteTriple are allowed.
            if (!(two instanceof LBiObjByteTriple)) {
                return false;
            }

            LBiObjByteTriple other = (LBiObjByteTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBiObjByteTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBiObjByteTriple are allowed.
            if (!(two instanceof LBiObjByteTriple)) {
                return false;
            }

            LBiObjByteTriple other = (LBiObjByteTriple) two;

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

    interface ComparableBiObjByteTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> extends LBiObjByteTriple<T1,T2>, Comparable<LBiObjByteTriple<T1,T2>> {
        @Override
        default int compareTo(LBiObjByteTriple<T1,T2> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Byte.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractBiObjByteTriple<T1,T2> implements LBiObjByteTriple<T1,T2> {

        @Override
        public boolean equals(Object that) {
            return LBiObjByteTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBiObjByteTriple.argHashCode(first(),second(),third());
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

     interface  Mut<T1,T2,SELF extends Mut<T1,T2,SELF>>  extends LBiObjByteTriple<T1,T2>   {



        SELF first(T1 first) ; 
        SELF second(T2 second) ; 
        SELF third(byte third) ; 

        default SELF setFirst(T1 first) {
            this.first(first);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setFirstIfArg(T1 first, LPredicate<T1> predicate) {
            if (predicate.test(first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setFirstIfArgNotNull(R arg, LFunction<R,T1> func) {
            if ( arg != null ) {
                return this.first(func.apply(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setFirstIf(LPredicate<T1> predicate, T1 first) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setFirstIf(T1 first, LBiPredicate<T1,T1> predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
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

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setSecondIfArg(T2 second, LPredicate<T2> predicate) {
            if (predicate.test(second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setSecondIfArgNotNull(R arg, LFunction<R,T2> func) {
            if ( arg != null ) {
                return this.second(func.apply(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setSecondIf(LPredicate<T2> predicate, T2 second) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setSecondIf(T2 second, LBiPredicate<T2,T2> predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setSecondIf(LBiPredicate<T2,T2> predicate, T2 second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF setThird(byte third) {
            this.third(third);
            return (SELF) this;
        }

        /** Sets value if predicate(newValue) OR newValue::predicate is true */
        default SELF setThirdIfArg(byte third, LBytePredicate predicate) {
            if (predicate.test(third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets value derived from non-null argument, only if argument is not null. */
        default <R> SELF setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
            if ( arg != null ) {
                return this.third(func.applyAsByte(arg));
            }
            return (SELF) this;
        }

        /** Sets value if predicate(current) OR current::predicate is true */
        default SELF setThirdIf(LBytePredicate predicate, byte third) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
        default SELF setThirdIf(byte third, LBiBytePredicate predicate) {
            // the order of arguments is intentional, to allow predicate:
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
        default SELF setThirdIf(LBiBytePredicate predicate, byte third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(null);
                this.third((byte)0);
            return (SELF) this;
        }
    }






  public static <T1,T2> MutBiObjByteTriple<T1,T2> of() { 
      return of(  null ,  null ,  (byte)0 );
  }
      

  public static <T1,T2> MutBiObjByteTriple<T1,T2> of(T1 a1,T2 a2,byte a3){
        return new MutBiObjByteTriple(a1,a2,a3);
  }

  public static <T1,T2> MutBiObjByteTriple<T1,T2> copyOf(LBiObjByteTriple<T1,T2> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBiObjByteTriple<T1,T2>  extends AbstractBiObjByteTriple<T1,T2> implements Mut<T1,T2,MutBiObjByteTriple<T1,T2>>   {

        private  T1 first;
        private  T2 second;
        private  byte third;

        public MutBiObjByteTriple(T1 a1,T2 a2,byte a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutBiObjByteTriple<T1,T2> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutBiObjByteTriple<T1,T2> second(T2 second)    {
            this.second = second;
            return this;
        }
            
        public @Override byte third() {
            return third;
        }

        public @Override MutBiObjByteTriple<T1,T2> third(byte third)    {
            this.third = third;
            return this;
        }
            




















    }






  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjByteTriple<T1,T2> comparableOf() { 
      return comparableOf(  null ,  null ,  (byte)0 );
  }
      

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjByteTriple<T1,T2> comparableOf(T1 a1,T2 a2,byte a3){
        return new MutCompBiObjByteTriple(a1,a2,a3);
  }

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> MutCompBiObjByteTriple<T1,T2> comparableCopyOf(LBiObjByteTriple<T1,T2> tuple) {
        return comparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, comparable tuple.
     */

    final  class  MutCompBiObjByteTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>>  extends AbstractBiObjByteTriple<T1,T2> implements ComparableBiObjByteTriple<T1,T2>,Mut<T1,T2,MutCompBiObjByteTriple<T1,T2>>   {

        private  T1 first;
        private  T2 second;
        private  byte third;

        public MutCompBiObjByteTriple(T1 a1,T2 a2,byte a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutCompBiObjByteTriple<T1,T2> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutCompBiObjByteTriple<T1,T2> second(T2 second)    {
            this.second = second;
            return this;
        }
            
        public @Override byte third() {
            return third;
        }

        public @Override MutCompBiObjByteTriple<T1,T2> third(byte third)    {
            this.third = third;
            return this;
        }
            




















    }







  public static <T1,T2> ImmBiObjByteTriple<T1,T2> immutableOf(T1 a1,T2 a2,byte a3){
        return new ImmBiObjByteTriple(a1,a2,a3);
  }

  public static <T1,T2> ImmBiObjByteTriple<T1,T2> immutableCopyOf(LBiObjByteTriple<T1,T2> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBiObjByteTriple<T1,T2>  extends AbstractBiObjByteTriple<T1,T2>    {

        private final T1 first;
        private final T2 second;
        private final byte third;

        public ImmBiObjByteTriple(T1 a1,T2 a2,byte a3){
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

        public @Override byte third() {
            return third;
        }



    }







  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> ImmCompBiObjByteTriple<T1,T2> immutableComparableOf(T1 a1,T2 a2,byte a3){
        return new ImmCompBiObjByteTriple(a1,a2,a3);
  }

  public static <T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> ImmCompBiObjByteTriple<T1,T2> immutableComparableCopyOf(LBiObjByteTriple<T1,T2> tuple) {
        return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, comparable tuple.
     */
@Immutable
    final  class  ImmCompBiObjByteTriple<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>>  extends AbstractBiObjByteTriple<T1,T2> implements ComparableBiObjByteTriple<T1,T2>   {

        private final T1 first;
        private final T2 second;
        private final byte third;

        public ImmCompBiObjByteTriple(T1 a1,T2 a2,byte a3){
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

        public @Override byte third() {
            return third;
        }



    }



}


