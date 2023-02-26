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
 * Exact equivalent of input parameters used in LBiBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolPair extends LTuple<Boolean> , Comparable<LBoolPair> 
  {

    int SIZE = 2;


    boolean first();

    default boolean value() {
        return first();
    }

    boolean second();



    @Override default Boolean get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            default: throw new NoSuchElementException();
        }
    }

    default boolean getBoolean(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBoolPair and calculates hash from it. */
    static  int argHashCode(boolean a1,boolean a2) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Boolean.hashCode(a1);
            result = prime * result + Boolean.hashCode(a2);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolPair and checks if all values are equal. */
    static  boolean argEquals(boolean a1,boolean a2, boolean b1,boolean b2) {
        return
            a1==b1 &&  //
            a2==b2;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBoolPair interface (among others) and their LBoolPair values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBoolPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolPair are allowed.
            if (!(two instanceof LBoolPair)) {
                return false;
            }

            LBoolPair other = (LBoolPair) two;

            return argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBoolPair the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolPair are allowed.
            if (!(two instanceof LBoolPair)) {
                return false;
            }

            LBoolPair other = (LBoolPair) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), other.first(), other.second());
        });
    }



        
    @Override default Iterator<Boolean> iterator() {
        return new Iterator<Boolean>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Boolean next() {
                index++;
                return get(index);
            }
        };
    }

        @Override
        default int compareTo(LBoolPair that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractBoolPair implements LBoolPair {

        @Override
        public boolean equals(Object that) {
            return LBoolPair.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBoolPair.argHashCode(first(),second());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LBoolPair   {



        SELF first(boolean first) ; 
        SELF second(boolean second) ; 

        default SELF setFirst(boolean first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(boolean first, LLogicalOperator predicate) {
            if (predicate.apply(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
            if (predicate.apply(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LLogicalBinaryOperator predicate, boolean first) {
            if (predicate.apply(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(boolean second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(boolean second, LLogicalOperator predicate) {
            if (predicate.apply(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
            if (predicate.apply(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LLogicalBinaryOperator predicate, boolean second) {
            if (predicate.apply(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(false);
                this.second(false);
            return (SELF) this;
        }
    }






  public static  MutBoolPair of() { 
      return of(  false ,  false );
  }
      

  public static  MutBoolPair of(boolean a1,boolean a2){
        return new MutBoolPair(a1,a2);
  }

  public static  MutBoolPair copyOf(LBoolPair tuple) {
        return of(tuple.first(), tuple.second());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBoolPair  extends AbstractBoolPair implements Mut<MutBoolPair>   {

        private  boolean first;
        private  boolean second;

        public MutBoolPair(boolean a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override MutBoolPair first(boolean first)    {
            this.first = first;
            return this;
        }
            
        public @Override boolean second() {
            return second;
        }

        public @Override MutBoolPair second(boolean second)    {
            this.second = second;
            return this;
        }
            












    }







  public static  ImmBoolPair immutableOf(boolean a1,boolean a2){
        return new ImmBoolPair(a1,a2);
  }

  public static  ImmBoolPair immutableCopyOf(LBoolPair tuple) {
        return immutableOf(tuple.first(), tuple.second());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBoolPair  extends AbstractBoolPair    {

        private final boolean first;
        private final boolean second;

        public ImmBoolPair(boolean a1,boolean a2){
            this.first = a1;
            this.second = a2;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override boolean second() {
            return second;
        }



    }




    public static  Iterator<LBoolPair.MutBoolPair> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBoolPair::of);}
    public static  Iterator<LBoolPair.ImmBoolPair> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBoolPair::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LBiBoolFunction<R> factory) {
		return iterator(SA.booleanIterator(), items, factory);
	}

    public static  Stream<LBoolPair.MutBoolPair> mutStream(IntStream items) { return stream(items, LBoolPair::of);}
    public static  Stream<LBoolPair.ImmBoolPair> immStream(IntStream items) { return stream(items, LBoolPair::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LBiBoolFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aBool> sa, C source, LBiBoolFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aBool> ia, C source, LBiBoolFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aBool> sa, C source, LBiBoolFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LPredicate<C> nextFunc = (LPredicate<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.test(iterator);
                var a2 = nextFunc.test(iterator);
				return factory.apply(a1,a2);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aBool> ia, C source, LBiBoolFunction<R> factory) {

        int size = ia.size(source);
        LObjIntPredicate<C> oiFunc = (LObjIntPredicate<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.test(source, index++);
                var a2 = oiFunc.test(source, index++);
				return factory.apply(a1,a2);
            }
        };
    }




    public static  void forEach(IntStream items, LBiBoolConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LBiBoolConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2) -> {
            consumer.accept(a1,a2);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


