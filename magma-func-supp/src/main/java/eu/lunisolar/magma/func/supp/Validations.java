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

package eu.lunisolar.magma.func.supp;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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
public final class Validations implements FluentSyntax {

	private static final String MESSAGE_S_S_S = "%s [%s]: %s.";
	private static final String MESSAGE_S_S_S_S = "%s [%s]: %s. Value: %s";

	// <editor-fold desc="no instance">
	private Validations() {
	}
	// </editor-fold>

	@ThreadSafe
	public final static class CheckBool implements FluentSyntax {

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

		public final boolean mustNot(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final boolean mustNot$(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final boolean mustNot(@Nonnull LLogicalBinaryOperator pred, boolean t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalBinaryOperator.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final boolean mustNot$(@Nonnull LLogicalBinaryOperator pred, boolean t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalBinaryOperator.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final boolean mustNot(@Nonnull LLogicalOperator pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final boolean must(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final boolean fails(@Nonnull String newMessage) {
			return must(LLogicalOperator::alwaysFalse, newMessage);
		}

		public final boolean must$(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final boolean fails$(@Nonnull String newMessage) {
			return must$(LLogicalOperator::alwaysFalse, newMessage);
		}

		public final boolean must(@Nonnull LLogicalBinaryOperator pred, boolean t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalBinaryOperator.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final boolean must$(@Nonnull LLogicalBinaryOperator pred, boolean t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLogicalBinaryOperator.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final boolean must(@Nonnull LLogicalOperator pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LLogicalOperator.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final boolean fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LLogicalOperator::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckBool arg(boolean value) {
		return new CheckBool(value, "?", X::arg, "Argument");
	}

	public static CheckBool arg(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::arg, "Argument");
	}

	public static CheckBool value(boolean value) {
		return new CheckBool(value, "?", X::arg, "Value");
	}

	public static CheckBool value(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::arg, "Value");
	}

	public static CheckBool state(boolean value) {
		return new CheckBool(value, "?", X::state, "State");
	}

	public static CheckBool state(boolean value, @Nullable String name) {
		return new CheckBool(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class Check<T> implements FluentSyntax {

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

		public final T mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final T mustNot$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final T mustNot(@Nonnull LBiPredicate<T, T> pred, T t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final T mustNot$(@Nonnull LBiPredicate<T, T> pred, T t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final T mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final T must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final T fails(@Nonnull String newMessage) {
			return must(LPredicate::alwaysFalse, newMessage);
		}

		public final T must$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final T fails$(@Nonnull String newMessage) {
			return must$(LPredicate::alwaysFalse, newMessage);
		}

		public final T must(@Nonnull LBiPredicate<T, T> pred, T t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final T must$(@Nonnull LBiPredicate<T, T> pred, T t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final T must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final T fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static <T> Check<T> arg(@Nullable T value) {
		return new Check<T>(value, "?", X::arg, "Argument");
	}

	public static <T> Check<T> arg(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::arg, "Argument");
	}

	public static <T> Check<T> value(@Nullable T value) {
		return new Check<T>(value, "?", X::arg, "Value");
	}

	public static <T> Check<T> value(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::arg, "Value");
	}

	public static <T> Check<T> state(@Nullable T value) {
		return new Check<T>(value, "?", X::state, "State");
	}

	public static <T> Check<T> state(@Nullable T value, @Nullable String name) {
		return new Check<T>(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckByte implements FluentSyntax {

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

		public final byte mustNot(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final byte mustNot$(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final byte mustNot(@Nonnull LBiBytePredicate pred, byte t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiBytePredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final byte mustNot$(@Nonnull LBiBytePredicate pred, byte t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiBytePredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final byte mustNot(@Nonnull LBytePredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final byte must(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final byte fails(@Nonnull String newMessage) {
			return must(LBytePredicate::alwaysFalse, newMessage);
		}

		public final byte must$(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final byte fails$(@Nonnull String newMessage) {
			return must$(LBytePredicate::alwaysFalse, newMessage);
		}

		public final byte must(@Nonnull LBiBytePredicate pred, byte t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiBytePredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final byte must$(@Nonnull LBiBytePredicate pred, byte t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiBytePredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final byte must(@Nonnull LBytePredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LBytePredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final byte fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LBytePredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckByte arg(byte value) {
		return new CheckByte(value, "?", X::arg, "Argument");
	}

	public static CheckByte arg(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::arg, "Argument");
	}

	public static CheckByte value(byte value) {
		return new CheckByte(value, "?", X::arg, "Value");
	}

	public static CheckByte value(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::arg, "Value");
	}

	public static CheckByte state(byte value) {
		return new CheckByte(value, "?", X::state, "State");
	}

	public static CheckByte state(byte value, @Nullable String name) {
		return new CheckByte(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckDbl implements FluentSyntax {

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

		public final double mustNot(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final double mustNot$(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final double mustNot(@Nonnull LBiDblPredicate pred, double t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiDblPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final double mustNot$(@Nonnull LBiDblPredicate pred, double t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiDblPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final double mustNot(@Nonnull LDblPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final double must(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final double fails(@Nonnull String newMessage) {
			return must(LDblPredicate::alwaysFalse, newMessage);
		}

		public final double must$(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final double fails$(@Nonnull String newMessage) {
			return must$(LDblPredicate::alwaysFalse, newMessage);
		}

		public final double must(@Nonnull LBiDblPredicate pred, double t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiDblPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final double must$(@Nonnull LBiDblPredicate pred, double t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiDblPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final double must(@Nonnull LDblPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LDblPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final double fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LDblPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckDbl arg(double value) {
		return new CheckDbl(value, "?", X::arg, "Argument");
	}

	public static CheckDbl arg(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::arg, "Argument");
	}

	public static CheckDbl value(double value) {
		return new CheckDbl(value, "?", X::arg, "Value");
	}

	public static CheckDbl value(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::arg, "Value");
	}

	public static CheckDbl state(double value) {
		return new CheckDbl(value, "?", X::state, "State");
	}

	public static CheckDbl state(double value, @Nullable String name) {
		return new CheckDbl(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckChar implements FluentSyntax {

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

		public final char mustNot(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final char mustNot$(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final char mustNot(@Nonnull LBiCharPredicate pred, char t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiCharPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final char mustNot$(@Nonnull LBiCharPredicate pred, char t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiCharPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final char mustNot(@Nonnull LCharPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final char must(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final char fails(@Nonnull String newMessage) {
			return must(LCharPredicate::alwaysFalse, newMessage);
		}

		public final char must$(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final char fails$(@Nonnull String newMessage) {
			return must$(LCharPredicate::alwaysFalse, newMessage);
		}

		public final char must(@Nonnull LBiCharPredicate pred, char t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiCharPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final char must$(@Nonnull LBiCharPredicate pred, char t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiCharPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final char must(@Nonnull LCharPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LCharPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final char fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LCharPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckChar arg(char value) {
		return new CheckChar(value, "?", X::arg, "Argument");
	}

	public static CheckChar arg(char value, @Nullable String name) {
		return new CheckChar(value, name, X::arg, "Argument");
	}

	public static CheckChar value(char value) {
		return new CheckChar(value, "?", X::arg, "Value");
	}

	public static CheckChar value(char value, @Nullable String name) {
		return new CheckChar(value, name, X::arg, "Value");
	}

	public static CheckChar state(char value) {
		return new CheckChar(value, "?", X::state, "State");
	}

	public static CheckChar state(char value, @Nullable String name) {
		return new CheckChar(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckSrt implements FluentSyntax {

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

		public final short mustNot(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final short mustNot$(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final short mustNot(@Nonnull LBiSrtPredicate pred, short t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiSrtPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final short mustNot$(@Nonnull LBiSrtPredicate pred, short t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiSrtPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final short mustNot(@Nonnull LSrtPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final short must(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final short fails(@Nonnull String newMessage) {
			return must(LSrtPredicate::alwaysFalse, newMessage);
		}

		public final short must$(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final short fails$(@Nonnull String newMessage) {
			return must$(LSrtPredicate::alwaysFalse, newMessage);
		}

		public final short must(@Nonnull LBiSrtPredicate pred, short t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiSrtPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final short must$(@Nonnull LBiSrtPredicate pred, short t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiSrtPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final short must(@Nonnull LSrtPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LSrtPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final short fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LSrtPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckSrt arg(short value) {
		return new CheckSrt(value, "?", X::arg, "Argument");
	}

	public static CheckSrt arg(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::arg, "Argument");
	}

	public static CheckSrt value(short value) {
		return new CheckSrt(value, "?", X::arg, "Value");
	}

	public static CheckSrt value(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::arg, "Value");
	}

	public static CheckSrt state(short value) {
		return new CheckSrt(value, "?", X::state, "State");
	}

	public static CheckSrt state(short value, @Nullable String name) {
		return new CheckSrt(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckFlt implements FluentSyntax {

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

		public final float mustNot(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final float mustNot$(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final float mustNot(@Nonnull LBiFltPredicate pred, float t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiFltPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final float mustNot$(@Nonnull LBiFltPredicate pred, float t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiFltPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final float mustNot(@Nonnull LFltPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final float must(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final float fails(@Nonnull String newMessage) {
			return must(LFltPredicate::alwaysFalse, newMessage);
		}

		public final float must$(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final float fails$(@Nonnull String newMessage) {
			return must$(LFltPredicate::alwaysFalse, newMessage);
		}

		public final float must(@Nonnull LBiFltPredicate pred, float t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiFltPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final float must$(@Nonnull LBiFltPredicate pred, float t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiFltPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final float must(@Nonnull LFltPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LFltPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final float fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LFltPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckFlt arg(float value) {
		return new CheckFlt(value, "?", X::arg, "Argument");
	}

	public static CheckFlt arg(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::arg, "Argument");
	}

	public static CheckFlt value(float value) {
		return new CheckFlt(value, "?", X::arg, "Value");
	}

	public static CheckFlt value(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::arg, "Value");
	}

	public static CheckFlt state(float value) {
		return new CheckFlt(value, "?", X::state, "State");
	}

	public static CheckFlt state(float value, @Nullable String name) {
		return new CheckFlt(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckInt implements FluentSyntax {

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

		public final int mustNot(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final int mustNot$(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final int mustNot(@Nonnull LBiIntPredicate pred, int t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiIntPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final int mustNot$(@Nonnull LBiIntPredicate pred, int t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiIntPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final int mustNot(@Nonnull LIntPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final int must(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final int fails(@Nonnull String newMessage) {
			return must(LIntPredicate::alwaysFalse, newMessage);
		}

		public final int must$(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final int fails$(@Nonnull String newMessage) {
			return must$(LIntPredicate::alwaysFalse, newMessage);
		}

		public final int must(@Nonnull LBiIntPredicate pred, int t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiIntPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final int must$(@Nonnull LBiIntPredicate pred, int t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiIntPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final int must(@Nonnull LIntPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LIntPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final int fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LIntPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckInt arg(int value) {
		return new CheckInt(value, "?", X::arg, "Argument");
	}

	public static CheckInt arg(int value, @Nullable String name) {
		return new CheckInt(value, name, X::arg, "Argument");
	}

	public static CheckInt value(int value) {
		return new CheckInt(value, "?", X::arg, "Value");
	}

	public static CheckInt value(int value, @Nullable String name) {
		return new CheckInt(value, name, X::arg, "Value");
	}

	public static CheckInt state(int value) {
		return new CheckInt(value, "?", X::state, "State");
	}

	public static CheckInt state(int value, @Nullable String name) {
		return new CheckInt(value, name, X::state, "State");
	}

	@ThreadSafe
	public final static class CheckLong implements FluentSyntax {

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

		public final long mustNot(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final long mustNot$(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final long mustNot(@Nonnull LBiLongPredicate pred, long t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiLongPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final long mustNot$(@Nonnull LBiLongPredicate pred, long t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiLongPredicate.throwIf(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final long mustNot(@Nonnull LLongPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIf(value, pred, factory, newMessage, messageParams);
		}

		public final long must(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final long fails(@Nonnull String newMessage) {
			return must(LLongPredicate::alwaysFalse, newMessage);
		}

		public final long must$(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final long fails$(@Nonnull String newMessage) {
			return must$(LLongPredicate::alwaysFalse, newMessage);
		}

		public final long must(@Nonnull LBiLongPredicate pred, long t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiLongPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S, type, name, newMessage);
		}

		public final long must$(@Nonnull LBiLongPredicate pred, long t2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			return LBiLongPredicate.throwIfNot(value, pred, t2, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
		}

		public final long must(@Nonnull LLongPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			return LLongPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
		}

		public final long fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			return must(LLongPredicate::alwaysFalse, newMessage, messageParams);
		}

	}

	public static CheckLong arg(long value) {
		return new CheckLong(value, "?", X::arg, "Argument");
	}

	public static CheckLong arg(long value, @Nullable String name) {
		return new CheckLong(value, name, X::arg, "Argument");
	}

	public static CheckLong value(long value) {
		return new CheckLong(value, "?", X::arg, "Value");
	}

	public static CheckLong value(long value, @Nullable String name) {
		return new CheckLong(value, name, X::arg, "Value");
	}

	public static CheckLong state(long value) {
		return new CheckLong(value, "?", X::state, "State");
	}

	public static CheckLong state(long value, @Nullable String name) {
		return new CheckLong(value, name, X::state, "State");
	}

}