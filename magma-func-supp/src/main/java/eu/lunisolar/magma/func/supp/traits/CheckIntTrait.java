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
import eu.lunisolar.magma.func.supp.*; // NOSONAR
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

public interface CheckIntTrait<SELF extends CheckIntTrait<SELF>> extends FailPoint<SELF>, aValue<aInt>, IntValueTrait<SELF>, aCheck<aInt> {

	int get();

	default int value() {
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

	//<editor-fold desc="main methods">

	/**   */
	default @Nonnull SELF must_(@Nonnull LIntPredicate predicate, @Nonnull LIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF must1(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(@Nonnull LIntPredicate predicate, @Nonnull LIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustEx(@Nonnull LIntFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF mustEx0(@Nonnull LIntFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF mustEx1(@Nonnull LIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx2(@Nonnull LIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx3(@Nonnull LIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
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
	default @Nonnull SELF must1(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF must1(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustEx(@Nonnull LBiIntFunction<? extends String> specialPredicate, int a2) {
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
	default @Nonnull SELF mustEx0(@Nonnull LBiIntFunction<? extends String> specialPredicate, int a2, @Nonnull String message) {
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
	default @Nonnull SELF mustEx1(@Nonnull LBiIntFunction<? extends String> specialPredicate, int a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx2(@Nonnull LBiIntFunction<? extends String> specialPredicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx3(@Nonnull LBiIntFunction<? extends String> specialPredicate, int a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustEx(int a2, @Nonnull LBiIntFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF mustEx0(int a2, @Nonnull LBiIntFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF mustEx1(int a2, @Nonnull LBiIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx2(int a2, @Nonnull LBiIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx3(int a2, @Nonnull LBiIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LTriIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
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
	default @Nonnull SELF must1(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must_(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LTriIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF must1(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LTriIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LTriIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustEx(@Nonnull LTriIntFunction<? extends String> specialPredicate, int a2, int a3) {
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
	default @Nonnull SELF mustEx0(@Nonnull LTriIntFunction<? extends String> specialPredicate, int a2, int a3, @Nonnull String message) {
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
	default @Nonnull SELF mustEx1(@Nonnull LTriIntFunction<? extends String> specialPredicate, int a2, int a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx2(@Nonnull LTriIntFunction<? extends String> specialPredicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx3(@Nonnull LTriIntFunction<? extends String> specialPredicate, int a2, int a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustEx(int a2, int a3, @Nonnull LTriIntFunction<? extends String> specialPredicate) {
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
	default @Nonnull SELF mustEx0(int a2, int a3, @Nonnull LTriIntFunction<? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF mustEx1(int a2, int a3, @Nonnull LTriIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx2(int a2, int a3, @Nonnull LTriIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx3(int a2, int a3, @Nonnull LTriIntFunction<? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWithInt_(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithInt1(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithInt2(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithInt3(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWithInt_(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithInt1(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithInt2(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithInt3(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustNotWithInt_(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustNotWithInt1(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustNotWithInt2(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustNotWithInt3(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustNotWithInt_(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustNotWithInt1(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustNotWithInt2(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustNotWithInt3(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWithIntEx(@Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, V1 with1) {
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
	default @Nonnull <V1> SELF mustWithIntEx0(@Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithIntEx1(@Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithIntEx2(@Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithIntEx3(@Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWithIntEx(V1 with1, @Nonnull LOiFunction<? super V1, ? extends String> specialPredicate) {
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
	default @Nonnull <V1> SELF mustWithIntEx0(V1 with1, @Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWithIntEx1(V1 with1, @Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWithIntEx2(V1 with1, @Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWithIntEx3(V1 with1, @Nonnull LOiFunction<? super V1, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith_(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith1(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith2(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith_(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith1(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith2(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustNotWith_(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustNotWith1(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustNotWith2(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustNotWith_(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustNotWith1(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustNotWith2(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWithEx(@Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2) {
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
	default @Nonnull <V1, V2> SELF mustWithEx0(@Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWithEx1(@Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWithEx2(@Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWithEx3(@Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWithEx(V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate) {
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
	default @Nonnull <V1, V2> SELF mustWithEx0(V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWithEx1(V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWithEx2(V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWithEx3(V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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

	default @Nonnull SELF checkWhen(@Nonnull LIntPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LIntPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiIntPredicate pred, int a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiIntPredicate pred, int a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		} ;
		return fluentCtx();
	}

	//</editor-fold>

	default @Nonnull SELF mustBeEqual(int expected) {
		return mustEx(Be::equalEx, expected);
	}

	default @Nonnull SELF mustBeEqual(int expected, String message) {
		return mustEx0(Be::equalEx, expected, message);
	}

	default @Nonnull SELF mustBeNotEqual(int expected) {
		return mustEx(Be::notEqualEx, expected);
	}

	default @Nonnull SELF mustBeNotEqual(int expected, String message) {
		return mustEx0(Be::notEqualEx, expected, message);
	}

	default @Nonnull SELF mustBeGreaterThan(int value) {
		return mustEx(Be::gtEx, value);
	}

	default @Nonnull SELF mustBeGreaterThan(int value, String message) {
		return mustEx0(Be::gtEx, value, message);
	}

	default @Nonnull SELF mustBeGreaterEqual(int value) {
		return mustEx(Be::gtEqEx, value);
	}

	default @Nonnull SELF mustBeGreaterEqual(int value, String message) {
		return mustEx0(Be::gtEqEx, value, message);
	}

	default @Nonnull SELF mustBeLessThan(int value) {
		return mustEx(Be::ltEx, value);
	}

	default @Nonnull SELF mustBeLessThan(int value, String message) {
		return mustEx0(Be::ltEx, value, message);
	}

	default @Nonnull SELF mustBeLessEqual(int value) {
		return mustEx(Be::ltEqEx, value);
	}

	default @Nonnull SELF mustBeLessEqual(int value, String message) {
		return mustEx0(Be::ltEqEx, value, message);
	}

	default @Nonnull SELF mustBeBetween(int min, int max) {
		return mustEx(Be::betweenEx, min, max);
	}

	default @Nonnull SELF mustBeBetween(int min, int max, String message) {
		return mustEx0(Be::betweenEx, min, max, message);
	}

	default @Nonnull SELF mustBeInRange(int min, int max) {
		return mustEx(Be::inRangeEx, min, max);
	}

	default @Nonnull SELF mustBeInRange(int min, int max, String message) {
		return mustEx0(Be::inRangeEx, min, max, message);
	}

	default @Nonnull SELF check(@Nonnull LConsumer<SELF> checks) {
		Null.nonNullArg(checks, "checks");
		checks.shovingAccept(fluentCtx());
		return fluentCtx();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		String failMessage = failMessage(newMessage);
		throw Handling.shoveIt(Handling.create(checkTraitFactory(), failMessage));
	}

	default @Nonnull String failMessage(@Nonnull String newMessage) {
		Null.nonNullArg(newMessage, "newMessage");
		var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
		params[params.length - 1] = newMessage;
		return format(verbosity().format1UM(), params);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		String failMessage = failMessage(newMessage, messageParams);
		throw Handling.shoveIt(Handling.create(checkTraitFactory(), failMessage));
	}

	default @Nonnull String failMessage(@Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(newMessage, "newMessage");
		var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
		params[params.length - 1] = format(newMessage, messageParams);
		String failMessage = format(verbosity().format1UM(), params);
		return failMessage;
	}

}
