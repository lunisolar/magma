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
 * Exact equivalent of input parameters used in LBiSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtPair extends LTuple<Short> , Comparable<LSrtPair> 
  {

    int SIZE = 2;


    short first();

    default short value() {
        return first();
    }

    short second();



    @Override default Short get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            default: throw new NoSuchElementException();
        }
    }

    default short getShort(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LSrtPair and calculates hash from it. */
    static  int argHashCode(short a1,short a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Short.hashCode(a1);
            result = prime * result + Short.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtPair and checks if all values are equal. */
    static  boolean argEquals(short a1,short a2, short b1,short b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LSrtPair interface (among others) and their LSrtPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LSrtPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtPair are allowed.
            if (!(two instanceof LSrtPair)) {
                return false;
            }

            LSrtPair other = (LSrtPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LSrtPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtPair are allowed.
            if (!(two instanceof LSrtPair)) {
                return false;
            }

            LSrtPair other = (LSrtPair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Short> iterator() {
        return new Iterator<Short>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Short next() {
                index++;
                return get(index);
            }
        };
    }


    default PrimitiveIterator.OfInt intIterator() {
        return new PrimitiveIterator.OfInt() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public int nextInt() {
                index++;
                return getShort(index);
            }
        };
    }
        @Override
        default int compareTo(LSrtPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractSrtPair implements LSrtPair {

        @Override
        public boolean equals(Object that) {
            return LSrtPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LSrtPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LSrtPair   {



        SELF first(short first) ; 
        SELF second(short second) ; 

        default SELF setFirst(short first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(short first, LSrtPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(short first, LBiSrtPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiSrtPredicate predicate, short first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(short second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(short second, LSrtPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(short second, LBiSrtPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiSrtPredicate predicate, short second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first((short)0);
                this.second((short)0);
            return (SELF) this;
        }
    }





  public static  MutSrtPair of() {
      return of(  (short)0 ,  (short)0 );
  }
      

  public static  MutSrtPair of(short a1,short a2){
        return new MutSrtPair(a1,a2);
  }

  public static  MutSrtPair copyOf(LSrtPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutSrtPair  extends AbstractSrtPair implements Mut<MutSrtPair>   {

        private  short first;
        private  short second;

        public MutSrtPair(short a1,short a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override MutSrtPair first(short first)    {
            this.first = first;
            return this;
        }
            
        public @Override short second() {
            return second;
        }

        public @Override MutSrtPair second(short second)    {
            this.second = second;
            return this;
        }
            












    }






  public static  LSrtPair immutableOf(short a1,short a2){
        return new ImmSrtPair(a1,a2);
  }

  public static  LSrtPair immutableCopyOf(LSrtPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmSrtPair  extends AbstractSrtPair    {

        private final short first;
        private final short second;

        public ImmSrtPair(short a1,short a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override short first() {
            return first;
        }

        public @Override short second() {
            return second;
        }



    }




    public static  Iterator<LSrtPair.MutSrtPair> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LSrtPair::of);}
    public static  Iterator<LSrtPair> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LSrtPair::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LBiSrtFunction<R> factory) {
		return iterator(SA.shortIterator(), items, factory);
	}

    public static  Stream<LSrtPair.MutSrtPair> mutStream(IntStream items) { return stream(items, LSrtPair::of);}
    public static  Stream<LSrtPair> immStream(IntStream items) { return stream(items, LSrtPair::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LBiSrtFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aShort> sa, C source, LBiSrtFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aShort> ia, C source, LBiSrtFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aShort> sa, C source, LBiSrtFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LToSrtFunction<C> nextFunc = (LToSrtFunction<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsSrt(iterator);
                var a2 = nextFunc.applyAsSrt(iterator);
				return factory.apply(a1,a2);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aShort> ia, C source, LBiSrtFunction<R> factory) {

        int size = ia.size(source);
        LOiToSrtFunction<C> oiFunc = (LOiToSrtFunction<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsSrt(source, index++);
                var a2 = oiFunc.applyAsSrt(source, index++);
				return factory.apply(a1,a2);
            }
        };
    }




    public static  void forEach(IntStream items, LBiSrtConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LBiSrtConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2) -> {
            consumer.accept(a1,a2);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


