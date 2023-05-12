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

package eu.lunisolar.magma.func.supp;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.lang.reflect.*; // NOSONAR
import java.util.*; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

import static eu.lunisolar.magma.func.function.LBiFunction.tryApplyThen; //NOSONAR

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
 */
public final class Clazz {
	// <editor-fold desc="no instance">
	private Clazz() {
	}
	// </editor-fold>

	public static <T> void doFor(@Nonnull Class<T> clazz, @Nullable Object instance, @Nonnull LConsumer<? super T> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			function.accept((T) instance);
		}
	}

	public static <T, H1> void doFor(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, @Nonnull LBiConsumer<? super T, ? super H1> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			function.accept((T) instance, a2);
		}
	}

	public static <T, H1, H2> void doFor(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, @Nonnull LTriConsumer<? super T, ? super H1, ? super H2> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			function.accept((T) instance, a2, a3);
		}
	}

	public static <T, H1, H2, H3> void doFor(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, H3 a4, @Nonnull LQuadConsumer<? super T, ? super H1, ? super H2, ? super H3> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			function.accept((T) instance, a2, a3, a4);
		}
	}

	public static @Nullable <T, R> R from(@Nonnull Class<T> clazz, @Nullable Object instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T) instance);
		}
		return null;
	}

	public static @Nonnull <T, R> Opt<R> optionalFrom(@Nonnull Class<T> clazz, @Nullable Object instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, function));
	}

	public static @Nullable <T, H1, R> R from(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, @Nonnull LBiFunction<? super T, ? super H1, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T) instance, a2);
		}
		return null;
	}

	public static @Nonnull <T, H1, R> Opt<R> optionalFrom(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, @Nonnull LBiFunction<? super T, ? super H1, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, a2, function));
	}

	public static @Nullable <T, H1, H2, R> R from(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, @Nonnull LTriFunction<? super T, ? super H1, ? super H2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T) instance, a2, a3);
		}
		return null;
	}

	public static @Nonnull <T, H1, H2, R> Opt<R> optionalFrom(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, @Nonnull LTriFunction<? super T, ? super H1, ? super H2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, a2, a3, function));
	}

	public static @Nullable <T, H1, H2, H3, R> R from(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, H3 a4, @Nonnull LQuadFunction<? super T, ? super H1, ? super H2, ? super H3, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T) instance, a2, a3, a4);
		}
		return null;
	}

	public static @Nonnull <T, H1, H2, H3, R> Opt<R> optionalFrom(@Nonnull Class<T> clazz, @Nullable Object instance, H1 a2, H2 a3, H3 a4, @Nonnull LQuadFunction<? super T, ? super H1, ? super H2, ? super H3, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, a2, a3, a4, function));
	}

	// <editor-fold desc="in some case's helps with compiler">

	public static @Nonnull <T, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		var retval = clazz.isInstance(instance) ? (R) instance : function.apply(instance);
		return Objects.requireNonNull(retval);
	}

	public static @Nullable <T, R> R nullableAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, function);
	}

	public static @Nonnull <T, H1, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, H1 a2, @Nonnull LBiFunction<? super T, ? super H1, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		var retval = clazz.isInstance(instance) ? (R) instance : function.apply(instance, a2);
		return Objects.requireNonNull(retval);
	}

	public static @Nullable <T, H1, R> R nullableAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, H1 a2, @Nonnull LBiFunction<? super T, ? super H1, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, a2, function);
	}

	public static @Nonnull <T, H1, H2, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, H1 a2, H2 a3, @Nonnull LTriFunction<? super T, ? super H1, ? super H2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		var retval = clazz.isInstance(instance) ? (R) instance : function.apply(instance, a2, a3);
		return Objects.requireNonNull(retval);
	}

	public static @Nullable <T, H1, H2, R> R nullableAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, H1 a2, H2 a3, @Nonnull LTriFunction<? super T, ? super H1, ? super H2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, a2, a3, function);
	}

	public static @Nonnull <T, H1, H2, H3, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, H1 a2, H2 a3, H3 a4, @Nonnull LQuadFunction<? super T, ? super H1, ? super H2, ? super H3, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		var retval = clazz.isInstance(instance) ? (R) instance : function.apply(instance, a2, a3, a4);
		return Objects.requireNonNull(retval);
	}

	public static @Nullable <T, H1, H2, H3, R> R nullableAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, H1 a2, H2 a3, H3 a4, @Nonnull LQuadFunction<? super T, ? super H1, ? super H2, ? super H3, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, a2, a3, a4, function);
	}

	// </editor-fold>

	// <editor-fold desc="casts">

	public static <T> T the(Object o) {
		return (T) o;
	}

	public static <T> Class<T> theClass(Class o) {
		return (Class<T>) o;
	}

	public static <T, E extends T> E extend(T o) {
		return (E) o;
	}

	public static <CT extends Class<T>, CE extends Class<E>, T, E extends T> CE extend(CT o) {
		return (CE) o;
	}

	public static <T, E extends T> T shorten(E o) {
		return o;
	}

	public static <CT extends Class<T>, CE extends Class<E>, T, E extends T> CT shorten(CE o) {
		return (CT) o;
	}

	@Deprecated
	public static <CT extends Class<T>, CE extends Class<? super T>, T> CT supers(CE o) {
		return (CT) o;
	}

	// </editor-fold>

	// <editor-fold desc="enum">

	public static @Nonnull <T extends Enum<T>> Opt<T> aEnum(@Nonnull Class<T> enumClazz, @Nullable Object rawValue, LFunction<Throwable, T> noMatchHandler) {
		return Opt.obj(Clazz.nullableAssuredClass(enumClazz, rawValue, enumClazz, noMatchHandler, (rv, ec, nmh) -> Opt.obj(rv.toString()).filterAndMap(String.class).map(str -> tryApplyThen(ec, str, Enum::valueOf, nmh)).orElse(null)));
	}

	public static @Nonnull <T extends Enum<T>> T theEnum(@Nonnull Class<T> enumClazz, @Nullable Object rawValue) {
		return aEnum(enumClazz, rawValue, x -> {
			throw x;
		}).orElseThrow(X::state, "No value found.");
	}

	// </editor-fold>

}
