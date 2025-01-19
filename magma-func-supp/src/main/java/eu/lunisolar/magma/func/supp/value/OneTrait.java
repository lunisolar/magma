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
 */
@SuppressWarnings({"ConstantConditions", "unchecked"})
public interface OneTrait<T> {

	@Nullable
	T nullable();

	default @Nullable T any() {
		return nullable();
	}

	default @Nonnull T nonnull() {
		return state(nullable()).must(Be::notNull, "Value cannot be null!").value();
	}

	//<editor-fold desc="the...">

	/** Expectation: there MUST be a value. */
	default @Nonnull T theValue() {
		return state(nullable()).must(Be::notNull, "The value doe not exists.").value();
	}

	/** Expectation: there MUST be a value. */
	default @Nonnull <R> R theCast() {
		return (R) theValue();
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull <R> R theValue(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (R) state(theValue()).must1(Be::instanceOf, clazz, "Value is not of expected class", clazz).value();
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Byte theByte() {
		return theValue(Byte.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Short theSrt() {
		return theValue(Short.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Integer theInt() {
		return theValue(Integer.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Long theLong() {
		return theValue(Long.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Float theFlt() {
		return theValue(Float.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Double theDbl() {
		return theValue(Double.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Character theChar() {
		return theValue(Character.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Boolean theBool() {
		return theValue(Boolean.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull String theStr() {
		return theValue(String.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull BigInteger theBigInt() {
		return theValue(BigInteger.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull BigDecimal theBigDec() {
		return theValue(BigDecimal.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull LocalDateTime theDateTime() {
		return theValue(LocalDateTime.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull LocalDate theDate() {
		return theValue(LocalDate.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull LocalTime theTime() {
		return theValue(LocalTime.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull OffsetDateTime theOffsetDateTime() {
		return theValue(OffsetDateTime.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull OffsetTime theOffsetTime() {
		return theValue(OffsetTime.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull ZonedDateTime theZonedDateTime() {
		return theValue(ZonedDateTime.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Temporal theTemporal() {
		return theValue(Temporal.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Duration theDuration() {
		return theValue(Duration.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Period thePeriod() {
		return theValue(Period.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Instant theInstant() {
		return theValue(Instant.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull MonthDay theMonthDay() {
		return theValue(MonthDay.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull DayOfWeek theDayOfWeek() {
		return theValue(DayOfWeek.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Month theMonth() {
		return theValue(Month.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull Year theYear() {
		return theValue(Year.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull YearMonth theYearMonth() {
		return theValue(YearMonth.class);
	}

	/** Expectation: there MUST be a value, it MUST be of specific type. */
	default @Nonnull byte[] theByteArr() {
		return theValue(byte[].class);
	}

	//</editor-fold>

	//<editor-fold desc="a...">

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

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptByte aByte() {
		return aValue(Byte.class).mapToByte(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptSrt aSrt() {
		return aValue(Short.class).mapToSrt(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptInt aInt() {
		return aValue(Integer.class).mapToInt(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptLong aLong() {
		return aValue(Long.class).mapToLong(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptFlt aFlt() {
		return aValue(Float.class).mapToFlt(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptDbl aDbl() {
		return aValue(Double.class).mapToDbl(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptChar aChar() {
		return aValue(Character.class).mapToChar(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull OptBool aBool() {
		return aValue(Boolean.class).mapToBool(v -> v);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<String> aStr() {
		return aValue(String.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<BigInteger> aBigInt() {
		return aValue(BigInteger.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<BigDecimal> aBigDec() {
		return aValue(BigDecimal.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<LocalDateTime> aDateTime() {
		return aValue(LocalDateTime.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<LocalDate> aDate() {
		return aValue(LocalDate.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<LocalTime> aTime() {
		return aValue(LocalTime.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<OffsetDateTime> aOffsetDateTime() {
		return aValue(OffsetDateTime.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<OffsetTime> aOffsetTime() {
		return aValue(OffsetTime.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<ZonedDateTime> aZonedDateTime() {
		return aValue(ZonedDateTime.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Temporal> aTemporal() {
		return aValue(Temporal.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Duration> aDuration() {
		return aValue(Duration.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Period> aPeriod() {
		return aValue(Period.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Instant> aInstant() {
		return aValue(Instant.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<MonthDay> aMonthDay() {
		return aValue(MonthDay.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<DayOfWeek> aDayOfWeek() {
		return aValue(DayOfWeek.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Month> aMonth() {
		return aValue(Month.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<Year> aYear() {
		return aValue(Year.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<YearMonth> aYearMonth() {
		return aValue(YearMonth.class);
	}

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull Opt<byte[]> aByteArr() {
		return aValue(byte[].class);
	}

	//</editor-fold>

	//<editor-fold desc="filter...">

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull <R> Opt<R> filter(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return aValue().filterAndMap(clazz);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptByte filterByte() {
		return filter(Byte.class).mapToByte(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptSrt filterSrt() {
		return filter(Short.class).mapToSrt(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptInt filterInt() {
		return filter(Integer.class).mapToInt(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptLong filterLong() {
		return filter(Long.class).mapToLong(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptFlt filterFlt() {
		return filter(Float.class).mapToFlt(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptDbl filterDbl() {
		return filter(Double.class).mapToDbl(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptChar filterChar() {
		return filter(Character.class).mapToChar(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull OptBool filterBool() {
		return filter(Boolean.class).mapToBool(v -> v);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<String> filterStr() {
		return filter(String.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<BigInteger> filterBigInt() {
		return filter(BigInteger.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<BigDecimal> filterBigDec() {
		return filter(BigDecimal.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<LocalDateTime> filterDateTime() {
		return filter(LocalDateTime.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<LocalDate> filterDate() {
		return filter(LocalDate.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<LocalTime> filterTime() {
		return filter(LocalTime.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<OffsetDateTime> filterOffsetDateTime() {
		return filter(OffsetDateTime.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<OffsetTime> filterOffsetTime() {
		return filter(OffsetTime.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<ZonedDateTime> filterZonedDateTime() {
		return filter(ZonedDateTime.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Temporal> filterTemporal() {
		return filter(Temporal.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Duration> filterDuration() {
		return filter(Duration.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Period> filterPeriod() {
		return filter(Period.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Instant> filterInstant() {
		return filter(Instant.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<MonthDay> filterMonthDay() {
		return filter(MonthDay.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<DayOfWeek> filterDayOfWeek() {
		return filter(DayOfWeek.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Month> filterMonth() {
		return filter(Month.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<Year> filterYear() {
		return filter(Year.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<YearMonth> filterYearMonth() {
		return filter(YearMonth.class);
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull Opt<byte[]> filterByteArr() {
		return filter(byte[].class);
	}

	//</editor-fold>

	//<editor-fold desc="or...">

	default T orElse(@Nullable T defaultValue) {
		var value = nullable();
		return value == null ? defaultValue : value;
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

	//</editor-fold>

	//<editor-fold desc="filterElse...">

	default T filterElse(@Nonnull T defaultValue) {
		Null.nonNullArg(defaultValue, "defaultValue");
		Opt<T> o = (Opt) filter(defaultValue.getClass());
		return o.orElse(defaultValue);
	}

	default T filterElseObj(@Nonnull T defaultValue) {
		return filterElse(defaultValue);
	}

	default byte filterOrElseByte(byte defaultValue) {
		return filterByte().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte filterAlt(byte defaultValue) {
		return filterOrElseByte(defaultValue);
	}

	default short filterOrElseSrt(short defaultValue) {
		return filterSrt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default short filterAlt(short defaultValue) {
		return filterOrElseSrt(defaultValue);
	}

	default int filterOrElseInt(int defaultValue) {
		return filterInt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default int filterAlt(int defaultValue) {
		return filterOrElseInt(defaultValue);
	}

	default long filterOrElseLong(long defaultValue) {
		return filterLong().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default long filterAlt(long defaultValue) {
		return filterOrElseLong(defaultValue);
	}

	default float filterOrElseFlt(float defaultValue) {
		return filterFlt().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default float filterAlt(float defaultValue) {
		return filterOrElseFlt(defaultValue);
	}

	default double filterOrElseDbl(double defaultValue) {
		return filterDbl().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default double filterAlt(double defaultValue) {
		return filterOrElseDbl(defaultValue);
	}

	default char filterOrElseChar(char defaultValue) {
		return filterChar().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default char filterAlt(char defaultValue) {
		return filterOrElseChar(defaultValue);
	}

	default boolean filterOrElseBool(boolean defaultValue) {
		return filterBool().orElse(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default boolean filterAlt(boolean defaultValue) {
		return filterOrElseBool(defaultValue);
	}

	default String filterOrElseStr(String defaultValue) {
		return filterStr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default String filterAlt(String defaultValue) {
		return filterOrElseStr(defaultValue);
	}

	default BigInteger filterOrElseBigInt(BigInteger defaultValue) {
		return filterBigInt().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigInteger filterAlt(BigInteger defaultValue) {
		return filterOrElseBigInt(defaultValue);
	}

	default BigDecimal filterOrElseBigDec(BigDecimal defaultValue) {
		return filterBigDec().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default BigDecimal filterAlt(BigDecimal defaultValue) {
		return filterOrElseBigDec(defaultValue);
	}

	default LocalDateTime filterOrElseDateTime(LocalDateTime defaultValue) {
		return filterDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDateTime filterAlt(LocalDateTime defaultValue) {
		return filterOrElseDateTime(defaultValue);
	}

	default LocalDate filterOrElseDate(LocalDate defaultValue) {
		return filterDate().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalDate filterAlt(LocalDate defaultValue) {
		return filterOrElseDate(defaultValue);
	}

	default LocalTime filterOrElseTime(LocalTime defaultValue) {
		return filterTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default LocalTime filterAlt(LocalTime defaultValue) {
		return filterOrElseTime(defaultValue);
	}

	default OffsetDateTime filterOrElseOffsetDateTime(OffsetDateTime defaultValue) {
		return filterOffsetDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetDateTime filterAlt(OffsetDateTime defaultValue) {
		return filterOrElseOffsetDateTime(defaultValue);
	}

	default OffsetTime filterOrElseOffsetTime(OffsetTime defaultValue) {
		return filterOffsetTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default OffsetTime filterAlt(OffsetTime defaultValue) {
		return filterOrElseOffsetTime(defaultValue);
	}

	default ZonedDateTime filterOrElseZonedDateTime(ZonedDateTime defaultValue) {
		return filterZonedDateTime().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default ZonedDateTime filterAlt(ZonedDateTime defaultValue) {
		return filterOrElseZonedDateTime(defaultValue);
	}

	default Temporal filterOrElseTemporal(Temporal defaultValue) {
		return filterTemporal().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Temporal filterAlt(Temporal defaultValue) {
		return filterOrElseTemporal(defaultValue);
	}

	default Duration filterOrElseDuration(Duration defaultValue) {
		return filterDuration().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Duration filterAlt(Duration defaultValue) {
		return filterOrElseDuration(defaultValue);
	}

	default Period filterOrElsePeriod(Period defaultValue) {
		return filterPeriod().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Period filterAlt(Period defaultValue) {
		return filterOrElsePeriod(defaultValue);
	}

	default Instant filterOrElseInstant(Instant defaultValue) {
		return filterInstant().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Instant filterAlt(Instant defaultValue) {
		return filterOrElseInstant(defaultValue);
	}

	default MonthDay filterOrElseMonthDay(MonthDay defaultValue) {
		return filterMonthDay().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default MonthDay filterAlt(MonthDay defaultValue) {
		return filterOrElseMonthDay(defaultValue);
	}

	default DayOfWeek filterOrElseDayOfWeek(DayOfWeek defaultValue) {
		return filterDayOfWeek().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default DayOfWeek filterAlt(DayOfWeek defaultValue) {
		return filterOrElseDayOfWeek(defaultValue);
	}

	default Month filterOrElseMonth(Month defaultValue) {
		return filterMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Month filterAlt(Month defaultValue) {
		return filterOrElseMonth(defaultValue);
	}

	default Year filterOrElseYear(Year defaultValue) {
		return filterYear().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default Year filterAlt(Year defaultValue) {
		return filterOrElseYear(defaultValue);
	}

	default YearMonth filterOrElseYearMonth(YearMonth defaultValue) {
		return filterYearMonth().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default YearMonth filterAlt(YearMonth defaultValue) {
		return filterOrElseYearMonth(defaultValue);
	}

	default byte[] filterOrElseByteArr(byte[] defaultValue) {
		return filterByteArr().orElseObj(defaultValue);
	}

	/** Name non-conflicting alternative to Opt.orElse. It will probably conflict with its own variants when trying to reference OneTrait::alt */
	default byte[] filterAlt(byte[] defaultValue) {
		return filterOrElseByteArr(defaultValue);
	}

	//</editor-fold>

	//<editor-fold desc="enum">

	/** Expectation: there might be a value, it MUST be of specific type. */
	default @Nonnull <T extends Enum<T>> Opt<T> aEnum(@Nonnull Class<T> enumClazz) {
		Null.nonNullArg(enumClazz, "enumClazz");
		return Clazz.aEnum(enumClazz, nullable(), x -> {
			throw x;
		});
	}

	/** Expectation: there might be a value, it might be of specific type. */
	default @Nonnull <T extends Enum<T>> Opt<T> filterEnum(@Nonnull Class<T> enumClazz) {
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

	default @Nonnull <T extends Enum<T>> T filterEnum(@Nonnull T defaultValue) {
		Null.nonNullArg(defaultValue, "defaultValue");
		return (T) filterEnum(defaultValue.getClass()).orElse(defaultValue);
	}

	//</editor-fold>

}
