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

	public static <T1, T2> void doFor(@Nonnull Class<T1> clazz, @Nullable Object instance, T2 t2, @Nonnull LBiConsumer<? super T1, ? super T2> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			function.accept((T1) instance, t2);
		}
	}

	@Nullable
	public static <T, R> R from(@Nonnull Class<T> clazz, @Nullable Object instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T) instance);
		}
		return null;
	}

	@Nullable
	public static <T1, T2, R> R from(@Nonnull Class<T1> clazz, @Nullable Object instance, T2 t2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		if (clazz.isInstance(instance)) {
			return function.apply((T1) instance, t2);
		}
		return null;
	}

	public static <T, R> Opt<R> optionalFrom(@Nonnull Class<T> clazz, @Nullable Object instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, function));
	}

	public static <T1, T2, R> Opt<R> optionalFrom(@Nonnull Class<T1> clazz, @Nullable Object instance, T2 t2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return Opt.of(from(clazz, instance, t2, function));
	}

	// <editor-fold desc="in some case's helps with compiler">

	public static <T, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		return clazz.isInstance(instance) ? (R) instance : function.apply(instance);
	}

	public static <T, R> R optionalAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, @Nonnull LFunction<? super T, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, function);
	}

	public static <T, H, R> R assuredClass(@Nonnull Class<R> clazz, @Nonnull T instance, H helper, @Nonnull LBiFunction<? super T, ? super H, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(instance, "instance");
		Null.nonNullArg(function, "function");
		return clazz.isInstance(instance) ? (R) instance : function.apply(instance, helper);
	}

	public static <T, H, R> R optionalAssuredClass(@Nonnull Class<R> clazz, @Nullable T instance, H helper, @Nonnull LBiFunction<? super T, ? super H, ? extends R> function) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(function, "function");
		return instance == null ? null : assuredClass(clazz, instance, helper, function);
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
}
