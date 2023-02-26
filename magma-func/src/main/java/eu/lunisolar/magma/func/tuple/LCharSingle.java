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
import java.util.concurrent.atomic.*;
import java.lang.invoke.*;



/**
 * Exact equivalent of input parameters used in LCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharSingle extends LTuple<Character> , Comparable<LCharSingle> 
  {

    int SIZE = 1;


    char value();

    default char first() {
        return value();
    }



    @Override default Character get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default char getChar(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LCharSingle and calculates hash from it. */
    static  int argHashCode(char a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Character.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LCharSingle and checks if all values are equal. */
    static  boolean argEquals(char a, char b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LCharSingle interface (among others) and their LCharSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LCharSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharSingle are allowed.
            if (!(two instanceof LCharSingle)) {
                return false;
            }

            LCharSingle other = (LCharSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LCharSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LCharSingle are allowed.
            if (!(two instanceof LCharSingle)) {
                return false;
            }

            LCharSingle other = (LCharSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
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
        default int compareTo(LCharSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Character.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractCharSingle implements LCharSingle {

        @Override
        public boolean equals(Object that) {
            return LCharSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LCharSingle.argHashCode(value());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(value());
            sb.append(')');
            return sb.toString();
        }

    }





    /**
     * Mutable tuple.
     */

     interface  Mut<SELF extends Mut<SELF>>  extends LCharSingle   {



        SELF value(char value) ; 

        default SELF setValue(char value) {
            this.value(value);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setValueIf(char value, LCharPredicate predicate) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setValueIf(char value, LBiCharPredicate predicate) {
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setValueIf(LBiCharPredicate predicate, char value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value('\u0000');
            return (SELF) this;
        }
    }






  public static  MutCharSingle of() { 
      return of(  '\u0000' );
  }
      

  public static  MutCharSingle of(char a){
        return new MutCharSingle(a);
  }

  public static  MutCharSingle copyOf(LCharSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutCharSingle  extends AbstractCharSingle implements Mut<MutCharSingle>   {

        private  char value;

        public MutCharSingle(char a){
            this.value = a;
        }


        public @Override char value() {
            return value;
        }

        public @Override MutCharSingle value(char value)    {
            this.value = value;
            return this;
        }
            







    }







  public static  ImmCharSingle immutableOf(char a){
        return new ImmCharSingle(a);
  }

  public static  ImmCharSingle immutableCopyOf(LCharSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmCharSingle  extends AbstractCharSingle    {

        private final char value;

        public ImmCharSingle(char a){
            this.value = a;
        }


        public @Override char value() {
            return value;
        }



    }






  public static  AtomicCharSingle atomicOf() { 
      return atomicOf(  '\u0000' );
  }
      

  public static  AtomicCharSingle atomicOf(char a){
        return new AtomicCharSingle(a);
  }

  public static  AtomicCharSingle atomicCopyOf(LCharSingle tuple) {
        return atomicOf(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

    final  class  AtomicCharSingle  extends AbstractCharSingle implements Mut<AtomicCharSingle>   {

        private volatile char value;

        public AtomicCharSingle(char a){
            this.value = a;
        }


        public @Override char value() {
            return value;
        }

        public @Override AtomicCharSingle value(char value)    {
            this.value = value;
            return this;
        }
            








        private static final  VarHandle vh;
        static {
            try {
                vh = MethodHandles
                .lookup()
                .in(AtomicCharSingle.class)
                .findVarHandle(AtomicCharSingle.class, "value", char.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        public char get() {
            return value();
        }

        public void set(char value) {
            value(value);
        }

        public void lazySet(char value) {
            vh.setRelease(this, value);
        }

        public char getAndSet(char value) {
            return (char) vh.getAndSet(this, value);
        }

        public boolean compareAndSet(char expected, char value) {
            return vh.compareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetPlain(char expected, char value) {
            return vh.weakCompareAndSetPlain(this, expected, value);
        }

        public char getAndIncrement() {
            return getAndAdd((char)1);
        }

        public char getAndDecrement() {
            return getAndAdd((char)-1);
        }

        public  char getAndAdd(char delta) {
            return (char) vh.getAndAdd(this, delta);
        }

        public char incrementAndGet() {
            return addAndGet((char)1);
        }

        public char decrementAndGet() {
            return addAndGet((char)-1);
        }

        public char addAndGet(char delta) {
            return (char) ((char)vh.getAndAdd(this, delta) + delta);
        }

        public final char getAndUpdate(LCharUnaryOperator updateFunction) {
            char prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsChar(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final char updateAndGet(LCharUnaryOperator updateFunction) {
            char prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsChar(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public final char getAndAccumulate(char x, LCharBinaryOperator accumulatorFunction) {
            char prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsChar(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final char accumulateAndGet(char x, LCharBinaryOperator accumulatorFunction) {
            char prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsChar(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public char getPlain() {
            return (char) vh.get(this);
        }

        public void setPlain(char value) {
            vh.set(this, value);
        }

        public char getOpaque() {
            return (char) vh.getOpaque(this);
        }

        public void setOpaque(char value) {
            vh.setOpaque(this, value);
        }

        public char getAcquire() {
            return (char) vh.getAcquire(this);
        }

        public void setRelease(char value) {
            vh.setRelease(this, value);
        }

        public char compareAndExchange(char expected, char value) {
            return (char) vh.compareAndExchange(this, expected, value);
        }

        public char compareAndExchangeAcquire(char expected, char value) {
            return (char) vh.compareAndExchangeAcquire(this, expected, value);
        }

        public char compareAndExchangeRelease(char expected, char value) {
            return (char) vh.compareAndExchangeRelease(this, expected, value);
        }

        public boolean weakCompareAndSetVolatile(char expected, char value) {
            return vh.weakCompareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetAcquire(char expected, char value) {
            return vh.weakCompareAndSetAcquire(this, expected, value);
        }

        public boolean weakCompareAndSetRelease(char expected, char value) {
            return vh.weakCompareAndSetRelease(this, expected, value);
        }

        /** Sets value if predicate(current) is true */
        public @Override AtomicCharSingle setValueIf(char value, LCharPredicate predicate) {
            getAndAccumulate(value, (current, newValue)-> {
                if (predicate.test(current)) {
                    return newValue;
                } else {
                    return current;
                }
            });
            return this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        public @Override AtomicCharSingle setValueIf(char value, LBiCharPredicate predicate) {
            getAndAccumulate(value, (current, newValue)-> {
                if (predicate.test(newValue, current)) {
                    return newValue;
                } else {
                    return current;
                }
            });
            return this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        public @Override AtomicCharSingle setValueIf(LBiCharPredicate predicate, char value) {
            getAndAccumulate(value, (current, newValue)-> {
                if (predicate.test(current, newValue)) {
                    return newValue;
                } else {
                    return current;
                }
            });
            return this;
        }


    }



}


