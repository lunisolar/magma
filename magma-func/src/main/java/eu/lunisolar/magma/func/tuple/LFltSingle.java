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
 * Exact equivalent of input parameters used in LFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltSingle extends LTuple<Float> , Comparable<LFltSingle> 
  {

    int SIZE = 1;


    float value();

    default float first() {
        return value();
    }



    @Override default Float get(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    default float getFloat(int index) {
        switch(index) {
            case 1: return value();
            default: throw new NoSuchElementException();
        }
    }

    /** Tuple size */
    @Override default int tupleSize() {
        return SIZE;
    }

    

    /** Static hashCode() implementation method that takes same arguments as fields of the LFltSingle and calculates hash from it. */
    static  int argHashCode(float a) {
        final int prime = 31;
        int result = 1;
            result = prime * result + Float.hashCode(a);
        return result;
    }

    /** Static equals() implementation that takes same arguments (doubled) as fields of the LFltSingle and checks if all values are equal. */
    static  boolean argEquals(float a, float b) {
        return
            a==b;  //
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     * Tuples are considered equal if are implementing LFltSingle interface (among others) and their LFltSingle values are equal regardless of the implementing class
     * and how many more values there are.
     */
    static  boolean argEquals(LFltSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltSingle are allowed.
            if (!(two instanceof LFltSingle)) {
                return false;
            }

            LFltSingle other = (LFltSingle) two;

            return argEquals(one.value(), other.value());
        });
    }

    /**
     * Static equals() implementation that takes two tuples and checks if they are equal.
     */
    public static  boolean tupleEquals(LFltSingle the, Object that) {
        return Null.equals(the, that, (one, two) -> {
                // Intentionally all implementations of LFltSingle are allowed.
            if (!(two instanceof LFltSingle)) {
                return false;
            }

            LFltSingle other = (LFltSingle) two;

            return  one.tupleSize() == other.tupleSize() &&
                    argEquals(one.value(), other.value());
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
        default int compareTo(LFltSingle that) {
            return Null.compare(this, that, (one, two) -> {
                int retval = 0;

                return
                    (retval = Float.compare(one.value(), two.value())) != 0 ? retval : 0; //
            });
        }

    

    abstract class AbstractFltSingle extends Number  implements LFltSingle {

        @Override
        public boolean equals(Object that) {
            return LFltSingle.tupleEquals(this, that);
        }

        @Override
        public int hashCode() {
            return LFltSingle.argHashCode(value());
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

     interface  Mut<SELF extends Mut<SELF>>  extends LFltSingle   {



        SELF value(float value) ; 

        default SELF setValue(float value) {
            this.value(value);
            return (SELF) this;
        }


        /** Sets value if predicate(current) is true */
        default SELF setValueIf(float value, LFltPredicate predicate) {
            if (predicate.test(this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(newValue, current) is true. */
        default SELF setValueIf(float value, LBiFltPredicate predicate) {
            if (predicate.test(value, this.value())) {
                return this.value(value);
            }
            return (SELF) this;
        }

        /** Sets new value if predicate predicate(current, newValue) is true. */
        default SELF setValueIf(LBiFltPredicate predicate, float value) {
            if (predicate.test(this.value(), value)) {
                return this.value(value);
            }
            return (SELF) this;
        }
            


        default SELF reset()   {
                this.value(0f);
            return (SELF) this;
        }

    default SELF add(float a1) {
        return value( (value()+a1));
    }

    default SELF sub(float a1) {
        return value( (value()-a1));
    }

    default SELF inc() {
        return add(1f);
    }

    default float incAndGet() {
        return inc().value();
    }

    default float getAndInc() {
        float v = value();
        inc();
        return v;
    }

    default SELF dec() {
        return sub(1f);
    }

    default float decAndGet() {
        return dec().value();
    }

    default float getAndDec() {
        float v = value();
        dec();
        return v;
    }
    
    }






  public static  MutFltSingle of() { 
      return of(  0f );
  }
      

  public static  MutFltSingle of(float a){
        return new MutFltSingle(a);
  }

  public static  MutFltSingle copyOf(LFltSingle tuple) {
        return of(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

     class  MutFltSingle  extends AbstractFltSingle implements Mut<MutFltSingle>   {

        private  float value;

        public MutFltSingle(float a){
            this.value = a;
        }


        public @Override float value() {
            return value;
        }

        public @Override MutFltSingle value(float value)    {
            this.value = value;
            return this;
        }
            







    }







  public static  ImmFltSingle immutableOf(float a){
        return new ImmFltSingle(a);
  }

  public static  ImmFltSingle immutableCopyOf(LFltSingle tuple) {
        return immutableOf(tuple.value());
  }


    /**
     * Immutable, non-comparable tuple.
     */
@Immutable
    final  class  ImmFltSingle  extends AbstractFltSingle    {

        private final float value;

        public ImmFltSingle(float a){
            this.value = a;
        }


        public @Override float value() {
            return value;
        }



    }






  public static  AtomicFltSingle atomicOf() { 
      return atomicOf(  0f );
  }
      

  public static  AtomicFltSingle atomicOf(float a){
        return new AtomicFltSingle(a);
  }

  public static  AtomicFltSingle atomicCopyOf(LFltSingle tuple) {
        return atomicOf(tuple.value());
  }


    /**
     * Mutable, non-comparable tuple.
     */

    final  class  AtomicFltSingle  extends AbstractFltSingle implements Mut<AtomicFltSingle>   {

        private volatile float value;

        public AtomicFltSingle(float a){
            this.value = a;
        }


        public @Override float value() {
            return value;
        }

        public @Override AtomicFltSingle value(float value)    {
            this.value = value;
            return this;
        }
            








    public AtomicFltSingle add(


        float a1) {
        addAndGet(a1);
        return this;
    }

    public AtomicFltSingle sub(float a1) {
        addAndGet(-a1);
        return this;
    }

    public AtomicFltSingle inc() {
        incrementAndGet();
        return this;
    }

    public float incAndGet() {
        return incrementAndGet();
    }

    public float getAndInc() {
        return getAndIncrement();
    }

    public AtomicFltSingle dec() {
        decrementAndGet();
        return this;
    }

    public float decAndGet() {
        return decrementAndGet();
    }

    public float getAndDec() {
        return getAndDecrement();
    }
    

        private static final  VarHandle vh;
        static {
            try {
                vh = MethodHandles
                .lookup()
                .in(AtomicFltSingle.class)
                .findVarHandle(AtomicFltSingle.class, "value", float.class);
            } catch (ReflectiveOperationException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        public float get() {
            return value();
        }

        public void set(float value) {
            value(value);
        }

        public void lazySet(float value) {
            vh.setRelease(this, value);
        }

        public float getAndSet(float value) {
            return (float) vh.getAndSet(this, value);
        }

        public boolean compareAndSet(float expected, float value) {
            return vh.compareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetPlain(float expected, float value) {
            return vh.weakCompareAndSetPlain(this, expected, value);
        }

        public float getAndIncrement() {
            return getAndAdd((float)1);
        }

        public float getAndDecrement() {
            return getAndAdd((float)-1);
        }

        public  float getAndAdd(float delta) {
            return (float) vh.getAndAdd(this, delta);
        }

        public float incrementAndGet() {
            return addAndGet((float)1);
        }

        public float decrementAndGet() {
            return addAndGet((float)-1);
        }

        public float addAndGet(float delta) {
            return (float) ((float)vh.getAndAdd(this, delta) + delta);
        }

        public final float getAndUpdate(LFltUnaryOperator updateFunction) {
            float prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsFlt(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final float updateAndGet(LFltUnaryOperator updateFunction) {
            float prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = updateFunction.applyAsFlt(prev);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public final float getAndAccumulate(float x, LFltBinaryOperator accumulatorFunction) {
            float prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsFlt(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return prev;
                haveNext = (prev == (prev = get()));
            }
        }

        public final float accumulateAndGet(float x, LFltBinaryOperator accumulatorFunction) {
            float prev = get(), next = 0;
            for (boolean haveNext = false;;) {
                if (!haveNext)
                    next = accumulatorFunction.applyAsFlt(prev, x);
                if (weakCompareAndSetVolatile(prev, next))
                    return next;
                haveNext = (prev == (prev = get()));
            }
        }

        public float getPlain() {
            return (float) vh.get(this);
        }

        public void setPlain(float value) {
            vh.set(this, value);
        }

        public float getOpaque() {
            return (float) vh.getOpaque(this);
        }

        public void setOpaque(float value) {
            vh.setOpaque(this, value);
        }

        public float getAcquire() {
            return (float) vh.getAcquire(this);
        }

        public void setRelease(float value) {
            vh.setRelease(this, value);
        }

        public float compareAndExchange(float expected, float value) {
            return (float) vh.compareAndExchange(this, expected, value);
        }

        public float compareAndExchangeAcquire(float expected, float value) {
            return (float) vh.compareAndExchangeAcquire(this, expected, value);
        }

        public float compareAndExchangeRelease(float expected, float value) {
            return (float) vh.compareAndExchangeRelease(this, expected, value);
        }

        public boolean weakCompareAndSetVolatile(float expected, float value) {
            return vh.weakCompareAndSet(this, expected, value);
        }

        public boolean weakCompareAndSetAcquire(float expected, float value) {
            return vh.weakCompareAndSetAcquire(this, expected, value);
        }

        public boolean weakCompareAndSetRelease(float expected, float value) {
            return vh.weakCompareAndSetRelease(this, expected, value);
        }

        /** Sets value if predicate(current) is true */
        public @Override AtomicFltSingle setValueIf(float value, LFltPredicate predicate) {
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
        public @Override AtomicFltSingle setValueIf(float value, LBiFltPredicate predicate) {
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
        public @Override AtomicFltSingle setValueIf(LBiFltPredicate predicate, float value) {
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


