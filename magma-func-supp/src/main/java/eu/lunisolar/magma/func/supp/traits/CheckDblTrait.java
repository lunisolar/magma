/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

public interface CheckDblTrait<SELF extends CheckDblTrait<SELF>> extends FluentTrait<SELF>, aValue<aDouble>, DblValueTrait<SELF>, aCheck<aDouble> {

	double get();

	default double value() {
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
	default @Nonnull SELF must_(@Nonnull LDblPredicate predicate, @Nonnull LDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must1(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot_(@Nonnull LDblPredicate predicate, @Nonnull LDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot1(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must$(@Nonnull LDblFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF must$0(@Nonnull LDblFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF must$1(@Nonnull LDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(@Nonnull LDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(@Nonnull LDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull LBiDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must1(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must_(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LBiDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must1(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must2(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must3(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot_(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull LBiDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot1(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot_(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LBiDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot1(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot2(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot3(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must$(@Nonnull LBiDblFunction<? extends String> specialPredicate, double a2) {
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
	default @Nonnull SELF must$0(@Nonnull LBiDblFunction<? extends String> specialPredicate, double a2, @Nonnull String message) {
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
	default @Nonnull SELF must$1(@Nonnull LBiDblFunction<? extends String> specialPredicate, double a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(@Nonnull LBiDblFunction<? extends String> specialPredicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(@Nonnull LBiDblFunction<? extends String> specialPredicate, double a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must$(double a2, @Nonnull LBiDblFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF must$0(double a2, @Nonnull LBiDblFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF must$1(double a2, @Nonnull LBiDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(double a2, @Nonnull LBiDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(double a2, @Nonnull LBiDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull LTriDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must1(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must2(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must3(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must_(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull LTriDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must1(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must2(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must3(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot_(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull LTriDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot1(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot2(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot3(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot_(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull LTriDblFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot1(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot2(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot3(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must$(@Nonnull LTriDblFunction<? extends String> specialPredicate, double a2, double a3) {
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
	default @Nonnull SELF must$0(@Nonnull LTriDblFunction<? extends String> specialPredicate, double a2, double a3, @Nonnull String message) {
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
	default @Nonnull SELF must$1(@Nonnull LTriDblFunction<? extends String> specialPredicate, double a2, double a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(@Nonnull LTriDblFunction<? extends String> specialPredicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(@Nonnull LTriDblFunction<? extends String> specialPredicate, double a2, double a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must$(double a2, double a3, @Nonnull LTriDblFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF must$0(double a2, double a3, @Nonnull LTriDblFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF must$1(double a2, double a3, @Nonnull LTriDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(double a2, double a3, @Nonnull LTriDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(double a2, double a3, @Nonnull LTriDblFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustInt(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt1(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt3(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt1(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt3(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt1(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt3(@Nonnull LDblIntPredicate predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt1(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt3(int v, @Nonnull LDblIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must__(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.testDblObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyDblObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_1(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_2(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_3(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must__(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.testDblObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyDblObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_1(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_2(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_3(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot__(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.testDblObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyDblObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_1(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_3(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot__(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.testDblObj(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.applyDblObj(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_1(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_2(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot_3(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.testDblObj(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$(@Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, V v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$0(@Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$1(@Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$2(@Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$3(@Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$0(V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$1(V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$2(V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_$3(V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.applyDblObj(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl_(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull LObjDblFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl1(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl2(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl3(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl_(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull LObjDblFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl1(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl2(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl3(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl_(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull LObjDblFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl1(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl2(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl3(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl_(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull LObjDblFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl1(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl2(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithDbl3(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithDbl$(@Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, V1 with1) {
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
	default @Nonnull <V1> SELF mustWithDbl$0(@Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithDbl$1(@Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithDbl$2(@Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithDbl$3(@Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWithDbl$(V1 with1, @Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate) {
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
	default @Nonnull <V1> SELF mustWithDbl$0(V1 with1, @Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithDbl$1(V1 with1, @Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithDbl$2(V1 with1, @Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithDbl$3(V1 with1, @Nonnull LObjDblFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith_(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith1(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith2(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith_(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith1(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith2(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith_(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith1(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith2(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith_(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith1(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith2(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2) {
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
	default @Nonnull <V1, V2> SELF mustWith$0(@Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith$1(@Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith$2(@Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith$3(@Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate) {
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
	default @Nonnull <V1, V2> SELF mustWith$0(V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith$1(V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith$2(V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith$3(V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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

	default @Nonnull SELF checkWhen(@Nonnull LDblPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LDblPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiDblPredicate pred, double a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiDblPredicate pred, double a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriDblPredicate pred, double a2, double a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	// </editor-fold>

	default @Nonnull SELF check(@Nonnull LConsumer<SELF> checks) {
		Null.nonNullArg(checks, "checks");
		checks.shovingAccept(fluentCtx());
		return fluentCtx();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		Null.nonNullArg(newMessage, "newMessage");
		return this.must(__ -> false, newMessage);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(newMessage, "newMessage");
		return this.must1(__ -> false, newMessage, messageParams);
	}

}
