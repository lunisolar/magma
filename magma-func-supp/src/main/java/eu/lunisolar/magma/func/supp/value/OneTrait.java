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

package eu.lunisolar.magma.func.supp.value;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Clazz;
import eu.lunisolar.magma.func.supp.opt.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

import static eu.lunisolar.magma.func.supp.Clazz.assuredClass;
import static eu.lunisolar.magma.func.supp.check.Checks.state;
import static eu.lunisolar.magma.func.supp.opt.Opt.opt;

/**
 * A set of terminal methods for value (in contrast to strictly SELF-fluent).
 * If trait is used for getting one of possible many values, "safety" or not - many values disqualify any method from producing result.
 */
@SuppressWarnings({"ConstantConditions", "unchecked"})
public interface OneTrait<T> {

	@Nullable
	T nullable();

	default @Nonnull T nonnull() {
		return state(nullable()).must(Be::notNull, "Value cannot be null!").value();
	}

	// <editor-fold desc="the...">

	/** Expectation: there MUST be a value. */
	default @Nonnull T theValue() {
		return state(nullable()).must(Be::notNull, "The value doe not exists.").value();
	}

	/** Expectation: there MUST be a value. */
	default @Nonnull <R> R theCast() {
		return (R) theValue();
	}

	/** Expectation: there MUST be a value, it MUST be of specific type */
	default @Nonnull <R> R theValue(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (R) state(theValue()).must1(Be::instanceOf, clazz, "Value is not of expected class", clazz).value();
	}

	default @Nonnull Byte theByte() {
		return theValue(Byte.class);
	}

	default @Nonnull Short theSrt() {
		return theValue(Short.class);
	}

	default @Nonnull Integer theInt() {
		return theValue(Integer.class);
	}

	default @Nonnull Long theLong() {
		return theValue(Long.class);
	}

	default @Nonnull Float theFlt() {
		return theValue(Float.class);
	}

	default @Nonnull Double theDbl() {
		return theValue(Double.class);
	}

	default @Nonnull Character theChar() {
		return theValue(Character.class);
	}

	default @Nonnull Boolean theBool() {
		return theValue(Boolean.class);
	}

	default @Nonnull String theStr() {
		return theValue(String.class);
	}

	default @Nonnull BigInteger theBigInt() {
		return theValue(BigInteger.class);
	}

	default @Nonnull BigDecimal theBigDec() {
		return theValue(BigDecimal.class);
	}

	default @Nonnull LocalDateTime theDateTime() {
		return theValue(LocalDateTime.class);
	}

	default @Nonnull LocalDate theDate() {
		return theValue(LocalDate.class);
	}

	default @Nonnull LocalTime theTime() {
		return theValue(LocalTime.class);
	}

	default @Nonnull OffsetDateTime theOffsetDateTime() {
		return theValue(OffsetDateTime.class);
	}

	default @Nonnull OffsetTime theOffsetTime() {
		return theValue(OffsetTime.class);
	}

	default @Nonnull ZonedDateTime theZonedDateTime() {
		return theValue(ZonedDateTime.class);
	}

	default @Nonnull Temporal theTemporal() {
		return theValue(Temporal.class);
	}

	default @Nonnull Duration theDuration() {
		return theValue(Duration.class);
	}

	default @Nonnull Period thePeriod() {
		return theValue(Period.class);
	}

	default @Nonnull Instant theInstant() {
		return theValue(Instant.class);
	}

	default @Nonnull MonthDay theMonthDay() {
		return theValue(MonthDay.class);
	}

	default @Nonnull DayOfWeek theDayOfWeek() {
		return theValue(DayOfWeek.class);
	}

	default @Nonnull Month theMonth() {
		return theValue(Month.class);
	}

	default @Nonnull Year theYear() {
		return theValue(Year.class);
	}

	default @Nonnull YearMonth theYearMonth() {
		return theValue(YearMonth.class);
	}

