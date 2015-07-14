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
	default <A extends LAction> LActionAssert.Impl<A> assertThat(LAction functionalInterface) {
		return new LActionAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LActionX<X>, X extends Throwable> LActionXAssert.Impl<A, X> assertThat(LActionX<X> functionalInterface) {
		return new LActionXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LConsumer<T>, T> LConsumerAssert.Impl<A, T> assertThat(LConsumer<T> functionalInterface) {
		return new LConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.Impl<A, T, X> assertThat(LConsumerX<T, X> functionalInterface) {
		return new LConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.Impl<A, T1, T2> assertThat(LBiConsumer<T1, T2> functionalInterface) {
		return new LBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> functionalInterface) {
		return new LBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.Impl<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> functionalInterface) {
		return new LTriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.Impl<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> functionalInterface) {
		return new LTriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LByteConsumer> LByteConsumerAssert.Impl<A> assertThat(LByteConsumer functionalInterface) {
		return new LByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.Impl<A, X> assertThat(LByteConsumerX<X> functionalInterface) {
		return new LByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LShortConsumer> LShortConsumerAssert.Impl<A> assertThat(LShortConsumer functionalInterface) {
		return new LShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.Impl<A, X> assertThat(LShortConsumerX<X> functionalInterface) {
		return new LShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LIntConsumer> LIntConsumerAssert.Impl<A> assertThat(LIntConsumer functionalInterface) {
		return new LIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.Impl<A, X> assertThat(LIntConsumerX<X> functionalInterface) {
		return new LIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LLongConsumer> LLongConsumerAssert.Impl<A> assertThat(LLongConsumer functionalInterface) {
		return new LLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.Impl<A, X> assertThat(LLongConsumerX<X> functionalInterface) {
		return new LLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatConsumer> LFloatConsumerAssert.Impl<A> assertThat(LFloatConsumer functionalInterface) {
		return new LFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.Impl<A, X> assertThat(LFloatConsumerX<X> functionalInterface) {
		return new LFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleConsumer> LDoubleConsumerAssert.Impl<A> assertThat(LDoubleConsumer functionalInterface) {
		return new LDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.Impl<A, X> assertThat(LDoubleConsumerX<X> functionalInterface) {
		return new LDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LCharConsumer> LCharConsumerAssert.Impl<A> assertThat(LCharConsumer functionalInterface) {
		return new LCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.Impl<A, X> assertThat(LCharConsumerX<X> functionalInterface) {
		return new LCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanConsumer> LBooleanConsumerAssert.Impl<A> assertThat(LBooleanConsumer functionalInterface) {
		return new LBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanConsumerX<X>, X extends Throwable> LBooleanConsumerXAssert.Impl<A, X> assertThat(LBooleanConsumerX<X> functionalInterface) {
		return new LBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LByteBiConsumer> LByteBiConsumerAssert.Impl<A> assertThat(LByteBiConsumer functionalInterface) {
		return new LByteBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LByteBiConsumerX<X>, X extends Throwable> LByteBiConsumerXAssert.Impl<A, X> assertThat(LByteBiConsumerX<X> functionalInterface) {
		return new LByteBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LShortBiConsumer> LShortBiConsumerAssert.Impl<A> assertThat(LShortBiConsumer functionalInterface) {
		return new LShortBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LShortBiConsumerX<X>, X extends Throwable> LShortBiConsumerXAssert.Impl<A, X> assertThat(LShortBiConsumerX<X> functionalInterface) {
		return new LShortBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LIntBiConsumer> LIntBiConsumerAssert.Impl<A> assertThat(LIntBiConsumer functionalInterface) {
		return new LIntBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LIntBiConsumerX<X>, X extends Throwable> LIntBiConsumerXAssert.Impl<A, X> assertThat(LIntBiConsumerX<X> functionalInterface) {
		return new LIntBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LLongBiConsumer> LLongBiConsumerAssert.Impl<A> assertThat(LLongBiConsumer functionalInterface) {
		return new LLongBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LLongBiConsumerX<X>, X extends Throwable> LLongBiConsumerXAssert.Impl<A, X> assertThat(LLongBiConsumerX<X> functionalInterface) {
		return new LLongBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatBiConsumer> LFloatBiConsumerAssert.Impl<A> assertThat(LFloatBiConsumer functionalInterface) {
		return new LFloatBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LFloatBiConsumerX<X>, X extends Throwable> LFloatBiConsumerXAssert.Impl<A, X> assertThat(LFloatBiConsumerX<X> functionalInterface) {
		return new LFloatBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleBiConsumer> LDoubleBiConsumerAssert.Impl<A> assertThat(LDoubleBiConsumer functionalInterface) {
		return new LDoubleBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LDoubleBiConsumerX<X>, X extends Throwable> LDoubleBiConsumerXAssert.Impl<A, X> assertThat(LDoubleBiConsumerX<X> functionalInterface) {
		return new LDoubleBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LCharBiConsumer> LCharBiConsumerAssert.Impl<A> assertThat(LCharBiConsumer functionalInterface) {
		return new LCharBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LCharBiConsumerX<X>, X extends Throwable> LCharBiConsumerXAssert.Impl<A, X> assertThat(LCharBiConsumerX<X> functionalInterface) {
		return new LCharBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanBiConsumer> LBooleanBiConsumerAssert.Impl<A> assertThat(LBooleanBiConsumer functionalInterface) {
		return new LBooleanBiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanBiConsumerX<X>, X extends Throwable> LBooleanBiConsumerXAssert.Impl<A, X> assertThat(LBooleanBiConsumerX<X> functionalInterface) {
		return new LBooleanBiConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanTriConsumer> LBooleanTriConsumerAssert.Impl<A> assertThat(LBooleanTriConsumer functionalInterface) {
		return new LBooleanTriConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBooleanTriConsumerX<X>, X extends Throwable> LBooleanTriConsumerXAssert.Impl<A, X> assertThat(LBooleanTriConsumerX<X> functionalInterface) {
		return new LBooleanTriConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.Impl<A, T> assertThat(LObjByteConsumer<T> functionalInterface) {
		return new LObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.Impl<A, T, X> assertThat(LObjByteConsumerX<T, X> functionalInterface) {
		return new LObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.Impl<A, T> assertThat(LObjShortConsumer<T> functionalInterface) {
		return new LObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.Impl<A, T, X> assertThat(LObjShortConsumerX<T, X> functionalInterface) {
		return new LObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.Impl<A, T> assertThat(LObjIntConsumer<T> functionalInterface) {
		return new LObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.Impl<A, T, X> assertThat(LObjIntConsumerX<T, X> functionalInterface) {
		return new LObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.Impl<A, T> assertThat(LObjLongConsumer<T> functionalInterface) {
		return new LObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.Impl<A, T, X> assertThat(LObjLongConsumerX<T, X> functionalInterface) {
		return new LObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.Impl<A, T> assertThat(LObjFloatConsumer<T> functionalInterface) {
		return new LObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.Impl<A, T, X> assertThat(LObjFloatConsumerX<T, X> functionalInterface) {
		return new LObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.Impl<A, T> assertThat(LObjDoubleConsumer<T> functionalInterface) {
		return new LObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.Impl<A, T, X> assertThat(LObjDoubleConsumerX<T, X> functionalInterface) {
		return new LObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.Impl<A, T> assertThat(LObjCharConsumer<T> functionalInterface) {
		return new LObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.Impl<A, T, X> assertThat(LObjCharConsumerX<T, X> functionalInterface) {
		return new LObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjBooleanConsumer<T>, T> LObjBooleanConsumerAssert.Impl<A, T> assertThat(LObjBooleanConsumer<T> functionalInterface) {
		return new LObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LObjBooleanConsumerX<T, X>, T, X extends Throwable> LObjBooleanConsumerXAssert.Impl<A, T, X> assertThat(LObjBooleanConsumerX<T, X> functionalInterface) {
		return new LObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> functionalInterface) {
		return new LBiObjByteConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjByteConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> functionalInterface) {
		return new LBiObjShortConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjShortConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> functionalInterface) {
		return new LBiObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjIntConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> functionalInterface) {
		return new LBiObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjLongConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> functionalInterface) {
		return new LBiObjFloatConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjFloatConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> functionalInterface) {
		return new LBiObjDoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjDoubleConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> functionalInterface) {
		return new LBiObjCharConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjCharConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjBooleanConsumer<T1, T2>, T1, T2> LBiObjBooleanConsumerAssert.Impl<A, T1, T2> assertThat(LBiObjBooleanConsumer<T1, T2> functionalInterface) {
		return new LBiObjBooleanConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LBiObjBooleanConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBooleanConsumerXAssert.Impl<A, T1, T2, X> assertThat(LBiObjBooleanConsumerX<T1, T2, X> functionalInterface) {
		return new LBiObjBooleanConsumerXAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends LUnaryOperator<T>, T> LUnaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(LUnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperatorX<T, X>, T, X extends Throwable> LUnaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(LUnaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBinaryOperator<T>, T> LBinaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(LBinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBinaryOperatorX<T, X>, T, X extends Throwable> LBinaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(LBinaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperator<T>, T> LTernaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(LTernaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperatorX<T, X>, T, X extends Throwable> LTernaryOperatorXAssert.Impl<A, ? extends OS, T, X> assertThat(LTernaryOperatorX<T, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperator> LByteUnaryOperatorAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LByteUnaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperatorX<X>, X extends Throwable> LByteUnaryOperatorXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LByteUnaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperator> LShortUnaryOperatorAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LShortUnaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperatorX<X>, X extends Throwable> LShortUnaryOperatorXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LShortUnaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperator> LIntUnaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LIntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperatorX<X>, X extends Throwable> LIntUnaryOperatorXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LIntUnaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperator> LLongUnaryOperatorAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LLongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperatorX<X>, X extends Throwable> LLongUnaryOperatorXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LLongUnaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperator> LFloatUnaryOperatorAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LFloatUnaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperatorX<X>, X extends Throwable> LFloatUnaryOperatorXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LFloatUnaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperator> LDoubleUnaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LDoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperatorX<X>, X extends Throwable> LDoubleUnaryOperatorXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleUnaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperator> LCharUnaryOperatorAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LCharUnaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperatorX<X>, X extends Throwable> LCharUnaryOperatorXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LCharUnaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperator> LLogicalOperatorAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LLogicalOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperatorX<X>, X extends Throwable> LLogicalOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperator> LByteBinaryOperatorAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LByteBinaryOperator functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperatorX<X>, X extends Throwable> LByteBinaryOperatorXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LByteBinaryOperatorX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperator> LShortBinaryOperatorAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LShortBinaryOperator functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperatorX<X>, X extends Throwable> LShortBinaryOperatorXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LShortBinaryOperatorX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperator> LIntBinaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LIntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperatorX<X>, X extends Throwable> LIntBinaryOperatorXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LIntBinaryOperatorX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperator> LLongBinaryOperatorAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LLongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperatorX<X>, X extends Throwable> LLongBinaryOperatorXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LLongBinaryOperatorX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperator> LFloatBinaryOperatorAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LFloatBinaryOperator functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperatorX<X>, X extends Throwable> LFloatBinaryOperatorXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LFloatBinaryOperatorX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperator> LDoubleBinaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LDoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperatorX<X>, X extends Throwable> LDoubleBinaryOperatorXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleBinaryOperatorX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperator> LCharBinaryOperatorAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LCharBinaryOperator functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperatorX<X>, X extends Throwable> LCharBinaryOperatorXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LCharBinaryOperatorX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperator> LLogicalBinaryOperatorAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LLogicalBinaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperatorX<X>, X extends Throwable> LLogicalBinaryOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalBinaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalBinaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperator> LLogicalTernaryOperatorAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LLogicalTernaryOperator functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalTernaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperatorX<X>, X extends Throwable> LLogicalTernaryOperatorXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalTernaryOperatorX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLogicalTernaryOperatorXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFunction<T, R>, T, R> LFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFunctionX<T, R, X>, T, R, X extends Throwable> LFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction<T1, T2, R>, T1, T2, R> LBiFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.Impl<A, ? extends OS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunctionX<T1, T2, T3, R, X>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.Impl<A, ? extends OS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunction<R>, R> LByteFunctionAssert.Impl<A, ? extends OS, R> assertThat(LByteFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunctionX<R, X>, R, X extends Throwable> LByteFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LByteFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunction<R>, R> LShortFunctionAssert.Impl<A, ? extends OS, R> assertThat(LShortFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunctionX<R, X>, R, X extends Throwable> LShortFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LShortFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunction<R>, R> LIntFunctionAssert.Impl<A, ? extends OS, R> assertThat(LIntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunctionX<R, X>, R, X extends Throwable> LIntFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LIntFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunction<R>, R> LLongFunctionAssert.Impl<A, ? extends OS, R> assertThat(LLongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunctionX<R, X>, R, X extends Throwable> LLongFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LLongFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunction<R>, R> LFloatFunctionAssert.Impl<A, ? extends OS, R> assertThat(LFloatFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunctionX<R, X>, R, X extends Throwable> LFloatFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LFloatFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunction<R>, R> LDoubleFunctionAssert.Impl<A, ? extends OS, R> assertThat(LDoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunctionX<R, X>, R, X extends Throwable> LDoubleFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LDoubleFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunction<R>, R> LCharFunctionAssert.Impl<A, ? extends OS, R> assertThat(LCharFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunctionX<R, X>, R, X extends Throwable> LCharFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LCharFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanFunction<R>, R> LBooleanFunctionAssert.Impl<A, ? extends OS, R> assertThat(LBooleanFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanFunctionX<R, X>, R, X extends Throwable> LBooleanFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LBooleanFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBiFunction<R>, R> LByteBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LByteBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteBiFunctionX<R, X>, R, X extends Throwable> LByteBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LByteBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LByteBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBiFunction<R>, R> LShortBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LShortBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortBiFunctionX<R, X>, R, X extends Throwable> LShortBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LShortBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LShortBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBiFunction<R>, R> LIntBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LIntBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntBiFunctionX<R, X>, R, X extends Throwable> LIntBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LIntBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LIntBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBiFunction<R>, R> LLongBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LLongBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongBiFunctionX<R, X>, R, X extends Throwable> LLongBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LLongBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LLongBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBiFunction<R>, R> LFloatBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LFloatBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBiFunctionX<R, X>, R, X extends Throwable> LFloatBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LFloatBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBiFunction<R>, R> LDoubleBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LDoubleBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBiFunctionX<R, X>, R, X extends Throwable> LDoubleBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LDoubleBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBiFunction<R>, R> LCharBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LCharBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharBiFunctionX<R, X>, R, X extends Throwable> LCharBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LCharBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LCharBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanBiFunction<R>, R> LBooleanBiFunctionAssert.Impl<A, ? extends OS, R> assertThat(LBooleanBiFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanBiFunctionX<R, X>, R, X extends Throwable> LBooleanBiFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LBooleanBiFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanTriFunction<R>, R> LBooleanTriFunctionAssert.Impl<A, ? extends OS, R> assertThat(LBooleanTriFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanTriFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanTriFunctionX<R, X>, R, X extends Throwable> LBooleanTriFunctionXAssert.Impl<A, ? extends OS, R, X> assertThat(LBooleanTriFunctionX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBooleanTriFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjByteFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunctionX<T, R, X>, T, R, X extends Throwable> LObjByteFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunction<T, R>, T, R> LObjShortFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjShortFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunctionX<T, R, X>, T, R, X extends Throwable> LObjShortFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunction<T, R>, T, R> LObjIntFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjIntFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunctionX<T, R, X>, T, R, X extends Throwable> LObjIntFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjLongFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunctionX<T, R, X>, T, R, X extends Throwable> LObjLongFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunction<T, R>, T, R> LObjFloatFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjFloatFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunctionX<T, R, X>, T, R, X extends Throwable> LObjFloatFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunction<T, R>, T, R> LObjDoubleFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjDoubleFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunctionX<T, R, X>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjCharFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunctionX<T, R, X>, T, R, X extends Throwable> LObjCharFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanFunction<T, R>, T, R> LObjBooleanFunctionAssert.Impl<A, ? extends OS, T, R> assertThat(LObjBooleanFunction<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanFunctionX<T, R, X>, T, R, X extends Throwable> LObjBooleanFunctionXAssert.Impl<A, ? extends OS, T, R, X> assertThat(LObjBooleanFunctionX<T, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunction<T1, T2, R>, T1, T2, R> LBiObjShortFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunction<T1, T2, R>, T1, T2, R> LBiObjFloatFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunction<T1, T2, R>, T1, T2, R> LBiObjDoubleFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjDoubleFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanFunction<T1, T2, R>, T1, T2, R> LBiObjBooleanFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(LBiObjBooleanFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBooleanFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjBooleanFunctionXAssert.Impl<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjBooleanFunctionX<T1, T2, R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBooleanFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunction<T>, T> LToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert, T> assertThat(LToByteFunction<T> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunctionX<T, X>, T, X extends Throwable> LToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, T, X> assertThat(LToByteFunctionX<T, X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunction<T>, T> LToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert, T> assertThat(LToShortFunction<T> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunctionX<T, X>, T, X extends Throwable> LToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, T, X> assertThat(LToShortFunctionX<T, X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunction<T>, T> LToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert, T> assertThat(LToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunctionX<T, X>, T, X extends Throwable> LToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, T, X> assertThat(LToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunction<T>, T> LToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert, T> assertThat(LToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunctionX<T, X>, T, X extends Throwable> LToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, T, X> assertThat(LToLongFunctionX<T, X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunction<T>, T> LToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert, T> assertThat(LToFloatFunction<T> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunctionX<T, X>, T, X extends Throwable> LToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, T, X> assertThat(LToFloatFunctionX<T, X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunction<T>, T> LToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert, T> assertThat(LToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunctionX<T, X>, T, X extends Throwable> LToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, T, X> assertThat(LToDoubleFunctionX<T, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunction<T>, T> LToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert, T> assertThat(LToCharFunction<T> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunctionX<T, X>, T, X extends Throwable> LToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, T, X> assertThat(LToCharFunctionX<T, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.Impl<A, ? extends AbstractByteAssert, T1, T2> assertThat(LToByteBiFunction<T1, T2> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.Impl<A, ? extends AbstractByteAssert, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunction<T1, T2>, T1, T2> LToShortBiFunctionAssert.Impl<A, ? extends AbstractShortAssert, T1, T2> assertThat(LToShortBiFunction<T1, T2> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.Impl<A, ? extends AbstractShortAssert, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction<T1, T2>, T1, T2> LToIntBiFunctionAssert.Impl<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(LToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.Impl<A, ? extends AbstractLongAssert, T1, T2> assertThat(LToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.Impl<A, ? extends AbstractLongAssert, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunction<T1, T2>, T1, T2> LToFloatBiFunctionAssert.Impl<A, ? extends AbstractFloatAssert, T1, T2> assertThat(LToFloatBiFunction<T1, T2> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunction<T1, T2>, T1, T2> LToDoubleBiFunctionAssert.Impl<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction<T1, T2>, T1, T2> LToCharBiFunctionAssert.Impl<A, ? extends AbstractCharacterAssert, T1, T2> assertThat(LToCharBiFunction<T1, T2> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunction<T>, T> LObjIntToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert, T> assertThat(LObjIntToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunctionX<T, X>, T, X extends Throwable> LObjIntToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, T, X> assertThat(LObjIntToIntFunctionX<T, X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunction> LByteToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LByteToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunctionX<X>, X extends Throwable> LByteToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LByteToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunction> LByteToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LByteToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunctionX<X>, X extends Throwable> LByteToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LByteToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunction> LByteToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LByteToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunctionX<X>, X extends Throwable> LByteToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LByteToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunction> LByteToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LByteToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunctionX<X>, X extends Throwable> LByteToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LByteToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunction> LByteToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LByteToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunctionX<X>, X extends Throwable> LByteToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LByteToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunction> LByteToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LByteToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunctionX<X>, X extends Throwable> LByteToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LByteToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunction> LShortToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LShortToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunctionX<X>, X extends Throwable> LShortToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LShortToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunction> LShortToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LShortToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunctionX<X>, X extends Throwable> LShortToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LShortToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunction> LShortToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LShortToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunctionX<X>, X extends Throwable> LShortToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LShortToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunction> LShortToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LShortToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunctionX<X>, X extends Throwable> LShortToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LShortToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunction> LShortToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LShortToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunctionX<X>, X extends Throwable> LShortToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LShortToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunction> LShortToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LShortToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunctionX<X>, X extends Throwable> LShortToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LShortToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunction> LIntToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LIntToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunctionX<X>, X extends Throwable> LIntToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LIntToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunction> LIntToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LIntToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunctionX<X>, X extends Throwable> LIntToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LIntToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunction> LIntToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LIntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunctionX<X>, X extends Throwable> LIntToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LIntToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunction> LIntToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LIntToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunctionX<X>, X extends Throwable> LIntToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LIntToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunction> LIntToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LIntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunctionX<X>, X extends Throwable> LIntToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LIntToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunction> LIntToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LIntToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunctionX<X>, X extends Throwable> LIntToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LIntToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunction> LLongToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LLongToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunctionX<X>, X extends Throwable> LLongToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LLongToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunction> LLongToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LLongToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunctionX<X>, X extends Throwable> LLongToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LLongToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunction> LLongToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LLongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunctionX<X>, X extends Throwable> LLongToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LLongToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunction> LLongToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LLongToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunctionX<X>, X extends Throwable> LLongToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LLongToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunction> LLongToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LLongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunctionX<X>, X extends Throwable> LLongToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LLongToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunction> LLongToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LLongToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunctionX<X>, X extends Throwable> LLongToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LLongToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunction> LFloatToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LFloatToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunctionX<X>, X extends Throwable> LFloatToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LFloatToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunction> LFloatToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LFloatToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunctionX<X>, X extends Throwable> LFloatToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LFloatToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunction> LFloatToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LFloatToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunctionX<X>, X extends Throwable> LFloatToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LFloatToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunction> LFloatToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LFloatToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunctionX<X>, X extends Throwable> LFloatToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LFloatToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunction> LFloatToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LFloatToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunctionX<X>, X extends Throwable> LFloatToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LFloatToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunction> LFloatToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LFloatToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunctionX<X>, X extends Throwable> LFloatToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LFloatToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunction> LDoubleToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LDoubleToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunctionX<X>, X extends Throwable> LDoubleToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LDoubleToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunction> LDoubleToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LDoubleToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunctionX<X>, X extends Throwable> LDoubleToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LDoubleToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunction> LDoubleToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LDoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunctionX<X>, X extends Throwable> LDoubleToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LDoubleToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunction> LDoubleToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LDoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunctionX<X>, X extends Throwable> LDoubleToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LDoubleToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunction> LDoubleToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LDoubleToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunctionX<X>, X extends Throwable> LDoubleToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LDoubleToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunction> LDoubleToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LDoubleToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunctionX<X>, X extends Throwable> LDoubleToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LDoubleToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunction> LCharToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LCharToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunctionX<X>, X extends Throwable> LCharToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LCharToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunction> LCharToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LCharToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunctionX<X>, X extends Throwable> LCharToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LCharToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunction> LCharToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LCharToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunctionX<X>, X extends Throwable> LCharToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LCharToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunction> LCharToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LCharToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunctionX<X>, X extends Throwable> LCharToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LCharToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunction> LCharToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LCharToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunctionX<X>, X extends Throwable> LCharToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LCharToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunction> LCharToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LCharToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunctionX<X>, X extends Throwable> LCharToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LCharToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToByteFunction> LBooleanToByteFunctionAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LBooleanToByteFunction functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBooleanToByteFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToByteFunctionX<X>, X extends Throwable> LBooleanToByteFunctionXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LBooleanToByteFunctionX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBooleanToByteFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToShortFunction> LBooleanToShortFunctionAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LBooleanToShortFunction functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBooleanToShortFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToShortFunctionX<X>, X extends Throwable> LBooleanToShortFunctionXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LBooleanToShortFunctionX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBooleanToShortFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToIntFunction> LBooleanToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LBooleanToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBooleanToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToIntFunctionX<X>, X extends Throwable> LBooleanToIntFunctionXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LBooleanToIntFunctionX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBooleanToIntFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToLongFunction> LBooleanToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LBooleanToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBooleanToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToLongFunctionX<X>, X extends Throwable> LBooleanToLongFunctionXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LBooleanToLongFunctionX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBooleanToLongFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToFloatFunction> LBooleanToFloatFunctionAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LBooleanToFloatFunction functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBooleanToFloatFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToFloatFunctionX<X>, X extends Throwable> LBooleanToFloatFunctionXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LBooleanToFloatFunctionX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBooleanToFloatFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToDoubleFunction> LBooleanToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LBooleanToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBooleanToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToDoubleFunctionX<X>, X extends Throwable> LBooleanToDoubleFunctionXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LBooleanToDoubleFunctionX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBooleanToDoubleFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToCharFunction> LBooleanToCharFunctionAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LBooleanToCharFunction functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBooleanToCharFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanToCharFunctionX<X>, X extends Throwable> LBooleanToCharFunctionXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LBooleanToCharFunctionX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBooleanToCharFunctionXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LPredicate<T>, T> LPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LPredicateX<T, X>, T, X extends Throwable> LPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LTriPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicateX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LTriPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicate> LBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicateX<X>, X extends Throwable> LBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicate> LShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicateX<X>, X extends Throwable> LShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicate> LIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LIntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicateX<X>, X extends Throwable> LIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LIntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicate> LLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LLongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicateX<X>, X extends Throwable> LLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LLongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicate> LFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LFloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicateX<X>, X extends Throwable> LFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LFloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicate> LDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LDoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicateX<X>, X extends Throwable> LDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LDoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicate> LCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LCharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicateX<X>, X extends Throwable> LCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LCharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate> LBiBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiBytePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicateX<X>, X extends Throwable> LBiBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiBytePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicate> LBiShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiShortPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicateX<X>, X extends Throwable> LBiShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiShortPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiIntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicateX<X>, X extends Throwable> LBiIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiIntPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiLongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicateX<X>, X extends Throwable> LBiLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiLongPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicate> LBiFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiFloatPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicateX<X>, X extends Throwable> LBiFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiFloatPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicate> LBiDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiDoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicateX<X>, X extends Throwable> LBiDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiDoublePredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBiCharPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicateX<X>, X extends Throwable> LBiCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBiCharPredicateX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBytePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicateX<T, X>, T, X extends Throwable> LObjBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBytePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicate<T>, T> LObjShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjShortPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicateX<T, X>, T, X extends Throwable> LObjShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjShortPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjIntPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicateX<T, X>, T, X extends Throwable> LObjIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjIntPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjLongPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicateX<T, X>, T, X extends Throwable> LObjLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjLongPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicate<T>, T> LObjFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjFloatPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicateX<T, X>, T, X extends Throwable> LObjFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjFloatPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicate<T>, T> LObjDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjDoublePredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicateX<T, X>, T, X extends Throwable> LObjDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjDoublePredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjCharPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicateX<T, X>, T, X extends Throwable> LObjCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjCharPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanPredicate<T>, T> LObjBooleanPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBooleanPredicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBooleanPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LObjBooleanPredicateX<T, X>, T, X extends Throwable> LObjBooleanPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBooleanPredicateX<T, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LObjBooleanPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBytePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBytePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicate<T1, T2>, T1, T2> LBiObjShortPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjShortPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjShortPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjIntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjIntPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjLongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjLongPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicate<T1, T2>, T1, T2> LBiObjFloatPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjFloatPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjFloatPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicate<T1, T2>, T1, T2> LBiObjDoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjDoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjDoublePredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjCharPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjCharPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanPredicate<T1, T2>, T1, T2> LBiObjBooleanPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBooleanPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBooleanPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBooleanPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBooleanPredicateXAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBooleanPredicateX<T1, T2, X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBiObjBooleanPredicateXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LSupplier<R>, R> LSupplierAssert.Impl<A, ? extends OS, R> assertThat(LSupplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LSupplierX<R, X>, R, X extends Throwable> LSupplierXAssert.Impl<A, ? extends OS, R, X> assertThat(LSupplierX<R, X> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplier> LByteSupplierAssert.Impl<A, ? extends AbstractByteAssert> assertThat(LByteSupplier functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplierX<X>, X extends Throwable> LByteSupplierXAssert.Impl<A, ? extends AbstractByteAssert, X> assertThat(LByteSupplierX<X> functionalInterface) {
		Function<Byte, AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplier> LShortSupplierAssert.Impl<A, ? extends AbstractShortAssert> assertThat(LShortSupplier functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplierX<X>, X extends Throwable> LShortSupplierXAssert.Impl<A, ? extends AbstractShortAssert, X> assertThat(LShortSupplierX<X> functionalInterface) {
		Function<Short, AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplier> LIntSupplierAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(LIntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplierX<X>, X extends Throwable> LIntSupplierXAssert.Impl<A, ? extends AbstractIntegerAssert, X> assertThat(LIntSupplierX<X> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplier> LLongSupplierAssert.Impl<A, ? extends AbstractLongAssert> assertThat(LLongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplierX<X>, X extends Throwable> LLongSupplierXAssert.Impl<A, ? extends AbstractLongAssert, X> assertThat(LLongSupplierX<X> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplier> LFloatSupplierAssert.Impl<A, ? extends AbstractFloatAssert> assertThat(LFloatSupplier functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplierX<X>, X extends Throwable> LFloatSupplierXAssert.Impl<A, ? extends AbstractFloatAssert, X> assertThat(LFloatSupplierX<X> functionalInterface) {
		Function<Float, AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplier> LDoubleSupplierAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(LDoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplierX<X>, X extends Throwable> LDoubleSupplierXAssert.Impl<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleSupplierX<X> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplier> LCharSupplierAssert.Impl<A, ? extends AbstractCharacterAssert> assertThat(LCharSupplier functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplierX<X>, X extends Throwable> LCharSupplierXAssert.Impl<A, ? extends AbstractCharacterAssert, X> assertThat(LCharSupplierX<X> functionalInterface) {
		Function<Character, AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanSupplier> LBooleanSupplierAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(LBooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBooleanSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends LBooleanSupplierX<X>, X extends Throwable> LBooleanSupplierXAssert.Impl<A, ? extends AbstractBooleanAssert, X> assertThat(LBooleanSupplierX<X> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LBooleanSupplierXAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.UnaryOperator<T>, T> UnaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(java.util.function.UnaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new UnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BinaryOperator<T>, T> BinaryOperatorAssert.Impl<A, ? extends OS, T> assertThat(java.util.function.BinaryOperator<T> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new BinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntUnaryOperator> IntUnaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntUnaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongUnaryOperator> LongUnaryOperatorAssert.Impl<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongUnaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleUnaryOperator> DoubleUnaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleUnaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleUnaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntBinaryOperator> IntBinaryOperatorAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntBinaryOperator functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongBinaryOperator> LongBinaryOperatorAssert.Impl<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongBinaryOperator functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleBinaryOperator> DoubleBinaryOperatorAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleBinaryOperator functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleBinaryOperatorAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Function<T, R>, T, R> FunctionAssert.Impl<A, ? extends OS, T, R> assertThat(java.util.function.Function<T, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new FunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiFunction<T1, T2, R>, T1, T2, R> BiFunctionAssert.Impl<A, ? extends OS, T1, T2, R> assertThat(java.util.function.BiFunction<T1, T2, R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new BiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntFunction<R>, R> IntFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.IntFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new IntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongFunction<R>, R> LongFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.LongFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new LongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleFunction<R>, R> DoubleFunctionAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.DoubleFunction<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new DoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntFunction<T>, T> ToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert, T> assertThat(java.util.function.ToIntFunction<T> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongFunction<T>, T> ToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert, T> assertThat(java.util.function.ToLongFunction<T> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleFunction<T>, T> ToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert, T> assertThat(java.util.function.ToDoubleFunction<T> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToIntBiFunction<T1, T2>, T1, T2> ToIntBiFunctionAssert.Impl<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(java.util.function.ToIntBiFunction<T1, T2> functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToLongBiFunction<T1, T2>, T1, T2> ToLongBiFunctionAssert.Impl<A, ? extends AbstractLongAssert, T1, T2> assertThat(java.util.function.ToLongBiFunction<T1, T2> functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.ToDoubleBiFunction<T1, T2>, T1, T2> ToDoubleBiFunctionAssert.Impl<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(java.util.function.ToDoubleBiFunction<T1, T2> functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleBiFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToLongFunction> IntToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(java.util.function.IntToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new IntToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntToDoubleFunction> IntToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.IntToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new IntToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToIntFunction> LongToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.LongToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LongToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongToDoubleFunction> LongToDoubleFunctionAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.LongToDoubleFunction functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LongToDoubleFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToIntFunction> DoubleToIntFunctionAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.DoubleToIntFunction functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new DoubleToIntFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleToLongFunction> DoubleToLongFunctionAssert.Impl<A, ? extends AbstractLongAssert> assertThat(java.util.function.DoubleToLongFunction functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new DoubleToLongFunctionAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Predicate<T>, T> PredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T> assertThat(java.util.function.Predicate<T> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new PredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BiPredicate<T1, T2>, T1, T2> BiPredicateAssert.Impl<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(java.util.function.BiPredicate<T1, T2> functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new BiPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntPredicate> IntPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.IntPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new IntPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongPredicate> LongPredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.LongPredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new LongPredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoublePredicate> DoublePredicateAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.DoublePredicate functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new DoublePredicateAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Supplier<R>, R> SupplierAssert.Impl<A, ? extends OS, R> assertThat(java.util.function.Supplier<R> functionalInterface) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		Function<Object, OS> assertFunc = this::assertThatObj;
		return new SupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.IntSupplier> IntSupplierAssert.Impl<A, ? extends AbstractIntegerAssert> assertThat(java.util.function.IntSupplier functionalInterface) {
		Function<Integer, AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.LongSupplier> LongSupplierAssert.Impl<A, ? extends AbstractLongAssert> assertThat(java.util.function.LongSupplier functionalInterface) {
		Function<Long, AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.DoubleSupplier> DoubleSupplierAssert.Impl<A, ? extends AbstractDoubleAssert> assertThat(java.util.function.DoubleSupplier functionalInterface) {
		Function<Double, AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.BooleanSupplier> BooleanSupplierAssert.Impl<A, ? extends AbstractBooleanAssert> assertThat(java.util.function.BooleanSupplier functionalInterface) {
		Function<Boolean, AbstractBooleanAssert> assertFunc = this::assertThatBoolean;
		return new BooleanSupplierAssert.Impl(functionalInterface, assertFunc);
	}

	@Nonnull
	default <A extends java.util.function.Consumer<T>, T> ConsumerAssert.Impl<A, T> assertThat(java.util.function.Consumer<T> functionalInterface) {
		return new ConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.Impl<A, T1, T2> assertThat(java.util.function.BiConsumer<T1, T2> functionalInterface) {
		return new BiConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.IntConsumer> IntConsumerAssert.Impl<A> assertThat(java.util.function.IntConsumer functionalInterface) {
		return new IntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.LongConsumer> LongConsumerAssert.Impl<A> assertThat(java.util.function.LongConsumer functionalInterface) {
		return new LongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.DoubleConsumer> DoubleConsumerAssert.Impl<A> assertThat(java.util.function.DoubleConsumer functionalInterface) {
		return new DoubleConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjIntConsumer<T>, T> ObjIntConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjIntConsumer<T> functionalInterface) {
		return new ObjIntConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjLongConsumer<T>, T> ObjLongConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjLongConsumer<T> functionalInterface) {
		return new ObjLongConsumerAssert.Impl(functionalInterface);
	}

	@Nonnull
	default <A extends java.util.function.ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.Impl<A, T> assertThat(java.util.function.ObjDoubleConsumer<T> functionalInterface) {
		return new ObjDoubleConsumerAssert.Impl(functionalInterface);
	}

}