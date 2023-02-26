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
 * Exact equivalent of input parameters used in LSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtSingle extends LTuple<Short> , Comparable<LSrtSingle> 
  {

    int SIZE = 1;


    short value();

    default short first() {
        return value();
    }



    @Override default Short get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default short getShort(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LSrtSingle and calculates hash from it. */
    static  int argHashCode(short a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Short.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtSingle and checks if all values are equal. */
    static  boolean argEquals(short a, short b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LSrtSingle interface (among others) and their LSrtSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LSrtSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtSingle are allowed.
            if (!(two instanceof LSrtSingle)) {
                return false;
            }

            LSrtSingle other = (LSrtSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LSrtSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LSrtSingle are allowed.
            if (!(two instanceof LSrtSingle)) {
                return false;
            }

            LSrtSingle other = (LSrtSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
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
        default int compareTo(LSrtSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Short.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractSrtSingle extends Number  implements LSrtSingle {

        @Override
        public boolean equals(Object that) {
            return LSrtSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LSrtSingle.argHashCode(value());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
                sb.append(value());
            sb.append(')');
            return sb.toString();
        }


        @Override public byte byteValue() {
            return (byte)value();
        }

        @Override public short shortValue() {
            return (short)value();
        }

        @Override public int intValue() {
            return (int)value();
        }

        @Override public long longValue() {
            return (long)value();
        }

        @Override public float floatValue() {
            return (float)value();
        }

        @Override public double doubleValue() {
            return (double)value();
        }
    }





    /**
     * Mutable tuple.
     */

     interface  Mut<SELF extends Mut<SELF>>  extends LSrtSingle   {



        SELF value(short value) ; 

        default SELF setValue(short value) {
            this.value(value);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setValueIf(short value, LSrtPredicate predicate) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setValueIf(short value, LBiSrtPredicate predicate) {
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setValueIf(LBiSrtPredicate predicate, short value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value((short)0);
            return (SELF) this;
        }
    }






  public static  MutSrtSingle of() { 
      return of(  (short)0 );
  }
      

  public static  MutSrtSingle of(short a){
        return new MutSrtSingle(a);
  }

  public static  MutSrtSingle copyOf(LSrtSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutSrtSingle  extends AbstractSrtSingle implements Mut<MutSrtSingle>   {

        private  short value;

        public MutSrtSingle(short a){
            this.value = a;
        }


        public @Override short value() {
            return value;
        }

        public @Override MutSrtSingle value(short value)    {
            this.value = value;
            return this;
        }
            







    }







  public static  ImmSrtSingle immutableOf(short a){
        return new ImmSrtSingle(a);
  }

  public static  ImmSrtSingle immutableCopyOf(LSrtSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmSrtSingle  extends AbstractSrtSingle    {

        private final short value;

        public ImmSrtSingle(short a){
            this.value = a;
        }


        public @Override short value() {
            return value;
        }



    }






  public static  AtomicSrtSingle atomicOf() { 
      return atomicOf(  (short)0 );
  }
      

  public static  AtomicSrtSingle atomicOf(short a){
        return new AtomicSrtSingle(a);
  }

  public static  AtomicSrtSingle atomicCopyOf(LSrtSingle tuple) {
        return atomicOf(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

    final  class  AtomicSrtSingle  extends AbstractSrtSingle implements Mut<AtomicSrtSingle>   {

        private volatile short value;

        public AtomicSrtSingle(short a){
            this.value = a;
        }


        public @Override short value() {
            return value;
        }

        public @Override AtomicSrtSingle value(short value)    {
            this.value = value;
            return this;
        }
            








        private static final  VarHandle vh;
        static {
            try {
                vh = MethodHandles
                .lookup()
                .in(AtomicSrtSingle.class)
                .findVarHandle(AtomicSrtSingle.class, "value", short.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        public short get() {
            return value();
        }

        public void set(short value) {
            value(value);
        }

        public void lazySet(short value) {
            vh.setRelease(this, value);
        }

        public short getAndSet(short value) {
            return (short) vh.getAndSet(this, value);
        }

        public boolean compareAndSet(short expected, short value) {
            return vh.compareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetPlain(short expected, short value) {
            return vh.weakCompareAndSetPlain(this, expected, value);
        }

        public short getAndIncrement() {
            return getAndAdd((short)1);
        }

        public short getAndDecrement() {
            return getAndAdd((short)-1);
        }

        public  short getAndAdd(short delta) {
            return (short) vh.getAndAdd(this, delta);
        }

        public short incrementAndGet() {
            return addAndGet((short)1);
        }

        public short decrementAndGet() {
            return addAndGet((short)-1);
        }

        public short addAndGet(short delta) {
            return (short) ((short)vh.getAndAdd(this, delta) + delta);
        }

        public final short getAndUpdate(LSrtUnaryOperator updateFunction) {
            short prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsSrt(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final short updateAndGet(LSrtUnaryOperator updateFunction) {
            short prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsSrt(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public final short getAndAccumulate(short x, LSrtBinaryOperator accumulatorFunction) {
            short prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsSrt(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final short accumulateAndGet(short x, LSrtBinaryOperator accumulatorFunction) {
            short prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsSrt(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public short getPlain() {
            return (short) vh.get(this);
        }

        public void setPlain(short value) {
            vh.set(this, value);
        }

        public short getOpaque() {
            return (short) vh.getOpaque(this);
        }

        public void setOpaque(short value) {
            vh.setOpaque(this, value);
        }

        public short getAcquire() {
            return (short) vh.getAcquire(this);
        }

        public void setRelease(short value) {
            vh.setRelease(this, value);
        }

        public short compareAndExchange(short expected, short value) {
            return (short) vh.compareAndExchange(this, expected, value);
        }

        public short compareAndExchangeAcquire(short expected, short value) {
            return (short) vh.compareAndExchangeAcquire(this, expected, value);
        }

        public short compareAndExchangeRelease(short expected, short value) {
            return (short) vh.compareAndExchangeRelease(this, expected, value);
        }

        public boolean weakCompareAndSetVolatile(short expected, short value) {
            return vh.weakCompareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetAcquire(short expected, short value) {
            return vh.weakCompareAndSetAcquire(this, expected, value);
        }

        public boolean weakCompareAndSetRelease(short expected, short value) {
            return vh.weakCompareAndSetRelease(this, expected, value);
        }

        /** Sets value if predicate(current) is true */
        public @Override AtomicSrtSingle setValueIf(short value, LSrtPredicate predicate) {
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
        public @Override AtomicSrtSingle setValueIf(short value, LBiSrtPredicate predicate) {
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
        public @Override AtomicSrtSingle setValueIf(LBiSrtPredicate predicate, short value) {
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