	default @Nonnull byte[] theByteArr() {
		return theValue(byte[].class);
	}

	// </editor-fold>

	// <editor-fold desc="a...">

	/** Expectation: there might be a value. */
	default @Nonnull Opt<T> aValue() {
		return assuredClass(Opt.class, this, me -> opt(me.nullable()));
	}

	/** Expectation: there might be a value. Casting variant to escape OneTrait<?>. */
	default @Nonnull <E> Opt<E> aCast() {
		return (Opt) aValue();
	}

	/** Expectation: there might be a value, it MUST be of specific type */
	default @Nonnull <R> Opt<R> aValue(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (Opt) aValue().ifExists(clazz, (__, c) -> __.must1(Be::instanceOf, c, "Value is not of expected class", c));
	}

	default @Nonnull OptByte aByte() {
		return aValue(Byte.class).mapToByte(v -> v);
	}

	default @Nonnull OptSrt aSrt() {
		return aValue(Short.class).mapToSrt(v -> v);
	}

	default @Nonnull OptInt aInt() {
		return aValue(Integer.class).mapToInt(v -> v);
	}

	default @Nonnull OptLong aLong() {
		return aValue(Long.class).mapToLong(v -> v);
	}

	default @Nonnull OptFlt aFlt() {
		return aValue(Float.class).mapToFlt(v -> v);
	}

	default @Nonnull OptDbl aDbl() {
		return aValue(Double.class).mapToDbl(v -> v);
	}

	default @Nonnull OptChar aChar() {
		return aValue(Character.class).mapToChar(v -> v);
	}

	default @Nonnull OptBool aBool() {
		return aValue(Boolean.class).mapToBool(v -> v);
	}

	default @Nonnull Opt<String> aStr() {
		return aValue(String.class);
	}

	default @Nonnull Opt<BigInteger> aBigInt() {
		return aValue(BigInteger.class);
	}

	default @Nonnull Opt<BigDecimal> aBigDec() {
		return aValue(BigDecimal.class);
	}

	default @Nonnull Opt<LocalDateTime> aDateTime() {
		return aValue(LocalDateTime.class);
	}

	default @Nonnull Opt<LocalDate> aDate() {
		return aValue(LocalDate.class);
	}

	default @Nonnull Opt<LocalTime> aTime() {
		return aValue(LocalTime.class);
	}

	default @Nonnull Opt<OffsetDateTime> aOffsetDateTime() {
		return aValue(OffsetDateTime.class);
	}

	default @Nonnull Opt<OffsetTime> aOffsetTime() {
		return aValue(OffsetTime.class);
	}

	default @Nonnull Opt<ZonedDateTime> aZonedDateTime() {
		return aValue(ZonedDateTime.class);
	}

	default @Nonnull Opt<Temporal> aTemporal() {
		return aValue(Temporal.class);
	}

	default @Nonnull Opt<Duration> aDuration() {
		return aValue(Duration.class);
	}

	default @Nonnull Opt<Period> aPeriod() {
		return aValue(Period.class);
	}

	default @Nonnull Opt<Instant> aInstant() {
		return aValue(Instant.class);
	}

	default @Nonnull Opt<MonthDay> aMonthDay() {
		return aValue(MonthDay.class);
	}

	default @Nonnull Opt<DayOfWeek> aDayOfWeek() {
		return aValue(DayOfWeek.class);
	}

	default @Nonnull Opt<Month> aMonth() {
		return aValue(Month.class);
	}

	default @Nonnull Opt<Year> aYear() {
		return aValue(Year.class);
	}

	default @Nonnull Opt<YearMonth> aYearMonth() {
		return aValue(YearMonth.class);
	}

	default @Nonnull Opt<byte[]> aByteArr() {
		return aValue(byte[].class);
	}

	// </editor-fold>

	// <editor-fold desc="aSafe...">

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull <R> Opt<R> aSafeValue(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return aValue().filterAndMap(clazz);
	}

