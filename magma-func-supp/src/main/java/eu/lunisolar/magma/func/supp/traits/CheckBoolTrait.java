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

package eu.lunisolar.magma.func.supp.traits;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.Fluent; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.Be; // NOSONAR
import eu.lunisolar.magma.func.supp.MsgVerbosity; // NOSONAR   
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

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

import eu.lunisolar.magma.func.supp.value.*;

import static eu.lunisolar.magma.func.supp.traits.CheckTrait.*;
import static java.lang.String.*; // NOSONAR
import static eu.lunisolar.magma.func.supp.MsgVerbosity.*; // NOSONAR

public interface CheckBoolTrait<SELF extends CheckBoolTrait<SELF>> extends FluentTrait<SELF>, aValue<aBool>, LBoolSingle, BoolValueTrait<SELF> {

	boolean get();

	default boolean value() {
		return get();
	}

	@Nonnull
	default String checkTraitType() {
		return "Value";
	}

	@Nonnull
	default String checkTraitName() {
		return "?";
	}

	@Nonnull
	default MsgVerbosity verbosity() {
		// Check classes will make this a writable property. Classes not focused on checks/assertions will probably stay on MIN without ability to change.
		return MIN;
	}

	@Nonnull
	default ExMF<RuntimeException> checkTraitFactory() {
		return X::value;
	}

	// <editor-fold desc="main methods">

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull LBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull LBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBoolFunction<? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must2Ex(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must3Ex(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must_2Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool2Ex(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		throw Handling.create(checkTraitFactory(), newMessage);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		throw Handling.create(checkTraitFactory(), newMessage, messageParams);
	}

}
