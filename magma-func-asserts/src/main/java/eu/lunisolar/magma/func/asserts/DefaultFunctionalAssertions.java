/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.asserts;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.math.*;
import java.time.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the usecase otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface DefaultFunctionalAssertions<OS extends Assert> extends BasicAssertions<OS> {

	default <RS extends Assert, R> DefaultFunctionalAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory) {
		return new DefaultFunctionalAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default <RS extends Assert, R> DefaultFunctionalAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory, Class<R> r, Class<RS> rs) {
		return new DefaultFunctionalAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractOptionalAssert> withinOptionalCodomain() {
		return new DefaultFunctionalAssertions<AbstractOptionalAssert>() {
			@Override
			public AbstractOptionalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Optional) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBigDecimalAssert> withinBigDecimalCodomain() {
		return new DefaultFunctionalAssertions<AbstractBigDecimalAssert>() {
			@Override
			public AbstractBigDecimalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((BigDecimal) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBooleanAssert> withinBooleanCodomain() {
		return new DefaultFunctionalAssertions<AbstractBooleanAssert>() {
			@Override
			public AbstractBooleanAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Boolean) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBooleanArrayAssert> withinBooleanArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractBooleanArrayAssert>() {
			@Override
			public AbstractBooleanArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((boolean[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractByteAssert> withinByteCodomain() {
		return new DefaultFunctionalAssertions<AbstractByteAssert>() {
			@Override
			public AbstractByteAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Byte) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractByteArrayAssert> withinByteArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractByteArrayAssert>() {
			@Override
			public AbstractByteArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((byte[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharacterAssert> withinCharacterCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharacterAssert>() {
			@Override
			public AbstractCharacterAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Character) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharArrayAssert> withinCharArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharArrayAssert>() {
			@Override
			public AbstractCharArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((char[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractClassAssert> withinClassCodomain() {
		return new DefaultFunctionalAssertions<AbstractClassAssert>() {
			@Override
			public AbstractClassAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Class) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractComparableAssert> withinComparableCodomain() {
		return new DefaultFunctionalAssertions<AbstractComparableAssert>() {
			@Override
			public AbstractComparableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Comparable) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIterableAssert> withinIterableCodomain() {
		return new DefaultFunctionalAssertions<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterable) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIterableAssert> withinIteratorCodomain() {
		return new DefaultFunctionalAssertions<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterator) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDoubleAssert> withinDoubleCodomain() {
		return new DefaultFunctionalAssertions<AbstractDoubleAssert>() {
			@Override
			public AbstractDoubleAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Double) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDoubleArrayAssert> withinDoubleArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractDoubleArrayAssert>() {
			@Override
			public AbstractDoubleArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((double[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractPathAssert> withinPathCodomain() {
		return new DefaultFunctionalAssertions<AbstractPathAssert>() {
			@Override
			public AbstractPathAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Path) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractInputStreamAssert> withinInputStreamCodomain() {
		return new DefaultFunctionalAssertions<AbstractInputStreamAssert>() {
			@Override
			public AbstractInputStreamAssert assertThatObj(Object actual) {
				return Assertions.assertThat((InputStream) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractFloatAssert> withinFloatCodomain() {
		return new DefaultFunctionalAssertions<AbstractFloatAssert>() {
			@Override
			public AbstractFloatAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Float) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractFloatArrayAssert> withinFloatArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractFloatArrayAssert>() {
			@Override
			public AbstractFloatArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((float[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIntegerAssert> withinIntegerCodomain() {
		return new DefaultFunctionalAssertions<AbstractIntegerAssert>() {
			@Override
			public AbstractIntegerAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Integer) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIntArrayAssert> withinIntArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractIntArrayAssert>() {
			@Override
			public AbstractIntArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((int[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractListAssert> withinListCodomain() {
		return new DefaultFunctionalAssertions<AbstractListAssert>() {
			@Override
			public AbstractListAssert assertThatObj(Object actual) {
				return Assertions.assertThat((List) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLongAssert> withinLongCodomain() {
		return new DefaultFunctionalAssertions<AbstractLongAssert>() {
			@Override
			public AbstractLongAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Long) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLongArrayAssert> withinLongArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractLongArrayAssert>() {
			@Override
			public AbstractLongArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((long[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractMapAssert> withinMapCodomain() {
		return new DefaultFunctionalAssertions<AbstractMapAssert>() {
			@Override
			public AbstractMapAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Map) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractShortAssert> withinShortCodomain() {
		return new DefaultFunctionalAssertions<AbstractShortAssert>() {
			@Override
			public AbstractShortAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Short) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractShortArrayAssert> withinShortArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractShortArrayAssert>() {
			@Override
			public AbstractShortArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((short[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharSequenceAssert> withinCharSequenceCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((CharSequence) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharSequenceAssert> withinStringCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((String) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDateAssert> withinDateCodomain() {
		return new DefaultFunctionalAssertions<AbstractDateAssert>() {
			@Override
			public AbstractDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Date) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractZonedDateTimeAssert> withinZonedDateTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractZonedDateTimeAssert>() {
			@Override
			public AbstractZonedDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((ZonedDateTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalDateTimeAssert> withinLocalDateTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalDateTimeAssert>() {
			@Override
			public AbstractLocalDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDateTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalTimeAssert> withinLocalTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalTimeAssert>() {
			@Override
			public AbstractLocalTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalDateAssert> withinLocalDateCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalDateAssert>() {
			@Override
			public AbstractLocalDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDate) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractThrowableAssert> withinThrowableCodomain() {
		return new DefaultFunctionalAssertions<AbstractThrowableAssert>() {
			@Override
			public AbstractThrowableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Throwable) actual);
			}
		};
	}

	default <T> DefaultFunctionalAssertions<AbstractObjectArrayAssert> withinTArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractObjectArrayAssert>() {
			@Override
			public AbstractObjectArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Object[]) actual);
			}
		};
	}

	@Nonnull
	default <A extends LAction> LActionAssert.The<A> assertThat(LAction functionalInterface) {
		return new LActionAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LActionX<X>, X extends Throwable> LActionXAssert.The<A, X> assertThat(LActionX<X> functionalInterface) {
		return new LActionXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertThat(LConsumer<T> functionalInterface) {
		return new LConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.The<A, T, X> assertThat(LConsumerX<T, X> functionalInterface) {
		return new LConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertThat(LBiConsumer<T1, T2> functionalInterface) {
		return new LBiConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.The<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> functionalInterface) {
		return new LBiConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> functionalInterface) {
		return new LTriConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.The<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> functionalInterface) {
		return new LTriConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LByteConsumer> LByteConsumerAssert.The<A> assertThat(LByteConsumer functionalInterface) {
		return new LByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.The<A, X> assertThat(LByteConsumerX<X> functionalInterface) {
		return new LByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LShortConsumer> LShortConsumerAssert.The<A> assertThat(LShortConsumer functionalInterface) {
		return new LShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.The<A, X> assertThat(LShortConsumerX<X> functionalInterface) {
		return new LShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LIntConsumer> LIntConsumerAssert.The<A> assertThat(LIntConsumer functionalInterface) {
		return new LIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.The<A, X> assertThat(LIntConsumerX<X> functionalInterface) {
		return new LIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LLongConsumer> LLongConsumerAssert.The<A> assertThat(LLongConsumer functionalInterface) {
		return new LLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.The<A, X> assertThat(LLongConsumerX<X> functionalInterface) {
		return new LLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatConsumer> LFloatConsumerAssert.The<A> assertThat(LFloatConsumer functionalInterface) {
		return new LFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.The<A, X> assertThat(LFloatConsumerX<X> functionalInterface) {
		return new LFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleConsumer> LDoubleConsumerAssert.The<A> assertThat(LDoubleConsumer functionalInterface) {
		return new LDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.The<A, X> assertThat(LDoubleConsumerX<X> functionalInterface) {
		return new LDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LCharConsumer> LCharConsumerAssert.The<A> assertThat(LCharConsumer functionalInterface) {
		return new LCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.The<A, X> assertThat(LCharConsumerX<X> functionalInterface) {
		return new LCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanConsumer> LBooleanConsumerAssert.The<A> assertThat(LBooleanConsumer functionalInterface) {
		return new LBooleanConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanConsumerX<X>, X extends Throwable> LBooleanConsumerXAssert.The<A, X> assertThat(LBooleanConsumerX<X> functionalInterface) {
		return new LBooleanConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertThat(LBiByteConsumer functionalInterface) {
		return new LBiByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiByteConsumerX<X>, X extends Throwable> LBiByteConsumerXAssert.The<A, X> assertThat(LBiByteConsumerX<X> functionalInterface) {
		return new LBiByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiShortConsumer> LBiShortConsumerAssert.The<A> assertThat(LBiShortConsumer functionalInterface) {
		return new LBiShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiShortConsumerX<X>, X extends Throwable> LBiShortConsumerXAssert.The<A, X> assertThat(LBiShortConsumerX<X> functionalInterface) {
		return new LBiShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertThat(LBiIntConsumer functionalInterface) {
		return new LBiIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiIntConsumerX<X>, X extends Throwable> LBiIntConsumerXAssert.The<A, X> assertThat(LBiIntConsumerX<X> functionalInterface) {
		return new LBiIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertThat(LBiLongConsumer functionalInterface) {
		return new LBiLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiLongConsumerX<X>, X extends Throwable> LBiLongConsumerXAssert.The<A, X> assertThat(LBiLongConsumerX<X> functionalInterface) {
		return new LBiLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiFloatConsumer> LBiFloatConsumerAssert.The<A> assertThat(LBiFloatConsumer functionalInterface) {
		return new LBiFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiFloatConsumerX<X>, X extends Throwable> LBiFloatConsumerXAssert.The<A, X> assertThat(LBiFloatConsumerX<X> functionalInterface) {
		return new LBiFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiDoubleConsumer> LBiDoubleConsumerAssert.The<A> assertThat(LBiDoubleConsumer functionalInterface) {
		return new LBiDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiDoubleConsumerX<X>, X extends Throwable> LBiDoubleConsumerXAssert.The<A, X> assertThat(LBiDoubleConsumerX<X> functionalInterface) {
		return new LBiDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertThat(LBiCharConsumer functionalInterface) {
		return new LBiCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiCharConsumerX<X>, X extends Throwable> LBiCharConsumerXAssert.The<A, X> assertThat(LBiCharConsumerX<X> functionalInterface) {
		return new LBiCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiBooleanConsumer> LBiBooleanConsumerAssert.The<A> assertThat(LBiBooleanConsumer functionalInterface) {
		return new LBiBooleanConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiBooleanConsumerX<X>, X extends Throwable> LBiBooleanConsumerXAssert.The<A, X> assertThat(LBiBooleanConsumerX<X> functionalInterface) {
		return new LBiBooleanConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LTriBooleanConsumer> LTriBooleanConsumerAssert.The<A> assertThat(LTriBooleanConsumer functionalInterface) {
		return new LTriBooleanConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LTriBooleanConsumerX<X>, X extends Throwable> LTriBooleanConsumerXAssert.The<A, X> assertThat(LTriBooleanConsumerX<X> functionalInterface) {
		return new LTriBooleanConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertThat(LObjByteConsumer<T> functionalInterface) {
		return new LObjByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.The<A, T, X> assertThat(LObjByteConsumerX<T, X> functionalInterface) {
		return new LObjByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.The<A, T> assertThat(LObjShortConsumer<T> functionalInterface) {
		return new LObjShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.The<A, T, X> assertThat(LObjShortConsumerX<T, X> functionalInterface) {
		return new LObjShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertThat(LObjIntConsumer<T> functionalInterface) {
		return new LObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.The<A, T, X> assertThat(LObjIntConsumerX<T, X> functionalInterface) {
		return new LObjIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertThat(LObjLongConsumer<T> functionalInterface) {
		return new LObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.The<A, T, X> assertThat(LObjLongConsumerX<T, X> functionalInterface) {
		return new LObjLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.The<A, T> assertThat(LObjFloatConsumer<T> functionalInterface) {
		return new LObjFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.The<A, T, X> assertThat(LObjFloatConsumerX<T, X> functionalInterface) {
		return new LObjFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.The<A, T> assertThat(LObjDoubleConsumer<T> functionalInterface) {
		return new LObjDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.The<A, T, X> assertThat(LObjDoubleConsumerX<T, X> functionalInterface) {
		return new LObjDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertThat(LObjCharConsumer<T> functionalInterface) {
		return new LObjCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.The<A, T, X> assertThat(LObjCharConsumerX<T, X> functionalInterface) {
		return new LObjCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjBooleanConsumer<T>, T> LObjBooleanConsumerAssert.The<A, T> assertThat(LObjBooleanConsumer<T> functionalInterface) {
		return new LObjBooleanConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LObjBooleanConsumerX<T, X>, T, X extends Throwable> LObjBooleanConsumerXAssert.The<A, T, X> assertThat(LObjBooleanConsumerX<T, X> functionalInterface) {
		return new LObjBooleanConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> functionalInterface) {
		return new LBiObjByteConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjByteConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.The<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> functionalInterface) {
		return new LBiObjShortConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjShortConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> functionalInterface) {
		return new LBiObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjIntConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> functionalInterface) {
		return new LBiObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjLongConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.The<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> functionalInterface) {
		return new LBiObjFloatConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.The<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> functionalInterface) {
		return new LBiObjDoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjDoubleConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> functionalInterface) {
		return new LBiObjCharConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjCharConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjBooleanConsumer<T1, T2>, T1, T2> LBiObjBooleanConsumerAssert.The<A, T1, T2> assertThat(LBiObjBooleanConsumer<T1, T2> functionalInterface) {
		return new LBiObjBooleanConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjBooleanConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBooleanConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjBooleanConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjBooleanConsumerXAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends LUnaryOperator<T>, T> LUnaryOperatorAssert.The<A, ? extends OS, T> assertThat(LUnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperatorX<T, X>, T, X extends Throwable> LUnaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LUnaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBinaryOperator<T>, T> LBinaryOperatorAssert.The<A, ? extends OS, T> assertThat(LBinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBinaryOperatorX<T, X>, T, X extends Throwable> LBinaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LBinaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperator<T>, T> LTernaryOperatorAssert.The<A, ? extends OS, T> assertThat(LTernaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperatorX<T, X>, T, X extends Throwable> LTernaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LTernaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperator> LByteUnaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteUnaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperatorX<X>, X extends Throwable> LByteUnaryOperatorXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteUnaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperator> LShortUnaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortUnaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperatorX<X>, X extends Throwable> LShortUnaryOperatorXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortUnaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperator> LIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperatorX<X>, X extends Throwable> LIntUnaryOperatorXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntUnaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperator> LLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperatorX<X>, X extends Throwable> LLongUnaryOperatorXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongUnaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperator> LFloatUnaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatUnaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperatorX<X>, X extends Throwable> LFloatUnaryOperatorXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatUnaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperator> LDoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperatorX<X>, X extends Throwable> LDoubleUnaryOperatorXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleUnaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperator> LCharUnaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharUnaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperatorX<X>, X extends Throwable> LCharUnaryOperatorXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharUnaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperator> LLogicalOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperatorX<X>, X extends Throwable> LLogicalOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperator> LByteBinaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteBinaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperatorX<X>, X extends Throwable> LByteBinaryOperatorXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteBinaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperator> LShortBinaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortBinaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperatorX<X>, X extends Throwable> LShortBinaryOperatorXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortBinaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperator> LIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperatorX<X>, X extends Throwable> LIntBinaryOperatorXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntBinaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperator> LLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperatorX<X>, X extends Throwable> LLongBinaryOperatorXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongBinaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperator> LFloatBinaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatBinaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperatorX<X>, X extends Throwable> LFloatBinaryOperatorXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatBinaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperator> LDoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperatorX<X>, X extends Throwable> LDoubleBinaryOperatorXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleBinaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperator> LCharBinaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharBinaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperatorX<X>, X extends Throwable> LCharBinaryOperatorXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharBinaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperator> LLogicalBinaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalBinaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperatorX<X>, X extends Throwable> LLogicalBinaryOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalBinaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalBinaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperator> LLogicalTernaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalTernaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalTernaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperatorX<X>, X extends Throwable> LLogicalTernaryOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalTernaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalTernaryOperatorXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFunction<T, R>, T, R> LFunctionAssert.The<A, ? extends OS, T, R> assertThat(LFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFunctionX<T, R, X>, T, R, X extends Throwable> LFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction<T1, T2, R>, T1, T2, R> LBiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, ? extends OS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunctionX<T1, T2, T3, R, X>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.The<A, ? extends OS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunction<R>, R> LByteFunctionAssert.The<A, ? extends OS, R> assertThat(LByteFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunctionX<R, X>, R, X extends Throwable> LByteFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LByteFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunction<R>, R> LShortFunctionAssert.The<A, ? extends OS, R> assertThat(LShortFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunctionX<R, X>, R, X extends Throwable> LShortFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LShortFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunction<R>, R> LIntFunctionAssert.The<A, ? extends OS, R> assertThat(LIntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunctionX<R, X>, R, X extends Throwable> LIntFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LIntFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunction<R>, R> LLongFunctionAssert.The<A, ? extends OS, R> assertThat(LLongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunctionX<R, X>, R, X extends Throwable> LLongFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LLongFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunction<R>, R> LFloatFunctionAssert.The<A, ? extends OS, R> assertThat(LFloatFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunctionX<R, X>, R, X extends Throwable> LFloatFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LFloatFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunction<R>, R> LDoubleFunctionAssert.The<A, ? extends OS, R> assertThat(LDoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunctionX<R, X>, R, X extends Throwable> LDoubleFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LDoubleFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunction<R>, R> LCharFunctionAssert.The<A, ? extends OS, R> assertThat(LCharFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunctionX<R, X>, R, X extends Throwable> LCharFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LCharFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanFunction<R>, R> LBooleanFunctionAssert.The<A, ? extends OS, R> assertThat(LBooleanFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanFunctionX<R, X>, R, X extends Throwable> LBooleanFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBooleanFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction<R>, R> LBiByteFunctionAssert.The<A, ? extends OS, R> assertThat(LBiByteFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunctionX<R, X>, R, X extends Throwable> LBiByteFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiByteFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortFunction<R>, R> LBiShortFunctionAssert.The<A, ? extends OS, R> assertThat(LBiShortFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortFunctionX<R, X>, R, X extends Throwable> LBiShortFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiShortFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction<R>, R> LBiIntFunctionAssert.The<A, ? extends OS, R> assertThat(LBiIntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunctionX<R, X>, R, X extends Throwable> LBiIntFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiIntFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction<R>, R> LBiLongFunctionAssert.The<A, ? extends OS, R> assertThat(LBiLongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunctionX<R, X>, R, X extends Throwable> LBiLongFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiLongFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatFunction<R>, R> LBiFloatFunctionAssert.The<A, ? extends OS, R> assertThat(LBiFloatFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatFunctionX<R, X>, R, X extends Throwable> LBiFloatFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiFloatFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoubleFunction<R>, R> LBiDoubleFunctionAssert.The<A, ? extends OS, R> assertThat(LBiDoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoubleFunctionX<R, X>, R, X extends Throwable> LBiDoubleFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiDoubleFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction<R>, R> LBiCharFunctionAssert.The<A, ? extends OS, R> assertThat(LBiCharFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunctionX<R, X>, R, X extends Throwable> LBiCharFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiCharFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBooleanFunction<R>, R> LBiBooleanFunctionAssert.The<A, ? extends OS, R> assertThat(LBiBooleanFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiBooleanFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBooleanFunctionX<R, X>, R, X extends Throwable> LBiBooleanFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiBooleanFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiBooleanFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriBooleanFunction<R>, R> LTriBooleanFunctionAssert.The<A, ? extends OS, R> assertThat(LTriBooleanFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriBooleanFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriBooleanFunctionX<R, X>, R, X extends Throwable> LTriBooleanFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LTriBooleanFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriBooleanFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjByteFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunctionX<T, R, X>, T, R, X extends Throwable> LObjByteFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunction<T, R>, T, R> LObjShortFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjShortFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunctionX<T, R, X>, T, R, X extends Throwable> LObjShortFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunction<T, R>, T, R> LObjIntFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjIntFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunctionX<T, R, X>, T, R, X extends Throwable> LObjIntFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjLongFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunctionX<T, R, X>, T, R, X extends Throwable> LObjLongFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunction<T, R>, T, R> LObjFloatFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjFloatFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunctionX<T, R, X>, T, R, X extends Throwable> LObjFloatFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunction<T, R>, T, R> LObjDoubleFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjDoubleFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunctionX<T, R, X>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjCharFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunctionX<T, R, X>, T, R, X extends Throwable> LObjCharFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanFunction<T, R>, T, R> LObjBooleanFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjBooleanFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBooleanFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanFunctionX<T, R, X>, T, R, X extends Throwable> LObjBooleanFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjBooleanFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBooleanFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunction<T1, T2, R>, T1, T2, R> LBiObjShortFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunction<T1, T2, R>, T1, T2, R> LBiObjFloatFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunction<T1, T2, R>, T1, T2, R> LBiObjDoubleFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanFunction<T1, T2, R>, T1, T2, R> LBiObjBooleanFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjBooleanFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBooleanFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjBooleanFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjBooleanFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBooleanFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunction<T>, T> LToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> assertThat(LToByteFunction<T> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunctionX<T, X>, T, X extends Throwable> LToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, T, X> assertThat(LToByteFunctionX<T, X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunction<T>, T> LToShortFunctionAssert.The<A, ? extends AbstractShortAssert, T> assertThat(LToShortFunction<T> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunctionX<T, X>, T, X extends Throwable> LToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, T, X> assertThat(LToShortFunctionX<T, X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunction<T>, T> LToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(LToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunctionX<T, X>, T, X extends Throwable> LToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T, X> assertThat(LToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunction<T>, T> LToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertThat(LToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunctionX<T, X>, T, X extends Throwable> LToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, T, X> assertThat(LToLongFunctionX<T, X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunction<T>, T> LToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert, T> assertThat(LToFloatFunction<T> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunctionX<T, X>, T, X extends Throwable> LToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, T, X> assertThat(LToFloatFunctionX<T, X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunction<T>, T> LToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertThat(LToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunctionX<T, X>, T, X extends Throwable> LToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, T, X> assertThat(LToDoubleFunctionX<T, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunction<T>, T> LToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> assertThat(LToCharFunction<T> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunctionX<T, X>, T, X extends Throwable> LToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, T, X> assertThat(LToCharFunctionX<T, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.The<A, ? extends AbstractByteAssert, T1, T2> assertThat(LToByteBiFunction<T1, T2> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.The<A, ? extends AbstractByteAssert, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunction<T1, T2>, T1, T2> LToShortBiFunctionAssert.The<A, ? extends AbstractShortAssert, T1, T2> assertThat(LToShortBiFunction<T1, T2> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.The<A, ? extends AbstractShortAssert, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction<T1, T2>, T1, T2> LToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(LToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertThat(LToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.The<A, ? extends AbstractLongAssert, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunction<T1, T2>, T1, T2> LToFloatBiFunctionAssert.The<A, ? extends AbstractFloatAssert, T1, T2> assertThat(LToFloatBiFunction<T1, T2> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.The<A, ? extends AbstractFloatAssert, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunction<T1, T2>, T1, T2> LToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.The<A, ? extends AbstractDoubleAssert, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction<T1, T2>, T1, T2> LToCharBiFunctionAssert.The<A, ? extends AbstractCharacterAssert, T1, T2> assertThat(LToCharBiFunction<T1, T2> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.The<A, ? extends AbstractCharacterAssert, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunction<T>, T> LObjIntToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(LObjIntToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunctionX<T, X>, T, X extends Throwable> LObjIntToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T, X> assertThat(LObjIntToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunction> LByteToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LByteToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunctionX<X>, X extends Throwable> LByteToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LByteToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunction> LByteToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LByteToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunctionX<X>, X extends Throwable> LByteToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LByteToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunction> LByteToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LByteToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunctionX<X>, X extends Throwable> LByteToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LByteToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunction> LByteToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LByteToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunctionX<X>, X extends Throwable> LByteToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LByteToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunction> LByteToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LByteToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunctionX<X>, X extends Throwable> LByteToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LByteToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunction> LByteToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LByteToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunctionX<X>, X extends Throwable> LByteToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LByteToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunction> LShortToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LShortToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunctionX<X>, X extends Throwable> LShortToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LShortToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunction> LShortToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LShortToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunctionX<X>, X extends Throwable> LShortToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LShortToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunction> LShortToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LShortToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunctionX<X>, X extends Throwable> LShortToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LShortToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunction> LShortToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LShortToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunctionX<X>, X extends Throwable> LShortToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LShortToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunction> LShortToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LShortToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunctionX<X>, X extends Throwable> LShortToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LShortToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunction> LShortToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LShortToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunctionX<X>, X extends Throwable> LShortToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LShortToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunction> LIntToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LIntToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunctionX<X>, X extends Throwable> LIntToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LIntToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunction> LIntToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LIntToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunctionX<X>, X extends Throwable> LIntToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LIntToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunction> LIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LIntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunctionX<X>, X extends Throwable> LIntToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LIntToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunction> LIntToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LIntToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunctionX<X>, X extends Throwable> LIntToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LIntToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunction> LIntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LIntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunctionX<X>, X extends Throwable> LIntToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LIntToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunction> LIntToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LIntToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunctionX<X>, X extends Throwable> LIntToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LIntToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunction> LLongToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LLongToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunctionX<X>, X extends Throwable> LLongToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LLongToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunction> LLongToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LLongToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunctionX<X>, X extends Throwable> LLongToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LLongToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunction> LLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LLongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunctionX<X>, X extends Throwable> LLongToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LLongToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunction> LLongToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LLongToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunctionX<X>, X extends Throwable> LLongToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LLongToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunction> LLongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LLongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunctionX<X>, X extends Throwable> LLongToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LLongToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunction> LLongToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LLongToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunctionX<X>, X extends Throwable> LLongToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LLongToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunction> LFloatToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LFloatToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunctionX<X>, X extends Throwable> LFloatToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LFloatToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunction> LFloatToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LFloatToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunctionX<X>, X extends Throwable> LFloatToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LFloatToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunction> LFloatToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LFloatToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunctionX<X>, X extends Throwable> LFloatToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LFloatToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunction> LFloatToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LFloatToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunctionX<X>, X extends Throwable> LFloatToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LFloatToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunction> LFloatToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LFloatToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunctionX<X>, X extends Throwable> LFloatToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LFloatToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunction> LFloatToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LFloatToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunctionX<X>, X extends Throwable> LFloatToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LFloatToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunction> LDoubleToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LDoubleToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunctionX<X>, X extends Throwable> LDoubleToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LDoubleToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunction> LDoubleToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LDoubleToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunctionX<X>, X extends Throwable> LDoubleToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LDoubleToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunction> LDoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LDoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunctionX<X>, X extends Throwable> LDoubleToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LDoubleToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunction> LDoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LDoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunctionX<X>, X extends Throwable> LDoubleToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LDoubleToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunction> LDoubleToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LDoubleToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunctionX<X>, X extends Throwable> LDoubleToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LDoubleToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunction> LDoubleToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LDoubleToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunctionX<X>, X extends Throwable> LDoubleToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LDoubleToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunction> LCharToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LCharToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunctionX<X>, X extends Throwable> LCharToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LCharToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunction> LCharToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LCharToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunctionX<X>, X extends Throwable> LCharToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LCharToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunction> LCharToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LCharToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunctionX<X>, X extends Throwable> LCharToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LCharToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunction> LCharToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LCharToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunctionX<X>, X extends Throwable> LCharToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LCharToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunction> LCharToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LCharToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunctionX<X>, X extends Throwable> LCharToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LCharToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunction> LCharToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LCharToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunctionX<X>, X extends Throwable> LCharToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LCharToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToByteFunction> LBooleanToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LBooleanToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBooleanToByteFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToByteFunctionX<X>, X extends Throwable> LBooleanToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LBooleanToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBooleanToByteFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToShortFunction> LBooleanToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LBooleanToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBooleanToShortFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToShortFunctionX<X>, X extends Throwable> LBooleanToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LBooleanToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBooleanToShortFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToIntFunction> LBooleanToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LBooleanToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBooleanToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToIntFunctionX<X>, X extends Throwable> LBooleanToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LBooleanToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBooleanToIntFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToLongFunction> LBooleanToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LBooleanToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBooleanToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToLongFunctionX<X>, X extends Throwable> LBooleanToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LBooleanToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBooleanToLongFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToFloatFunction> LBooleanToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LBooleanToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBooleanToFloatFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToFloatFunctionX<X>, X extends Throwable> LBooleanToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LBooleanToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBooleanToFloatFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToDoubleFunction> LBooleanToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LBooleanToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBooleanToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToDoubleFunctionX<X>, X extends Throwable> LBooleanToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LBooleanToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBooleanToDoubleFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToCharFunction> LBooleanToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LBooleanToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBooleanToCharFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToCharFunctionX<X>, X extends Throwable> LBooleanToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LBooleanToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBooleanToCharFunctionXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LPredicate<T>, T> LPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LPredicateX<T, X>, T, X extends Throwable> LPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LTriPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicateX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LTriPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicate> LBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBytePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicateX<X>, X extends Throwable> LBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBytePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicate> LShortPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LShortPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicateX<X>, X extends Throwable> LShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LShortPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicate> LIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LIntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LIntPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicateX<X>, X extends Throwable> LIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LIntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LIntPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicate> LLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLongPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicateX<X>, X extends Throwable> LLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLongPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicate> LFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LFloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LFloatPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicateX<X>, X extends Throwable> LFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LFloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LFloatPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicate> LDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LDoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LDoublePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicateX<X>, X extends Throwable> LDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LDoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LDoublePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicate> LCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LCharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LCharPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicateX<X>, X extends Throwable> LCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LCharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LCharPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate> LBiBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiBytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiBytePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicateX<X>, X extends Throwable> LBiBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiBytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiBytePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicate> LBiShortPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiShortPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicateX<X>, X extends Throwable> LBiShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiShortPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiIntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiIntPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicateX<X>, X extends Throwable> LBiIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiIntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiIntPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiLongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiLongPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicateX<X>, X extends Throwable> LBiLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiLongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiLongPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicate> LBiFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiFloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiFloatPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicateX<X>, X extends Throwable> LBiFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiFloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiFloatPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicate> LBiDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiDoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiDoublePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicateX<X>, X extends Throwable> LBiDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiDoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiDoublePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiCharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiCharPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicateX<X>, X extends Throwable> LBiCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiCharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiCharPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBytePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBytePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicateX<T, X>, T, X extends Throwable> LObjBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBytePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBytePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicate<T>, T> LObjShortPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjShortPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjShortPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicateX<T, X>, T, X extends Throwable> LObjShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjShortPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjShortPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjIntPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjIntPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicateX<T, X>, T, X extends Throwable> LObjIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjIntPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjIntPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjLongPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjLongPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicateX<T, X>, T, X extends Throwable> LObjLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjLongPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjLongPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicate<T>, T> LObjFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjFloatPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjFloatPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicateX<T, X>, T, X extends Throwable> LObjFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjFloatPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjFloatPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicate<T>, T> LObjDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjDoublePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjDoublePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicateX<T, X>, T, X extends Throwable> LObjDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjDoublePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjDoublePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjCharPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjCharPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicateX<T, X>, T, X extends Throwable> LObjCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjCharPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjCharPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanPredicate<T>, T> LObjBooleanPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBooleanPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBooleanPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanPredicateX<T, X>, T, X extends Throwable> LObjBooleanPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBooleanPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBooleanPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBytePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBytePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicate<T1, T2>, T1, T2> LBiObjShortPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjShortPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjShortPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjIntPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjIntPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjLongPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjLongPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicate<T1, T2>, T1, T2> LBiObjFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjFloatPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjFloatPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicate<T1, T2>, T1, T2> LBiObjDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjDoublePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjDoublePredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjCharPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjCharPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanPredicate<T1, T2>, T1, T2> LBiObjBooleanPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBooleanPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBooleanPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBooleanPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBooleanPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBooleanPredicateXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LSupplier<R>, R> LSupplierAssert.The<A, ? extends OS, R> assertThat(LSupplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LSupplierX<R, X>, R, X extends Throwable> LSupplierXAssert.The<A, ? extends OS, R, X> assertThat(LSupplierX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplier> LByteSupplierAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteSupplier functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplierX<X>, X extends Throwable> LByteSupplierXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteSupplierX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplier> LShortSupplierAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortSupplier functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplierX<X>, X extends Throwable> LShortSupplierXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortSupplierX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplier> LIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplierX<X>, X extends Throwable> LIntSupplierXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntSupplierX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplier> LLongSupplierAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplierX<X>, X extends Throwable> LLongSupplierXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongSupplierX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplier> LFloatSupplierAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatSupplier functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplierX<X>, X extends Throwable> LFloatSupplierXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatSupplierX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplier> LDoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplierX<X>, X extends Throwable> LDoubleSupplierXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleSupplierX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplier> LCharSupplierAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharSupplier functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplierX<X>, X extends Throwable> LCharSupplierXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharSupplierX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanSupplier> LBooleanSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBooleanSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanSupplierX<X>, X extends Throwable> LBooleanSupplierXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBooleanSupplierX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBooleanSupplierXAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.UnaryOperator<T>, T> UnaryOperatorAssert.The<A, ? extends OS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new UnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BinaryOperator<T>, T> BinaryOperatorAssert.The<A, ? extends OS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new BinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntUnaryOperator> IntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongUnaryOperator> LongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleUnaryOperator> DoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleUnaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntBinaryOperator> IntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongBinaryOperator> LongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleBinaryOperator> DoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleBinaryOperatorAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Function<T, R>, T, R> FunctionAssert.The<A, ? extends OS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new FunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiFunction<T1, T2, R>, T1, T2, R> BiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new BiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntFunction<R>, R> IntFunctionAssert.The<A, ? extends OS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new IntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongFunction<R>, R> LongFunctionAssert.The<A, ? extends OS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleFunction<R>, R> DoubleFunctionAssert.The<A, ? extends OS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new DoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntFunction<T>, T> ToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongFunction<T>, T> ToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleFunction<T>, T> ToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntBiFunction<T1, T2>, T1, T2> ToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongBiFunction<T1, T2>, T1, T2> ToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleBiFunction<T1, T2>, T1, T2> ToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleBiFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToLongFunction> IntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new IntToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToDoubleFunction> IntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new IntToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToIntFunction> LongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LongToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToDoubleFunction> LongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LongToDoubleFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToIntFunction> DoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new DoubleToIntFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToLongFunction> DoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new DoubleToLongFunctionAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Predicate<T>, T> PredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new PredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiPredicate<T1, T2>, T1, T2> BiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new BiPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntPredicate> IntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.IntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new IntPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongPredicate> LongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.LongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LongPredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoublePredicate> DoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.DoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new DoublePredicateAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Supplier<R>, R> SupplierAssert.The<A, ? extends OS, R> assertThat(java.util.function.Supplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new SupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntSupplier> IntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongSupplier> LongSupplierAssert.The<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleSupplier> DoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BooleanSupplier> BooleanSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new BooleanSupplierAssert.The(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Consumer<T>, T> ConsumerAssert.The<A, T> assertThat(java.util.function.Consumer<T> functionalInterface) {
		return new ConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.The<A, T1, T2> assertThat(java.util.function.BiConsumer<T1, T2> functionalInterface) {
		return new BiConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.IntConsumer> IntConsumerAssert.The<A> assertThat(java.util.function.IntConsumer functionalInterface) {
		return new IntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.LongConsumer> LongConsumerAssert.The<A> assertThat(java.util.function.LongConsumer functionalInterface) {
		return new LongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.DoubleConsumer> DoubleConsumerAssert.The<A> assertThat(java.util.function.DoubleConsumer functionalInterface) {
		return new DoubleConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjIntConsumer<T>, T> ObjIntConsumerAssert.The<A, T> assertThat(java.util.function.ObjIntConsumer<T> functionalInterface) {
		return new ObjIntConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjLongConsumer<T>, T> ObjLongConsumerAssert.The<A, T> assertThat(java.util.function.ObjLongConsumer<T> functionalInterface) {
		return new ObjLongConsumerAssert.The(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.The<A, T> assertThat(java.util.function.ObjDoubleConsumer<T> functionalInterface) {
		return new ObjDoubleConsumerAssert.The(functionalInterface);
	}

}