	default @Nonnull OptByte aSafeByte() {
		return aSafeValue(Byte.class).mapToByte(v -> v);
	}

	default @Nonnull OptSrt aSafeSrt() {
		return aSafeValue(Short.class).mapToSrt(v -> v);
	}

	default @Nonnull OptInt aSafeInt() {
		return aSafeValue(Integer.class).mapToInt(v -> v);
	}

	default @Nonnull OptLong aSafeLong() {
		return aSafeValue(Long.class).mapToLong(v -> v);
	}

	default @Nonnull OptFlt aSafeFlt() {
		return aSafeValue(Float.class).mapToFlt(v -> v);
	}

	default @Nonnull OptDbl aSafeDbl() {
		return aSafeValue(Double.class).mapToDbl(v -> v);
	}

	default @Nonnull OptChar aSafeChar() {
		return aSafeValue(Character.class).mapToChar(v -> v);
	}

	default @Nonnull OptBool aSafeBool() {
		return aSafeValue(Boolean.class).mapToBool(v -> v);
	}

	default @Nonnull Opt<String> aSafeStr() {
		return aSafeValue(String.class);
	}

	default @Nonnull Opt<BigInteger> aSafeBigInt() {
		return aSafeValue(BigInteger.class);
	}

	default @Nonnull Opt<BigDecimal> aSafeBigDec() {
		return aSafeValue(BigDecimal.class);
	}

	default @Nonnull Opt<LocalDateTime> aSafeDateTime() {
		return aSafeValue(LocalDateTime.class);
	}

	default @Nonnull Opt<LocalDate> aSafeDate() {
		return aSafeValue(LocalDate.class);
	}

	default @Nonnull Opt<LocalTime> aSafeTime() {
		return aSafeValue(LocalTime.class);
	}

	default @Nonnull Opt<OffsetDateTime> aSafeOffsetDateTime() {
		return aSafeValue(OffsetDateTime.class);
	}

	default @Nonnull Opt<OffsetTime> aSafeOffsetTime() {
		return aSafeValue(OffsetTime.class);
	}

	default @Nonnull Opt<ZonedDateTime> aSafeZonedDateTime() {
		return aSafeValue(ZonedDateTime.class);
	}

	default @Nonnull Opt<Temporal> aSafeTemporal() {
		return aSafeValue(Temporal.class);
	}

	default @Nonnull Opt<Duration> aSafeDuration() {
		return aSafeValue(Duration.class);
	}

	default @Nonnull Opt<Period> aSafePeriod() {
		return aSafeValue(Period.class);
	}

	default @Nonnull Opt<Instant> aSafeInstant() {
		return aSafeValue(Instant.class);
	}

	default @Nonnull Opt<MonthDay> aSafeMonthDay() {
		return aSafeValue(MonthDay.class);
	}

	default @Nonnull Opt<DayOfWeek> aSafeDayOfWeek() {
		return aSafeValue(DayOfWeek.class);
	}

	default @Nonnull Opt<Month> aSafeMonth() {
		return aSafeValue(Month.class);
	}

	default @Nonnull Opt<Year> aSafeYear() {
		return aSafeValue(Year.class);
	}

	default @Nonnull Opt<YearMonth> aSafeYearMonth() {
		return aSafeValue(YearMonth.class);
	}

	default @Nonnull Opt<byte[]> aSafeByteArr() {
		return aSafeValue(byte[].class);
	}

	// </editor-fold>

	// <editor-fold desc="or...">

	default T orElse(@Nullable T defaultValue) {
		Opt<T> o = (Opt) aValue(defaultValue.getClass());
		return o.orElse(defaultValue);
	}

	default T orElseObj(@Nullable T defaultValue) {
		return orElse(defaultValue);
	}

