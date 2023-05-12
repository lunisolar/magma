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
 * Exact equivalent of input parameters used in LBiByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBytePair extends LTuple<Byte> , Comparable<LBytePair> 
  {

    int SIZE = 2;


    byte first();

    default byte value() {
        return first();
    }

    byte second();



    @Override default Byte get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            default: throw new NoSuchElementException();
        }
    }

    default byte getByte(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBytePair and calculates hash from it. */
    static  int argHashCode(byte a1,byte a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Byte.hashCode(a1);
            result = prime * result + Byte.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBytePair and checks if all values are equal. */
    static  boolean argEquals(byte a1,byte a2, byte b1,byte b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBytePair interface (among others) and their LBytePair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBytePair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBytePair are allowed.
            if (!(two instanceof LBytePair)) {
                return false;
            }

            LBytePair other = (LBytePair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBytePair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBytePair are allowed.
            if (!(two instanceof LBytePair)) {
                return false;
            }

            LBytePair other = (LBytePair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Byte> iterator() {
        return new Iterator<Byte>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Byte next() {
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
                return getByte(index);
            }
        };
    }
        @Override
        default int compareTo(LBytePair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Byte.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractBytePair implements LBytePair {

        @Override
        public boolean equals(Object that) {
            return LBytePair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBytePair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LBytePair   {



        SELF first(byte first) ; 
        SELF second(byte second) ; 

        default SELF setFirst(byte first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(byte first, LBytePredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(byte first, LBiBytePredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiBytePredicate predicate, byte first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(byte second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(byte second, LBytePredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(byte second, LBiBytePredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiBytePredicate predicate, byte second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first((byte)0);
                this.second((byte)0);
            return (SELF) this;
        }
    }






  public static  MutBytePair of() { 
      return of(  (byte)0 ,  (byte)0 );
  }
      

  public static  MutBytePair of(byte a1,byte a2){
        return new MutBytePair(a1,a2);
  }

  public static  MutBytePair copyOf(LBytePair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBytePair  extends AbstractBytePair implements Mut<MutBytePair>   {

        private  byte first;
        private  byte second;

        public MutBytePair(byte a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override byte first() {
            return first;
        }

        public @Override MutBytePair first(byte first)    {
            this.first = first;
            return this;
        }
            
        public @Override byte second() {
            return second;
        }

        public @Override MutBytePair second(byte second)    {
            this.second = second;
            return this;
        }
            












    }







  public static  ImmBytePair immutableOf(byte a1,byte a2){
        return new ImmBytePair(a1,a2);
  }

  public static  ImmBytePair immutableCopyOf(LBytePair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBytePair  extends AbstractBytePair    {

        private final byte first;
        private final byte second;

        public ImmBytePair(byte a1,byte a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override byte first() {
            return first;
        }

        public @Override byte second() {
            return second;
        }



    }




    public static  Iterator<LBytePair.MutBytePair> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBytePair::of);}
    public static  Iterator<LBytePair.ImmBytePair> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBytePair::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LBiByteFunction<R> factory) {
		return iterator(SA.byteIterator(), items, factory);
	}

    public static  Stream<LBytePair.MutBytePair> mutStream(IntStream items) { return stream(items, LBytePair::of);}
    public static  Stream<LBytePair.ImmBytePair> immStream(IntStream items) { return stream(items, LBytePair::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LBiByteFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aByte> sa, C source, LBiByteFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aByte> ia, C source, LBiByteFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aByte> sa, C source, LBiByteFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LToByteFunction<C> nextFunc = (LToByteFunction<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsByte(iterator);
                var a2 = nextFunc.applyAsByte(iterator);
				return factory.apply(a1,a2);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aByte> ia, C source, LBiByteFunction<R> factory) {

        int size = ia.size(source);
        LOiToByteFunction<C> oiFunc = (LOiToByteFunction<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsByte(source, index++);
                var a2 = oiFunc.applyAsByte(source, index++);
				return factory.apply(a1,a2);
            }
        };
    }




    public static  void forEach(IntStream items, LBiByteConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LBiByteConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2) -> {
            consumer.accept(a1,a2);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


