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
	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull LFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull LFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get()));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get())) {
			var params = new Object[]{get(), null, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format1UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustEx(@Nonnull LFunction<? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF mustEx(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF mustEx(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF mustEx(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF mustEx(@Nonnull LFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot2(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2)) {
			var params = new Object[]{get(), a2, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2) {
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
	default @Nonnull SELF uniMust2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message) {
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
	default @Nonnull SELF uniMust2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T a2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust2Ex(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF uniMust2Ex(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust2Ex(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust2Ex(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust2Ex(T a2, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot3(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust3Ex(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3) {
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
	default @Nonnull SELF uniMust3Ex(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message) {
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
	default @Nonnull SELF uniMust3Ex(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust3Ex(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3Ex(@Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust3Ex(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull SELF uniMust3Ex(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull SELF uniMust3Ex(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull SELF uniMust3Ex(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull SELF uniMust3Ex(T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull SELF uniMust4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNot4(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(@Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMust4Ex(T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LObjBoolFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotBool2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(@Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, boolean v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustBool2Ex(boolean v, @Nonnull LObjBoolFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LObjByteFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotByte2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(@Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, byte v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustByte2Ex(byte v, @Nonnull LObjByteFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LObjDblFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotDbl2(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(@Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, double v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustDbl2Ex(double v, @Nonnull LObjDblFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LObjCharFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotChar2(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(@Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, char v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustChar2Ex(char v, @Nonnull LObjCharFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LObjSrtFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotSrt2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(@Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, short v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustSrt2Ex(short v, @Nonnull LObjSrtFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LObjFltFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotFlt2(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(@Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, float v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustFlt2Ex(float v, @Nonnull LObjFltFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LOiFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotInt2(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(@Nonnull LOiFunction<? super T, ? extends String> specialPredicate, int v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustInt2Ex(int v, @Nonnull LOiFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LObjLongFunction<? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF mustNotLong2(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(@Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, long v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF mustLong2Ex(long v, @Nonnull LObjLongFunction<? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LBiFunction<? super T, ? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), v)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), v));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V> SELF mustNot2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), v)) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(@Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, V v, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V> SELF must2Ex(V v, @Nonnull LBiFunction<? super T, ? super V, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), v);
		if (msg != null) {
			var params = new Object[]{get(), v, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF mustNot3(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3)) {
			var params = new Object[]{get(), a2, a3, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V2, V3> SELF must3Ex(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3) {
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
	default @Nonnull <V2, V3> SELF must3Ex(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must3Ex(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must3Ex(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must3Ex(@Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3> SELF must3Ex(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate) {
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
	default @Nonnull <V2, V3> SELF must3Ex(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V2, V3> SELF must3Ex(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V2, V3> SELF must3Ex(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V2, V3> SELF must3Ex(V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V2, V3, V4> SELF must4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(get(), a2, a3, a4)) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(get(), a2, a3, a4));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF mustNot4(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(get(), a2, a3, a4)) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(@Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(get(), a2, a3, a4);
		if (msg != null) {
			var params = new Object[]{get(), a2, a3, a4, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format4MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V2, V3, V4> SELF must4Ex(V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LBiFunction<? super V1, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustNotWith2(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, get())) {
			var params = new Object[]{get(), with1, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1> SELF mustWith2Ex(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1) {
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
	default @Nonnull <V1> SELF mustWith2Ex(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith2Ex(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith2Ex(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith2Ex(@Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, V1 with1, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1> SELF mustWith2Ex(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull <V1> SELF mustWith2Ex(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1> SELF mustWith2Ex(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1> SELF mustWith2Ex(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1> SELF mustWith2Ex(V1 with1, @Nonnull LBiFunction<? super V1, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
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
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with1, with2, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with1, with2, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustNotWith3(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with1, with2, get())) {
			var params = new Object[]{get(), with1, with2, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format3UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(@Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
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
	default @Nonnull <V1, V2> SELF mustWith3Ex(V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (!predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LBiFunction<? super T, ? super T, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (predicate.test(with, get())) {
			throw Handling.create(checkTraitFactory(), msgFunc.apply(with, get()));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
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
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustNotWith2(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(message, "message");
		if (predicate.test(with, get())) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), null, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2UM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(@Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, T with, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2M(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, params);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	/**   */
	default @Nonnull SELF uniMustWith2Ex(T with, @Nonnull LBiFunction<? super T, ? super T, ? extends String> specialPredicate, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(message, "message");
		@Nonnull
		String msg = specialPredicate.apply(with, get());
		if (msg != null) {
			var params = new Object[]{get(), with, null, null, checkTraitType(), checkTraitName(), msg, null};
			params[params.length - 1] = format(message, param1, param2, param3);
			throw Handling.shoveIt(Handling.create(checkTraitFactory(), format(verbosity().format2MUM(), params)));
		}
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2> SELF checkWhen(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2> SELF checkWhenNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2, T3> SELF checkWhen(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2, T3> SELF checkWhenNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF checkBool(@Nonnull LPredicate<T> func, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkBool(@Nonnull LPredicate<T> func, @Nullable String name, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckBool(func.test(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkByte(@Nonnull LToByteFunction<T> func, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkByte(@Nonnull LToByteFunction<T> func, @Nullable String name, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckByte(func.applyAsByte(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkDbl(@Nonnull LToDblFunction<T> func, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkDbl(@Nonnull LToDblFunction<T> func, @Nullable String name, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckDbl(func.applyAsDbl(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkChar(@Nonnull LToCharFunction<T> func, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkChar(@Nonnull LToCharFunction<T> func, @Nullable String name, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckChar(func.applyAsChar(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkSrt(@Nonnull LToSrtFunction<T> func, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkSrt(@Nonnull LToSrtFunction<T> func, @Nullable String name, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckSrt(func.applyAsSrt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkFlt(@Nonnull LToFltFunction<T> func, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkFlt(@Nonnull LToFltFunction<T> func, @Nullable String name, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckFlt(func.applyAsFlt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkInt(@Nonnull LToIntFunction<T> func, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkInt(@Nonnull LToIntFunction<T> func, @Nullable String name, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckInt(func.applyAsInt(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkLong(@Nonnull LToLongFunction<T> func, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF checkLong(@Nonnull LToLongFunction<T> func, @Nullable String name, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.CheckLong(func.applyAsLong(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull <R> SELF check(@Nonnull LFunction<T, R> func, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get()), "?", checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull <R> SELF check(@Nonnull LFunction<T, R> func, @Nullable String name, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(new Checks.Check<R>(func.apply(get()), name, checkTraitFactory(), checkTraitType(), verbosity()));
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		throw Handling.create(checkTraitFactory(), newMessage);
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		throw Handling.create(checkTraitFactory(), newMessage, messageParams);
	}

}
