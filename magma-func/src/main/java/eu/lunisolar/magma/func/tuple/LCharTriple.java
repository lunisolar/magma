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
 * Exact equivalent of input parameters used in LTriCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharTriple extends LTuple<Character> , Comparable<LCharTriple> 
  {

    int SIZE = 3;


    char first();

    default char value() {
        return first();
    }

    char second();

    char third();



    @Override default Character get(int index) {
        switch(index) {
            case 1: return first();
            case 2: return second();
            case 3: return third();
            default: throw new NoSuchElementException();
        }
    }

    default char getChar(int index) {
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

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LCharTriple and calculates hash from it. */
    static  int argHashCode(char a1,char a2,char a3) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Character.hashCode(a1);
            result = prime * result + Character.hashCode(a2);
            result = prime * result + Character.hashCode(a3);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LCharTriple and checks if all values are equal. */
    static  boolean argEquals(char a1,char a2,char a3, char b1,char b2,char b3) {
        return
            a1==b1 &&  //
            a2==b2 &&  //
            a3==b3;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LCharTriple interface (among others) and their LCharTriple values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LCharTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharTriple are allowed.
            if (!(two instanceof LCharTriple)) {
                return false;
            }

            LCharTriple other = (LCharTriple) two;

            return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LCharTriple the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharTriple are allowed.
            if (!(two instanceof LCharTriple)) {
                return false;
            }

            LCharTriple other = (LCharTriple) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
        });
    }



        
    @Override default Iterator<Character> iterator() {
        return new Iterator<Character>() {

            private int index;

            @Override public boolean hasNext() {
                return index<SIZE;
            }

            @Override public Character next() {
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
                return getChar(index);
            }
        };
    }
        @Override
        default int compareTo(LCharTriple that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
                    (retval = Character.compare(one.second(), two.second())) != 0 ? retval : //
                    (retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractCharTriple implements LCharTriple {

        @Override
        public boolean equals(Object that) {
            return LCharTriple.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LCharTriple.argHashCode(first(),second(),third());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LCharTriple   {



        SELF first(char first) ; 
        SELF second(char second) ; 
        SELF third(char third) ; 

        default SELF setFirst(char first) {
            this.first(first);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setFirstIf(char first, LCharPredicate predicate) {
            if (predicate.test(this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setFirstIf(char first, LBiCharPredicate predicate) {
            if (predicate.test(first, this.first())) {
                return this.first(first);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setFirstIf(LBiCharPredicate predicate, char first) {
            if (predicate.test(this.first(), first)) {
                return this.first(first);
            }
            return (SELF) this;
        }
            


        default SELF setSecond(char second) {
            this.second(second);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setSecondIf(char second, LCharPredicate predicate) {
            if (predicate.test(this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setSecondIf(char second, LBiCharPredicate predicate) {
            if (predicate.test(second, this.second())) {
                return this.second(second);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setSecondIf(LBiCharPredicate predicate, char second) {
            if (predicate.test(this.second(), second)) {
                return this.second(second);
            }
            return (SELF) this;
        }
            


        default SELF setThird(char third) {
            this.third(third);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setThirdIf(char third, LCharPredicate predicate) {
            if (predicate.test(this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setThirdIf(char third, LBiCharPredicate predicate) {
            if (predicate.test(third, this.third())) {
                return this.third(third);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setThirdIf(LBiCharPredicate predicate, char third) {
            if (predicate.test(this.third(), third)) {
                return this.third(third);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.first('\u0000');
                this.second('\u0000');
                this.third('\u0000');
            return (SELF) this;
        }
    }






  public static  MutCharTriple of() { 
      return of(  '\u0000' ,  '\u0000' ,  '\u0000' );
  }
      

  public static  MutCharTriple of(char a1,char a2,char a3){
        return new MutCharTriple(a1,a2,a3);
  }

  public static  MutCharTriple copyOf(LCharTriple tuple) {
        return of(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutCharTriple  extends AbstractCharTriple implements Mut<MutCharTriple>   {

        private  char first;
        private  char second;
        private  char third;

        public MutCharTriple(char a1,char a2,char a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override char first() {
            return first;
        }

        public @Override MutCharTriple first(char first)    {
            this.first = first;
            return this;
        }
            
        public @Override char second() {
            return second;
        }

        public @Override MutCharTriple second(char second)    {
            this.second = second;
            return this;
        }
            
        public @Override char third() {
            return third;
        }

        public @Override MutCharTriple third(char third)    {
            this.third = third;
            return this;
        }
            

















    }







  public static  ImmCharTriple immutableOf(char a1,char a2,char a3){
        return new ImmCharTriple(a1,a2,a3);
  }

  public static  ImmCharTriple immutableCopyOf(LCharTriple tuple) {
        return immutableOf(tuple.first(), tuple.second(), tuple.third());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmCharTriple  extends AbstractCharTriple    {

        private final char first;
        private final char second;
        private final char third;

        public ImmCharTriple(char a1,char a2,char a3){
            this.first = a1;
            this.second = a2;
            this.third = a3;
        }


        public @Override char first() {
            return first;
        }

        public @Override char second() {
            return second;
        }

        public @Override char third() {
            return third;
        }



    }




    public static  Iterator<LCharTriple.MutCharTriple> mutIterator(PrimitiveIterator.OfInt items) { return iterator(items, LCharTriple::of);}
    public static  Iterator<LCharTriple.ImmCharTriple> immIterator(PrimitiveIterator.OfInt items) { return iterator(items, LCharTriple::immutableOf);}

   	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LTriCharFunction<R> factory) {
		return iterator(SA.charIterator(), items, factory);
	}

    public static  Stream<LCharTriple.MutCharTriple> mutStream(IntStream items) { return stream(items, LCharTriple::of);}
    public static  Stream<LCharTriple.ImmCharTriple> immStream(IntStream items) { return stream(items, LCharTriple::immutableOf);}

	public static <R> Stream<R> stream(IntStream items, LTriCharFunction<R> factory) {
       var pairs =  iterator(items.iterator(), factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(SequentialRead<C, ?, aChar> sa, C source, LTriCharFunction<R> factory) {
       var pairs =  iterator(sa, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Stream<R> stream(IndexedRead<C, aChar> ia, C source, LTriCharFunction<R> factory) {
       var pairs =  iterator(ia, source, factory);
       return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

    public static <C,R> Iterator<R> iterator(SequentialRead<C, ?, aChar> sa, C source, LTriCharFunction<R> factory) {

        C iterator = (C) ((LFunction) sa.adapter()).apply(source);
        LPredicate<C> testFunc = (LPredicate<C>) sa.tester();
        LToCharFunction<C> nextFunc = (LToCharFunction<C>) sa.supplier();

        return new Iterator<R>() {

            @Override public boolean hasNext() { return testFunc.doApplyAsBoolean(iterator);}

            @Override public R next() {
                var a1 = nextFunc.applyAsChar(iterator);
                var a2 = nextFunc.applyAsChar(iterator);
                var a3 = nextFunc.applyAsChar(iterator);
				return factory.apply(a1,a2,a3);
            }
        };
    }

    public static <C,R> Iterator<R> iterator(IndexedRead<C, aChar> ia, C source, LTriCharFunction<R> factory) {

        int size = ia.size(source);
        LOiToCharFunction<C> oiFunc = (LOiToCharFunction<C>) ia.getter();

        return new Iterator<R>() {

            private int index = 0;

            @Override public boolean hasNext() { return index < size;}

            @Override public R next() {
                var a1 = oiFunc.applyAsChar(source, index++);
                var a2 = oiFunc.applyAsChar(source, index++);
                var a3 = oiFunc.applyAsChar(source, index++);
				return factory.apply(a1,a2,a3);
            }
        };
    }




    public static  void forEach(IntStream items, LTriCharConsumer consumer) {
        forEach(items.iterator(), consumer);
    }

    public static  void forEach(PrimitiveIterator.OfInt items, LTriCharConsumer consumer) {
        var emptyTuples = iterator(items, (a1,a2,a3) -> {
            consumer.accept(a1,a2,a3);
            return null;
        });

        while (emptyTuples.hasNext()) {
            emptyTuples.next();
        }
    }


}


