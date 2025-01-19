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
import eu.lunisolar.magma.func.consumer.*;
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
 * Exact equivalent of input parameters used in LDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblSingle extends LTuple<Double>, Comparable<LDblSingle> {

	int SIZE = 1;

	double value();

	default double first() {
		return value();
	}

	@Override
	default Double get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default double getDouble(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	@Override
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblSingle and calculates hash from it. */
	static int argHashCode(double a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblSingle and checks if all values are equal. */
	static boolean argEquals(double a, double b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LDblSingle interface (among others) and their LDblSingle values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LDblSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblSingle are allowed.
			if (!(two instanceof LDblSingle)) {
				return false;
			}

			LDblSingle other = (LDblSingle) two;

			return argEquals(one.value(), other.value());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LDblSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblSingle are allowed.
			if (!(two instanceof LDblSingle)) {
				return false;
			}

			LDblSingle other = (LDblSingle) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
		});
	}

	@Override
	default Iterator<Double> iterator() {
		return new Iterator<Double>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Double next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfDouble doubleIterator() {
		return new PrimitiveIterator.OfDouble() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public double nextDouble() {
				index++;
				return getDouble(index);
			}
		};
	}

	@Override
	default int compareTo(LDblSingle that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Double.compare(one.value(), two.value())) != 0 ? retval : 0; //
		});
	}

	abstract class AbstractDblSingle extends Number implements LDblSingle {

		@Override
		public boolean equals(Object that) {
			return LDblSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(value());
			sb.append(')');
			return sb.toString();
		}

		@Override
		public byte byteValue() {
			return (byte) value();
		}

		@Override
		public short shortValue() {
			return (short) value();
		}

		@Override
		public int intValue() {
			return (int) value();
		}

		@Override
		public long longValue() {
			return (long) value();
		}

		@Override
		public float floatValue() {
			return (float) value();
		}

		@Override
		public double doubleValue() {
			return (double) value();
		}
	}

	/**
	 * Mutable tuple.
	 */
	interface Mut<SELF extends Mut<SELF>> extends LDblSingle {

		SELF value(double value);

		default SELF setValue(double value) {
			this.value(value);
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setValueIf(double value, LDblPredicate predicate) {
			if (predicate.test(this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setValueIf(double value, LBiDblPredicate predicate) {
			if (predicate.test(value, this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setValueIf(LBiDblPredicate predicate, double value) {
			if (predicate.test(this.value(), value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.value(0d);
			return (SELF) this;
		}

		default SELF add(double a1) {
			return value((value() + a1));
		}

		default SELF sub(double a1) {
			return value((value() - a1));
		}

		default SELF inc() {
			return add(1d);
		}

		default double incAndGet() {
			return inc().value();
		}

		default double getAndInc() {
			double v = value();
			inc();
			return v;
		}

		default SELF dec() {
			return sub(1d);
		}

		default double decAndGet() {
			return dec().value();
		}

		default double getAndDec() {
			double v = value();
			dec();
			return v;
		}
	}

	public static MutDblSingle of() {
		return of(0d);
	}

	public static MutDblSingle of(double a) {
		return new MutDblSingle(a);
	}

	public static MutDblSingle copyOf(LDblSingle tuple) {
		return of(tuple.value());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutDblSingle extends AbstractDblSingle implements Mut<MutDblSingle> {

		private double value;

		public MutDblSingle(double a) {
			this.value = a;
		}

		public @Override double value() {
			return value;
		}

		public @Override MutDblSingle value(double value) {
			this.value = value;
			return this;
		}

	}

	public static LDblSingle immutableOf(double a) {
		return new ImmDblSingle(a);
	}

	public static LDblSingle immutableCopyOf(LDblSingle tuple) {
		return immutableOf(tuple.value());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblSingle extends AbstractDblSingle {

		private final double value;

		public ImmDblSingle(double a) {
			this.value = a;
		}

		public @Override double value() {
			return value;
		}
	}

	public static AtomicDblSingle atomicOf() {
		return atomicOf(0d);
	}

	public static AtomicDblSingle atomicOf(double a) {
		return new AtomicDblSingle(a);
	}

	public static AtomicDblSingle atomicCopyOf(LDblSingle tuple) {
		return atomicOf(tuple.value());
	}

	public static Mut<?> of(boolean atomic, double a) {
		return atomic ? atomicOf(a) : of(a);
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class AtomicDblSingle extends AbstractDblSingle implements Mut<AtomicDblSingle> {

		private volatile double value;

		public AtomicDblSingle(double a) {
			this.value = a;
		}

		public @Override double value() {
			return value;
		}

		public @Override AtomicDblSingle value(double value) {
			this.value = value;
			return this;
		}

		public AtomicDblSingle add(

				double a1) {
			addAndGet(a1);
			return this;
		}

		public AtomicDblSingle sub(double a1) {
			addAndGet(-a1);
			return this;
		}

		public AtomicDblSingle inc() {
			incrementAndGet();
			return this;
		}

		public double incAndGet() {
			return incrementAndGet();
		}

		public double getAndInc() {
			return getAndIncrement();
		}

		public AtomicDblSingle dec() {
			decrementAndGet();
			return this;
		}

		public double decAndGet() {
			return decrementAndGet();
		}

		public double getAndDec() {
			return getAndDecrement();
		}

		private static final VarHandle vh;
		static {
			try {
				vh = MethodHandles.lookup().in(AtomicDblSingle.class).findVarHandle(AtomicDblSingle.class, "value", double.class);
			} catch (ReflectiveOperationException e) {
				throw new ExceptionInInitializerError(e);
			}
		}

		public double get() {
			return value();
		}

		public void set(double value) {
			value(value);
		}

		public void lazySet(double value) {
			vh.setRelease(this, value);
		}

		public double getAndSet(double value) {
			return (double) vh.getAndSet(this, value);
		}

		public boolean compareAndSet(double expected, double value) {
			return vh.compareAndSet(this, expected, value);
		}

		public boolean weakCompareAndSetPlain(double expected, double value) {
			return vh.weakCompareAndSetPlain(this, expected, value);
		}

		public double getAndIncrement() {
			return getAndAdd((double) 1);
		}

		public double getAndDecrement() {
			return getAndAdd((double) -1);
		}

		public double getAndAdd(double delta) {
			return (double) vh.getAndAdd(this, delta);
		}

		public double incrementAndGet() {
			return addAndGet((double) 1);
		}

		public double decrementAndGet() {
			return addAndGet((double) -1);
		}

		public double addAndGet(double delta) {
			return (double) ((double) vh.getAndAdd(this, delta) + delta);
		}

		public final double getAndUpdate(LDblUnaryOperator updateFunction) {
			double prev = get(), next = 0;
			for (boolean haveNext = false;;) {
				if (!haveNext)
					next = updateFunction.applyAsDbl(prev);
				if (weakCompareAndSetVolatile(prev, next))
					return prev;
				haveNext = (prev == (prev = get()));
			}
		}

		public final double updateAndGet(LDblUnaryOperator updateFunction) {
			double prev = get(), next = 0;
			for (boolean haveNext = false;;) {
				if (!haveNext)
					next = updateFunction.applyAsDbl(prev);
				if (weakCompareAndSetVolatile(prev, next))
					return next;
				haveNext = (prev == (prev = get()));
			}
		}

		public final double getAndAccumulate(double x, LDblBinaryOperator accumulatorFunction) {
			double prev = get(), next = 0;
			for (boolean haveNext = false;;) {
				if (!haveNext)
					next = accumulatorFunction.applyAsDbl(prev, x);
				if (weakCompareAndSetVolatile(prev, next))
					return prev;
				haveNext = (prev == (prev = get()));
			}
		}

		public final double accumulateAndGet(double x, LDblBinaryOperator accumulatorFunction) {
			double prev = get(), next = 0;
			for (boolean haveNext = false;;) {
				if (!haveNext)
					next = accumulatorFunction.applyAsDbl(prev, x);
				if (weakCompareAndSetVolatile(prev, next))
					return next;
				haveNext = (prev == (prev = get()));
			}
		}

		public double getPlain() {
			return (double) vh.get(this);
		}

		public void setPlain(double value) {
			vh.set(this, value);
		}

		public double getOpaque() {
			return (double) vh.getOpaque(this);
		}

		public void setOpaque(double value) {
			vh.setOpaque(this, value);
		}

		public double getAcquire() {
			return (double) vh.getAcquire(this);
		}

		public void setRelease(double value) {
			vh.setRelease(this, value);
		}

		public double compareAndExchange(double expected, double value) {
			return (double) vh.compareAndExchange(this, expected, value);
		}

		public double compareAndExchangeAcquire(double expected, double value) {
			return (double) vh.compareAndExchangeAcquire(this, expected, value);
		}

		public double compareAndExchangeRelease(double expected, double value) {
			return (double) vh.compareAndExchangeRelease(this, expected, value);
		}

		public boolean weakCompareAndSetVolatile(double expected, double value) {
			return vh.weakCompareAndSet(this, expected, value);
		}

		public boolean weakCompareAndSetAcquire(double expected, double value) {
			return vh.weakCompareAndSetAcquire(this, expected, value);
		}

		public boolean weakCompareAndSetRelease(double expected, double value) {
			return vh.weakCompareAndSetRelease(this, expected, value);
		}

		/** Sets value if predicate(current) is true */
		public @Override AtomicDblSingle setValueIf(double value, LDblPredicate predicate) {
			getAndAccumulate(value, (current, newValue) -> {
				if (predicate.test(current)) {
					return newValue;
				} else {
					return current;
				}
			});
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		public @Override AtomicDblSingle setValueIf(double value, LBiDblPredicate predicate) {
			getAndAccumulate(value, (current, newValue) -> {
				if (predicate.test(newValue, current)) {
					return newValue;
				} else {
					return current;
				}
			});
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		public @Override AtomicDblSingle setValueIf(LBiDblPredicate predicate, double value) {
			getAndAccumulate(value, (current, newValue) -> {
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
