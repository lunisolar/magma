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
 * Exact equivalent of input parameters used in LBiLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongPair extends LTuple<Long> , Comparable<LLongPair> 
  {

    int SIZE = 2;


    long first();

    default long value() {
        return first();
    }

    long second();



    @Override default Long get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            default: throw new NoSuchElementException();
        }
    }

    default long getLong(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LLongPair and calculates hash from it. */
    static  int argHashCode(long a1,long a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Long.hashCode(a1);
            result = prime * result + Long.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LLongPair and checks if all values are equal. */
    static  boolean argEquals(long a1,long a2, long b1,long b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LLongPair interface (among others) and their LLongPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LLongPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LLongPair are allowed.
            if (!(two instanceof LLongPair)) {
                return false;
            }

            LLongPair other = (LLongPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LLongPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LLongPair are allowed.
            if (!(two instanceof LLongPair)) {
                return false;
            }

            LLongPair other = (LLongPair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Long> iterator() {
        return new Iterator<Long>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Long next() {
                index++;
                return get(index);
            }
        };
    }


    default PrimitiveIterator.OfLong longIterator() {
        return new PrimitiveIterator.OfLong() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public long nextLong() {
                index++;
                return getLong(index);
            }
        };
    }
        @Override
        default int compareTo(LLongPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Long.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractLongPair implements LLongPair {

        @Override
        public boolean equals(Object that) {
            return LLongPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LLongPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LLongPair   {



        SELF first(long first) ; 
        SELF second(long second) ; 

        default SELF setFirst(long first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(long first, LLongPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(long first, LBiLongPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiLongPredicate predicate, long first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(long second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(long second, LLongPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(long second, LBiLongPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiLongPredicate predicate, long second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(0L);
                this.second(0L);
            return (SELF) this;
        }
    }





  public static  MutLongPair of() {
      return of(  0L ,  0L );
  }
      

  public static  MutLongPair of(long a1,long a2){
        return new MutLongPair(a1,a2);
  }

  public static  MutLongPair copyOf(LLongPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutLongPair  extends AbstractLongPair implements Mut<MutLongPair>   {

        private  long first;
        private  long second;

        public MutLongPair(long a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override long first() {
            return first;
        }

        public @Override MutLongPair first(long first)    {
            this.first = first;
            return this;
        }
            
        public @Override long second() {
            return second;
        }

        public @Override MutLongPair second(long second)    {
            this.second = second;
            return this;
        }
            












    }






  public static  LLongPair immutableOf(long a1,long a2){
        return new ImmLongPair(a1,a2);
  }

  public static  LLongPair immutableCopyOf(LLongPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmLongPair  extends AbstractLongPair    {

        private final long first;
        private final long second;

        public ImmLongPair(long a1,long a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override long first() {
            return first;
        }

        public @Override long second() {
            return second;
        }



    }




    public static  Iterator<LLongPair.MutLongPair> mutIterator(PrimitiveIterator.OfLong items) { return iterator(items, LLongPair::of);}
    public static  Iterator<LLongPair> immIterator(PrimitiveIterator.OfLong items) { return iterator(items, LLongPair::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfLong items, LBiLongFunction<R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

    public static  Stream<LLongPair.MutLongPair> mutStream(LongStream items) { return stream(items, LLongPair::of);}
    public static  Stream<LLongPair> immStream(LongStream items) { return stream(items, LLongPair::immutableOf);}

	public static <R> Stream<R> stream(LongStream items, LBiLongFunction<R> factory) {
       var pairs = iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aLong> sa, C source, LBiLongFunction<R> factory) {
       var pairs = iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aLong> ia, C source, LBiLongFunction<R> factory) {
       var pairs = iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aLong> sa_, C source, LBiLongFunction<R> factory) {

        var sa = (SequentialRead<C, Object, aLong>) sa_;
        var iterator = SA.adapter(sa).apply(source);
        var testFunc = SA.tester(sa);
        var nextFunc = SA.longSupplier(sa);

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsLong(iterator);
                var a2 = nextFunc.applyAsLong(iterator);
				return factory.apply(a1,a2);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aLong> ia, C source, LBiLongFunction<R> factory) {

        int size = ia.size(source);
        var oiFunc = IA.longGetter(ia);

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsLong(source, index++);
                var a2 = oiFunc.applyAsLong(source, index++);
				return factory.apply(a1,a2);
            }
        };
    }




    public static  void forEach(LongStream items, LBiLongConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfLong items, LBiLongConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2) -> {
            consumer.accept(a1,a2);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


