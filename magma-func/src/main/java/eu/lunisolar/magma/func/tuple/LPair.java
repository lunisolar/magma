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
 * Exact equivalent of input parameters used in LBiConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LPair<T1,T2> extends LTuple<Object> 
 , Map.Entry<T1, T2> {

    int SIZE = 2;


    T1 first();

    default T1 value() {
        return first();
    }

    T2 second();



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

        //<editor-fold desc="Map.Entry">

        /** Returns key as Entry.key() */
        @Override default T1 getKey() {
			return first();
		}

        /** Returns value as Entry.value(). 'Value' is assigned to first tuple element. */
		@Override default T2 getValue() {
			return second();
		}

		@Override default T2 setValue(T2 value) {
			throw new UnsupportedOperationException();
		}

		//</editor-fold>


    /** Static hashCode() implementation method that takes same arguments as fields of the LPair and calculates hash from it. */
    static <T1,T2> int argHashCode(T1 a1,T2 a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
            result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LPair and checks if all values are equal. */
    static <T1,T2> boolean argEquals(T1 a1,T2 a2, T1 b1,T2 b2) {
        return
            Null.equals(a1, b1) && //
            Null.equals(a2, b2); //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LPair interface (among others) and their LPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LPair are allowed.
            if (!(two instanceof LPair)) {
                return false;
            }

            LPair other = (LPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LPair are allowed.
            if (!(two instanceof LPair)) {
                return false;
            }

            LPair other = (LPair) two;

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

    interface ComparablePair<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> extends LPair<T1,T2>, Comparable<LPair<T1,T2>> {
        @Override
        default int compareTo(LPair<T1,T2> that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Null.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    }
    

    abstract class AbstractPair<T1,T2> implements LPair<T1,T2> {

        @Override
        public boolean equals(Object that) {
            return LPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LPair.argHashCode(first(),second());
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

     interface  Mut<T1,T2,SELF extends Mut<T1,T2,SELF>>  extends LPair<T1,T2>   {



        SELF first(T1 first) ; 
        SELF second(T2 second) ; 

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
            


        default SELF reset()   {
                this.first(null);
                this.second(null);
            return (SELF) this;
        }
    }





  public static <T1,T2> MutPair<T1,T2> of() {
      return of(  null ,  null );
  }
      

  public static <T1,T2> MutPair<T1,T2> of(T1 a1,T2 a2){
        return new MutPair(a1,a2);
  }

  public static <T1,T2> MutPair<T1,T2> copyOf(LPair<T1,T2> tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutPair<T1,T2>  extends AbstractPair<T1,T2> implements Mut<T1,T2,MutPair<T1,T2>>   {

        private  T1 first;
        private  T2 second;

        public MutPair(T1 a1,T2 a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override MutPair<T1,T2> first(T1 first)    {
            this.first = first;
            return this;
        }
            
        public @Override T2 second() {
            return second;
        }

        public @Override MutPair<T1,T2> second(T2 second)    {
            this.second = second;
            return this;
        }
            







        @Override public T2 setValue(T2 value) {
            var old = second();
            second(value);
            return old;
        }






    }






  public static <T1,T2> LPair<T1,T2> immutableOf(T1 a1,T2 a2){
        return new ImmPair(a1,a2);
  }

  public static <T1,T2> LPair<T1,T2> immutableCopyOf(LPair<T1,T2> tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmPair<T1,T2>  extends AbstractPair<T1,T2>    {

        private final T1 first;
        private final T2 second;

        public ImmPair(T1 a1,T2 a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override T1 first() {
            return first;
        }

        public @Override T2 second() {
            return second;
        }



    }




    public static <T> Iterator<LPair.MutPair> mutIterator(Iterator<? extends T> items) { return iterator(items, LPair::of);}
    public static <T> Iterator<LPair> immIterator(Iterator<? extends T> items) { return iterator(items, LPair::immutableOf);}

   	public static <T,R> Iterator<R> iterator(Iterator<? extends T> items, LBiFunction<T,T,R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

    public static <T> Stream<LPair.MutPair> mutStream(Stream<? extends T> items) { return stream(items, LPair::of);}
    public static <T> Stream<LPair> immStream(Stream<? extends T> items) { return stream(items, LPair::immutableOf);}

	public static <T,R> Stream<R> stream(Stream<? extends T> items, LBiFunction<T,T,R> factory) {
       var pairs = iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Stream<R> stream(SequentialRead<C, ?, a<T>> sa, C source, LBiFunction<T,T,R> factory) {
       var pairs = iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Stream<R> stream(IndexedRead<C, a<T>> ia, C source, LBiFunction<T,T,R> factory) {
       var pairs = iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <T,C,R> Iterator<R> iterator(SequentialRead<C, ?, a<T>> sa_, C source, LBiFunction<T,T,R> factory) {

        var sa = (SequentialRead<C, Object, a<T>>) sa_;
        var iterator = SA.adapter(sa).apply(source);
        var testFunc = SA.tester(sa);
        var nextFunc = SA.supplier(sa);

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.apply(iterator);
                var a2 = nextFunc.apply(iterator);
				return factory.apply(a1,a2);
            }
        };
    }

    public static <T,C,R> Iterator<R> iterator(IndexedRead<C, a<T>> ia, C source, LBiFunction<T,T,R> factory) {

        int size = ia.size(source);
        var oiFunc = IA.getter(ia);

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.apply(source, index++);
                var a2 = oiFunc.apply(source, index++);
				return factory.apply(a1,a2);
            }
        };
    }




    public static <T> void forEach(Stream<? extends T> items, LBiConsumer<T,T> consumer) {
        forEach(items.iterator(), consumer);
    }

    public static <T> void forEach(Iterator<? extends T> items, LBiConsumer<T,T> consumer) {
        var emptyTuples = iterator(items, (a1,a2) -> {
            consumer.accept(a1,a2);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


