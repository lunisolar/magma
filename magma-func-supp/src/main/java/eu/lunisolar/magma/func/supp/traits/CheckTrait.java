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

import static java.lang.String.*; // NOSONAR
import static eu.lunisolar.magma.func.supp.MsgVerbosity.*; // NOSONAR

public interface CheckTrait<T, SELF extends CheckTrait<T, SELF>> extends FluentTrait<SELF>, aValue<a<T>>, ValueTrait<T, SELF> {

	@Nullable
	T get();

	default @Nullable T value() {
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
	default @Nonnull SELF must_(@Nonnull LPredicate<? super T> predicate, @Nonnull LFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF must1(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must2(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must3(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNot_(@Nonnull LPredicate<? super T> predicate, @Nonnull LFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNot1(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNot2(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNot3(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF must$(@Nonnull LFunction<? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF must$0(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF must$1(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF must$2(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF must$3(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust_(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
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
	default @Nonnull SELF uniMust1(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust_(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust1(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMustNot_(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
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
	default @Nonnull SELF uniMustNot1(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMustNot2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMustNot3(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMustNot_(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMustNot1(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMustNot2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMustNot3(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust$(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2) {
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
	default @Nonnull SELF uniMust$0(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message) {
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
	default @Nonnull SELF uniMust$1(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust$2(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust$3(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust$(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF uniMust$0(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust$1(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust$2(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust$3(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust_(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
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
	default @Nonnull SELF uniMust1(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust_(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust1(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMustNot_(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
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
	default @Nonnull SELF uniMustNot1(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMustNot2(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMustNot3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMustNot_(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMustNot1(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMustNot2(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMustNot3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust$(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3) {
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
	default @Nonnull SELF uniMust$0(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message) {
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
	default @Nonnull SELF uniMust$1(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust$2(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust$3(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust$(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF uniMust$0(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust$1(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust$2(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust$3(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust_(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust1(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust2(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust3(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust_(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust1(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust2(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust3(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot_(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot1(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot_(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot1(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$0(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$1(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$2(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$3(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$0(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$1(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$2(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMust$3(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustA_(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull LBiFunction<T, V[], String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustA(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustA1(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustA2(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustA3(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustA_(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull LBiFunction<T, V[], String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustA1(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustA2(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustA3(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustNotA_(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull LBiFunction<T, V[], String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNotA(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustNotA1(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustNotA2(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustNotA3(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustNotA_(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull LBiFunction<T, V[], String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNotA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustNotA1(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustNotA2(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustNotA3(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustA$(@Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, V... a2) {
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
	default @Nonnull <V> SELF mustA$0(@Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, V[] a2, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustA$1(@Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, V[] a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustA$2(@Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustA$3(@Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, V[] a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustA$(V[] a2, @Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate) {
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
	default @Nonnull <V> SELF mustA$0(V[] a2, @Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustA$1(V[] a2, @Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustA$2(V[] a2, @Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustA$3(V[] a2, @Nonnull LBiFunction<? super T, V[], ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustBool_(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
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
	default @Nonnull SELF mustBool1(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustBool3(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustBool_(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustBool1(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustBool3(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotBool_(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotBool1(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotBool3(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotBool_(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotBool1(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotBool3(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustBool$(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$0(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$1(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$2(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$3(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$0(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$1(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$2(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustBool$3(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte_(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
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
	default @Nonnull SELF mustByte1(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustByte3(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustByte_(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustByte1(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustByte3(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotByte_(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotByte1(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotByte3(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotByte_(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotByte1(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotByte3(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustByte$(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$0(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$1(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$2(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$3(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$0(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$1(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$2(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustByte$3(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl_(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
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
	default @Nonnull SELF mustDbl1(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustDbl3(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustDbl_(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustDbl1(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustDbl3(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotDbl_(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotDbl1(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotDbl3(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotDbl_(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotDbl1(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotDbl3(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustDbl$(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$0(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$1(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$2(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$3(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$0(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$1(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$2(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustDbl$3(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar_(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
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
	default @Nonnull SELF mustChar1(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustChar3(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustChar_(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustChar1(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustChar3(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotChar_(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotChar1(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotChar3(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotChar_(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotChar1(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotChar3(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustChar$(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$0(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$1(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$2(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$3(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$0(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$1(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$2(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustChar$3(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt_(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
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
	default @Nonnull SELF mustSrt1(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustSrt3(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustSrt_(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustSrt1(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustSrt3(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotSrt_(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotSrt1(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotSrt3(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotSrt_(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotSrt1(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotSrt3(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustSrt$(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$0(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$1(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$2(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$3(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$0(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$1(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$2(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustSrt$3(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt_(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
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
	default @Nonnull SELF mustFlt1(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustFlt3(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustFlt_(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustFlt1(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustFlt3(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotFlt_(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotFlt1(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotFlt3(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotFlt_(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotFlt1(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotFlt3(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustFlt$(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$0(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$1(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$2(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$3(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$0(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$1(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$2(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustFlt$3(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt_(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
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
	default @Nonnull SELF mustInt1(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustInt3(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustInt_(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustInt1(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustInt3(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotInt_(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotInt1(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotInt3(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotInt_(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotInt1(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotInt3(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustInt$(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$0(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$1(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$2(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$3(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$0(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$1(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$2(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustInt$3(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong_(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
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
	default @Nonnull SELF mustLong1(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustLong3(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustLong_(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustLong1(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustLong3(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotLong_(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
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
	default @Nonnull SELF mustNotLong1(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotLong3(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustNotLong_(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
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
	default @Nonnull SELF mustNotLong1(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustNotLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustNotLong3(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF mustLong$(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$0(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$1(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$2(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$3(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$0(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$1(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$2(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF mustLong$3(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must_(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
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
	default @Nonnull <V> SELF must1(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF must2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF must3(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF must_(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
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
	default @Nonnull <V> SELF must1(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF must2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF must3(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustNot_(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustNot1(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustNot2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustNot3(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF mustNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
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
	default @Nonnull <V> SELF mustNot1(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V> SELF mustNot2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V> SELF mustNot3(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V> SELF must$(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$0(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$1(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$2(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$3(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$0(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$1(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$2(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V> SELF must$3(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must_(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must1(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must2(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF must_(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must1(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must2(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF mustNot_(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF mustNot1(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF mustNot2(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF mustNot3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF mustNot_(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF mustNot1(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF mustNot2(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF mustNot3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF must$(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3) {
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
	default @Nonnull <V2, V3> SELF must$0(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must$1(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must$2(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must$3(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF must$(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate) {
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
	default @Nonnull <V2, V3> SELF must$0(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must$1(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must$2(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must$3(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3, V4> SELF must_(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must1(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must2(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must3(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must_(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must1(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must2(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must3(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot_(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot1(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot2(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot3(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot_(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot1(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot2(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot3(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$0(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$1(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$2(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$3(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$0(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$1(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$2(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must$3(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith_(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith1(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith3(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWith_(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith1(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith3(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustNotWith_(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustNotWith1(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustNotWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustNotWith3(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustNotWith_(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustNotWith1(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustNotWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustNotWith3(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWith$(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1) {
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
	default @Nonnull <V1> SELF mustWith$0(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith$1(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith$2(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith$3(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWith$(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull <V1> SELF mustWith$0(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith$1(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith$2(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith$3(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith_(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith1(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith2(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith_(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith1(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith2(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustNotWith_(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustNotWith1(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustNotWith2(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustNotWith_(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustNotWith1(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustNotWith2(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2) {
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
	default @Nonnull <V1, V2> SELF mustWith$0(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith$1(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith$2(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith$3(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull <V1, V2> SELF mustWith$0(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith$1(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith$2(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith$3(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
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
	default @Nonnull SELF uniMustWith_(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith1(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith3(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith_(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith1(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith3(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith_(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith1(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith3(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith_(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith1(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith3(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$0(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$1(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$2(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$3(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$0(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$1(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$2(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	/**   */
	default @Nonnull SELF uniMustWith$3(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return fluentCtx();
	}

	default @Nonnull SELF checkWhen(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull <T2> SELF checkWhen(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull <T2> SELF checkWhenNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull <T2, T3> SELF checkWhen(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	default @Nonnull <T2, T3> SELF checkWhenNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(fluentCtx());
		};
		return fluentCtx();
	}

	// </editor-fold>

	default @Nonnull SELF checkBool(@Nonnull LPredicate<T> func, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkBool(@Nullable String name, @Nonnull LPredicate<T> func, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkBool(@Nonnull LBiPredicate<T, K1> func, K1 a2, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkBool(@Nullable String name, @Nonnull LBiPredicate<T, K1> func, K1 a2, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2> SELF checkBool(@Nonnull LTriPredicate<T, K1, K2> func, K1 a2, K2 a3, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2, a3), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2> SELF checkBool(@Nullable String name, @Nonnull LTriPredicate<T, K1, K2> func, K1 a2, K2 a3, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2, a3), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2, K3> SELF checkBool(@Nonnull LQuadPredicate<T, K1, K2, K3> func, K1 a2, K2 a3, K3 a4, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2, a3, a4), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2, K3> SELF checkBool(@Nullable String name, @Nonnull LQuadPredicate<T, K1, K2, K3> func, K1 a2, K2 a3, K3 a4, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get(), a2, a3, a4), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkByte(@Nonnull LToByteFunction<T> func, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkByte(@Nullable String name, @Nonnull LToByteFunction<T> func, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkByte(@Nonnull LToByteBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkByte(@Nullable String name, @Nonnull LToByteBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkDbl(@Nonnull LToDblFunction<T> func, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkDbl(@Nullable String name, @Nonnull LToDblFunction<T> func, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkDbl(@Nonnull LToDblBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkDbl(@Nullable String name, @Nonnull LToDblBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkChar(@Nonnull LToCharFunction<T> func, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkChar(@Nullable String name, @Nonnull LToCharFunction<T> func, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkChar(@Nonnull LToCharBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkChar(@Nullable String name, @Nonnull LToCharBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkSrt(@Nonnull LToSrtFunction<T> func, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkSrt(@Nullable String name, @Nonnull LToSrtFunction<T> func, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkSrt(@Nonnull LToSrtBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkSrt(@Nullable String name, @Nonnull LToSrtBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkFlt(@Nonnull LToFltFunction<T> func, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkFlt(@Nullable String name, @Nonnull LToFltFunction<T> func, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkFlt(@Nonnull LToFltBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkFlt(@Nullable String name, @Nonnull LToFltBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkInt(@Nonnull LToIntFunction<T> func, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkInt(@Nullable String name, @Nonnull LToIntFunction<T> func, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkInt(@Nonnull LToIntBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkInt(@Nullable String name, @Nonnull LToIntBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2> SELF checkInt(@Nonnull LToIntTriFunction<T, K1, K2> func, K1 a2, K2 a3, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get(), a2, a3), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2> SELF checkInt(@Nullable String name, @Nonnull LToIntTriFunction<T, K1, K2> func, K1 a2, K2 a3, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get(), a2, a3), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkLong(@Nonnull LToLongFunction<T> func, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull SELF checkLong(@Nullable String name, @Nonnull LToLongFunction<T> func, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkLong(@Nonnull LToLongBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1> SELF checkLong(@Nullable String name, @Nonnull LToLongBiFunction<T, K1> func, K1 a2, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R> SELF check(@Nonnull LFunction<T, R> func, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R> SELF check(@Nullable String name, @Nonnull LFunction<T, R> func, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R, C extends CheckTrait<? super R, C>> SELF check(@Nonnull LFunction<T, R> func, @Nonnull LFunction<R, C> factory, LConsumer<C> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(checks, "checks");
		checks.accept(factory.apply(func.apply(get())));
		return fluentCtx();
	}

	default @Nonnull <R, K1> SELF check(@Nonnull LBiFunction<T, K1, R> func, K1 a2, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R, K1> SELF check(@Nullable String name, @Nonnull LBiFunction<T, K1, R> func, K1 a2, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, R, C extends CheckTrait<? super R, C>> SELF check(@Nonnull LBiFunction<T, K1, R> func, K1 a2, @Nonnull LFunction<R, C> factory, LConsumer<C> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(checks, "checks");
		checks.accept(factory.apply(func.apply(get(), a2)));
		return fluentCtx();
	}

	default @Nonnull <R, K1, K2> SELF check(@Nonnull LTriFunction<T, K1, K2, R> func, K1 a2, K2 a3, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2, a3), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R, K1, K2> SELF check(@Nullable String name, @Nonnull LTriFunction<T, K1, K2, R> func, K1 a2, K2 a3, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2, a3), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2, R, C extends CheckTrait<? super R, C>> SELF check(@Nonnull LTriFunction<T, K1, K2, R> func, K1 a2, K2 a3, @Nonnull LFunction<R, C> factory, LConsumer<C> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(checks, "checks");
		checks.accept(factory.apply(func.apply(get(), a2, a3)));
		return fluentCtx();
	}

	default @Nonnull <R, K1, K2, K3> SELF check(@Nonnull LQuadFunction<T, K1, K2, K3, R> func, K1 a2, K2 a3, K3 a4, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2, a3, a4), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <R, K1, K2, K3> SELF check(@Nullable String name, @Nonnull LQuadFunction<T, K1, K2, K3, R> func, K1 a2, K2 a3, K3 a4, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get(), a2, a3, a4), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return fluentCtx();
	}

	default @Nonnull <K1, K2, K3, R, C extends CheckTrait<? super R, C>> SELF check(@Nonnull LQuadFunction<T, K1, K2, K3, R> func, K1 a2, K2 a3, K3 a4, @Nonnull LFunction<R, C> factory, LConsumer<C> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(checks, "checks");
		checks.accept(factory.apply(func.apply(get(), a2, a3, a4)));
		return fluentCtx();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		return this.must(__ -> false, newMessage);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		return this.must1(__ -> false, newMessage, messageParams);
	}

}