	default byte orElseByte(byte defaultValue) {
		return aByte().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte alt(byte defaultValue) {
		return orElseByte(defaultValue);
	}

	default short orElseSrt(short defaultValue) {
		return aSrt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default short alt(short defaultValue) {
		return orElseSrt(defaultValue);
	}

	default int orElseInt(int defaultValue) {
		return aInt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default int alt(int defaultValue) {
		return orElseInt(defaultValue);
	}

	default long orElseLong(long defaultValue) {
		return aLong().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default long alt(long defaultValue) {
		return orElseLong(defaultValue);
	}

	default float orElseFlt(float defaultValue) {
		return aFlt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default float alt(float defaultValue) {
		return orElseFlt(defaultValue);
	}

	default double orElseDbl(double defaultValue) {
		return aDbl().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default double alt(double defaultValue) {
		return orElseDbl(defaultValue);
	}

	default char orElseChar(char defaultValue) {
		return aChar().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default char alt(char defaultValue) {
		return orElseChar(defaultValue);
	}

	default boolean orElseBool(boolean defaultValue) {
		return aBool().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default boolean alt(boolean defaultValue) {
		return orElseBool(defaultValue);
	}

	default String orElseStr(String defaultValue) {
		return aStr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default String alt(String defaultValue) {
		return orElseStr(defaultValue);
	}

	default BigInteger orElseBigInt(BigInteger defaultValue) {
		return aBigInt().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigInteger alt(BigInteger defaultValue) {
		return orElseBigInt(defaultValue);
	}

	default BigDecimal orElseBigDec(BigDecimal defaultValue) {
		return aBigDec().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigDecimal alt(BigDecimal defaultValue) {
		return orElseBigDec(defaultValue);
	}

	default LocalDateTime orElseDateTime(LocalDateTime defaultValue) {
		return aDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDateTime alt(LocalDateTime defaultValue) {
		return orElseDateTime(defaultValue);
	}

	default LocalDate orElseDate(LocalDate defaultValue) {
		return aDate().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDate alt(LocalDate defaultValue) {
		return orElseDate(defaultValue);
	}

	default LocalTime orElseTime(LocalTime defaultValue) {
		return aTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalTime alt(LocalTime defaultValue) {
		return orElseTime(defaultValue);
	}

	default OffsetDateTime orElseOffsetDateTime(OffsetDateTime defaultValue) {
		return aOffsetDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetDateTime alt(OffsetDateTime defaultValue) {
		return orElseOffsetDateTime(defaultValue);
	}

	default OffsetTime orElseOffsetTime(OffsetTime defaultValue) {
		return aOffsetTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetTime alt(OffsetTime defaultValue) {
		return orElseOffsetTime(defaultValue);
	}

	default ZonedDateTime orElseZonedDateTime(ZonedDateTime defaultValue) {
		return aZonedDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default ZonedDateTime alt(ZonedDateTime defaultValue) {
		return orElseZonedDateTime(defaultValue);
	}

	default Temporal orElseTemporal(Temporal defaultValue) {
		return aTemporal().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Temporal alt(Temporal defaultValue) {
		return orElseTemporal(defaultValue);
	}

	default Duration orElseDuration(Duration defaultValue) {
		return aDuration().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Duration alt(Duration defaultValue) {
		return orElseDuration(defaultValue);
	}

	default Period orElsePeriod(Period defaultValue) {
		return aPeriod().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Period alt(Period defaultValue) {
		return orElsePeriod(defaultValue);
	}

	default Instant orElseInstant(Instant defaultValue) {
		return aInstant().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Instant alt(Instant defaultValue) {
		return orElseInstant(defaultValue);
	}

	default MonthDay orElseMonthDay(MonthDay defaultValue) {
		return aMonthDay().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default MonthDay alt(MonthDay defaultValue) {
		return orElseMonthDay(defaultValue);
	}

	default DayOfWeek orElseDayOfWeek(DayOfWeek defaultValue) {
		return aDayOfWeek().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default DayOfWeek alt(DayOfWeek defaultValue) {
		return orElseDayOfWeek(defaultValue);
	}

	default Month orElseMonth(Month defaultValue) {
		return aMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Month alt(Month defaultValue) {
		return orElseMonth(defaultValue);
	}

	default Year orElseYear(Year defaultValue) {
		return aYear().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Year alt(Year defaultValue) {
		return orElseYear(defaultValue);
	}

	default YearMonth orElseYearMonth(YearMonth defaultValue) {
		return aYearMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default YearMonth alt(YearMonth defaultValue) {
		return orElseYearMonth(defaultValue);
	}

	default byte[] orElseByteArr(byte[] defaultValue) {
		return aByteArr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte[] alt(byte[] defaultValue) {
		return orElseByteArr(defaultValue);
	}

	// </editor-fold>

	// <editor-fold desc="orSafe...">

	default T orSafeElse(@Nullable T defaultValue) {
		Opt<T> o = (Opt) aSafeValue(defaultValue.getClass());
		return o.orElse(defaultValue);
	}

	default T orSafeElseObj(@Nullable T defaultValue) {
		return orSafeElse(defaultValue);
	}

	default byte orSafeElseByte(byte defaultValue) {
		return aSafeByte().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte altSafe(byte defaultValue) {
		return orSafeElseByte(defaultValue);
	}

	default short orSafeElseSrt(short defaultValue) {
		return aSafeSrt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default short altSafe(short defaultValue) {
		return orSafeElseSrt(defaultValue);
	}

	default int orSafeElseInt(int defaultValue) {
		return aSafeInt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default int altSafe(int defaultValue) {
		return orSafeElseInt(defaultValue);
	}

	default long orSafeElseLong(long defaultValue) {
		return aSafeLong().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default long altSafe(long defaultValue) {
		return orSafeElseLong(defaultValue);
	}

	default float orSafeElseFlt(float defaultValue) {
		return aSafeFlt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default float altSafe(float defaultValue) {
		return orSafeElseFlt(defaultValue);
	}

	default double orSafeElseDbl(double defaultValue) {
		return aSafeDbl().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default double altSafe(double defaultValue) {
		return orSafeElseDbl(defaultValue);
	}

	default char orSafeElseChar(char defaultValue) {
		return aSafeChar().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default char altSafe(char defaultValue) {
		return orSafeElseChar(defaultValue);
	}

	default boolean orSafeElseBool(boolean defaultValue) {
		return aSafeBool().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default boolean altSafe(boolean defaultValue) {
		return orSafeElseBool(defaultValue);
	}

	default String orSafeElseStr(String defaultValue) {
		return aSafeStr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default String altSafe(String defaultValue) {
		return orSafeElseStr(defaultValue);
	}

	default BigInteger orSafeElseBigInt(BigInteger defaultValue) {
		return aSafeBigInt().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigInteger altSafe(BigInteger defaultValue) {
		return orSafeElseBigInt(defaultValue);
	}

	default BigDecimal orSafeElseBigDec(BigDecimal defaultValue) {
		return aSafeBigDec().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigDecimal altSafe(BigDecimal defaultValue) {
		return orSafeElseBigDec(defaultValue);
	}

	default LocalDateTime orSafeElseDateTime(LocalDateTime defaultValue) {
		return aSafeDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDateTime altSafe(LocalDateTime defaultValue) {
		return orSafeElseDateTime(defaultValue);
	}

	default LocalDate orSafeElseDate(LocalDate defaultValue) {
		return aSafeDate().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDate altSafe(LocalDate defaultValue) {
		return orSafeElseDate(defaultValue);
	}

	default LocalTime orSafeElseTime(LocalTime defaultValue) {
		return aSafeTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalTime altSafe(LocalTime defaultValue) {
		return orSafeElseTime(defaultValue);
	}

	default OffsetDateTime orSafeElseOffsetDateTime(OffsetDateTime defaultValue) {
		return aSafeOffsetDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetDateTime altSafe(OffsetDateTime defaultValue) {
		return orSafeElseOffsetDateTime(defaultValue);
	}

	default OffsetTime orSafeElseOffsetTime(OffsetTime defaultValue) {
		return aSafeOffsetTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetTime altSafe(OffsetTime defaultValue) {
		return orSafeElseOffsetTime(defaultValue);
	}

	default ZonedDateTime orSafeElseZonedDateTime(ZonedDateTime defaultValue) {
		return aSafeZonedDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default ZonedDateTime altSafe(ZonedDateTime defaultValue) {
		return orSafeElseZonedDateTime(defaultValue);
	}

	default Temporal orSafeElseTemporal(Temporal defaultValue) {
		return aSafeTemporal().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Temporal altSafe(Temporal defaultValue) {
		return orSafeElseTemporal(defaultValue);
	}

	default Duration orSafeElseDuration(Duration defaultValue) {
		return aSafeDuration().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Duration altSafe(Duration defaultValue) {
		return orSafeElseDuration(defaultValue);
	}

	default Period orSafeElsePeriod(Period defaultValue) {
		return aSafePeriod().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Period altSafe(Period defaultValue) {
		return orSafeElsePeriod(defaultValue);
	}

	default Instant orSafeElseInstant(Instant defaultValue) {
		return aSafeInstant().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Instant altSafe(Instant defaultValue) {
		return orSafeElseInstant(defaultValue);
	}

	default MonthDay orSafeElseMonthDay(MonthDay defaultValue) {
		return aSafeMonthDay().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default MonthDay altSafe(MonthDay defaultValue) {
		return orSafeElseMonthDay(defaultValue);
	}

	default DayOfWeek orSafeElseDayOfWeek(DayOfWeek defaultValue) {
		return aSafeDayOfWeek().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default DayOfWeek altSafe(DayOfWeek defaultValue) {
		return orSafeElseDayOfWeek(defaultValue);
	}

	default Month orSafeElseMonth(Month defaultValue) {
		return aSafeMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Month altSafe(Month defaultValue) {
		return orSafeElseMonth(defaultValue);
	}

	default Year orSafeElseYear(Year defaultValue) {
		return aSafeYear().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Year altSafe(Year defaultValue) {
		return orSafeElseYear(defaultValue);
	}

	default YearMonth orSafeElseYearMonth(YearMonth defaultValue) {
		return aSafeYearMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default YearMonth altSafe(YearMonth defaultValue) {
		return orSafeElseYearMonth(defaultValue);
	}

	default byte[] orSafeElseByteArr(byte[] defaultValue) {
		return aSafeByteArr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte[] altSafe(byte[] defaultValue) {
		return orSafeElseByteArr(defaultValue);
	}

	// </editor-fold>

	// <editor-fold desc="enum">

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull <T extends Enum<T>> Opt<T> aEnum(@Nonnull Class<T> enumClazz) {
		Null.nonNullArg(enumClazz, "enumClazz");
		return Clazz.aEnum(enumClazz, nullable(), x -> {
			throw x;
		});
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull <T extends Enum<T>> Opt<T> aSafeEnum(@Nonnull Class<T> enumClazz) {
		Null.nonNullArg(enumClazz, "enumClazz");
		return Clazz.aEnum(enumClazz, nullable(), x -> null);
	}

	default @Nonnull <T extends Enum<T>> T theEnum(@Nonnull Class<T> enumClazz) {
		Null.nonNullArg(enumClazz, "enumClazz");
		return Clazz.theEnum(enumClazz, nullable());
	}

	default @Nonnull <T extends Enum<T>> T aEnum(@Nonnull T defaultValue) {
		Null.nonNullArg(defaultValue, "defaultValue");
		return (T) aEnum(defaultValue.getClass()).orElse(defaultValue);
	}

	default @Nonnull <T extends Enum<T>> T aSafeEnum(@Nonnull T defaultValue) {
		Null.nonNullArg(defaultValue, "defaultValue");
		return (T) aSafeEnum(defaultValue.getClass()).orElse(defaultValue);
	}

	// </editor-fold>

}
