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

public interface CheckBoolTrait<SELF extends CheckBoolTrait<SELF>> extends FluentTrait<SELF>, aValue<aBool>, BoolValueTrait<SELF> {

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
	default @Nonnull SELF mustF(@Nonnull LLogicalOperator operator, @Nonnull LBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
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
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM1(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM2(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM3(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotF(@Nonnull LLogicalOperator operator, @Nonnull LBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
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
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM1(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM2(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM3(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
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
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM1(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM2(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM3(@Nonnull LBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get());
		if (msg != null) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustF(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM1(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM3(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustF(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM1(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM3(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotF(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM1(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM2(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM3(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotF(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBiBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM1(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM2(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM3(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM1(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM2(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM3(@Nonnull LBiBoolFunction<? extends String> specialPredicate, boolean a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustEx(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM1(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM2(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM3(boolean a2, @Nonnull LBiBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2);
		if (msg != null) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustF(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM1(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM2(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustF(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM1(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM2(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustM3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotF(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM1(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM2(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM3(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotF(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LTriBoolFunction<String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.apply(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM1(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM2(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotM3(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.apply(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM1(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM2(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM3(@Nonnull LTriBoolFunction<? extends String> specialPredicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustEx(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM1(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM2(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustExM3(boolean a2, boolean a3, @Nonnull LTriBoolFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM1(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM3(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM1(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustIntM3(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM1(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM2(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM3(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM1(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM2(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotIntM3(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_F(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M1(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M3(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_F(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M1(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_M3(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_F(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M1(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M2(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M3(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_F(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.testBoolObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyBoolObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M1(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M2(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_M3(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.testBoolObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_Ex(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM1(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM2(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM3(@Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_Ex(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM1(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM2(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_ExM3(V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyBoolObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolF(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM1(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM3(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolF(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM1(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolM3(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolF(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM1(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM2(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM3(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolF(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LObjBoolFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM1(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM2(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithBoolM3(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolEx(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM1(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM2(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM3(@Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolEx(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM1(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM2(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithBoolExM3(V1 with1, @Nonnull LObjBoolFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithF(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM1(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM2(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithF(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM1(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM2(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithM3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (!operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithF(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM1(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM2(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM3(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithF(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (operator.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM1(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM2(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWithM3(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(operator, "operator");
		Null.nonNullArg(message, "message");
		if (operator.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithEx(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM1(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM2(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM3(@Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithEx(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM1(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM2(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWithExM3(V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with1, with2, get());
		if (msg != null) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3MUM(), params)));
		}
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		return this.must(__ -> false, newMessage);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		return this.mustM1(__ -> false, newMessage, messageParams);
	}

}
