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

package eu.lunisolar.magma.func.supp.opt;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.value.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

import static eu.lunisolar.magma.func.supp.MsgVerbosity.*; // NOSONAR

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
 * Extension over the Optional/OptionalInt/OptionalLong/OptionalDouble. You can use them as 'optional' response. But this class mainly aims to provide more
 * flexible possibility to have further transformations.
 *
 * Why?
 * - To introduce more possibility for cases where simply referencing existing method (that can be provided by editor completion) just will make the job done.
 * - That means more possibility to build 'sentences' that actually tell what happens.
 * - Performance wise, if use correctly, both Java's Optional and this class work similar way. Given that escape analysis, and 'stack allocation' will not be
 * blocked to provide full optimization, even capturing lambdas will be fully optimized by JVM. So 'allocating" and using Optional/Opt locally is not as much
 * costly as one would expected (in correct circumstances).
 */
@ThreadSafe
public final class OptBool extends OptBoolBase<OptBool> {

	private static final OptBool EMPTY = new OptBool();
	private static final OptBool TRUE = new OptBool(true);
	private static final OptBool FALSE = new OptBool(false);

	//<editor-fold desc="factories">

	private OptBool() {
		super();
	}

	private OptBool(boolean value) {
		super(value);
	}

	public @Nonnull OptBool value(boolean value) {
		return of(value);
	}

	public @Nonnull OptBool voidValue() {
		return empty();
	}

	public static OptBool empty() {
		return EMPTY;
	}

	public static OptBool from(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptBool.class, opt, o -> o.isPresent() ? OptBool.of(o.get()) : OptBool.empty());
	}

	public static OptBool from(Boolean value) {
		return value == null ? empty() : value ? TRUE : FALSE;
	}

	public static OptBool of(boolean value) {
		return value ? TRUE : FALSE;
	}

	public static OptBool valueOf(boolean value) {
		return of(value);
	}

	public static OptBool valueOf(boolean value, LLogicalOperator predicate) {
		return predicate.apply(value) ? of(value) : empty();
	}

	//</editor-fold>

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull OptBool safelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LBoolSupplier producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.valueOf(producer.getAsBool());
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return OptBool.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull OptBool safelyFrom(@Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBoolSupplier producer) {
		return safelyFrom(produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull OptBool safelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBoolSupplier producer) {
		return safelyFrom(produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull OptBool shovingSafelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBoolSupplier producer) {
		return safelyFrom(produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K> OptBool safelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LPredicate<K> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.valueOf(producer.test(a));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return OptBool.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K> OptBool safelyFrom(K a, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LPredicate<K> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K> OptBool safelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LPredicate<K> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K> OptBool shovingSafelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LPredicate<K> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K1, K2> OptBool safelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LBiPredicate<K1, K2> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.valueOf(producer.test(a1, a2));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return OptBool.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K1, K2> OptBool safelyFrom(K1 a1, K2 a2, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiPredicate<K1, K2> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K1, K2> OptBool safelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiPredicate<K1, K2> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K1, K2> OptBool shovingSafelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiPredicate<K1, K2> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K1, K2, K3> OptBool safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LTriPredicate<K1, K2, K3> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.valueOf(producer.test(a1, a2, a3));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return OptBool.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K1, K2, K3> OptBool safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriPredicate<K1, K2, K3> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K1, K2, K3> OptBool safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriPredicate<K1, K2, K3> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K1, K2, K3> OptBool shovingSafelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriPredicate<K1, K2, K3> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	public static OptBool safelyParse(String str) {
		if (str == null) {
			return OptBool.empty();
		}

		if ("true".equalsIgnoreCase(str)) {
			return OptBool.of(true);
		}

		if ("false".equalsIgnoreCase(str)) {
			return OptBool.of(false);
		}

		return OptBool.empty();
	}

}
