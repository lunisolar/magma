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
import eu.lunisolar.magma.basics.fluent.Fluent; // NOSONAR   
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
	public final static class CheckBool implements Fluent<CheckBool> {

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

		public final CheckBool mustNot(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool mustNot$(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool mustNot(@Nonnull LLogicalOperator pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool must$(@Nonnull LLogicalOperator pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalOperator pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalOperator.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool mustNot(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool mustNot$(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool mustNot(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool must$(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalBinaryOperator.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool mustNot(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool mustNot$(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool mustNot(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckBool must$(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckBool must(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLogicalTernaryOperator.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckBool fails(@Nonnull String newMessage) {
			must(LLogicalOperator::alwaysFalse, newMessage);
			return self();
		}

		public final CheckBool fails$(@Nonnull String newMessage) {
			must$(LLogicalOperator::alwaysFalse, newMessage);
			return self();
		}

		public final CheckBool fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LLogicalOperator::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final boolean value() {
			return value;
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

	@ThreadSafe
	public final static class Check<T> implements Fluent<Check<T>> {

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

		public final Check<T> mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> mustNot$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> must$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> mustNot(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> mustNot$(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> mustNot(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> must(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> must$(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> must(@Nonnull LBiPredicate<T, T> pred, T a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> mustNot(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> mustNot$(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> mustNot(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> must(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final Check<T> must$(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final Check<T> must(@Nonnull LTriPredicate<T, T, T> pred, T a2, T a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final Check<T> fails(@Nonnull String newMessage) {
			must(LPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final Check<T> fails$(@Nonnull String newMessage) {
			must$(LPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final Check<T> fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final T value() {
			return value;
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

	@ThreadSafe
	public final static class CheckByte implements Fluent<CheckByte> {

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

		public final CheckByte mustNot(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte mustNot$(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte mustNot(@Nonnull LBytePredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte must(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte must$(@Nonnull LBytePredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte must(@Nonnull LBytePredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBytePredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte mustNot(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte mustNot$(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte mustNot(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte must(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte must$(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte must(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiBytePredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte mustNot(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte mustNot$(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte mustNot(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte must(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckByte must$(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckByte must(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriBytePredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckByte fails(@Nonnull String newMessage) {
			must(LBytePredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckByte fails$(@Nonnull String newMessage) {
			must$(LBytePredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckByte fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LBytePredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final byte value() {
			return value;
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

	@ThreadSafe
	public final static class CheckDbl implements Fluent<CheckDbl> {

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

		public final CheckDbl mustNot(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl mustNot$(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl mustNot(@Nonnull LDblPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl must(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl must$(@Nonnull LDblPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl must(@Nonnull LDblPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LDblPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl mustNot(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl mustNot$(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl mustNot(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl must(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl must$(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl must(@Nonnull LBiDblPredicate pred, double a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiDblPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl mustNot(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl mustNot$(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl mustNot(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl must(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckDbl must$(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckDbl must(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriDblPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckDbl fails(@Nonnull String newMessage) {
			must(LDblPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckDbl fails$(@Nonnull String newMessage) {
			must$(LDblPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckDbl fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LDblPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final double value() {
			return value;
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

	@ThreadSafe
	public final static class CheckChar implements Fluent<CheckChar> {

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

		public final CheckChar mustNot(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar mustNot$(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar mustNot(@Nonnull LCharPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar must(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar must$(@Nonnull LCharPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar must(@Nonnull LCharPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LCharPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar mustNot(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar mustNot$(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar mustNot(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar must(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar must$(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar must(@Nonnull LBiCharPredicate pred, char a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiCharPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar mustNot(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar mustNot$(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar mustNot(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar must(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckChar must$(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckChar must(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriCharPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckChar fails(@Nonnull String newMessage) {
			must(LCharPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckChar fails$(@Nonnull String newMessage) {
			must$(LCharPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckChar fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LCharPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final char value() {
			return value;
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

	@ThreadSafe
	public final static class CheckSrt implements Fluent<CheckSrt> {

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

		public final CheckSrt mustNot(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt mustNot$(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt mustNot(@Nonnull LSrtPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt must(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt must$(@Nonnull LSrtPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt must(@Nonnull LSrtPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LSrtPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt mustNot(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt mustNot$(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt mustNot(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt must(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt must$(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt must(@Nonnull LBiSrtPredicate pred, short a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiSrtPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt mustNot(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt mustNot$(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt mustNot(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt must(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckSrt must$(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckSrt must(@Nonnull LTriSrtPredicate pred, short a2, short a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriSrtPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckSrt fails(@Nonnull String newMessage) {
			must(LSrtPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckSrt fails$(@Nonnull String newMessage) {
			must$(LSrtPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckSrt fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LSrtPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final short value() {
			return value;
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

	@ThreadSafe
	public final static class CheckFlt implements Fluent<CheckFlt> {

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

		public final CheckFlt mustNot(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt mustNot$(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt mustNot(@Nonnull LFltPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt must(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt must$(@Nonnull LFltPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt must(@Nonnull LFltPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LFltPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt mustNot(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt mustNot$(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt mustNot(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt must(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt must$(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt must(@Nonnull LBiFltPredicate pred, float a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiFltPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt mustNot(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt mustNot$(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt mustNot(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt must(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckFlt must$(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckFlt must(@Nonnull LTriFltPredicate pred, float a2, float a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriFltPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckFlt fails(@Nonnull String newMessage) {
			must(LFltPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckFlt fails$(@Nonnull String newMessage) {
			must$(LFltPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckFlt fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LFltPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final float value() {
			return value;
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

	@ThreadSafe
	public final static class CheckInt implements Fluent<CheckInt> {

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

		public final CheckInt mustNot(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt mustNot$(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt mustNot(@Nonnull LIntPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt must(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt must$(@Nonnull LIntPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt must(@Nonnull LIntPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LIntPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt mustNot(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt mustNot$(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt mustNot(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt must(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt must$(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt must(@Nonnull LBiIntPredicate pred, int a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiIntPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt mustNot(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt mustNot$(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt mustNot(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt must(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckInt must$(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckInt must(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriIntPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckInt fails(@Nonnull String newMessage) {
			must(LIntPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckInt fails$(@Nonnull String newMessage) {
			must$(LIntPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckInt fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LIntPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final int value() {
			return value;
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

	@ThreadSafe
	public final static class CheckLong implements Fluent<CheckLong> {

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

		public final CheckLong mustNot(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong mustNot$(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIf(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong mustNot(@Nonnull LLongPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIf(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong must(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong must$(@Nonnull LLongPredicate pred, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIfNot(value, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong must(@Nonnull LLongPredicate pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LLongPredicate.throwIfNot(value, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong mustNot(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong mustNot$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIf(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong mustNot(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIf(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong must(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong must$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIfNot(value, a2, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong must(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LBiLongPredicate.throwIfNot(value, a2, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong mustNot(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong mustNot$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIf(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong mustNot(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIf(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong must(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S, type, name, newMessage);
			return self();
		}

		public final CheckLong must$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIfNot(value, a2, a3, pred, factory, MESSAGE_S_S_S_S, type, name, newMessage, value);
			return self();
		}

		public final CheckLong must(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
			Null.nonNullArg(pred, "pred");
			LTriLongPredicate.throwIfNot(value, a2, a3, pred, factory, newMessage, messageParams);
			return self();
		}

		public final CheckLong fails(@Nonnull String newMessage) {
			must(LLongPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckLong fails$(@Nonnull String newMessage) {
			must$(LLongPredicate::alwaysFalse, newMessage);
			return self();
		}

		public final CheckLong fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
			must(LLongPredicate::alwaysFalse, newMessage, messageParams);
			return self();
		}

		/** Returns the arg/state/value. */
		public final long value() {
			return value;
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

}