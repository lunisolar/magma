/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.check;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.Fluent; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.FluentSyntax;

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Common validations.
 */
public final class Checks implements FluentSyntax {

	// <editor-fold desc="no instance">
	private Checks() {
	}
	// </editor-fold>

	@ThreadSafe
	public final static class CheckBool implements CheckBoolTrait<CheckBool> {

		private final String type;
		private final boolean value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckBool(boolean value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final boolean get() {
			return value;
		}

		public @Nonnull CheckBool value(boolean value) {
			return new CheckBool(value, name, factory, type);
		}

	}

	public static CheckBool arg(boolean value) {
		return new CheckBool(value, "?", X::arg, "Argument");
	}

	public static CheckBool arg(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::arg, "Argument");
	}

	public static CheckBool value(boolean value) {
		return new CheckBool(value, "?", X::value, "Value");
	}

	public static CheckBool value(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::value, "Value");
	}

	public static CheckBool state(boolean value) {
		return new CheckBool(value, "?", X::state, "State");
	}

	public static CheckBool state(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::state, "State");
	}

	public static CheckBool check(boolean value, ExMF<RuntimeException> factory) {
		return new CheckBool(value, "?", factory, "Check");
	}

	public static CheckBool check(boolean value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckBool(value, name, factory, "Check");
	}

	public static CheckBool attest(boolean value, ExMF<Error> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckBool attest(boolean value, @Nullable String name, ExMF<Error> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckBool attest(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckBool attest(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class Check<T> implements CheckTrait<T, Check<T>> {

		private final String type;
		private final @Nullable T value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private Check(@Nullable T value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final T get() {
			return value;
		}

		public @Nonnull Check<T> value(@Nullable T value) {
			return new Check(value, name, factory, type);
		}

	}

	public static <T> Check<T> arg(@Nullable T value) {
		return new Check<T>(value, "?", X::arg, "Argument");
	}

	public static <T> Check<T> arg(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::arg, "Argument");
	}

	public static <T> Check<T> value(@Nullable T value) {
		return new Check<T>(value, "?", X::value, "Value");
	}

	public static <T> Check<T> value(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::value, "Value");
	}

	public static <T> Check<T> state(@Nullable T value) {
		return new Check<T>(value, "?", X::state, "State");
	}

	public static <T> Check<T> state(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::state, "State");
	}

	public static <T> Check<T> check(@Nullable T value, ExMF<RuntimeException> factory) {
		return new Check<T>(value, "?", factory, "Check");
	}

	public static <T> Check<T> check(@Nullable T value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new Check<T>(value, name, factory, "Check");
	}

	public static <T> Check<T> attest(@Nullable T value, ExMF<Error> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "Check");
	}

	public static <T> Check<T> attest(@Nullable T value, @Nullable String name, ExMF<Error> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "Check");
	}

	public static <T> Check<T> attest(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static <T> Check<T> attest(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckByte implements CheckByteTrait<CheckByte> {

		private final String type;
		private final byte value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckByte(byte value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final byte get() {
			return value;
		}

		public @Nonnull CheckByte value(byte value) {
			return new CheckByte(value, name, factory, type);
		}

	}

	public static CheckByte arg(byte value) {
		return new CheckByte(value, "?", X::arg, "Argument");
	}

	public static CheckByte arg(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::arg, "Argument");
	}

	public static CheckByte value(byte value) {
		return new CheckByte(value, "?", X::value, "Value");
	}

	public static CheckByte value(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::value, "Value");
	}

	public static CheckByte state(byte value) {
		return new CheckByte(value, "?", X::state, "State");
	}

	public static CheckByte state(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::state, "State");
	}

	public static CheckByte check(byte value, ExMF<RuntimeException> factory) {
		return new CheckByte(value, "?", factory, "Check");
	}

	public static CheckByte check(byte value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckByte(value, name, factory, "Check");
	}

	public static CheckByte attest(byte value, ExMF<Error> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckByte attest(byte value, @Nullable String name, ExMF<Error> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckByte attest(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckByte attest(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckDbl implements CheckDblTrait<CheckDbl> {

		private final String type;
		private final double value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckDbl(double value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final double get() {
			return value;
		}

		public @Nonnull CheckDbl value(double value) {
			return new CheckDbl(value, name, factory, type);
		}

	}

	public static CheckDbl arg(double value) {
		return new CheckDbl(value, "?", X::arg, "Argument");
	}

	public static CheckDbl arg(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::arg, "Argument");
	}

	public static CheckDbl value(double value) {
		return new CheckDbl(value, "?", X::value, "Value");
	}

	public static CheckDbl value(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::value, "Value");
	}

	public static CheckDbl state(double value) {
		return new CheckDbl(value, "?", X::state, "State");
	}

	public static CheckDbl state(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::state, "State");
	}

	public static CheckDbl check(double value, ExMF<RuntimeException> factory) {
		return new CheckDbl(value, "?", factory, "Check");
	}

	public static CheckDbl check(double value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckDbl(value, name, factory, "Check");
	}

	public static CheckDbl attest(double value, ExMF<Error> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckDbl attest(double value, @Nullable String name, ExMF<Error> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckDbl attest(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckDbl attest(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckChar implements CheckCharTrait<CheckChar> {

		private final String type;
		private final char value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckChar(char value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final char get() {
			return value;
		}

		public @Nonnull CheckChar value(char value) {
			return new CheckChar(value, name, factory, type);
		}

	}

	public static CheckChar arg(char value) {
		return new CheckChar(value, "?", X::arg, "Argument");
	}

	public static CheckChar arg(char value, @Nullable String name) {
		return new CheckChar(value, name, X::arg, "Argument");
	}

	public static CheckChar value(char value) {
		return new CheckChar(value, "?", X::value, "Value");
	}

	public static CheckChar value(char value, @Nullable String name) {
		return new CheckChar(value, name, X::value, "Value");
	}

	public static CheckChar state(char value) {
		return new CheckChar(value, "?", X::state, "State");
	}

	public static CheckChar state(char value, @Nullable String name) {
		return new CheckChar(value, name, X::state, "State");
	}

	public static CheckChar check(char value, ExMF<RuntimeException> factory) {
		return new CheckChar(value, "?", factory, "Check");
	}

	public static CheckChar check(char value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckChar(value, name, factory, "Check");
	}

	public static CheckChar attest(char value, ExMF<Error> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckChar attest(char value, @Nullable String name, ExMF<Error> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckChar attest(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckChar attest(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckSrt implements CheckSrtTrait<CheckSrt> {

		private final String type;
		private final short value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckSrt(short value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final short get() {
			return value;
		}

		public @Nonnull CheckSrt value(short value) {
			return new CheckSrt(value, name, factory, type);
		}

	}

	public static CheckSrt arg(short value) {
		return new CheckSrt(value, "?", X::arg, "Argument");
	}

	public static CheckSrt arg(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::arg, "Argument");
	}

	public static CheckSrt value(short value) {
		return new CheckSrt(value, "?", X::value, "Value");
	}

	public static CheckSrt value(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::value, "Value");
	}

	public static CheckSrt state(short value) {
		return new CheckSrt(value, "?", X::state, "State");
	}

	public static CheckSrt state(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::state, "State");
	}

	public static CheckSrt check(short value, ExMF<RuntimeException> factory) {
		return new CheckSrt(value, "?", factory, "Check");
	}

	public static CheckSrt check(short value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckSrt(value, name, factory, "Check");
	}

	public static CheckSrt attest(short value, ExMF<Error> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckSrt attest(short value, @Nullable String name, ExMF<Error> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckSrt attest(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckSrt attest(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckFlt implements CheckFltTrait<CheckFlt> {

		private final String type;
		private final float value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckFlt(float value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final float get() {
			return value;
		}

		public @Nonnull CheckFlt value(float value) {
			return new CheckFlt(value, name, factory, type);
		}

	}

	public static CheckFlt arg(float value) {
		return new CheckFlt(value, "?", X::arg, "Argument");
	}

	public static CheckFlt arg(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::arg, "Argument");
	}

	public static CheckFlt value(float value) {
		return new CheckFlt(value, "?", X::value, "Value");
	}

	public static CheckFlt value(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::value, "Value");
	}

	public static CheckFlt state(float value) {
		return new CheckFlt(value, "?", X::state, "State");
	}

	public static CheckFlt state(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::state, "State");
	}

	public static CheckFlt check(float value, ExMF<RuntimeException> factory) {
		return new CheckFlt(value, "?", factory, "Check");
	}

	public static CheckFlt check(float value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckFlt(value, name, factory, "Check");
	}

	public static CheckFlt attest(float value, ExMF<Error> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckFlt attest(float value, @Nullable String name, ExMF<Error> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckFlt attest(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckFlt attest(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckInt implements CheckIntTrait<CheckInt> {

		private final String type;
		private final int value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckInt(int value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final int get() {
			return value;
		}

		public @Nonnull CheckInt value(int value) {
			return new CheckInt(value, name, factory, type);
		}

	}

	public static CheckInt arg(int value) {
		return new CheckInt(value, "?", X::arg, "Argument");
	}

	public static CheckInt arg(int value, @Nullable String name) {
		return new CheckInt(value, name, X::arg, "Argument");
	}

	public static CheckInt value(int value) {
		return new CheckInt(value, "?", X::value, "Value");
	}

	public static CheckInt value(int value, @Nullable String name) {
		return new CheckInt(value, name, X::value, "Value");
	}

	public static CheckInt state(int value) {
		return new CheckInt(value, "?", X::state, "State");
	}

	public static CheckInt state(int value, @Nullable String name) {
		return new CheckInt(value, name, X::state, "State");
	}

	public static CheckInt check(int value, ExMF<RuntimeException> factory) {
		return new CheckInt(value, "?", factory, "Check");
	}

	public static CheckInt check(int value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckInt(value, name, factory, "Check");
	}

	public static CheckInt attest(int value, ExMF<Error> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckInt attest(int value, @Nullable String name, ExMF<Error> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckInt attest(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckInt attest(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::assertion), "Check");
	}

	@ThreadSafe
	public final static class CheckLong implements CheckLongTrait<CheckLong> {

		private final String type;
		private final long value;
		private final String name;
		private final ExMF<RuntimeException> factory;

		private CheckLong(long value, @Nullable String name, ExMF<RuntimeException> factory, String type) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
		}

		@Nonnull
		@Override
		public String checkTraitType() {
			return type;
		}

		@Nonnull
		@Override
		public String checkTraitName() {
			return name;
		}

		@Nonnull
		@Override
		public ExMF<RuntimeException> checkTraitFactory() {
			return factory;
		}

		/** Returns the arg/state/value. */
		public final long get() {
			return value;
		}

		public @Nonnull CheckLong value(long value) {
			return new CheckLong(value, name, factory, type);
		}

	}

	public static CheckLong arg(long value) {
		return new CheckLong(value, "?", X::arg, "Argument");
	}

	public static CheckLong arg(long value, @Nullable String name) {
		return new CheckLong(value, name, X::arg, "Argument");
	}

	public static CheckLong value(long value) {
		return new CheckLong(value, "?", X::value, "Value");
	}

	public static CheckLong value(long value, @Nullable String name) {
		return new CheckLong(value, name, X::value, "Value");
	}

	public static CheckLong state(long value) {
		return new CheckLong(value, "?", X::state, "State");
	}

	public static CheckLong state(long value, @Nullable String name) {
		return new CheckLong(value, name, X::state, "State");
	}

	public static CheckLong check(long value, ExMF<RuntimeException> factory) {
		return new CheckLong(value, "?", factory, "Check");
	}

	public static CheckLong check(long value, @Nullable String name, ExMF<RuntimeException> factory) {
		return new CheckLong(value, name, factory, "Check");
	}

	public static CheckLong attest(long value, ExMF<Error> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "Check");
	}

	public static CheckLong attest(long value, @Nullable String name, ExMF<Error> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "Check");
	}

	public static CheckLong attest(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::assertion), "Check");
	}

	public static CheckLong attest(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::assertion), "Check");
	}

}