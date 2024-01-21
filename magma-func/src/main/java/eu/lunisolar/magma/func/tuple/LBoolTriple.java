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
 * Exact equivalent of input parameters used in LTriBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolTriple extends LTuple<Boolean> , Comparable<LBoolTriple> 
  {

    int SIZE = 3;


    boolean first();

    default boolean value() {
        return first();
    }

    boolean second();

    boolean third();



    @Override default Boolean get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }

    default boolean getBoolean(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LBoolTriple and calculates hash from it. */
    static  int argHashCode(boolean a1,boolean a2,boolean a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Boolean.hashCode(a1);
            result = prime * result + Boolean.hashCode(a2);
            result = prime * result + Boolean.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolTriple and checks if all values are equal. */
    static  boolean argEquals(boolean a1,boolean a2,boolean a3, boolean b1,boolean b2,boolean b3) {
        return
            a1==b1 &&  //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LBoolTriple interface (among others) and their LBoolTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LBoolTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolTriple are allowed.
            if (!(two instanceof LBoolTriple)) {
                return false;
            }

            LBoolTriple other = (LBoolTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LBoolTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LBoolTriple are allowed.
            if (!(two instanceof LBoolTriple)) {
                return false;
            }

            LBoolTriple other = (LBoolTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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
        default int compareTo(LBoolTriple that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Boolean.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractBoolTriple implements LBoolTriple {

        @Override
        public boolean equals(Object that) {
            return LBoolTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LBoolTriple.argHashCode(first(),second(),third());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LBoolTriple   {



        SELF first(boolean first) ; 
        SELF second(boolean second) ; 
        SELF third(boolean third) ; 

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
            


        default SELF setThird(boolean third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(boolean third, LLogicalOperator predicate) {
            if (predicate.apply(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(boolean third, LLogicalBinaryOperator predicate) {
            if (predicate.apply(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LLogicalBinaryOperator predicate, boolean third) {
            if (predicate.apply(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(false);
                this.second(false);
                this.third(false);
            return (SELF) this;
        }
    }





  public static  MutBoolTriple of() {
      return of(  false ,  false ,  false );
  }
      

  public static  MutBoolTriple of(boolean a1,boolean a2,boolean a3){
        return new MutBoolTriple(a1,a2,a3);
  }

  public static  MutBoolTriple copyOf(LBoolTriple tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutBoolTriple  extends AbstractBoolTriple implements Mut<MutBoolTriple>   {

        private  boolean first;
        private  boolean second;
        private  boolean third;

        public MutBoolTriple(boolean a1,boolean a2,boolean a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override MutBoolTriple first(boolean first)    {
            this.first = first;
            return this;
        }
            
        public @Override boolean second() {
            return second;
        }

        public @Override MutBoolTriple second(boolean second)    {
            this.second = second;
            return this;
        }
            
        public @Override boolean third() {
            return third;
        }

        public @Override MutBoolTriple third(boolean third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static  LBoolTriple immutableOf(boolean a1,boolean a2,boolean a3){
        return new ImmBoolTriple(a1,a2,a3);
  }

  public static  LBoolTriple immutableCopyOf(LBoolTriple tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmBoolTriple  extends AbstractBoolTriple    {

        private final boolean first;
        private final boolean second;
        private final boolean third;

        public ImmBoolTriple(boolean a1,boolean a2,boolean a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override boolean first() {
            return first;
        }

        public @Override boolean second() {
            return second;
        }

        public @Override boolean third() {
            return third;
        }



    }




    public static  Iterator<LBoolTriple.MutBoolTriple> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBoolTriple::of);}
    public static  Iterator<LBoolTriple> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LBoolTriple::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LTriBoolFunction<R> factory) {
		return iterator(SA.booleanIterator(), items, factory);
	}

    public static  Stream<LBoolTriple.MutBoolTriple> mutStream(IntStream items) { return stream(items, LBoolTriple::of);}
    public static  Stream<LBoolTriple> immStream(IntStream items) { return stream(items, LBoolTriple::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LTriBoolFunction<R> factory) {
       var pairs = iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aBool> sa, C source, LTriBoolFunction<R> factory) {
       var pairs = iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aBool> ia, C source, LTriBoolFunction<R> factory) {
       var pairs = iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aBool> sa_, C source, LTriBoolFunction<R> factory) {

        var sa = (SequentialRead<C, Object, aBool>) sa_;
        var iterator = SA.adapter(sa).apply(source);
        var testFunc = SA.tester(sa);
        var nextFunc = SA.boolSupplier(sa);

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.test(iterator);
                var a2 = nextFunc.test(iterator);
                var a3 = nextFunc.test(iterator);
				return factory.apply(a1,a2,a3);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aBool> ia, C source, LTriBoolFunction<R> factory) {

        int size = ia.size(source);
        var oiFunc = IA.boolGetter(ia);

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.test(source, index++);
                var a2 = oiFunc.test(source, index++);
                var a3 = oiFunc.test(source, index++);
				return factory.apply(a1,a2,a3);
            }
        };
    }




    public static  void forEach(IntStream items, LTriBoolConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LTriBoolConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3) -> {
            consumer.accept(a1,a2,a3);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


