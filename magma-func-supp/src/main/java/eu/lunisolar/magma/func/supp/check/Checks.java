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
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

import static eu.lunisolar.magma.func.supp.MsgVerbosity.*; // NOSONAR

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
		private final MsgVerbosity verbosity;

		public CheckBool(boolean value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final boolean get() {
			return value;
		}

		public @Nonnull CheckBool value(boolean value) {
			return new CheckBool(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckBool verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckBool(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckBool verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckBool arg(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckBool arg(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckBool arg(boolean value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckBool arg(boolean value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckBool value(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckBool value(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckBool value(boolean value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckBool value(boolean value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckBool state(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckBool state(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckBool state(boolean value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckBool state(boolean value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckBool check(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckBool check(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckBool check(boolean value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckBool check(boolean value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckBool attest(boolean value) {
		return new CheckBool(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckBool attest(boolean value, @Nullable String name) {
		return new CheckBool(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckBool attest(boolean value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckBool(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckBool attest(boolean value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckBool(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class Check<T> implements CheckTrait<T, Check<T>> {

		private final String type;
		private final @Nullable T value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public Check(@Nullable T value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final T get() {
			return value;
		}

		public @Nonnull Check<T> value(@Nullable T value) {
			return new Check(value, name, factory, type, verbosity);
		}

		public @Nonnull Check<T> verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new Check(value, name, factory, type, verbosity);
		}

		public @Nonnull Check<T> verbose() {
			return verbosity(ALL);
		}

		/**
		 * Must-be-instance-of - must be present and be instance of.
		 * @see {@link #mustBeInstanceOf(Class)}, {@link #mustBeInstanceOf(Class, String)} , {@link #shouldBeInstanceOf(Class)}, {@link #shouldBeInstanceOf(Class, String)}, {@link #filterAndMap}
		 */
		public @Nonnull <R> Check<R> mustBeInstanceOf(@Nonnull Class<R> clazz, @Nonnull String message) {
			Null.nonNullArg(clazz, "clazz");
			return (Check) must(Be::instanceOf, clazz, message);
		}

		/**
		 * Must-be-instance-of - must be present and be instance of.
		 * @see {@link #mustBeInstanceOf(Class)}, {@link #mustBeInstanceOf(Class, String)} , {@link #shouldBeInstanceOf(Class)}, {@link #shouldBeInstanceOf(Class, String)}, {@link #filterAndMap}
		 */
		public @Nonnull <R> Check<R> mustBeInstanceOf(@Nonnull Class<R> clazz) {
			Null.nonNullArg(clazz, "clazz");
			T obj = get();
			if (!clazz.isInstance(obj)) {
				fails("Value <%s> of actual class <%s> must be instance of class <%s> but is not.", obj, obj.getClass(), clazz);
			}
			return (Check) this;
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static <T> Check<T> arg(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static <T> Check<T> arg(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static <T> Check<T> arg(@Nullable T value, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static <T> Check<T> arg(@Nullable T value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static <T> Check<T> value(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static <T> Check<T> value(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static <T> Check<T> value(@Nullable T value, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static <T> Check<T> value(@Nullable T value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static <T> Check<T> state(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static <T> Check<T> state(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static <T> Check<T> state(@Nullable T value, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static <T> Check<T> state(@Nullable T value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static <T> Check<T> check(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static <T> Check<T> check(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static <T> Check<T> check(@Nullable T value, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static <T> Check<T> check(@Nullable T value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static <T> Check<T> attest(@Nullable T value) {
		return new Check<T>(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static <T> Check<T> attest(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static <T> Check<T> attest(@Nullable T value, @Nonnull ExMF<? extends Error> factory) {
		return new Check<T>(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static <T> Check<T> attest(@Nullable T value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new Check<T>(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckByte implements CheckByteTrait<CheckByte> {

		private final String type;
		private final byte value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckByte(byte value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final byte get() {
			return value;
		}

		public @Nonnull CheckByte value(byte value) {
			return new CheckByte(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckByte verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckByte(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckByte verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckByte arg(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckByte arg(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckByte arg(byte value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckByte arg(byte value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckByte value(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckByte value(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckByte value(byte value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckByte value(byte value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckByte state(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckByte state(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckByte state(byte value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckByte state(byte value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckByte check(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckByte check(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckByte check(byte value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckByte check(byte value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckByte attest(byte value) {
		return new CheckByte(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckByte attest(byte value, @Nullable String name) {
		return new CheckByte(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckByte attest(byte value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckByte(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckByte attest(byte value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckByte(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckDbl implements CheckDblTrait<CheckDbl> {

		private final String type;
		private final double value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckDbl(double value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final double get() {
			return value;
		}

		public @Nonnull CheckDbl value(double value) {
			return new CheckDbl(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckDbl verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckDbl(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckDbl verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckDbl arg(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckDbl arg(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckDbl arg(double value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckDbl arg(double value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckDbl value(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckDbl value(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckDbl value(double value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckDbl value(double value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckDbl state(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckDbl state(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckDbl state(double value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckDbl state(double value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckDbl check(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckDbl check(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckDbl check(double value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckDbl check(double value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckDbl attest(double value) {
		return new CheckDbl(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckDbl attest(double value, @Nullable String name) {
		return new CheckDbl(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckDbl attest(double value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckDbl(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckDbl attest(double value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckDbl(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckChar implements CheckCharTrait<CheckChar> {

		private final String type;
		private final char value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckChar(char value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final char get() {
			return value;
		}

		public @Nonnull CheckChar value(char value) {
			return new CheckChar(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckChar verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckChar(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckChar verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckChar arg(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckChar arg(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckChar arg(char value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckChar arg(char value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckChar value(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckChar value(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckChar value(char value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckChar value(char value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckChar state(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckChar state(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckChar state(char value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckChar state(char value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckChar check(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckChar check(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckChar check(char value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckChar check(char value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckChar attest(char value) {
		return new CheckChar(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckChar attest(char value, @Nullable String name) {
		return new CheckChar(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckChar attest(char value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckChar(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckChar attest(char value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckChar(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckSrt implements CheckSrtTrait<CheckSrt> {

		private final String type;
		private final short value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckSrt(short value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final short get() {
			return value;
		}

		public @Nonnull CheckSrt value(short value) {
			return new CheckSrt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckSrt verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckSrt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckSrt verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckSrt arg(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckSrt arg(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckSrt arg(short value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckSrt arg(short value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckSrt value(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckSrt value(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckSrt value(short value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckSrt value(short value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckSrt state(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckSrt state(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckSrt state(short value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckSrt state(short value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckSrt check(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckSrt check(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckSrt check(short value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckSrt check(short value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckSrt attest(short value) {
		return new CheckSrt(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckSrt attest(short value, @Nullable String name) {
		return new CheckSrt(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckSrt attest(short value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckSrt(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckSrt attest(short value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckSrt(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckFlt implements CheckFltTrait<CheckFlt> {

		private final String type;
		private final float value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckFlt(float value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final float get() {
			return value;
		}

		public @Nonnull CheckFlt value(float value) {
			return new CheckFlt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckFlt verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckFlt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckFlt verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckFlt arg(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckFlt arg(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckFlt arg(float value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckFlt arg(float value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckFlt value(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckFlt value(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckFlt value(float value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckFlt value(float value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckFlt state(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckFlt state(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckFlt state(float value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckFlt state(float value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckFlt check(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckFlt check(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckFlt check(float value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckFlt check(float value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckFlt attest(float value) {
		return new CheckFlt(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckFlt attest(float value, @Nullable String name) {
		return new CheckFlt(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckFlt attest(float value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckFlt(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckFlt attest(float value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckFlt(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckInt implements CheckIntTrait<CheckInt> {

		private final String type;
		private final int value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckInt(int value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final int get() {
			return value;
		}

		public @Nonnull CheckInt value(int value) {
			return new CheckInt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckInt verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckInt(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckInt verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckInt arg(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckInt arg(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckInt arg(int value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckInt arg(int value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckInt value(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckInt value(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckInt value(int value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckInt value(int value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckInt state(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckInt state(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckInt state(int value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckInt state(int value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckInt check(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckInt check(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckInt check(int value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckInt check(int value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckInt attest(int value) {
		return new CheckInt(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckInt attest(int value, @Nullable String name) {
		return new CheckInt(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckInt attest(int value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckInt(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckInt attest(int value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckInt(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	@ThreadSafe
	public final static class CheckLong implements CheckLongTrait<CheckLong> {

		private final String type;
		private final long value;
		private final String name;
		private final ExMF<RuntimeException> factory;
		private final MsgVerbosity verbosity;

		public CheckLong(long value, @Nullable String name, ExMF<RuntimeException> factory, String type, MsgVerbosity verbosity) {
			this.name = name;
			this.value = value;
			this.factory = factory;
			this.type = type;
			this.verbosity = verbosity;
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

		@Nonnull
		@Override
		public MsgVerbosity verbosity() {
			return verbosity;
		}

		/** Returns the arg/state/value. */
		public final long get() {
			return value;
		}

		public @Nonnull CheckLong value(long value) {
			return new CheckLong(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckLong verbosity(@Nonnull MsgVerbosity verbosity) {
			Null.nonNullArg(verbosity, "verbosity");
			return new CheckLong(value, name, factory, type, verbosity);
		}

		public @Nonnull CheckLong verbose() {
			return verbosity(ALL);
		}

		public String toString() {
			var v = value();
			var sb = new StringBuilder().append(checkTraitType()).append(" [").append(checkTraitName()).append("==");
			ToStr.toSb(sb, v);
			return sb.append("]").toString();
		}

	}

	public static CheckLong arg(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckLong arg(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::arg), "Argument", MIN);
	}

	public static CheckLong arg(long value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckLong arg(long value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "Argument", MIN);
	}

	public static CheckLong value(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckLong value(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::value), "Value", MIN);
	}

	public static CheckLong value(long value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckLong value(long value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "Value", MIN);
	}

	public static CheckLong state(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckLong state(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::state), "State", MIN);
	}

	public static CheckLong state(long value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "State", MIN);
	}

	public static CheckLong state(long value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "State", MIN);
	}

	public static CheckLong check(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckLong check(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::state), "Check", MIN);
	}

	public static CheckLong check(long value, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckLong check(long value, @Nullable String name, @Nonnull ExMF<? extends Throwable> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "Check", MIN);
	}

	public static CheckLong attest(long value) {
		return new CheckLong(value, "?", ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckLong attest(long value, @Nullable String name) {
		return new CheckLong(value, name, ExMF.shoving(X::assertion), "Check/attest", ALL);
	}

	public static CheckLong attest(long value, @Nonnull ExMF<? extends Error> factory) {
		return new CheckLong(value, "?", ExMF.shoving(factory), "Check/attest", ALL);
	}

	public static CheckLong attest(long value, @Nullable String name, @Nonnull ExMF<? extends Error> factory) {
		return new CheckLong(value, name, ExMF.shoving(factory), "Check/attest", ALL);
	}

	/** Due to the nature of the check, this method is not recommended for production runtime. */
	public static Check<Throwable> attestThrownBy(@Nonnull LAction action) {
		Null.nonNullArg(action, "action");
		try {
			action.executeX();
		} catch (Throwable e) {
			return Checks.attest(e, "exception");
		}

		return Checks.attest(null, "exception");
	}

}