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
 * Exact equivalent of input parameters used in LTriFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltTriple extends LTuple<Float> , Comparable<LFltTriple> 
  {

    int SIZE = 3;


    float first();

    default float value() {
        return first();
    }

    float second();

    float third();



    @Override default Float get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }

    default float getFloat(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LFltTriple and calculates hash from it. */
    static  int argHashCode(float a1,float a2,float a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Float.hashCode(a1);
            result = prime * result + Float.hashCode(a2);
            result = prime * result + Float.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LFltTriple and checks if all values are equal. */
    static  boolean argEquals(float a1,float a2,float a3, float b1,float b2,float b3) {
        return
            a1==b1 &&  //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LFltTriple interface (among others) and their LFltTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltTriple are allowed.
            if (!(two instanceof LFltTriple)) {
                return false;
            }

            LFltTriple other = (LFltTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LFltTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltTriple are allowed.
            if (!(two instanceof LFltTriple)) {
                return false;
            }

            LFltTriple other = (LFltTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }



        
    @Override default Iterator<Float> iterator() {
        return new Iterator<Float>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Float next() {
                index++;
                return get(index);
            }
        };
    }


    default PrimitiveIterator.OfDouble doubleIterator() {
        return new PrimitiveIterator.OfDouble() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public double nextDouble() {
                index++;
                return getFloat(index);
            }
        };
    }
        @Override
        default int compareTo(LFltTriple that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Float.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractFltTriple implements LFltTriple {

        @Override
        public boolean equals(Object that) {
            return LFltTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LFltTriple.argHashCode(first(),second(),third());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LFltTriple   {



        SELF first(float first) ; 
        SELF second(float second) ; 
        SELF third(float third) ; 

        default SELF setFirst(float first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(float first, LFltPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(float first, LBiFltPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiFltPredicate predicate, float first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(float second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(float second, LFltPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(float second, LBiFltPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiFltPredicate predicate, float second) {
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
                this.first(0f);
                this.second(0f);
                this.third(0f);
            return (SELF) this;
        }
    }






  public static  MutFltTriple of() { 
      return of(  0f ,  0f ,  0f );
  }
      

  public static  MutFltTriple of(float a1,float a2,float a3){
        return new MutFltTriple(a1,a2,a3);
  }

  public static  MutFltTriple copyOf(LFltTriple tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutFltTriple  extends AbstractFltTriple implements Mut<MutFltTriple>   {

        private  float first;
        private  float second;
        private  float third;

        public MutFltTriple(float a1,float a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override float first() {
            return first;
        }

        public @Override MutFltTriple first(float first)    {
            this.first = first;
            return this;
        }
            
        public @Override float second() {
            return second;
        }

        public @Override MutFltTriple second(float second)    {
            this.second = second;
            return this;
        }
            
        public @Override float third() {
            return third;
        }

        public @Override MutFltTriple third(float third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static  ImmFltTriple immutableOf(float a1,float a2,float a3){
        return new ImmFltTriple(a1,a2,a3);
  }

  public static  ImmFltTriple immutableCopyOf(LFltTriple tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmFltTriple  extends AbstractFltTriple    {

        private final float first;
        private final float second;
        private final float third;

        public ImmFltTriple(float a1,float a2,float a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override float first() {
            return first;
        }

        public @Override float second() {
            return second;
        }

        public @Override float third() {
            return third;
        }



    }




    public static  Iterator<LFltTriple.MutFltTriple> mutIterator(PrimitiveIterator.OfDouble items) { return iterator(items, LFltTriple::of);}
    public static  Iterator<LFltTriple.ImmFltTriple> immIterator(PrimitiveIterator.OfDouble items) { return iterator(items, LFltTriple::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfDouble items, LTriFltFunction<R> factory) {
		return iterator(SA.floatIterator(), items, factory);
	}

    public static  Stream<LFltTriple.MutFltTriple> mutStream(DoubleStream items) { return stream(items, LFltTriple::of);}
    public static  Stream<LFltTriple.ImmFltTriple> immStream(DoubleStream items) { return stream(items, LFltTriple::immutableOf);}

	public static <R> Stream<R> stream(DoubleStream items, LTriFltFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aFloat> sa, C source, LTriFltFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aFloat> ia, C source, LTriFltFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aFloat> sa, C source, LTriFltFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LToFltFunction<C> nextFunc = (LToFltFunction<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsFlt(iterator);
                var a2 = nextFunc.applyAsFlt(iterator);
                var a3 = nextFunc.applyAsFlt(iterator);
				return factory.apply(a1,a2,a3);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aFloat> ia, C source, LTriFltFunction<R> factory) {

        int size = ia.size(source);
        LOiToFltFunction<C> oiFunc = (LOiToFltFunction<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsFlt(source, index++);
                var a2 = oiFunc.applyAsFlt(source, index++);
                var a3 = oiFunc.applyAsFlt(source, index++);
				return factory.apply(a1,a2,a3);
            }
        };
    }




    public static  void forEach(DoubleStream items, LTriFltConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfDouble items, LTriFltConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3) -> {
            consumer.accept(a1,a2,a3);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


