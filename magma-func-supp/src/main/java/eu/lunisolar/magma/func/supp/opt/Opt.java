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
public final class Opt<T> extends OptBase<T, Opt<T>> {

	private static final Opt<?> EMPTY = new Opt();

	// <editor-fold desc="factories">

	private Opt() {
		super();
	}

	private Opt(@Nullable T value) {
		super(value);
	}

	public @Nonnull Opt<T> value(@Nullable T value) {
		return of(value);
	}

	public @Nonnull Opt<T> voidValue() {
		return empty();
	}

	public static <T> Opt<T> empty() {
		return (Opt) EMPTY;
	}

	public static OptBool from(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptBool.class, opt, o -> o.isPresent() ? OptBool.of(o.get()) : OptBool.empty());
	}

	public static <T> Opt<T> from(@Nonnull ValueTrait<? extends T, ?> source) {
		Null.nonNullArg(source, "source");
		return Clazz.assuredClass(Opt.class, source, o -> Opt.obj(source.nullable()));
	}

	public static OptByte from(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptByte.class, opt, o -> o.isPresent() ? OptByte.of(o.get()) : OptByte.empty());
	}

	public static OptDbl from(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptDbl.class, opt, o -> o.isPresent() ? OptDbl.of(o.get()) : OptDbl.empty());
	}

	public static OptChar from(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptChar.class, opt, o -> o.isPresent() ? OptChar.of(o.get()) : OptChar.empty());
	}

	public static OptSrt from(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptSrt.class, opt, o -> o.isPresent() ? OptSrt.of(o.get()) : OptSrt.empty());
	}

	public static OptFlt from(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptFlt.class, opt, o -> o.isPresent() ? OptFlt.of(o.get()) : OptFlt.empty());
	}

	public static OptInt from(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptInt.class, opt, o -> o.isPresent() ? OptInt.of(o.get()) : OptInt.empty());
	}

	public static OptLong from(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptLong.class, opt, o -> o.isPresent() ? OptLong.of(o.get()) : OptLong.empty());
	}

	public static <T> Opt<T> of(@Nullable T value) {
		return value == null ? empty() : new Opt(value);
	}

	public static <T> Opt<T> obj(@Nullable T value) {
		return value == null ? empty() : new Opt(value);
	}

	public static <T> Opt<T> opt(@Nullable T value) {
		return value == null ? empty() : new Opt(value);
	}

	public static OptByte of(byte value) {
		return OptByte.of(value);
	}

	public static OptByte valueOf(byte value) {
		return of(value);
	}

	public static OptSrt of(short value) {
		return OptSrt.of(value);
	}

	public static OptSrt valueOf(short value) {
		return of(value);
	}

	public static OptInt of(int value) {
		return OptInt.of(value);
	}

	public static OptInt valueOf(int value) {
		return of(value);
	}

	public static OptLong of(long value) {
		return OptLong.of(value);
	}

	public static OptLong valueOf(long value) {
		return of(value);
	}

	public static OptFlt of(float value) {
		return OptFlt.of(value);
	}

	public static OptFlt valueOf(float value) {
		return of(value);
	}

	public static OptDbl of(double value) {
		return OptDbl.of(value);
	}

	public static OptDbl valueOf(double value) {
		return of(value);
	}

	public static OptChar of(char value) {
		return OptChar.of(value);
	}

	public static OptChar valueOf(char value) {
		return of(value);
	}

	public static OptBool of(boolean value) {
		return OptBool.of(value);
	}

	public static OptBool valueOf(boolean value) {
		return of(value);
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> boolean r_(OptBoolTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */

	public static <T> T r_(OptTrait<T, ?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> byte r_(OptByteTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> double r_(OptDblTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> char r_(OptCharTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> short r_(OptSrtTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> float r_(OptFltTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> int r_(OptIntTrait<?> optional) {
		return optional.get();
	}

	/** Just to shorten one line in method implementations that just check or/and map or/and return content of optional. */
	public static <SELF> long r_(OptLongTrait<?> optional) {
		return optional.get();
	}

	public static <T> Opt<T> valueOf(@Nonnull T value, LPredicate<T> predicate) {
		return predicate.test(value) ? of(value) : empty();
	}

	public static <T> Opt<T> from(@Nonnull Optional<T> optional) {
		Null.nonNullArg(optional, "optional");
		return optional.isPresent() ? Opt.of(optional.get()) : empty();
	}

	// </editor-fold>

	public @Nonnull <R> Opt<R> mustBeInstanceOf(@Nonnull Class<R> clazz, @Nonnull String message) {
		Null.nonNullArg(clazz, "clazz");
		return (Opt) must2(Be::instanceOf, clazz, message);
	}

	public @Nonnull <R> Opt<R> mustBeInstanceOf(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		var nullable = nullable();
		return (Opt) must2(Be::instanceOf, clazz, "Value <%s> must be instance of class <%s> but is not.");
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <T> Opt<T> safelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LSupplier<T> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.obj(producer.get());
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return Opt.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <T> Opt<T> safelyFrom(@Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LSupplier<T> producer) {
		return safelyFrom(produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <T> Opt<T> safelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LSupplier<T> producer) {
		return safelyFrom(produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <T> Opt<T> shovingSafelyFrom(@Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LSupplier<T> producer) {
		return safelyFrom(produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K, T> Opt<T> safelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LFunction<K, T> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.obj(producer.apply(a));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return Opt.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K, T> Opt<T> safelyFrom(K a, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LFunction<K, T> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K, T> Opt<T> safelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LFunction<K, T> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K, T> Opt<T> shovingSafelyFrom(K a, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LFunction<K, T> producer) {
		return safelyFrom(a, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K1, K2, T> Opt<T> safelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LBiFunction<K1, K2, T> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.obj(producer.apply(a1, a2));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return Opt.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K1, K2, T> Opt<T> safelyFrom(K1 a1, K2 a2, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiFunction<K1, K2, T> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K1, K2, T> Opt<T> safelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiFunction<K1, K2, T> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K1, K2, T> Opt<T> shovingSafelyFrom(K1 a1, K2 a2, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LBiFunction<K1, K2, T> producer) {
		return safelyFrom(a1, a2, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are handled with function, that should either throw exception or return one to be thrown. */
	public static @Nonnull <K1, K2, K3, T> Opt<T> safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull ExWMF<RuntimeException> elseHandler, @Nonnull LTriFunction<K1, K2, K3, T> producer) {
		Null.nonNullArg(produceEmpty, "produceEmpty");
		Null.nonNullArg(elseHandler, "elseHandler");
		Null.nonNullArg(producer, "producer");

		try {
			return Opt.obj(producer.apply(a1, a2, a3));
		} catch (Throwable e) {
			Handling.handleErrors(e);
			if (produceEmpty.test(e)) {
				return Opt.empty();
			}
			throw elseHandler.produce(e.getMessage(), e);
		}
	}

	public static @Nonnull <K1, K2, K3, T> Opt<T> safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull ExWF<RuntimeException> elseHandler, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriFunction<K1, K2, K3, T> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> elseHandler.produce(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are re-throw (only runtime exceptions) or nested (checked exceptions). */
	public static @Nonnull <K1, K2, K3, T> Opt<T> safelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriFunction<K1, K2, K3, T> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> Handling.nestCheckedAndThrow(e), producer);
	}

	/** Tries to produce optional value. Any exception tested positively with predicate produces empty optional. Others are shoved as is (undeclared checked exception that can be catch directly). */
	public static @Nonnull <K1, K2, K3, T> Opt<T> shovingSafelyFrom(K1 a1, K2 a2, K3 a3, @Nonnull LPredicate<Throwable> produceEmpty, @Nonnull LTriFunction<K1, K2, K3, T> producer) {
		return safelyFrom(a1, a2, a3, produceEmpty, (s, e) -> Handling.shoveIt(e), producer);
	}

}
