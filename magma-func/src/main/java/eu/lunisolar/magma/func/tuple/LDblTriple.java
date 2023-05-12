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
 * Exact equivalent of input parameters used in LTriDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblTriple extends LTuple<Double> , Comparable<LDblTriple> 
  {

    int SIZE = 3;


    double first();

    default double value() {
        return first();
    }

    double second();

    double third();



    @Override default Double get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }

    default double getDouble(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LDblTriple and calculates hash from it. */
    static  int argHashCode(double a1,double a2,double a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Double.hashCode(a1);
            result = prime * result + Double.hashCode(a2);
            result = prime * result + Double.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LDblTriple and checks if all values are equal. */
    static  boolean argEquals(double a1,double a2,double a3, double b1,double b2,double b3) {
        return
            a1==b1 &&  //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LDblTriple interface (among others) and their LDblTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LDblTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LDblTriple are allowed.
            if (!(two instanceof LDblTriple)) {
                return false;
            }

            LDblTriple other = (LDblTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LDblTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LDblTriple are allowed.
            if (!(two instanceof LDblTriple)) {
                return false;
            }

            LDblTriple other = (LDblTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }



        
    @Override default Iterator<Double> iterator() {
        return new Iterator<Double>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Double next() {
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
                return getDouble(index);
            }
        };
    }
        @Override
        default int compareTo(LDblTriple that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Double.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractDblTriple implements LDblTriple {

        @Override
        public boolean equals(Object that) {
            return LDblTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LDblTriple.argHashCode(first(),second(),third());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LDblTriple   {



        SELF first(double first) ; 
        SELF second(double second) ; 
        SELF third(double third) ; 

        default SELF setFirst(double first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(double first, LDblPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(double first, LBiDblPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiDblPredicate predicate, double first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(double second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(double second, LDblPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(double second, LBiDblPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiDblPredicate predicate, double second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF setThird(double third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(double third, LDblPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(double third, LBiDblPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiDblPredicate predicate, double third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(0d);
                this.second(0d);
                this.third(0d);
            return (SELF) this;
        }
    }






  public static  MutDblTriple of() { 
      return of(  0d ,  0d ,  0d );
  }
      

  public static  MutDblTriple of(double a1,double a2,double a3){
        return new MutDblTriple(a1,a2,a3);
  }

  public static  MutDblTriple copyOf(LDblTriple tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutDblTriple  extends AbstractDblTriple implements Mut<MutDblTriple>   {

        private  double first;
        private  double second;
        private  double third;

        public MutDblTriple(double a1,double a2,double a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override double first() {
            return first;
        }

        public @Override MutDblTriple first(double first)    {
            this.first = first;
            return this;
        }
            
        public @Override double second() {
            return second;
        }

        public @Override MutDblTriple second(double second)    {
            this.second = second;
            return this;
        }
            
        public @Override double third() {
            return third;
        }

        public @Override MutDblTriple third(double third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static  ImmDblTriple immutableOf(double a1,double a2,double a3){
        return new ImmDblTriple(a1,a2,a3);
  }

  public static  ImmDblTriple immutableCopyOf(LDblTriple tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmDblTriple  extends AbstractDblTriple    {

        private final double first;
        private final double second;
        private final double third;

        public ImmDblTriple(double a1,double a2,double a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override double first() {
            return first;
        }

        public @Override double second() {
            return second;
        }

        public @Override double third() {
            return third;
        }



    }




    public static  Iterator<LDblTriple.MutDblTriple> mutIterator(PrimitiveIterator.OfDouble items) { return iterator(items, LDblTriple::of);}
    public static  Iterator<LDblTriple.ImmDblTriple> immIterator(PrimitiveIterator.OfDouble items) { return iterator(items, LDblTriple::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfDouble items, LTriDblFunction<R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

    public static  Stream<LDblTriple.MutDblTriple> mutStream(DoubleStream items) { return stream(items, LDblTriple::of);}
    public static  Stream<LDblTriple.ImmDblTriple> immStream(DoubleStream items) { return stream(items, LDblTriple::immutableOf);}

	public static <R> Stream<R> stream(DoubleStream items, LTriDblFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aDouble> sa, C source, LTriDblFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aDouble> ia, C source, LTriDblFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aDouble> sa, C source, LTriDblFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LToDblFunction<C> nextFunc = (LToDblFunction<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsDbl(iterator);
                var a2 = nextFunc.applyAsDbl(iterator);
                var a3 = nextFunc.applyAsDbl(iterator);
				return factory.apply(a1,a2,a3);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aDouble> ia, C source, LTriDblFunction<R> factory) {

        int size = ia.size(source);
        LOiToDblFunction<C> oiFunc = (LOiToDblFunction<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsDbl(source, index++);
                var a2 = oiFunc.applyAsDbl(source, index++);
                var a3 = oiFunc.applyAsDbl(source, index++);
				return factory.apply(a1,a2,a3);
            }
        };
    }




    public static  void forEach(DoubleStream items, LTriDblConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfDouble items, LTriDblConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3) -> {
            consumer.accept(a1,a2,a3);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


