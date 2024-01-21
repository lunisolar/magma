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
 * Exact equivalent of input parameters used in LQuintConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LQuint<T1,T2,T3,T4,T5> extends LTuple<Object> 
 , Map.Entry<T2, T1> {

    int SIZE = 5;


    T1 first();

    default T1 value() {
        return first();
    }

    T2 second();

    T3 third();

    T4 fourth();

    T5 fifth();



    @Override default Object get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            case 4: return fourth();
            case 5: return fifth();
            default: throw new NoSuchElementException();
        }
    }


    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

        //<editor-fold desc="Map.Entry">

        /** Returns key as Entry.key() */
        @Override default T2 getKey() {
			return second();
		}

        /** Returns value as Entry.value(). 'Value' is assigned to first tuple element. */
		@Override default T1 getValue() {
			return first();
		}

		@Override default T1 setValue(T1 value) {
			throw new UnsupportedOperationException();
		}

		//</editor-fold>
		
    

    /** Static hashCode() implementation method that takes same arguments as fields of the LQuint and calculates hash from it. */
    static <T1,T2,T3,T4,T5> int argHashCode(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
            result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
            result = prime * result + ((a4 == null) ? 0 : a4.hashCode());
            result = prime * result + ((a5 == null) ? 0 : a5.hashCode());
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LQuint and checks if all values are equal. */
    static <T1,T2,T3,T4,T5> boolean argEquals(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5, T1 b1,T2 b2,T3 b3,T4 b4,T5 b5) {
        return
            Null.equals(a1, b1) && //
            Null.equals(a2, b2) && //
            Null.equals(a3, b3) && //
            Null.equals(a4, b4) && //
            Null.equals(a5, b5); //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LQuint interface (among others) and their LQuint values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LQuint the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LQuint are allowed.
            if (!(two instanceof LQuint)) {
                return false;
            }

            LQuint other = (LQuint) two;

            return argEquals(one.first(), one.second(), one.third(), one.fourth(), one.fifth(), other.first(), other.second(), other.third(), other.fourth(), other.fifth());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LQuint the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LQuint are allowed.
            if (!(two instanceof LQuint)) {
                return false;
            }

            LQuint other = (LQuint) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), one.fourth(), one.fifth(), other.first(), other.second(), other.third(), other.fourth(), other.fifth());
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

    interface ComparableQuint<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>,T3 extends Comparable<? super T3>,T4 extends Comparable<? super T4>,T5 extends Comparable<? super T5>> extends LQuint<T1,T2,T3,T4,T5>, Comparable<LQuint<T1,T2,T3,T4,T5>> {
        @Override
        default int compareTo(LQuint<T1,T2,T3,T4,T5> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Null.compare(one.third(), two.third())) != 0 ? retval : //
                    (retval = Null.compare(one.fourth(), two.fourth())) != 0 ? retval : //
                    (retval = Null.compare(one.fifth(), two.fifth())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractQuint<T1,T2,T3,T4,T5> implements LQuint<T1,T2,T3,T4,T5> {

        @Override
        public boolean equals(Object that) {
            return LQuint.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LQuint.argHashCode(first(),second(),third(),fourth(),fifth());
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
                sb.append(',');
                sb.append(fourth());
                sb.append(',');
                sb.append(fifth());
            sb.append(')');
            return sb.toString();
        }

    }





    /**
     * Mutable tuple.
     */

     interface  Mut<T1,T2,T3,T4,T5,SELF extends Mut<T1,T2,T3,T4,T5,SELF>>  extends LQuint<T1,T2,T3,T4,T5>   {



        SELF first(T1 first) ; 
        SELF second(T2 second) ; 
        SELF third(T3 third) ; 
        SELF fourth(T4 fourth) ; 
        SELF fifth(T5 fifth) ; 

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
            


        default SELF setThird(T3 third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(T3 third, LPredicate<T3> predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(T3 third, LBiPredicate<T3,T3> predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiPredicate<T3,T3> predicate, T3 third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF setFourth(T4 fourth) {
            this.fourth(fourth);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFourthIf(T4 fourth, LPredicate<T4> predicate) {
            if (predicate.test(this.fourth())) {
                return this.fourth(fourth);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFourthIf(T4 fourth, LBiPredicate<T4,T4> predicate) {
            if (predicate.test(fourth, this.fourth())) {
                return this.fourth(fourth);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFourthIf(LBiPredicate<T4,T4> predicate, T4 fourth) {
            if (predicate.test(this.fourth(), fourth)) {
                return this.fourth(fourth);
            }
            return (SELF) this;
        }
            


        default SELF setFifth(T5 fifth) {
            this.fifth(fifth);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFifthIf(T5 fifth, LPredicate<T5> predicate) {
            if (predicate.test(this.fifth())) {
                return this.fifth(fifth);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFifthIf(T5 fifth, LBiPredicate<T5,T5> predicate) {
            if (predicate.test(fifth, this.fifth())) {
                return this.fifth(fifth);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFifthIf(LBiPredicate<T5,T5> predicate, T5 fifth) {
            if (predicate.test(this.fifth(), fifth)) {
                return this.fifth(fifth);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(null);
                this.second(null);
                this.third(null);
                this.fourth(null);
                this.fifth(null);
            return (SELF) this;
        }
    }





  public static <T1,T2,T3,T4,T5> MutQuint<T1,T2,T3,T4,T5> of() {
      return of(  null ,  null ,  null ,  null ,  null );
  }
      

  public static <T1,T2,T3,T4,T5> MutQuint<T1,T2,T3,T4,T5> of(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5){
        return new MutQuint(a1,a2,a3,a4,a5);
  }

  public static <T1,T2,T3,T4,T5> MutQuint<T1,T2,T3,T4,T5> copyOf(LQuint<T1,T2,T3,T4,T5> tuple) {
        return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutQuint<T1,T2,T3,T4,T5>  extends AbstractQuint<T1,T2,T3,T4,T5> implements Mut<T1,T2,T3,T4,T5,MutQuint<T1,T2,T3,T4,T5>>   {

        private  T1 first;
        private  T2 second;
        private  T3 third;
        private  T4 fourth;
        private  T5 fifth;

        public MutQuint(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5){
            this.first = a1;
            this.second = a2;
            this.third = a3;
            this.fourth = a4;
            this.fifth = a5;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutQuint<T1,T2,T3,T4,T5> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutQuint<T1,T2,T3,T4,T5> second(T2 second)    {
            this.second = second;
            return this;
        }
            
        public @Override T3 third() {
            return third;
        }

        public @Override MutQuint<T1,T2,T3,T4,T5> third(T3 third)    {
            this.third = third;
            return this;
        }
            
        public @Override T4 fourth() {
            return fourth;
        }

        public @Override MutQuint<T1,T2,T3,T4,T5> fourth(T4 fourth)    {
            this.fourth = fourth;
            return this;
        }
            
        public @Override T5 fifth() {
            return fifth;
        }

        public @Override MutQuint<T1,T2,T3,T4,T5> fifth(T5 fifth)    {
            this.fifth = fifth;
            return this;
        }
            



























    }






  public static <T1,T2,T3,T4,T5> LQuint<T1,T2,T3,T4,T5> immutableOf(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5){
        return new ImmQuint(a1,a2,a3,a4,a5);
  }

  public static <T1,T2,T3,T4,T5> LQuint<T1,T2,T3,T4,T5> immutableCopyOf(LQuint<T1,T2,T3,T4,T5> tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmQuint<T1,T2,T3,T4,T5>  extends AbstractQuint<T1,T2,T3,T4,T5>    {

        private final T1 first;
        private final T2 second;
        private final T3 third;
        private final T4 fourth;
        private final T5 fifth;

        public ImmQuint(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5){
            this.first = a1;
            this.second = a2;
            this.third = a3;
            this.fourth = a4;
            this.fifth = a5;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override T2 second() {
            return second;
        }

        public @Override T3 third() {
            return third;
        }

        public @Override T4 fourth() {
            return fourth;
        }

        public @Override T5 fifth() {
            return fifth;
        }



    }




    public static <T> Iterator<LQuint.MutQuint> mutIterator(Iterator<? extends T> items) { return iterator(items, LQuint::of);}
    public static <T> Iterator<LQuint> immIterator(Iterator<? extends T> items) { return iterator(items, LQuint::immutableOf);}

   	public static <T,R> Iterator<R> iterator(Iterator<? extends T> items, LQuintFunction<T,T,T,T,T,R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

    public static <T> Stream<LQuint.MutQuint> mutStream(Stream<? extends T> items) { return stream(items, LQuint::of);}
    public static <T> Stream<LQuint> immStream(Stream<? extends T> items) { return stream(items, LQuint::immutableOf);}

	public static <T,R> Stream<R> stream(Stream<? extends T> items, LQuintFunction<T,T,T,T,T,R> factory) {
       var pairs = iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Stream<R> stream(SequentialRead<C, ?, a<T>> sa, C source, LQuintFunction<T,T,T,T,T,R> factory) {
       var pairs = iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Stream<R> stream(IndexedRead<C, a<T>> ia, C source, LQuintFunction<T,T,T,T,T,R> factory) {
       var pairs = iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Iterator<R> iterator(SequentialRead<C, ?, a<T>> sa_, C source, LQuintFunction<T,T,T,T,T,R> factory) {

        var sa = (SequentialRead<C, Object, a<T>>) sa_;
        var iterator = SA.adapter(sa).apply(source);
        var testFunc = SA.tester(sa);
        var nextFunc = SA.supplier(sa);

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.apply(iterator);
                var a2 = nextFunc.apply(iterator);
                var a3 = nextFunc.apply(iterator);
                var a4 = nextFunc.apply(iterator);
                var a5 = nextFunc.apply(iterator);
				return factory.apply(a1,a2,a3,a4,a5);
            }
        };
    }

    public static <T,C,R> Iterator<R> iterator(IndexedRead<C, a<T>> ia, C source, LQuintFunction<T,T,T,T,T,R> factory) {

        int size = ia.size(source);
        var oiFunc = IA.getter(ia);

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.apply(source, index++);
                var a2 = oiFunc.apply(source, index++);
                var a3 = oiFunc.apply(source, index++);
                var a4 = oiFunc.apply(source, index++);
                var a5 = oiFunc.apply(source, index++);
				return factory.apply(a1,a2,a3,a4,a5);
            }
        };
    }




    public static <T> void forEach(Stream<? extends T> items, LQuintConsumer<T,T,T,T,T> consumer) {
        forEach(items.iterator(), consumer);
    }

    public static <T> void forEach(Iterator<? extends T> items, LQuintConsumer<T,T,T,T,T> consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3,a4,a5) -> {
            consumer.accept(a1,a2,a3,a4,a5);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


