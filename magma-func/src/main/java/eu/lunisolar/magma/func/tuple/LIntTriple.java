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
 * Exact equivalent of input parameters used in LTriIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntTriple extends LTuple<Integer> , Comparable<LIntTriple> 
  {

    int SIZE = 3;


    int first();

    default int value() {
        return first();
    }

    int second();

    int third();



    @Override default Integer get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }

    default int getInt(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LIntTriple and calculates hash from it. */
    static  int argHashCode(int a1,int a2,int a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Integer.hashCode(a1);
            result = prime * result + Integer.hashCode(a2);
            result = prime * result + Integer.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LIntTriple and checks if all values are equal. */
    static  boolean argEquals(int a1,int a2,int a3, int b1,int b2,int b3) {
        return
            a1==b1 &&  //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LIntTriple interface (among others) and their LIntTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LIntTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LIntTriple are allowed.
            if (!(two instanceof LIntTriple)) {
                return false;
            }

            LIntTriple other = (LIntTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LIntTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LIntTriple are allowed.
            if (!(two instanceof LIntTriple)) {
                return false;
            }

            LIntTriple other = (LIntTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }



        
    @Override default Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Integer next() {
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
                return getInt(index);
            }
        };
    }
        @Override
        default int compareTo(LIntTriple that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Integer.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Integer.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractIntTriple implements LIntTriple {

        @Override
        public boolean equals(Object that) {
            return LIntTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LIntTriple.argHashCode(first(),second(),third());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LIntTriple   {



        SELF first(int first) ; 
        SELF second(int second) ; 
        SELF third(int third) ; 

        default SELF setFirst(int first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(int first, LIntPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(int first, LBiIntPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiIntPredicate predicate, int first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(int second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(int second, LIntPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(int second, LBiIntPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiIntPredicate predicate, int second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF setThird(int third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(int third, LIntPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(int third, LBiIntPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiIntPredicate predicate, int third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first(0);
                this.second(0);
                this.third(0);
            return (SELF) this;
        }
    }





  public static  MutIntTriple of() {
      return of(  0 ,  0 ,  0 );
  }
      

  public static  MutIntTriple of(int a1,int a2,int a3){
        return new MutIntTriple(a1,a2,a3);
  }

  public static  MutIntTriple copyOf(LIntTriple tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutIntTriple  extends AbstractIntTriple implements Mut<MutIntTriple>   {

        private  int first;
        private  int second;
        private  int third;

        public MutIntTriple(int a1,int a2,int a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override int first() {
            return first;
        }

        public @Override MutIntTriple first(int first)    {
            this.first = first;
            return this;
        }
            
        public @Override int second() {
            return second;
        }

        public @Override MutIntTriple second(int second)    {
            this.second = second;
            return this;
        }
            
        public @Override int third() {
            return third;
        }

        public @Override MutIntTriple third(int third)    {
            this.third = third;
            return this;
        }
            

















    }






  public static  LIntTriple immutableOf(int a1,int a2,int a3){
        return new ImmIntTriple(a1,a2,a3);
  }

  public static  LIntTriple immutableCopyOf(LIntTriple tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmIntTriple  extends AbstractIntTriple    {

        private final int first;
        private final int second;
        private final int third;

        public ImmIntTriple(int a1,int a2,int a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override int first() {
            return first;
        }

        public @Override int second() {
            return second;
        }

        public @Override int third() {
            return third;
        }



    }




    public static  Iterator<LIntTriple.MutIntTriple> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LIntTriple::of);}
    public static  Iterator<LIntTriple> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LIntTriple::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LTriIntFunction<R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

    public static  Stream<LIntTriple.MutIntTriple> mutStream(IntStream items) { return stream(items, LIntTriple::of);}
    public static  Stream<LIntTriple> immStream(IntStream items) { return stream(items, LIntTriple::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LTriIntFunction<R> factory) {
       var pairs = iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aInt> sa, C source, LTriIntFunction<R> factory) {
       var pairs = iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aInt> ia, C source, LTriIntFunction<R> factory) {
       var pairs = iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aInt> sa_, C source, LTriIntFunction<R> factory) {

        var sa = (SequentialRead<C, Object, aInt>) sa_;
        var iterator = SA.adapter(sa).apply(source);
        var testFunc = SA.tester(sa);
        var nextFunc = SA.intSupplier(sa);

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsInt(iterator);
                var a2 = nextFunc.applyAsInt(iterator);
                var a3 = nextFunc.applyAsInt(iterator);
				return factory.apply(a1,a2,a3);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aInt> ia, C source, LTriIntFunction<R> factory) {

        int size = ia.size(source);
        var oiFunc = IA.intGetter(ia);

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsInt(source, index++);
                var a2 = oiFunc.applyAsInt(source, index++);
                var a3 = oiFunc.applyAsInt(source, index++);
				return factory.apply(a1,a2,a3);
            }
        };
    }




    public static  void forEach(IntStream items, LTriIntConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LTriIntConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3) -> {
            consumer.accept(a1,a2,a3);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


